package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.BigFileInfo;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.AssetRedirector;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e extends b {
    private com.excelliance.lbsdk.main.ad.d a;
    private Context b;
    private List<com.excelliance.lbsdk.main.bwbx.a.C0001a> c;
    private List<String> d;
    private long[] e;
    private long f;
    private com.excelliance.lbsdk.main.ad.e g;
    private long h;
    private long i;

    private class a extends com.excelliance.lbsdk.main.ad.e {
        public a(String str, URL url) {
            super(str, url);
        }

        @Override // com.excelliance.lbsdk.main.ad.e, com.excelliance.lbsdk.main.s.al
        protected RandomAccessFile a(File file, String str) {
            if (e.this.a == null) {
                e.this.a = (com.excelliance.lbsdk.main.ad.d) super.a(file, str);
                e.this.a.b((e.this.h - e.this.f) + e.this.g.b(e.this.g.a(i.b)), e.this.i);
            }
            return e.this.a;
        }

        @Override // com.excelliance.lbsdk.main.s.al, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            super.close();
            if (e.this.a != null) {
                e.this.a.close();
            }
        }
    }

    public e(Context context) {
        this.b = context;
    }

    private List<String> a(List<String> list) {
        List<com.excelliance.lbsdk.main.bwbx.a.C0001a> listC = com.excelliance.lbsdk.main.p.a.a(this.b).c();
        List<String> listA = a(b(list, listC), listC);
        com.excelliance.lbsdk.main.bwbx.b.a(this.c);
        HashMap<String, BigFileInfo> bigFileList = BwbxUtil.getBigFileList();
        if (listA.size() > 0) {
            String str = listA.get(0);
            com.excelliance.lbsdk.main.bwbx.a.C0001a c0001aA = com.excelliance.lbsdk.main.p.a.a(listC, com.excelliance.lbsdk.main.bwbx.f.a(bigFileList, str), (int) com.excelliance.lbsdk.main.bwbx.f.b(str));
            this.h += c0001aA.a();
            String str2 = listA.get(listA.size() - 1);
            com.excelliance.lbsdk.main.bwbx.a.C0001a c0001aA2 = com.excelliance.lbsdk.main.p.a.a(listC, com.excelliance.lbsdk.main.bwbx.f.a(bigFileList, str2), (int) com.excelliance.lbsdk.main.bwbx.f.b(str2));
            this.i = (c0001aA2.a() + ((long) c0001aA2.b())) - c0001aA.a();
        }
        return listA;
    }

    public static List<String> a(List<String> list, final List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list2) {
        final HashMap<String, BigFileInfo> bigFileList = BwbxUtil.getBigFileList();
        Collections.sort(list, new Comparator<String>() { // from class: com.excelliance.lbsdk.main.o.e.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(String str, String str2) {
                long jA = e.b(str, (List<com.excelliance.lbsdk.main.bwbx.a.C0001a>) list2, (HashMap<String, BigFileInfo>) bigFileList).a() - e.b(str2, (List<com.excelliance.lbsdk.main.bwbx.a.C0001a>) list2, (HashMap<String, BigFileInfo>) bigFileList).a();
                if (jA < 0) {
                    return -1;
                }
                return jA == 0 ? 0 : 1;
            }
        });
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.excelliance.lbsdk.main.bwbx.a.C0001a b(String str, List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list, HashMap<String, BigFileInfo> map) {
        return com.excelliance.lbsdk.main.p.a.a(list, com.excelliance.lbsdk.main.bwbx.f.a(map, str), (int) com.excelliance.lbsdk.main.bwbx.f.b(str));
    }

    private List<String> b(List<String> list, List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list2) {
        HashMap<String, BigFileInfo> bigFileList = BwbxUtil.getBigFileList();
        ArrayList arrayList = new ArrayList();
        int size = list != null ? list.size() : 0;
        this.c = new ArrayList();
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            com.excelliance.lbsdk.main.bwbx.a.C0001a c0001aA = com.excelliance.lbsdk.main.p.a.a(list2, com.excelliance.lbsdk.main.bwbx.f.a(bigFileList, str), (int) com.excelliance.lbsdk.main.bwbx.f.b(str));
            if (c0001aA != null) {
                arrayList.add(str);
                this.c.add(c0001aA);
            }
        }
        return arrayList;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
        return this.e[1];
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
        return this.e[1] - j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public List<String> a(List<String> list, String str) {
        this.d = a(list);
        return this.d;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        this.g = new a(i.a(), new URL(i.b(i)));
        if (!((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).d()) {
            return true;
        }
        this.f = i.a(i.c(i));
        return true;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        return AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        if (this.e == null) {
            this.e = new long[3];
        }
        this.e[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.e);
        long j3 = (j + this.e[0]) - this.f;
        com.excelliance.lbsdk.main.ad.e eVar = this.g;
        return eVar.a(eVar.a(i.b), j3, j2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        this.g.b(i.b(i));
        return true;
    }
}
