package com.excelliance.lbsdk.main.u;

import com.excelliance.lbsdk.main.AbTestManager;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.excelliance.lbsdk.main.t.a {
    private int a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private int f;
    private final b g;
    private int h;
    private InputStream i;
    private final boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private char x;
    private C0005a y;

    /* JADX INFO: renamed from: com.excelliance.lbsdk.main.u.a$a, reason: collision with other inner class name */
    private static final class C0005a {
        final boolean[] a = new boolean[256];
        final byte[] b = new byte[256];
        final byte[] c = new byte[18002];
        final byte[] d = new byte[18002];
        final int[] e = new int[256];
        final int[][] f = (int[][]) Array.newInstance((Class<?>) int.class, 6, 258);
        final int[][] g = (int[][]) Array.newInstance((Class<?>) int.class, 6, 258);
        final int[][] h = (int[][]) Array.newInstance((Class<?>) int.class, 6, 258);
        final int[] i = new int[6];
        final int[] j = new int[257];
        final char[] k = new char[256];
        final char[][] l = (char[][]) Array.newInstance((Class<?>) char.class, 6, 258);
        final byte[] m = new byte[6];
        int[] n;
        byte[] o;

        C0005a(int i) {
            this.o = new byte[i * 100000];
        }

        int[] a(int i) {
            int[] iArr = this.n;
            if (iArr != null && iArr.length >= i) {
                return iArr;
            }
            int[] iArr2 = new int[i];
            this.n = iArr2;
            return iArr2;
        }
    }

    public a(InputStream inputStream) {
        this(inputStream, false);
    }

    public a(InputStream inputStream, boolean z) throws IOException {
        this.g = new b();
        this.k = 1;
        this.i = inputStream;
        this.j = z;
        a(true);
        c();
    }

    private void a() {
        boolean[] zArr = this.y.a;
        byte[] bArr = this.y.b;
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            if (zArr[i2]) {
                bArr[i] = (byte) i2;
                i++;
            }
        }
        this.h = i;
    }

    private void a(int i, int i2) {
        C0005a c0005a = this.y;
        char[][] cArr = c0005a.l;
        int[] iArr = c0005a.i;
        int[][] iArr2 = c0005a.f;
        int[][] iArr3 = c0005a.g;
        int[][] iArr4 = c0005a.h;
        for (int i3 = 0; i3 < i2; i3++) {
            char[] cArr2 = cArr[i3];
            int i4 = i;
            char c = 0;
            char c2 = ' ';
            while (true) {
                i4--;
                if (i4 >= 0) {
                    char c3 = cArr2[i4];
                    if (c3 > c) {
                        c = c3;
                    }
                    if (c3 < c2) {
                        c2 = c3;
                    }
                }
            }
            a(iArr2[i3], iArr3[i3], iArr4[i3], cArr[i3], c2, c, i);
            iArr[i3] = c2;
        }
    }

    private static void a(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = i;
        int i6 = 0;
        while (i5 <= i2) {
            int i7 = i6;
            for (int i8 = 0; i8 < i3; i8++) {
                if (cArr[i8] == i5) {
                    iArr3[i7] = i8;
                    i7++;
                }
            }
            i5++;
            i6 = i7;
        }
        int i9 = 23;
        while (true) {
            i9--;
            if (i9 <= 0) {
                break;
            }
            iArr2[i9] = 0;
            iArr[i9] = 0;
        }
        for (int i10 = 0; i10 < i3; i10++) {
            int i11 = cArr[i10] + 1;
            iArr2[i11] = iArr2[i11] + 1;
        }
        int i12 = iArr2[0];
        for (int i13 = 1; i13 < 23; i13++) {
            i12 += iArr2[i13];
            iArr2[i13] = i12;
        }
        int i14 = iArr2[i];
        int i15 = i;
        while (i15 <= i2) {
            int i16 = i15 + 1;
            int i17 = iArr2[i16];
            int i18 = i4 + (i17 - i14);
            iArr[i15] = i18 - 1;
            i4 = i18 << 1;
            i15 = i16;
            i14 = i17;
        }
        for (int i19 = i + 1; i19 <= i2; i19++) {
            iArr2[i19] = ((iArr[i19 - 1] + 1) << 1) - iArr2[i19];
        }
    }

    private boolean a(boolean z) throws IOException {
        InputStream inputStream = this.i;
        if (inputStream == null) {
            throw new IOException("No InputStream");
        }
        int i = inputStream.read();
        if (i == -1 && !z) {
            return false;
        }
        int i2 = this.i.read();
        int i3 = this.i.read();
        if (i != 66 || i2 != 90 || i3 != 104) {
            throw new IOException(z ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream");
        }
        int i4 = this.i.read();
        if (i4 < 49 || i4 > 57) {
            throw new IOException("BZip2 block size is invalid");
        }
        this.c = i4 - 48;
        this.f = 0;
        this.o = 0;
        return true;
    }

    private int b() {
        switch (this.k) {
            case AbTestManager.ABTEST_TARGET_FIRST_DIALOG /* 0 */:
                return -1;
            case 1:
                return k();
            case 2:
                throw new IllegalStateException();
            case 3:
                return n();
            case 4:
                return o();
            case 5:
                throw new IllegalStateException();
            case 6:
                return p();
            case 7:
                return q();
            default:
                throw new IllegalStateException();
        }
    }

    private int b(int i) throws IOException {
        int i2 = this.f;
        int i3 = this.e;
        if (i2 < i) {
            InputStream inputStream = this.i;
            do {
                int i4 = inputStream.read();
                if (i4 < 0) {
                    throw new IOException("unexpected end of stream");
                }
                i3 = (i3 << 8) | i4;
                i2 += 8;
            } while (i2 < i);
            this.e = i3;
        }
        int i5 = i2 - i;
        this.f = i5;
        return ((1 << i) - 1) & (i3 >> i5);
    }

    private int c(int i) throws IOException {
        InputStream inputStream = this.i;
        C0005a c0005a = this.y;
        int i2 = c0005a.c[i] & 255;
        int[] iArr = c0005a.f[i2];
        int i3 = c0005a.i[i2];
        int iB = b(i3);
        int i4 = this.f;
        int i5 = this.e;
        while (iB > iArr[i3]) {
            i3++;
            while (i4 < 1) {
                int i6 = inputStream.read();
                if (i6 < 0) {
                    throw new IOException("unexpected end of stream");
                }
                i5 = (i5 << 8) | i6;
                i4 += 8;
            }
            i4--;
            iB = (iB << 1) | (1 & (i5 >> i4));
        }
        this.f = i4;
        this.e = i5;
        return c0005a.h[i2][iB - c0005a.g[i2][i3]];
    }

    private void c() throws IOException {
        do {
            char cG = g();
            char cG2 = g();
            char cG3 = g();
            char cG4 = g();
            char cG5 = g();
            char cG6 = g();
            if (cG != 23 || cG2 != 'r' || cG3 != 'E' || cG4 != '8' || cG5 != 'P' || cG6 != 144) {
                if (cG != '1' || cG2 != 'A' || cG3 != 'Y' || cG4 != '&' || cG5 != 'S' || cG6 != 'Y') {
                    this.k = 0;
                    throw new IOException("bad block header");
                }
                this.l = h();
                this.d = b(1) == 1;
                if (this.y == null) {
                    this.y = new C0005a(this.c);
                }
                j();
                this.g.a();
                this.k = 1;
                return;
            }
        } while (!e());
    }

    private void d() throws IOException {
        this.n = this.g.b();
        int i = this.l;
        int i2 = this.n;
        if (i == i2) {
            int i3 = this.o;
            this.o = (i3 >>> 31) | (i3 << 1);
            this.o ^= i2;
            return;
        }
        int i4 = this.m;
        this.o = (i4 >>> 31) | (i4 << 1);
        this.o = i ^ this.o;
        throw new IOException("BZip2 CRC error");
    }

    private boolean e() throws IOException {
        this.m = h();
        this.k = 0;
        this.y = null;
        if (this.m == this.o) {
            return (this.j && a(false)) ? false : true;
        }
        throw new IOException("BZip2 CRC error");
    }

    private boolean f() {
        return b(1) != 0;
    }

    private char g() {
        return (char) b(8);
    }

    private int h() {
        return b(8) | (((((b(8) << 8) | b(8)) << 8) | b(8)) << 8);
    }

    private void i() throws IOException {
        C0005a c0005a = this.y;
        boolean[] zArr = c0005a.a;
        byte[] bArr = c0005a.m;
        byte[] bArr2 = c0005a.c;
        byte[] bArr3 = c0005a.d;
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            if (f()) {
                i |= 1 << i2;
            }
        }
        int i3 = 256;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            } else {
                zArr[i3] = false;
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            if (((1 << i4) & i) != 0) {
                int i5 = i4 << 4;
                for (int i6 = 0; i6 < 16; i6++) {
                    if (f()) {
                        zArr[i5 + i6] = true;
                    }
                }
            }
        }
        a();
        int i7 = this.h + 2;
        int iB = b(3);
        int iB2 = b(15);
        for (int i8 = 0; i8 < iB2; i8++) {
            int i9 = 0;
            while (f()) {
                i9++;
            }
            bArr3[i8] = (byte) i9;
        }
        int i10 = iB;
        while (true) {
            i10--;
            if (i10 < 0) {
                break;
            } else {
                bArr[i10] = (byte) i10;
            }
        }
        for (int i11 = 0; i11 < iB2; i11++) {
            int i12 = bArr3[i11] & 255;
            byte b = bArr[i12];
            while (i12 > 0) {
                bArr[i12] = bArr[i12 - 1];
                i12--;
            }
            bArr[0] = b;
            bArr2[i11] = b;
        }
        char[][] cArr = c0005a.l;
        for (int i13 = 0; i13 < iB; i13++) {
            int iB3 = b(5);
            char[] cArr2 = cArr[i13];
            int i14 = iB3;
            for (int i15 = 0; i15 < i7; i15++) {
                while (f()) {
                    i14 += f() ? -1 : 1;
                }
                cArr2[i15] = (char) i14;
            }
        }
        a(i7, iB);
    }

    private void j() throws IOException {
        int i;
        int i2;
        int i3;
        char c;
        int i4;
        this = this;
        this.b = this.b(24);
        i();
        InputStream inputStream = this.i;
        C0005a c0005a = this.y;
        byte[] bArr = c0005a.o;
        int[] iArr = c0005a.e;
        byte[] bArr2 = c0005a.c;
        byte[] bArr3 = c0005a.b;
        char[] cArr = c0005a.k;
        int[] iArr2 = c0005a.i;
        int[][] iArr3 = c0005a.f;
        int[][] iArr4 = c0005a.g;
        int[][] iArr5 = c0005a.h;
        int i5 = this.c * 100000;
        int i6 = 256;
        while (true) {
            i6--;
            if (i6 < 0) {
                break;
            }
            cArr[i6] = (char) i6;
            iArr[i6] = 0;
        }
        int i7 = this.h + 1;
        int iC = this.c(0);
        int i8 = this.e;
        int i9 = this.f;
        int i10 = bArr2[0] & 255;
        int[] iArr6 = iArr4[i10];
        int[] iArr7 = iArr3[i10];
        int i11 = i9;
        int i12 = i8;
        int[] iArr8 = iArr5[i10];
        int i13 = -1;
        int i14 = 49;
        int[] iArr9 = iArr7;
        int[] iArr10 = iArr6;
        int i15 = iArr2[i10];
        int i16 = iC;
        int i17 = 0;
        while (i16 != i7) {
            i7 = i7;
            if (i16 == 0 || i16 == 1) {
                int[] iArr11 = iArr8;
                int i18 = -1;
                int[] iArr12 = iArr9;
                int[] iArr13 = iArr10;
                int i19 = i15;
                int i20 = i17;
                int i21 = 1;
                while (true) {
                    if (i16 == 0) {
                        i2 = i18 + i21;
                        i = i13;
                    } else {
                        i = i13;
                        if (i16 != 1) {
                            int[][] iArr14 = iArr5;
                            byte b = bArr3[cArr[0]];
                            int i22 = b & 255;
                            iArr[i22] = iArr[i22] + i18 + 1;
                            i13 = i;
                            while (true) {
                                int i23 = i18 - 1;
                                if (i18 < 0) {
                                    break;
                                }
                                i13++;
                                bArr[i13] = b;
                                i18 = i23;
                            }
                            if (i13 >= i5) {
                                throw new IOException("block overrun");
                            }
                            i17 = i20;
                            i15 = i19;
                            iArr10 = iArr13;
                            iArr9 = iArr12;
                            iArr8 = iArr11;
                            iArr5 = iArr14;
                            break;
                        }
                        i2 = i18 + (i21 << 1);
                    }
                    if (i14 == 0) {
                        i20++;
                        int i24 = bArr2[i20] & 255;
                        iArr13 = iArr4[i24];
                        iArr12 = iArr3[i24];
                        iArr11 = iArr5[i24];
                        i3 = iArr2[i24];
                        i14 = 49;
                    } else {
                        i14--;
                        i3 = i19;
                    }
                    int i25 = i11;
                    while (i25 < i3) {
                        int i26 = inputStream.read();
                        if (i26 < 0) {
                            throw new IOException("unexpected end of stream");
                        }
                        i12 = (i12 << 8) | i26;
                        i25 += 8;
                    }
                    int i27 = i25 - i3;
                    int i28 = i2;
                    i11 = i27;
                    int i29 = (i12 >> i27) & ((1 << i3) - 1);
                    int i30 = i3;
                    while (i29 > iArr12[i30]) {
                        int i31 = i30 + 1;
                        int[][] iArr15 = iArr5;
                        int i32 = i11;
                        while (i32 < 1) {
                            int i33 = inputStream.read();
                            if (i33 < 0) {
                                throw new IOException("unexpected end of stream");
                            }
                            i12 = (i12 << 8) | i33;
                            i32 += 8;
                        }
                        i11 = i32 - 1;
                        i29 = (i29 << 1) | ((i12 >> i11) & 1);
                        i30 = i31;
                        iArr5 = iArr15;
                    }
                    int i34 = iArr11[i29 - iArr13[i30]];
                    i21 <<= 1;
                    i19 = i3;
                    i13 = i;
                    i16 = i34;
                    i18 = i28;
                }
            } else {
                i13++;
                if (i13 >= i5) {
                    throw new IOException("block overrun");
                }
                int i35 = i16 - 1;
                char c2 = cArr[i35];
                int i36 = bArr3[c2] & 255;
                iArr[i36] = iArr[i36] + 1;
                bArr[i13] = bArr3[c2];
                if (i16 <= 16) {
                    while (i35 > 0) {
                        int i37 = i35 - 1;
                        cArr[i35] = cArr[i37];
                        i35 = i37;
                    }
                    c = 0;
                } else {
                    c = 0;
                    System.arraycopy(cArr, 0, cArr, 1, i35);
                }
                cArr[c] = c2;
                if (i14 == 0) {
                    i17++;
                    int i38 = bArr2[i17] & 255;
                    int[] iArr16 = iArr4[i38];
                    int[] iArr17 = iArr3[i38];
                    int[] iArr18 = iArr5[i38];
                    i4 = iArr2[i38];
                    iArr10 = iArr16;
                    iArr9 = iArr17;
                    iArr8 = iArr18;
                    i14 = 49;
                } else {
                    i14--;
                    i4 = i15;
                }
                int i39 = i11;
                while (i39 < i4) {
                    int i40 = inputStream.read();
                    if (i40 < 0) {
                        throw new IOException("unexpected end of stream");
                    }
                    i12 = (i12 << 8) | i40;
                    i39 += 8;
                }
                int i41 = i39 - i4;
                int i42 = (i12 >> i41) & ((1 << i4) - 1);
                i11 = i41;
                int i43 = i4;
                while (i42 > iArr9[i43]) {
                    i43++;
                    int i44 = i4;
                    int i45 = i11;
                    while (i45 < 1) {
                        int i46 = inputStream.read();
                        if (i46 < 0) {
                            throw new IOException("unexpected end of stream");
                        }
                        i12 = (i12 << 8) | i46;
                        i45 += 8;
                    }
                    i11 = i45 - 1;
                    i42 = (i42 << 1) | ((i12 >> i11) & 1);
                    i4 = i44;
                }
                i16 = iArr8[i42 - iArr10[i43]];
                i15 = i4;
            }
        }
        this.a = i13;
        this.f = i11;
        this.e = i12;
    }

    private int k() throws IOException {
        C0005a c0005a;
        if (this.k == 0 || (c0005a = this.y) == null) {
            return -1;
        }
        int[] iArr = c0005a.j;
        int[] iArrA = this.y.a(this.a + 1);
        byte[] bArr = this.y.o;
        iArr[0] = 0;
        System.arraycopy(this.y.e, 0, iArr, 1, 256);
        int i = iArr[0];
        for (int i2 = 1; i2 <= 256; i2++) {
            i += iArr[i2];
            iArr[i2] = i;
        }
        int i3 = this.a;
        for (int i4 = 0; i4 <= i3; i4++) {
            int i5 = bArr[i4] & 255;
            int i6 = iArr[i5];
            iArr[i5] = i6 + 1;
            iArrA[i6] = i4;
        }
        int i7 = this.b;
        if (i7 < 0 || i7 >= iArrA.length) {
            throw new IOException("stream corrupted");
        }
        this.w = iArrA[i7];
        this.p = 0;
        this.s = 0;
        this.q = 256;
        if (!this.d) {
            return m();
        }
        this.u = 0;
        this.v = 0;
        return l();
    }

    private int l() throws IOException {
        if (this.s > this.a) {
            d();
            c();
            return k();
        }
        this.r = this.q;
        int i = this.y.o[this.w] & 255;
        this.w = this.y.n[this.w];
        int i2 = this.u;
        if (i2 == 0) {
            this.u = c.a(this.v) - 1;
            int i3 = this.v + 1;
            this.v = i3;
            if (i3 == 512) {
                this.v = 0;
            }
        } else {
            this.u = i2 - 1;
        }
        int i4 = i ^ (this.u == 1 ? 1 : 0);
        this.q = i4;
        this.s++;
        this.k = 3;
        this.g.a(i4);
        return i4;
    }

    private int m() throws IOException {
        if (this.s > this.a) {
            this.k = 5;
            d();
            c();
            return k();
        }
        this.r = this.q;
        int i = this.y.o[this.w] & 255;
        this.q = i;
        this.w = this.y.n[this.w];
        this.s++;
        this.k = 6;
        this.g.a(i);
        return i;
    }

    private int n() {
        if (this.q != this.r) {
            this.k = 2;
            this.p = 1;
        } else {
            int i = this.p + 1;
            this.p = i;
            if (i >= 4) {
                this.x = (char) (this.y.o[this.w] & 255);
                this.w = this.y.n[this.w];
                int i2 = this.u;
                if (i2 == 0) {
                    this.u = c.a(this.v) - 1;
                    int i3 = this.v + 1;
                    this.v = i3;
                    if (i3 == 512) {
                        this.v = 0;
                    }
                } else {
                    this.u = i2 - 1;
                }
                this.t = 0;
                this.k = 4;
                if (this.u == 1) {
                    this.x = (char) (this.x ^ 1);
                }
                return o();
            }
            this.k = 2;
        }
        return l();
    }

    private int o() {
        if (this.t < this.x) {
            this.g.a(this.q);
            this.t++;
            return this.q;
        }
        this.k = 2;
        this.s++;
        this.p = 0;
        return l();
    }

    private int p() {
        if (this.q != this.r) {
            this.p = 1;
            return m();
        }
        int i = this.p + 1;
        this.p = i;
        if (i < 4) {
            return m();
        }
        this.x = (char) (this.y.o[this.w] & 255);
        this.w = this.y.n[this.w];
        this.t = 0;
        return q();
    }

    private int q() {
        if (this.t >= this.x) {
            this.s++;
            this.p = 0;
            return m();
        }
        int i = this.q;
        this.g.a(i);
        this.t++;
        this.k = 7;
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        InputStream inputStream = this.i;
        if (inputStream != null) {
            try {
                if (inputStream != System.in) {
                    inputStream.close();
                }
            } finally {
                this.y = null;
                this.i = null;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.i == null) {
            throw new IOException("stream closed");
        }
        int iB = b();
        a(iB < 0 ? -1 : 1);
        return iB;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
        int i3 = i + i2;
        if (i3 > bArr.length) {
            throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > dest.length(" + bArr.length + ").");
        }
        if (this.i == null) {
            throw new IOException("stream closed");
        }
        if (i2 == 0) {
            return 0;
        }
        int i4 = i;
        while (i4 < i3) {
            int iB = b();
            if (iB < 0) {
                break;
            }
            bArr[i4] = (byte) iB;
            a(1);
            i4++;
        }
        if (i4 == i) {
            return -1;
        }
        return i4 - i;
    }
}
