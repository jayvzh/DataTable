package com.excelliance.lbsdk.main.l;

import android.os.HandlerThread;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.excelliance.lbsdk.main.b.f {
    private HandlerThread a;

    public Looper a() {
        HandlerThread handlerThread;
        HandlerThread handlerThread2 = this.a;
        if (handlerThread2 != null) {
            return handlerThread2.getLooper();
        }
        synchronized (this) {
            handlerThread = this.a;
            if (handlerThread == null) {
                handlerThread = new HandlerThread("lb-async-task");
                handlerThread.start();
                this.a = handlerThread;
            }
        }
        return handlerThread.getLooper();
    }
}
