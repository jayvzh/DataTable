package com.excelliance.lbsdk.main;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class PromptActivityParcel implements Parcelable {
    public static final Parcelable.Creator<PromptActivityParcel> CREATOR = new Parcelable.Creator<PromptActivityParcel>() { // from class: com.excelliance.lbsdk.main.PromptActivityParcel.4
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PromptActivityParcel createFromParcel(Parcel parcel) {
            return new PromptActivityParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PromptActivityParcel[] newArray(int i) {
            return new PromptActivityParcel[i];
        }
    };
    protected static final int QUIT_ACTION_DEFAULT = 0;
    protected static final int QUIT_ACTION_ERROR_COMMIT = 2;
    protected static final int QUIT_ACTION_INSTALL = 1;
    private boolean g;
    private boolean h;
    private boolean i;
    private BroadcastReceiver j;
    private Activity a = null;
    private final String b = "PromptActivityParcel";
    private Handler c = null;
    private boolean d = false;
    private boolean e = true;
    private boolean f = true;
    private final Runnable k = new Runnable() { // from class: com.excelliance.lbsdk.main.PromptActivityParcel.2
        @Override // java.lang.Runnable
        public void run() {
            boolean booleanExtra;
            String str;
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "mOnPauseCallBack enter", new Object[0]);
            if (al.c(PromptActivityParcel.this.a)) {
                return;
            }
            if (!al.b(PromptActivityParcel.this.a)) {
                ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                int i = entryActivityInfo != null ? entryActivityInfo.launchMode : -1;
                if (i == 2 || i == 3) {
                    com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "launchMode=" + i, new Object[0]);
                    GameUtil.getIntance().killGameProcesses(PromptActivityParcel.this.a);
                    PromptActivityParcel.this.e = true;
                    PromptActivityParcel.this.a();
                    return;
                }
                return;
            }
            String topActivity = GameUtil.getTopActivity(PromptActivityParcel.this.a);
            if (TextUtils.isEmpty(topActivity)) {
                return;
            }
            if (topActivity == null || !topActivity.contains("GrantPermissionsActivity")) {
                Intent intentC = PromptActivityParcel.this.c();
                Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
                intent.setPackage(PromptActivityParcel.this.e());
                intent.putExtra("dialogtype", intentC.getIntExtra("dialogtype", 1));
                com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "dialogtype=" + intentC.getIntExtra("dialogtype", 1), new Object[0]);
                int intExtra = intentC.getIntExtra("dialogtype", 1);
                if (intExtra != 1) {
                    if (intExtra == 2) {
                        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "downloaded....", new Object[0]);
                        l lVar = (l) intentC.getSerializableExtra("detail");
                        intent.putExtra("apkPath", lVar.savePath);
                        intent.putExtra("isInstall", lVar.isInstall);
                        intent.putExtra("forceUpdate", lVar.forceUpdate);
                        intent.putExtra("detail", lVar);
                    } else if (intExtra == 3) {
                        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "install....", new Object[0]);
                        intent.putExtra("apkPath", intentC.getStringExtra("apkPath"));
                        intent.putExtra("forceUpdate", intentC.getBooleanExtra("forceUpdate", false));
                    } else if (intExtra == 4) {
                        intent.putExtra(BwbxUtil.ACTION_SIZE, intentC.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                        intent.putExtra("dataConnection", intentC.getBooleanExtra("dataConnection", false));
                        intent.putExtra("zipExists", intentC.getBooleanExtra("zipExists", false));
                        intent.putExtra("behavior", intentC.getIntExtra("behavior", -1));
                    } else if (intExtra == 5) {
                        intent.putExtra(BwbxUtil.ACTION_SIZE, intentC.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                        intent.putExtra("dlsZipType", intentC.getIntExtra("dlsZipType", 1));
                        booleanExtra = intentC.getBooleanExtra("lbresLater", false);
                        str = "lbresLater";
                    } else if (intExtra == 8) {
                        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "loading....", new Object[0]);
                        intent.putExtra(BwbxUtil.ACTION_TYPE, intentC.getIntExtra(BwbxUtil.ACTION_TYPE, 0));
                        booleanExtra = intentC.getBooleanExtra("restart", false);
                        str = "restart";
                    } else if (intExtra != 9) {
                        if (intExtra == 12) {
                            intent.putExtra("dlsZipType", intentC.getIntExtra("dlsZipType", 1));
                        }
                        intent.putExtras(intentC.getExtras());
                    } else {
                        intent.putExtra("requiredSize", intentC.getLongExtra("requiredSize", 0L));
                    }
                    intent.addFlags(536870912);
                    PromptActivityParcel.this.b(intent);
                    PromptActivityParcel.this.e = false;
                    PromptActivityParcel.this.a.finish();
                }
                com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "update....", new Object[0]);
                intent.putExtra("detail", intentC.getSerializableExtra("detail"));
                intent.putExtra("dataConnection", intentC.getBooleanExtra("dataConnection", false));
                intent.putExtra("forceUpdate", intentC.getBooleanExtra("forceUpdate", false));
                intent.putExtra(BwbxUtil.ACTION_SIZE, intentC.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                intent.putExtra("expsize", intentC.getLongExtra("expsize", 0L));
                intent.putExtra("tsize", intentC.getLongExtra("tsize", 0L));
                intent.putExtra("psize", intentC.getLongExtra("psize", 0L));
                intent.putExtra("savePath", intentC.getStringExtra("savePath"));
                intent.putExtra("isSmallPkg", intentC.getBooleanExtra("isSmallPkg", false));
                intent.putExtra("quitKillSelf", intentC.getBooleanExtra("quitKillSelf", true));
                booleanExtra = intentC.getBooleanExtra("stopOtherDl", false);
                str = "stopOtherDl";
                intent.putExtra(str, booleanExtra);
                intent.addFlags(536870912);
                PromptActivityParcel.this.b(intent);
                PromptActivityParcel.this.e = false;
                PromptActivityParcel.this.a.finish();
            }
        }
    };
    private boolean l = false;

    protected PromptActivityParcel(Parcel parcel) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        cancelCheck();
        this.a.finish();
    }

    private void a(Intent intent) {
        GameUtil.startService(intent, this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Intent intent) {
        w.a(this.a).b(intent);
    }

    private boolean b() {
        return this.a.isFinishing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent c() {
        return this.a.getIntent();
    }

    private void c(Intent intent) {
        this.a.setIntent(intent);
    }

    private Context d() {
        return this.a.getBaseContext();
    }

    private void d(Intent intent) {
        s.a(this.a, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        return this.a.getPackageName();
    }

    public boolean alreadyDownloaded(Serializable serializable) {
        l lVar = (l) serializable;
        if (lVar.expansionOnly) {
            String strComputeFileMd5 = GameUtil.getIntance().computeFileMd5(GameUtil.getIntance().getObbName(GameUtil.getIntance().getRunningVersionCode(true)));
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "alreadyDownloaded obb md5:" + strComputeFileMd5 + " nmd5:" + lVar.nmd5 + " dmd5:" + lVar.dmd5, new Object[0]);
            if (strComputeFileMd5 != null) {
                if (strComputeFileMd5.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                    return true;
                }
            }
            return false;
        }
        for (l lVar2 : an.a().b(lVar.gameLib)) {
            boolean zContains = lVar2.savePath.contains("lebian_hu_list-");
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "new path=" + lVar2.savePath, new Object[0]);
            String strC = zContains ? com.excelliance.lbsdk.main.ae.c.c(lVar2.savePath) : GameUtil.getIntance().computeFileMd5(lVar2.savePath);
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "alreadyDownloaded md5:" + strC + " nmd5:" + lVar.nmd5 + " dmd5" + lVar.dmd5, new Object[0]);
            if (strC.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                return true;
            }
        }
        return false;
    }

    public void bwbxContinue() {
        ae.a(this.a, "lebian_module_bwbxinfo", "dlresPatch");
        Intent intent = new Intent(e() + BwbxUtil.ACTION_DOWNLOAD_ALL_LATER);
        intent.putExtra("rmFirst", true);
        d(intent);
    }

    public boolean canRequestPackageInstalls() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.a.getPackageManager().canRequestPackageInstalls();
        }
        return false;
    }

    public void cancelCheck() {
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "cancelCheck: ", new Object[0]);
        ae.a((Context) this.a, "lebian_module_prompt", "cancelCheck", true);
        Intent intent = new Intent(BwbxUtil.ACTION_PLAT_DO);
        intent.setPackage(e());
        intent.setComponent(new ComponentName(this.a, BGService.class.getName()));
        intent.putExtra(BwbxUtil.ACTION_TYPE, 7);
        intent.putExtra("cancelCheck", true);
        a(intent);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void downloadComponent(Serializable serializable) {
        downloadComponent(serializable, false);
    }

    public void downloadComponent(Serializable serializable, Boolean bool) {
        l lVar = (l) serializable;
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "quitGamedownloadComponent enter flag:" + lVar.flag + ", savePath=" + lVar.savePath + ", auto=" + bool.booleanValue(), new Object[0]);
        if (lVar.savePath == null) {
            return;
        }
        an.a().a(lVar.cfgPath, lVar);
        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
        intent.setPackage(e());
        intent.setComponent(new ComponentName(this.a, BGService.class.getName()));
        intent.putExtra("detail", lVar);
        intent.putExtra("autodl", bool);
        intent.putExtra("check", false);
        a(intent);
    }

    public Resources getResources() {
        return PlatformResources.getMainResources(this.a);
    }

    public void init(Activity activity, Bundle bundle) {
        setActivity(activity);
        onCreate(bundle);
    }

    public void init(Activity activity, Handler handler, Bundle bundle) {
        setActivity(activity);
        setHandler(handler);
        onCreate(bundle);
    }

    public Boolean isForeground() {
        return Boolean.valueOf(al.b(this.a));
    }

    public Boolean isUseGooglePlayUpdate(Serializable serializable) {
        return Boolean.valueOf(serializable != null ? h.a(((l) serializable).flag, 2097152) : false);
    }

    public void loadingPackage() {
        if (this.l) {
            return;
        }
        this.l = true;
        Intent intent = new Intent("com.excelliance.lbsdk.action.BGService");
        Intent intentC = c();
        if (intentC.getBooleanExtra("copyObb", false)) {
            intent.putExtra("target", "CopyService");
            intent.putExtra("targetPath", intentC.getStringExtra("targetPath"));
            intent.putExtra("originPath", intentC.getStringExtra("originPath"));
        } else {
            intent.putExtra("target", "InstService");
            intent.putExtra("reinstall", intentC.getBooleanExtra("reinstall", false));
        }
        intent.setPackage(e());
        intent.setComponent(new ComponentName(this.a, BGService.class.getName()));
        a(intent);
        ad.a(this.a).a();
    }

    protected void onCreate(Bundle bundle) {
        Intent intent;
        GameUtil.getIntance().setContext(this.a.getApplicationContext());
        Intent intentC = c();
        ae.a(this.a, "lebian_module_prompt", "cancelCheck");
        if (8 == intentC.getIntExtra("dialogtype", 1)) {
            this.i = true;
            registerRestartBroadcast();
        }
        if (intentC.getBooleanExtra("quit", false)) {
            Bundle bundleExtra = intentC.getBundleExtra("param");
            int i = bundleExtra != null ? bundleExtra.getInt("action", 0) : -1;
            this.d = true;
            a();
            if (bundleExtra != null) {
                if (i == 0) {
                    ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                    if (entryActivityInfo == null || entryActivityInfo.launchMode != 3) {
                        return;
                    }
                    intent = new Intent("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.HOME");
                    intent.addFlags(268435456);
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            return;
                        }
                        String string = bundleExtra.getString("errCommitPath");
                        Intent intent2 = new Intent("android.intent.action.VIEW");
                        intent2.setData(Uri.parse(string));
                        b(intent2);
                        return;
                    }
                    intent = u.a(this.a, bundleExtra.getString("path"));
                }
                b(intent);
            }
        }
    }

    protected void onDestroy() {
        unregisterRestartBroadcastIfExist();
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "onDestroy1: " + this.d + ", installing=" + this.h, new Object[0]);
        if (this.h) {
            return;
        }
        l lVar = (l) c().getSerializableExtra("detail");
        if (this.d) {
            GameUtil.getIntance().killThirdProcesses();
        } else {
            if (!this.e) {
                return;
            }
            if (lVar != null && lVar.forceUpdate) {
                return;
            }
            if (c().getIntExtra("dialogtype", 1) != 0 && (this.g || al.d(this.a))) {
                this.f = false;
            }
            if (!this.f) {
                return;
            } else {
                al.a(this.a, Process.myPid());
            }
        }
        Process.killProcess(Process.myPid());
    }

    protected void onNewIntent(Intent intent) {
        c(intent);
        ae.a(this.a, "lebian_module_prompt", "cancelCheck");
    }

    protected void onPause() {
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "onPause isFinishing:" + b() + ", installing=" + this.h, new Object[0]);
        if (b() || this.h || this.i) {
            return;
        }
        this.c.removeCallbacks(this.k);
        this.c.postDelayed(this.k, 100L);
    }

    protected void onResume() {
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "onResume this=" + this.a, new Object[0]);
        if (c().getIntExtra("dialogtype", 1) == 1) {
            ae.a((Context) this.a, "lebian_module_prompt", "showUpdate", false);
        }
    }

    public void openNetworkSettings() {
        Intent intent;
        if (Build.VERSION.SDK_INT >= 10) {
            intent = new Intent("android.settings.SETTINGS");
        } else {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
            intent.setAction("android.intent.action.VIEW");
        }
        b(intent);
    }

    public void overInsPackage(boolean z, String str) {
        if (str == null) {
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "error action apkPath is null", new Object[0]);
            return;
        }
        if (!new File(str).exists()) {
            str = an.a().a(ae.b(this.a, "lebian_module_extractinfo", "next", (String) null)).savePath;
            if (str == null || !new File(str).exists()) {
                com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "error action apkPath = " + str, new Object[0]);
                return;
            }
        }
        this.h = true;
        a();
        Intent intent = new Intent("com.excelliance.open.action.startAssistActivity");
        intent.setPackage(e());
        intent.setFlags(268435456 | (z ? 32768 : 536870912));
        intent.putExtra("install", true);
        intent.putExtra("forceUpdate", z);
        intent.putExtra("apkPath", str);
        b(intent);
    }

    public void quitGame() {
        quitGame(null);
    }

    public void quitGame(Bundle bundle) {
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "quit enter", new Object[0]);
        this.g = true;
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setFlags(268468224);
        intent.setPackage(e());
        intent.putExtra("quit", true);
        intent.putExtra("dialogtype", 0);
        if (bundle == null) {
            bundle = new Bundle();
            bundle.putInt("action", 0);
        }
        intent.putExtra("param", bundle);
        b(intent);
    }

    public void registerRestartBroadcast() {
        if (this.j != null) {
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "already register", new Object[0]);
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        this.j = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.PromptActivityParcel.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0) == 100) {
                    com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "receive progress 100", new Object[0]);
                    PromptActivityParcel.this.restartGame(null);
                }
            }
        };
        intentFilter.addAction("com.excelliance.open.action.downloadcomponent.progress");
        s.a(this.a, this.j, intentFilter);
    }

    public void restartGame(Serializable serializable) {
        l lVar;
        boolean z;
        if (serializable != null) {
            lVar = (l) serializable;
            z = lVar.isInstall;
        } else {
            lVar = null;
            z = false;
        }
        if (z && lVar != null) {
            com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "overinstall: ", new Object[0]);
            overInsPackage(lVar.forceUpdate, lVar.savePath);
            return;
        }
        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "restartGame: kill dns before restart", new Object[0]);
        GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
        GameUtil.getIntance().killThirdProcesses();
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent launchIntentForPackage = d().getPackageManager().getLaunchIntentForPackage(d().getPackageName());
        if (launchIntentForPackage != null) {
            ComponentName component = launchIntentForPackage.getComponent();
            if (component != null) {
                launchIntentForPackage = new Intent();
                launchIntentForPackage.setComponent(component);
            }
            launchIntentForPackage.setAction("android.intent.action.MAIN");
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        } else {
            if (GameUtil.getIntance().checkJJ(this.a)) {
                launchIntentForPackage = new Intent("com.excelliance.open.action.startPromptActivity");
                launchIntentForPackage.putExtra("dialogtype", 7);
            } else {
                launchIntentForPackage = new Intent("action.lebian.MAIN");
                launchIntentForPackage.addCategory("android.intent.category.DEFAULT");
            }
            launchIntentForPackage.setPackage(e());
        }
        launchIntentForPackage.addFlags(268468224);
        b(launchIntentForPackage);
        a();
    }

    public void setActivity(Activity activity) {
        this.a = activity;
    }

    public void setHandler(Handler handler) {
        this.c = handler;
        GameUtil.getIntance().setHandler(handler);
    }

    public void switchToUpdate(Serializable serializable) {
        switchToUpdate(serializable, true, false);
    }

    public void switchToUpdate(Serializable serializable, boolean z) {
        switchToUpdate(serializable, z, !z);
    }

    /* JADX WARN: Type inference failed for: r11v6, types: [com.excelliance.lbsdk.main.PromptActivityParcel$3] */
    public void switchToUpdate(Serializable serializable, final boolean z, final boolean z2) {
        long length;
        final l lVar = (l) serializable;
        if (h.a(lVar.flag, 2097152)) {
            GameUtil.toGP(this.a);
            return;
        }
        boolean z3 = false;
        if (lVar == null || lVar.savePath == null) {
            length = 0;
        } else {
            File parentFile = new File(lVar.savePath).getParentFile();
            parentFile.mkdirs();
            length = ((lVar.size + lVar.expanSize) + lVar.tSize) - new File(lVar.savePath).length();
            if (parentFile == null || GameUtil.getFreeSpace(parentFile) < length) {
                z3 = true;
            }
        }
        if (!z3 && lVar != null && lVar.expanSavePath != null) {
            File parentFile2 = new File(lVar.expanSavePath).getParentFile();
            parentFile2.mkdirs();
            length = ((lVar.size + lVar.expanSize) + lVar.tSize) - new File(lVar.expanSavePath).length();
            if (parentFile2 == null || GameUtil.getFreeSpace(parentFile2) < ((lVar.size + lVar.expanSize) + lVar.tSize) - new File(lVar.expanSavePath).length()) {
                z3 = true;
            }
        }
        if (!z3) {
            new Thread("PromptActivityParcel:switchToUpdate") { // from class: com.excelliance.lbsdk.main.PromptActivityParcel.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Intent intent = new Intent("com.excelliance.open.action.startNextChapter");
                    intent.setPackage(PromptActivityParcel.this.e());
                    intent.setFlags(268468224);
                    intent.putExtra("gameRunning", true);
                    intent.putExtra("detail", lVar);
                    intent.putExtra("exppatch", lVar.expanPatch);
                    intent.putExtra("rppatch", lVar.hotResPatch);
                    intent.putExtra("gameId", lVar.gameId);
                    intent.putExtra("savePath", lVar.savePath);
                    intent.putExtra("forceUpdate", lVar.forceUpdate);
                    intent.putExtra("ISINSTALL", lVar.isInstall);
                    intent.putExtra(BwbxUtil.ACTION_SIZE, lVar.size);
                    intent.putExtra("expsize", lVar.expanSize);
                    intent.putExtra("tsize", lVar.tSize);
                    intent.putExtra("patch", lVar.patch);
                    intent.putExtra("quitKillSelf", z);
                    intent.putExtra("stopOtherDl", z2);
                    intent.putExtra("dlType", "update");
                    intent.putExtra("notifyTitle", lVar.notifyTitle);
                    intent.putExtra("notifyMsg", lVar.notifyMsg);
                    intent.putExtra("extra.lbsdk.hostintent", true);
                    PromptActivityParcel.this.b(intent);
                    ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                    int i = entryActivityInfo == null ? -1 : entryActivityInfo.launchMode;
                    if (i == 2 || i == 3) {
                        String strA = n.a();
                        com.excelliance.lbsdk.main.g.b.c("PromptActivityParcel", "switchToUpdate launchMode=" + i + ",emulatorName=" + strA, new Object[0]);
                        if ("BlueStackEmulator".equals(strA)) {
                            return;
                        }
                        GameUtil.getIntance().killThirdProcesses();
                    }
                }
            }.start();
            return;
        }
        ae.a(d(), "lebian_module_dloaderror", "spaceError", 1);
        ae.a(d(), "lebian_module_dloaderror", BwbxUtil.ACTION_SIZE, lVar.size);
        ae.a(d(), "lebian_module_dloaderror", "expsize", lVar.expanSize);
        ae.a(d(), "lebian_module_dloaderror", "tsize", lVar.tSize);
        ae.a(d(), "lebian_module_dloaderror", "forceUpdate", lVar.forceUpdate);
        ae.a(d(), "lebian_module_dloaderror", "preDownload", lVar.preDownload);
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setFlags(268468224);
        intent.putExtra("dialogtype", 9);
        intent.putExtra("requiredSize", length);
        b(intent);
    }

    public void unregisterRestartBroadcastIfExist() {
        s.a(this.a, this.j);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
