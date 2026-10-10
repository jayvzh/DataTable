package com.excelliance.lbsdk.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;

/* JADX INFO: loaded from: classes.dex */
public class t {
    private static BroadcastReceiver a = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.t.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            int i = extras.getInt("lbevent");
            com.excelliance.lbsdk.main.g.b.c("LBEventManager", "onReceive: event=" + i, new Object[0]);
            if (i == 1) {
                com.excelliance.lbsdk.main.g.b.c("LBEventManager", "onReceive EVENT_MISSING: missingFile=" + extras.getString("missing"), new Object[0]);
                return;
            }
            if (i == 2) {
                com.excelliance.lbsdk.main.g.b.c("LBEventManager", "onReceive EVENT_DIALOG: dialogType=" + extras.getInt("dialog_type"), new Object[0]);
                return;
            }
            if (i == 3) {
                com.excelliance.lbsdk.main.g.b.c("LBEventManager", "onReceive EVENT_DIALOG_CLICK: dialogType=" + extras.getInt("dialog_type") + " clickType:" + extras.getInt("click_type"), new Object[0]);
                return;
            }
            if (i == 4) {
                com.excelliance.lbsdk.main.g.b.c("LBEventManager", "onReceive EVENT_DOWNLOAD_START", new Object[0]);
                return;
            }
            if (i != 5) {
                return;
            }
            com.excelliance.lbsdk.main.g.b.c("LBEventManager", "onReceive EVENT_DOWNLOADED_PROGRESS: progress=" + extras.getInt(BwbxUtil.ACTION_PROGRESS), new Object[0]);
        }
    };

    public static void a(Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("lbevent", i);
        a(context, bundle);
    }

    public static void a(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        Intent intent = new Intent("lebian.event.action");
        intent.setPackage(context.getPackageName());
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }
}
