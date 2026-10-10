package com.excelliance.lbsdk.main.s;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class al implements Closeable {
    protected static final byte[] b = ag.f;
    private static final long s = am.getValue(ag.d);
    private final List<af> a;
    protected final Map<String, LinkedList<af>> c;
    protected final byte[] d;
    protected final byte[] e;
    protected Map<af, c> f;
    protected final Comparator<af> g;
    private final String h;
    private final ai i;
    private final String j;
    private final RandomAccessFile k;
    private final File l;
    private final boolean m;
    private volatile boolean n;
    private final byte[] o;
    private final byte[] p;
    private final boolean q;
    private final boolean r;

    /* JADX INFO: renamed from: com.excelliance.lbsdk.main.s.al$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[an.values().length];

        static {
            try {
                a[an.STORED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[an.UNSHRINKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[an.IMPLODING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[an.DEFLATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[an.BZIP2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[an.AES_ENCRYPTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[an.ENHANCED_DEFLATED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[an.EXPANDING_LEVEL_1.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[an.EXPANDING_LEVEL_2.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[an.EXPANDING_LEVEL_3.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[an.EXPANDING_LEVEL_4.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[an.JPEG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[an.LZMA.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[an.PKWARE_IMPLODING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                a[an.PPMD.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                a[an.TOKENIZATION.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                a[an.UNKNOWN.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                a[an.WAVPACK.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public class a extends InputStream {
        protected long a;
        protected RandomAccessFile b;
        private long d;
        private boolean e = false;

        public a(long j, long j2) throws IOException {
            this.a = j2;
            this.d = j;
            this.b = al.this.a(al.this.l, "r");
            this.b.seek(this.d);
        }

        void a() {
            this.e = true;
        }

        @Override // java.io.InputStream
        public int read() {
            long j = this.a;
            this.a = j - 1;
            if (j > 0) {
                this.d++;
                return this.b.read();
            }
            if (!this.e) {
                return -1;
            }
            this.e = false;
            return 0;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            long j = this.a;
            if (j <= 0) {
                if (!this.e) {
                    return -1;
                }
                this.e = false;
                bArr[i] = 0;
                return 1;
            }
            if (i2 <= 0) {
                return 0;
            }
            if (i2 > j) {
                i2 = (int) j;
            }
            int i3 = this.b.read(bArr, i, i2);
            if (i3 > 0) {
                long j2 = i3;
                this.d += j2;
                this.a -= j2;
            }
            return i3;
        }
    }

    public static class b extends af {
        private final d a;

        b(d dVar) {
            this.a = dVar;
        }

        @Override // com.excelliance.lbsdk.main.s.af
        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            b bVar = (b) obj;
            return this.a.a == bVar.a.a && this.a.b == bVar.a.b;
        }

        @Override // com.excelliance.lbsdk.main.s.af, java.util.zip.ZipEntry
        public int hashCode() {
            return (super.hashCode() * 3) + ((int) (this.a.a % 2147483647L));
        }

        public d k() {
            return this.a;
        }
    }

    protected static final class c {
        private final byte[] a;
        private final byte[] b;

        private c(byte[] bArr, byte[] bArr2) {
            this.a = bArr;
            this.b = bArr2;
        }
    }

    public static final class d {
        public long a = -1;
        public long b = -1;
    }

    public al(File file) {
        this(file, "UTF8");
    }

    public al(File file, String str) {
        this(file, str, true);
    }

    public al(File file, String str, boolean z) {
        this.a = new LinkedList();
        this.c = new HashMap(509);
        this.n = true;
        this.d = new byte[8];
        this.e = new byte[4];
        this.o = new byte[42];
        this.p = new byte[2];
        this.g = new Comparator<af>() { // from class: com.excelliance.lbsdk.main.s.al.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(af afVar, af afVar2) {
                if (afVar == afVar2) {
                    return 0;
                }
                b bVar = afVar instanceof b ? (b) afVar : null;
                b bVar2 = afVar2 instanceof b ? (b) afVar2 : null;
                if (bVar == null) {
                    return 1;
                }
                if (bVar2 == null) {
                    return -1;
                }
                long j = bVar.k().a - bVar2.k().a;
                if (j == 0) {
                    return 0;
                }
                return j < 0 ? -1 : 1;
            }
        };
        this.j = file.getAbsolutePath();
        this.h = str;
        this.i = aj.a(str);
        this.m = z;
        this.l = file;
        this.k = b(file, "r");
        this.r = f();
        this.q = d();
        if (this.r) {
            e();
        }
    }

    public al(String str) {
        this(new File(str), "UTF8");
    }

    private void a(af afVar, d dVar, int i) throws ZipException {
        ac acVar = (ac) afVar.b(ac.a);
        if (acVar != null) {
            boolean z = afVar.getSize() == 4294967295L;
            boolean z2 = afVar.getCompressedSize() == 4294967295L;
            boolean z3 = dVar.a == 4294967295L;
            acVar.a(z, z2, z3, i == 65535);
            if (z) {
                afVar.setSize(acVar.a().getLongValue());
            } else if (z2) {
                acVar.a(new ah(afVar.getSize()));
            }
            if (z2) {
                afVar.setCompressedSize(acVar.b().getLongValue());
            } else if (z) {
                acVar.b(new ah(afVar.getCompressedSize()));
            }
            if (z3) {
                dVar.a = acVar.c().getLongValue();
            }
        }
    }

    private void a(Map<af, c> map) throws IOException {
        this.k.readFully(this.o);
        d dVar = new d();
        b bVar = new b(dVar);
        int value = ao.getValue(this.o, 0);
        bVar.c(value);
        bVar.b((value >> 8) & 15);
        bVar.d(ao.getValue(this.o, 2));
        i iVarB = i.b(this.o, 4);
        boolean zA = iVarB.a();
        ai aiVar = zA ? aj.a : this.i;
        bVar.a(iVarB);
        bVar.e(ao.getValue(this.o, 4));
        bVar.setMethod(ao.getValue(this.o, 6));
        bVar.setTime(ap.b(am.getValue(this.o, 8)));
        bVar.setCrc(am.getValue(this.o, 12));
        bVar.setCompressedSize(am.getValue(this.o, 16));
        bVar.setSize(am.getValue(this.o, 20));
        int value2 = ao.getValue(this.o, 24);
        int value3 = ao.getValue(this.o, 26);
        int value4 = ao.getValue(this.o, 28);
        int value5 = ao.getValue(this.o, 30);
        bVar.a(ao.getValue(this.o, 32));
        bVar.a(am.getValue(this.o, 34));
        byte[] bArr = new byte[value2];
        this.k.readFully(bArr);
        bVar.a(aiVar.a(bArr), bArr);
        dVar.a = am.getValue(this.o, 38);
        this.a.add(bVar);
        if (!this.q) {
            String name = bVar.getName();
            LinkedList<af> linkedList = this.c.get(name);
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                this.c.put(name, linkedList);
            }
            linkedList.addLast(bVar);
        }
        byte[] bArr2 = new byte[value3];
        this.k.readFully(bArr2);
        bVar.a(bArr2);
        a(bVar, dVar, value5);
        byte[] bArr3 = new byte[value4];
        this.k.readFully(bArr3);
        bVar.setComment(aiVar.a(bArr3));
        if (zA || !this.m) {
            return;
        }
        map.put(bVar, new c(bArr, bArr3));
    }

    private void b(Map<af, c> map) throws IOException {
        Iterator<af> it = this.a.iterator();
        while (it.hasNext()) {
            a(it.next(), map, (RandomAccessFile) null);
        }
    }

    private Map<af, c> h() throws IOException {
        HashMap<af, c> map = new HashMap<>();
        i();
        a(map);
        return map;
    }

    private void i() throws IOException {
        j();
        boolean zEquals = false;
        boolean z = this.k.getFilePointer() > 20;
        if (z) {
            RandomAccessFile randomAccessFile = this.k;
            randomAccessFile.seek(randomAccessFile.getFilePointer() - 20);
            this.k.readFully(this.e);
            zEquals = Arrays.equals(ag.g, this.e);
        }
        if (zEquals) {
            b();
            return;
        }
        if (z) {
            a(16);
        }
        c();
    }

    private void j() throws ZipException {
        if (!a(22L, 65557L, ag.e)) {
            throw new ZipException("archive is not a ZIP archive");
        }
    }

    private boolean k() throws IOException {
        this.k.seek(0L);
        this.k.readFully(this.e);
        return Arrays.equals(this.e, ag.b);
    }

    public af a(String str) {
        LinkedList<af> linkedList = this.c.get(str);
        if (linkedList != null) {
            return linkedList.getFirst();
        }
        return null;
    }

    protected a a(long j, long j2) {
        return new a(j, j2);
    }

    public InputStream a(af afVar) throws t {
        if (!(afVar instanceof b)) {
            return null;
        }
        d dVarK = ((b) afVar).k();
        ap.a(afVar);
        return a(afVar, a(dVarK.b, afVar.getCompressedSize()));
    }

    protected InputStream a(af afVar, a aVar) throws ZipException {
        int i = AnonymousClass3.a[an.getMethodByCode(afVar.getMethod()).ordinal()];
        if (i == 1) {
            return new BufferedInputStream(aVar);
        }
        if (i == 2) {
            return new s(aVar);
        }
        if (i == 3) {
            return new f(afVar.h().c(), afVar.h().d(), new BufferedInputStream(aVar));
        }
        if (i == 4) {
            aVar.a();
            final Inflater inflater = new Inflater(true);
            return new InflaterInputStream(new BufferedInputStream(aVar), inflater, 8192) { // from class: com.excelliance.lbsdk.main.s.al.2
                @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    try {
                        super.close();
                    } finally {
                        inflater.end();
                    }
                }
            };
        }
        if (i == 5) {
            return new com.excelliance.lbsdk.main.u.a(aVar);
        }
        throw new ZipException("Found unsupported compression method " + afVar.getMethod());
    }

    protected RandomAccessFile a(File file, String str) {
        return new RandomAccessFile(file, str);
    }

    public Enumeration<af> a() {
        return Collections.enumeration(this.a);
    }

    protected void a(int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int iSkipBytes = this.k.skipBytes(i - i2);
            if (iSkipBytes <= 0) {
                throw new EOFException();
            }
            i2 += iSkipBytes;
        }
    }

    protected void a(af afVar, Map<af, c> map, RandomAccessFile randomAccessFile) throws IOException {
        if (map == null) {
            map = this.f;
        }
        if (randomAccessFile == null) {
            randomAccessFile = this.k;
        }
        b bVar = (b) afVar;
        d dVarK = bVar.k();
        long j = dVarK.a + 26;
        randomAccessFile.seek(j);
        randomAccessFile.readFully(this.p);
        int value = ao.getValue(this.p);
        randomAccessFile.readFully(this.p);
        int value2 = ao.getValue(this.p);
        int i = value;
        while (i > 0) {
            int iSkipBytes = randomAccessFile.skipBytes(i);
            if (iSkipBytes <= 0) {
                throw new IOException("failed to skip file name in local file header");
            }
            i -= iSkipBytes;
        }
        byte[] bArr = new byte[value2];
        randomAccessFile.readFully(bArr);
        bVar.setExtra(bArr);
        dVarK.b = j + 2 + 2 + ((long) value) + ((long) value2);
        if (map.containsKey(bVar)) {
            c cVar = map.get(bVar);
            ap.a(bVar, cVar.a, cVar.b);
        }
        String name = bVar.getName();
        LinkedList<af> linkedList = this.c.get(name);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this.c.put(name, linkedList);
        }
        linkedList.addLast(bVar);
    }

    protected void a(HashMap<af, c> map) throws IOException {
        this.k.readFully(this.e);
        long value = am.getValue(this.e);
        if (value != s && k()) {
            throw new IOException("central directory is empty, can't expand corrupt archive.");
        }
        while (value == s) {
            a((Map<af, c>) map);
            this.k.readFully(this.e);
            value = am.getValue(this.e);
        }
    }

    protected boolean a(long j, long j2, byte[] bArr) throws IOException {
        long length = this.k.length() - j;
        long jMax = Math.max(0L, this.k.length() - j2);
        boolean z = false;
        if (length >= 0) {
            while (length >= jMax) {
                this.k.seek(length);
                int i = this.k.read();
                if (i == -1) {
                    break;
                }
                if (i == bArr[0] && this.k.read() == bArr[1] && this.k.read() == bArr[2] && this.k.read() == bArr[3]) {
                    z = true;
                    break;
                }
                length--;
            }
        }
        if (z) {
            this.k.seek(length);
        }
        return z;
    }

    public long b(af afVar) {
        return ((b) afVar).k().a;
    }

    protected RandomAccessFile b(File file, String str) {
        return new RandomAccessFile(file, str);
    }

    protected void b() throws IOException {
        a(4);
        this.k.readFully(this.d);
        this.k.seek(ah.getLongValue(this.d));
        this.k.readFully(this.e);
        if (!Arrays.equals(this.e, ag.f)) {
            throw new ZipException("archive's ZIP64 end of central directory locator is corrupt.");
        }
        a(44);
        this.k.readFully(this.d);
        this.k.seek(ah.getLongValue(this.d));
    }

    protected void c() throws IOException {
        a(16);
        this.k.readFully(this.e);
        this.k.seek(am.getValue(this.e));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.n = true;
        this.k.close();
    }

    protected boolean d() {
        return true;
    }

    protected boolean e() {
        try {
            Map<af, c> mapH = h();
            if (this.q) {
                b(mapH);
            } else {
                this.f = mapH;
            }
            this.n = false;
            return true;
        } catch (Throwable th) {
            this.n = true;
            com.excelliance.lbsdk.main.w.c.a(this.k);
            throw th;
        }
    }

    protected boolean f() {
        return true;
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.n) {
                System.err.println("Cleaning up unclosed ZipFile for archive " + this.j);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    protected List<af> g() {
        ArrayList arrayList = new ArrayList(this.a);
        Collections.sort(arrayList, this.g);
        return arrayList;
    }
}
