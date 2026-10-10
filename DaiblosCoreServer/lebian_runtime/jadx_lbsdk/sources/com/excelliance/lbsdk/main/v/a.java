package com.excelliance.lbsdk.main.v;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends com.excelliance.lbsdk.main.t.a {
    protected final com.excelliance.lbsdk.main.w.a a;
    private byte e;
    private int g;
    private int[] h;
    private byte[] i;
    private byte[] j;
    private int k;
    private final byte[] b = new byte[1];
    private int c = -1;
    private int d = 9;
    private int f = -1;

    protected a(InputStream inputStream, ByteOrder byteOrder) {
        this.a = new com.excelliance.lbsdk.main.w.a(inputStream, byteOrder);
    }

    private int a(byte[] bArr, int i, int i2) {
        int length = this.j.length - this.k;
        if (length <= 0) {
            return 0;
        }
        int iMin = Math.min(length, i2);
        System.arraycopy(this.j, this.k, bArr, i, iMin);
        this.k += iMin;
        return iMin;
    }

    protected abstract int a();

    protected abstract int a(int i, byte b);

    protected int a(int i, byte b, int i2) {
        int i3 = this.g;
        if (i3 >= i2) {
            return -1;
        }
        this.h[i3] = i;
        this.i[i3] = b;
        this.g = i3 + 1;
        return i3;
    }

    protected int a(int i, boolean z) {
        int i2 = i;
        while (i2 >= 0) {
            byte[] bArr = this.j;
            int i3 = this.k - 1;
            this.k = i3;
            bArr[i3] = this.i[i2];
            i2 = this.h[i2];
        }
        int i4 = this.f;
        if (i4 != -1 && !z) {
            a(i4, this.j[this.k]);
        }
        this.f = i;
        byte[] bArr2 = this.j;
        int i5 = this.k;
        this.e = bArr2[i5];
        return i5;
    }

    protected void a(int i, int i2) {
        this.h[i] = i2;
    }

    protected int b() {
        int i = this.d;
        if (i <= 31) {
            return (int) this.a.b(i);
        }
        throw new IllegalArgumentException("code size must not be bigger than 31");
    }

    protected void b(int i) {
        this.c = 1 << (i - 1);
    }

    protected int c() throws IOException {
        int i = this.f;
        if (i != -1) {
            return a(i, this.e);
        }
        throw new IOException("The first code can't be a reference to its preceding code");
    }

    protected void c(int i) {
        int i2 = 1 << i;
        this.h = new int[i2];
        this.i = new byte[i2];
        this.j = new byte[i2];
        this.k = i2;
        for (int i3 = 0; i3 < 256; i3++) {
            this.h[i3] = -1;
            this.i[i3] = (byte) i3;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.a.close();
    }

    protected int d() {
        return this.d;
    }

    protected int d(int i) {
        return this.h[i];
    }

    protected void e() {
        this.d++;
    }

    protected void e(int i) {
        this.g = i;
    }

    protected int f() {
        return this.h.length;
    }

    protected int g() {
        return this.c;
    }

    protected int h() {
        return this.g;
    }

    @Override // java.io.InputStream
    public int read() {
        int i = read(this.b);
        return i < 0 ? i : this.b[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int iA = a(bArr, i, i2);
        while (true) {
            int i3 = i2 - iA;
            if (i3 <= 0) {
                a(iA);
                return iA;
            }
            int iA2 = a();
            if (iA2 < 0) {
                if (iA <= 0) {
                    return iA2;
                }
                a(iA);
                return iA;
            }
            iA += a(bArr, i + iA, i3);
        }
    }
}
