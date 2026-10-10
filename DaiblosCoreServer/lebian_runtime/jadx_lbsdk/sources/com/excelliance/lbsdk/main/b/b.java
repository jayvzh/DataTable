package com.excelliance.lbsdk.main.b;

import android.app.Application;
import android.content.Context;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements c {
    private Context a;
    private Map<String, d> b;
    private boolean c;
    private boolean d;
    private Application e;

    private d a(Class<? extends d> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            com.excelliance.lbsdk.main.g.b.a("ApplicationModule", e);
            return null;
        }
    }

    private void a(Map<String, d> map) {
        Map<String, Class<? extends d>> mapC = c();
        if ((mapC != null ? mapC.size() : 0) < 1) {
            return;
        }
        for (String str : mapC.keySet()) {
            if (map == null || !map.containsKey(str)) {
                d dVarA = a(mapC.get(str));
                b(str, dVarA);
                dVarA.attach(this);
            }
        }
    }

    private void b(String str, d dVar) {
        Map linkedHashMap = this.b;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
            this.b = linkedHashMap;
        }
        linkedHashMap.put(str, dVar);
    }

    private void j() {
        Map<String, d> map = this.b;
        if (map == null) {
            return;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            map.get(it.next()).attach(this);
        }
    }

    @Override // com.excelliance.lbsdk.main.b.c
    public Context a() {
        return this.a;
    }

    @Override // com.excelliance.lbsdk.main.b.c
    public d a(String str) {
        Map<String, d> map = this.b;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public void a(Application application) {
        this.e = application;
    }

    public void a(Context context) {
        if (context != null) {
            this.a = context;
        }
        if (this.c) {
            return;
        }
        j();
        a(this.b);
        this.c = true;
    }

    public void a(String str, d dVar) {
        b(str, dVar);
        if (this.c) {
            dVar.attach(this);
            if (this.d) {
                dVar.preInitialize();
                dVar.initialize();
                dVar.postInitialize();
            }
        }
    }

    public void b() {
        Map<String, d> map = this.b;
        if (map == null || this.d) {
            return;
        }
        Iterator<d> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().preInitialize();
        }
        Iterator<d> it2 = map.values().iterator();
        while (it2.hasNext()) {
            it2.next().initialize();
        }
        Iterator<d> it3 = map.values().iterator();
        while (it3.hasNext()) {
            it3.next().postInitialize();
        }
        this.d = true;
    }

    protected Map<String, Class<? extends d>> c() {
        return null;
    }

    public void d() {
        Map<String, d> map = this.b;
        if (map == null) {
            return;
        }
        Iterator<d> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().preLoadSo();
        }
    }

    public void e() {
        Map<String, d> map = this.b;
        if (map == null) {
            return;
        }
        Iterator<d> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().postLoadSo();
        }
    }

    public void f() {
        Map<String, d> map = this.b;
        if (map == null) {
            return;
        }
        Iterator<d> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().onCreate();
        }
    }

    public void g() {
        Map<String, d> map = this.b;
        if (map == null) {
            return;
        }
        Iterator<d> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().overlayInstallation();
        }
    }

    public void h() {
        com.excelliance.lbsdk.main.g.b.c("ApplicationModule", "upgrade apk, base.apk", new Object[0]);
        Map<String, d> map = this.b;
        if (map == null) {
            return;
        }
        Iterator<d> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().upgrade();
        }
    }

    @Override // com.excelliance.lbsdk.main.b.c
    public Application i() {
        return this.e;
    }
}
