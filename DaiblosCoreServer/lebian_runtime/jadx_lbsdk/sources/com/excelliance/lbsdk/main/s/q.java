package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public final class q implements ak {
    private static final ao a = new ao(44225);
    private byte[] b;
    private byte[] c;

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        byte[] bArr = this.c;
        return bArr == null ? getLocalFileDataData() : ap.b(bArr);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        byte[] bArr = this.c;
        return bArr == null ? getLocalFileDataLength() : new ao(bArr.length);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        return ap.b(this.b);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        byte[] bArr = this.b;
        return new ao(bArr == null ? 0 : bArr.length);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        this.c = new byte[i2];
        System.arraycopy(bArr, i, this.c, 0, i2);
        if (this.b == null) {
            parseFromLocalFileData(bArr, i, i2);
        }
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        this.b = new byte[i2];
        System.arraycopy(bArr, i, this.b, 0, i2);
    }
}
