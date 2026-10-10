package com.excelliance.lbsdk.main.s;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class aa implements ak, Serializable, Cloneable {
    public static final byte ACCESS_TIME_BIT = 2;
    public static final byte CREATE_TIME_BIT = 4;
    public static final byte MODIFY_TIME_BIT = 1;
    private static final ao a = new ao(21589);
    private static final long serialVersionUID = 1;
    private byte b;
    private boolean c;
    private boolean d;
    private boolean e;
    private am f;
    private am g;
    private am h;

    private static am a(Date date) {
        if (date == null) {
            return null;
        }
        long time = date.getTime() / 1000;
        if (time < 4294967296L) {
            return new am(time);
        }
        throw new IllegalArgumentException("Cannot set an X5455 timestamp larger than 2^32: " + time);
    }

    private void a() {
        setFlags((byte) 0);
        this.f = null;
        this.g = null;
        this.h = null;
    }

    public Object clone() {
        return super.clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof aa)) {
            return false;
        }
        aa aaVar = (aa) obj;
        if ((this.b & 7) != (aaVar.b & 7)) {
            return false;
        }
        am amVar = this.f;
        am amVar2 = aaVar.f;
        if (amVar != amVar2 && (amVar == null || !amVar.equals(amVar2))) {
            return false;
        }
        am amVar3 = this.g;
        am amVar4 = aaVar.g;
        if (amVar3 != amVar4 && (amVar3 == null || !amVar3.equals(amVar4))) {
            return false;
        }
        am amVar5 = this.h;
        am amVar6 = aaVar.h;
        return amVar5 == amVar6 || (amVar5 != null && amVar5.equals(amVar6));
    }

    public Date getAccessJavaTime() {
        am amVar = this.g;
        if (amVar != null) {
            return new Date(amVar.getValue() * 1000);
        }
        return null;
    }

    public am getAccessTime() {
        return this.g;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        byte[] bArr = new byte[getCentralDirectoryLength().getValue()];
        System.arraycopy(getLocalFileDataData(), 0, bArr, 0, bArr.length);
        return bArr;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        return new ao((this.c ? 4 : 0) + 1);
    }

    public Date getCreateJavaTime() {
        am amVar = this.h;
        if (amVar != null) {
            return new Date(amVar.getValue() * 1000);
        }
        return null;
    }

    public am getCreateTime() {
        return this.h;
    }

    public byte getFlags() {
        return this.b;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        am amVar;
        am amVar2;
        byte[] bArr = new byte[getLocalFileDataLength().getValue()];
        bArr[0] = 0;
        int i = 1;
        if (this.c) {
            bArr[0] = (byte) (bArr[0] | 1);
            System.arraycopy(this.f.getBytes(), 0, bArr, 1, 4);
            i = 5;
        }
        if (this.d && (amVar2 = this.g) != null) {
            bArr[0] = (byte) (bArr[0] | 2);
            System.arraycopy(amVar2.getBytes(), 0, bArr, i, 4);
            i += 4;
        }
        if (this.e && (amVar = this.h) != null) {
            bArr[0] = (byte) (bArr[0] | 4);
            System.arraycopy(amVar.getBytes(), 0, bArr, i, 4);
        }
        return bArr;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        return new ao((this.c ? 4 : 0) + 1 + ((!this.d || this.g == null) ? 0 : 4) + ((!this.e || this.h == null) ? 0 : 4));
    }

    public Date getModifyJavaTime() {
        am amVar = this.f;
        if (amVar != null) {
            return new Date(amVar.getValue() * 1000);
        }
        return null;
    }

    public am getModifyTime() {
        return this.f;
    }

    public int hashCode() {
        int iRotateLeft = (this.b & 7) * (-123);
        am amVar = this.f;
        if (amVar != null) {
            iRotateLeft ^= amVar.hashCode();
        }
        am amVar2 = this.g;
        if (amVar2 != null) {
            iRotateLeft ^= Integer.rotateLeft(amVar2.hashCode(), 11);
        }
        am amVar3 = this.h;
        return amVar3 != null ? iRotateLeft ^ Integer.rotateLeft(amVar3.hashCode(), 22) : iRotateLeft;
    }

    public boolean isBit0_modifyTimePresent() {
        return this.c;
    }

    public boolean isBit1_accessTimePresent() {
        return this.d;
    }

    public boolean isBit2_createTimePresent() {
        return this.e;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        a();
        parseFromLocalFileData(bArr, i, i2);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        int i3;
        a();
        int i4 = i2 + i;
        int i5 = i + 1;
        setFlags(bArr[i]);
        if (this.c) {
            this.f = new am(bArr, i5);
            i5 += 4;
        }
        if (!this.d || (i3 = i5 + 4) > i4) {
            i3 = i5;
        } else {
            this.g = new am(bArr, i5);
        }
        if (!this.e || i3 + 4 > i4) {
            return;
        }
        this.h = new am(bArr, i3);
    }

    public void setAccessJavaTime(Date date) {
        setAccessTime(a(date));
    }

    public void setAccessTime(am amVar) {
        this.d = amVar != null;
        this.b = (byte) (amVar != null ? this.b | 2 : this.b & (-3));
        this.g = amVar;
    }

    public void setCreateJavaTime(Date date) {
        setCreateTime(a(date));
    }

    public void setCreateTime(am amVar) {
        this.e = amVar != null;
        this.b = (byte) (amVar != null ? this.b | 4 : this.b & (-5));
        this.h = amVar;
    }

    public void setFlags(byte b) {
        this.b = b;
        this.c = (b & 1) == 1;
        this.d = (b & 2) == 2;
        this.e = (b & 4) == 4;
    }

    public void setModifyJavaTime(Date date) {
        setModifyTime(a(date));
    }

    public void setModifyTime(am amVar) {
        this.c = amVar != null;
        this.b = (byte) (amVar != null ? 1 | this.b : this.b & (-2));
        this.f = amVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("0x5455 Zip Extra Field: Flags=");
        sb.append(Integer.toBinaryString(ap.b((int) this.b)));
        sb.append(" ");
        if (this.c && this.f != null) {
            Date modifyJavaTime = getModifyJavaTime();
            sb.append(" Modify:[");
            sb.append(modifyJavaTime);
            sb.append("] ");
        }
        if (this.d && this.g != null) {
            Date accessJavaTime = getAccessJavaTime();
            sb.append(" Access:[");
            sb.append(accessJavaTime);
            sb.append("] ");
        }
        if (this.e && this.h != null) {
            Date createJavaTime = getCreateJavaTime();
            sb.append(" Create:[");
            sb.append(createJavaTime);
            sb.append("] ");
        }
        return sb.toString();
    }
}
