package com.excelliance.lbsdk.main;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.util.Xml;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class NextChapterParcel implements Parcelable {
    public static final String ACTION_NCGENERIC = "broadcast.action.NextChapter.Generic";
    public static final String ACTION_NOTIFY = ".action.NextChapter.Notify";
    public static final Parcelable.Creator<NextChapterParcel> CREATOR = new Parcelable.Creator<NextChapterParcel>() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.2
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NextChapterParcel createFromParcel(Parcel parcel) {
            return new NextChapterParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NextChapterParcel[] newArray(int i) {
            return new NextChapterParcel[i];
        }
    };
    public static final String GAME_RUNNING = "gameRunning";
    private static boolean p = true;
    private static boolean q = false;
    private long H;
    private String c;
    private String f;
    private boolean g;
    private boolean h;
    private ad i;
    private int u;
    private long v;
    private Activity a = null;
    private final String b = "NextChapterParcel";
    private l d = null;
    private l e = null;
    private boolean j = false;
    private boolean k = false;
    private Handler l = null;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private final Runnable r = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.1
        @Override // java.lang.Runnable
        public void run() {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "FinishCallBack: finish activity", new Object[0]);
            NextChapterParcel.this.e();
        }
    };
    private final Runnable s = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.3
        @Override // java.lang.Runnable
        public void run() {
            boolean zA = aa.a(NextChapterParcel.this.a);
            boolean remove_auto_download = NextChapterParcel.this.getREMOVE_AUTO_DOWNLOAD();
            if (NextChapterParcel.this.D || NextChapterParcel.this.u >= 99) {
                if (NextChapterParcel.this.A && !zA) {
                    NextChapterParcel.this.quitDownloadBg();
                    NextChapterParcel.this.D = false;
                    NextChapterParcel.this.C = true;
                }
            } else if (remove_auto_download || !zA) {
                NextChapterParcel.this.C = true;
            } else {
                NextChapterParcel.this.g();
                NextChapterParcel.this.C = false;
            }
            NextChapterParcel.this.A = zA;
        }
    };
    private final Runnable t = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.4
        @Override // java.lang.Runnable
        public void run() {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "Mumu BackGround Callback", new Object[0]);
            if (al.b(NextChapterParcel.this.a)) {
                NextChapterParcel.this.onResume();
            } else {
                NextChapterParcel.this.l.removeCallbacks(NextChapterParcel.this.t);
                NextChapterParcel.this.l.postDelayed(NextChapterParcel.this.t, 1000L);
            }
        }
    };
    private boolean w = true;
    private int x = 0;
    private boolean y = false;
    private boolean z = false;
    private boolean A = false;
    private boolean B = true;
    private boolean C = false;
    private boolean D = false;
    private BroadcastReceiver E = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (NextChapterParcel.this.B) {
                    NextChapterParcel.this.B = false;
                    return;
                }
                if (aa.b(NextChapterParcel.this.a) && NextChapterParcel.this.u < 99) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "net change", new Object[0]);
                    int i = NextChapterParcel.this.D ? 1000 : 0;
                    NextChapterParcel.this.l.removeCallbacks(NextChapterParcel.this.s);
                    NextChapterParcel.this.l.postDelayed(NextChapterParcel.this.s, i);
                    return;
                }
                if (aa.b(NextChapterParcel.this.a)) {
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "net unconnected", new Object[0]);
                NextChapterParcel.this.l.removeCallbacks(NextChapterParcel.this.s);
                NextChapterParcel.this.C = false;
            }
        }
    };
    private BroadcastReceiver F = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            context.getPackageName();
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onReceive action = " + action, new Object[0]);
            if (!action.equals("com.excelliance.open.action.appstate")) {
                if (action.equals(NextChapterParcel.ACTION_NCGENERIC) && intent.getIntExtra("method", 0) == 1) {
                    NextChapterParcel.this.b((Intent) intent.getParcelableExtra("param"));
                    return;
                }
                return;
            }
            String stringExtra = intent.getStringExtra("apk");
            int intExtra = intent.getIntExtra("state", -1);
            int intExtra2 = intent.getIntExtra("error", 0);
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onReceive apkPath = " + stringExtra + ", state = " + intExtra + ", error=" + intExtra2, new Object[0]);
            if (intExtra >= 0) {
                if (intExtra == 1) {
                    if (intExtra2 == 0 || intExtra2 != 1) {
                        return;
                    }
                } else if ((intExtra != 2 || stringExtra == null || stringExtra.length() <= 0) && intExtra != 3) {
                    return;
                }
                NextChapterParcel.this.e();
            }
        }
    };
    private final Runnable G = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.9
        @Override // java.lang.Runnable
        public void run() {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "NextChapter mOnPauseCallBack enter", new Object[0]);
            if (al.c(NextChapterParcel.this.a)) {
                return;
            }
            if (!al.b(NextChapterParcel.this.a)) {
                if (Build.VERSION.SDK_INT >= 33 && TextUtils.isEmpty(GameUtil.getTopActivity(NextChapterParcel.this.a))) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "topActivity = null", new Object[0]);
                    return;
                }
                ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                int i = entryActivityInfo == null ? -1 : entryActivityInfo.launchMode;
                if (i == 2 || i == 3) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "launchMode=" + i, new Object[0]);
                    GameUtil.getIntance().killGameProcesses(NextChapterParcel.this.a);
                    NextChapterParcel.this.e();
                    return;
                }
                return;
            }
            String topActivity = GameUtil.getTopActivity(NextChapterParcel.this.a);
            if (topActivity == null || !topActivity.contains("GrantPermissionsActivity")) {
                if (Build.VERSION.SDK_INT >= 34 && TextUtils.isEmpty(GameUtil.getTopActivity(NextChapterParcel.this.a))) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "topActivity = null", new Object[0]);
                    return;
                }
                if (Build.VERSION.SDK_INT >= 33 && TextUtils.isEmpty(topActivity)) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "topActivity = null", new Object[0]);
                    return;
                }
                Bundle extras = NextChapterParcel.this.c().getExtras();
                Intent intent = new Intent("com.excelliance.open.action.startNextChapter");
                intent.setPackage(NextChapterParcel.this.b());
                intent.replaceExtras(extras);
                intent.addFlags(536870912);
                NextChapterParcel.this.b(intent);
                NextChapterParcel.this.a.finish();
            }
        }
    };

    protected NextChapterParcel(Parcel parcel) {
        this.g = false;
        this.h = false;
        if (parcel != null) {
            Bundle bundle = parcel.readBundle();
            this.g = bundle.getBoolean("stopCheck");
            this.h = bundle.getBoolean("gameStarted");
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "restore from parcel stopCheck=" + this.g + " gameStarted=" + this.h, new Object[0]);
        }
    }

    private void a(BroadcastReceiver broadcastReceiver) {
        s.a(this.a, broadcastReceiver);
    }

    private void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        s.a(this.a, broadcastReceiver, intentFilter);
    }

    private void a(Context context, Bundle bundle) {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "startCheckNextChapter", new Object[0]);
        Intent intent = new Intent();
        intent.setPackage(b());
        intent.setComponent(new ComponentName(b(), BGService.class.getName()));
        bundle.putInt(BwbxUtil.ACTION_TYPE, BGService.a);
        intent.setAction(BwbxUtil.ACTION_PLAT_DO);
        intent.replaceExtras(bundle);
        a(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent) {
        GameUtil.startService(intent, this.a);
    }

    private void a(l lVar, Bundle bundle) {
        Intent intent;
        if (!new File(lVar.savePath).exists()) {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "startApp failed, file not exists:" + lVar, new Object[0]);
            return;
        }
        int iA = an.a().a(lVar.cfgPath, lVar.savePath);
        if (iA == 1 || iA == -1) {
            try {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setPackage(b());
                intent2.addFlags(268435456);
                if (bundle != null) {
                    intent2.replaceExtras(bundle);
                }
                b(intent2);
            } catch (Exception unused) {
                if (GameUtil.getIntance().checkJJ(this.a)) {
                    intent = new Intent("com.excelliance.open.action.startPromptActivity");
                    intent.putExtra("dialogtype", 7);
                } else {
                    intent = new Intent("action.lebian.MAIN");
                    intent.addCategory("android.intent.category.DEFAULT");
                }
                intent.addFlags(268435456);
                intent.setPackage(b());
                b(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context) {
        if (context != null) {
            return aa.b(context);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b() {
        return this.a.getPackageName();
    }

    private void b(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        s.b(this.a, broadcastReceiver, intentFilter);
    }

    private void b(Context context) {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "cancelCheckNextChapter", new Object[0]);
        ae.a(context, "lebian_module_prompt", "cancelChapterCheck", true);
        Intent intent = new Intent();
        intent.setPackage(b());
        intent.setComponent(new ComponentName(b(), BGService.class.getName()));
        intent.setAction(BwbxUtil.ACTION_PLAT_DO);
        intent.putExtra(BwbxUtil.ACTION_TYPE, BGService.a);
        intent.putExtra("cancelChapterCheck", true);
        a(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Intent intent) {
        this.a.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent c() {
        return this.a.getIntent();
    }

    private Context d() {
        return this.a.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.a.finish();
    }

    private boolean f() {
        return this.a.isFinishing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.D = true;
        int i = this.m;
        if (i == 1) {
            Intent intent = new Intent("com.excelliance.open.action.gameverchk");
            intent.setComponent(new ComponentName(this.a, BGService.class.getName()));
            intent.setPackage(b());
            a(intent);
            return;
        }
        if (i == 2) {
            Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
            intent2.setPackage(b());
            intent2.setComponent(new ComponentName(this.a, BGService.class.getName()));
            intent2.putExtra("netChange", true);
            intent2.putExtra("extra.lbsdk.hostintent", true);
            a(intent2);
        }
    }

    public boolean alreadyDownloaded(Serializable serializable) {
        l lVar = (l) serializable;
        if (lVar.expansionOnly) {
            String strComputeFileMd5 = GameUtil.getIntance().computeFileMd5(GameUtil.getIntance().getObbName(GameUtil.getIntance().getRunningVersionCode(true)));
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "alreadyDownloaded obb md5:" + strComputeFileMd5 + " nmd5:" + lVar.nmd5 + " dmd5:" + lVar.dmd5, new Object[0]);
            if (strComputeFileMd5 != null) {
                if (strComputeFileMd5.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                    return true;
                }
            }
            return false;
        }
        for (l lVar2 : an.a().b(lVar.gameLib)) {
            boolean zContains = lVar2.savePath.contains("lebian_hu_list-");
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "new path=" + lVar2.savePath, new Object[0]);
            String strC = zContains ? com.excelliance.lbsdk.main.ae.c.c(lVar2.savePath) : GameUtil.getIntance().computeFileMd5(lVar2.savePath);
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "alreadyDownloaded md5:" + strC + " nmd5:" + lVar.nmd5 + " dmd5" + lVar.dmd5, new Object[0]);
            if (strC != null) {
                if (strC.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.excelliance.lbsdk.main.NextChapterParcel$10] */
    public void downloadComponent(Serializable serializable) {
        final l lVar = (l) serializable;
        lVar.flag |= 4;
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "downloadComponent enter flag:" + lVar.flag + ", sc = " + this.g, new Object[0]);
        an.a().a(lVar.cfgPath, lVar);
        this.g = false;
        this.C = false;
        this.D = true;
        new Thread("NextChapterParcel:downloadComponent") { // from class: com.excelliance.lbsdk.main.NextChapterParcel.10
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                GameUtil.getIntance();
                NextChapterParcel.this.H = System.currentTimeMillis();
                boolean z = false;
                while (!NextChapterParcel.this.g) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long jAbs = Math.abs(jCurrentTimeMillis - NextChapterParcel.this.H);
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "downloaded:" + NextChapterParcel.this.v + "--difftime:" + jAbs + " downloadStart:" + NextChapterParcel.this.o, new Object[0]);
                    NextChapterParcel nextChapterParcel = NextChapterParcel.this;
                    if (!nextChapterParcel.a(nextChapterParcel.a)) {
                        NextChapterParcel.this.H = jCurrentTimeMillis;
                    } else if (!NextChapterParcel.this.o && NextChapterParcel.this.v == 0 && NextChapterParcel.this.u == 0 && NextChapterParcel.this.w && jAbs > 120000) {
                        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "restartgame patch:" + lVar.patch, new Object[0]);
                        NextChapterParcel.this.restartGame(lVar.patch == -1);
                    }
                    if (aa.a(NextChapterParcel.this.a) || (!aa.a(NextChapterParcel.this.a) && (lVar.flag & 4) != 0)) {
                        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "downloadComponent startService", new Object[0]);
                        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
                        intent.setPackage(NextChapterParcel.this.b());
                        intent.setComponent(new ComponentName(NextChapterParcel.this.b(), BGService.class.getName()));
                        if (z) {
                            intent.putExtra("ISINSTALL", lVar.isInstall);
                            intent.putExtra("check", z);
                        } else {
                            intent.putExtra("detail", lVar);
                            intent.putExtra("autodl", false);
                            intent.putExtra("stopOtherDl", NextChapterParcel.q);
                            intent.putExtra("ISINSTALL", lVar.isInstall);
                            z = true;
                        }
                        NextChapterParcel.this.a(intent);
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

    public void downloadComponentCheck() {
        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
        intent.setPackage(b());
        intent.setComponent(new ComponentName(b(), BGService.class.getName()));
        intent.putExtra("gameid", "check");
        a(intent);
    }

    public void downloadFullRes() {
        downloadFullRes(1, null);
    }

    public void downloadFullRes(int i, Bundle bundle) {
        this.C = false;
        this.D = true;
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(b());
        intent.setComponent(new ComponentName(this.a, BGService.class.getName()));
        intent.putExtra("dlsType", 2);
        intent.putExtra("dlsZipType", i);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("extra.lbsdk.hostintent", true);
        a(intent);
    }

    public void errorCommit(String str, String str2) {
        if (aa.b(this.a)) {
            String errorCommitPath = GameUtil.getIntance().getErrorCommitPath(str, str2);
            Bundle bundle = new Bundle();
            bundle.putInt("action", 2);
            bundle.putString("errCommitPath", errorCommitPath);
            quitGame(bundle);
        }
    }

    public String getCURRRESNUM() {
        return "currResNum";
    }

    public String getDMD5() {
        return "dmd5";
    }

    public String getDOWNLOADED() {
        return "downloaded";
    }

    public String getDOWNLOADED_INTENT() {
        return ".action.downloadcomponent.downloaded";
    }

    public String getERROR() {
        return "error";
    }

    public String getERRORTYPE() {
        return "errType";
    }

    public String getERROR_INTENT() {
        return "com.excelliance.open.downloadcomponent.error";
    }

    public String getEXTRACTED_INTENT() {
        return ".action.downloadcomponent.extracted";
    }

    public String getGAMEID() {
        return "gameid";
    }

    public String getMERGE() {
        return "merge";
    }

    public String getPROGRESS() {
        return BwbxUtil.ACTION_PROGRESS;
    }

    public String getPROGRESS_INTENT() {
        return "com.excelliance.open.action.downloadcomponent.progress";
    }

    public boolean getPopUnfinishedDialog() {
        if (this.u >= 90) {
            return false;
        }
        boolean z = this.C;
        this.C = false;
        return z;
    }

    public boolean getREMOVE_AUTO_DOWNLOAD() {
        return false;
    }

    public Resources getResources() {
        return PlatformResources.getMainResources(this.a);
    }

    public String getSIZE() {
        return BwbxUtil.ACTION_SIZE;
    }

    public String getTOTALRESNUM() {
        return "totalResNum";
    }

    public void init(Activity activity, Handler handler, Bundle bundle) {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onCreate nc=" + activity, new Object[0]);
        setActivity(activity);
        setHandler(handler);
        onCreate(bundle);
    }

    public boolean isGP() {
        l lVar = this.d;
        if (lVar == null || !h.a(lVar.flag, 2097152)) {
            return false;
        }
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "is gp", new Object[0]);
        return true;
    }

    protected void onCreate(Bundle bundle) {
        Intent intentC = c();
        if (intentC == null) {
            return;
        }
        this.j = intentC.getBooleanExtra("gameRunning", false);
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "gameRunning:" + this.j, new Object[0]);
        this.c = b();
        if (!intentC.getBooleanExtra("ISINSTALL", false)) {
            this.i = ad.a(d());
        }
        GameUtil.getIntance().setContext(d());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.excelliance.open.action.appstate");
        intentFilter.addAction(ACTION_NCGENERIC);
        a(this.F, intentFilter);
        this.A = aa.a(this.a);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        b(this.E, intentFilter2);
        this.m = intentC.getIntExtra("dlsType", 1);
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "mDownloadsType: " + this.m, new Object[0]);
        int i = this.m;
        if (i == 1) {
            this.d = (l) intentC.getSerializableExtra("detail");
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "autoMerge:" + this.d.autoMerge, new Object[0]);
            this.e = (l) intentC.getSerializableExtra("localDetail");
            this.f = intentC.getStringExtra("dlType");
            p = intentC.getBooleanExtra("quitKillSelf", true);
            q = intentC.getBooleanExtra("stopOtherDl", false);
            String stringExtra = intentC.getStringExtra("mainActivity");
            l lVar = this.d;
            if (lVar != null) {
                String str = lVar.savePath;
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onCreate detail=" + this.d, new Object[0]);
                if (str != null) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "action.NextChapterBC savePath != null", new Object[0]);
                    an.a();
                    if (!aa.a(this.a) && !this.j) {
                        boolean zB = ae.b((Context) this.a, "lebian_module_prompt", "gameUpdate", false);
                        if (!this.f.equals("update")) {
                            if (zB) {
                                startMainActivity(stringExtra);
                                return;
                            }
                            return;
                        } else {
                            if (zB || this.e == null) {
                                startGame(this.e, false);
                                return;
                            }
                            return;
                        }
                    }
                    if (this.d.autoMerge || !alreadyDownloaded(this.d)) {
                        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "oncreat_startdl", new Object[0]);
                        downloadComponent(this.d);
                    } else {
                        this.k = true;
                    }
                }
            }
        } else if (i == 2) {
            downloadFullRes(intentC.getIntExtra("dlsZipType", 1), intentC.getExtras());
        }
        if (this.z) {
            b(this.a.getApplicationContext());
        } else if (intentC.getExtras() != null) {
            a(this.a.getApplicationContext(), intentC.getExtras());
        }
    }

    protected void onDestroy() {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onDestroy nc=" + this.a, new Object[0]);
        this.g = true;
        try {
            a(this.E);
            a(this.F);
            this.l.removeCallbacks(this.r);
            this.l.removeCallbacks(this.s);
            b(this.a.getApplicationContext());
        } catch (Exception unused) {
        }
        if (this.n) {
            GameUtil.getIntance().killGameProcesses(this.a);
        }
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", " quitKillSelf:" + p, new Object[0]);
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", " getMyMemoryState name:" + runningAppProcessInfo.importance, new Object[0]);
        if (runningAppProcessInfo.importance > 350 && p) {
            al.a(this.a, Process.myPid());
            Process.killProcess(Process.myPid());
        }
        p = true;
    }

    protected void onPause() {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onPause gameStarted=" + this.h + ", nc=" + this.a, new Object[0]);
        if (!f() && this.h && !GameUtil.getIntance().checkJJ(this.a)) {
            this.l.postDelayed(this.r, 3000L);
        }
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onPause isFinishing : " + f() + " installing " + this.z, new Object[0]);
        if (f() || this.z) {
            return;
        }
        this.l.removeCallbacks(this.G);
        this.l.postDelayed(this.G, 100L);
    }

    /* JADX WARN: Code duplicated, block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x0124  */
    /* JADX WARN: Code duplicated, block: B:72:0x012f  */
    /* JADX WARN: Code duplicated, block: B:74:0x014d  */
    /* JADX WARN: Code duplicated, block: B:75:0x0155  */
    /* JADX WARN: Code duplicated, block: B:76:0x0160  */
    /* JADX WARN: Code duplicated, block: B:77:0x017f  */
    /* JADX WARN: Instruction removed from duplicated block: B:72:0x012f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:76:0x0160, please report this as an issue */
    public void onReceive(Context context, Intent intent) {
        Handler handler;
        Runnable runnable;
        l lVar;
        l lVar2;
        l lVar3;
        l lVar4;
        String action = intent.getAction();
        String strB = b();
        String stringExtra = intent.getStringExtra("gameid");
        if ((this.m == 1 && stringExtra == null) || (this.m == 2 && stringExtra != null)) {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "bc mismatch", new Object[0]);
            return;
        }
        if (action.equals(strB + "com.excelliance.open.downloadcomponent.error")) {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "gameId=" + stringExtra + " detail=" + this.d, new Object[0]);
            if (this.m == 1 && ((lVar4 = this.d) == null || stringExtra == null || !stringExtra.equals(lVar4.gameId))) {
                return;
            }
            this.w = false;
            this.g = true;
            this.D = false;
            return;
        }
        if (!action.equals("com.excelliance.open.action.downloadcomponent.progress")) {
            if (action.equals(strB + ".action.downloadcomponent.downloaded")) {
                this.v = intent.getLongExtra("downloaded", 0L);
                if (this.m != 1 || this.v <= 0 || this.o) {
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onReceive: downloadStart", new Object[0]);
                this.o = true;
                return;
            }
            if (action.equals(strB + ACTION_NOTIFY)) {
                this.w = false;
                this.D = false;
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "network not available", new Object[0]);
                return;
            }
            return;
        }
        this.u = intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0);
        if (this.m == 1 && ((lVar3 = this.d) == null || stringExtra == null || !stringExtra.equals(lVar3.gameId))) {
            return;
        }
        int i = this.u;
        if (i == -2) {
            this.g = true;
            return;
        }
        if (i == -1) {
            return;
        }
        if (this.m == 1 && (lVar2 = this.d) != null && lVar2.patch != -1 && !this.y && this.u > 0 && !intent.getBooleanExtra("ISINSTALL", false)) {
            this.y = true;
            ad.a(context).a();
        }
        if (this.u == 100) {
            int i2 = this.m;
            if (i2 != 1) {
                if (i2 == 2) {
                    if (al.b(context)) {
                        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "isForwardground", new Object[0]);
                        ae.a((Context) this.a, "lebian_module_dloadinfo", "restartAfterDl", true);
                        handler = this.l;
                        runnable = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.6
                            @Override // java.lang.Runnable
                            public void run() {
                                NextChapterParcel.this.restartGame();
                            }
                        };
                        handler.postDelayed(runnable, 500L);
                    }
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "is not Forwardground", new Object[0]);
                    this.k = true;
                    if (!"MUMUEmulator".equals(n.a())) {
                        return;
                    }
                    this.l.postDelayed(this.t, 1000L);
                    return;
                }
                return;
            }
            if (this.d.gameId.equals(stringExtra)) {
                this.g = true;
            }
            String stringExtra2 = intent.getStringExtra("savePath");
            if (stringExtra2 == null) {
                String strB2 = ae.b(this.a, "lebian_module_extractinfo", "next", (String) null);
                if (strB2 == null) {
                    strB2 = ae.b(this.a, "lebian_module_extractinfo", "current", (String) null);
                }
                l lVarA = an.a().a(strB2);
                if (lVarA != null) {
                    lVar = this.d;
                    stringExtra2 = lVarA.savePath;
                }
                if (al.b(context)) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "is not Forwardground", new Object[0]);
                    this.k = true;
                    if (!"MUMUEmulator".equals(n.a())) {
                        return;
                    }
                    this.l.postDelayed(this.t, 1000L);
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "isForwardground", new Object[0]);
                if (!this.d.isInstall) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "install savePath:" + this.d.savePath, new Object[0]);
                    overInsPackage();
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onReceive: patch:" + this.d.patch, new Object[0]);
                if (this.j) {
                    startGame(this.d, true);
                    return;
                }
                handler = this.l;
                runnable = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.5
                    @Override // java.lang.Runnable
                    public void run() {
                        NextChapterParcel nextChapterParcel = NextChapterParcel.this;
                        nextChapterParcel.restartGame(nextChapterParcel.d.patch == -1);
                    }
                };
                handler.postDelayed(runnable, 500L);
            }
            lVar = this.d;
            lVar.savePath = stringExtra2;
            if (al.b(context)) {
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "is not Forwardground", new Object[0]);
                this.k = true;
                if (!"MUMUEmulator".equals(n.a())) {
                    return;
                }
                this.l.postDelayed(this.t, 1000L);
                return;
            }
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "isForwardground", new Object[0]);
            if (!this.d.isInstall) {
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "install savePath:" + this.d.savePath, new Object[0]);
                overInsPackage();
                return;
            }
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onReceive: patch:" + this.d.patch, new Object[0]);
            if (this.j) {
                startGame(this.d, true);
                return;
            }
            handler = this.l;
            runnable = new Runnable() { // from class: com.excelliance.lbsdk.main.NextChapterParcel.5
                @Override // java.lang.Runnable
                public void run() {
                    NextChapterParcel nextChapterParcel = NextChapterParcel.this;
                    nextChapterParcel.restartGame(nextChapterParcel.d.patch == -1);
                }
            };
            handler.postDelayed(runnable, 500L);
        }
    }

    protected void onResume() {
        l lVar;
        this.l.removeCallbacks(this.t);
        if (this.k) {
            boolean z = false;
            this.k = false;
            l lVar2 = this.d;
            if ((lVar2 == null || lVar2.isInstall) && this.m != 2) {
                l lVar3 = this.d;
                if (lVar3 == null || !lVar3.isInstall) {
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onResume install new version", new Object[0]);
                overInsPackage();
                return;
            }
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "onResume restartGame", new Object[0]);
            if (this.m == 2) {
                ae.a((Context) this.a, "lebian_module_dloadinfo", "restartAfterDl", true);
            }
            if (this.m == 1 && (lVar = this.d) != null && lVar.patch == -1) {
                z = true;
            }
            restartGame(z);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
    }

    public void overInsPackage() {
        l lVar = this.d;
        if (lVar == null || lVar.savePath == null || !new File(this.d.savePath).exists()) {
            return;
        }
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "overInsPackage: quitKillSelf false", new Object[0]);
        p = false;
        this.z = true;
        e();
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setPackage(this.a.getPackageName());
        intent.setFlags(268435456);
        intent.putExtra("dialogtype", 3);
        intent.putExtra("apkPath", this.d.savePath);
        intent.putExtra("forceUpdate", this.d.forceUpdate);
        this.a.startActivity(intent);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00aa  */
    public String parseUserinfo(String str) {
        ByteArrayInputStream byteArrayInputStream;
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "parseUserinfo enter", new Object[0]);
        File file = new File(GameUtil.getDataRoot(this.a) + "userinfo.cfg");
        String str2 = null;
        try {
            if (file.exists()) {
                StringBuilder sb = new StringBuilder();
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    fileInputStream.available();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        sb.append(new String(bArr, 0, i, "UTF-8"));
                    }
                    fileInputStream.close();
                } catch (Exception e) {
                    com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "e=" + e, new Object[0]);
                }
                String string = sb.toString();
                if (string == null || string.length() == 0) {
                    return null;
                }
                if (string != null) {
                    try {
                        if (string.trim().equals("")) {
                            byteArrayInputStream = null;
                        } else {
                            byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
                        }
                    } catch (Exception e2) {
                        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "parseLocalConfig e" + e2, new Object[0]);
                    }
                } else {
                    byteArrayInputStream = null;
                }
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                try {
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                    String attributeValue = null;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0 && eventType == 2) {
                            try {
                                if (xmlPullParserNewPullParser.getName().equalsIgnoreCase("info")) {
                                    attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, str);
                                }
                            } catch (XmlPullParserException e3) {
                                e = e3;
                                str2 = attributeValue;
                                e.printStackTrace();
                                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "parseUserinfo end", new Object[0]);
                                return str2;
                            } catch (Exception e4) {
                                e = e4;
                                str2 = attributeValue;
                                e.printStackTrace();
                                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "parseUserinfo end", new Object[0]);
                                return str2;
                            }
                        }
                    }
                    byteArrayInputStream.close();
                    str2 = attributeValue;
                } catch (XmlPullParserException e5) {
                    e = e5;
                } catch (Exception e6) {
                    e = e6;
                }
            }
        } catch (Exception unused) {
        }
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "parseUserinfo end", new Object[0]);
        return str2;
    }

    public void quitDownloadBg() {
        Intent intent;
        String str;
        int i = this.m;
        if (i == 1) {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "quitDownloadBg: stopCheck", new Object[0]);
            this.g = true;
            intent = new Intent("com.excelliance.open.action.gameverchk");
            intent.setPackage(b());
            intent.setComponent(new ComponentName(b(), BGService.class.getName()));
            str = "canceldownloading";
        } else {
            if (i != 2) {
                return;
            }
            intent = new Intent("com.excelliance.open.action.bwbxdla");
            intent.setPackage(b());
            intent.setComponent(new ComponentName(b(), BGService.class.getName()));
            intent.putExtra("dlsType", 2);
            intent.putExtra("zipCancel", true);
            str = "extra.lbsdk.hostintent";
        }
        intent.putExtra(str, true);
        a(intent);
    }

    public void quitGame() {
        quitGame(null);
    }

    public void quitGame(Bundle bundle) {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "quit enter", new Object[0]);
        b(this.a.getApplicationContext());
        this.n = true;
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.addFlags(268468224);
        intent.setPackage(b());
        intent.putExtra("quit", true);
        intent.putExtra("dialogtype", 0);
        if (bundle != null) {
            intent.putExtra("param", bundle);
        }
        b(intent);
    }

    public void restartGame() {
        restartGame(false);
    }

    public void restartGame(boolean z) {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "restartGame gameStarted:" + this.h + " killall:" + z, new Object[0]);
        if (this.h) {
            return;
        }
        this.h = true;
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "restartGame: kill dns before restart", new Object[0]);
        GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
        if (z) {
            GameUtil.getIntance().restartGameKillAll(this.a);
        } else {
            GameUtil.getIntance().restartGame(this.a);
            e();
        }
    }

    public void setActivity(Activity activity) {
        this.a = activity;
    }

    public void setHandler(Handler handler) {
        this.l = handler;
    }

    public void startGame(Serializable serializable, Boolean bool) {
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "startGame nc=" + this.a, new Object[0]);
        l lVar = (l) serializable;
        this.h = true;
        a(lVar, GameUtil.getIntance().getParam(lVar.savePath));
    }

    public void startMainActivity(String str) {
        Intent intent;
        this.h = true;
        try {
            try {
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "startMainActivity className:" + str, new Object[0]);
                Intent intent2 = new Intent();
                intent2.setComponent(new ComponentName(b(), str));
                intent2.addCategory("android.intent.category.LAUNCHER");
                b(intent2);
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "startMainActivity e:" + e, new Object[0]);
            }
        } catch (Exception unused) {
            if (GameUtil.getIntance().checkJJ(this.a)) {
                intent = new Intent("com.excelliance.open.action.startPromptActivity");
                intent.putExtra("dialogtype", 7);
            } else {
                intent = new Intent("action.lebian.MAIN");
                intent.addCategory("android.intent.category.DEFAULT");
            }
            intent.addFlags(268435456);
            intent.setPackage(b());
            b(intent);
        }
    }

    public void switchToGP() {
        if (this.a != null) {
            com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "switchToGP", new Object[0]);
            GameUtil.toGP(this.a);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("stopCheck", this.g);
        bundle.putBoolean("gameStarted", this.h);
        com.excelliance.lbsdk.main.g.b.c("NextChapterParcel", "writeToParcel stopCheck=" + this.g + " gameStarted=" + this.h, new Object[0]);
        parcel.writeBundle(bundle);
    }
}
