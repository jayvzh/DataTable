package com.excelliance.lbsdk.main;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a(int i) {
        return Build.VERSION.SDK_INT >= 23 ? i | 67108864 : i;
    }
}
