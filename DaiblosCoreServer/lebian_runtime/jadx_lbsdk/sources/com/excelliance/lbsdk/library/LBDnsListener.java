package com.excelliance.lbsdk.library;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface LBDnsListener {
    void onAutoDismissDialog(int i);

    void onComputeMD5Finish(int i);

    void onComputeMD5Progress(int i, int i2);

    void onComputeMD5Start(int i);

    void onDownloadConnecting(int i);

    void onDownloadFinish(int i);

    void onDownloadProgress(int i, int i2);

    void onDownloadSize(long j, long j2);

    void onDownloadSpeed(float f);

    void onDownloadStart(int i, String str);

    void onError(int i, String str);

    void onExtractFinish(int i);

    void onExtractProgress(int i, int i2);

    void onExtractStart(int i, String str);

    void onShowDialog(int i, Bundle bundle, DialogClickCallback dialogClickCallback);
}
