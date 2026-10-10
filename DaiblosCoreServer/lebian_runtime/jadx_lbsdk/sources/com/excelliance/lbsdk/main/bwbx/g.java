package com.excelliance.lbsdk.main.bwbx;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ae;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static g a;
    private List<Long> b;
    private int c;
    private a e;
    private Context g;
    private String h;
    private long i;
    private boolean d = false;
    private int f = 0;

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    public static List<Long> b(Context context) {
        String line;
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(com.excelliance.lbsdk.main.p.d.a(context, "lebian/commonCentral.txt")));
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    for (String str : line.split(";")) {
                        arrayList.add(Long.valueOf(str));
                    }
                }
            } while (line != null);
            bufferedReader.close();
        } catch (Exception unused) {
        }
        return arrayList;
    }

    private void b(int i) {
        this.c = i;
    }

    private long d(Context context) {
        if (this.i == 0) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(com.excelliance.lbsdk.main.p.d.a(context, "lebian/commonCentralBa.txt")));
                this.i = Long.parseLong(bufferedReader.readLine());
                bufferedReader.close();
            } catch (Exception unused) {
            }
        }
        return this.i;
    }

    public long a(Context context) {
        int iMax = Math.max(0, b());
        if (!this.d) {
            if (this.b == null) {
                this.b = b(context);
            }
            if (iMax >= this.b.size()) {
                return -1L;
            }
            return this.b.get(iMax).longValue();
        }
        if (this.e == null) {
            this.e = new a(context, this.h + "assets/lbres/lbres.rdata.mp4.idx.left");
        }
        if (iMax >= this.e.a().size()) {
            return -1L;
        }
        return this.e.a(iMax).d;
    }

    public long a(Context context, int i) {
        a(i);
        return a(context);
    }

    public g a(int i) {
        a.b(i);
        return a;
    }

    public void a(Context context, List<a.C0001a> list) throws IOException {
        com.excelliance.lbsdk.main.g.b.c("SplitZipManager", "writeIdx start", new Object[0]);
        if (this.d) {
            String str = this.h + "assets/lbres/lbres.rdata.mp4.idx";
            List<a.C0001a> listA = new a(context, str).a();
            String str2 = str + ".tmp";
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            int size = listA != null ? listA.size() : 0;
            for (int i = 0; i < size; i++) {
                a.C0001a c0001a = listA.get(i);
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("SplitZipManager", "writeIdx write original:" + c0001a, new Object[0]);
                }
                c0001a.a(fileOutputStream);
            }
            int size2 = list != null ? list.size() : 0;
            for (int i2 = 0; i2 < size2; i2++) {
                a.C0001a c0001a2 = list.get(i2);
                if (!listA.contains(c0001a2)) {
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("SplitZipManager", "writeIdx write:" + c0001a2, new Object[0]);
                    }
                    c0001a2.a(fileOutputStream);
                }
            }
            fileOutputStream.close();
            File file = new File(str);
            File file2 = new File(str2);
            file.delete();
            file2.renameTo(file);
        }
    }

    public void a(Context context, boolean z, int i) {
        this.g = context;
        this.d = z;
        this.f = -1;
        this.h = GameUtil.getCachePath(context, String.valueOf(i));
        com.excelliance.lbsdk.main.g.b.c("SplitZipManager", "mBlockCountInSmall = " + this.f, new Object[0]);
        if (this.f == -1 && z) {
            this.f = new a(context, this.h + "assets/lbres/lbres.rdata.mp4.idx").a("lbres/lbres.rdata.mp4.idx");
            com.excelliance.lbsdk.main.g.b.c("SplitZipManager", "mBlockCountInSmall read count = " + this.f, new Object[0]);
            ae.a(context, "lebian_module_bwbxinfo", "block_count_small_" + i, this.f);
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0033  */
    public boolean a(int i, int i2) {
        boolean z = true;
        if (this.d) {
            if (this.e == null) {
                this.e = new a(this.g, this.h + "assets/lbres/lbres.rdata.mp4.idx.left");
            }
            if (b() < this.e.b()) {
                z = false;
            }
        } else if (i < i2) {
            z = false;
        }
        com.excelliance.lbsdk.main.g.b.c("SplitZipManager", "is all asset finish: " + z, new Object[0]);
        return z;
    }

    public int b() {
        if (!this.d) {
            return this.c - ae.b(this.g, "lebian_module_dloadinfo", "twice_entry_size", 0);
        }
        return new a(this.g, this.h + "assets/lbres/lbres.rdata.mp4.idx").b() - this.f;
    }

    public long c() {
        if (this.d) {
            return d(this.g);
        }
        GameUtil.getIntance().setContext(this.g);
        return GameUtil.getIntance().getOrigSize(true, 1);
    }

    public long c(Context context) {
        return com.excelliance.lbsdk.main.p.a.a(context).b();
    }
}
