package com.excelliance.lbsdk.main.q;

import android.content.Context;
import android.os.Bundle;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.ai.i;
import com.excelliance.lbsdk.main.z.c;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;
    private c b;

    public a(Context context) {
        this.a = context;
    }

    private static Bundle a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            bundle.putString(str, map.get(str));
        }
        return bundle;
    }

    private void a(Bundle bundle) {
        ae.a(this.a, "lebian_module_dloadinfo", "preload", i.a(b(bundle)));
    }

    private static Map<String, String> b(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                map.put(str, (String) obj);
            }
        }
        return map;
    }

    private c c() {
        String strB = ae.b(this.a, "lebian_module_dloadinfo", "preload", (String) null);
        if (strB == null || strB.length() < 1) {
            return null;
        }
        return new c(a(i.a(strB)));
    }

    private c d() {
        if (this.b == null) {
            c cVarC = c();
            if (cVarC == null) {
                cVarC = new c(new Bundle());
            }
            this.b = cVarC;
        }
        return this.b;
    }

    private void d(c cVar) {
        a(cVar.d());
    }

    public c a() {
        return d();
    }

    public void a(c cVar) {
        c cVarD = d();
        if (b.a(cVarD, cVar)) {
            return;
        }
        d(cVar);
        if (cVarD != null) {
            Bundle bundleA = cVarD.a();
            Bundle bundleA2 = cVar.a();
            if (bundleA == null || bundleA2 == null) {
                return;
            }
            bundleA.clear();
            bundleA.putAll(bundleA2);
        }
    }

    public void b() {
        this.b = null;
        ae.a(this.a, "lebian_module_dloadinfo", "preload");
    }

    public void b(c cVar) {
        if (!b.a(c(), cVar)) {
            a(cVar);
        }
        Bundle bundleD = cVar.d();
        bundleD.putString("status", "finish");
        a(bundleD);
    }

    public boolean c(c cVar) {
        c cVarD = d();
        if (b.a(cVarD, cVar)) {
            return "finish".equals(cVarD.d().getString("status"));
        }
        return false;
    }
}
