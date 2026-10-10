package com.excelliance.lbsdk.main.bwbx.compat;

import com.excelliance.lbsdk.main.GameUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static int a(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getShort(i) & 65535;
    }

    public static void a(File file, File file2, HashMap<String, HoleFileEntry> map) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
            int i = 30;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(30);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            long j = 0;
            long j2 = 0;
            while (true) {
                randomAccessFile.seek(j2);
                randomAccessFile2.seek(j2);
                randomAccessFile2.readFully(byteBufferAllocate.array(), 0, i);
                if (byteBufferAllocate.getInt(0) != 67324752) {
                    break;
                }
                byteBufferAllocate.position(0);
                long jB = b(byteBufferAllocate);
                int iC = c(byteBufferAllocate);
                int iD = d(byteBufferAllocate);
                byte[] bArr = new byte[iC];
                byte[] bArr2 = new byte[iD];
                randomAccessFile2.read(bArr);
                randomAccessFile2.read(bArr2);
                String str = new String(bArr);
                randomAccessFile.write(byteBufferAllocate.array());
                byte[] bArr3 = new byte[1024];
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                ByteBuffer byteBuffer = byteBufferAllocate;
                long j3 = jB;
                while (j3 > j) {
                    int length = j3 > ((long) bArr3.length) ? bArr3.length : (int) j3;
                    randomAccessFile2.read(bArr3, 0, length);
                    messageDigest.update(bArr3, 0, length);
                    j3 -= (long) length;
                    j = 0;
                }
                long j4 = iC + 30 + iD;
                HoleFileEntry holeFileEntry = new HoleFileEntry(str, j4 + j2, jB, GameUtil.byteArrayToHex(messageDigest.digest()), false, "1");
                randomAccessFile.write(bArr);
                randomAccessFile.write(bArr2);
                map.put(str, holeFileEntry);
                j2 += j4 + jB;
                byteBufferAllocate = byteBuffer;
                i = 30;
                j = 0;
            }
            randomAccessFile2.seek(j2);
            byte[] bArr4 = new byte[1024];
            while (true) {
                int i2 = randomAccessFile2.read(bArr4, 0, 1024);
                if (i2 == -1) {
                    randomAccessFile2.close();
                    randomAccessFile.close();
                    return;
                }
                randomAccessFile.write(bArr4, 0, i2);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
        }
    }

    public static void a(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static long b(ByteBuffer byteBuffer) {
        a(byteBuffer);
        return b(byteBuffer, byteBuffer.position() + 18);
    }

    public static long b(ByteBuffer byteBuffer, int i) {
        return ((long) byteBuffer.getInt(i)) & 4294967295L;
    }

    public static int c(ByteBuffer byteBuffer) {
        a(byteBuffer);
        return a(byteBuffer, byteBuffer.position() + 26);
    }

    public static int d(ByteBuffer byteBuffer) {
        a(byteBuffer);
        return a(byteBuffer, byteBuffer.position() + 28);
    }
}
