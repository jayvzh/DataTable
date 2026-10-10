package com.excelliance.lbsdk.main.ai;

import android.content.Context;
import android.content.Intent;
import com.excelliance.lbsdk.main.BGService;
import com.excelliance.lbsdk.main.GameUtil;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) BGService.class);
        intent.setPackage(context.getPackageName());
        GameUtil.startService(intent, context);
    }

    public static void a(Context context, com.excelliance.lbsdk.main.z.c cVar) {
        Intent intent = new Intent(context, (Class<?>) BGService.class);
        intent.setAction("com.excelliance.open.action.bwbxdla");
        intent.setPackage(context.getPackageName());
        intent.putExtra("dlsType", 2);
        intent.putExtra("dlsZipType", 3);
        intent.putExtras(cVar.a());
        intent.putExtra("extra.lbsdk.hostintent", true);
        GameUtil.startService(intent, context);
    }
}
