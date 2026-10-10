package com.excelliance.lbsdk.main.a;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class a {
    ThreadPoolExecutor a;
    private int b;
    private int c;
    private ThreadFactory d;

    public a(int i, int i2, ThreadFactory threadFactory) {
        this.b = i;
        this.c = i2;
        this.d = threadFactory;
    }

    private void a() {
        ThreadPoolExecutor threadPoolExecutor = this.a;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown() || this.a.isTerminated()) {
            synchronized (a.class) {
                if (this.a == null || this.a.isShutdown() || this.a.isTerminated()) {
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
                    ThreadFactory threadFactoryDefaultThreadFactory = this.d;
                    if (threadFactoryDefaultThreadFactory == null) {
                        threadFactoryDefaultThreadFactory = Executors.defaultThreadFactory();
                    }
                    ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
                    this.a = new ThreadPoolExecutor(this.b, this.c, 3000L, timeUnit, linkedBlockingDeque, threadFactoryDefaultThreadFactory, discardPolicy);
                }
            }
        }
    }

    public void a(Runnable runnable) {
        a();
        this.a.execute(runnable);
    }
}
