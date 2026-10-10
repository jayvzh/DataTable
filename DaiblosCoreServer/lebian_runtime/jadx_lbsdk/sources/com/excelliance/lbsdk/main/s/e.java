package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
class e {
    private final int a;
    private final byte[] b;
    private int c;
    private int d;

    e(int i) {
        this.a = i;
        this.b = new byte[i];
    }

    public void a(int i) {
        byte[] bArr = this.b;
        int i2 = this.d;
        bArr[i2] = (byte) i;
        this.d = (i2 + 1) % this.a;
    }

    public void a(int i, int i2) {
        int i3 = this.d - i;
        int i4 = i2 + i3;
        while (i3 < i4) {
            byte[] bArr = this.b;
            int i5 = this.d;
            int i6 = this.a;
            bArr[i5] = bArr[(i3 + i6) % i6];
            this.d = (i5 + 1) % i6;
            i3++;
        }
    }

    public boolean a() {
        return this.c != this.d;
    }

    public int b() {
        if (!a()) {
            return -1;
        }
        byte[] bArr = this.b;
        int i = this.c;
        byte b = bArr[i];
        this.c = (i + 1) % this.a;
        return b & 255;
    }
}
