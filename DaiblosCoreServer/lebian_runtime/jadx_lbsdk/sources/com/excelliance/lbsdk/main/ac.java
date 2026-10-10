package com.excelliance.lbsdk.main;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Xml;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ac {
    private static ac d;
    private Context a;
    private an b = an.a();
    private GameUtil c;

    private ac(Context context) {
        this.a = context;
        this.b.a(this.a);
        this.c = GameUtil.getIntance();
    }

    public static synchronized ac a(Context context) {
        if (d == null) {
            d = new ac(context);
        }
        return d;
    }

    private byte[] a(byte[] bArr, int i) {
        boolean z;
        byte[] bArr2 = {80, 75, 5, 6};
        int iMin = Math.min(bArr.length, i);
        for (int i2 = iMin - 22; i2 >= 0; i2--) {
            int i3 = 0;
            while (true) {
                if (i3 >= bArr2.length) {
                    z = true;
                    break;
                }
                if (bArr[i2 + i3] != bArr2[i3]) {
                    z = false;
                    break;
                }
                i3++;
            }
            if (z) {
                int i4 = (bArr[i2 + 20] & 255) + ((bArr[i2 + 21] << 8) & 65535);
                int i5 = (iMin - i2) - 22;
                com.excelliance.lbsdk.main.g.b.c("OverlayManager", "ZIP comment found at buffer position " + (i2 + 22) + " with len=" + i4, new Object[0]);
                if (i4 != i5) {
                    com.excelliance.lbsdk.main.g.b.c("OverlayManager", "ZIP comment size mismatch: directory says len is " + i4 + ", but file ends after " + i5, new Object[0]);
                }
                byte[] bArr3 = new byte[i4];
                int i6 = iMin - i4;
                for (int i7 = 0; i6 < iMin && i7 < i4; i7++) {
                    bArr3[i7] = bArr[i6];
                    i6++;
                }
                return bArr3;
            }
        }
        return null;
    }

    private synchronized void b(ab abVar) {
        if (abVar != null) {
            try {
                if (abVar.overlayList != null) {
                    String str = this.a.getApplicationInfo().sourceDir;
                    String str2 = this.b.d(1048576L) + "overlay/";
                    long jLastModified = new File(this.a.getApplicationContext().getPackageResourcePath()).lastModified();
                    File file = new File(str2);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    ZipFile zipFile = new ZipFile(str);
                    byte[] bArr = new byte[1024];
                    String str3 = str2 + "overlay_res.zip";
                    new File(str3).delete();
                    ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File(str3)), 1024));
                    int i = 0;
                    for (String str4 : abVar.overlayList) {
                        if (str4.endsWith("/")) {
                            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                            while (enumerationEntries.hasMoreElements()) {
                                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                                String name = zipEntryNextElement.getName();
                                if (name.contains("../")) {
                                    throw new IOException("entry name contains illegal characters ../");
                                }
                                if (!zipEntryNextElement.isDirectory() && name.startsWith(str4)) {
                                    zipOutputStream.putNextEntry(new ZipEntry(name));
                                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                                    while (true) {
                                        int i2 = bufferedInputStream.read(bArr);
                                        if (i2 == -1) {
                                            break;
                                        } else {
                                            zipOutputStream.write(bArr, 0, i2);
                                        }
                                    }
                                    i++;
                                    bufferedInputStream.close();
                                    zipOutputStream.flush();
                                    zipOutputStream.closeEntry();
                                }
                            }
                        } else {
                            ZipEntry entry = zipFile.getEntry(str4);
                            if (entry != null) {
                                zipOutputStream.putNextEntry(new ZipEntry(str4));
                                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(entry));
                                while (true) {
                                    int i3 = bufferedInputStream2.read(bArr);
                                    if (i3 == -1) {
                                        break;
                                    } else {
                                        zipOutputStream.write(bArr, 0, i3);
                                    }
                                }
                                i++;
                                bufferedInputStream2.close();
                                zipOutputStream.flush();
                                zipOutputStream.closeEntry();
                            }
                        }
                    }
                    zipFile.close();
                    com.excelliance.lbsdk.main.g.b.c("OverlayManager", "finish assets unzip", new Object[0]);
                    File file2 = new File(str2, "AndroidManifest.xml");
                    String str5 = "<?xml version=\"1.0\" encoding=\"utf-8\"?><lastTime>" + jLastModified + "</lastTime>";
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    fileOutputStream.write(str5.getBytes());
                    fileOutputStream.close();
                    GameUtil gameUtil = this.c;
                    GameUtil.a(file2, zipOutputStream, "");
                    byte[] bArr2 = {2, 0, 12, 0, 40, 0, 0, 0, 0, 0, 0, 0, 1, 0, 28, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    File file3 = new File(str2, "resources.arsc");
                    if (!file3.exists()) {
                        file3.createNewFile();
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                    fileOutputStream2.write(bArr2, 0, bArr2.length);
                    fileOutputStream2.close();
                    GameUtil gameUtil2 = this.c;
                    GameUtil.a(file3, zipOutputStream, "");
                    zipOutputStream.close();
                    if (i > 0) {
                        ae.a(this.a, "lebian_module_overlayinfo", "overlay_res", str3);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String a() {
        String strB = ae.b(this.a, "lebian_module_overlayinfo", "id", "-1");
        String strB2 = ae.b(this.a, "lebian_module_overlayinfo", "overlay_res", (String) null);
        if (strB2 == null || new File(strB2).exists()) {
            return strB;
        }
        b();
        return "-1";
    }

    protected synchronized void a(ab abVar) {
        if (abVar != null) {
            try {
                if (!a().equals(abVar.id)) {
                    b();
                    ae.a(this.a, "lebian_module_overlayinfo", "overlay_md", abVar.overlayMetaData);
                    ae.a(this.a, "lebian_module_overlayinfo", "overlay_md_s", abVar.overlayMetaDatas);
                    ae.a(this.a, "lebian_module_overlayinfo", "overlay_mi", abVar.overlayMetaInf);
                    ae.a(this.a, "lebian_module_overlayinfo", "overlay_sign", abVar.overlaySign);
                    ae.a(this.a, "lebian_module_overlayinfo", "overlay_v2_channel", abVar.overlaySignV2Channel);
                    ae.a(this.a, "lebian_module_overlayinfo", "id", abVar.id);
                    b(abVar);
                    c();
                    String str = this.a.getApplicationInfo().sourceDir;
                    String str2 = null;
                    l lVarA = this.b.a(ae.b(this.a, "lebian_module_extractinfo", "next", (String) null));
                    l lVarD = this.b.d();
                    if (lVarA != null && new File(lVarA.savePath).exists() && !lVarA.isInstall) {
                        str2 = lVarA.savePath;
                    } else if (lVarD != null && !lVarD.isInstall) {
                        str2 = lVarD.savePath;
                    }
                    if (str2 != null) {
                        if (this.c.apkSigV2(str2)) {
                            c(str, str2);
                        } else {
                            b(str, str2);
                        }
                        a(str, str2);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ae.b(this.a, "lebian_module_overlayinfo");
            }
        }
    }

    public synchronized void a(String str, String str2) {
        try {
            com.excelliance.lbsdk.main.g.b.c("OverlayManager", "overlayExceptAssets srcZip:" + str + " destZip:" + str2, new Object[0]);
            long jComputeFileLen = this.c.computeFileLen(str2);
            com.excelliance.lbsdk.main.g.b.c("OverlayManager", "overlayExceptAssets reserve:" + jComputeFileLen + " real length:" + new File(str2).length(), new Object[0]);
            if (jComputeFileLen != new File(str2).length()) {
                FileChannel channel = new FileOutputStream(str2, true).getChannel();
                channel.truncate(jComputeFileLen);
                channel.close();
            }
            String strB = ae.b(this.a, "lebian_module_overlayinfo", "overlay_res", (String) null);
            if (strB != null && new File(strB).exists()) {
                ArrayList arrayList = new ArrayList();
                ZipFile zipFile = new ZipFile(strB);
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (name.contains("../")) {
                        throw new IOException("entry name contains illegal characters ../");
                    }
                    if (!zipEntryNextElement.isDirectory() && !name.startsWith("assets") && !name.startsWith("AndroidManifest.xml") && !name.startsWith("resources.arsc")) {
                        arrayList.add(name);
                    }
                }
                zipFile.close();
                if (!arrayList.isEmpty()) {
                    NativeUtil.overwriteEntry(str, str2, arrayList, this.c.computeFileMd5(str2), String.valueOf(this.c.computeFileLen(str2)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void a(String str, String str2, int i, String str3) throws Throwable {
        String packageResourcePath;
        if (str != null) {
            packageResourcePath = str.substring(0, str.lastIndexOf("/") + 1) + "base.odex.apk";
        } else {
            packageResourcePath = null;
        }
        if (packageResourcePath == null || !new File(packageResourcePath).exists()) {
            packageResourcePath = this.a.getApplicationContext().getPackageResourcePath();
        }
        byte[] bArrA = a(packageResourcePath);
        if (bArrA == null || bArrA.length <= 0) {
            return;
        }
        byte[] bArrA2 = a(str2);
        if (bArrA2 == null || bArrA2.length == 0) {
            if (a(bArrA, str2)) {
                try {
                    a(str3, GameUtil.getIntance().computeInputStreamMd5(new BufferedInputStream(new FileInputStream(str2))), str2.substring(0, str2.lastIndexOf("/") + 1) + "md5Info.cfg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            com.excelliance.lbsdk.main.g.b.c("OverlayManager", "zip comment = " + bArrA, new Object[0]);
        }
    }

    public void a(String str, String str2, String str3) throws Throwable {
        File file = new File(str3);
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    } else {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file));
                    try {
                        bufferedWriter2.write(str + ":" + str2);
                        bufferedWriter2.flush();
                        bufferedWriter2.close();
                    } catch (Exception e) {
                        e = e;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter == null) {
                        } else {
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.RandomAccessFile] */
    public boolean a(byte[] bArr, String str) throws Throwable {
        ?? randomAccessFile;
        File file = new File(str);
        boolean z = false;
        ?? r1 = 0;
        ?? r2 = 0;
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    randomAccessFile.seek(file.length() - 2);
                    ByteBuffer byteBufferOrder = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
                    short length = (short) bArr.length;
                    byteBufferOrder.putShort(length);
                    randomAccessFile.write(byteBufferOrder.array());
                    randomAccessFile.write(bArr);
                    try {
                        randomAccessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    z = true;
                    r1 = length;
                } catch (Exception e2) {
                    e = e2;
                    r2 = randomAccessFile;
                    com.excelliance.lbsdk.main.g.b.c("OverlayManager", "write comment failed", e, new Object[0]);
                    r1 = r2;
                    if (r2 != 0) {
                        try {
                            r2.close();
                            r1 = r2;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            r1 = r2;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (randomAccessFile != 0) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = r1;
            }
        } catch (Exception e5) {
            e = e5;
        }
        return z;
    }

    public byte[] a(String str) {
        byte[] bArrA = null;
        try {
            File file = new File(str);
            long length = file.length();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) Math.min(length, 8192L)];
            fileInputStream.skip(length - ((long) bArr.length));
            int i = fileInputStream.read(bArr);
            bArrA = i > 0 ? a(bArr, i) : null;
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArrA;
    }

    public void b() {
        String packageName = this.a.getPackageName();
        boolean zB = ae.b(this.a, "lebian_module_overlayinfo", "overlay_md", false);
        String strB = ae.b(this.a, "lebian_module_overlayinfo", "overlay_md_s", (String) null);
        if (strB != null && strB.length() > 0) {
            e();
        }
        if (zB) {
            ad.a(this.a).a(0, packageName, 2097152L, true);
        }
        if (ae.b(this.a, "lebian_module_overlayinfo", "overlay_sign", false)) {
            ad.a(this.a).a(0, packageName, 16777216L, true);
        }
        String strB2 = ae.b(this.a, "lebian_module_overlayinfo", "overlay_res", (String) null);
        if (strB2 != null) {
            ad.a(this.a).a(0, packageName, (String) null);
            new File(strB2).delete();
        }
        ae.b(this.a, "lebian_module_overlayinfo");
    }

    public void b(String str) {
        Bundle bundle;
        String name = null;
        try {
            PackageInfo packageArchiveInfo = this.a.getPackageManager().getPackageArchiveInfo(this.b.e(), GameUtil.F_NOTIFY_TYPE_AB_TEST);
            bundle = packageArchiveInfo != null ? packageArchiveInfo.applicationInfo.metaData : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bundle == null || bundle.getString("unity.build-id") == null || str == null) {
            return;
        }
        try {
            ZipFile zipFile = new ZipFile(new File(str));
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    if (!zipEntryNextElement.isDirectory()) {
                        name = zipEntryNextElement.getName();
                        if (!name.startsWith("assets")) {
                            break;
                        }
                        name = name.substring(name.indexOf("/") + 1);
                        break;
                    }
                }
                zipFile.close();
            } catch (Throwable th) {
                try {
                    zipFile.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (name != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("unity.build-id", name);
            ad.a(this.a).a(0, this.a.getPackageName(), bundle2);
        }
    }

    public void b(String str, String str2) {
        try {
            if (ae.b(this.a, "lebian_module_overlayinfo", "overlay_mi", true)) {
                ZipFile zipFile = new ZipFile(str);
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                ArrayList arrayList = new ArrayList();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (name.contains("../")) {
                        throw new IOException("entry name contains illegal characters ../");
                    }
                    if (!zipEntryNextElement.isDirectory() && name.startsWith("META-INF") && !name.endsWith(".RSA") && !name.endsWith(".SF") && !name.endsWith(".MF")) {
                        arrayList.add(name);
                    }
                }
                zipFile.close();
                ZipFile zipFile2 = new ZipFile(str2);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (zipFile2.getEntry((String) it.next()) != null) {
                        it.remove();
                    }
                }
                zipFile2.close();
                if (arrayList.isEmpty()) {
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("OverlayManager", "overlayMetaInfo overwriteEntry", new Object[0]);
                String strComputeFileMd5 = this.c.computeFileMd5(str2);
                String.valueOf(this.c.computeFileLen(str2));
                NativeUtil.overwriteEntry(str, str2, arrayList, strComputeFileMd5, "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
        String strB = ae.b(this.a, "lebian_module_extractinfo", "installed", (String) null);
        l lVarD = this.b.d();
        if (strB == null && lVarD == null) {
            return;
        }
        String strB2 = ae.b(this.a, "lebian_module_overlayinfo", "id", "-1");
        if (strB2.equals("-1")) {
            return;
        }
        com.excelliance.lbsdk.main.g.b.c("OverlayManager", "enable overlay " + strB2, new Object[0]);
        String packageName = this.a.getPackageName();
        boolean zB = ae.b(this.a, "lebian_module_overlayinfo", "overlay_md", false);
        String strB3 = ae.b(this.a, "lebian_module_overlayinfo", "overlay_md_s", (String) null);
        if (strB3 == null || strB3.length() <= 0) {
            ad.a(this.a).a(0, packageName, 2097152L, !zB);
        } else {
            c(strB3);
        }
        ad.a(this.a).a(0, packageName, 16777216L, !ae.b(this.a, "lebian_module_overlayinfo", "overlay_sign", false));
        String strB4 = ae.b(this.a, "lebian_module_overlayinfo", "overlay_res", (String) null);
        if (strB4 != null) {
            if (!new File(strB4).exists()) {
                b();
                return;
            }
            com.excelliance.lbsdk.main.g.b.c("OverlayManager", "or=" + ad.a(this.a).a(0, packageName, strB4), new Object[0]);
        }
    }

    public void c(String str) {
        String[] strArrSplit = str.split(";");
        com.excelliance.lbsdk.main.g.b.c("OverlayManager", "overlaySomeMetaData arr len:" + strArrSplit.length, new Object[0]);
        if (strArrSplit.length > 0) {
            Bundle bundle = null;
            try {
                PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), GameUtil.F_NOTIFY_TYPE_AB_TEST);
                if (packageInfo != null) {
                    bundle = packageInfo.applicationInfo.metaData;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Bundle bundle2 = new Bundle();
            for (String str2 : strArrSplit) {
                Object obj = bundle.get(str2);
                if (bundle != null && obj != null) {
                    if (obj instanceof Boolean) {
                        bundle2.putBoolean(str2, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Integer) {
                        bundle2.putInt(str2, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        bundle2.putLong(str2, ((Long) obj).longValue());
                    } else if (obj instanceof Double) {
                        bundle2.putDouble(str2, ((Double) obj).doubleValue());
                    } else if (obj instanceof String) {
                        bundle2.putString(str2, (String) obj);
                    } else if (obj instanceof boolean[]) {
                        bundle2.putBooleanArray(str2, (boolean[]) obj);
                    } else if (obj instanceof int[]) {
                        bundle2.putIntArray(str2, (int[]) obj);
                    } else if (obj instanceof long[]) {
                        bundle2.putLongArray(str2, (long[]) obj);
                    } else if (obj instanceof double[]) {
                        bundle2.putDoubleArray(str2, (double[]) obj);
                    } else {
                        if (!(obj instanceof String[])) {
                            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
                        }
                        bundle2.putStringArray(str2, (String[]) obj);
                    }
                }
            }
            ad.a(this.a).a(0, this.a.getPackageName(), bundle2);
        }
    }

    public void c(String str, String str2) throws IOException {
        if (ae.b(this.a, "lebian_module_overlayinfo", "overlay_v2_channel", false)) {
            com.excelliance.lbsdk.main.y.d.a(str, str2, this.c.computeFileMd5(str2, false, true));
        }
    }

    public ab d() {
        String str;
        com.excelliance.lbsdk.main.g.b.c("OverlayManager", "queryOverlayInfo entered", new Object[0]);
        GameUtil intance = GameUtil.getIntance();
        StringBuilder sb = new StringBuilder(intance.getBaseUrl() + "pickoverlay.php");
        sb.append("?chid=" + intance.getApkMainCh());
        try {
            sb.append("&cpmeta=" + URLEncoder.encode(intance.getClientChId(), "UTF-8"));
        } catch (Exception unused) {
        }
        sb.append("&vercode=" + intance.getVerCodeFromApk(this.a.getPackageResourcePath()));
        sb.append("&ovid=" + a());
        sb.append("&pkg=" + this.a.getPackageName());
        sb.append("&md5=" + intance.computeFileMd5(this.a.getApplicationContext().getPackageResourcePath(), false, true));
        ab abVar = null;
        try {
            o oVarA = aa.a(sb.toString(), this.a);
            if (oVarA.a == 200 && (str = oVarA.b) != null && str.length() != 0) {
                ByteArrayInputStream byteArrayInputStream = (str == null || str.trim().equals("")) ? null : new ByteArrayInputStream(str.getBytes());
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                ab abVar2 = null;
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                    if (eventType != 0 && eventType == 2) {
                        try {
                            if (xmlPullParserNewPullParser.getName().equalsIgnoreCase("ovl")) {
                                String attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "oid");
                                String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "of");
                                List listAsList = (attributeValue == null || attributeValue2 == null || attributeValue2.length() <= 0) ? null : Arrays.asList(attributeValue2.split(";"));
                                String attributeValue3 = xmlPullParserNewPullParser.getAttributeValue(null, "om");
                                boolean z = attributeValue3 != null && attributeValue3.equals("1");
                                String attributeValue4 = xmlPullParserNewPullParser.getAttributeValue(null, "omi");
                                boolean z2 = attributeValue4 == null || !attributeValue4.equals("0");
                                String attributeValue5 = xmlPullParserNewPullParser.getAttributeValue(null, "os");
                                boolean z3 = attributeValue5 != null && attributeValue5.equals("1");
                                String attributeValue6 = xmlPullParserNewPullParser.getAttributeValue(null, "ov2");
                                abVar2 = new ab(attributeValue, listAsList, xmlPullParserNewPullParser.getAttributeValue(null, "um"), z, z2, z3, attributeValue6 == null || !attributeValue6.equals("1"));
                            }
                        } catch (Exception e) {
                            e = e;
                            abVar = abVar2;
                            com.excelliance.lbsdk.main.g.b.c("OverlayManager", "queryOverlayInfo e=", e, new Object[0]);
                            return abVar;
                        }
                    }
                }
                byteArrayInputStream.close();
                return abVar2;
            }
            return null;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void e() {
        ad.a(this.a).a(0, this.a.getPackageName(), new Bundle());
    }
}
