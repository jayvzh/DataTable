package com.excelliance.lbsdk.main.s;

import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class ac implements ak {
    static final ao a = new ao(1);
    private static final byte[] b = new byte[0];
    private ah c;
    private ah d;
    private ah e;
    private am f;
    private byte[] g;

    private int a(byte[] bArr) {
        int i;
        ah ahVar = this.c;
        if (ahVar != null) {
            System.arraycopy(ahVar.getBytes(), 0, bArr, 0, 8);
            i = 8;
        } else {
            i = 0;
        }
        ah ahVar2 = this.d;
        if (ahVar2 == null) {
            return i;
        }
        System.arraycopy(ahVar2.getBytes(), 0, bArr, i, 8);
        return i + 8;
    }

    public ah a() {
        return this.c;
    }

    public void a(ah ahVar) {
        this.c = ahVar;
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) throws ZipException {
        if (this.g != null) {
            int i = 0;
            int i2 = (z ? 8 : 0) + (z2 ? 8 : 0) + (z3 ? 8 : 0) + (z4 ? 4 : 0);
            byte[] bArr = this.g;
            if (bArr.length < i2) {
                throw new ZipException("central directory zip64 extended information extra field's length doesn't match central directory data.  Expected length " + i2 + " but is " + this.g.length);
            }
            if (z) {
                this.c = new ah(bArr, 0);
                i = 8;
            }
            if (z2) {
                this.d = new ah(this.g, i);
                i += 8;
            }
            if (z3) {
                this.e = new ah(this.g, i);
                i += 8;
            }
            if (z4) {
                this.f = new am(this.g, i);
            }
        }
    }

    public ah b() {
        return this.d;
    }

    public void b(ah ahVar) {
        this.d = ahVar;
    }

    public ah c() {
        return this.e;
    }

    public void c(ah ahVar) {
        this.e = ahVar;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        byte[] bArr = new byte[getCentralDirectoryLength().getValue()];
        int iA = a(bArr);
        ah ahVar = this.e;
        if (ahVar != null) {
            System.arraycopy(ahVar.getBytes(), 0, bArr, iA, 8);
            iA += 8;
        }
        am amVar = this.f;
        if (amVar != null) {
            System.arraycopy(amVar.getBytes(), 0, bArr, iA, 4);
        }
        return bArr;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        return new ao((this.c != null ? 8 : 0) + (this.d != null ? 8 : 0) + (this.e == null ? 0 : 8) + (this.f != null ? 4 : 0));
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        if (this.c == null && this.d == null) {
            return b;
        }
        if (this.c == null || this.d == null) {
            throw new IllegalArgumentException("Zip64 extended information must contain both size values in the local file header.");
        }
        byte[] bArr = new byte[16];
        a(bArr);
        return bArr;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        return new ao(this.c != null ? 16 : 0);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        this.g = new byte[i2];
        System.arraycopy(bArr, i, this.g, 0, i2);
        if (i2 >= 28) {
            parseFromLocalFileData(bArr, i, i2);
            return;
        }
        if (i2 != 24) {
            if (i2 % 8 == 4) {
                this.f = new am(bArr, (i + i2) - 4);
            }
        } else {
            this.c = new ah(bArr, i);
            int i3 = i + 8;
            this.d = new ah(bArr, i3);
            this.e = new ah(bArr, i3 + 8);
        }
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 == 0) {
            return;
        }
        if (i2 < 16) {
            throw new ZipException("Zip64 extended information must contain both size values in the local file header.");
        }
        this.c = new ah(bArr, i);
        int i3 = i + 8;
        this.d = new ah(bArr, i3);
        int i4 = i3 + 8;
        int i5 = i2 - 16;
        if (i5 >= 8) {
            this.e = new ah(bArr, i4);
            i4 += 8;
            i5 -= 8;
        }
        if (i5 >= 4) {
            this.f = new am(bArr, i4);
        }
    }
}
