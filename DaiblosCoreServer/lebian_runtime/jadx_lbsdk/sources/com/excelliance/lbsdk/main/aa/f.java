package com.excelliance.lbsdk.main.aa;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private HttpURLConnection a;
    private int b;

    private f() {
    }

    private long a(String str, long j) {
        try {
            return Long.parseLong(this.a.getHeaderField(str));
        } catch (Exception unused) {
            return j;
        }
    }

    static f a(int i, HttpURLConnection httpURLConnection) {
        f fVar = new f();
        fVar.a(httpURLConnection);
        fVar.a(i);
        return fVar;
    }

    public long a() {
        return a("content-length", -1L);
    }

    void a(int i) {
        this.b = i;
    }

    void a(HttpURLConnection httpURLConnection) {
        this.a = httpURLConnection;
    }

    public InputStream b() {
        HttpURLConnection httpURLConnection = this.a;
        String contentEncoding = httpURLConnection.getContentEncoding();
        InputStream inputStream = httpURLConnection.getInputStream();
        return (contentEncoding == null || !contentEncoding.contains("gzip")) ? inputStream : new GZIPInputStream(inputStream);
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}
