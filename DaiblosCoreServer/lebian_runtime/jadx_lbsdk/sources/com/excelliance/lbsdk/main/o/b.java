package com.excelliance.lbsdk.main.o;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public long a(String str) {
        return 0L;
    }

    public long a(String str, long j) {
        return j;
    }

    public long a(String str, long j, long j2) {
        return j2;
    }

    public List<String> a(List<String> list, String str) {
        return list;
    }

    public void a(String str, InputStream inputStream) throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public boolean a(String str, int i) {
        return true;
    }

    public boolean a(String str, String str2) {
        return true;
    }

    public boolean a(String str, String str2, long j, long j2, int i) {
        return true;
    }

    public boolean a(String str, String str2, boolean z) {
        return true;
    }

    public InputStream b(String str, long j, long j2) {
        return null;
    }

    public boolean b(String str) {
        return true;
    }

    public boolean b(String str, int i) {
        return true;
    }

    public boolean b(String str, String str2) {
        return false;
    }

    public boolean c(String str, long j, long j2) {
        return true;
    }
}
