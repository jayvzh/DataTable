package com.excelliance.lbsdk.main.p;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class h extends g {
    private com.excelliance.lbsdk.main.b.c a;
    private Context c;
    private com.excelliance.lbsdk.main.bwbx.f d;
    private com.excelliance.lbsdk.main.a.a e;
    private com.excelliance.lbsdk.main.h.a<com.excelliance.lbsdk.main.bwbx.e> f;

    private static class a implements Runnable {
        private volatile String a;
        private String b;
        private byte[] c;
        private long d;
        private int e;
        private volatile boolean f;

        private a() {
        }

        public String a() {
            String str;
            synchronized (this) {
                while (!this.f) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        com.excelliance.lbsdk.main.g.b.a("MissingDownloader", e);
                    }
                }
                str = this.a;
            }
            return str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    this.a = BwbxUtil.downloadMissing(this.b, this.c, this.d, this.e);
                    this.f = true;
                    synchronized (this) {
                        notify();
                    }
                } catch (Exception e) {
                    com.excelliance.lbsdk.main.g.b.a("MissingDownloader", e);
                    this.f = true;
                    synchronized (this) {
                        notify();
                    }
                }
            } catch (Throwable th) {
                this.f = true;
                synchronized (this) {
                    notify();
                    throw th;
                }
            }
        }
    }

    private static class b implements ThreadFactory {
        private final AtomicInteger a;

        private b() {
            this.a = new AtomicInteger(1);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            SecurityManager securityManager = System.getSecurityManager();
            Thread thread = new Thread(securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup(), runnable, "missing-download-thread-" + this.a.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public h(com.excelliance.lbsdk.main.b.c cVar) {
        super(cVar);
        this.a = cVar;
        this.c = cVar.a();
        this.d = new com.excelliance.lbsdk.main.bwbx.f(this.c);
    }

    public static com.excelliance.lbsdk.main.bwbx.e a(int i) {
        return ((com.excelliance.lbsdk.main.l.b) LBSdkHelper.application().a("Download")).b().b(i);
    }

    public static String a(String str, boolean z) {
        return ((com.excelliance.lbsdk.main.l.b) LBSdkHelper.application().a("Download")).b().b(str, z);
    }

    public static String a(String str, byte[] bArr, long j, int i) {
        return ((com.excelliance.lbsdk.main.l.b) LBSdkHelper.application().a("Download")).b().b(str, bArr, j, i);
    }

    private void a(Runnable runnable) {
        com.excelliance.lbsdk.main.a.a aVar = this.e;
        if (aVar == null) {
            synchronized (this) {
                aVar = this.e;
                if (aVar == null) {
                    aVar = new com.excelliance.lbsdk.main.a.a(5, 10, new b());
                    this.e = aVar;
                }
            }
        }
        aVar.a(runnable);
    }

    public static boolean a(com.excelliance.lbsdk.main.bwbx.e eVar) {
        return ((com.excelliance.lbsdk.main.l.b) LBSdkHelper.application().a("Download")).b().b(eVar);
    }

    private com.excelliance.lbsdk.main.bwbx.e b(int i) {
        com.excelliance.lbsdk.main.h.a<com.excelliance.lbsdk.main.bwbx.e> aVarC = c();
        com.excelliance.lbsdk.main.bwbx.e eVarA = aVarC.a();
        if (aVarC.b(eVarA) < 1) {
            eVarA.a((String) null, i);
        } else {
            eVarA.b((String) null, i);
        }
        return eVarA;
    }

    private String b(String str, boolean z) {
        try {
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("MissingDownloader", "postDataProcess filename=" + str, new Object[0]);
            }
            String missingFile = BwbxUtil.missingHit(str) ? BwbxUtil.getMissingFile(str, null, 0L, 0, z) : null;
            if (b()) {
                String strD = d(str, z);
                if (missingFile == null) {
                    return strD;
                }
            }
            return missingFile;
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.a("MissingDownloader", e);
            return null;
        }
    }

    private String b(String str, byte[] bArr, long j, int i) {
        a aVar = new a();
        aVar.b = str;
        aVar.c = bArr;
        aVar.d = j;
        aVar.e = i;
        a(aVar);
        return aVar.a();
    }

    private boolean b() {
        return ((com.excelliance.lbsdk.main.l.e) this.a.a("Runtime")).e();
    }

    private boolean b(com.excelliance.lbsdk.main.bwbx.e eVar) {
        com.excelliance.lbsdk.main.h.a<com.excelliance.lbsdk.main.bwbx.e> aVar = this.f;
        if (aVar == null) {
            return false;
        }
        return aVar.a(eVar);
    }

    private com.excelliance.lbsdk.main.h.a<com.excelliance.lbsdk.main.bwbx.e> c() {
        com.excelliance.lbsdk.main.h.a<com.excelliance.lbsdk.main.bwbx.e> aVar = this.f;
        if (aVar != null) {
            return aVar;
        }
        synchronized (this) {
            if (this.f == null) {
                final Context contextA = this.a.a();
                this.f = new com.excelliance.lbsdk.main.h.a<>(new com.excelliance.lbsdk.main.h.a.InterfaceC0002a<com.excelliance.lbsdk.main.bwbx.e>() { // from class: com.excelliance.lbsdk.main.p.h.1
                    @Override // com.excelliance.lbsdk.main.h.a.InterfaceC0002a
                    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                    public com.excelliance.lbsdk.main.bwbx.e a() {
                        return new com.excelliance.lbsdk.main.bwbx.e(contextA);
                    }
                });
            }
        }
        return this.f;
    }

    private void c(String str, boolean z) {
        com.excelliance.lbsdk.main.af.b bVar = (com.excelliance.lbsdk.main.af.b) this.a.a("DryRun");
        if (bVar != null) {
            bVar.a(str, z);
        }
    }

    private String d(String str, boolean z) {
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("MissingDownloader", "postDataProcess doRecord", new Object[0]);
        }
        c(str, z);
        if (str.charAt(0) != '/' && !str.startsWith("assets/")) {
            str = "assets/" + str;
        }
        StringBuffer stringBuffer = new StringBuffer(!BwbxUtil.isSmallPkg() ? BwbxUtil.getRecordPath() : BwbxUtil.getCurrCachePath());
        stringBuffer.append("/");
        stringBuffer.append(str);
        String string = stringBuffer.toString();
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("MissingDownloader", "fullPath=" + string + ", exists=" + new File(string).exists(), new Object[0]);
        }
        if (new File(string).exists()) {
            return string;
        }
        return null;
    }
}
