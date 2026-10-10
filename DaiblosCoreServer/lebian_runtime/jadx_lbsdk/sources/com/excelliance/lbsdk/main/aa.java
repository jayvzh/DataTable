package com.excelliance.lbsdk.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class aa {
    public static o a(String str, Context context) {
        int iIndexOf = str.indexOf(".") + 1;
        String strSubstring = str.contains("aishengji") ? "aishengji" : str.substring(iIndexOf, iIndexOf + 4);
        return str.startsWith("https://") ? b(str, context, strSubstring) : a(str, context, strSubstring);
    }

    public static o a(String str, Context context, String str2) throws Throwable {
        HttpURLConnection httpURLConnection;
        o oVar = new o();
        oVar.a = -1;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                URL url = new URL(str);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(60000);
                    httpURLConnection.setReadTimeout(60000);
                    oVar.a = httpURLConnection.getResponseCode();
                    ao.c("NetworkUtil", "responseCode=%d, url=%s", Integer.valueOf(oVar.a), url);
                    if (oVar.a == 200) {
                        byte[] bArr = new byte[1024];
                        InputStream inputStream = httpURLConnection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                        oVar.b = new String(byteArrayOutputStream.toByteArray());
                        ao.c("NetworkUtil", "response=%s", oVar.b);
                        inputStream.close();
                        byteArrayOutputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Exception e) {
                    e = e;
                    httpURLConnection2 = httpURLConnection;
                    ao.c("NetworkUtil", "url=%s, e=%s", str, e.toString());
                    if (e.toString().toLowerCase().contains("cleartext")) {
                        String strA = a(context, str);
                        ao.c("NetworkUtil", "reqHttp: change url to https", new Object[0]);
                        oVar = b(strA, context, str2);
                    } else {
                        ao.a("NetworkUtil", e);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return oVar;
    }

    public static o a(String str, Context context, JSONObject jSONObject, String str2) throws Throwable {
        HttpURLConnection httpURLConnection;
        o oVar = new o();
        oVar.a = -1;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                URL url = new URL(str);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(60000);
                    httpURLConnection.setReadTimeout(60000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestProperty("Charset", "UTF-8");
                    byte[] bytes = jSONObject.toString().getBytes();
                    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    httpURLConnection.setRequestProperty("accept", "application/json");
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(bytes, 0, bytes.length);
                    outputStream.flush();
                    oVar.a = httpURLConnection.getResponseCode();
                    ao.c("NetworkUtil", "responseCode=%d, url=%s", Integer.valueOf(oVar.a), url);
                    if (oVar.a == 200) {
                        byte[] bArr = new byte[1024];
                        InputStream inputStream = httpURLConnection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                        oVar.b = new String(byteArrayOutputStream.toByteArray());
                        ao.c("NetworkUtil", "response=%s", oVar.b);
                        inputStream.close();
                        byteArrayOutputStream.close();
                        outputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Exception e) {
                    e = e;
                    httpURLConnection2 = httpURLConnection;
                    ao.c("NetworkUtil", "url=%s", str);
                    if (e.toString().toLowerCase().contains("cleartext")) {
                        String strA = a(context, str);
                        ao.c("NetworkUtil", "reqHttp: change url to https", new Object[0]);
                        oVar = b(strA, context, str2);
                    } else {
                        ao.a("NetworkUtil", e);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return oVar;
    }

    public static o a(String str, JSONObject jSONObject, Context context) {
        int iIndexOf = str.indexOf(".") + 1;
        String strSubstring = str.contains("aishengji") ? "aishengji" : str.substring(iIndexOf, iIndexOf + 4);
        ao.c("NetworkUtil", "reqPost ，url=%s", str);
        return str.startsWith("https://") ? b(str, context, jSONObject, strSubstring) : a(str, context, jSONObject, strSubstring);
    }

    private static String a(Context context, String str) {
        if (m.a(context, "SELECTED_AREA", 0) == 2) {
            String strReplace = str.replace("http://", "https://").replace("sdktw.loveota.net", "sdktwssl.loveota.net");
            m.b(context, "USE_HTTP_OR_HTTPS", 2);
            return strReplace;
        }
        String strReplace2 = str.replace("http://", "https://").replace(".loveota.com", ".lbota.com");
        m.b(context, "USE_HTTP_OR_HTTPS", 1);
        return strReplace2;
    }

    public static HttpURLConnection a(HttpURLConnection httpURLConnection, boolean z) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            if (z) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(new d());
            }
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(a(httpURLConnection.getURL().getHost()));
        }
        return httpURLConnection;
    }

    public static HttpURLConnection a(URL url, Context context) {
        if (url.getProtocol().startsWith("https://".substring(0, "https://".indexOf("://"))) && !url.getHost().contains(".lbota.com") && Build.VERSION.SDK_INT < 21 && !(HttpsURLConnection.getDefaultSSLSocketFactory() instanceof d)) {
            ao.b("NetworkUtil", HttpsURLConnection.getDefaultSSLSocketFactory().getClass().toString(), new Object[0]);
            HttpsURLConnection.setDefaultSSLSocketFactory(new d());
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        if (url.getProtocol().startsWith("https://".substring(0, "https://".indexOf("://")))) {
            return a(httpURLConnection, Build.VERSION.SDK_INT < 21);
        }
        return httpURLConnection;
    }

    private static HostnameVerifier a(final String str) {
        return new HostnameVerifier() { // from class: com.excelliance.lbsdk.main.aa.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str2, SSLSession sSLSession) {
                return str.equals(str2);
            }
        };
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected() && (activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 9);
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    private static byte[] a(byte[] bArr) {
        byte[] bArr2 = {101, 102, 107};
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i % bArr2.length]);
        }
        return bArr3;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0090 A[Catch: all -> 0x00cd, Exception -> 0x00cf, TryCatch #0 {Exception -> 0x00cf, blocks: (B:3:0x000d, B:5:0x0015, B:7:0x001b, B:21:0x0064, B:23:0x0090, B:24:0x009d, B:26:0x00a3, B:27:0x00a7, B:14:0x0042, B:15:0x0049, B:16:0x004a), top: B:46:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:26:0x00a3 A[Catch: all -> 0x00cd, Exception -> 0x00cf, LOOP:0: B:24:0x009d->B:26:0x00a3, LOOP_END, TryCatch #0 {Exception -> 0x00cf, blocks: (B:3:0x000d, B:5:0x0015, B:7:0x001b, B:21:0x0064, B:23:0x0090, B:24:0x009d, B:26:0x00a3, B:27:0x00a7, B:14:0x0042, B:15:0x0049, B:16:0x004a), top: B:46:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00de A[PHI: r5
  0x00de: PHI (r5v3 javax.net.ssl.HttpsURLConnection) = (r5v2 javax.net.ssl.HttpsURLConnection), (r5v9 javax.net.ssl.HttpsURLConnection) binds: [B:38:0x00dc, B:28:0x00c3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:42:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:52:0x00a7 A[EDGE_INSN: B:52:0x00a7->B:27:0x00a7 BREAK  A[LOOP:0: B:24:0x009d->B:26:0x00a3], SYNTHETIC] */
    public static o b(String str, Context context, String str2) throws Throwable {
        URL url;
        byte[] bArr;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        o oVar = new o();
        oVar.a = -1;
        HttpsURLConnection httpsURLConnection = null;
        try {
            try {
                if (m.a(context, "USE_HTTP_OR_HTTPS", 0) == 1) {
                    SSLContext sSLContextB = b(context, str2);
                    if (sSLContextB == null) {
                        throw new RuntimeException("Self Certificate Error");
                    }
                    url = new URL(str);
                    HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) url.openConnection();
                    try {
                        httpsURLConnection2.setSSLSocketFactory(sSLContextB.getSocketFactory());
                        httpsURLConnection2.setHostnameVerifier(a(url.getHost()));
                        httpsURLConnection = httpsURLConnection2;
                        httpsURLConnection.setRequestMethod("GET");
                        httpsURLConnection.setConnectTimeout(60000);
                        httpsURLConnection.setReadTimeout(60000);
                        oVar.a = httpsURLConnection.getResponseCode();
                        ao.c("NetworkUtil", "responseCode=%d, url=%s", Integer.valueOf(oVar.a), url);
                        if (oVar.a == 200) {
                            bArr = new byte[1024];
                            inputStream = httpsURLConnection.getInputStream();
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                i = inputStream.read(bArr);
                                if (i != -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i);
                            }
                            oVar.b = new String(byteArrayOutputStream.toByteArray());
                            ao.c("NetworkUtil", "response=%s", oVar.b);
                            inputStream.close();
                            byteArrayOutputStream.close();
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                    } catch (Exception e) {
                        e = e;
                        httpsURLConnection = httpsURLConnection2;
                        ao.c("NetworkUtil", "url=%s", str);
                        ao.a("NetworkUtil", e);
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        httpsURLConnection = httpsURLConnection2;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } else {
                    url = new URL(str);
                    HttpsURLConnection httpsURLConnection3 = (HttpsURLConnection) url.openConnection();
                    try {
                        if (Build.VERSION.SDK_INT < 21) {
                            httpsURLConnection3.setSSLSocketFactory(new d());
                        }
                        httpsURLConnection = httpsURLConnection3;
                        httpsURLConnection.setRequestMethod("GET");
                        httpsURLConnection.setConnectTimeout(60000);
                        httpsURLConnection.setReadTimeout(60000);
                        oVar.a = httpsURLConnection.getResponseCode();
                        ao.c("NetworkUtil", "responseCode=%d, url=%s", Integer.valueOf(oVar.a), url);
                        if (oVar.a == 200) {
                            bArr = new byte[1024];
                            inputStream = httpsURLConnection.getInputStream();
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                i = inputStream.read(bArr);
                                if (i != -1) {
                                    break;
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i);
                            }
                            oVar.b = new String(byteArrayOutputStream.toByteArray());
                            ao.c("NetworkUtil", "response=%s", oVar.b);
                            inputStream.close();
                            byteArrayOutputStream.close();
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                    } catch (Exception e2) {
                        httpsURLConnection = httpsURLConnection3;
                        e = e2;
                        ao.c("NetworkUtil", "url=%s", str);
                        ao.a("NetworkUtil", e);
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        httpsURLConnection = httpsURLConnection3;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
            return oVar;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00f7 A[PHI: r5
  0x00f7: PHI (r5v3 javax.net.ssl.HttpsURLConnection) = (r5v2 javax.net.ssl.HttpsURLConnection), (r5v5 javax.net.ssl.HttpsURLConnection) binds: [B:30:0x00f5, B:24:0x00e3] A[DONT_GENERATE, DONT_INLINE]] */
    public static o b(String str, Context context, JSONObject jSONObject, String str2) throws Throwable {
        URL url;
        o oVar = new o();
        oVar.a = -1;
        HttpsURLConnection httpsURLConnection = null;
        try {
            try {
                if (m.a(context, "USE_HTTP_OR_HTTPS", 0) == 1) {
                    SSLContext sSLContextB = b(context, str2);
                    if (sSLContextB == null) {
                        throw new RuntimeException("Self Certificate Error");
                    }
                    url = new URL(str);
                    HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) url.openConnection();
                    try {
                        httpsURLConnection2.setSSLSocketFactory(sSLContextB.getSocketFactory());
                        httpsURLConnection2.setHostnameVerifier(a(url.getHost()));
                        httpsURLConnection = httpsURLConnection2;
                    } catch (Exception e) {
                        e = e;
                        httpsURLConnection = httpsURLConnection2;
                        ao.c("NetworkUtil", "url=%s", str);
                        ao.a("NetworkUtil", e);
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        httpsURLConnection = httpsURLConnection2;
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } else {
                    url = new URL(str);
                    httpsURLConnection = (HttpsURLConnection) url.openConnection();
                }
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setConnectTimeout(60000);
                httpsURLConnection.setReadTimeout(60000);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestProperty("Charset", "UTF-8");
                byte[] bytes = jSONObject.toString().getBytes();
                httpsURLConnection.setRequestProperty("Content-Length", String.valueOf(bytes.length));
                httpsURLConnection.setRequestProperty("contentType", "application/json");
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                oVar.a = httpsURLConnection.getResponseCode();
                ao.c("NetworkUtil", "responseCode=%d, url=%s", Integer.valueOf(oVar.a), url);
                if (oVar.a == 200) {
                    byte[] bArr = new byte[1024];
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                    oVar.b = new String(byteArrayOutputStream.toByteArray());
                    ao.c("NetworkUtil", "response=%s", oVar.b);
                    inputStream.close();
                    byteArrayOutputStream.close();
                }
                if (httpsURLConnection != null) {
                    httpsURLConnection.disconnect();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return oVar;
    }

    private static InputStream b(byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }

    public static HashMap<String, Object> b(String str, Context context) {
        HashMap<String, Object> map = new HashMap<>();
        o oVarA = a(str, context);
        map.put("code", Integer.valueOf(oVarA.a));
        map.put("response", oVarA.b);
        return map;
    }

    private static SSLContext b(Context context, String str) {
        SSLContext sSLContext;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream inputStreamB = b(a(a(context.getAssets().open("lebian/crt" + File.separator + str + ".bin"))));
            Certificate certificateGenerateCertificate = certificateFactory.generateCertificate(inputStreamB);
            inputStreamB.close();
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certificateGenerateCertificate);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, null);
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            sSLContext = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
            try {
                sSLContext.init(keyManagers, trustManagers, null);
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
            sSLContext = null;
        }
        return sSLContext;
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            return activeNetworkInfo.getTypeName() + activeNetworkInfo.getSubtypeName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static int d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType();
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }
}
