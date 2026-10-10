package com.excelliance.lbsdk.main.bwbx.compat;

/* JADX INFO: loaded from: classes.dex */
public class HoleFileEntry {
    private String a;
    private String b;
    private long c;
    private long d;
    private String e;
    private boolean f;
    private String g;

    public HoleFileEntry(String str, long j, long j2, String str2, boolean z, String str3) {
        this.a = str + str3;
        this.b = str;
        this.c = j;
        this.f = z;
        this.d = j2;
        this.e = str2;
        this.g = str3;
    }

    public String getId() {
        return this.a;
    }

    public String getMd5() {
        return this.e;
    }

    public String getName() {
        return this.b;
    }

    public long getPosition() {
        return this.c;
    }

    public long getSize() {
        return this.d;
    }

    public boolean isInflate() {
        com.excelliance.lbsdk.main.g.b.c("AssetCompat", "is inlfate:" + this.f, new Object[0]);
        return this.f;
    }

    public void setInflate(boolean z) {
        this.f = z;
    }

    public String toString() {
        return "HoleFileEntry{id='" + this.a + "', name='" + this.b + "', position=" + this.c + ", size=" + this.d + ", md5='" + this.e + "', inflate=" + this.f + ", version='" + this.g + "'}";
    }

    public boolean verifyMD5(String str) {
        return str != null && str.equals(this.e);
    }
}
