package com.excelliance.lbsdk.library;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface PromptDialogListener {
    void onShowDialog(int i, Bundle bundle, DialogClickCallback dialogClickCallback);

    void onToast(int i, Bundle bundle);
}
