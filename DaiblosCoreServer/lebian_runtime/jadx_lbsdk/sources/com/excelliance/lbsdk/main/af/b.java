package com.excelliance.lbsdk.main.af;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ab.e;
import com.excelliance.lbsdk.main.ai.d;
import com.excelliance.lbsdk.main.b.f;
import com.excelliance.lbsdk.main.bwbx.AssetRedirector;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class b extends f {
    public static final ArrayList<String> a = new ArrayList<>();
    private Context b;
    private e c;
    private a d;
    private long e = 0;
    private long f = 0;
    private List<String> g = new ArrayList();
    private ZipFile h = null;
    private ZipFile i = null;
    private BufferedWriter j;

    static {
        a.add("assets/assetsonly.jar");
        a.add("assets/lbsdk.jar");
        a.add("assets/lbmust.jar");
        a.add("assets/lbui.jar");
        a.add("assets/lbvmrt.jar");
        a.add("assets/lebianFlag.xml");
        a.add("assets/localData.png");
        a.add("assets/origSize.txt");
        a.add("assets/tryflag");
        a.add("assets/zmunity.small");
        a.add("assets/zmtest");
        a.add("assets/lbbigres.info");
        a.add("assets/lbres.meta");
    }

    public b(Context context) {
        this.b = context;
        this.c = new e(context, this);
    }

    private boolean c(String str) {
        boolean z = str != null && (str.startsWith("assets/lebian") || str.startsWith("assets/lbres"));
        return !z ? a.contains(str) : z;
    }

    private boolean d(String str) {
        return str != null && str.startsWith("assets/");
    }

    private void e(String str) {
        if (b(str)) {
            if (this.i == null) {
                this.e += a(this.b, str);
                return;
            }
            ZipFile zipFile = this.h;
            if (zipFile != null) {
                try {
                    if (zipFile.getEntry(str) == null) {
                        this.e += a(this.b, str);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    private boolean f(String str) {
        return this.h.getEntry(str) != null;
    }

    private ZipFile g(String str) {
        if (!d.a(str)) {
            return null;
        }
        try {
            return new ZipFile(str);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("DryRun", e);
            return null;
        }
    }

    private void h() {
        i();
        this.h = g(this.b.getPackageCodePath());
        if (BwbxUtil.isSmallPkg()) {
            this.f = new File(this.b.getPackageCodePath()).length();
        } else {
            this.f = new File(this.b.getPackageCodePath()).length();
            Enumeration<? extends ZipEntry> enumerationEntries = this.h.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                if (!zipEntryNextElement.isDirectory() && b(zipEntryNextElement.getName())) {
                    this.f -= zipEntryNextElement.getCompressedSize();
                }
            }
        }
        this.i = g(BwbxUtil.getSdApk(this.b));
        Iterator<String> it = this.g.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
        com.excelliance.lbsdk.main.g.b.c("DryRun", "recordInit: recordedSize:" + this.e + " smallApkSize:" + this.f, new Object[0]);
        j();
    }

    private void h(String str) {
        if (BwbxUtil.isSmallPkg() && f(str)) {
            return;
        }
        e(str);
    }

    private void i() {
        String line;
        if (BwbxUtil.isRecordPkg(this.b)) {
            try {
                File file = new File(GameUtil.getExternalDataPath(this.b) + "/fileSet.txt");
                if (file.exists()) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    do {
                        line = bufferedReader.readLine();
                        if (line != null) {
                            this.g.add(line);
                        }
                    } while (line != null);
                    bufferedReader.close();
                    com.excelliance.lbsdk.main.g.b.b("DryRun", "recordList size:" + this.g.size(), new Object[0]);
                }
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.a("DryRun", e);
            }
        }
    }

    private void j() {
        try {
            String recordPath = BwbxUtil.getRecordPath(this.b);
            File file = new File(GameUtil.getExternalDataPath(this.b) + "/", "lbsmall.zip");
            if (file.exists()) {
                com.excelliance.lbsdk.main.g.b.b("DryRun", "start unzip small zip:" + file + " target:" + recordPath, new Object[0]);
                GameUtil.getIntance().unZipFile(file, recordPath, true);
                com.excelliance.lbsdk.main.g.b.b("DryRun", "unzip small zip success", new Object[0]);
                file.delete();
            }
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.a("DryRun", e);
        }
    }

    private BufferedWriter k() {
        BufferedWriter bufferedWriter = this.j;
        if (bufferedWriter != null) {
            return bufferedWriter;
        }
        synchronized (this) {
            if (this.j != null) {
                return this.j;
            }
            this.j = new BufferedWriter(new FileWriter(((com.excelliance.lbsdk.main.l.e) getService("Runtime")).h() + "/fileSet.txt", true));
            return this.j;
        }
    }

    public long a() {
        return this.c.a();
    }

    public long a(Context context, String str) {
        ZipEntry entry;
        try {
            if (this.h != null) {
                ZipEntry entry2 = this.h.getEntry(str);
                if (entry2 != null) {
                    return entry2.getCompressedSize();
                }
            } else if (this.i != null && (entry = this.i.getEntry(str)) != null) {
                return entry.getCompressedSize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.excelliance.lbsdk.main.l.e eVar = (com.excelliance.lbsdk.main.l.e) getService("Runtime");
        File file = new File(!BwbxUtil.isSmallPkg() ? eVar.b() : eVar.c(), str);
        if (file.exists()) {
            return file.length();
        }
        return 0L;
    }

    public void a(String str, boolean z) {
        com.excelliance.lbsdk.main.g.b.a("DryRun", "doRecord enter", new Object[0]);
        if (str == null || str.length() <= 0) {
            return;
        }
        if (str.charAt(0) != '/' && !str.startsWith("assets/")) {
            if (!str.startsWith("res/") && (str.endsWith(".epg") || str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".pg2"))) {
                str = "res/" + str;
            }
            str = "assets/" + str;
        }
        if (!b(str) || this.g.contains(str)) {
            com.excelliance.lbsdk.main.g.b.a("DryRun", "doRecord recordList contains:" + str, new Object[0]);
            return;
        }
        boolean z2 = str.split(":").length == 2;
        com.excelliance.lbsdk.main.g.b.a("DryRun", "hasBno :" + z2, new Object[0]);
        if (!z2) {
            try {
                BufferedWriter bufferedWriterK = k();
                bufferedWriterK.write(str);
                bufferedWriterK.newLine();
                this.g.add(str);
            } catch (IOException e) {
                com.excelliance.lbsdk.main.g.b.a("DryRun", e);
            }
        }
        if (z) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(GameUtil.getExternalDataPath(this.b) + "/lbAssetManagerFileSet.txt", true));
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception unused) {
                com.excelliance.lbsdk.main.g.b.c("DryRun", "doRecord exception", new Object[0]);
            }
        }
        if (BwbxUtil.isBigAsset(this.b)) {
            return;
        }
        h(str);
    }

    public boolean a(String str) {
        return this.c.a(str);
    }

    public boolean a(long[] jArr) {
        if (BwbxUtil.isBigAsset(getContext())) {
            AssetRedirector.getInstance().getBigVisistedStat(jArr);
            long j = jArr[1];
            long j2 = this.f;
            jArr[1] = j + j2;
            jArr[1] = jArr[1] + j2;
        } else {
            jArr[0] = e();
            jArr[1] = f();
        }
        return true;
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void attach(com.excelliance.lbsdk.main.b.c cVar) {
        super.attach(cVar);
    }

    public int b() {
        return this.c.b();
    }

    public boolean b(String str) {
        if (str == null || !d(str) || c(str)) {
            return false;
        }
        return !a(str);
    }

    public long c() {
        return a() + this.f + this.e;
    }

    public int d() {
        this.e = 0L;
        List<String> list = this.g;
        if (list == null) {
            return 0;
        }
        list.clear();
        return 0;
    }

    public int e() {
        return b() + this.g.size();
    }

    public long f() {
        return a() + this.f + this.e;
    }

    void g() {
        BufferedWriter bufferedWriter = this.j;
        if (bufferedWriter == null) {
            return;
        }
        synchronized (this) {
            try {
                bufferedWriter.flush();
            } catch (IOException e) {
                com.excelliance.lbsdk.main.g.b.a("DryRun", e);
            }
        }
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void initialize() {
        this.c.c();
        h();
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void postLoadSo() {
        super.postLoadSo();
        if (this.d == null) {
            this.d = new a(this.b, this);
        }
        this.d.b();
        this.d.a();
    }
}
