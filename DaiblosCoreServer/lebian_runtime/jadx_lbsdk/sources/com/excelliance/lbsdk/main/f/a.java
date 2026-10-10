package com.excelliance.lbsdk.main.f;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private File a;
    private b.a b;
    private c c;
    private long d = -1;
    private c.a e = new c.a() { // from class: com.excelliance.lbsdk.main.f.a.1
        @Override // com.excelliance.lbsdk.main.f.c.a
        public void a(File file) {
            if (a.this.a(file)) {
                a.a(a.this, file.length());
            }
        }
    };

    public a(File file, b.a aVar) {
        this.a = file;
        this.b = aVar;
    }

    static /* synthetic */ long a(a aVar, long j) {
        long j2 = aVar.d + j;
        aVar.d = j2;
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(File file) {
        b.a aVar = this.b;
        if (aVar == null) {
            return true;
        }
        return aVar.a(file);
    }

    private void b() {
        c cVar = this.c;
        if (cVar == null) {
            cVar = new c(this.a);
            this.c = cVar;
        }
        cVar.a(this.e);
        this.d = 0L;
        cVar.a();
    }

    public long a() {
        long j = this.d;
        if (-1 != j) {
            return j;
        }
        b();
        return this.d;
    }

    public void a(c cVar) {
        if (cVar == null) {
            return;
        }
        this.c = cVar;
        cVar.a(this.e);
    }
}
