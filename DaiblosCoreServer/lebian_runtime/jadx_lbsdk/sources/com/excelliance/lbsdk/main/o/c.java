package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.AssetRedirector;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class c extends i {
    private Context d;
    private long[] e;
    private int f;
    private final boolean g;
    private h h;
    private i i;
    private i j;

    public c(Context context) {
        super(context);
        this.d = context;
        this.g = ((com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting")).c();
    }

    private void e() {
        Context context;
        int i = this.f;
        if (!this.g) {
            this.h = new h(this.d);
            return;
        }
        int i2 = 1;
        if (((com.excelliance.lbsdk.main.ab.a) LBSdkHelper.application().a("GlobalSettings")).f()) {
            context = this.d;
        } else {
            this.i = i.a(this.d, 1, i);
            if (!((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).d()) {
                return;
            }
            context = this.d;
            i2 = 2;
        }
        this.j = i.a(context, i2, i);
    }

    private boolean f() {
        return (this.h == null && this.i == null && this.j == null) ? false : true;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
        return this.e[1];
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return j;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
        return j2 > 0 ? j2 : this.e[1];
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        this.f = i;
        if (!f()) {
            e();
        }
        if (!this.g) {
            return this.h.a(str, i);
        }
        i iVar = this.i;
        boolean zA = iVar != null ? false | iVar.a(str, i) : false;
        i iVar2 = this.j;
        return iVar2 != null ? zA & iVar2.a(str, i) : zA;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        return AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
        long j3 = this.e[0];
        long j4 = j + j3;
        if (!this.g) {
            return this.h.b(i.a, j4, j2);
        }
        long jA = i.a(this.i);
        long jA2 = i.a(this.j);
        String str2 = i.b;
        if (j3 < jA2) {
            return this.j.b(str2, j4, j2);
        }
        long j5 = jA + jA2;
        if (j3 < j5) {
            return this.i.b(str2, j4 - jA2, j2);
        }
        com.excelliance.lbsdk.main.g.b.e("MissingBigDownloadManager", "offset(%d) exceed mp4 length(%d)", Long.valueOf(j4), Long.valueOf(j5));
        return null;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        this.f = i;
        if (!f()) {
            e();
        }
        if (!this.g) {
            return this.h.b(str, i);
        }
        i iVar = this.i;
        boolean zB = iVar != null ? false | iVar.b(str, i) : false;
        i iVar2 = this.j;
        return iVar2 != null ? zB & iVar2.b(str, i) : zB;
    }
}
