package com.excelliance.lbsdk.main.n;

import android.content.Context;
import android.content.Intent;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;

    public a(Context context) {
        this.a = context;
    }

    private Intent a() {
        Intent intent = new Intent("com.excelliance.assist.action.thruster");
        intent.putExtra("action", "action_start_update_jar");
        intent.setFlags(805306368);
        intent.setPackage(this.a.getPackageName());
        return intent;
    }

    public void a(int i, int i2) {
        Intent intentA = a();
        intentA.putExtra("dialogtype", i);
        intentA.putExtra(BwbxUtil.ACTION_TYPE, i2);
        intentA.putExtra("action", "action_start_update_jar");
        this.a.startActivity(intentA);
    }
}
