package com.excelliance.lbsdk.main.s;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class af extends ZipEntry {
    private static final byte[] a = new byte[0];
    private static final ak[] o = new ak[0];
    private int b;
    private long c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private long i;
    private ak[] j;
    private q k;
    private String l;
    private byte[] m;
    private i n;
    private byte[] p;

    protected af() {
        this("");
    }

    public af(String str) {
        super(str);
        this.b = -1;
        this.c = -1L;
        this.d = 0;
        this.g = 0;
        this.i = 0L;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = new i();
        a(str);
    }

    private void a(ak[] akVarArr, boolean z) {
        if (this.j == null) {
            a(akVarArr);
            return;
        }
        for (ak akVar : akVarArr) {
            ak akVarB = akVar instanceof q ? this.k : b(akVar.getHeaderId());
            if (akVarB == null) {
                a(akVar);
            } else if (z) {
                byte[] localFileDataData = akVar.getLocalFileDataData();
                akVarB.parseFromLocalFileData(localFileDataData, 0, localFileDataData.length);
            } else {
                byte[] centralDirectoryData = akVar.getCentralDirectoryData();
                akVarB.parseFromCentralDirectoryData(centralDirectoryData, 0, centralDirectoryData.length);
            }
        }
        d();
    }

    private ak[] a(ak[] akVarArr, int i) {
        ak[] akVarArr2 = new ak[i];
        System.arraycopy(akVarArr, 0, akVarArr2, 0, Math.min(akVarArr.length, i));
        return akVarArr2;
    }

    private ak[] k() {
        ak[] akVarArr = this.j;
        if (akVarArr == null) {
            return m();
        }
        return this.k != null ? l() : akVarArr;
    }

    private ak[] l() {
        ak[] akVarArr = this.j;
        ak[] akVarArrA = a(akVarArr, akVarArr.length + 1);
        akVarArrA[this.j.length] = this.k;
        return akVarArrA;
    }

    private ak[] m() {
        q qVar = this.k;
        return qVar == null ? o : new ak[]{qVar};
    }

    public int a() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(long j) {
        this.i = j;
    }

    public void a(ak akVar) {
        if (akVar instanceof q) {
            this.k = (q) akVar;
        } else if (this.j == null) {
            this.j = new ak[]{akVar};
        } else {
            if (b(akVar.getHeaderId()) != null) {
                a(akVar.getHeaderId());
            }
            ak[] akVarArr = this.j;
            ak[] akVarArrA = a(akVarArr, akVarArr.length + 1);
            akVarArrA[akVarArrA.length - 1] = akVar;
            this.j = akVarArrA;
        }
        d();
    }

    public void a(ao aoVar) {
        if (this.j == null) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        for (ak akVar : this.j) {
            if (!aoVar.equals(akVar.getHeaderId())) {
                arrayList.add(akVar);
            }
        }
        if (this.j.length == arrayList.size()) {
            throw new NoSuchElementException();
        }
        this.j = (ak[]) arrayList.toArray(new ak[arrayList.size()]);
        d();
    }

    public void a(i iVar) {
        this.n = iVar;
    }

    protected void a(String str) {
        if (str != null && c() == 0 && !str.contains("/")) {
            str = str.replace('\\', '/');
        }
        this.l = str;
    }

    protected void a(String str, byte[] bArr) {
        a(str);
        this.m = bArr;
    }

    public void a(byte[] bArr) {
        this.p = bArr;
        try {
            a(g.a(bArr, false, g.a.c), false);
        } catch (ZipException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void a(ak[] akVarArr) {
        ArrayList arrayList = new ArrayList();
        for (ak akVar : akVarArr) {
            if (akVar instanceof q) {
                this.k = (q) akVar;
            } else {
                arrayList.add(akVar);
            }
        }
        this.j = (ak[]) arrayList.toArray(new ak[arrayList.size()]);
        d();
    }

    public long b() {
        return this.i;
    }

    public ak b(ao aoVar) {
        ak[] akVarArr = this.j;
        if (akVarArr == null) {
            return null;
        }
        for (ak akVar : akVarArr) {
            if (aoVar.equals(akVar.getHeaderId())) {
                return akVar;
            }
        }
        return null;
    }

    protected void b(int i) {
        this.g = i;
    }

    public void b(ak akVar) {
        if (akVar instanceof q) {
            this.k = (q) akVar;
        } else {
            if (b(akVar.getHeaderId()) != null) {
                a(akVar.getHeaderId());
            }
            ak[] akVarArr = this.j;
            this.j = new ak[akVarArr != null ? akVarArr.length + 1 : 1];
            ak[] akVarArr2 = this.j;
            akVarArr2[0] = akVar;
            if (akVarArr != null) {
                System.arraycopy(akVarArr, 0, akVarArr2, 1, akVarArr2.length - 1);
            }
        }
        d();
    }

    public int c() {
        return this.g;
    }

    public void c(int i) {
        this.f = i;
    }

    @Override // java.util.zip.ZipEntry
    public Object clone() {
        af afVar = (af) super.clone();
        afVar.a(a());
        afVar.a(b());
        afVar.a(k());
        return afVar;
    }

    protected void d() {
        super.setExtra(g.a(k()));
    }

    public void d(int i) {
        this.e = i;
    }

    public void e(int i) {
        this.h = i;
    }

    public byte[] e() {
        byte[] extra = getExtra();
        return extra != null ? extra : a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        af afVar = (af) obj;
        String name = getName();
        String name2 = afVar.getName();
        if (name == null) {
            if (name2 != null) {
                return false;
            }
        } else if (!name.equals(name2)) {
            return false;
        }
        String comment = getComment();
        String comment2 = afVar.getComment();
        if (comment == null) {
            comment = "";
        }
        if (comment2 == null) {
            comment2 = "";
        }
        return getTime() == afVar.getTime() && comment.equals(comment2) && a() == afVar.a() && c() == afVar.c() && b() == afVar.b() && getMethod() == afVar.getMethod() && getSize() == afVar.getSize() && getCrc() == afVar.getCrc() && getCompressedSize() == afVar.getCompressedSize() && Arrays.equals(f(), afVar.f()) && Arrays.equals(e(), afVar.e()) && this.n.equals(afVar.n);
    }

    public byte[] f() {
        return g.b(k());
    }

    public byte[] g() {
        byte[] bArr = this.m;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    @Override // java.util.zip.ZipEntry
    public int getMethod() {
        return this.b;
    }

    @Override // java.util.zip.ZipEntry
    public String getName() {
        String str = this.l;
        return str == null ? super.getName() : str;
    }

    @Override // java.util.zip.ZipEntry
    public long getSize() {
        return this.c;
    }

    public i h() {
        return this.n;
    }

    @Override // java.util.zip.ZipEntry
    public int hashCode() {
        return getName().hashCode();
    }

    public Date i() {
        return new Date(getTime());
    }

    @Override // java.util.zip.ZipEntry
    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    public byte[] j() {
        return this.p;
    }

    @Override // java.util.zip.ZipEntry
    public void setExtra(byte[] bArr) {
        try {
            a(g.a(bArr, true, g.a.c), true);
        } catch (ZipException e) {
            throw new RuntimeException("Error parsing extra fields for entry: " + getName() + " - " + e.getMessage(), e);
        }
    }

    @Override // java.util.zip.ZipEntry
    public void setMethod(int i) {
        if (i >= 0) {
            this.b = i;
            return;
        }
        throw new IllegalArgumentException("ZIP compression method can not be negative: " + i);
    }

    @Override // java.util.zip.ZipEntry
    public void setSize(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid entry size");
        }
        this.c = j;
    }
}
