package com.excelliance.lbsdk.main.bwbx;

import android.content.Context;
import com.excelliance.lbsdk.main.BigFileInfo;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.o.h;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private Context a;
    private com.excelliance.lbsdk.main.ab.a b;
    private com.excelliance.lbsdk.main.ab.d c;
    private int d;

    public f(Context context) {
        this.a = context;
        this.c = new com.excelliance.lbsdk.main.ab.d(context);
    }

    public static int a(HashMap<String, BigFileInfo> map, String str) {
        BigFileInfo bigFileInfo = map.get(a(str));
        if (bigFileInfo == null) {
            return -1;
        }
        return bigFileInfo.getNidx();
    }

    public static String a(String str) {
        return str != null ? str.split(":")[0] : str;
    }

    public static long b(String str) {
        if (str == null) {
            return 0L;
        }
        String[] strArrSplit = str.split(":");
        if (strArrSplit.length > 1) {
            return Integer.parseInt(strArrSplit[1].trim());
        }
        return 0L;
    }

    public int a() {
        return this.d;
    }

    public com.excelliance.lbsdk.main.o.b a(boolean z) {
        if (this.b == null) {
            this.b = (com.excelliance.lbsdk.main.ab.a) LBSdkHelper.application().a("GlobalSettings");
        }
        boolean zA = this.b.a();
        if (z && zA) {
            return new com.excelliance.lbsdk.main.o.e(this.a);
        }
        if (z) {
            return new com.excelliance.lbsdk.main.o.a(this.a);
        }
        if (z || !zA) {
            return ((com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting")).c() ? new com.excelliance.lbsdk.main.o.g(this.a) : new h(this.a);
        }
        return new com.excelliance.lbsdk.main.o.f(this.a);
    }

    public List<String> a(com.excelliance.lbsdk.main.o.b bVar, List<String> list, String str) {
        ArrayList arrayList = new ArrayList((list != null ? list.size() : 10) / 2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String str2 = list.get(i);
            if (!b(bVar, str2, str)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public boolean a(com.excelliance.lbsdk.main.o.b bVar, String str) {
        return bVar.b(str);
    }

    public boolean a(com.excelliance.lbsdk.main.o.b bVar, String str, int i) {
        try {
            this.d++;
            return bVar.a(str, i);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }

    public boolean a(com.excelliance.lbsdk.main.o.b bVar, String str, long j, long j2) {
        try {
            return bVar.c(str, j, j2);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }

    protected boolean a(com.excelliance.lbsdk.main.o.b bVar, String str, String str2) {
        try {
            return bVar.a(str, str2);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }

    public boolean a(com.excelliance.lbsdk.main.o.b bVar, String str, String str2, long j, long j2, int i) {
        try {
            return bVar.a(str, str2, j, j2, i);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }

    public boolean a(com.excelliance.lbsdk.main.o.b bVar, String str, String str2, boolean z) {
        try {
            return bVar.a(str, str2, z);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }

    public List<String> b(com.excelliance.lbsdk.main.o.b bVar, List<String> list, String str) {
        try {
            return bVar.a(list, str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void b(com.excelliance.lbsdk.main.o.b bVar, String str) {
        try {
            bVar.c(str, 0L, 0L);
            bVar.a((String) null, (String) null, false);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
        }
    }

    public boolean b(com.excelliance.lbsdk.main.o.b bVar, String str, int i) {
        try {
            return bVar.b(str, i);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }

    public boolean b(com.excelliance.lbsdk.main.o.b bVar, String str, String str2) {
        try {
            return bVar.b(str, str2);
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.a("OutsideController", e);
            return false;
        }
    }
}
