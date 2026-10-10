package com.excelliance.lbsdk.main.s;

import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements ak {
    private long a;
    private byte[] b;
    private byte[] c;

    protected a() {
    }

    private void c() {
        byte[] bArr = this.b;
        if (bArr == null) {
            return;
        }
        this.c = new byte[bArr.length + 5];
        this.c[0] = 1;
        System.arraycopy(am.getBytes(this.a), 0, this.c, 1, 4);
        byte[] bArr2 = this.b;
        System.arraycopy(bArr2, 0, this.c, 5, bArr2.length);
    }

    public long a() {
        return this.a;
    }

    public byte[] b() {
        byte[] bArr = this.b;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        if (this.c == null) {
            c();
        }
        byte[] bArr = this.c;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        if (this.c == null) {
            c();
        }
        byte[] bArr = this.c;
        return new ao(bArr != null ? bArr.length : 0);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        return getCentralDirectoryData();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        return getCentralDirectoryLength();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromLocalFileData(bArr, i, i2);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 < 5) {
            throw new ZipException("UniCode path extra data must have at least 5 bytes.");
        }
        byte b = bArr[i];
        if (b != 1) {
            throw new ZipException("Unsupported version [" + ((int) b) + "] for UniCode path extra data.");
        }
        this.a = am.getValue(bArr, i + 1);
        int i3 = i2 - 5;
        this.b = new byte[i3];
        System.arraycopy(bArr, i + 5, this.b, 0, i3);
        this.c = null;
    }
}
