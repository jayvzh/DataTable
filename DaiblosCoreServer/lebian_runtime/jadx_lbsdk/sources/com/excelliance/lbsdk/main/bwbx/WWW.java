package com.excelliance.lbsdk.main.bwbx;

import com.excelliance.lbsdk.main.p.h;
import java.io.BufferedWriter;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class WWW {
    public static final String TAG = "WWW";
    public static BufferedWriter bufw;
    private static ArrayList<String> fileNames = new ArrayList<>();
    public static Class<?> mUnityWWWClass;
    private Object mProxyInstacne;

    WWW(int i, String str, byte[] bArr, Map map) {
        String strSubstring;
        this.mProxyInstacne = null;
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b(TAG, "uri1: = " + str, new Object[0]);
        }
        if (!BwbxUtil.isResExtracting()) {
            try {
                URL url = new URL(str);
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b(TAG, "uri2: = " + url.getProtocol(), new Object[0]);
                }
                String protocol = url.getProtocol();
                String path = url.getPath();
                int i2 = 6;
                int iIndexOf = path.indexOf(".apk!/");
                if (iIndexOf <= 0 && (iIndexOf = path.indexOf(".apk/!/")) > 0) {
                    i2 = 7;
                }
                iIndexOf = iIndexOf <= 0 ? path.indexOf(".jar!/") : iIndexOf;
                if (iIndexOf <= 0 && (iIndexOf = path.indexOf(".jar/!/")) > 0) {
                    i2 = 7;
                }
                if ((protocol.equals("jar") && iIndexOf > 0) || protocol.equals("file")) {
                    if (protocol.equals("jar")) {
                        strSubstring = path.substring(iIndexOf + i2);
                    } else {
                        strSubstring = AssetRedirector.gameApkPath + path.substring(path.indexOf("/files/") + 7);
                    }
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b(TAG, "uri3: = " + strSubstring, new Object[0]);
                    }
                    if (!BwbxUtil.fileSetDl.isEmpty()) {
                        com.excelliance.lbsdk.main.g.a().b(strSubstring);
                    }
                    String strA = h.a(strSubstring, false);
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b(TAG, "newContentPath:" + strA, new Object[0]);
                    }
                    if (strA != null && strA.length() > 0) {
                        if (!BwbxUtil.isMultiEnd && !fileNames.contains(strSubstring)) {
                            fileNames.add(strSubstring);
                        }
                        String str2 = "file://" + strA;
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b(TAG, "uri4: = " + str2, new Object[0]);
                        }
                        str = str2;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        Class<?> cls = mUnityWWWClass;
        if (cls != null) {
            try {
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(Integer.TYPE, String.class, byte[].class, Map.class);
                declaredConstructor.setAccessible(true);
                this.mProxyInstacne = declaredConstructor.newInstance(Integer.valueOf(i), str, bArr, map);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static native void doneCallback(int i);

    private static native void errorCallback(int i, String str);

    public static ArrayList<String> getFileNames() {
        ArrayList<String> arrayList = (ArrayList) fileNames.clone();
        fileNames.clear();
        return arrayList;
    }

    private static native boolean headerCallback(int i, String str);

    private static native void progressCallback(int i, float f, float f2, double d, int i2);

    private static native boolean readCallback(int i, byte[] bArr, int i2);

    public final boolean isAlive() {
        if (this.mProxyInstacne != null) {
            try {
                return ((Boolean) mUnityWWWClass.getMethod("isAlive", new Class[0]).invoke(this.mProxyInstacne, new Object[0])).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public final void join() {
        if (this.mProxyInstacne != null) {
            try {
                mUnityWWWClass.getMethod("join", new Class[0]).invoke(this.mProxyInstacne, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        if (this.mProxyInstacne != null) {
            try {
                mUnityWWWClass.getMethod("run", new Class[0]).invoke(this.mProxyInstacne, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        if (this.mProxyInstacne != null) {
            try {
                mUnityWWWClass.getMethod("start", new Class[0]).invoke(this.mProxyInstacne, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
