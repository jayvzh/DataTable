package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.q;
import com.excelliance.lbsdk.main.s.af;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class i extends b {
    public static final String a = BwbxUtil.DOWNLOAD_MP4_FILE_NAME;
    public static final String b = BwbxUtil.MP4_FILE_NAME;
    protected com.excelliance.lbsdk.main.ad.e c;
    private Context d;
    private int e;

    protected i(Context context) {
        this.d = context;
    }

    protected i(Context context, com.excelliance.lbsdk.main.ad.e eVar) {
        this.d = context;
        this.c = eVar;
    }

    protected static long a(com.excelliance.lbsdk.main.ad.e eVar) {
        if (eVar == null) {
            return 0L;
        }
        af afVarA = eVar.a(b);
        if (afVarA == null) {
            com.excelliance.lbsdk.main.g.b.e("ZipDownloadManager", "entry file(%s) not in net zip file(%s)", b, eVar.h());
        }
        if (afVarA.getMethod() != 0) {
            com.excelliance.lbsdk.main.g.b.e("ZipDownloadManager", "entry file(%s) must not compressed", b, eVar.h());
        }
        return afVarA.getSize();
    }

    protected static long a(i iVar) {
        if (iVar == null) {
            return 0L;
        }
        return a(iVar.c());
    }

    protected static com.excelliance.lbsdk.main.ad.e a(int i) {
        Context contextA = LBSdkHelper.application().a();
        com.excelliance.lbsdk.main.ad.e eVar = new com.excelliance.lbsdk.main.ad.e(a(), new URL(b(i)));
        eVar.a(BwbxUtil.getOrigSize(contextA, true, 1));
        eVar.b(((com.excelliance.lbsdk.main.ab.d) LBSdkHelper.application().a("TryFlag")).a());
        return eVar;
    }

    public static i a(Context context, int i, int i2) {
        com.excelliance.lbsdk.main.ad.e eVarC;
        if (1 == i) {
            eVarC = a(i2);
        } else {
            eVarC = 2 == i ? c(i2) : null;
        }
        i iVar = new i(context, eVarC);
        iVar.e = i;
        return iVar;
    }

    protected static String a() {
        return com.excelliance.lbsdk.main.ai.i.a(((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).c(), "lbres-" + com.excelliance.lbsdk.main.ai.f.a(((com.excelliance.lbsdk.main.ab.d) LBSdkHelper.application().a("TryFlag")).c()) + ".zip.temp");
    }

    protected static String b(int i) {
        return q.a(LBSdkHelper.application().a(), i, 1);
    }

    protected static com.excelliance.lbsdk.main.ad.e c(int i) {
        Context contextA = LBSdkHelper.application().a();
        com.excelliance.lbsdk.main.ad.e eVar = new com.excelliance.lbsdk.main.ad.e(d(), new URL(q.a(contextA, i, 2)));
        eVar.a(BwbxUtil.getOrigSize(contextA, true, 2));
        eVar.b(((com.excelliance.lbsdk.main.ab.d) LBSdkHelper.application().a("TryFlag")).b());
        return eVar;
    }

    protected static boolean c(String str, String str2) {
        return new File(str2, str).exists();
    }

    protected static String d() {
        return com.excelliance.lbsdk.main.ai.i.a(((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).c(), "twice_load-" + com.excelliance.lbsdk.main.ai.f.a(((com.excelliance.lbsdk.main.ab.d) LBSdkHelper.application().a("TryFlag")).c()) + ".zip.temp");
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        return this.c.a(str).getSize();
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        long compressedSize = this.c.a(str).getCompressedSize() - j;
        if (j2 <= 0) {
            j2 = compressedSize;
        }
        return Math.min(j2, compressedSize);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        return b(str, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2, boolean z) {
        com.excelliance.lbsdk.main.ad.e eVar = this.c;
        if (z && eVar != null) {
            eVar.i();
        }
        b();
        return true;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        com.excelliance.lbsdk.main.ad.e eVar = this.c;
        af afVarA = eVar.a(str);
        return b.equals(str) ? eVar.b(afVarA, j, j2) : eVar.a(afVarA, j, j2);
    }

    protected void b() {
        com.excelliance.lbsdk.main.ad.e eVar = this.c;
        if (eVar != null) {
            eVar.close();
            this.c = null;
        }
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        String strA;
        int i2 = this.e;
        if (1 == i2) {
            strA = q.a(this.d, i, 1);
        } else {
            strA = 2 == i2 ? q.a(this.d, i, 2) : null;
        }
        com.excelliance.lbsdk.main.ad.e eVar = this.c;
        if (eVar != null && strA != null) {
            eVar.b(strA);
        }
        eVar.e();
        return true;
    }

    public com.excelliance.lbsdk.main.ad.e c() {
        return this.c;
    }
}
