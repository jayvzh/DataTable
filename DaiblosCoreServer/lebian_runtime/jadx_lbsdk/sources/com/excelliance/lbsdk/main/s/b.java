package com.excelliance.lbsdk.main.s;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class b implements ak, Cloneable {
    private static final ao a = new ao(30062);
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private String e = "";
    private boolean f = false;
    private CRC32 g = new CRC32();

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.b = b(i);
    }

    public void a(boolean z) {
        this.f = z;
        this.b = b(this.b);
    }

    public int b() {
        return this.d;
    }

    protected int b(int i) {
        int i2;
        if (d()) {
            i2 = 40960;
        } else {
            i2 = f() ? 16384 : 32768;
        }
        return (i & 4095) | i2;
    }

    public String c() {
        return this.e;
    }

    public Object clone() {
        try {
            b bVar = (b) super.clone();
            bVar.g = new CRC32();
            return bVar;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean d() {
        return c().length() != 0;
    }

    public int e() {
        return this.b;
    }

    public boolean f() {
        return this.f && !d();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        return getLocalFileDataData();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        return getLocalFileDataLength();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        byte[] bArr = new byte[getLocalFileDataLength().getValue() - 4];
        System.arraycopy(ao.getBytes(e()), 0, bArr, 0, 2);
        byte[] bytes = c().getBytes();
        System.arraycopy(am.getBytes(bytes.length), 0, bArr, 2, 4);
        System.arraycopy(ao.getBytes(a()), 0, bArr, 6, 2);
        System.arraycopy(ao.getBytes(b()), 0, bArr, 8, 2);
        System.arraycopy(bytes, 0, bArr, 10, bytes.length);
        this.g.reset();
        this.g.update(bArr);
        long value = this.g.getValue();
        byte[] bArr2 = new byte[bArr.length + 4];
        System.arraycopy(am.getBytes(value), 0, bArr2, 0, 4);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        return bArr2;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        return new ao(c().getBytes().length + 14);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        long value = am.getValue(bArr, i);
        int i3 = i2 - 4;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i + 4, bArr2, 0, i3);
        this.g.reset();
        this.g.update(bArr2);
        long value2 = this.g.getValue();
        if (value != value2) {
            throw new ZipException("bad CRC checksum " + Long.toHexString(value) + " instead of " + Long.toHexString(value2));
        }
        int value3 = ao.getValue(bArr2, 0);
        byte[] bArr3 = new byte[(int) am.getValue(bArr2, 2)];
        this.c = ao.getValue(bArr2, 6);
        this.d = ao.getValue(bArr2, 8);
        if (bArr3.length == 0) {
            this.e = "";
        } else {
            System.arraycopy(bArr2, 10, bArr3, 0, bArr3.length);
            this.e = new String(bArr3);
        }
        a((value3 & 16384) != 0);
        a(value3);
    }
}
