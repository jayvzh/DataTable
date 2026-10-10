package com.excelliance.lbsdk.main.y;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class e {

    public static class a extends Exception {
        private static final long serialVersionUID = 1;

        public a(String str) {
            super(str);
        }
    }

    public static long a(ByteBuffer byteBuffer) {
        b(byteBuffer);
        return c(byteBuffer, byteBuffer.position() + 16);
    }

    public static b<ByteBuffer, Long> a(RandomAccessFile randomAccessFile) {
        return c(randomAccessFile);
    }

    private static b<ByteBuffer, Long> a(RandomAccessFile randomAccessFile, int i) throws IOException {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("maxCommentSize: " + i);
        }
        long length = randomAccessFile.length();
        if (length < 22) {
            return null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(((int) Math.min(i, length - 22)) + 22);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        long jCapacity = length - ((long) byteBufferAllocate.capacity());
        randomAccessFile.seek(jCapacity);
        randomAccessFile.readFully(byteBufferAllocate.array(), byteBufferAllocate.arrayOffset(), byteBufferAllocate.capacity());
        int iC = c(byteBufferAllocate);
        if (iC == -1) {
            return null;
        }
        byteBufferAllocate.position(iC);
        ByteBuffer byteBufferSlice = byteBufferAllocate.slice();
        byteBufferSlice.order(ByteOrder.LITTLE_ENDIAN);
        return b.a(byteBufferSlice, Long.valueOf(jCapacity + ((long) iC)));
    }

    public static b<ByteBuffer, Long> a(RandomAccessFile randomAccessFile, long j) throws a, IOException {
        if (j < 32) {
            throw new a("APK too small for APK Signing Block. ZIP Central Directory offset: " + j);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(24);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j - ((long) byteBufferAllocate.capacity()));
        randomAccessFile.readFully(byteBufferAllocate.array(), byteBufferAllocate.arrayOffset(), byteBufferAllocate.capacity());
        if (byteBufferAllocate.getLong(8) != 2334950737559900225L || byteBufferAllocate.getLong(16) != 3617552046287187010L) {
            throw new a("No APK Signing Block before ZIP Central Directory");
        }
        long j2 = byteBufferAllocate.getLong(0);
        if (j2 < byteBufferAllocate.capacity() || j2 > 2147483639) {
            throw new a("APK Signing Block size out of range: " + j2);
        }
        int i = (int) (8 + j2);
        long j3 = j - ((long) i);
        if (j3 < 0) {
            throw new a("APK Signing Block offset out of range: " + j3);
        }
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i);
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j3);
        randomAccessFile.readFully(byteBufferAllocate2.array(), byteBufferAllocate2.arrayOffset(), byteBufferAllocate2.capacity());
        long j4 = byteBufferAllocate2.getLong(0);
        if (j4 == j2) {
            return b.a(byteBufferAllocate2, Long.valueOf(j3));
        }
        throw new a("APK Signing Block sizes in header and footer do not match: " + j4 + " <> " + j2);
    }

    public static b<ByteBuffer, Long> a(RandomAccessFile randomAccessFile, long j, int i) {
        return b.a(b(randomAccessFile, j, i), Long.valueOf(j));
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("size: " + i);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        int i2 = i + iPosition;
        if (i2 < iPosition || i2 > iLimit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i2);
        try {
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            byteBuffer.position(i2);
            return byteBufferSlice;
        } finally {
            byteBuffer.limit(iLimit);
        }
    }

    public static ByteBuffer a(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("start: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("end < start: " + i2 + " < " + i);
        }
        int iCapacity = byteBuffer.capacity();
        if (i2 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i2 + " > " + iCapacity);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i2);
            byteBuffer.position(i);
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            return byteBufferSlice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
        }
    }

    private static void a(ByteBuffer byteBuffer, int i, long j) {
        if (j >= 0 && j <= 4294967295L) {
            byteBuffer.putInt(byteBuffer.position() + i, (int) j);
            return;
        }
        throw new IllegalArgumentException("uint32 value of out range: " + j);
    }

    public static void a(ByteBuffer byteBuffer, long j) {
        b(byteBuffer);
        a(byteBuffer, byteBuffer.position() + 16, j);
    }

    public static int b(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getShort(i) & 65535;
    }

    public static b<ByteBuffer, Long> b(RandomAccessFile randomAccessFile) {
        return a(randomAccessFile, a(a(randomAccessFile).a()));
    }

    public static ByteBuffer b(RandomAccessFile randomAccessFile, long j, int i) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j);
        randomAccessFile.readFully(byteBufferAllocate.array(), byteBufferAllocate.arrayOffset(), byteBufferAllocate.capacity());
        return byteBufferAllocate;
    }

    public static void b(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static final boolean b(RandomAccessFile randomAccessFile, long j) throws IOException {
        long j2 = j - 20;
        if (j2 < 0) {
            return false;
        }
        randomAccessFile.seek(j2);
        return randomAccessFile.readInt() == 1347094023;
    }

    private static int c(ByteBuffer byteBuffer) {
        b(byteBuffer);
        int iCapacity = byteBuffer.capacity();
        if (iCapacity < 22) {
            return -1;
        }
        int i = iCapacity - 22;
        int iMin = Math.min(i, 65535);
        for (int i2 = 0; i2 <= iMin; i2++) {
            int i3 = i - i2;
            if (byteBuffer.getInt(i3) == 101010256 && b(byteBuffer, i3 + 20) == i2) {
                return i3;
            }
        }
        return -1;
    }

    public static long c(ByteBuffer byteBuffer, int i) {
        return ((long) byteBuffer.getInt(i)) & 4294967295L;
    }

    static b<ByteBuffer, Long> c(RandomAccessFile randomAccessFile) throws IOException {
        if (randomAccessFile.length() < 22) {
            return null;
        }
        b<ByteBuffer, Long> bVarA = a(randomAccessFile, 0);
        return bVarA != null ? bVarA : a(randomAccessFile, 65535);
    }

    public static com.excelliance.lbsdk.main.y.a d(RandomAccessFile randomAccessFile) throws a {
        b<ByteBuffer, Long> bVarA = a(randomAccessFile);
        ByteBuffer byteBufferA = bVarA.a();
        long jLongValue = bVarA.b().longValue();
        if (b(randomAccessFile, jLongValue)) {
            throw new a("ZIP64 APK not supported");
        }
        long jA = a(byteBufferA);
        b<ByteBuffer, Long> bVarA2 = null;
        try {
            bVarA2 = a(randomAccessFile, jA);
        } catch (Exception unused) {
        }
        b<ByteBuffer, Long> bVarA3 = a(randomAccessFile, jA, (int) (jLongValue - jA));
        com.excelliance.lbsdk.main.y.a aVar = new com.excelliance.lbsdk.main.y.a();
        aVar.a = randomAccessFile.length();
        aVar.b = bVarA2;
        aVar.c = bVarA3;
        aVar.d = bVarA;
        return aVar;
    }
}
