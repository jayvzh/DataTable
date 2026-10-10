package com.excelliance.lbsdk.main.s;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes.dex */
public abstract class ap {
    private static final byte[] a = am.getBytes(8448);

    public static int a(byte b) {
        return b >= 0 ? b : b + 256;
    }

    public static long a(int i) {
        return i < 0 ? ((long) i) + 4294967296L : i;
    }

    static long a(BigInteger bigInteger) {
        if (bigInteger.bitLength() <= 63) {
            return bigInteger.longValue();
        }
        throw new NumberFormatException("The BigInteger cannot fit inside a 64 bit java long: [" + bigInteger + "]");
    }

    private static String a(a aVar, byte[] bArr) {
        if (aVar != null) {
            CRC32 crc32 = new CRC32();
            crc32.update(bArr);
            if (crc32.getValue() == aVar.a()) {
                try {
                    return aj.a.a(aVar.b());
                } catch (IOException unused) {
                }
            }
        }
        return null;
    }

    static BigInteger a(long j) {
        if (j >= -2147483648L) {
            if (j < 0 && j >= -2147483648L) {
                j = a((int) j);
            }
            return BigInteger.valueOf(j);
        }
        throw new IllegalArgumentException("Negative longs < -2^31 not permitted: [" + j + "]");
    }

    static void a(af afVar) throws t {
        if (!b(afVar)) {
            throw new t(t.a.a, afVar);
        }
        if (c(afVar)) {
            return;
        }
        an methodByCode = an.getMethodByCode(afVar.getMethod());
        if (methodByCode != null) {
            throw new t(methodByCode, afVar);
        }
        throw new t(t.a.b, afVar);
    }

    static void a(af afVar, byte[] bArr, byte[] bArr2) {
        String strA;
        p pVar = (p) afVar.b(p.a);
        String name = afVar.getName();
        String strA2 = a(pVar, bArr);
        if (strA2 != null && !name.equals(strA2)) {
            afVar.a(strA2);
        }
        if (bArr2 == null || bArr2.length <= 0 || (strA = a((o) afVar.b(o.a), bArr2)) == null) {
            return;
        }
        afVar.setComment(strA);
    }

    static void a(Calendar calendar, long j, byte[] bArr, int i) {
        calendar.setTimeInMillis(j);
        int i2 = calendar.get(1);
        if (i2 < 1980) {
            byte[] bArr2 = a;
            System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        } else {
            am.putLong((calendar.get(13) >> 1) | ((i2 - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5), bArr, i);
        }
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length - 1;
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            int i2 = length - i;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        return bArr;
    }

    public static byte b(int i) {
        if (i <= 255 && i >= 0) {
            return i < 128 ? (byte) i : (byte) (i - 256);
        }
        throw new IllegalArgumentException("Can only convert non-negative integers between [0,255] to byte: [" + i + "]");
    }

    public static long b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, ((int) ((j >> 25) & 127)) + 1980);
        calendar.set(2, ((int) ((j >> 21) & 15)) - 1);
        calendar.set(5, ((int) (j >> 16)) & 31);
        calendar.set(11, ((int) (j >> 11)) & 31);
        calendar.set(12, ((int) (j >> 5)) & 63);
        calendar.set(13, ((int) (j << 1)) & 62);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    private static boolean b(af afVar) {
        return !afVar.h().b();
    }

    static byte[] b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    private static boolean c(af afVar) {
        return afVar.getMethod() == 0 || afVar.getMethod() == an.UNSHRINKING.getCode() || afVar.getMethod() == an.IMPLODING.getCode() || afVar.getMethod() == 8 || afVar.getMethod() == an.BZIP2.getCode();
    }
}
