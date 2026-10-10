package com.excelliance.lbsdk.main.f;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class b {
    private File a;
    private a b;
    private c c;
    private int d = -1;
    private c.a e = new c.a() { // from class: com.excelliance.lbsdk.main.f.b.1
        @Override // com.excelliance.lbsdk.main.f.c.a
        public void a(File file) {
            if (b.this.a(file)) {
                b.a(b.this);
            }
        }
    };

    public interface a {
        boolean a(File file);
    }

    public b(File file, a aVar) {
        this.a = file;
        this.b = aVar;
    }

    static /* synthetic */ int a(b bVar) {
        int i = bVar.d;
        bVar.d = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(File file) {
        a aVar = this.b;
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
        this.d = 0;
        cVar.a();
    }

    public int a() {
        int i = this.d;
        if (-1 != i) {
            return i;
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
