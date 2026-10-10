package com.excelliance.lbsdk.main.s;

import java.io.Closeable;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* JADX INFO: loaded from: classes.dex */
public abstract class n implements Closeable {
    private final Deflater a;
    private final CRC32 b;
    private long c;
    private long d;
    private long e;
    private final byte[] f;

    private void c() {
        while (!this.a.needsInput()) {
            b();
        }
    }

    private void c(byte[] bArr, int i, int i2) {
        Deflater deflater;
        if (i2 <= 0 || this.a.finished()) {
            return;
        }
        if (i2 <= 8192) {
            deflater = this.a;
        } else {
            int i3 = i2 / 8192;
            for (int i4 = 0; i4 < i3; i4++) {
                this.a.setInput(bArr, (i4 * 8192) + i, 8192);
                c();
            }
            int i5 = i3 * 8192;
            if (i5 >= i2) {
                return;
            }
            deflater = this.a;
            i += i5;
            i2 -= i5;
        }
        deflater.setInput(bArr, i, i2);
        c();
    }

    public long a() {
        return this.e;
    }

    long a(byte[] bArr, int i, int i2, int i3) {
        long j = this.c;
        this.b.update(bArr, i, i2);
        if (i3 == 8) {
            c(bArr, i, i2);
        } else {
            a(bArr, i, i2);
        }
        this.d += (long) i2;
        return this.c - j;
    }

    public void a(byte[] bArr) {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i, int i2) {
        b(bArr, i, i2);
        long j = i2;
        this.c += j;
        this.e += j;
    }

    void b() {
        Deflater deflater = this.a;
        byte[] bArr = this.f;
        int iDeflate = deflater.deflate(bArr, 0, bArr.length);
        if (iDeflate > 0) {
            a(this.f, 0, iDeflate);
        }
    }

    protected abstract void b(byte[] bArr, int i, int i2);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.a.end();
    }
}
