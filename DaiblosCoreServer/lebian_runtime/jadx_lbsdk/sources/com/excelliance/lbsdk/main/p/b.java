package com.excelliance.lbsdk.main.p;

import android.content.Context;
import android.content.Intent;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.q;
import com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private static volatile b a;
    private Context b;
    private int c;

    private b(Context context) {
        if (context != null) {
            this.b = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        }
    }

    public static b a(Context context) {
        synchronized (b.class) {
            if (a == null) {
                synchronized (b.class) {
                    if (a == null) {
                        a = new b(context);
                    }
                }
            }
        }
        return a;
    }

    public void a() {
        this.c = 0;
    }

    public void a(int i) {
        com.excelliance.lbsdk.main.g.b.c("BwbxDownloadHelper", "publishProgress progress:" + i, new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.downloadcomponent.progress");
        intent.setPackage(this.b.getPackageName());
        intent.putExtra(BwbxUtil.ACTION_PROGRESS, i);
        s.a(this.b, intent);
    }

    public void a(long j, long j2) {
        int i = (int) ((j / j2) * 100.0f * 0.9f);
        int i2 = this.c;
        if (i != i2) {
            int i3 = 0;
            if (i2 < 80 && i >= 80) {
                i3 = 80;
            } else if (this.c < 60 && i >= 60) {
                i3 = 60;
            } else if (this.c < 40 && i >= 40) {
                i3 = 40;
            } else if (this.c < 20 && i >= 20) {
                i3 = 20;
            }
            if (i3 > 0) {
                Intent intent = new Intent();
                intent.putExtra("action", 12);
                intent.putExtra("vercode", String.valueOf(q.a));
                intent.putExtra(BwbxUtil.ACTION_PROGRESS, i3);
                GameUtil.getIntance().notifyAction(intent);
            }
            this.c = i;
            a(i);
        }
        b(j, j2);
    }

    public void b(long j, long j2) {
        Intent intent = new Intent(this.b.getPackageName() + ".action.downloadcomponent.downloaded");
        intent.putExtra("downloaded", j);
        intent.putExtra(BwbxUtil.ACTION_SIZE, j2);
        s.a(this.b, intent);
    }
}
