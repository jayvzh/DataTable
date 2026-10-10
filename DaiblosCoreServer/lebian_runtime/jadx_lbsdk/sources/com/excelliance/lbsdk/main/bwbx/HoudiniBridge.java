package com.excelliance.lbsdk.main.bwbx;

/* JADX INFO: loaded from: classes.dex */
public class HoudiniBridge {
    public static native void doLoadNativeLibrary(String str, int i, long j);

    public static void loadNativeLibrary(String str, int i, long j) {
        doLoadNativeLibrary(str, i, j);
    }
}
