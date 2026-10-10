#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
游戏 TCP 长连接观测器 (默认 6001)
监听 0.0.0.0:6001，记录客户端发来的原始字节（当前只观测不回包），
用于学习游戏 TCP 协议握手格式，为后续实现本地游戏服务器做准备。
日志: tcp_log/summary.log + tcp_log/conn<N>_c2s.bin

启动 (conda env apk-re):
  C:/Users/Jayv/miniforge3/envs/apk-re/python.exe tcp_logger.py [port]
"""
import asyncio
import sys
from datetime import datetime
from pathlib import Path

LISTEN_PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 6001
LOG_DIR = Path(__file__).resolve().parent / "tcp_log"
LOG_DIR.mkdir(exist_ok=True)
SUMMARY = LOG_DIR / "summary.log"
_conn_seq = 0


async def handle(reader: asyncio.StreamReader, writer: asyncio.StreamWriter):
    global _conn_seq
    _conn_seq += 1
    n = _conn_seq
    peer = writer.get_extra_info("peername")
    ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    line = f"[{ts}] conn#{n} from {peer[0]}:{peer[1]}\n"
    print(line, end="", flush=True)
    with open(SUMMARY, "a", encoding="utf-8") as f:
        f.write(line)
    binpath = LOG_DIR / f"conn{n}_c2s.bin"
    total = 0
    try:
        while True:
            data = await reader.read(65536)
            if not data:
                break
            with open(binpath, "ab") as bf:
                bf.write(data)
            total += len(data)
            print(f"conn#{n} +{len(data)}B (total {total}): {data[:64].hex()}", flush=True)
    except (ConnectionResetError, asyncio.IncompleteReadError):
        pass
    finally:
        writer.close()
        line = f"[{datetime.now().strftime('%H:%M:%S')}] conn#{n} closed, total {total}B -> {binpath.name}\n"
        print(line, end="", flush=True)
        with open(SUMMARY, "a", encoding="utf-8") as f:
            f.write(line)


async def main():
    server = await asyncio.start_server(handle, "0.0.0.0", LISTEN_PORT)
    print(f"[tcp-logger] listening 0.0.0.0:{LISTEN_PORT}, log dir: {LOG_DIR}")
    async with server:
        await server.serve_forever()


if __name__ == "__main__":
    asyncio.run(main())
