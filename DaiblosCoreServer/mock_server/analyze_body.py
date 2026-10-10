#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Analyze the 397B /sdk/init body captured in requests.log (MJ-FAIL hex)."""
import gzip, json, re, struct, zlib
try:
    from Crypto.Cipher import AES
except ImportError:
    AES = None

LOG = r"D:\Code\github\DataTable\DaiblosCoreServer\mock_server\requests.log"
_m = re.search(r"\[MJ-FAIL\] (/sdk/init\S*) body\((\d+)B\) hex\(head 2KB\): ([0-9a-f]+)", open(LOG, encoding="utf-8").read())
assert _m, "MJ-FAIL /sdk/init hex not found in requests.log"
path, blen, hexstr = _m.group(1), int(_m.group(2)), _m.group(3)[: int(_m.group(2)) * 2]
print(f"path={path} declared_len={blen} hex_chars={len(_m.group(3))} use={len(hexstr)}")

KEYS = [
    b"KGJDkn689Fu3JAnd",   # KEY_CF_ANDROID
    b"KLJDkn789lu1J9A1",   # KEY_ANDROID
    b"FYJDkG553WK3JLan",   # KEY_LANGUAGE
    b"C76C2D61DBD43087",   # sdkKey
    b"HLvDmnv4Qqu0PX5w",   # testSdkKey
]

data = bytes.fromhex(hexstr)
print(f"len={len(data)}  len%16={len(data)%16}")
print("head 32:", data[:32].hex(" "))
print("tail 16:", data[-16:].hex(" "))


def json_ok(b: bytes):
    try:
        o = json.loads(b.decode("utf-8").strip())
        return isinstance(o, (dict, list))
    except Exception:
        return False


def strip_pad(pt: bytes) -> bytes:
    if pt and len(pt) % 16 == 0 and 0 < pt[-1] <= 16 and pt.endswith(bytes([pt[-1]]) * pt[-1]):
        return pt[:-pt[-1]]
    return pt.rstrip(b"\n").rstrip(b"\x00")


# --- 1) raw deflate / zlib / gzip at every offset ---
for off in range(0, 6):
    for wbits, name in ((-15, "raw-deflate"), (15, "zlib"), (31, "gzip")):
        try:
            out = zlib.decompress(data[off:], wbits)
            print(f"[HIT] off={off} {name}: {out[:120]!r}")
        except Exception:
            pass

# --- 2) AES at every 16-aligned window (skip N head bytes / explicit IV) ---
for off in range(0, 32):
    rest = data[off:]
    if len(rest) < 16:
        continue
    body = rest[: len(rest) // 16 * 16]
    for ki, key in enumerate(KEYS):
        cands = [
            ("CBC(iv=key)", AES.new(key, AES.MODE_CBC, key)),
            ("CBC(iv=0)", AES.new(key, AES.MODE_CBC, b"\x00" * 16)),
            ("ECB", AES.new(key, AES.MODE_ECB)),
        ]
        if len(rest) >= 32:  # first 16B of window as IV
            cands.append(("CBC(iv=head16)", AES.new(key, AES.MODE_CBC, rest[:16])))
        for name, c in cands:
            try:
                pt = strip_pad(c.decrypt(body if name != "CBC(iv=head16)" else rest[16:16 + (len(rest) - 16) // 16 * 16]))
                if json_ok(pt):
                    print(f"[HIT] AES off={off} key#{ki}({key[:5]}) {name}: {pt[:150]!r}")
            except Exception:
                pass

# --- 3) snappy raw decoder ---
def read_varint(buf, i):
    shift = val = 0
    while i < len(buf):
        b = buf[i]; i += 1
        val |= (b & 0x7F) << shift
        if not b & 0x80:
            return val, i
        shift += 7
    raise ValueError("varint overrun")


def snappy_decode(buf, with_preamble=True):
    i = read_varint(buf, 0)[1] if with_preamble else 0
    out = bytearray()
    while i < len(buf):
        tag = buf[i]
        t = tag & 3
        if t == 0:
            ln = tag >> 2; i += 1
            if ln >= 60:
                extra = ln - 59
                ln = int.from_bytes(buf[i:i + extra], "little"); i += extra
            ln += 1
            out += buf[i:i + ln]; i += ln
        else:
            if t == 1:
                ln = ((tag >> 2) & 7) + 4
                off = ((tag >> 5) << 8) | buf[i + 1]; i += 2
            elif t == 2:
                ln = (tag >> 2) + 1
                off = int.from_bytes(buf[i + 1:i + 3], "little"); i += 3
            else:
                ln = (tag >> 2) + 1
                off = int.from_bytes(buf[i + 1:i + 5], "little"); i += 5
            if off == 0 or off > len(out):
                raise ValueError(f"bad copy off={off} at {i}")
            for _ in range(ln):
                out.append(out[-off])
    return bytes(out)


try:
    ulen, _ = read_varint(data, 0)
    out = snappy_decode(data)
    print(f"[SNAPPY] preamble ulen={ulen} decoded={len(out)}B")
    print("  text head:", out[:200])
    print("  json_ok:", json_ok(out))
except Exception as e:
    print("[SNAPPY] fail:", e)

# --- 4) LZ4 block decoder ---
def lz4_block(buf):
    out = bytearray(); i = 0
    while i < len(buf):
        tok = buf[i]; i += 1
        lit = tok >> 4
        if lit == 15:
            while True:
                b = buf[i]; i += 1; lit += b
                if b != 255:
                    break
        out += buf[i:i + lit]; i += lit
        if i >= len(buf):
            break
        off = int.from_bytes(buf[i:i + 2], "little"); i += 2
        ml = tok & 15
        if ml == 15:
            while True:
                b = buf[i]; i += 1; ml += b
                if b != 255:
                    break
        ml += 4
        if off == 0 or off > len(out):
            raise ValueError(f"bad lz4 off={off} at {i}")
        for _ in range(ml):
            out.append(out[-off])
    return bytes(out)


try:
    out = lz4_block(data)
    print(f"[LZ4] decoded={len(out)}B head={out[:120]!r}")
except Exception as e:
    print("[LZ4] fail:", e)

# --- 5) treat whole as varint stream (protobuf sanity) ---
i = 0; vals = []
try:
    while i < len(data) and len(vals) < 24:
        v, i = read_varint(data, i)
        vals.append(v)
    print("[VARINT] first vals:", vals, f"consumed_to={i}")
except Exception as e:
    print("[VARINT] fail:", e)


# --- 6) msgpack decode attempt (head + every offset) ---
def mp_decode(buf, i=0):
    c = buf[i]; i += 1
    if c <= 0x7f: return c, i
    if c >= 0xe0: return c - 0x100, i
    if 0x80 <= c <= 0x8f:
        d = {}
        for _ in range(c - 0x80):
            k, i = mp_decode(buf, i); v, i = mp_decode(buf, i); d[k] = v
        return d, i
    if 0x90 <= c <= 0x9f:
        a = []
        for _ in range(c - 0x90):
            v, i = mp_decode(buf, i); a.append(v)
        return a, i
    if 0xa0 <= c <= 0xbf:
        s = buf[i:i + c - 0xa0]; i += c - 0xa0
        return (s.decode("utf-8") if s.isascii() or len(s.decode("utf-8", "ignore")) else s), i
    if c == 0xc0: return None, i
    if c == 0xc2: return False, i
    if c == 0xc3: return True, i
    if 0xc4 <= c <= 0xc6:
        n = {0xc4: 1, 0xc5: 2, 0xc6: 4}[c]
        n = int.from_bytes(buf[i:i + n], "big"); i += {0xc4: 1, 0xc5: 2, 0xc6: 4}[c]
        b = buf[i:i + n]; i += n; return b, i
    if c in (0xca, 0xcb):
        v = struct.unpack(">f" if c == 0xca else ">d", buf[i:i + {0xca: 4, 0xcb: 8}[c]])[0]
        i += {0xca: 4, 0xcb: 8}[c]; return v, i
    if 0xcc <= c <= 0xcf:
        n = c - 0xcb; v = int.from_bytes(buf[i:i + n], "big"); i += n; return v, i
    if 0xd0 <= c <= 0xd3:
        n = c - 0xcf; v = struct.unpack({0xd0: ">b", 0xd1: ">h", 0xd2: ">i", 0xd3: ">q"}[c], buf[i:i + n])[0]
        i += n; return v, i
    if 0xd9 <= c <= 0xdb:
        n = {0xd9: 1, 0xda: 2, 0xdb: 4}[c]
        n = int.from_bytes(buf[i:i + n], "big"); i += {0xd9: 1, 0xda: 2, 0xdb: 4}[c]
        s = buf[i:i + n]; i += n; return s, i
    if 0xc7 <= c <= 0xc9:
        nb = {0xc7: 1, 0xc8: 2, 0xc9: 4}[c]
        n = int.from_bytes(buf[i:i + nb], "big"); i += nb
        t = buf[i]; i += 1; b = buf[i:i + n]; i += n
        return ("ext", t, b), i
    if c == 0xdc or c == 0xdd:
        n = int.from_bytes(buf[i:i + c - 0xdc + 2], "big"); i += c - 0xdc + 2
        a = []
        for _ in range(n):
            v, i = mp_decode(buf, i); a.append(v)
        return a, i
    raise ValueError(f"unknown byte {c:02x} at {i-1}")


for off in range(0, 8):
    try:
        obj, end = mp_decode(data, off)
        print(f"[MSGPACK?] off={off} consumed={end}/{len(data)} obj={str(obj)[:400]}")
        break
    except Exception as e:
        print(f"[MSGPACK] off={off} fail: {e}")


# --- 7) repeated-substring scan (compression back-reference / ECB-block evidence) ---
seen = {}
for n in (16, 12, 8):
    hits = []
    for j in range(len(data) - n + 1):
        w = data[j:j + n]
        if w in seen and seen[w][0] + n <= j:
            hits.append((seen[w][0], j, w.hex()))
        else:
            seen.setdefault(w, (j,))
    if hits:
        print(f"[REPEAT {n}B]")
        for a, b, h in hits[:6]:
            print(f"  @{a} == @{b}: {h}")
    seen = {}


# --- 8) layer-cake: [flag byte] + raw-deflate( AES( json ) ) ---
for off in range(0, 4):
    try:
        d = zlib.decompressobj(-15)
        out = d.decompress(data[off:]) + d.flush()
        print(f"[DEFLATE off={off}] flag_byte={data[off-1:off].hex() if off else '-'} "
              f"inflated={len(out)}B len%16={len(out)%16} unused_tail={len(d.unused_data)}B")
        print("  head 48:", out[:48].hex(" "))
        print("  tail 16:", out[-16:].hex(" "))
        if AES is not None and len(out) % 16 == 0:
            for ki, key in enumerate(KEYS):
                for name, c in (
                    ("CBC(iv=key)", AES.new(key, AES.MODE_CBC, key)),
                    ("CBC(iv=0)", AES.new(key, AES.MODE_CBC, b"\x00" * 16)),
                    ("ECB", AES.new(key, AES.MODE_ECB)),
                ):
                    try:
                        pt = c.decrypt(out)
                        pt2 = strip_pad(pt)
                        tag = "[HIT]" if json_ok(pt2) else "    "
                        print(f"  {tag} AES key#{ki}({key[:5]}) {name}: {pt2[:160]!r}")
                    except Exception:
                        pass
    except Exception as e:
        print(f"[DEFLATE off={off}] fail: {e}")

print("done")
