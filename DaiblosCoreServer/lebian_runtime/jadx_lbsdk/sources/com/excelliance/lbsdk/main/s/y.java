package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public class y extends l {
    private int a;
    private l.a b;
    private int c;
    private int d;
    private long e;
    private l.b f;
    private int g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;

    public y() {
        super(new ao(23));
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a = ao.getValue(bArr, i);
        this.b = l.a.getAlgorithmByCode(ao.getValue(bArr, i + 2));
        this.c = ao.getValue(bArr, i + 4);
        this.d = ao.getValue(bArr, i + 6);
        this.e = am.getValue(bArr, i + 8);
        if (this.e > 0) {
            this.f = l.b.getAlgorithmByCode(ao.getValue(bArr, i + 12));
            this.g = ao.getValue(bArr, i + 14);
            for (int i3 = 0; i3 < this.e; i3++) {
                for (int i4 = 0; i4 < this.g; i4++) {
                }
            }
        }
    }

    public void b(byte[] bArr, int i, int i2) {
        int value = ao.getValue(bArr, i);
        this.h = new byte[value];
        System.arraycopy(bArr, i + 4, this.h, 0, value);
        int i3 = i + value;
        this.a = ao.getValue(bArr, i3 + 6);
        this.b = l.a.getAlgorithmByCode(ao.getValue(bArr, i3 + 8));
        this.c = ao.getValue(bArr, i3 + 10);
        this.d = ao.getValue(bArr, i3 + 12);
        int value2 = ao.getValue(bArr, i3 + 14);
        this.i = new byte[value2];
        int i4 = i3 + 16;
        System.arraycopy(bArr, i4, this.i, 0, value2);
        this.e = am.getValue(bArr, i4 + value2);
        System.out.println("rcount: " + this.e);
        if (this.e == 0) {
            int value3 = ao.getValue(bArr, i3 + 20 + value2);
            int i5 = value3 - 4;
            this.l = new byte[i5];
            this.m = new byte[4];
            int i6 = i3 + 22 + value2;
            System.arraycopy(bArr, i6, this.l, 0, i5);
            System.arraycopy(bArr, (i6 + value3) - 4, this.m, 0, 4);
            return;
        }
        this.f = l.b.getAlgorithmByCode(ao.getValue(bArr, i3 + 20 + value2));
        int i7 = i3 + 22 + value2;
        this.g = ao.getValue(bArr, i7);
        int i8 = i3 + 24 + value2;
        int value4 = ao.getValue(bArr, i8);
        int i9 = this.g;
        this.j = new byte[i9];
        this.k = new byte[value4 - i9];
        System.arraycopy(bArr, i8, this.j, 0, i9);
        int i10 = this.g;
        System.arraycopy(bArr, i8 + i10, this.k, 0, value4 - i10);
        int value5 = ao.getValue(bArr, i3 + 26 + value2 + value4);
        int i11 = value5 - 4;
        this.l = new byte[i11];
        this.m = new byte[4];
        int i12 = i7 + value4;
        System.arraycopy(bArr, i12, this.l, 0, i11);
        System.arraycopy(bArr, (i12 + value5) - 4, this.m, 0, 4);
    }

    @Override // com.excelliance.lbsdk.main.s.l, com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        super.parseFromCentralDirectoryData(bArr, i, i2);
        a(bArr, i, i2);
    }

    @Override // com.excelliance.lbsdk.main.s.l, com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        super.parseFromLocalFileData(bArr, i, i2);
        b(bArr, i, i2);
    }
}
