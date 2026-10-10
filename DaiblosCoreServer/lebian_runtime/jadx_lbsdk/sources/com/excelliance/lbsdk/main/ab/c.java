package com.excelliance.lbsdk.main.ab;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(Context context) {
        return GameUtil.getExternalDataPath(context) + "/fileSet.txt";
    }

    public static String b(Context context) {
        return GameUtil.getExternalDataPath(context) + "/file_sequence.txt";
    }
}
