package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public class r implements ak {
    private ao a;
    private byte[] b;
    private byte[] c;

    public void a(ao aoVar) {
        this.a = aoVar;
    }

    public void a(byte[] bArr) {
        this.b = ap.b(bArr);
    }

    public void b(byte[] bArr) {
        this.c = ap.b(bArr);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        byte[] bArr = this.c;
        return bArr != null ? ap.b(bArr) : getLocalFileDataData();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        byte[] bArr = this.c;
        return bArr != null ? new ao(bArr.length) : getLocalFileDataLength();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return this.a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        return ap.b(this.b);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        byte[] bArr = this.b;
        return new ao(bArr != null ? bArr.length : 0);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        b(bArr2);
        if (this.b == null) {
            a(bArr2);
        }
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        a(bArr2);
    }
}
