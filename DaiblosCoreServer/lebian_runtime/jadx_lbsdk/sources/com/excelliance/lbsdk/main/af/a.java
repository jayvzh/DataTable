package com.excelliance.lbsdk.main.af;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static int a = 5000;
    private Handler b;
    private Context c;
    private b d;
    private final Runnable e = new Runnable() { // from class: com.excelliance.lbsdk.main.af.a.1
        long[] a = new long[2];

        @Override // java.lang.Runnable
        public void run() {
            a.this.d.g();
            Intent intent = new Intent(a.this.c.getPackageName() + BwbxUtil.ACTION_RECORD_SIZE);
            intent.setPackage(a.this.c.getPackageName());
            a.this.d.a(this.a);
            intent.putExtra("count", (int) this.a[0]);
            intent.putExtra(BwbxUtil.ACTION_SIZE, this.a[1]);
            a.this.c.sendBroadcast(intent);
            a.this.b.postDelayed(a.this.e, a.a);
        }
    };

    public a(Context context, b bVar) {
        this.c = context;
        this.d = bVar;
    }

    public void a() {
        Handler handler = this.b;
        if (handler == null) {
            HandlerThread handlerThread = new HandlerThread("Broadcast");
            handlerThread.start();
            Handler handler2 = new Handler(handlerThread.getLooper());
            this.b = handler2;
            handler = handler2;
        }
        handler.postDelayed(this.e, 0L);
    }

    public void b() {
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacks(this.e);
        }
    }
}
