# -*- coding: utf-8 -*-
import frida, time

dev = frida.get_usb_device(timeout=5)
pid = dev.spawn(["com.gamebeans.gp.jczx.offline"])
session = dev.attach(pid)

def on_msg(m, d):
    t = m.get("type")
    if t == "send":
        print("SEND:", m.get("payload"))
    elif t == "log":
        print("LOG:", m.get("payload"))
    elif t == "error":
        print("ERR:", m.get("stack") or m.get("description"))

script = session.create_script(
    'send("[top] script eval OK");'
    'console.log("[console] works");'
    'setInterval(function(){ send("[tick] t=" + Date.now() + " il2cpp=" + (Process.findModuleByName("libil2cpp.so") ? Process.findModuleByName("libil2cpp.so").base : "null")); }, 1000);'
)
script.on("message", on_msg)
script.load()
dev.resume(pid)
time.sleep(10)
