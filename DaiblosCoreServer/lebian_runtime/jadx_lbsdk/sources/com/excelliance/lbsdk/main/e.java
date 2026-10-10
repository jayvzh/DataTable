package com.excelliance.lbsdk.main;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static InputStream a(Context context) {
        return a(context, (com.excelliance.lbsdk.main.s.al) null);
    }

    public static InputStream a(Context context, com.excelliance.lbsdk.main.s.al alVar) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        String str = strC + "assets/localData.png";
        if (strC == null || !new File(str).exists()) {
            return alVar != null ? alVar.a(alVar.a("localData.png")) : context.getAssets().open("localData.png");
        }
        return new FileInputStream(str);
    }

    public static InputStream a(Context context, String str) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        if (strC == null) {
            return context.getAssets().open(str);
        }
        return new FileInputStream(strC + "assets/" + str);
    }

    public static InputStream b(Context context) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        String str = strC + "assets/origSize.txt";
        return (strC == null || !new File(str).exists()) ? context.getAssets().open("origSize.txt") : new FileInputStream(str);
    }

    public static InputStream b(Context context, String str) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        String str2 = strC + "assets/ " + str;
        return (strC == null || !new File(str2).exists()) ? context.getAssets().open(str) : new FileInputStream(str2);
    }

    public static InputStream c(Context context) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        String str = strC + "assets/tryflag";
        if (strC == null || !new File(str).exists()) {
            return context.getAssets().open("tryflag");
        }
        return new FileInputStream(strC + "assets/tryflag");
    }

    public static InputStream d(Context context) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        String str = strC + "assets/lbresinfo";
        return (strC == null || !new File(str).exists()) ? context.getAssets().open("lbresinfo") : new FileInputStream(str);
    }

    public static InputStream e(Context context) {
        String strC = com.excelliance.lbsdk.main.ae.c.c(context);
        String str = strC + "assets/lebian/lbstream";
        if (strC == null || !new File(str).exists()) {
            return context.getAssets().open("lebian/lbstream");
        }
        return new FileInputStream(strC + "assets/lebian/lbstream");
    }
}
