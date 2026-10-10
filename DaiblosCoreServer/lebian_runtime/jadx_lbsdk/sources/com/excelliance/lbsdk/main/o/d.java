package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.ao;
import com.excelliance.lbsdk.main.s.af;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class d extends i {
    private final boolean d;
    private h e;
    private i f;
    private i g;
    private int h;
    private Context i;

    public d(Context context) {
        super(context);
        this.i = context;
        this.d = ((com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting")).c();
    }

    private void e() {
        Context context;
        int i = this.h;
        if (!this.d) {
            this.e = new h(this.i);
            return;
        }
        int i2 = 1;
        if (((com.excelliance.lbsdk.main.ab.a) LBSdkHelper.application().a("GlobalSettings")).f()) {
            context = this.i;
        } else {
            this.f = i.a(this.i, 1, i);
            if (!((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).d()) {
                return;
            }
            context = this.i;
            i2 = 2;
        }
        this.g = i.a(context, i2, i);
    }

    private boolean f() {
        return (this.e == null && this.f == null && this.g == null) ? false : true;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        af afVarA;
        af afVarA2;
        if (!this.d) {
            return this.e.a(str);
        }
        i iVar = this.g;
        if (iVar != null && (afVarA2 = iVar.c().a(str)) != null) {
            return afVarA2.getSize();
        }
        i iVar2 = this.f;
        return (iVar2 == null || (afVarA = iVar2.c().a(str)) == null) ? super.a(str) : afVarA.getSize();
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return !this.d ? this.e.a(str, j) : j;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        if (!this.d) {
            return this.e.a(str, j, j2);
        }
        i iVar = this.g;
        if (iVar != null && iVar.c().a(str) != null) {
            return iVar.a(str, j, j2);
        }
        i iVar2 = this.f;
        if (iVar2 != null && iVar2.c().a(str) != null) {
            return iVar2.a(str, j, j2);
        }
        ao.e("MissingResDownloadManager", "restoreDownloadLength, not found(%s)", str);
        return 0L;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        this.h = i;
        if (!f()) {
            e();
        }
        if (!this.d) {
            return this.e.a(str, i);
        }
        i iVar = this.f;
        boolean zA = iVar != null ? false | iVar.a(str, i) : false;
        i iVar2 = this.g;
        return iVar2 != null ? zA & iVar2.a(str, i) : zA;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        if (!this.d) {
            return this.e.b(str, j, j2);
        }
        i iVar = this.g;
        if (iVar != null && iVar.c().a(str) != null) {
            return iVar.b(str, j, j2);
        }
        i iVar2 = this.f;
        if (iVar2 != null && iVar2.c().a(str) != null) {
            return iVar2.b(str, j, j2);
        }
        ao.e("MissingResDownloadManager", "getInputStream, not found(%s)", str);
        return null;
    }

    @Override // com.excelliance.lbsdk.main.o.i, com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        this.h = i;
        if (!f()) {
            e();
        }
        if (!this.d) {
            return this.e.a(str, i);
        }
        i iVar = this.f;
        boolean zA = iVar != null ? false | iVar.a(str, i) : false;
        i iVar2 = this.g;
        return iVar2 != null ? zA & iVar2.a(str, i) : zA;
    }
}
