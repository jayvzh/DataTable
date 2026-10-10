package com.excelliance.lbsdk.main;

import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class n {
    private static Class f;
    private static Class g;
    private static Method h;
    private static final Pattern t;
    private static Map<String, ClassLoader> a = new HashMap();
    private static boolean b = false;
    private static ClassLoader c = null;
    private static final ClassLoader d = Object.class.getClassLoader();
    private static final ClassLoader e = ClassLoader.getSystemClassLoader();
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static boolean l = false;
    private static boolean m = false;
    private static boolean n = false;
    private static boolean o = false;
    private static boolean p = false;
    private static boolean q = false;
    private static boolean r = false;
    private static boolean s = false;

    static {
        try {
            f = Class.forName("dalvik.system.PathClassLoader");
        } catch (Exception unused) {
            f = null;
        }
        try {
            g = Class.forName("dalvik.system.BaseDexClassLoader");
        } catch (Exception unused2) {
            g = null;
        }
        t = Pattern.compile("[\\w%+,./=_-]+");
    }

    public static int a(String str, int i2, int i3, int i4) {
        try {
            Method declaredMethod = Class.forName("android.os.FileUtils").getDeclaredMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(null, str, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4))).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX WARN: Code duplicated, block: B:63:0x00f7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:64:0x00f8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x00fa A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:67:0x00fc A[RETURN] */
    private static int a(String str, File file, String str2) throws Throwable {
        boolean z;
        boolean z2;
        int iLastIndexOf;
        File file2 = new File(str);
        int length = str2.length();
        try {
            try {
                ZipFile zipFile = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                    z = false;
                    z2 = false;
                    while (enumerationEntries.hasMoreElements()) {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (!zipEntryNextElement.isDirectory()) {
                            String name = zipEntryNextElement.getName();
                            if (name.contains("../")) {
                                throw new IOException("entry name contains illegal characters ../");
                            }
                            if (name.length() >= 13 && name.endsWith(".so") && name.startsWith("lib/") && (iLastIndexOf = name.lastIndexOf(47)) >= 0) {
                                int i2 = iLastIndexOf + 1;
                                if (name.regionMatches(i2, "lib", 0, 3)) {
                                    if (iLastIndexOf == 4 + length) {
                                        try {
                                            if (name.regionMatches(4, str2, 0, length)) {
                                                String strSubstring = name.substring(i2);
                                                if (a(new File(strSubstring))) {
                                                    try {
                                                        File file3 = new File(file2.getPath() + File.separator + strSubstring);
                                                        if (com.excelliance.lbsdk.main.g.b.a()) {
                                                            com.excelliance.lbsdk.main.g.b.b("HKUtil", "Caching shared lib " + zipEntryNextElement.getName(), new Object[0]);
                                                        }
                                                        file2.mkdirs();
                                                        a(zipFile, zipEntryNextElement, file2, file3);
                                                        z = true;
                                                        z2 = true;
                                                    } catch (IOException e2) {
                                                        e = e2;
                                                        z = true;
                                                        z2 = true;
                                                        e.printStackTrace();
                                                        if (!z) {
                                                            return 1;
                                                        }
                                                        if (z2) {
                                                            return 0;
                                                        }
                                                        return 2;
                                                    } catch (Throwable th) {
                                                        th = th;
                                                    }
                                                }
                                            }
                                        } catch (IOException e3) {
                                            e = e3;
                                            z = true;
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    }
                                    z = true;
                                } else {
                                    continue;
                                }
                            }
                            try {
                                zipFile.close();
                            } catch (Throwable unused) {
                            }
                            throw th;
                        }
                    }
                    zipFile.close();
                    if (!z) {
                        return 1;
                    }
                    if (z2) {
                        return 2;
                    }
                    return 0;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (IOException e5) {
            e = e5;
            z = false;
            z2 = false;
        }
    }

    public static String a() {
        if (b()) {
            if (i) {
                return "YiwanEmulator";
            }
            if (j) {
                return "Droid4xEmulator";
            }
            if (k) {
                return "TencentEmulator";
            }
            if (m) {
                return "BlueStackEmulator";
            }
            if (n) {
                return "iToolsEmulator";
            }
            if (o) {
                return "MUMUEmulator";
            }
            if (p) {
                return "51Emulator";
            }
            if (l) {
                return "YeshenEmulator";
            }
            if (q) {
                return "TiantianEmulator";
            }
            if (r) {
                return "DuowanEmulator";
            }
            if (s) {
                return "XiaoyaoEmulator";
            }
        }
        return "UnknownEmulator";
    }

    public static String a(String str, String str2) {
        try {
            if (h == null) {
                h = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class);
                h.setAccessible(true);
            }
            return (String) h.invoke(null, str, str2);
        } catch (Exception e2) {
            com.excelliance.lbsdk.main.g.b.d("HKUtil", "getSystemProperties failed", e2, new Object[0]);
            return str2;
        }
    }

    private static Field a(Class<?> cls, String str) throws NoSuchFieldException {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException e2) {
            while (true) {
                cls = cls.getSuperclass();
                if (cls == null || cls.equals(Object.class)) {
                    break;
                }
                try {
                    return cls.getDeclaredField(str);
                } catch (NoSuchFieldException unused) {
                }
            }
            throw e2;
        }
    }

    private static void a(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.d("HKUtil", "Deleting native binaries from: " + file.getPath(), new Object[0]);
        }
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("HKUtil", "    Deleting " + fileArrListFiles[i2].getName(), new Object[0]);
            }
            if (!fileArrListFiles[i2].delete()) {
                com.excelliance.lbsdk.main.g.b.d("HKUtil", "Could not delete native binary: " + fileArrListFiles[i2].getPath(), new Object[0]);
            }
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file, File file2) throws IOException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        try {
            File fileCreateTempFile = File.createTempFile("tmp", "tmp", file);
            String path = fileCreateTempFile.getPath();
            if (a(inputStream, fileCreateTempFile) && fileCreateTempFile.setLastModified(zipEntry.getTime()) && a(path, 493, -1, -1) == 0 && fileCreateTempFile.renameTo(file2)) {
                inputStream.close();
                return;
            }
            fileCreateTempFile.delete();
            throw new IOException("Couldn't create cached binary " + file2 + " in " + file);
        } catch (Throwable th) {
            inputStream.close();
            throw th;
        }
    }

    public static boolean a(File file) {
        return t.matcher(file.getPath()).matches();
    }

    public static boolean a(InputStream inputStream, File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i2 = inputStream.read(bArr);
                    if (i2 < 0) {
                        fileOutputStream.close();
                        return true;
                    }
                    fileOutputStream.write(bArr, 0, i2);
                }
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    private static boolean a(String str, File file, boolean z) throws Throwable {
        if (z) {
            a(str);
        }
        try {
            String[] strArrC = (!b() || str.contains("/.lebiansdk/")) ? c() : new String[]{"armeabi-v7a", "armeabi"};
            if (strArrC != null) {
                for (String str2 : strArrC) {
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("HKUtil", "cachePackageSharedLibsLI: try cpuAbi " + str2, new Object[0]);
                    }
                    if (str2 != null) {
                        int iA = a(str, file, str2);
                        if (iA != 2) {
                            if (iA == 0) {
                                if (!com.excelliance.lbsdk.main.g.b.a()) {
                                    break;
                                }
                                com.excelliance.lbsdk.main.g.b.c("HKUtil", "Found matched native library, cpuAbi = " + str2, new Object[0]);
                                break;
                            }
                        } else if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.d("HKUtil", "Native ABI mismatch from package file, try next cpuAbi", new Object[0]);
                        }
                    }
                }
            }
            return true;
        } catch (ZipException e2) {
            com.excelliance.lbsdk.main.g.b.d("HKUtil", "Failed to extract data from package file", e2, new Object[0]);
            return false;
        } catch (IOException e3) {
            com.excelliance.lbsdk.main.g.b.d("HKUtil", "Failed to cache package shared libs", e3, new Object[0]);
            return false;
        } catch (Exception e4) {
            e4.printStackTrace();
            return false;
        }
    }

    public static boolean a(String str, String str2, boolean z) {
        File file;
        File file2 = new File(str);
        if (!file2.exists()) {
            return false;
        }
        if (str2 == null) {
            return true;
        }
        new File(str2).mkdirs();
        if (file2.getParent().equals(str2)) {
            throw new RuntimeException("ERROR: libraryPath shouldn't be the same as jarPath,please using other private directory for libraryPath!");
        }
        String strA = a("ro.product.cpu.abi", "");
        if (b() || (!TextUtils.isEmpty(strA) && strA.contains("x86"))) {
            file = new File(str);
        } else {
            try {
                try {
                    Class.forName("com.android.internal.content.NativeLibraryHelper").getDeclaredMethod("copyNativeBinariesIfNeededLI", File.class, File.class).invoke(null, file2, new File(str2));
                    return true;
                } catch (Exception unused) {
                    file = new File(str);
                }
            } catch (Exception unused2) {
                Class.forName("com.android.internal.content.NativeLibraryHelper").getDeclaredMethod("copyNativeBinariesLI", File.class, File.class).invoke(null, file2, new File(str2));
                return true;
            }
        }
        return a(str2, file, z);
    }

    public static Object b(String str, String str2, boolean z) {
        try {
            Field fieldA = a(Class.forName(str), str2);
            fieldA.setAccessible(true);
            return fieldA.get(null);
        } catch (IllegalAccessException e2) {
            throw new IllegalAccessError(e2.getMessage());
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (Exception e4) {
            if (!z) {
                e4.printStackTrace();
            }
            return null;
        }
    }

    public static boolean b() {
        boolean z;
        i = Build.BRAND != null && Build.BRAND.equals("Yiwan") && Build.DEVICE != null && Build.DEVICE.equals("Yiwan");
        String strA = a("ro.product.device", "");
        String strA2 = a("ro.product.brand", "");
        String strA3 = a("ro.product.name", "");
        String strA4 = a("ro.product.oem", "");
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "isEmulator: device= " + strA + ",brand= " + strA2 + ",name=" + strA3 + " produt:" + Build.PRODUCT, new Object[0]);
        j = "Droid4X".equalsIgnoreCase(strA);
        k = "tencent".equalsIgnoreCase(strA2);
        m = h() || ("hlteatt".equalsIgnoreCase(strA) && "hlteuc".equalsIgnoreCase(strA3)) || ("t03g".equalsIgnoreCase(strA) && "t03gxx".equalsIgnoreCase(strA3));
        n = strA.startsWith("iTools") && strA3.startsWith("iTools");
        o = "cancro".equalsIgnoreCase(strA3) || "cancro".equals(Build.PRODUCT) || i();
        p = "51mnq".equalsIgnoreCase(strA4);
        l = d();
        q = e();
        r = f();
        s = g();
        String strA5 = a("ro.product.cpu.abi", Build.CPU_ABI);
        if (strA5 != null && strA5.contains("x86")) {
            return true;
        }
        try {
            z = new File("/system/lib/libhoudini.so").exists() || new File("/system/lib/arm/libhoudini.so").exists();
        } catch (Throwable unused) {
        }
        return z || i || j || k || m || n || o || p || l || q || r || s;
    }

    private static String[] c() {
        String[] strArr;
        String name;
        String str;
        if (Build.VERSION.SDK_INT < 21) {
            String strA = a("ro.product.cpu.abi", Build.CPU_ABI);
            String strA2 = a("ro.product.cpu.abi2", Build.CPU_ABI2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(strA);
            if (!arrayList.contains(Build.CPU_ABI)) {
                arrayList.add(Build.CPU_ABI);
            }
            if (!arrayList.contains(strA2)) {
                arrayList.add(strA2);
            }
            if (!arrayList.contains(Build.CPU_ABI2)) {
                arrayList.add(Build.CPU_ABI2);
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        } else {
            try {
                Class<?> cls = Class.forName("dalvik.system.VMRuntime");
                if (((Boolean) cls.getDeclaredMethod("is64Bit", new Class[0]).invoke(cls.getDeclaredMethod("getRuntime", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue()) {
                    name = Build.class.getName();
                    str = "SUPPORTED_64_BIT_ABIS";
                } else {
                    name = Build.class.getName();
                    str = "SUPPORTED_32_BIT_ABIS";
                }
                strArr = (String[]) b(name, str, false);
            } catch (Exception unused) {
                strArr = new String[]{Build.CPU_ABI};
            }
        }
        if (com.excelliance.lbsdk.main.g.b.a() && strArr != null) {
            com.excelliance.lbsdk.main.g.b.b("HKUtil", "selectBestCpuAbis: final abiList: " + Arrays.toString(strArr), new Object[0]);
        }
        return strArr;
    }

    private static boolean d() {
        long jLastModified = new File("/system/bin/noxspeedup").lastModified();
        long jLastModified2 = new File("/dev/vboxguest").lastModified();
        long jLastModified3 = new File("/data/data/com.vphone.launcher").lastModified();
        if (jLastModified <= 0 && jLastModified2 <= 0 && jLastModified3 <= 0) {
            return false;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is yeshen", new Object[0]);
        return true;
    }

    private static boolean e() {
        if (new File("/dev/vboxpci").lastModified() <= 0) {
            return false;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is tiantian", new Object[0]);
        return true;
    }

    private static boolean f() {
        long jLastModified = new File("/system/bin/VBoxGuestProperty").lastModified();
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "r time:" + jLastModified, new Object[0]);
        if (jLastModified <= 0) {
            return false;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is duowan", new Object[0]);
        return true;
    }

    private static boolean g() {
        long jLastModified = new File("/system/bin/microvirt-prop").lastModified();
        long jLastModified2 = new File("/system/bin/microvirt-vbox-sf").lastModified();
        if (jLastModified <= 0 || jLastModified2 <= 0) {
            return false;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is xiaoyao", new Object[0]);
        return true;
    }

    private static boolean h() {
        long jLastModified = new File("/system/bin/bstshutdown").lastModified();
        long jLastModified2 = new File("/dev/bstpgaipc").lastModified();
        long jLastModified3 = new File("/storage/emulated/0/Android/data/com.bluestacks.home").lastModified();
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is_bluestacks_emulator: time1:" + jLastModified + " time2:" + jLastModified2 + " time3:" + jLastModified3, new Object[0]);
        if (jLastModified <= 0 && jLastModified2 <= 0 && jLastModified3 <= 0) {
            return false;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is bluestacks", new Object[0]);
        return true;
    }

    private static boolean i() {
        boolean z;
        long jLastModified = new File("/system/bin/nemuVM-prop").lastModified();
        long jLastModified2 = new File("/system/bin/nemuVM-nemu-sf").lastModified();
        long jLastModified3 = new File("/system/bin/nemuVM-nemu-service").lastModified();
        long jLastModified4 = new File("/dev/muvm6guest").lastModified();
        long jLastModified5 = new File("/dev/muvm6usr").lastModified();
        if (jLastModified > 0 || jLastModified2 > 0 || jLastModified3 > 0) {
            com.excelliance.lbsdk.main.g.b.c("HKUtil", "is mumu", new Object[0]);
            z = true;
        } else {
            z = false;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is_mumu_emulator time3:" + jLastModified3 + " time4:" + jLastModified4, new Object[0]);
        if (z) {
            return z;
        }
        if (jLastModified4 <= 0 && jLastModified5 <= 0) {
            return z;
        }
        com.excelliance.lbsdk.main.g.b.c("HKUtil", "is mumu", new Object[0]);
        return true;
    }
}
