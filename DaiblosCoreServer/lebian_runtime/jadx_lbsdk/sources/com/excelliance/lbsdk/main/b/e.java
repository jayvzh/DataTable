package com.excelliance.lbsdk.main.b;

import android.content.Context;
import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public class e extends f {
    private String a;
    private int b;

    public boolean a() {
        int i = this.b;
        boolean z = false;
        if (i > 0) {
            return 2 == i;
        }
        Context context = getContext();
        String strA = this.a;
        if (strA == null) {
            strA = com.excelliance.lbsdk.main.j.b.a(Process.myPid());
            this.a = strA;
        }
        if (strA != null && strA.equals(context.getApplicationInfo().processName)) {
            z = true;
        }
        if (z) {
            this.b = z ? 2 : 1;
        }
        return z;
    }
}
