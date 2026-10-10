package com.excelliance.lbsdk.main.s;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
class s extends com.excelliance.lbsdk.main.v.a {
    private final boolean[] b;

    public s(InputStream inputStream) {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        b(9);
        c(13);
        this.b = new boolean[f()];
        for (int i = 0; i < 256; i++) {
            this.b[i] = true;
        }
        e(g() + 1);
    }

    private void i() {
        boolean[] zArr = new boolean[8192];
        int i = 0;
        while (true) {
            boolean[] zArr2 = this.b;
            if (i >= zArr2.length) {
                break;
            }
            if (zArr2[i] && d(i) != -1) {
                zArr[d(i)] = true;
            }
            i++;
        }
        for (int iG = g() + 1; iG < zArr.length; iG++) {
            if (!zArr[iG]) {
                this.b[iG] = false;
                a(iG, -1);
            }
        }
    }

    @Override // com.excelliance.lbsdk.main.v.a
    protected int a() throws IOException {
        int iB = b();
        if (iB < 0) {
            return -1;
        }
        boolean z = false;
        if (iB != g()) {
            if (!this.b[iB]) {
                iB = c();
                z = true;
            }
            return a(iB, z);
        }
        int iB2 = b();
        if (iB2 < 0) {
            throw new IOException("Unexpected EOF;");
        }
        if (iB2 == 1) {
            if (d() >= 13) {
                throw new IOException("Attempt to increase code size beyond maximum");
            }
            e();
        } else {
            if (iB2 != 2) {
                throw new IOException("Invalid clear code subcode " + iB2);
            }
            i();
            e(g() + 1);
        }
        return 0;
    }

    @Override // com.excelliance.lbsdk.main.v.a
    protected int a(int i, byte b) {
        int iH = h();
        while (iH < 8192 && this.b[iH]) {
            iH++;
        }
        e(iH);
        int iA = a(i, b, 8192);
        if (iA >= 0) {
            this.b[iA] = true;
        }
        return iA;
    }
}
