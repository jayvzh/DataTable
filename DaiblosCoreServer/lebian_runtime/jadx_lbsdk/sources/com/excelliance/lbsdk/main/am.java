package com.excelliance.lbsdk.main;

import android.content.Context;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class am {
    private static int a(int i) {
        return ((i & 255) << 24) + ((65280 & i) << 8) + ((16711680 & i) >>> 8) + ((i >>> 24) & 255);
    }

    private static int a(RandomAccessFile randomAccessFile) {
        return a(randomAccessFile.readInt());
    }

    public static void a(Context context, String str, HashMap<String, String> map) throws IOException {
        int engine = BwbxUtil.getEngine(context);
        if (engine == 1 || engine == 5) {
            try {
                a(context.getPackageCodePath(), str + "unityCde", map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void a(String str, String str2, HashMap<String, String> map) throws IOException {
        String str3;
        File file = new File(str);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        if (length < 22) {
            throw new IOException();
        }
        long j = 65557 > length ? length : 65557L;
        randomAccessFile.seek(0L);
        int iA = a(randomAccessFile);
        int i = 0;
        if (iA == 101010256) {
            com.excelliance.lbsdk.main.g.b.c("UnityResHelper", "Found Zip archive, but it looks empty", new Object[0]);
            throw new IOException();
        }
        if (iA != 67324752) {
            com.excelliance.lbsdk.main.g.b.a("UnityResHelper", "Not a Zip archive", new Object[0]);
            throw new IOException();
        }
        randomAccessFile.seek(length - j);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) j);
        byte[] bArrArray = byteBufferAllocate.array();
        randomAccessFile.readFully(bArrArray);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        int length2 = bArrArray.length - 22;
        while (length2 >= 0) {
            if (bArrArray[length2] == 80 && byteBufferAllocate.getInt(length2) == 101010256) {
                if (!com.excelliance.lbsdk.main.g.b.a()) {
                    break;
                }
                com.excelliance.lbsdk.main.g.b.a("UnityResHelper", "+++ Found EOCD at index: " + length2, new Object[0]);
                break;
            }
            length2--;
        }
        if (length2 < 0) {
            com.excelliance.lbsdk.main.g.b.c("UnityResHelper", "Zip: EOCD not found, " + str + " is not zip", new Object[0]);
        }
        int i2 = length2 + 10;
        int i3 = byteBufferAllocate.getShort(i2) & 65535;
        int i4 = length2 + 12;
        long j2 = ((long) byteBufferAllocate.getInt(i4)) & 4294967295L;
        long j3 = ((long) byteBufferAllocate.getInt(length2 + 16)) & 4294967295L;
        if (j3 + j2 > length) {
            com.excelliance.lbsdk.main.g.b.d("UnityResHelper", "bad offsets (dir " + j3 + ", size " + j2 + ", eocd " + length2 + ")", new Object[0]);
            throw new IOException();
        }
        if (i3 == 0) {
            com.excelliance.lbsdk.main.g.b.d("UnityResHelper", "empty archive?", new Object[0]);
            throw new IOException();
        }
        if (com.excelliance.lbsdk.main.g.b.a()) {
            str3 = "UnityResHelper";
            com.excelliance.lbsdk.main.g.b.a(str3, "+++ numEntries=" + i3 + " dirSize=" + j2 + " dirOffset=" + j3, new Object[0]);
        } else {
            str3 = "UnityResHelper";
        }
        MappedByteBuffer map2 = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j3, j2);
        map2.order(ByteOrder.LITTLE_ENDIAN);
        byte[] bArr = new byte[65535];
        int i5 = 0;
        int i6 = 0;
        short s = 0;
        while (i5 < i3) {
            int i7 = i3;
            if (map2.getInt(i) != 33639248) {
                com.excelliance.lbsdk.main.g.b.d(str3, "Missed a central dir sig (at " + i + ")", new Object[0]);
                throw new IOException();
            }
            int i8 = map2.getShort(i + 28) & 65535;
            RandomAccessFile randomAccessFile2 = randomAccessFile;
            int i9 = map2.getShort(i + 30) & 65535;
            int i10 = i2;
            int i11 = map2.getShort(i + 32) & 65535;
            map2.position(i + 46);
            map2.get(bArr, 0, i8);
            int i12 = length2;
            String str4 = new String(bArr, 0, i8);
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.a(str3, "Filename: " + str4, new Object[0]);
            }
            int i13 = i8 + 46 + i9 + i11;
            if (!map.containsKey(str4) || !str4.contains("assets/bin/Data")) {
                byte[] bArr2 = new byte[i13];
                map2.position(i);
                map2.get(bArr2);
                fileOutputStream.write(bArr2);
                i6 += i13;
                s = (short) (s + 1);
            }
            map2.position(0);
            i += i13;
            i5++;
            i2 = i10;
            i3 = i7;
            randomAccessFile = randomAccessFile2;
            length2 = i12;
            bArr = bArr;
            byteBufferAllocate = byteBufferAllocate;
        }
        int i14 = i2;
        ByteBuffer byteBuffer = byteBufferAllocate;
        int i15 = length2;
        byteBuffer.position(i4);
        byteBuffer.putInt(i6);
        byteBuffer.position(i15 + 8);
        byteBuffer.putShort(s);
        byteBuffer.position(i14);
        byteBuffer.putShort(s);
        byte[] bArr3 = new byte[22];
        byteBuffer.position(i15);
        byteBuffer.get(bArr3);
        fileOutputStream.write(bArr3);
        fileOutputStream.close();
        randomAccessFile.close();
    }
}
