package com.excelliance.lbsdk.main.bwbx.so;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class X86Bridge extends a {
    private native void nativeAttachBaseContext(Context context);

    private native void nativeSetLogLevel(int i);

    @Override // com.excelliance.lbsdk.main.bwbx.so.a
    protected void b(int i) {
        nativeSetLogLevel(i);
    }

    @Override // com.excelliance.lbsdk.main.bwbx.so.a
    protected void b(Context context) {
        nativeAttachBaseContext(context);
    }

    @Override // com.excelliance.lbsdk.main.bwbx.so.a
    protected void b(Object obj) {
        nativeAttachBridge(obj);
    }

    protected native void nativeAttachBridge(Object obj);
}
