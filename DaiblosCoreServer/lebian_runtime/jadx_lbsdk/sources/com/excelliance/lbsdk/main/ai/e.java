package com.excelliance.lbsdk.main.ai;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ao;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static String a(Context context) {
        int i = 0;
        try {
            i = context.getPackageManager().getApplicationInfo(context.getPackageName(), GameUtil.F_NOTIFY_TYPE_AB_TEST).metaData.getInt("MainChId");
        } catch (Exception e) {
            ao.e("ManifestUtil", "", e, new Object[0]);
        }
        return String.valueOf(i);
    }
}
