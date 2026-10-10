package com.excelliance.lbsdk.library;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface BwbxUIListener {
    void onFloatProgressChange(int i, long j);

    void onFloatWindowCreate(Activity activity);

    void onFloatWindowDestroy(Activity activity);

    void onFloatWindowDismiss(Activity activity);

    void onFloatWindowShow(Activity activity);

    void onNetworkChange(boolean z);

    void onProgress(int i, long j);

    void onProgressWindowDismiss(Activity activity);

    void onProgressWindowShow(Activity activity);

    void onRecord(int i, long j);

    void onShowDialog(int i, Bundle bundle, Activity activity, boolean z, DialogClickCallback dialogClickCallback);
}
