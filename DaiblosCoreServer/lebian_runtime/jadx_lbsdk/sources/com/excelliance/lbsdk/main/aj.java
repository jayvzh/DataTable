package com.excelliance.lbsdk.main;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class aj {
    ThreadPoolExecutor a;
    private int b;
    private int c;

    public aj(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    private void a() {
        ThreadPoolExecutor threadPoolExecutor = this.a;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || this.a.isTerminated()) {
            synchronized (aj.class) {
                if (this.a == null || this.a.isShutdown() || this.a.isTerminated()) {
                    this.a = new ThreadPoolExecutor(this.b, this.c, 3000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
    }

    public void a(Runnable runnable) {
        a();
        this.a.execute(runnable);
    }

    public Future b(Runnable runnable) {
        a();
        return this.a.submit(runnable);
    }
}
