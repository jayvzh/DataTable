package com.excelliance.lbsdk.main.p;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.q;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class a extends e {
    private static a f;
    private Context c;
    private List<com.excelliance.lbsdk.main.bwbx.a.C0001a> d;
    private List<C0003a> e;
    private List<com.excelliance.lbsdk.main.bwbx.a.C0001a> g;
    private List<com.excelliance.lbsdk.main.bwbx.a.C0001a> h;
    private ThreadLocal<List<com.excelliance.lbsdk.main.bwbx.a.C0001a>> i;
    private ConcurrentHashMap<Integer, Long> j;

    /* JADX INFO: renamed from: com.excelliance.lbsdk.main.p.a$a, reason: collision with other inner class name */
    static class C0003a {
        public long a;
        public long b;

        public C0003a(long j, long j2) {
            this.a = j;
            this.b = j2;
        }
    }

    public a(com.excelliance.lbsdk.main.b.c cVar) {
        super(cVar);
        this.i = new ThreadLocal<>();
        this.j = new ConcurrentHashMap<>();
        this.c = cVar.a();
    }

    private long a(List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list) {
        int size = list != null ? list.size() : 0;
        long jB = 0;
        for (int i = 0; i < size; i++) {
            jB += (long) list.get(i).b();
        }
        return jB;
    }

    private static com.excelliance.lbsdk.main.bwbx.a.C0001a a(HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> map, int i, int i2) {
        if (map == null || map.size() < 1) {
            return null;
        }
        return map.get(Long.valueOf(com.excelliance.lbsdk.main.bwbx.a.C0001a.a((short) i, (short) i2)));
    }

    public static com.excelliance.lbsdk.main.bwbx.a.C0001a a(List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list, int i, int i2) {
        if (list != null && list.size() >= 1) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a = list.get(i3);
                if (c0001a.c() == i && c0001a.d() == i2) {
                    return c0001a;
                }
            }
        }
        return null;
    }

    public static a a(Context context) {
        if (f == null) {
            synchronized (a.class) {
                if (f == null) {
                    f = new a(LBSdkHelper.application());
                }
            }
        }
        return f;
    }

    private HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> a(String str) {
        String cachePath = GameUtil.getCachePath(this.c, String.valueOf(Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true))));
        List<com.excelliance.lbsdk.main.bwbx.a.C0001a> listA = new com.excelliance.lbsdk.main.bwbx.a(this.c, cachePath + str).a();
        HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> map = new HashMap<>(listA.size());
        for (int i = 0; i < listA.size(); i++) {
            com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a = listA.get(i);
            map.put(Long.valueOf(c0001a.e()), c0001a);
        }
        return map;
    }

    private List<C0003a> a(long j, long j2, List<C0003a> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; j <= j2 && i < list.size(); i++) {
            C0003a c0003a = list.get(i);
            long j3 = c0003a.a;
            long j4 = c0003a.b;
            if (j3 <= j) {
                j = Math.max(j, j4 + 1);
            } else {
                arrayList.add(new C0003a(j, Math.min(j2, j3 - 1)));
                j = j4 + 1;
            }
        }
        if (j <= j2) {
            arrayList.add(new C0003a(j, j2));
        }
        return arrayList;
    }

    private void a(HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> map, HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> map2) {
        if (map == null) {
            return;
        }
        int size = ((map.size() + 1) / 2) + 10;
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        Iterator<Long> it = map.keySet().iterator();
        while (it.hasNext()) {
            com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a = map.get(Long.valueOf(it.next().longValue()));
            if (a(map2, c0001a.c(), c0001a.d()) != null) {
                arrayList.add(c0001a);
            } else {
                arrayList2.add(c0001a);
            }
        }
        this.d = arrayList;
        this.g = arrayList2;
    }

    private void d(long j, long j2) {
        b.a(this.c).a();
        boolean z = true;
        while (z) {
            long jG = 0;
            int i = 0;
            if (q.b) {
                q();
                com.excelliance.lbsdk.main.g.b.c("BigFileDownloader", "moreThreadDownloadFile canceled", new Object[0]);
                return;
            }
            if (q.c) {
                q();
                throw new RuntimeException("speed is too slowly, change url");
            }
            if (this.a != null) {
                boolean z2 = false;
                while (i < this.a.length) {
                    if (this.a[i] != null && b(this.a[i].a())) {
                        z2 = true;
                    }
                    i++;
                    jG += g(i);
                }
                b.a(this.c).a(jG + j, j2);
                z = z2;
            } else {
                z = false;
            }
            a_();
            if (z) {
                try {
                    Thread.sleep(200L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private long g(int i) {
        Long l = this.j.get(Integer.valueOf(i));
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    private void j() {
        List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list = this.g;
        Collections.sort(list, new Comparator<com.excelliance.lbsdk.main.bwbx.a.C0001a>() { // from class: com.excelliance.lbsdk.main.p.a.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a, com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a2) {
                return (c0001a.a() > c0001a2.a() ? 1 : (c0001a.a() == c0001a2.a() ? 0 : -1));
            }
        });
        this.h = new ArrayList(list.size());
        this.h.addAll(list);
    }

    private List<C0003a> k() {
        List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list = this.d;
        Collections.sort(list, new Comparator<com.excelliance.lbsdk.main.bwbx.a.C0001a>() { // from class: com.excelliance.lbsdk.main.p.a.2
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a, com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a2) {
                return (c0001a.a() > c0001a2.a() ? 1 : (c0001a.a() == c0001a2.a() ? 0 : -1));
            }
        });
        ArrayList arrayList = new ArrayList();
        int i = 0;
        C0003a c0003a = null;
        while (i < list.size()) {
            com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a = list.get(i);
            if (c0003a == null) {
                c0003a = new C0003a(c0001a.a(), (c0001a.a() + ((long) c0001a.b())) - 1);
                arrayList.add(c0003a);
            } else if (c0003a.b + 1 == c0001a.a()) {
                c0003a.b = (c0001a.a() + ((long) c0001a.b())) - 1;
            } else {
                i--;
                c0003a = null;
            }
            i++;
        }
        return arrayList;
    }

    private HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> l() {
        return a("assets/lbres/lbres.rdata.mp4.idx.left");
    }

    private HashMap<Long, com.excelliance.lbsdk.main.bwbx.a.C0001a> m() {
        return a("assets/lbres/lbres.rdata.mp4.idx");
    }

    private int n() {
        return Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true));
    }

    private void o() {
        a(l(), m());
        this.e = k();
        j();
    }

    private String p() {
        return GameUtil.getCachePath(this.c, String.valueOf(n())) + "assets/lbres/lbres.rdata.mp4";
    }

    private void q() {
        int length = this.a != null ? this.a.length : 0;
        for (int i = 0; i < length; i++) {
            f fVar = this.a[i];
            if (fVar != null) {
                fVar.c();
                this.a[i] = null;
            }
        }
    }

    List<C0003a> a(long j, long j2, int i) {
        return a(j, j2, this.e);
    }

    @Override // com.excelliance.lbsdk.main.p.e
    public void a() {
        super.a();
    }

    void a(int i) {
    }

    void a(int i, long j) {
        this.j.put(Integer.valueOf(i), Long.valueOf(j));
    }

    void a(int i, long j, long j2) {
        List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list = this.h;
        synchronized (this) {
            List<com.excelliance.lbsdk.main.bwbx.a.C0001a> arrayList = this.i.get();
            if (arrayList == null) {
                arrayList = new ArrayList<>(10);
                this.i.set(arrayList);
            }
            arrayList.clear();
            int size = list != null ? list.size() : 0;
            for (int i2 = 0; i2 < size; i2++) {
                com.excelliance.lbsdk.main.bwbx.a.C0001a c0001a = list.get(i2);
                long jA = c0001a.a();
                long jB = c0001a.b();
                if (j > jA || jA + jB > j + j2) {
                    if (!arrayList.isEmpty()) {
                        break;
                    }
                } else {
                    arrayList.add(c0001a);
                }
            }
            if (!arrayList.isEmpty()) {
                try {
                    com.excelliance.lbsdk.main.bwbx.g.a().a(this.c, arrayList);
                    list.removeAll(arrayList);
                    com.excelliance.lbsdk.main.g.b.c("BigFileDownloader", "writeIdxForDownload(), remain slice size:" + list.size(), new Object[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void a(int i, String str, long j) throws IOException {
        long jB = ae.b(this.c, "lebian_module_dloadinfo", "bigAssetTwiceMp4Size", 0L);
        long j2 = jB + j;
        q();
        o();
        this.j.clear();
        long jA = a(this.g);
        c(3);
        File file = new File(str);
        if (!file.exists()) {
            file.createNewFile();
        }
        File file2 = new File(p());
        long j3 = j - jA;
        q.a(jA);
        b(j, j3);
        long jA2 = a(j, 3);
        long j4 = j3;
        String str2 = "BigFileDownloader";
        com.excelliance.lbsdk.main.g.b.c("BigFileDownloader", "block ： " + jA2, new Object[0]);
        int i2 = 0;
        while (i2 < this.a.length) {
            long j5 = jB + (((long) i2) * jA2);
            int i3 = i2 + 1;
            this.a[i2] = new f(this.c, file2, j5, Math.min(j5 + jA2, j2), 0L, i3, i);
            this.a[i2].a(this);
            this.a[i2].start();
            i2 = i3;
            jA2 = jA2;
            j4 = j4;
            str2 = str2;
        }
        com.excelliance.lbsdk.main.g.b.c(str2, "downloadBigFileZipFile: undownload slice count: " + this.h.size() + ", undownloadSize: " + jA, new Object[0]);
        d(j4, j);
    }

    void a(long j) {
        b(j);
    }

    public void a(File file) {
        try {
            com.excelliance.lbsdk.main.bwbx.g.a().a(this.c, this.g);
            if (file.exists()) {
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean a(Exception exc) {
        return b(exc);
    }

    public long b() {
        a(l(), m());
        return a(this.g);
    }

    public List<com.excelliance.lbsdk.main.bwbx.a.C0001a> c() {
        a(l(), m());
        return this.g;
    }
}
