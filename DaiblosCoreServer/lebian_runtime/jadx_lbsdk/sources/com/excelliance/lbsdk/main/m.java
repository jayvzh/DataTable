package com.excelliance.lbsdk.main;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class m {
    public static int a(Context context, String str, int i) {
        try {
            i = Class.forName("com.excelliance.open.GlobalSettings", false, GameUtil.getClassLoader(context)).getField(str).getInt(null);
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("GlobalSettingsHelper", "getGlobalInt Exception", e, new Object[0]);
        }
        return ae.b(context, "lebian_module_global_settings", str, i);
    }

    public static boolean a(Context context, String str, boolean z) {
        try {
            z = Class.forName("com.excelliance.open.GlobalSettings", false, GameUtil.getClassLoader(context)).getField(str).getBoolean(null);
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("GlobalSettingsHelper", "getGlobalBoolean Exception", e, new Object[0]);
        }
        return ae.b(context, "lebian_module_global_settings", str, z);
    }

    public static void b(Context context, String str, int i) {
        try {
            Class.forName("com.excelliance.open.GlobalSettings", false, GameUtil.getClassLoader(context)).getDeclaredField(str).setInt(null, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
