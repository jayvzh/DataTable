#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Disassemble InitSdkMgr.HttpInitMJSdkCallback.OnSuccess/OnFail + InitMJSdk + SetupConfig."""
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN

SO = r"D:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
TARGETS = {
    "OnSuccess": (0x23D0388, 130),
    "OnFail": (0x23D077C, 60),
    "InitMJSdk": (0x23CFF54, 90),
    "SetupConfig": (0x23D0688, 90),
}
data = open(SO, "rb").read()
md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
for name, (off, n) in TARGETS.items():
    print(f"===== {name} @ 0x{off:X} =====")
    for i in list(md.disasm(data[off: off + n * 4], off))[:n]:
        print(f"  {i.address:X}: {i.mnemonic} {i.op_str}")
    print()
