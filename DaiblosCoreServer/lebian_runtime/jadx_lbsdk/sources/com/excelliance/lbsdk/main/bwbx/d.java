package com.excelliance.lbsdk.main.bwbx;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private List<Object> a;
    private int b = 0;

    private void a(Object obj, int i) {
        try {
            obj.getClass().getMethod("onDownloadStatus", Integer.TYPE).invoke(obj, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Object> e() {
        List<Object> list = this.a;
        if (list != null) {
            return list;
        }
        synchronized (this) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
        }
        return this.a;
    }

    private List<Object> f() {
        ArrayList arrayList;
        List<Object> listE = e();
        synchronized (this) {
            arrayList = new ArrayList(listE.size());
            arrayList.addAll(listE);
        }
        return arrayList;
    }

    private void g() {
        List<Object> list = this.a;
        synchronized (this) {
            if (list != null) {
                list.clear();
            }
        }
    }

    void a() {
        this.b = 1001;
        List<Object> listF = f();
        int size = listF != null ? listF.size() : 0;
        for (int i = 0; i < size; i++) {
            a(listF.get(i), 1001);
        }
    }

    boolean a(Object obj) {
        List<Object> listE = e();
        if (listE.contains(obj)) {
            return false;
        }
        return listE.add(obj);
    }

    void b() {
        this.b = 1002;
        List<Object> listF = f();
        int size = listF != null ? listF.size() : 0;
        for (int i = 0; i < size; i++) {
            a(listF.get(i), 1002);
        }
    }

    boolean b(Object obj) {
        return e().remove(obj);
    }

    void c() {
        this.b = 1003;
        List<Object> listF = f();
        int size = listF != null ? listF.size() : 0;
        for (int i = 0; i < size; i++) {
            a(listF.get(i), 1003);
        }
        g();
    }

    public int d() {
        return this.b;
    }
}
