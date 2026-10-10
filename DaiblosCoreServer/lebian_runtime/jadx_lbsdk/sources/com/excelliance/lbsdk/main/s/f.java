package com.excelliance.lbsdk.main.s;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class f extends InputStream {
    private final InputStream a;
    private d b;
    private final int c;
    private final int d;
    private final int e;
    private c f;
    private c g;
    private c h;
    private final e i = new e(32768);

    public f(int i, int i2, InputStream inputStream) {
        if (i != 4096 && i != 8192) {
            throw new IllegalArgumentException("The dictionary size must be 4096 or 8192");
        }
        if (i2 != 2 && i2 != 3) {
            throw new IllegalArgumentException("The number of trees must be 2 or 3");
        }
        this.c = i;
        this.d = i2;
        this.e = i2;
        this.a = inputStream;
    }

    private void a() {
        if (this.b == null) {
            if (this.d == 3) {
                this.f = c.a(this.a, 256);
            }
            this.g = c.a(this.a, 64);
            this.h = c.a(this.a, 64);
            this.b = new d(this.a);
        }
    }

    private void b() throws IOException {
        a();
        int iA = this.b.a();
        if (iA == 1) {
            c cVar = this.f;
            int iA2 = cVar != null ? cVar.a(this.b) : this.b.b();
            if (iA2 == -1) {
                return;
            }
            this.i.a(iA2);
            return;
        }
        if (iA == 0) {
            int i = this.c == 4096 ? 6 : 7;
            int iA3 = (int) this.b.a(i);
            int iA4 = this.h.a(this.b);
            if (iA4 != -1 || iA3 > 0) {
                int i2 = (iA4 << i) | iA3;
                int iA5 = this.g.a(this.b);
                if (iA5 == 63) {
                    iA5 = (int) (((long) iA5) + this.b.a(8));
                }
                this.i.a(i2 + 1, iA5 + this.e);
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (!this.i.a()) {
            b();
        }
        return this.i.b();
    }
}
