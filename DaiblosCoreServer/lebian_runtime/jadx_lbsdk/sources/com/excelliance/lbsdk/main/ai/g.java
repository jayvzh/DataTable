package com.excelliance.lbsdk.main.ai;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private static String a;
    private static String b;

    public static String a() throws Throwable {
        if (a == null) {
            a("");
        }
        return a;
    }

    public static boolean a(String str) throws Throwable {
        String upperCase;
        String str2 = a;
        if (str2 != null) {
            return str2.equals(str);
        }
        String strB = b("ro.miui.ui.version.name");
        b = strB;
        if (TextUtils.isEmpty(strB)) {
            String strB2 = b("ro.build.version.emui");
            b = strB2;
            if (TextUtils.isEmpty(strB2)) {
                String strB3 = b("ro.build.version.opporom");
                b = strB3;
                if (TextUtils.isEmpty(strB3)) {
                    String strB4 = b("ro.vivo.os.version");
                    b = strB4;
                    if (TextUtils.isEmpty(strB4)) {
                        String strB5 = b("ro.smartisan.version");
                        b = strB5;
                        if (TextUtils.isEmpty(strB5)) {
                            b = Build.DISPLAY;
                            if (b.toUpperCase().contains("FLYME")) {
                                a = "FLYME";
                            } else {
                                b = "unknown";
                                upperCase = Build.MANUFACTURER.toUpperCase();
                            }
                            return a.equals(str);
                        }
                        upperCase = "SMARTISAN";
                    } else {
                        upperCase = "VIVO";
                    }
                } else {
                    upperCase = "OPPO";
                }
            } else {
                upperCase = "EMUI";
            }
        } else {
            upperCase = "MIUI";
        }
        a = upperCase;
        return a.equals(str);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static String b(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            try {
                try {
                    String line = bufferedReader.readLine();
                    bufferedReader.close();
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return line;
                } catch (IOException e2) {
                    e = e2;
                    com.excelliance.lbsdk.main.g.b.e("Rom", "Unable to read prop " + str, e, new Object[0]);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }
}
