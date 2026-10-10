package com.excelliance.lbsdk.main.p;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private j a;
    protected Context b;
    private i c;
    private int d = -10;
    private com.excelliance.lbsdk.main.b.c e;

    public g(com.excelliance.lbsdk.main.b.c cVar) {
        this.e = cVar;
        this.b = cVar.a();
    }

    protected static int e(int i) {
        if (3 == i) {
            return 4;
        }
        if (2 == i) {
            return 1;
        }
        return 1 == i ? 3 : 2;
    }

    public static int f(int i) {
        return e(i);
    }

    public void a(long j, long j2) {
        b(j, j2);
    }

    protected void a(Context context, int i) {
    }

    void a(Context context, int i, int i2) {
        int iC = c(context);
        int iG = g();
        com.excelliance.lbsdk.main.g.b.c("Downloader", "report download progress, percent(%d), downType(%d), pkgType(%d), upgradeStat(%d)", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(iC), Integer.valueOf(iG));
        com.excelliance.lbsdk.main.k.c.a(context, i, iC, iG, i2);
    }

    void a(Context context, int i, long j) {
        com.excelliance.lbsdk.main.g.b.c("Downloader", "report download speed, speed(%d), type(%d)", Long.valueOf(j), Integer.valueOf(i));
        com.excelliance.lbsdk.main.k.c.a(context, i, j);
    }

    protected void a(Context context, long j) {
    }

    protected void a_() {
        Context context = this.b;
        int iA = this.c.a();
        if (iA - this.d >= 10) {
            int i = (iA / 10) * 10;
            a(context, i);
            a(context, this.a.c(15L));
            this.d = i;
        }
    }

    protected void a_(long j) {
        this.c.b(j);
    }

    public void b(long j) {
        a_(j);
    }

    protected void b(long j, long j2) {
        this.a = new j(true);
        this.a.a();
        this.c = new i(j);
        this.c.a(j2);
        int iA = this.c.a();
        if (iA > 0) {
            this.d = (iA / 10) * 10;
        }
    }

    protected void b(Context context, int i) {
    }

    protected int c(Context context) {
        if (GameUtil.getIntance().isTwload(context)) {
            return 3;
        }
        return ((com.excelliance.lbsdk.main.e.a) this.e.a("GlobalSettings")).f() ? 1 : 2;
    }

    public void c(long j) {
        d(j);
    }

    public void c(long j, long j2) {
        i iVar = new i(j);
        this.c = iVar;
        iVar.a(j2);
        int iA = this.c.a();
        this.d = iA > 0 ? (iA / 10) * 10 : -10;
    }

    protected void d(long j) {
        this.a.a(j);
    }

    public void e(long j) {
        this.c.b(j);
    }

    protected int g() {
        com.excelliance.lbsdk.main.ah.b bVar = (com.excelliance.lbsdk.main.ah.b) this.e.a("PreferenceModule");
        if (bVar.a()) {
            return 3;
        }
        return bVar.b() ? 2 : 1;
    }

    public void h() {
        a_();
    }

    public void i() {
        Context context = this.b;
        int iA = this.c.a();
        if (iA - this.d >= 10) {
            int i = (iA / 10) * 10;
            b(context, i);
            this.d = i;
        }
    }
}
