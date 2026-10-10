package com.excelliance.lbsdk.main.q;

import android.content.Context;
import android.os.Bundle;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ai.d;
import com.excelliance.lbsdk.main.ai.h;
import com.excelliance.lbsdk.main.k.e;
import com.excelliance.lbsdk.main.z.c;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private Context a;
    private a b;

    public b(Context context) {
        this.a = context;
        this.b = new a(this.a);
    }

    public static String a(Bundle bundle, Context context) {
        return GameUtil.getCachePath(context, a(b(bundle)));
    }

    private static String a(String str) {
        int iA = h.a(str, '/', 2) + 1;
        int iA2 = h.a(str, '/', iA, 0);
        if (iA < iA2) {
            return str.substring(iA, iA2);
        }
        return null;
    }

    public static boolean a(c cVar, c cVar2) {
        if (cVar == cVar2) {
            return true;
        }
        if (cVar == null || cVar2 == null) {
            return false;
        }
        return a(cVar, cVar2.b(), cVar2.c());
    }

    private static boolean a(c cVar, String str, String str2) {
        if (cVar == null) {
            return false;
        }
        String strB = cVar.b();
        String strC = cVar.c();
        return strB != null && strB.equals(str) && strC != null && strC.equals(str2);
    }

    private static String b(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString("od");
        }
        return null;
    }

    public static String b(Bundle bundle, Context context) {
        return GameUtil.getCachePath(context, a(c(bundle)));
    }

    private void b(c cVar) {
        String strC = cVar != null ? cVar.c() : null;
        if (strC != null) {
            d.b(GameUtil.getCachePath(this.a, a(strC)));
        }
    }

    private static String c(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString("nd");
        }
        return null;
    }

    private void c(c cVar) {
        String strB = cVar != null ? cVar.b() : null;
        if (strB != null) {
            d.b(GameUtil.getCachePath(this.a, a(strB)));
        }
    }

    private boolean d(c cVar) {
        return a(cVar, GameUtil.getIntance().getResourceCfg(this.a).get("lastTryFlag"), GameUtil.getRemoteResPathZip(this.a)) && this.b.c(cVar);
    }

    public void a() {
        Context context = this.a;
        com.excelliance.lbsdk.main.k.d<c> dVarA = e.a(context);
        if (!dVarA.b() || dVarA.c() == null) {
            return;
        }
        c cVarA = this.b.a();
        c cVarC = dVarA.c();
        boolean zA = a(cVarA, cVarC);
        if (zA && this.b.c(cVarC)) {
            return;
        }
        if (!zA) {
            b(cVarA);
            this.b.a(cVarC);
        }
        com.excelliance.lbsdk.main.ai.c.a(context, cVarC);
    }

    public void a(Bundle bundle) {
        a(new c(bundle));
    }

    public void a(c cVar) {
        if (a(cVar, this.b.a())) {
            this.b.b(cVar);
        }
    }

    public boolean b() {
        return d(this.b.a());
    }

    public void c() {
        c cVarA = this.b.a();
        if (b()) {
            c(cVarA);
            this.b.b();
        }
    }
}
