# -*- coding: utf-8 -*-
# Scan all BL xrefs to Quit entries in libil2cpp.so and map callers via dump.cs
import re, sys

SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
DUMP = r"d:\Code\github\DataTable\DaiblosCoreServer\il2cpp_dump\dump.cs"
BASE_DELTA = 0x4000

TARGETS = {
    0x2BC7550: "UnityEngine.Application.Quit()",
    0x2BC7514: "UnityEditor.Application.Quit(int)",
    0x14A6180: "CSAPI.Quit",
    0x14A5280: "CSAPI.QuitGame",
    0x14CC1A0: "ShiryuSDK.ExitGame",
}

with open(SO, "rb") as f:
    data = f.read()

# pass 1: scan BL instructions
hits = []  # (caller_rva, target)
n = len(data)
for off in range(0, n - 4, 4):
    w = int.from_bytes(data[off:off+4], "little")
    if (w & 0xFC000000) == 0x94000000:
        imm = w & 0x03FFFFFF
        if imm & 0x02000000:
            imm -= 0x04000000
        tgt = (off + BASE_DELTA) + imm * 4
        if tgt in TARGETS:
            hits.append((off + BASE_DELTA, tgt))

print("total BL hits:", len(hits))
for caller, tgt in hits:
    print("  caller RVA 0x%X -> %s" % (caller, TARGETS[tgt]))

# pass 2: build rva -> method map from dump.cs
rva_re = re.compile(r"// RVA: 0x([0-9A-Fa-f]+)")
methods = []
with open(DUMP, "r", encoding="utf-8", errors="ignore") as f:
    lines = f.readlines()
for i, line in enumerate(lines):
    m = rva_re.search(line)
    if m and i + 1 < len(lines):
        rva = int(m.group(1), 16)
        sig = lines[i+1].strip()
        if sig and not sig.startswith("//"):
            methods.append((rva, sig))
methods.sort()
import bisect
keys = [m[0] for m in methods]

print("\n--- caller identification ---")
for caller, tgt in hits:
    idx = bisect.bisect_right(keys, caller) - 1
    if idx >= 0:
        rva0, sig = methods[idx]
        print("0x%X -> %s\n    nearest method: 0x%X %s (+0x%X)" % (caller, TARGETS[tgt], rva0, sig[:150], caller - rva0))
