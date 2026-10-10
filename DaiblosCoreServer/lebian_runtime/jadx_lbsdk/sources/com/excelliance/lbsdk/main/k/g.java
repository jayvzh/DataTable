package com.excelliance.lbsdk.main.k;

import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g<T> extends b<T> {

    protected static class a {
        public static int a(JSONObject jSONObject, String str, int i) {
            return jSONObject == null ? i : jSONObject.optInt(str, i);
        }

        public static String a(JSONObject jSONObject, String str, String str2) {
            return jSONObject == null ? str2 : jSONObject.optString(str, str2);
        }

        public static JSONObject a(JSONObject jSONObject, String str) {
            if (jSONObject == null) {
                return null;
            }
            return jSONObject.optJSONObject(str);
        }
    }

    @Override // com.excelliance.lbsdk.main.k.b
    public d<T> a(String str) {
        d<T> dVar = new d<>();
        if (str != null && !"".equals(str.trim())) {
            String strTrim = str.trim();
            if (strTrim.length() < 1) {
                return dVar;
            }
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(strTrim);
            } catch (JSONException unused) {
            }
            if (jSONObject != null) {
                dVar.a(a.a(jSONObject, "code", 1));
                dVar.a(a.a(jSONObject, "msg", ""));
                JSONObject jSONObjectA = a.a(jSONObject, BwbxUtil.ACTION_DATA);
                if (jSONObjectA != null) {
                    dVar.a(a(jSONObjectA));
                }
            }
        }
        return dVar;
    }

    protected T a(JSONObject jSONObject) {
        return null;
    }
}
