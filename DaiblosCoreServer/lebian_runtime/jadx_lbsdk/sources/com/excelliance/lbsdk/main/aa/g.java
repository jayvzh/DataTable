package com.excelliance.lbsdk.main.aa;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private static String a() {
        return a(22);
    }

    private static String a(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code duplicated, block: B:56:0x01a1 A[Catch: IOException -> 0x01a5, TRY_ENTER, TryCatch #4 {IOException -> 0x01a5, blocks: (B:32:0x0176, B:33:0x0179, B:56:0x01a1, B:60:0x01a9, B:62:0x01ae), top: B:83:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x01a9 A[Catch: IOException -> 0x01a5, TryCatch #4 {IOException -> 0x01a5, blocks: (B:32:0x0176, B:33:0x0179, B:56:0x01a1, B:60:0x01a9, B:62:0x01ae), top: B:83:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x01ae A[Catch: IOException -> 0x01a5, TRY_LEAVE, TryCatch #4 {IOException -> 0x01a5, blocks: (B:32:0x0176, B:33:0x0179, B:56:0x01a1, B:60:0x01a9, B:62:0x01ae), top: B:83:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x01c2 A[Catch: IOException -> 0x01be, TryCatch #10 {IOException -> 0x01be, blocks: (B:69:0x01ba, B:73:0x01c2, B:75:0x01c7), top: B:87:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:75:0x01c7 A[Catch: IOException -> 0x01be, TRY_LEAVE, TryCatch #10 {IOException -> 0x01be, blocks: (B:69:0x01ba, B:73:0x01c2, B:75:0x01c7), top: B:87:0x01ba }] */
    /* JADX WARN: Code duplicated, block: B:87:0x01ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:99:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    public static String a(String str, Map<String, String> map, String str2, String str3, File file) throws Throwable {
        ?? r6;
        DataOutputStream dataOutputStream;
        HttpURLConnection httpURLConnection;
        Throwable th;
        ?? fileInputStream;
        String strA = a();
        try {
            try {
                try {
                    httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    try {
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                        httpURLConnection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
                        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + strA);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        try {
                            for (String str4 : map.keySet()) {
                                String str5 = map.get(str4);
                                if (str5 == null) {
                                    str5 = "";
                                }
                                dataOutputStream.writeBytes("--" + strA + "\r\nContent-Disposition: form-data; name=\"" + str4 + "\"\r\nContent-Type: text/plain; charset=utf-8\r\n\r\n");
                                dataOutputStream.write(str5.getBytes());
                                StringBuilder sb = new StringBuilder();
                                sb.append("\r\n");
                                sb.append("--");
                                sb.append(strA);
                                sb.append("\r\n");
                                dataOutputStream.writeBytes(sb.toString());
                            }
                            dataOutputStream.writeBytes("--" + strA + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + file.getName() + "\"\r\nContent-Type:" + str3 + "\r\nContent-Transfer-Encoding: binary\r\n\r\n");
                            byte[] bArr = new byte[1024];
                            fileInputStream = new FileInputStream(file);
                            while (true) {
                                try {
                                    int i = fileInputStream.read(bArr);
                                    if (i == -1) {
                                        break;
                                    }
                                    dataOutputStream.write(bArr, 0, i);
                                } catch (IOException e) {
                                    e = e;
                                    e.printStackTrace();
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    if (dataOutputStream != null) {
                                        dataOutputStream.close();
                                    }
                                    if (fileInputStream != 0) {
                                        fileInputStream.close();
                                    }
                                }
                            }
                            dataOutputStream.writeBytes("\r\n");
                            dataOutputStream.writeBytes("--" + strA + "--\r\n");
                            dataOutputStream.flush();
                            int responseCode = httpURLConnection.getResponseCode();
                            StringBuilder sb2 = new StringBuilder();
                            if (responseCode == 200) {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    sb2.append(line);
                                }
                                String string = sb2.toString();
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                dataOutputStream.close();
                                fileInputStream.close();
                                return string;
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            dataOutputStream.close();
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e = e3;
                            fileInputStream = 0;
                        } catch (Throwable th2) {
                            th = th2;
                            r6 = 0;
                            th = th;
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    throw th;
                                }
                            }
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (r6 != 0) {
                                throw th;
                            }
                            r6.close();
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        dataOutputStream = null;
                        fileInputStream = dataOutputStream;
                        e.printStackTrace();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (fileInputStream != 0) {
                            fileInputStream.close();
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        dataOutputStream = null;
                        r6 = dataOutputStream;
                        th = th;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (r6 != 0) {
                            throw th;
                        }
                        r6.close();
                        throw th;
                    }
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            } catch (IOException e7) {
                e = e7;
                httpURLConnection = null;
                dataOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = null;
                dataOutputStream = null;
            }
            return null;
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
