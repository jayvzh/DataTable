package com.excelliance.lbsdk.main.bwbx;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.loader.ResourcesLoader;
import android.content.res.loader.ResourcesProvider;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import com.excelliance.lbsdk.main.BigFileInfo;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.ao;
import com.excelliance.lbsdk.main.bwbx.so.X86Bridge;
import com.excelliance.lbsdk.main.m;
import com.excelliance.lbsdk.main.n;
import com.excelliance.lbsdk.main.p.h;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class AssetRedirector extends com.excelliance.lbsdk.main.b.f {
    private static final int IO_RELOCATE_MODE_KEEP = 2;
    private static final int IO_RELOCATE_MODE_REDIRECT = 1;
    public static final String SERVICE_NAME = "AssetRedirector";
    private static final String TAG = "AssetRedirector";
    public static String gameApkPath;
    public static String gameCachePath;
    private static Context mAppContext;
    private static Boolean mHasX86;
    private static ResourcesLoader rl;
    private BwbxBridge bwbxBridge;
    private String mAssetDirectory;
    private com.excelliance.lbsdk.main.bwbx.so.a x86Bridge;

    public static void addOverlayResource(final Context context, Application application, final String str, final String[] strArr) {
        if (application != null && Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.excelliance.lbsdk.main.bwbx.AssetRedirector.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    ao.c("AssetRedirector", "onActivityCreated activity:" + activity, new Object[0]);
                    AssetRedirector.addOverlayResource(activity, str, strArr);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    ao.c("AssetRedirector", "onActivityDestroyed activity:" + activity, new Object[0]);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    ao.c("AssetRedirector", "onActivityPaused activity:" + activity, new Object[0]);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPreCreated(Activity activity, Bundle bundle) {
                    AssetRedirector.addOverlayResource(activity, str, strArr);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    ao.c("AssetRedirector", "onActivityResumed activity:" + activity, new Object[0]);
                    if (AssetRedirector.isMyCardActivity(activity.getComponentName().getClassName())) {
                        return;
                    }
                    if (((com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting")).b("use_webview")) {
                        ao.c("AssetRedirector", "use webview", new Object[0]);
                        new WebView(activity);
                    }
                    AssetRedirector.addOverlayResource(activity, str, strArr);
                    if (BwbxUtil.handleAssetmanager(context)) {
                        BwbxUtil.preloadDialog(context);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    ao.c("AssetRedirector", "onActivitySaveInstanceState activity:" + activity, new Object[0]);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    ao.c("AssetRedirector", "onActivityStarted activity:" + activity, new Object[0]);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    ao.c("AssetRedirector", "onActivityStopped activity:" + activity, new Object[0]);
                }
            });
        } else if (context != null) {
            addOverlayResource(context, str, strArr);
        }
    }

    public static void addOverlayResource(Context context, Application application, boolean z, String str) {
        String[] strArrAssetManagerAssetPath = assetManagerAssetPath(context);
        ao.c("AssetRedirector", "addOverlayResource: overlayResPath:" + strArrAssetManagerAssetPath, new Object[0]);
        if (com.excelliance.lbsdk.main.bwbx.compat.a.b()) {
            long length = new File(strArrAssetManagerAssetPath[0]).length();
            ao.c("AssetRedirector", "addOverlayResource: overlayResPath len:" + length, new Object[0]);
            if (length <= 150) {
                return;
            }
        }
        addOverlayResource(context, application, str, strArrAssetManagerAssetPath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addOverlayResource(Context context, String str, String[] strArr) {
        if (context == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            if (rl == null) {
                rl = new ResourcesLoader();
                if (str != null) {
                    try {
                        rl.addProvider(ResourcesProvider.loadFromDirectory(com.excelliance.lbsdk.main.ae.c.c(context), null));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < strArr.length; i++) {
                    if (new File(strArr[i]).exists()) {
                        rl.addProvider(ResourcesProvider.loadFromDirectory(strArr[i], null));
                    }
                }
                ao.c("AssetRedirector", "addLoaders: -------->" + strArr, new Object[0]);
            }
            context.getResources().addLoaders(rl);
            return;
        }
        AssetManager assets = context.getAssets();
        try {
            ao.c("AssetRedirector", "overlay resource path:" + strArr, new Object[0]);
            Method method = assets.getClass().getMethod("addAssetPath", String.class);
            if (str != null) {
                ao.c("AssetRedirector", "addOverlayResource: --------> v4:" + ((Integer) method.invoke(assets, com.excelliance.lbsdk.main.ae.c.c(context))).intValue(), new Object[0]);
            }
            for (String str2 : strArr) {
                ao.c("AssetRedirector", "addOverlayResource: -------->" + ((Integer) method.invoke(assets, str2)).intValue(), new Object[0]);
            }
        } catch (Exception e2) {
            ao.e("AssetRedirector", "addOverlayResource: " + e2, new Object[0]);
        }
    }

    private static String[] assetManagerAssetPath(Context context) {
        if (BwbxUtil.isSmallPkg()) {
            return com.excelliance.lbsdk.main.bwbx.compat.a.b() ? com.excelliance.lbsdk.main.bwbx.compat.a.d() : new String[]{GameUtil.getCachePath(context)};
        }
        return com.excelliance.lbsdk.main.bwbx.compat.a.b() ? new String[]{BwbxUtil.getSdApk(context)} : new String[]{BwbxUtil.getRecordPath(context)};
    }

    private static String[] assetManagerZipPath(Context context) {
        if (BwbxUtil.isSmallPkg()) {
            if (BwbxUtil.handleAssetmanager(context)) {
                return com.excelliance.lbsdk.main.bwbx.compat.a.d();
            }
        } else if (com.excelliance.lbsdk.main.bwbx.compat.a.b()) {
            return new String[]{BwbxUtil.getSdApk(context)};
        }
        return new String[0];
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0047 A[Catch: Exception -> 0x0089, TryCatch #0 {Exception -> 0x0089, blocks: (B:4:0x000b, B:17:0x0061, B:19:0x0070, B:21:0x007d, B:22:0x0083, B:6:0x0011, B:8:0x001a, B:10:0x0034, B:12:0x0047, B:14:0x0054, B:15:0x005a, B:9:0x0030), top: B:27:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:14:0x0054 A[Catch: Exception -> 0x0089, TryCatch #0 {Exception -> 0x0089, blocks: (B:4:0x000b, B:17:0x0061, B:19:0x0070, B:21:0x007d, B:22:0x0083, B:6:0x0011, B:8:0x001a, B:10:0x0034, B:12:0x0047, B:14:0x0054, B:15:0x005a, B:9:0x0030), top: B:27:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:15:0x005a A[Catch: Exception -> 0x0089, TryCatch #0 {Exception -> 0x0089, blocks: (B:4:0x000b, B:17:0x0061, B:19:0x0070, B:21:0x007d, B:22:0x0083, B:6:0x0011, B:8:0x001a, B:10:0x0034, B:12:0x0047, B:14:0x0054, B:15:0x005a, B:9:0x0030), top: B:27:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:6:0x0011 A[Catch: Exception -> 0x0089, TryCatch #0 {Exception -> 0x0089, blocks: (B:4:0x000b, B:17:0x0061, B:19:0x0070, B:21:0x007d, B:22:0x0083, B:6:0x0011, B:8:0x001a, B:10:0x0034, B:12:0x0047, B:14:0x0054, B:15:0x005a, B:9:0x0030), top: B:27:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:8:0x001a A[Catch: Exception -> 0x0089, TryCatch #0 {Exception -> 0x0089, blocks: (B:4:0x000b, B:17:0x0061, B:19:0x0070, B:21:0x007d, B:22:0x0083, B:6:0x0011, B:8:0x001a, B:10:0x0034, B:12:0x0047, B:14:0x0054, B:15:0x005a, B:9:0x0030), top: B:27:0x000b }] */
    /* JADX WARN: Code duplicated, block: B:9:0x0030 A[Catch: Exception -> 0x0089, TryCatch #0 {Exception -> 0x0089, blocks: (B:4:0x000b, B:17:0x0061, B:19:0x0070, B:21:0x007d, B:22:0x0083, B:6:0x0011, B:8:0x001a, B:10:0x0034, B:12:0x0047, B:14:0x0054, B:15:0x005a, B:9:0x0030), top: B:27:0x000b }] */
    /* JADX WARN: Instruction removed from duplicated block: B:8:0x001a, please report this as an issue */
    private static void createManifestXml(Context context, boolean z, String str) {
        File file;
        String cachePath;
        if (z) {
            StringBuilder sb = new StringBuilder();
            if (z) {
                cachePath = BwbxUtil.getRecordPath(context) + "/";
            } else {
                cachePath = GameUtil.getCachePath(context);
            }
            sb.append(cachePath);
            sb.append("AndroidManifest.xml");
            file = new File(sb.toString());
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                if (file.createNewFile()) {
                    ao.c("AssetRedirector", "create success", new Object[0]);
                } else {
                    ao.c("AssetRedirector", "create fail", new Object[0]);
                }
            }
        } else {
            try {
                if (BwbxUtil.isSmallPkg()) {
                    StringBuilder sb2 = new StringBuilder();
                    if (z) {
                        cachePath = BwbxUtil.getRecordPath(context) + "/";
                    } else {
                        cachePath = GameUtil.getCachePath(context);
                    }
                    sb2.append(cachePath);
                    sb2.append("AndroidManifest.xml");
                    file = new File(sb2.toString());
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        if (file.createNewFile()) {
                            ao.c("AssetRedirector", "create success", new Object[0]);
                        } else {
                            ao.c("AssetRedirector", "create fail", new Object[0]);
                        }
                    }
                }
            } catch (Exception e) {
                ao.a("AssetRedirector", e);
                return;
            }
        }
        if (str != null) {
            File file2 = new File(com.excelliance.lbsdk.main.ae.c.c(context), "AndroidManifest.xml");
            if (file2.exists()) {
                return;
            }
            file2.getParentFile().mkdirs();
            if (file2.createNewFile()) {
                ao.c("AssetRedirector", "create success", new Object[0]);
            } else {
                ao.c("AssetRedirector", "create fail", new Object[0]);
            }
        }
    }

    public static native String getDlVisitedFiles(int i);

    public static AssetRedirector getInstance() {
        return (AssetRedirector) LBSdkHelper.application().a("AssetRedirector");
    }

    private static Method getNativeMethod(Class cls, String str, Class cls2) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (declaredMethods == null) {
            return null;
        }
        for (Method method : declaredMethods) {
            if (method.getName().contains(str) && (method.getModifiers() & 256) != 0) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                ao.c("AssetRedirector", String.format("getNativeMethod: found %s(%s)", str, Arrays.toString(parameterTypes)), new Object[0]);
                if (parameterTypes == null) {
                    return null;
                }
                for (Class<?> cls3 : parameterTypes) {
                    if (cls3 == cls2) {
                        return method;
                    }
                }
            }
        }
        return null;
    }

    private BwbxBridge getNotNullBwbxBridge() {
        BwbxBridge bwbxBridge = this.bwbxBridge;
        if (bwbxBridge != null) {
            return bwbxBridge;
        }
        BwbxBridge bwbxBridge2 = new BwbxBridge(this);
        this.bwbxBridge = bwbxBridge2;
        return bwbxBridge2;
    }

    public static native String getVisitedFiles();

    public static boolean hasX86() {
        boolean z;
        String[] strArr;
        Boolean bool = mHasX86;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z2 = true;
        if (mAppContext == null) {
            ao.c("AssetRedirector", "hasX86: mAppContext is null", new Object[0]);
            return true;
        }
        String packageCodePath = null;
        if (Build.VERSION.SDK_INT < 21 || (strArr = mAppContext.getApplicationInfo().splitSourceDirs) == null) {
            packageCodePath = mAppContext.getPackageCodePath();
            z = false;
        } else {
            z = false;
            for (String str : strArr) {
                ao.c("AssetRedirector", "split apk path:" + str, new Object[0]);
                if (str.contains("split_config.x86")) {
                    z = true;
                }
            }
        }
        if (!z && packageCodePath != null && new File(packageCodePath).exists()) {
            try {
                ZipFile zipFile = new ZipFile(packageCodePath);
                try {
                    Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                    while (true) {
                        if (!enumerationEntries.hasMoreElements()) {
                            z2 = z;
                            break;
                        }
                        if (enumerationEntries.nextElement().getName().startsWith("lib/x86")) {
                            ao.c("AssetRedirector", "x86 found", new Object[0]);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mHasX86 = Boolean.valueOf(z2);
        return z2;
    }

    private static void initWowo(Context context, String str, boolean z, boolean z2, int i) throws IOException {
        LBSdkHelper.postLoadSo();
        String packageCodePath = context.getPackageCodePath();
        boolean zHandleAssetmanager = BwbxUtil.handleAssetmanager(context);
        boolean zIsBigAsset = BwbxUtil.isBigAsset(context);
        String strA = b.a();
        nativeInit(WWW.class, packageCodePath, str, !BwbxUtil.isSmallPkg() ? BwbxUtil.getRecordPath() : GameUtil.getCachePath(context), gameCachePath, gameApkPath, z, zHandleAssetmanager, com.excelliance.lbsdk.main.bwbx.compat.a.a(), z2, i, zIsBigAsset, strA);
    }

    private void initWowoForX86(Context context, String str, boolean z, boolean z2, int i) throws IOException {
        LBSdkHelper.postLoadSo();
        String packageCodePath = context.getPackageCodePath();
        boolean zHandleAssetmanager = BwbxUtil.handleAssetmanager(context);
        boolean zIsBigAsset = BwbxUtil.isBigAsset(context);
        String strA = b.a();
        nativeInitHoudini(WWW.class, packageCodePath, str, !BwbxUtil.isSmallPkg() ? BwbxUtil.getRecordPath() : GameUtil.getCachePath(context), gameCachePath, gameApkPath, z, zHandleAssetmanager, com.excelliance.lbsdk.main.bwbx.compat.a.a(), z2, i, zIsBigAsset, strA);
    }

    public static native boolean isLoadX86LibNeeded();

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMyCardActivity(String str) {
        return str.contains("mycard.UI.PDChannelPayActivity") || str.contains("tw.com.mycard.paymentsdk.PSDKActivity") || str.contains("mycard.paymentapp.ui.MainActivity");
    }

    private boolean isRecordMode() {
        return ((com.excelliance.lbsdk.main.l.e) getService("Runtime")).e();
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0021  */
    @TargetApi(GameUtil.NOTIFY_TYPE_SPEED_REPORT)
    private boolean isX86Env(Context context) {
        boolean zContains;
        String str = context.getApplicationInfo().nativeLibraryDir;
        if (str == null) {
            if (Build.VERSION.SDK_INT >= 21) {
                String[] strArr = context.getApplicationInfo().splitSourceDirs;
                if (strArr.length > 0) {
                    zContains = strArr[0].contains("split_config.x86");
                } else {
                    zContains = false;
                }
            } else {
                zContains = false;
            }
        } else if (str.endsWith("x86") || str.endsWith("x86_64")) {
            zContains = true;
        } else {
            zContains = false;
        }
        ao.c("AssetRedirector", "game abi:" + str, new Object[0]);
        return zContains;
    }

    public static String[] jniGetHolePaths() {
        return assetManagerZipPath(mAppContext);
    }

    public static native void listBigFileNames(Map<String, BigFileInfo> map, Class cls);

    public static native void loadNewIdx();

    public static native void nativeAMRHook(Method method);

    public static native void nativeAMRHookHoudini(Method method);

    private native void nativeAttachBaseContext(Context context);

    private native boolean nativeGetBigVisistedStat(long[] jArr);

    public static native void nativeInit(Class<?> cls, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4, int i, boolean z5, String str6);

    @Deprecated
    private static native void nativeInitHoudini(Class<?> cls, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, boolean z4, int i, boolean z5, String str6);

    private native void nativeSetLogLevel(int i);

    public static native boolean nextIncompeleteDataRegion(String str, long[] jArr);

    public static String onAccessAsset(String str, boolean z) {
        ao.c("AssetRedirector", "onAccessFile = " + str + " isp = " + z, new Object[0]);
        if (str.startsWith(BwbxUtil.getSdPath()) && !BwbxUtil.isUnity()) {
            return null;
        }
        String str2 = gameCachePath;
        if (str2 != null && gameApkPath != null && str.contains(str2)) {
            String strSubstring = str.substring(str.indexOf("/files/") + 7);
            if (strSubstring.startsWith("/")) {
                strSubstring = strSubstring.substring(1);
            }
            str = gameApkPath + strSubstring;
            ao.c("AssetRedirector", "filePath:" + str, new Object[0]);
        }
        if (BwbxUtil.isUnity()) {
            if (str.contains("assets/")) {
                str = str.substring(str.indexOf("assets/"));
            }
            if (BwbxUtil.isResExtracting() && !str.startsWith("assets/bin/")) {
                ao.c("AssetRedirector", "extract...", new Object[0]);
                return null;
            }
            if (str.endsWith(".resG") || str.endsWith(".resS")) {
                return null;
            }
        }
        ((com.excelliance.lbsdk.main.l.b) LBSdkHelper.application().a("Download")).b();
        String strA = h.a(str, z);
        ao.c("AssetRedirector", "onAccessAsset ret:" + strA, new Object[0]);
        return strA;
    }

    public static byte[] onDownloadMissingRangeData(String str, long j, long j2, int i, int i2) throws InterruptedException, NoSuchAlgorithmException, IOException {
        ao.c("AssetRedirector", "onDownloadMissingRangeData arcname:" + str + " pos:" + j2 + " size:" + i, new Object[0]);
        byte[] bArr = new byte[i];
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        sb.append(i2);
        BwbxUtil.getMissingFile(sb.toString(), bArr, j, i, false);
        return bArr;
    }

    public static native void recordWwwVisitedFile(String str);

    public static native void resetIncompleteBlockIndex(String str, int i);

    public static native boolean saveFile(String str, String str2);

    public static native void setCached(String str, String str2, boolean z);

    public static native void setDeepLearnStop(boolean z);

    public static native void setDownloadFinished(boolean z, boolean z2);

    public static native void setHuListPath(String str);

    public static native void setUseCache(boolean z);

    public static native void setUseMp4Download(int i);

    public static native void startLogging();

    public static native void stopLogging();

    public static native boolean writeData(String str, byte[] bArr, long j, int i);

    protected void attachBaseContext(Context context) {
        nativeAttachBaseContext(context);
        nativeAttachBridge(getNotNullBwbxBridge());
    }

    public boolean getBigVisistedStat(long[] jArr) {
        return nativeGetBigVisistedStat(jArr);
    }

    /* JADX WARN: Code duplicated, block: B:81:0x0199  */
    public void init(Context context, Application application) throws IOException {
        String str;
        String str2;
        boolean z;
        String str3;
        String str4;
        String str5;
        if (mAppContext != null) {
            throw new RuntimeException("AssetRedirector has init!");
        }
        mAppContext = context;
        if (m.a(context, "LOADING_LOCAL_RES", false)) {
            com.excelliance.lbsdk.main.e.b bVar = (com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting");
            gameCachePath = bVar.a();
            gameApkPath = bVar.b();
        }
        String strD = com.excelliance.lbsdk.main.ae.c.d(context);
        boolean zIsRecordMode = isRecordMode();
        boolean zHandleStrncpy = zIsRecordMode ? true : BwbxUtil.handleStrncpy(context);
        createManifestXml(context, zIsRecordMode, strD);
        int engine = BwbxUtil.getEngine(context);
        ao.c("AssetRedirector", "record:" + zIsRecordMode + " gameCachePath:" + gameCachePath + " gameApkPath:" + gameApkPath, new Object[0]);
        if (!zIsRecordMode && !BwbxUtil.isSmallPkg()) {
            ao.c("AssetRedirector", "is full pkg", new Object[0]);
            return;
        }
        boolean zHandleAssetmanager = BwbxUtil.handleAssetmanager(context);
        boolean z2 = engine == 2 || engine == 7;
        boolean z3 = engine == 7;
        boolean z4 = n.b() && isX86Env(context);
        String strA = b.a();
        String str6 = "";
        for (String str7 : an.a().g()) {
            str6 = (str6 + str7) + ";";
        }
        LBSdkHelper.preLoadSo();
        ao.c("AssetRedirector", "obb Path:" + strA, new Object[0]);
        if (!z3 && !z4) {
            try {
                System.loadLibrary("lbfb");
                if (strD != null) {
                    setHuListPath(strD);
                }
                attachBaseContext(context);
                if (!z2) {
                    initWowo(context, str6, zIsRecordMode, zHandleStrncpy, engine);
                }
            } catch (Throwable th) {
                ao.e("AssetRedirector", "load fail", th, new Object[0]);
            }
        }
        if (n.b()) {
            try {
                System.loadLibrary("lbfb_x86");
                if (strD != null) {
                    setHuListPath(strD);
                }
                this.x86Bridge = new X86Bridge();
                this.x86Bridge.a(context);
                this.x86Bridge.a(getNotNullBwbxBridge());
                if (z2) {
                    str = "load fail";
                    str2 = str6;
                    z = zHandleAssetmanager;
                    str3 = "AssetRedirector";
                } else {
                    str = "load fail";
                    str2 = str6;
                    z = zHandleAssetmanager;
                    str3 = "AssetRedirector";
                    try {
                        initWowoForX86(context, str6, zIsRecordMode, zHandleStrncpy, engine);
                    } catch (Throwable th2) {
                        th = th2;
                        str4 = str3;
                        ao.c(str4, "load x86 fail", th, new Object[0]);
                    }
                }
                str4 = str3;
            } catch (Throwable th3) {
                th = th3;
                str = "load fail";
                str2 = str6;
                z = zHandleAssetmanager;
                str3 = "AssetRedirector";
            }
        } else {
            str = "load fail";
            str2 = str6;
            z = zHandleAssetmanager;
            str4 = "AssetRedirector";
        }
        if (!z3 || z4) {
            str5 = str2;
        } else {
            try {
                System.loadLibrary("lbfb");
                if (strD != null) {
                    setHuListPath(strD);
                }
                attachBaseContext(context);
                if (z2) {
                    str5 = str2;
                } else {
                    str5 = str2;
                    try {
                        initWowo(context, str5, zIsRecordMode, zHandleStrncpy, engine);
                    } catch (Throwable th4) {
                        th = th4;
                        ao.c(str4, str, th, new Object[0]);
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                str5 = str2;
            }
        }
        if (z2) {
            initWowo(context, str5, zIsRecordMode, zHandleStrncpy, engine);
        }
        if (engine != 1 && engine != 5) {
            StringBuilder sb = new StringBuilder();
            sb.append("hookam: ");
            boolean z5 = z;
            sb.append(z5);
            ao.c(str4, sb.toString(), new Object[0]);
            if (z5) {
                addOverlayResource(context, application, zIsRecordMode, strD);
                return;
            }
            return;
        }
        if (z) {
            addOverlayResource(context, application, zIsRecordMode, strD);
            Method nativeMethod = getNativeMethod(AssetManager.class, "addAssetPath", String.class);
            ao.c(str4, "Found assetAsetsPath" + nativeMethod, new Object[0]);
            if (isLoadX86LibNeeded()) {
                nativeAMRHookHoudini(nativeMethod);
            } else {
                nativeAMRHook(nativeMethod);
            }
        }
    }

    protected native void nativeAttachBridge(Object obj);

    public void setLogLevel(int i) {
        nativeSetLogLevel(i);
        com.excelliance.lbsdk.main.bwbx.so.a aVar = this.x86Bridge;
        if (aVar != null) {
            aVar.a(i);
        }
    }
}
