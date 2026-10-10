package com.excelliance.lbsdk.main.bwbx;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.util.Xml;
import com.excelliance.lbsdk.main.AbTestManager;
import com.excelliance.lbsdk.main.BGService;
import com.excelliance.lbsdk.main.BigFileInfo;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.LoadLib;
import com.excelliance.lbsdk.main.aa;
import com.excelliance.lbsdk.main.ae;
import com.excelliance.lbsdk.main.ak;
import com.excelliance.lbsdk.main.al;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.m;
import com.excelliance.lbsdk.main.p.h;
import com.excelliance.lbsdk.main.q;
import com.excelliance.lbsdk.main.s;
import com.excelliance.lbsdk.main.t;
import com.excelliance.lbsdk.main.w;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.http.conn.ConnectTimeoutException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class BwbxUtil {
    public static final String ACTION_CANCEL_DOWNLOAD_ALL = ".action.CANCEL_DOWNLOAD_ALL";
    public static final String ACTION_DATA = "data";
    public static final String ACTION_DOWNLOAD_ALL = ".action.DOWNLOAD_ALL";
    public static final String ACTION_DOWNLOAD_ALL_LATER = ".action.DOWNLOAD_ALL_LATER";
    public static final String ACTION_DOWNLOAD_CONTROL = ".action.DOWNLOAD_CONTROL";
    public static final String ACTION_DOWNLOAD_FULL_RES_IN_MOBILE = ".action.DOWNLOAD_FULL_RES_IN_MOBILE";
    public static final String ACTION_DOWNLOAD_MISSING_PROGRESS = ".action.DOWNLOAD_MISSING_PROGRESS";
    public static final String ACTION_DOWNLOAD_PROPORTION = ".action.downloadProportionInfo";
    public static final String ACTION_EXTRACT_FINISHED = ".action.EXTRACT_FINISHED";
    public static final String ACTION_FINISHED = ".action.FINISHED";
    public static final String ACTION_FLOAT_WINDOW = ".action.ACTION_FLOAT_WINDOW";
    public static final String ACTION_PLAT_DO = "com.excelliance.open.action.PLAT_DO";
    public static final String ACTION_PRE_FETCH = ".action.PRE_FETCH";
    public static final String ACTION_PROGRESS = "progress";
    public static final String ACTION_QUERY_RETRY = ".action.QUERY_RETRY";
    public static final String ACTION_QUERY_USER = ".action.QUERY_USER";
    public static final String ACTION_RECORD_SIZE = ".action.RECORD_SIZE";
    public static final String ACTION_SIZE = "size";
    public static final String ACTION_TYPE = "type";
    public static final String ACTION_URL = "url";
    public static final String ACTION_USER_ACTION = ".action.USER_ACTION";
    public static final String ACTION_USER_CLICK = ".action.USER_CLICK";
    public static final String ACTION_USER_RETRY = ".action.USER_RETRY";
    public static final String AL = "rcdnal";
    public static final String AWS = "rcdnaws";
    public static final String CDN_B = "cdnbres";
    public static final String CDN_M = "cdnmres";
    public static final int COCOS = 2;
    public static final int DL_ALREADY = -3;
    public static final int DL_INIT_NOT_FINISH = -5;
    public static final int DL_IS_FULL_PKG = -2;
    public static final int DL_NET_ERROR = -1;
    public static final int DL_SPACE_NOT_ENOUGH = -4;
    public static final int DL_START = 1;
    public static final int DL_TIME_OUT = -7;
    public static final int DL_UNKNOWN = -6;
    public static final int LAYA = 8;
    public static final String NO_EXIST = "lebian_no_exist";
    public static final String SECOND_CN = "cn";
    public static final String SECOND_OVS = "hk";
    public static final String SECOND_SG = "sg";
    public static final String SECOND_TW = "tw";
    public static final int UE3 = 6;
    public static final int UE4 = 3;
    public static final String UKD = "rcdnukd";
    public static final int UNITY = 1;
    public static final int UNITY_BIG_ASSET = 5;
    private static short V = 0;
    public static final int WANMEI = 4;
    public static final int WANMEI_XIAOAO = 7;
    public static final String WS = "rcdnws";
    private static ZipFile a = null;
    private static HashMap<String, com.excelliance.lbsdk.main.ae.b> aG = null;
    private static HashMap<String, BigFileInfo> aI = null;
    private static f aO = null;
    private static int aP = 0;
    private static com.excelliance.lbsdk.main.p.c aQ = null;
    private static long al = 0;
    private static ZipFile b = null;
    private static String c = null;
    public static int count = 0;
    private static Context d = null;
    public static volatile boolean downloadAllDataConn = false;
    public static volatile boolean downloadAllInterrupt = false;
    public static volatile boolean downloadAllReturnFlag = false;
    public static volatile boolean downloadAllSuccess = false;
    private static volatile String e = null;
    private static String f = null;
    public static boolean failure = false;
    private static volatile String g;
    private static volatile String h;
    private static volatile String i;
    public static BufferedWriter newContentBufw;
    public static FileWriter newContentFw;
    private static Object j = new Object();
    private static HashMap<Thread, Boolean> k = new HashMap<>();
    private static Object l = new Object();
    private static Object m = new Object();
    private static Map<String, String> n = new TreeMap();
    public static List<String> fileSetDl = new ArrayList();
    public static List<String> localRecord = new ArrayList();
    private static List<String> o = new ArrayList();
    private static boolean p = true;
    private static volatile boolean q = false;
    private static volatile boolean r = false;
    private static volatile boolean s = false;
    private static volatile int t = 0;
    private static String u = "-1";
    private static volatile String v = null;
    private static String w = null;
    public static boolean downloadLater = false;
    private static boolean x = false;
    public static int ACTION_TYPE_QUERY = 1;
    public static int ACTION_TYPE_PROGRESS = 2;
    public static int ACTION_TYPE_DOWNLOAD_FULL = 3;
    public static int ACTION_TYPE_CHECK_FOREGROUND = 4;
    public static int ACTION_TYPE_CANCEL_CHECK_FOREGROUND = 5;
    public static int ACTION_TYPE_CLEAN_EXIT = 6;
    public static int ACTION_TYPE_DOWNLOAD_MISSING = 7;
    public static int ACTION_TYPE_SWITCH_FULL = 8;
    public static int ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI = 9;
    public static int ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER = 10;
    public static int ACTION_TYPE_DOWNLOAD_ALL_RES = 11;
    public static int ACTION_TYPE_PRELOAD_DIALOG = 12;
    public static int ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER_INTERFACE = 13;
    public static int ACTION_TYPE_REMAINGING_RES_DOWNLOADING = 14;
    private static int y = 0;
    private static int z = 1;
    private static int A = 2;
    private static int B = 3;
    private static int C = 10000;
    private static int D = 60000;
    private static AtomicLong E = new AtomicLong(0);
    private static AtomicInteger F = new AtomicInteger(0);
    private static AtomicInteger G = new AtomicInteger(0);
    private static AtomicLong H = new AtomicLong(0);
    private static volatile boolean I = false;
    private static volatile int J = 0;
    private static long K = 0;
    private static long L = 0;
    private static long M = 0;
    private static long N = 0;
    public static AtomicLong dlAllTime = new AtomicLong(0);
    public static AtomicLong dlAllSize = new AtomicLong(0);
    public static boolean stopSpeed = false;
    private static boolean O = false;
    private static boolean P = true;
    private static boolean Q = true;
    private static boolean R = false;
    private static Object S = new Object();
    private static volatile int T = 0;
    public static int ERR_NETWORK = -2;
    public static int ERR_SPACE_NOT_ENOUGH = -3;
    public static int ERR_TIMEOUT = -4;
    public static int ERR_MD5_MIS = -5;
    public static int ERR_RENAME_FAIL = -6;
    public static int ERR_RET_CODE = -7;
    private static AtomicBoolean U = new AtomicBoolean(false);
    private static long W = -1;
    private static long X = -1;
    private static long Y = -1;
    private static long Z = -1;
    private static String aa = null;
    private static int ab = 0;
    private static volatile int ac = 0;
    private static int ad = 10;
    private static volatile int ae = 0;
    private static int af = 10;
    private static volatile int ag = 0;
    private static int ah = 10;
    public static int DIALOG_TYPE_FIRST = 1;
    public static int DIALOG_TYPE_SECOND = 2;
    public static int DIALOG_TYPE_OLD_USER = 3;
    public static int DIALOG_TYPE_OLD_USER_FIRST = 4;
    public static int DIALOG_TYPE_DOWNLOAD_FAILED_UNKNOWN = 5;
    public static int DIALOG_TYPE_DOWNLOAD_FAILED_MD5_MIS = 6;
    public static int DIALOG_TYPE_DOWNLOAD_FAILED_ERR_NETWORK = 7;
    public static int DIALOG_TYPE_DOWNLOAD_FAILED_TIMOUT = 8;
    public static int DIALOG_TYPE_DOWNLOAD_FAILED_SPACE_NOT_ENOUGH = 9;
    public static int DIALOG_TYPE_DOWNLOAD_FAILED_RENAME_FAIL = 10;
    public static int DIALOG_TYPE_SWITCH_MOBILE = 11;
    public static int DIALOG_TYPE_SECOND_INTERFACE = 12;
    public static int DIALOG_TYPE_REMAINING_RES_DOWNLOADING = 13;
    public static int DIALOG_TYPE_NET_POOR = 14;
    private static String ai = null;
    private static volatile String aj = null;
    private static int ak = 0;
    private static boolean am = true;
    private static volatile boolean an = false;
    private static boolean ao = true;
    private static boolean ap = true;
    private static volatile boolean aq = false;
    private static c ar = null;
    public static boolean isMultiEnd = true;
    public static volatile boolean isSwitch = false;
    public static boolean gIsUnity = true;
    private static int as = -1;
    public static boolean userApproveDownload = false;
    private static String at = null;
    private static Object au = new Object();
    private static volatile boolean av = false;
    private static CopyOnWriteArrayList<String> aw = new CopyOnWriteArrayList<>();
    private static long ax = 0;
    private static long ay = 0;
    private static AtomicBoolean az = new AtomicBoolean(false);
    private static boolean aA = false;
    private static boolean aB = false;
    private static long aC = 0;
    private static int aD = 1;
    public static String DOWNLOAD_MP4_FILE_NAME = "assets/lbres.rdata.mp4";
    public static String MP4_FILE_NAME = "assets/lbres/lbres.rdata.mp4";
    public static int gEngine = -1;
    private static boolean aE = false;
    private static final Object aF = new Object();
    public static boolean needSoOut = false;
    private static volatile boolean aH = false;
    private static AtomicInteger aJ = new AtomicInteger(0);
    private static AtomicInteger aK = new AtomicInteger(0);
    public static long weakDialogLastShowTime = 0;
    public static boolean isPrivacyBwbxInit = false;
    private static boolean aL = false;
    private static AtomicBoolean aM = new AtomicBoolean(false);
    private static d aN = new d();
    private static Handler aR = new Handler(((com.excelliance.lbsdk.main.l.a) LBSdkHelper.application().a("AsyncTask")).a()) { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.1
        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            Intent intent;
            int i2;
            Context context;
            int i3;
            boolean z2;
            boolean z3;
            Intent intent2;
            switch (message.what) {
                case 1:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "hide progress", new Object[0]);
                    boolean unused = BwbxUtil.O = false;
                    intent = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                    intent.putExtra(BwbxUtil.ACTION_TYPE, BwbxUtil.ACTION_TYPE_PROGRESS);
                    intent.putExtra(BwbxUtil.ACTION_PROGRESS, -1);
                    BwbxUtil.b(BwbxUtil.d, intent);
                    break;
                case 2:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "download full", new Object[0]);
                    BwbxUtil.E.set(0L);
                    intent = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                    i2 = BwbxUtil.ACTION_TYPE_DOWNLOAD_MISSING;
                    intent.putExtra(BwbxUtil.ACTION_TYPE, i2);
                    BwbxUtil.b(BwbxUtil.d, intent);
                    break;
                case 3:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "switch full", new Object[0]);
                    intent = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                    i2 = BwbxUtil.ACTION_TYPE_SWITCH_FULL;
                    intent.putExtra(BwbxUtil.ACTION_TYPE, i2);
                    BwbxUtil.b(BwbxUtil.d, intent);
                    break;
                case 4:
                    if (!ae.b(BwbxUtil.d, "lebian_module_dloadinfo", "success", false)) {
                        if (!al.b(BwbxUtil.d)) {
                            BwbxUtil.aR.postDelayed(BwbxUtil.aV, BwbxUtil.C);
                        } else {
                            long j2 = new File(GameUtil.getCachePath(BwbxUtil.d, String.valueOf(BwbxUtil.t))).exists() ? BwbxUtil.H.get() : 0L;
                            float fB = BwbxUtil.b(BwbxUtil.d, true) / BwbxUtil.b(BwbxUtil.d, false);
                            long totalOrigSize = (long) ((BwbxUtil.getTotalOrigSize(BwbxUtil.d, false) - j2) * fB);
                            boolean zExists = new File(GameUtil.getZipPath(BwbxUtil.d)).exists();
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloaded:" + j2 + " arg2:" + message.arg2 + " ratio:" + fB + " rSize:" + totalOrigSize + " zipExists:" + zExists, new Object[0]);
                            if (totalOrigSize > 0) {
                                if (message.arg2 == 1) {
                                    if (!aa.b(BwbxUtil.d)) {
                                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "network not available", new Object[0]);
                                    } else {
                                        if (zExists) {
                                            totalOrigSize = BwbxUtil.b(BwbxUtil.d, true) - (new File(GameUtil.getZipPath(BwbxUtil.d)).exists() ? BwbxUtil.getDownloadedSize(BwbxUtil.d) : 0L);
                                        }
                                        Intent intent3 = new Intent("com.excelliance.open.action.fw");
                                        intent3.setPackage(BwbxUtil.d.getPackageName());
                                        intent3.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
                                        intent3.putExtra("dialogtype", 4);
                                        intent3.putExtra(BwbxUtil.ACTION_SIZE, totalOrigSize);
                                        intent3.putExtra("zipExists", zExists);
                                        intent3.putExtra("dataConnection", !aa.a(BwbxUtil.d));
                                        intent3.putExtra("extra.lbsdk.hostintent", true);
                                        intent3.putExtra("behavior", AbTestManager.getInstance(BwbxUtil.d).getBehavior(0));
                                        BwbxUtil.b(intent3);
                                        context = BwbxUtil.d;
                                        i3 = BwbxUtil.DIALOG_TYPE_FIRST;
                                    }
                                    break;
                                } else if (message.arg2 == 2) {
                                    Intent intent4 = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                                    intent4.putExtra(BwbxUtil.ACTION_TYPE, BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI);
                                    intent4.putExtra(BwbxUtil.ACTION_SIZE, totalOrigSize);
                                    BwbxUtil.b(BwbxUtil.d, intent4);
                                    context = BwbxUtil.d;
                                    i3 = BwbxUtil.DIALOG_TYPE_SWITCH_MOBILE;
                                } else if (message.arg2 == 3) {
                                    if (m.a(BwbxUtil.d, "DOWNLOAD_FULL_AFTER_SECOND_DIALOG_BWBX", false) && BwbxUtil.checkDlFullRes(BwbxUtil.d, false, false, false, 1)) {
                                        totalOrigSize = BwbxUtil.b(BwbxUtil.d, true);
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                    if (zExists) {
                                        totalOrigSize = BwbxUtil.b(BwbxUtil.d, true) - (new File(GameUtil.getZipPath(BwbxUtil.d)).exists() ? BwbxUtil.getDownloadedSize(BwbxUtil.d) : 0L);
                                    }
                                    Intent intent5 = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                                    intent5.putExtra(BwbxUtil.ACTION_TYPE, BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER);
                                    intent5.putExtra(BwbxUtil.ACTION_SIZE, totalOrigSize);
                                    intent5.putExtra("zipExists", zExists);
                                    intent5.putExtra("downloadZip", z3);
                                    intent5.putExtra("dataConnection", !aa.a(BwbxUtil.d));
                                    BwbxUtil.b(BwbxUtil.d, intent5);
                                    context = BwbxUtil.d;
                                    i3 = BwbxUtil.DIALOG_TYPE_SECOND;
                                } else if (message.arg2 == 4) {
                                    if (m.a(BwbxUtil.d, "DOWNLOAD_FULL_AFTER_SECOND_DIALOG_BWBX", false) && BwbxUtil.checkDlFullRes(BwbxUtil.d, false, false, false, 1)) {
                                        totalOrigSize = BwbxUtil.b(BwbxUtil.d, true);
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (zExists) {
                                        totalOrigSize = BwbxUtil.b(BwbxUtil.d, true) - (new File(GameUtil.getZipPath(BwbxUtil.d)).exists() ? BwbxUtil.getDownloadedSize(BwbxUtil.d) : 0L);
                                    }
                                    Intent intent6 = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                                    intent6.putExtra(BwbxUtil.ACTION_TYPE, BwbxUtil.ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER_INTERFACE);
                                    intent6.putExtra(BwbxUtil.ACTION_SIZE, totalOrigSize);
                                    intent6.putExtra("zipExists", zExists);
                                    intent6.putExtra("downloadZip", z2);
                                    intent6.putExtra("dataConnection", !aa.a(BwbxUtil.d));
                                    BwbxUtil.b(BwbxUtil.d, intent6);
                                    context = BwbxUtil.d;
                                    i3 = BwbxUtil.DIALOG_TYPE_SECOND_INTERFACE;
                                } else if (message.arg2 == 5) {
                                    Intent intent7 = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                                    intent7.putExtra(BwbxUtil.ACTION_TYPE, BwbxUtil.ACTION_TYPE_REMAINGING_RES_DOWNLOADING);
                                    intent7.putExtra(BwbxUtil.ACTION_SIZE, totalOrigSize);
                                    BwbxUtil.b(BwbxUtil.d, intent7);
                                    context = BwbxUtil.d;
                                    i3 = BwbxUtil.DIALOG_TYPE_REMAINING_RES_DOWNLOADING;
                                }
                                BwbxUtil.dialogStatistics(context, i3);
                            }
                        }
                        break;
                    }
                    break;
                case 5:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "download all res msg.arg2:" + message.arg2, new Object[0]);
                    if (message.obj != null) {
                        BwbxUtil.downloadFullRes(BwbxUtil.d, message.arg2, ((Boolean) message.obj).booleanValue());
                    } else {
                        BwbxUtil.downloadFullRes(BwbxUtil.d, message.arg2);
                    }
                    break;
                case 6:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "show choose", new Object[0]);
                    long downloadedSize = new File(GameUtil.getZipPath(BwbxUtil.d)).exists() ? BwbxUtil.getDownloadedSize(BwbxUtil.d) : 0L;
                    intent2 = new Intent("com.excelliance.open.action.fw");
                    intent2.setPackage(BwbxUtil.d.getPackageName());
                    intent2.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
                    intent2.putExtra("dialogtype", 5);
                    intent2.putExtra(BwbxUtil.ACTION_SIZE, BwbxUtil.b(BwbxUtil.d, true) - downloadedSize);
                    intent2.putExtra("extra.lbsdk.hostintent", true);
                    BwbxUtil.b(intent2);
                    break;
                case 7:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "preload dialog", new Object[0]);
                    intent = new Intent(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_QUERY_USER);
                    i2 = BwbxUtil.ACTION_TYPE_PRELOAD_DIALOG;
                    intent.putExtra(BwbxUtil.ACTION_TYPE, i2);
                    BwbxUtil.b(BwbxUtil.d, intent);
                    break;
                case 8:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "switch dl", new Object[0]);
                    GameUtil.switchToDownloadFullRes(BwbxUtil.d, 1, false);
                    break;
                case GameUtil.NOTIFY_TYPE_BWBX_USE_BK_CDN /* 10 */:
                    Intent intent8 = new Intent("com.excelliance.open.action.fw");
                    intent8.setPackage(BwbxUtil.d.getPackageName());
                    intent8.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
                    intent8.putExtra("requiredSize", ((Long) message.obj).longValue());
                    intent8.putExtra("dialogtype", 9);
                    intent8.putExtra("extra.lbsdk.hostintent", true);
                    BwbxUtil.b(intent8);
                    break;
                case GameUtil.NOTIFY_TYPE_DOWNLOAD_RES_MD5_ERROR /* 11 */:
                    intent2 = new Intent("com.excelliance.open.action.fw");
                    intent2.setPackage(BwbxUtil.d.getPackageName());
                    intent2.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
                    intent2.putExtra("dialogtype", 10);
                    intent2.putExtra("extra.lbsdk.hostintent", true);
                    BwbxUtil.b(intent2);
                    break;
                case 13:
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "md5 isn't ok, download all res", new Object[0]);
                    File file = new File(BwbxUtil.v, "assets/lbres");
                    ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "success", false);
                    GameUtil.cleanTwiceLoadFlag(BwbxUtil.d);
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "md5 isn't ok, download all res delete:" + GameUtil.deleteDir(file), new Object[0]);
                    try {
                        Intent intent9 = new Intent("com.excelliance.open.action.fw");
                        intent9.setPackage(BwbxUtil.d.getPackageName());
                        intent9.setComponent(new ComponentName(BwbxUtil.d, "com.excelliance.lbsdk.main.BGService"));
                        intent9.putExtra(BwbxUtil.ACTION_TYPE, 1);
                        intent9.putExtra("extra.lbsdk.hostintent", true);
                        GameUtil.startService(intent9, BwbxUtil.d);
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "restartGame start service", new Object[0]);
                    } catch (Exception e2) {
                        com.excelliance.lbsdk.main.g.b.d("BwbxUtil", "startService e=" + e2, new Object[0]);
                        return;
                    }
                    break;
            }
        }
    };
    private static final Runnable aS = new Runnable() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.8
        @Override // java.lang.Runnable
        public void run() {
            BwbxUtil.aR.removeCallbacks(BwbxUtil.aS);
            BwbxUtil.transferMissing(BwbxUtil.ak);
            int unused = BwbxUtil.ak = 0;
            if (System.currentTimeMillis() - BwbxUtil.al < GameUtil.getMissingFileTime()) {
                BwbxUtil.aR.postDelayed(BwbxUtil.aS, GameUtil.getIntervalTime());
            }
        }
    };
    private static final Runnable aT = new Runnable() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.9
        @Override // java.lang.Runnable
        public void run() {
            BwbxUtil.aR.removeCallbacks(BwbxUtil.aT);
            if (aa.a(BwbxUtil.d)) {
                boolean unused = BwbxUtil.ao = false;
                BwbxUtil.downloadAll(true);
                return;
            }
            if (aa.b(BwbxUtil.d)) {
                if (BwbxUtil.an) {
                    BwbxUtil.downloadAll(false);
                    return;
                } else if (!BwbxUtil.ap) {
                    BwbxUtil.downloadAll(true);
                    boolean unused2 = BwbxUtil.ap = true;
                    return;
                } else if (BwbxUtil.aq) {
                    BwbxUtil.downloadAll(true);
                }
            }
            BwbxUtil.aR.postDelayed(BwbxUtil.aT, 5000L);
        }
    };
    private static final Runnable aU = new Runnable() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.10
        @Override // java.lang.Runnable
        public void run() {
            BwbxUtil.aR.removeCallbacks(BwbxUtil.aU);
            if (al.c(BwbxUtil.d) || !al.b(BwbxUtil.d)) {
                BwbxUtil.aR.postDelayed(BwbxUtil.aU, 500L);
                return;
            }
            com.excelliance.lbsdk.main.g.b.e("BwbxUtil", "mIsForegroundCallBack", new Object[0]);
            Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
            intent.setPackage(BwbxUtil.d.getPackageName());
            intent.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
            intent.putExtra("cancel", true);
            intent.putExtra("extra.lbsdk.hostintent", true);
            BwbxUtil.b(intent);
            Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
            intent2.setPackage(BwbxUtil.d.getPackageName());
            intent2.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
            intent2.putExtra("dlsType", 2);
            intent2.putExtra("downloadingCheck", true);
            intent2.putExtra("extra.lbsdk.hostintent", true);
            BwbxUtil.b(intent2);
            if (ae.b(BwbxUtil.d, "lebian_module_bwbxinfo", "dlresPatch", false)) {
                Intent intent3 = new Intent("com.excelliance.open.action.bwbxdla");
                intent3.setPackage(BwbxUtil.d.getPackageName());
                intent3.setComponent(new ComponentName(BwbxUtil.d.getPackageName(), BGService.class.getName()));
                intent3.putExtra("dlsType", 2);
                intent3.putExtra("resPatchCheck", true);
                intent3.putExtra("extra.lbsdk.hostintent", true);
                BwbxUtil.b(intent3);
            }
        }
    };
    private static final Runnable aV = new Runnable() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.11
        @Override // java.lang.Runnable
        public void run() {
            BwbxUtil.aR.removeCallbacks(BwbxUtil.aV);
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "mUpdateCallBack enter", new Object[0]);
            if (!al.b(BwbxUtil.d)) {
                BwbxUtil.aR.postDelayed(BwbxUtil.aV, BwbxUtil.C);
                return;
            }
            if (aa.b(BwbxUtil.d)) {
                if (aa.a(BwbxUtil.d) || BwbxUtil.an || BwbxUtil.aH) {
                    BwbxUtil.downloadAll(true);
                    return;
                }
                BwbxUtil.aR.removeMessages(4);
                Message messageObtainMessage = BwbxUtil.aR.obtainMessage(4);
                messageObtainMessage.arg2 = 2;
                BwbxUtil.aR.sendMessageDelayed(messageObtainMessage, 0L);
            }
        }
    };
    private static final Runnable aW = new Runnable() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.12
        @Override // java.lang.Runnable
        public void run() {
            BwbxUtil.aR.removeCallbacks(BwbxUtil.aW);
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "mDownloadLaterCallBack enter downloadLater:" + BwbxUtil.downloadLater, new Object[0]);
            if (BwbxUtil.downloadLater && aa.b(BwbxUtil.d)) {
                BwbxUtil.downloadLater = false;
                BwbxUtil.aR.removeMessages(4);
                Message messageObtainMessage = BwbxUtil.aR.obtainMessage(4);
                messageObtainMessage.arg2 = 3;
                BwbxUtil.aR.sendMessageDelayed(messageObtainMessage, 0L);
            }
        }
    };
    private static BroadcastReceiver aX = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws Throwable {
            String action = intent.getAction();
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "action=" + action, new Object[0]);
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_DOWNLOAD_ALL)) {
                boolean unused = BwbxUtil.an = true;
                BwbxUtil.U.set(false);
                BwbxUtil.userApproveDownload = true;
                BwbxUtil.downloadAll(false);
                return;
            }
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_DOWNLOAD_ALL_LATER)) {
                boolean unused2 = BwbxUtil.an = false;
                if (intent.getBooleanExtra("rmFirst", false)) {
                    BwbxUtil.aR.removeMessages(4);
                    boolean unused3 = BwbxUtil.aB = true;
                }
                BwbxUtil.aR.removeCallbacks(BwbxUtil.aT);
                BwbxUtil.aR.postDelayed(BwbxUtil.aT, 5000L);
                BwbxUtil.U();
                return;
            }
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_CANCEL_DOWNLOAD_ALL)) {
                BwbxUtil.U.set(true);
                return;
            }
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_USER_CLICK)) {
                BwbxUtil.userClick(intent.getIntExtra("dtype", 0), intent.getIntExtra("ctype", 0));
                return;
            }
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_DOWNLOAD_FULL_RES_IN_MOBILE)) {
                boolean unused4 = BwbxUtil.an = true;
                GameUtil.switchToDlFullresBg(context, 1, null);
                return;
            }
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_EXTRACT_FINISHED)) {
                int filesCount = GameUtil.getFilesCount(new File(BwbxUtil.v));
                if (BwbxUtil.n.isEmpty() || filesCount < BwbxUtil.n.size()) {
                    return;
                }
                BwbxUtil.setDownloadFinished(true);
                return;
            }
            if (action.equals(BwbxUtil.d.getPackageName() + BwbxUtil.ACTION_DOWNLOAD_CONTROL)) {
                boolean booleanExtra = intent.getBooleanExtra("control", true);
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "onReceive: control:" + booleanExtra, new Object[0]);
                BwbxUtil.downloadAllControl(BwbxUtil.d, booleanExtra);
            }
        }
    };
    private static AtomicInteger aY = new AtomicInteger(0);
    public static Boolean mIsSmall = null;
    public static float zipRatio = 0.0f;
    private static int aZ = -1;

    public interface SingleDownloadCallBack {
        void onSingleResult(String str, int i);
    }

    static /* synthetic */ int M() {
        int i2 = ag;
        ag = i2 + 1;
        return i2;
    }

    private static void R() {
        String line;
        String strSubstring;
        String strSubstring2;
        String line2;
        if (d == null) {
            return;
        }
        if (n.isEmpty()) {
            try {
                InputStream inputStreamA = com.excelliance.lbsdk.main.e.a(d);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArrA = com.excelliance.lbsdk.main.f.a();
                int length = bArrA.length;
                byte[] bArr = new byte[1024];
                inputStreamA.skip(261L);
                int i2 = 0;
                while (true) {
                    int i3 = inputStreamA.read(bArr);
                    if (i3 == -1) {
                        break;
                    }
                    int i4 = i2;
                    int i5 = 0;
                    while (i5 < i3) {
                        bArr[i5] = (byte) (bArrA[i4 % length] ^ bArr[i5]);
                        i5++;
                        i4++;
                    }
                    byteArrayOutputStream.write(bArr, 0, i3);
                    i2 = i4;
                }
                byteArrayOutputStream.flush();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
                do {
                    line = bufferedReader.readLine();
                    if (line != null) {
                        int length2 = line.length();
                        int iIndexOf = line.indexOf(32);
                        if (iIndexOf != 32 || iIndexOf == length2 - 1) {
                            strSubstring = null;
                            strSubstring2 = line;
                        } else {
                            strSubstring2 = line.substring(iIndexOf + 1);
                            strSubstring = line.substring(0, iIndexOf);
                        }
                        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "filename=%s, md5=%s", strSubstring2, strSubstring);
                        n.put(strSubstring2, strSubstring);
                        fileSetDl.add(strSubstring2);
                    }
                } while (line != null);
                bufferedReader.close();
                byteArrayOutputStream.close();
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "fileSet size:" + n.size(), new Object[0]);
            } catch (Exception e2) {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "no localData", e2, new Object[0]);
            }
        }
        if (n.isEmpty()) {
            try {
                File file = new File(GameUtil.getExternalDataPath(d) + "/fileSet.txt");
                if (file.exists()) {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    do {
                        line2 = bufferedReader2.readLine();
                        if (line2 != null) {
                            o.add(line2);
                        }
                    } while (line2 != null);
                    bufferedReader2.close();
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "recordList size:" + o.size(), new Object[0]);
                }
            } catch (Exception unused) {
            }
        }
        if (localRecord.isEmpty()) {
            final File file2 = new File(getRecordPath());
            com.excelliance.lbsdk.main.f.c cVar = new com.excelliance.lbsdk.main.f.c(file2, 1);
            cVar.a(new com.excelliance.lbsdk.main.f.c.a() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.4
                @Override // com.excelliance.lbsdk.main.f.c.a
                public void a(File file3) {
                    BwbxUtil.localRecord.add(file3.getAbsolutePath().substring(file2.getAbsolutePath().length() + 1));
                }
            });
            cVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:224:0x0658 A[Catch: all -> 0x0605, TryCatch #1 {all -> 0x0605, blocks: (B:203:0x05c8, B:205:0x05e6, B:222:0x0652, B:224:0x0658, B:225:0x0663, B:227:0x0680, B:229:0x0683, B:207:0x05f3, B:214:0x060f, B:216:0x0637, B:217:0x063f, B:218:0x0644), top: B:254:0x05c8 }] */
    /* JADX WARN: Code duplicated, block: B:277:0x0680 A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x0645, code lost:
    
        r6 = r0;
        r29 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0684, code lost:
    
        com.excelliance.lbsdk.main.bwbx.BwbxUtil.aO.a(r13, r11, r2[0], r2[1]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0693, code lost:
    
        if (r32 == false) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x06aa, code lost:
    
        r6 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void S() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1773
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.bwbx.BwbxUtil.S():void");
    }

    private static String T() {
        String str = aa;
        if (str != null && str.length() > 0) {
            return aa;
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(com.excelliance.lbsdk.main.e.c(d), "UTF-8");
            aa = new BufferedReader(inputStreamReader).readLine();
            inputStreamReader.close();
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "path=" + aa, new Object[0]);
        } catch (IOException unused) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "no tryflag", new Object[0]);
        }
        return aa;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void U() {
        downloadLater = true;
        x = true;
        E.set(0L);
        int iA = m.a(d, "SECOND_DIALOG_INTERVAL_BWBX", 0);
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadAllLater post mDownloadLaterCallBack intervel:" + iA, new Object[0]);
        if (iA > 0) {
            aR.postDelayed(aW, iA);
        }
    }

    private static boolean V() {
        boolean z2;
        long jLastModified = new File(d.getPackageResourcePath()).lastModified();
        long jLongValue = ((Long) new GameUtil.b(d, "lebian_base").a("firstInstalled", 0L)).longValue();
        if (jLongValue != jLastModified) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "hasOverInsOrRg reinstalled flag = " + jLongValue + " currentVersion = " + jLastModified, new Object[0]);
            z2 = true;
        } else {
            z2 = false;
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "hasOverInsOrRg reinstalled = " + z2, new Object[0]);
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void W() throws Throwable {
        StringBuilder sb;
        String str;
        if (ae.b(d, "lebian_module_dloadinfo", "big_asset_md5_check", false)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(v);
        stringBuffer.append("/assets/lbres/lbres.rdata.mp4");
        String string = stringBuffer.toString();
        boolean zIsTwload = GameUtil.getIntance().isTwload(d);
        GameUtil intance = GameUtil.getIntance();
        if (zIsTwload) {
            sb = new StringBuilder();
            str = "twice_load+lbres/";
        } else {
            sb = new StringBuilder();
            str = "lbres/";
        }
        sb.append(str);
        sb.append(DOWNLOAD_MP4_FILE_NAME);
        if (intance.verifyMp4Md5(string, sb.toString())) {
            ae.a(d, "lebian_module_dloadinfo", "big_asset_md5_check", true);
            return;
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "md5 wrong turn to download full res ", new Object[0]);
        aR.removeMessages(13);
        aR.sendMessageDelayed(aR.obtainMessage(13), 70L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean X() {
        if (ae.b(d, "lebian_module_dloadinfo", "file_md5_check", false)) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "check file md5 success", new Object[0]);
        } else {
            boolean z2 = false;
            for (int i2 = 0; i2 < fileSetDl.size(); i2++) {
                String str = fileSetDl.get(i2);
                String str2 = v + str;
                if (!new File(str2).exists()) {
                    StringBuffer stringBuffer = new StringBuffer(w);
                    stringBuffer.append(str);
                    String string = stringBuffer.toString();
                    if (!GameUtil.isCacheChecking(d) || !new File(string).exists()) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "is not all file", new Object[0]);
                        return false;
                    }
                } else if (!b(str, str2)) {
                    new File(str2).delete();
                    z2 = true;
                }
            }
            if (z2) {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "has wrong file", new Object[0]);
                return false;
            }
            ae.a(d, "lebian_module_dloadinfo", "file_md5_check", true);
        }
        return true;
    }

    private static boolean Y() {
        return V >= m.a(d, "SHOW_OLD_USER_COUNT", 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Z() {
        aN.c();
        if (aa.a(d)) {
            new com.excelliance.lbsdk.main.q.b(d).a();
        }
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 49681. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private static long a(java.lang.String r38, java.lang.String r39, byte[] r40, long r41, int r43, java.lang.String r44, int r45) {
        /*
            Method dump skipped, instruction units count: 4968
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.bwbx.BwbxUtil.a(java.lang.String, java.lang.String, byte[], long, int, java.lang.String, int):long");
    }

    /* JADX WARN: Code duplicated, block: B:260:0x04ca  */
    /* JADX WARN: Code duplicated, block: B:265:0x04e7  */
    /* JADX WARN: Code duplicated, block: B:274:0x053a  */
    /* JADX WARN: Code duplicated, block: B:275:0x0554  */
    /* JADX WARN: Code duplicated, block: B:278:0x0564  */
    /* JADX WARN: Code duplicated, block: B:283:0x0581  */
    /* JADX WARN: Code duplicated, block: B:289:0x05cc  */
    /* JADX WARN: Code duplicated, block: B:290:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:293:0x05f6  */
    /* JADX WARN: Code duplicated, block: B:298:0x0613  */
    /* JADX WARN: Code duplicated, block: B:304:0x065e  */
    /* JADX WARN: Code duplicated, block: B:305:0x0678  */
    /* JADX WARN: Code duplicated, block: B:308:0x0688  */
    /* JADX WARN: Code duplicated, block: B:313:0x06a5  */
    /* JADX WARN: Code duplicated, block: B:335:0x0795  */
    /* JADX WARN: Code duplicated, block: B:336:0x07af  */
    /* JADX WARN: Code duplicated, block: B:339:0x07bf  */
    /* JADX WARN: Code duplicated, block: B:344:0x07dc  */
    /* JADX WARN: Code duplicated, block: B:377:? A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:265:0x04e7, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:274:0x053a, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:283:0x0581, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:289:0x05cc, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:298:0x0613, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:304:0x065e, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:313:0x06a5, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:335:0x0795, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:344:0x07dc, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v112 */
    /* JADX WARN: Type inference failed for: r2v113 */
    /* JADX WARN: Type inference failed for: r2v114 */
    /* JADX WARN: Type inference failed for: r2v115 */
    /* JADX WARN: Type inference failed for: r2v116 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v20 */
    private static long a(String str, String str2, byte[] bArr, long j2, int i2, String str3, com.excelliance.lbsdk.main.o.b bVar) throws Throwable {
        Throwable th;
        long j3;
        long j4;
        long jCurrentTimeMillis;
        Exception exc;
        long j5;
        ConnectTimeoutException connectTimeoutException;
        UnknownHostException unknownHostException;
        SocketTimeoutException socketTimeoutException;
        com.excelliance.lbsdk.main.aa.b.a aVar;
        String str4;
        ?? r2;
        boolean zA;
        ?? r3;
        long j6;
        boolean zA2;
        boolean zA3;
        boolean zA4;
        int i3;
        String str5 = str;
        String str6 = str2;
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        int iA = m.a(d, "SHOW_LOADING_AFTER_DOWNLOAD_MISSING_TIME", 15) * 1000;
        aA = iA == 0;
        long j7 = 0;
        try {
            try {
                if (aa.b(d)) {
                    File file = new File(str5);
                    file.getParentFile().mkdirs();
                    if (bArr == null && file.exists()) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "delete existed " + str5, new Object[0]);
                        file.delete();
                    }
                    if (q && e != null && str5.startsWith(e) && !O && aA) {
                        k(true);
                    }
                    long jA = bVar.a(str3, 0L);
                    long jCurrentTimeMillis3 = jCurrentTimeMillis2;
                    int i4 = iA;
                    InputStream inputStreamB = bVar.b(str3, jA, bVar.a(str3, jA, i2));
                    long jA2 = bVar.a(str3);
                    ay = jA2;
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "length:" + jA2, new Object[0]);
                    }
                    if (jA2 > getFreeSpace(str)) {
                        long j8 = ERR_SPACE_NOT_ENOUGH;
                        dlAllSize.getAndAdd(0L);
                        dlAllTime.getAndAdd(0L);
                        if (q && O) {
                            aR.removeMessages(1);
                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                        }
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=0, progress=" + O + ", full=" + c, new Object[0]);
                        }
                        return j8;
                    }
                    jCurrentTimeMillis = System.currentTimeMillis();
                    try {
                        byte[] bArr2 = new byte[8192];
                        BufferedOutputStream bufferedOutputStream = null;
                        if (bArr == null) {
                            try {
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                            } catch (com.excelliance.lbsdk.main.aa.b.a e2) {
                                aVar = e2;
                                j7 = 0;
                                j5 = 0;
                            } catch (SocketTimeoutException e3) {
                                socketTimeoutException = e3;
                                j7 = 0;
                                j5 = 0;
                                socketTimeoutException.printStackTrace();
                                j6 = ERR_TIMEOUT;
                                if (j6 < 0) {
                                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                } else {
                                    dlAllSize.getAndAdd(j6);
                                    j7 -= jCurrentTimeMillis;
                                    dlAllTime.getAndAdd(j7);
                                }
                                if (q) {
                                    aR.removeMessages(1);
                                    aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                }
                                zA4 = com.excelliance.lbsdk.main.g.b.a();
                                str6 = zA4;
                                if (zA4) {
                                    str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                    Object[] objArr = new Object[0];
                                    com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr);
                                    str6 = objArr;
                                }
                                return j6;
                            } catch (UnknownHostException e4) {
                                unknownHostException = e4;
                                j7 = 0;
                                j5 = 0;
                                unknownHostException.printStackTrace();
                                j6 = ERR_NETWORK;
                                if (j6 < 0) {
                                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                } else {
                                    dlAllSize.getAndAdd(j6);
                                    j7 -= jCurrentTimeMillis;
                                    dlAllTime.getAndAdd(j7);
                                }
                                if (q) {
                                    aR.removeMessages(1);
                                    aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                }
                                zA3 = com.excelliance.lbsdk.main.g.b.a();
                                str6 = zA3;
                                if (zA3) {
                                    str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                    Object[] objArr2 = new Object[0];
                                    com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr2);
                                    str6 = objArr2;
                                }
                                return j6;
                            } catch (ConnectTimeoutException e5) {
                                connectTimeoutException = e5;
                                j7 = 0;
                                j5 = 0;
                                connectTimeoutException.printStackTrace();
                                j6 = ERR_TIMEOUT;
                                if (j6 < 0) {
                                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                } else {
                                    dlAllSize.getAndAdd(j6);
                                    j7 -= jCurrentTimeMillis;
                                    dlAllTime.getAndAdd(j7);
                                }
                                if (q) {
                                    aR.removeMessages(1);
                                    aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                }
                                zA2 = com.excelliance.lbsdk.main.g.b.a();
                                str6 = zA2;
                                if (zA2) {
                                    str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                    Object[] objArr3 = new Object[0];
                                    com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr3);
                                    str6 = objArr3;
                                }
                                return j6;
                            } catch (Exception e6) {
                                exc = e6;
                                j7 = 0;
                                j5 = 0;
                                exc.printStackTrace();
                                aj = exc.toString();
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                if (q) {
                                    aR.removeMessages(1);
                                    aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                }
                                zA = com.excelliance.lbsdk.main.g.b.a();
                                r3 = zA;
                                if (zA) {
                                    str5 = "downloadFileHttpSimple end " + str5 + ", total=-1, progress=" + O + ", full=" + c;
                                    Object[] objArr4 = new Object[0];
                                    com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr4);
                                    r3 = objArr4;
                                }
                                j6 = -1;
                                str6 = r3;
                                return j6;
                            } catch (Throwable th2) {
                                th = th2;
                                j3 = 0;
                                j7 = 0;
                                j4 = 0;
                                str4 = str6;
                                if (j7 < j3) {
                                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
                                } else {
                                    dlAllSize.getAndAdd(j7);
                                    dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
                                }
                                if (q) {
                                    aR.removeMessages(1);
                                    aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                }
                                if (!com.excelliance.lbsdk.main.g.b.a()) {
                                    throw th;
                                }
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                                throw th;
                            }
                        }
                        int iA2 = m.a(d, "BWBX_DOWNLOAD_SPEED_LIMIT", 0) * 1024;
                        j5 = 0;
                        while (true) {
                            try {
                                int i5 = inputStreamB.read(bArr2);
                                if (i5 == -1) {
                                    break;
                                }
                                int i6 = i4;
                                long j9 = i5;
                                aQ.c(j9);
                                if (bArr == null) {
                                    try {
                                        bufferedOutputStream.write(bArr2, 0, i5);
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                        j7 = j5;
                                    } catch (com.excelliance.lbsdk.main.aa.b.a e7) {
                                        aVar = e7;
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                    } catch (SocketTimeoutException e8) {
                                        socketTimeoutException = e8;
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                        j7 = 0;
                                        socketTimeoutException.printStackTrace();
                                        j6 = ERR_TIMEOUT;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q && O) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA4 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA4;
                                        if (zA4) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr5 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr5);
                                            str6 = objArr5;
                                        }
                                        return j6;
                                    } catch (UnknownHostException e9) {
                                        unknownHostException = e9;
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                        j7 = 0;
                                        unknownHostException.printStackTrace();
                                        j6 = ERR_NETWORK;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q && O) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA3 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA3;
                                        if (zA3) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr6 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr6);
                                            str6 = objArr6;
                                        }
                                        return j6;
                                    } catch (ConnectTimeoutException e10) {
                                        connectTimeoutException = e10;
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                        j7 = 0;
                                        connectTimeoutException.printStackTrace();
                                        j6 = ERR_TIMEOUT;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q && O) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA2 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA2;
                                        if (zA2) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr7 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr7);
                                            str6 = objArr7;
                                        }
                                        return j6;
                                    } catch (Exception e11) {
                                        exc = e11;
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                        j7 = 0;
                                        exc.printStackTrace();
                                        aj = exc.toString();
                                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        if (q && O) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA = com.excelliance.lbsdk.main.g.b.a();
                                        r3 = zA;
                                        if (zA) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=-1, progress=" + O + ", full=" + c;
                                            Object[] objArr8 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr8);
                                            r3 = objArr8;
                                        }
                                        j6 = -1;
                                        str6 = r3;
                                        return j6;
                                    } catch (Throwable th3) {
                                        th = th3;
                                        jCurrentTimeMillis = jCurrentTimeMillis;
                                        j7 = j5;
                                        j3 = 0;
                                        j4 = 0;
                                        str4 = str6;
                                        if (j7 < j3) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j7);
                                            dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        if (!com.excelliance.lbsdk.main.g.b.a()) {
                                            throw th;
                                        }
                                        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                                        throw th;
                                    }
                                } else {
                                    jCurrentTimeMillis = jCurrentTimeMillis;
                                    j7 = j5;
                                    try {
                                        System.arraycopy(bArr2, 0, bArr, (int) j7, i5);
                                    } catch (com.excelliance.lbsdk.main.aa.b.a e12) {
                                        aVar = e12;
                                        j5 = j7;
                                    } catch (SocketTimeoutException e13) {
                                        socketTimeoutException = e13;
                                        j5 = j7;
                                        j7 = 0;
                                        socketTimeoutException.printStackTrace();
                                        j6 = ERR_TIMEOUT;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA4 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA4;
                                        if (zA4) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr9 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr9);
                                            str6 = objArr9;
                                        }
                                        return j6;
                                    } catch (UnknownHostException e14) {
                                        unknownHostException = e14;
                                        j5 = j7;
                                        j7 = 0;
                                        unknownHostException.printStackTrace();
                                        j6 = ERR_NETWORK;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA3 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA3;
                                        if (zA3) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr10 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr10);
                                            str6 = objArr10;
                                        }
                                        return j6;
                                    } catch (ConnectTimeoutException e15) {
                                        connectTimeoutException = e15;
                                        j5 = j7;
                                        j7 = 0;
                                        connectTimeoutException.printStackTrace();
                                        j6 = ERR_TIMEOUT;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA2 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA2;
                                        if (zA2) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr11 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr11);
                                            str6 = objArr11;
                                        }
                                        return j6;
                                    } catch (Exception e16) {
                                        exc = e16;
                                        j5 = j7;
                                        j7 = 0;
                                        exc.printStackTrace();
                                        aj = exc.toString();
                                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA = com.excelliance.lbsdk.main.g.b.a();
                                        r3 = zA;
                                        if (zA) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=-1, progress=" + O + ", full=" + c;
                                            Object[] objArr12 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr12);
                                            r3 = objArr12;
                                        }
                                        j6 = -1;
                                        str6 = r3;
                                        return j6;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        th = th;
                                        j3 = 0;
                                        j4 = 0;
                                        str4 = str6;
                                        if (j7 < j3) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j7);
                                            dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        if (!com.excelliance.lbsdk.main.g.b.a()) {
                                            throw th;
                                        }
                                        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                                        throw th;
                                    }
                                }
                                j5 = j7 + j9;
                                if (i5 > 0 && iA2 > 0) {
                                    try {
                                        long jCurrentTimeMillis4 = System.currentTimeMillis() - jCurrentTimeMillis3;
                                        aJ.getAndAdd(i5);
                                        aK.getAndAdd((int) jCurrentTimeMillis4);
                                        if (aJ.get() > iA2) {
                                            if (aK.get() < 1000) {
                                                try {
                                                    Thread.sleep(1000 - aK.get());
                                                } catch (InterruptedException e17) {
                                                    e17.printStackTrace();
                                                }
                                            }
                                            aJ.set(0);
                                            aK.set(0);
                                        }
                                        jCurrentTimeMillis3 = System.currentTimeMillis();
                                    } catch (com.excelliance.lbsdk.main.aa.b.a e18) {
                                        e = e18;
                                    } catch (SocketTimeoutException e19) {
                                        e = e19;
                                        socketTimeoutException = e;
                                        j7 = 0;
                                        socketTimeoutException.printStackTrace();
                                        j6 = ERR_TIMEOUT;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA4 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA4;
                                        if (zA4) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr13 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr13);
                                            str6 = objArr13;
                                        }
                                        return j6;
                                    } catch (UnknownHostException e20) {
                                        e = e20;
                                        unknownHostException = e;
                                        j7 = 0;
                                        unknownHostException.printStackTrace();
                                        j6 = ERR_NETWORK;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA3 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA3;
                                        if (zA3) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr14 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr14);
                                            str6 = objArr14;
                                        }
                                        return j6;
                                    } catch (ConnectTimeoutException e21) {
                                        e = e21;
                                        connectTimeoutException = e;
                                        j7 = 0;
                                        connectTimeoutException.printStackTrace();
                                        j6 = ERR_TIMEOUT;
                                        if (j6 < 0) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j6);
                                            j7 -= jCurrentTimeMillis;
                                            dlAllTime.getAndAdd(j7);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA2 = com.excelliance.lbsdk.main.g.b.a();
                                        str6 = zA2;
                                        if (zA2) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                            Object[] objArr15 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr15);
                                            str6 = objArr15;
                                        }
                                        return j6;
                                    } catch (Exception e22) {
                                        e = e22;
                                        exc = e;
                                        j7 = 0;
                                        exc.printStackTrace();
                                        aj = exc.toString();
                                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        zA = com.excelliance.lbsdk.main.g.b.a();
                                        r3 = zA;
                                        if (zA) {
                                            str5 = "downloadFileHttpSimple end " + str5 + ", total=-1, progress=" + O + ", full=" + c;
                                            Object[] objArr16 = new Object[0];
                                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr16);
                                            r3 = objArr16;
                                        }
                                        j6 = -1;
                                        str6 = r3;
                                        return j6;
                                    } catch (Throwable th5) {
                                        th = th5;
                                        j7 = j5;
                                        j3 = 0;
                                        j4 = 0;
                                        str4 = str6;
                                        if (j7 < j3) {
                                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
                                        } else {
                                            dlAllSize.getAndAdd(j7);
                                            dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
                                        }
                                        if (q) {
                                            aR.removeMessages(1);
                                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                                        }
                                        if (!com.excelliance.lbsdk.main.g.b.a()) {
                                            throw th;
                                        }
                                        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                                        throw th;
                                    }
                                }
                                if (av) {
                                    synchronized (m) {
                                        try {
                                            m.wait();
                                        } catch (Throwable th6) {
                                            throw th6;
                                        }
                                    }
                                }
                                if (!q || e == null || !str5.startsWith(e) || O || aA) {
                                    i3 = i6;
                                } else {
                                    i3 = i6;
                                    if (System.currentTimeMillis() - K >= i3) {
                                        Intent intent = new Intent();
                                        intent.putExtra("action", 15);
                                        intent.putExtra("vercode", String.valueOf(t));
                                        intent.putExtra(ACTION_URL, str6);
                                        GameUtil.getIntance().notifyAction(intent);
                                        k(false);
                                    }
                                    i4 = i3;
                                    bufferedOutputStream = bufferedOutputStream;
                                    jCurrentTimeMillis = jCurrentTimeMillis;
                                }
                                i4 = i3;
                                bufferedOutputStream = bufferedOutputStream;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                            } catch (com.excelliance.lbsdk.main.aa.b.a e23) {
                                e = e23;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                            } catch (SocketTimeoutException e24) {
                                e = e24;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                            } catch (UnknownHostException e25) {
                                e = e25;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                            } catch (ConnectTimeoutException e26) {
                                e = e26;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                            } catch (Exception e27) {
                                e = e27;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                            } catch (Throwable th7) {
                                th = th7;
                                jCurrentTimeMillis = jCurrentTimeMillis;
                                j7 = j5;
                            }
                            aVar = e;
                            j7 = 0;
                            int httpCode = aVar.getHttpCode();
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "retCode=" + httpCode, new Object[0]);
                            ab = httpCode;
                            j6 = ERR_RET_CODE;
                            if (j6 < 0) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j6);
                                j7 -= jCurrentTimeMillis;
                                dlAllTime.getAndAdd(j7);
                            }
                            if (q && O) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            boolean zA5 = com.excelliance.lbsdk.main.g.b.a();
                            str6 = zA5;
                            if (zA5) {
                                str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                Object[] objArr17 = new Object[0];
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr17);
                                str6 = objArr17;
                            }
                            return j6;
                        }
                        BufferedOutputStream bufferedOutputStream2 = bufferedOutputStream;
                        jCurrentTimeMillis = jCurrentTimeMillis;
                        j7 = j5;
                        if (q) {
                            L = System.currentTimeMillis();
                        }
                        long jCurrentTimeMillis5 = System.currentTimeMillis();
                        try {
                            bVar.a(str3, inputStreamB);
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.flush();
                                bufferedOutputStream2.close();
                            }
                            if (j7 < 0) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j7);
                                dlAllTime.getAndAdd(jCurrentTimeMillis5 - jCurrentTimeMillis);
                            }
                            if (q && O) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            if (com.excelliance.lbsdk.main.g.b.a()) {
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                            }
                            return j7;
                        } catch (com.excelliance.lbsdk.main.aa.b.a e28) {
                            aVar = e28;
                            j5 = j7;
                            j7 = jCurrentTimeMillis5;
                        } catch (SocketTimeoutException e29) {
                            socketTimeoutException = e29;
                            j5 = j7;
                            j7 = jCurrentTimeMillis5;
                            socketTimeoutException.printStackTrace();
                            j6 = ERR_TIMEOUT;
                            if (j6 < 0) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j6);
                                j7 -= jCurrentTimeMillis;
                                dlAllTime.getAndAdd(j7);
                            }
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            zA4 = com.excelliance.lbsdk.main.g.b.a();
                            str6 = zA4;
                            if (zA4) {
                                str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                Object[] objArr18 = new Object[0];
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr18);
                                str6 = objArr18;
                            }
                            return j6;
                        } catch (UnknownHostException e30) {
                            unknownHostException = e30;
                            j5 = j7;
                            j7 = jCurrentTimeMillis5;
                            unknownHostException.printStackTrace();
                            j6 = ERR_NETWORK;
                            if (j6 < 0) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j6);
                                j7 -= jCurrentTimeMillis;
                                dlAllTime.getAndAdd(j7);
                            }
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            zA3 = com.excelliance.lbsdk.main.g.b.a();
                            str6 = zA3;
                            if (zA3) {
                                str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                Object[] objArr19 = new Object[0];
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr19);
                                str6 = objArr19;
                            }
                            return j6;
                        } catch (ConnectTimeoutException e31) {
                            connectTimeoutException = e31;
                            j5 = j7;
                            j7 = jCurrentTimeMillis5;
                            connectTimeoutException.printStackTrace();
                            j6 = ERR_TIMEOUT;
                            if (j6 < 0) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j6);
                                j7 -= jCurrentTimeMillis;
                                dlAllTime.getAndAdd(j7);
                            }
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            zA2 = com.excelliance.lbsdk.main.g.b.a();
                            str6 = zA2;
                            if (zA2) {
                                str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                                Object[] objArr110 = new Object[0];
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr110);
                                str6 = objArr110;
                            }
                            return j6;
                        } catch (Exception e32) {
                            exc = e32;
                            j5 = j7;
                            j7 = jCurrentTimeMillis5;
                            exc.printStackTrace();
                            aj = exc.toString();
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            zA = com.excelliance.lbsdk.main.g.b.a();
                            r3 = zA;
                            if (zA) {
                                str5 = "downloadFileHttpSimple end " + str5 + ", total=-1, progress=" + O + ", full=" + c;
                                Object[] objArr111 = new Object[0];
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr111);
                                r3 = objArr111;
                            }
                            j6 = -1;
                            str6 = r3;
                            return j6;
                        } catch (Throwable th8) {
                            th = th8;
                            j4 = jCurrentTimeMillis5;
                            r2 = str6;
                            j3 = 0;
                            str4 = r2;
                            if (j7 < j3) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j7);
                                dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
                            }
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            if (!com.excelliance.lbsdk.main.g.b.a()) {
                                throw th;
                            }
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                            throw th;
                        }
                    } catch (com.excelliance.lbsdk.main.aa.b.a e33) {
                        aVar = e33;
                    } catch (SocketTimeoutException e34) {
                        socketTimeoutException = e34;
                    } catch (UnknownHostException e35) {
                        unknownHostException = e35;
                    } catch (ConnectTimeoutException e36) {
                        connectTimeoutException = e36;
                    } catch (Exception e37) {
                        exc = e37;
                    } catch (Throwable th9) {
                        th = th9;
                    }
                } else {
                    try {
                        long j10 = ERR_NETWORK;
                        dlAllSize.getAndAdd(0L);
                        dlAllTime.getAndAdd(0L);
                        if (q && O) {
                            aR.removeMessages(1);
                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                        }
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=0, progress=" + O + ", full=" + c, new Object[0]);
                        }
                        return j10;
                    } catch (com.excelliance.lbsdk.main.aa.b.a e38) {
                        aVar = e38;
                        j5 = 0;
                        jCurrentTimeMillis = 0;
                    } catch (SocketTimeoutException e39) {
                        socketTimeoutException = e39;
                        j5 = 0;
                        jCurrentTimeMillis = 0;
                        socketTimeoutException.printStackTrace();
                        j6 = ERR_TIMEOUT;
                        if (j6 < 0) {
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                        } else {
                            dlAllSize.getAndAdd(j6);
                            j7 -= jCurrentTimeMillis;
                            dlAllTime.getAndAdd(j7);
                        }
                        if (q) {
                            aR.removeMessages(1);
                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                        }
                        zA4 = com.excelliance.lbsdk.main.g.b.a();
                        str6 = zA4;
                        if (zA4) {
                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                            Object[] objArr112 = new Object[0];
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr112);
                            str6 = objArr112;
                        }
                        return j6;
                    } catch (UnknownHostException e40) {
                        unknownHostException = e40;
                        j5 = 0;
                        jCurrentTimeMillis = 0;
                        unknownHostException.printStackTrace();
                        j6 = ERR_NETWORK;
                        if (j6 < 0) {
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                        } else {
                            dlAllSize.getAndAdd(j6);
                            j7 -= jCurrentTimeMillis;
                            dlAllTime.getAndAdd(j7);
                        }
                        if (q) {
                            aR.removeMessages(1);
                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                        }
                        zA3 = com.excelliance.lbsdk.main.g.b.a();
                        str6 = zA3;
                        if (zA3) {
                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                            Object[] objArr113 = new Object[0];
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr113);
                            str6 = objArr113;
                        }
                        return j6;
                    } catch (ConnectTimeoutException e41) {
                        connectTimeoutException = e41;
                        j5 = 0;
                        jCurrentTimeMillis = 0;
                        connectTimeoutException.printStackTrace();
                        j6 = ERR_TIMEOUT;
                        if (j6 < 0) {
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                        } else {
                            dlAllSize.getAndAdd(j6);
                            j7 -= jCurrentTimeMillis;
                            dlAllTime.getAndAdd(j7);
                        }
                        if (q) {
                            aR.removeMessages(1);
                            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                        }
                        zA2 = com.excelliance.lbsdk.main.g.b.a();
                        str6 = zA2;
                        if (zA2) {
                            str5 = "downloadFileHttpSimple end " + str5 + ", total=" + j6 + ", progress=" + O + ", full=" + c;
                            Object[] objArr114 = new Object[0];
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr114);
                            str6 = objArr114;
                        }
                        return j6;
                    } catch (Exception e42) {
                        exc = e42;
                        j5 = 0;
                        jCurrentTimeMillis = 0;
                        exc.printStackTrace();
                        try {
                            aj = exc.toString();
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str6, new Object[0]);
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            zA = com.excelliance.lbsdk.main.g.b.a();
                            r3 = zA;
                            if (zA) {
                                str5 = "downloadFileHttpSimple end " + str5 + ", total=-1, progress=" + O + ", full=" + c;
                                Object[] objArr115 = new Object[0];
                                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", str5, objArr115);
                                r3 = objArr115;
                            }
                            j6 = -1;
                            str6 = r3;
                            return j6;
                        } catch (Throwable th10) {
                            th = th10;
                            j4 = j7;
                            j7 = -1;
                            r2 = str6;
                            j3 = 0;
                            str4 = r2;
                            if (j7 < j3) {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
                            } else {
                                dlAllSize.getAndAdd(j7);
                                dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
                            }
                            if (q) {
                                aR.removeMessages(1);
                                aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
                            }
                            if (!com.excelliance.lbsdk.main.g.b.a()) {
                                throw th;
                            }
                            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
                            throw th;
                        }
                    } catch (Throwable th11) {
                        th = th11;
                        j3 = 0;
                        j4 = 0;
                        jCurrentTimeMillis = 0;
                        str4 = str6;
                    }
                }
            } catch (Throwable th12) {
                th = th12;
                j4 = j7;
                j7 = j5;
                r2 = str6;
            }
        } catch (com.excelliance.lbsdk.main.aa.b.a e43) {
            aVar = e43;
            j7 = 0;
            j5 = 0;
            jCurrentTimeMillis = 0;
        } catch (SocketTimeoutException e44) {
            socketTimeoutException = e44;
            j7 = 0;
            j5 = 0;
            jCurrentTimeMillis = 0;
        } catch (UnknownHostException e45) {
            unknownHostException = e45;
            j7 = 0;
            j5 = 0;
            jCurrentTimeMillis = 0;
        } catch (ConnectTimeoutException e46) {
            connectTimeoutException = e46;
            j7 = 0;
            j5 = 0;
            jCurrentTimeMillis = 0;
        } catch (Exception e47) {
            exc = e47;
            j7 = 0;
            j5 = 0;
            jCurrentTimeMillis = 0;
        } catch (Throwable th13) {
            th = th13;
            j3 = 0;
            j7 = 0;
            j4 = 0;
            jCurrentTimeMillis = 0;
            str4 = str6;
        }
        if (j7 < j3) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFileHttpSimple url=" + str4, new Object[0]);
        } else {
            dlAllSize.getAndAdd(j7);
            dlAllTime.getAndAdd(j4 - jCurrentTimeMillis);
        }
        if (q && O) {
            aR.removeMessages(1);
            aR.sendMessageDelayed(aR.obtainMessage(1), 500L);
        }
        if (!com.excelliance.lbsdk.main.g.b.a()) {
            throw th;
        }
        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "downloadFileHttpSimple end " + str5 + ", total=" + j7 + ", progress=" + O + ", full=" + c, new Object[0]);
        throw th;
    }

    private static long a(String str, boolean z2) {
        ZipFile zipFile;
        File file;
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(GameUtil.getResPatchPath(d, t, false));
        if (!GameUtil.isCacheChecking(d)) {
            arrayList.add(GameUtil.getZipPath(d, false));
            arrayList.add(GameUtil.getTwLoadPath(d, false));
        }
        for (String str2 : arrayList) {
            try {
                if (new File(str2).exists()) {
                    if (b != null && !b.getName().equals(str2)) {
                        b.close();
                        b = null;
                    }
                    synchronized (aF) {
                        if (b == null) {
                            b = new ZipFile(str2);
                        }
                        zipFile = b;
                    }
                    ZipEntry entry = zipFile.getEntry(str);
                    if (entry != null) {
                        if (!z2) {
                            return entry.getCompressedSize();
                        }
                        String str3 = e;
                        int i2 = 0;
                        while (true) {
                            if (!new File(str3 + ".tmp").exists()) {
                                break;
                            }
                            int i3 = i2 + 1;
                            if (i2 >= 100) {
                                break;
                            }
                            Thread.sleep(100L);
                            i2 = i3;
                        }
                        if (new File(str3).exists()) {
                            file = new File(str3);
                        } else {
                            new File(str3).getParentFile().mkdirs();
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3 + ".temp"));
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(entry));
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i4 = bufferedInputStream.read(bArr, 0, 1024);
                                if (i4 == -1) {
                                    break;
                                }
                                bufferedOutputStream.write(bArr, 0, i4);
                            }
                            bufferedInputStream.close();
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            new File(str3 + ".temp").renameTo(new File(str3));
                            file = new File(str3);
                        }
                        return file.length();
                    }
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "extract missing file failed:" + str + " extract zip:" + str2, new Object[0]);
                } else if (b != null && b.getName().equals(str2)) {
                    b.close();
                    b = null;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0L;
    }

    private static void a(Context context, File file) throws IOException {
        int i2;
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                a(context, file2);
            } else {
                if (file2.getName().contains("libmain.so")) {
                    i2 = isBigAsset(context) ? 5 : 1;
                } else if (file2.getName().contains("libUE4.so")) {
                    i2 = 3;
                } else if (file2.getName().contains("libInterfaceJava2C.so")) {
                    i2 = 4;
                } else if (file2.getName().contains("libUnrealEngine3.so")) {
                    i2 = 6;
                } else if (file2.getName().contains("libSwordsman.so")) {
                    i2 = 7;
                } else if (file2.getName().contains("liblayaair.so")) {
                    i2 = 8;
                }
                gEngine = i2;
            }
        }
    }

    private static void a(Context context, ZipFile zipFile) throws IOException {
        int i2;
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            String name = enumerationEntries.nextElement().getName();
            if (name.startsWith("lib/")) {
                if (name.endsWith("libunity.so")) {
                    if (isBigAsset(context)) {
                        gEngine = 5;
                    }
                    i2 = 1;
                } else if (name.endsWith("libUE4.so")) {
                    i2 = 3;
                } else if (name.endsWith("libInterfaceJava2C.so")) {
                    i2 = 4;
                } else if (name.endsWith("libUnrealEngine3.so")) {
                    i2 = 6;
                } else if (name.endsWith("libSwordsman.so")) {
                    i2 = 7;
                } else if (name.endsWith("liblayaair.so")) {
                    i2 = 8;
                }
                gEngine = i2;
            }
        }
    }

    private static void a(final SingleDownloadCallBack singleDownloadCallBack) {
        ak.b().a(new Thread("BwbxUtil:singleFileDownloadProgress") { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.7
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (!aa.b(BwbxUtil.d)) {
                    BwbxUtil.az.set(false);
                    SingleDownloadCallBack singleDownloadCallBack2 = singleDownloadCallBack;
                    if (singleDownloadCallBack2 != null) {
                        singleDownloadCallBack2.onSingleResult("error", -4);
                    }
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                if (!aa.a(BwbxUtil.d) && BwbxUtil.downloadLater) {
                    BwbxUtil.downloadLater = false;
                    BwbxUtil.aR.removeMessages(4);
                    Message messageObtainMessage = BwbxUtil.aR.obtainMessage(4);
                    messageObtainMessage.arg2 = 3;
                    BwbxUtil.aR.sendMessageDelayed(messageObtainMessage, 0L);
                    synchronized (BwbxUtil.au) {
                        try {
                            BwbxUtil.au.wait();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
                Thread threadCurrentThread = Thread.currentThread();
                int priority = threadCurrentThread.getPriority();
                threadCurrentThread.setPriority(10);
                int i2 = BwbxUtil.aP;
                int i3 = 3;
                while (BwbxUtil.aw.size() > 0) {
                    String str = (String) BwbxUtil.aw.get(0);
                    SingleDownloadCallBack singleDownloadCallBack3 = singleDownloadCallBack;
                    if (singleDownloadCallBack3 != null) {
                        singleDownloadCallBack3.onSingleResult(str, -3);
                    }
                    StringBuffer stringBuffer = new StringBuffer(GameUtil.getCachePath(BwbxUtil.d));
                    stringBuffer.append(str);
                    stringBuffer.append(".dload");
                    String string = stringBuffer.toString();
                    String strSubstring = string.substring(0, string.lastIndexOf(46));
                    File file = new File(strSubstring);
                    synchronized (BwbxUtil.l) {
                        if (str.equals(BwbxUtil.g)) {
                            boolean unused = BwbxUtil.q = true;
                            try {
                                BwbxUtil.l.wait();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                            if (file.exists()) {
                                singleDownloadCallBack.onSingleResult(str, 100);
                                BwbxUtil.aw.remove(str);
                            } else {
                                singleDownloadCallBack.onSingleResult(str, -6);
                            }
                            threadCurrentThread = threadCurrentThread;
                        } else {
                            String unused2 = BwbxUtil.at = str;
                            StringBuffer stringBuffer2 = new StringBuffer(BwbxUtil.f(i2));
                            stringBuffer2.append(BwbxUtil.d(str));
                            String string2 = stringBuffer2.toString();
                            long jB = BwbxUtil.b(string, string2, i2, str);
                            long j2 = (jB < 0 || BwbxUtil.b(str, string)) ? jB : BwbxUtil.ERR_MD5_MIS;
                            if (j2 >= 0) {
                                new File(string).renameTo(file);
                                if (file.exists()) {
                                    BwbxUtil.setCached(str, strSubstring, false);
                                    BwbxUtil.d(new File(strSubstring).length());
                                } else {
                                    long j3 = BwbxUtil.ERR_RENAME_FAIL;
                                    if (BwbxUtil.ag < BwbxUtil.ah) {
                                        BwbxUtil.M();
                                        BwbxUtil.handleDownloadFailed(j3, BwbxUtil.ab, string2);
                                    }
                                }
                                synchronized (BwbxUtil.l) {
                                    if (BwbxUtil.r) {
                                        BwbxUtil.l.notifyAll();
                                    }
                                }
                                BwbxUtil.aw.remove(str);
                                SingleDownloadCallBack singleDownloadCallBack4 = singleDownloadCallBack;
                                if (singleDownloadCallBack4 != null) {
                                    singleDownloadCallBack4.onSingleResult(str, 100);
                                }
                                if (BwbxUtil.aw.size() <= 0) {
                                    break;
                                }
                            } else {
                                Thread thread = threadCurrentThread;
                                if (BwbxUtil.ag < BwbxUtil.ah) {
                                    BwbxUtil.M();
                                    BwbxUtil.handleDownloadFailed(j2, BwbxUtil.ab, string2);
                                }
                                synchronized (BwbxUtil.l) {
                                    i3--;
                                    i2 = (i2 + 1) % 3;
                                    int unused3 = BwbxUtil.aP = i2;
                                    if (i3 <= 0) {
                                        if (singleDownloadCallBack != null) {
                                            singleDownloadCallBack.onSingleResult(str, -6);
                                        }
                                        BwbxUtil.aw.remove(str);
                                        i3 = BwbxUtil.B;
                                    }
                                }
                                threadCurrentThread = thread;
                            }
                        }
                    }
                    threadCurrentThread = threadCurrentThread;
                }
                threadCurrentThread.setPriority(priority);
                synchronized (BwbxUtil.l) {
                    String unused4 = BwbxUtil.at = null;
                }
                BwbxUtil.az.set(false);
            }
        });
    }

    public static boolean addDownloadStatusListener(Object obj) {
        return aN.a(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long b(Context context, boolean z2) {
        return getOrigSize(context, z2, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 21481. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public static long b(java.lang.String r29, java.lang.String r30, int r31, java.lang.String r32) {
        /*
            Method dump skipped, instruction units count: 2148
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.bwbx.BwbxUtil.b(java.lang.String, java.lang.String, int, java.lang.String):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, Intent intent) {
        s.a(context, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Intent intent) {
        w.a(d).a(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str, String str2) throws NoSuchAlgorithmException, IOException {
        String str3 = n.get(str);
        if (str3 == null) {
            return true;
        }
        String strComputeFileMd5 = computeFileMd5(str2);
        boolean zEquals = str3.equals(strComputeFileMd5);
        if (!zEquals) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "md5=" + str3 + ", real=" + strComputeFileMd5, new Object[0]);
        }
        return zEquals;
    }

    private static void c(long j2) {
        Context context;
        int i2;
        Intent intent = new Intent(d.getPackageName() + ACTION_QUERY_RETRY);
        intent.putExtra("errcode", j2);
        if (j2 == -1) {
            intent.putExtra("exceptionInfo", ai);
        }
        b(d, intent);
        if (j2 == ERR_NETWORK) {
            context = d;
            i2 = DIALOG_TYPE_DOWNLOAD_FAILED_ERR_NETWORK;
        } else if (j2 == ERR_TIMEOUT) {
            context = d;
            i2 = DIALOG_TYPE_DOWNLOAD_FAILED_TIMOUT;
        } else if (j2 == ERR_SPACE_NOT_ENOUGH) {
            context = d;
            i2 = DIALOG_TYPE_DOWNLOAD_FAILED_SPACE_NOT_ENOUGH;
        } else if (j2 == ERR_MD5_MIS) {
            context = d;
            i2 = DIALOG_TYPE_DOWNLOAD_FAILED_MD5_MIS;
        } else if (j2 == ERR_RENAME_FAIL) {
            context = d;
            i2 = DIALOG_TYPE_DOWNLOAD_FAILED_RENAME_FAIL;
        } else {
            context = d;
            i2 = DIALOG_TYPE_DOWNLOAD_FAILED_UNKNOWN;
        }
        dialogStatistics(context, i2);
    }

    private static boolean c(String str) {
        return a(str, true) > 0;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0139  */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e3, code lost:
    
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean checkDlFullRes(android.content.Context r18, boolean r19, boolean r20, boolean r21, int r22) {
        /*
            Method dump skipped, instruction units count: 593
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.bwbx.BwbxUtil.checkDlFullRes(android.content.Context, boolean, boolean, boolean, int):boolean");
    }

    public static void closeRetryDialogStopDownload() {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "before closeRetryDialogStopDownload", new Object[0]);
        synchronized (j) {
            Iterator<Thread> it = k.keySet().iterator();
            while (it.hasNext()) {
                k.put(it.next(), true);
            }
            j.notifyAll();
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "after closeRetryDialogStopDownload", new Object[0]);
    }

    public static String computeFileMd5(String str) throws NoSuchAlgorithmException, IOException {
        int i2;
        if (str == null || str.length() == 0) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            while (true) {
                int i3 = bufferedInputStream.read(bArr, 0, 1024);
                if (i3 == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i3);
            }
            bufferedInputStream.close();
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean containBigFile(String str) {
        return aI.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        String[] strArrSplit = str.split("/");
        StringBuffer stringBuffer = new StringBuffer();
        if (strArrSplit.length > 0) {
            try {
                stringBuffer.append(URLEncoder.encode(strArrSplit[0], "UTF-8"));
                for (int i2 = 1; i2 < strArrSplit.length; i2++) {
                    stringBuffer.append("/");
                    stringBuffer.append(URLEncoder.encode(strArrSplit[i2], "UTF-8"));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "encodeURL=" + stringBuffer.toString(), new Object[0]);
        }
        return stringBuffer.toString().replaceAll("\\+", "%20");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(long j2) {
        int size;
        G.getAndIncrement();
        H.getAndAdd(j2);
        if (I) {
            GameUtil.setDownloadedSize(d, Integer.toString(t), H.get());
        }
        if (isBigAsset(d)) {
            long jB = b(d, false);
            double d2 = H.get();
            double d3 = jB;
            Double.isNaN(d2);
            Double.isNaN(d3);
            size = (int) ((d2 / d3) * 100.0d);
        } else {
            size = (int) ((G.get() / fileSetDl.size()) * 100.0f);
        }
        com.excelliance.lbsdk.main.g.b.a("BwbxUtil", "downloadedProgress currentPercentage:" + size + " mLastPercentage:" + J + " mTotalDownloadedAllCount:" + G + " mTotalDownloadedAllSize" + H, new Object[0]);
        if (size != J) {
            int iMin = Math.min(Math.max(0, size / 10), 100);
            if (iMin > 0) {
                Intent intent = new Intent();
                intent.putExtra("action", 12);
                intent.putExtra("vercode", String.valueOf(t));
                intent.putExtra(ACTION_PROGRESS, iMin);
                GameUtil.getIntance().notifyAction(intent);
                Bundle bundle = new Bundle();
                bundle.putInt("lbevent", 5);
                bundle.putInt(ACTION_PROGRESS, iMin);
                t.a(d, bundle);
            }
            J = size;
            if (size != 1) {
                Context context = d;
                if (size < 1) {
                    size = 1;
                }
                sendDownloadProportionInfo(context, size);
            }
        }
    }

    public static void dialogRejected(int i2) {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "dialogRejected: type:" + i2, new Object[0]);
        if (i2 == ACTION_TYPE_DOWNLOAD_ALL_WITHOUT_WIFI_LATER) {
            downloadLater = true;
        } else if (i2 == ACTION_TYPE_PROGRESS) {
            O = false;
        }
    }

    public static void dialogStatistics(Context context, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("lbevent", 2);
        bundle.putInt("dialog_type", i2);
        t.a(context, bundle);
        Intent intent = new Intent();
        intent.putExtra("action", 18);
        intent.putExtra("vercode", String.valueOf(t));
        intent.putExtra("pkg", context.getPackageName());
        intent.putExtra(ACTION_TYPE, i2);
        GameUtil.getIntance().notifyAction(intent);
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "dialogStatistics", new Object[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.excelliance.lbsdk.main.bwbx.BwbxUtil$5] */
    public static void downloadAll(final boolean z2) {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadAll prefetching:" + s, new Object[0]);
        if (s) {
            return;
        }
        s = true;
        new Thread("BwbxUtil:downloadAll2") { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                boolean zX;
                if (BwbxUtil.isInternalPkg()) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadAll: is internal pkg ", new Object[0]);
                    return;
                }
                BwbxUtil.aM.set(ae.b(BwbxUtil.d, "lebian_module_dloadinfo", "restartAfterDl", false));
                if (BwbxUtil.aM.get()) {
                    ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "restartAfterDl");
                }
                int i2 = 0;
                while (GameUtil.isCacheChecking(BwbxUtil.d)) {
                    int i3 = i2 + 1;
                    if (i2 >= 60) {
                        break;
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    i2 = i3;
                }
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "run: " + BwbxUtil.v + " ver" + BwbxUtil.t, new Object[0]);
                boolean zIsBigAsset = BwbxUtil.isBigAsset(BwbxUtil.d);
                File file = new File(BwbxUtil.v);
                if (file.exists()) {
                    BwbxUtil.G.set(GameUtil.getFilesCount(file));
                    BwbxUtil.H.set(GameUtil.getDownloadedSize(BwbxUtil.d, String.valueOf(BwbxUtil.t)));
                    boolean unused = BwbxUtil.I = true;
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "last count:" + BwbxUtil.G + " size:" + BwbxUtil.H, new Object[0]);
                }
                boolean z3 = z2;
                boolean zB = ae.b(BwbxUtil.d, "lebian_module_dloadinfo", "split_zip_success", false);
                if (z3) {
                    z3 = !zB;
                }
                if (ae.b(BwbxUtil.d, "lebian_module_dloadinfo", "success", false)) {
                    String zipPath = GameUtil.getZipPath(BwbxUtil.d, false);
                    if ((new File(zipPath).exists() || (new File(BwbxUtil.v).exists() && !BwbxUtil.e(BwbxUtil.G.get()))) && !GameUtil.isCacheChecking(BwbxUtil.d)) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "load finished", new Object[0]);
                        if (zIsBigAsset) {
                            BwbxUtil.W();
                            zX = true;
                        } else {
                            zX = BwbxUtil.X();
                        }
                        if (zX && !new File(zipPath).exists()) {
                            BwbxUtil.setDownloadFinished(true);
                        }
                        BwbxUtil.sendDownloadProportionInfo(BwbxUtil.d, 100);
                        if (com.excelliance.lbsdk.main.bwbx.compat.a.b()) {
                            BwbxUtil.setMultifileCached();
                        }
                        BwbxUtil.Z();
                        return;
                    }
                    ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "downloadStartStatistics", false);
                    ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "success", false);
                }
                if (!ae.b(BwbxUtil.d, "lebian_module_dloadinfo", "downloadStartStatistics", false)) {
                    GameUtil.getIntance().bwbxDownloadStatistics(1);
                    t.a(BwbxUtil.d, 4);
                }
                boolean zA = m.a(BwbxUtil.d, "SHOW_FIRST_DIALOG_ALWAYS_BWBX", false);
                BwbxUtil.setMultifileCached();
                BwbxUtil.downloadAllDataConn = false;
                if (!aa.b(BwbxUtil.d)) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "network not available", new Object[0]);
                    boolean unused2 = BwbxUtil.s = false;
                    BwbxUtil.aR.removeCallbacks(BwbxUtil.aT);
                    BwbxUtil.aR.postDelayed(BwbxUtil.aT, 5000L);
                    boolean unused3 = BwbxUtil.ap = false;
                    BwbxUtil.aN.b();
                    return;
                }
                if (!BwbxUtil.aH && z3 && ((!aa.a(BwbxUtil.d) || zA) && !BwbxUtil.aq)) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "wifi not available", new Object[0]);
                    BwbxUtil.downloadAllDataConn = true;
                    boolean unused4 = BwbxUtil.s = false;
                    boolean unused5 = BwbxUtil.aL = true;
                    if (m.a(BwbxUtil.d, "CONTROL_FIRST_DIALOG_BWBX", false)) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "show first dialog by game", new Object[0]);
                        return;
                    } else {
                        BwbxUtil.showFirstDialog(8000);
                        return;
                    }
                }
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "checkNetChange:" + ((aa.b(BwbxUtil.d) && aa.a(BwbxUtil.d)) || BwbxUtil.aq), new Object[0]);
                boolean unused6 = BwbxUtil.aq = false;
                if (BwbxUtil.fileSetDl.size() <= 0 || !GameUtil.getIntance().getNotifySwitch(5)) {
                    try {
                        Thread.sleep(3000L);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } else {
                    BwbxUtil.reorderOrigin();
                }
                BwbxUtil.downloadAllSuccess = true;
                BwbxUtil.downloadAllInterrupt = false;
                BwbxUtil.downloadAllReturnFlag = false;
                BwbxUtil.aY.set(0);
                boolean zA2 = m.a(BwbxUtil.d, "BWBX_DOWNLOAD_USE_MULTI_THREAD", false);
                Intent intent = new Intent();
                intent.putExtra("action", 35);
                intent.putExtra("vercode", String.valueOf(BwbxUtil.t));
                GameUtil.getIntance().notifyAction(intent);
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(zA2 ? "multi-" : "single ");
                    sb.append("threaded download");
                    com.excelliance.lbsdk.main.g.b.b("BwbxUtil", sb.toString(), new Object[0]);
                }
                BwbxUtil.aN.a();
                if (zA2) {
                    ArrayList arrayList = new ArrayList();
                    for (int i4 = 0; i4 < 3; i4++) {
                        arrayList.add(ak.b().b(new Runnable() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.5.1
                            @Override // java.lang.Runnable
                            public void run() throws Throwable {
                                BwbxUtil.S();
                            }
                        }));
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        try {
                            ((Future) it.next()).get();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                } else {
                    BwbxUtil.S();
                }
                if (BwbxUtil.downloadAllReturnFlag) {
                    return;
                }
                boolean zIsCacheChecking = GameUtil.isCacheChecking(BwbxUtil.d);
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadAllExecute finish, downloadAllSuccess=" + BwbxUtil.downloadAllSuccess + " isChecking=" + zIsCacheChecking, new Object[0]);
                }
                if (!BwbxUtil.downloadAllSuccess || zIsCacheChecking) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("action", 38);
                    intent2.putExtra("vercode", String.valueOf(BwbxUtil.t));
                    GameUtil.getIntance().notifyAction(intent2);
                    if (!BwbxUtil.downloadAllInterrupt) {
                        boolean unused7 = BwbxUtil.aq = true;
                    }
                    if (!BwbxUtil.U.get()) {
                        BwbxUtil.aR.removeCallbacks(BwbxUtil.aT);
                        BwbxUtil.aR.post(BwbxUtil.aT);
                    }
                    boolean unused8 = BwbxUtil.s = false;
                    BwbxUtil.aN.b();
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "download finish", new Object[0]);
                Intent intent3 = new Intent();
                intent3.putExtra("action", 37);
                intent3.putExtra("vercode", String.valueOf(BwbxUtil.t));
                GameUtil.getIntance().notifyAction(intent3);
                BwbxUtil.sendDownloadProportionInfo(BwbxUtil.d, 100);
                ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "success", true);
                GameUtil.setDownloadedSize(BwbxUtil.d, Integer.toString(BwbxUtil.t), GameUtil.getIntance().getTotalOrigSize(false));
                if (BwbxUtil.ar != null && BwbxUtil.ar.b != null) {
                    BwbxUtil.isMultiEnd = true;
                    BwbxUtil.ar.b.cancel();
                    com.excelliance.lbsdk.main.g.b.e("BwbxUtil", "match is end!", new Object[0]);
                }
                GameUtil.getIntance().bwbxDownloadStatistics(2);
                ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "downloadStartStatistics", false);
                if (zIsBigAsset) {
                    BwbxUtil.W();
                }
                BwbxUtil.Z();
            }
        }.start();
    }

    public static void downloadAllControl(Context context, boolean z2) {
        boolean z3;
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadAllControl chooseDownloadByUser:" + z2 + " context:" + context, new Object[0]);
        try {
            InputStream inputStreamOpen = context.getAssets().open("tryflag");
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            z3 = true;
        } catch (IOException unused) {
            z3 = false;
        }
        if (m.a(context, "USE_BWBX", false) && z3) {
            U.set(z2);
            if (z2) {
                return;
            }
            downloadAll(false);
        }
    }

    public static void downloadFullRes(Context context) throws Throwable {
        downloadFullRes(context, 0);
    }

    public static void downloadFullRes(Context context, int i2) throws Throwable {
        downloadFullRes(context, i2, false);
    }

    public static void downloadFullRes(Context context, int i2, boolean z2) throws Throwable {
        downloadFullRes(context, i2, z2, false);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0047  */
    public static void downloadFullRes(Context context, int i2, boolean z2, boolean z3) throws Throwable {
        boolean z4;
        int i3;
        if (d == null) {
            d = context;
        }
        boolean z5 = false;
        String runningVersionCode = GameUtil.getIntance().getRunningVersionCode(false, true);
        if (ae.b(context, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(context), false)) {
            z4 = true;
        } else {
            if (ae.b(context, "lebian_module_dloadinfo", "twloadFinished-" + runningVersionCode, false)) {
                z4 = true;
            } else {
                z4 = false;
            }
        }
        boolean z6 = !z4 && GameUtil.getIntance().isTwload(context);
        int i4 = z6 ? 2 : 1;
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFullRes: ziptype:" + i4, new Object[0]);
        if (checkDlFullRes(context, true, z2, i2 == 0, i4)) {
            String zipPath = GameUtil.getZipPath(context);
            if (z6) {
                zipPath = GameUtil.getTwLoadPath(context);
            }
            if (!new File(zipPath.substring(0, zipPath.lastIndexOf("."))).exists() || new File(zipPath).exists()) {
                sendDownloadInfo(context, 1);
                boolean zA = m.a(d, "CHOOSE_BY_USER_BWBX_LATER", false);
                boolean zB = ae.b(d, "lebian_module_gameinfo", "hint", false);
                if (!zA || zB) {
                    i3 = i2;
                } else {
                    ae.a(d, "lebian_module_gameinfo", "hint", true);
                    if (al.d(d)) {
                        return;
                    }
                    if (aa.a(context) || an) {
                        GameUtil.switchToDownloadFullRes(context, 1, false);
                        return;
                    } else {
                        if (m.a(d, "SHOW_DIALOG_WITHOUT_WIFI_WHEN_LBRES_LATER", false)) {
                            aR.removeMessages(4);
                            Message messageObtainMessage = aR.obtainMessage(4);
                            messageObtainMessage.arg2 = 3;
                            aR.sendMessageDelayed(messageObtainMessage, 0L);
                            return;
                        }
                        i3 = 2;
                    }
                }
                if (z3) {
                    GameUtil.switchToDownloadFullRes(context, i4, false);
                    return;
                }
                long downloadedSize = new File(zipPath).exists() ? getDownloadedSize(d) : 0L;
                Intent intent = new Intent(context.getPackageName() + ACTION_QUERY_USER);
                intent.putExtra(ACTION_TYPE, ACTION_TYPE_DOWNLOAD_ALL_RES);
                GameUtil.getIntance().setContext(d);
                boolean zB2 = ae.b(d, "lebian_module_dloadinfo", "split_zip_success", false);
                if (com.excelliance.lbsdk.main.p.d.b(context) && zB2) {
                    return;
                }
                if (com.excelliance.lbsdk.main.p.d.b(context) && i4 == 1) {
                    g.a().a(G.get());
                    long jA = g.a().a(d);
                    if (jA == -1) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFullRes has all files", new Object[0]);
                        return;
                    }
                    int iB = g.a().b();
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadFullRes  count:" + iB + " ,offset:" + jA, new Object[0]);
                    intent.putExtra(ACTION_SIZE, isBigAsset(d) ? g.a().c(d) : (g.a().c() - downloadedSize) - jA);
                    Bundle bundle = new Bundle();
                    bundle.putLong("offset", jA);
                    bundle.putInt("count", iB);
                    intent.putExtras(bundle);
                } else {
                    intent.putExtra(ACTION_SIZE, getOrigSize(context, true, i4) - downloadedSize);
                }
                intent.putExtra("triggerReason", i3);
                intent.putExtra("zipType", i4);
                if (AbTestManager.getInstance(d).getBehavior(2) == 6 && Y()) {
                    z5 = true;
                }
                intent.putExtra("needOneButton", z5);
                b(context, intent);
                dialogStatistics(d, i3 == 1 ? DIALOG_TYPE_NET_POOR : DIALOG_TYPE_OLD_USER);
            }
        }
    }

    public static void downloadFullRes(Context context, boolean z2) throws Throwable {
        downloadFullRes(context, 0, false, z2);
    }

    /* JADX WARN: Code duplicated, block: B:108:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:110:0x02f7  */
    /* JADX WARN: Code duplicated, block: B:114:0x0351 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:124:0x0385  */
    /* JADX WARN: Code duplicated, block: B:129:0x0392  */
    /* JADX WARN: Code duplicated, block: B:132:0x039e  */
    /* JADX WARN: Code duplicated, block: B:134:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:137:0x03f6  */
    /* JADX WARN: Code duplicated, block: B:140:0x03fe A[LOOP:1: B:140:0x03fe->B:143:0x040e, LOOP_START, PHI: r18
  0x03fe: PHI (r18v3 int) = (r18v1 int), (r18v5 int) binds: [B:139:0x03fc, B:143:0x040e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:144:0x0410  */
    /* JADX WARN: Code duplicated, block: B:147:0x0416  */
    /* JADX WARN: Code duplicated, block: B:149:0x043b  */
    /* JADX WARN: Code duplicated, block: B:151:0x043f  */
    /* JADX WARN: Code duplicated, block: B:153:0x0465  */
    /* JADX WARN: Code duplicated, block: B:157:0x049a A[Catch: all -> 0x04af, Exception -> 0x04b1, TRY_LEAVE, TryCatch #3 {Exception -> 0x04b1, blocks: (B:155:0x0468, B:157:0x049a, B:160:0x04ab), top: B:204:0x0468, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x04ab A[Catch: all -> 0x04af, Exception -> 0x04b1, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x04b1, blocks: (B:155:0x0468, B:157:0x049a, B:160:0x04ab), top: B:204:0x0468, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:173:0x04d4  */
    /* JADX WARN: Code duplicated, block: B:174:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:182:0x0511 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:186:0x0520  */
    /* JADX WARN: Code duplicated, block: B:187:0x052a  */
    /* JADX WARN: Code duplicated, block: B:190:0x0531  */
    /* JADX WARN: Code duplicated, block: B:199:0x0505 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:203:0x0217 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:204:0x0468 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:207:0x04c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:208:0x04ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:210:0x038a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x0214  */
    /* JADX WARN: Code duplicated, block: B:71:0x021f A[Catch: all -> 0x02ad, TryCatch #2 {, blocks: (B:69:0x0217, B:71:0x021f, B:74:0x0228, B:75:0x022a, B:77:0x022d, B:79:0x024d, B:80:0x024f, B:82:0x0257, B:83:0x0259, B:85:0x0265, B:87:0x0292, B:91:0x029c, B:92:0x02ab, B:90:0x0299, B:86:0x0284), top: B:203:0x0217, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x024d A[Catch: all -> 0x02ad, TryCatch #2 {, blocks: (B:69:0x0217, B:71:0x021f, B:74:0x0228, B:75:0x022a, B:77:0x022d, B:79:0x024d, B:80:0x024f, B:82:0x0257, B:83:0x0259, B:85:0x0265, B:87:0x0292, B:91:0x029c, B:92:0x02ab, B:90:0x0299, B:86:0x0284), top: B:203:0x0217, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0257 A[Catch: all -> 0x02ad, TryCatch #2 {, blocks: (B:69:0x0217, B:71:0x021f, B:74:0x0228, B:75:0x022a, B:77:0x022d, B:79:0x024d, B:80:0x024f, B:82:0x0257, B:83:0x0259, B:85:0x0265, B:87:0x0292, B:91:0x029c, B:92:0x02ab, B:90:0x0299, B:86:0x0284), top: B:203:0x0217, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0265 A[Catch: all -> 0x02ad, TryCatch #2 {, blocks: (B:69:0x0217, B:71:0x021f, B:74:0x0228, B:75:0x022a, B:77:0x022d, B:79:0x024d, B:80:0x024f, B:82:0x0257, B:83:0x0259, B:85:0x0265, B:87:0x0292, B:91:0x029c, B:92:0x02ab, B:90:0x0299, B:86:0x0284), top: B:203:0x0217, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x0284 A[Catch: all -> 0x02ad, TRY_LEAVE, TryCatch #2 {, blocks: (B:69:0x0217, B:71:0x021f, B:74:0x0228, B:75:0x022a, B:77:0x022d, B:79:0x024d, B:80:0x024f, B:82:0x0257, B:83:0x0259, B:85:0x0265, B:87:0x0292, B:91:0x029c, B:92:0x02ab, B:90:0x0299, B:86:0x0284), top: B:203:0x0217, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x02b4  */
    /* JADX WARN: Instruction removed from duplicated block: B:85:0x0265, please report this as an issue */
    public static String downloadMissing(String str, byte[] bArr, long j2, int i2) {
        long j3;
        boolean z2;
        Thread threadCurrentThread;
        int priority;
        ArrayList arrayList;
        int i3;
        String str2;
        String str3;
        int i4;
        int size;
        String str4;
        int i5;
        ArrayList arrayList2;
        Thread thread;
        boolean z3;
        long j4;
        long jA;
        long j5;
        long length;
        String str5;
        String str6;
        boolean z4;
        Thread threadCurrentThread2;
        long jCurrentTimeMillis;
        com.excelliance.lbsdk.main.g.a().a(str);
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing filename:" + str, new Object[0]);
        StringBuffer stringBuffer = new StringBuffer(v);
        stringBuffer.append(str);
        String string = stringBuffer.toString();
        if (c(str)) {
            return string;
        }
        int iA = m.a(d, "MISSING_SIZE_LIMIT", 5) * 1024 * 1024;
        int iA2 = m.a(d, "OLD_USER_MISSING_CONDITION", 20);
        int behavior = AbTestManager.getInstance(d).getBehavior(1);
        if (behavior == 4) {
            iA = 1048576;
        } else if (behavior == 5) {
            iA = 10485760;
        }
        boolean z5 = F.incrementAndGet() >= iA2 && !Y();
        if (!downloadLater) {
            if (!q.a() && !Y() && dlAllTime.get() > 0 && dlAllSize.get() / dlAllTime.get() < 102 && (dlAllTime.get() > 5000 || F.get() >= 5)) {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "current size = " + dlAllSize + " time = " + dlAllTime, new Object[0]);
                stopSpeed = true;
                if (m.a(d, "SHOW_DIALOG_NETWORK_WEAK_BWBX", true) && System.currentTimeMillis() - weakDialogLastShowTime > 60000) {
                    weakDialogLastShowTime = System.currentTimeMillis();
                    aR.removeMessages(5);
                    Message messageObtainMessage = aR.obtainMessage(5);
                    messageObtainMessage.arg2 = 1;
                    j3 = 0;
                    aR.sendMessageDelayed(messageObtainMessage, 0L);
                    V = (short) (V + 1);
                }
            }
            z2 = z5;
            StringBuffer stringBuffer2 = new StringBuffer(f(0));
            stringBuffer2.append(d(str));
            f = stringBuffer2.toString();
            if (bArr == null) {
                synchronized (l) {
                    if (!str.equals(g)) {
                        i = str;
                    }
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "already downloading " + str, new Object[0]);
                    if (str.equals(g)) {
                        q = true;
                    }
                    if (str.equals(at)) {
                        r = true;
                    }
                    jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - L > 3000) {
                        K = jCurrentTimeMillis;
                        Q = true;
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "startTime=" + K, new Object[0]);
                    } else {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "remove hide progress", new Object[0]);
                        aR.removeMessages(1);
                    }
                    l.wait();
                    q = false;
                    r = false;
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing finished, file(%s)", str);
                    return string;
                }
            }
            if (!x) {
                transferMissing(str, g);
            }
            Bundle bundle = new Bundle();
            bundle.putInt("lbevent", 1);
            bundle.putString("missing", str);
            t.a(d, bundle);
            if (GameUtil.getIntance().getNotifySwitch(7)) {
                ak++;
                if (am) {
                    am = false;
                    aR.postDelayed(aS, GameUtil.getIntervalTime());
                }
            }
            int i6 = B;
            threadCurrentThread = Thread.currentThread();
            priority = threadCurrentThread.getPriority();
            threadCurrentThread.setPriority(10);
            arrayList = new ArrayList();
            av = true;
            i3 = i6;
            str2 = string;
            str3 = string + ".dl";
            i4 = 0;
            size = 3;
            while (true) {
                str4 = str2;
                i5 = priority;
                arrayList2 = arrayList;
                thread = threadCurrentThread;
                z3 = z2;
                j4 = j3;
                jA = a(str3, f, bArr, j2, i2, str, i4);
                if (jA >= j4) {
                    j5 = jA;
                } else {
                    j5 = jA;
                }
                if (j5 > j4) {
                    if (str.equals(h)) {
                        h = null;
                    }
                    com.excelliance.lbsdk.main.p.c.a(str, j5);
                    break;
                }
                str5 = ai;
                if (str5 == null) {
                    str6 = str3;
                } else {
                    str6 = str3;
                }
                if (arrayList2.size() < 3) {
                    do {
                        i4 = (i4 + 1) % 3;
                    } while (arrayList2.contains(Integer.valueOf(i4)));
                } else {
                    i3 = 0;
                }
                size--;
                if (size <= 0) {
                    i3--;
                    if (i3 <= 0) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing hit MAX_RETRY_COUNT, ignore", new Object[0]);
                        if (ae < af) {
                            z4 = true;
                            ae++;
                            handleDownloadFailed(j5, ab, f);
                        } else {
                            z4 = true;
                        }
                        aR.removeMessages(5);
                        Message messageObtainMessage2 = aR.obtainMessage(5);
                        messageObtainMessage2.obj = Boolean.valueOf(z4);
                        messageObtainMessage2.arg2 = 3;
                        aR.sendMessageDelayed(messageObtainMessage2, j4);
                        break;
                    }
                    StringBuffer stringBuffer3 = new StringBuffer(f(i4));
                    stringBuffer3.append(d(str));
                    f = stringBuffer3.toString();
                    c(j5);
                    size = 3 - arrayList2.size();
                    if (!com.excelliance.lbsdk.main.l.b.c()) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "coco2dx should pop retry dialog on background", new Object[0]);
                        break;
                    }
                    synchronized (j) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "before retry...", new Object[0]);
                        threadCurrentThread2 = Thread.currentThread();
                        k.put(threadCurrentThread2, false);
                        j.wait();
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "after retry...", new Object[0]);
                        if (k.get(threadCurrentThread2).booleanValue()) {
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "close retry dialog and stop missing download", new Object[0]);
                            k.remove(threadCurrentThread2);
                            break;
                        }
                        Thread.sleep(3000L);
                    }
                } else {
                    StringBuffer stringBuffer4 = new StringBuffer(f(i4));
                    stringBuffer4.append(d(str));
                    f = stringBuffer4.toString();
                }
                str3 = str6;
                str2 = str4;
                arrayList = arrayList2;
                j3 = j4;
                priority = i5;
                threadCurrentThread = thread;
                z2 = z3;
            }
            thread.setPriority(i5);
            synchronized (m) {
                av = false;
                m.notifyAll();
                if (jA >= j4) {
                    if (bArr == null) {
                        length = new File(str4).length();
                    } else {
                        length = bArr.length;
                    }
                    d(length);
                }
                if (z3) {
                    aR.removeMessages(5);
                    Message messageObtainMessage3 = aR.obtainMessage(5);
                    messageObtainMessage3.obj = true;
                    messageObtainMessage3.arg2 = 3;
                    aR.sendMessageDelayed(messageObtainMessage3, j4);
                    V = (short) (V + 1);
                    F.set(0);
                }
                return str4;
            }
        }
        if (!q.a() && !Y() && dlAllTime.get() > 10000 && dlAllSize.get() < iA) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "current size = " + dlAllSize + " time = " + dlAllTime, new Object[0]);
            stopSpeed = true;
            if (m.a(d, "SHOW_DIALOG_NETWORK_WEAK_BWBX", true) && System.currentTimeMillis() - weakDialogLastShowTime > 60000) {
                weakDialogLastShowTime = System.currentTimeMillis();
                aR.removeMessages(5);
                Message messageObtainMessage4 = aR.obtainMessage(5);
                messageObtainMessage4.arg2 = 1;
                aR.sendMessageDelayed(messageObtainMessage4, 0L);
                V = (short) (V + 1);
            }
        }
        if (aa.b(d) && (E.get() >= iA || z5)) {
            dlAllTime.set(0L);
            dlAllSize.set(0L);
            boolean zA = m.a(d, "SHOW_FIRST_DIALOG_ALWAYS_BWBX", false);
            if (aH || (aa.a(d) && !zA)) {
                downloadLater = false;
                downloadAll(false);
            } else {
                if (z5) {
                    F.set(0);
                    z5 = false;
                }
                downloadLater = false;
                aR.removeMessages(4);
                Message messageObtainMessage5 = aR.obtainMessage(4);
                messageObtainMessage5.arg2 = 3;
                aR.sendMessageDelayed(messageObtainMessage5, 0L);
            }
            z2 = z5;
            j3 = 0;
        }
        StringBuffer stringBuffer5 = new StringBuffer(f(0));
        stringBuffer5.append(d(str));
        f = stringBuffer5.toString();
        if (bArr == null) {
            synchronized (l) {
                if (!str.equals(g) && !str.equals(at)) {
                    i = str;
                }
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "already downloading " + str, new Object[0]);
                if (str.equals(g)) {
                    q = true;
                }
                if (str.equals(at)) {
                    r = true;
                }
                jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - L > 3000) {
                    K = jCurrentTimeMillis;
                    Q = true;
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "startTime=" + K, new Object[0]);
                } else {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "remove hide progress", new Object[0]);
                    aR.removeMessages(1);
                }
                try {
                    l.wait();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                q = false;
                r = false;
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing finished, file(%s)", str);
                return string;
            }
        }
        if (!x && g != null && g.length() > 0 && GameUtil.getIntance().getNotifySwitch(5)) {
            transferMissing(str, g);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("lbevent", 1);
        bundle2.putString("missing", str);
        t.a(d, bundle2);
        if (GameUtil.getIntance().getNotifySwitch(7)) {
            ak++;
            if (am) {
                am = false;
                aR.postDelayed(aS, GameUtil.getIntervalTime());
            }
        }
        int i7 = B;
        threadCurrentThread = Thread.currentThread();
        priority = threadCurrentThread.getPriority();
        threadCurrentThread.setPriority(10);
        arrayList = new ArrayList();
        av = true;
        i3 = i7;
        str2 = string;
        str3 = string + ".dl";
        i4 = 0;
        size = 3;
        while (true) {
            str4 = str2;
            i5 = priority;
            arrayList2 = arrayList;
            thread = threadCurrentThread;
            z3 = z2;
            j4 = j3;
            jA = a(str3, f, bArr, j2, i2, str, i4);
            if (jA >= j4 || bArr != null) {
                j5 = jA;
            } else {
                if (b(str, str3)) {
                    j5 = jA;
                } else {
                    j5 = ERR_MD5_MIS;
                    arrayList2.add(Integer.valueOf(i4));
                }
                if (j5 > j4) {
                    new File(str3).renameTo(new File(str4));
                    if (!new File(str4).exists()) {
                        j5 = ERR_RENAME_FAIL;
                    }
                }
            }
            if (j5 > j4) {
                if (str.equals(h)) {
                    h = null;
                }
                com.excelliance.lbsdk.main.p.c.a(str, j5);
                break;
            }
            str5 = ai;
            if (str5 == null && str5.contains("FileNotFoundException")) {
                GameUtil.setResRootData(d);
                v = GameUtil.getCachePath(d, String.valueOf(t));
                StringBuffer stringBuffer6 = new StringBuffer(v);
                stringBuffer6.append(str);
                String string2 = stringBuffer6.toString();
                str6 = string2 + ".dl";
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "missingSavedPath changed:" + string2, new Object[0]);
                str4 = string2;
            } else {
                str6 = str3;
            }
            if (arrayList2.size() < 3) {
                do {
                    i4 = (i4 + 1) % 3;
                } while (arrayList2.contains(Integer.valueOf(i4)));
            } else {
                i3 = 0;
            }
            size--;
            if (size <= 0) {
                i3--;
                if (i3 <= 0) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing hit MAX_RETRY_COUNT, ignore", new Object[0]);
                    if (ae < af) {
                        z4 = true;
                        ae++;
                        handleDownloadFailed(j5, ab, f);
                    } else {
                        z4 = true;
                    }
                    aR.removeMessages(5);
                    Message messageObtainMessage6 = aR.obtainMessage(5);
                    messageObtainMessage6.obj = Boolean.valueOf(z4);
                    messageObtainMessage6.arg2 = 3;
                    aR.sendMessageDelayed(messageObtainMessage6, j4);
                    break;
                }
                StringBuffer stringBuffer7 = new StringBuffer(f(i4));
                stringBuffer7.append(d(str));
                f = stringBuffer7.toString();
                c(j5);
                size = 3 - arrayList2.size();
                if (!com.excelliance.lbsdk.main.l.b.c()) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "coco2dx should pop retry dialog on background", new Object[0]);
                    break;
                }
                synchronized (j) {
                    try {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "before retry...", new Object[0]);
                        threadCurrentThread2 = Thread.currentThread();
                        k.put(threadCurrentThread2, false);
                        j.wait();
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "after retry...", new Object[0]);
                        if (k.get(threadCurrentThread2).booleanValue()) {
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "close retry dialog and stop missing download", new Object[0]);
                            k.remove(threadCurrentThread2);
                        } else {
                            Thread.sleep(3000L);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                break;
            }
            StringBuffer stringBuffer8 = new StringBuffer(f(i4));
            stringBuffer8.append(d(str));
            f = stringBuffer8.toString();
            str3 = str6;
            str2 = str4;
            arrayList = arrayList2;
            j3 = j4;
            priority = i5;
            threadCurrentThread = thread;
            z2 = z3;
        }
        thread.setPriority(i5);
        synchronized (m) {
            av = false;
            m.notifyAll();
        }
        if (jA >= j4 && (bArr != null || new File(str4).exists())) {
            if (bArr == null) {
                length = new File(str4).length();
            } else {
                length = bArr.length;
            }
            d(length);
        }
        if (z3 && m.a(d, "CHECK_OLD_USER_AUTO", false)) {
            aR.removeMessages(5);
            Message messageObtainMessage7 = aR.obtainMessage(5);
            messageObtainMessage7.obj = true;
            messageObtainMessage7.arg2 = 3;
            aR.sendMessageDelayed(messageObtainMessage7, j4);
            V = (short) (V + 1);
            F.set(0);
        }
        return str4;
        j3 = 0;
        z2 = z5;
        StringBuffer stringBuffer9 = new StringBuffer(f(0));
        stringBuffer9.append(d(str));
        f = stringBuffer9.toString();
        if (bArr == null) {
            synchronized (l) {
                if (!str.equals(g)) {
                    i = str;
                }
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "already downloading " + str, new Object[0]);
                if (str.equals(g)) {
                    q = true;
                }
                if (str.equals(at)) {
                    r = true;
                }
                jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - L > 3000) {
                    K = jCurrentTimeMillis;
                    Q = true;
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "startTime=" + K, new Object[0]);
                } else {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "remove hide progress", new Object[0]);
                    aR.removeMessages(1);
                }
                l.wait();
                q = false;
                r = false;
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing finished, file(%s)", str);
                return string;
            }
        }
        if (!x) {
            transferMissing(str, g);
        }
        Bundle bundle3 = new Bundle();
        bundle3.putInt("lbevent", 1);
        bundle3.putString("missing", str);
        t.a(d, bundle3);
        if (GameUtil.getIntance().getNotifySwitch(7)) {
            ak++;
            if (am) {
                am = false;
                aR.postDelayed(aS, GameUtil.getIntervalTime());
            }
        }
        int i8 = B;
        threadCurrentThread = Thread.currentThread();
        priority = threadCurrentThread.getPriority();
        threadCurrentThread.setPriority(10);
        arrayList = new ArrayList();
        av = true;
        i3 = i8;
        str2 = string;
        str3 = string + ".dl";
        i4 = 0;
        size = 3;
        while (true) {
            str4 = str2;
            i5 = priority;
            arrayList2 = arrayList;
            thread = threadCurrentThread;
            z3 = z2;
            j4 = j3;
            jA = a(str3, f, bArr, j2, i2, str, i4);
            if (jA >= j4) {
                j5 = jA;
            } else {
                j5 = jA;
            }
            if (j5 > j4) {
                if (str.equals(h)) {
                    h = null;
                }
                com.excelliance.lbsdk.main.p.c.a(str, j5);
                break;
            }
            str5 = ai;
            if (str5 == null) {
                str6 = str3;
            } else {
                str6 = str3;
            }
            if (arrayList2.size() < 3) {
                do {
                    i4 = (i4 + 1) % 3;
                } while (arrayList2.contains(Integer.valueOf(i4)));
            } else {
                i3 = 0;
            }
            size--;
            if (size <= 0) {
                i3--;
                if (i3 <= 0) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "downloadMissing hit MAX_RETRY_COUNT, ignore", new Object[0]);
                    if (ae < af) {
                        z4 = true;
                        ae++;
                        handleDownloadFailed(j5, ab, f);
                    } else {
                        z4 = true;
                    }
                    aR.removeMessages(5);
                    Message messageObtainMessage8 = aR.obtainMessage(5);
                    messageObtainMessage8.obj = Boolean.valueOf(z4);
                    messageObtainMessage8.arg2 = 3;
                    aR.sendMessageDelayed(messageObtainMessage8, j4);
                    break;
                }
                StringBuffer stringBuffer10 = new StringBuffer(f(i4));
                stringBuffer10.append(d(str));
                f = stringBuffer10.toString();
                c(j5);
                size = 3 - arrayList2.size();
                if (!com.excelliance.lbsdk.main.l.b.c()) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "coco2dx should pop retry dialog on background", new Object[0]);
                    break;
                }
                synchronized (j) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "before retry...", new Object[0]);
                    threadCurrentThread2 = Thread.currentThread();
                    k.put(threadCurrentThread2, false);
                    j.wait();
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "after retry...", new Object[0]);
                    if (k.get(threadCurrentThread2).booleanValue()) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "close retry dialog and stop missing download", new Object[0]);
                        k.remove(threadCurrentThread2);
                        break;
                    }
                    Thread.sleep(3000L);
                }
            } else {
                StringBuffer stringBuffer11 = new StringBuffer(f(i4));
                stringBuffer11.append(d(str));
                f = stringBuffer11.toString();
            }
            str3 = str6;
            str2 = str4;
            arrayList = arrayList2;
            j3 = j4;
            priority = i5;
            threadCurrentThread = thread;
            z2 = z3;
        }
        thread.setPriority(i5);
        synchronized (m) {
            av = false;
            m.notifyAll();
            if (jA >= j4) {
                if (bArr == null) {
                    length = new File(str4).length();
                } else {
                    length = bArr.length;
                }
                d(length);
            }
            if (z3) {
                aR.removeMessages(5);
                Message messageObtainMessage9 = aR.obtainMessage(5);
                messageObtainMessage9.obj = true;
                messageObtainMessage9.arg2 = 3;
                aR.sendMessageDelayed(messageObtainMessage9, j4);
                V = (short) (V + 1);
                F.set(0);
            }
            return str4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e(int i2) {
        return !g.a().a(i2, n.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(int i2) {
        return q.a(d, i2);
    }

    public static HashMap<Long, String> getAllPositionMap(String str) {
        return com.excelliance.lbsdk.main.bwbx.compat.a.a(str);
    }

    public static HashMap<String, BigFileInfo> getBigFileList() {
        return aI;
    }

    public static String getCurrCachePath() {
        if (d == null) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "getCurrCachePath mContext is null", new Object[0]);
            return null;
        }
        if (v != null) {
            return v;
        }
        return GameUtil.getCachePath(d, String.valueOf(Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true))));
    }

    public static long getCurrentDlSize() {
        if (d == null) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "getCurrentDlSize mContext is null", new Object[0]);
            return 0L;
        }
        if (!isSmallPkg()) {
            return 0L;
        }
        if (zipRatio == 0.0f) {
            zipRatio = (b(d, true) + getOrigSize(d, true, 2)) / (b(d, false) + getOrigSize(d, false, 2));
        }
        return (G.get() <= 0 || G.get() < n.size()) ? (long) (H.get() * zipRatio) : getTotalSize(true);
    }

    public static int getDownloadStatus() {
        return aN.d();
    }

    public static int getDownloadedFileCount() {
        return G.get();
    }

    public static long getDownloadedSize(Context context) {
        Map<String, String> mapA = ae.a(context, "lebian_module_bwbxdloader_multi_thread");
        long jLongValue = 0;
        int i2 = 0;
        while (i2 < mapA.size()) {
            i2++;
            String str = mapA.get(String.valueOf(i2));
            if (str != null) {
                jLongValue += Long.valueOf(str).longValue();
            } else {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "getDownloadedSize size:" + jLongValue + ",map size is " + mapA.size() + "," + i2 + " key's value is null", new Object[0]);
            }
        }
        return jLongValue;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x009a A[Catch: Exception -> 0x00b4, TRY_LEAVE, TryCatch #3 {Exception -> 0x00b4, blocks: (B:8:0x0015, B:10:0x004c, B:11:0x0050, B:13:0x0057, B:15:0x005f, B:17:0x0064, B:19:0x0084, B:21:0x008c, B:24:0x0095, B:27:0x009e, B:28:0x00a3, B:29:0x00a6, B:35:0x00b0, B:32:0x00ab, B:33:0x00ae, B:26:0x009a), top: B:50:0x0015, inners: #2 }] */
    @TargetApi(GameUtil.NOTIFY_TYPE_SPEED_REPORT)
    public static int getEngine(Context context) throws IOException {
        String packageCodePath;
        String[] strArr;
        int i2 = gEngine;
        if (i2 >= 0) {
            return i2;
        }
        gEngine = m.a(context, "ENGINE", -1);
        int i3 = gEngine;
        if (i3 >= 0) {
            return i3;
        }
        try {
            com.excelliance.lbsdk.main.g.b.d("BwbxUtil", "engine not be set", new Object[0]);
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String str = applicationInfo.sourceDir;
            File file = new File(applicationInfo.nativeLibraryDir);
            com.excelliance.lbsdk.main.g.b.d("BwbxUtil", "engine size:" + file.list().length, new Object[0]);
            if (file.list().length > 0) {
                a(context, file);
            } else {
                if (Build.VERSION.SDK_INT < 21 || (strArr = context.getApplicationInfo().splitSourceDirs) == null) {
                    packageCodePath = context.getPackageCodePath();
                } else {
                    packageCodePath = null;
                    for (String str2 : strArr) {
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "split apk path:" + str2, new Object[0]);
                        if (str2.contains("split_config.armeabi") || str2.contains("split_config.arm64_v8a") || str2.contains("split_config.x86")) {
                            packageCodePath = str2;
                        }
                    }
                    if (packageCodePath == null) {
                        packageCodePath = context.getPackageCodePath();
                    }
                }
                try {
                    ZipFile zipFile = new ZipFile(packageCodePath);
                    try {
                        a(context, zipFile);
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
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        int i4 = gEngine;
        if (i4 >= 0) {
            return i4;
        }
        gEngine = 2;
        return 2;
    }

    public static List<String> getFileSet(Context context) {
        if (d == null) {
            d = context;
        }
        R();
        return fileSetDl;
    }

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
            } catch (Exception e2) {
                e2.printStackTrace();
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
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "getFree: " + (availableBlocks * blockSize), new Object[0]);
        }
        return availableBlocks * blockSize;
    }

    public static String getFullVer() {
        return c;
    }

    public static String[] getHolePaths() {
        return com.excelliance.lbsdk.main.bwbx.compat.a.d();
    }

    public static String getMd5(String str) {
        return n.get(str);
    }

    /* JADX WARN: Code duplicated, block: B:83:0x020d A[Catch: all -> 0x0268, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000d, B:8:0x001b, B:12:0x002c, B:14:0x004e, B:17:0x0056, B:19:0x005e, B:21:0x0066, B:23:0x006e, B:25:0x0076, B:27:0x007e, B:29:0x0086, B:31:0x008e, B:32:0x009f, B:33:0x00b0, B:35:0x00b8, B:37:0x00c0, B:38:0x00c5, B:40:0x00c9, B:41:0x00d7, B:43:0x00db, B:47:0x00ea, B:51:0x0130, B:53:0x0136, B:55:0x016e, B:58:0x017b, B:60:0x0186, B:62:0x018e, B:64:0x01a0, B:65:0x01c0, B:67:0x01c6, B:69:0x01d1, B:70:0x01d3, B:82:0x020c, B:84:0x0211, B:86:0x0221, B:88:0x022c, B:89:0x0235, B:91:0x023b, B:83:0x020d, B:48:0x00f7, B:50:0x011e, B:71:0x01d4, B:73:0x01d8, B:78:0x0208, B:77:0x01e3, B:76:0x01df), top: B:103:0x0003, inners: #2 }] */
    public static synchronized String getMissingFile(String str, byte[] bArr, long j2, int i2, boolean z2) throws InterruptedException, NoSuchAlgorithmException, IOException {
        String string;
        boolean z3 = (aG.get(str) == null || aG.get(str).a() || aG.get(str).b()) ? false : true;
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "getMissingFile isV4:" + z3 + " filename:" + str, new Object[0]);
        if (str != null && str.length() > 0) {
            if (str.charAt(0) != '/' && !str.startsWith("assets/")) {
                if (!str.startsWith("res/") && (str.endsWith(".epg") || str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".pg2"))) {
                    str = "res/" + str;
                }
                str = "assets/" + str;
            }
            int iLastIndexOf = str.lastIndexOf("/assets/");
            if (iLastIndexOf > 1 && !n.containsKey(str)) {
                str = str.substring(iLastIndexOf + 1);
            }
            if (v == null) {
                v = GameUtil.getCachePath(d, String.valueOf(t));
            }
            if (w == null) {
                w = GameUtil.getCachePath(d, u);
            }
            String string2 = null;
            if (z2 && z3) {
                string = aG.get(str).c();
            } else {
                StringBuffer stringBuffer = new StringBuffer(v);
                stringBuffer.append(str);
                string = stringBuffer.toString();
                StringBuffer stringBuffer2 = new StringBuffer(w);
                stringBuffer2.append(str);
                string2 = stringBuffer2.toString();
                if (!new File(string2).exists()) {
                    String resPatchPath = GameUtil.getResPatchPath(d, t, false);
                    string2 = resPatchPath.substring(0, resPatchPath.lastIndexOf("."));
                }
            }
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "getMissingFile isV4:" + z3 + " fullPath:" + string + " oldPath:" + string2 + " isCacheChecking:" + GameUtil.isCacheChecking(d), new Object[0]);
            }
            if (bArr != null) {
                h.a(str, bArr, j2, i2);
            } else if (!new File(string).exists() && !z3) {
                if (new File(string2).exists() && GameUtil.isCacheChecking(d)) {
                    String strComputeFileMd5 = computeFileMd5(string2);
                    String str2 = n.get(str);
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "rmd5:" + strComputeFileMd5 + " emd5:" + str2, new Object[0]);
                    }
                    if (strComputeFileMd5.equals(str2)) {
                        if (!new File(string2).renameTo(new File(string)) && !new File(string).exists()) {
                            new File(string2).delete();
                        }
                    } else if (new File(string2).exists()) {
                        synchronized (GameUtil.checkCacheSync) {
                            if (GameUtil.checkingFile == str) {
                                try {
                                    GameUtil.checkCacheSync.wait();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            } else {
                                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "setChecked:" + str, new Object[0]);
                                GameUtil.checkedList.add(str);
                                new File(string2).delete();
                            }
                        }
                    }
                    h.a(str, bArr, j2, i2);
                } else {
                    h.a(str, bArr, j2, i2);
                }
            }
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "getMissingFile=" + string + ", exists=" + new File(string).exists(), new Object[0]);
            }
            return string;
        }
        return str;
    }

    public static long getOrigSize(Context context, boolean z2, int i2) {
        boolean z3 = i2 == 2;
        if (z3) {
            if (z2) {
                long j2 = Z;
                if (j2 >= 0) {
                    return j2;
                }
            }
            if (!z2) {
                long j3 = Y;
                if (j3 >= 0) {
                    return j3;
                }
            }
        } else {
            if (z2) {
                long j4 = X;
                if (j4 >= 0) {
                    return j4;
                }
            }
            if (!z2) {
                long j5 = W;
                if (j5 >= 0) {
                    return j5;
                }
            }
        }
        long origSize = GameUtil.getIntance().getOrigSize(z2, i2);
        if (z3) {
            if (z2) {
                Z = origSize;
            } else {
                Y = origSize;
            }
        } else if (z2) {
            X = origSize;
        } else {
            W = origSize;
        }
        return origSize;
    }

    public static String getPlatfromPackageName() {
        return d.getPackageName();
    }

    public static String getRecordPath() {
        return com.excelliance.lbsdk.main.l.e.f();
    }

    public static String getRecordPath(Context context) {
        return com.excelliance.lbsdk.main.l.e.f();
    }

    public static String getSdApk(Context context) {
        return getRecordPath(context) + "/lebian.apk";
    }

    public static String getSdPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String getThirdPackageName() {
        return d.getPackageName();
    }

    public static boolean getTotalCountAndDownloadedCount(int[] iArr) {
        iArr[0] = fileSetDl.size();
        iArr[1] = G.get();
        return true;
    }

    public static long getTotalOrigSize(Context context, boolean z2) {
        return getOrigSize(context, z2, 1) + getOrigSize(context, z2, 2);
    }

    public static long getTotalSize(boolean z2) {
        Context context = d;
        if (context != null) {
            return getOrigSize(context, z2, 1) + getOrigSize(d, z2, 2);
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "getTotalSize mContext is null", new Object[0]);
        return 0L;
    }

    public static String getUnitySoPath() {
        String str = com.excelliance.lbsdk.main.ae.a.a(d, v) + "/libunity.so";
        if (new File(str).exists()) {
            return str;
        }
        return null;
    }

    public static boolean handleAssetmanager(Context context) throws IOException {
        boolean zA = m.a(context, "HANDLE_ASSETMANAGER_BWBX", false);
        if (zA || getEngine(context) != 8) {
            return zA;
        }
        return true;
    }

    public static void handleDownloadFailed(long j2, int i2, String str) {
        Intent intent = new Intent();
        intent.putExtra("action", j2 != ((long) ERR_MD5_MIS) ? 14 : 19);
        intent.putExtra("vercode", String.valueOf(t));
        intent.putExtra(ACTION_TYPE, j2);
        intent.putExtra("code", i2);
        intent.putExtra(ACTION_URL, str);
        GameUtil.getIntance().notifyAction(intent);
    }

    public static boolean handleStrncpy(Context context) {
        boolean z2 = false;
        try {
            InputStream inputStreamOpen = context.getAssets().open("lbblank.txt");
            z2 = true;
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
        } catch (IOException unused) {
        }
        return z2;
    }

    public static boolean hasFileSet() {
        try {
            d.getAssets().open("lebian/fileSet1.png").close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static void init(Context context) throws Throwable {
        init(context, null);
    }

    /* JADX WARN: Type inference failed for: r5v20, types: [com.excelliance.lbsdk.main.bwbx.BwbxUtil$2] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.excelliance.lbsdk.main.bwbx.BwbxUtil$13] */
    public static void init(Context context, Application application) throws Throwable {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "bwbx init", new Object[0]);
        if (!isPrivacyBwbxInit) {
            initPrivacy(context, application);
        }
        try {
            com.excelliance.lbsdk.main.ae.a.a(d.getClassLoader(), com.excelliance.lbsdk.main.ae.a.a(d));
        } catch (Throwable th) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "no so load", th, new Object[0]);
        }
        if (al.c(context) || (!al.b(context) && isSmallPkg())) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "game process start background", new Object[0]);
            if (!m.a(d, "TWLOAD_API_CONTROL", false)) {
                aR.removeCallbacks(aU);
                aR.postDelayed(aU, 500L);
            }
        }
        new Thread("BwbxUtil:getNotifySwitch") { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.13
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                GameUtil.getIntance().getNotifySwitch(1);
            }
        }.start();
        if (needSoOut) {
            try {
                com.excelliance.lbsdk.main.ae.a.a(d.getClassLoader(), com.excelliance.lbsdk.main.ae.a.a(d, v));
            } catch (Throwable th2) {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "needSoOut so load failed", th2, new Object[0]);
            }
        }
        if (!fileSetDl.isEmpty()) {
            com.excelliance.lbsdk.main.g.a().a(d, fileSetDl, aI, new com.excelliance.lbsdk.main.g.c() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.14
                @Override // com.excelliance.lbsdk.main.g.c
                public void a(List<String> list) {
                    synchronized (BwbxUtil.aF) {
                        BwbxUtil.fileSetDl = new ArrayList(list);
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "fileSetDl parse reset size:" + BwbxUtil.fileSetDl.size(), new Object[0]);
                        BwbxUtil.isSwitch = true;
                        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "fileSetDl parse has reset", new Object[0]);
                    }
                }
            });
        }
        if (isSmallPkg()) {
            if (V()) {
                com.excelliance.lbsdk.main.g.a(true);
            } else {
                com.excelliance.lbsdk.main.g.a().a(d, t, T().split("/")[4], new com.excelliance.lbsdk.main.g.c() { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.15
                    @Override // com.excelliance.lbsdk.main.g.c
                    public void a(List<String> list) {
                        synchronized (BwbxUtil.aF) {
                            BwbxUtil.fileSetDl = new ArrayList(list);
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "fileSetDl reset size:" + BwbxUtil.fileSetDl.size(), new Object[0]);
                            BwbxUtil.isSwitch = true;
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "fileSetDl has reset", new Object[0]);
                        }
                    }
                });
            }
        }
        GameUtil.getIntance().setHandler(aR);
        if (!n.isEmpty()) {
            GameUtil.getIntance().checkCache();
            u = GameUtil.getIntance().getLastVer(d);
        }
        w = GameUtil.getCachePath(d, u);
        if (needSoOut) {
            LoadLib.load(v, d.getClassLoader());
        }
        new Thread("BwbxUtil:downloadAll1") { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                if (BwbxUtil.n.isEmpty()) {
                    ae.a(BwbxUtil.d, "lebian_module_dloadinfo", "success", true);
                    return;
                }
                long unused = BwbxUtil.al = System.currentTimeMillis();
                int iRequireResZip = GameUtil.getIntance().requireResZip(BwbxUtil.d);
                if ((iRequireResZip != -1 && BwbxUtil.checkDlFullRes(BwbxUtil.d, false, false, false, iRequireResZip)) || new File(GameUtil.getZipPath(BwbxUtil.d)).exists() || new File(GameUtil.getTwLoadPath(BwbxUtil.d)).exists()) {
                    return;
                }
                BwbxUtil.downloadAll(true);
            }
        }.start();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(d.getPackageName() + ACTION_DOWNLOAD_ALL);
        intentFilter.addAction(d.getPackageName() + ACTION_DOWNLOAD_ALL_LATER);
        intentFilter.addAction(d.getPackageName() + ACTION_CANCEL_DOWNLOAD_ALL);
        intentFilter.addAction(d.getPackageName() + ACTION_USER_CLICK);
        intentFilter.addAction(d.getPackageName() + ACTION_DOWNLOAD_FULL_RES_IN_MOBILE);
        intentFilter.addAction(d.getPackageName() + ACTION_EXTRACT_FINISHED);
        intentFilter.addAction(d.getPackageName() + ACTION_DOWNLOAD_CONTROL);
        s.a(d, aX, intentFilter);
    }

    public static void initPrivacy(Context context, Application application) throws IOException {
        String str;
        d = context;
        if (GameUtil.getIntance().getContext() == null) {
            an.a().a(context);
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "classloader:" + d.getClassLoader(), new Object[0]);
        getEngine(d);
        StringBuilder sb = new StringBuilder();
        sb.append("bwbx init engine:");
        if (isUnity()) {
            str = "unity";
        } else if (isUE4()) {
            str = "ue4";
        } else if (isUE3()) {
            str = "ue3";
        } else if (isCocos()) {
            str = "cocos";
        } else {
            str = isLaya() ? "laya" : "wanmei";
        }
        sb.append(str);
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", sb.toString(), new Object[0]);
        b(d, true);
        getOrigSize(d, true, 2);
        b(d, false);
        getOrigSize(d, false, 2);
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "after getOrigSize origSize:" + W + " origSizeCompress:" + X + " origSizeTwice:" + Y + " origSizeCompressTwice:" + Z, new Object[0]);
        }
        aH = m.a(d, "SHOW_BWBX_FLOAT_WINDOW", false);
        U.set(false);
        t = Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true));
        needSoOut = isNeedSoOut(d);
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "needSoOut :" + needSoOut, new Object[0]);
        R();
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(context.getPackageName());
        intent.putExtra("cancel", true);
        aG = com.excelliance.lbsdk.main.ae.c.a().a(d);
        com.excelliance.lbsdk.main.bwbx.compat.a.a(d);
        com.excelliance.lbsdk.main.bwbx.compat.a.a(application, n, aG.size() > 0);
        v = GameUtil.getCachePath(d, String.valueOf(t));
        b.a(d, n);
        g gVarA = g.a();
        Context context2 = d;
        gVarA.a(context2, isBigAsset(context2), t);
        AssetRedirector.getInstance().init(d, application);
        aI = new HashMap<>();
        if (isBigAsset(d)) {
            AssetRedirector.listBigFileNames(aI, BigFileInfo.class);
            if (getEngine(d) == 7) {
                Iterator<String> it = aI.keySet().iterator();
                while (it.hasNext()) {
                    b.a(d, it.next());
                }
            }
        }
        if (!n.isEmpty()) {
            b.a(d, aI);
        }
        if (isBigAsset(d)) {
            AssetRedirector.setUseMp4Download(aD);
        }
        isPrivacyBwbxInit = true;
        q.b(d);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0047  */
    /* JADX WARN: Code duplicated, block: B:20:0x005f A[Catch: IOException -> 0x006a, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x006a, blocks: (B:17:0x0051, B:18:0x005b, B:20:0x005f, B:24:0x0066, B:25:0x0069), top: B:48:0x0051, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x006c  */
    /* JADX WARN: Code duplicated, block: B:34:0x00bc A[Catch: Throwable -> 0x00c2, Exception -> 0x00c7, TRY_LEAVE, TryCatch #2 {Throwable -> 0x00c2, blocks: (B:32:0x009e, B:34:0x00bc), top: B:51:0x009e, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:27:0x006c, please report this as an issue */
    public static boolean isBigAsset(Context context) throws IOException {
        InputStream inputStreamOpen;
        String strE;
        ZipFile zipFile;
        ZipEntry entry;
        int i2 = aZ;
        if (i2 >= 0) {
            return i2 == 1;
        }
        aZ = 0;
        if (GameUtil.getIntance().getContext() == null) {
            GameUtil.getIntance().setContext(context);
        }
        if (isRecordPkg(context)) {
            if (new File(getRecordPath(context) + "/assets/lbres/lbres.rdata.meta").exists()) {
                aZ = 1;
            } else if (an.a().c()) {
                strE = an.a().e();
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isBigAssets filePath:" + strE, new Object[0]);
                if (strE != null && new File(strE).exists()) {
                    try {
                        zipFile = new ZipFile(strE);
                        try {
                            entry = zipFile.getEntry("assets/lbbigres.info");
                            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isBigAssets entry:" + entry, new Object[0]);
                            if (entry != null) {
                                aZ = 1;
                            }
                            zipFile.close();
                        } catch (Throwable th) {
                            try {
                                zipFile.close();
                            } catch (Throwable unused) {
                            }
                            throw th;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else {
                try {
                    inputStreamOpen = context.getAssets().open("lbbigres.info");
                    try {
                        aZ = 1;
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                        }
                    } catch (Throwable th2) {
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (Throwable unused2) {
                            }
                        }
                        throw th2;
                    }
                } catch (IOException unused3) {
                }
            }
        } else if (an.a().c()) {
            inputStreamOpen = context.getAssets().open("lbbigres.info");
            aZ = 1;
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
        } else {
            strE = an.a().e();
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isBigAssets filePath:" + strE, new Object[0]);
            if (strE != null) {
                zipFile = new ZipFile(strE);
                entry = zipFile.getEntry("assets/lbbigres.info");
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isBigAssets entry:" + entry, new Object[0]);
                if (entry != null) {
                    aZ = 1;
                }
                zipFile.close();
            }
        }
        return aZ == 1;
    }

    public static boolean isCocos() {
        return gEngine == 2;
    }

    public static boolean isDownloadFinished(Context context) {
        an.a().a(context);
        boolean z2 = true;
        if (!isSmallPkg() || n.isEmpty()) {
            return true;
        }
        boolean zB = ae.b(d, "lebian_module_dloadinfo", "success", false);
        boolean zB2 = ae.b(d, "lebian_module_dloadinfo", "split_zip_success", false);
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "mTotalDownloadedAllCount:" + G + " fileSet:" + n.size() + " success:" + zB + " split success:" + zB2, new Object[0]);
        if (!isBigAsset(context) ? !(G.get() >= n.size() || zB || zB2) : !(zB || zB2)) {
            z2 = false;
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isDownloadFinished ret:" + z2, new Object[0]);
        return z2;
    }

    public static boolean isInternalPkg() {
        try {
            InputStream inputStreamOpen = d.getAssets().open("zmtest");
            if (inputStreamOpen == null) {
                return true;
            }
            try {
                inputStreamOpen.close();
                return true;
            } catch (IOException unused) {
                return true;
            }
        } catch (IOException unused2) {
            return false;
        }
    }

    public static boolean isLaya() {
        return gEngine == 8;
    }

    public static boolean isNeedSoOut(Context context) {
        try {
            context.getAssets().open("lebian/lbunitysoout").close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isRecordPkg(Context context) throws IOException {
        try {
            InputStream inputStreamOpen = context.getAssets().open("zmunity.small");
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isResExtracting() {
        return aE;
    }

    public static boolean isSmallPkg() {
        Boolean bool = mIsSmall;
        if (bool != null) {
            return bool.booleanValue();
        }
        mIsSmall = Boolean.valueOf(an.a().i());
        return mIsSmall.booleanValue();
    }

    public static boolean isTwloadFinished(Context context) {
        an.a().a(context);
        boolean z2 = true;
        if (!isSmallPkg() || !GameUtil.getIntance().isTwload(d) || ae.b(d, "lebian_module_dloadinfo", "success", false)) {
            return true;
        }
        String runningVersionCode = GameUtil.getIntance().getRunningVersionCode(false, true);
        if (!ae.b(context, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(context), false)) {
            if (!ae.b(context, "lebian_module_dloadinfo", "twloadFinished-" + runningVersionCode, false)) {
                z2 = false;
            }
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isTwloadFinished twloadFinished:" + z2, new Object[0]);
        return z2;
    }

    public static boolean isTwloadFinishedLite(Context context) {
        boolean z2 = true;
        String runningVersionCode = GameUtil.getIntance().getRunningVersionCode(false, true);
        if (!ae.b(context, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(context), false)) {
            if (!ae.b(context, "lebian_module_dloadinfo", "twloadFinished-" + runningVersionCode, false)) {
                z2 = false;
            }
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "isTwloadFinished twloadFinished:" + z2, new Object[0]);
        return z2;
    }

    public static boolean isUE3() {
        return gEngine == 6;
    }

    public static boolean isUE4() {
        return gEngine == 3;
    }

    public static boolean isUnity() {
        int i2 = gEngine;
        return i2 == 1 || i2 == 5;
    }

    public static boolean isWanmei() {
        return gEngine == 4;
    }

    private static void k(boolean z2) {
        if (c != null) {
            if (Q) {
                Q = false;
                aR.removeMessages(3);
                aR.sendMessageDelayed(aR.obtainMessage(3), 0L);
                return;
            }
            return;
        }
        aR.removeMessages(2);
        Intent intent = new Intent(d.getPackageName() + ACTION_QUERY_USER);
        intent.putExtra(ACTION_TYPE, ACTION_TYPE_PROGRESS);
        intent.putExtra(ACTION_PROGRESS, 1);
        b(d, intent);
        O = true;
        aC = 0L;
    }

    public static boolean missingHit(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        if (str.charAt(0) != '/' && !str.startsWith("assets/")) {
            if (!str.startsWith("res/") && (str.endsWith(".epg") || str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".pg2"))) {
                str = "res/" + str;
            }
            str = "assets/" + str;
        }
        int iLastIndexOf = str.lastIndexOf("/assets/");
        if (iLastIndexOf > 1 && !n.containsKey(str)) {
            str = str.substring(iLastIndexOf + 1);
        }
        return n.containsKey(str) || aG.containsKey(str);
    }

    public static void preloadDialog(Context context) {
        Intent intent = new Intent(d.getPackageName() + ACTION_QUERY_USER);
        intent.putExtra(ACTION_TYPE, ACTION_TYPE_PRELOAD_DIALOG);
        b(context, intent);
    }

    public static long recomputeOffset(Context context, Bundle bundle) {
        if (bundle == null) {
            return 0L;
        }
        long jA = g.a().a(context, getDownloadedFileCount());
        if (jA >= 0) {
            bundle.putLong("offset", jA);
            bundle.putInt("count", g.a().b());
        }
        return 0L;
    }

    public static boolean removeDownloadStatusListener(Object obj) {
        return aN.b(obj);
    }

    public static void reorderOrigin() {
        GameUtil intance = GameUtil.getIntance();
        StringBuilder sb = new StringBuilder(intance.getBaseUrl("s") + "newreslist.php");
        sb.append("?chid=" + intance.getApkMainCh());
        sb.append("&md5=" + computeFileMd5(d.getPackageResourcePath()));
        sb.append("&cpmeta=" + intance.getClientChId());
        sb.append("&vercode=" + t);
        sb.append("&uid=" + an.a().b());
        sb.append("&pkg=" + d.getPackageName());
        try {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "reorderOrigin getURL:" + sb.toString(), new Object[0]);
            HashMap<String, Object> mapB = aa.b(sb.toString(), d);
            int iIntValue = ((Integer) mapB.get("code")).intValue();
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "getStatusCode=" + iIntValue, new Object[0]);
            if (iIntValue != 200) {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "reorderOrigin error getStatusCode:" + iIntValue, new Object[0]);
                return;
            }
            String str = (String) mapB.get("response");
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "reorderOrigin result:" + str, new Object[0]);
            if (str == null) {
                str = "";
            }
            if (str != null && str.length() != 0) {
                ByteArrayInputStream byteArrayInputStream = (str == null || str.trim().equals("")) ? null : new ByteArrayInputStream(str.getBytes());
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                    if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equalsIgnoreCase("flist")) {
                        String attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "tfpath");
                        String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "lfpath");
                        if (attributeValue != null && attributeValue2 != null && !attributeValue.equals(attributeValue2) && fileSetDl.contains(attributeValue) && fileSetDl.contains(attributeValue2)) {
                            fileSetDl.remove(attributeValue);
                            fileSetDl.add(fileSetDl.indexOf(attributeValue2), attributeValue);
                        }
                    }
                }
                byteArrayInputStream.close();
            }
        } catch (Exception e2) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "reorderOrigin e=" + e2, new Object[0]);
        }
    }

    public static void sendDownloadInfo(Context context, int i2) {
        Intent intent = new Intent("com.excelliance.open.action.downloadResInfo");
        intent.setPackage(context.getPackageName());
        intent.putExtra("result", i2);
        context.sendBroadcast(intent);
    }

    public static void sendDownloadProportionInfo(Context context, int i2) {
        Intent intent = new Intent(context.getPackageName() + ACTION_DOWNLOAD_PROPORTION);
        intent.setPackage(context.getPackageName());
        intent.putExtra("proportion", i2);
        intent.putExtra(ACTION_SIZE, (long) (((float) (getTotalOrigSize(d, false) - H.get())) * (((float) b(d, true)) / ((float) b(d, false)))));
        context.sendBroadcast(intent);
    }

    public static void setCached(String str, String str2, boolean z2) {
        boolean z3;
        if (isUE4()) {
            return;
        }
        if (z2 || !(isUnity() || com.excelliance.lbsdk.main.bwbx.compat.a.b())) {
            z3 = false;
        } else if (!isUnity()) {
            return;
        } else {
            z3 = true;
        }
        AssetRedirector.setCached(str, str2, z3);
    }

    public static void setContext(Context context) {
        if (context != null) {
            d = context;
        }
    }

    public static void setDownloadFinished(boolean z2) {
        boolean z3;
        if (isUE4()) {
            return;
        }
        if (!isUnity() && !com.excelliance.lbsdk.main.bwbx.compat.a.b()) {
            z3 = false;
        } else if (!isUnity()) {
            return;
        } else {
            z3 = true;
        }
        AssetRedirector.setDownloadFinished(z2, z3);
    }

    public static void setFull(String str, String str2) {
        try {
            a = new ZipFile(str2);
            c = str;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void setMultifileCached() {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "setMultifileCached start", new Object[0]);
        if (!fileSetDl.isEmpty() && isUnity()) {
            for (int i2 = 0; i2 < fileSetDl.size(); i2++) {
                String str = fileSetDl.get(i2);
                StringBuffer stringBuffer = new StringBuffer(v);
                stringBuffer.append(str);
                String string = stringBuffer.toString();
                if (new File(string).exists()) {
                    setCached(str, string, false);
                }
            }
        }
        if (isUnity()) {
            HashMap<String, com.excelliance.lbsdk.main.ae.b> mapA = com.excelliance.lbsdk.main.ae.c.a().a(d);
            for (String str2 : mapA.keySet()) {
                String strC = mapA.get(str2).c();
                boolean zA = mapA.get(str2).a();
                boolean zB = mapA.get(str2).b();
                if (new File(strC).exists() && !zB && !zA) {
                    setCached(str2, strC, false);
                }
            }
        }
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "setMultifileCached end", new Object[0]);
    }

    public static void setResExtracting(boolean z2) {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "setResExtracting extracting:" + z2, new Object[0]);
        aE = z2;
    }

    public static void setShowProgressImmediately(boolean z2) {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "setShowProgressImmediately:" + z2, new Object[0]);
        aA = z2;
    }

    public static void setUseCache(Context context, boolean z2) {
        boolean zIsUnity = isUnity();
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("BwbxUtil", "setUseCache isUnity:" + zIsUnity, new Object[0]);
        }
        AssetRedirector.setUseCache(z2);
    }

    public static void showDownloadRemainingRes(Context context) {
        Message messageObtainMessage;
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "showDownloadRemainingRes", new Object[0]);
        if (context == null) {
            return;
        }
        if (d == null) {
            d = context;
        }
        an.a().a(context);
        if (!isSmallPkg()) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "showDownloadRemainingRes is full pkg", new Object[0]);
            return;
        }
        if (isDownloadFinished(d)) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "showDownloadRemainingRes download finished", new Object[0]);
            return;
        }
        if (!aa.b(d)) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "showDownloadRemainingRes no network", new Object[0]);
            return;
        }
        if (downloadLater) {
            boolean zA = m.a(d, "SHOW_FIRST_DIALOG_ALWAYS_BWBX", false);
            if (!aa.a(d) || zA) {
                aR.removeMessages(4);
                messageObtainMessage = aR.obtainMessage(4);
                messageObtainMessage.arg2 = 4;
            } else {
                downloadLater = false;
                downloadAll(false);
                aR.removeMessages(4);
                messageObtainMessage = aR.obtainMessage(4);
                messageObtainMessage.arg2 = 5;
            }
        } else {
            aR.removeMessages(4);
            messageObtainMessage = aR.obtainMessage(4);
            messageObtainMessage.arg2 = 5;
        }
        aR.sendMessageDelayed(messageObtainMessage, 0L);
    }

    public static void showFirstDialog() {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "showFirstDialog called by game", new Object[0]);
        if (!isSmallPkg()) {
            com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "showFirstDialog is full apk", new Object[0]);
        } else {
            aL = true;
            showFirstDialog(1000);
        }
    }

    public static void showFirstDialog(int i2) {
        if (aL) {
            aL = false;
            boolean zA = m.a(d, "SHOW_FIRST_DIALOG_ALWAYS_BWBX", false);
            boolean zA2 = m.a(d, "SHOW_FIRST_DIALOG_WITHOUT_WIFI_BWBX", true);
            ae.a(d, "lebian_module_dloadinfo", "restartAfterDl");
            int behavior = AbTestManager.getInstance(d).getBehavior(0);
            if (!zA2 || behavior == 2 || aM.get() || (aa.a(d) && !zA)) {
                U();
            } else {
                if (ao && !aB) {
                    aR.removeMessages(4);
                    Message messageObtainMessage = aR.obtainMessage(4);
                    messageObtainMessage.arg2 = 1;
                    aR.sendMessageDelayed(messageObtainMessage, i2);
                }
                aB = false;
                ao = true;
            }
            aR.removeCallbacks(aT);
            aR.postDelayed(aT, 5000L);
            aN.b();
        }
    }

    public static int singleFileDownload(String str, SingleDownloadCallBack singleDownloadCallBack) {
        int i2;
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "singleFileDownload filename:" + str, new Object[0]);
        if (d == null) {
            singleDownloadCallBack.onSingleResult(str, -2);
            return 0;
        }
        boolean zIsSmallPkg = isSmallPkg();
        if (!m.a(d, "USE_BWBX", false) || !zIsSmallPkg) {
            singleDownloadCallBack.onSingleResult(str, -2);
            return 0;
        }
        if (str == null) {
            singleDownloadCallBack.onSingleResult(str, -2);
            return 0;
        }
        if (!str.startsWith("assets/")) {
            str = "assets/" + str;
        }
        StringBuffer stringBuffer = new StringBuffer(GameUtil.getCachePath(d));
        stringBuffer.append(str);
        stringBuffer.append(".dload");
        String string = stringBuffer.toString();
        if (new File(string.substring(0, string.lastIndexOf(46))).exists()) {
            i2 = 100;
        } else {
            if (fileSetDl.contains(str)) {
                com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "singleFileDownload downloadMissing filename:" + str, new Object[0]);
                aw.add(str);
                com.excelliance.lbsdk.main.g.a().a(str);
                if (!az.get()) {
                    az.set(true);
                    a(singleDownloadCallBack);
                }
                return 0;
            }
            i2 = -1;
        }
        singleDownloadCallBack.onSingleResult(str, i2);
        return 0;
    }

    public static byte[] toByteArray(String str) throws Throwable {
        File file = new File(str);
        if (!file.exists()) {
            throw new FileNotFoundException(str);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i2 = bufferedInputStream2.read(bArr, 0, 1024);
                    if (-1 == i2) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    bufferedInputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                byteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e3) {
                e = e3;
                bufferedInputStream = bufferedInputStream2;
                e.printStackTrace();
                throw e;
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = bufferedInputStream2;
                try {
                    bufferedInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
        }
    }

    public static void transferMissing(int i2) {
        GameUtil intance = GameUtil.getIntance();
        Intent intent = new Intent();
        intent.putExtra("action", 17);
        intent.putExtra("vercode", String.valueOf(t));
        intent.putExtra("cpmeta", intance.getClientChId());
        intent.putExtra("pkg", d.getPackageName());
        intent.putExtra("cnt", i2);
        GameUtil.getIntance().notifyAction(intent);
    }

    public static void transferMissing(final String str, final String str2) {
        ak.a().a(new Thread("BwbxUtil:transferMissing") { // from class: com.excelliance.lbsdk.main.bwbx.BwbxUtil.6
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    GameUtil intance = GameUtil.getIntance();
                    StringBuilder sb = new StringBuilder(intance.getBaseUrl("s") + "fileforward.php");
                    sb.append("?chid=" + intance.getApkMainCh());
                    sb.append("&uid=" + an.a().b());
                    sb.append("&vercode=" + BwbxUtil.t);
                    sb.append("&md5=" + BwbxUtil.computeFileMd5(BwbxUtil.d.getPackageResourcePath()));
                    sb.append("&cpmeta=" + intance.getClientChId());
                    sb.append("&pkg=" + BwbxUtil.d.getPackageName());
                    sb.append("&tf=" + str);
                    sb.append("&lf=" + str2);
                    aa.b(sb.toString(), BwbxUtil.d);
                } catch (Exception e2) {
                    com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "transferMissing Exception:", e2, new Object[0]);
                }
            }
        });
    }

    public static void userAction(int i2, int i3) {
        if (i2 == ACTION_TYPE_QUERY) {
            synchronized (S) {
                T = i3;
                S.notifyAll();
            }
        }
    }

    public static void userClick(int i2, int i3) throws Throwable {
        com.excelliance.lbsdk.main.g.b.c("BwbxUtil", "userClick type:" + i2 + " clickType:" + i3, new Object[0]);
        Intent intent = new Intent();
        intent.putExtra("action", 13);
        intent.putExtra("vercode", String.valueOf(t));
        intent.putExtra(ACTION_TYPE, i2);
        intent.putExtra("tp", i3);
        intent.putExtra("package", d.getPackageName());
        intent.putExtra("nettype", aa.c(d));
        GameUtil.getIntance().notifyAction(intent);
        Bundle bundle = new Bundle();
        bundle.putInt("lbevent", 3);
        bundle.putInt("dialog_type", i2);
        bundle.putInt("click_type", i3);
        t.a(d, bundle);
        if (i2 == DIALOG_TYPE_OLD_USER && m.a(d, "SHOW_DIALOG_BUTTON_BY_OLD_USER", false) && i3 == 2) {
            Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
            intent2.setComponent(new ComponentName(d.getPackageName(), BGService.class.getName()));
            intent2.setPackage(d.getPackageName());
            intent2.putExtra("downloadAfterQuit", true);
            intent2.putExtra("extra.lbsdk.hostintent", true);
            b(intent2);
        }
        if ((i2 == DIALOG_TYPE_OLD_USER_FIRST || i2 == DIALOG_TYPE_OLD_USER) && i3 == 1) {
            GameUtil.getIntance().clearChecked();
        }
    }

    public static void userDownload() {
        userApproveDownload = true;
        an = true;
        downloadLater = false;
        synchronized (l) {
            l.notifyAll();
        }
        synchronized (au) {
            au.notifyAll();
        }
        downloadAll(false);
    }

    public static void userRetry() {
        synchronized (j) {
            j.notifyAll();
        }
    }

    public static void writeVisitedFiles(String str, String str2) {
        try {
            if (newContentFw == null) {
                newContentFw = new FileWriter(str, true);
            }
            if (newContentBufw == null) {
                newContentBufw = new BufferedWriter(newContentFw);
            }
            newContentBufw.write(str2);
            newContentBufw.newLine();
            newContentBufw.flush();
        } catch (Exception unused) {
        }
    }
}
