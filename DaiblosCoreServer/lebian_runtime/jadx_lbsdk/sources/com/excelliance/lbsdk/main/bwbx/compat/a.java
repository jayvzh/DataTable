package com.excelliance.lbsdk.main.bwbx.compat;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ao;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import com.excelliance.lbsdk.main.e;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static com.excelliance.lbsdk.main.m.a d;
    private File f;
    private File g;
    private Context h;
    private RandomAccessFile i;
    private boolean j;
    private String k;
    private b l;
    private Map<String, String> m;
    private boolean n;
    private static final Object a = new Object();
    private static final Map<String, a> b = new Hashtable();
    private static final Map<String, a> c = new Hashtable();
    private static boolean e = false;

    public a(Context context, File file, String str, HashMap<String, HoleFileEntry> map, boolean z) {
        this.j = true;
        this.n = false;
        this.h = context;
        if (str == null) {
            try {
                str = GameUtil.getIntance().getRunningVersionCode();
            } catch (Exception e2) {
                ao.c("AssetCompat", "AssetCompat: error" + e2, new Object[0]);
                return;
            }
        }
        this.k = str;
        this.f = file.getParentFile();
        if (!this.f.exists()) {
            this.f.mkdirs();
        }
        this.g = file;
        if (ao.a()) {
            ao.c("AssetCompat", "AssetCompat: root" + this.f, new Object[0]);
        }
        this.l = new b(context, str, z);
        this.l.inflateAll(map);
    }

    public a(Context context, Map<String, String> map, String str, String str2, boolean z) {
        this.j = true;
        this.n = false;
        this.h = context;
        if (str == null) {
            try {
                str = GameUtil.getIntance().getRunningVersionCode();
            } catch (Exception e2) {
                ao.e("AssetCompat", "AssetCompat: error" + e2, new Object[0]);
                return;
            }
        }
        this.k = str;
        this.f = new File(str2);
        if (!this.f.exists()) {
            this.f.mkdirs();
        }
        if (ao.a()) {
            ao.c("AssetCompat", "AssetCompat: root" + this.f, new Object[0]);
        }
        this.g = new File(this.f, z ? "hole_v4_9.0" : "hole_9.0");
        this.l = new b(context, str, z);
        if (!z) {
            try {
                if (this.g.exists() && map != null) {
                    new ZipFile(this.g).close();
                }
            } catch (Exception e3) {
                this.g.delete();
                this.l.clear();
                ao.e("AssetCompat", "AssetCompat version =" + str + e3, new Object[0]);
            }
        }
        if (this.g.exists()) {
            f();
            this.n = z;
        }
        if (map == null || map.isEmpty()) {
            return;
        }
        this.m = map;
        a(map, str);
    }

    public static HashMap<Long, String> a(String str) {
        return d.a(str);
    }

    public static void a(Context context) {
        e = BwbxUtil.handleAssetmanager(context);
    }

    public static void a(Context context, GameUtil gameUtil) {
        d(context, GameUtil.getIntance().getRunningVersionCode(false, true));
    }

    public static void a(Context context, String str) {
        d(context, str);
    }

    public static void a(Context context, Map<String, String> map, boolean z) {
        if (e) {
            d = new com.excelliance.lbsdk.main.m.a(context, GameUtil.getCachePath(context, GameUtil.getIntance().getRunningVersionCode(false, true)));
            d.a();
        }
    }

    private void a(String str, String str2) {
        if (ao.a(3)) {
            ao.b("AssetCompat", "readDataToPath from version " + this.k + "  name " + str2, new Object[0]);
        }
        File file = new File(str, str2 + ".copy");
        File file2 = new File(str, str2);
        if (file2.exists()) {
            return;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        HoleFileEntry holeFileEntryC = c(str2);
        RandomAccessFile randomAccessFile = this.i;
        try {
            synchronized (a) {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    byte[] bArr = new byte[1024];
                    long size = holeFileEntryC.getSize();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    randomAccessFile.seek(holeFileEntryC.getPosition());
                    while (size > 0) {
                        int i = randomAccessFile.read(bArr);
                        if (i > -1) {
                            long j = i;
                            size -= j;
                            if (size < 0) {
                                i = (int) (j + size);
                            }
                            fileOutputStream.write(bArr, 0, i);
                        }
                    }
                    fileOutputStream.close();
                    file.renameTo(file2);
                    if (ao.a()) {
                        ao.b("AssetCompat", "readDataToPath success=" + file2.exists(), new Object[0]);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception e2) {
            ao.c("AssetCompat", "readDataToPath error " + e2, new Object[0]);
        }
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 28 && e;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x01fb A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x01fd  */
    private boolean a(Map<String, String> map, String str) {
        boolean z;
        InputStream inputStream;
        long j;
        int i;
        ZipFile zipFile;
        a aVar = this;
        File file = aVar.g;
        char c2 = 0;
        if (c()) {
            return false;
        }
        aVar.g.delete();
        aVar.l.clear();
        g();
        RandomAccessFile randomAccessFile = aVar.i;
        ArrayList arrayList = new ArrayList();
        HashMap<String, HoleFileEntry> map2 = new HashMap<>();
        try {
            InputStream inputStreamD = e.d(aVar.h);
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            int i2 = inputStreamD.read(bArr2);
            int i3 = -1;
            if (i2 == -1 || i2 < 4) {
                throw new RuntimeException("bad format lbresinfo");
            }
            byte[] bArr4 = bArr3;
            long j2 = ((bArr2[1] & 255) << 8) | (bArr2[0] & 255) | ((bArr2[2] & 255) << 16) | ((bArr2[3] & 255) << 24);
            long j3 = 4;
            try {
                while (true) {
                    if (j3 != j2) {
                        int i4 = inputStreamD.read(bArr);
                        if (i4 == i3) {
                            break;
                        }
                        j = j3 + ((long) i4);
                        i = (bArr[c2] & 255) | ((bArr[1] & 255) << 8);
                        inputStream = inputStreamD;
                        byte[] bArr5 = new byte[i];
                        byte[] bArr6 = bArr4;
                        j3 = j + ((long) inputStream.read(bArr5)) + ((long) inputStream.read(bArr6));
                        File file2 = file;
                        long j4 = ((((long) bArr6[1]) & 255) << 8) | (((long) bArr6[0]) & 255) | ((((long) bArr6[2]) & 255) << 16) | ((((long) bArr6[3]) & 255) << 24) | ((((long) bArr6[4]) & 255) << 32) | ((((long) bArr6[5]) & 255) << 40) | ((((long) bArr6[6]) & 255) << 48) | ((((long) bArr6[7]) & 255) << 56);
                        randomAccessFile.seek(j4);
                        randomAccessFile.write(bArr5);
                        ArrayList arrayList2 = arrayList;
                        arrayList2.add(Long.valueOf(j4 + ((long) i)));
                        arrayList = arrayList2;
                        bArr4 = bArr6;
                        bArr = bArr;
                        file = file2;
                        c2 = 0;
                        i3 = -1;
                        aVar = this;
                        inputStreamD = inputStream;
                    } else {
                        inputStream = inputStreamD;
                        int i5 = inputStream.read(bArr2);
                        if (i5 == i3) {
                            break;
                        }
                        j = j3 + ((long) i5);
                        i = (bArr2[0] & 255) | ((bArr2[1] & 255) << 8) | ((bArr2[2] & 255) << 16) | ((bArr2[3] & 255) << 24);
                        byte[] bArr7 = new byte[i];
                        byte[] bArr8 = bArr4;
                        j3 = j + ((long) inputStream.read(bArr7)) + ((long) inputStream.read(bArr8));
                        File file3 = file;
                        long j5 = ((((long) bArr8[1]) & 255) << 8) | (((long) bArr8[0]) & 255) | ((((long) bArr8[2]) & 255) << 16) | ((((long) bArr8[3]) & 255) << 24) | ((((long) bArr8[4]) & 255) << 32) | ((((long) bArr8[5]) & 255) << 40) | ((((long) bArr8[6]) & 255) << 48) | ((((long) bArr8[7]) & 255) << 56);
                        randomAccessFile.seek(j5);
                        randomAccessFile.write(bArr7);
                        ArrayList arrayList3 = arrayList;
                        arrayList3.add(Long.valueOf(j5 + ((long) i)));
                        arrayList = arrayList3;
                        bArr4 = bArr8;
                        bArr = bArr;
                        file = file3;
                        c2 = 0;
                        i3 = -1;
                        aVar = this;
                        inputStreamD = inputStream;
                    }
                    if (z) {
                        return true;
                    }
                    throw new RuntimeException("create hole file fail");
                }
                zipFile.close();
                z = true;
            } catch (Exception e2) {
                e = e2;
                z = true;
                ao.c("AssetCompat", "version" + str + " ee: " + e + "", new Object[0]);
            }
            zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            int i6 = 0;
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                String name = zipEntryNextElement.getName();
                if (name.contains("../")) {
                    throw new IOException("entry name contains illegal characters ../");
                }
                long jLongValue = ((Long) arrayList.get(i6)).longValue();
                map2.put(name, new HoleFileEntry(name, jLongValue, zipEntryNextElement.getSize(), map.get(name), false, str));
                ao.c("AssetCompat", "newHoleConfig position:" + jLongValue + " name: " + name, new Object[0]);
                i6++;
                enumerationEntries = enumerationEntries;
                arrayList = arrayList;
            }
            aVar.l.inflateAll(map2);
            if (z) {
                return true;
            }
            throw new RuntimeException("create hole file fail");
        } catch (Exception e3) {
            e = e3;
            z = false;
        }
    }

    private static a b(Context context, String str) {
        a aVar = b.get(str);
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(context, (Map<String, String>) null, str, GameUtil.getCachePath(context, str), false);
        b.put(str, aVar2);
        return aVar2;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT < 30 && e;
    }

    public static boolean b(Context context) {
        e = BwbxUtil.handleAssetmanager(context);
        return Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT < 30 && e;
    }

    private boolean b(String str) {
        if (this.l.containsKey(str)) {
            return this.l.get(str).isInflate();
        }
        return false;
    }

    private HoleFileEntry c(String str) {
        return this.l.get(str);
    }

    private static a c(Context context, String str) {
        a aVar = c.get(str);
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(context, (Map<String, String>) null, str, com.excelliance.lbsdk.main.ae.c.c(context), true);
        c.put(str, aVar2);
        return aVar2;
    }

    private static void d(Context context, String str) {
        String cachePath = GameUtil.getCachePath(context, str);
        File file = new File(cachePath, "hole_9.0");
        if (file.exists()) {
            b(context, str).e();
            file.delete();
        }
        File file2 = new File(cachePath, "hole_v4_9.0");
        if (file2.exists()) {
            c(context, str).e();
            file2.delete();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            SQLiteDatabase.deleteDatabase(context.getDatabasePath("lbcfg"));
        }
    }

    public static String[] d() {
        return d.b();
    }

    private void e() {
        for (String str : this.l.keySet()) {
            if (b(str)) {
                a(this.f.getAbsolutePath(), str);
            }
        }
    }

    private void f() {
        if (this.j || this.i == null) {
            try {
                this.i = new RandomAccessFile(this.g, "rw");
                this.j = false;
            } catch (FileNotFoundException e2) {
                ao.e("AssetCompat", "ensureOpen: " + e2, new Object[0]);
            }
        }
    }

    private void g() {
        try {
            this.i = new RandomAccessFile(this.g, "rw");
            this.j = false;
        } catch (FileNotFoundException e2) {
            ao.c("AssetCompat", "ensureOpen: " + e2, new Object[0]);
        }
    }

    public boolean c() {
        return this.g.exists() && !this.l.isEmpty();
    }
}
