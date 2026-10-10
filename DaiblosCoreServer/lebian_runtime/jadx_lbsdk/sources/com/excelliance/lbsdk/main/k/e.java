package com.excelliance.lbsdk.main.k;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.aa;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.o;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e {
    static d a(int i, Map<String, Object> map, Context context) {
        String str = GameUtil.getIntance().getBaseUrl("s") + "sdkstatistics.php";
        HashMap map2 = new HashMap();
        map2.put("action", String.valueOf(i));
        map2.put(BwbxUtil.ACTION_DATA, Base64.encodeToString(new JSONObject(map).toString().getBytes(), 0));
        return a(com.excelliance.lbsdk.main.ai.i.a(str, map2), context);
    }

    public static d<com.excelliance.lbsdk.main.z.c> a(Context context) {
        return a(context, GameUtil.getRemoteResPathZip(context), (String) null);
    }

    private static d<com.excelliance.lbsdk.main.z.c> a(Context context, String str, String str2) {
        HashMap map = new HashMap();
        h.a(map, context);
        map.put("od", str);
        if (str2 != null) {
            map.put("nd", str2);
        }
        map.put("picklvl", String.valueOf(23));
        o oVarA = aa.a(com.excelliance.lbsdk.main.ai.i.a(com.excelliance.lbsdk.main.ai.i.a(a(), "picklbres.php"), map), context);
        if (oVarA.a != 200) {
            d<com.excelliance.lbsdk.main.z.c> dVar = new d<>();
            dVar.a(oVarA.b);
            return dVar;
        }
        d<com.excelliance.lbsdk.main.z.c> dVarA = new j().a(oVarA.b);
        com.excelliance.lbsdk.main.z.c cVarC = dVarA.c();
        if (cVarC != null) {
            cVarC.a(str);
        }
        return dVarA;
    }

    public static d a(String str, int i, Context context) throws Throwable {
        HashMap map = new HashMap();
        h.a(map, context);
        h.b(map, context);
        map.put("md5", str);
        map.put("brand", Build.BRAND);
        map.put("model", com.excelliance.lbsdk.main.ai.a.a(context));
        map.put("release", Build.VERSION.RELEASE);
        map.put("display", Build.DISPLAY);
        map.put("romName", com.excelliance.lbsdk.main.ai.g.a());
        map.put("mergeProgress", Integer.valueOf(i));
        float[] fArrA = com.excelliance.lbsdk.main.ai.b.a(context.getFilesDir().getAbsolutePath());
        map.put("readSpeed", String.format("read speed: %.4f", Float.valueOf(fArrA[0])));
        map.put("writeSpeed", String.format("read speed: %.4f", Float.valueOf(fArrA[1])));
        return a(43, map, context);
    }

    private static d a(String str, Context context) {
        o oVarA = aa.a(str, context);
        if (oVarA.a == 200) {
            return new g().a(oVarA.b);
        }
        d dVar = new d();
        dVar.a(oVarA.b);
        return dVar;
    }

    private static String a() {
        return GameUtil.getIntance().getBaseUrl();
    }

    public static d<com.excelliance.lbsdk.main.z.c> b(Context context) {
        return a(context, GameUtil.getIntance().getResourceCfg(context).get("lastTryFlag"), GameUtil.getRemoteResPathZip(context));
    }

    public static d<com.excelliance.lbsdk.main.z.b> c(Context context) {
        HashMap map = new HashMap();
        h.a(map, context);
        o oVarA = aa.a(com.excelliance.lbsdk.main.ai.i.a(com.excelliance.lbsdk.main.ai.i.a(a(), "getautosplitdomain.php"), map), context);
        if (oVarA.a == 200) {
            return new i().a(oVarA.b);
        }
        d<com.excelliance.lbsdk.main.z.b> dVar = new d<>();
        dVar.a(oVarA.b);
        return dVar;
    }
}
