package com.excelliance.lbsdk.main.p;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.ae;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends Thread {
    private final File a;
    private final long b;
    private final long c;
    private final int d;
    private long e;
    private volatile int f;
    private a g;
    private int h;
    private int i;
    private int j;
    private List<a.C0003a> k;
    private volatile boolean l;
    private Context m;
    private int n;
    private boolean o;

    public f(Context context, File file, long j, long j2, long j3, int i, int i2) {
        super("DFT" + i);
        this.f = 0;
        this.l = false;
        this.m = context;
        this.a = file;
        this.b = j;
        this.c = j2;
        this.d = i;
        this.e = j3;
        this.n = i2;
        this.o = ((com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting")).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (!(3 == i && d()) && 3 == i) {
            return;
        }
        this.f = i;
    }

    private void a(long j, long j2, List<a.C0003a> list) {
        com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "runDownload, threadId = " + this.d + ", start = " + j + ", end = " + j2 + ", ranges.size = " + list.size(), new Object[0]);
        try {
            this.k = list;
            for (int i = 0; !this.l && i < list.size(); i++) {
                this.h = i;
                a.C0003a c0003a = list.get(i);
                long j3 = c0003a.a;
                long j4 = c0003a.b;
                com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "runDownload, threadId = " + this.d + ", rangeStart = " + j3 + ", rangeEnd = " + j4, new Object[0]);
                a(this.a, j3, j4, new e.a() { // from class: com.excelliance.lbsdk.main.p.f.1
                    @Override // com.excelliance.lbsdk.main.p.e.a
                    void a(int i2) {
                        f.this.b(i2);
                    }

                    @Override // com.excelliance.lbsdk.main.p.e.a
                    void b(int i2) {
                        f.this.a(i2);
                    }
                });
            }
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "download failed:", e, new Object[0]);
            this.f = this.g.a(e) ? 21 : 2;
            this.e = -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        long j = i;
        this.g.a(j);
        this.e += j;
        this.g.a(this.d, this.e);
        int i2 = this.i;
        int i3 = this.h;
        if (i2 != i3) {
            this.i = i3;
            this.j = 0;
        }
        this.j += i;
        long j2 = this.k.get(this.h).a + ((long) this.j);
        a aVar = this.g;
        int i4 = this.d;
        long j3 = this.b;
        aVar.a(i4, j3, j2 - j3);
    }

    private boolean d() {
        return this.h >= this.k.size() - 1;
    }

    private com.excelliance.lbsdk.main.o.b e() {
        return this.o ? new com.excelliance.lbsdk.main.o.g(this.m) : new com.excelliance.lbsdk.main.o.h(this.m);
    }

    private String f() {
        return this.o ? com.excelliance.lbsdk.main.o.i.b : com.excelliance.lbsdk.main.o.i.a;
    }

    public int a() {
        return this.f;
    }

    void a(a aVar) {
        this.g = aVar;
    }

    void a(File file, long j, long j2, e.a aVar) throws IOException {
        b(file, j, j2, aVar);
    }

    protected void b(File file, long j, long j2, e.a aVar) throws IOException {
        this.l = false;
        String strF = f();
        com.excelliance.lbsdk.main.o.b bVarE = e();
        bVarE.a((String) null, this.n);
        long j3 = (j2 - j) + 1;
        long jA = bVarE.a(strF, this.o ? j - ae.b(this.m, "lebian_module_dloadinfo", "bigAssetTwiceMp4Size", 0L) : j);
        long jA2 = bVarE.a(strF, jA, j3);
        if (jA2 < 1) {
            com.excelliance.lbsdk.main.g.b.e("DownloadFileThread", "download length(%d) must not lt 1", Long.valueOf(jA2));
        }
        InputStream inputStreamB = bVarE.b(strF, jA, jA2);
        byte[] bArr = new byte[8192];
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(j);
        aVar.b(1);
        while (true) {
            int i = inputStreamB.read(bArr, 0, 8192);
            if (i == -1) {
                break;
            }
            if (this.l || interrupted()) {
                com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "thread stop or interrupted", new Object[0]);
                break;
            } else {
                randomAccessFile.write(bArr, 0, i);
                aVar.a(i);
            }
        }
        randomAccessFile.close();
        bVarE.a(strF, inputStreamB);
        bVarE.c(strF, jA, jA2);
        bVarE.a((String) null, (String) null, true);
        aVar.b(3);
        com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "download finish", new Object[0]);
    }

    public boolean b() {
        return this.f == 3 || this.f == 2;
    }

    public void c() {
        this.l = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        this.g.a(this.d);
        com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "begin=" + this.b + ", start=" + (this.b + this.e) + ", end=" + this.c, new Object[0]);
        long j = this.b;
        long j2 = this.e;
        long j3 = j + j2;
        long j4 = this.c;
        if (j3 >= j4) {
            this.f = 3;
            return;
        }
        long j5 = j + j2;
        long j6 = j4 - 1;
        List<a.C0003a> listA = this.g.a(j5, j6, this.d);
        com.excelliance.lbsdk.main.g.b.c("DownloadFileThread", "ranges size: " + listA.size(), new Object[0]);
        if (listA == null || listA.size() < 1) {
            this.f = 3;
        } else {
            a(j5, j6, listA);
        }
    }
}
