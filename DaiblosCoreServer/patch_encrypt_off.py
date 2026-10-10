#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Patch MJHttp.PostRequest state-machine isEncrypt store to zero (disable MJ SDK encryption).

Patch point (arm64 libil2cpp.so, file offset 0x197C3E8):
    strb w10, [x25, #0x38]   (0x3900E32A)  ->  strb wzr, [x25, #0x38]  (0x3900E33F)
Covers request-encrypt branch, response ParseResponseData arg, network-error retry.
"""
import shutil
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN

SO = r"D:\Code\github\DataTable\DaiblosCoreServer\apktool_out\lib\arm64-v8a\libil2cpp.so"
ORIG = SO + ".orig"
OFF = 0x197C3E8
OLD = bytes.fromhex("2ae30039")  # strb w10, [x25, #0x38]
NEW = bytes.fromhex("3fe30039")  # strb wzr, [x25, #0x38]

data = open(SO, "rb").read()

try:
    orig = open(ORIG, "rb").read()
    print(f"[backup] exists: {ORIG} ({len(orig)}B)")
except FileNotFoundError:
    shutil.copyfile(SO, ORIG)
    print(f"[backup] created: {ORIG}")

cur = data[OFF:OFF + 4]
print(f"[check ] @0x{OFF:X} current={cur.hex()} expected(uncorrected)={OLD.hex()}")

if cur == NEW:
    print("[skip  ] already patched")
    raise SystemExit(0)
if cur != OLD:
    raise SystemExit(f"[abort ] unexpected bytes at 0x{OFF:X}: {cur.hex()} (lib changed? re-run find_patch_points.py)")

md = Cs(CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN)
print("[before]")
for i in md.disasm(data[OFF - 0x10: OFF + 0x10], OFF - 0x10):
    print(f"  {i.address:X}: {i.mnemonic} {i.op_str}")

patched = data[:OFF] + NEW + data[OFF + 4:]
open(SO, "wb").write(patched)
print(f"[patch ] wrote {NEW.hex()} @0x{OFF:X}")

verify = open(SO, "rb").read()[OFF:OFF + 4]
assert verify == NEW, "verify failed"
print("[after ]")
for i in md.disasm(open(SO, "rb").read()[OFF - 0x10: OFF + 0x10], OFF - 0x10):
    print(f"  {i.address:X}: {i.mnemonic} {i.op_str}")
print("[done  ] libil2cpp.so patched")
