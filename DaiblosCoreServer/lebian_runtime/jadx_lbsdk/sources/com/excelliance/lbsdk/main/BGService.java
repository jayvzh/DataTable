package com.excelliance.lbsdk.main;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BGService extends Service implements com.excelliance.lbsdk.main.c {
    public static int a = 8;
    private static int e = 4;
    private static int f = 5;
    private static int g = 6;
    private static int h = 7;
    private static int o;
    private static Intent z;
    private Context i;
    private Looper j;
    private d k;
    private ConnectivityManager l;
    private a m;
    private h q;
    private ag r;
    private boolean t;
    private Timer y;
    private static Set<String> p = new HashSet();
    public static int b = 1;
    private static boolean A = false;
    private boolean c = false;
    private boolean d = false;
    private boolean n = false;
    private IBinder s = new b();
    private int u = -1;
    private int v = 0;
    private final Runnable w = new Runnable() { // from class: com.excelliance.lbsdk.main.BGService.1
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
            if (al.b(BGService.this.i) && !GameUtil.getIntance().isScreenLocked()) {
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("BGService", "isTopActivity", new Object[0]);
                }
                BGService.this.k.removeCallbacks(BGService.this.w);
                BGService.this.k.postDelayed(BGService.this.w, 2000L);
                return;
            }
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("BGService", "is not TopActivity", new Object[0]);
            }
            BGService bGService = BGService.this;
            bGService.b(bGService, 9);
            GameUtil.getIntance().cleanExit();
        }
    };
    private Handler x = new Handler();

    private class a extends BroadcastReceiver {
        final /* synthetic */ BGService a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.e("BGService", "DataStateReceiver intent=" + intent.getAction(), new Object[0]);
            }
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                synchronized (this.a.l) {
                    if (aa.b(context)) {
                        this.a.l.notifyAll();
                        this.a.n = false;
                    }
                }
            }
        }
    }

    public class b extends p.a {
        public b() {
        }

        @Override // com.excelliance.lbsdk.main.p
        public void a() {
        }
    }

    class c extends TimerTask {
        c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (BGService.z == null) {
                return;
            }
            int intExtra = BGService.z.getIntExtra("dialogtype", 1);
            boolean z = intExtra == 1;
            boolean booleanExtra = BGService.z.getBooleanExtra("ignoreOwnActivity", false);
            boolean zB = ae.b(BGService.this.i, "lebian_module_prompt", "showUpdate", false);
            com.excelliance.lbsdk.main.g.b.c("BGService", "finished = " + BGService.A + ", ignoreOwnActivity = " + booleanExtra + " showUpdate = " + zB + " dialogType = " + intExtra, new Object[0]);
            if (BGService.A || ((al.d(BGService.this.i) && !booleanExtra) || (!z && zB))) {
                if (BGService.this.y != null) {
                    BGService bGService = BGService.this;
                    bGService.b(bGService.y);
                    BGService.this.y.cancel();
                    BGService.this.y = null;
                    return;
                }
                return;
            }
            if ((al.b(BGService.this.i) || GameUtil.getIntance().remoteRunningPid() <= 0) && BGService.z != null) {
                boolean unused = BGService.A = true;
                if (BGService.this.y != null) {
                    BGService bGService2 = BGService.this;
                    bGService2.b(bGService2.y);
                    BGService.this.y.cancel();
                    BGService.this.y = null;
                }
                BGService.this.x.postDelayed(new Runnable() { // from class: com.excelliance.lbsdk.main.BGService.c.1
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
                        Intent intent;
                        boolean booleanExtra2;
                        Intent intent2 = new Intent("com.excelliance.open.action.startPromptActivity");
                        intent2.setPackage(BGService.this.getPackageName());
                        intent2.setFlags(805306368);
                        int intExtra2 = BGService.z.getIntExtra("dialogtype", 1);
                        String str = "forceUpdate";
                        if (intExtra2 != 1) {
                            if (intExtra2 == 2) {
                                l lVar = (l) BGService.z.getSerializableExtra("detail");
                                intent2.putExtra("apkPath", lVar.savePath);
                                intent2.putExtra("isInstall", lVar.isInstall);
                                intent2.putExtra("forceUpdate", lVar.forceUpdate);
                                intent2.putExtra("detail", lVar);
                            } else if (intExtra2 == 4) {
                                intent2.putExtra(BwbxUtil.ACTION_SIZE, BGService.z.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                                intent2.putExtra("zipExists", BGService.z.getBooleanExtra("zipExists", false));
                                intent2.putExtra("dataConnection", BGService.z.getBooleanExtra("dataConnection", false));
                                intent2.putExtra("behavior", BGService.z.getIntExtra("behavior", -1));
                            } else if (intExtra2 != 5) {
                                if (intExtra2 == 9) {
                                    intent2.putExtra("requiredSize", BGService.z.getLongExtra("requiredSize", 0L));
                                } else if (intExtra2 != 11) {
                                    if (intExtra2 != 12) {
                                        intent2.putExtras(BGService.z.getExtras());
                                    } else {
                                        intent2.putExtras(BGService.z.getExtras());
                                        intent2.putExtra("dlsZipType", BGService.z.getIntExtra("dlsZipType", 1));
                                    }
                                }
                                GameUtil intance = GameUtil.getIntance();
                                String stringExtra = BGService.z.getStringExtra("dealpath");
                                if (TextUtils.isEmpty(stringExtra)) {
                                    intent2.putExtra("dealpath", intance.getDealCommitPath());
                                } else {
                                    intent2.putExtra("dealpath", stringExtra);
                                }
                                booleanExtra2 = BGService.z.getBooleanExtra("forceUpdate", true);
                                intent2.putExtra(str, booleanExtra2);
                            } else {
                                intent2.putExtra(BwbxUtil.ACTION_SIZE, BGService.z.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                                intent2.putExtra("dlsZipType", BGService.z.getIntExtra("dlsZipType", 1));
                                intent = BGService.z;
                                str = "lbresLater";
                            }
                            intent2.putExtra("dialogtype", intExtra2);
                            BGService.this.startActivity(intent2);
                            boolean unused2 = BGService.A = false;
                            Intent intent3 = new Intent(BwbxUtil.ACTION_PLAT_DO);
                            intent3.setPackage(BGService.this.getPackageName());
                            intent3.setComponent(new ComponentName(BGService.this.getPackageName(), BGService.class.getName()));
                            intent3.putExtra("activityIntent", intent2);
                            intent3.putExtra(BwbxUtil.ACTION_TYPE, BGService.h);
                            GameUtil.startService(intent3, BGService.this.i);
                        }
                        l lVar2 = (l) BGService.z.getSerializableExtra("detail");
                        boolean booleanExtra3 = BGService.z.getBooleanExtra("dataConnection", false);
                        intent2.putExtra("detail", lVar2);
                        intent2.putExtra("dataConnection", booleanExtra3);
                        intent2.putExtra("forceUpdate", lVar2.forceUpdate);
                        intent2.putExtra(BwbxUtil.ACTION_SIZE, lVar2.size);
                        intent2.putExtra("expsize", lVar2.expanSize);
                        intent2.putExtra("tsize", lVar2.tSize);
                        intent2.putExtra("psize", lVar2.hotResDSize);
                        intent2.putExtra("savePath", lVar2.savePath);
                        intent2.putExtra("quitKillSelf", BGService.z.getBooleanExtra("quitKillSelf", true));
                        intent = BGService.z;
                        str = "stopOtherDl";
                        booleanExtra2 = intent.getBooleanExtra(str, false);
                        intent2.putExtra(str, booleanExtra2);
                        intent2.putExtra("dialogtype", intExtra2);
                        BGService.this.startActivity(intent2);
                        boolean unused3 = BGService.A = false;
                        Intent intent4 = new Intent(BwbxUtil.ACTION_PLAT_DO);
                        intent4.setPackage(BGService.this.getPackageName());
                        intent4.setComponent(new ComponentName(BGService.this.getPackageName(), BGService.class.getName()));
                        intent4.putExtra("activityIntent", intent2);
                        intent4.putExtra(BwbxUtil.ACTION_TYPE, BGService.h);
                        GameUtil.startService(intent4, BGService.this.i);
                    }
                }, 100L);
            }
        }
    }

    private final class d extends Handler {
        public d(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0081 A[PHI: r2 r14
  0x0081: PHI (r2v41 android.content.Intent) = (r2v34 android.content.Intent), (r2v44 android.content.Intent) binds: [B:30:0x0109, B:15:0x007f] A[DONT_GENERATE, DONT_INLINE]
  0x0081: PHI (r14v3 int) = (r14v0 int), (r14v4 int) binds: [B:30:0x0109, B:15:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:32:0x010d A[PHI: r12 r14
  0x010d: PHI (r12v2 java.lang.String) = (r12v1 java.lang.String), (r12v1 java.lang.String), (r12v3 java.lang.String) binds: [B:19:0x00ce, B:21:0x00da, B:6:0x004f] A[DONT_GENERATE, DONT_INLINE]
  0x010d: PHI (r14v1 int) = (r14v0 int), (r14v0 int), (r14v4 int) binds: [B:19:0x00ce, B:21:0x00da, B:6:0x004f] A[DONT_GENERATE, DONT_INLINE]] */
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
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Intent intent;
            Intent intent2;
            String str;
            int i;
            Message messageObtainMessage = message;
            switch (messageObtainMessage.what) {
                case 1:
                    BGService.this.q.a((Intent) messageObtainMessage.obj);
                    break;
                case 3:
                    if (!BGService.this.a()) {
                        com.excelliance.lbsdk.main.g.b.c("BGService", "nothing to do, quit", new Object[0]);
                        BGService.this.stopSelf();
                        BGService.this.stopForeground(true);
                        Process.killProcess(Process.myPid());
                    }
                    break;
                case 4:
                    if (!BGService.this.a()) {
                        com.excelliance.lbsdk.main.g.b.c("BGService", "send unbind req", new Object[0]);
                        s.a(BGService.this.i, new Intent(BGService.this.getPackageName() + ".action.unbindreq"));
                    }
                    break;
                case 6:
                    if (BGService.this.y == null) {
                        boolean unused = BGService.A = false;
                        BGService.this.y = new Timer();
                        BGService bGService = BGService.this;
                        bGService.a(bGService.y);
                        BGService.this.y.scheduleAtFixedRate(BGService.this.new c(), 0L, 500L);
                    }
                    break;
                case 7:
                    com.excelliance.lbsdk.main.g.b.c("BGService", "start main", new Object[0]);
                    try {
                        Intent intent3 = new Intent("android.intent.action.MAIN");
                        intent3.addCategory(GameUtil.getIntance().isHomeApp() ? "android.intent.category.HOME" : "android.intent.category.LAUNCHER");
                        intent3.setPackage(BGService.this.getPackageName());
                        intent3.addFlags(268435456);
                        BGService.this.startActivity(intent3);
                    } catch (Exception unused2) {
                        if (GameUtil.getIntance().checkJJ(BGService.this.i)) {
                            intent = new Intent("com.excelliance.open.action.startPromptActivity");
                            intent.putExtra("dialogtype", 7);
                        } else {
                            intent = new Intent("action.lebian.MAIN");
                            intent.addCategory("android.intent.category.DEFAULT");
                        }
                        intent.addFlags(268435456);
                        intent.setPackage(BGService.this.getPackageName());
                        BGService.this.startActivity(intent);
                    }
                    break;
                case 8:
                    BGService.this.r.a((Intent) messageObtainMessage.obj);
                    break;
                case GameUtil.NOTIFY_TYPE_BWBX_USE_BK_CDN /* 10 */:
                    com.excelliance.lbsdk.main.g.b.c("BGService", "recheck activity", new Object[0]);
                    intent2 = (Intent) messageObtainMessage.obj;
                    str = "cancelCheck";
                    boolean zB = ae.b(BGService.this.i, "lebian_module_prompt", "cancelCheck", false);
                    i = 10;
                    BGService.this.k.removeMessages(10);
                    if (zB || al.c(BGService.this.i)) {
                        BGService bGService2 = BGService.this;
                        bGService2.b(bGService2, i);
                        ae.a(BGService.this.i, "lebian_module_prompt", str);
                    } else {
                        if (!al.d(BGService.this.i) && al.b(BGService.this.i)) {
                            String topActivity = GameUtil.getTopActivity(BGService.this.i);
                            if (!TextUtils.isEmpty(topActivity) && !topActivity.contains("GrantPermissionsActivity")) {
                                intent2.addFlags(536870912);
                                BGService.this.startActivity(intent2);
                            }
                        }
                        BGService bGService3 = BGService.this;
                        bGService3.b(bGService3, i);
                        BGService bGService4 = BGService.this;
                        bGService4.a(bGService4, i);
                        messageObtainMessage = BGService.this.k.obtainMessage(i);
                        messageObtainMessage.arg2 = 1;
                        messageObtainMessage.obj = intent2;
                        BGService.this.k.sendMessageDelayed(messageObtainMessage, 2000L);
                    }
                    break;
                case GameUtil.NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR /* 11 */:
                    com.excelliance.lbsdk.main.g.b.c("BGService", "recheck chapter activity", new Object[0]);
                    intent2 = (Intent) messageObtainMessage.obj;
                    str = "cancelChapterCheck";
                    boolean zB2 = ae.b(BGService.this.i, "lebian_module_prompt", "cancelChapterCheck", false);
                    i = 11;
                    BGService.this.k.removeMessages(11);
                    com.excelliance.lbsdk.main.g.b.c("BGService", "isCancel : " + zB2, new Object[0]);
                    if (!zB2) {
                        if (!al.d(BGService.this.i) && al.b(BGService.this.i)) {
                            String topActivity2 = GameUtil.getTopActivity(BGService.this.i);
                            if (!TextUtils.isEmpty(topActivity2) && !topActivity2.contains("GrantPermissionsActivity")) {
                                intent2.addFlags(536870912);
                                BGService.this.startActivity(intent2);
                            }
                        }
                        BGService bGService5 = BGService.this;
                        bGService5.b(bGService5, i);
                        BGService bGService6 = BGService.this;
                        bGService6.a(bGService6, i);
                        messageObtainMessage = BGService.this.k.obtainMessage(i);
                        messageObtainMessage.arg2 = 1;
                        messageObtainMessage.obj = intent2;
                        BGService.this.k.sendMessageDelayed(messageObtainMessage, 2000L);
                    } else {
                        BGService bGService7 = BGService.this;
                        bGService7.b(bGService7, i);
                        ae.a(BGService.this.i, "lebian_module_prompt", str);
                    }
                    break;
            }
            com.excelliance.lbsdk.main.g.b.c("BGService", "handle msg finished, msg=" + messageObtainMessage.what, new Object[0]);
            if (messageObtainMessage.what == 3 || messageObtainMessage.what == 4) {
                return;
            }
            if (messageObtainMessage.arg2 == 1) {
                BGService bGService8 = BGService.this;
                bGService8.b(bGService8, messageObtainMessage.what);
            } else {
                BGService bGService9 = BGService.this;
                bGService9.b((Object) bGService9);
            }
        }
    }

    public static String a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), GameUtil.F_NOTIFY_TYPE_AB_TEST)).toString();
        } catch (Exception e2) {
            com.excelliance.lbsdk.main.g.b.c("BGService", "Exception=" + e2.toString(), new Object[0]);
            return str;
        }
    }

    private void a(int i, int i2, Intent intent) {
        a((Object) this);
        com.excelliance.lbsdk.main.g.b.c("BGService", "handleMsg msg=" + i, new Object[0]);
        Message messageObtainMessage = this.k.obtainMessage(i, intent);
        messageObtainMessage.arg1 = i2;
        messageObtainMessage.arg2 = 0;
        this.k.sendMessage(messageObtainMessage);
    }

    private void a(Context context, int i, int i2) {
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.c("BGService", "scheduleImplMsg nMsg=" + i + ", time = " + i2, new Object[0]);
        }
        if (i != 3 && i != 4) {
            a(this, i);
        }
        this.k.removeMessages(i);
        Message messageObtainMessage = this.k.obtainMessage(i);
        messageObtainMessage.arg2 = 1;
        this.k.sendMessageDelayed(messageObtainMessage, i2);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.excelliance.lbsdk.main.BGService$3] */
    private void a(final Intent intent) {
        if (this.d) {
            return;
        }
        this.d = true;
        new Thread("BGService:COPY") { // from class: com.excelliance.lbsdk.main.BGService.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                BGService.this.a(this);
                try {
                    Thread.sleep(200L);
                } catch (Exception unused) {
                }
                GameUtil.showNullViewNotification(BGService.this);
                String stringExtra = intent.getStringExtra("originPath");
                String stringExtra2 = intent.getStringExtra("targetPath");
                File file = new File(stringExtra);
                File file2 = new File(stringExtra2);
                GameUtil.getIntance().killThirdProcesses();
                try {
                    if (file.exists()) {
                        GameUtil.getIntance().copyFile(file, file2);
                        ac.a(BGService.this.i).b(stringExtra2);
                    } else {
                        com.excelliance.lbsdk.main.g.b.c("BGService", "copy obb fail: obb not exist", new Object[0]);
                    }
                    file.delete();
                } catch (Exception e2) {
                    com.excelliance.lbsdk.main.g.b.c("BGService", "run: copy obb fail " + e2, new Object[0]);
                    file2.delete();
                }
                BGService.this.stopForeground(true);
                Intent launchIntentForPackage = BGService.this.getPackageManager().getLaunchIntentForPackage(BGService.this.getPackageName());
                if (launchIntentForPackage != null) {
                    ComponentName component = launchIntentForPackage.getComponent();
                    if (component != null) {
                        launchIntentForPackage = new Intent();
                        launchIntentForPackage.setComponent(component);
                    }
                    launchIntentForPackage.setAction("android.intent.action.MAIN");
                    launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
                } else {
                    if (GameUtil.getIntance().checkJJ(BGService.this.i)) {
                        launchIntentForPackage = new Intent("com.excelliance.open.action.startPromptActivity");
                        launchIntentForPackage.putExtra("dialogtype", 7);
                    } else {
                        launchIntentForPackage = new Intent("action.lebian.MAIN");
                        launchIntentForPackage.addCategory("android.intent.category.DEFAULT");
                    }
                    launchIntentForPackage.setPackage(BGService.this.getPackageName());
                }
                launchIntentForPackage.addFlags(268468224);
                BGService.this.startActivity(launchIntentForPackage);
                BGService.this.b(this);
                BGService.this.d = false;
            }
        }.start();
    }

    @TargetApi(26)
    private void a(Bundle bundle) {
        Notification.Builder builder;
        try {
            int i = getPackageManager().getApplicationInfo(getPackageName(), 0).icon;
            if (Build.VERSION.SDK_INT >= 26) {
                String str = getPackageName() + ".DOWNLOAD_TIP_ID";
                ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel(str, getPackageName() + ".DOWNLOAD_TIP", 2));
                builder = new Notification.Builder(this, str);
            } else {
                builder = new Notification.Builder(this);
            }
            builder.setSmallIcon(i);
            if (bundle != null) {
                String string = bundle.getString("title", "");
                int i2 = bundle.getInt("show_level", 0);
                com.excelliance.lbsdk.main.g.b.c("BGService", "startForeground notification : title : " + string + " level : " + i2, new Object[0]);
                if (i2 > this.u) {
                    this.u = i2;
                    if (string != null) {
                        builder.setContentTitle(a(this.i, " "));
                        builder.setContentText(string);
                    }
                }
            }
            startForeground(97, builder.build());
        } catch (Throwable th) {
            com.excelliance.lbsdk.main.g.b.c("BGService", "invalid notification" + th, new Object[0]);
        }
    }

    private void f() {
        if (ae.b(this.i, "lebian_module_dloaderror", "preDownload", false)) {
            return;
        }
        boolean zB = ae.b(this.i, "lebian_module_dloaderror", "forceUpdate", false);
        int iB = ae.b(this.i, "lebian_module_dloaderror", "spaceError", 0);
        long jB = ae.b(this.i, "lebian_module_dloaderror", BwbxUtil.ACTION_SIZE, 0L);
        long jB2 = ae.b(this.i, "lebian_module_dloaderror", "expsize", 0L);
        long jB3 = ae.b(this.i, "lebian_module_dloaderror", "tsize", 0L);
        long jB4 = ae.b(this.i, "lebian_module_dloaderror", "psize", 0L);
        if (!zB || iB == 0 || jB + jB2 + jB3 + jB4 == 0) {
            return;
        }
        Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.setPackage(getPackageName());
        intent.setFlags(268435456);
        intent.putExtra("dialogtype", 1);
        intent.putExtra("forceUpdate", zB);
        intent.putExtra(BwbxUtil.ACTION_SIZE, jB);
        intent.putExtra("expsize", jB2);
        intent.putExtra("tsize", jB3);
        intent.putExtra("psize", jB4);
        startActivity(intent);
        Intent intent2 = new Intent(BwbxUtil.ACTION_PLAT_DO);
        intent2.setPackage(getPackageName());
        intent2.setComponent(new ComponentName(getPackageName(), BGService.class.getName()));
        intent2.putExtra("activityIntent", intent);
        intent2.putExtra(BwbxUtil.ACTION_TYPE, h);
        GameUtil.startService(intent2, this);
    }

    private void g() {
        s.a(this, this.m);
        this.m = null;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.excelliance.lbsdk.main.BGService$2] */
    private void h() {
        if (this.c) {
            return;
        }
        this.c = true;
        new Thread("BGService:INRT") { // from class: com.excelliance.lbsdk.main.BGService.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                BGService.this.a(this);
                GameUtil.showNullViewNotification(BGService.this);
                String strB = ae.b(BGService.this.i, "lebian_module_extractinfo", "next", (String) null);
                l lVarA = an.a().a(strB);
                GameUtil.getIntance().killThirdProcesses();
                if (lVarA != null && lVarA.savePath != null && new File(lVarA.savePath).exists()) {
                    an.a().a(strB, lVarA.savePath);
                }
                GameUtil.getIntance().killThirdProcesses();
                try {
                    Thread.sleep(1000L);
                } catch (Exception unused) {
                }
                BGService.this.stopForeground(true);
                Intent launchIntentForPackage = BGService.this.getPackageManager().getLaunchIntentForPackage(BGService.this.getPackageName());
                if (launchIntentForPackage != null) {
                    ComponentName component = launchIntentForPackage.getComponent();
                    if (component != null) {
                        launchIntentForPackage = new Intent();
                        launchIntentForPackage.setComponent(component);
                    }
                    launchIntentForPackage.setAction("android.intent.action.MAIN");
                    launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
                } else {
                    if (GameUtil.getIntance().checkJJ(BGService.this.i)) {
                        launchIntentForPackage = new Intent("com.excelliance.open.action.startPromptActivity");
                        launchIntentForPackage.putExtra("dialogtype", 7);
                    } else {
                        launchIntentForPackage = new Intent("action.lebian.MAIN");
                        launchIntentForPackage.addCategory("android.intent.category.DEFAULT");
                    }
                    launchIntentForPackage.setPackage(BGService.this.getPackageName());
                }
                launchIntentForPackage.addFlags(268468224);
                BGService.this.startActivity(launchIntentForPackage);
                BGService.this.b(this);
                BGService.this.c = false;
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.excelliance.lbsdk.main.BGService$4] */
    public void a(final int i) {
        new Thread("BGService:notifyAction") { // from class: com.excelliance.lbsdk.main.BGService.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
                    if (i == 5 && ae.b(BGService.this.i, "lebian_module_userinfo", "lastNotifyDate", "-1").equals(str)) {
                        return;
                    }
                    GameUtil intance = GameUtil.getIntance();
                    intance.setContext(BGService.this.i);
                    StringBuilder sb = new StringBuilder(intance.getBaseUrl("s") + "sdkstatistics.php");
                    sb.append("?action=" + i);
                    JSONObject jSONObject = new JSONObject();
                    an anVarA = an.a();
                    anVarA.a(BGService.this.i);
                    String strB = anVarA.b();
                    if (strB != null) {
                        jSONObject.put("uid", strB);
                    }
                    jSONObject.put("pkg", BGService.this.i.getPackageName());
                    jSONObject.put("chid", intance.getApkMainCh());
                    jSONObject.put("cpmeta", URLEncoder.encode(intance.getClientChId(), "UTF-8").replaceAll("\\+", "%20"));
                    int i2 = 1;
                    jSONObject.put("vercode", URLEncoder.encode(intance.getRunningVersionCode(false, true), "UTF-8").replaceAll("\\+", "%20"));
                    jSONObject.put("iv", URLEncoder.encode(String.valueOf(intance.getVerCodeFromApk(BGService.this.i.getPackageCodePath())), "UTF-8").replaceAll("\\+", "%20"));
                    if (i == 5) {
                        if (!anVarA.i()) {
                            i2 = 0;
                        }
                        jSONObject.put("is_fb", i2);
                    }
                    com.excelliance.lbsdk.main.g.b.c("BGService", "json=" + jSONObject, new Object[0]);
                    sb.append("&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
                    com.excelliance.lbsdk.main.g.b.c("BGService", "notifyAction getURL:" + sb.toString(), new Object[0]);
                    o oVarA = aa.a(sb.toString(), BGService.this.i);
                    com.excelliance.lbsdk.main.g.b.c("BGService", " APP action status:" + oVarA.a, new Object[0]);
                    if (i == 5 && oVarA.a == 200) {
                        ae.a(BGService.this.i, "lebian_module_userinfo", "lastNotifyDate", str);
                    }
                    if (anVarA.j()) {
                        BGService.this.b();
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    com.excelliance.lbsdk.main.g.b.c("BGService", "APP Action Exception:", e2, new Object[0]);
                    return;
                }
                throw th;
            }
        }.start();
    }

    @Override // com.excelliance.lbsdk.main.c
    public synchronized void a(Object obj) {
        o++;
        com.excelliance.lbsdk.main.g.b.c("BGService", "add o=" + obj.getClass().getName() + ", busyCount=" + (o + p.size()), new Object[0]);
    }

    @Override // com.excelliance.lbsdk.main.c
    public synchronized void a(Object obj, int i) {
        String str = obj.getClass().getName() + String.valueOf(i);
        p.add(str);
        com.excelliance.lbsdk.main.g.b.c("BGService", "add msg=" + str + ", busyCount=" + (o + p.size()), new Object[0]);
    }

    public boolean a() {
        return o > 0 || !p.isEmpty();
    }

    public void b() {
        StringBuilder sb = new StringBuilder("https://sdk.lbstreaming.com/api/play-game");
        JSONObject jSONObject = new JSONObject();
        an.a().a(this.i);
        GameUtil intance = GameUtil.getIntance();
        intance.setContext(this.i);
        try {
            jSONObject.put("uuid", Settings.Secure.getString(this.i.getContentResolver(), "android_id"));
            jSONObject.put("package_name", this.i.getPackageName());
            jSONObject.put("chid", intance.getApkMainCh());
            jSONObject.put("cloud_id", intance.getLBCloudId(this.i));
            aa.a(sb.toString(), jSONObject, this.i);
            com.excelliance.lbsdk.main.g.b.c("BGService", "reportGameStart : " + jSONObject, new Object[0]);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.excelliance.lbsdk.main.c
    public synchronized void b(Object obj) {
        o--;
        com.excelliance.lbsdk.main.g.b.c("BGService", "remove o=" + obj.getClass().getName() + ", busyCount=" + (o + p.size()), new Object[0]);
        if (!a()) {
            a(this, 4, 5000);
            a(this, 3, 10000);
        }
    }

    @Override // com.excelliance.lbsdk.main.c
    public synchronized void b(Object obj, int i) {
        String str = obj.getClass().getName() + String.valueOf(i);
        if (p.contains(str)) {
            p.remove(str);
            com.excelliance.lbsdk.main.g.b.c("BGService", "remove msg=" + str + ", busyCount=" + (o + p.size()), new Object[0]);
            if (!a()) {
                a(this, 4, 5000);
                a(this, 3, 10000);
            }
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return PlatformResources.getApkResources(this);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.s;
    }

    @Override // android.app.Service
    public void onCreate() {
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.e("BGService", "onCreate###", new Object[0]);
        }
        this.l = (ConnectivityManager) getSystemService("connectivity");
        HandlerThread handlerThread = new HandlerThread("BGService");
        handlerThread.start();
        this.j = handlerThread.getLooper();
        this.k = new d(this.j);
        this.i = this;
        an.a().a(this.i);
        this.q = new h(this, this.i);
        this.r = new ag(this, this.i);
        this.q.a(this.i);
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.c("BGService", "onDestroy###", new Object[0]);
        }
        com.excelliance.lbsdk.main.g.b.c("BGService", "onDestroy", new Object[0]);
        this.q.b(this.i);
        stopForeground(true);
        g();
        this.j.quit();
        Timer timer = this.y;
        if (timer != null) {
            timer.cancel();
            this.y = null;
        }
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int i3;
        int iB;
        a((Object) this);
        boolean z2 = false;
        if (intent != null) {
            this.t = intent.getBooleanExtra("startForground", false);
            com.excelliance.lbsdk.main.g.b.c("BGService", "onStartCommand:startForground= " + this.t, new Object[0]);
            if (this.t) {
                a(intent.getBundleExtra("notificationMsg"));
            }
        }
        if (intent == null || intent.getAction() == null) {
            b((Object) this);
            return 2;
        }
        if (al.f(this)) {
            com.excelliance.lbsdk.main.g.b.c("BGService", "do nothing while waiting for install", new Object[0]);
        } else {
            if (!GameUtil.getIntance().checkForceSdk(this.i)) {
                String action = intent.getAction();
                String packageName = this.i.getPackageName();
                com.excelliance.lbsdk.main.g.b.c("BGService", "onStartCommand action=" + action, new Object[0]);
                s.a(this.i, new Intent(packageName + ".action.bindreq"));
                if (action.equals("com.excelliance.open.action.gameverchk")) {
                    boolean booleanExtra = intent.getBooleanExtra("startQuery", false);
                    boolean booleanExtra2 = intent.getBooleanExtra("startGame", false);
                    if (booleanExtra2) {
                        a(5);
                    }
                    if (!booleanExtra2 || booleanExtra) {
                        if (booleanExtra) {
                            an anVarA = an.a();
                            l lVarD = anVarA.d();
                            if (lVarD != null && lVarD.isInstall && lVarD.savePath != null && new File(lVarD.savePath).exists()) {
                                anVarA.a(lVarD);
                            }
                            f();
                        }
                        boolean booleanExtra3 = intent.getBooleanExtra("queryUpdate", false);
                        long jB = ae.b(this.i, "lebian_module_queryinfo", "lastQueryTime", 0L);
                        if (booleanExtra3 && Math.abs(System.currentTimeMillis() - jB) < 5000 && (iB = ae.b(this.i, "lebian_module_gameinfo", "queryUpdateResult", 0)) != 0) {
                            GameUtil.sendUpdateInfo(this.i, iB);
                            z2 = true;
                        }
                        if (!z2 && (!booleanExtra || Math.abs(System.currentTimeMillis() - jB) > 5000)) {
                            a(1, i2, intent);
                        }
                    }
                } else if (action.equals("com.excelliance.open.action.dmchk")) {
                    a(5, i2, intent);
                } else if (action.equals("com.excelliance.open.action.fw")) {
                    int intExtra = intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0);
                    com.excelliance.lbsdk.main.g.b.c("BGService", "ACT_NEW_VERSION type=" + intExtra, new Object[0]);
                    if (intExtra == 0) {
                        z = new Intent(intent);
                        i3 = 6;
                        a(i3, i2, intent);
                    } else if (intExtra == 1) {
                        an.a();
                        com.excelliance.lbsdk.main.g.b.c("BGService", "onStartCommand: TYPE_FW_DLOK", new Object[0]);
                        GameUtil.getIntance().killThirdProcesses();
                        GameUtil.getIntance().killProcessBySuffix(":lbmain");
                        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                        if (launchIntentForPackage != null) {
                            ComponentName component = launchIntentForPackage.getComponent();
                            if (component != null) {
                                launchIntentForPackage = new Intent();
                                launchIntentForPackage.setComponent(component);
                            }
                            launchIntentForPackage.setAction("android.intent.action.MAIN");
                            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
                        } else {
                            if (GameUtil.getIntance().checkJJ(this.i)) {
                                launchIntentForPackage = new Intent("com.excelliance.open.action.startPromptActivity");
                                launchIntentForPackage.putExtra("dialogtype", 7);
                            } else {
                                launchIntentForPackage = new Intent("action.lebian.MAIN");
                                launchIntentForPackage.addCategory("android.intent.category.DEFAULT");
                            }
                            launchIntentForPackage.setPackage(getPackageName());
                        }
                        launchIntentForPackage.addFlags(268468224);
                        startActivity(launchIntentForPackage);
                        com.excelliance.lbsdk.main.g.b.c("BGService", "onStartCommand: kill dns before restart", new Object[0]);
                        stopSelf();
                        Process.killProcess(Process.myPid());
                    }
                } else if (action.equals("com.excelliance.lbsdk.action.BGService")) {
                    String stringExtra = intent.getStringExtra("target");
                    if (stringExtra != null) {
                        if (stringExtra.equals("ReceiverService")) {
                            i3 = 8;
                            a(i3, i2, intent);
                        } else if (stringExtra.equals("CrashReportService")) {
                            if (!(intent.getParcelableExtra("intent") instanceof Intent)) {
                                return 2;
                            }
                        } else if (stringExtra.equals("StartQueryService")) {
                            if (Math.abs(System.currentTimeMillis() - ae.b(this.i, "lebian_module_queryinfo", "lastQueryTime", 0L)) > 3600000) {
                                Intent intent2 = new Intent("com.excelliance.open.action.gameverchk");
                                intent2.setPackage(getPackageName());
                                intent2.setComponent(new ComponentName(getPackageName(), BGService.class.getName()));
                                GameUtil.startService(intent2, this);
                                Parcelable parcelableExtra = intent.getParcelableExtra("intent");
                                if (!(parcelableExtra instanceof Intent)) {
                                    return 2;
                                }
                                com.excelliance.lbsdk.main.g.b.c("BGService", "StartQueryService remote=" + ((Intent) parcelableExtra).getStringExtra("remotePkg"), new Object[0]);
                            }
                        } else if (stringExtra.equals("RescueService")) {
                            com.excelliance.lbsdk.main.g.b.c("BGService", "nothing to do in normal case", new Object[0]);
                        } else if (stringExtra.equals("InstService")) {
                            com.excelliance.lbsdk.main.g.b.c("BGService", "onStartCommand:InstService", new Object[0]);
                            if (intent.getBooleanExtra("reinstall", false)) {
                                an.a().h();
                            }
                            h();
                        } else if (stringExtra.equals("CopyService")) {
                            a(intent);
                        }
                    }
                } else if (!action.equals("com.excelliance.open.NEXT_CHAPTER")) {
                    if (action.equals(BwbxUtil.ACTION_PLAT_DO)) {
                        int intExtra2 = intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0);
                        com.excelliance.lbsdk.main.g.b.c("BGService", "ACTION_PLAT_DO type=" + intExtra2, new Object[0]);
                        int i4 = 9;
                        if (intExtra2 == g) {
                            b(this, 9);
                            GameUtil.getIntance().cleanExit();
                        } else {
                            if (intExtra2 == e) {
                                this.k.removeCallbacks(this.w);
                                this.k.postDelayed(this.w, 2000L);
                            } else if (intExtra2 == f) {
                                this.k.removeCallbacks(this.w);
                                b(this, 9);
                            } else if (intExtra2 == h) {
                                boolean booleanExtra4 = intent.getBooleanExtra("cancelCheck", false);
                                this.k.removeMessages(10);
                                b(this, 10);
                                if (!booleanExtra4) {
                                    Message messageObtainMessage = this.k.obtainMessage(10);
                                    messageObtainMessage.arg2 = 1;
                                    messageObtainMessage.obj = intent.getParcelableExtra("activityIntent");
                                    this.k.sendMessageDelayed(messageObtainMessage, 2000L);
                                    a(this, 10);
                                }
                            } else if (intExtra2 == a) {
                                boolean booleanExtra5 = intent.getBooleanExtra("cancelChapterCheck", false);
                                i4 = 11;
                                this.k.removeMessages(11);
                                b(this, 11);
                                com.excelliance.lbsdk.main.g.b.c("BGService", "ACTION_TYPE_CHECK_NEXT_CHAPTER_ACTIVITY cancel : " + booleanExtra5, new Object[0]);
                                if (!booleanExtra5) {
                                    Message messageObtainMessage2 = this.k.obtainMessage(11);
                                    messageObtainMessage2.arg2 = 1;
                                    Intent intent3 = new Intent("com.excelliance.open.action.startNextChapter");
                                    intent3.setPackage(getPackageName());
                                    intent3.addFlags(268435456);
                                    intent3.replaceExtras(intent.getExtras());
                                    messageObtainMessage2.obj = intent3;
                                    this.k.sendMessageDelayed(messageObtainMessage2, 2000L);
                                }
                            }
                            a(this, i4);
                        }
                    } else if (action.equals("com.excelliance.open.action.bwbxdla")) {
                        if (an.a().i()) {
                            q.a(this, this.i, intent);
                        }
                    } else if (action.equals("com.excelliance.open.action.gamecontrol")) {
                        int intExtra3 = intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0);
                        if (intExtra3 == 1) {
                            GameUtil.getIntance().restartGame(this.i);
                        } else if (intExtra3 == 2) {
                            GameUtil.getIntance().setContext(this.i);
                            GameUtil.getIntance().killThirdProcesses();
                        } else if (intExtra3 == 3) {
                            this.v = intent.getIntExtra("speed", 0);
                            com.excelliance.lbsdk.main.g.b.c("BGService", "downloadSpeedLimit:" + this.v, new Object[0]);
                        }
                    }
                }
                b((Object) this);
                return 2;
            }
            com.excelliance.lbsdk.main.g.b.c("BGService", "receive source " + intent.getBooleanExtra("vm", false), new Object[0]);
            GameUtil.getIntance().restartGameKillAll(this.i);
        }
        b((Object) this);
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
