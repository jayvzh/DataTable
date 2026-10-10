package com.excelliance.lbsdk.main;

import android.os.Build;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import dalvik.system.PathClassLoader;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class FakeClassLoader {
    public static final String TAG = "FakeClassLoader";
    private static FakeClassLoader a;

    private String a(String str) {
        if (str == null) {
            throw new NullPointerException("nickname == null");
        }
        return "lib" + str + ".so";
    }

    public static FakeClassLoader getInstance() {
        if (a == null) {
            synchronized (FakeClassLoader.class) {
                if (a == null) {
                    a = new FakeClassLoader();
                }
            }
        }
        return a;
    }

    public String findLibrary(String str) {
        com.excelliance.lbsdk.main.g.b.c(TAG, "findLibrary: " + str + " cpu:" + Build.CPU_ABI + " cpu2:" + Build.CPU_ABI2, new Object[0]);
        if (!str.contains("il2cpp")) {
            return ((PathClassLoader) getClass().getClassLoader()).findLibrary(str);
        }
        return BwbxUtil.getCurrCachePath() + "lib" + File.separator + Build.CPU_ABI + File.separator + a(str);
    }
}
