package com.excelliance.lbsdk.main;

/* JADX INFO: loaded from: classes.dex */
public class NativeUtil {
    public static native boolean addInApkInfo(String str, String str2, int i);

    public static native boolean generateZipInfoFile(String str, String str2);

    public static native boolean mergeApkWithUpdatedVersion(String[] strArr, String str, String str2, String[] strArr2, String[] strArr3);

    public static native boolean overwriteEntry(String str, String str2, Object obj, String str3, String str4);

    public static void progress(int i) {
        h.b(i);
    }

    public static native void setHulistPath(String str);

    public static native void setInterruptApplyPatch(boolean z);

    public static native void setWorkSpace(String str);

    public static native boolean upgradeByPatch(String str, String str2, String str3, String str4, String str5, int i);

    public static native boolean upgradeByPatchFile(String[] strArr, String str, String str2, String str3, String str4, int i, int i2);

    public static native boolean upgradeByPatchFileAndRemoveSign(String[] strArr, String str, String str2, String str3, String str4, int i, int i2);

    public static native boolean upgradeByPatchNoGenApk(String[] strArr, String str, String str2);

    public static native boolean upgradeFullApkByFullRes(String str, String str2, String str3, String str4);

    public static native boolean upgradeFullApkByPatchRes(String str, String str2, String str3, boolean z, String str4);

    public static native boolean upgradeHotResByPatchFile(String str, String str2, String str3, int i, int i2, int i3);

    public static native boolean upgradeSmallByPatchFile(String str, String str2, String str3, int i, int i2);
}
