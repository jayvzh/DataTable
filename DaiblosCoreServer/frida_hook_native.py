# -*- coding: utf-8 -*-
# Frida 16: native hook on libil2cpp.so Quit entries, print backtrace addresses
import frida, time

PKG = "com.gamebeans.gp.jczx.offline"
OUT = r"d:\Code\github\DataTable\DaiblosCoreServer\frida_native_log.txt"

JS = r"""
var installed = false;
var timer = setInterval(function() {
    var m = Process.findModuleByName("libil2cpp.so");
    if (!m) return;
    if (installed) return;
    installed = true;
    clearInterval(timer);
    var base = m.base;
    send("[*] libil2cpp.so base=" + base);

    var targets = {
        "Application.Quit":       0x2BC7550,
        "CSAPI.Quit":             0x14A6180,
        "CSAPI.QuitGame":         0x14A5280,
        "ShiryuSDKExitGame":      0x14CC1A0,
    };
    Object.keys(targets).forEach(function(name) {
        var addr = base.add(targets[name]);
        Interceptor.attach(addr, {
            onEnter: function(args) {
                var bt = Thread.backtrace(this.context, Backtracer.FUZZY)
                    .map(function(a) { return "  " + a + " (rva 0x" + a.sub(base).toString(16) + ")"; })
                    .join("\n");
                send("[NATIVE-HOOK] " + name + " called, backtrace from base " + base + ":\n" + bt);
            }
        });
        send("[*] hooked " + name + " @ " + addr);
    });
    send("[*] native hooks installed");
}, 300);
"""

log = open(OUT, "w", encoding="utf-8")

def on_message(message, data):
    if message["type"] == "send":
        line = message["payload"]
        print(line)
        log.write(line + "\n"); log.flush()
    elif message["type"] == "error":
        print("[JS ERR]", message.get("stack", message))
        log.write("[JS ERR] " + str(message) + "\n"); log.flush()

dev = frida.get_usb_device(timeout=5)
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
