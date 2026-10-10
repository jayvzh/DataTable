package com.excelliance.lbsdk.main;

import android.content.Context;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.excelliance.lbsdk.main.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0006r {
    Context a;
    private Object b;
    private Class c;

    public C0006r(Context context, String str, String str2) {
        this.a = context;
        try {
            try {
                this.c = Class.forName("com.excelliance.lbsdk.LBCallImpl", false, this.a.getClassLoader());
                this.b = this.c.getConstructor(Context.class, String.class, String.class).newInstance(context, str, str2);
            } catch (Exception unused) {
                Field declaredField = Class.forName("com.excelliance.lbsdk.LebianSdk", false, this.a.getClassLoader()).getDeclaredField("MUST_CL");
                declaredField.setAccessible(true);
                this.c = Class.forName("com.excelliance.lbsdk.must.LBCallImpl", false, (ClassLoader) declaredField.get(null));
                this.b = this.c.getConstructor(Context.class, String.class, String.class).newInstance(context, str, str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bundle a(String str, String str2, Bundle bundle) throws Exception {
        try {
            Method declaredMethod = this.c.getDeclaredMethod("call", String.class, String.class, Bundle.class);
            declaredMethod.setAccessible(true);
            return (Bundle) declaredMethod.invoke(this.b, str, str2, bundle);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void a() {
        try {
            Method declaredMethod = this.c.getDeclaredMethod("release", (Class[]) null);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.b, (Object[]) null);
        } catch (Exception e) {
            throw new AndroidRuntimeException(e);
        }
    }
}
