package com.excelliance.lbsdk.main.l;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.p.h;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.excelliance.lbsdk.main.b.f {
    private com.excelliance.lbsdk.main.p.c a;
    private h b;
    private volatile boolean c;

    private void a(Application application) {
        if (Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new com.excelliance.lbsdk.main.b.a() { // from class: com.excelliance.lbsdk.main.l.b.1
                @Override // com.excelliance.lbsdk.main.b.a, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    super.onActivityPaused(activity);
                    b.this.c = false;
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPrePaused(Activity activity) {
                    b.this.d();
                }

                @Override // com.excelliance.lbsdk.main.b.a, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    super.onActivityResumed(activity);
                    b.this.c = true;
                }
            });
        }
    }

    public static boolean c() {
        return ((b) LBSdkHelper.application().a("Download")).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (2 == BwbxUtil.getEngine(getContext())) {
            BwbxUtil.closeRetryDialogStopDownload();
        }
    }

    private boolean e() {
        if (2 != BwbxUtil.getEngine(getContext())) {
            return true;
        }
        return this.c;
    }

    public com.excelliance.lbsdk.main.p.c a() {
        com.excelliance.lbsdk.main.p.c cVar;
        com.excelliance.lbsdk.main.p.c cVar2 = this.a;
        if (cVar2 != null) {
            return cVar2;
        }
        synchronized (this) {
            cVar = this.a;
            if (cVar == null) {
                cVar = new com.excelliance.lbsdk.main.p.c(getApplicationModule());
                this.a = cVar;
            }
        }
        return cVar;
    }

    public h b() {
        h hVar;
        h hVar2 = this.b;
        if (hVar2 != null) {
            return hVar2;
        }
        synchronized (this) {
            hVar = this.b;
            if (hVar == null) {
                hVar = new h(getApplicationModule());
                this.b = hVar;
            }
        }
        return hVar;
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void initialize() {
        super.initialize();
        a(getAndroidApplication());
    }
}
