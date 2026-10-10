package com.excelliance.lbsdk.main.l;

import com.excelliance.lbsdk.main.bwbx.AssetRedirector;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.excelliance.lbsdk.main.g.a {
    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void postLoadSo() {
        super.postLoadSo();
        if (((com.excelliance.lbsdk.main.d.a) getApplicationModule().a("DebugModule")).a()) {
            AssetRedirector.getInstance().setLogLevel(2);
        }
    }
}
