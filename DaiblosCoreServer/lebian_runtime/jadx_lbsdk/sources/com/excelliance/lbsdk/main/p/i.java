package com.excelliance.lbsdk.main.p;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class i {
    private final long a;
    private long b;
    private AtomicLong c = new AtomicLong();

    public i(long j) {
        this.a = j;
    }

    public int a() {
        double d = this.c.get();
        double d2 = this.a;
        Double.isNaN(d);
        Double.isNaN(d2);
        return Math.min(Math.max((int) (((d / d2) * 100.0d) + 0.5d), 0), 100);
    }

    public void a(long j) {
        if (this.c.get() > 0) {
            throw new IllegalStateException("can not setRestore after calling in");
        }
        if (j < 0) {
            throw new IllegalStateException("restore must >= 0");
        }
        this.b = j;
        this.c.set(j);
    }

    public void b(long j) {
        this.c.addAndGet(j);
    }
}
