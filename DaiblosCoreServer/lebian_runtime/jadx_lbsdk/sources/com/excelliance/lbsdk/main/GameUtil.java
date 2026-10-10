package com.excelliance.lbsdk.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Xml;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class GameUtil {
    public static final int F_NOTIFY_TYPE_AB_TEST = 128;
    public static final int F_NOTIFY_TYPE_BWBX_DIALOG_CLICK = 2;
    public static final int F_NOTIFY_TYPE_BWBX_DOWNLOAD_ERROR = 4;
    public static final int F_NOTIFY_TYPE_BWBX_DOWNLOAD_PROGRESS = 1;
    public static final int F_NOTIFY_TYPE_BWBX_DOWNLOAD_TIME_OVER_LIMIT = 32;
    public static final int F_NOTIFY_TYPE_BWBX_MISSING_FILE = 64;
    public static final int F_NOTIFY_TYPE_BWBX_RECORD_FILES = 8;
    public static final int F_NOTIFY_TYPE_BWBX_TRANS_MISSING = 16;
    public static final int F_NOTIFY_TYPE_BWBX_USE_BK_CDN = 2;
    public static final int F_NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR = 8;
    public static final int F_NOTIFY_TYPE_SPEED_REPORT = 4;
    public static final int F_NOTIFY_TYPE_SPEED_SWITCH = 1;
    public static final String MAIN_JAR = "lbsdk.jar";
    public static final String MUST_JAR = "lbmust.jar";
    public static final int NOTIFY_TYPE_AB_TEST = 12;
    public static final int NOTIFY_TYPE_BWBX_DIALOG_CLICK = 2;
    public static final int NOTIFY_TYPE_BWBX_DOWNLOAD_ERROR = 3;
    public static final int NOTIFY_TYPE_BWBX_DOWNLOAD_PROGRESS = 1;
    public static final int NOTIFY_TYPE_BWBX_DOWNLOAD_TIME_OVER_LIMIT = 6;
    public static final int NOTIFY_TYPE_BWBX_MISS_FILE = 7;
    public static final int NOTIFY_TYPE_BWBX_RECORD_FILES = 4;
    public static final int NOTIFY_TYPE_BWBX_TRANS_MISSING = 5;
    public static final int NOTIFY_TYPE_BWBX_USE_BK_CDN = 10;
    public static final int NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR = 11;
    public static final int NOTIFY_TYPE_SPEED_REPORT = 9;
    public static final int NOTIFY_TYPE_SPEED_SWITCH = 8;
    public static final String UI_JAR = "lbui.jar";
    public static final String VM_JAR = "lbvmrt.jar";
    public static GameUtil instance;
    public static final Object overlaySync = new Object();
    private static long h = 0;
    private static long i = 0;
    private static int j = 65536;
    private static long k = -1;
    public static int isCacheChecking = -1;
    public static String checkingFile = null;
    public static List checkedList = Collections.synchronizedList(new ArrayList());
    public static Object checkCacheSync = new Object();
    private static String y = null;
    private Context a = null;
    private Handler b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private Object g = new Object();
    public boolean getNotifySwitching = false;
    private boolean l = false;
    private int m = -1;
    private int n = -1;
    private int o = -1;
    private int p = -1;
    private int q = -1;
    private int r = -1;
    private int s = -1;
    private int t = -1;
    private int u = -1;
    private int v = -1;
    private int w = -1;
    private int x = -1;
    private an f = an.a();

    public interface a {
        void a(long j, long j2);
    }

    public static class b {
        int a = 0;
        private SharedPreferences b;
        private SharedPreferences.Editor c;

        public b(Context context, String str) {
            this.b = null;
            this.c = null;
            this.b = context.getSharedPreferences(str, Build.VERSION.SDK_INT >= 11 ? 4 : 0);
            this.c = this.b.edit();
        }

        public Object a(String str, Object obj) {
            if (obj instanceof Boolean) {
                return Boolean.valueOf(this.b.getBoolean(str, ((Boolean) obj).booleanValue()));
            }
            if (obj instanceof Float) {
                return Float.valueOf(this.b.getFloat(str, ((Float) obj).floatValue()));
            }
            if (obj instanceof Integer) {
                return Integer.valueOf(this.b.getInt(str, ((Integer) obj).intValue()));
            }
            if (obj instanceof Long) {
                return Long.valueOf(this.b.getLong(str, ((Long) obj).longValue()));
            }
            if ((obj instanceof String) || obj == null) {
                return this.b.getString(str, (String) obj);
            }
            return null;
        }
    }

    private GameUtil() {
    }

    private long a(RandomAccessFile randomAccessFile, long j2) throws IOException {
        byte[] bArr = new byte[24];
        if (j2 == 0 || randomAccessFile == null) {
            return 0L;
        }
        randomAccessFile.seek(j2 - 24);
        randomAccessFile.read(bArr);
        if (!new String(bArr, 8, 16, "gbk").equals("APK Sig Block 42")) {
            return 0L;
        }
        return 8 + (((((long) bArr[2]) & 255) << 16) | (((long) bArr[0]) & 255) | ((((long) bArr[1]) & 255) << 8) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[7]) & 255) << 56));
    }

    private long a(String str, RandomAccessFile randomAccessFile) throws IOException {
        if (randomAccessFile == null) {
            return 0L;
        }
        byte[] bArrA = ac.a(this.a).a(str);
        byte[] bArr = new byte[(bArrA != null ? bArrA.length : 0) + 22];
        randomAccessFile.seek(randomAccessFile.length() - ((long) bArr.length));
        if (randomAccessFile.read(bArr) > 0) {
            return (((long) bArr[16]) & 255) + ((((long) bArr[17]) & 255) << 8) + ((((long) bArr[18]) & 255) << 16) + ((((long) bArr[19]) & 255) << 24);
        }
        return 0L;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x005d: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:34:0x005d */
    private static String a(int i2) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        try {
            try {
                try {
                    fileInputStream2 = new FileInputStream("/proc/" + i2 + "/cmdline");
                    try {
                        byte[] bArr = new byte[256];
                        int i3 = 0;
                        while (true) {
                            int i4 = fileInputStream2.read();
                            if (i4 <= 0 || i3 >= bArr.length) {
                                break;
                            }
                            bArr[i3] = (byte) i4;
                            i3++;
                        }
                        if (i3 > 0) {
                            String str = new String(bArr, 0, i3, "UTF-8");
                            try {
                                fileInputStream2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return str;
                        }
                        fileInputStream2.close();
                    } catch (Throwable th) {
                        th = th;
                        th.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return null;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream3 != null) {
                    try {
                        fileInputStream3.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream3 = fileInputStream;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        try {
            return InetAddress.getByName(new URL(str).getHost()).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static void a(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.trim().length() == 0 ? "" : File.separator);
        sb.append(file.getName());
        String str2 = new String(sb.toString().getBytes("8859_1"), "GB2312");
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    a(file2, zipOutputStream, str2);
                }
            }
        } else {
            byte[] bArr = new byte[1024];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1024);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 == -1) {
                    break;
                } else {
                    zipOutputStream.write(bArr, 0, i2);
                }
            }
            bufferedInputStream.close();
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        file.delete();
    }

    private static void a(String str, File file) {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
            zipOutputStream.setMethod(0);
            writeZipStore(zipOutputStream, file, "");
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void a(String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        if (getIntance().a == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str2.trim().length() == 0 ? "" : File.separator);
        sb.append(str);
        String str3 = new String(sb.toString().getBytes("8859_1"), "GB2312");
        byte[] bArr = new byte[1024];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(getIntance().a.getAssets().open(str), 1024);
        zipOutputStream.putNextEntry(new ZipEntry(str3));
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 == -1) {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, i2);
        }
    }

    private boolean a() {
        if (this.d == null) {
            this.d = this.a.getApplicationInfo().dataDir;
        }
        if (this.e == null) {
            this.e = this.d + "/.lebiansdk/";
        }
        return new File(this.e, MAIN_JAR).exists() || new File(this.e, "new/main/lbsdk.jar").exists();
    }

    private static boolean a(Context context) {
        boolean zExists = m.a(context, "START_CHECK_PRIVACY_PERMISSION", false) ? new File(context.getCacheDir().getParent(), "lebian_privacy").exists() : true;
        if (zExists) {
            return zExists;
        }
        return m.a(context, "SELECTED_AREA", 0) != 0;
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(jarFile.getInputStream(jarEntry));
            while (bufferedInputStream.read(bArr, 0, bArr.length) != -1) {
            }
            bufferedInputStream.close();
            if (jarEntry != null) {
                return jarEntry.getCertificates();
            }
            return null;
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.d("GameUtil", "Exception reading " + jarEntry.getName() + " in " + jarFile.getName(), e, new Object[0]);
            return null;
        } catch (RuntimeException e2) {
            com.excelliance.lbsdk.main.g.b.d("GameUtil", "Exception reading " + jarEntry.getName() + " in " + jarFile.getName(), e2, new Object[0]);
            return null;
        }
    }

    private com.excelliance.lbsdk.main.x.a b(String str, RandomAccessFile randomAccessFile) throws IOException {
        if (randomAccessFile == null) {
            return new com.excelliance.lbsdk.main.x.a(0L, 0L);
        }
        byte[] bArrA = ac.a(this.a).a(str);
        int length = bArrA != null ? bArrA.length : 0;
        byte[] bArr = new byte[length + 22];
        long length2 = randomAccessFile.length();
        randomAccessFile.seek(length2 - ((long) bArr.length));
        return new com.excelliance.lbsdk.main.x.a(randomAccessFile.read(bArr) > 0 ? (((long) bArr[16]) & 255) + ((((long) bArr[17]) & 255) << 8) + ((((long) bArr[18]) & 255) << 16) + ((((long) bArr[19]) & 255) << 24) : 0L, length == 0 ? -1L : (length2 - ((long) length)) - 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Iterator<l> it = an.a().b(this.a.getPackageName()).iterator();
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            l next = it.next();
            if (next.patch == 3) {
                z = getIntance().getVerCodeFromApk(next.savePath, true) < getIntance().getVerCodeFromApk(this.a.getPackageCodePath(), true) && getIntance().getVerCodeFromApk(next.savePath, false, true) > getIntance().getVerCodeFromApk(this.a.getPackageCodePath(), false);
            }
        }
        if (z) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "overInsCountingSize: dont check res patch", new Object[0]);
            clearResPatchInfo();
            return;
        }
        String strB = ae.b(this.a, "lebian_module_bwbxinfo", "lastResPatchVersion", "-1");
        ae.a(this.a, "lebian_module_bwbxinfo", "lastResPatchVersion");
        boolean zB = ae.b(this.a, "lebian_module_bwbxinfo", "dlresPatch", false);
        boolean z2 = !strB.equals(getRunningVersionCode(false, true));
        if (!z2) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "overInsCountingSize: has been download", new Object[0]);
        }
        if (z2 && !zB) {
            try {
                String strComputeFileMd5 = computeFileMd5(getLocalDataPath(this.a));
                String strComputeInputStreamMd5 = computeInputStreamMd5(e.a(this.a));
                boolean zIsBigAsset = BwbxUtil.isBigAsset(this.a);
                if (zIsBigAsset || (strComputeFileMd5 != null && !strComputeFileMd5.equals(strComputeInputStreamMd5))) {
                    if (zIsBigAsset) {
                        zB = ae.b(this.a, "lebian_module_dloadinfo", "success", false);
                    } else {
                        double downloadedSize = getDownloadedSize(this.a, getLastVer(this.a));
                        double origSize = getIntance().getOrigSize(false) + getIntance().getOrigSize(false, 2);
                        Double.isNaN(origSize);
                        if (downloadedSize > origSize * 0.5d) {
                            zB = true;
                        }
                    }
                    if (zB && ae.b(this.a, "lebian_module_dloadinfo", "success", false) && !ae.b(this.a, "lebian_module_dloadinfo", "bwbxZipExtracting", false)) {
                        ae.a(this.a, "lebian_module_bwbxinfo", "resEnough", true);
                    }
                    ae.a(this.a, "lebian_module_bwbxinfo", "dlresPatch", zB);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "dlresPatch:" + zB + z2, new Object[0]);
        if (!zB || !z2) {
            clearResPatchInfo();
            return;
        }
        if (BwbxUtil.isBigAsset(this.a)) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "dlresPatch isNeedCheckResPath:" + this.l, new Object[0]);
            this.l = true;
            return;
        }
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(this.a.getPackageName());
        intent.setComponent(new ComponentName(this.a.getPackageName(), BGService.class.getName()));
        intent.putExtra("dlsType", 2);
        intent.putExtra("resPatchCheck", true);
        intent.putExtra("extra.lbsdk.hostintent", true);
        w.a(this.a).a(intent);
    }

    public static String byteArrayToHex(byte[] bArr) {
        return com.excelliance.lbsdk.main.ai.f.a(bArr);
    }

    private void c() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cleanTwiceLoadFlag(Context context) {
        ae.a(context, "lebian_module_dloadinfo", "twloadFinished-" + getFullPkgMd5(context));
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "isTwloadFinished removed", new Object[0]);
    }

    public static String computeInputStreamMd5(InputStream inputStream, long j2) {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[j];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            while (true) {
                int i2 = inputStream.read(bArr, 0, j2 > ((long) j) ? j : (int) j2);
                if (i2 <= 0) {
                    inputStream.close();
                    return byteArrayToHex(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, i2);
                j2 -= (long) i2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject convertLbresMetaIsToJson(InputStream inputStream, String str) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i2 = bufferedInputStream.read();
            if (i2 == -1) {
                try {
                    return new JSONObject(byteArrayOutputStream.toString()).optJSONObject(str);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            byteArrayOutputStream.write((byte) i2);
        }
    }

    public static Notification createNotification(Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16) {
            return builder.build();
        }
        if (Build.VERSION.SDK_INT >= 11) {
            return builder.getNotification();
        }
        return null;
    }

    public static Notification createNotification(Context context, boolean z, int i2, String str, String str2, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 16) {
            return createNotificationBuilder(context, z, i2, str, str2, pendingIntent).build();
        }
        if (Build.VERSION.SDK_INT >= 11) {
            return createNotificationBuilder(context, z, i2, str, str2, pendingIntent).getNotification();
        }
        return null;
    }

    public static Notification.Builder createNotificationBuilder(Context context, boolean z, int i2, String str, String str2, PendingIntent pendingIntent) {
        String str3 = z ? "lbNotify_Silent" : "lbNotify_Unsilent";
        if (context.getApplicationInfo().targetSdkVersion < 26 || Build.VERSION.SDK_INT < 26) {
            if (Build.VERSION.SDK_INT < 11) {
                return null;
            }
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(i2).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setOngoing(true).setAutoCancel(true);
            if (Build.VERSION.SDK_INT < 16 && Build.VERSION.SDK_INT < 11) {
                return null;
            }
            return builder;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager.getNotificationChannel(str3) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(str3, context.getPackageName() + ":lebian", 3);
            notificationChannel.enableVibration(z ^ true);
            notificationChannel.enableLights(z ^ true);
            if (z) {
                notificationChannel.setSound(null, null);
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Notification.Builder builder2 = new Notification.Builder(context, str3);
        builder2.setSmallIcon(i2).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setChannelId(str3).setAutoCancel(true);
        return builder2;
    }

    public static boolean deleteDir(File file) {
        String[] list;
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteDir(File file, String str) {
        String[] list;
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str2 : list) {
                File file2 = new File(file, str2);
                if ((!file2.isFile() || !file2.getName().equals(str)) && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth < 0 || intrinsicHeight < 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static String encryptByMD5(String str) {
        try {
            return byteArrayToHex(MessageDigest.getInstance("MD5").digest(str.getBytes())).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void finishActivitys() {
        for (Activity activity : getTopActivitys()) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "gactivity:" + activity, new Object[0]);
            activity.finish();
        }
    }

    public static Drawable getAppIcon(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = getPackageInfo(context, str);
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (Build.VERSION.SDK_INT >= 8) {
            applicationInfo.sourceDir = str;
            applicationInfo.publicSourceDir = str;
        }
        return packageManager.getApplicationIcon(applicationInfo);
    }

    public static String getBkDomain(Context context, int i2) {
        int iA = m.a(context, "SELECTED_AREA", 0);
        if (m.a(context, "USE_HTTP_OR_HTTPS", 0) == 0) {
            if (iA == 0) {
                return i2 > 0 ? "http://rcdnws.loveota.com/" : "http://rcdnal.loveota.com/";
            }
            return i2 > 0 ? "http://rcdnukd.loveota.net/" : "http://rcdnaws.loveota.net/";
        }
        if (iA == 0) {
            return i2 > 0 ? "https://cdnmrescn.smallpkg.com/" : "https://cdnbrescn.smallpkg.com/";
        }
        if (iA == 1) {
            return i2 > 0 ? "https://cdnmreshk.smallpkg.com/" : "https://cdnbreshk.smallpkg.com/";
        }
        if (iA == 2) {
            return i2 > 0 ? "https://cdnmrestw.smallpkg.com/" : "https://cdnbrestw.smallpkg.com/";
        }
        if (iA == 3) {
            return i2 > 0 ? "https://cdnmressg.smallpkg.com/" : "https://cdnbressg.smallpkg.com/";
        }
        return "http://rcdnws.loveota.com/";
    }

    public static String getCDNIP(String str) {
        InetAddress byName;
        try {
            byName = InetAddress.getByName(new URL(str).getHost());
        } catch (Exception e) {
            e.printStackTrace();
            byName = null;
        }
        if (byName == null) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "iAddress ==null", new Object[0]);
            return null;
        }
        String hostAddress = byName.getHostAddress();
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "cdn ipAddress is " + hostAddress, new Object[0]);
        return hostAddress;
    }

    public static String getCachePath(Context context) {
        return getCachePath(context, null);
    }

    public static String getCachePath(Context context, String str) {
        String str2 = getResRoot(context) + "cacheFiles/" + context.getPackageName() + "/";
        if (!new File(str2).exists() || str == null || str.equals(getIntance().getRunningVersionCode(false, true))) {
            str2 = null;
        }
        if (str2 != null) {
            return str2;
        }
        if (str == null || str == "-1") {
            str = getIntance().getRunningVersionCode(false, true);
        }
        return getResRoot(context) + "cacheFiles/" + context.getPackageName() + "-" + str + "/";
    }

    public static ClassLoader getClassLoader(Context context) {
        return context.getApplicationContext() != null ? context.getApplicationContext().getClassLoader() : context.getClassLoader();
    }

    public static String getDataRoot(Context context) {
        return context.getApplicationInfo().dataDir + "/lebian/";
    }

    public static long getDownloadedSize(Context context, String str) {
        long jB = ae.b(context, "lebian_module_dloadinfo", "downloaded_size_" + str, 0L);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "getDownloadedSize: vercode:" + str + " size:" + jB, new Object[0]);
        if (BwbxUtil.isBigAsset(context) || jB > 0 || Integer.valueOf(str).intValue() < 0) {
            return jB;
        }
        String cachePath = getCachePath(context, str);
        long jB2 = new com.excelliance.lbsdk.main.ac.a(context, new File(cachePath)).b();
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "getDownloadedSize: path:" + cachePath + " dirsize:" + jB2, new Object[0]);
        return jB2;
    }

    public static Signature[] getEntrySignatures(String str, String str2) {
        byte[] bArr = new byte[8192];
        try {
            JarFile jarFile = new JarFile(str);
            JarEntry jarEntry = jarFile.getJarEntry(str2);
            Certificate[] certificateArrA = a(jarFile, jarEntry, bArr);
            if (certificateArrA == null) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "Package " + str + " has no certificates at entry " + jarEntry.getName() + "; ignoring!", new Object[0]);
                jarFile.close();
                return null;
            }
            jarFile.close();
            if (certificateArrA == null || certificateArrA.length <= 0) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "Package " + str + " has no certificates; ignoring!", new Object[0]);
                return null;
            }
            int length = certificateArrA.length;
            Signature[] signatureArr = new Signature[certificateArrA.length];
            for (int i2 = 0; i2 < length; i2++) {
                signatureArr[i2] = new Signature(certificateArrA[i2].getEncoded());
            }
            return signatureArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getExternalDataPath(Context context) {
        String path = null;
        try {
            if (Build.VERSION.SDK_INT >= 11 && context.getExternalFilesDir(null) != null) {
                path = context.getExternalFilesDir(null).getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((path != null && path.length() > 0) || !Environment.getExternalStorageState().equals("mounted")) {
            return path;
        }
        StringBuffer stringBuffer = new StringBuffer(Environment.getExternalStorageDirectory().getPath());
        stringBuffer.append("/Android/data/");
        stringBuffer.append(context.getPackageName());
        stringBuffer.append("/files");
        return stringBuffer.toString();
    }

    public static int getFilesCount(File file) {
        return new com.excelliance.lbsdk.main.ac.a(LBSdkHelper.application().a(), file).a();
    }

    @SuppressLint({"NewApi"})
    public static long getFreeSpace(File file) {
        long blockSize;
        long availableBlocks;
        if (!file.exists()) {
            try {
                if (!file.isDirectory()) {
                    file = file.getParentFile();
                }
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!file.exists()) {
            return 0L;
        }
        StatFs statFs = new StatFs(file.getAbsolutePath());
        if (Build.VERSION.SDK_INT > 18) {
            blockSize = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        } else {
            blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getFree: ");
        long j2 = availableBlocks * blockSize;
        sb.append(j2);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", sb.toString(), new Object[0]);
        return j2;
    }

    @SuppressLint({"NewApi"})
    public static long getFreeSpace(String str) {
        long blockSize;
        long availableBlocks;
        File file = new File(str);
        if (!file.exists()) {
            try {
                if (!file.isDirectory()) {
                    str = file.getParent();
                    file = file.getParentFile();
                }
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!new File(str).exists()) {
            return 0L;
        }
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT > 18) {
            blockSize = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        } else {
            blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getFree: ");
        long j2 = availableBlocks * blockSize;
        sb.append(j2);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", sb.toString(), new Object[0]);
        return j2;
    }

    public static String getFullPkgMd5(Context context) {
        String remoteResPathZip = getRemoteResPathZip(context);
        if (remoteResPathZip == null || remoteResPathZip.length() <= 0) {
            return "";
        }
        String[] strArrSplit = remoteResPathZip.split("/");
        return strArrSplit[strArrSplit.length - 2];
    }

    public static String getGUID() {
        int iNextInt;
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < 16; i2++) {
            int iNextInt2 = secureRandom.nextInt(3);
            if (iNextInt2 != 0) {
                if (iNextInt2 == 1) {
                    iNextInt = secureRandom.nextInt(25) + 65;
                } else if (iNextInt2 == 2) {
                    iNextInt = secureRandom.nextInt(25) + 97;
                }
                sb.append((char) iNextInt);
            } else {
                sb.append(secureRandom.nextInt(10));
            }
        }
        return sb.toString();
    }

    public static GameUtil getIntance() {
        if (instance == null) {
            instance = new GameUtil();
        }
        return instance;
    }

    public static long getIntervalTime() {
        return i;
    }

    public static String getLbDeepLearnPath(Context context) {
        return getLbDeepLearnPath(context, null);
    }

    public static String getLbDeepLearnPath(Context context, String str) {
        String str2 = getResRoot(context) + "lbdeeplearn/" + context.getPackageName() + "/";
        if (!new File(str2).exists() || str == null || str.equals(getIntance().getRunningVersionCode(false, true))) {
            str2 = null;
        }
        if (str2 != null) {
            return str2;
        }
        if (str == null || str == "-1") {
            str = getIntance().getRunningVersionCode(false, true);
        }
        return getResRoot(context) + "lbdeeplearn/" + context.getPackageName() + "-" + str + "/";
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0070  */
    /* JADX WARN: Code duplicated, block: B:13:0x008a  */
    /* JADX WARN: Code duplicated, block: B:15:0x009a  */
    /* JADX WARN: Code duplicated, block: B:17:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:19:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:21:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:22:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:24:0x0103  */
    /* JADX WARN: Code duplicated, block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:11:0x0070, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:15:0x009a, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:19:0x00bf, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:21:0x00d9, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:22:0x00e9, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:24:0x0103, please report this as an issue */
    public static String getLocalDataPath(Context context) {
        String string;
        StringBuilder sb;
        StringBuilder sb2;
        String resRoot = getResRoot(context);
        String str = "/data/data/" + context.getPackageName() + "/";
        if (!new File(resRoot + "localData.png").exists()) {
            if (new File(str + "localData.png").exists()) {
                sb = new StringBuilder();
                sb.append(str);
            } else {
                string = null;
            }
            if (string == null) {
                if (new File(resRoot + "localData.png").exists()) {
                    sb2 = new StringBuilder();
                    sb2.append(resRoot);
                } else {
                    if (new File(str + "localData.png").exists()) {
                        sb2 = new StringBuilder();
                        sb2.append(str);
                    }
                }
                sb2.append("localData.png");
                string = sb2.toString();
            }
            if (string == null) {
                return string;
            }
            if (new File(resRoot + "localData.png").exists()) {
                return resRoot + "localData.png";
            }
            if (new File(str + "localData.png").exists()) {
                return string;
            }
            return str + "localData.png";
        }
        sb = new StringBuilder();
        sb.append(resRoot);
        sb.append("localData.png");
        string = sb.toString();
        if (string == null) {
            if (new File(resRoot + "localData.png").exists()) {
                sb2 = new StringBuilder();
                sb2.append(resRoot);
            } else {
                if (new File(str + "localData.png").exists()) {
                    sb2 = new StringBuilder();
                    sb2.append(str);
                }
            }
            sb2.append("localData.png");
            string = sb2.toString();
        }
        if (string == null) {
            return string;
        }
        if (new File(resRoot + "localData.png").exists()) {
            return resRoot + "localData.png";
        }
        if (new File(str + "localData.png").exists()) {
            return string;
        }
        return str + "localData.png";
    }

    public static String[] getMd5AndVecodeFromTryFlag(String str) {
        String[] strArr = {null, null};
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "path=" + line, new Object[0]);
            int iLastIndexOf = line.lastIndexOf("/", line.length() - 2);
            int iLastIndexOf2 = line.lastIndexOf("/", iLastIndexOf + (-1));
            int iLastIndexOf3 = line.lastIndexOf("/", iLastIndexOf2 + (-1));
            String strSubstring = line.substring(iLastIndexOf2 + 1, iLastIndexOf);
            String strSubstring2 = line.substring(iLastIndexOf3 + 1, iLastIndexOf2);
            strArr[0] = strSubstring;
            strArr[1] = strSubstring2;
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "no tryflag", e, new Object[0]);
        }
        return strArr;
    }

    public static long getMissingFileTime() {
        return h;
    }

    public static String getObbPath(Context context) {
        String path = "";
        try {
            if (Build.VERSION.SDK_INT >= 11 && context.getObbDir() != null) {
                path = context.getObbDir().getPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((path != null && path.length() > 0) || !Environment.getExternalStorageState().equals("mounted")) {
            return path;
        }
        StringBuffer stringBuffer = new StringBuffer(Environment.getExternalStorageDirectory().getPath());
        stringBuffer.append("/Android/obb/");
        stringBuffer.append(context.getPackageName());
        return stringBuffer.toString();
    }

    public static PackageInfo getPackageInfo(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPublicIP() throws Throwable {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL("http://ip.chinaz.com").openConnection()).getInputStream(), "UTF-8"));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line + "\r\n");
                    } else {
                        try {
                            break;
                        } catch (IOException unused) {
                        }
                    }
                } catch (Exception unused2) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            }
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>").matcher(sb.toString());
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (Exception unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getRemoteResPathZip(Context context) {
        String strE = an.a().e();
        String line = "";
        if (strE == null) {
            try {
                InputStream inputStreamC = e.c(context);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStreamC, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                bufferedReader.readLine();
                line = bufferedReader.readLine();
                inputStreamReader.close();
                inputStreamC.close();
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "path=" + line, new Object[0]);
                return line;
            } catch (IOException e) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "no tryflag", e, new Object[0]);
                return line;
            }
        }
        if (strE == null || !new File(strE).exists()) {
            return "";
        }
        try {
            ZipFile zipFile = new ZipFile(strE);
            ZipEntry entry = zipFile.getEntry("assets/tryflag");
            if (entry != null) {
                InputStreamReader inputStreamReader2 = new InputStreamReader(zipFile.getInputStream(entry), "UTF-8");
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                bufferedReader2.readLine();
                String line2 = bufferedReader2.readLine();
                try {
                    inputStreamReader2.close();
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "path1=" + line2, new Object[0]);
                    line = line2;
                } catch (IOException e2) {
                    e = e2;
                    line = line2;
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "no tryflag1", e, new Object[0]);
                    return line;
                }
            }
            zipFile.close();
            return line;
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static String getResPatchPath(Context context, int i2, boolean z) {
        StringBuffer stringBuffer = new StringBuffer(getResRoot(context));
        stringBuffer.append("lbdiff-vercode.zip".replace("vercode", String.valueOf(i2)) + ".dload");
        String string = stringBuffer.toString();
        return !z ? string.substring(0, string.lastIndexOf(".")) : string;
    }

    public static String getResRoot(Context context) {
        String str = y;
        if (str != null) {
            return str;
        }
        y = ae.b(context, "lebian_module_bwbxinfo", "resRoot", (String) null);
        String str2 = y;
        if (str2 != null) {
            return str2;
        }
        y = context.getApplicationInfo().dataDir + "/lebian/";
        ae.a(context, "lebian_module_bwbxinfo", "resRoot", y);
        return y;
    }

    public static int getShellVersion(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), F_NOTIFY_TYPE_AB_TEST).metaData.getInt("ShellVersion");
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("GameUtil", "getShellVersion e:", e, new Object[0]);
            return 0;
        }
    }

    public static Activity getTopActivity() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Activity activity = null;
            for (Object obj : ((Map) declaredField.get(objInvoke)).values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
                Field declaredField4 = cls2.getDeclaredField("stopped");
                declaredField4.setAccessible(true);
                if (!declaredField4.getBoolean(obj) && activity == null) {
                    Field declaredField5 = cls2.getDeclaredField("activity");
                    declaredField5.setAccessible(true);
                    activity = (Activity) declaredField5.get(obj);
                }
            }
            return activity;
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "getTopActivity", e, new Object[0]);
            return null;
        }
    }

    public static String getTopActivity(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ComponentName componentName;
        if (!haveTaskPermission(context) || (runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1)) == null || runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null) {
            return null;
        }
        return componentName.getClassName();
    }

    public static List<Activity> getTopActivitys() {
        ArrayList arrayList = new ArrayList();
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            for (Object obj : ((Map) declaredField.get(objInvoke)).values()) {
                Field declaredField2 = obj.getClass().getDeclaredField("activity");
                declaredField2.setAccessible(true);
                arrayList.add((Activity) declaredField2.get(obj));
            }
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "getTopActivitys", e, new Object[0]);
        }
        return arrayList;
    }

    public static String getTwLoadPath(Context context) {
        return getTwLoadPath(context, true);
    }

    public static String getTwLoadPath(Context context, boolean z) {
        StringBuffer stringBuffer = new StringBuffer(getResRoot(context));
        stringBuffer.append("twice_load.zip.dload");
        String string = stringBuffer.toString();
        return !z ? string.substring(0, string.lastIndexOf(".")) : string;
    }

    public static String getZipPath(Context context) {
        return getZipPath(context, true);
    }

    public static String getZipPath(Context context, boolean z) {
        StringBuffer stringBuffer = new StringBuffer(getResRoot(context));
        stringBuffer.append("lbres.zip.dload");
        String string = stringBuffer.toString();
        return !z ? string.substring(0, string.lastIndexOf(".")) : string;
    }

    public static boolean haveTaskPermission(Context context) {
        return a(context);
    }

    public static boolean isAppInstalled(Context context, String str) {
        List<ApplicationInfo> installedApplications = context.getApplicationContext().getPackageManager().getInstalledApplications(8192);
        ArrayList arrayList = new ArrayList();
        Iterator<ApplicationInfo> it = installedApplications.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().packageName);
        }
        return arrayList.contains(str);
    }

    public static boolean isBwbx(Context context) {
        return m.a(context, "USE_BWBX", false);
    }

    public static boolean isCacheChecking(Context context) {
        int i2 = isCacheChecking;
        if (i2 == -1) {
            return ae.b(context, "lebian_module_bwbxinfo", "isCacheChecking", false);
        }
        return i2 > 0;
    }

    public static boolean isInMainLand(Context context) {
        return m.a(context, "SELECTED_AREA", 0) == 0;
    }

    public static boolean isLejiaguShell(Context context) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 65536);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        return applicationInfo.className != null && applicationInfo.className.startsWith("com.tencent.StubShell.TxAppEntry");
    }

    public static boolean isMIUI() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            boolean z = (properties.getProperty("ro.miui.ui.version.code", null) == null && properties.getProperty("ro.miui.ui.version.name", null) == null && properties.getProperty("ro.miui.internal.storage", null) == null) ? false : true;
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "IMUI OS: " + z, new Object[0]);
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isServiceOpen(Context context, String str, String str2) {
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningServices(10000).iterator();
        while (it.hasNext()) {
            ComponentName componentName = it.next().service;
            if (TextUtils.equals(componentName.getClassName(), str) && TextUtils.equals(componentName.getPackageName(), str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSpecificDir(Context context) {
        int i2;
        String line;
        try {
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    i2 = 0;
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.processName.equals(context.getApplicationInfo().processName)) {
                    i2 = next.pid;
                    break;
                }
                i2 = 0;
                break;
            }
        } catch (Exception unused) {
        }
        try {
            File file = new File("/proc/" + i2 + "/maps");
            if (!file.exists()) {
                return false;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    if (line.endsWith("/data/data/com.qihoo360.mobilesafe/files/libavb.so.4x.so")) {
                        return true;
                    }
                }
            } while (!line.endsWith("/system/lib/libsechook.so"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void removeDownloadedSize(Context context, String str) {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "removeDownloadedSize: vercode:" + str, new Object[0]);
        ae.a(context, "lebian_module_dloadinfo", "downloaded_size_" + str);
    }

    public static void sendUpdateInfo(Context context, int i2) {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "sendUpdateInfo infoId:" + i2, new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.updateInfo");
        intent.setPackage(context.getPackageName());
        intent.putExtra("result", i2);
        context.sendBroadcast(intent);
    }

    public static void setDownloadedSize(Context context, String str, long j2) {
        ae.a(context, "lebian_module_dloadinfo", "downloaded_size_" + str, j2);
    }

    public static void setResRootData(Context context) {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "setResRootData enter", new Object[0]);
        y = context.getApplicationInfo().dataDir + "/lebian/";
        ae.a(context, "lebian_module_bwbxinfo", "resRoot", y);
    }

    public static void showNullViewNotification(BGService bGService) {
        Notification notificationCreateNotification = createNotification(bGService, true, 0, null, null, null);
        if (notificationCreateNotification == null) {
            return;
        }
        notificationCreateNotification.flags |= 32;
        notificationCreateNotification.defaults = 0;
        bGService.startForeground(99, notificationCreateNotification);
    }

    public static void startService(Intent intent, Context context) {
        new com.excelliance.lbsdk.main.c.b(new com.excelliance.lbsdk.main.c.a() { // from class: com.excelliance.lbsdk.main.GameUtil.6
            @Override // com.excelliance.lbsdk.main.c.a
            public boolean a(Context context2) {
                return al.b(context2);
            }
        }).a(context, intent);
    }

    public static void switchToDlFullresBg(Context context, int i2, Bundle bundle) throws Throwable {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "switchToDownloadFullresBg, zipType: " + i2, new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(context.getPackageName());
        intent.setComponent(new ComponentName(context.getPackageName(), BGService.class.getName()));
        intent.putExtra("dlsType", 2);
        intent.putExtra("dlsZipType", i2);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        w.a(context).a(intent);
        getIntance().clearChecked(i2);
    }

    public static void switchToDownloadFullRes(Context context) throws Throwable {
        switchToDownloadFullRes(context, 1);
    }

    public static void switchToDownloadFullRes(Context context, int i2) throws Throwable {
        switchToDownloadFullRes(context, i2, (Bundle) null);
    }

    public static void switchToDownloadFullRes(Context context, int i2, Bundle bundle) throws Throwable {
        switchToDownloadFullRes(context, i2, true, bundle);
    }

    public static void switchToDownloadFullRes(Context context, int i2, boolean z) throws Throwable {
        switchToDownloadFullRes(context, i2, z, null);
    }

    public static void switchToDownloadFullRes(Context context, int i2, boolean z, Bundle bundle) throws Throwable {
        Intent intent;
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "switchToDownloadFullRes, zipType: " + i2 + " start:" + z + " has bundle:" + bundle, new Object[0]);
        if (!ae.b(context, "lebian_module_dloadinfo", "success", false) || bundle == null || bundle.getLong("offset") <= 0) {
            if (bundle != null && bundle.containsKey(BwbxUtil.ACTION_SIZE)) {
                long j2 = bundle.getLong(BwbxUtil.ACTION_SIZE);
                if (getFreeSpace(new File(getResRoot(context))) < j2) {
                    intent = new Intent("com.excelliance.open.action.startPromptActivity");
                    intent.putExtra("dialogtype", 9);
                    intent.putExtra("requiredSize", j2);
                }
            }
            if (1 == i2) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                BwbxUtil.recomputeOffset(context, bundle);
            }
            Intent intent2 = new Intent("com.excelliance.open.action.startNextChapter");
            intent2.setPackage(context.getPackageName());
            intent2.setFlags(268468224);
            intent2.putExtra("gameRunning", true);
            intent2.putExtra("dlsType", 2);
            intent2.putExtra("dlsZipType", i2);
            if (bundle != null) {
                intent2.putExtras(bundle);
            }
            intent2.putExtra("extra.lbsdk.hostintent", true);
            w.a(context).b(intent2);
            String strA = n.a();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "switchToDownloadFullRes emulatorName=" + strA, new Object[0]);
            if ("MUMUEmulator".equals(strA)) {
                com.excelliance.lbsdk.main.ai.c.a(context);
            }
            if (z || (!(strA == null || "MUMUEmulator".equals(strA)) || Build.VERSION.SDK_INT > 31)) {
                getIntance().killGameProcesses(context);
            } else {
                Intent intent3 = new Intent(context.getPackageName() + BwbxUtil.ACTION_DOWNLOAD_CONTROL);
                intent3.setPackage(context.getPackageName());
                intent3.putExtra("control", true);
                context.sendBroadcast(intent3);
            }
            getIntance().clearChecked(i2);
            if (i2 != 3) {
                getIntance().clearResPatchInfo();
                return;
            }
            return;
        }
        intent = new Intent("com.excelliance.open.action.startPromptActivity");
        intent.putExtra("dialogtype", 100);
        intent.setFlags(805306368);
        intent.setPackage(context.getPackageName());
        context.startActivity(intent);
    }

    public static void toGP(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
            intent.setPackage("com.android.vending");
            if (intent.resolveActivity(context.getPackageManager()) == null) {
                intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName()));
                if (intent.resolveActivity(context.getPackageManager()) == null) {
                    return;
                }
            }
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            com.excelliance.lbsdk.main.g.b.e("prompt", "gp Intent not found", new Object[0]);
        }
    }

    public static int unzip(Context context, File file, String str, int i2) {
        try {
            return unzip(context, null, file, str, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int unzip(Context context, String str, File file, String str2, int i2) {
        long length = file.length();
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "unzip: size:" + length, new Object[0]);
        return length < 2147483648L ? aq.a(context, str, file, str2, i2) : ar.a(context, str, file, str2, i2);
    }

    public static void unzipForBigRes(Context context, File file, String str, int i2) {
        try {
            long length = file.length();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "unzip: size:" + length, new Object[0]);
            if (length < 2147483648L) {
                aq.a(context, file, str, i2);
            } else {
                ar.a(context, file, str, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unzipForBigResAndSingle(Context context, File file, String str, String str2, int i2) {
        try {
            long length = file.length();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "unzip: size:" + length, new Object[0]);
            if (length < 2147483648L) {
                aq.a(context, file, str, str2, i2);
            } else {
                ar.a(context, file, str, str2, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unzipForSplitZip(Context context, File file, String str, int i2, List<String> list) {
        try {
            ar.a(context, file, str, i2, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unzipForSplitZip(Context context, File file, String str, int i2, boolean z, long j2) {
        ar.a(context, file, str, i2, z, j2);
    }

    public static void writeZipStore(ZipOutputStream zipOutputStream, File file, String str) {
        CRC32 crc32 = new CRC32();
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            String str2 = str.length() == 0 ? "" : str + "/";
            for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                writeZipStore(zipOutputStream, fileArrListFiles[i2], str2 + fileArrListFiles[i2].getName());
            }
            return;
        }
        try {
            String absolutePath = file.getAbsolutePath();
            if (!com.excelliance.lbsdk.main.ae.c.e(absolutePath) && !absolutePath.contains("lebian_hu_list-")) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[(int) file.length()];
                fileInputStream.read(bArr, 0, (int) file.length());
                ZipEntry zipEntry = new ZipEntry(str);
                crc32.reset();
                crc32.update(bArr);
                zipEntry.setCrc(crc32.getValue());
                zipEntry.setSize(file.length());
                zipEntry.setTime(System.currentTimeMillis());
                zipOutputStream.putNextEntry(zipEntry);
                fileInputStream.close();
                zipOutputStream.write(bArr, 0, bArr.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(File[] fileArr, File file) throws IOException {
        zipFiles(fileArr, file, null);
    }

    public static void zipFiles(File[] fileArr, File file, String[] strArr) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1024));
        for (File file2 : fileArr) {
            a(file2, zipOutputStream, "");
        }
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                a(str, zipOutputStream, "");
            }
        }
        zipOutputStream.close();
    }

    public static void zipStore(String str, String str2) {
        a(str2, new File(str));
    }

    public boolean apkSigV2(String str) throws Throwable {
        boolean z = false;
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(str, "r");
                try {
                    z = a(randomAccessFile2, a(str, randomAccessFile2)) > 32;
                    randomAccessFile2.close();
                    randomAccessFile2.close();
                } catch (Exception unused) {
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return z;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    public void bwbxDownloadStatistics(int i2) {
        bwbxDownloadStatistics(i2, getRunningVersionCode(false, true));
    }

    public void bwbxDownloadStatistics(final int i2, final String str) {
        ak.a().a(new Thread("GameUtil:bwbxDownloadStatistics") { // from class: com.excelliance.lbsdk.main.GameUtil.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    an anVarA = an.a();
                    StringBuilder sb = new StringBuilder(GameUtil.this.getBaseUrl("s") + "sdkstatistics.php");
                    sb.append("?action=11");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("chid", GameUtil.this.getApkMainCh());
                    jSONObject.put("vercode", str);
                    jSONObject.put("cpmeta", GameUtil.this.getClientChId());
                    jSONObject.put("nettype", aa.c(GameUtil.this.a));
                    String strB = anVarA.b();
                    if (strB != null) {
                        jSONObject.put("uid", strB);
                    }
                    jSONObject.put("tp", i2);
                    sb.append("&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
                    if (aa.a(sb.toString(), GameUtil.this.a).a == 200 && i2 == 1) {
                        ae.a(GameUtil.this.a, "lebian_module_dloadinfo", "downloadStartStatistics", true);
                    }
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "The Type is " + i2, new Object[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0051  */
    /* JADX WARN: Type inference failed for: r1v19, types: [com.excelliance.lbsdk.main.GameUtil$3] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.excelliance.lbsdk.main.GameUtil$2] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.excelliance.lbsdk.main.GameUtil$1] */
    public void checkCache() throws Throwable {
        boolean z;
        final boolean z2;
        if (v.a()) {
            z = false;
        } else {
            long jB = ae.b(this.a, "lebian_module_base", "lastVersionSdkCheckCache", 0L);
            long jLastModified = new File(this.a.getPackageResourcePath()).lastModified();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "lastVersion:" + jB + ", currentVersion:" + jLastModified, new Object[0]);
            if (jLastModified != jB) {
                ae.a(this.a, "lebian_module_base", "lastVersionSdkCheckCache", jLastModified);
                clearResPatchInfo();
                z = true;
            } else {
                z = false;
            }
        }
        final String strE = an.a().e();
        final String strC = com.excelliance.lbsdk.main.ae.c.c(this.a);
        if (z || !ae.b(this.a, "lebian_module_bwbxinfo", "checkCacheInstalled", false)) {
            z2 = z;
        } else {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache install check not finished", new Object[0]);
            z2 = true;
        }
        final boolean z3 = ae.b(this.a, "lebian_module_bwbxinfo", "checkCache", false) && !(strC == null && strE == null);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache installed:" + z2 + " hasNew:" + z3, new Object[0]);
        updateResourceCfg(this.a);
        final String runningVersionCode = getRunningVersionCode(false, true);
        if (!z2 && !z3) {
            isCacheChecking = 0;
            boolean zB = ae.b(this.a, "lebian_module_bwbxinfo", "checkedAgain", false);
            boolean zB2 = ae.b(this.a, "lebian_module_bwbxinfo", "dlresPatch", false);
            if (!zB && !zB2) {
                new Thread("GameUtil:checkCache3") { // from class: com.excelliance.lbsdk.main.GameUtil.3
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        boolean zB3 = ae.b(GameUtil.this.a, "lebian_module_dloadinfo", "bwbxZipExtracting", false);
                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache extracting:" + zB3, new Object[0]);
                        String zipPath = GameUtil.getZipPath(GameUtil.this.a, false);
                        String twLoadPath = GameUtil.getTwLoadPath(GameUtil.this.a, false);
                        if (!zB3 || (!new File(zipPath).exists() && !new File(twLoadPath).exists())) {
                            ae.a(GameUtil.this.a, "lebian_module_bwbxinfo", "checkedAgain", true);
                            if (strC != null) {
                                GameUtil gameUtil = GameUtil.this;
                                gameUtil.extractFrameByPatchV4(gameUtil.a);
                            } else {
                                GameUtil gameUtil2 = GameUtil.this;
                                gameUtil2.extractFrame(gameUtil2.a.getPackageResourcePath(), GameUtil.getResRoot(GameUtil.this.a), "localData.png");
                            }
                        }
                        GameUtil gameUtil3 = GameUtil.this;
                        String lastVer = gameUtil3.getLastVer(gameUtil3.a);
                        if (!GameUtil.getCachePath(GameUtil.this.a, lastVer).contains("-" + runningVersionCode)) {
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "delete old dir res", new Object[0]);
                            GameUtil.deleteDir(new File(GameUtil.getCachePath(GameUtil.this.a, lastVer)));
                            GameUtil.removeDownloadedSize(GameUtil.this.a, lastVer);
                        }
                        if (!GameUtil.getLbDeepLearnPath(GameUtil.this.a, lastVer).contains("-" + runningVersionCode)) {
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "delete old dir deep learn", new Object[0]);
                            GameUtil.deleteDir(new File(GameUtil.getLbDeepLearnPath(GameUtil.this.a, lastVer)));
                        }
                        String str = Environment.getExternalStorageDirectory().getPath() + "/." + GameUtil.this.a.getPackageName() + "/cacheFiles/";
                        if (new File(str).exists()) {
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "delete old hidden dir res", new Object[0]);
                            GameUtil.deleteDir(new File(str));
                        }
                        String str2 = Environment.getExternalStorageDirectory().getPath() + "/Android/obb/" + GameUtil.this.a.getPackageName() + "/lebian/cacheFiles/";
                        if (new File(str2).exists()) {
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "delete old obb dir res", new Object[0]);
                            GameUtil.deleteDir(new File(str2));
                        }
                        if (new File(GameUtil.this.a.getApplicationInfo().dataDir + "/cacheFiles/").exists()) {
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "delete old data dir res", new Object[0]);
                            GameUtil.deleteDir(new File(str2));
                        }
                    }
                }.start();
            }
            if (ae.b(this.a, "lebian_module_bwbxinfo", "dlresPatch", false)) {
                Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
                intent.setPackage(this.a.getPackageName());
                intent.setComponent(new ComponentName(this.a.getPackageName(), BGService.class.getName()));
                intent.putExtra("dlsType", 2);
                intent.putExtra("resPatchCheck", true);
                intent.putExtra("extra.lbsdk.hostintent", true);
                w.a(this.a).a(intent);
                return;
            }
            return;
        }
        String localDataPath = getLocalDataPath(this.a);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache localDataPath:" + localDataPath, new Object[0]);
        ae.b(this.a, "lebian_module_domain");
        ae.a(this.a, "lebian_module_bwbxinfo", "checkedAgain");
        if (localDataPath == null) {
            new Thread("GameUtil:checkCache2") { // from class: com.excelliance.lbsdk.main.GameUtil.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    String packageResourcePath;
                    if (strC != null) {
                        GameUtil gameUtil = GameUtil.this;
                        gameUtil.extractFrameByPatchV4(gameUtil.a);
                    } else {
                        GameUtil gameUtil2 = GameUtil.this;
                        if (z2 || (packageResourcePath = strE) == null) {
                            packageResourcePath = GameUtil.this.a.getPackageResourcePath();
                        }
                        gameUtil2.extractFrame(packageResourcePath, GameUtil.getResRoot(GameUtil.this.a), "localData.png");
                    }
                    GameUtil gameUtil3 = GameUtil.this;
                    String lastVer = gameUtil3.getLastVer(gameUtil3.a);
                    if (!GameUtil.getCachePath(GameUtil.this.a, lastVer).contains("-" + runningVersionCode)) {
                        GameUtil.deleteDir(new File(GameUtil.getCachePath(GameUtil.this.a, lastVer)));
                        GameUtil.removeDownloadedSize(GameUtil.this.a, lastVer);
                    }
                    ae.a(GameUtil.this.a, "lebian_module_bwbxinfo", "checkCache");
                    ae.a(GameUtil.this.a, "lebian_module_bwbxinfo", "checkCacheInstalled");
                    GameUtil.this.clearResPatchInfo();
                }
            }.start();
            return;
        }
        if (!z3) {
            ae.a(this.a, "lebian_module_bwbxinfo", "checkCacheInstalled", true);
        }
        BwbxUtil.setUseCache(this.a, false);
        isCacheChecking = 1;
        ae.a(this.a, "lebian_module_bwbxinfo", "isCacheChecking", true);
        new Thread("GameUtil:checkCache1") { // from class: com.excelliance.lbsdk.main.GameUtil.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                com.excelliance.lbsdk.main.q.b bVar = new com.excelliance.lbsdk.main.q.b(GameUtil.this.a);
                if (z3 || !z2 || ae.b(GameUtil.this.a, "lebian_module_bwbxinfo", "resPatchVer", "-1").equals(runningVersionCode) || new File(GameUtil.getResPatchPath(GameUtil.this.a, Integer.parseInt(runningVersionCode), false)).exists()) {
                    GameUtil.this.clearResPatchInfo();
                } else if (!bVar.b()) {
                    GameUtil.this.b();
                }
                GameUtil gameUtil = GameUtil.this;
                gameUtil.clearDiffZip(gameUtil.a);
                String packageResourcePath = GameUtil.this.a.getPackageResourcePath();
                if (z3 && strC == null) {
                    Context context = GameUtil.this.a;
                    GameUtil gameUtil2 = GameUtil.this;
                    com.excelliance.lbsdk.main.bwbx.compat.a.a(context, gameUtil2.getLastVer(gameUtil2.a));
                    GameUtil.this.checkCacheMd5(strE);
                    String str = strE;
                    if (str != null) {
                        packageResourcePath = str;
                    }
                } else {
                    Context context2 = GameUtil.this.a;
                    GameUtil gameUtil3 = GameUtil.this;
                    com.excelliance.lbsdk.main.bwbx.compat.a.a(context2, gameUtil3.getLastVer(gameUtil3.a));
                    GameUtil.this.checkCacheMd5(null);
                }
                bVar.c();
                BwbxUtil.setUseCache(GameUtil.this.a, true);
                GameUtil.isCacheChecking = 0;
                ae.a(GameUtil.this.a, "lebian_module_bwbxinfo", "isCacheChecking");
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "apk:" + packageResourcePath, new Object[0]);
                if (strC != null) {
                    GameUtil gameUtil4 = GameUtil.this;
                    gameUtil4.extractFrameByPatchV4(gameUtil4.a);
                } else {
                    GameUtil gameUtil5 = GameUtil.this;
                    gameUtil5.extractFrame(packageResourcePath, GameUtil.getResRoot(gameUtil5.a), "localData.png");
                }
                ae.a(GameUtil.this.a, "lebian_module_bwbxinfo", "checkCache");
                ae.a(GameUtil.this.a, "lebian_module_bwbxinfo", "checkCacheInstalled");
            }
        }.start();
    }

    public void checkCache(boolean z) {
    }

    public void checkCacheMd5(String str) {
        InputStream inputStreamA;
        InputStream inputStreamA2;
        String line;
        String line2;
        String str2;
        String strSubstring;
        String strSubstring2;
        String strSubstring3;
        String strSubstring4;
        String localDataPath = getLocalDataPath(this.a);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCacheMd5 enter localDataPath:" + getLocalDataPath(this.a), new Object[0]);
        String lastVer = getLastVer(this.a);
        String runningVersionCode = getRunningVersionCode(false, true);
        if (localDataPath == null) {
            if (getCachePath(this.a, lastVer).contains("-" + runningVersionCode)) {
                return;
            }
            deleteDir(new File(getCachePath(this.a, lastVer)));
            removeDownloadedSize(this.a, lastVer);
            return;
        }
        try {
            TreeMap treeMap = new TreeMap();
            TreeMap treeMap2 = new TreeMap();
            if (str == null) {
                inputStreamA = e.a(this.a);
                inputStreamA2 = e.a(this.a);
            } else {
                ZipFile zipFile = new ZipFile(str);
                ZipEntry entry = zipFile.getEntry("assets/localData.png");
                if (entry != null) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(entry));
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(zipFile.getInputStream(entry));
                    inputStreamA = bufferedInputStream;
                    inputStreamA2 = bufferedInputStream2;
                } else {
                    inputStreamA = null;
                    inputStreamA2 = null;
                }
            }
            if (inputStreamA == null) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "check no localData", new Object[0]);
                return;
            }
            if (computeFileMd5(localDataPath).equals(computeInputStreamMd5(inputStreamA2))) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCacheMd5 same version", new Object[0]);
            }
            boolean zB = ae.b(this.a, "lebian_module_dloadinfo", "bwbxZipExtracting", false);
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCacheMd5 extracting:" + zB, new Object[0]);
            String zipPath = getZipPath(this.a, false);
            String twLoadPath = getTwLoadPath(this.a, false);
            if (zB && (new File(zipPath).exists() || new File(twLoadPath).exists())) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "remove lbres", new Object[0]);
                new File(zipPath).delete();
                new File(twLoadPath).delete();
                ae.a(this.a, "lebian_module_dloadinfo", "bwbxZipExtracting");
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArrA = f.a();
            int length = bArrA.length;
            byte[] bArr = new byte[1024];
            inputStreamA.skip(261L);
            int i2 = 0;
            while (true) {
                int i3 = inputStreamA.read(bArr);
                if (i3 == -1) {
                    break;
                }
                int i4 = 0;
                while (i4 < i3) {
                    bArr[i4] = (byte) (bArr[i4] ^ bArrA[i2 % length]);
                    i4++;
                    i2++;
                }
                byteArrayOutputStream.write(bArr, 0, i3);
            }
            byteArrayOutputStream.flush();
            inputStreamA.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    int length2 = line.length();
                    int iIndexOf = line.indexOf(32);
                    if (iIndexOf != 32 || iIndexOf == length2 - 1) {
                        strSubstring3 = line;
                        strSubstring4 = null;
                    } else {
                        strSubstring3 = line.substring(iIndexOf + 1);
                        strSubstring4 = line.substring(0, iIndexOf);
                    }
                    treeMap.put(strSubstring3, strSubstring4);
                }
            } while (line != null);
            bufferedReader.close();
            byteArrayOutputStream.close();
            BufferedInputStream bufferedInputStream3 = new BufferedInputStream(new FileInputStream(localDataPath));
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            bufferedInputStream3.skip(261L);
            int i5 = 0;
            while (true) {
                int i6 = bufferedInputStream3.read(bArr);
                if (i6 == -1) {
                    break;
                }
                int i7 = i5;
                int i8 = 0;
                while (i8 < i6) {
                    bArr[i8] = (byte) (bArr[i8] ^ bArrA[i7 % length]);
                    i8++;
                    i7++;
                }
                byteArrayOutputStream2.write(bArr, 0, i6);
                i5 = i7;
            }
            byteArrayOutputStream2.flush();
            bufferedInputStream3.close();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream2.toByteArray())));
            do {
                line2 = bufferedReader2.readLine();
                if (line2 != null) {
                    int length3 = line2.length();
                    int iIndexOf2 = line2.indexOf(32);
                    if (iIndexOf2 != 32 || iIndexOf2 == length3 - 1) {
                        strSubstring = line2;
                        strSubstring2 = null;
                    } else {
                        strSubstring = line2.substring(iIndexOf2 + 1);
                        strSubstring2 = line2.substring(0, iIndexOf2);
                    }
                    treeMap2.put(strSubstring, strSubstring2);
                }
            } while (line2 != null);
            bufferedReader2.close();
            byteArrayOutputStream2.close();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "fileSet1 size:" + treeMap.size() + " fileSet2 size:" + treeMap2.size(), new Object[0]);
            if (!treeMap.isEmpty() && !treeMap2.isEmpty()) {
                String cachePath = getCachePath(this.a, lastVer);
                String cachePath2 = getCachePath(this.a, runningVersionCode);
                boolean zB2 = ae.b(this.a, "lebian_module_bwbxinfo", "dlresPatch", false);
                String strB = ae.b(this.a, "lebian_module_bwbxinfo", "resPatchVer", "-1");
                if (BwbxUtil.isBigAsset(this.a)) {
                    Thread.sleep(1000L);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache big asset start", new Object[0]);
                    synchronized (checkCacheSync) {
                        boolean zB3 = ae.b(this.a, "lebian_module_dloadinfo", "success", true);
                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache big asset success:" + zB3, new Object[0]);
                        if (zB3) {
                            String str3 = cachePath + "assets/lbres";
                            String str4 = cachePath2 + "assets/lbres";
                            File file = new File(str3, "lbres.rdata.meta");
                            File file2 = new File(str4, "lbres.rdata.meta");
                            File file3 = new File(str3, "lbres.rdata.mp4.idx.left");
                            str2 = strB;
                            File file4 = new File(str4, "lbres.rdata.mp4.idx.left");
                            String strComputeFileMd5 = BwbxUtil.computeFileMd5(file.getAbsolutePath());
                            String strComputeFileMd6 = BwbxUtil.computeFileMd5(file2.getAbsolutePath());
                            String strComputeFileMd7 = BwbxUtil.computeFileMd5(file3.getAbsolutePath());
                            String strComputeFileMd8 = BwbxUtil.computeFileMd5(file4.getAbsolutePath());
                            if (strComputeFileMd5.equals(strComputeFileMd6) && strComputeFileMd7.equals(strComputeFileMd8)) {
                                File file5 = new File(str4, "lbres.rdata.mp4.idx");
                                File file6 = new File(str4, "lbres.rdata.mp4");
                                if (!file6.getParentFile().exists()) {
                                    file6.getParentFile().mkdirs();
                                }
                                File file7 = new File(str3, "lbres.rdata.mp4");
                                File file8 = new File(str3, "lbres.rdata.mp4.idx");
                                if (file6.exists()) {
                                    file6.delete();
                                }
                                if (file5.exists()) {
                                    file5.delete();
                                }
                                com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache big asset same mp4 rename success:" + file7.renameTo(file6), new Object[0]);
                                com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCache big asset same idx rename success:" + file8.renameTo(file5), new Object[0]);
                                com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCacheMd5: new idx:" + file5.exists(), new Object[0]);
                            }
                        } else {
                            str2 = strB;
                        }
                    }
                } else {
                    str2 = strB;
                }
                if (this.l) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "isNeedCheckResPath: " + this.l, new Object[0]);
                    Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
                    intent.setPackage(this.a.getPackageName());
                    intent.setComponent(new ComponentName(this.a.getPackageName(), BGService.class.getName()));
                    intent.putExtra("dlsType", 2);
                    intent.putExtra("resPatchCheck", true);
                    intent.putExtra("extra.lbsdk.hostintent", true);
                    w.a(this.a).a(intent);
                    return;
                }
                boolean z = false;
                for (Map.Entry entry2 : treeMap2.entrySet()) {
                    synchronized (checkCacheSync) {
                        if (checkedList.contains(entry2.getKey())) {
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "has checked:" + ((String) entry2.getKey()), new Object[0]);
                        } else {
                            checkingFile = (String) entry2.getKey();
                            String str5 = cachePath + ((String) entry2.getKey());
                            if (new File(str5).exists()) {
                                if (treeMap.containsKey(entry2.getKey())) {
                                    if (((String) treeMap.get(entry2.getKey())).equals((String) entry2.getValue())) {
                                        File file9 = new File(cachePath2 + ((String) entry2.getKey()));
                                        if (!file9.exists()) {
                                            file9.getParentFile().mkdirs();
                                            new File(str5).renameTo(file9);
                                        }
                                    } else {
                                        if (!zB2) {
                                            new File(str5).delete();
                                        }
                                        if (!new File(cachePath2 + ((String) entry2.getKey())).exists()) {
                                            z = true;
                                        }
                                    }
                                } else {
                                    new File(str5).delete();
                                }
                            }
                            synchronized (checkCacheSync) {
                                checkCacheSync.notify();
                            }
                        }
                    }
                }
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkCacheMd5: " + cachePath + " ver" + runningVersionCode + " last" + lastVer, new Object[0]);
                if (!cachePath.contains("-" + runningVersionCode) && !zB2) {
                    deleteDir(new File(cachePath));
                    removeDownloadedSize(this.a, lastVer);
                }
                boolean zExtractZipRes = extractZipRes();
                checkingFile = null;
                if (z && ((!str2.equals(runningVersionCode) || !zExtractZipRes) && ae.b(this.a, "lebian_module_dloadinfo", "success", false))) {
                    ae.a(this.a, "lebian_module_dloadinfo", "success", false);
                }
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "check end", new Object[0]);
            }
        } catch (Exception e) {
            synchronized (checkCacheSync) {
                checkCacheSync.notify();
                e.printStackTrace();
            }
        }
    }

    public boolean checkForceSdk(Context context) {
        if (this.d == null) {
            this.d = context.getApplicationInfo().dataDir;
        }
        if (this.e == null) {
            this.e = this.d + "/.lebiansdk/";
        }
        String str = this.d + "/.lebiansdk/newver/";
        if (ae.b(context, "lebian_module_base", "mainflag", 0) == 1) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive main update", new Object[0]);
            ae.a(context, "lebian_module_base", "mainflag");
            return true;
        }
        if (ae.b(context, "lebian_module_base", "vmflag", 0) == 1) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive vm update", new Object[0]);
            ae.a(context, "lebian_module_base", "vmflag");
            return true;
        }
        if (ae.b(context, "lebian_module_base", "uiflag", 0) == 1) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive ui update", new Object[0]);
            ae.a(context, "lebian_module_base", "uiflag");
            return true;
        }
        if (ae.b(context, "lebian_module_base", "mustflag", 0) == 1) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive must update", new Object[0]);
            ae.a(context, "lebian_module_base", "mustflag");
            return true;
        }
        if (an.a().c()) {
            int iB = ae.b(context, "lebian_module_base", "v2mainflag", 0);
            if (new File(str + "new/main", MAIN_JAR).exists() && iB == 1) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive vm main update", new Object[0]);
                c();
                return true;
            }
            int iB2 = ae.b(context, "lebian_module_base", "v2uiflag", 0);
            if (new File(str + "new/ui", UI_JAR).exists() && iB2 == 1) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive vm ui update", new Object[0]);
                c();
                return true;
            }
            int iB3 = ae.b(context, "lebian_module_base", "v2mustflag", 0);
            if (new File(str + "new/must", MUST_JAR).exists() && iB3 == 1) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "receive vm must update", new Object[0]);
                c();
                return true;
            }
        }
        return false;
    }

    public boolean checkJJ(Context context) {
        if (context == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "cn.jj.combplatlib.activity.JJCombPlatLibLoginActivity"), F_NOTIFY_TYPE_AB_TEST);
            return "true".equals(activityInfo != null ? String.valueOf(activityInfo.metaData.get("lbvm_hu_entry")) : "false");
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public boolean checkObbMd5(String str, String str2) {
        String str3 = "saveObbMd5Key" + str2;
        String strB = ae.b(this.a, "lebian_module_md5info", getIntance().computeInputStreamMd5(new ByteArrayInputStream(str3.getBytes())), (String) null);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkObbMd5 check :" + str + " fileInfo:" + str3, new Object[0]);
        return str != null && (str.equals(strB) || str.equals(str2));
    }

    public boolean checkProviders(boolean z) {
        String packageName = this.a.getPackageName();
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packageInfo == null || packageInfo.providers == null) {
            return false;
        }
        int i2 = z ? 2 : 0;
        boolean z2 = false;
        for (ProviderInfo providerInfo : packageInfo.providers) {
            ComponentName componentName = new ComponentName(packageName, providerInfo.name);
            int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "checkProviders name=" + providerInfo.name + ", expected=" + i2 + ", old=" + componentEnabledSetting, new Object[0]);
            if (i2 != componentEnabledSetting) {
                packageManager.setComponentEnabledSetting(componentName, z ? 2 : 0, 1);
                int componentEnabledSetting2 = packageManager.getComponentEnabledSetting(componentName);
                StringBuilder sb = new StringBuilder();
                sb.append("old=");
                sb.append(componentEnabledSetting);
                sb.append(", set=");
                sb.append(z ? 2 : 0);
                sb.append(", newflag=");
                sb.append(componentEnabledSetting2);
                sb.append(", COMPONENT_ENABLED_STATE_DISABLED=");
                sb.append(2);
                sb.append(", COMPONENT_ENABLED_STATE_DEFAULT=");
                sb.append(0);
                com.excelliance.lbsdk.main.g.b.c("GameUtil", sb.toString(), new Object[0]);
                z2 = true;
            }
        }
        ae.a(this.a, "lebian_module_gameinfo", "pro_disabled", z);
        return z2;
    }

    public void cleanExit() {
        killThirdProcesses();
        try {
            al.a(this.a, Process.myPid());
            Process.killProcess(Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearChecked() throws Throwable {
        clearChecked(1);
    }

    public void clearChecked(int i2) throws Throwable {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "clearChecked", new Object[0]);
        ae.a(this.a, "lebian_module_bwbxinfo", "isCacheChecking");
        ae.a(this.a, "lebian_module_bwbxinfo", "checkCache");
        if (i2 != 3) {
            ae.a(this.a, "lebian_module_bwbxinfo", "checkCacheInstalled");
        }
        updateResourceCfg(this.a);
        String packageResourcePath = this.a.getPackageResourcePath();
        String strE = an.a().e();
        String strC = null;
        if (strE != null) {
            packageResourcePath = strE;
        } else {
            strC = com.excelliance.lbsdk.main.ae.c.c(this.a);
        }
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "clearChecked apk:" + packageResourcePath, new Object[0]);
        if (i2 != 3) {
            if (strC != null) {
                extractFrameByPatchV4(this.a);
            } else {
                extractFrame(packageResourcePath, getResRoot(this.a), "localData.png");
            }
        }
    }

    public void clearDiffZip(Context context) {
        String runningVersionCode = getRunningVersionCode(false, true);
        ArrayList arrayList = new ArrayList();
        arrayList.add(getExternalDataPath(context));
        arrayList.add(context.getApplicationInfo().dataDir);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File[] fileArrListFiles = new File((String) it.next()).listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    String name = file.getName();
                    if (name.startsWith("lbdiff-") && Integer.valueOf(name.substring(7, name.lastIndexOf(".zip"))).intValue() < Integer.valueOf(runningVersionCode).intValue()) {
                        file.delete();
                    }
                }
            }
        }
    }

    public void clearResPatchInfo() {
        ae.a(this.a, "lebian_module_bwbxinfo", "dlresPatch");
        ae.a(this.a, "lebian_module_bwbxinfo", "resEnough");
        String resPatchPath = getResPatchPath(this.a, Integer.parseInt(getRunningVersionCode(false, true)), true);
        if (resPatchPath != null) {
            new File(resPatchPath).delete();
        }
    }

    public boolean computeDirMd5(File file, FileWriter fileWriter, BufferedWriter bufferedWriter) {
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list == null) {
                return true;
            }
            for (String str : list) {
                if (!computeDirMd5(new File(file, str), fileWriter, bufferedWriter)) {
                    return false;
                }
            }
            return true;
        }
        try {
            String path = file.getPath();
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "computeDirMd5 file:" + path, new Object[0]);
            String str2 = "temp/" + this.a.getPackageName() + "/";
            String str3 = computeFileMd5(path, false) + " " + path.substring(path.indexOf(str2) + str2.length());
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "computeDirMd5 result:" + str3, new Object[0]);
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter);
            bufferedWriter2.write(str3);
            bufferedWriter2.newLine();
            bufferedWriter2.flush();
            return true;
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("GameUtil", "computeDirMd5 fail", e, new Object[0]);
            e.printStackTrace();
            return false;
        }
    }

    public long computeFileLen(String str) {
        long length;
        int iIndexOf;
        String inapkInfo = getInapkInfo(str);
        if (inapkInfo == null || inapkInfo.length() <= 0 || (iIndexOf = inapkInfo.indexOf(32)) <= 0) {
            length = -1;
        } else {
            try {
                String strSubstring = inapkInfo.substring(0, iIndexOf);
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "longInfo=" + strSubstring, new Object[0]);
                length = new Long(strSubstring).longValue();
            } catch (Exception e) {
                e.printStackTrace();
                length = -1;
            }
        }
        if (length <= 0) {
            length = new File(str).length();
        }
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "len=" + length, new Object[0]);
        return length;
    }

    public String computeFileMd5(String str) {
        return computeFileMd5(str, true);
    }

    public String computeFileMd5(String str, boolean z) {
        return computeFileMd5(str, z, false);
    }

    public String computeFileMd5(String str, boolean z, boolean z2) {
        return computeFileMd5(str, z, z2, null);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01fb A[EDGE_INSN: B:101:0x01fb->B:73:0x01fb BREAK  A[LOOP:0: B:65:0x01d1->B:72:0x01f6], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:103:0x01f6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x01db A[Catch: Exception -> 0x0236, TryCatch #1 {Exception -> 0x0236, blocks: (B:57:0x0169, B:59:0x017a, B:64:0x01b9, B:65:0x01d1, B:67:0x01db, B:70:0x01f1, B:73:0x01fb), top: B:98:0x0169 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x01ef A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x01f1 A[Catch: Exception -> 0x0236, TryCatch #1 {Exception -> 0x0236, blocks: (B:57:0x0169, B:59:0x017a, B:64:0x01b9, B:65:0x01d1, B:67:0x01db, B:70:0x01f1, B:73:0x01fb), top: B:98:0x0169 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x020e A[Catch: Exception -> 0x0233, TryCatch #2 {Exception -> 0x0233, blocks: (B:74:0x0208, B:76:0x020e, B:78:0x021a, B:82:0x0226), top: B:100:0x0208 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x021a A[Catch: Exception -> 0x0233, TryCatch #2 {Exception -> 0x0233, blocks: (B:74:0x0208, B:76:0x020e, B:78:0x021a, B:82:0x0226), top: B:100:0x0208 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0221  */
    public String computeFileMd5(String str, boolean z, boolean z2, a aVar) throws Throwable {
        String strA;
        String str2;
        String str3;
        boolean z3;
        int i2;
        String str4;
        String str5;
        String strByteArrayToHex;
        String realMd5;
        long j2;
        long jCurrentTimeMillis;
        int iIndexOf;
        String str6;
        if (str == null || str.length() == 0) {
            return null;
        }
        File file = new File(str);
        boolean zEndsWith = file.getName().endsWith(".obb");
        if (!file.exists()) {
            return null;
        }
        String str7 = str + String.valueOf(file.lastModified());
        String strComputeInputStreamMd5 = computeInputStreamMd5(new ByteArrayInputStream(str7.getBytes()));
        String strComputeInputStreamMd6 = computeInputStreamMd5(new ByteArrayInputStream(("real" + str7).getBytes()));
        String strComputeInputStreamMd7 = computeInputStreamMd5(new ByteArrayInputStream(("v2" + str7).getBytes()));
        boolean zApkSigV2 = zEndsWith ? false : apkSigV2(str);
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("GameUtil", "fileInfo=" + str7 + " key=" + strComputeInputStreamMd5, new Object[0]);
        }
        String str8 = "lebian_module_md5info";
        if (strComputeInputStreamMd5 == null || strComputeInputStreamMd6 == null) {
            strA = null;
        } else {
            Context context = this.a;
            if (z) {
                str6 = strComputeInputStreamMd5;
            } else {
                str6 = zApkSigV2 ? strComputeInputStreamMd7 : strComputeInputStreamMd6;
            }
            strA = ae.b(context, "lebian_module_md5info", str6, (String) null);
            if (strA != null) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "saved=" + strA, new Object[0]);
                return strA;
            }
        }
        String inapkInfo = zEndsWith ? null : getInapkInfo(str);
        if (inapkInfo != null && inapkInfo.length() > 0 && (iIndexOf = inapkInfo.indexOf(32)) > 0) {
            strA = inapkInfo.substring(iIndexOf + 1);
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("GameUtil", "md5=" + strA, new Object[0]);
            }
            if (z2) {
                ae.a(this.a, "lebian_module_md5info", strComputeInputStreamMd5, strA);
            }
            if (z) {
                return strA;
            }
        }
        if (zApkSigV2) {
            strA = com.excelliance.lbsdk.main.y.d.a(file);
            if (strA != null) {
                com.excelliance.lbsdk.main.g.b.b("GameUtil", "v2 md5=" + strA, new Object[0]);
                if (z2) {
                    ae.a(this.a, "lebian_module_md5info", strComputeInputStreamMd5, strA);
                    ae.a(this.a, "lebian_module_md5info", strComputeInputStreamMd7, strA);
                }
                return strA;
            }
            zApkSigV2 = false;
        }
        byte[] bArr = new byte[j];
        try {
            long length = file.length();
            long j3 = 0;
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            try {
                com.excelliance.lbsdk.main.x.b bVar = new com.excelliance.lbsdk.main.x.b(file);
                try {
                    if (!str.equals(this.a.getPackageResourcePath())) {
                        StringBuilder sb = new StringBuilder();
                        z3 = zApkSigV2;
                        sb.append("main.");
                        str3 = inapkInfo;
                        sb.append(getVerCodeFromApk(this.a.getPackageCodePath(), true, true));
                        sb.append(".");
                        sb.append(this.a.getPackageName());
                        sb.append(".obb");
                        if (str.endsWith(sb.toString())) {
                        }
                        while (true) {
                            i2 = bVar.read(bArr, 0, j);
                            if (i2 != -1) {
                                break;
                            }
                            String str9 = strComputeInputStreamMd5;
                            String str10 = str8;
                            j2 = j3 + ((long) i2);
                            messageDigest.update(bArr, 0, i2);
                            jCurrentTimeMillis = System.currentTimeMillis();
                            if (jCurrentTimeMillis - jCurrentTimeMillis2 <= 1000) {
                                if (aVar != null) {
                                    aVar.a(length, j2);
                                }
                                jCurrentTimeMillis2 = jCurrentTimeMillis;
                            }
                            j3 = j2;
                            str8 = str10;
                            strComputeInputStreamMd5 = str9;
                            e.printStackTrace();
                            return str2;
                        }
                        str4 = strComputeInputStreamMd5;
                        str5 = str8;
                        bVar.close();
                        strByteArrayToHex = byteArrayToHex(messageDigest.digest());
                        realMd5 = getRealMd5(strByteArrayToHex, str);
                        if (z2) {
                            ae.a(this.a, str5, strComputeInputStreamMd6, strByteArrayToHex);
                            ae.a(this.a, str5, strComputeInputStreamMd7, strByteArrayToHex);
                            if (realMd5 != null) {
                                ae.a(this.a, str5, str4, realMd5);
                            } else if (str3 == null && !z3) {
                                ae.a(this.a, str5, str4, strByteArrayToHex);
                            }
                        }
                        return (z || realMd5 == null) ? strByteArrayToHex : realMd5;
                    }
                    str3 = inapkInfo;
                    z3 = zApkSigV2;
                    realMd5 = getRealMd5(strByteArrayToHex, str);
                    if (z2) {
                        ae.a(this.a, str5, strComputeInputStreamMd6, strByteArrayToHex);
                        ae.a(this.a, str5, strComputeInputStreamMd7, strByteArrayToHex);
                        if (realMd5 != null) {
                            ae.a(this.a, str5, str4, realMd5);
                        } else if (str3 == null) {
                            ae.a(this.a, str5, str4, strByteArrayToHex);
                        }
                    }
                    if (z) {
                    }
                } catch (Exception e) {
                    e = e;
                    str2 = strByteArrayToHex;
                }
                com.excelliance.lbsdk.main.x.a computeMd5SkipSize = getComputeMd5SkipSize(str);
                bVar.skip(computeMd5SkipSize.a());
                bVar.a(computeMd5SkipSize.b());
                bVar.a(2);
                while (true) {
                    i2 = bVar.read(bArr, 0, j);
                    if (i2 != -1) {
                        break;
                        break;
                    }
                    String str11 = strComputeInputStreamMd5;
                    String str12 = str8;
                    j2 = j3 + ((long) i2);
                    messageDigest.update(bArr, 0, i2);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jCurrentTimeMillis2 <= 1000) {
                        if (aVar != null) {
                            aVar.a(length, j2);
                        }
                        jCurrentTimeMillis2 = jCurrentTimeMillis;
                    }
                    j3 = j2;
                    str8 = str12;
                    strComputeInputStreamMd5 = str11;
                    e.printStackTrace();
                    return str2;
                }
                str4 = strComputeInputStreamMd5;
                str5 = str8;
                bVar.close();
                strByteArrayToHex = byteArrayToHex(messageDigest.digest());
            } catch (Exception e2) {
                e = e2;
                str2 = strA;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public String computeInputStreamMd5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[j];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            while (true) {
                int i2 = inputStream.read(bArr, 0, j);
                if (i2 <= 0) {
                    inputStream.close();
                    return byteArrayToHex(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void copyFile(File file, File file2) throws IOException {
        if (file.getPath().equals(file2.getPath())) {
            return;
        }
        file2.getParentFile().mkdirs();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] bArr = new byte[8192];
        while (true) {
            int i2 = bufferedInputStream.read(bArr);
            if (i2 == -1) {
                bufferedInputStream.close();
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                return;
            }
            bufferedOutputStream.write(bArr, 0, i2);
        }
    }

    public void delOldObb() {
        String packageName = this.a.getPackageName();
        File[] fileArrListFiles = new File(getObbPath(this.a)).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.getName().endsWith(packageName + ".obb")) {
                    file.delete();
                }
            }
        }
    }

    public boolean extractFile(String str, String str2, String str3, boolean z) {
        try {
            com.excelliance.lbsdk.main.s.al alVar = new com.excelliance.lbsdk.main.s.al(str);
            com.excelliance.lbsdk.main.s.af afVarA = alVar.a(str3);
            byte[] bArr = new byte[1024];
            new File(str2).mkdirs();
            String str4 = str2 + str3;
            if (new File(str4).exists() && z) {
                new File(str4).delete();
            }
            if (!new File(str4).exists()) {
                new File(str4).getParentFile().mkdirs();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str4));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(alVar.a(afVarA));
                while (true) {
                    int i2 = bufferedInputStream.read(bArr, 0, 1024);
                    if (i2 == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, i2);
                }
                bufferedInputStream.close();
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            alVar.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean extractFrame(String str, String str2, String str3) {
        return extractFrame(str, str2, str3, null, true);
    }

    public boolean extractFrame(String str, String str2, String str3, String str4, boolean z) {
        StringBuilder sb;
        try {
            ZipFile zipFile = new ZipFile(str);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            byte[] bArr = new byte[1024];
            new File(str2).mkdirs();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                String name = zipEntryNextElement.getName();
                if (name.contains("../")) {
                    throw new IOException("entry name contains illegal characters ../");
                }
                if (str4 == null || !name.endsWith(str4)) {
                    if (name.indexOf(str3, 0) == -1) {
                        continue;
                    } else {
                        if (!zipEntryNextElement.isDirectory()) {
                            if (str3.endsWith("/")) {
                                sb = new StringBuilder();
                                sb.append(str2);
                                sb.append(name);
                            } else {
                                sb = new StringBuilder();
                                sb.append(str2);
                                sb.append(str3);
                            }
                            String string = sb.toString();
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "ze.getName() = " + name + " target=" + string, new Object[0]);
                            if (new File(string).exists() && z) {
                                new File(string).delete();
                            }
                            if (!new File(string).exists()) {
                                new File(string).getParentFile().mkdirs();
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string));
                                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                                while (true) {
                                    int i2 = bufferedInputStream.read(bArr, 0, 1024);
                                    if (i2 == -1) {
                                        break;
                                    }
                                    bufferedOutputStream.write(bArr, 0, i2);
                                }
                                bufferedInputStream.close();
                                bufferedOutputStream.flush();
                                bufferedOutputStream.close();
                            }
                        }
                        if (!str3.endsWith("/")) {
                            break;
                        }
                    }
                }
            }
            zipFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean extractFrameByPatchV4(Context context) {
        String str = getResRoot(context) + "localData.png";
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "localDataPath:" + str, new Object[0]);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "patchv4 test localDataPath:" + str, new Object[0]);
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        String str2 = com.excelliance.lbsdk.main.ae.c.c(context) + "assets/localData.png";
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "patchv4 test newLocalDataPath:" + str2, new Object[0]);
        try {
            copyFile(new File(str2), file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean extractZipRes() {
        boolean z = true;
        boolean z2 = false;
        String runningVersionCode = getIntance().getRunningVersionCode(false, true);
        String resPatchPath = getResPatchPath(this.a, Integer.parseInt(runningVersionCode), false);
        String strSubstring = resPatchPath.substring(0, resPatchPath.lastIndexOf("."));
        if (resPatchPath != null && new File(resPatchPath).exists()) {
            try {
                unzip(this.a, new File(resPatchPath), getCachePath(this.a, runningVersionCode), 3);
                try {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "unzip lbdiff success", new Object[0]);
                    z2 = true;
                } catch (Exception e) {
                    e = e;
                    com.excelliance.lbsdk.main.g.b.e("GameUtil", "unzip lbdiff failed", e, new Object[0]);
                    z2 = z;
                }
            } catch (Exception e2) {
                e = e2;
                z = false;
            }
            new File(resPatchPath).delete();
        } else if (new File(strSubstring).exists()) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "cp lbdiff", new Object[0]);
            moveDirectory(strSubstring, getCachePath(this.a, runningVersionCode));
        }
        return z2;
    }

    public Set<String> fileOrder() {
        try {
            InputStream inputStreamOpen = this.a.getAssets().open("lbres.meta");
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamOpen);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int i2 = bufferedInputStream.read();
                            if (i2 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write((byte) i2);
                        } catch (Throwable th) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable unused) {
                            }
                            throw th;
                        }
                    }
                    String string = new JSONObject(byteArrayOutputStream.toString()).getString("fileOrder");
                    if (string == null) {
                        byteArrayOutputStream.close();
                        bufferedInputStream.close();
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                        }
                        return Collections.emptySet();
                    }
                    LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(string.split(",")));
                    byteArrayOutputStream.close();
                    bufferedInputStream.close();
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    return linkedHashSet;
                } catch (Throwable th2) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable unused2) {
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th3;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public String generatePkgMd5InfoFile(String[] strArr, String str) {
        String str2;
        int i2;
        String str3;
        File file;
        String strComputeInputStreamMd5;
        GameUtil gameUtil = this;
        String str4 = "GameUtil";
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "generatePkgMd5InfoFile pkg md5:" + str, new Object[0]);
        String strB = gameUtil.f.b(2097152L);
        String str5 = strB + str + "_file.zip";
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "generatePkgMd5InfoFile zip path:" + str5, new Object[0]);
        if (new File(str5).exists()) {
            return str5;
        }
        try {
            String str6 = strB + str + ".nmd5";
            String str7 = strB + str + ".nmeta";
            String str8 = strB + "apk.cfg";
            File file2 = new File(str8);
            File file3 = new File(str6);
            File file4 = new File(str7);
            file3.getParentFile().mkdirs();
            file4.getParentFile().mkdirs();
            file2.getParentFile().mkdirs();
            if (file3.exists()) {
                file3.delete();
            }
            if (file4.exists()) {
                file4.delete();
            }
            if (file2.exists()) {
                file2.delete();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str6));
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(str7));
            BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter(str8));
            ap apVarA = com.excelliance.lbsdk.main.a.a(strArr);
            ap.a[] aVarArrA = apVarA.a();
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            int length = aVarArrA.length;
            int i3 = 0;
            while (i3 < length) {
                try {
                    ap.a aVar = aVarArrA[i3];
                    ap.a[] aVarArr = aVarArrA;
                    int i4 = length;
                    str2 = str4;
                    str3 = str5;
                    File file5 = file4;
                    if (aVar.k) {
                        try {
                            String str9 = aVar.b;
                            JSONObject jSONObject3 = new JSONObject();
                            if (com.excelliance.lbsdk.main.ae.c.e(str9)) {
                                file = file3;
                                if (aVar.a.exists()) {
                                    FileInputStream fileInputStream = new FileInputStream(aVar.a);
                                    strComputeInputStreamMd5 = gameUtil.computeInputStreamMd5(fileInputStream);
                                    jSONObject3.put("checksum", strComputeInputStreamMd5);
                                    jSONObject3.put(BwbxUtil.ACTION_SIZE, aVar.a.length());
                                    jSONObject3.put("offset", 0);
                                    fileInputStream.close();
                                } else {
                                    bufferedWriter3 = bufferedWriter3;
                                }
                            } else {
                                file = file3;
                                strComputeInputStreamMd5 = null;
                            }
                            bufferedWriter.write(strComputeInputStreamMd5 + " " + str9);
                            bufferedWriter.newLine();
                            jSONObject.put(str9, jSONObject3);
                        } catch (Exception e) {
                            e = e;
                            str5 = str3;
                            i2 = 0;
                            com.excelliance.lbsdk.main.g.b.e(str2, "generatePkgInfoFile fail ", e, new Object[i2]);
                            new File(str5).delete();
                            return null;
                        }
                    } else {
                        bufferedWriter3 = bufferedWriter3;
                        file = file3;
                        try {
                            String str10 = aVar.b;
                            if (!str10.endsWith("/") && (!str10.startsWith("META-INF") || str10.endsWith(".SF") || str10.endsWith(".RSA") || str10.endsWith("MANIFEST.MF"))) {
                                InputStream inputStreamA = apVarA.a(aVar.b);
                                bufferedWriter.write(gameUtil.computeInputStreamMd5(inputStreamA) + " " + str10);
                                bufferedWriter.newLine();
                                inputStreamA.close();
                            }
                            JSONObject jSONObject4 = new JSONObject();
                            FileInputStream fileInputStream2 = new FileInputStream(aVar.d());
                            fileInputStream2.skip(aVar.j);
                            jSONObject4.put("checksum", computeInputStreamMd5(fileInputStream2, aVar.h));
                            jSONObject4.put(BwbxUtil.ACTION_SIZE, aVar.h);
                            jSONObject4.put("offset", aVar.j);
                            jSONObject.put(str10, jSONObject4);
                            fileInputStream2.close();
                        } catch (Exception e2) {
                            e = e2;
                            i2 = 0;
                            str5 = str3;
                            com.excelliance.lbsdk.main.g.b.e(str2, "generatePkgInfoFile fail ", e, new Object[i2]);
                            new File(str5).delete();
                            return null;
                        }
                    }
                    i3++;
                    gameUtil = this;
                    aVarArrA = aVarArr;
                    apVarA = apVarA;
                    length = i4;
                    str4 = str2;
                    str5 = str3;
                    file4 = file5;
                    file2 = file2;
                    file3 = file;
                    bufferedWriter3 = bufferedWriter3;
                } catch (Exception e3) {
                    e = e3;
                    str2 = str4;
                    i2 = 0;
                    com.excelliance.lbsdk.main.g.b.e(str2, "generatePkgInfoFile fail ", e, new Object[i2]);
                    new File(str5).delete();
                    return null;
                }
            }
            BufferedWriter bufferedWriter4 = bufferedWriter3;
            str3 = str5;
            str2 = str4;
            File file6 = file2;
            File file7 = file3;
            File file8 = file4;
            try {
                bufferedWriter.close();
                jSONObject2.put("meta", jSONObject);
                bufferedWriter2.write(jSONObject2.toString());
                bufferedWriter2.close();
                bufferedWriter4.write("chid:" + getApkMainCh());
                bufferedWriter4.newLine();
                bufferedWriter4.write("cpmeta:" + getClientChId());
                bufferedWriter4.newLine();
                StringBuilder sb = new StringBuilder();
                sb.append("pkg:");
                try {
                    sb.append(this.a.getPackageName());
                    bufferedWriter4.write(sb.toString());
                    bufferedWriter4.newLine();
                    bufferedWriter4.write("vname:" + getVerNameFromApk(strArr[0]));
                    bufferedWriter4.newLine();
                    bufferedWriter4.write("vcode:" + getVerCodeFromApk(strArr[0], false, true));
                    bufferedWriter4.newLine();
                    bufferedWriter4.write("supsmall:1");
                    bufferedWriter4.close();
                    File[] fileArr = new File[3];
                    i2 = 0;
                    try {
                        fileArr[0] = file7;
                        fileArr[1] = file6;
                        fileArr[2] = file8;
                        str5 = str3;
                        try {
                            zipFiles(fileArr, new File(str5), new String[]{"lebianFlag.xml"});
                            return str5;
                        } catch (Exception e4) {
                            e = e4;
                            i2 = 0;
                            com.excelliance.lbsdk.main.g.b.e(str2, "generatePkgInfoFile fail ", e, new Object[i2]);
                            new File(str5).delete();
                            return null;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        str5 = str3;
                        com.excelliance.lbsdk.main.g.b.e(str2, "generatePkgInfoFile fail ", e, new Object[i2]);
                        new File(str5).delete();
                        return null;
                    }
                } catch (Exception e6) {
                    e = e6;
                    str5 = str3;
                    i2 = 0;
                    com.excelliance.lbsdk.main.g.b.e(str2, "generatePkgInfoFile fail ", e, new Object[i2]);
                    new File(str5).delete();
                    return null;
                }
            } catch (Exception e7) {
                e = e7;
            }
        } catch (Exception e8) {
            e = e8;
            str2 = "GameUtil";
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public String generatePkgZipInfoFile(String[] strArr, String str) throws Throwable {
        BufferedWriter bufferedWriter;
        String strB = this.f.b(2097152L);
        String str2 = strB + str + "_bin.zip";
        if (new File(str2).exists()) {
            return str2;
        }
        String str3 = null;
        try {
            try {
                String str4 = strB + str + ".meta";
                File file = new File(str4);
                if (file.exists()) {
                    file.delete();
                }
                file.getParentFile().mkdirs();
                if (!generateZipInfoFile(strArr, str4)) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "generateZipInfoFile fail", new Object[0]);
                    return null;
                }
                String str5 = strB + "apk.cfg";
                File file2 = new File(str5);
                if (file2.exists()) {
                    file2.delete();
                }
                file2.getParentFile().mkdirs();
                bufferedWriter = new BufferedWriter(new FileWriter(str5));
                try {
                    try {
                        bufferedWriter.write("chid:" + getApkMainCh());
                        bufferedWriter.newLine();
                        bufferedWriter.write("cpmeta:" + getClientChId());
                        bufferedWriter.newLine();
                        bufferedWriter.write("pkg:" + this.a.getPackageName());
                        bufferedWriter.newLine();
                        bufferedWriter.write("vname:" + getVerNameFromApk(strArr[0]));
                        bufferedWriter.newLine();
                        bufferedWriter.write("vcode:" + getVerCodeFromApk(strArr[0], false, true));
                        bufferedWriter.newLine();
                        bufferedWriter.write("supsmall:1");
                        bufferedWriter.flush();
                        zipFiles(new File[]{file, file2}, new File(str2));
                        bufferedWriter.close();
                        str3 = str2;
                    } catch (Exception e) {
                        e = e;
                        com.excelliance.lbsdk.main.g.b.e("GameUtil", "generatePkgInfoFile fail", e, new Object[0]);
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        return str3;
                    }
                    return str3;
                } catch (Throwable th) {
                    th = th;
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
            bufferedWriter = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    public boolean generateZipInfoFile(String[] strArr, String str) {
        String packageCodePath;
        long j2;
        String strComputeInputStreamMd5;
        long j3;
        JSONObject jSONObject;
        try {
            ap.a[] aVarArrA = com.excelliance.lbsdk.main.a.a(strArr).a();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            FileWriter fileWriter = new FileWriter(str);
            int length = aVarArrA.length;
            int i2 = 0;
            while (i2 < length) {
                ap.a aVar = aVarArrA[i2];
                JSONObject jSONObject4 = new JSONObject();
                if (aVar.k) {
                    packageCodePath = this.a.getPackageCodePath();
                    if (com.excelliance.lbsdk.main.ae.c.e(packageCodePath)) {
                        if (aVar.a.exists()) {
                            FileInputStream fileInputStream = new FileInputStream(aVar.a);
                            strComputeInputStreamMd5 = computeInputStreamMd5(fileInputStream);
                            fileInputStream.close();
                            j3 = aVar.h;
                            j2 = 0;
                        } else {
                            jSONObject = jSONObject2;
                        }
                        i2++;
                        jSONObject2 = jSONObject;
                    } else {
                        strComputeInputStreamMd5 = null;
                        j2 = 0;
                        j3 = 0;
                    }
                } else {
                    packageCodePath = aVar.b;
                    FileInputStream fileInputStream2 = new FileInputStream(aVar.d());
                    fileInputStream2.skip(aVar.j);
                    String strComputeInputStreamMd6 = computeInputStreamMd5(fileInputStream2, aVar.h);
                    fileInputStream2.close();
                    long j4 = aVar.h;
                    j2 = aVar.j;
                    strComputeInputStreamMd5 = strComputeInputStreamMd6;
                    j3 = j4;
                }
                jSONObject4.put("checksum", strComputeInputStreamMd5);
                jSONObject4.put(BwbxUtil.ACTION_SIZE, j3);
                jSONObject4.put("offset", j2);
                jSONObject = jSONObject2;
                jSONObject.put(packageCodePath, jSONObject4);
                i2++;
                jSONObject2 = jSONObject;
            }
            jSONObject3.put("meta", jSONObject2);
            fileWriter.write(jSONObject3.toString());
            fileWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getAndroidVersion() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getApiLvl() {
        try {
            return Integer.toString(Build.VERSION.SDK_INT);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getApkMainCh() {
        try {
            return this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), F_NOTIFY_TYPE_AB_TEST).metaData.getInt("MainChId");
        } catch (Exception unused) {
            com.excelliance.lbsdk.main.g.b.e("GameUtil", "No meta-data MainChId found in manifest", new Object[0]);
            return 0;
        }
    }

    public String getBaseUrl() {
        return getBaseUrl("r");
    }

    public String getBaseUrl(String str) {
        String str2 = "http://def.d" + str + BwbxUtil.SECOND_CN + ".loveota.com/";
        try {
            Class<?> cls = Class.forName("com.excelliance.lbsdk.base.BaseUtil", false, this.a.getClassLoader());
            Method declaredMethod = cls.getDeclaredMethod("getInstance", Context.class);
            declaredMethod.setAccessible(true);
            return (String) cls.getDeclaredMethod("getUrlRoot", String.class).invoke(declaredMethod.invoke(null, this.a), str);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public String getClientChId() {
        int i2;
        String strValueOf = null;
        try {
            Bundle bundle = this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), F_NOTIFY_TYPE_AB_TEST).metaData;
            strValueOf = String.valueOf(bundle.get("ClientChId"));
            if (strValueOf == null && (i2 = bundle.getInt("ClientChId", -123456)) != -123456) {
                strValueOf = String.valueOf(i2);
            }
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("GameUtil", "getClientChId e:", e, new Object[0]);
        }
        if (strValueOf == null) {
            strValueOf = "0";
        }
        return strValueOf.trim();
    }

    public String getClientChIdFromApk(String str) {
        int i2;
        String strValueOf = null;
        try {
            Bundle bundle = this.a.getPackageManager().getPackageArchiveInfo(str, F_NOTIFY_TYPE_AB_TEST).applicationInfo.metaData;
            strValueOf = String.valueOf(bundle.get("ClientChId"));
            if (strValueOf == null && (i2 = bundle.getInt("ClientChId", -123456)) != -123456) {
                strValueOf = String.valueOf(i2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strValueOf == null ? "0" : strValueOf;
    }

    public com.excelliance.lbsdk.main.x.a getComputeMd5SkipSize(String str) throws Throwable {
        RandomAccessFile randomAccessFile;
        long jA;
        long jB = 0;
        try {
            try {
                randomAccessFile = new RandomAccessFile(str, "r");
                try {
                    try {
                        com.excelliance.lbsdk.main.x.a aVarB = b(str, randomAccessFile);
                        jA = aVarB.a();
                        try {
                            jB = aVarB.b();
                            randomAccessFile.close();
                            randomAccessFile.close();
                        } catch (Exception unused) {
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            return new com.excelliance.lbsdk.main.x.a(jA, jB);
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception unused2) {
                    jA = 0;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Exception unused3) {
            jA = 0;
            randomAccessFile = null;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
        return new com.excelliance.lbsdk.main.x.a(jA, jB);
    }

    public Context getContext() {
        return this.a;
    }

    public String getCountry(Context context) {
        try {
            return Locale.getDefault().getCountry();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getCurProcessName() throws Throwable {
        int iMyPid = Process.myPid();
        String strA = a(iMyPid);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "pid=" + iMyPid + ", name=" + strA, new Object[0]);
        return strA;
    }

    public String getDealCommitPath() {
        StringBuilder sb = new StringBuilder(getBaseUrl("s") + "signmismatch.php");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pkg", this.a.getPackageName());
            jSONObject.put("chid", getApkMainCh());
            jSONObject.put("cpmeta", getClientChId());
            sb.append("?data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public int getDownloadPid() {
        int i2 = -1;
        try {
            int iMyUid = Process.myUid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid && runningAppProcessInfo.processName.endsWith(":lebian.dns")) {
                    i2 = runningAppProcessInfo.pid;
                }
            }
        } catch (Exception unused) {
        }
        return i2;
    }

    public ActivityInfo getEntryActivityInfo() {
        try {
            PackageManager packageManager = this.a.getPackageManager();
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(this.a.getPackageName());
            if (launchIntentForPackage != null) {
                return packageManager.getActivityInfo(launchIntentForPackage.getComponent(), 65536);
            }
            Intent intent = new Intent("action.lebian.MAIN");
            intent.addCategory("android.intent.category.DEFAULT");
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 65536)) {
                if (resolveInfo.activityInfo.packageName.equals(this.a.getPackageName())) {
                    return resolveInfo.activityInfo;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getErrorCommitPath(String str, String str2) {
        StringBuilder sb = new StringBuilder(getBaseUrl("s") + "sdkcrashlog.php");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("reason", str);
            jSONObject.put("pkgname", this.a.getPackageName());
            jSONObject.put("chid", getApkMainCh());
            jSONObject.put("cpmeta", getClientChId());
            String strB = this.f.b();
            if (strB != null) {
                jSONObject.put("uid", strB);
            }
            jSONObject.put("iv", getVerCodeFromApk(this.a.getPackageResourcePath()));
            jSONObject.put("rv", getRunningVersionCode());
            jSONObject.put("dmd5", str2);
            jSONObject.put("localMd5", computeFileMd5(this.a.getPackageResourcePath(), true, true));
            jSONObject.put("main", getMainVer());
            jSONObject.put("vm", getVMVer());
            jSONObject.put("model", getModel());
            String strP = this.f.p();
            if (strP != null) {
                jSONObject.put("pickUrl", strP);
            }
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "error json : " + jSONObject, new Object[0]);
            sb.append("?data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("GameUtil", "getErrorCommitPath fail", e, new Object[0]);
        }
        return sb.toString();
    }

    public String getFakeAndroidId() {
        String strB = ae.b(this.a, "lebian_device_info", "FakeIMSI", (String) null);
        if (strB != null) {
            return strB;
        }
        String guid = getGUID();
        ae.a(this.a, "lebian_device_info", "FakeIMSI", guid);
        return guid;
    }

    public String getInapkInfo(String str) {
        String string = null;
        try {
            ZipFile zipFile = new ZipFile(str);
            try {
                ZipEntry entry = zipFile.getEntry("META-INF/lebian_inapkinfo.MF");
                if (entry == null) {
                    entry = zipFile.getEntry("assets/lebian_inapkinfo");
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(entry));
                byte[] bArr = new byte[F_NOTIFY_TYPE_AB_TEST];
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    int i2 = bufferedInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    stringBuffer.append(new String(bArr, 0, i2));
                }
                string = stringBuffer.toString();
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "inapkinfo=" + string, new Object[0]);
                zipFile.close();
            } catch (Throwable th) {
                try {
                    zipFile.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Exception unused2) {
        }
        return string;
    }

    public int getLBCloudId(Context context) {
        int i2;
        try {
            i2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), F_NOTIFY_TYPE_AB_TEST).metaData.getInt("LBCloudId");
            try {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "getLBCloudId:" + i2, new Object[0]);
            } catch (Exception e) {
                e = e;
                com.excelliance.lbsdk.main.g.b.e("GameUtil", "getLBCloudId e:", e, new Object[0]);
            }
        } catch (Exception e2) {
            e = e2;
            i2 = 0;
        }
        return i2 == 0 ? getApkMainCh() : i2;
    }

    public String getLanguage(Context context) {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getLastVer(Context context) {
        String str = getResourceCfg(context).get("lastVer");
        return str == null ? "-1" : str;
    }

    public String getLogUrl() {
        try {
            Class<?> cls = Class.forName("com.excelliance.lbsdk.base.BaseUtil", false, this.a.getClassLoader());
            Method declaredMethod = cls.getDeclaredMethod("getInstance", Context.class);
            declaredMethod.setAccessible(true);
            return (String) cls.getDeclaredMethod("getLogUrl", (Class[]) null).invoke(declaredMethod.invoke(null, this.a), (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
            return "http://log.dscn.loveota.com/";
        }
    }

    public int getMainVer() {
        if (!a()) {
            return -1;
        }
        int iB = ae.b(this.a, "lebian_module_base", "newmain", -1);
        return iB == -1 ? ae.b(this.a, "lebian_module_base", "defmain", -1) : iB;
    }

    public String getMd5AndroidId() {
        return m.a(this.a, "USE_KWWD", false) ? Settings.Secure.getString(this.a.getContentResolver(), "android_id") : encryptByMD5(getFakeAndroidId());
    }

    public String getModel() {
        try {
            return Build.BRAND + " " + com.excelliance.lbsdk.main.ai.a.a(this.a);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:101:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:102:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:105:0x01db  */
    /* JADX WARN: Code duplicated, block: B:106:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:109:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:110:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:113:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:114:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:117:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:118:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:121:0x0249  */
    /* JADX WARN: Code duplicated, block: B:122:0x024b  */
    /* JADX WARN: Code duplicated, block: B:125:0x0252  */
    /* JADX WARN: Code duplicated, block: B:126:0x0254  */
    /* JADX WARN: Code duplicated, block: B:129:0x025b  */
    /* JADX WARN: Code duplicated, block: B:130:0x025d  */
    /* JADX WARN: Code duplicated, block: B:133:0x0264  */
    /* JADX WARN: Code duplicated, block: B:134:0x0266  */
    /* JADX WARN: Code duplicated, block: B:138:0x0271 A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:140:0x027f A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:144:0x028f A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:153:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:156:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:159:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:162:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:165:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:168:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:171:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:174:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:177:0x02db  */
    /* JADX WARN: Code duplicated, block: B:180:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:183:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:186:0x02ed  */
    /* JADX WARN: Code duplicated, block: B:266:0x029d A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:268:0x029d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:269:0x029d A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x009a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:70:0x009b A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0113 A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0126 A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x013e A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0156  */
    /* JADX WARN: Code duplicated, block: B:82:0x0158  */
    /* JADX WARN: Code duplicated, block: B:87:0x016a A[Catch: Exception -> 0x02a3, TryCatch #0 {Exception -> 0x02a3, blocks: (B:67:0x0073, B:70:0x009b, B:72:0x0113, B:73:0x0116, B:75:0x0126, B:77:0x013e, B:79:0x0144, B:85:0x015d, B:87:0x016a, B:91:0x01ba, B:95:0x01c3, B:99:0x01cc, B:103:0x01d5, B:107:0x01de, B:111:0x01e7, B:115:0x01f0, B:119:0x01f9, B:123:0x024c, B:127:0x0255, B:131:0x025e, B:135:0x0267, B:136:0x0269, B:138:0x0271, B:140:0x027f, B:142:0x0285, B:144:0x028f, B:146:0x0295, B:147:0x029d), top: B:262:0x0073 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:90:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:93:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:94:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:97:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:98:0x01cb  */
    /* JADX WARN: Instruction removed from duplicated block: B:70:0x009b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:75:0x0126, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:87:0x016a, please report this as an issue */
    public boolean getNotifySwitch(int i2) throws Throwable {
        String curProcessName;
        long jB;
        long jCurrentTimeMillis;
        o oVarA;
        String str;
        XmlPullParser xmlPullParserNewPullParser;
        int eventType;
        String name;
        String attributeValue;
        String attributeValue2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        if (!this.getNotifySwitching) {
            this.getNotifySwitching = true;
            switch (i2) {
                case 1:
                    int i17 = this.m;
                    if (i17 != -1) {
                        return i17 == 1;
                    }
                    try {
                        curProcessName = getCurProcessName();
                        jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                        jCurrentTimeMillis = System.currentTimeMillis();
                        if (jCurrentTimeMillis - jB < 5000) {
                            return false;
                        }
                        StringBuilder sb = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                        sb.append("?chid=");
                        sb.append(getApkMainCh());
                        sb.append("&cpmeta=");
                        sb.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                        sb.append("&vercode=");
                        sb.append(String.valueOf(getRunningVersionCode(false, true)));
                        sb.append("&abver=");
                        sb.append(1);
                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb.toString(), new Object[0]);
                        if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                            SystemClock.sleep(1000L);
                        }
                        oVarA = aa.a(sb.toString(), this.a);
                        if (oVarA.a == 200) {
                            ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                            str = oVarA.b;
                            if (str != null && str.length() > 0) {
                                xmlPullParserNewPullParser = Xml.newPullParser();
                                xmlPullParserNewPullParser.setInput(new StringReader(str));
                                for (eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                                    if (eventType == 0 && eventType == 2) {
                                        name = xmlPullParserNewPullParser.getName();
                                        if (name.equalsIgnoreCase("stct")) {
                                            i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                            i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                            String attributeValue3 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue3, new Object[0]);
                                            AbTestManager.getInstance(this.a).update(attributeValue3);
                                            if ((i3 & 1) > 0) {
                                                i5 = 1;
                                            } else {
                                                i5 = 0;
                                            }
                                            this.m = i5;
                                            if ((i3 & 2) > 0) {
                                                i6 = 1;
                                            } else {
                                                i6 = 0;
                                            }
                                            this.n = i6;
                                            if ((i3 & 4) > 0) {
                                                i7 = 1;
                                            } else {
                                                i7 = 0;
                                            }
                                            this.o = i7;
                                            if ((i3 & 8) > 0) {
                                                i8 = 1;
                                            } else {
                                                i8 = 0;
                                            }
                                            this.p = i8;
                                            if ((i3 & 16) > 0) {
                                                i9 = 1;
                                            } else {
                                                i9 = 0;
                                            }
                                            this.q = i9;
                                            if ((i3 & 32) > 0) {
                                                i10 = 1;
                                            } else {
                                                i10 = 0;
                                            }
                                            this.r = i10;
                                            if ((i3 & 64) > 0) {
                                                i11 = 1;
                                            } else {
                                                i11 = 0;
                                            }
                                            this.s = i11;
                                            if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                                i12 = 1;
                                            } else {
                                                i12 = 0;
                                            }
                                            this.t = i12;
                                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                            if ((i4 & 1) > 0) {
                                                i13 = 1;
                                            } else {
                                                i13 = 0;
                                            }
                                            this.u = i13;
                                            if ((i4 & 4) > 0) {
                                                i14 = 1;
                                            } else {
                                                i14 = 0;
                                            }
                                            this.v = i14;
                                            if ((i4 & 2) > 0) {
                                                i15 = 1;
                                            } else {
                                                i15 = 0;
                                            }
                                            this.w = i15;
                                            if ((i4 & 8) > 0) {
                                                i16 = 1;
                                            } else {
                                                i16 = 0;
                                            }
                                            this.x = i16;
                                        }
                                        if (name.equalsIgnoreCase("resmiss")) {
                                            attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                            attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                            if (attributeValue != null && attributeValue.length() > 0) {
                                                h = Long.parseLong(attributeValue) * 1000;
                                            }
                                            if (attributeValue2 == null && attributeValue2.length() > 0) {
                                                i = Long.parseLong(attributeValue2) * 1000;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 2:
                    int i18 = this.n;
                    if (i18 != -1) {
                        return i18 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb2 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb2.append("?chid=");
                    sb2.append(getApkMainCh());
                    sb2.append("&cpmeta=");
                    sb2.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb2.append("&vercode=");
                    sb2.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb2.append("&abver=");
                    sb2.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb2.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb2.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue4 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue4, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue4);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 3:
                    int i19 = this.o;
                    if (i19 != -1) {
                        return i19 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb3 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb3.append("?chid=");
                    sb3.append(getApkMainCh());
                    sb3.append("&cpmeta=");
                    sb3.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb3.append("&vercode=");
                    sb3.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb3.append("&abver=");
                    sb3.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb3.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb3.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue5 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue5, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue5);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 4:
                    int i20 = this.p;
                    if (i20 != -1) {
                        return i20 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb4 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb4.append("?chid=");
                    sb4.append(getApkMainCh());
                    sb4.append("&cpmeta=");
                    sb4.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb4.append("&vercode=");
                    sb4.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb4.append("&abver=");
                    sb4.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb4.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb4.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue6 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue6, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue6);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 5:
                    int i21 = this.q;
                    if (i21 != -1) {
                        return i21 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb5 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb5.append("?chid=");
                    sb5.append(getApkMainCh());
                    sb5.append("&cpmeta=");
                    sb5.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb5.append("&vercode=");
                    sb5.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb5.append("&abver=");
                    sb5.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb5.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb5.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue7 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue7, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue7);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 6:
                    int i22 = this.r;
                    if (i22 != -1) {
                        return i22 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb6 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb6.append("?chid=");
                    sb6.append(getApkMainCh());
                    sb6.append("&cpmeta=");
                    sb6.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb6.append("&vercode=");
                    sb6.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb6.append("&abver=");
                    sb6.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb6.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb6.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue8 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue8, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue8);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 7:
                    int i23 = this.s;
                    if (i23 != -1) {
                        return i23 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb7 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb7.append("?chid=");
                    sb7.append(getApkMainCh());
                    sb7.append("&cpmeta=");
                    sb7.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb7.append("&vercode=");
                    sb7.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb7.append("&abver=");
                    sb7.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb7.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb7.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue9 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue9, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue9);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case 8:
                    int i24 = this.u;
                    if (i24 != -1) {
                        return i24 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb8 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb8.append("?chid=");
                    sb8.append(getApkMainCh());
                    sb8.append("&cpmeta=");
                    sb8.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb8.append("&vercode=");
                    sb8.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb8.append("&abver=");
                    sb8.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb8.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb8.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue10 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue10, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue10);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case NOTIFY_TYPE_SPEED_REPORT /* 9 */:
                    int i25 = this.v;
                    if (i25 != -1) {
                        return i25 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb9 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb9.append("?chid=");
                    sb9.append(getApkMainCh());
                    sb9.append("&cpmeta=");
                    sb9.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb9.append("&vercode=");
                    sb9.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb9.append("&abver=");
                    sb9.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb9.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb9.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue11 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue11, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue11);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case NOTIFY_TYPE_BWBX_USE_BK_CDN /* 10 */:
                    int i26 = this.w;
                    if (i26 != -1) {
                        return i26 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb10 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb10.append("?chid=");
                    sb10.append(getApkMainCh());
                    sb10.append("&cpmeta=");
                    sb10.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb10.append("&vercode=");
                    sb10.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb10.append("&abver=");
                    sb10.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb10.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb10.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue12 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue12, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue12);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR /* 11 */:
                    int i27 = this.x;
                    if (i27 != -1) {
                        return i27 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb11 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb11.append("?chid=");
                    sb11.append(getApkMainCh());
                    sb11.append("&cpmeta=");
                    sb11.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb11.append("&vercode=");
                    sb11.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb11.append("&abver=");
                    sb11.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb11.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb11.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue13 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue13, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue13);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                case NOTIFY_TYPE_AB_TEST /* 12 */:
                    int i28 = this.t;
                    if (i28 != -1) {
                        return i28 == 1;
                    }
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb12 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb12.append("?chid=");
                    sb12.append(getApkMainCh());
                    sb12.append("&cpmeta=");
                    sb12.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb12.append("&vercode=");
                    sb12.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb12.append("&abver=");
                    sb12.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb12.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb12.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue14 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue14, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue14);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
                default:
                    curProcessName = getCurProcessName();
                    jB = ae.b(this.a, "lebian_module_base", "last_switch_" + curProcessName, 0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return false;
                    }
                    StringBuilder sb13 = new StringBuilder(getBaseUrl("s") + "sdkstctr.php");
                    sb13.append("?chid=");
                    sb13.append(getApkMainCh());
                    sb13.append("&cpmeta=");
                    sb13.append(URLEncoder.encode(getClientChId(), "UTF-8"));
                    sb13.append("&vercode=");
                    sb13.append(String.valueOf(getRunningVersionCode(false, true)));
                    sb13.append("&abver=");
                    sb13.append(1);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "getNotifySwitch URL:" + sb13.toString(), new Object[0]);
                    if ("FUJITSU".equalsIgnoreCase(Build.MANUFACTURER)) {
                        SystemClock.sleep(1000L);
                    }
                    oVarA = aa.a(sb13.toString(), this.a);
                    if (oVarA.a == 200) {
                        ae.a(this.a, "lebian_module_base", "last_switch_" + curProcessName, jCurrentTimeMillis);
                        str = oVarA.b;
                        if (str != null) {
                            xmlPullParserNewPullParser = Xml.newPullParser();
                            xmlPullParserNewPullParser.setInput(new StringReader(str));
                            while (eventType != 1) {
                                if (eventType == 0) {
                                    name = xmlPullParserNewPullParser.getName();
                                    if (name.equalsIgnoreCase("stct")) {
                                        i3 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                                        i4 = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "fasterflag"));
                                        String attributeValue15 = xmlPullParserNewPullParser.getAttributeValue(null, "branch");
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "flag=" + i3 + " fasterflag = " + i4 + " branch = " + attributeValue15, new Object[0]);
                                        AbTestManager.getInstance(this.a).update(attributeValue15);
                                        if ((i3 & 1) > 0) {
                                            i5 = 1;
                                        } else {
                                            i5 = 0;
                                        }
                                        this.m = i5;
                                        if ((i3 & 2) > 0) {
                                            i6 = 1;
                                        } else {
                                            i6 = 0;
                                        }
                                        this.n = i6;
                                        if ((i3 & 4) > 0) {
                                            i7 = 1;
                                        } else {
                                            i7 = 0;
                                        }
                                        this.o = i7;
                                        if ((i3 & 8) > 0) {
                                            i8 = 1;
                                        } else {
                                            i8 = 0;
                                        }
                                        this.p = i8;
                                        if ((i3 & 16) > 0) {
                                            i9 = 1;
                                        } else {
                                            i9 = 0;
                                        }
                                        this.q = i9;
                                        if ((i3 & 32) > 0) {
                                            i10 = 1;
                                        } else {
                                            i10 = 0;
                                        }
                                        this.r = i10;
                                        if ((i3 & 64) > 0) {
                                            i11 = 1;
                                        } else {
                                            i11 = 0;
                                        }
                                        this.s = i11;
                                        if ((i3 & F_NOTIFY_TYPE_AB_TEST) > 0) {
                                            i12 = 1;
                                        } else {
                                            i12 = 0;
                                        }
                                        this.t = i12;
                                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifySwitch_bwbx_download_progress=" + this.m + " notifySwitch_bwbx_dialog_click=" + this.n + " notifySwitch_bwbx_download_error=" + this.o + " notifySwitch_bwbx_download_time_over_limit=" + this.r + " notifySwitch_bwbx_missing_file=" + this.s + " notifySwitch_ab_test=" + this.t, new Object[0]);
                                        if ((i4 & 1) > 0) {
                                            i13 = 1;
                                        } else {
                                            i13 = 0;
                                        }
                                        this.u = i13;
                                        if ((i4 & 4) > 0) {
                                            i14 = 1;
                                        } else {
                                            i14 = 0;
                                        }
                                        this.v = i14;
                                        if ((i4 & 2) > 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = 0;
                                        }
                                        this.w = i15;
                                        if ((i4 & 8) > 0) {
                                            i16 = 1;
                                        } else {
                                            i16 = 0;
                                        }
                                        this.x = i16;
                                    }
                                    if (name.equalsIgnoreCase("resmiss")) {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "time");
                                        attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "intervaltime");
                                        if (attributeValue != null) {
                                            h = Long.parseLong(attributeValue) * 1000;
                                        }
                                        if (attributeValue2 == null) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.m == -1) {
                        this.m = 1;
                    }
                    if (this.n == -1) {
                        this.n = 1;
                    }
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    if (this.p == -1) {
                        this.p = 0;
                    }
                    if (this.q == -1) {
                        this.q = 0;
                    }
                    if (this.r == -1) {
                        this.r = 0;
                    }
                    if (this.s == -1) {
                        this.s = 0;
                    }
                    if (this.u == -1) {
                        this.u = 0;
                    }
                    if (this.v == -1) {
                        this.v = 0;
                    }
                    if (this.w == -1) {
                        this.w = 0;
                    }
                    if (this.x == -1) {
                        this.x = 0;
                    }
                    if (this.t == -1) {
                        this.t = 0;
                    }
                    this.getNotifySwitching = false;
                    break;
            }
        }
        switch (i2) {
            case 1:
                int i29 = this.m;
                return i29 == -1 || i29 == 1;
            case 2:
                int i30 = this.n;
                return i30 == -1 || i30 == 1;
            case 3:
                int i31 = this.o;
                return i31 == -1 || i31 == 1;
            case 4:
                int i32 = this.p;
                return i32 != -1 && i32 == 1;
            case 5:
                int i33 = this.q;
                return i33 != -1 && i33 == 1;
            case 6:
                int i34 = this.r;
                return i34 != -1 && i34 == 1;
            case 7:
                int i35 = this.s;
                return i35 != -1 && i35 == 1;
            case 8:
                int i36 = this.u;
                return i36 != -1 && i36 == 1;
            case NOTIFY_TYPE_SPEED_REPORT /* 9 */:
                int i37 = this.v;
                return i37 != -1 && i37 == 1;
            case NOTIFY_TYPE_BWBX_USE_BK_CDN /* 10 */:
                int i38 = this.w;
                return i38 != -1 && i38 == 1;
            case NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR /* 11 */:
                int i39 = this.x;
                return i39 != -1 && i39 == 1;
            case NOTIFY_TYPE_AB_TEST /* 12 */:
                int i40 = this.t;
                return i40 != -1 && i40 == 1;
            default:
                return false;
        }
    }

    public int getOTAVersion() {
        return getSdkVer();
    }

    public String getObbName(String str) {
        if (isInMainLand(this.a)) {
            return null;
        }
        String str2 = getObbPath(this.a) + (File.separator + "main." + str + "." + this.a.getPackageName() + ".obb");
        if (new File(str2).exists()) {
            return str2;
        }
        return null;
    }

    public String getObbPath() {
        return getObbPath(this.a) + (File.separator + "main." + getRunningVersionCode(true) + "." + this.a.getPackageName() + ".obb");
    }

    public long getOrigSize(boolean z) {
        return getOrigSize(z, 1);
    }

    public long getOrigSize(boolean z, int i2) {
        ZipFile zipFile;
        ZipEntry entry;
        an anVarA = an.a();
        InputStream bufferedInputStream = null;
        long j2 = 0;
        try {
            if (anVarA.c()) {
                String strE = anVarA.e();
                if (strE != null && new File(strE).exists() && (entry = (zipFile = new ZipFile(strE)).getEntry("assets/origSize.txt")) != null) {
                    bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(entry));
                }
            } else {
                bufferedInputStream = e.b(this.a);
            }
            if (bufferedInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                if (i2 == 2) {
                    line = bufferedReader.readLine();
                }
                if (line != null) {
                    String[] strArrSplit = line.split(";");
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "strarray[0]:" + strArrSplit[0] + " strarray[1]:" + strArrSplit[1], new Object[0]);
                    j2 = Long.parseLong(z ? strArrSplit[0] : strArrSplit[1]);
                }
                inputStreamReader.close();
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "size=" + j2 + " zipType = " + i2, new Object[0]);
            }
        } catch (IOException unused) {
            com.excelliance.lbsdk.main.g.b.c("GameUtil", "no origSize, zipType:" + i2, new Object[0]);
        }
        return j2;
    }

    public int getOriginalMainVer() {
        if (!a()) {
            return -1;
        }
        int iB = ae.b(this.a, "lebian_module_base", "defmain", -1);
        if (iB != -1) {
            return iB;
        }
        return getVerCodeFromApk(this.e + MAIN_JAR, true);
    }

    public PackageInfo getPackageInfo(String str) {
        try {
            return this.a.getPackageManager().getPackageArchiveInfo(str, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bundle getParam(String str) {
        Bundle bundle = new Bundle();
        String strB = ae.b(this.a, "lebian_module_gameinfo", "newvername", (String) null);
        String strB2 = ae.b(this.a, "lebian_module_gameinfo", "oldvername", (String) null);
        if (strB2 == null) {
            try {
                strB2 = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).versionName;
            } catch (Exception unused) {
            }
        }
        PackageInfo packageInfo = getPackageInfo(str);
        bundle.putString("oldvername", strB2);
        if (packageInfo != null && packageInfo.versionName != null) {
            bundle.putString("newvername", packageInfo.versionName);
            if (packageInfo.versionName.equals(strB)) {
                bundle.putBoolean("updated", true);
            }
            ae.a(this.a, "lebian_module_gameinfo", "oldvername", packageInfo.versionName);
            ae.a(this.a, "lebian_module_gameinfo", "newvername");
        }
        String strB3 = ae.b(this.a, "lebian_module_overlayinfo", "overlay_res", (String) null);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "overlay=" + strB3, new Object[0]);
        bundle.putString("overlayPath", strB3);
        return bundle;
    }

    public String getPkId() {
        Context context = this.a;
        if (context != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("lbpkid")));
                this.c = bufferedReader.readLine();
                bufferedReader.close();
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.e("GameUtil", "getPkId:", e, new Object[0]);
            }
        }
        return this.c;
    }

    public String getPlatformType() {
        return "300";
    }

    public String getRealMd5(String str, String str2) {
        File file = new File(str2.substring(0, str2.lastIndexOf("/") + 1) + "md5Info.cfg");
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            String[] strArrSplit = line.split(":");
            if (str == null || !str.equals(strArrSplit[1])) {
                return null;
            }
            return strArrSplit[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getResourceCfg(Context context) throws Throwable {
        BufferedReader bufferedReader;
        HashMap map = new HashMap();
        String str = getResRoot(context) + "resourceInfo.cfg";
        if (new File(str).exists()) {
            BufferedReader bufferedReader2 = null;
            try {
                try {
                    try {
                        bufferedReader = new BufferedReader(new FileReader(str));
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                String[] strArrSplit = line.split(":");
                                map.put(strArrSplit[0], strArrSplit[1]);
                            } catch (Exception e) {
                                e = e;
                                bufferedReader2 = bufferedReader;
                                e.printStackTrace();
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                return map;
                            } catch (Throwable th) {
                                th = th;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        bufferedReader.close();
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        return map;
    }

    public String getRunningVersionCode() {
        return getRunningVersionCode(false);
    }

    public String getRunningVersionCode(boolean z) {
        return getRunningVersionCode(z, false);
    }

    public String getRunningVersionCode(boolean z, boolean z2) {
        String strB = null;
        l lVarA = this.f.a(ae.b(this.a, "lebian_module_extractinfo", "current", (String) null));
        if (lVarA != null && lVarA.savePath != null && new File(lVarA.savePath).exists()) {
            strB = String.valueOf(getVerCodeFromApk(lVarA.savePath, z, z2));
        }
        if (z2 && strB == null) {
            strB = com.excelliance.lbsdk.main.ae.c.b(this.a);
        }
        return strB == null ? String.valueOf(getVerCodeFromApk(this.a.getPackageResourcePath(), z, z2)) : strB;
    }

    public String getScreenDensity(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getSdkVer() {
        try {
            return Class.forName("com.excelliance.lbsdk.base.BuildInfo", false, this.a.getClassLoader()).getDeclaredField("SDK_VERSION").getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getShellVersion() {
        try {
            return this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), F_NOTIFY_TYPE_AB_TEST).metaData.getInt("ShellVersion");
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("GameUtil", "getShellVersion e:", e, new Object[0]);
            return 0;
        }
    }

    public String getSignature(Context context) {
        return getSignature(context, F_NOTIFY_TYPE_AB_TEST);
    }

    public String getSignature(Context context, int i2) {
        try {
            String charsString = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toCharsString();
            return (i2 <= 0 || charsString == null || charsString.length() <= i2) ? charsString : charsString.substring(0, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getSignatureMd5(Context context) {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "getSignatureMd5: 1", new Object[0]);
        String signature = getSignature(context, 0);
        String strEncryptByMD5 = encryptByMD5(signature);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "getSignatureMd5: sign:" + signature + " signMd5:" + strEncryptByMD5, new Object[0]);
        return strEncryptByMD5;
    }

    public String getTotalMemory() {
        long jLongValue = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            jLongValue = Long.valueOf(bufferedReader.readLine().split("\\s+")[1]).longValue();
            bufferedReader.close();
        } catch (Exception unused) {
        }
        return String.valueOf(jLongValue / 1024) + "MB";
    }

    public long getTotalOrigSize(boolean z) {
        return getOrigSize(z, 1) + getOrigSize(z, 2);
    }

    public int getVMVer() {
        if (!a()) {
            return -1;
        }
        int iB = ae.b(this.a, "lebian_module_base", "newvm", -1);
        return iB == -1 ? ae.b(this.a, "lebian_module_base", "defvm", -1) : iB;
    }

    public int getVerCodeFromApk(String str) {
        return getVerCodeFromApk(str, false);
    }

    public int getVerCodeFromApk(String str, boolean z) {
        return getVerCodeFromApk(str, z, false);
    }

    public int getVerCodeFromApk(String str, boolean z, boolean z2) {
        int verCodeFromFile;
        int iIntValue;
        Bundle bundle;
        if (z2) {
            try {
                ZipFile zipFile = new ZipFile(str);
                ZipEntry entry = zipFile.getEntry("assets/lebian/LEBIAN_VERCODE.MF");
                if (entry != null) {
                    InputStream inputStream = zipFile.getInputStream(entry);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    iIntValue = Integer.valueOf(bufferedReader.readLine()).intValue();
                    bufferedReader.close();
                    inputStream.close();
                } else {
                    iIntValue = 0;
                }
                zipFile.close();
                verCodeFromFile = iIntValue;
            } catch (IOException unused) {
                verCodeFromFile = getVerCodeFromFile(str + "assets/lebian/LEBIAN_VERCODE.MF");
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "getVerCodeFromApk: use hulist:" + verCodeFromFile, new Object[0]);
            }
        } else {
            verCodeFromFile = 0;
        }
        if (verCodeFromFile > 0) {
            return verCodeFromFile;
        }
        try {
            PackageInfo packageArchiveInfo = this.a.getPackageManager().getPackageArchiveInfo(str, 129);
            if (!z && (bundle = packageArchiveInfo.applicationInfo.metaData) != null) {
                verCodeFromFile = bundle.getInt("LEBIAN_VERCODE", 0);
            }
            return verCodeFromFile <= 0 ? packageArchiveInfo.versionCode : verCodeFromFile;
        } catch (Exception unused2) {
            return verCodeFromFile;
        }
    }

    public int getVerCodeFromFile(String str) {
        int iIntValue = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            iIntValue = Integer.valueOf(bufferedReader.readLine()).intValue();
            bufferedReader.close();
            fileInputStream.close();
            return iIntValue;
        } catch (IOException unused) {
            return iIntValue;
        }
    }

    public String getVerNameFromApk(String str) {
        try {
            return this.a.getPackageManager().getPackageArchiveInfo(str, 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public void handleProviders(boolean z) {
        String packageName = this.a.getPackageName();
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager != null) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo(packageName, 8);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (packageInfo == null || packageInfo.providers == null) {
                return;
            }
            for (ProviderInfo providerInfo : packageInfo.providers) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "handleProviders name=" + providerInfo.name, new Object[0]);
                ComponentName componentName = new ComponentName(packageName, providerInfo.name);
                int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
                packageManager.setComponentEnabledSetting(componentName, z ? 2 : 0, 1);
                int componentEnabledSetting2 = packageManager.getComponentEnabledSetting(componentName);
                StringBuilder sb = new StringBuilder();
                sb.append("old=");
                sb.append(componentEnabledSetting);
                sb.append(", set=");
                sb.append(z ? 2 : 0);
                sb.append(", newflag=");
                sb.append(componentEnabledSetting2);
                sb.append(", COMPONENT_ENABLED_STATE_DISABLED=");
                sb.append(2);
                sb.append(", COMPONENT_ENABLED_STATE_DEFAULT=");
                sb.append(0);
                com.excelliance.lbsdk.main.g.b.c("GameUtil", sb.toString(), new Object[0]);
            }
            ae.a(this.a, "lebian_module_gameinfo", "pro_disabled", z);
        }
    }

    public boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public boolean isHomeApp() {
        return false;
    }

    public boolean isScreenLocked() {
        return ((KeyguardManager) this.a.getSystemService("keyguard")).isKeyguardLocked();
    }

    /* JADX WARN: Code duplicated, block: B:48:0x008e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x0090 A[Catch: IOException -> 0x008c, TRY_LEAVE, TryCatch #8 {IOException -> 0x008c, blocks: (B:45:0x0088, B:49:0x0090), top: B:57:0x0088 }] */
    /* JADX WARN: Code duplicated, block: B:57:0x0088 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public boolean isTwload(Context context) throws Throwable {
        InputStreamReader inputStreamReader;
        InputStream bufferedInputStream;
        ZipFile zipFile;
        ZipEntry entry;
        InputStream inputStream = null;
        boolean z = false;
        try {
            try {
                String strE = an.a().e();
                if (strE == null) {
                    bufferedInputStream = e.b(this.a);
                } else {
                    bufferedInputStream = (!new File(strE).exists() || (entry = (zipFile = new ZipFile(strE)).getEntry("assets/origSize.txt")) == null) ? null : new BufferedInputStream(zipFile.getInputStream(entry));
                }
                if (bufferedInputStream != null) {
                    try {
                        inputStreamReader = new InputStreamReader(bufferedInputStream, "UTF-8");
                        try {
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            bufferedReader.readLine();
                            if (bufferedReader.readLine() != null) {
                                z = true;
                            }
                        } catch (IOException unused) {
                            inputStream = bufferedInputStream;
                            try {
                                com.excelliance.lbsdk.main.g.b.c("GameUtil", "no origSize", new Object[0]);
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        throw th;
                                    }
                                } else if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = bufferedInputStream;
                            if (inputStream != null) {
                                inputStream.close();
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                            } else if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            throw th;
                        }
                    } catch (IOException unused2) {
                        inputStreamReader = null;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStreamReader = null;
                    }
                } else {
                    inputStreamReader = null;
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (IOException unused3) {
            inputStreamReader = null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
        }
        return z;
    }

    public boolean isX86Exist(Context context) {
        boolean z = false;
        try {
            ZipFile zipFile = new ZipFile(new File(context.getPackageResourcePath()));
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                String name = enumerationEntries.nextElement().getName();
                if (name.contains("../")) {
                    throw new IOException("entry name contains illegal characters ../");
                }
                if (name.startsWith("lib/x86")) {
                    z = true;
                    break;
                }
            }
            zipFile.close();
        } catch (IOException unused) {
        }
        return z;
    }

    public void killAllExceptSelf() {
        try {
            stopGameServices();
            ae.a(this.a, "lebian_module_gameinfo", "selfKill", true);
            int iMyUid = Process.myUid();
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid && runningAppProcessInfo.pid != iMyPid && !runningAppProcessInfo.processName.endsWith(":lebian.base")) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "all alive " + runningAppProcessInfo.processName + ", pid=" + runningAppProcessInfo.pid, new Object[0]);
                    al.a(this.a, runningAppProcessInfo.pid);
                    Process.killProcess(runningAppProcessInfo.pid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void killAllProcesses() {
        try {
            stopGameServices();
            ae.a(this.a, "lebian_module_gameinfo", "selfKill", true);
            int iMyUid = Process.myUid();
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid && runningAppProcessInfo.pid != iMyPid) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "all alive " + runningAppProcessInfo.processName + ", pid=" + runningAppProcessInfo.pid, new Object[0]);
                    al.a(this.a, runningAppProcessInfo.pid);
                    Process.killProcess(runningAppProcessInfo.pid);
                }
            }
            Process.killProcess(Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void killGameProcesses(Context context) {
        try {
            stopGameServices();
            int iMyUid = Process.myUid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "alive " + runningAppProcessInfo.processName + ", pid=" + runningAppProcessInfo.pid, new Object[0]);
                    if (!runningAppProcessInfo.processName.contains(":lebian.") && !runningAppProcessInfo.processName.endsWith(":lbcore") && !runningAppProcessInfo.processName.endsWith(":lbmain")) {
                        al.a(this.a, runningAppProcessInfo.pid);
                        Process.killProcess(runningAppProcessInfo.pid);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void killProcessBySuffix(String str) {
        try {
            if (str.equals(":lbmain")) {
                ae.a(this.a, "lebian_module_gameinfo", "selfKill", true);
            }
            int iMyUid = Process.myUid();
            ActivityManager activityManager = (ActivityManager) this.a.getSystemService("activity");
            for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(3000)) {
                if (runningServiceInfo.uid == iMyUid && runningServiceInfo.process.endsWith(str)) {
                    Intent intent = new Intent();
                    intent.setComponent(runningServiceInfo.service);
                    this.a.stopService(intent);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "stop kill services in pid=" + runningServiceInfo.pid + ", pname=" + runningServiceInfo.process, new Object[0]);
                }
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid && runningAppProcessInfo.processName.endsWith(str)) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "alive " + runningAppProcessInfo.processName + ", pid=" + runningAppProcessInfo.pid, new Object[0]);
                    al.a(this.a, runningAppProcessInfo.pid);
                    Process.killProcess(runningAppProcessInfo.pid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void killThirdProcesses() {
        try {
            stopGameServices();
            ae.a(this.a, "lebian_module_gameinfo", "selfKill", true);
            int iMyUid = Process.myUid();
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid && runningAppProcessInfo.pid != iMyPid) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "alive " + runningAppProcessInfo.processName + ", pid=" + runningAppProcessInfo.pid, new Object[0]);
                    if (!runningAppProcessInfo.processName.contains(":lebian.") && !runningAppProcessInfo.processName.endsWith(":lbcore") && !runningAppProcessInfo.processName.endsWith(":lbmain")) {
                        al.a(this.a, runningAppProcessInfo.pid);
                        Process.killProcess(runningAppProcessInfo.pid);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean moveDirectory(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (File file3 : file.listFiles()) {
            if (file3.isFile()) {
                moveFile(file3.getAbsolutePath(), file2.getAbsolutePath());
            } else if (file3.isDirectory()) {
                moveDirectory(file3.getAbsolutePath(), file2.getAbsolutePath() + File.separator + file3.getName());
            }
        }
        return file.delete();
    }

    public boolean moveFile(String str, String str2) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return file.renameTo(new File(str2 + File.separator + file.getName()));
    }

    public void notifyAction(final Intent intent) {
        ak.a().a(new Thread("GameUtil:notifyAction") { // from class: com.excelliance.lbsdk.main.GameUtil.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                String runningVersionCode;
                String stringExtra;
                try {
                    int intExtra = intent.getIntExtra("action", 0);
                    StringBuilder sb = new StringBuilder(GameUtil.this.getBaseUrl("s") + "sdkstatistics.php");
                    sb.append("?action=" + intExtra);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("uid", an.a().b());
                    jSONObject.put("chid", GameUtil.this.getApkMainCh());
                    jSONObject.put("nettype", aa.c(GameUtil.this.a));
                    if (intExtra != 4) {
                        if (intExtra != 7) {
                            if (intExtra == 12) {
                                if (!GameUtil.this.getNotifySwitch(1)) {
                                    return;
                                }
                                jSONObject.put("vercode", intent.getStringExtra("vercode"));
                                jSONObject.put(BwbxUtil.ACTION_PROGRESS, intent.getIntExtra(BwbxUtil.ACTION_PROGRESS, 0));
                            } else if (intExtra == 13) {
                                if (!GameUtil.this.getNotifySwitch(2)) {
                                    return;
                                }
                                jSONObject.put("vercode", intent.getStringExtra("vercode"));
                                jSONObject.put(BwbxUtil.ACTION_TYPE, intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0));
                                jSONObject.put("tp", intent.getIntExtra("tp", 0));
                                jSONObject.put("cpmeta", GameUtil.this.getClientChId());
                                stringExtra = intent.getStringExtra("package");
                            } else if (intExtra == 14) {
                                if (!GameUtil.this.getNotifySwitch(3)) {
                                    return;
                                }
                                jSONObject.put("vercode", intent.getStringExtra("vercode"));
                                long longExtra = intent.getLongExtra(BwbxUtil.ACTION_TYPE, 0L);
                                jSONObject.put(BwbxUtil.ACTION_TYPE, longExtra);
                                jSONObject.put("code", intent.getIntExtra("code", 0));
                                jSONObject.put(BwbxUtil.ACTION_URL, intent.getStringExtra(BwbxUtil.ACTION_URL));
                                if (longExtra == BwbxUtil.ERR_MD5_MIS || longExtra == BwbxUtil.ERR_RET_CODE || longExtra == -1) {
                                    jSONObject.put("ip", GameUtil.this.a(intent.getStringExtra(BwbxUtil.ACTION_URL)));
                                }
                            } else if (intExtra == 15) {
                                if (!GameUtil.this.getNotifySwitch(6)) {
                                    return;
                                }
                                jSONObject.put("vercode", intent.getStringExtra("vercode"));
                                jSONObject.put(BwbxUtil.ACTION_URL, intent.getStringExtra(BwbxUtil.ACTION_URL));
                            } else if (intExtra == 16) {
                                jSONObject.put("speed", intent.getLongExtra("speed", 0L));
                                jSONObject.put("address", intent.getStringExtra("address"));
                                jSONObject.put("speedBk", intent.getLongExtra("speedBk", 0L));
                                jSONObject.put("addressBk", intent.getStringExtra("addressBk"));
                                runningVersionCode = GameUtil.this.getRunningVersionCode(false, true);
                            } else if (intExtra == 17) {
                                jSONObject.put("vercode", intent.getStringExtra("vercode"));
                                jSONObject.put("cpmeta", intent.getStringExtra("cpmeta"));
                                jSONObject.put("pkg", intent.getStringExtra("pkg"));
                                jSONObject.put("cnt", intent.getIntExtra("cnt", 0));
                            } else if (intExtra == 18) {
                                jSONObject.put("pkg", intent.getStringExtra("pkg"));
                                jSONObject.put("vercode", intent.getStringExtra("vercode"));
                                jSONObject.put(BwbxUtil.ACTION_TYPE, intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0));
                                jSONObject.put("cpmeta", GameUtil.this.getClientChId());
                            } else if (intExtra == 19) {
                                if (!GameUtil.this.getNotifySwitch(11)) {
                                    return;
                                }
                                jSONObject.put("uip", GameUtil.getPublicIP());
                                String stringExtra2 = intent.getStringExtra(BwbxUtil.ACTION_URL);
                                jSONObject.put(BwbxUtil.ACTION_URL, stringExtra2);
                                String cdnip = GameUtil.getCDNIP(stringExtra2);
                                if (cdnip == null) {
                                    return;
                                } else {
                                    jSONObject.put("pop", cdnip);
                                }
                            } else if (intExtra >= 32 && intExtra <= 38) {
                                jSONObject.put("vercode", Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true)));
                            }
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "json=" + jSONObject, new Object[0]);
                            sb.append("&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifyAction getURL:" + sb.toString(), new Object[0]);
                            com.excelliance.lbsdk.main.g.b.c("GameUtil", " APP action status:" + aa.a(sb.toString(), GameUtil.this.a).a, new Object[0]);
                        }
                        jSONObject.put("clientchid", GameUtil.this.getClientChId());
                        jSONObject.put("cpmeta", GameUtil.this.getClientChId());
                        jSONObject.put("errType", intent.getIntExtra("errType", -1));
                        jSONObject.put("excpt", intent.getStringExtra("excpt"));
                        jSONObject.put("pkg", intent.getStringExtra("package"));
                        runningVersionCode = GameUtil.this.getRunningVersionCode(false, true);
                        jSONObject.put("vercode", runningVersionCode);
                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "json=" + jSONObject, new Object[0]);
                        sb.append("&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
                        com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifyAction getURL:" + sb.toString(), new Object[0]);
                        com.excelliance.lbsdk.main.g.b.c("GameUtil", " APP action status:" + aa.a(sb.toString(), GameUtil.this.a).a, new Object[0]);
                    }
                    jSONObject.put("md5", GameUtil.this.computeFileMd5(intent.getStringExtra("path")));
                    jSONObject.put("tp", intent.getIntExtra(BwbxUtil.ACTION_TYPE, 0));
                    stringExtra = intent.getStringExtra("package");
                    jSONObject.put("pkg", stringExtra);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "json=" + jSONObject, new Object[0]);
                    sb.append("&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "notifyAction getURL:" + sb.toString(), new Object[0]);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", " APP action status:" + aa.a(sb.toString(), GameUtil.this.a).a, new Object[0]);
                } catch (Exception e) {
                    com.excelliance.lbsdk.main.g.b.e("GameUtil", "APP Action Exception:", e, new Object[0]);
                }
            }
        });
    }

    public Uri overInstallApkUri(Context context, String str) {
        File file = new File(str);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            String str2 = this.a.getPackageName() + ":com.excelliance.kxqp.fp";
            try {
                Class<?> cls = Class.forName("com.excelliance.lbsdk.must.content.LebianFP", false, context.getApplicationContext().getClassLoader());
                ProviderInfo providerInfo = context.getPackageManager().getProviderInfo(new ComponentName(context, cls.getName()), 65536);
                if (providerInfo.authority != null) {
                    str2 = providerInfo.authority;
                }
                Method declaredMethod = cls.getDeclaredMethod("getUriForFile", Context.class, String.class, File.class);
                declaredMethod.setAccessible(true);
                uri = (Uri) declaredMethod.invoke(null, context.getApplicationContext(), str2, file);
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.e("GameUtil", "overInstallApkUri: ", e, new Object[0]);
            }
            if (uri != null) {
                com.excelliance.lbsdk.main.g.b.c("GameUtil", "overInstallApkUri: uri=" + uri, new Object[0]);
                return uri;
            }
        }
        return uri == null ? Uri.fromFile(file) : uri;
    }

    public boolean overwriteEntry(String str, String str2, Object obj, String str3, String str4) {
        return NativeUtil.overwriteEntry(str, str2, obj, str3, str4);
    }

    public void pkKillProcesses() {
        try {
            stopGameServices();
            int iMyUid = Process.myUid();
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.uid == iMyUid && runningAppProcessInfo.pid != iMyPid) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "alive " + runningAppProcessInfo.processName + ", pid=" + runningAppProcessInfo.pid, new Object[0]);
                    al.a(this.a, runningAppProcessInfo.pid);
                    Process.killProcess(runningAppProcessInfo.pid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int remoteRunningPid() throws Throwable {
        int iB;
        if (!m.a(this.a, "DONT_USE_ACTIVITY_TASK", false)) {
            try {
                String str = this.a.getApplicationInfo().processName;
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                    if (runningAppProcessInfo.processName.equals(str)) {
                        return runningAppProcessInfo.pid;
                    }
                }
            } catch (Exception unused) {
            }
            return 0;
        }
        try {
            iB = af.a(this.a).b(this.a.getApplicationInfo().processName);
        } catch (Exception unused2) {
            iB = 0;
        }
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "remoteRunningPid: status:" + iB, new Object[0]);
        return iB;
    }

    public int requireResZip(Context context) {
        if (isTwload(context)) {
            return 2;
        }
        return m.a(context, "CHOOSE_BY_USER_BWBX", false) ? 1 : -1;
    }

    public void restartGame(Context context) {
        String strA = al.a(context);
        if (strA != null && !strA.contains("PromptActivity") && !strA.contains("NextChapter")) {
            Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
            intent.putExtra("dialogtype", 8);
            intent.putExtra(BwbxUtil.ACTION_TYPE, 1);
            intent.setFlags(805306368);
            intent.setPackage(context.getPackageName());
            context.startActivity(intent);
        }
        try {
            Thread.sleep(500L);
        } catch (Exception unused) {
        }
        getIntance().killThirdProcesses();
        try {
            Thread.sleep(200L);
        } catch (Exception unused2) {
        }
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage != null) {
            ComponentName component = launchIntentForPackage.getComponent();
            if (component != null) {
                launchIntentForPackage = new Intent();
                launchIntentForPackage.setComponent(component);
            }
            launchIntentForPackage.setAction("android.intent.action.MAIN");
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        } else {
            if (getIntance().checkJJ(context)) {
                launchIntentForPackage = new Intent("com.excelliance.open.action.startPromptActivity");
                launchIntentForPackage.putExtra("dialogtype", 7);
            } else {
                launchIntentForPackage = new Intent("action.lebian.MAIN");
                launchIntentForPackage.addCategory("android.intent.category.DEFAULT");
            }
            launchIntentForPackage.setPackage(context.getPackageName());
        }
        launchIntentForPackage.addFlags(268468224);
        context.startActivity(launchIntentForPackage);
    }

    public void restartGameKillAll(Context context) {
        if (al.b(context)) {
            ae.a(this.a, "lebian_module_queryinfo", "lastQueryTime");
            ae.a(this.a, "lebian_module_base", "last_chklbres");
            new com.excelliance.lbsdk.main.n.a(this.a).a(8, 1);
        }
    }

    public void saveObbInfo(String str, String str2) {
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "saveObbInfo obbpath:" + str + " obbNmd5:" + str2, new Object[0]);
        String strComputeFileMd5 = getIntance().computeFileMd5(str, true, false);
        StringBuilder sb = new StringBuilder();
        sb.append("saveObbMd5Key");
        sb.append(str2);
        String string = sb.toString();
        ae.a(this.a, "lebian_module_md5info", getIntance().computeInputStreamMd5(new ByteArrayInputStream(string.getBytes())), strComputeFileMd5);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "saveObbInfo write :" + strComputeFileMd5 + " fileInfo:" + string, new Object[0]);
    }

    public void setContext(Context context) {
        this.a = context;
        this.f.a(context);
    }

    public void setHandler(Handler handler) {
        this.b = handler;
    }

    public void stopGameServices() {
        try {
            int iMyUid = Process.myUid();
            Process.myPid();
            for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningServices(3000)) {
                if (runningServiceInfo.uid == iMyUid && !runningServiceInfo.process.contains(":lebian.") && !runningServiceInfo.process.endsWith(":lbcore") && !runningServiceInfo.process.endsWith(":lbmain")) {
                    Intent intent = new Intent();
                    intent.setComponent(runningServiceInfo.service);
                    this.a.stopService(intent);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "stop services in pid=" + runningServiceInfo.pid + ", pname=" + runningServiceInfo.process, new Object[0]);
                }
            }
        } catch (Exception unused) {
        }
    }

    public void stopProcServices(int i2) {
        try {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningServices(3000)) {
                if (runningServiceInfo.pid == i2) {
                    Intent intent = new Intent();
                    intent.setComponent(runningServiceInfo.service);
                    this.a.stopService(intent);
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "stop service in " + i2, new Object[0]);
                }
            }
        } catch (Exception unused) {
        }
    }

    public int unZipFile(File file, String str, boolean z) throws IOException {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        byte[] bArr = new byte[1024];
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            String name = zipEntryNextElement.getName();
            if (name.contains("../")) {
                throw new IOException("entry name contains illegal characters ../");
            }
            String str2 = str + name;
            File file2 = new File(str2);
            file2.getParentFile().mkdirs();
            if (zipEntryNextElement.isDirectory()) {
                file2.mkdir();
            } else {
                if (z && file2.exists()) {
                    file2.delete();
                }
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                while (true) {
                    int i2 = bufferedInputStream.read(bArr, 0, 1024);
                    if (i2 == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, i2);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
            }
        }
        zipFile.close();
        return 0;
    }

    public boolean updateResourceCfg(Context context) throws Throwable {
        String str;
        String str2;
        BufferedWriter bufferedWriter;
        String str3;
        String str4;
        new HashMap();
        String str5 = getResRoot(context) + "resourceInfo.cfg";
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                String runningVersionCode = getRunningVersionCode(false, true);
                String remoteResPathZip = getRemoteResPathZip(context);
                if (new File(str5).exists()) {
                    Map<String, String> resourceCfg = getResourceCfg(context);
                    str2 = resourceCfg.get("currVer");
                    str4 = resourceCfg.get("currTryFlag");
                } else {
                    str = null;
                    str2 = null;
                }
                if (str == null) {
                    str = str4;
                    String strB = ae.b(this.a, "lebian_module_bwbxinfo", "lastTryFlag", (String) null);
                    if (strB == null || strB.equals(remoteResPathZip)) {
                        str3 = strB;
                        String strB2 = ae.b(this.a, "lebian_module_bwbxinfo", "oldTryFlag", (String) null);
                        str = str;
                        if (strB2 != null && !remoteResPathZip.equals(strB2) && ae.b(this.a, "lebian_module_bwbxinfo", "dlresPatch", false)) {
                            str = str;
                            str = str;
                            str3 = strB2;
                            str3 = strB;
                            str = str3;
                        }
                    } else {
                        str3 = strB;
                        str = str3;
                    }
                    str = str;
                    str = str;
                    str = str;
                    ae.a(this.a, "lebian_module_bwbxinfo", "lastTryFlag");
                    ae.a(this.a, "lebian_module_bwbxinfo", "oldTryFlag");
                }
                if (str2 == null || !runningVersionCode.equals(str2)) {
                    new File(str5).delete();
                    bufferedWriter = new BufferedWriter(new FileWriter(str5));
                    try {
                        bufferedWriter.write("currVer:" + runningVersionCode + "\r\n");
                        bufferedWriter.write("currTryFlag:" + remoteResPathZip + "\r\n");
                        if (str2 != null) {
                            bufferedWriter.write("lastVer:" + str2 + "\r\n");
                        }
                        if (str != null) {
                            bufferedWriter.write("lastTryFlag:" + str + "\r\n");
                        }
                        bufferedWriter.flush();
                    } catch (Exception e) {
                        e = e;
                        bufferedWriter2 = bufferedWriter;
                        e.printStackTrace();
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter2 = bufferedWriter;
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } else {
                    bufferedWriter = null;
                }
                if (bufferedWriter == null) {
                    return true;
                }
                try {
                    bufferedWriter.close();
                    return true;
                } catch (IOException e4) {
                    e4.printStackTrace();
                    return true;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public boolean verifyMp4Md5(String str, String str2) throws NoSuchAlgorithmException, IOException {
        String strComputeFileMd5 = BwbxUtil.computeFileMd5(str);
        com.excelliance.lbsdk.main.g.b.c("GameUtil", "mp4 md5 = " + strComputeFileMd5, new Object[0]);
        try {
            InputStream inputStreamOpen = this.a.getAssets().open("lbres.meta");
            try {
                JSONObject jSONObjectConvertLbresMetaIsToJson = convertLbresMetaIsToJson(inputStreamOpen, str2);
                if (jSONObjectConvertLbresMetaIsToJson == null) {
                    com.excelliance.lbsdk.main.g.b.c("GameUtil", "lbres.meta resolve fail", new Object[0]);
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    return false;
                }
                String string = jSONObjectConvertLbresMetaIsToJson.getString("md5");
                if (string == null || string.equals(strComputeFileMd5)) {
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    return true;
                }
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
                return false;
            } catch (Throwable th) {
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
