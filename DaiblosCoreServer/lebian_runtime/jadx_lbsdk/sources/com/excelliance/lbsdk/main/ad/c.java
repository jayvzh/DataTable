package com.excelliance.lbsdk.main.ad;

import android.content.Context;
import com.excelliance.lbsdk.main.aa;
import com.excelliance.lbsdk.main.aa.f;
import com.excelliance.lbsdk.main.aa.h;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.excelliance.lbsdk.main.aa.b {
    private f c(String str, long j, long j2) {
        return d(str, j, j2);
    }

    private f d(String str, long j, long j2) {
        String str2;
        com.excelliance.lbsdk.main.aa.c cVar = new com.excelliance.lbsdk.main.aa.c(str);
        if (j2 <= 0) {
            if (j > 0) {
                cVar.a("Accept-Encoding", "identity");
                str2 = "bytes=" + j + "-";
            } else {
                cVar.a("Accept-Encoding", "gzip,deflate");
            }
            h hVarA = h.a();
            hVarA.b(30000).a(30000).b(false).a(true);
            cVar.a(hVarA);
            return a(cVar);
        }
        cVar.a("Accept-Encoding", "identity");
        str2 = "bytes=" + j + "-" + j2;
        cVar.a("Range", str2);
        h hVarA2 = h.a();
        hVarA2.b(30000).a(30000).b(false).a(true);
        cVar.a(hVarA2);
        return a(cVar);
    }

    public long a(String str) {
        com.excelliance.lbsdk.main.aa.d dVar = new com.excelliance.lbsdk.main.aa.d(str);
        h hVarA = h.a();
        hVarA.b(30000).a(30000).b(false).a(false);
        dVar.a(hVarA);
        return a(dVar).a();
    }

    public f a(String str, long j) {
        return c(str, j, -1L);
    }

    public byte[] a(String str, long j, long j2) throws IOException {
        f fVarC = c(str, j, j2);
        long jA = fVarC.a();
        if (-1 == jA) {
            jA = (j2 - j) + 1;
        }
        int i = (int) jA;
        if (i < 0) {
            i = 0;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        InputStream inputStreamB = fVarC.b();
        com.excelliance.lbsdk.main.w.c.a(inputStreamB, byteArrayOutputStream);
        inputStreamB.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public f b(String str) {
        return c(str, -1L, -1L);
    }

    public f b(String str, long j, long j2) {
        return c(str, j, j2);
    }

    @Override // com.excelliance.lbsdk.main.aa.b
    protected HttpURLConnection b(com.excelliance.lbsdk.main.aa.e eVar) {
        return aa.a(new URL(eVar.b()), (Context) null);
    }
}
