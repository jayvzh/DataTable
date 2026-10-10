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
public class aq {
    public static List<String> a = new ArrayList();
    public static List<String> b = new ArrayList();
    private static Context c = null;
    private static int d = 0;

    public static int a(Context context, String str, File file, String str2, int i) {
        return a(context, str, file, str2, i, true, null);
    }

    private static int a(Context context, String str, File file, String str2, int i, boolean z, List<String> list) throws Exception {
        String str3 = str2;
        ao.c("ZipUtils", "unzip cover:" + z + " uncoverList:" + list + " target:" + str3, new Object[0]);
        c = context;
        if (!str3.endsWith(File.separator)) {
            str3 = str3 + File.separator;
        }
        String str4 = str3;
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        int i2 = 0;
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            if (name.contains("../")) {
                throw new IOException("entry name contains illegal characters ../");
            }
            if (!zipEntryNextElement.isDirectory()) {
                i2++;
            }
            a.add(name);
        }
        for (int i3 = 1; i3 <= i; i3++) {
            new Thread(new y(i3, i, zipFile, str4, z, list), "ZipUtils:unzip").start();
        }
        while (y.a() != i) {
            if (y.a != null) {
                throw y.a;
            }
            SystemClock.sleep(1000L);
            a(str);
        }
        y.a(0);
        zipFile.close();
        if (y.a != null) {
            throw y.a;
        }
        ao.e("ZipUtils", "extract finish", new Object[0]);
        return i2;
    }

    public static List<String> a() {
        return a;
    }

    public static void a(Context context, File file, String str, int i) throws IOException {
        ao.c("ZipUtils", "unzipForBigRes target:" + str, new Object[0]);
        c = context;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (zipEntryNextElement.getName().endsWith("lbres.rdata.mp4")) {
                ao.e("ZipUtils", "extract for big res,size:" + zipEntryNextElement.getSize(), new Object[0]);
                ae.a(c, "lebian_module_dloadinfo", "bigAssetTwiceMp4Size", zipEntryNextElement.getSize());
            }
            a.add(zipEntryNextElement.getName());
        }
        for (int i2 = 1; i2 <= i; i2++) {
            new Thread(new y(i2, i, zipFile, str), "ZipUtils:unzip").start();
        }
        while (y.a() != i) {
            SystemClock.sleep(1000L);
            c();
        }
        y.a(0);
        zipFile.close();
        ao.e("ZipUtils", "extract finish", new Object[0]);
    }

    public static void a(Context context, File file, String str, String str2, int i) throws IOException {
        ao.c("ZipUtils", "unzipForBigResAndSingle target:" + str + " tmpDir:" + str2, new Object[0]);
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
            c();
        }
        y.a(0);
        zipFile.close();
        ao.e("ZipUtils", "extract finish", new Object[0]);
    }

    private static void a(String str) {
        int iB = (int) ((y.b() / (a.size() + b.size())) * 100.0f);
        if (iB != d) {
            ao.c("ZipUtils", "extract progress:" + iB, new Object[0]);
            d = iB;
            Intent intent = new Intent(c.getPackageName() + ".action.downloadcomponent.extracted");
            intent.setPackage(c.getPackageName());
            intent.putExtra(BwbxUtil.ACTION_PROGRESS, iB);
            intent.putExtra("gameid", str);
            c.sendBroadcast(intent);
        }
    }

    public static void a(ZipFile zipFile, String str, String str2, boolean z, List<String> list) {
        ZipEntry entry = zipFile.getEntry(str);
        String name = entry.getName();
        if (name.contains("../")) {
            throw new IOException("entry name contains illegal characters ../");
        }
        String str3 = str2 + name;
        File file = new File(str3);
        if (entry.isDirectory()) {
            file.mkdir();
            return;
        }
        if (!a(file, z, list)) {
            ao.c("ZipUtils", "do not need unzip it : " + file.getName(), new Object[0]);
            return;
        }
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(str3 + ".tmp");
        if (file2.exists()) {
            file2.delete();
        }
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        byte[] bArr = new byte[4096];
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3 + ".tmp"));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(entry));
        while (true) {
            int i = bufferedInputStream.read(bArr, 0, 4096);
            if (i == -1) {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
                file.delete();
                new File(str3 + ".tmp").renameTo(file);
                return;
            }
            bufferedOutputStream.write(bArr, 0, i);
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

    public static List<String> b() {
        return b;
    }

    private static void c() {
        a(null);
    }
}
