package com.excelliance.lbsdk.main;

import android.app.Application;
import android.content.Context;
import com.excelliance.lbsdk.library.UploadResult;

/* JADX INFO: loaded from: classes.dex */
public class LBSdkHelper {
    private static com.excelliance.lbsdk.main.l.c a;

    private static com.excelliance.lbsdk.main.l.c a() {
        if (a == null) {
            a = new com.excelliance.lbsdk.main.l.c();
        }
        return a;
    }

    public static com.excelliance.lbsdk.main.l.c application() {
        return a();
    }

    public static void attachApplication(Application application) {
        a().a(application);
    }

    public static void attachBaseContext(Context context) {
        a().a(context);
    }

    public static int clearRecord(Context context) {
        return a().b(context);
    }

    public static void doRecord(String str, boolean z) {
        a().a(str, z);
    }

    public static String getHotResPath() {
        an anVarA = an.a();
        l lVarA = anVarA.a(anVarA.r());
        if (lVarA == null) {
            return null;
        }
        return anVarA.g(lVarA.hotResNmd5);
    }

    public static void initialize() {
        a().b();
    }

    public static void onCreate() {
        a().f();
    }

    public static void overlayInstallation() {
        a().g();
    }

    public static void postLoadSo() {
        a.e();
    }

    public static void preLoadSo() {
        a.d();
    }

    public static void upgrade() {
        a().h();
    }

    public static UploadResult uploadRecord(Context context, int i, String str) {
        return a().a(context, i, str);
    }
}
