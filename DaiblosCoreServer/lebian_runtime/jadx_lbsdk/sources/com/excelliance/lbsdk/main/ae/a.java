package com.excelliance.lbsdk.main.ae;

import android.content.Context;
import android.os.Build;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.ai;
import com.excelliance.lbsdk.main.n;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: com.excelliance.lbsdk.main.ae.a$a, reason: collision with other inner class name */
    private static final class C0000a {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, NoSuchFieldException {
            Object obj = ai.a(classLoader, "pathList").get(classLoader);
            Field fieldA = ai.a(obj, "nativeLibraryDirectories");
            File[] fileArr = (File[]) fieldA.get(obj);
            ArrayList arrayList = new ArrayList(fileArr.length + 1);
            arrayList.add(file);
            for (File file2 : fileArr) {
                if (!file.equals(file2)) {
                    arrayList.add(file2);
                }
            }
            fieldA.set(obj, arrayList.toArray(new File[0]));
        }
    }

    private static final class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException {
            Object obj = ai.a(classLoader, "pathList").get(classLoader);
            List arrayList = (List) ai.a(obj, "nativeLibraryDirectories").get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (file.equals((File) it.next())) {
                    it.remove();
                    break;
                }
            }
            arrayList.add(0, file);
            List arrayList2 = (List) ai.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(2);
            }
            ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            ai.a(obj, "nativeLibraryPathElements").set(obj, (Object[]) ai.a(obj, "makePathElements", List.class, File.class, List.class).invoke(obj, arrayList3, null, new ArrayList()));
        }
    }

    private static final class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException {
            Object obj = ai.a(classLoader, "pathList").get(classLoader);
            List arrayList = (List) ai.a(obj, "nativeLibraryDirectories").get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (file.equals((File) it.next())) {
                    it.remove();
                    break;
                }
            }
            arrayList.add(0, file);
            List arrayList2 = (List) ai.a(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(2);
            }
            ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            ai.a(obj, "nativeLibraryPathElements").set(obj, (Object[]) ai.a(obj, "makePathElements", List.class).invoke(obj, arrayList3));
        }
    }

    private static final class d {
        /* JADX INFO: Access modifiers changed from: private */
        public static void b(ClassLoader classLoader, File file) throws IllegalAccessException, NoSuchFieldException {
            String path = file.getPath();
            Field fieldA = ai.a(classLoader, "libPath");
            String[] strArrSplit = ((String) fieldA.get(classLoader)).split(":");
            StringBuilder sb = new StringBuilder(path);
            for (String str : strArrSplit) {
                if (str != null && !path.equals(str)) {
                    sb.append(':');
                    sb.append(str);
                }
            }
            fieldA.set(classLoader, sb.toString());
            Field fieldA2 = ai.a(classLoader, "libraryPathElements");
            List list = (List) fieldA2.get(classLoader);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (path.equals((String) it.next())) {
                    it.remove();
                    break;
                }
            }
            list.add(0, path);
            fieldA2.set(classLoader, list);
        }
    }

    public static String a(Context context) {
        String str = com.excelliance.lbsdk.main.ae.c.c(context) + "lib";
        if (new File(str).exists()) {
            return str;
        }
        return null;
    }

    public static String a(Context context, String str) {
        String[] strArrA = a();
        boolean zContains = context.getApplicationInfo().nativeLibraryDir.contains("lib/arm");
        for (String str2 : strArrA) {
            File file = new File(str, "lib/" + str2);
            if (str2 != null && (!(str2.contains("x86") && zContains) && file.exists())) {
                com.excelliance.lbsdk.main.g.b.c("AssetUpLoadLibrary", "selectSoOutPath path:" + file + " isArm:" + zContains, new Object[0]);
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    public static void a(Context context, String str, String str2) {
        String[] strArrA = a();
        boolean zContains = context.getApplicationInfo().nativeLibraryDir.contains("lib/arm");
        com.excelliance.lbsdk.main.g.b.c("AssetUpLoadLibrary", "copyLibrary src:" + str + " dest:" + str2 + " isArm:" + zContains, new Object[0]);
        for (String str3 : strArrA) {
            if (str3 != null && (!str3.contains("x86") || !zContains)) {
                File file = new File(str, "lib/" + str3);
                if (file.exists()) {
                    for (File file2 : file.listFiles()) {
                        try {
                            GameUtil.getIntance().copyFile(file2, new File(str2, file2.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void a(ClassLoader classLoader, String str) throws IllegalAccessException, NoSuchFieldException {
        if (str == null) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            if ((Build.VERSION.SDK_INT == 25 && Build.VERSION.PREVIEW_SDK_INT != 0) || Build.VERSION.SDK_INT > 25) {
                try {
                    c.b(classLoader, file);
                    return;
                } catch (Throwable unused) {
                    b.b(classLoader, file);
                    return;
                }
            }
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    b.b(classLoader, file);
                    return;
                } catch (Throwable unused2) {
                }
            } else if (Build.VERSION.SDK_INT < 14) {
                d.b(classLoader, file);
                return;
            }
            C0000a.b(classLoader, file);
        }
    }

    private static String[] a() {
        String[] strArr;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Class<?> cls = Class.forName("dalvik.system.VMRuntime");
                strArr = (String[]) (((Boolean) cls.getDeclaredMethod("is64Bit", new Class[0]).invoke(cls.getDeclaredMethod("getRuntime", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue() ? n.b(Build.class.getName(), "SUPPORTED_64_BIT_ABIS", false) : n.b(Build.class.getName(), "SUPPORTED_32_BIT_ABIS", false));
            } catch (Exception unused) {
                strArr = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
            }
            return strArr;
        }
        String strA = n.a("ro.product.cpu.abi", Build.CPU_ABI);
        String strA2 = n.a("ro.product.cpu.abi2", Build.CPU_ABI2);
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
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
