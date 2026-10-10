package com.excelliance.lbsdk.main;

import android.content.Context;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class x {
    public static String a(Context context) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("getTopActivity", Context.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(null, context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(Context context, int i) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("clearActivityStack", Context.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, context, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean b(Context context) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("isForeground", Context.class);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("isLBMainOnTop", Context.class);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean d(Context context) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("isPromptOnTop", Context.class);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean e(Context context) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("isAssistOnTop", Context.class);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean f(Context context) {
        try {
            Method declaredMethod = Class.forName("com.excelliance.lbsdk.life.LifeCycleHelper", false, GameUtil.getClassLoader(context)).getDeclaredMethod("isNextChapterOnTop", Context.class);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(null, context)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
