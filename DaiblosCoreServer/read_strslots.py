# -*- coding: utf-8 -*-
# Resolve string literal slots (adrp 0x2fd2000 page) -> Il2CppString content
SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
DELTA = 0x4000
SLOTS = [0xba0, 0xac8, 0xaf0, 0xac0, 0xb28, 0xb58, 0xbb0, 0xad8, 0xb80, 0xb40,
         0xb20, 0xb70, 0xb08, 0xb98, 0xb50]

with open(SO, "rb") as f:
    data = f.read()

def rd64(va):
    off = va - DELTA
    return int.from_bytes(data[off:off+8], "little")

for s in SLOTS:
    slot_va = 0x2FD2000 + s
    ptr = rd64(slot_va)
    if ptr == 0 or ptr < DELTA or ptr - DELTA + 0x14 >= len(data):
        print("slot +0x%X -> 0x%X (invalid)" % (s, ptr))
        continue
    soff = ptr - DELTA
    length = int.from_bytes(data[soff+0x10:soff+0x14], "little", signed=True)
    if length < 0 or length > 512:
        print("slot +0x%X -> 0x%X (bad len %d)" % (s, ptr, length))
        continue
    raw = data[soff+0x14 : soff+0x14+length*2]
    try:
        txt = raw.decode("utf-16-le")
    except Exception:
        txt = repr(raw[:40])
    print("slot +0x%X -> 0x%X : %r" % (s, ptr, txt))
