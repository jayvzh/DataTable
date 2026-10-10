package com.excelliance.lbsdk.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class AssistActivityParcel implements Parcelable {
    public static final Parcelable.Creator<AssistActivityParcel> CREATOR = new Parcelable.Creator<AssistActivityParcel>() { // from class: com.excelliance.lbsdk.main.AssistActivityParcel.2
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AssistActivityParcel createFromParcel(Parcel parcel) {
            return new AssistActivityParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AssistActivityParcel[] newArray(int i) {
            return new AssistActivityParcel[i];
        }
    };
    private boolean c;
    private String d;
    private boolean e;
    private Handler f;
    private long g;
    private Activity a = null;
    private final String b = "AssistActivityParcel";
    private Runnable h = new Runnable() { // from class: com.excelliance.lbsdk.main.AssistActivityParcel.1
        @Override // java.lang.Runnable
        public void run() {
            AssistActivityParcel.this.a();
        }
    };

    protected AssistActivityParcel(Parcel parcel) {
        if (parcel != null) {
            this.d = parcel.readString();
            this.c = parcel.readInt() == 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Intent intentD = d();
        boolean booleanExtra = intentD.getBooleanExtra("install", false);
        this.d = intentD.getStringExtra("apkPath");
        this.c = intentD.getBooleanExtra("forceUpdate", false);
        com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "onResume call back apk=" + this.d, new Object[0]);
        if (booleanExtra) {
            String str = this.d;
            if (str == null || !new File(str).exists()) {
                b();
            } else if (!this.e) {
                overInsPackage(this.c, this.d);
            } else {
                com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "back from installer", new Object[0]);
                b();
            }
        }
    }

    private void b() {
        this.a.finish();
    }

    private boolean c() {
        return this.a.isFinishing();
    }

    private Intent d() {
        return this.a.getIntent();
    }

    private String e() {
        return this.a.getPackageName();
    }

    public boolean canRequestPackageInstalls() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.a.getPackageManager().canRequestPackageInstalls();
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected Handler getHandler() {
        if (this.f == null) {
            this.f = new Handler(Looper.getMainLooper());
        }
        return this.f;
    }

    public Resources getResources() {
        return PlatformResources.getApkResources(this.a);
    }

    public void init(Activity activity, Bundle bundle) {
        setActivity(activity);
        onCreate(bundle);
    }

    public void installPackage(boolean z, String str) {
        this.e = true;
        this.a.startActivity(u.a(this.a, str));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "onActivityResult resultCode=" + i2 + ", requestCode=" + i + ", canRequest =" + canRequestPackageInstalls(), new Object[0]);
        if (i == 101) {
            if (i2 != -1 && !canRequestPackageInstalls()) {
                if (this.c) {
                    GameUtil.getIntance().killAllExceptSelf();
                }
                b();
                return;
            } else if (this.a.getApplicationInfo().targetSdkVersion < 26 || canRequestPackageInstalls()) {
                installPackage(this.c, this.d);
                return;
            } else if (this.c) {
                GameUtil.getIntance().killAllExceptSelf();
            }
        } else if (i != 102) {
            return;
        }
        b();
    }

    protected void onCreate(Bundle bundle) {
        GameUtil.getIntance().setContext(this.a.getApplicationContext());
    }

    protected void onDestroy() {
        com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "onDestroy: force = " + this.c, new Object[0]);
        if (this.c) {
            GameUtil.getIntance().killThirdProcesses();
        }
        Process.killProcess(Process.myPid());
    }

    protected void onPause() {
        com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "onPause isFinishing:" + c() + ", installing=" + this.e, new Object[0]);
        if (TextUtils.equals(Build.BRAND, "blackshark")) {
            getHandler().removeCallbacks(this.h);
        }
    }

    protected void onResume() {
        com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "onResume " + this.d, new Object[0]);
        if (TextUtils.equals(Build.BRAND, "blackshark")) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.g <= 500) {
                getHandler().removeCallbacks(this.h);
                getHandler().postDelayed(this.h, 1000L);
                return;
            }
            this.g = jCurrentTimeMillis;
        }
        a();
    }

    public void overInsPackage(boolean z, String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean zCanRequestPackageInstalls = canRequestPackageInstalls();
            com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "haveInstallPermission: =" + zCanRequestPackageInstalls, new Object[0]);
            if (!zCanRequestPackageInstalls && this.a.getApplicationInfo().targetSdkVersion >= 26) {
                try {
                    this.e = true;
                    this.a.startActivityForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + e())), 101);
                    return;
                } catch (Exception e) {
                    com.excelliance.lbsdk.main.g.b.e("AssistActivityParcel", "requestPermissions: e=", e, new Object[0]);
                    return;
                }
            }
        }
        installPackage(z, str);
    }

    public void setActivity(Activity activity) {
        com.excelliance.lbsdk.main.g.b.c("AssistActivityParcel", "setActivity: assistActivity=" + activity, new Object[0]);
        this.a = activity;
    }

    public void setHandler(Handler handler) {
        GameUtil.getIntance().setHandler(handler);
    }

    public void startActivity(Intent intent) {
        this.a.startActivity(intent);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.d);
        parcel.writeInt(this.c ? 1 : 0);
    }
}
