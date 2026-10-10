package com.excelliance.lbsdk.main.ab;

import android.content.Context;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.b.f;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class d extends f {
    private Context a;
    private HashMap<String, Object> b;

    public d() {
    }

    public d(Context context) {
        this.a = context;
    }

    private static long a(Object obj) {
        if (obj == null) {
            return -1L;
        }
        return ((Long) obj).longValue();
    }

    private long a(String str) {
        if (this.b == null) {
            synchronized (this) {
                a(this.a);
            }
        }
        return a(this.b.get(str));
    }

    private void a(Context context) {
        InputStream inputStream;
        String strE = an.a().e();
        this.b = new HashMap<>();
        ZipFile zipFile = null;
        try {
            if (strE == null) {
                inputStream = com.excelliance.lbsdk.main.e.c(context);
            } else {
                zipFile = new ZipFile(strE);
                inputStream = zipFile.getInputStream(zipFile.getEntry("assets/tryflag"));
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            this.b.put("key_whole_dir_path", bufferedReader.readLine());
            this.b.put("key_small_dir_path", bufferedReader.readLine());
            bufferedReader.readLine();
            b(bufferedReader.readLine());
            inputStream.close();
            if (zipFile != null) {
                zipFile.close();
            }
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.c("TryFlag", "no tryflag1", e, new Object[0]);
        }
    }

    private void b(String str) {
        if (str == null || str.length() < 1) {
            return;
        }
        String[] strArrSplit = str.split("\\s");
        this.b.put("lbres.zip.cd.offset", Long.valueOf(c(strArrSplit[0])));
        if (strArrSplit.length > 1) {
            this.b.put("twiceload.zip.cd.offset", Long.valueOf(c(strArrSplit[1])));
        }
    }

    private static long c(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    private Object d(String str) {
        HashMap<String, Object> map;
        HashMap<String, Object> map2 = this.b;
        if (map2 != null) {
            return map2.get(str);
        }
        synchronized (this) {
            map = this.b;
            if (map == null) {
                a(this.a);
                map = this.b;
            }
        }
        return map.get(str);
    }

    public long a() {
        return a("lbres.zip.cd.offset");
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void attach(com.excelliance.lbsdk.main.b.c cVar) {
        super.attach(cVar);
        this.a = getContext();
    }

    public long b() {
        return a("twiceload.zip.cd.offset");
    }

    public String c() {
        return (String) d("key_small_dir_path");
    }
}
