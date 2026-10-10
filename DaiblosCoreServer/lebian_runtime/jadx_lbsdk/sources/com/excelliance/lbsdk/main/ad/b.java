package com.excelliance.lbsdk.main.ad;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private long a;
    private long b;

    public b(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long c() {
        return (this.b - this.a) + 1;
    }
}
