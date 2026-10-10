package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.q;
import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class h extends b {
    private com.excelliance.lbsdk.main.ad.c a = new com.excelliance.lbsdk.main.ad.c();
    private com.excelliance.lbsdk.main.aa.f b;
    private Context c;
    private String d;

    public h(Context context) {
        this.c = context;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        com.excelliance.lbsdk.main.aa.f fVarB = this.b;
        if (fVarB == null) {
            fVarB = this.a.b(com.excelliance.lbsdk.main.ai.i.a(this.d, str));
            this.b = fVarB;
        }
        return fVarB.a();
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        if (j2 >= 1) {
            return j2;
        }
        com.excelliance.lbsdk.main.aa.f fVarA = this.a.a(com.excelliance.lbsdk.main.ai.i.a(this.d, str), j);
        long jA = fVarA.a();
        this.b = fVarA;
        return jA;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        return b(str, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2) {
        return !new File(str2, str).exists();
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        com.excelliance.lbsdk.main.aa.f fVarB = this.b;
        if (fVarB == null) {
            fVarB = this.a.b(com.excelliance.lbsdk.main.ai.i.a(this.d, str), j, (j2 + j) - 1);
        }
        return fVarB.b();
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        if (str == null) {
            str = q.a(this.c, i);
        }
        this.d = str;
        return true;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, String str2) {
        return new File(str2, str).exists();
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean c(String str, long j, long j2) {
        if (this.b != null) {
            this.b = null;
        }
        return super.c(str, j, j2);
    }
}
