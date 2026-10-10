package com.excelliance.lbsdk.main.k;

import android.content.Context;
import com.excelliance.lbsdk.main.ak;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(final Context context, int i, int i2, int i3, int i4) {
        final HashMap map = new HashMap();
        a(map, context);
        h.f(map, context);
        h.e(map, context);
        map.put("downType", String.valueOf(i));
        map.put("pkgType", String.valueOf(i2));
        map.put("upState", String.valueOf(i3));
        map.put(BwbxUtil.ACTION_PROGRESS, String.valueOf(i4));
        ak.a().a(new Runnable() { // from class: com.excelliance.lbsdk.main.k.c.3
            @Override // java.lang.Runnable
            public void run() {
                e.a(44, map, context);
            }
        });
    }

    public static void a(final Context context, int i, long j) {
        final HashMap map = new HashMap();
        a(map, context);
        h.e(map, context);
        h.f(map, context);
        map.put(BwbxUtil.ACTION_TYPE, String.valueOf(i));
        map.put("speed", String.valueOf(j));
        ak.a().a(new Runnable() { // from class: com.excelliance.lbsdk.main.k.c.1
            @Override // java.lang.Runnable
            public void run() {
                e.a(41, map, context);
            }
        });
    }

    public static void a(final String str, final int i, final Context context, final a aVar) {
        ak.a().a(new Runnable() { // from class: com.excelliance.lbsdk.main.k.c.2
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                d dVarA = e.a(str, i, context);
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(dVarA);
                }
            }
        });
    }

    private static void a(HashMap<String, String> map, Context context) {
        h.a(map, context);
        h.b(map, context);
        h.c(map, context);
        h.d(map, context, "pkg");
        h.d(map, context);
    }

    public static void b(final Context context, int i, int i2, int i3, int i4) {
        final HashMap map = new HashMap();
        a(map, context);
        h.f(map, context);
        h.e(map, context);
        map.put("unzipType", String.valueOf(i));
        map.put("pkgType", String.valueOf(i2));
        map.put("upState", String.valueOf(i3));
        map.put(BwbxUtil.ACTION_PROGRESS, String.valueOf(i4));
        ak.a().a(new Runnable() { // from class: com.excelliance.lbsdk.main.k.c.4
            @Override // java.lang.Runnable
            public void run() {
                e.a(51, map, context);
            }
        });
    }
}
