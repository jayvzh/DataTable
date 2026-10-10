package com.excelliance.lbsdk.main.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.excelliance.lbsdk.library.DialogClickCallback;
import com.excelliance.lbsdk.library.PromptActivityInterface;
import com.excelliance.lbsdk.library.PromptDialogListener;
import com.excelliance.lbsdk.main.BGService;
import com.excelliance.lbsdk.main.GameUtil;
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
import com.excelliance.lbsdk.main.u;
import java.io.File;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class PromptActivityImpl extends a implements PromptActivityInterface {
    public static final String TAG = "PromptActivityImpl";
    private PromptDialogListener a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0);
            boolean booleanExtra = intent.getBooleanExtra("rponly", false);
            if (intExtra == 100) {
                b.c(PromptActivityImpl.TAG, "receive progress 100", new Object[0]);
                boolean zA = m.a(context, "RESTART_NEW_RAW", false);
                if (!booleanExtra || zA) {
                    PromptActivityImpl.this.a((l) null);
                } else {
                    GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
                    PromptActivityImpl.this.i();
                }
            }
        }
    };
    private final Runnable j = new Runnable() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.12
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public void run() {
            boolean booleanExtra;
            String str;
            b.c(PromptActivityImpl.TAG, "mOnPauseCallBack enter", new Object[0]);
            if (al.c(PromptActivityImpl.this.f())) {
                return;
            }
            if (al.b(PromptActivityImpl.this.f())) {
                String topActivity = GameUtil.getTopActivity(PromptActivityImpl.this.f());
                if (TextUtils.isEmpty(topActivity)) {
                    return;
                }
                if (topActivity != null && topActivity.contains("GrantPermissionsActivity")) {
                    return;
                }
                Intent intentE = PromptActivityImpl.this.e();
                Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
                intent.setPackage(PromptActivityImpl.this.d());
                int iK = PromptActivityImpl.this.k();
                intent.putExtra("dialogtype", iK);
                b.c(PromptActivityImpl.TAG, "dialogtype=" + iK, new Object[0]);
                if (iK != 1) {
                    if (iK == 2) {
                        b.c(PromptActivityImpl.TAG, "downloaded....", new Object[0]);
                        l lVar = (l) intentE.getSerializableExtra("detail");
                        intent.putExtra("apkPath", lVar.savePath);
                        intent.putExtra("isInstall", lVar.isInstall);
                        intent.putExtra("forceUpdate", lVar.forceUpdate);
                        intent.putExtra("detail", lVar);
                    } else if (iK == 3) {
                        b.c(PromptActivityImpl.TAG, "install....", new Object[0]);
                        intent.putExtra("apkPath", intentE.getStringExtra("apkPath"));
                        intent.putExtra("forceUpdate", intentE.getBooleanExtra("forceUpdate", false));
                    } else if (iK == 4) {
                        intent.putExtra(BwbxUtil.ACTION_SIZE, intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                        intent.putExtra("dataConnection", intentE.getBooleanExtra("dataConnection", false));
                        intent.putExtra("zipExists", intentE.getBooleanExtra("zipExists", false));
                        intent.putExtra("behavior", intentE.getIntExtra("behavior", -1));
                    } else if (iK == 5) {
                        intent.putExtra(BwbxUtil.ACTION_SIZE, intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                        intent.putExtra("dlsZipType", intentE.getIntExtra("dlsZipType", 1));
                        booleanExtra = intentE.getBooleanExtra("lbresLater", false);
                        str = "lbresLater";
                    } else if (iK == 8) {
                        b.c(PromptActivityImpl.TAG, "loading....", new Object[0]);
                        intent.putExtra(BwbxUtil.ACTION_TYPE, intentE.getIntExtra(BwbxUtil.ACTION_TYPE, 0));
                        booleanExtra = intentE.getBooleanExtra("restart", false);
                        str = "restart";
                    } else if (iK != 9) {
                        if (iK == 12) {
                            intent.putExtra("dlsZipType", intentE.getIntExtra("dlsZipType", 1));
                        }
                        intent.putExtras(intentE.getExtras());
                    } else {
                        intent.putExtra("requiredSize", intentE.getLongExtra("requiredSize", 0L));
                    }
                    intent.addFlags(536870912);
                    PromptActivityImpl.this.c(intent);
                    PromptActivityImpl.this.e = false;
                } else {
                    b.c(PromptActivityImpl.TAG, "update....", new Object[0]);
                    intent.putExtra("detail", intentE.getSerializableExtra("detail"));
                    intent.putExtra("dataConnection", intentE.getBooleanExtra("dataConnection", false));
                    intent.putExtra("forceUpdate", intentE.getBooleanExtra("forceUpdate", false));
                    intent.putExtra(BwbxUtil.ACTION_SIZE, intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                    intent.putExtra("expsize", intentE.getLongExtra("expsize", 0L));
                    intent.putExtra("tsize", intentE.getLongExtra("tsize", 0L));
                    intent.putExtra("psize", intentE.getLongExtra("psize", 0L));
                    intent.putExtra("savePath", intentE.getStringExtra("savePath"));
                    intent.putExtra("isSmallPkg", intentE.getBooleanExtra("isSmallPkg", false));
                    intent.putExtra("quitKillSelf", intentE.getBooleanExtra("quitKillSelf", true));
                    booleanExtra = intentE.getBooleanExtra("stopOtherDl", false);
                    str = "stopOtherDl";
                }
                intent.putExtra(str, booleanExtra);
                intent.addFlags(536870912);
                PromptActivityImpl.this.c(intent);
                PromptActivityImpl.this.e = false;
            } else {
                ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                int i = entryActivityInfo != null ? entryActivityInfo.launchMode : -1;
                if (i != 2 && i != 3) {
                    return;
                }
                b.c(PromptActivityImpl.TAG, "launchMode=" + i, new Object[0]);
                GameUtil.getIntance().killGameProcesses(PromptActivityImpl.this.f());
                PromptActivityImpl.this.e = true;
            }
            PromptActivityImpl.this.i();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        Intent intent = new Intent(d() + BwbxUtil.ACTION_USER_CLICK);
        intent.setPackage(d());
        intent.putExtra("dtype", i);
        intent.putExtra("ctype", i2);
        a(intent, (String) null);
    }

    private void a(int i, String str, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("mergeErrType", i);
        bundle.putString("errContent", str);
        bundle.putInt("mainChId", i2);
        this.a.onShowDialog(206, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.2
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i3, Bundle bundle2, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i3, Bundle bundle2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i3, Bundle bundle2) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
            }
        });
    }

    private void a(long j, final int i, final boolean z, final Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putLong(BwbxUtil.ACTION_SIZE, j);
        bundle2.putInt("zipType", i);
        bundle2.putBoolean("lbresLater", z);
        this.a.onShowDialog(205, bundle2, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.18
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle3, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle3) {
                if (m.a(PromptActivityImpl.this.f(), "CHOOSE_BY_USER_BWBX_FORCE", false)) {
                    return;
                }
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                if (!z && m.a(PromptActivityImpl.this.f(), "ENABLE_EXIT_BUTTON", false)) {
                    PromptActivityImpl.this.quitGame();
                }
                PromptActivityImpl.this.a(BwbxUtil.DIALOG_TYPE_OLD_USER_FIRST, 2);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle3) throws Throwable {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                PromptActivityImpl.this.a(BwbxUtil.DIALOG_TYPE_OLD_USER_FIRST, 1);
                if (!z) {
                    GameUtil.switchToDownloadFullRes(PromptActivityImpl.this.f(), i, bundle);
                    return;
                }
                PromptActivityImpl.this.a(new Intent(PromptActivityImpl.this.d() + BwbxUtil.ACTION_DOWNLOAD_FULL_RES_IN_MOBILE), (String) null);
            }
        });
    }

    private void a(long j, boolean z, final int i, final boolean z2, final Bundle bundle) {
        b.c(TAG, "showChooseResPatch ENTER", new Object[0]);
        Bundle bundle2 = new Bundle();
        bundle2.putLong(BwbxUtil.ACTION_SIZE, j);
        bundle2.putBoolean("dataConnection", z);
        bundle2.putBoolean("forceDl", z2);
        this.a.onShowDialog(212, bundle2, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.4
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle3, boolean z3) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle3) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                if (!z2) {
                    PromptActivityImpl.this.bwbxContinue();
                } else if (m.a(PromptActivityImpl.this.f(), "ENABLE_EXIT_BUTTON", false)) {
                    PromptActivityImpl.this.quitGame();
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle3) throws Throwable {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                GameUtil.switchToDownloadFullRes(PromptActivityImpl.this.f(), i, bundle);
            }
        });
    }

    private void a(long j, final boolean z, boolean z2, int i) {
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        bundle.putBoolean("dataConnection", z2);
        bundle.putInt("behavior", i);
        this.a.onShowDialog(204, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.17
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i2, Bundle bundle2, boolean z3) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i2, Bundle bundle2) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                PromptActivityImpl.this.a(new Intent(PromptActivityImpl.this.d() + BwbxUtil.ACTION_DOWNLOAD_ALL_LATER), (String) null);
                PromptActivityImpl.this.a(BwbxUtil.DIALOG_TYPE_FIRST, 2);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i2, Bundle bundle2) throws Throwable {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                if (z) {
                    GameUtil.switchToDownloadFullRes(PromptActivityImpl.this.f());
                } else {
                    PromptActivityImpl.this.a(new Intent(PromptActivityImpl.this.d() + BwbxUtil.ACTION_DOWNLOAD_ALL), (String) null);
                }
                PromptActivityImpl.this.a(BwbxUtil.DIALOG_TYPE_FIRST, 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(l lVar) {
        if ((lVar != null ? lVar.isInstall : false) && lVar != null) {
            b.c(TAG, "overinstall", new Object[0]);
            overInsPackage(lVar.forceUpdate, lVar.savePath);
            return;
        }
        b.c(TAG, "restartGame: kill dns before restart", new Object[0]);
        GameUtil.getIntance().killProcessBySuffix(":lebian.dns");
        GameUtil.getIntance().killThirdProcesses();
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent launchIntentForPackage = c().getPackageManager().getLaunchIntentForPackage(c().getPackageName());
        if (launchIntentForPackage != null) {
            ComponentName component = launchIntentForPackage.getComponent();
            if (component != null) {
                launchIntentForPackage = new Intent();
                launchIntentForPackage.setComponent(component);
            }
            launchIntentForPackage.setAction("android.intent.action.MAIN");
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        } else {
            if (GameUtil.getIntance().checkJJ(f())) {
                launchIntentForPackage = new Intent("com.excelliance.open.action.startPromptActivity");
                launchIntentForPackage.putExtra("dialogtype", 7);
            } else {
                launchIntentForPackage = new Intent("action.lebian.MAIN");
                launchIntentForPackage.addCategory("android.intent.category.DEFAULT");
            }
            launchIntentForPackage.setPackage(d());
        }
        launchIntentForPackage.addFlags(268468224);
        c(launchIntentForPackage);
        i();
    }

    private void a(final l lVar, final boolean z, final boolean z2, long j, final boolean z3, final boolean z4) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isUseGooglePlayUpdate", isUseGooglePlayUpdate(lVar).booleanValue());
        bundle.putBoolean("forceUpdate", z2);
        bundle.putBoolean("dataConnection", z);
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(201, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.15
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z5) {
                ae.a(PromptActivityImpl.this.f(), "lebian_module_prompt", "gameUpdate", z5);
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) {
                boolean zAlreadyDownloaded = PromptActivityImpl.this.alreadyDownloaded(lVar);
                if (m.a(PromptActivityImpl.this.f(), "DOWNLOAD_AFTER_QUIT", true) && !zAlreadyDownloaded && !z && z2) {
                    PromptActivityImpl.this.downloadComponent(lVar, true);
                }
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                if (z2) {
                    PromptActivityImpl.this.quitGame();
                } else {
                    ae.a(PromptActivityImpl.this.f(), "lebian_module_prompt", "refusedByUser", true);
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) {
                boolean zAlreadyDownloaded = PromptActivityImpl.this.alreadyDownloaded(lVar);
                if (z2) {
                    ae.a(PromptActivityImpl.this.f(), "lebian_module_gameinfo", "selfKill", true);
                    if (zAlreadyDownloaded) {
                        PromptActivityImpl.this.a(lVar);
                    } else {
                        PromptActivityImpl.this.switchToUpdate(lVar, z3, z4);
                    }
                } else {
                    if (!zAlreadyDownloaded) {
                        PromptActivityImpl.this.downloadComponent(lVar);
                        PromptActivityImpl.this.cancelCheck();
                    }
                    PromptActivityImpl.this.i();
                }
                ae.a(PromptActivityImpl.this.f(), "lebian_module_prompt", "gameUpdate", false);
            }
        });
    }

    private void a(String str) {
        b.c(TAG, "showSecIdErrDialog enter", new Object[0]);
        Bundle bundle = new Bundle();
        bundle.putString("errMsg", str);
        this.a.onShowDialog(213, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.8
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
            }
        });
    }

    private void a(final String str, final boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("haveInstallPermission", canRequestPackageInstalls());
        bundle.putBoolean("forceUpdate", z);
        this.a.onShowDialog(203, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.13
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) {
                b.c(PromptActivityImpl.TAG, "neg forceUpdate=" + z + " apkPath:" + str, new Object[0]);
                if (z) {
                    PromptActivityImpl.this.quitGame();
                } else {
                    PromptActivityImpl.this.cancelCheck();
                    PromptActivityImpl.this.i();
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.overInsPackage(z, str);
                b.c(PromptActivityImpl.TAG, "positive forceUpdate=" + z + " apkPath:" + str, new Object[0]);
            }
        });
    }

    private void a(final boolean z, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong(BwbxUtil.ACTION_SIZE, j);
        this.a.onShowDialog(209, bundle, new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.16
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle2, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle2) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                if (z) {
                    PromptActivityImpl.this.quitGame();
                }
            }
        });
    }

    private void b(final l lVar) {
        this.a.onShowDialog(202, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.14
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
                PromptActivityImpl.this.a(lVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int k() {
        return e().getIntExtra("dialogtype", 1);
    }

    private void l() {
        b.c(TAG, "showRestartDialogForJJ enter", new Object[0]);
        this.a.onShowDialog(207, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.3
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                PromptActivityImpl.this.quitGame();
            }
        });
    }

    private void m() {
        b.c(TAG, "showLoadingDialog enter", new Object[0]);
        this.a.onShowDialog(208, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.5
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
            }
        });
    }

    private void n() {
        b.c(TAG, "showTargetSdkVersionErrDialog enter", new Object[0]);
        this.a.onShowDialog(214, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.9
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
            }
        });
    }

    private void o() {
        this.a.onShowDialog(215, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.10
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                PromptActivityImpl.this.quitGame();
            }
        });
    }

    private void p() {
        b.c(TAG, "showDownloadSuccessToast enter", new Object[0]);
        this.a.onToast(216, new Bundle());
    }

    public boolean alreadyDownloaded(l lVar) {
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
        for (l lVar2 : an.a().b(lVar.gameLib)) {
            boolean zContains = lVar2.savePath.contains("lebian_hu_list-");
            b.c(TAG, "new path=" + lVar2.savePath, new Object[0]);
            String strC = zContains ? c.c(lVar2.savePath) : GameUtil.getIntance().computeFileMd5(lVar2.savePath);
            b.c(TAG, "alreadyDownloaded md5:" + strC + " nmd5:" + lVar.nmd5 + " dmd5" + lVar.dmd5, new Object[0]);
            if (strC.equals(lVar.patch > 0 ? lVar.nmd5 : lVar.dmd5)) {
                return true;
            }
        }
        return false;
    }

    public void bwbxContinue() {
        ae.a(f(), "lebian_module_bwbxinfo", "dlresPatch");
        Intent intent = new Intent(d() + BwbxUtil.ACTION_DOWNLOAD_ALL_LATER);
        intent.putExtra("rmFirst", true);
        a(intent, (String) null);
    }

    @Override // com.excelliance.lbsdk.main.ui.a
    public boolean canRequestPackageInstalls() {
        if (Build.VERSION.SDK_INT >= 26) {
            return f().getPackageManager().canRequestPackageInstalls();
        }
        return false;
    }

    public void cancelCheck() {
        b.c(TAG, "cancelCheck", new Object[0]);
        ae.a(f(), "lebian_module_prompt", "cancelCheck", true);
        Intent intent = new Intent(BwbxUtil.ACTION_PLAT_DO);
        intent.setPackage(d());
        intent.setComponent(new ComponentName(f(), BGService.class.getName()));
        intent.putExtra(BwbxUtil.ACTION_TYPE, 7);
        intent.putExtra("cancelCheck", true);
        b(intent);
    }

    public void downloadComponent(Serializable serializable) {
        downloadComponent(serializable, false);
    }

    public void downloadComponent(Serializable serializable, Boolean bool) {
        l lVar = (l) serializable;
        b.c(TAG, "quit Game downloadComponent enter flag:" + lVar.flag + ", savePath=" + lVar.savePath + ", auto=" + bool.booleanValue(), new Object[0]);
        if (lVar.savePath == null) {
            return;
        }
        an.a().a(lVar.cfgPath, lVar);
        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
        intent.setPackage(d());
        intent.setComponent(new ComponentName(f(), BGService.class.getName()));
        intent.putExtra("detail", lVar);
        intent.putExtra("autodl", bool);
        intent.putExtra("check", false);
        b(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void init(PromptDialogListener promptDialogListener, Handler handler, Intent intent) {
        if (promptDialogListener instanceof Activity) {
            a((Activity) promptDialogListener);
            a((Context) promptDialogListener);
        }
        this.a = promptDialogListener;
        a(handler);
        a(intent);
        GameUtil.getIntance().setContext(f());
    }

    public Boolean isUseGooglePlayUpdate(l lVar) {
        return Boolean.valueOf(lVar != null ? h.a(lVar.flag, 2097152) : false);
    }

    public void loadingPackage() {
        if (this.h) {
            return;
        }
        this.h = true;
        Intent intent = new Intent("com.excelliance.lbsdk.action.BGService");
        Intent intentE = e();
        if (intentE.getBooleanExtra("copyObb", false)) {
            intent.putExtra("target", "CopyService");
            intent.putExtra("targetPath", intentE.getStringExtra("targetPath"));
            intent.putExtra("originPath", intentE.getStringExtra("originPath"));
        } else {
            intent.putExtra("target", "InstService");
            intent.putExtra("reinstall", intentE.getBooleanExtra("reinstall", false));
        }
        intent.setPackage(d());
        intent.setComponent(new ComponentName(f(), BGService.class.getName()));
        b(intent);
        ad.a(f()).a();
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onCreate(Bundle bundle) {
        Intent intent;
        GameUtil.getIntance().setContext(b());
        ae.a(f(), "lebian_module_prompt", "cancelCheck");
        if (8 == k()) {
            this.b = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.excelliance.open.action.downloadcomponent.progress");
            a(this.i, intentFilter);
        }
        if (e().getBooleanExtra("quit", false)) {
            Bundle bundleExtra = e().getBundleExtra("param");
            int i = bundleExtra != null ? bundleExtra.getInt("action", 0) : -1;
            this.c = true;
            i();
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
                        c(intent2);
                        return;
                    }
                    intent = u.a(f(), bundleExtra.getString("path"));
                }
                c(intent);
            }
        }
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onDestroy() {
        a(this.i);
        b.c(TAG, "onDestroy: " + this.c + ", installing=" + this.d, new Object[0]);
        if (this.d) {
            return;
        }
        l lVar = (l) e().getSerializableExtra("detail");
        if (this.c) {
            GameUtil.getIntance().killThirdProcesses();
        } else {
            if (!this.e) {
                return;
            }
            if (lVar != null && lVar.forceUpdate) {
                return;
            }
            if (k() != 0 && (this.f || al.d(f()))) {
                this.g = false;
            }
            if (!this.g) {
                return;
            } else {
                al.a(f(), Process.myPid());
            }
        }
        Process.killProcess(Process.myPid());
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onNewIntent(Intent intent) {
        a(intent);
        ae.a(f(), "lebian_module_prompt", "cancelCheck");
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onPause() {
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onResume() {
        Intent intentE = e();
        int iK = k();
        l lVar = (l) intentE.getSerializableExtra("detail");
        b.c(TAG, "onResume type:" + iK + "this=" + g(), new Object[0]);
        if (iK == 100) {
            p();
            return;
        }
        switch (iK) {
            case 1:
                ae.a(f(), "lebian_module_prompt", "showUpdate", false);
                boolean booleanExtra = intentE.getBooleanExtra("dataConnection", false);
                boolean booleanExtra2 = intentE.getBooleanExtra("forceUpdate", false);
                long longExtra = intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L);
                long longExtra2 = intentE.getLongExtra("expsize", 0L);
                long longExtra3 = intentE.getLongExtra("tsize", 0L);
                long longExtra4 = intentE.getLongExtra("psize", 0L);
                if (intentE.getStringExtra("savePath") != null) {
                    if (alreadyDownloaded(lVar)) {
                        a(lVar);
                        return;
                    } else {
                        a(lVar, booleanExtra, booleanExtra2, longExtra + longExtra2 + longExtra3 + longExtra4, intentE.getBooleanExtra("quitKillSelf", true), intentE.getBooleanExtra("stopOtherDl", false));
                        return;
                    }
                }
                if (!booleanExtra2 || longExtra <= 0) {
                    b.c(TAG, "space not enough in unforce", new Object[0]);
                    i();
                    return;
                } else {
                    b.c(TAG, "space not enough", new Object[0]);
                    a(booleanExtra2, longExtra + longExtra2 + longExtra3 + longExtra4);
                    return;
                }
            case 2:
                if (!intentE.getBooleanExtra("isInstall", false)) {
                    b(lVar);
                    return;
                }
                break;
            case 3:
                b.c(TAG, "show install dialog...", new Object[0]);
                break;
            case 4:
                boolean booleanExtra3 = intentE.getBooleanExtra("zipExists", false);
                boolean booleanExtra4 = intentE.getBooleanExtra("dataConnection", false);
                int intExtra = intentE.getIntExtra("behavior", -1);
                b.c(TAG, "zipExists:" + booleanExtra3 + " dataConnection:" + booleanExtra4 + "behavior:" + intExtra, new Object[0]);
                a(intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L), booleanExtra3, booleanExtra4, intExtra);
                return;
            case 5:
                a(intentE.getLongExtra(BwbxUtil.ACTION_SIZE, 0L), intentE.getIntExtra("dlsZipType", 1), intentE.getBooleanExtra("lbresLater", false), intentE.getExtras());
                return;
            case 6:
                a(intentE.getIntExtra("mergeErrType", 0), intentE.getStringExtra("errContent"), intentE.getIntExtra("MainChId", 0));
                return;
            case 7:
                l();
                return;
            case 8:
                if (intentE.getIntExtra(BwbxUtil.ACTION_TYPE, 0) != 1) {
                    loadingPackage();
                }
                if (this.h) {
                    return;
                }
                this.h = true;
                m();
                return;
            case GameUtil.NOTIFY_TYPE_SPEED_REPORT /* 9 */:
                a(true, intentE.getLongExtra("requiredSize", 0L));
                return;
            case GameUtil.NOTIFY_TYPE_BWBX_USE_BK_CDN /* 10 */:
                if (al.b(f())) {
                    showNotNetworkDialog();
                    return;
                }
                return;
            case GameUtil.NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR /* 11 */:
                showDloadFromWebsite(intentE.getStringExtra("dealpath"), intentE.getBooleanExtra("forceUpdate", true));
                return;
            case GameUtil.NOTIFY_TYPE_AB_TEST /* 12 */:
                int intExtra2 = intentE.getIntExtra("dlsZipType", 1);
                Bundle extras = intentE.getExtras();
                long jB = com.excelliance.lbsdk.main.z.c.b(extras);
                b.c(TAG, "patchSize:" + jB, new Object[0]);
                a(jB, extras.getBoolean("dataConnect", false), intExtra2, extras.getBoolean("forceDl", false), extras);
                return;
            case 13:
                a(intentE.getStringExtra("errMsg"));
                return;
            case 14:
                n();
                return;
            case 15:
                a((l) null);
                return;
            case GameUtil.F_NOTIFY_TYPE_BWBX_TRANS_MISSING /* 16 */:
                o();
                return;
            default:
                return;
        }
        a(intentE.getStringExtra("apkPath"), intentE.getBooleanExtra("forceUpdate", false));
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void onStop() {
        b.c(TAG, "onStop isFinishing:" + j() + ", installing=" + this.d, new Object[0]);
        if (j() || this.d || this.b) {
            return;
        }
        h().removeCallbacks(this.j);
        h().postDelayed(this.j, 100L);
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
        c(intent);
    }

    public void overInsPackage(boolean z, String str) {
        if (str == null) {
            b.c(TAG, "error action apkPath is null", new Object[0]);
            return;
        }
        if (!new File(str).exists()) {
            str = an.a().a(ae.b(f(), "lebian_module_extractinfo", "next", (String) null)).savePath;
            if (str == null || !new File(str).exists()) {
                b.c(TAG, "error action apkPath = " + str, new Object[0]);
                return;
            }
        }
        this.d = true;
        i();
        Intent intent = new Intent("com.excelliance.open.action.startAssistActivity");
        intent.setPackage(d());
        intent.setFlags(268435456 | (z ? 32768 : 536870912));
        intent.putExtra("install", true);
        intent.putExtra("forceUpdate", z);
        intent.putExtra("apkPath", str);
        c(intent);
    }

    @Override // com.excelliance.lbsdk.library.PromptActivityInterface
    public void prepareViewFinished() {
    }

    public void quitGame() {
        quitGame(null);
    }

    public void quitGame(Bundle bundle) {
        b.c(TAG, "quit enter", new Object[0]);
        this.f = true;
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setFlags(268468224);
        intent.setPackage(d());
        intent.putExtra("quit", true);
        intent.putExtra("dialogtype", 0);
        if (bundle == null) {
            bundle = new Bundle();
            bundle.putInt("action", 0);
        }
        intent.putExtra("param", bundle);
        c(intent);
    }

    public void showDloadFromWebsite(final String str, final boolean z) {
        this.a.onShowDialog(211, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.7
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z2) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
                if (!z || !m.a(PromptActivityImpl.this.f(), "ENABLE_EXIT_BUTTON", false)) {
                    PromptActivityImpl.this.cancelCheck();
                    PromptActivityImpl.this.i();
                } else {
                    PromptActivityImpl.this.cancelCheck();
                    PromptActivityImpl.this.i();
                    PromptActivityImpl.this.quitGame();
                }
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                PromptActivityImpl.this.c(intent);
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
            }
        });
    }

    public void showNotNetworkDialog() {
        this.a.onShowDialog(210, new Bundle(), new DialogClickCallback() { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.6
            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void dismiss() {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onCheckBox(int i, Bundle bundle, boolean z) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onNegativeClick(int i, Bundle bundle) {
            }

            @Override // com.excelliance.lbsdk.library.DialogClickCallback
            public void onPositiveClick(int i, Bundle bundle) {
                PromptActivityImpl.this.cancelCheck();
                PromptActivityImpl.this.i();
                PromptActivityImpl.this.openNetworkSettings();
            }
        });
    }

    public void switchToUpdate(Serializable serializable) {
        switchToUpdate(serializable, true, false);
    }

    public void switchToUpdate(Serializable serializable, boolean z) {
        switchToUpdate(serializable, z, !z);
    }

    /* JADX WARN: Type inference failed for: r11v7, types: [com.excelliance.lbsdk.main.ui.PromptActivityImpl$1] */
    public void switchToUpdate(Serializable serializable, final boolean z, final boolean z2) {
        long length;
        final l lVar = (l) serializable;
        if (h.a(lVar.flag, 2097152)) {
            GameUtil.toGP(f());
            return;
        }
        boolean z3 = false;
        if (lVar == null || lVar.savePath == null) {
            length = 0;
        } else {
            File parentFile = new File(lVar.savePath).getParentFile();
            parentFile.mkdirs();
            length = (((lVar.size + lVar.expanSize) + lVar.tSize) + lVar.hotResDSize) - new File(lVar.savePath).length();
            if (parentFile == null || GameUtil.getFreeSpace(parentFile) < length) {
                z3 = true;
            }
        }
        if (!z3 && lVar != null && lVar.expanSavePath != null) {
            File parentFile2 = new File(lVar.expanSavePath).getParentFile();
            parentFile2.mkdirs();
            length = (((lVar.size + lVar.expanSize) + lVar.tSize) + lVar.hotResDSize) - new File(lVar.expanSavePath).length();
            if (parentFile2 == null || GameUtil.getFreeSpace(parentFile2) < ((lVar.size + lVar.expanSize) + lVar.tSize) - new File(lVar.expanSavePath).length()) {
                z3 = true;
            }
        }
        if (!z3 && lVar != null && lVar.hotResSavePath != null) {
            File parentFile3 = new File(lVar.hotResSavePath).getParentFile();
            parentFile3.mkdirs();
            length = (((lVar.size + lVar.expanSize) + lVar.tSize) + lVar.hotResDSize) - new File(lVar.hotResSavePath).length();
            if (parentFile3 == null || GameUtil.getFreeSpace(parentFile3) < (((lVar.size + lVar.expanSize) + lVar.tSize) + lVar.hotResDSize) - new File(lVar.hotResSavePath).length()) {
                z3 = true;
            }
        }
        if (!z3) {
            new Thread("PromptActivityParcel:switchToUpdate") { // from class: com.excelliance.lbsdk.main.ui.PromptActivityImpl.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Intent intent = new Intent("com.excelliance.open.action.startNextChapter");
                    intent.setPackage(PromptActivityImpl.this.d());
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
                    intent.putExtra("psize", lVar.hotResDSize);
                    intent.putExtra("patch", lVar.patch);
                    intent.putExtra("quitKillSelf", z);
                    intent.putExtra("stopOtherDl", z2);
                    intent.putExtra("dlType", "update");
                    intent.putExtra("notifyTitle", lVar.notifyTitle);
                    intent.putExtra("notifyMsg", lVar.notifyMsg);
                    intent.putExtra("extra.lbsdk.hostintent", true);
                    PromptActivityImpl.this.c(intent);
                    ActivityInfo entryActivityInfo = GameUtil.getIntance().getEntryActivityInfo();
                    int i = entryActivityInfo == null ? -1 : entryActivityInfo.launchMode;
                    if (i == 2 || i == 3) {
                        String strA = n.a();
                        b.c(PromptActivityImpl.TAG, "switchToUpdate launchMode=" + i + ",emulatorName=" + strA, new Object[0]);
                        if ("BlueStackEmulator".equals(strA)) {
                            return;
                        }
                        GameUtil.getIntance().killThirdProcesses();
                    }
                }
            }.start();
            return;
        }
        ae.a(c(), "lebian_module_dloaderror", "spaceError", 1);
        ae.a(c(), "lebian_module_dloaderror", BwbxUtil.ACTION_SIZE, lVar.size);
        ae.a(c(), "lebian_module_dloaderror", "expsize", lVar.expanSize);
        ae.a(c(), "lebian_module_dloaderror", "tsize", lVar.tSize);
        ae.a(c(), "lebian_module_dloaderror", "psize", lVar.hotResDSize);
        ae.a(c(), "lebian_module_dloaderror", "forceUpdate", lVar.forceUpdate);
        ae.a(c(), "lebian_module_dloaderror", "preDownload", lVar.preDownload);
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setFlags(268468224);
        intent.putExtra("dialogtype", 9);
        intent.putExtra("requiredSize", length);
        c(intent);
    }
}
