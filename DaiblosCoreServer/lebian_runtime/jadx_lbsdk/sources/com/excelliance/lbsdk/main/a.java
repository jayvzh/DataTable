package com.excelliance.lbsdk.main;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static ap a(String[] strArr) throws IOException {
        ap apVar = null;
        for (String str : strArr) {
            if (apVar == null) {
                apVar = new ap(str);
            } else if (str.contains("lebian_hu_list-")) {
                apVar.b(str);
            } else {
                apVar.d(str);
            }
        }
        return apVar;
    }
}
