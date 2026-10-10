package com.excelliance.lbsdk.main.r;

import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends OutputStream {
    private final byte[] a;
    private long b;

    protected void a(long j) {
        if (j != -1) {
            this.b += j;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        byte[] bArr = this.a;
        bArr[0] = (byte) (i & 255);
        write(bArr, 0, 1);
    }
}
