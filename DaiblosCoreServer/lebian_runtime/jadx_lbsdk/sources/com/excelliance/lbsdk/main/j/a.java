package com.excelliance.lbsdk.main.j;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Context context) {
        String path = null;
        try {
            if (Build.VERSION.SDK_INT >= 11 && context.getExternalFilesDir(null) != null) {
                path = context.getExternalFilesDir(null).getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((path != null && path.length() > 0) || !Environment.getExternalStorageState().equals("mounted")) {
            return path;
        }
        StringBuffer stringBuffer = new StringBuffer(Environment.getExternalStorageDirectory().getPath());
        stringBuffer.append("/Android/data/");
        stringBuffer.append(context.getPackageName());
        stringBuffer.append("/files");
        return stringBuffer.toString();
    }
}
