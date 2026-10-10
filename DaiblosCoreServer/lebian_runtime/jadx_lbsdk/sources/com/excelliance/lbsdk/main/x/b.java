package com.excelliance.lbsdk.main.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class b extends FileInputStream {
    private long a;
    private long b;
    private boolean c;
    private int d;
    private long e;

    public b(File file) {
        super(file);
        this.b = 0L;
        this.a = file.length();
        this.c = false;
        this.e = this.a;
    }

    public b(String str) {
        super(str);
        this.b = 0L;
        this.a = new File(str).length();
        this.c = false;
        this.e = this.a;
    }

    public void a(int i) {
        if (this.a >= this.e) {
            this.c = false;
        } else {
            this.c = true;
            this.d = i;
        }
    }

    public void a(long j) {
        if (j > 0) {
            this.a = j;
        }
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) != -1) {
            return bArr[0] & 255;
        }
        return -1;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int iMin = (int) Math.min(i2, this.a - this.b);
        if (iMin > 0) {
            int i3 = super.read(bArr, i, iMin);
            if (i3 > 0) {
                this.b += (long) i3;
            }
            return i3;
        }
        if (!this.c) {
            return -1;
        }
        if (bArr.length > this.d) {
            for (int i4 = 0; i4 < this.d; i4++) {
                bArr[i4] = 0;
            }
        }
        this.c = false;
        return this.d;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = super.skip(j);
        if (jSkip > 0) {
            this.b = jSkip;
        }
        return jSkip;
    }
}
