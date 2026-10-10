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
import base64
import gzip
import json
import os
import time
from datetime import datetime
from pathlib import Path

from flask import Flask, Response, request
from Crypto.Cipher import AES
import requests

BASE_DIR = Path(__file__).resolve().parent
RESPONSES_DIR = BASE_DIR / "responses"
LOG_FILE = BASE_DIR / "requests.log"
FORWARD_LOG = BASE_DIR / "forward.log"

app = Flask(__name__)
FALLBACK = os.environ.get("MOCK_FALLBACK", "empty200")  # empty200 | 404
MAX_BODY_LOG = 4000  # body 日志截断长度(字节)

# ---------------------------------------------------------------------------
# FORWARD 抓包模式: --forward 开启后把请求原样转发到官方服务器, 双向报文落盘
# forward.log (抓官方真实响应做 fixtures 用; 抓完即关, 不长期连官方)
# ---------------------------------------------------------------------------
FORWARD = False
FORWARD_HOSTS = {
    "sdk/": "https://mg-account.game-beans.net/",       # account/pay
    "data/": "https://mgbp-event.game-beans.net/",      # ext/event 上报
    "TWJCZX/": "https://mg-account.game-beans.net/",    # 版本检查/服务器列表(宿主待验证)
    "unitysdkcfg": "https://mg-account.game-beans.net/",  # unity sdk 配置(宿主待验证)
}
HOP_HEADERS = {"host", "content-length", "connection", "accept-encoding",
               "transfer-encoding", "keep-alive"}


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


# ---------------------------------------------------------------------------
# MJ SDK 加密协议 (自适应识别)
# 依据: MJSdkInfo.KEY_ANDROID="KLJDkn789lu1J9A1" / KEY_CF_ANDROID="KGJDkn689Fu3JAnd"
# MJInfo_Android.bin 实测: AES-128-CBC, key=IV, Padding=None(尾部\n补齐).
# MJHttp wire 格式未知, 故对请求 body 全组合试解, 命中后缓存方案并用同款回加密.
# ---------------------------------------------------------------------------
MJ_KEYS = [b"KGJDkn689Fu3JAnd", b"KLJDkn789lu1J9A1"]  # KEY_CF / KEY_ANDROID
MJ_PREFIXES = ("sdk/", "data/")  # Flask path converter 不带前导斜杠
MJ_SCHEME = None  # 命中后缓存: (b64: bool, cipher_name: str, key: bytes)


def _strip_pad(pt: bytes) -> bytes:
    """None-padding(尾部 \\n / \\x00) 与 PKCS7 两种习惯都尝试剥离."""
    if pt and len(pt) % 16 == 0 and 0 < pt[-1] <= 16:
        n = pt[-1]
        if pt.endswith(bytes([n]) * n):  # PKCS7 合法则优先
            return pt[:-n]
    return pt.rstrip(b"\n").rstrip(b"\x00")


def _json_ok(pt: bytes):
    try:
        obj = json.loads(pt.decode("utf-8").strip())
        return obj if isinstance(obj, (dict, list)) else None
    except Exception:
        return None


def mj_try_decrypt(raw: bytes):
    """返回 (scheme, obj); scheme=("plain"|"gzip"|("b64",cipher,key)). 全部失败返回 (None, None)."""
    # -1) gzip 通道 (data/*: isEncrypt=False, isGzip=True) — 先解压再看明文
    if raw[:2] == b"\x1f\x8b":
        try:
            dec = gzip.decompress(raw)
            obj = _json_ok(dec)
            if obj is not None:
                return ("gzip", None), obj
        except Exception:
            pass
    # 0) 明文 JSON (v5 未加密请求)
    obj = _json_ok(raw)
    if obj is not None:
        return ("plain", None), obj
    for b64 in (False, True):
        data = raw
        if b64:
            try:
                data = base64.b64decode(raw)
            except Exception:
                continue
        if not data or len(data) % 16:
            continue
        for key in MJ_KEYS:
            ciphers = [
                ("CBC(iv=key)", AES.new(key, AES.MODE_CBC, key)),
                ("CBC(iv=0)", AES.new(key, AES.MODE_CBC, b"\x00" * 16)),
                ("ECB", AES.new(key, AES.MODE_ECB)),
            ]
            for name, cipher in ciphers:
                try:
                    obj = _json_ok(_strip_pad(cipher.decrypt(data)))
                except Exception:
                    continue
                if obj is not None:
                    return (b64, name, key), obj
    return None, None


def mj_encrypt(scheme, obj) -> Response:
    """用请求识别出的同款方案回写响应.
    plain/gzip: 直接回 JSON (gzip 通道按客户端 ParseResponseData(isGzip=True) 预期压 gzip).
    加密习惯: \\n 补齐至 16 倍数."""
    if scheme[0] == "gzip":
        pt = json.dumps(obj, ensure_ascii=False, separators=(",", ":")).encode("utf-8")
        return Response(gzip.compress(pt), status=200,
                        content_type="application/json", headers={"Content-Encoding": "gzip"})
    if scheme[0] == "plain":
        pt = json.dumps(obj, ensure_ascii=False, separators=(",", ":")).encode("utf-8")
        return Response(pt, status=200, content_type="application/json")
    b64, name, key = scheme
    pt = json.dumps(obj, ensure_ascii=False, separators=(",", ":")).encode("utf-8")
    if len(pt) % 16:
        pt += b"\n" * (16 - len(pt) % 16)
    if name == "CBC(iv=key)":
        ct = AES.new(key, AES.MODE_CBC, key).encrypt(pt)
    elif name == "CBC(iv=0)":
        ct = AES.new(key, AES.MODE_CBC, b"\x00" * 16).encrypt(pt)
    elif name == "ECB":
        ct = AES.new(key, AES.MODE_ECB).encrypt(pt)
    else:  # plain
        return Response(pt, status=200, content_type="application/json")
    if b64:
        return Response(base64.b64encode(ct), status=200, content_type="text/plain")
    return Response(ct, status=200, content_type="application/octet-stream")


def mj_response_for(path: str) -> dict:
    """按相对路径构造 MJSDK 业务成功响应 (字段全集, 与 dump.cs 对齐).
    信封格式来自官方真响应抓包 (forward.log): {"timeSecs","sign","res":{code,msg},...}.
    注意: 成功码是 200 而非 0 — MJHttp..cctor: SUCCESS_CODE=0xc8, CommonStatusCode.Success.code=200."""
    env = {"timeSecs": int(time.time()), "sign": "ab294c125784638b",
           "res": {"code": 200, "msg": "success"}}
    if path.startswith("sdk/init"):
        obj = dict(env)
        obj.update({
            "payload": {},
            "adName": "", "turboOpenIds": False,
            "privacyUrl": "", "serviceUrl": "", "csEmail": "", "communityUrl": "",
            "qaUrl": "", "csUrl": "", "deletionUrl": "", "gameUrl": "", "webPlayUrl": "",
            "heartInterval": 60, "eventInterval": 60, "eventSize": 50, "roleInterval": 60,
            "updateVersion": 0, "updateTips": "", "updateUrl": "", "isReview": False,
        })
        return obj
    if path.startswith("sdk/user/login"):
        obj = dict(env)
        obj.update({
            "payload": {},
            "uid": "100000000000000001", "email": "offline@local", "phone": "",
            "needUpgrade": False, "skipBind": True, "firstJoin": False,
            "firstJoinSecs": 0, "openId": "offline_openid_001",
            "authToken": "offline_auth_token", "secondToken": "",
        })
        return obj
    return dict(env)


def mj_handle(path: str) -> Response:
    global MJ_SCHEME
    raw = request.get_data()
    scheme, obj = mj_try_decrypt(raw)
    if obj is not None:
        if scheme[0] == "plain":
            tag = "plain"
        elif scheme[0] == "gzip":
            tag = "gzip"
        else:
            tag = f"b64={scheme[0]},{scheme[1]},key={scheme[2][:5].decode()}"
        ts = datetime.now().strftime("%H:%M:%S")
        info = f"[{ts}] [MJ] {request.method} /{path}\n[MJ] scheme={tag}\n[MJ] req : {json.dumps(obj, ensure_ascii=False)[:1200]}\n"
        print(info, flush=True)
        with open(LOG_FILE, "a", encoding="utf-8") as f:
            f.write(info + "\n")
        if not isinstance(scheme[0], str):
            MJ_SCHEME = scheme
        # 官方真响应 (forward.log): event 通道回字面量 "200\n", 非 JSON 非 gzip
        if path.startswith("data/event"):
            return Response(b"200\n", status=200, content_type="application/octet-stream")
        return mj_encrypt(scheme, mj_response_for(path))
    # 识别失败: 全量 hex 落盘供人工分析, 返回明文 success 兜底
    ts = datetime.now().strftime("%H:%M:%S")
    info = (f"[{ts}] [MJ-FAIL] /{path} body({len(raw)}B) hex(head 2KB): "
            f"{raw[:2048].hex()}\n")
    print(info, flush=True)
    with open(LOG_FILE, "a", encoding="utf-8") as f:
        f.write(info + "\n")
    if path.startswith("data/event"):
        return Response(b"200\n", status=200, content_type="application/octet-stream")
    return Response(json.dumps(mj_response_for(path)), status=200,
                    content_type="application/json")


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


def forward_to_official(p: str):
    """原样转发到官方并双向落盘 forward.log; 出错/无宿主映射返回 None (回落本地处理)."""
    host = next((v for k, v in FORWARD_HOSTS.items() if p.startswith(k)), None)
    if host is None:
        return None
    url = host + p
    if request.query_string:
        url += "?" + request.query_string.decode("utf-8", "replace")
    headers = {k: v for k, v in request.headers.items() if k.lower() not in HOP_HEADERS}
    raw = request.get_data()
    ts = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    try:
        r = requests.request(request.method, url, headers=headers, data=raw, timeout=15)
    except Exception as e:
        line = f"[{ts}] FWD-ERR {request.method} /{p} -> {url}: {e}\n"
        print(line, flush=True)
        with open(FORWARD_LOG, "a", encoding="utf-8") as f:
            f.write(line)
        return None
    body = r.content
    with open(FORWARD_LOG, "a", encoding="utf-8") as f:
        f.write(f"[{ts}] >>> {request.method} {url}\n")
        f.write(f"    req-headers: {json.dumps(headers, ensure_ascii=False)}\n")
        f.write(f"    req-body({len(raw)}B): {raw[:4096].hex()}\n")
        f.write(f"[{ts}] <<< {r.status_code}\n")
        f.write(f"    rsp-headers: {json.dumps(dict(r.headers), ensure_ascii=False)}\n")
        f.write(f"    rsp-body({len(body)}B): {body[:4096].hex()}\n")
        f.write("=" * 70 + "\n")
    print(f"[fwd] {request.method} /{p} -> {r.status_code} ({len(body)}B)", flush=True)
    ctype = r.headers.get("Content-Type", "application/octet-stream")
    return Response(body, status=r.status_code, content_type=ctype)


@app.route("/", defaults={"p": ""})
@app.route("/<path:p>", methods=["GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH"])
def catch_all(p):
    log_request()
    # FORWARD 抓包模式: 一切先转官方, 双向留痕; 失败回落本地处理
    if FORWARD:
        fwd = forward_to_official(p)
        if fwd is not None:
            return fwd
    # 原版实测: unitysdk.json 官方 404 时 GET_UNITYSDKCONFIG 走容错路径
    # (默认 lbDownloadFullLevel=30) 并正常进入游戏; 返回 200{} 反而抛
    # KeyNotFoundException. 故此路径必须 404 以复刻原版行为.
    if p.startswith("unitysdkcfg"):
        return Response("404 Not Found", status=404, content_type="text/plain")
    # MJ SDK 加密协议: sdk/* (account/pay) 与 data/* (ext/event)
    if p.startswith(MJ_PREFIXES):
        return mj_handle(p.lstrip("/"))
    resp = resolve_response(p)
    if resp is not None:
        return resp
    # 原版实测: verFix.bytes 官方 404(当前无热修复), 客户端走容错分支.
    if p.startswith("TWJCZX/zs/update/") and p.endswith(".bytes"):
        return Response("404 Not Found", status=404, content_type="text/plain")
    # update<ver>.txt: 官方已停服返回 404 会触发客户端无限重试+网络错误弹窗.
    # GetHotResVerTxt 用 Contains/IndexOf 解析键值标记, 空文本 => 全部判定 false
    # => 默认"无热更"放行. (responses/ 放真实文件时优先生效)
    if p.startswith("TWJCZX/zs/update/") and p.endswith(".txt"):
        return Response("", status=200, content_type="text/plain")
    if FALLBACK == "404":
        return Response("mock: no response file", status=404, content_type="text/plain")
    return Response("{}", status=200, content_type="application/json")


def main():
    global FALLBACK, FORWARD
    parser = argparse.ArgumentParser(description="daiblos offline mock server")
    parser.add_argument("--port", type=int, default=8000)
    parser.add_argument("--host", default="0.0.0.0")
    parser.add_argument("--fallback", choices=["empty200", "404"], default="empty200")
    parser.add_argument("--forward", action="store_true",
                        help="抓包模式: 请求原样转发官方, 双向落盘 forward.log")
    args = parser.parse_args()
    FALLBACK = args.fallback
    FORWARD = args.forward

    print(f"[mock] responses dir : {RESPONSES_DIR}")
    print(f"[mock] request log   : {LOG_FILE}")
    print(f"[mock] fallback      : {FALLBACK}")
    print(f"[mock] forward       : {FORWARD}" + (f" hosts={FORWARD_HOSTS}" if FORWARD else ""))
    print(f"[mock] listening     : http://{args.host}:{args.port}")
    print(f"[mock] sample        : http://127.0.0.1:{args.port}/TWJCZX/a11/serverlist_release.json")
    app.run(host=args.host, port=args.port, debug=False)


if __name__ == "__main__":
    main()
