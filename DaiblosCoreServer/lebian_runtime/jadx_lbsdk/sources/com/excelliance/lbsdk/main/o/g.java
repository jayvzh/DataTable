package com.excelliance.lbsdk.main.o;

import android.content.Context;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class g extends b {
    private Context a;
    private i b;

    public g(Context context) {
        this.a = context;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        return this.b.a(str);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return this.b.a(str, j);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        return this.b.a(str, j, j2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public void a(String str, InputStream inputStream) {
        this.b.a(str, inputStream);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        if (this.b == null) {
            this.b = i.a(this.a, 1, i);
        }
        return this.b.a(str, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2, long j, long j2, int i) {
        return this.b.a(str, str2, j, j2, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        return this.b.b(str, j, j2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str) {
        return this.b.b(str);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        if (this.b == null) {
            this.b = i.a(this.a, 1, i);
        }
        return this.b.b(str, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean c(String str, long j, long j2) {
        return this.b.c(str, j, j2);
    }
}
