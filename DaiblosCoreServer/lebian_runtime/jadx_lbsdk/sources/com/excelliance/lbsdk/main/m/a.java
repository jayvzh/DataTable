package com.excelliance.lbsdk.main.m;

import android.content.Context;
import android.content.res.AssetManager;
import com.excelliance.lbsdk.main.ao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;
    private String b;
    private Map<String, b> c;

    public a(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    private HashMap<String, b> a(List<String> list) {
        Context context = this.a;
        int size = list != null ? list.size() : 0;
        HashMap<String, b> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            b bVar = new b(context, this.b + str, str);
            bVar.a(this.a);
            map.put(str, bVar);
        }
        return map;
    }

    private static List<String> a(AssetManager assetManager) {
        String[] list;
        ArrayList arrayList = new ArrayList();
        try {
            String[] list2 = assetManager.list("lebian");
            int length = list2 != null ? list2.length : 0;
            for (int i = 0; i < length; i++) {
                if (list2[i].startsWith("lbresinfo")) {
                    arrayList.add("lebian" + File.separator + list2[i]);
                }
            }
            if (arrayList.isEmpty() && (list = assetManager.list("lbresinfo")) != null && list.length > 0) {
                arrayList.add("lbresinfo");
            }
        } catch (IOException e) {
            ao.e("AssetHole", "", e, new Object[0]);
        }
        return arrayList;
    }

    private String b(String str) {
        return str.substring(this.b.length());
    }

    public HashMap<Long, String> a(String str) {
        return this.c.get(b(str)).a();
    }

    public void a() {
        Context context = this.a;
        if (this.c != null) {
            return;
        }
        this.c = a(a(context.getAssets()));
    }

    public String[] b() {
        if (this.c == null) {
            a();
        }
        Set<String> setKeySet = this.c.keySet();
        int i = 0;
        String[] strArr = new String[setKeySet.size()];
        Iterator<String> it = setKeySet.iterator();
        while (it.hasNext()) {
            strArr[i] = this.b + it.next();
            i++;
        }
        return strArr;
    }
}
