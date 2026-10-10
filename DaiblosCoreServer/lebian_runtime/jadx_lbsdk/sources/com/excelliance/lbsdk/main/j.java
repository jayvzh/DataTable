package com.excelliance.lbsdk.main;

import android.os.FileObserver;

/* JADX INFO: loaded from: classes.dex */
public class j extends FileObserver {
    private String a;
    private int b;
    private String c;

    @Override // android.os.FileObserver
    public void onEvent(int i, String str) {
        int i2 = i & 4095;
        com.excelliance.lbsdk.main.g.b.c("FileListener", "onEvent event:" + i2 + " path:" + str, new Object[0]);
        if (i2 != 512) {
            return;
        }
        com.excelliance.lbsdk.main.g.b.c("FileListener", "FileObserver.DELETE path=" + str, new Object[0]);
        if (str.equals(this.a)) {
            com.excelliance.lbsdk.main.g.b.c("FileListener", "FileObserver.DELETE targetFile deleted", new Object[0]);
            int i3 = this.b;
            if (i3 != 1) {
                if (i3 != 2) {
                    return;
                }
                GameUtil.getIntance().killAllProcesses();
            } else {
                if (an.a().c(this.c)) {
                    return;
                }
                GameUtil.getIntance().killThirdProcesses();
            }
        }
    }
}
