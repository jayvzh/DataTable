package com.excelliance.lbsdk.main.w;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {
    private static final long[] a = new long[64];
    private final InputStream b;
    private final ByteOrder c;
    private long d = 0;
    private int e = 0;

    static {
        for (int i = 1; i <= 63; i++) {
            long[] jArr = a;
            jArr[i] = (jArr[i - 1] << 1) + 1;
        }
    }

    public a(InputStream inputStream, ByteOrder byteOrder) {
        this.b = inputStream;
        this.c = byteOrder;
    }

    public long b(int i) {
        long j;
        long j2;
        if (i < 0 || i > 63) {
            throw new IllegalArgumentException("count must not be negative or greater than 63");
        }
        while (this.e < i) {
            long j3 = this.b.read();
            if (j3 < 0) {
                return j3;
            }
            if (this.c == ByteOrder.LITTLE_ENDIAN) {
                j2 = this.d;
                j3 <<= this.e;
            } else {
                this.d <<= 8;
                j2 = this.d;
            }
            this.d = j3 | j2;
            this.e += 8;
        }
        if (this.c == ByteOrder.LITTLE_ENDIAN) {
            long j4 = this.d;
            j = a[i] & j4;
            this.d = j4 >>> i;
        } else {
            j = a[i] & (this.d >> (this.e - i));
        }
        this.e -= i;
        return j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.b.close();
    }
}
