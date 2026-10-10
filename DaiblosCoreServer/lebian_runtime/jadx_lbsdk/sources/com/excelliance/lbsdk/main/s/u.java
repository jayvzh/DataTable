package com.excelliance.lbsdk.main.s;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class u implements ak {
    private static final ao a = new ao(10);
    private static final ao b = new ao(1);
    private static final ao c = new ao(24);
    private ah d = ah.ZERO;
    private ah e = ah.ZERO;
    private ah f = ah.ZERO;

    private static Date a(ah ahVar) {
        if (ahVar == null || ah.ZERO.equals(ahVar)) {
            return null;
        }
        return new Date((ahVar.getLongValue() - 116444736000000000L) / 10000);
    }

    private void a(byte[] bArr, int i, int i2) {
        if (i2 >= 26) {
            if (c.equals(new ao(bArr, i))) {
                int i3 = i + 2;
                this.d = new ah(bArr, i3);
                int i4 = i3 + 8;
                this.e = new ah(bArr, i4);
                this.f = new ah(bArr, i4 + 8);
            }
        }
    }

    private void d() {
        this.d = ah.ZERO;
        this.e = ah.ZERO;
        this.f = ah.ZERO;
    }

    public Date a() {
        return a(this.d);
    }

    public Date b() {
        return a(this.e);
    }

    public Date c() {
        return a(this.f);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        ah ahVar = this.d;
        ah ahVar2 = uVar.d;
        if (ahVar != ahVar2 && (ahVar == null || !ahVar.equals(ahVar2))) {
            return false;
        }
        ah ahVar3 = this.e;
        ah ahVar4 = uVar.e;
        if (ahVar3 != ahVar4 && (ahVar3 == null || !ahVar3.equals(ahVar4))) {
            return false;
        }
        ah ahVar5 = this.f;
        ah ahVar6 = uVar.f;
        return ahVar5 == ahVar6 || (ahVar5 != null && ahVar5.equals(ahVar6));
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
        byte[] bArr = new byte[getLocalFileDataLength().getValue()];
        System.arraycopy(b.getBytes(), 0, bArr, 4, 2);
        System.arraycopy(c.getBytes(), 0, bArr, 6, 2);
        System.arraycopy(this.d.getBytes(), 0, bArr, 8, 8);
        System.arraycopy(this.e.getBytes(), 0, bArr, 16, 8);
        System.arraycopy(this.f.getBytes(), 0, bArr, 24, 8);
        return bArr;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        return new ao(32);
    }

    public int hashCode() {
        ah ahVar = this.d;
        int iHashCode = ahVar != null ? (-123) ^ ahVar.hashCode() : -123;
        ah ahVar2 = this.e;
        if (ahVar2 != null) {
            iHashCode ^= Integer.rotateLeft(ahVar2.hashCode(), 11);
        }
        ah ahVar3 = this.f;
        return ahVar3 != null ? iHashCode ^ Integer.rotateLeft(ahVar3.hashCode(), 22) : iHashCode;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        d();
        parseFromLocalFileData(bArr, i, i2);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        int value = i + 4;
        while (value + 4 <= i3) {
            ao aoVar = new ao(bArr, value);
            int i4 = value + 2;
            if (aoVar.equals(b)) {
                a(bArr, i4, i3 - i4);
                return;
            }
            value = i4 + new ao(bArr, i4).getValue() + 2;
        }
    }

    public String toString() {
        return "0x000A Zip Extra Field: Modify:[" + a() + "]  Access:[" + b() + "]  Create:[" + c() + "] ";
    }
}
