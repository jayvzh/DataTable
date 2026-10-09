#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
本地假服务端 (Mock Server) — 日志优先 + 可插拔响应
项目: daiblos-core-2-10-0.apk 单机离线化改造

设计原则 (来自 apk-reverse skill references/server-api.md):
  1. 日志优先: 记录全部请求 (method/path/query/headers/body) -> 控制台 + requests.log,
     真机联调后据此迭代 responses/ 里的响应格式
  2. 可插拔响应: responses/ 目录按 URL 路径镜像映射.
     例: GET /TWJCZX/a11/serverlist_release.json
         -> responses/TWJCZX/a11/serverlist_release.json (自动尝试追加 .json 后缀)
  3. 未命中响应文件时走 fallback (默认返回 {} + 200, 可选 404)
  4. Patch 决策路径而非数据: 响应内容由我们下发, 可覆盖客户端行为

启动 (conda env apk-re):
  C:/Users/Jayv/miniforge3/envs/apk-re/python.exe server.py [--port 8000] [--fallback empty200|404]

真机/模拟器接入:
  adb reverse tcp:8000 tcp:8000   -> 客户端 URL 用 http://127.0.0.1:8000
  或直接把 MJEnv.txt 的 URL 指向 <PC局域网IP>:8000 (本服务绑定 0.0.0.0)
"""

import argparse
import json
import os
from datetime import datetime
from pathlib import Path

from flask import Flask, Response, request

BASE_DIR = Path(__file__).resolve().parent
RESPONSES_DIR = BASE_DIR / "responses"
LOG_FILE = BASE_DIR / "requests.log"

app = Flask(__name__)
FALLBACK = os.environ.get("MOCK_FALLBACK", "empty200")  # empty200 | 404
MAX_BODY_LOG = 4000  # body 日志截断长度(字节)


def log_request() -> None:
    ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    url = request.path
    if request.query_string:
        url += "?" + request.query_string.decode("utf-8", "replace")
    lines = [
        "=" * 70,
        f"[{ts}] {request.method} {url}",
        f"From: {request.remote_addr}",
        "Headers: " + json.dumps(dict(request.headers), ensure_ascii=False),
    ]
    raw = request.get_data()
    if raw:
        try:
            text = raw.decode("utf-8")
            lines.append(f"Body({len(raw)}B): {text[:MAX_BODY_LOG]}")
        except UnicodeDecodeError:
            lines.append(f"Body: <binary {len(raw)} bytes> head={raw[:64].hex()}")
    block = "\n".join(lines) + "\n"
    print(block, flush=True)
    with open(LOG_FILE, "a", encoding="utf-8") as f:
        f.write(block)


def resolve_response(path: str):
    """responses/ 目录按 URL path 镜像匹配, 自动尝试 .json 后缀, 防目录穿越."""
    for cand in (path, path + ".json"):
        rel = cand.lstrip("/")
        target = (RESPONSES_DIR / rel).resolve()
        if not str(target).startswith(str(RESPONSES_DIR.resolve())):
            continue
        if target.is_file():
            ctype = (
                "application/json; charset=utf-8"
                if target.suffix == ".json"
                else "application/octet-stream"
            )
            return Response(target.read_bytes(), status=200, content_type=ctype)
    return None


@app.route("/", defaults={"p": ""})
@app.route("/<path:p>", methods=["GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH"])
def catch_all(p):
    log_request()
    resp = resolve_response(p)
    if resp is not None:
        return resp
    if FALLBACK == "404":
        return Response("mock: no response file", status=404, content_type="text/plain")
    return Response("{}", status=200, content_type="application/json")


def main():
    global FALLBACK
    parser = argparse.ArgumentParser(description="daiblos offline mock server")
    parser.add_argument("--port", type=int, default=8000)
    parser.add_argument("--host", default="0.0.0.0")
    parser.add_argument("--fallback", choices=["empty200", "404"], default="empty200")
    args = parser.parse_args()
    FALLBACK = args.fallback

    print(f"[mock] responses dir : {RESPONSES_DIR}")
    print(f"[mock] request log   : {LOG_FILE}")
    print(f"[mock] fallback      : {FALLBACK}")
    print(f"[mock] listening     : http://{args.host}:{args.port}")
    print(f"[mock] sample        : http://127.0.0.1:{args.port}/TWJCZX/a11/serverlist_release.json")
    app.run(host=args.host, port=args.port, debug=False)


if __name__ == "__main__":
    main()
