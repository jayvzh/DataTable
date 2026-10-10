package com.excelliance.lbsdk.main.ai;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public static int a(String str, char c, int i) {
        return a(str, c, 0, i);
    }

    public static int a(String str, char c, int i, int i2) {
        int iIndexOf;
        if (str == null) {
            return -1;
        }
        int i3 = 0;
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (true) {
            iIndexOf = str.indexOf(c, i);
            int i4 = i3 + 1;
            if (i3 >= i2 || iIndexOf == -1) {
                break;
            }
            i = iIndexOf + 1;
            i3 = i4;
        }
        return iIndexOf;
    }
}
