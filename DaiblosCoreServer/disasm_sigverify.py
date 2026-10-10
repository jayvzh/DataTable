# -*- coding: utf-8 -*-
# Disassemble SignatureVerify.{Verify,IsCorrect,ForceQuit,GetHash} from libil2cpp.so
import sys
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN

SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
BASE_DELTA = 0x4000  # Offset = RVA - 0x4000

FUNCS = {
    "Verify":    (0x14B19DC, 0x14B1C2C),
    "IsCorrect": (0x14B1B00, 0x14B1C2C),
    "ForceQuit": (0x14B1C2C, 0x14B1D20),
    "GetHash":   (0x14B1D20, 0x14B2A6C),
}

# known call targets for annotation
KNOWN = {
    0x13d9908: "il2cpp_runtime_init_helper?",
    0x23d1944: "SignAble_get_verified(patched)",
}

def main():
    with open(SO, "rb") as f:
        data = f.read()
    md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
    md.detail = False
    for name, (rva, end) in FUNCS.items():
        off = rva - BASE_DELTA
        code = data[off: off + (end - rva)]
        print("=" * 30, name, hex(rva), "=" * 30)
        for ins in md.disasm(code, rva):
            line = "%x:\t%s\t%s" % (ins.address, ins.mnemonic, ins.op_str)
            if ins.mnemonic in ("bl", "b") and ins.op_str.startswith("#"):
                try:
                    tgt = int(ins.op_str[1:], 16)
                    if tgt in KNOWN:
                        line += "   ; " + KNOWN[tgt]
                except ValueError:
                    pass
            print(line)
        print()

if __name__ == "__main__":
    main()
