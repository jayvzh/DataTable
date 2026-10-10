package com.excelliance.lbsdk.main.j;

import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private static final String a = "b";

    public static final String a(int i) {
        return b(i);
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x005d: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:34:0x005d */
    private static String b(int i) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                try {
                    fileInputStream2 = new FileInputStream("/proc/" + i + "/cmdline");
                    try {
                        byte[] bArr = new byte[256];
                        int i2 = 0;
                        while (true) {
                            int i3 = fileInputStream2.read();
                            if (i3 <= 0 || i2 >= bArr.length) {
                                break;
                            }
                            bArr[i2] = (byte) i3;
                            i2++;
                        }
                        if (i2 > 0) {
                            String str = new String(bArr, 0, i2, "UTF-8");
                            try {
                                fileInputStream2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return str;
                        }
                        fileInputStream2.close();
                    } catch (Throwable th) {
                        th = th;
                        th.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return null;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream3 != null) {
                    try {
                        fileInputStream3.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream3 = fileInputStream;
        }
    }
}
