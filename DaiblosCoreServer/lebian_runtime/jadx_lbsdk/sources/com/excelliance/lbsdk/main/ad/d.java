package com.excelliance.lbsdk.main.ad;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d extends RandomAccessFile {
    private URL a;
    private a b;
    private List<b> c;
    private List<b> d;
    private c e;
    private boolean f;
    private InputStream g;
    private long h;
    private long i;
    private String j;

    public d(File file, URL url, String str) {
        super(file, str);
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.a = url;
        this.b = new a(file.getPath());
        if (str.startsWith("rw")) {
            this.f = true;
        }
        this.e = new c();
        this.j = file.getAbsolutePath();
    }

    private int a(List<b> list) throws Throwable {
        int length = 0;
        for (int i = 0; i < list.size(); i++) {
            b bVar = list.get(i);
            byte[] bArrA = a(bVar);
            seek(bVar.a());
            write(bArrA);
            this.b.a(bVar.a(), bVar.b());
            length += bArrA.length;
        }
        return length;
    }

    private int a(byte[] bArr, int i, int i2, List<b> list) throws Throwable {
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < list.size()) {
            b bVar = list.get(i4);
            byte[] bArrA = a(bVar);
            int iA = (int) (((long) i) + (bVar.a() - ((long) i2)));
            long jC = bVar.c();
            System.arraycopy(bArrA, i3, bArr, iA, (int) jC);
            if (this.f) {
                seek(bVar.a());
                this.b.a(bVar.a(), bVar.b());
                write(bArrA);
            }
            i5 = (int) (((long) i5) + jC);
            i4++;
            i3 = 0;
        }
        return i5;
    }

    private int a(byte[] bArr, int i, int i2, List<b> list, List<b> list2) {
        return a(bArr, i, i2, list) + b(bArr, i, i2, list2);
    }

    private byte[] a(b bVar) {
        return this.e.a(this.a.toString(), bVar.a(), bVar.b());
    }

    private int b(byte[] bArr, int i, int i2, List<b> list) {
        int iA = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            b bVar = list.get(i3);
            iA += a(bArr, (int) (((long) i) + (bVar.a() - ((long) i2))), (int) ((bVar.b() - bVar.a()) + 1));
        }
        return iA;
    }

    protected int a(byte[] bArr, int i, int i2) {
        return super.read(bArr, i, i2);
    }

    public long a(long j, long j2) {
        if (!this.f) {
            return 0L;
        }
        List<b> listB = this.b.b(j, (j + j2) - 1);
        if (listB == null || listB.size() < 1) {
            return j2;
        }
        long filePointer = getFilePointer();
        int iA = a(listB);
        seek(filePointer);
        return iA;
    }

    public void a() throws IOException {
        InputStream inputStream = this.g;
        if (inputStream != null) {
            inputStream.close();
            this.g = null;
        }
    }

    public void a(long j) {
        if (this.g == null) {
            long filePointer = getFilePointer();
            this.g = this.e.b(this.a.toString(), filePointer, (j + filePointer) - 1).b();
            this.i = filePointer;
            this.h = 0L;
        }
    }

    protected int b(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i;
        int i4 = i2;
        while (i4 > 0) {
            int i5 = this.g.read(bArr, i3, i4);
            if (-1 == i5) {
                break;
            }
            i3 += i5;
            i4 -= i5;
        }
        return i2 - i4;
    }

    public long b(long j) {
        return a(getFilePointer(), j);
    }

    public void b() {
        new File(this.j).delete();
        a aVar = this.b;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void b(long j, long j2) {
        if (this.g == null) {
            this.i = j;
            this.g = this.e.b(this.a.toString(), j, (j2 + j) - 1).b();
            if (j != getFilePointer()) {
                seek(j);
            }
        }
    }

    public long c(long j) {
        return a(j, length() - j);
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() {
        return super.getFilePointer();
    }

    @Override // java.io.RandomAccessFile
    public long length() {
        long jA = this.b.a();
        if (-1 == jA) {
            jA = this.e.a(this.a.toString());
            if (this.f) {
                this.b.a(jA);
            }
        }
        return jA;
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] bArr, int i, int i2) {
        if (1 > i2) {
            return 0;
        }
        if (this.g != null) {
            long filePointer = getFilePointer();
            long jB = b(bArr, i, i2);
            if (jB > 0) {
                this.h += jB;
                seek(filePointer + jB);
            }
            return (int) jB;
        }
        long filePointer2 = getFilePointer();
        List<b> list = this.c;
        list.clear();
        List<b> list2 = this.d;
        list2.clear();
        this.b.a(filePointer2, (((long) i2) + filePointer2) - 1, list, list2);
        if (list2.size() < 1) {
            return a(bArr, i, i2);
        }
        long jA = a(bArr, i, (int) filePointer2, list2, list);
        long j = filePointer2 + jA;
        if (j != getFilePointer()) {
            seek(j);
        }
        return (int) jA;
    }

    @Override // java.io.RandomAccessFile
    public void seek(long j) {
        InputStream inputStream = this.g;
        if (inputStream != null) {
            long j2 = j - (this.i + this.h);
            if (j2 > 0) {
                inputStream.skip(j2);
                this.h += j2;
            }
        }
        super.seek(j);
    }

    @Override // java.io.RandomAccessFile
    public void setLength(long j) {
        if (-1 == this.b.a()) {
            this.b.a(j);
        }
    }

    @Override // java.io.RandomAccessFile, java.io.DataInput
    public int skipBytes(int i) throws IOException {
        InputStream inputStream = this.g;
        if (inputStream != null) {
            long j = i;
            inputStream.skip(j);
            this.h += j;
        }
        return super.skipBytes(i);
    }
}
