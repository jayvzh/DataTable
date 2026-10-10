package com.excelliance.lbsdk.library;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public interface NextChapterInterface {
    void init(LBDnsListener lBDnsListener, Handler handler, Intent intent);

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    boolean onKeyDown(int i, KeyEvent keyEvent);

    void onPause();

    void onResume();

    void onStop();

    void prepareViewFinished();
}
