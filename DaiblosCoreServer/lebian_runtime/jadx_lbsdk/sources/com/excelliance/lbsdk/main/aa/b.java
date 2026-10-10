package com.excelliance.lbsdk.main.aa;

import com.excelliance.lbsdk.main.ao;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class b {

    public static class a extends IOException {
        private int a;

        public a(int i) {
            this.a = i;
        }

        public int getHttpCode() {
            return this.a;
        }
    }

    public f a(e eVar) throws IOException {
        HttpURLConnection httpURLConnectionB = b(eVar);
        httpURLConnectionB.setRequestMethod(eVar.a());
        h hVarC = eVar.c();
        httpURLConnectionB.setConnectTimeout(hVarC.d());
        httpURLConnectionB.setReadTimeout(hVarC.e());
        httpURLConnectionB.setDoOutput(hVarC.c());
        httpURLConnectionB.setDoInput(hVarC.b());
        com.excelliance.lbsdk.main.aa.a[] aVarArrD = eVar.d();
        if (aVarArrD != null) {
            for (com.excelliance.lbsdk.main.aa.a aVar : aVarArrD) {
                httpURLConnectionB.setRequestProperty(aVar.a(), aVar.b());
            }
        }
        int responseCode = httpURLConnectionB.getResponseCode();
        ao.c("HttpClient", "responseCode=%d, url=%s", Integer.valueOf(responseCode), eVar.b());
        if (responseCode == 200 || responseCode == 206) {
            return f.a(responseCode, httpURLConnectionB);
        }
        throw new a(responseCode);
    }

    protected HttpURLConnection b(e eVar) {
        return (HttpURLConnection) new URL(eVar.b()).openConnection();
    }
}
