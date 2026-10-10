package com.excelliance.lbsdk.main.s;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class c {
    private final int[] a;

    public c(int i) {
        this.a = new int[(1 << (i + 1)) - 1];
        Arrays.fill(this.a, -1);
    }

    static c a(InputStream inputStream, int i) throws IOException {
        int i2 = inputStream.read() + 1;
        if (i2 == 0) {
            throw new IOException("Cannot read the size of the encoded tree, unexpected end of stream");
        }
        byte[] bArr = new byte[i2];
        new DataInputStream(inputStream).readFully(bArr);
        int[] iArr = new int[i];
        int length = bArr.length;
        int i3 = 0;
        int iMax = 0;
        int i4 = 0;
        while (i3 < length) {
            byte b = bArr[i3];
            int i5 = ((b & 240) >> 4) + 1;
            int i6 = (b & 15) + 1;
            int i7 = i4;
            int i8 = 0;
            while (i8 < i5) {
                iArr[i7] = i6;
                i8++;
                i7++;
            }
            iMax = Math.max(iMax, i6);
            i3++;
            i4 = i7;
        }
        int[] iArr2 = new int[iArr.length];
        for (int i9 = 0; i9 < iArr2.length; i9++) {
            iArr2[i9] = i9;
        }
        int[] iArr3 = new int[iArr.length];
        int i10 = 0;
        int i11 = 0;
        while (i10 < iArr.length) {
            int i12 = i11;
            for (int i13 = 0; i13 < iArr.length; i13++) {
                if (iArr[i13] == i10) {
                    iArr3[i12] = i10;
                    iArr2[i12] = i13;
                    i12++;
                }
            }
            i10++;
            i11 = i12;
        }
        int[] iArr4 = new int[i];
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        for (int i17 = i - 1; i17 >= 0; i17--) {
            i14 += i15;
            if (iArr3[i17] != i16) {
                int i18 = iArr3[i17];
                i16 = i18;
                i15 = 1 << (16 - i18);
            }
            iArr4[iArr2[i17]] = i14;
        }
        c cVar = new c(iMax);
        for (int i19 = 0; i19 < iArr4.length; i19++) {
            int i20 = iArr[i19];
            if (i20 > 0) {
                cVar.a(0, Integer.reverse(iArr4[i19] << 16), i20, i19);
            }
        }
        return cVar;
    }

    public int a(d dVar) throws IOException {
        int i = 0;
        while (true) {
            int iA = dVar.a();
            if (iA == -1) {
                return -1;
            }
            int i2 = (i * 2) + 1 + iA;
            int i3 = this.a[i2];
            if (i3 != -2) {
                if (i3 != -1) {
                    return i3;
                }
                throw new IOException("The child " + iA + " of node at index " + i + " is not defined");
            }
            i = i2;
        }
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i3 != 0) {
            this.a[i] = -2;
            a((i * 2) + 1 + (i2 & 1), i2 >>> 1, i3 - 1, i4);
            return;
        }
        int[] iArr = this.a;
        if (iArr[i] == -1) {
            iArr[i] = i4;
            return;
        }
        throw new IllegalArgumentException("Tree value at index " + i + " has already been assigned (" + this.a[i] + ")");
    }
}
