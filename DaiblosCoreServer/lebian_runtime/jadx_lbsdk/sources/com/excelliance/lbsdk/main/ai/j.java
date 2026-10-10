package com.excelliance.lbsdk.main.ai;

import com.excelliance.lbsdk.main.s.af;
import com.excelliance.lbsdk.main.s.al;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class j {
    private ZipFile a;
    private al b;

    public j(String str, long j) {
        com.excelliance.lbsdk.main.g.b.c("ZipFileX", "ZipFileX: " + str + " size:" + j, new Object[0]);
        if (j > 2097152) {
            this.b = new al(str);
        } else {
            this.a = new ZipFile(str);
        }
    }

    public InputStream a(ZipEntry zipEntry) {
        ZipFile zipFile = this.a;
        if (zipFile != null) {
            return zipFile.getInputStream(zipEntry);
        }
        al alVar = this.b;
        if (alVar != null) {
            return alVar.a((af) zipEntry);
        }
        return null;
    }

    public ZipEntry a(String str) {
        ZipFile zipFile = this.a;
        if (zipFile != null) {
            return zipFile.getEntry(str);
        }
        al alVar = this.b;
        if (alVar != null) {
            return alVar.a(str);
        }
        return null;
    }

    public void a() throws IOException {
        ZipFile zipFile = this.a;
        if (zipFile != null) {
            zipFile.close();
        }
        al alVar = this.b;
        if (alVar != null) {
            alVar.close();
        }
    }

    public InputStream b(String str) {
        ZipFile zipFile = this.a;
        if (zipFile != null) {
            return zipFile.getInputStream(zipFile.getEntry(str));
        }
        al alVar = this.b;
        if (alVar != null) {
            return alVar.a(alVar.a(str));
        }
        return null;
    }
}
