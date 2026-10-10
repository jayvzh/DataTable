package com.excelliance.lbsdk.main.g;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private static volatile int a = 4;

    public static void a(String str, String str2, Throwable th, Object... objArr) {
        try {
            if (a(2)) {
                if (th == null) {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.v(str, str2);
                } else {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.v(str, str2, th);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        a(str, str2, null, objArr);
    }

    public static void a(String str, Throwable th) {
        e(str, null, th, new Object[0]);
    }

    public static boolean a() {
        return a(3);
    }

    public static boolean a(int i) {
        return i >= a;
    }

    public static void b(int i) {
        a = i;
    }

    public static void b(String str, String str2, Throwable th, Object... objArr) {
        try {
            if (a(3)) {
                if (th == null) {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.d(str, str2);
                } else {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.d(str, str2, th);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        b(str, str2, null, objArr);
    }

    public static void c(String str, String str2, Throwable th, Object... objArr) {
        try {
            if (a(4)) {
                if (th == null) {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.i(str, str2);
                } else {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.i(str, str2, th);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        c(str, str2, null, objArr);
    }

    public static void d(String str, String str2, Throwable th, Object... objArr) {
        try {
            if (a(5)) {
                if (th == null) {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.w(str, str2);
                } else {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.w(str, str2, th);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        d(str, str2, null, objArr);
    }

    public static void e(String str, String str2, Throwable th, Object... objArr) {
        try {
            if (a(6)) {
                if (th == null) {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.e(str, str2);
                } else {
                    if (objArr.length != 0) {
                        str2 = String.format(str2, objArr);
                    }
                    Log.e(str, str2, th);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        e(str, str2, null, objArr);
    }
}
