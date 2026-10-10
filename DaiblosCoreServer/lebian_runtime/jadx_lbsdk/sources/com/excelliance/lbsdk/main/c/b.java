package com.excelliance.lbsdk.main.c;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private com.excelliance.lbsdk.main.c.a a;

    private class a implements Runnable {
        Context a;
        Intent b;
        int c;
        private Handler e;

        public a(Context context, Intent intent) {
            this.a = context;
            this.b = intent;
        }

        public void a() {
            if (this.e == null) {
                this.e = new Handler();
            }
            this.e.postDelayed(this, 1000L);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.c(this.a, this.b)) {
                return;
            }
            int i = this.c;
            this.c = i + 1;
            if (i < 5) {
                a();
            }
        }
    }

    public b() {
        this(null);
    }

    public b(com.excelliance.lbsdk.main.c.a aVar) {
        this.a = aVar;
    }

    private static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            com.excelliance.lbsdk.main.g.b.e("ServiceStarter", "", e, new Object[0]);
        }
    }

    private boolean a(Context context) {
        com.excelliance.lbsdk.main.c.a aVar = this.a;
        if (aVar != null) {
            return aVar.a(context);
        }
        return false;
    }

    private void b(Context context, Intent intent) {
        new a(context, intent).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Context context, Intent intent) {
        try {
            return d(context, intent);
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("ServiceStarter", null, e, new Object[0]);
            return false;
        }
    }

    private boolean d(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26 || a(context)) {
            intent.removeExtra("startForground");
            context.startService(intent);
        } else {
            try {
                com.excelliance.lbsdk.main.g.b.b("ServiceStarter", "startForegroundService: ", new Object[0]);
                intent.putExtra("startForground", true);
                context.startForegroundService(intent);
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.b("ServiceStarter", "startForegroundService err", new Object[0]);
                com.excelliance.lbsdk.main.g.b.e("ServiceStarter", null, e, new Object[0]);
                intent.removeExtra("startForground");
                context.startService(intent);
            }
        }
        return true;
    }

    public void a(Context context, Intent intent) {
        boolean zC = c(context, intent);
        if (zC) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            b(context, intent);
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 5 || zC) {
                return;
            }
            a(1000L);
            zC = c(context, intent);
            i = i2;
        }
    }
}
