package com.excelliance.lbsdk.main.ae;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private int a;
    private String b;

    public b(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public boolean a() {
        return this.a == 0;
    }

    public boolean b() {
        return this.a == 2;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        int i = this.a;
        if (i == 0) {
            return "-f";
        }
        return i == 2 ? "em" : "up";
    }
}
