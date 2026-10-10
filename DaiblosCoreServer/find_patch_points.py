#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Locate isEncrypt guard branches in MJHttp methods (arm64, static disasm).

Targets from dump.cs (Offset = file offset in libil2cpp.so):
  - MJHttp.ParseResponseData<object>        0x197C0A4  (static: x0=data w1=isEncrypt w2=isGzip)
  - MJHttp.<PostRequest>d__2<object>.MoveNext 0x1AF1AA0 (state machine fields: isEncrypt≈0x38, isGzip≈0x39)
  - MJHttp.PostRequest<object> (object ov)  0x197C28C
  - MJHttp.PostRequest<object> (string ov)  0x197C35C
"""
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN

SO = r"D:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
TARGETS = {
    "ParseResponseData": (0x197C0A4, 128),
    "MoveNext": (0x1AF1AA0, 2000),
    "PostRequest_obj": (0x197C28C, 80),
    "PostRequest_str": (0x197C35C, 80),
}
COND = {"cbz", "cbnz", "tbz", "tbnz"}

data = open(SO, "rb").read()
md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
md.detail = False

for name, (off, count) in TARGETS.items():
    code = data[off: off + count * 4]
    insns = list(md.disasm(code, off))
    lines = [f"===== {name} @ file 0x{off:X} ({len(insns)} insns) ====="]
    lines += [f"  {i.address:X}: {i.mnemonic} {i.op_str}" for i in insns[:8]]
    lines.append("  ...")
    for idx, i in enumerate(insns):
        m = i.mnemonic
        if m in COND or m.startswith("b.") or m in ("b", "bl", "br", "ret"):
            ctx = insns[max(0, idx - 4): idx]
            ctext = " | ".join(f"{c.mnemonic} {c.op_str}" for c in ctx)
            lines.append(f"  BR @{i.address:X}: {m} {i.op_str}   <- {ctext}")
    # bool field loads (candidate state-machine field reads)
    loads = [f"{i.address:X}: {i.mnemonic} {i.op_str}" for i in insns
             if i.mnemonic in ("ldrb", "ldurb") and "#0x3" in i.op_str]
    if loads:
        lines.append("  -- ldrb candidates (off 0x30-0x3F):")
        lines += [f"    {l}" for l in loads]
    print("\n".join(lines))
    print()

    with open(rf"D:\Code\github\DataTable\DaiblosCoreServer\disasm_{name}.txt", "w", encoding="utf-8") as f:
        for i in insns:
            f.write(f"{i.address:X}: {i.mnemonic} {i.op_str}\n")

print("full listings written: disasm_*.txt")
