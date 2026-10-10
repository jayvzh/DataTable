package com.excelliance.lbsdk.main.s;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ag extends com.excelliance.lbsdk.main.r.a {
    protected boolean a;
    private a i;
    private String j;
    private final List<af> k;
    private final n l;
    private long m;
    private long n;
    private final Map<af, Long> r;
    private ai s;
    private final RandomAccessFile t;
    private final OutputStream u;
    private boolean v;
    private boolean w;
    private boolean x;
    private ad y;
    private final Calendar z;
    private static final byte[] h = new byte[0];
    private static final byte[] o = {0, 0};
    private static final byte[] p = {0, 0, 0, 0};
    private static final byte[] q = am.getBytes(1);
    static final byte[] b = am.LFH_SIG.getBytes();
    static final byte[] c = am.DD_SIG.getBytes();
    static final byte[] d = am.CFH_SIG.getBytes();
    static final byte[] e = am.getBytes(101010256);
    static final byte[] f = am.getBytes(101075792);
    static final byte[] g = am.getBytes(117853008);

    private static final class a {
        private final af a;
        private boolean b;
    }

    private i a(int i, boolean z) {
        i iVar = new i();
        iVar.a(this.v || z);
        if (a(i)) {
            iVar.b(true);
        }
        return iVar;
    }

    private void a(af afVar, long j, boolean z) {
        ah ahVar;
        if (z) {
            ac acVarB = b(afVar);
            if (afVar.getCompressedSize() >= 4294967295L || afVar.getSize() >= 4294967295L || this.y == ad.Always) {
                acVarB.b(new ah(afVar.getCompressedSize()));
                ahVar = new ah(afVar.getSize());
            } else {
                ahVar = null;
                acVarB.b(null);
            }
            acVarB.a(ahVar);
            if (j >= 4294967295L || this.y == ad.Always) {
                acVarB.c(new ah(j));
            }
            afVar.d();
        }
    }

    private boolean a(int i) {
        return i == 8 && this.t == null;
    }

    private byte[] a(af afVar) throws ae {
        long jLongValue = this.r.get(afVar).longValue();
        boolean z = c(afVar) || afVar.getCompressedSize() >= 4294967295L || afVar.getSize() >= 4294967295L || jLongValue >= 4294967295L || this.y == ad.Always;
        if (z && this.y == ad.Never) {
            throw new ae("archive's size exceeds the limit of 4GByte.");
        }
        a(afVar, jLongValue, z);
        return a(afVar, e(afVar), jLongValue, z);
    }

    private byte[] a(af afVar, ByteBuffer byteBuffer, long j, boolean z) {
        byte[] bArrF = afVar.f();
        String comment = afVar.getComment();
        if (comment == null) {
            comment = "";
        }
        ByteBuffer byteBufferB = d(afVar).b(comment);
        int iLimit = byteBuffer.limit() - byteBuffer.position();
        int iLimit2 = byteBufferB.limit() - byteBufferB.position();
        int i = iLimit + 46;
        byte[] bArr = new byte[bArrF.length + i + iLimit2];
        System.arraycopy(d, 0, bArr, 0, 4);
        ao.putShort((afVar.c() << 8) | (!this.x ? 20 : 45), bArr, 4);
        int method = afVar.getMethod();
        boolean zA = this.s.a(afVar.getName());
        ao.putShort(b(method, z), bArr, 6);
        a(method, !zA && this.w).a(bArr, 8);
        ao.putShort(method, bArr, 10);
        ap.a(this.z, afVar.getTime(), bArr, 12);
        am.putLong(afVar.getCrc(), bArr, 16);
        if (afVar.getCompressedSize() >= 4294967295L || afVar.getSize() >= 4294967295L || this.y == ad.Always) {
            am.a.putLong(bArr, 20);
            am.a.putLong(bArr, 24);
        } else {
            am.putLong(afVar.getCompressedSize(), bArr, 20);
            am.putLong(afVar.getSize(), bArr, 24);
        }
        ao.putShort(iLimit, bArr, 28);
        ao.putShort(bArrF.length, bArr, 30);
        ao.putShort(iLimit2, bArr, 32);
        System.arraycopy(o, 0, bArr, 34, 2);
        ao.putShort(afVar.a(), bArr, 36);
        am.putLong(afVar.b(), bArr, 38);
        if (j >= 4294967295L || this.y == ad.Always) {
            am.putLong(4294967295L, bArr, 42);
        } else {
            am.putLong(Math.min(j, 4294967295L), bArr, 42);
        }
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset(), bArr, 46, iLimit);
        System.arraycopy(bArrF, 0, bArr, i, bArrF.length);
        System.arraycopy(byteBufferB.array(), byteBufferB.arrayOffset(), bArr, i + bArrF.length, iLimit2);
        return bArr;
    }

    private int b(int i, boolean z) {
        if (z) {
            return 45;
        }
        return a(i) ? 20 : 10;
    }

    private ac b(af afVar) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.b = !this.x;
        }
        this.x = true;
        ac acVar = (ac) afVar.b(ac.a);
        if (acVar == null) {
            acVar = new ac();
        }
        afVar.b(acVar);
        return acVar;
    }

    private void b(byte[] bArr) {
        this.l.a(bArr);
    }

    private boolean c(af afVar) {
        return afVar.b(ac.a) != null;
    }

    private ai d(af afVar) {
        return (this.s.a(afVar.getName()) || !this.w) ? this.s : aj.a;
    }

    private ByteBuffer e(af afVar) {
        return d(afVar).b(afVar.getName());
    }

    private void e() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(70000);
        Iterator<af> it = this.k.iterator();
        while (true) {
            int i = 0;
            do {
                if (!it.hasNext()) {
                    b(byteArrayOutputStream.toByteArray());
                    return;
                } else {
                    byteArrayOutputStream.write(a(it.next()));
                    i++;
                }
            } while (i <= 1000);
            b(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.reset();
        }
    }

    public void a() throws IOException {
        if (this.a) {
            throw new IOException("This archive has already been finished");
        }
        if (this.i != null) {
            throw new IOException("This archive contains unclosed entries.");
        }
        this.m = this.l.a();
        e();
        this.n = this.l.a() - this.m;
        c();
        b();
        this.r.clear();
        this.k.clear();
        this.l.close();
        this.a = true;
    }

    protected final void a(byte[] bArr) {
        this.l.b(bArr, 0, bArr.length);
    }

    protected void b() throws ae {
        b(e);
        b(o);
        b(o);
        int size = this.k.size();
        if (size > 65535 && this.y == ad.Never) {
            throw new ae("archive contains more than 65535 entries.");
        }
        if (this.m > 4294967295L && this.y == ad.Never) {
            throw new ae("archive's size exceeds the limit of 4GByte.");
        }
        byte[] bytes = ao.getBytes(Math.min(size, 65535));
        b(bytes);
        b(bytes);
        b(am.getBytes(Math.min(this.n, 4294967295L)));
        b(am.getBytes(Math.min(this.m, 4294967295L)));
        ByteBuffer byteBufferB = this.s.b(this.j);
        int iLimit = byteBufferB.limit() - byteBufferB.position();
        b(ao.getBytes(iLimit));
        this.l.a(byteBufferB.array(), byteBufferB.arrayOffset(), iLimit);
    }

    protected void c() {
        if (this.y == ad.Never) {
            return;
        }
        if (!this.x && (this.m >= 4294967295L || this.n >= 4294967295L || this.k.size() >= 65535)) {
            this.x = true;
        }
        if (this.x) {
            long jA = this.l.a();
            a(f);
            a(ah.getBytes(44L));
            a(ao.getBytes(45));
            a(ao.getBytes(45));
            a(p);
            a(p);
            byte[] bytes = ah.getBytes(this.k.size());
            a(bytes);
            a(bytes);
            a(ah.getBytes(this.n));
            a(ah.getBytes(this.m));
            a(g);
            a(p);
            a(ah.getBytes(jA));
            a(q);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.a) {
            a();
        }
        d();
    }

    void d() throws IOException {
        RandomAccessFile randomAccessFile = this.t;
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
        OutputStream outputStream = this.u;
        if (outputStream != null) {
            outputStream.close();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        OutputStream outputStream = this.u;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws t {
        a aVar = this.i;
        if (aVar == null) {
            throw new IllegalStateException("No current entry");
        }
        ap.a(aVar.a);
        a(this.l.a(bArr, i, i2, this.i.a.getMethod()));
    }
}
