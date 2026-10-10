package com.excelliance.lbsdk.main.k;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.aa;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h {
    public static void a(Map<String, String> map, Context context) {
        a(map, context, "chid");
    }

    public static void a(Map<String, String> map, Context context, String str) {
        map.put(str, com.excelliance.lbsdk.main.ai.e.a(context));
    }

    public static void b(Map<String, String> map, Context context) {
        b(map, context, "uid");
    }

    public static void b(Map<String, String> map, Context context, String str) {
        map.put(str, com.excelliance.lbsdk.main.ai.a.b(context));
    }

    public static void c(Map<String, String> map, Context context) {
        c(map, context, "md5");
    }

    public static void c(Map<String, String> map, Context context, String str) {
        map.put(str, ((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).g());
    }

    public static void d(Map<String, String> map, Context context) {
        e(map, context, "vercode");
    }

    public static void d(Map<String, String> map, Context context, String str) {
        map.put(str, context.getPackageName());
    }

    public static void e(Map<String, String> map, Context context) {
        f(map, context, "nettype");
    }

    public static void e(Map<String, String> map, Context context, String str) {
        map.put(str, GameUtil.getIntance().getRunningVersionCode());
    }

    public static void f(Map<String, String> map, Context context) {
        g(map, context, "cpmeta");
    }

    public static void f(Map<String, String> map, Context context, String str) {
        map.put(str, aa.c(context));
    }

    public static void g(Map<String, String> map, Context context, String str) {
        map.put(str, GameUtil.getIntance().getClientChId());
    }
}
