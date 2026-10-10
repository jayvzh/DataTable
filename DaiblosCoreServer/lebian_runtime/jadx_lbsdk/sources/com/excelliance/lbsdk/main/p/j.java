package com.excelliance.lbsdk.main.p;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class j {
    private final boolean a;
    private LinkedList<a> b;
    private final Object c;
    private volatile boolean d;

    private static class a {
        long a;
        AtomicLong b = new AtomicLong();

        public a(long j) {
            this.a = j;
        }

        public long a() {
            return this.a;
        }

        public void a(long j) {
            this.b.addAndGet(j);
        }

        public long b() {
            return this.b.get();
        }

        public void b(long j) {
            this.a = j;
            this.b.set(0L);
        }
    }

    public j() {
        this(false);
    }

    public j(boolean z) {
        this.c = new Object();
        this.a = z;
        this.b = new LinkedList<>();
    }

    private long b() {
        return System.currentTimeMillis() / 1000;
    }

    private void e(long j) {
        if (this.a) {
            h(j);
        } else {
            g(j);
        }
    }

    private long f(long j) {
        if (this.b.isEmpty()) {
            return 0L;
        }
        long jB = b();
        long j2 = jB - j;
        LinkedList<a> linkedList = this.b;
        ListIterator<a> listIterator = linkedList.listIterator(linkedList.size() - 1);
        int i = 0;
        long jB2 = 0;
        long j3 = 0;
        int i2 = 0;
        while (listIterator.hasPrevious() && i < j) {
            a aVarPrevious = listIterator.previous();
            long jA = aVarPrevious.a();
            if (jA < jB) {
                if (jA < j2) {
                    break;
                }
                jB2 += aVarPrevious.b();
                i++;
                i2 = 0 == j3 ? i2 + 1 : (int) (((long) i2) + (j3 - jA));
                j3 = jA;
            }
        }
        if (i2 < 1) {
            return 0L;
        }
        double d = jB2;
        double d2 = i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (long) ((d / d2) + 0.5d);
    }

    private void g(long j) {
        a aVar;
        long jB = b();
        a last = !this.b.isEmpty() ? this.b.getLast() : null;
        if (last != null && last.a() == jB) {
            last.a(j);
            return;
        }
        if (this.b.size() > 60) {
            aVar = this.b.removeFirst();
            aVar.b(jB);
        } else {
            aVar = new a(jB);
        }
        aVar.a(j);
        this.b.add(aVar);
    }

    private void h(long j) {
        synchronized (this.c) {
            g(j);
        }
    }

    private long i(long j) {
        long jF;
        synchronized (this.c) {
            jF = f(j);
        }
        return jF;
    }

    public void a() {
    }

    public void a(long j) {
        if (j >= 0) {
            e(j);
            return;
        }
        throw new RuntimeException("in stream size must > 0, current size : " + j);
    }

    public long b(long j) {
        if (this.d) {
            return 0L;
        }
        return d(j);
    }

    public long c(long j) {
        double dB = b(j);
        Double.isNaN(dB);
        return (int) ((dB / 1024.0d) + 0.5d);
    }

    public long d(long j) {
        return this.a ? i(j) : f(j);
    }
}
