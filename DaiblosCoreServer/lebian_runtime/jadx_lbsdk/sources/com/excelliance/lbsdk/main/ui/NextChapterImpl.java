package com.excelliance.lbsdk.main.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.window.OnBackInvokedCallback;
import com.excelliance.lbsdk.library.DialogClickCallback;
import com.excelliance.lbsdk.library.ErrorCode;
import com.excelliance.lbsdk.library.LBDnsListener;
import com.excelliance.lbsdk.library.NextChapterInterface;
import com.excelliance.lbsdk.main.BGService;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.NextChapterParcel;
import com.excelliance.lbsdk.main.aa;
import com.excelliance.lbsdk.main.ad;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.ae.c;
import com.excelliance.lbsdk.main.al;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.g.b;
import com.excelliance.lbsdk.main.h;
import com.excelliance.lbsdk.main.l;
import com.excelliance.lbsdk.main.m;
import com.excelliance.lbsdk.main.n;
import java.io.File;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class NextChapterImpl extends com.excelliance.lbsdk.main.ui.a implements NextChapterInterface {
    public static final String GAME_RUNNING = "gameRunning";
    public static final String TAG = "NextChapterImpl";
    private static boolean h = true;
    private static boolean i = false;
    private boolean A;
    private long B;
    private boolean C;
    private String D;
    private String E;
    private int F;
    private long G;
    private Bundle I;
    private LBDnsListener a;
    private a b;
    private boolean c;
    private l d;
    private l e;
    private String f;
    private boolean o;
    private boolean p;
    private long q;
    private boolean r;
    private boolean s;
    private long t;
    private int u;
    private boolean v;
    private boolean w;
    private boolean x;
    private String y;
    private String z;
    private int g = 0;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private int H = 0;
    private boolean J = false;
    private final Runnable K = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.10
        @Override // java.lang.Runnable
        public void run() {
            b.c(NextChapterImpl.TAG, "downloadCallBack enter", new Object[0]);
            if (NextChapterImpl.this.getPopUnfinishedDialog()) {
                NextChapterImpl.this.J = false;
                NextChapterImpl.this.a.onError(ErrorCode.NET_ERROR.getCode(), null);
                NextChapterImpl nextChapterImpl = NextChapterImpl.this;
                nextChapterImpl.a(nextChapterImpl.d, NextChapterImpl.this.H, NextChapterImpl.this.I);
            }
            NextChapterImpl.this.h().postDelayed(NextChapterImpl.this.K, 10000L);
        }
    };
    private final Runnable L = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.11
        @Override // java.lang.Runnable
        public void run() {
            b.c(NextChapterImpl.TAG, "Mumu BackGround Callback", new Object[0]);
            if (al.b(NextChapterImpl.this.f())) {
                NextChapterImpl.this.onResume();
            } else {
                NextChapterImpl.this.h().removeCallbacks(NextChapterImpl.this.L);
                NextChapterImpl.this.h().postDelayed(NextChapterImpl.this.L, 1000L);
            }
        }
    };
    private final Runnable M = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.12
        @Override // java.lang.Runnable
        public void run() {
            b.c(NextChapterImpl.TAG, "FinishCallBack: finish activity", new Object[0]);
            NextChapterImpl.this.i();
        }
    };
    private final Runnable N = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.13
        @Override // java.lang.Runnable
        public void run() {
            b.c(NextChapterImpl.TAG, "NextChapter mOnPauseCallBack enter", new Object[0]);
            if (al.c(NextChapterImpl.this.f())) {
                return;
            }
            if (al.b(NextChapterImpl.this.f())) {
                String topActivity = GameUtil.getTopActivity(NextChapterImpl.this.f());
                if (TextUtils.isEmpty(topActivity)) {
                    return;
                }
                if (topActivity != null && topActivity.contains("GrantPermissionsActivity")) {
                    return;
                }
                Bundle extras = NextChapterImpl.this.e().getExtras();
                Intent intent = new Intent("com.excelliance.open.action.startNextChapter");
                intent.setPackage(NextChapterImpl.this.d());
                intent.replaceExtras(extras);
                intent.addFlags(536870912);
                NextChapterImpl.this.c(intent);
            } else {
                ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                int i2 = entryActivityInfo == null ? -1 : entryActivityInfo.launchMode;
                if (i2 != 2 && i2 != 3) {
                    return;
                }
                b.c(NextChapterImpl.TAG, "launchMode=" + i2, new Object[0]);
                GameUtil.getIntance().killGameProcesses(NextChapterImpl.this.f());
            }
            NextChapterImpl.this.i();
        }
    };
    private final Runnable O = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.14
        @Override // java.lang.Runnable
        public void run() {
            boolean zA = aa.a(NextChapterImpl.this.f());
            if (NextChapterImpl.this.p || NextChapterImpl.this.u >= 99) {
                if (NextChapterImpl.this.c && !zA) {
                    NextChapterImpl.this.quitDownloadBg();
                    NextChapterImpl.this.p = false;
                    NextChapterImpl.this.o = true;
                }
            } else if (zA) {
                NextChapterImpl.this.o();
                NextChapterImpl.this.o = false;
            } else {
                NextChapterImpl.this.o = true;
            }
            NextChapterImpl.this.c = zA;
        }
    };
    private final BroadcastReceiver P = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.15
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            b.c(NextChapterImpl.TAG, "onReceive action = " + action, new Object[0]);
            if (!"com.excelliance.open.action.appstate".equals(action)) {
                if (NextChapterParcel.ACTION_NCGENERIC.equals(action)) {
                    if (intent.getIntExtra("method", 0) == 1) {
                        NextChapterImpl.this.c((Intent) intent.getParcelableExtra("param"));
                        return;
                    }
                    return;
                }
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    if (NextChapterImpl.this.w) {
                        NextChapterImpl.this.w = false;
                        return;
                    }
                    if (aa.b(NextChapterImpl.this.f()) && NextChapterImpl.this.u < 99) {
                        b.c(NextChapterImpl.TAG, "net change", new Object[0]);
                        int i2 = NextChapterImpl.this.p ? 1000 : 0;
                        NextChapterImpl.this.h().removeCallbacks(NextChapterImpl.this.O);
                        NextChapterImpl.this.h().postDelayed(NextChapterImpl.this.O, i2);
                        return;
                    }
                    if (aa.b(NextChapterImpl.this.f())) {
                        return;
                    }
                    b.c(NextChapterImpl.TAG, "net unconnected", new Object[0]);
                    NextChapterImpl.this.h().removeCallbacks(NextChapterImpl.this.O);
                    NextChapterImpl.this.o = false;
                    return;
                }
                return;
            }
            String stringExtra = intent.getStringExtra("apk");
            int intExtra = intent.getIntExtra("state", -1);
            int intExtra2 = intent.getIntExtra("error", 0);
            b.c(NextChapterImpl.TAG, "onReceive apkPath = " + stringExtra + ", state = " + intExtra + ", error=" + intExtra2, new Object[0]);
            if (intExtra >= 0) {
                if (intExtra == 1) {
                    if (intExtra2 == 0 || intExtra2 != 1) {
                        return;
                    }
                } else if ((intExtra != 2 || stringExtra == null || stringExtra.length() <= 0) && intExtra != 3) {
                    return;
                }
                NextChapterImpl.this.i();
            }
        }
    };
    private final BroadcastReceiver Q = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.16
        /* JADX WARN: Code duplicated, block: B:103:0x0335  */
        /* JADX WARN: Code duplicated, block: B:105:0x0346  */
        /* JADX WARN: Code duplicated, block: B:107:0x036c  */
        /* JADX WARN: Code duplicated, block: B:109:0x037a  */
        /* JADX WARN: Code duplicated, block: B:110:0x0385  */
        /* JADX WARN: Code duplicated, block: B:111:0x03aa  */
        /* JADX WARN: Code duplicated, block: B:185:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Instruction removed from duplicated block: B:105:0x0346, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:110:0x0385, please report this as an issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Handler handlerH;
            Runnable runnable;
            long j;
            l lVar;
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("gameid");
            boolean booleanExtra = intent.getBooleanExtra("merge", false);
            if ((NextChapterImpl.this.m() && stringExtra == null) || (NextChapterImpl.this.n() && stringExtra != null)) {
                b.c(NextChapterImpl.TAG, "bc mismatch", new Object[0]);
                return;
            }
            if (action.equals(NextChapterImpl.this.d() + "com.excelliance.open.downloadcomponent.error")) {
                String stringExtra2 = intent.getStringExtra("error");
                int intExtra = intent.getIntExtra("errType", -1);
                b.c(NextChapterImpl.TAG, "gameId=" + stringExtra + " error=" + stringExtra2 + " detail=" + NextChapterImpl.this.d + " errType = " + intExtra, new Object[0]);
                NextChapterImpl.this.J = false;
                NextChapterImpl.this.a.onError(intExtra, stringExtra2);
                if (NextChapterImpl.this.m() && (NextChapterImpl.this.d == null || stringExtra == null || !stringExtra.equals(NextChapterImpl.this.d.gameId))) {
                    return;
                }
                NextChapterImpl.this.v = false;
                NextChapterImpl.this.j = true;
                NextChapterImpl.this.p = false;
                if (intExtra != -1) {
                    NextChapterImpl.this.errorCommit(String.valueOf(intExtra), intent.getStringExtra("dmd5"));
                    return;
                }
                return;
            }
            if (!action.equals("com.excelliance.open.action.downloadcomponent.progress")) {
                if (!action.equals(NextChapterImpl.this.d() + ".action.downloadcomponent.downloaded")) {
                    if (action.equals(NextChapterImpl.this.d() + NextChapterParcel.ACTION_NOTIFY)) {
                        NextChapterImpl.this.v = false;
                        NextChapterImpl.this.p = false;
                        b.c(NextChapterImpl.TAG, "network not available", new Object[0]);
                        NextChapterImpl.this.a.onError(ErrorCode.CONNECT_ERROR.getCode(), null);
                        return;
                    }
                    if (action.equals(NextChapterImpl.this.d() + ".action.downloadcomponent.extracted")) {
                        int intExtra2 = intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0);
                        if (intExtra2 > 100) {
                            b.c(NextChapterImpl.TAG, "progress is over " + intExtra2, new Object[0]);
                            NextChapterImpl.this.a.onExtractFinish(NextChapterImpl.this.g);
                            intExtra2 = 100;
                        }
                        NextChapterImpl.this.a.onExtractProgress(NextChapterImpl.this.g, intExtra2);
                        return;
                    }
                    if (action.equals(NextChapterImpl.this.d() + ".action.downloadcomponent.computeMD5")) {
                        NextChapterImpl.this.a.onDownloadProgress(NextChapterImpl.this.g, 99);
                        int intExtra3 = intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0);
                        if (intExtra3 <= 0) {
                            NextChapterImpl.this.a.onComputeMD5Start(NextChapterImpl.this.g);
                        } else if (intExtra3 > 100) {
                            b.c(NextChapterImpl.TAG, "progress is over " + intExtra3, new Object[0]);
                            NextChapterImpl.this.a.onComputeMD5Finish(NextChapterImpl.this.g);
                            intExtra3 = 100;
                        }
                        NextChapterImpl.this.a.onComputeMD5Progress(NextChapterImpl.this.g, intExtra3);
                        return;
                    }
                    return;
                }
                if (NextChapterImpl.this.m() && (NextChapterImpl.this.d == null || stringExtra == null || !stringExtra.equals(NextChapterImpl.this.d.gameId))) {
                    return;
                }
                NextChapterImpl.this.t = intent.getLongExtra("downloaded", 0L);
                if (NextChapterImpl.this.m() && NextChapterImpl.this.t > 0 && !NextChapterImpl.this.n) {
                    b.c(NextChapterImpl.TAG, "onReceive: downloadStart", new Object[0]);
                    NextChapterImpl.this.n = true;
                }
                long longExtra = intent.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                int intExtra4 = intent.getIntExtra("currResNum", 1);
                int intExtra5 = intent.getIntExtra("totalResNum", 1);
                if (NextChapterImpl.this.F != intExtra4) {
                    NextChapterImpl.this.J = false;
                    NextChapterImpl.this.F = intExtra4;
                    NextChapterImpl.this.b.a();
                }
                if (!NextChapterImpl.this.J) {
                    if (intExtra5 > 1) {
                        if (intExtra4 > intExtra5) {
                            intExtra4 = intExtra5;
                        }
                        NextChapterImpl.this.a.onDownloadStart(NextChapterImpl.this.g, intExtra4 + "/" + intExtra5);
                    } else {
                        NextChapterImpl.this.a.onDownloadStart(NextChapterImpl.this.g, "1/1");
                    }
                    NextChapterImpl.this.J = true;
                    b.c(NextChapterImpl.TAG, "PROGRESS_INTENT start downloadCallBack", new Object[0]);
                    NextChapterImpl.this.h().removeCallbacks(NextChapterImpl.this.K);
                    NextChapterImpl.this.h().postDelayed(NextChapterImpl.this.K, 10000L);
                }
                NextChapterImpl.this.a.onDownloadSize(NextChapterImpl.this.t, longExtra);
                Message messageObtain = Message.obtain();
                messageObtain.what = 131072;
                messageObtain.obj = Long.valueOf(NextChapterImpl.this.t);
                NextChapterImpl.this.b.sendMessage(messageObtain);
                if (longExtra <= 0 || NextChapterImpl.this.G == longExtra) {
                    return;
                }
                b.c(NextChapterImpl.TAG, "size change rSize:" + NextChapterImpl.this.G, new Object[0]);
                NextChapterImpl.this.G = longExtra;
                return;
            }
            NextChapterImpl.this.u = intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0);
            int intExtra6 = intent.getIntExtra("currResNum", 1);
            int intExtra7 = intent.getIntExtra("totalResNum", 1);
            if (NextChapterImpl.this.m() && (NextChapterImpl.this.d == null || stringExtra == null || !stringExtra.equals(NextChapterImpl.this.d.gameId))) {
                return;
            }
            if (NextChapterImpl.this.u == -2) {
                NextChapterImpl.this.j = true;
                return;
            }
            if (NextChapterImpl.this.u == -1) {
                NextChapterImpl.this.J = false;
                NextChapterImpl.this.a.onError(ErrorCode.NET_ERROR.getCode(), null);
                return;
            }
            NextChapterImpl.this.a.onAutoDismissDialog(105);
            NextChapterImpl.this.a.onAutoDismissDialog(103);
            NextChapterImpl.this.a.onAutoDismissDialog(102);
            b.c(NextChapterImpl.TAG, "gameId=" + stringExtra + " progress=" + NextChapterImpl.this.u + " detail=" + NextChapterImpl.this.d, new Object[0]);
            if (NextChapterImpl.this.u < 100) {
                NextChapterImpl.this.a.onDownloadProgress(NextChapterImpl.this.g, NextChapterImpl.this.u);
            }
            boolean booleanExtra2 = intent.getBooleanExtra("mergeApk", false);
            if ((NextChapterImpl.this.u == 99 && (NextChapterImpl.this.C || NextChapterImpl.this.n())) || booleanExtra2) {
                if (intExtra7 > 1) {
                    if (intExtra6 > intExtra7) {
                        intExtra6 = intExtra7;
                    }
                    NextChapterImpl.this.a.onExtractStart(NextChapterImpl.this.g, intExtra6 + "/" + intExtra7);
                } else {
                    NextChapterImpl.this.a.onExtractStart(NextChapterImpl.this.g, null);
                }
            }
            if (NextChapterImpl.this.u == 100 && (NextChapterImpl.this.n() || NextChapterImpl.this.d != null)) {
                NextChapterImpl.this.h().removeCallbacks(NextChapterImpl.this.K);
                NextChapterImpl.this.J = false;
                if (booleanExtra) {
                    NextChapterImpl.this.a.onExtractFinish(NextChapterImpl.this.g);
                } else {
                    NextChapterImpl.this.a.onDownloadFinish(NextChapterImpl.this.g);
                }
            }
            if (NextChapterImpl.this.m() && NextChapterImpl.this.d != null && NextChapterImpl.this.d.patch != -1 && !NextChapterImpl.this.x && NextChapterImpl.this.u > 0 && !intent.getBooleanExtra("ISINSTALL", false)) {
                NextChapterImpl.this.x = true;
                ad.a(context).a();
            }
            if (NextChapterImpl.this.u == 100) {
                if (NextChapterImpl.this.m()) {
                    if (NextChapterImpl.this.d.gameId.equals(stringExtra)) {
                        NextChapterImpl.this.j = true;
                    }
                    String stringExtra3 = intent.getStringExtra("savePath");
                    if (stringExtra3 != null) {
                        lVar = NextChapterImpl.this.d;
                    } else {
                        String strB = ae.b(NextChapterImpl.this.f(), "lebian_module_extractinfo", "next", (String) null);
                        if (strB == null) {
                            strB = ae.b(NextChapterImpl.this.f(), "lebian_module_extractinfo", "current", (String) null);
                        }
                        l lVarA = an.a().a(strB);
                        if (lVarA != null) {
                            lVar = NextChapterImpl.this.d;
                            stringExtra3 = lVarA.savePath;
                        }
                        boolean zA = m.a(context, "RESTART_NEW_RAW", false);
                        if (!NextChapterImpl.this.d.rpOnly && !zA) {
                            GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
                            NextChapterImpl.this.i();
                            return;
                        }
                        if (al.b(context)) {
                            b.c(NextChapterImpl.TAG, "isForwardground", new Object[0]);
                            if (!NextChapterImpl.this.d.isInstall) {
                                b.c(NextChapterImpl.TAG, "install savePath:" + NextChapterImpl.this.d.savePath, new Object[0]);
                                NextChapterImpl.this.overInsPackage();
                                return;
                            }
                            b.c(NextChapterImpl.TAG, "onReceive: patch:" + NextChapterImpl.this.d.patch, new Object[0]);
                            if (NextChapterImpl.this.l) {
                                NextChapterImpl nextChapterImpl = NextChapterImpl.this;
                                nextChapterImpl.startGame(nextChapterImpl.d);
                                return;
                            } else {
                                handlerH = NextChapterImpl.this.h();
                                runnable = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.16.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        NextChapterImpl.this.restartGame(NextChapterImpl.this.d.patch == -1);
                                    }
                                };
                                j = 500;
                            }
                        } else {
                            b.c(NextChapterImpl.TAG, "is not Forwardground", new Object[0]);
                            NextChapterImpl.this.m = true;
                            if (!"MUMUEmulator".equals(n.a())) {
                                return;
                            }
                            handlerH = NextChapterImpl.this.h();
                            runnable = NextChapterImpl.this.L;
                            j = 1000;
                        }
                    }
                    lVar.savePath = stringExtra3;
                    boolean zA2 = m.a(context, "RESTART_NEW_RAW", false);
                    if (!NextChapterImpl.this.d.rpOnly) {
                    }
                    if (al.b(context)) {
                        b.c(NextChapterImpl.TAG, "isForwardground", new Object[0]);
                        if (!NextChapterImpl.this.d.isInstall) {
                            b.c(NextChapterImpl.TAG, "install savePath:" + NextChapterImpl.this.d.savePath, new Object[0]);
                            NextChapterImpl.this.overInsPackage();
                            return;
                        }
                        b.c(NextChapterImpl.TAG, "onReceive: patch:" + NextChapterImpl.this.d.patch, new Object[0]);
                        if (NextChapterImpl.this.l) {
                            NextChapterImpl nextChapterImpl2 = NextChapterImpl.this;
                            nextChapterImpl2.startGame(nextChapterImpl2.d);
                            return;
                        } else {
                            handlerH = NextChapterImpl.this.h();
                            runnable = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.16.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    NextChapterImpl.this.restartGame(NextChapterImpl.this.d.patch == -1);
                                }
                            };
                            j = 500;
                        }
                    } else {
                        b.c(NextChapterImpl.TAG, "is not Forwardground", new Object[0]);
                        NextChapterImpl.this.m = true;
                        if (!"MUMUEmulator".equals(n.a())) {
                            return;
                        }
                        handlerH = NextChapterImpl.this.h();
                        runnable = NextChapterImpl.this.L;
                        j = 1000;
                    }
                } else {
                    if (!NextChapterImpl.this.n()) {
                        return;
                    }
                    if (al.b(context)) {
                        b.c(NextChapterImpl.TAG, "isForwardground", new Object[0]);
                        ae.a(NextChapterImpl.this.f(), "lebian_module_dloadinfo", "restartAfterDl", true);
                        handlerH = NextChapterImpl.this.h();
                        runnable = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.16.2
                            @Override // java.lang.Runnable
                            public void run() {
                                NextChapterImpl.this.restartGame();
                            }
                        };
                        j = 500;
                    } else {
                        b.c(NextChapterImpl.TAG, "is not Forwardground", new Object[0]);
                        NextChapterImpl.this.m = true;
                        if (!"MUMUEmulator".equals(n.a())) {
                            return;
                        }
                        handlerH = NextChapterImpl.this.h();
                        runnable = NextChapterImpl.this.L;
                        j = 1000;
                    }
                }
                handlerH.postDelayed(runnable, j);
            }
        }
    };

    private static class a extends Handler {
        private long a;
        private long b;
        private final LBDnsListener c;

        public a(LBDnsListener lBDnsListener, Looper looper) {
            super(looper);
            this.c = lBDnsListener;
        }

        public void a() {
            this.a = 0L;
            this.b = 0L;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 131072) {
                long jLongValue = ((Long) message.obj).longValue();
                if (this.a == 0) {
                    this.a = jLongValue;
                }
                if (this.b == 0) {
                    this.b = SystemClock.elapsedRealtime();
                }
                long jElapsedRealtime = SystemClock.elapsedRealtime() - this.b;
                if (jElapsedRealtime > 2000) {
                    this.c.onDownloadSpeed((jLongValue - this.a) / jElapsedRealtime);
                    this.b = SystemClock.elapsedRealtime();
                    this.a = jLongValue;
                }
                removeMessages(131072);
                Message messageObtain = Message.obtain();
                messageObtain.what = 131072;
                messageObtain.obj = message.obj;
                sendMessageDelayed(messageObtain, 3000L);
            }
        }
    }

    private void a(long j) {
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(104, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.2
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle2, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle2) {
                if (NextChapterImpl.this.l) {
                    NextChapterImpl.this.i();
                    if (NextChapterImpl.this.A) {
                        NextChapterImpl.this.quitGame();
                        return;
                    }
                    return;
                }
                if (NextChapterImpl.this.e == null || NextChapterImpl.this.A) {
                    NextChapterImpl.this.i();
                } else {
                    NextChapterImpl nextChapterImpl = NextChapterImpl.this;
                    nextChapterImpl.startGame(nextChapterImpl.e);
                }
            }
        });
    }

    private void a(Bundle bundle) {
        b.c(TAG, "startCheckNextChapter", new Object[0]);
        Intent intent = new Intent();
        intent.setPackage(d());
        intent.setComponent(new ComponentName(d(), BGService.class.getName()));
        bundle.putInt(BwbxUtil.ACTION_TYPE, BGService.a);
        intent.setAction(BwbxUtil.ACTION_PLAT_DO);
        intent.replaceExtras(bundle);
        b(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final l lVar, final int i2, final Bundle bundle) {
        b.c(TAG, "showUnfinishedDialog enter, type:" + this.g, new Object[0]);
        this.a.onShowDialog(105, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.5
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i3, Bundle bundle2, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i3, Bundle bundle2) {
                NextChapterImpl.this.i();
                if (NextChapterImpl.this.l) {
                    NextChapterImpl.this.quitGame();
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i3, Bundle bundle2) {
                if (NextChapterImpl.this.d == null && !NextChapterImpl.this.n()) {
                    b.c(NextChapterImpl.TAG, "showUnfinishedDlDialog detail==null", new Object[0]);
                    return;
                }
                if (NextChapterImpl.this.m()) {
                    NextChapterImpl.this.downloadComponent(lVar);
                } else if (NextChapterImpl.this.n()) {
                    NextChapterImpl.this.downloadFullRes(i2, bundle);
                }
                NextChapterImpl.this.a.onDownloadConnecting(NextChapterImpl.this.g);
            }
        });
    }

    private void a(l lVar, Bundle bundle) {
        Intent intent;
        if (!new File(lVar.savePath).exists()) {
            b.c(TAG, "startApp failed, file not exists:" + lVar, new Object[0]);
            return;
        }
        int iA = an.a().a(lVar.cfgPath, lVar.savePath);
        if (iA == 1 || iA == -1) {
            try {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setPackage(d());
                intent2.addFlags(268435456);
                if (bundle != null) {
                    intent2.replaceExtras(bundle);
                }
                c(intent2);
            } catch (Exception unused) {
                if (GameUtil.getIntance().checkJJ(g())) {
                    intent = new Intent("com.excelliance.open.action.startPromptActivity");
                    intent.putExtra("dialogtype", 7);
                } else {
                    intent = new Intent("action.lebian.MAIN");
                    intent.addCategory("android.intent.category.DEFAULT");
                }
                intent.addFlags(268435456);
                intent.setPackage(d());
                c(intent);
            }
        }
    }

    private void a(final l lVar, final l lVar2, boolean z, final boolean z2, long j) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("dataConnection", z);
        bundle.putBoolean("forceUpdate", z2);
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(102, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.4
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle2, boolean z3) {
                ae.a(NextChapterImpl.this.f(), "lebian_module_prompt", "gameUpdate", z3);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle2) {
                if (!z2) {
                    NextChapterImpl.this.startGame(lVar2);
                } else if (m.a(NextChapterImpl.this.f(), "ENABLE_EXIT_BUTTON", false)) {
                    NextChapterImpl.this.i();
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle2) {
                NextChapterImpl.this.downloadComponent(lVar);
                ae.a(NextChapterImpl.this.f(), "lebian_module_prompt", "gameUpdate", false);
            }
        });
    }

    private void a(final String str, long j, boolean z, boolean z2, final l lVar) {
        Bundle bundle = new Bundle();
        bundle.putString("mainActivity", str);
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        bundle.putBoolean("forceUpdate", z);
        bundle.putBoolean("dataConnection", z2);
        this.a.onShowDialog(103, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.3
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle2, boolean z3) {
                if (NextChapterImpl.this.a()) {
                    ae.a(NextChapterImpl.this.f(), "lebian_module_prompt", "gameUpdate", z3);
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle2) {
                String str2 = str;
                if (str2 == null || str2.length() <= 0) {
                    NextChapterImpl.this.i();
                } else {
                    NextChapterImpl.this.startMainActivity(str);
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle2) {
                NextChapterImpl.this.downloadComponent(lVar);
                if (NextChapterImpl.this.a()) {
                    ae.a(NextChapterImpl.this.f(), "lebian_module_prompt", "gameUpdate", false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(Context context) {
        if (context != null) {
            return aa.b(context);
        }
        return false;
    }

    private void c(Context context) {
        b.c(TAG, "cancelCheckNextChapter", new Object[0]);
        ae.a(context, "lebian_module_prompt", "cancelChapterCheck", true);
        Intent intent = new Intent();
        intent.setPackage(d());
        intent.setComponent(new ComponentName(d(), BGService.class.getName()));
        intent.setAction(BwbxUtil.ACTION_PLAT_DO);
        intent.putExtra(BwbxUtil.ACTION_TYPE, BGService.a);
        intent.putExtra("cancelChapterCheck", true);
        b(intent);
    }

    private void l() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.excelliance.open.action.appstate");
        intentFilter.addAction(NextChapterParcel.ACTION_NCGENERIC);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        a(this.P, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(d() + NextChapterParcel.ACTION_NOTIFY);
        intentFilter2.addAction("com.excelliance.open.action.downloadcomponent.progress");
        intentFilter2.addAction(d() + ".action.downloadcomponent.downloaded");
        intentFilter2.addAction(d() + "com.excelliance.open.downloadcomponent.error");
        intentFilter2.addAction(d() + ".action.downloadcomponent.extracted");
        intentFilter2.addAction(d() + ".action.downloadcomponent.computeMD5");
        a(this.Q, intentFilter2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m() {
        return this.g == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        return this.g == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.p = true;
        if (m()) {
            Intent intent = new Intent("com.excelliance.open.action.gameverchk");
            intent.setComponent(new ComponentName(f(), BGService.class.getName()));
            intent.setPackage(d());
            b(intent);
            return;
        }
        if (n()) {
            Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
            intent2.setPackage(d());
            intent2.setComponent(new ComponentName(f(), BGService.class.getName()));
            intent2.putExtra("netChange", true);
            intent2.putExtra("extra.lbsdk.hostintent", true);
            b(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (aa.b(f()) && !aa.a(f()) && this.J) {
            q();
        } else {
            s();
        }
    }

    private void q() {
        this.a.onShowDialog(106, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.6
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle) {
                NextChapterImpl.this.quitDownloadBg();
                NextChapterImpl.this.i();
                if (NextChapterImpl.this.l) {
                    NextChapterImpl.this.quitGame();
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle) {
                NextChapterImpl.this.i();
                if (NextChapterImpl.this.l) {
                    NextChapterImpl.this.quitGame();
                }
            }
        });
    }

    private void r() {
        this.a.onShowDialog(107, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.7
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle) {
                NextChapterImpl.this.switchToGP();
            }
        });
    }

    private void s() {
        this.a.onShowDialog(101, null, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.8
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle) {
                NextChapterImpl.this.i();
                if (NextChapterImpl.this.l) {
                    NextChapterImpl.this.quitGame();
                }
            }
        });
    }

    public boolean alreadyDownloaded(Serializable serializable) {
        l lVar = (l) serializable;
        if (lVar.expansionOnly) {
            String strComputeFileMd5 = GameUtil.getIntance().computeFileMd5(GameUtil.getIntance().getObbName(GameUtil.getIntance().getRunningVersionCode(true)));
            b.c(TAG, "alreadyDownloaded obb md5:" + strComputeFileMd5 + " nmd5:" + lVar.nmd5 + " dmd5:" + lVar.dmd5, new Object[0]);
            if (strComputeFileMd5 != null) {
                if (strComputeFileMd5.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                    return true;
                }
            }
            return false;
        }
        if (lVar.rpOnly) {
            String strR = an.a().r();
            if (strR != null) {
                l lVarA = an.a().a(strR);
                b.c(TAG, "alreadyDownloaded res nmd5:" + lVar.nmd5 + " dmd5:" + lVar.dmd5, new Object[0]);
                if (lVarA != null && lVarA.hotResNmd5.equals(lVar.hotResNmd5)) {
                    b.c(TAG, "alreadyDownloaded res md5:" + lVarA.hotResNmd5 + " nmd5:" + lVar.hotResNmd5 + " dmd5:" + lVar.hotResDmd5, new Object[0]);
                    return true;
                }
            }
            return false;
        }
        for (l lVar2 : an.a().b(lVar.gameLib)) {
            boolean zContains = lVar2.savePath.contains("lebian_hu_list-");
            b.c(TAG, "new path=" + lVar2.savePath, new Object[0]);
            String strC = zContains ? c.c(lVar2.savePath) : GameUtil.getIntance().computeFileMd5(lVar2.savePath);
            b.c(TAG, "alreadyDownloaded md5:" + strC + " nmd5:" + lVar.nmd5 + " dmd5" + lVar.dmd5, new Object[0]);
            if (strC != null) {
                if (strC.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.excelliance.lbsdk.main.ui.NextChapterImpl$1] */
    public void downloadComponent(final l lVar) {
        lVar.flag |= 4;
        b.c(TAG, "downloadComponent enter flag:" + lVar.flag + ", sc = " + this.j, new Object[0]);
        an.a().a(lVar.cfgPath, lVar);
        this.j = false;
        this.o = false;
        this.p = true;
        new Thread("NextChapterParcel:downloadComponent") { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                NextChapterImpl.this.q = System.currentTimeMillis();
                boolean z = false;
                while (!NextChapterImpl.this.j) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long jAbs = Math.abs(jCurrentTimeMillis - NextChapterImpl.this.q);
                    b.c(NextChapterImpl.TAG, "downloaded:" + NextChapterImpl.this.t + "--difftime:" + jAbs + " downloadStart:" + NextChapterImpl.this.n, new Object[0]);
                    NextChapterImpl nextChapterImpl = NextChapterImpl.this;
                    if (!nextChapterImpl.b(nextChapterImpl.f())) {
                        NextChapterImpl.this.q = jCurrentTimeMillis;
                    } else if (!NextChapterImpl.this.n && NextChapterImpl.this.t == 0 && NextChapterImpl.this.u == 0 && NextChapterImpl.this.v && jAbs > 120000) {
                        b.c(NextChapterImpl.TAG, "restartgame patch:" + lVar.patch, new Object[0]);
                        NextChapterImpl.this.restartGame(lVar.patch == -1);
                    }
                    if (aa.a(NextChapterImpl.this.f()) || (!aa.a(NextChapterImpl.this.f()) && (lVar.flag & 4) != 0)) {
                        b.c(NextChapterImpl.TAG, "downloadComponent startService", new Object[0]);
                        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
                        intent.setPackage(NextChapterImpl.this.d());
                        intent.setComponent(new ComponentName(NextChapterImpl.this.d(), BGService.class.getName()));
                        if (z) {
                            intent.putExtra("ISINSTALL", lVar.isInstall);
                            intent.putExtra("check", z);
                        } else {
                            intent.putExtra("detail", lVar);
                            intent.putExtra("autodl", false);
                            intent.putExtra("stopOtherDl", NextChapterImpl.i);
                            intent.putExtra("ISINSTALL", lVar.isInstall);
                            z = true;
                        }
                        NextChapterImpl.this.b(intent);
                    }
                    try {
                        Thread.sleep(10000L);
                    } catch (Exception unused) {
                        return;
                    }
                }
            }
        }.start();
    }

    public void downloadFullRes() {
        downloadFullRes(1, null);
    }

    public void downloadFullRes(int i2, Bundle bundle) {
        this.o = false;
        this.p = true;
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(d());
        intent.setComponent(new ComponentName(f(), BGService.class.getName()));
        intent.putExtra("dlsType", 2);
        intent.putExtra("dlsZipType", i2);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("extra.lbsdk.hostintent", true);
        b(intent);
    }

    public void errorCommit(String str, String str2) {
        if (aa.b(f())) {
            String errorCommitPath = GameUtil.getIntance().getErrorCommitPath(str, str2);
            Bundle bundle = new Bundle();
            bundle.putInt("action", 2);
            bundle.putString("errCommitPath", errorCommitPath);
            quitGame(bundle);
        }
    }

    public boolean getPopUnfinishedDialog() {
        if (this.u >= 90) {
            return false;
        }
        boolean z = this.o;
        this.o = false;
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void init(LBDnsListener lBDnsListener, Handler handler, Intent intent) {
        if (lBDnsListener instanceof Activity) {
            a((Activity) lBDnsListener);
            a((Context) lBDnsListener);
        }
        this.a = lBDnsListener;
        a(handler);
        this.b = new a(this.a, Looper.getMainLooper());
        a(intent);
        GameUtil.getIntance().setContext(f());
    }

    public boolean isGP() {
        l lVar = this.d;
        if (lVar == null || !h.a(lVar.flag, 2097152)) {
            return false;
        }
        b.c(TAG, "is gp", new Object[0]);
        return true;
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void onCreate(Bundle bundle) {
        l lVar;
        Intent intentE = e();
        if (intentE == null) {
            return;
        }
        this.l = intentE.getBooleanExtra("gameRunning", false);
        this.y = intentE.getStringExtra("gameid");
        b.c(TAG, "gameRunning:" + this.l, new Object[0]);
        this.c = aa.a(f());
        this.g = intentE.getIntExtra("dlsType", 1);
        l();
        b.c(TAG, "mDownloadsType: " + this.g, new Object[0]);
        if (m()) {
            this.d = (l) intentE.getSerializableExtra("detail");
            this.e = (l) intentE.getSerializableExtra("localDetail");
            this.f = intentE.getStringExtra("dlType");
            this.G = intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L) + intentE.getLongExtra("expsize", 0L) + intentE.getLongExtra("tsize", 0L) + intentE.getLongExtra("psize", 0L);
            h = intentE.getBooleanExtra("quitKillSelf", true);
            i = intentE.getBooleanExtra("stopOtherDl", false);
            this.z = intentE.getStringExtra("savePath");
            this.A = intentE.getBooleanExtra("forceUpdate", false);
            this.B = intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L) + intentE.getLongExtra("expsize", 0L) + intentE.getLongExtra("tsize", 0L) + intentE.getLongExtra("psize", 0L);
            this.C = intentE.getIntExtra("patch", -1) > 0 || intentE.getIntExtra("exppatch", -1) > 0 || intentE.getIntExtra("rppatch", -1) > 0;
            String stringExtra = intentE.getStringExtra("mainActivity");
            this.D = intentE.getStringExtra("notifyTitle");
            this.E = intentE.getStringExtra("notifyMsg");
            l lVar2 = this.d;
            if (lVar2 != null) {
                String str = lVar2.savePath;
                b.c(TAG, "onCreate detail=" + this.d, new Object[0]);
                if (str != null) {
                    b.c(TAG, "action.NextChapterBC savePath != null", new Object[0]);
                    if (!aa.a(f()) && !this.l) {
                        boolean zB = ae.b(f(), "lebian_module_prompt", "gameUpdate", false);
                        if (!"update".equals(this.f)) {
                            if (zB) {
                                startMainActivity(stringExtra);
                                return;
                            } else {
                                a(stringExtra, this.G, this.A, !aa.a(f()), this.d);
                                return;
                            }
                        }
                        if (zB || (lVar = this.e) == null) {
                            startGame(this.e);
                            return;
                        } else {
                            a(this.d, lVar, !aa.a(f()), this.A, this.G);
                            return;
                        }
                    }
                    if (this.d.autoMerge || !alreadyDownloaded(this.d)) {
                        b.c(TAG, "oncreat_startdl", new Object[0]);
                        downloadComponent(this.d);
                    } else {
                        this.m = true;
                    }
                } else {
                    a(this.G);
                }
            }
        } else if (n()) {
            this.H = intentE.getIntExtra("dlsZipType", 1);
            this.I = intentE.getExtras();
            downloadFullRes(this.H, this.I);
        }
        if (this.s) {
            c(b());
        } else if (intentE.getExtras() != null) {
            a(intentE.getExtras());
        }
        if (Build.VERSION.SDK_INT >= 33) {
            g().getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, new OnBackInvokedCallback() { // from class: com.excelliance.lbsdk.main.ui.NextChapterImpl.9
                @Override // android.window.OnBackInvokedCallback
                public void onBackInvoked() {
                    NextChapterImpl.this.p();
                }
            });
        }
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    @TargetApi(GameUtil.F_NOTIFY_TYPE_BWBX_TRANS_MISSING)
    public void onDestroy() {
        b.c(TAG, "onDestroy nc=" + g(), new Object[0]);
        this.j = true;
        try {
            a(this.Q);
            a(this.P);
            h().removeCallbacks(this.M);
            h().removeCallbacks(this.O);
            c(b());
        } catch (Exception unused) {
        }
        if (this.r) {
            GameUtil.getIntance().killGameProcesses(f());
        }
        b.c(TAG, " quitKillSelf:" + h, new Object[0]);
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        b.c(TAG, " getMyMemoryState name:" + runningAppProcessInfo.importance, new Object[0]);
        if (runningAppProcessInfo.importance > 350 && h) {
            al.a(f(), Process.myPid());
            Process.killProcess(Process.myPid());
        }
        h = true;
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 4) {
            return false;
        }
        p();
        return false;
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void onPause() {
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void onResume() {
        l lVar;
        if (isGP()) {
            r();
        }
        boolean z = false;
        if (!aa.b(f())) {
            this.J = false;
            this.a.onError(ErrorCode.CONNECT_ERROR.getCode(), null);
        }
        h().removeCallbacks(this.L);
        if (this.m) {
            this.m = false;
            boolean zA = m.a(f(), "RESTART_NEW_RAW", false);
            l lVar2 = this.d;
            if (lVar2 != null && lVar2.rpOnly && !zA) {
                GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
                i();
                return;
            }
            l lVar3 = this.d;
            if ((lVar3 == null || lVar3.isInstall) && !n()) {
                l lVar4 = this.d;
                if (lVar4 == null || !lVar4.isInstall) {
                    return;
                }
                b.c(TAG, "onResume install new version", new Object[0]);
                overInsPackage();
                return;
            }
            b.c(TAG, "onResume restartGame", new Object[0]);
            if (n()) {
                ae.a(f(), "lebian_module_dloadinfo", "restartAfterDl", true);
            }
            if (m() && (lVar = this.d) != null && lVar.patch == -1) {
                z = true;
            }
            restartGame(z);
        }
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void onStop() {
        b.c(TAG, "onStop gameStarted=" + this.k + ", nc=" + g(), new Object[0]);
        if (!j() && this.k && !GameUtil.getIntance().checkJJ(f())) {
            h().postDelayed(this.M, 3000L);
        }
        b.c(TAG, "onStop isFinishing : " + j() + " installing " + this.s, new Object[0]);
        if (j() || this.s) {
            return;
        }
        h().removeCallbacks(this.N);
        h().postDelayed(this.N, 100L);
    }

    public void overInsPackage() {
        l lVar = this.d;
        if (lVar == null || lVar.savePath == null || !new File(this.d.savePath).exists()) {
            return;
        }
        b.c(TAG, "overInsPackage: quitKillSelf false", new Object[0]);
        h = false;
        this.s = true;
        i();
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setPackage(d());
        intent.setFlags(268435456);
        intent.putExtra("dialogtype", 3);
        intent.putExtra("apkPath", this.d.savePath);
        intent.putExtra("forceUpdate", this.d.forceUpdate);
        c(intent);
    }

    @Override // com.excelliance.lbsdk.library.NextChapterInterface
    public void prepareViewFinished() {
    }

    public void quitDownloadBg() {
        Intent intent;
        String str;
        if (m()) {
            b.c(TAG, "quitDownloadBg: stopCheck", new Object[0]);
            this.j = true;
            intent = new Intent("com.excelliance.open.action.gameverchk");
            intent.setPackage(d());
            intent.setComponent(new ComponentName(d(), BGService.class.getName()));
            str = "canceldownloading";
        } else {
            if (!n()) {
                return;
            }
            intent = new Intent("com.excelliance.open.action.bwbxdla");
            intent.setPackage(d());
            intent.setComponent(new ComponentName(d(), BGService.class.getName()));
            intent.putExtra("dlsType", 2);
            intent.putExtra("zipCancel", true);
            str = "extra.lbsdk.hostintent";
        }
        intent.putExtra(str, true);
        b(intent);
    }

    public void quitGame() {
        quitGame(null);
    }

    public void quitGame(Bundle bundle) {
        b.c(TAG, "quit enter", new Object[0]);
        c(b());
        this.r = true;
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.addFlags(268468224);
        intent.setPackage(d());
        intent.putExtra("quit", true);
        intent.putExtra("dialogtype", 0);
        if (bundle != null) {
            intent.putExtra("param", bundle);
        }
        c(intent);
    }

    public void restartGame() {
        restartGame(false);
    }

    public void restartGame(boolean z) {
        b.c(TAG, "restartGame gameStarted:" + this.k + " killall:" + z, new Object[0]);
        if (this.k) {
            return;
        }
        this.k = true;
        b.c(TAG, "restartGame: kill dns before restart", new Object[0]);
        GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
        if (z) {
            GameUtil.getIntance().restartGameKillAll(f());
        } else {
            GameUtil.getIntance().restartGame(f());
            i();
        }
    }

    public void startGame(l lVar) {
        b.c(TAG, "startGame nc=" + g(), new Object[0]);
        this.k = true;
        a(lVar, GameUtil.getIntance().getParam(lVar.savePath));
    }

    public void startMainActivity(String str) {
        Intent intent;
        this.k = true;
        try {
            try {
                b.c(TAG, "startMainActivity className:" + str, new Object[0]);
                Intent intent2 = new Intent();
                intent2.setComponent(new ComponentName(d(), str));
                intent2.addCategory("android.intent.category.LAUNCHER");
                c(intent2);
            } catch (Exception e) {
                b.b(TAG, "startMainActivity e:" + e, new Object[0]);
            }
        } catch (Exception unused) {
            if (GameUtil.getIntance().checkJJ(g())) {
                intent = new Intent("com.excelliance.open.action.startPromptActivity");
                intent.putExtra("dialogtype", 7);
            } else {
                intent = new Intent("action.lebian.MAIN");
                intent.addCategory("android.intent.category.DEFAULT");
            }
            intent.addFlags(268435456);
            intent.setPackage(d());
            c(intent);
        }
    }

    public void switchToGP() {
        if (a()) {
            b.c(TAG, "switchToGP", new Object[0]);
            GameUtil.toGP(f());
        }
    }
}
