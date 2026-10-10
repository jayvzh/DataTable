package com.excelliance.lbsdk.main.ab;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private Context a;
    private HashMap<String, Long> b;
    private long c;
    private com.excelliance.lbsdk.main.af.b d;

    public e(Context context, com.excelliance.lbsdk.main.af.b bVar) {
        this.a = context;
        this.d = bVar;
    }

    private long a(HashMap<String, Long> map) {
        Iterator<String> it = map.keySet().iterator();
        long jLongValue = 0;
        while (it.hasNext()) {
            Long l = map.get(it.next());
            jLongValue += l != null ? l.longValue() : 0L;
        }
        return jLongValue;
    }

    private HashMap<String, Long> d() {
        if (this.b == null) {
            this.b = e();
            this.c = a(this.b);
        }
        return this.b;
    }

    private HashMap<String, Long> e() {
        HashMap<String, Long> map = new HashMap<>();
        try {
            InputStream inputStreamOpen = this.a.getAssets().open("zmunity.small");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String strTrim = line.trim();
                if (strTrim.length() > 0) {
                    map.put(strTrim, Long.valueOf(this.d.a(this.a, strTrim)));
                }
            }
            inputStreamReader.close();
            inputStreamOpen.close();
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.e("UnitySmall", null, e, new Object[0]);
        }
        return map;
    }

    public long a() {
        d();
        return this.c;
    }

    public boolean a(String str) {
        return d().containsKey(str);
    }

    public int b() {
        return d().size();
    }

    public int c() {
        d();
        return 0;
    }
}
