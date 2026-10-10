package com.excelliance.lbsdk.main;

import android.content.Context;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ae {
    public static Map<String, String> a(Context context, String str) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("getAll", Context.class, String.class);
            declaredMethod.setAccessible(true);
            return (Map) declaredMethod.invoke(null, context, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("remove", Context.class, String.class, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, int i) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("putInt", Context.class, String.class, String.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, str, str2, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, long j) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("putLong", Context.class, String.class, String.class, Long.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, str, str2, Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("putString", Context.class, String.class, String.class, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, str, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("putBoolean", Context.class, String.class, String.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, str, str2, Boolean.valueOf(z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int b(Context context, String str, String str2, int i) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("getInt", Context.class, String.class, String.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(null, context, str, str2, Integer.valueOf(i))).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static long b(Context context, String str, String str2, long j) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("getLong", Context.class, String.class, String.class, Long.TYPE);
            declaredMethod.setAccessible(true);
            return ((Long) declaredMethod.invoke(null, context, str, str2, Long.valueOf(j))).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static String b(Context context, String str, String str2, String str3) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("getString", Context.class, String.class, String.class, String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, context, str, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
            return str3;
        }
    }

    public static void b(Context context, String str) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("clear", Context.class, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.preferences.PreferencesHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("getBoolean", Context.class, String.class, String.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, context, str, str2, Boolean.valueOf(z))).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }
}
