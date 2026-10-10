# -*- coding: utf-8 -*-
# Frida: hook all exit paths of com.gamebeans.gp.jczx.offline, print Java stacks
import frida, sys, time

PKG = "com.gamebeans.gp.jczx.offline"
OUT = r"d:\Code\github\DataTable\DaiblosCoreServer\frida_exit_log.txt"

JS = r"""
Java.perform(function() {
    function stack() {
        return Java.use("android.util.Log").getStackTraceString(Java.use("java.lang.Exception").$new());
    }
    var Activity = Java.use("android.app.Activity");
    Activity.finish.overload().implementation = function() {
        console.log("[HOOK] Activity.finish()\n" + stack() + "\n----");
        return this.finish();
    };
    try {
        var MA = Java.use("com.zlongame.PDUnitySDK.MainActivity");
        MA.exit.overload().implementation = function() {
            console.log("[HOOK] MainActivity.exit()\n" + stack() + "\n----");
            return this.exit();
        };
    } catch(e) { console.log("[!] MainActivity.exit hook fail: " + e); }
    try {
        var M7 = Java.use("com.zlongame.PDUnitySDK.MainActivity$7");
        M7.run.implementation = function() {
            console.log("[HOOK] MainActivity$7.run()\n" + stack() + "\n----");
            return this.run();
        };
    } catch(e) { console.log("[!] MainActivity$7 hook fail: " + e); }
    var Sys = Java.use("java.lang.System");
    Sys.exit.overload('int').implementation = function(code) {
        console.log("[HOOK] System.exit(" + code + ")\n" + stack() + "\n----");
        return this.exit(code);
    };
    var Rt = Java.use("java.lang.Runtime");
    Rt.exit.overload('int').implementation = function(code) {
        console.log("[HOOK] Runtime.exit(" + code + ")\n" + stack() + "\n----");
        return this.exit(code);
    };
    Rt.halt.overload('int').implementation = function(code) {
        console.log("[HOOK] Runtime.halt(" + code + ")\n" + stack() + "\n----");
        return this.halt(code);
    };
    var Proc = Java.use("android.os.Process");
    Proc.killProcess.overload('int').implementation = function(pid) {
        console.log("[HOOK] Process.killProcess(" + pid + ")\n" + stack() + "\n----");
        return this.killProcess(pid);
    };
    console.log("[*] hooks installed");
});
"""

log = open(OUT, "w", encoding="utf-8")

def on_message(message, data):
    if message["type"] == "send":
        line = message["payload"]
        print(line)
        log.write(line + "\n")
        log.flush()
    elif message["type"] == "error":
        print("[JS ERR]", message.get("stack", message))
        log.write("[JS ERR] " + str(message) + "\n")
        log.flush()

dev = frida.get_device_manager().add_remote_device("127.0.0.1:27042") if False else frida.get_usb_device(timeout=5)
pid = dev.spawn([PKG])
session = dev.attach(pid)
script = session.create_script(JS)
script.on("message", on_message)
script.load()
dev.resume(pid)
print("[*] spawned pid=%d, waiting 75s..." % pid)
time.sleep(75)
print("[*] done")
log.close()
