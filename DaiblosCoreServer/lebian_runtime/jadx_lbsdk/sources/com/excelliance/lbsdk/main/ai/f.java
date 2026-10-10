package com.excelliance.lbsdk.main.ai;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class f {
    public static String a(Context context, String str) {
        return b(context, str);
    }

    public static String a(String str) {
        MessageDigest messageDigest = null;
        if (str == null) {
            return null;
        }
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            com.excelliance.lbsdk.main.g.b.a("Md5Util", e);
        }
        if (messageDigest == null) {
            return str;
        }
        messageDigest.update(str.getBytes());
        return a(messageDigest.digest());
    }

    public static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i = i2 + 1;
            cArr2[i2] = cArr[b & 15];
        }
        return new String(cArr2);
    }

    private static String b(Context context, String str) {
        return GameUtil.getIntance().computeFileMd5(str);
    }
}
