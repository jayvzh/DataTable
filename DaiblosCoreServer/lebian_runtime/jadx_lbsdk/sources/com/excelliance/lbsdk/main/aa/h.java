package com.excelliance.lbsdk.main.aa;

/* JADX INFO: loaded from: classes.dex */
public class h {
    private int a = 60000;
    private int b = 60000;
    private boolean c;
    private boolean d;

    private h() {
    }

    public static h a() {
        return new h();
    }

    public h a(int i) {
        if (i > 0) {
            this.a = i;
        }
        return this;
    }

    public h a(boolean z) {
        this.c = z;
        return this;
    }

    public h b(int i) {
        if (i > 0) {
            this.b = i;
        }
        return this;
    }

    public h b(boolean z) {
        this.d = z;
        return this;
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public int d() {
        return this.a;
    }

    public int e() {
        return this.b;
    }
}
