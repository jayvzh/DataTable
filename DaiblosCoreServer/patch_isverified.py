#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Patch SignAble.isVerified() to always return true (trust all mock responses).

isVerified: dump.cs RVA 0x23D5944 / file offset 0x23D1944 (delta 0x4000).
Called from MJHttp.<PostRequest>d__2.MoveNext @0x1AF1F54 (bl 0x23d1944),
false -> 0x1AF2390 OnFail (signature error -> init dialog -> designed exit).
Patch: mov w0, #1 ; ret  (8 bytes, replaces whole function head).
"""
import shutil
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN

SO = r"D:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
ORIG = SO + ".orig"
OFF = 0x23D1944
NEW = bytes.fromhex("20008052c0035fd6")  # mov w0,#1 ; ret

cur = open(SO, "rb").read()
orig = open(ORIG, "rb").read()

old = cur[OFF:OFF + 8]
print(f"[check ] @0x{OFF:X} current={old.hex()}")
if old == NEW:
    print("[skip  ] already patched")
    raise SystemExit(0)

md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
print("[orig function head]")
for i in md.disasm(orig[OFF:OFF + 0x20], OFF):
    print(f"  {i.address:X}: {i.mnemonic} {i.op_str}")

patched = cur[:OFF] + NEW + cur[OFF + 8:]
open(SO, "wb").write(patched)
print(f"[patch ] wrote mov w0,#1 ; ret @0x{OFF:X}")

verify = open(SO, "rb").read()
assert verify[OFF:OFF + 8] == NEW
print("[after ]")
for i in md.disasm(verify[OFF:OFF + 0x10], OFF):
    print(f"  {i.address:X}: {i.mnemonic} {i.op_str}")
print("[done  ] isVerified patched (encrypt-off patch still in place:",
      cur[0x197C3E8:0x197C3EC].hex() == "3fe30039", ")")
