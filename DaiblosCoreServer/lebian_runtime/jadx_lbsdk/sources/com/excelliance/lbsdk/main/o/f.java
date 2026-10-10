package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.s.af;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends b {
    private com.excelliance.lbsdk.main.ad.d a;
    private long b;
    private long c;
    private Context d;
    private com.excelliance.lbsdk.main.ad.e e;

    private class a extends com.excelliance.lbsdk.main.ad.e {
        public a(String str, URL url) {
            super(str, url);
        }

        @Override // com.excelliance.lbsdk.main.ad.e, com.excelliance.lbsdk.main.s.al
        protected RandomAccessFile a(File file, String str) {
            if (f.this.a == null) {
                f.this.a = (com.excelliance.lbsdk.main.ad.d) super.a(file, str);
                f.this.a.b(f.this.b, f.this.c);
            }
            return f.this.a;
        }

        @Override // com.excelliance.lbsdk.main.s.al, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            if (f.this.a != null) {
                f.this.a.a();
                f.this.a.close();
            }
        }
    }

    public f(Context context) {
        this.d = context;
    }

    private List<String> b(List<String> list, String str) {
        Enumeration<af> enumerationA = this.e.a();
        ArrayList arrayList = new ArrayList();
        while (enumerationA.hasMoreElements()) {
            af afVarNextElement = enumerationA.nextElement();
            if (list.contains(afVarNextElement.getName())) {
                arrayList.add(afVarNextElement);
            }
        }
        Collections.sort(arrayList, new Comparator<af>() { // from class: com.excelliance.lbsdk.main.o.f.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(af afVar, af afVar2) {
                return (int) (f.this.e.c(afVar) - f.this.e.c(afVar2));
            }
        });
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(((af) arrayList.get(i)).getName());
        }
        if (arrayList.size() > 0) {
            this.b = this.e.b((af) arrayList.get(0));
            af afVar = (af) arrayList.get(arrayList.size() - 1);
            this.c = (this.e.b(afVar) + this.e.d(afVar)) - this.b;
        }
        return arrayList2;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        return this.e.a(str).getSize();
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        long compressedSize = this.e.a(str).getCompressedSize();
        if (j2 <= 0) {
            j2 = compressedSize;
        }
        return Math.min(j2, compressedSize) - j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public List<String> a(List<String> list, String str) {
        return b(list, str);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        this.e = new a(i.a(), new URL(i.b(i)));
        this.e.e();
        return true;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2) {
        return !i.c(str, str2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2, long j, long j2, int i) {
        super.a(str, str2, j, j2, i);
        return false;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        com.excelliance.lbsdk.main.ad.e eVar = this.e;
        return eVar.a(eVar.a(str), j, j2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str) {
        super.b(str);
        return false;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        this.e.b(i.b(i));
        return true;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, String str2) {
        return i.c(str, str2);
    }
}
