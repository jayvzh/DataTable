package com.excelliance.lbsdk.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(14)
public class w {
    private static volatile w a;
    private Context b;
    private Handler c = new Handler(Looper.getMainLooper());
    private boolean d;

    public w(Context context) {
        this.b = context;
        this.d = "HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) && !n.b();
        com.excelliance.lbsdk.main.g.b.c("launcher", "Launcher: " + Build.MANUFACTURER, new Object[0]);
    }

    public static w a(Context context) {
        if (a == null) {
            synchronized (w.class) {
                if (a == null) {
                    a = new w(context);
                }
            }
        }
        return a;
    }

    public void a(final Intent intent) {
        intent.putExtra("extra.lbsdk.hostintent", true);
        if (this.d) {
            this.c.post(new Runnable() { // from class: com.excelliance.lbsdk.main.w.1
                @Override // java.lang.Runnable
                public void run() {
                    GameUtil.startService(intent, w.this.b);
                }
            });
        } else {
            GameUtil.startService(intent, this.b);
        }
    }

    public void b(Intent intent) {
        if ("com.excelliance.open.action.startNextChapter".equals(intent.getAction())) {
            s.a(this.b, new Intent(this.b.getPackageName() + BwbxUtil.ACTION_CANCEL_DOWNLOAD_ALL));
        }
        this.b.startActivity(intent);
    }
}
