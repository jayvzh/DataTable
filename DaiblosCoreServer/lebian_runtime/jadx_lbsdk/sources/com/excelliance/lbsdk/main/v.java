package com.excelliance.lbsdk.main;

/* JADX INFO: loaded from: classes.dex */
public class v {
    public static boolean a() {
        String property = System.getProperty("lb.vm.version");
        int i = property != null ? Integer.parseInt(property) : -1;
        String property2 = System.getProperty("lb.vm.launchMode");
        if (i > 0) {
            return "HU".equals(property2) || property2 == null;
        }
        return false;
    }
}
