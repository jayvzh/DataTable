package com.excelliance.lbsdk.main.d;

import android.content.Context;
import com.excelliance.lbsdk.main.b.f;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class a extends f {
    private int a;
    private boolean b;
    private String c;

    public static String a(Context context) {
        return ("/data/local/tmp/" + context.getPackageName()) + "/files";
    }

    private boolean d() {
        Context context = getContext();
        File file = new File(com.excelliance.lbsdk.main.j.a.a(context) + "/lebian/.debug");
        if (file.isDirectory()) {
            this.c = file.getAbsolutePath();
            return true;
        }
        File file2 = new File(a(context) + "/lebian/.debug");
        boolean zIsDirectory = file2.isDirectory();
        if (zIsDirectory) {
            this.c = file2.getAbsolutePath();
        }
        return zIsDirectory;
    }

    private boolean e() {
        if (f()) {
            return true;
        }
        com.excelliance.lbsdk.main.e.a aVar = (com.excelliance.lbsdk.main.e.a) getService("GlobalSettings");
        return !(aVar.d() && aVar.e() == 0);
    }

    private boolean f() {
        if (this.a == 0) {
            this.a = new File(getContext().getCacheDir().getParent(), "lebian_privacy").exists() ? 2 : 1;
        }
        return 2 == this.a;
    }

    public boolean a() {
        return this.b;
    }

    public String b() {
        if (this.b) {
            return this.c;
        }
        return null;
    }

    public String c() {
        String strB = b();
        if (strB == null) {
            return null;
        }
        return strB + "/assets";
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void preInitialize() {
        super.preInitialize();
        this.b = !e() ? false : d();
    }
}
