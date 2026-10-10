package com.excelliance.lbsdk.main.ag;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;

    public a(Context context) {
        this.a = context;
    }

    public String a() {
        return GameUtil.getExternalDataPath(this.a);
    }

    public String b() {
        return GameUtil.getCachePath(this.a);
    }

    public boolean c() {
        return BwbxUtil.isSmallPkg();
    }

    public String d() {
        return BwbxUtil.getRecordPath(this.a);
    }

    public String e() {
        return GameUtil.getCachePath(this.a);
    }
}
