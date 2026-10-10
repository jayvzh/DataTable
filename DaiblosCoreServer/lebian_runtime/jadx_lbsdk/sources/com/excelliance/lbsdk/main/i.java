package com.excelliance.lbsdk.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class i {
    private Context a;

    public i(Context context) {
        this.a = context;
    }

    public String M() {
        return this.a.getPackageName();
    }

    public Resources N() {
        return PlatformResources.getMainResources(this.a);
    }

    public PackageManager O() {
        return this.a.getPackageManager();
    }

    protected void b(Intent intent) {
        GameUtil.startService(intent, this.a);
    }

    public void c(Intent intent) {
        w.a(this.a).b(intent);
    }

    public void d(Intent intent) {
        s.a(this.a, intent);
    }
}
