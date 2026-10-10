package com.excelliance.lbsdk.main.aa;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    private h a;
    private String b;
    private HashMap<String, String> c;

    public e() {
    }

    public e(String str) {
        this.b = str;
    }

    public e a(h hVar) {
        this.a = hVar;
        return this;
    }

    public e a(String str, String str2) {
        if (this.c == null) {
            this.c = new HashMap<>();
        }
        this.c.put(str, str2);
        return this;
    }

    public abstract String a();

    public String b() {
        return this.b;
    }

    public h c() {
        if (this.a == null) {
            this.a = h.a();
        }
        return this.a;
    }

    public a[] d() {
        HashMap<String, String> map = this.c;
        if (map == null || map.size() < 1) {
            return null;
        }
        a[] aVarArr = new a[this.c.size()];
        int i = 0;
        for (String str : this.c.keySet()) {
            aVarArr[i] = new a(str, this.c.get(str));
            i++;
        }
        return aVarArr;
    }
}
