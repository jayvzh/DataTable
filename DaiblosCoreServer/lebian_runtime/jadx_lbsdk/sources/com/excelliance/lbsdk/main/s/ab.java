package com.excelliance.lbsdk.main.s;

import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class ab implements ak, Serializable, Cloneable {
    private static final ao a = new ao(30837);
    private static final ao b = new ao(0);
    private static final BigInteger c = BigInteger.valueOf(1000);
    private static final long serialVersionUID = 1;
    private int d = 1;
    private BigInteger e;
    private BigInteger f;

    public ab() {
        a();
    }

    private void a() {
        BigInteger bigInteger = c;
        this.e = bigInteger;
        this.f = bigInteger;
    }

    static byte[] a(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length && bArr[i2] == 0; i2++) {
            i++;
        }
        byte[] bArr2 = new byte[Math.max(1, bArr.length - i)];
        int length2 = bArr2.length - (bArr.length - i);
        System.arraycopy(bArr, i, bArr2, length2, bArr2.length - length2);
        return bArr2;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ab)) {
            return false;
        }
        ab abVar = (ab) obj;
        return this.d == abVar.d && this.e.equals(abVar.e) && this.f.equals(abVar.f);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        return new byte[0];
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        return b;
    }

    public long getGID() {
        return ap.a(this.f);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        byte[] byteArray = this.e.toByteArray();
        byte[] byteArray2 = this.f.toByteArray();
        byte[] bArrA = a(byteArray);
        byte[] bArrA2 = a(byteArray2);
        byte[] bArr = new byte[bArrA.length + 3 + bArrA2.length];
        ap.a(bArrA);
        ap.a(bArrA2);
        bArr[0] = ap.b(this.d);
        bArr[1] = ap.b(bArrA.length);
        System.arraycopy(bArrA, 0, bArr, 2, bArrA.length);
        int length = 2 + bArrA.length;
        bArr[length] = ap.b(bArrA2.length);
        System.arraycopy(bArrA2, 0, bArr, length + 1, bArrA2.length);
        return bArr;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        return new ao(a(this.e.toByteArray()).length + 3 + a(this.f.toByteArray()).length);
    }

    public long getUID() {
        return ap.a(this.e);
    }

    public int hashCode() {
        return ((this.d * (-1234567)) ^ Integer.rotateLeft(this.e.hashCode(), 16)) ^ this.f.hashCode();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        a();
        int i3 = i + 1;
        this.d = ap.a(bArr[i]);
        int i4 = i3 + 1;
        int iA = ap.a(bArr[i3]);
        byte[] bArr2 = new byte[iA];
        System.arraycopy(bArr, i4, bArr2, 0, iA);
        int i5 = i4 + iA;
        this.e = new BigInteger(1, ap.a(bArr2));
        int i6 = i5 + 1;
        int iA2 = ap.a(bArr[i5]);
        byte[] bArr3 = new byte[iA2];
        System.arraycopy(bArr, i6, bArr3, 0, iA2);
        this.f = new BigInteger(1, ap.a(bArr3));
    }

    public void setGID(long j) {
        this.f = ap.a(j);
    }

    public void setUID(long j) {
        this.e = ap.a(j);
    }

    public String toString() {
        return "0x7875 Zip Extra Field: UID=" + this.e + " GID=" + this.f;
    }
}
