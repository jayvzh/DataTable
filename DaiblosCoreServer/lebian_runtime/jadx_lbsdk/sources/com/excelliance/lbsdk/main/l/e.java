package com.excelliance.lbsdk.main.l;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.excelliance.lbsdk.main.b.e {
    private String a;
    private String b;
    private String c;
    private Boolean d;
    private Boolean e;
    private String f;

    public static String f() {
        return ((e) LBSdkHelper.application().a("Runtime")).b();
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0015  */
    private String i() {
        File parentFile;
        Context context = getContext();
        File file = new File(GameUtil.getExternalDataPath(context), "lebian/assets");
        if (file.isDirectory()) {
            parentFile = file.getParentFile();
        } else {
            File file2 = new File(com.excelliance.lbsdk.main.d.a.a(context), "lebian/assets");
            if (file2.isDirectory()) {
                parentFile = file2.getParentFile();
            } else {
                parentFile = file.getParentFile();
            }
        }
        return parentFile.getAbsolutePath();
    }

    public String b() {
        String str = this.b;
        if (str != null) {
            return str;
        }
        String strI = i();
        this.b = strI;
        return strI;
    }

    public String c() {
        Context context = getContext();
        String str = this.c;
        if (str != null) {
            return str;
        }
        BwbxUtil.setContext(context);
        String currCachePath = BwbxUtil.getCurrCachePath();
        this.c = currCachePath;
        return currCachePath;
    }

    public boolean d() {
        Context context = getContext();
        if (this.d == null) {
            this.d = Boolean.valueOf(GameUtil.getIntance().isTwload(context));
        }
        return this.d.booleanValue();
    }

    public boolean e() throws IOException {
        Boolean bool = this.e;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zIsRecordPkg = BwbxUtil.isRecordPkg(getContext());
        this.e = Boolean.valueOf(zIsRecordPkg);
        return zIsRecordPkg;
    }

    public String g() {
        String str = this.f;
        if (str != null) {
            return str;
        }
        synchronized (this) {
            if (this.f == null) {
                Context context = getContext();
                this.f = com.excelliance.lbsdk.main.ai.f.a(context, context.getPackageResourcePath());
            }
        }
        return this.f;
    }

    public String h() {
        String str = this.a;
        if (str != null) {
            return str;
        }
        this.a = com.excelliance.lbsdk.main.j.a.a(getContext());
        return this.a;
    }
}
