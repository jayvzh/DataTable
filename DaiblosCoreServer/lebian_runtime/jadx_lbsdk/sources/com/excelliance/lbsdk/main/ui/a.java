package com.excelliance.lbsdk.main.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Activity a;
    private Context b;
    private Intent c;
    private Handler d;
    private String e;

    protected void a(Activity activity) {
        this.a = activity;
    }

    protected void a(BroadcastReceiver broadcastReceiver) {
        s.a(f(), broadcastReceiver);
    }

    protected void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        s.a(f(), broadcastReceiver, intentFilter);
    }

    protected void a(Context context) {
        this.b = context;
    }

    protected void a(Intent intent) {
        this.c = intent;
    }

    protected void a(Intent intent, String str) {
        s.a(f(), intent, str);
    }

    protected void a(Handler handler) {
        this.d = handler;
    }

    protected boolean a() {
        return this.a != null;
    }

    protected Context b() {
        return f().getApplicationContext();
    }

    protected void b(Intent intent) {
        GameUtil.startService(intent, f());
    }

    protected Context c() {
        return g().getBaseContext();
    }

    protected void c(Intent intent) {
        g().startActivity(intent);
    }

    protected boolean canRequestPackageInstalls() {
        if (Build.VERSION.SDK_INT >= 26) {
            return f().getPackageManager().canRequestPackageInstalls();
        }
        return false;
    }

    protected String d() {
        if (this.e == null) {
            this.e = f().getPackageName();
        }
        return this.e;
    }

    protected Intent e() {
        return this.c;
    }

    protected Context f() {
        return this.b;
    }

    protected Activity g() {
        return this.a;
    }

    protected Handler h() {
        return this.d;
    }

    protected void i() {
        if (a()) {
            g().finish();
        }
    }

    protected boolean j() {
        if (a()) {
            return g().isFinishing();
        }
        return false;
    }
}
