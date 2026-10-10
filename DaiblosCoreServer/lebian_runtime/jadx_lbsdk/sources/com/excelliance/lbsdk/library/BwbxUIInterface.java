package com.excelliance.lbsdk.library;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface BwbxUIInterface {
    void create(Context context);

    void init(Application application, boolean z, BwbxUIListener bwbxUIListener);
}
