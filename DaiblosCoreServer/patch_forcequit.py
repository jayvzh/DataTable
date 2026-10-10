# -*- coding: utf-8 -*-
# Patch SignatureVerify.ForceQuit (RVA 0x14B1C2C) to immediate ret in libil2cpp.so
import shutil

SO = r"d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
BAK = SO + ".orig"
RVA = 0x14B1C2C
DELTA = 0x4000
off = RVA - DELTA
RET = bytes.fromhex("C0035FD6")  # ret

with open(SO, "rb") as f:
    data = bytearray(f.read())

orig = bytes(data[off:off+4])
print("offset 0x%X current bytes: %s" % (off, orig.hex()))
# expect first instr: str x30,[sp,#-0x20]! = F81F0FFE -> little endian bytes FE 0F 1F F8
if orig != bytes.fromhex("FE0F1EF8"):  # str x30,[sp,#-0x20]! = 0xF81E0FFE
    print("WARN: unexpected instruction bytes, aborting")
    raise SystemExit(1)

import os
if not os.path.exists(BAK):
    shutil.copy2(SO, BAK)
    print("backup saved:", BAK)

data[off:off+4] = RET
with open(SO, "wb") as f:
    f.write(data)
print("patched: ForceQuit -> ret")

# verify via disasm
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN
md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
for ins in md.disasm(bytes(data[off:off+16]), RVA):
    print("%x:\t%s\t%s" % (ins.address, ins.mnemonic, ins.op_str))
    break
