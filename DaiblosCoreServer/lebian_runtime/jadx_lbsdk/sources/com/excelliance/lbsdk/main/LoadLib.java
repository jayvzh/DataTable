package com.excelliance.lbsdk.main;

import android.os.Build;
import java.io.File;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class LoadLib {
    public static String[] getSoList() {
        return new String[]{"libunity.so"};
    }

    public static void load(String str, ClassLoader classLoader) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.LBLoad", true, classLoader).getDeclaredMethod("load", String.class);
            declaredMethod.setAccessible(true);
            for (String str2 : getSoList()) {
                String str3 = str + "lib" + File.separator + Build.CPU_ABI + File.separator + str2;
                if (new File(str3).exists()) {
                    com.excelliance.lbsdk.main.g.b.c("LoadLib", "load: " + str2, new Object[0]);
                    declaredMethod.invoke(null, str3);
                } else {
                    String str4 = str + "lib" + File.separator + Build.CPU_ABI2 + File.separator + str2;
                    if (new File(str4).exists()) {
                        com.excelliance.lbsdk.main.g.b.c("LoadLib", "load: " + str2, new Object[0]);
                        declaredMethod.invoke(null, str4);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
