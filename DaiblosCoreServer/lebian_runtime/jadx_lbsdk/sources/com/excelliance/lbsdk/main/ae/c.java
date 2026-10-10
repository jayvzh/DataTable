package com.excelliance.lbsdk.main.ae;

import android.content.Context;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.l;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static c a;
    private static String c;
    private HashMap<String, b> b;

    private c() {
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public static String b(Context context) {
        String strD = d(context);
        if (strD != null) {
            return strD.substring(strD.indexOf("lebian_hu_list-") + 15, strD.lastIndexOf("-"));
        }
        return null;
    }

    public static String b(String str) {
        if (str != null) {
            return str.substring(str.indexOf("lebian_hu_list-") + 15, str.lastIndexOf("-"));
        }
        return null;
    }

    public static String c(Context context) {
        String strD = d(context);
        String strSubstring = strD != null ? strD.substring(0, strD.indexOf("lebian_hu_list-")) : null;
        com.excelliance.lbsdk.main.g.b.c("AssetUpdateUtil", "getFilePathInResHu: " + strSubstring, new Object[0]);
        return strSubstring;
    }

    public static String c(String str) {
        if (str != null) {
            return str.substring(str.lastIndexOf("-") + 1);
        }
        return null;
    }

    public static String d(Context context) {
        if (c == null) {
            l lVarA = an.a().a(ae.b(context, "lebian_module_extractinfo", "hulistcurrent", (String) null));
            if (lVarA != null) {
                c = lVarA.savePath;
            }
            String str = c;
            if (str != null && !new File(str).exists()) {
                c = null;
            }
        }
        return c;
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split(":");
        return !strArrSplit[0].equals("-f") ? strArrSplit[1] : BwbxUtil.NO_EXIST;
    }

    public static String e(Context context) {
        String strD = d(context);
        if (strD != null) {
            return c(strD);
        }
        return null;
    }

    public static boolean e(String str) {
        return str.endsWith("assets/localData.png") || str.endsWith("assets/origSize.txt") || str.endsWith("assets/tryflag") || str.endsWith("assets/lbresinfo") || str.contains("assets/lebian/");
    }

    public HashMap<String, b> a(Context context) {
        return a(d(context));
    }

    public HashMap<String, b> a(String str) {
        HashMap<String, b> map = this.b;
        if (map != null && map.size() > 0) {
            return this.b;
        }
        this.b = new HashMap<>();
        if (this.b.size() == 0 && str != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] strArrSplit = line.split(":");
                    int i = 2;
                    if (strArrSplit.length == 2) {
                        int iIndexOf = line.indexOf("assets/");
                        if (iIndexOf == -1) {
                            iIndexOf = line.indexOf("lib/");
                        }
                        String strSubstring = line.substring(iIndexOf);
                        if ("-f".equals(strArrSplit[0])) {
                            i = 0;
                        } else if (!"em".equals(strArrSplit[0])) {
                            i = 1;
                        }
                        this.b.put(strSubstring, new b(i, strArrSplit[1]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.b;
    }
}
