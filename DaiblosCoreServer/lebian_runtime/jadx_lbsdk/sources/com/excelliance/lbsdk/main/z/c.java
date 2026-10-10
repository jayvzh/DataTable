package com.excelliance.lbsdk.main.z;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private Bundle a;

    public c() {
    }

    public c(Bundle bundle) {
        a(bundle);
    }

    public static long b(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("rSize")) {
            return 0L;
        }
        Object obj = bundle.get("rSize");
        if (obj instanceof String) {
            return Long.parseLong((String) obj);
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return 0L;
    }

    private String b(String str) {
        Bundle bundle = this.a;
        if (bundle == null) {
            return null;
        }
        return bundle.getString(str);
    }

    private void e() {
        if (this.a == null) {
            this.a = new Bundle();
        }
    }

    public Bundle a() {
        return this.a;
    }

    public void a(Bundle bundle) {
        Bundle bundle2 = this.a;
        if (bundle2 == null) {
            this.a = new Bundle(bundle);
        } else {
            bundle2.putAll(bundle);
        }
        this.a = bundle;
    }

    public void a(String str) {
        e();
        this.a.putString("od", str);
    }

    public String b() {
        return b("od");
    }

    public String c() {
        return b("nd");
    }

    public Bundle d() {
        Bundle bundle = this.a;
        return bundle != null ? new Bundle(bundle) : new Bundle();
    }
}
