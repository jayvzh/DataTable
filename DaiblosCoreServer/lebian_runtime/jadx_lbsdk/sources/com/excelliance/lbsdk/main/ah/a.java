package com.excelliance.lbsdk.main.ah;

import android.content.Context;
import com.excelliance.lbsdk.main.ae;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static final String d = new String();
    private Context a;
    private String b;
    private HashMap<String, Object> c = new HashMap<>();

    public a(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public String a(String str, String str2) {
        String str3 = (String) this.c.get(str);
        if (str3 != null && str3 != d) {
            return str3;
        }
        if (str3 != null) {
            return str2;
        }
        String strB = ae.b(this.a, this.b, str, str2);
        if (strB != null) {
            this.c.put(str, strB);
        } else {
            this.c.put(str, d);
        }
        return strB;
    }

    public void a(String str) {
        ae.a(this.a, this.b, str);
    }

    public boolean a(String str, boolean z) {
        return "true".equals(a(str, z ? "true" : "false"));
    }

    public void b(String str, String str2) {
        if (str2 == null) {
            return;
        }
        this.c.put(str, str2);
        ae.a(this.a, this.b, str, str2);
    }

    public void b(String str, boolean z) {
        b(str, z ? "true" : "false");
    }
}
