package com.excelliance.lbsdk.main.ad;

import com.excelliance.lbsdk.main.s.af;
import com.excelliance.lbsdk.main.s.ah;
import com.excelliance.lbsdk.main.s.al;
import com.excelliance.lbsdk.main.s.am;
import com.excelliance.lbsdk.main.s.ao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class e extends al {
    private static int a = 22;
    private static long h = 30;
    private static ThreadLocal<URL> i = new ThreadLocal<>();
    private URL j;
    private a k;
    private d l;
    private boolean m;
    private long n;
    private List<af> o;

    protected class a {
        int a;
        int b;
        long c;
        long d;
        long e;
        long f;
        short g;

        protected a() {
        }

        int a(byte[] bArr, int i) {
            this.a = (short) ao.getValue(bArr, 4);
            this.b = (short) ao.getValue(bArr, 6);
            this.c = (short) ao.getValue(bArr, 8);
            this.d = (short) ao.getValue(bArr, 10);
            this.e = am.getValue(bArr, 12);
            this.f = am.getValue(bArr, 16);
            this.g = (short) ao.getValue(bArr, 20);
            return 0;
        }

        int b(byte[] bArr, int i) {
            this.a = (int) am.getValue(bArr, 16);
            this.b = (int) am.getValue(bArr, 20);
            this.c = ah.getLongValue(bArr, 24);
            this.d = ao.getValue(bArr, 32);
            this.e = ah.getLongValue(bArr, 40);
            this.f = ah.getLongValue(bArr, 48);
            return 0;
        }
    }

    protected class b extends al.a {
        public b(long j, long j2) {
            super(j, j2);
            if (this.b instanceof d) {
                ((d) this.b).a(j2);
            }
        }

        public void a(long j) {
            this.a = j;
        }

        public RandomAccessFile b() {
            return this.b;
        }
    }

    public e(String str, URL url) {
        super(a(str, url));
        this.n = -1L;
        this.j = url;
    }

    private static String a(String str, URL url) {
        i.set(url);
        return c(str);
    }

    private static String c(String str) throws IOException {
        com.excelliance.lbsdk.main.ai.d.c(str);
        return str;
    }

    private void e(af afVar) {
        al.b bVar = (al.b) afVar;
        long j = bVar.k().a;
        int length = bVar.g().length;
        byte[] bArrJ = bVar.j();
        this.l.a(j, ((long) length) + 30 + ((long) (bArrJ != null ? bArrJ.length : 0)));
    }

    @Override // com.excelliance.lbsdk.main.s.al
    public af a(String str) {
        if (!this.m) {
            try {
                e();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.a(str);
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected al.a a(long j, long j2) {
        return new b(j, j2);
    }

    @Override // com.excelliance.lbsdk.main.s.al
    public InputStream a(af afVar) {
        return a(afVar, 0L, ((al.b) afVar).getCompressedSize());
    }

    public InputStream a(af afVar, long j, long j2) throws IOException {
        b bVar;
        synchronized (this) {
            bVar = (b) a(((al.b) afVar).k().a, d(afVar));
            a(afVar, (Map<af, al.c>) null, bVar.b());
        }
        long j3 = ((al.b) afVar).k().b + j;
        long compressedSize = afVar.getCompressedSize() - j;
        if (j2 <= 0) {
            j2 = compressedSize;
        }
        long jMin = Math.min(j2, compressedSize);
        bVar.b().seek(j3);
        bVar.a(jMin);
        return a(afVar, bVar);
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected RandomAccessFile a(File file, String str) {
        URL url = this.j;
        return url != null ? new d(file, url, "r") : super.a(file, str);
    }

    public void a(long j) {
        this.l.setLength(j);
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected void a(HashMap<af, al.c> map) throws IOException {
        this.l.a(this.k.f, this.k.e);
        super.a(map);
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected boolean a(long j, long j2, byte[] bArr) {
        d dVar = this.l;
        if (dVar != null) {
            dVar.a(dVar.length() - j, j);
        }
        return super.a(j, j2, bArr);
    }

    public InputStream b(af afVar, long j, long j2) {
        synchronized (this) {
            e(afVar);
            a(afVar, (Map<af, al.c>) null, (RandomAccessFile) null);
        }
        long j3 = ((al.b) afVar).k().b + j;
        long compressedSize = afVar.getCompressedSize() - j;
        if (j2 <= 0) {
            j2 = compressedSize;
        }
        return a(afVar, a(j3, Math.min(j2, compressedSize)));
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected RandomAccessFile b(File file, String str) {
        if (this.j == null) {
            this.j = i.get();
            i.remove();
        }
        URL url = this.j;
        if (url == null) {
            return super.b(file, str);
        }
        d dVar = new d(file, url, "rws");
        if (this.l == null) {
            this.l = dVar;
        }
        return dVar;
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected void b() throws ZipException {
        a(4);
        this.l.readFully(this.d);
        this.l.seek(ah.getLongValue(this.d));
        this.l.b(56L);
        byte[] bArr = new byte[56];
        this.l.readFully(bArr);
        System.arraycopy(bArr, 0, this.e, 0, this.e.length);
        if (!Arrays.equals(this.e, b)) {
            throw new ZipException("archive's ZIP64 end of central directory locator is corrupt.");
        }
        this.k = new a();
        this.k.b(bArr, 48);
        this.l.seek(this.k.f);
    }

    public void b(long j) {
        this.n = j;
    }

    public void b(String str) {
        this.j = new URL(str);
    }

    public long c(af afVar) {
        al.d dVarK = ((al.b) afVar).k();
        long j = dVarK.b;
        if (-1 != j) {
            return j;
        }
        long j2 = dVarK.a;
        int length = afVar.g().length;
        byte[] bArrJ = afVar.j();
        return j2 + 26 + 2 + 2 + ((long) length) + ((long) (bArrJ != null ? bArrJ.length : 0));
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected void c() {
        byte[] bArr = new byte[a];
        this.l.read(bArr);
        this.k = new a();
        this.k.a(bArr, a);
        this.l.seek(this.k.f);
    }

    public long d(af afVar) {
        List<af> listG = this.o;
        if (listG == null) {
            listG = g();
            this.o = listG;
        }
        int iBinarySearch = Collections.binarySearch(listG, afVar, this.g);
        return (iBinarySearch >= listG.size() + (-1) ? this.k.f : ((al.b) listG.get(iBinarySearch + 1)).k().a) - ((al.b) afVar).k().a;
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected boolean d() {
        return false;
    }

    @Override // com.excelliance.lbsdk.main.s.al
    public boolean e() {
        if (this.m) {
            return true;
        }
        long j = this.n;
        if (j >= 0) {
            this.l.c(j);
        }
        boolean zE = super.e();
        this.m = zE;
        return zE;
    }

    @Override // com.excelliance.lbsdk.main.s.al
    protected boolean f() {
        return false;
    }

    public String h() {
        URL url = this.j;
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    public void i() {
        d dVar = this.l;
        if (dVar != null) {
            dVar.b();
        }
    }
}
