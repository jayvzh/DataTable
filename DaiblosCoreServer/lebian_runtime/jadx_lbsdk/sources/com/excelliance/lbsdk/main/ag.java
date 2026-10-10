package com.excelliance.lbsdk.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.Build;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class ag extends i {
    private Context a;
    private c b;

    public ag(c cVar, Context context) {
        super(context);
        this.a = context;
        this.b = cVar;
    }

    public void a(Intent intent) {
        String runningVersionCode;
        Intent intent2 = (Intent) intent.getParcelableExtra("intent");
        String action = intent2.getAction();
        boolean z = false;
        ao.c("ReceiverService", "action:" + action, new Object[0]);
        if (action == null || !action.equals("com.excelliance.open.action.appstate")) {
            if (action == null || !action.equals("com.excelliance.open.action.ss")) {
                return;
            }
            String stringExtra = intent2.getStringExtra("intentStr");
            Intent intent3 = new Intent(stringExtra);
            intent3.setPackage(this.a.getPackageName());
            if (stringExtra.equals("com.excelliance.open.action.bwbxdla")) {
                ao.c("ReceiverService", "cancel bwbxdla", new Object[0]);
                if (intent2.getBooleanExtra("cancel", false)) {
                    intent3.putExtra("cancel", true);
                }
            } else if (stringExtra.equals("com.excelliance.open.action.fw")) {
                ao.c("ReceiverService", "fw", new Object[0]);
                intent3.putExtra("dialogtype", intent2.getIntExtra("dialogtype", 1));
                intent3.putExtra(BwbxUtil.ACTION_SIZE, intent2.getLongExtra(BwbxUtil.ACTION_SIZE, 0L));
                intent3.putExtra("zipExists", intent2.getBooleanExtra("zipExists", false));
                intent3.putExtra("dataConnection", intent2.getBooleanExtra("dataConnection", false));
            }
            GameUtil.startService(intent3, this.a);
            return;
        }
        int intExtra = intent2.getIntExtra("state", -1);
        String stringExtra2 = intent2.getStringExtra("apk");
        int intExtra2 = intent2.getIntExtra("crashCount", 0);
        ao.c("ReceiverService", "onReceive apkPath = " + stringExtra2 + ", state = " + intExtra + ", crashCount=" + intExtra2 + ", pid=" + intent2.getIntExtra("pid", 0), new Object[0]);
        GameUtil intance = GameUtil.getIntance();
        if (n.b()) {
            String strE = an.a().e();
            if (strE == null || !new File(strE).exists()) {
                runningVersionCode = intance.getRunningVersionCode();
                ao.c("ReceiverService", "onHandleIntent: new version not exist", new Object[0]);
            } else {
                runningVersionCode = String.valueOf(intance.getVerCodeFromApk(strE, false, true));
            }
            File file = new File(an.a().e(runningVersionCode));
            if (file.exists()) {
                stringExtra2 = file.getAbsolutePath();
                ao.c("ReceiverService", "onHandleIntent: change apk path", new Object[0]);
            }
        }
        intance.setContext(this.a);
        intance.killGameProcesses(this.a);
        if (intExtra != 2 || intExtra2 < 3) {
            return;
        }
        String signature = GameUtil.getIntance().getSignature(this.a);
        Signature[] entrySignatures = GameUtil.getEntrySignatures(stringExtra2, "AndroidManifest.xml");
        if (entrySignatures != null) {
            for (Signature signature2 : entrySignatures) {
                String charsString = signature2.toCharsString();
                if (charsString != null && charsString.startsWith(signature)) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            if (Build.VERSION.SDK_INT < 26) {
                this.a.startActivity(u.a(this.a, stringExtra2));
                return;
            }
            Intent intent4 = new Intent("com.excelliance.open.action.startPromptActivity");
            intent4.setPackage(this.a.getPackageName());
            intent4.setFlags(805306368);
            intent4.putExtra("dialogtype", 3);
            intent4.putExtra("apkPath", stringExtra2);
            intent4.putExtra("forceUpdate", true);
        }
    }
}
