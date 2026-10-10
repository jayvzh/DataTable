package com.excelliance.lbsdk.main.bwbx;

import android.content.Context;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.excelliance.lbsdk.main.o.b {
    private Context a;
    private com.excelliance.lbsdk.main.o.b b;

    public e(Context context) {
        this.a = context;
    }

    private com.excelliance.lbsdk.main.o.b a() {
        com.excelliance.lbsdk.main.o.b bVar = this.b;
        if (bVar != null) {
            return bVar;
        }
        this.b = BwbxUtil.isBigAsset(this.a) ? new com.excelliance.lbsdk.main.o.c(this.a) : new com.excelliance.lbsdk.main.o.d(this.a);
        return this.b;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        return a().a(str);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return a().a(str, j);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        com.excelliance.lbsdk.main.o.b bVarA = a();
        return bVarA.a(str, j, bVarA instanceof com.excelliance.lbsdk.main.o.c ? j2 : 0L);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        return a().a(str, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        return a().b(str, j, j2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        return a().b(str, i);
    }
}
