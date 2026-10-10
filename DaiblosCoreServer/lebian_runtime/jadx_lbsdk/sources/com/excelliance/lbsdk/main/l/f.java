package com.excelliance.lbsdk.main.l;

import android.content.Context;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
public class f extends com.excelliance.lbsdk.main.b.f {
    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void attach(com.excelliance.lbsdk.main.b.c cVar) {
        super.attach(cVar);
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void postInitialize() {
        super.postInitialize();
        Context context = getContext();
        BwbxUtil.setContext(context);
        an.a().a(context);
    }
}
