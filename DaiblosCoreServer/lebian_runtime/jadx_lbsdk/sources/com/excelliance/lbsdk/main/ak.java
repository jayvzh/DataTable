package com.excelliance.lbsdk.main;

/* JADX INFO: loaded from: classes.dex */
public class ak {
    static aj a;
    static aj b;

    public static aj a() {
        if (a == null) {
            synchronized (ak.class) {
                if (a == null) {
                    a = new aj(20, 20);
                }
            }
        }
        return a;
    }

    public static aj b() {
        if (b == null) {
            synchronized (ak.class) {
                if (b == null) {
                    b = new aj(20, 20);
                }
            }
        }
        return b;
    }
}
