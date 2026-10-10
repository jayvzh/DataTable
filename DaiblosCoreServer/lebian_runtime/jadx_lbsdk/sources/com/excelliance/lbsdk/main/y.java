package com.excelliance.lbsdk.main;

import android.os.SystemClock;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
class y implements Runnable {
    private static int g;
    private String b;
    private String c;
    private ZipFile d;
    private int e;
    private final int f;
    private final int h;
    private boolean i;
    private List<String> j;
    private final List<String> k;
    private final List<String> l;
    private static AtomicInteger m = new AtomicInteger(0);
    private static AtomicInteger n = new AtomicInteger(0);
    public static volatile Exception a = null;

    public y(int i, int i2, ZipFile zipFile, String str) {
        this.i = true;
        this.e = i;
        this.d = zipFile;
        this.b = str;
        this.k = aq.a();
        this.l = null;
        this.f = aq.a().size();
        this.h = i2;
    }

    public y(int i, int i2, ZipFile zipFile, String str, String str2) {
        this.i = true;
        this.e = i;
        this.d = zipFile;
        this.b = str;
        this.c = str2;
        this.k = aq.a();
        this.l = aq.b();
        this.f = aq.a().size();
        g = aq.b().size();
        this.h = i2;
    }

    public y(int i, int i2, ZipFile zipFile, String str, boolean z, List<String> list) {
        this.i = true;
        this.e = i;
        this.d = zipFile;
        this.b = str;
        this.k = aq.a();
        this.l = null;
        this.f = aq.a().size();
        this.h = i2;
        this.i = z;
        this.j = list;
    }

    public static int a() {
        return m.get();
    }

    public static void a(int i) {
        m.set(i);
    }

    private void a(int i, int i2, boolean z) {
        int i3 = i - 1;
        int iFloor = (int) Math.floor((this.f * i) / i2);
        for (int iFloor2 = (int) Math.floor((this.f * i3) / i2); iFloor2 < iFloor; iFloor2++) {
            try {
                n.getAndIncrement();
                aq.a(this.d, this.k.get(iFloor2), this.b, z, this.j);
            } catch (Exception e) {
                a = e;
                e.printStackTrace();
            }
        }
        int iFloor3 = (int) Math.floor((i * g) / i2);
        for (int iFloor4 = (int) Math.floor((i3 * g) / i2); iFloor4 < iFloor3; iFloor4++) {
            try {
                n.getAndIncrement();
                aq.a(this.d, this.l.get(iFloor4), this.c, z, this.j);
            } catch (Exception e2) {
                a = e2;
                e2.printStackTrace();
            }
        }
        m.getAndIncrement();
    }

    public static int b() {
        int i = n.get();
        if (g == 0 || i < 100) {
            return i;
        }
        return 99;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a(this.e, this.h, this.i);
            while (a() != this.h) {
                SystemClock.sleep(200L);
            }
            ao.e("MultiThreadEntry", "extracting.....", new Object[0]);
        } catch (Exception e) {
            a = e;
            e.printStackTrace();
        }
    }
}
