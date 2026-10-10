package com.excelliance.lbsdk.main.s;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class am implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    private final long b;
    public static final am CFH_SIG = new am(33639248);
    public static final am LFH_SIG = new am(67324752);
    public static final am DD_SIG = new am(134695760);
    static final am a = new am(4294967295L);
    public static final am SINGLE_SEGMENT_SPLIT_MARKER = new am(808471376);
    public static final am AED_SIG = new am(134630224);

    public am(long j) {
        this.b = j;
    }

    public am(byte[] bArr) {
        this(bArr, 0);
    }

    public am(byte[] bArr, int i) {
        this.b = getValue(bArr, i);
    }

    public static byte[] getBytes(long j) {
        byte[] bArr = new byte[4];
        putLong(j, bArr, 0);
        return bArr;
    }

    public static long getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public static long getValue(byte[] bArr, int i) {
        return (((long) (bArr[i + 3] << 24)) & 4278190080L) + ((long) ((bArr[i + 2] << 16) & 16711680)) + ((long) ((bArr[i + 1] << 8) & 65280)) + ((long) (bArr[i] & 255));
    }

    public static void putLong(long j, byte[] bArr, int i) {
        int i2 = i + 1;
        bArr[i] = (byte) (255 & j);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((65280 & j) >> 8);
        bArr[i3] = (byte) ((16711680 & j) >> 16);
        bArr[i3 + 1] = (byte) ((j & 4278190080L) >> 24);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof am) && this.b == ((am) obj).getValue();
    }

    public byte[] getBytes() {
        return getBytes(this.b);
    }

    public long getValue() {
        return this.b;
    }

    public int hashCode() {
        return (int) this.b;
    }

    public void putLong(byte[] bArr, int i) {
        putLong(this.b, bArr, i);
    }

    public String toString() {
        return "ZipLong value: " + this.b;
    }
}
