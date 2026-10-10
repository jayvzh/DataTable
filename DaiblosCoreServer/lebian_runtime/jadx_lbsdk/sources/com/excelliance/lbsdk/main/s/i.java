package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Cloneable {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private int e;
    private int f;

    public static i b(byte[] bArr, int i) {
        int value = ao.getValue(bArr, i);
        i iVar = new i();
        iVar.b((value & 8) != 0);
        iVar.a((value & 2048) != 0);
        iVar.d((value & 64) != 0);
        iVar.c((value & 1) != 0);
        iVar.e = (value & 2) != 0 ? 8192 : 4096;
        iVar.f = (value & 4) != 0 ? 3 : 2;
        return iVar;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public void a(byte[] bArr, int i) {
        ao.putShort((this.b ? 8 : 0) | (this.a ? 2048 : 0) | (this.c ? 1 : 0) | (this.d ? 64 : 0), bArr, i);
    }

    public boolean a() {
        return this.a;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean b() {
        return this.c;
    }

    int c() {
        return this.e;
    }

    public void c(boolean z) {
        this.c = z;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("GeneralPurposeBit is not Cloneable?", e);
        }
    }

    int d() {
        return this.f;
    }

    public void d(boolean z) {
        this.d = z;
        if (z) {
            c(true);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return iVar.c == this.c && iVar.d == this.d && iVar.a == this.a && iVar.b == this.b;
    }

    public int hashCode() {
        return (((((((this.c ? 1 : 0) * 17) + (this.d ? 1 : 0)) * 13) + (this.a ? 1 : 0)) * 7) + (this.b ? 1 : 0)) * 3;
    }
}
