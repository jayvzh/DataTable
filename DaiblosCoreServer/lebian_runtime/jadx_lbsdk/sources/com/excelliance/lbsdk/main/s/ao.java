package com.excelliance.lbsdk.main.s;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class ao implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    private final int a;

    public ao(int i) {
        this.a = i;
    }

    public ao(byte[] bArr) {
        this(bArr, 0);
    }

    public ao(byte[] bArr, int i) {
        this.a = getValue(bArr, i);
    }

    public static byte[] getBytes(int i) {
        byte[] bArr = new byte[2];
        putShort(i, bArr, 0);
        return bArr;
    }

    public static int getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public static int getValue(byte[] bArr, int i) {
        return ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public static void putShort(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i & 65280) >> 8);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ao) && this.a == ((ao) obj).getValue();
    }

    public byte[] getBytes() {
        int i = this.a;
        return new byte[]{(byte) (i & 255), (byte) ((i & 65280) >> 8)};
    }

    public int getValue() {
        return this.a;
    }

    public int hashCode() {
        return this.a;
    }

    public String toString() {
        return "ZipShort value: " + this.a;
    }
}
