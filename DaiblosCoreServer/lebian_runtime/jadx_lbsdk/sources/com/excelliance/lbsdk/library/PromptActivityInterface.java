package com.excelliance.lbsdk.library;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public interface PromptActivityInterface {
    void init(PromptDialogListener promptDialogListener, Handler handler, Intent intent);

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    boolean onKeyDown(int i, KeyEvent keyEvent);

    void onNewIntent(Intent intent);

    void onPause();

    void onResume();

    void onStop();

    void prepareViewFinished();
}
