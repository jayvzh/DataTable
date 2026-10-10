package com.excelliance.lbsdk.main.bwbx;

import android.content.Context;
import com.excelliance.lbsdk.main.BigFileInfo;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.an;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static String a;

    public static String a() {
        return a;
    }

    public static String a(boolean z, Context context) throws IOException {
        if (!z) {
            return context.getPackageCodePath();
        }
        for (String str : an.a().g()) {
            try {
                ZipFile zipFile = new ZipFile(str);
                try {
                    if (zipFile.getEntry("assets/lbres/lbres.rdata.mp4") != null) {
                        zipFile.close();
                        return str;
                    }
                    zipFile.close();
                } catch (Throwable th) {
                    try {
                        zipFile.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<a.C0001a> a(List<a.C0001a> list) {
        if (list != null && list.size() >= 2) {
            Collections.sort(list, new Comparator<a.C0001a>() { // from class: com.excelliance.lbsdk.main.bwbx.b.1
                @Override // java.util.Comparator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(a.C0001a c0001a, a.C0001a c0001a2) {
                    long jA = c0001a.a() - c0001a2.a();
                    if (jA < 0) {
                        return -1;
                    }
                    return jA == 0 ? 0 : 1;
                }
            });
        }
        return list;
    }

    public static void a(Context context, String str) {
        if (str.startsWith("assets/")) {
            str = str.substring(str.indexOf("assets/") + 7);
        }
        File filesDir = context.getFilesDir();
        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filesDir, str));
            fileOutputStream.write(new byte[]{1});
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, HashMap<String, BigFileInfo> map) {
        String cachePath = GameUtil.getCachePath(context);
        for (String str : map.keySet()) {
            if (map.get(str) != null) {
                StringBuffer stringBuffer = new StringBuffer(cachePath);
                stringBuffer.append(str);
                File file = new File(stringBuffer.toString());
                if (!file.exists()) {
                    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                        com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "create dir fail", new Object[0]);
                    }
                    try {
                        if (!file.createNewFile()) {
                            com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "create fail", new Object[0]);
                        }
                    } catch (Exception e) {
                        com.excelliance.lbsdk.main.g.b.a("BigAssetPrepare", e);
                    }
                }
            }
        }
    }

    public static void a(Context context, Map<String, String> map) {
        String cachePath;
        boolean zIsBigAsset = BwbxUtil.isBigAsset(context);
        com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "isBigAssets:" + zIsBigAsset, new Object[0]);
        if (zIsBigAsset) {
            com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "generateNullFilesForBigRes start", new Object[0]);
            boolean zIsEmpty = map.isEmpty();
            if (zIsEmpty) {
                cachePath = BwbxUtil.getRecordPath() + "/";
            } else {
                cachePath = GameUtil.getCachePath(context);
            }
            if (zIsEmpty || !c(context, cachePath)) {
                if (!zIsEmpty) {
                    b(context, cachePath);
                }
                a(a(!zIsEmpty, context), cachePath);
            }
            com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "generateNullFilesForBigRes end", new Object[0]);
        }
    }

    private static void a(String str, String str2) {
        if (new File(str2 + "assets/lbres/lbapk.png").exists()) {
            return;
        }
        GameUtil.getIntance().extractFrame(str, str2, "assets/lbres/", "lbres.rdata.mp4", false);
        try {
            File file = new File(str2 + "assets/lbres/lbres.rdata.mp4");
            if (file.exists()) {
                return;
            }
            file.createNewFile();
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "create mp4 failed", e, new Object[0]);
        }
    }

    /* JADX WARN: Code duplicated, block: B:65:0x016b  */
    private static void b(Context context, String str) throws Throwable {
        long size;
        File file = new File(str, "assets/lbres/lbres.rdata.mp4");
        File file2 = new File(str, "assets/lbres/lbres.rdata.mp4.idx");
        File file3 = new File(str + "assets/lbres/lbapk.png");
        boolean z = true;
        if (ae.b(context, "lebian_module_dloadinfo", "success", false)) {
            com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "verifyMp4: mp4 len:" + file.length() + " mp4Idx:" + file2.exists(), new Object[0]);
            if (!file3.exists() || (file.exists() && file.length() != 0 && file2.exists())) {
                z = false;
            }
        } else if (file3.exists()) {
            try {
                try {
                    ZipFile zipFile = new ZipFile(context.getPackageResourcePath());
                    try {
                        ZipEntry entry = zipFile.getEntry("assets/lbres/lbres.rdata.mp4.idx");
                        if (entry != null) {
                            size = entry.getSize();
                            try {
                                com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "idxInApkLen: " + size, new Object[0]);
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    zipFile.close();
                                } catch (Throwable unused) {
                                }
                                throw th;
                            }
                        } else {
                            size = 0;
                        }
                        zipFile.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e) {
                    e = e;
                    size = 0;
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
            }
            if (file2.exists() && file.exists() && (file.length() != 0 || file2.length() <= size)) {
                try {
                    ZipFile zipFile2 = new ZipFile(context.getPackageResourcePath());
                    ZipEntry entry2 = zipFile2.getEntry("assets/lbres/lbres.rdata.mp4.idx.left");
                    com.excelliance.lbsdk.main.g.b.c("BigAssetPrepare", "verifyMp4: idx left size:" + entry2.getSize() + " idx size:" + file2.length(), new Object[0]);
                    if (entry2 != null && ae.b(context, "lebian_module_dloadinfo", "success", false) && file2.length() != entry2.getSize()) {
                        byte[] bArr = new byte[1024];
                        if (file2.exists()) {
                            file2.delete();
                        }
                        if (!file2.exists()) {
                            file2.getParentFile().mkdirs();
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile2.getInputStream(entry2));
                            while (true) {
                                int i = bufferedInputStream.read(bArr, 0, 1024);
                                if (i == -1) {
                                    break;
                                } else {
                                    bufferedOutputStream.write(bArr, 0, i);
                                }
                            }
                            bufferedInputStream.close();
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        }
                    }
                    zipFile2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                z = false;
            }
        } else {
            z = false;
        }
        if (z) {
            GameUtil.deleteDir(file.getParentFile());
            ae.a(context, "lebian_module_dloadinfo", "success");
            GameUtil.cleanTwiceLoadFlag(context);
        }
    }

    private static boolean c(Context context, String str) {
        boolean z;
        try {
            z = context.getAssets().list("lbres").length < 3;
        } catch (IOException unused) {
        }
        if (z) {
            String str2 = GameUtil.getObbPath(context) + "/main." + GameUtil.getIntance().getVerCodeFromApk(context.getPackageCodePath(), true) + "." + context.getPackageName() + ".obb";
            if (new File(str2).exists()) {
                a(str2, str);
            }
            a = str2;
        }
        return z;
    }
}
