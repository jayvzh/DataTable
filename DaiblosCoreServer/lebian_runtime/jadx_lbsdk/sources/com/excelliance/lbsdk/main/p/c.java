package com.excelliance.lbsdk.main.p;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
public class c extends g {
    private volatile boolean a;

    public c(com.excelliance.lbsdk.main.b.c cVar) {
        super(cVar);
    }

    public static void a(String str, long j) {
        c cVarA = ((com.excelliance.lbsdk.main.l.b) LBSdkHelper.application().a("Download")).a();
        cVarA.b(j);
        cVarA.h();
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void a(Context context, int i) {
        a(context, 2, i);
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void a(Context context, long j) {
        a(context, 3, j);
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void a_() {
        synchronized (this) {
            super.a_();
        }
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void a_(long j) {
        if (!this.a) {
            synchronized (this) {
                int[] iArr = new int[2];
                BwbxUtil.getTotalCountAndDownloadedCount(iArr);
                a(iArr[0], iArr[1]);
                this.a = true;
            }
        }
        super.a_(1L);
    }
}
