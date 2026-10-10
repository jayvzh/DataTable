# -*- coding: utf-8 -*-
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN
SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
BASE_DELTA = 0x4000
FUNCS = {
    "GetHotResVerTxt_0x171A9C0": (0x171A9C0, 0x171B528),
}
with open(SO, "rb") as f:
    data = f.read()
md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
for name, (rva, end) in FUNCS.items():
    off = rva - BASE_DELTA
    print("=" * 28, name, "=" * 28)
    for ins in md.disasm(data[off: off + (end - rva)], rva):
        print("%x:\t%s\t%s" % (ins.address, ins.mnemonic, ins.op_str))
