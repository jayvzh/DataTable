package com.excelliance.lbsdk.main.ac;

import android.content.Context;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.f.b;
import com.excelliance.lbsdk.main.f.c;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private Context a;
    private File b;
    private List<String> c;

    public a(Context context, File file) {
        this.a = context;
        this.b = file;
    }

    private static String a(File file, File file2) {
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        if (absolutePath2.startsWith(absolutePath) && absolutePath2.length() > absolutePath.length()) {
            return absolutePath2.substring(absolutePath.length() + 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(File file) {
        String strA = a(this.b, file);
        if (strA == null) {
            return false;
        }
        return this.c.contains(strA);
    }

    public int a() {
        File file = this.b;
        if (file == null) {
            File file2 = new File(BwbxUtil.getCurrCachePath());
            this.b = file2;
            file = file2;
        }
        this.c = BwbxUtil.getFileSet(this.a);
        b bVar = new b(file, new b.a() { // from class: com.excelliance.lbsdk.main.ac.a.1
            @Override // com.excelliance.lbsdk.main.f.b.a
            public boolean a(File file3) {
                return a.this.a(file3);
            }
        });
        bVar.a(new c(file, 1));
        return bVar.a();
    }

    public long b() {
        File file = this.b;
        if (file == null) {
            File file2 = new File(BwbxUtil.getCurrCachePath());
            this.b = file2;
            file = file2;
        }
        this.c = BwbxUtil.getFileSet(this.a);
        com.excelliance.lbsdk.main.f.a aVar = new com.excelliance.lbsdk.main.f.a(file, new b.a() { // from class: com.excelliance.lbsdk.main.ac.a.2
            @Override // com.excelliance.lbsdk.main.f.b.a
            public boolean a(File file3) {
                return a.this.a(file3);
            }
        });
        aVar.a(new c(file, 1));
        return aVar.a();
    }
}
