package com.excelliance.lbsdk.main.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import com.excelliance.lbsdk.library.BwbxUIInterface;
import com.excelliance.lbsdk.library.BwbxUIListener;
import com.excelliance.lbsdk.library.DialogClickCallback;
import com.excelliance.lbsdk.main.BGService;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.aa;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.al;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.g.b;
import com.excelliance.lbsdk.main.m;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class BwbxUIImpl extends a implements BwbxUIInterface {
    public static String ACTION_DOWNLOAD_ALL = null;
    public static String ACTION_DOWNLOAD_ALL_LATER = null;
    public static String ACTION_DOWNLOAD_MISSING_PROGRESS = null;
    public static String ACTION_DOWNLOAD_PROPORTION = null;
    public static String ACTION_FINISHED = null;
    public static String ACTION_QUERY_RETRY = null;
    public static String ACTION_QUERY_USER = null;
    public static String ACTION_RECORD_SIZE = null;
    public static final String TAG = "BwbxUIImpl";
    private BwbxUIListener a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private AtomicBoolean g;
    private long h;

    @SuppressLint({"MissingPermission"})
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            b.c(BwbxUIImpl.TAG, "action=" + action, new Object[0]);
            if (action.equals(BwbxUIImpl.ACTION_DOWNLOAD_PROPORTION)) {
                int intExtra = intent.getIntExtra("proportion", 0);
                long longExtra = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                BwbxUIImpl.this.h = longExtra;
                BwbxUIImpl.this.a.onProgress(intExtra, longExtra);
                BwbxUIImpl.this.a.onFloatProgressChange(intExtra, longExtra);
                if (intExtra == 100) {
                    BwbxUIImpl.this.a.onFloatProgressChange(-1, 0L);
                    BwbxUIImpl.this.a.onProgressWindowDismiss(BwbxUIImpl.this.g());
                    BwbxUIImpl.this.c = true;
                    return;
                }
                return;
            }
            if (action.equals(BwbxUIImpl.ACTION_RECORD_SIZE)) {
                long longExtra2 = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                int intExtra2 = intent.getIntExtra("count", 0);
                b.c(BwbxUIImpl.TAG, "size = " + longExtra2 + " count:" + intExtra2, new Object[0]);
                BwbxUIImpl.this.a.onRecord(intExtra2, longExtra2);
                return;
            }
            if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (BwbxUIImpl.this.b) {
                    BwbxUIImpl.this.b = false;
                    return;
                }
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo == null || networkInfo2 == null || networkInfo.isConnected() || !networkInfo2.isConnected()) {
                    return;
                }
                b.c(BwbxUIImpl.TAG, "wifiState  =WIFI已断开,移动数据已连接", new Object[0]);
                BwbxUIImpl.this.f = false;
                BwbxUIImpl.this.a.onNetworkChange(false);
                if (BwbxUIImpl.this.k() && BwbxUIImpl.this.l()) {
                    BwbxUIImpl.this.a.onProgressWindowShow(BwbxUIImpl.this.g());
                    BwbxUIImpl.this.a.onNetworkChange(false);
                    BwbxUIImpl.this.f = false;
                    return;
                }
                return;
            }
            if (action.equals(BwbxUIImpl.ACTION_QUERY_RETRY)) {
                BwbxUIImpl.this.a(intent.getLongExtra("errcode", -1L), intent.getStringExtra("exceptionInfo"), true);
                return;
            }
            if (!action.equals(BwbxUIImpl.ACTION_QUERY_USER)) {
                action.equals(BwbxUIImpl.ACTION_DOWNLOAD_MISSING_PROGRESS);
                return;
            }
            int intExtra3 = intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0);
            b.c(BwbxUIImpl.TAG, "acti=" + action + ", type=" + intExtra3 + "showing= " + BwbxUIImpl.this.g.get() + " is fore=" + al.b(BwbxUIImpl.this.f()) + " isLBMainOnTop=" + al.d(context), new Object[0]);
            boolean z = intExtra3 == BwbxUtil.ACTION_TYPE_PROGRESS && intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, -1) == -1;
            if ((!al.b(BwbxUIImpl.this.f()) || BwbxUIImpl.this.g.get() || al.d(context)) && !z) {
                b.c(BwbxUIImpl.TAG, "has dialog", new Object[0]);
                BwbxUtil.dialogRejected(intExtra3);
                return;
            }
            if (intExtra3 == BwbxUtil.ACTION_TYPE_PROGRESS) {
                if (((com.excelliance.lbsdk.main.ab.a) LBSdkHelper.application().a("GlobalSettings")).g()) {
                    BwbxUIImpl.this.a(intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, -1), true);
                    return;
                }
                return;
            }
            if (intExtra3 == BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI) {
                long longExtra3 = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                b.c(BwbxUIImpl.TAG, "size:" + longExtra3, new Object[0]);
                BwbxUIImpl.this.a(longExtra3, true);
                return;
            }
            if (intExtra3 == BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER) {
                long longExtra4 = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                boolean booleanExtra = intent.getBooleanExtra("zipExists", false);
                boolean booleanExtra2 = intent.getBooleanExtra("dataConnection", false);
                boolean booleanExtra3 = intent.getBooleanExtra("downloadZip", false);
                b.c(BwbxUIImpl.TAG, "size:" + longExtra4 + " zipExists:" + booleanExtra + " downloadZip:" + booleanExtra3 + " dataConnection:" + booleanExtra2, new Object[0]);
                BwbxUIImpl.this.a(longExtra4, booleanExtra, booleanExtra3, booleanExtra2, true);
                return;
            }
            if (intExtra3 == BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER_INTERFACE) {
                long longExtra5 = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                boolean booleanExtra4 = intent.getBooleanExtra("zipExists", false);
                boolean booleanExtra5 = intent.getBooleanExtra("dataConnection", false);
                boolean booleanExtra6 = intent.getBooleanExtra("downloadZip", false);
                b.c(BwbxUIImpl.TAG, "size:" + longExtra5 + " zipExists:" + booleanExtra4 + " downloadZip:" + booleanExtra6 + " dataConnection:" + booleanExtra5, new Object[0]);
                BwbxUIImpl.this.a(longExtra5, booleanExtra4, booleanExtra6, true);
                return;
            }
            if (intExtra3 == BwbxUtil.ACTION_TYPE_REMAINGING_RES_DOWNLOADING) {
                long longExtra6 = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                b.c(BwbxUIImpl.TAG, "size:" + longExtra6, new Object[0]);
                BwbxUIImpl.this.b(longExtra6, true);
                return;
            }
            if (intExtra3 != BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_RES) {
                int i = BwbxUtil.ACTION_TYPE_PRELOAD_DIALOG;
                return;
            }
            long longExtra7 = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
            int intExtra4 = intent.getIntExtra("triggerReason", 0);
            int intExtra5 = intent.getIntExtra("zipType", 1);
            Boolean boolValueOf = Boolean.valueOf(intent.getBooleanExtra("needOneButton", false));
            b.c(BwbxUIImpl.TAG, "size:" + longExtra7 + " triggerReason:" + intExtra4 + " zipType:" + intExtra5 + " needOneButton:" + boolValueOf + " extra:" + intent.getExtras(), new Object[0]);
            BwbxUIImpl.this.a(longExtra7, intExtra5, true, intExtra4, boolValueOf.booleanValue(), intent.getExtras());
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public int a(long j) {
        int i = BwbxUtil.DIALOG_TYPE_DOWNLOAD_FAILED_UNKNOWN;
        if (j == BwbxUtil.ERR_NETWORK) {
            return BwbxUtil.DIALOG_TYPE_DOWNLOAD_FAILED_ERR_NETWORK;
        }
        if (j == BwbxUtil.ERR_TIMEOUT) {
            return BwbxUtil.DIALOG_TYPE_DOWNLOAD_FAILED_TIMOUT;
        }
        if (j == BwbxUtil.ERR_SPACE_NOT_ENOUGH) {
            return BwbxUtil.DIALOG_TYPE_DOWNLOAD_FAILED_SPACE_NOT_ENOUGH;
        }
        if (j == BwbxUtil.ERR_MD5_MIS) {
            return BwbxUtil.DIALOG_TYPE_DOWNLOAD_FAILED_MD5_MIS;
        }
        return j == ((long) BwbxUtil.ERR_RENAME_FAIL) ? BwbxUtil.DIALOG_TYPE_DOWNLOAD_FAILED_RENAME_FAIL : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        b.c(TAG, "platDo type=" + i, new Object[0]);
        Intent intent = new Intent(BwbxUtil.ACTION_PLAT_DO);
        intent.setPackage(d());
        intent.setComponent(new ComponentName(f(), BGService.class.getName()));
        intent.putExtra(BwbxUtil.ACTION_TYPE, i);
        b(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        b.c(TAG, "showProgressDialog ENTER, progress=" + i, new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putInt(BwbxUtil.ACTION_PROGRESS, i);
        this.a.onShowDialog(302, bundle, GameUtil.getTopActivity(), z, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, final int i, boolean z, int i2, final boolean z2, final Bundle bundle) {
        b.c(TAG, "showDownloadFullRes ENTER", new Object[0]);
        Bundle bundle2 = new Bundle();
        bundle2.putLong(BwbxUtil.ACTION_SIZE, j);
        bundle2.putInt("triggerReason", i2);
        bundle2.putBoolean("oneBtn", z2);
        final int i3 = i2 == 1 ? BwbxUtil.DIALOG_TYPE_NET_POOR : BwbxUtil.DIALOG_TYPE_OLD_USER;
        this.a.onShowDialog(307, bundle2, GameUtil.getTopActivity(), z, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.8
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
                BwbxUIImpl.this.g.set(false);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i4, Bundle bundle3, boolean z3) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i4, Bundle bundle3) throws Throwable {
                if (!z2 && !m.a(BwbxUIImpl.this.f(), "SHOW_DIALOG_BUTTON_BY_OLD_USER", false)) {
                    BwbxUtil.userClick(i3, 2);
                } else if (m.a(BwbxUIImpl.this.f(), "ENABLE_EXIT_BUTTON", false)) {
                    BwbxUIImpl.this.a(BwbxUtil.ACTION_TYPE_CANCEL_CHECK_FOREGROUND);
                    BwbxUIImpl.this.a(BwbxUtil.ACTION_TYPE_CLEAN_EXIT);
                    BwbxUtil.userClick(i3, 2);
                    GameUtil.getIntance().killGameProcesses(BwbxUIImpl.this.f());
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i4, Bundle bundle3) throws Throwable {
                BwbxUtil.userClick(i3, 1);
                GameUtil.switchToDownloadFullRes(BwbxUIImpl.this.f(), i, false, bundle);
            }
        });
        this.g.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, String str, boolean z) {
        b.c(TAG, "showGameDialog ENTER", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putLong("errcode", j);
        bundle.putString("exception", str);
        this.a.onShowDialog(301, bundle, GameUtil.getTopActivity(), z, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.3
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
                BwbxUIImpl.this.g.set(false);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) throws Throwable {
                GameUtil.finishActivitys();
                BwbxUIImpl.this.a(BwbxUtil.ACTION_TYPE_CANCEL_CHECK_FOREGROUND);
                BwbxUIImpl.this.a(BwbxUtil.ACTION_TYPE_CLEAN_EXIT);
                BwbxUtil.userClick(BwbxUIImpl.this.a(bundle2.getLong("errcode")), 2);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) throws Throwable {
                BwbxUtil.userRetry();
                BwbxUtil.userClick(BwbxUIImpl.this.a(bundle2.getLong("errcode")), 1);
            }
        });
        this.g.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, boolean z) {
        b.c(TAG, "wifi to mobile", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(303, bundle, GameUtil.getTopActivity(), z, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.4
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
                BwbxUIImpl.this.g.set(false);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) throws Throwable {
                BwbxUIImpl.this.a(new Intent(BwbxUIImpl.ACTION_DOWNLOAD_ALL_LATER), (String) null);
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_SWITCH_MOBILE, 2);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) throws Throwable {
                BwbxUIImpl.this.a(new Intent(BwbxUIImpl.ACTION_DOWNLOAD_ALL), (String) null);
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_SWITCH_MOBILE, 1);
            }
        });
        this.g.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, final boolean z, final boolean z2, boolean z3) {
        b.c(TAG, "showDownloadAllLaterInterface ENTER", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(305, bundle, GameUtil.getTopActivity(), z3, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.6
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
                BwbxUIImpl.this.g.set(false);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z4) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) throws Throwable {
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_SECOND_INTERFACE, 2);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) throws Throwable {
                if (z || z2) {
                    GameUtil.switchToDownloadFullRes(BwbxUIImpl.this.f(), 1, false);
                } else {
                    BwbxUtil.userDownload();
                }
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_SECOND_INTERFACE, 1);
            }
        });
        this.g.set(z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, final boolean z, final boolean z2, boolean z3, boolean z4) {
        b.c(TAG, "show bwbx dialog second", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        bundle.putBoolean("dataConnection", z3);
        this.a.onShowDialog(304, bundle, GameUtil.getTopActivity(), z4, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.5
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
                BwbxUIImpl.this.g.set(false);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z5) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) throws Throwable {
                GameUtil.finishActivitys();
                BwbxUIImpl.this.a(BwbxUtil.ACTION_TYPE_CANCEL_CHECK_FOREGROUND);
                BwbxUIImpl.this.a(BwbxUtil.ACTION_TYPE_CLEAN_EXIT);
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_SECOND, 2);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) throws Throwable {
                if (z || z2) {
                    GameUtil.switchToDownloadFullRes(BwbxUIImpl.this.f(), 1, false);
                } else {
                    BwbxUtil.userDownload();
                }
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_SECOND, 1);
            }
        });
        this.g.set(z4);
    }

    private void a(Application application) {
        if (Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.2
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    if (BwbxUIImpl.this.k()) {
                        BwbxUIImpl.this.a.onFloatWindowCreate(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    if (BwbxUIImpl.this.k()) {
                        BwbxUIImpl.this.a.onFloatWindowDestroy(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    if (BwbxUIImpl.this.k()) {
                        BwbxUIImpl.this.a.onFloatWindowDismiss(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    BwbxUIImpl.this.a(activity);
                    if (BwbxUIImpl.this.k() && BwbxUIImpl.this.l() && BwbxUIImpl.this.h == 0) {
                        BwbxUIImpl bwbxUIImpl = BwbxUIImpl.this;
                        bwbxUIImpl.h = ae.b(bwbxUIImpl.f(), "lebian_module_dloadinfo", "downloaded_size_float_win", 0L);
                        BwbxUIImpl.this.a.onFloatProgressChange(-1, BwbxUIImpl.this.h);
                    }
                    if (BwbxUIImpl.this.k()) {
                        BwbxUIImpl.this.a.onFloatWindowShow(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, boolean z) {
        b.c(TAG, "showRemainingResDownloading ENTER", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(306, bundle, GameUtil.getTopActivity(), z, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.BwbxUIImpl.7
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
                BwbxUIImpl.this.g.set(false);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) throws Throwable {
                BwbxUtil.userClick(BwbxUtil.DIALOG_TYPE_REMAINING_RES_DOWNLOADING, 1);
            }
        });
        this.g.set(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return (this.d && !this.c) || this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        return !this.f || this.e;
    }

    @Override // com.excelliance.lbsdk.library.BwbxUIInterface
    public void create(Context context) {
        a(context);
        ACTION_QUERY_USER = d() + BwbxUtil.ACTION_QUERY_USER;
        ACTION_QUERY_RETRY = d() + BwbxUtil.ACTION_QUERY_RETRY;
        ACTION_FINISHED = d() + BwbxUtil.ACTION_FINISHED;
        ACTION_DOWNLOAD_MISSING_PROGRESS = d() + BwbxUtil.ACTION_DOWNLOAD_MISSING_PROGRESS;
        ACTION_DOWNLOAD_PROPORTION = d() + BwbxUtil.ACTION_DOWNLOAD_PROPORTION;
        ACTION_RECORD_SIZE = d() + BwbxUtil.ACTION_RECORD_SIZE;
        ACTION_DOWNLOAD_ALL = d() + BwbxUtil.ACTION_DOWNLOAD_ALL;
        ACTION_DOWNLOAD_ALL_LATER = d() + BwbxUtil.ACTION_DOWNLOAD_ALL_LATER;
        this.g = new AtomicBoolean(false);
    }

    @Override // com.excelliance.lbsdk.library.BwbxUIInterface
    public void init(Application application, boolean z, BwbxUIListener bwbxUIListener) {
        b.c(TAG, "init is record:" + z, new Object[0]);
        this.a = bwbxUIListener;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_QUERY_USER);
        intentFilter.addAction(ACTION_QUERY_RETRY);
        intentFilter.addAction(ACTION_FINISHED);
        intentFilter.addAction(ACTION_DOWNLOAD_MISSING_PROGRESS);
        intentFilter.addAction(ACTION_DOWNLOAD_PROPORTION);
        intentFilter.addAction(ACTION_RECORD_SIZE);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        a(this.i, intentFilter);
        this.c = ae.b(f(), "lebian_module_dloadinfo", "success", false);
        this.d = m.a(f(), "SHOW_BWBX_FLOAT_WINDOW", false);
        this.e = z;
        this.f = aa.a(f());
        if (k()) {
            a(application);
        }
    }
}
