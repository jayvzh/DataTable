package com.excelliance.lbsdk.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.util.AndroidRuntimeException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ad {
    private static final String a = "ad";
    private static ad d;
    private static Object e;
    private final Context b;
    private C0006r c;
    private int f = -100;

    private ad(Context context) {
        this.b = context;
        this.c = new C0006r(this.b, this.b.getPackageName() + ":com.excelliance.kxqp.platform.PluginManagerProvider", "com.excelliance.kxqp.platform.PluginManagerService");
    }

    public static ad a(Context context) {
        synchronized (ad.class) {
            if (d == null) {
                d = new ad(context);
            }
        }
        return d;
    }

    private Object a(int i, Object[] objArr) {
        Bundle bundleA;
        if (this.b == null) {
            throw new RuntimeException("mContext == null !!!");
        }
        ArrayList arrayList = new ArrayList();
        if (objArr != null && objArr.length > 0) {
            for (int i2 = 0; i2 < objArr.length; i2++) {
                arrayList.add(i2, objArr[i2]);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("extra.vmcall.cmd", i);
        bundle.putSerializable("extra.vmcall.args", arrayList);
        synchronized (this) {
            try {
                try {
                    bundleA = this.c.a("vm_call", null, bundle);
                } catch (Exception e2) {
                    throw new AndroidRuntimeException(e2);
                }
            } catch (DeadObjectException unused) {
                this.c.a();
                this.c = new C0006r(this.b, this.b.getPackageName() + ":com.excelliance.kxqp.platform.PluginManagerProvider", "com.excelliance.kxqp.platform.PluginManagerService");
                bundleA = this.c.a("vm_call", null, bundle);
            } catch (Exception e3) {
                throw new AndroidRuntimeException(e3);
            }
        }
        if (bundleA == null) {
            throw new AndroidRuntimeException("pluginmanager call failed!");
        }
        ArrayList arrayList2 = (ArrayList) bundleA.getSerializable("extra.vmcall.result");
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return null;
        }
        return arrayList2.get(0);
    }

    private int b() {
        if (this.f == -100) {
            Object objB = b(0, new Object[0]);
            this.f = objB instanceof Integer ? ((Integer) objB).intValue() : -1111;
        }
        return this.f;
    }

    private Object b(int i, Object[] objArr) {
        try {
            return a(i, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int a(int i, String str, int i2) {
        Object objA = a(55, new Object[]{Integer.valueOf(i), str, Integer.valueOf(i2)});
        if (objA instanceof Integer) {
            return ((Integer) objA).intValue();
        }
        return -1111;
    }

    public ComponentName a(int i, Intent intent) {
        if (b() < 3) {
            return null;
        }
        Object objA = a(52, new Object[]{Integer.valueOf(i), intent});
        if (objA instanceof ComponentName) {
            return (ComponentName) objA;
        }
        return null;
    }

    public void a(int i, String str, Bundle bundle) {
        a(108, new Object[]{Integer.valueOf(i), str, bundle});
    }

    public boolean a() {
        Object objB = b(1, null);
        if (objB instanceof Boolean) {
            return ((Boolean) objB).booleanValue();
        }
        return false;
    }

    public boolean a(int i, String str, long j, boolean z) {
        if (b() >= 3) {
            Object objA = a(53, new Object[]{Integer.valueOf(i), str, Long.valueOf(j), Boolean.valueOf(z)});
            if (objA instanceof Boolean) {
                return ((Boolean) objA).booleanValue();
            }
        }
        return false;
    }

    public boolean a(int i, String str, String str2) {
        Object objA = a(57, new Object[]{Integer.valueOf(i), str, str2});
        return (objA instanceof Boolean) && ((Boolean) objA).booleanValue();
    }
}
