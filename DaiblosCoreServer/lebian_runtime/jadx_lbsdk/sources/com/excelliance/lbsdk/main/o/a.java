package com.excelliance.lbsdk.main.o;

import android.content.Context;
import com.excelliance.lbsdk.main.BigFileInfo;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.bwbx.AssetRedirector;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends b {
    private Context a;
    private long[] b;
    private int c;
    private final boolean d = ((com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting")).c();
    private h e;
    private i f;
    private long g;

    public a(Context context) {
        this.a = context;
    }

    public static List<String> a(List<String> list, List<com.excelliance.lbsdk.main.bwbx.a.C0001a> list2) {
        HashMap<String, BigFileInfo> bigFileList = BwbxUtil.getBigFileList();
        ArrayList arrayList = new ArrayList();
        int size = list != null ? list.size() : 0;
        for (int i = 0; i < size; i++) {
            String str = list.get(i);
            if (com.excelliance.lbsdk.main.p.a.a(list2, com.excelliance.lbsdk.main.bwbx.f.a(bigFileList, str), (int) com.excelliance.lbsdk.main.bwbx.f.b(str)) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private boolean a() {
        return (this.e == null && this.f == null) ? false : true;
    }

    private void b() {
        int i = this.c;
        if (this.d) {
            this.f = i.a(this.a, 1, i);
        } else {
            this.e = new h(this.a);
        }
        if (((com.excelliance.lbsdk.main.l.e) LBSdkHelper.application().a("Runtime")).d()) {
            this.g = i.a(i.c(i));
        }
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str) {
        if (this.b == null) {
            this.b = new long[3];
        }
        this.b[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.b);
        return this.b[1];
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j) {
        return j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public long a(String str, long j, long j2) {
        if (this.b == null) {
            this.b = new long[3];
        }
        this.b[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.b);
        return this.b[1] - j;
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public List<String> a(List<String> list, String str) {
        return a(list, com.excelliance.lbsdk.main.p.a.a(this.a).c());
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, int i) {
        this.c = i;
        if (!a()) {
            b();
        }
        b bVar = this.e;
        if (bVar == null) {
            bVar = this.f;
        }
        return bVar.a(str, i);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean a(String str, String str2) {
        if (this.b == null) {
            this.b = new long[3];
        }
        this.b[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        return AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.b);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public InputStream b(String str, long j, long j2) {
        if (this.b == null) {
            this.b = new long[3];
        }
        this.b[2] = com.excelliance.lbsdk.main.bwbx.f.b(str);
        AssetRedirector.nextIncompeleteDataRegion(com.excelliance.lbsdk.main.bwbx.f.a(str), this.b);
        long j3 = j + this.b[0];
        if (!this.d) {
            return this.e.b(i.a, j3, j2);
        }
        return this.f.b(i.b, j3 - this.g, j2);
    }

    @Override // com.excelliance.lbsdk.main.o.b
    public boolean b(String str, int i) {
        this.c = i;
        if (!a()) {
            b();
        }
        b bVar = this.e;
        if (bVar == null) {
            bVar = this.f;
        }
        return bVar.b(str, i);
    }
}
