package com.excelliance.lbsdk.main.m;

import android.content.Context;
import com.excelliance.lbsdk.main.ao;
import com.excelliance.lbsdk.main.e;
import com.excelliance.lbsdk.main.s.af;
import com.excelliance.lbsdk.main.s.al;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private Context a;
    private File b;
    private String c;
    private HashMap<Long, String> d;

    public b(Context context, String str, String str2) {
        this.a = context;
        this.b = new File(str);
        this.c = str2;
    }

    private boolean b() {
        return this.b.exists();
    }

    private boolean b(Context context) {
        if (!b()) {
            d();
        }
        if (this.d != null) {
            return true;
        }
        this.d = e();
        return true;
    }

    private RandomAccessFile c() {
        try {
            File parentFile = this.b.getParentFile();
            if (!parentFile.isDirectory()) {
                if (parentFile.exists()) {
                    parentFile.delete();
                }
                parentFile.mkdirs();
            }
            return new RandomAccessFile(this.b, "rw");
        } catch (FileNotFoundException e) {
            ao.c("HoleZiper", "ensureOpen: " + e, new Object[0]);
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r12v10 */
    private boolean d() {
        byte[] bArr;
        long j;
        int i;
        RandomAccessFile randomAccessFileC = c();
        try {
            InputStream inputStreamB = e.b(this.a, this.c);
            byte[] bArr2 = new byte[2];
            byte[] bArr3 = new byte[4];
            int i2 = 8;
            byte[] bArr4 = new byte[8];
            int i3 = inputStreamB.read(bArr3);
            int i4 = -1;
            if (i3 == -1 || i3 < 4) {
                throw new RuntimeException("bad format lbresinfo");
            }
            ?? r12 = 1;
            long j2 = (bArr3[0] & 255) | ((bArr3[1] & 255) << 8) | ((bArr3[2] & 255) << 16) | ((bArr3[3] & 255) << 24);
            long j3 = 4;
            while (true) {
                if (j3 != j2) {
                    int i5 = inputStreamB.read(bArr2);
                    if (i5 == i4) {
                        break;
                    }
                    j = j3 + ((long) i5);
                    i = (bArr2[0] & 255) | ((bArr2[r12] & 255) << i2);
                    bArr = bArr3;
                    byte[] bArr5 = new byte[i];
                    j3 = j + ((long) inputStreamB.read(bArr5)) + ((long) inputStreamB.read(bArr4));
                    randomAccessFileC.seek(((((long) bArr4[3]) & 255) << 24) | (((long) bArr4[0]) & 255) | ((((long) bArr4[r12]) & 255) << i2) | ((((long) bArr4[2]) & 255) << 16) | ((((long) bArr4[4]) & 255) << 32) | ((((long) bArr4[5]) & 255) << 40) | ((((long) bArr4[6]) & 255) << 48) | ((((long) bArr4[7]) & 255) << 56));
                    randomAccessFileC.write(bArr5);
                    bArr3 = bArr;
                    bArr2 = bArr2;
                    j2 = j2;
                    i2 = 8;
                    i4 = -1;
                    r12 = 1;
                } else {
                    bArr = bArr3;
                    int i6 = inputStreamB.read(bArr);
                    if (i6 == i4) {
                        break;
                    }
                    j = j3 + ((long) i6);
                    i = (bArr[0] & 255) | ((bArr[r12] & 255) << i2) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
                    byte[] bArr6 = new byte[i];
                    j3 = j + ((long) inputStreamB.read(bArr6)) + ((long) inputStreamB.read(bArr4));
                    randomAccessFileC.seek(((((long) bArr4[3]) & 255) << 24) | (((long) bArr4[0]) & 255) | ((((long) bArr4[r12]) & 255) << i2) | ((((long) bArr4[2]) & 255) << 16) | ((((long) bArr4[4]) & 255) << 32) | ((((long) bArr4[5]) & 255) << 40) | ((((long) bArr4[6]) & 255) << 48) | ((((long) bArr4[7]) & 255) << 56));
                    randomAccessFileC.write(bArr6);
                    bArr3 = bArr;
                    bArr2 = bArr2;
                    j2 = j2;
                    i2 = 8;
                    i4 = -1;
                    r12 = 1;
                }
            }
            randomAccessFileC.close();
            return r12;
        } catch (IOException e) {
            ao.e("HoleZiper", "", e, new Object[0]);
            return false;
        }
    }

    private HashMap<Long, String> e() {
        HashMap<Long, String> map = new HashMap<>();
        try {
            al alVar = new al(this.b);
            Enumeration<af> enumerationA = alVar.a();
            while (enumerationA.hasMoreElements()) {
                al.b bVar = (al.b) enumerationA.nextElement();
                String name = bVar.getName();
                if (name.contains("../")) {
                    throw new IOException("entry name contains illegal characters ../");
                }
                long j = bVar.k().b;
                map.put(Long.valueOf(j), name);
                ao.a("HoleZiper", "hole entry name: %s, position: %d", name, Long.valueOf(j));
            }
            alVar.close();
        } catch (Exception e) {
            ao.e("HoleZiper", "", e, new Object[0]);
        }
        return map;
    }

    public HashMap<Long, String> a() {
        return this.d;
    }

    public boolean a(Context context) {
        return b(context);
    }
}
