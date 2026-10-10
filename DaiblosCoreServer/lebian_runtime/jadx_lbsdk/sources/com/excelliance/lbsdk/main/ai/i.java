package com.excelliance.lbsdk.main.ai;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class i {
    public static String a(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        if (str.endsWith("/") && str2.startsWith("/")) {
            str = str.substring(0, str.length() - 1);
        } else if (!str.endsWith("/") && !str2.startsWith("/")) {
            str = str + "/";
        }
        return str + str2;
    }

    public static String a(String str, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(str);
        if (str != null && !str.endsWith("?")) {
            sb.append("?");
        }
        sb.append(a(map));
        return sb.toString();
    }

    public static String a(Map<String, String> map) {
        if (map == null || map.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            sb.append(URLEncoder.encode(str));
            sb.append("=");
            sb.append(URLEncoder.encode(str2));
            sb.append("&");
        }
        String string = sb.toString();
        return string.substring(0, string.length() - 1);
    }

    public static Map<String, String> a(String str) {
        if (str == null) {
            return null;
        }
        HashMap map = new HashMap();
        int i = 0;
        do {
            int iIndexOf = str.indexOf(38, i);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i);
            if (iIndexOf2 > iIndexOf || iIndexOf2 == -1) {
                iIndexOf2 = iIndexOf;
            }
            String strDecode = URLDecoder.decode(str.substring(i, iIndexOf2));
            int i2 = iIndexOf2 + 1;
            map.put(strDecode, URLDecoder.decode(i2 < iIndexOf ? str.substring(i2, iIndexOf) : ""));
            i = iIndexOf + 1;
        } while (i < str.length());
        return map;
    }

    public static String b(String str, String str2) {
        if (str == null || str.length() < 1) {
            return str2;
        }
        if (str2 == null || str2.length() < 1) {
            return str;
        }
        if (str.endsWith("/") && str2.startsWith("/")) {
            return str + str2.substring(1);
        }
        if (str.endsWith("/") || str2.startsWith("/")) {
            return str + str2;
        }
        return str + "/" + str2;
    }
}
