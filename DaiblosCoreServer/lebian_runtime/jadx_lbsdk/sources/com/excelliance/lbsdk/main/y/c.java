package com.excelliance.lbsdk.main.y;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(File file, int i) {
        byte[] bArrB;
        if (file != null && file.exists() && file.isFile() && (bArrB = b(file, i)) != null) {
            try {
                if (bArrB.length > 0) {
                    return new String(bArrB, "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map<Integer, ByteBuffer> a(RandomAccessFile randomAccessFile) {
        try {
            return a(e.b(randomAccessFile).a());
        } catch (e.a e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Map<Integer, ByteBuffer> a(ByteBuffer byteBuffer) {
        return a(byteBuffer, false);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0061  */
    public static Map<Integer, ByteBuffer> a(ByteBuffer byteBuffer, boolean z) throws e.a {
        e.b(byteBuffer);
        ByteBuffer byteBufferA = e.a(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (byteBufferA.hasRemaining()) {
            i++;
            if (byteBufferA.remaining() < 8) {
                throw new e.a("Insufficient data to read size of APK Signing Block entry #" + i);
            }
            long j = byteBufferA.getLong();
            if (j < 4 || j > 2147483647L) {
                throw new e.a("APK Signing Block entry #" + i + " size out of range: " + j);
            }
            int i2 = (int) j;
            int iPosition = byteBufferA.position() + i2;
            if (i2 > byteBufferA.remaining()) {
                throw new e.a("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + byteBufferA.remaining());
            }
            int i3 = byteBufferA.getInt();
            if (z) {
                if (i3 == 1896449818) {
                    linkedHashMap.put(Integer.valueOf(i3), e.a(byteBufferA, i2 - 4));
                }
                if (i3 == -262969152) {
                    linkedHashMap.put(Integer.valueOf(i3), e.a(byteBufferA, i2 - 4));
                }
            } else {
                linkedHashMap.put(Integer.valueOf(i3), e.a(byteBufferA, i2 - 4));
            }
            byteBufferA.position(iPosition);
        }
        if (!linkedHashMap.isEmpty()) {
            return linkedHashMap;
        }
        throw new e.a("not have Id-Value Pair in APK Signing Block entry #" + i);
    }

    public static Map<Integer, ByteBuffer> b(ByteBuffer byteBuffer) {
        return a(byteBuffer, true);
    }

    public static byte[] b(File file, int i) {
        ByteBuffer byteBufferC;
        if (file == null || !file.exists() || !file.isFile() || (byteBufferC = c(file, i)) == null) {
            return null;
        }
        int iArrayOffset = byteBufferC.arrayOffset() + byteBufferC.limit();
        int iArrayOffset2 = byteBufferC.arrayOffset() + byteBufferC.position();
        int i2 = iArrayOffset - iArrayOffset2;
        if (i2 >= 0) {
            byte[] bArr = new byte[i2];
            System.arraycopy(byteBufferC.array(), iArrayOffset2, bArr, 0, Math.min(byteBufferC.array().length - iArrayOffset2, i2));
            return bArr;
        }
        throw new IllegalArgumentException(iArrayOffset2 + " > " + iArrayOffset);
    }

    public static ByteBuffer c(File file, int i) {
        if (file != null && file.exists() && file.isFile()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                Map<Integer, ByteBuffer> mapA = a(randomAccessFile);
                randomAccessFile.close();
                if (mapA != null) {
                    return mapA.get(Integer.valueOf(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
