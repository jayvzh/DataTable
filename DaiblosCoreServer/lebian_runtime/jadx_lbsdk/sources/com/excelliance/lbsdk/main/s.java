package com.excelliance.lbsdk.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class s {
    public static void a(Context context, BroadcastReceiver broadcastReceiver) {
        if (context == null || broadcastReceiver == null) {
            return;
        }
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (Exception unused) {
            com.excelliance.lbsdk.main.g.b.c("LBContextCompat", "unregister wrong", new Object[0]);
        }
    }

    public static void a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null || broadcastReceiver == null || intentFilter == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(broadcastReceiver, intentFilter, null, null, 4);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter, null, null);
        }
    }

    public static void a(Context context, Intent intent) {
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent);
    }

    public static void a(Context context, Intent intent, String str) {
        intent.setPackage(context.getPackageName());
        context.sendBroadcast(intent, str);
    }

    public static void b(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null || broadcastReceiver == null || intentFilter == null) {
            return;
        }
        context.registerReceiver(broadcastReceiver, intentFilter, null, null);
    }
}
