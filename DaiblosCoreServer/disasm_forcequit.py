# -*- coding: utf-8 -*-
# Disassemble ForceQuit(0x14B1C2C..0x14B1D80) and Verify(0x14B19DC..0x14B1B60)
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN

SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
BASE_DELTA = 0x4000
FUNCS = {
    "ForceQuit_0x14B1C2C": (0x14B1C2C, 0x14B1D80),
    "Verify_0x14B19DC":    (0x14B19DC, 0x14B1B00),
}
with open(SO, "rb") as f:
    data = f.read()
md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
for name, (rva, end) in FUNCS.items():
    off = rva - BASE_DELTA
    print("=" * 28, name, "=" * 28)
    for ins in md.disasm(data[off: off + (end - rva)], rva):
        print("%x:\t%s\t%s" % (ins.address, ins.mnemonic, ins.op_str))
    print()
