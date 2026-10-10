package com.excelliance.lbsdk.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class u {
    public static Intent a(Context context, String str) {
        GameUtil intance = GameUtil.getIntance();
        if (intance.getContext() == null) {
            intance.setContext(context);
        }
        Uri uriOverInstallApkUri = intance.overInstallApkUri(context, str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uriOverInstallApkUri, "application/vnd.android.package-archive");
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            String str2 = it.next().activityInfo.packageName;
            ao.c("LBSdkModules", "getIstIntent: packageName:" + str2, new Object[0]);
            context.grantUriPermission(str2, uriOverInstallApkUri, 3);
        }
        intent.setFlags(268435456);
        if (Build.VERSION.SDK_INT >= 19) {
            intent.addFlags(64);
        }
        intent.addFlags(3);
        return intent;
    }
}
