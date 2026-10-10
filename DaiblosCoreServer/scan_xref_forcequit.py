# -*- coding: utf-8 -*-
# Scan libil2cpp.so for BL/B xrefs and ADRP+ADD function-pointer refs to targets
import struct

SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
BASE_DELTA = 0x4000

TARGETS = {
    0x14B19DC: "SignatureVerify.Verify",
    0x14B1B00: "SignatureVerify.IsCorrect",
    0x14B1C2C: "SignatureVerify.ForceQuit",
    0x14B1D20: "SignatureVerify.GetHash",
}

def sx(val, bits):
    sign = 1 << (bits - 1)
    return (val & (sign - 1)) - (val & sign)

def main():
    with open(SO, "rb") as f:
        data = f.read()
    n = len(data) // 4
    words = struct.unpack("<%dI" % n, data[: n * 4])
    hits = []
    for i, w in enumerate(words):
        pc = i * 4 + BASE_DELTA  # RVA
        op = w >> 24
        if (w & 0xFC000000) in (0x94000000, 0x14000000):  # BL / B
            imm26 = w & 0x03FFFFFF
            off = sx(imm26, 26) << 2
            tgt = pc + off
            if tgt in TARGETS:
                hits.append((pc, "BL" if (w & 0xFC000000) == 0x94000000 else "B ", TARGETS[tgt]))
        # ADRP + immediate ADD  (function pointer materialization)
        if (w & 0x9F000000) == 0x90000000:  # ADRP
            rd = w & 0x1F
            immlo = (w >> 29) & 0x3
            immhi = (w >> 5) & 0x7FFFF
            imm = sx((immhi << 2) | immlo, 21) << 12
            page = (pc & ~0xFFF) + imm
            # scan a small window for: ADD Xd, Xd, #imm  (0x91000000 | imm12<<10 | rn<<5 | rd)
            for j in range(i + 1, min(i + 16, n)):
                w2 = words[j]
                if (w2 & 0xFF800000) == 0x91000000:
                    rd2 = w2 & 0x1F
                    rn2 = (w2 >> 5) & 0x1F
                    imm12 = (w2 >> 10) & 0xFFF
                    if rd2 == rn2 == rd:
                        tgt = page + imm12
                        if tgt in TARGETS:
                            hits.append((pc, "PTR", TARGETS[tgt]))
                        break
                if (w2 & 0xFF000000) not in (0x91000000, 0xFF000000 & 0xFF000000):
                    break
    for pc, kind, name in hits:
        print("%-8x %s %s" % (pc, kind, name))
    print("total:", len(hits))

if __name__ == "__main__":
    main()
