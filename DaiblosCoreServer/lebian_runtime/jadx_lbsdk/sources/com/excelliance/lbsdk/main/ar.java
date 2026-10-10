package com.excelliance.lbsdk.main;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class ar {
    public static List<String> a = new ArrayList();
    public static List<String> b = new ArrayList();
    private static Context c = null;
    private static int d = 0;

    public static int a(Context context, File file, String str, int i, List<String> list) {
        return a(context, file, str, i, true, list);
    }

    public static int a(Context context, File file, String str, int i, boolean z, List<String> list) {
        return a(context, null, file, str, i, z, list);
    }

    public static int a(Context context, String str, File file, String str2, int i) {
        return a(context, str, file, str2, i, true, null);
    }

    public static int a(Context context, String str, File file, String str2, int i, boolean z, List<String> list) throws Exception {
        String str3 = str2;
        com.excelliance.lbsdk.main.g.b.c("ZipUtilsApache", "unzip cover:" + z + " uncoverList:" + list + " target:" + str3, new Object[0]);
        c = context;
        if (!str3.endsWith(File.separator)) {
            str3 = str3 + File.separator;
        }
        String str4 = str3;
        com.excelliance.lbsdk.main.s.al alVar = new com.excelliance.lbsdk.main.s.al(file);
        Enumeration<com.excelliance.lbsdk.main.s.af> enumerationA = alVar.a();
        int i2 = 0;
        while (enumerationA.hasMoreElements()) {
            com.excelliance.lbsdk.main.s.af afVarNextElement = enumerationA.nextElement();
            String name = afVarNextElement.getName();
            if (name.contains("../")) {
                throw new IOException("entry name contains illegal characters ../");
            }
            if (!afVarNextElement.isDirectory()) {
                i2++;
            }
            a.add(name);
        }
        for (int i3 = 1; i3 <= i; i3++) {
            new Thread(new z(i3, i, alVar, str4, z, list), "ZipUtilsApache:unzip").start();
        }
        while (z.a() != i) {
            if (z.a != null) {
                throw z.a;
            }
            SystemClock.sleep(1000L);
            a(str);
        }
        z.a(0);
        alVar.close();
        if (z.a != null) {
            throw z.a;
        }
        com.excelliance.lbsdk.main.g.b.e("ZipUtilsApache", "extract finish", new Object[0]);
        return i2;
    }

    public static List<String> a() {
        return a;
    }

    public static void a(Context context, File file, String str, int i) {
        com.excelliance.lbsdk.main.g.b.c("ZipUtilsApache", "unzipForBigRes target:" + str, new Object[0]);
        c = context;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        com.excelliance.lbsdk.main.s.al alVar = new com.excelliance.lbsdk.main.s.al(file);
        Enumeration<com.excelliance.lbsdk.main.s.af> enumerationA = alVar.a();
        while (enumerationA.hasMoreElements()) {
            com.excelliance.lbsdk.main.s.af afVarNextElement = enumerationA.nextElement();
            if (afVarNextElement.getName().endsWith("lbres.rdata.mp4")) {
                com.excelliance.lbsdk.main.g.b.e("ZipUtilsApache", "extract for big res,size:" + afVarNextElement.getSize(), new Object[0]);
                ae.a(c, "lebian_module_dloadinfo", "bigAssetTwiceMp4Size", afVarNextElement.getSize());
            }
            a.add(afVarNextElement.getName());
        }
        for (int i2 = 1; i2 <= i; i2++) {
            new Thread(new z(i2, i, alVar, str), "ZipUtilsApache:unzip").start();
        }
        while (z.a() != i) {
            SystemClock.sleep(1000L);
            b();
        }
        z.a(0);
        alVar.close();
        com.excelliance.lbsdk.main.g.b.e("ZipUtilsApache", "extract finish", new Object[0]);
    }

    public static void a(Context context, File file, String str, int i, boolean z, long j) {
        c = context;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        com.excelliance.lbsdk.main.s.al alVar = new com.excelliance.lbsdk.main.s.al(file);
        Enumeration<com.excelliance.lbsdk.main.s.af> enumerationA = alVar.a();
        while (enumerationA.hasMoreElements()) {
            com.excelliance.lbsdk.main.s.af afVarNextElement = enumerationA.nextElement();
            String name = afVarNextElement.getName();
            if (name.contains("../")) {
                throw new IOException("entry name contains illegal characters ../");
            }
            if (alVar.b(afVarNextElement) >= j) {
                a.add(name);
                com.excelliance.lbsdk.main.g.b.c("ZipUtilsApache", "unzipForSplitZip add name:" + name, new Object[0]);
            }
        }
        for (int i2 = 1; i2 <= i; i2++) {
            new Thread(new z(i2, i, alVar, str, z, null), "ZipUtilsApache:unzip").start();
        }
        while (z.a() != i) {
            SystemClock.sleep(1000L);
            b();
        }
        z.a(0);
        alVar.close();
        com.excelliance.lbsdk.main.g.b.e("ZipUtilsApache", "extract finish", new Object[0]);
    }

    public static void a(Context context, File file, String str, String str2, int i) {
        com.excelliance.lbsdk.main.g.b.c("ZipUtilsApache", "unzipForBigResAndSingle target:" + str + " tmpDir:" + str2, new Object[0]);
        c = context;
        if (!str2.endsWith(File.separator)) {
            str2 = str2 + File.separator;
        }
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            String name = enumerationEntries.nextElement().getName();
            if (name.contains("../")) {
                throw new IOException("entry name contains illegal characters ../");
            }
            (name.startsWith("assets/lbres/") ? b : a).add(name);
        }
        for (int i2 = 1; i2 <= i; i2++) {
            new Thread(new y(i2, i, zipFile, str, str2), "ZipUtils:unzip").start();
        }
        while (y.a() != i) {
            SystemClock.sleep(1000L);
            b();
        }
        y.a(0);
        zipFile.close();
        com.excelliance.lbsdk.main.g.b.e("ZipUtilsApache", "extract finish", new Object[0]);
    }

    public static void a(com.excelliance.lbsdk.main.s.al alVar, String str, String str2, boolean z, List<String> list) {
        com.excelliance.lbsdk.main.s.af afVarA = alVar.a(str);
        String name = afVarA.getName();
        if (name.contains("../")) {
            throw new IOException("entry name contains illegal characters ../");
        }
        String str3 = str2 + name;
        File file = new File(str3);
        if (afVarA.isDirectory()) {
            file.mkdirs();
            return;
        }
        if (!a(file, z, list)) {
            com.excelliance.lbsdk.main.g.b.c("ZipUtilsApache", "do not need unzip : " + file.getName(), new Object[0]);
            return;
        }
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str3 + ".tmp");
        if (file2.exists()) {
            file2.delete();
        }
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        byte[] bArr = new byte[4096];
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3 + ".tmp"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(alVar.a(afVarA));
        while (true) {
            int i = bufferedInputStream.read(bArr, 0, 4096);
            if (i == -1) {
                break;
            } else {
                bufferedOutputStream.write(bArr, 0, i);
            }
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();
        if (file.exists()) {
            file.delete();
        }
        new File(str3 + ".tmp").renameTo(file);
    }

    private static void a(String str) {
        int iB = (int) ((z.b() / (a.size() + b.size())) * 100.0f);
        if (iB != d) {
            com.excelliance.lbsdk.main.g.b.c("ZipUtilsApache", "extract progress:" + iB, new Object[0]);
            d = iB;
            Intent intent = new Intent(c.getPackageName() + ".action.downloadcomponent.extracted");
            intent.setPackage(c.getPackageName());
            intent.putExtra(BwbxUtil.ACTION_PROGRESS, iB);
            intent.putExtra("gameid", str);
            c.sendBroadcast(intent);
        }
    }

    private static boolean a(File file, boolean z, List<String> list) {
        if (file.exists()) {
            if (!z) {
                return false;
            }
            if (list != null && !list.isEmpty()) {
                return list.contains(file.getPath());
            }
        }
        return true;
    }

    private static void b() {
        a(null);
    }
}
