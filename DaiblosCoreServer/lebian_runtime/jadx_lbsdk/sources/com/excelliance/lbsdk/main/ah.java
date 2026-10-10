package com.excelliance.lbsdk.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ah extends FileInputStream {
    private long a;

    public ah(File file, long j, long j2) throws IOException {
        super(file);
        super.skip(j2);
        this.a = j;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int available() {
        long j = this.a;
        if (j < 0) {
            return super.available();
        }
        if (j < 2147483647L) {
            return (int) j;
        }
        return Integer.MAX_VALUE;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() {
        byte[] bArr = new byte[1];
        if (read(bArr, 0, 1) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.a;
        if (j < 0 || j == 0) {
            return -1;
        }
        if (i2 > j) {
            i2 = (int) j;
        }
        int i3 = super.read(bArr, i, i2);
        if (i3 >= 0) {
            this.a -= (long) i3;
        }
        return i3;
    }
}
