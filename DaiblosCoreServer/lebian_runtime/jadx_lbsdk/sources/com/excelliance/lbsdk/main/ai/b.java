package com.excelliance.lbsdk.main.ai;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class b {
    /* JADX WARN: Code duplicated, block: B:47:0x00d9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:48:0x00db A[Catch: IOException -> 0x00d7, TRY_LEAVE, TryCatch #2 {IOException -> 0x00d7, blocks: (B:44:0x00d3, B:48:0x00db), top: B:66:0x00d3 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x00f6 A[Catch: IOException -> 0x00f2, TRY_LEAVE, TryCatch #6 {IOException -> 0x00f2, blocks: (B:56:0x00ee, B:60:0x00f6), top: B:70:0x00ee }] */
    /* JADX WARN: Code duplicated, block: B:66:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r3v7 */
    public static float[] a(String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        IOException e;
        File file;
        float[] fArr = new float[2];
        if (str == null || str.length() == 0) {
            return fArr;
        }
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.endsWith("/") ? "" : "/");
        sb.append(System.currentTimeMillis());
        sb.append(".tmp");
        String string = sb.toString();
        ?? bufferedOutputStream = new Object[0];
        com.excelliance.lbsdk.main.g.b.c("DiskUtil", "stFile: " + string, bufferedOutputStream);
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string));
                try {
                    bufferedInputStream2 = new BufferedInputStream(new FileInputStream(string));
                    try {
                        byte[] bArr = new byte[4];
                        long jNanoTime = System.nanoTime();
                        for (int i = 0; i < 262144.0f; i++) {
                            bufferedOutputStream.write(bArr);
                        }
                        fArr[1] = (1048576.0f / ((System.nanoTime() - jNanoTime) / 1.0E9f)) / 1048576.0f;
                        long jNanoTime2 = System.nanoTime();
                        while (bufferedInputStream2.read(bArr) != -1) {
                        }
                        fArr[0] = (1048576.0f / ((System.nanoTime() - jNanoTime2) / 1.0E9f)) / 1048576.0f;
                        try {
                            bufferedOutputStream.close();
                            bufferedInputStream2.close();
                        } catch (IOException e2) {
                            com.excelliance.lbsdk.main.g.b.a("DiskUtil", e2);
                        }
                        file = new File(string);
                    } catch (IOException e3) {
                        e = e3;
                        com.excelliance.lbsdk.main.g.b.a("DiskUtil", e);
                        if (bufferedOutputStream != 0) {
                            try {
                                bufferedOutputStream.close();
                                if (bufferedInputStream2 != null) {
                                    bufferedInputStream2.close();
                                }
                            } catch (IOException e4) {
                                com.excelliance.lbsdk.main.g.b.a("DiskUtil", e4);
                                file = new File(string);
                                file.delete();
                                return fArr;
                            }
                        } else if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        file = new File(string);
                    }
                } catch (IOException e5) {
                    e = e5;
                    bufferedInputStream2 = null;
                    bufferedOutputStream = bufferedOutputStream;
                    e = e;
                    com.excelliance.lbsdk.main.g.b.a("DiskUtil", e);
                    if (bufferedOutputStream != 0) {
                        bufferedOutputStream.close();
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                    } else if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    file = new File(string);
                    file.delete();
                    return fArr;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = null;
                    if (bufferedOutputStream != 0) {
                        try {
                            bufferedOutputStream.close();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                        } catch (IOException e6) {
                            com.excelliance.lbsdk.main.g.b.a("DiskUtil", e6);
                            new File(string).delete();
                            throw th;
                        }
                    } else if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    new File(string).delete();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e7) {
            e = e7;
            bufferedOutputStream = 0;
            bufferedInputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = 0;
            bufferedInputStream = null;
        }
        file.delete();
        return fArr;
    }
}
