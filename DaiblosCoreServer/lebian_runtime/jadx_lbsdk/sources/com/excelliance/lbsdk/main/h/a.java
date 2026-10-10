package com.excelliance.lbsdk.main.h;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a<T> {
    private InterfaceC0002a a;
    private List<b<T>> b;
    private List<b<T>> c;

    /* JADX INFO: renamed from: com.excelliance.lbsdk.main.h.a$a, reason: collision with other inner class name */
    public interface InterfaceC0002a<T> {
        T a();
    }

    private static class b<T> {
        private int a;
        private T b;

        private b() {
        }

        static /* synthetic */ int b(b bVar) {
            int i = bVar.a;
            bVar.a = i + 1;
            return i;
        }
    }

    public a(InterfaceC0002a<T> interfaceC0002a) {
        if (interfaceC0002a == null) {
            throw new NullPointerException("创建对象不能为 null");
        }
        this.a = interfaceC0002a;
    }

    private T b() {
        T t = (T) this.a.a();
        if (t != null) {
            return t;
        }
        throw new RuntimeException("IObtainListener.onObtain() 函数禁止返回空对象");
    }

    private List<b<T>> c() {
        List<b<T>> list = this.c;
        if (list != null) {
            return list;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
        }
        return this.c;
    }

    private List<b<T>> d() {
        List<b<T>> list = this.b;
        if (list != null) {
            return list;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
        }
        return this.b;
    }

    public T a() {
        T t;
        List<b<T>> listC = c();
        synchronized (this) {
            if (listC.isEmpty()) {
                T tB = b();
                b<T> bVar = new b<>();
                ((b) bVar).b = tB;
                listC.add(bVar);
            }
            b<T> bVar2 = listC.get(listC.size() - 1);
            listC.remove(bVar2);
            d().add(bVar2);
            t = (T) ((b) bVar2).b;
        }
        return t;
    }

    public boolean a(T t) {
        List<b<T>> list = this.b;
        if (list == null || list.isEmpty()) {
            return false;
        }
        b<T> bVar = null;
        List<b<T>> listC = c();
        synchronized (this) {
            for (int i = 0; i < list.size(); i++) {
                b<T> bVar2 = list.get(i);
                if (((b) bVar2).b == t) {
                    bVar = bVar2;
                    break;
                }
            }
            if (bVar != null) {
                list.remove(bVar);
                b.b(bVar);
                listC.add(bVar);
            }
        }
        return bVar != null;
    }

    public int b(T t) {
        b<T> bVar;
        b<T> bVar2;
        synchronized (this) {
            List<b<T>> list = this.b;
            int i = 0;
            if (list != null && !list.isEmpty()) {
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        bVar = null;
                        break;
                    }
                    bVar = list.get(i2);
                    if (t == ((b) bVar).b) {
                        break;
                    }
                    i2++;
                }
            } else {
                bVar = null;
                break;
            }
            List<b<T>> list2 = this.c;
            if (bVar == null && list2 != null && !list2.isEmpty()) {
                while (true) {
                    if (i >= list2.size()) {
                        bVar2 = bVar;
                        break;
                    }
                    bVar2 = list2.get(i);
                    if (t == ((b) bVar2).b) {
                        break;
                    }
                    i++;
                }
            } else {
                bVar2 = bVar;
                break;
            }
        }
        if (bVar2 == null) {
            return -1;
        }
        return ((b) bVar2).a;
    }
}
