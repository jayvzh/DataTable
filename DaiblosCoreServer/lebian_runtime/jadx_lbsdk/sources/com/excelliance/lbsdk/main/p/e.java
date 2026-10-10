package com.excelliance.lbsdk.main.p;

import android.content.Context;
import android.util.SparseArray;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.aa;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.q;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e extends g {
    private static volatile boolean c = false;
    private static e g;
    protected f[] a;
    private b[] d;
    private final SparseArray<Long> e;
    private int f;

    static abstract class a {
        a() {
        }

        void a(int i) {
        }

        void b(int i) {
        }
    }

    private class b extends Thread {
        private long b;
        private final File c;
        private final URL d;
        private final long e;
        private final int f;
        private final long g;
        private volatile int h;

        public b(URL url, File file, long j, long j2, long j3, int i) {
            super("DFT" + i);
            this.h = 0;
            this.d = url;
            this.c = file;
            this.e = j;
            this.f = i;
            this.g = j2;
            this.b = j3;
        }

        static /* synthetic */ long a(b bVar, long j) {
            long j2 = bVar.b + j;
            bVar.b = j2;
            return j2;
        }

        public int a() {
            return this.h;
        }

        public boolean b() {
            return this.h == 3 || this.h == 2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean unused = e.c = false;
            long j = this.e + this.b;
            com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "blockStart : " + this.e + " blockEnd : " + this.g + " downloadedSize : " + this.b, new Object[0]);
            long j2 = this.g;
            if (j >= j2) {
                this.h = 3;
                return;
            }
            try {
                e.this.a(e.this.b, this.d, this.c, j, j2 - 1, 0L, new a() { // from class: com.excelliance.lbsdk.main.p.e.b.1
                    @Override // com.excelliance.lbsdk.main.p.e.a
                    void a(int i) {
                        long j3 = i;
                        b.a(b.this, j3);
                        e.this.e.put(b.this.f, Long.valueOf(b.this.b));
                        e.this.c(j3);
                        e.this.b(j3);
                    }

                    @Override // com.excelliance.lbsdk.main.p.e.a
                    void b(int i) {
                        b.this.h = i;
                    }
                });
            } catch (Exception e) {
                this.b = -1L;
                this.h = e.b(e) ? 21 : 2;
                com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "download failed:", e, new Object[0]);
            }
        }
    }

    protected e(com.excelliance.lbsdk.main.b.c cVar) {
        super(cVar);
        this.b = cVar.a();
        this.e = new SparseArray<>();
    }

    private long a(int i) {
        if (this.e.get(i) == null) {
            return 0L;
        }
        return this.e.get(i).longValue();
    }

    /* JADX WARN: Code duplicated, block: B:37:0x007b  */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    private void a(int r10, long r11, long r13) {
        /*
            r9 = this;
            android.content.Context r0 = r9.b
            com.excelliance.lbsdk.main.p.b r0 = com.excelliance.lbsdk.main.p.b.a(r0)
            r0.a()
            r0 = 1
            r1 = 1
        Lb:
            if (r1 == 0) goto La2
            r1 = 0
            android.util.SparseArray<java.lang.Long> r3 = r9.e
            r9.b(r3)
            boolean r3 = com.excelliance.lbsdk.main.q.b
            r4 = 0
            if (r3 == 0) goto L24
            java.lang.Object[] r10 = new java.lang.Object[r4]
            java.lang.String r11 = "BwbxZipDownloader"
            java.lang.String r12 = "moreThreadDownloadFile canceled"
            com.excelliance.lbsdk.main.g.b.c(r11, r12, r10)
            goto La2
        L24:
            boolean r3 = com.excelliance.lbsdk.main.q.c
            if (r3 != 0) goto L98
            if (r10 == r0) goto L58
            if (r10 != 0) goto L2d
            goto L58
        L2d:
            r3 = 3
            if (r10 != r3) goto L7b
            com.excelliance.lbsdk.main.p.f[] r3 = r9.a
            if (r3 == 0) goto L7b
            r2 = r1
            r1 = 0
        L36:
            com.excelliance.lbsdk.main.p.f[] r5 = r9.a
            int r6 = r5.length
            if (r4 >= r6) goto L54
            r6 = r5[r4]
            if (r6 == 0) goto L4c
            r5 = r5[r4]
            int r5 = r5.a()
            boolean r5 = r9.b(r5)
            if (r5 == 0) goto L4c
            r1 = 1
        L4c:
            int r4 = r4 + 1
            long r5 = r9.a(r4)
            long r2 = r2 + r5
            goto L36
        L54:
            r7 = r2
            r3 = r1
            r1 = r7
            goto L7c
        L58:
            com.excelliance.lbsdk.main.p.e$b[] r3 = r9.d
            if (r3 == 0) goto L7b
            r3 = 0
        L5d:
            com.excelliance.lbsdk.main.p.e$b[] r5 = r9.d
            int r6 = r5.length
            if (r4 >= r6) goto L7c
            r6 = r5[r4]
            if (r6 == 0) goto L73
            r5 = r5[r4]
            int r5 = r5.a()
            boolean r5 = r9.b(r5)
            if (r5 == 0) goto L73
            r3 = 1
        L73:
            int r4 = r4 + 1
            long r5 = r9.a(r4)
            long r1 = r1 + r5
            goto L5d
        L7b:
            r3 = 0
        L7c:
            android.content.Context r4 = r9.b
            com.excelliance.lbsdk.main.p.b r4 = com.excelliance.lbsdk.main.p.b.a(r4)
            long r1 = r1 + r13
            r4.a(r1, r11)
            r9.a_()
            if (r3 == 0) goto L95
            r1 = 200(0xc8, double:9.9E-322)
            java.lang.Thread.sleep(r1)     // Catch: java.lang.Exception -> L91
            goto L95
        L91:
            r1 = move-exception
            r1.printStackTrace()
        L95:
            r1 = r3
            goto Lb
        L98:
            com.excelliance.lbsdk.main.p.e.c = r0
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.String r11 = "speed is too slowly, change url"
            r10.<init>(r11)
            throw r10
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.p.e.a(int, long, long):void");
    }

    public static e b(Context context) {
        if (g == null) {
            synchronized (e.class) {
                if (g == null) {
                    g = new e(LBSdkHelper.application());
                }
            }
        }
        return g;
    }

    private void b(SparseArray<Long> sparseArray) {
        for (int i = 0; i < sparseArray.size(); i++) {
            ae.a(this.b, "lebian_module_bwbxdloader_multi_thread", String.valueOf(sparseArray.keyAt(i)), sparseArray.valueAt(i).longValue());
        }
    }

    static boolean b(Exception exc) {
        if (exc == null || exc.getMessage() == null) {
            return false;
        }
        String message = exc.getMessage();
        return message.toLowerCase().contains("space") || message.contains("空间");
    }

    protected long a(long j, int i) throws Exception {
        if (j <= 0) {
            throw new Exception("need download size can't less zero");
        }
        if (i == 1 || i == 0) {
            b[] bVarArr = this.d;
            return j % ((long) bVarArr.length) == 0 ? j / ((long) bVarArr.length) : (j / ((long) bVarArr.length)) + 1;
        }
        if (i != 3) {
            return 0L;
        }
        f[] fVarArr = this.a;
        return j % ((long) fVarArr.length) == 0 ? j / ((long) fVarArr.length) : (j / ((long) fVarArr.length)) + 1;
    }

    public long a(SparseArray<Long> sparseArray) {
        int i = 0;
        long jLongValue = 0;
        while (i < sparseArray.size()) {
            i++;
            jLongValue += sparseArray.get(i) == null ? 0L : sparseArray.get(i).longValue();
        }
        return jLongValue;
    }

    protected File a(String str, long j) {
        File file = new File(str);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "initDownloadSaveFile mkdirs fail", new Object[0]);
            }
            e();
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.setLength(j);
                randomAccessFile.close();
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "read file error" + e, new Object[0]);
            }
        }
        return file;
    }

    public void a() {
        c = true;
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void a(Context context, int i) {
        a(context, e(this.f), i);
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void a(Context context, long j) {
        a(context, 1, j);
    }

    protected void a(Context context, URL url, File file, long j, long j2, long j3, a aVar) throws IOException {
        c = false;
        HttpURLConnection httpURLConnectionA = aa.a(url, context);
        httpURLConnectionA.setConnectTimeout(60000);
        httpURLConnectionA.setReadTimeout(60000);
        httpURLConnectionA.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnectionA.setRequestProperty("Range", "bytes=" + j + "-" + j2);
        com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "Range : " + j + "-" + j2, new Object[0]);
        InputStream inputStream = httpURLConnectionA.getInputStream();
        byte[] bArr = new byte[8192];
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        randomAccessFile.seek(j - j3);
        aVar.b(1);
        while (true) {
            int i = inputStream.read(bArr, 0, 8192);
            if (i == -1) {
                break;
            }
            if (c || Thread.interrupted()) {
                com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "thread stop or interrupted", new Object[0]);
                break;
            } else {
                randomAccessFile.write(bArr, 0, i);
                aVar.a(i);
            }
        }
        randomAccessFile.close();
        inputStream.close();
        aVar.b(3);
        com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "download finish", new Object[0]);
    }

    public synchronized void a(String str, String str2, long j) {
        int i;
        com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "moreThreadDownloadFile : " + j, new Object[0]);
        c(1);
        File fileA = a(str2, j);
        d();
        long jA = a(this.e);
        q.a(jA);
        b(j, jA);
        long jA2 = a(j, 1);
        int i2 = 0;
        while (i2 < this.d.length) {
            int i3 = i2 + 1;
            long jA3 = a(i3);
            if (jA3 >= jA2 || jA >= j) {
                i = i3;
                this.d[i2] = null;
            } else {
                i = i3;
                int i4 = i2;
                this.d[i4] = new b(new URL(str), fileA, jA2 * ((long) i2), Math.min(((long) i3) * jA2, j), jA3, i);
                this.d[i4].start();
            }
            jA = jA;
            i2 = i;
        }
        b(1, j);
    }

    public synchronized void a(String str, String str2, long j, long j2) {
        long j3 = j2;
        synchronized (this) {
            com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "downloadSplitZipFile offset : " + j3 + " zipFileSize:" + j, new Object[0]);
            c(0);
            File fileA = a(str2, j);
            d();
            long jA = a(this.e);
            q.a(jA);
            b(j, jA);
            long jA2 = a(j - j3, 0);
            com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "block ： " + jA2, new Object[0]);
            int i = 0;
            while (i < this.d.length) {
                long j4 = j3 + (((long) i) * jA2);
                int i2 = i + 1;
                this.d[i] = new b(new URL(str), fileA, j4, Math.min(j4 + jA2, j), a(i2), i2);
                this.d[i].start();
                j3 = j2;
                i = i2;
                jA2 = jA2;
            }
            a(0, j, j2);
        }
    }

    protected void b(int i, long j) {
        a(i, j, 0L);
    }

    @Override // com.excelliance.lbsdk.main.p.g
    protected void b(Context context, int i) {
        if (i == 0 || 100 == i) {
            int iF = f(this.f);
            int iC = c(context);
            int iG = g();
            com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "report unzip progress, percent(%d), unzipType(%d), pkgType(%d), upgradeStat(%d)", Integer.valueOf(i), Integer.valueOf(iF), Integer.valueOf(iC), Integer.valueOf(iG));
            com.excelliance.lbsdk.main.k.c.b(context, iF, iC, iG, i);
        }
    }

    public boolean b(int i) {
        if (i == 3) {
            return false;
        }
        if (i == 2) {
            c = true;
            throw new RuntimeException("thread error");
        }
        if (i != 21) {
            return true;
        }
        c = true;
        throw new RuntimeException("thread error no space");
    }

    protected void c(int i) {
        int i2 = 0;
        if (i == 1 || i == 0) {
            if (this.d == null) {
                this.d = new b[3];
                return;
            }
            while (true) {
                b[] bVarArr = this.d;
                if (i2 >= bVarArr.length) {
                    return;
                }
                if (bVarArr[i2] != null && !bVarArr[i2].b()) {
                    this.d[i2].interrupt();
                    this.d[i2].join();
                    this.d[i2] = null;
                }
                i2++;
            }
        } else {
            if (i != 3) {
                return;
            }
            if (this.a == null) {
                this.a = new f[3];
                return;
            }
            while (true) {
                f[] fVarArr = this.a;
                if (i2 >= fVarArr.length) {
                    return;
                }
                if (fVarArr[i2] != null && !fVarArr[i2].b()) {
                    this.a[i2].interrupt();
                    this.a[i2].join();
                    this.a[i2] = null;
                }
                i2++;
            }
        }
    }

    protected void d() {
        Map<String, String> mapA = ae.a(this.b, "lebian_module_bwbxdloader_multi_thread");
        if (mapA.size() > 0) {
            com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "has download file", new Object[0]);
            for (Map.Entry<String, String> entry : mapA.entrySet()) {
                int i = Integer.parseInt(entry.getKey());
                long j = Long.parseLong(entry.getValue());
                com.excelliance.lbsdk.main.g.b.c("BwbxZipDownloader", "initDownloadedData : key:" + i + " value:" + j, new Object[0]);
                this.e.put(i, Long.valueOf(j));
            }
        }
    }

    public void d(int i) {
        this.f = i;
    }

    public void e() {
        ae.b(this.b, "lebian_module_bwbxdloader_multi_thread");
    }

    public Map<String, String> f() {
        return ae.a(this.b, "lebian_module_bwbxdloader_multi_thread");
    }
}
