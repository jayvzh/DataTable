package com.excelliance.lbsdk.library;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface DialogClickCallback {
    void dismiss();

    void onCheckBox(int i, Bundle bundle, boolean z);

    void onNegativeClick(int i, Bundle bundle);

    void onPositiveClick(int i, Bundle bundle);
}
