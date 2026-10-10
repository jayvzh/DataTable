package com.excelliance.lbsdk.main.ag;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private Context a;

    public b(Context context) {
        this.a = context;
    }

    public String a() {
        return this.a.getPackageName();
    }

    public String b() {
        return this.a.getPackageCodePath();
    }

    public String c() {
        return this.a.getExternalFilesDir(null).getAbsolutePath();
    }

    public String d() {
        return this.a.getFilesDir().getAbsolutePath();
    }
}
