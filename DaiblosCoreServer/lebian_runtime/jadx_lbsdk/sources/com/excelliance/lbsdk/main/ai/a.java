package com.excelliance.lbsdk.main.ai;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.an;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static String a;

    public static String a(Context context) {
        String strB = a;
        if (strB == null) {
            strB = "";
            try {
                strB = ae.b(context, "lebian_device_info", "model", (String) null);
                if (strB == null) {
                    strB = Build.MODEL;
                    ae.a(context, "lebian_device_info", "model", strB);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            a = strB;
        }
        return strB;
    }

    public static String a(com.excelliance.lbsdk.main.z.a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (aVar.a() != -1) {
                jSONObject.put("api", aVar.a());
            }
            if (aVar.u() != null && aVar.u().length() > 0) {
                jSONObject.put("sdkver", aVar.u());
            }
            if (aVar.v() != null && aVar.v().length() > 0) {
                jSONObject.put("andver", aVar.v());
            }
            if (aVar.d() != null && aVar.d().length() > 0) {
                jSONObject.put("abi", aVar.d());
            }
            if (aVar.e() != null && aVar.e().length() > 0) {
                jSONObject.put("abi2", aVar.e());
            }
            if (aVar.f() != null && aVar.f().length() > 0) {
                jSONObject.put("abilist", aVar.f());
            }
            if (aVar.g() != null && aVar.g().length() > 0) {
                jSONObject.put("abilist32", aVar.g());
            }
            if (aVar.h() != null && aVar.h().length() > 0) {
                jSONObject.put("abilist64", aVar.h());
            }
            if (aVar.n() != null && aVar.n().length() > 0) {
                jSONObject.put("andid", aVar.n());
            }
            if (aVar.i() != null && aVar.i().length() > 0) {
                jSONObject.put("brand", aVar.i());
            }
            if (aVar.q() != null && aVar.q().length() > 0) {
                jSONObject.put("country", aVar.q());
            }
            if (aVar.t() != null && aVar.t().length() > 0) {
                jSONObject.put("emulatorname", aVar.t());
            }
            if (aVar.o() != null && aVar.o().length() > 0) {
                jSONObject.put("ename", aVar.o());
            }
            if (aVar.m() != null && aVar.m().length() > 0) {
                jSONObject.put("imei", aVar.m());
            }
            if (aVar.j() != null && aVar.j().length() > 0) {
                jSONObject.put("manufacturer", aVar.j());
            }
            if (aVar.k() != null && aVar.k().length() > 0) {
                jSONObject.put("model", aVar.k());
            }
            if (aVar.r() != null && aVar.r().length() > 0) {
                jSONObject.put("ncpuabi", aVar.r());
            }
            if (aVar.l() != null && aVar.l().length() > 0) {
                jSONObject.put("product", aVar.l());
            }
            if (aVar.p() != null && aVar.p().length() > 0) {
                jSONObject.put("language", aVar.p());
            }
            if (aVar.b() != null && aVar.b().length() > 0) {
                jSONObject.put("release", aVar.b());
            }
            if (aVar.s() != null && aVar.s().length() > 0) {
                jSONObject.put("signmd5", aVar.s());
            }
            if (aVar.c() != null && aVar.c().length() > 0) {
                jSONObject.put("uid", aVar.c());
            }
            if (aVar.w() != null && aVar.w().length() > 0) {
                jSONObject.put("screen", aVar.w());
            }
            if (aVar.x() != null && aVar.x().length() > 0) {
                jSONObject.put("meminfo", aVar.x());
            }
            if (aVar.z() != null && aVar.z().length() > 0) {
                jSONObject.put("pkgname", aVar.z());
            }
            if (aVar.y() != null && aVar.y().length() > 0) {
                jSONObject.put("nettype", aVar.y());
            }
            if (aVar.A() != null && aVar.A().length() > 0) {
                jSONObject.put("gver", aVar.A());
            }
            return Base64.encodeToString(jSONObject.toString().getBytes(), 0);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(Context context) {
        String strB = ae.b(context, "lebian_module_userinfo", "uid", (String) null);
        return strB == null ? an.a().b() : strB;
    }

    public static boolean c(Context context) {
        ae.a(context, "lebian_module_userinfo", "uid");
        return true;
    }
}
