package com.excelliance.lbsdk.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Xml;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class q {
    private static com.excelliance.lbsdk.main.p.e Z;
    private static com.excelliance.lbsdk.main.p.a aa;
    private static long ab;
    private static int ac;
    private static com.excelliance.lbsdk.main.ab.a af;
    private static Context d;
    private static c z;
    private static Map<String, String> e = new TreeMap();
    private static List<String> f = new ArrayList();
    private static boolean g = false;
    public static int a = 0;
    private static String h = null;
    private static String i = null;
    private static String j = null;
    private static String k = null;
    private static String l = null;
    private static String m = null;
    private static boolean n = false;
    private static ArrayList<Integer> o = new ArrayList<>();
    private static int p = 0;
    private static String q = null;
    private static String r = null;
    private static int s = 0;
    private static int t = -2;
    private static int u = -3;
    private static int v = -4;
    private static int w = -5;
    private static int x = -6;
    private static int y = -7;
    private static boolean A = false;
    public static boolean b = false;
    private static boolean B = false;
    private static int C = 0;
    private static int D = 0;
    private static int E = 1;
    private static boolean F = false;
    private static boolean G = false;
    private static boolean H = false;
    private static boolean I = false;
    private static boolean J = false;
    private static int K = 0;
    private static int L = 0;
    private static long M = 0;
    private static int N = 0;
    private static int O = 0;
    private static boolean P = false;
    private static long[] Q = new long[2];
    private static boolean R = true;
    public static boolean c = false;
    private static long S = 0;
    private static int T = 0;
    private static long U = 0;
    private static int V = -1;
    private static int W = 0;
    private static int X = 10;
    private static int Y = 60000;
    private static Handler ad = new Handler(Looper.getMainLooper()) { // from class: com.excelliance.lbsdk.main.q.1
        /* JADX WARN: Type inference failed for: r9v4, types: [com.excelliance.lbsdk.main.q$1$1] */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            long origSize;
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 != 2) {
                    return;
                }
                new Thread("LBBwbxDownloader:updateResourceBase1") { // from class: com.excelliance.lbsdk.main.q.1.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        while (!aa.b(q.d)) {
                            try {
                                Thread.sleep(1000L);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "try updateResourceBase again", new Object[0]);
                        q.M();
                    }
                }.start();
                return;
            }
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "show choose", new Object[0]);
            int i3 = message.arg1;
            long downloadedSize = new File(i3 == 2 ? GameUtil.getTwLoadPath(q.d) : GameUtil.getZipPath(q.d)).exists() ? BwbxUtil.getDownloadedSize(q.d) : 0L;
            Intent intent = new Intent("com.excelliance.open.action.fw");
            intent.setPackage(q.d.getPackageName());
            intent.setComponent(new ComponentName(q.d, BGService.class.getName()));
            intent.putExtra("dialogtype", 5);
            intent.putExtra("dlsZipType", i3);
            if (i3 == 1 && com.excelliance.lbsdk.main.p.d.b(q.d)) {
                origSize = com.excelliance.lbsdk.main.bwbx.g.a().c() - downloadedSize;
                downloadedSize = q.ab;
            } else {
                origSize = GameUtil.getIntance().getOrigSize(true, i3);
            }
            intent.putExtra(BwbxUtil.ACTION_SIZE, origSize - downloadedSize);
            if (message.arg2 == 1) {
                intent.putExtra("lbresLater", true);
            }
            if (message.obj != null) {
                intent.putExtras((Bundle) message.obj);
            }
            q.b(intent);
            BwbxUtil.dialogStatistics(q.d, BwbxUtil.DIALOG_TYPE_OLD_USER_FIRST);
        }
    };
    private static AtomicBoolean ae = new AtomicBoolean(false);
    private static final Runnable ag = new Runnable() { // from class: com.excelliance.lbsdk.main.q.8
        @Override // java.lang.Runnable
        public void run() {
            q.ad.removeCallbacks(q.ag);
            Map<String, String> mapF = q.Z.f();
            long j2 = 0;
            int i2 = 0;
            while (i2 < mapF.size()) {
                i2++;
                String strValueOf = mapF.get(String.valueOf(i2));
                if (strValueOf == null) {
                    strValueOf = String.valueOf(0);
                }
                j2 += (long) Integer.parseInt(strValueOf);
            }
            long j3 = j2 - q.S;
            long unused = q.S = j2;
            q.U += (j3 / 1024) / 10;
            q.Q[q.p] = q.U / ((long) q.I());
            int iD = aa.d(q.d);
            if (q.V == -1) {
                int unused2 = q.V = iD;
            } else if (q.V != iD && iD != -1) {
                int unused3 = q.V = iD;
                int unused4 = q.T = 0;
                long unused5 = q.U = 0L;
                q.Q[0] = 0;
                q.Q[1] = 0;
            }
            if (q.T < 6) {
                q.ad.postDelayed(q.ag, 10000L);
                return;
            }
            int i3 = q.p;
            if (q.Q[q.p] < 500) {
                i3 = (i3 + 1) % 2;
                if (q.Q[0] != 0 && q.Q[1] != 0) {
                    boolean unused6 = q.P = true;
                    boolean unused7 = q.R = false;
                    double d2 = q.Q[1];
                    double d3 = q.Q[0];
                    Double.isNaN(d3);
                    i3 = d2 < d3 * 1.3d ? 0 : 1;
                }
            } else {
                boolean unused8 = q.P = true;
            }
            if (i3 != q.p) {
                int unused9 = q.p = i3;
                q.c = true;
                ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
            }
        }
    };

    static /* synthetic */ int D() {
        int i2 = K;
        K = i2 + 1;
        return i2;
    }

    static /* synthetic */ int I() {
        int i2 = T + 1;
        T = i2;
        return i2;
    }

    private static void L() {
        InputStream bufferedInputStream;
        ZipFile zipFile;
        ZipEntry entry;
        String line;
        String strSubstring;
        String strSubstring2;
        if (!e.isEmpty() || d == null) {
            return;
        }
        try {
            an anVarA = an.a();
            if (anVarA.c()) {
                String strE = anVarA.e();
                bufferedInputStream = (strE == null || !new File(strE).exists() || (entry = (zipFile = new ZipFile(strE)).getEntry("assets/localData.png")) == null) ? null : new BufferedInputStream(zipFile.getInputStream(entry));
            } else {
                bufferedInputStream = e.a(d);
            }
            if (bufferedInputStream == null) {
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "localData not found", new Object[0]);
                return;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArrA = f.a();
            int length = bArrA.length;
            byte[] bArr = new byte[1024];
            bufferedInputStream.skip(261L);
            int i2 = 0;
            while (true) {
                int i3 = bufferedInputStream.read(bArr);
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
                        strSubstring = line;
                        strSubstring2 = null;
                    } else {
                        strSubstring = line.substring(iIndexOf + 1);
                        strSubstring2 = line.substring(0, iIndexOf);
                    }
                    e.put(strSubstring, strSubstring2);
                    f.add(strSubstring);
                }
            } while (line != null);
            bufferedReader.close();
            byteArrayOutputStream.close();
        } catch (Exception unused) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "no localData", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void M() {
        long jB = ae.b(d, "lebian_module_base", "last_resdomain_lbres", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - jB < 5000) {
            return;
        }
        ae.a(d, "lebian_module_base", "last_resdomain_lbres", jCurrentTimeMillis);
        String str = null;
        String str2 = null;
        String attributeValue = null;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i2 >= 3 || (str != null && str.length() > 0)) {
                break;
            }
            try {
                GameUtil intance = GameUtil.getIntance();
                StringBuilder sb = new StringBuilder(com.excelliance.lbsdk.main.ai.i.b(intance.getBaseUrl("f"), "/getresdomain.php"));
                sb.append("?chid=" + intance.getApkMainCh());
                sb.append("&vercode=" + T());
                sb.append("&cpmeta=" + intance.getClientChId());
                sb.append("&pkg=" + d.getPackageName());
                String string = sb.toString();
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "getResourceBase URL:" + string + ", retry=" + i3, new Object[0]);
                o oVarA = aa.a(string, d);
                int i4 = oVarA.a;
                if (i4 == 200) {
                    String str3 = oVarA.b;
                    if (str3 != null && str3.length() != 0) {
                        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                        xmlPullParserNewPullParser.setInput(new StringReader(str3));
                        for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                            if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equalsIgnoreCase("uinfo")) {
                                String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "domain");
                                try {
                                    String attributeValue3 = xmlPullParserNewPullParser.getAttributeValue(null, "domainbak");
                                    try {
                                        attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "domainaddbak");
                                        str2 = attributeValue3;
                                        str = attributeValue2;
                                    } catch (Exception e2) {
                                        str = attributeValue2;
                                        e = e2;
                                        str2 = attributeValue3;
                                        e.printStackTrace();
                                        i2 = i3;
                                    }
                                } catch (Exception e3) {
                                    str = attributeValue2;
                                    e = e3;
                                }
                            }
                        }
                    }
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "retCode=" + i4, new Object[0]);
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "domain=" + str + " domainBk=" + str2 + " domainBkSec = " + attributeValue, new Object[0]);
                if (str != null && str.length() > 0) {
                    String strN = N();
                    String remoteResPathZip = GameUtil.getRemoteResPathZip(d);
                    if (strN != null && strN.length() > 0) {
                        h = str + strN;
                        i = str2 + strN;
                        j = attributeValue + strN;
                        ae.a(d, "lebian_module_domain", "baseUrl_" + a, h);
                        ae.a(d, "lebian_module_domain", "baseUrlBk_" + a, i);
                        ae.a(d, "lebian_module_domain", "baseUrlBkSec_" + a, j);
                    }
                    if (remoteResPathZip != null && remoteResPathZip.length() > 0) {
                        k = str + remoteResPathZip;
                        l = str2 + remoteResPathZip;
                        m = attributeValue + remoteResPathZip;
                        ae.a(d, "lebian_module_domain", "baseUrlZip_" + a, k);
                        ae.a(d, "lebian_module_domain", "baseUrlBkZip_" + a, l);
                        ae.a(d, "lebian_module_domain", "baseUrlBkSecZip_" + a, m);
                    }
                }
            } catch (Exception e4) {
                e = e4;
            }
            i2 = i3;
        }
        if (str == null || str.length() <= 0) {
            ad.removeMessages(2);
            ad.sendMessageDelayed(ad.obtainMessage(2), Y);
        }
    }

    private static String N() {
        an anVarA = an.a();
        String line = "";
        if (!anVarA.c()) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(d.getAssets().open("tryflag"), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                line = bufferedReader.readLine();
                inputStreamReader.close();
                bufferedReader.close();
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "path=" + line, new Object[0]);
                return line;
            } catch (IOException unused) {
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "no tryflag", new Object[0]);
                return line;
            }
        }
        String strE = anVarA.e();
        if (strE == null || !new File(strE).exists()) {
            return "";
        }
        try {
            ZipFile zipFile = new ZipFile(strE);
            try {
                ZipEntry entry = zipFile.getEntry("assets/tryflag");
                if (entry != null) {
                    InputStreamReader inputStreamReader2 = new InputStreamReader(new BufferedInputStream(zipFile.getInputStream(entry)), "UTF-8");
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                    line = bufferedReader2.readLine();
                    inputStreamReader2.close();
                    bufferedReader2.close();
                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "path1=" + line, new Object[0]);
                }
                zipFile.close();
                return line;
            } catch (Throwable th) {
                try {
                    zipFile.close();
                } catch (Throwable unused2) {
                }
                throw th;
            }
        } catch (Exception unused3) {
            return line;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void O() {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "cancelAlarm", new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(d.getPackageName());
        intent.putExtra("alarmcheck", true);
        intent.putExtra("dlsType", C);
        ((AlarmManager) d.getSystemService("alarm")).cancel(PendingIntent.getService(d, 0, intent, b.a(0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle P() {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "queryResPatch enter", new Object[0]);
        Bundle bundle = new Bundle();
        String str = GameUtil.getIntance().getResourceCfg(d).get("lastTryFlag");
        String remoteResPathZip = GameUtil.getRemoteResPathZip(d);
        if (str != null && !str.equals(remoteResPathZip)) {
            com.excelliance.lbsdk.main.k.d<com.excelliance.lbsdk.main.z.c> dVarB = com.excelliance.lbsdk.main.k.e.b(d);
            if (dVarB.b()) {
                com.excelliance.lbsdk.main.z.c cVarC = dVarB.c();
                Bundle bundleD = cVarC != null ? cVarC.d() : null;
                if (bundleD != null) {
                    bundle.putAll(bundleD);
                    bundle.putBoolean("reqSucc", true);
                }
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean Q() {
        ab = ae.b(d, "lebian_module_dloadinfo", "bwbxOffset_" + a, 0L);
        if (ab == 0) {
            String cachePath = GameUtil.getCachePath(d, String.valueOf(a));
            if (new File(cachePath).exists()) {
                L = GameUtil.getFilesCount(new File(cachePath));
                M = GameUtil.getDownloadedSize(d, String.valueOf(a));
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "last count:" + L, new Object[0]);
            }
            com.excelliance.lbsdk.main.bwbx.g gVarA = com.excelliance.lbsdk.main.bwbx.g.a().a(L);
            ab = gVarA.a(d);
            ac = gVarA.b();
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "initDownloadedFileInfo  count:" + L + " ,offset:" + ab, new Object[0]);
        }
        return ab >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void R() {
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.q.9
            @Override // java.lang.Runnable
            public void run() {
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    if (i2 >= 99) {
                        return;
                    }
                    try {
                        if (q.ae.get()) {
                            return;
                        }
                        Intent intent = new Intent(q.d.getPackageName() + ".action.downloadcomponent.extracted");
                        intent.setPackage(q.d.getPackageName());
                        intent.putExtra(BwbxUtil.ACTION_PROGRESS, i3);
                        intent.putExtra("merge", true);
                        s.a(q.d, intent);
                        Thread.sleep(3000L);
                        i2 = i3;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        }, "startResPatchProgress").start();
    }

    private static com.excelliance.lbsdk.main.ab.a S() {
        com.excelliance.lbsdk.main.ab.a aVar = af;
        if (aVar != null) {
            return aVar;
        }
        com.excelliance.lbsdk.main.ab.a aVar2 = (com.excelliance.lbsdk.main.ab.a) LBSdkHelper.application().a("GlobalSettings");
        af = aVar2;
        return aVar2;
    }

    private static int T() {
        int i2 = a;
        if (i2 >= 1) {
            return i2;
        }
        int i3 = Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true));
        a = i3;
        return i3;
    }

    public static Resources a(Context context) {
        return PlatformResources.getMainResources(context);
    }

    public static String a(int i2) {
        String str;
        String str2 = h;
        String strB = null;
        if (str2 != null && !str2.contains(N())) {
            h = null;
            i = null;
            j = null;
            k = null;
            l = null;
            m = null;
            ae.b(d, "lebian_module_domain");
        }
        if (i2 == 1) {
            if (i == null) {
                i = ae.b(d, "lebian_module_domain", "baseUrlBk_" + a, (String) null);
            }
            str = i;
        } else if (i2 == 2) {
            if (j == null) {
                j = ae.b(d, "lebian_module_domain", "baseUrlBkSec_" + a, (String) null);
            }
            str = j;
        } else {
            if (h == null) {
                h = ae.b(d, "lebian_module_domain", "baseUrl_" + a, (String) null);
            }
            str = h;
        }
        if (str == null || "".equals(str.trim())) {
            String strC = S().c();
            if (strC != null) {
                strB = com.excelliance.lbsdk.main.ai.i.b(strC, GameUtil.getRemoteResPathZip(d));
            }
        } else {
            strB = str;
        }
        if (strB != null) {
            return strB;
        }
        n = true;
        StringBuffer stringBuffer = new StringBuffer(GameUtil.getBkDomain(d, i2));
        stringBuffer.append(GameUtil.getRemoteResPathZip(d));
        h = stringBuffer.toString();
        String str3 = h;
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "fallback to default baseUrl=" + h, new Object[0]);
        return str3;
    }

    public static String a(Context context, int i2) {
        if (d == null && context != null) {
            d = context;
        }
        return a(i2);
    }

    public static String a(Context context, int i2, int i3) {
        if (d == null) {
            d = context;
        }
        return b(i2, i3);
    }

    private static void a(int i2, boolean z2) {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "startAlarm", new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(d.getPackageName());
        intent.putExtra("alarmcheck", true);
        intent.putExtra("dlsType", C);
        intent.putExtra("dlsZipType", i2);
        intent.putExtra("downloadingCheck", z2);
        PendingIntent service = PendingIntent.getService(d, 0, intent, b.a(268435456));
        AlarmManager alarmManager = (AlarmManager) d.getSystemService("alarm");
        alarmManager.cancel(service);
        alarmManager.setRepeating(0, System.currentTimeMillis() + 20000, 20000L, service);
    }

    public static void a(long j2) {
        int i2;
        if (n || !GameUtil.getIntance().getNotifySwitch(8) || P || (i2 = p) == 2 || Q[i2] != 0) {
            return;
        }
        T = 0;
        U = 0L;
        S = j2;
        ad.removeCallbacks(ag);
        ad.postDelayed(ag, 10000L);
    }

    public static void a(long j2, long j3) {
        double d2 = j2;
        double d3 = j3;
        Double.isNaN(d2);
        Double.isNaN(d3);
        int i2 = (int) ((d2 / d3) * 100.0d);
        if (i2 != N) {
            if (i2 > 0) {
                Intent intent = new Intent(d.getPackageName() + ".action.downloadcomponent.extracted");
                intent.setPackage(d.getPackageName());
                intent.putExtra(BwbxUtil.ACTION_PROGRESS, i2);
                s.a(d, intent);
            }
            N = i2;
        }
    }

    public static void a(Bundle bundle) {
        Intent intent = new Intent(d, (Class<?>) BGService.class);
        intent.putExtra("startForground", true);
        if (al.b(d) && bundle != null) {
            intent.putExtra("notificationMsg", bundle);
        }
        b(intent);
    }

    /* JADX WARN: Type inference failed for: r0v23, types: [com.excelliance.lbsdk.main.q$4] */
    /* JADX WARN: Type inference failed for: r0v24, types: [com.excelliance.lbsdk.main.q$3] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.excelliance.lbsdk.main.q$5] */
    /* JADX WARN: Type inference failed for: r3v16, types: [com.excelliance.lbsdk.main.q$6] */
    public static void a(c cVar, Context context, Intent intent) {
        String twLoadPath;
        long length;
        long origSize;
        long origSize2;
        z = cVar;
        d = context;
        if (GameUtil.getIntance().getContext() == null) {
            GameUtil.getIntance().setContext(context);
        }
        Z = com.excelliance.lbsdk.main.p.e.b(context);
        aa = com.excelliance.lbsdk.main.p.a.a(context);
        a = Integer.parseInt(GameUtil.getIntance().getRunningVersionCode(false, true));
        C = intent.getIntExtra("dlsType", 1);
        A = intent.getBooleanExtra("cancel", false);
        boolean booleanExtra = intent.getBooleanExtra("zipCancel", false);
        b = booleanExtra;
        D = intent.getIntExtra("dlsZipType", 0);
        B = BwbxUtil.isBigAsset(d) && GameUtil.getIntance().isTwload(d);
        com.excelliance.lbsdk.main.bwbx.g gVarA = com.excelliance.lbsdk.main.bwbx.g.a();
        Context context2 = d;
        gVarA.a(context2, BwbxUtil.isBigAsset(context2), a);
        boolean booleanExtra2 = intent.getBooleanExtra("netChange", false);
        boolean booleanExtra3 = intent.getBooleanExtra("downloadingCheck", false);
        boolean booleanExtra4 = intent.getBooleanExtra("downloadAfterQuit", false);
        boolean booleanExtra5 = intent.getBooleanExtra("alarmcheck", false);
        boolean booleanExtra6 = intent.getBooleanExtra("resPatchCheck", false);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int i2 = intent.getExtras().getInt("count", -1);
            if (i2 > 0) {
                L = i2;
            }
            long j2 = intent.getExtras().getLong("offset", -1L);
            if (j2 > 0) {
                ab = j2;
            }
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "initial mLbresZipEntryOffset :" + ab, new Object[0]);
        }
        boolean z2 = new File(GameUtil.getZipPath(d)).exists() || new File(GameUtil.getTwLoadPath(d)).exists();
        boolean zExists = new File(GameUtil.getResPatchPath(d, a, true)).exists();
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "bwbx init type:" + C + " zipType:" + D + " mDownloading:" + F + " mExtracting:" + G + " mCancel:" + A + " mZipCancel:" + b + " downloadingCheck:" + booleanExtra3 + " netChange:" + booleanExtra2 + " prefetching:" + g + " quitDownload:" + booleanExtra4 + " alarmCheck:" + booleanExtra5 + " zipExists:" + z2 + " patchZipExists:" + zExists + " sTargetVerCode:" + a + " resPatchCheck:" + booleanExtra6 + "isBigAssetTwiceLoad:" + B + "mBigAssetMerging:" + H, new Object[0]);
        String strB = ae.b(d, "lebian_module_dloadinfo", "bwbxZipUrl", (String) null);
        if (strB != null && strB.length() > 0 && !strB.contains(GameUtil.getRemoteResPathZip(d))) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "rm last version zip", new Object[0]);
            b = true;
            Z.a();
            G = false;
            O();
            ae.a(d, "lebian_module_dloadinfo", "bwbxZipUrl");
            if (new File(GameUtil.getZipPath(d)).exists()) {
                new File(GameUtil.getZipPath(d)).delete();
            }
            if (new File(GameUtil.getTwLoadPath(d)).exists()) {
                new File(GameUtil.getTwLoadPath(d)).delete();
            }
            String zipPath = GameUtil.getZipPath(d, false);
            if (new File(zipPath).exists()) {
                new File(zipPath).delete();
            }
            String twLoadPath2 = GameUtil.getTwLoadPath(d, false);
            if (new File(twLoadPath2).exists()) {
                new File(twLoadPath2).delete();
            }
        }
        if (booleanExtra6) {
            new Thread("LBBwbxDownloader:resPatchCheck") { // from class: com.excelliance.lbsdk.main.q.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws Throwable {
                    q.z.a(this);
                    long jB = ae.b(q.d, "lebian_module_base", "last_chklbres", 0L);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jB < 5000) {
                        return;
                    }
                    if (!al.c(q.d)) {
                        String resPatchPath = GameUtil.getResPatchPath(q.d, q.a, true);
                        if (!q.F || !new File(resPatchPath).exists() || !al.b(q.d)) {
                            ae.a(q.d, "lebian_module_base", "last_chklbres", jCurrentTimeMillis);
                            q.b(q.d, q.P(), true);
                        } else if (q.I || q.J) {
                            GameUtil.switchToDownloadFullRes(q.d, 3);
                        } else {
                            Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
                            intent2.setPackage(q.d.getPackageName());
                            intent2.setComponent(new ComponentName(q.d, BGService.class.getName()));
                            intent2.putExtra("dlsType", 2);
                            intent2.putExtra("zipCancel", true);
                            q.b(intent2);
                            ae.a(q.d, "lebian_module_base", "last_chklbres", jCurrentTimeMillis);
                            q.b(q.d, q.P(), true);
                        }
                    }
                    q.z.b(this);
                }
            }.start();
            return;
        }
        if (booleanExtra3) {
            new Thread("LBBwbxDownloader:downloadingCheck") { // from class: com.excelliance.lbsdk.main.q.4
                /* JADX WARN: Code duplicated, block: B:25:0x007e  */
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws Throwable {
                    int i3;
                    if (h.a()) {
                        return;
                    }
                    Bundle bundle = null;
                    l lVarA = an.a().a(ae.b(q.d, "lebian_module_extractinfo", "next", (String) null));
                    if (lVarA == null || lVarA.preDownload || !new File(lVarA.savePath).exists() || lVarA.isInstall) {
                        q.z.a(this);
                        int iRequireResZip = GameUtil.getIntance().requireResZip(q.d);
                        int i4 = 2;
                        if (iRequireResZip == 2 && new File(GameUtil.getZipPath(q.d)).exists()) {
                            iRequireResZip = 1;
                        }
                        if (iRequireResZip == 1) {
                            boolean zA = m.a(q.d, "CHOOSE_BY_USER_BWBX_LATER", false);
                            boolean zB = ae.b(q.d, "lebian_module_gameinfo", "hint", false);
                            if (!zA || zB) {
                                i3 = 0;
                            } else {
                                i3 = 1;
                            }
                        } else {
                            i3 = 0;
                        }
                        String twLoadPath3 = iRequireResZip == 2 ? GameUtil.getTwLoadPath(q.d) : GameUtil.getZipPath(q.d);
                        String strSubstring = twLoadPath3.substring(0, twLoadPath3.lastIndexOf("."));
                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "zipType:" + iRequireResZip, new Object[0]);
                        if ((iRequireResZip != -1 || q.F || new File(twLoadPath3).exists()) && al.b(q.d) && !new File(strSubstring).exists() && !q.G && BwbxUtil.checkDlFullRes(q.d, true, false, new File(twLoadPath3).exists(), iRequireResZip)) {
                            if (iRequireResZip <= 0) {
                                iRequireResZip = 1;
                            }
                            if (iRequireResZip == 1 && com.excelliance.lbsdk.main.p.d.b(q.d)) {
                                if (!q.Q()) {
                                    return;
                                }
                                bundle = new Bundle();
                                bundle.putLong("offset", q.ab);
                                bundle.putInt("count", com.excelliance.lbsdk.main.bwbx.g.a().b());
                            }
                            if (!al.c(q.d)) {
                                h.e();
                                boolean zIsNeedSoOut = BwbxUtil.isNeedSoOut(q.d);
                                boolean zA2 = m.a(q.d, "SHOW_DIALOG_WITH_WIFI_WHEN_TWLOAD_BWBX", false);
                                boolean zA3 = m.a(q.d, "SHOW_DIALOG_WITHOUT_WIFI_WHEN_TWLOAD_BWBX", false);
                                if ((aa.a(q.d) || q.F || zIsNeedSoOut || !zA3) && !zA2) {
                                    if (i3 != 0) {
                                        GameUtil.switchToDlFullresBg(q.d, iRequireResZip, bundle);
                                    } else {
                                        GameUtil.switchToDownloadFullRes(q.d, iRequireResZip, bundle);
                                    }
                                } else if (i3 == 0 || m.a(q.d, "SHOW_DIALOG_WITHOUT_WIFI_WHEN_LBRES_LATER", false)) {
                                    q.ad.removeMessages(1);
                                    Message messageObtainMessage = q.ad.obtainMessage(1);
                                    messageObtainMessage.arg1 = iRequireResZip;
                                    messageObtainMessage.arg2 = i3;
                                    if (bundle != null) {
                                        messageObtainMessage.obj = bundle;
                                    }
                                    q.ad.sendMessageDelayed(messageObtainMessage, 1000L);
                                }
                            }
                        } else if (al.b(q.d)) {
                            boolean zB2 = ae.b(q.d, "lebian_module_dloadinfo", "bwbxZipExtracting", false);
                            ae.b(q.d, "lebian_module_dloadinfo", "bwbxBigAssetMerging", false);
                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "checkCacheMd5 new extracting:" + zB2, new Object[0]);
                            String zipPath2 = GameUtil.getZipPath(q.d, false);
                            if (new File(zipPath2).exists()) {
                                i4 = 1;
                            } else {
                                zipPath2 = GameUtil.getTwLoadPath(q.d, false);
                            }
                            if (!new File(zipPath2).exists()) {
                                zipPath2 = GameUtil.getResPatchPath(q.d, q.a, false);
                                i4 = 3;
                            }
                            if (zB2 && new File(zipPath2).exists()) {
                                if (!al.c(q.d)) {
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putBoolean("extracting", true);
                                    GameUtil.switchToDownloadFullRes(q.d, iRequireResZip, bundle2);
                                }
                                if (!q.F && !q.G) {
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    q.n(99);
                                    q.d(zipPath2, i4);
                                    q.q(i4);
                                    q.r(i4);
                                    q.n(100);
                                }
                            }
                        }
                        q.z.b(this);
                    }
                }
            }.start();
            return;
        }
        if (A || booleanExtra) {
            if (booleanExtra) {
                Z.a();
                O();
                return;
            }
            return;
        }
        if (h.d()) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "version upgrade", new Object[0]);
            return;
        }
        if (F || G) {
            if (I) {
                n(99);
                return;
            }
            return;
        }
        if (booleanExtra5 && !aa.a(d)) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "is not wifi", new Object[0]);
            return;
        }
        if ((booleanExtra2 || booleanExtra4 || booleanExtra5) && aa.a(d)) {
            if (D == 3 || zExists) {
                if (ae.b(d, "lebian_module_bwbxinfo", "dlresPatch", false)) {
                    new Thread("LBBwbxDownloader:handleResPatch") { // from class: com.excelliance.lbsdk.main.q.5
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() throws Throwable {
                            q.z.a(this);
                            q.b(q.d, q.P(), false);
                            q.z.b(this);
                        }
                    }.start();
                    return;
                }
                return;
            } else if (z2) {
                long downloadedSize = GameUtil.getDownloadedSize(d, String.valueOf(a));
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "downloaded:" + downloadedSize, new Object[0]);
                if (new File(GameUtil.getZipPath(d)).exists()) {
                    D = 1;
                }
                if (new File(GameUtil.getTwLoadPath(d)).exists()) {
                    D = 2;
                }
                if (downloadedSize < (GameUtil.getIntance().getOrigSize(false, D) >> 1)) {
                    C = 2;
                    if (g) {
                        A = true;
                    }
                }
            }
        }
        b((Context) null);
        new Thread("LBBwbxDownloader:getNotifySwitch") { // from class: com.excelliance.lbsdk.main.q.6
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                GameUtil.getIntance().getNotifySwitch(1);
            }
        }.start();
        int i3 = C;
        if (i3 == 1) {
            an anVarA = an.a();
            SystemClock.sleep(1000L);
            if (z2 || al.b(d) || anVarA.c("20000") || !GameUtil.isBwbx(d) || !anVarA.i() || ae.b(d, "lebian_module_dloadinfo", "success", false)) {
                return;
            }
            L();
            e.isEmpty();
            return;
        }
        if (i3 == 2) {
            if (extras != null && extras.getBoolean("extracting", false)) {
                n(99);
                return;
            }
            if (extras != null && extras.getBoolean("bigAssetMerging", false)) {
                n(99);
                return;
            }
            if (D == 3) {
                new File(GameUtil.getZipPath(d)).delete();
                new File(GameUtil.getTwLoadPath(d)).delete();
                if (extras != null) {
                    q = extras.getString("rDmd5");
                    r = extras.getString("rUrl");
                    ae.a(d, "lebian_module_dloadinfo", "resPatchDmd5", q);
                }
                twLoadPath = GameUtil.getResPatchPath(d, a, true);
                if (new File(twLoadPath.substring(0, twLoadPath.lastIndexOf(".dload"))).exists()) {
                    return;
                }
                length = new File(twLoadPath).exists() ? new File(twLoadPath).length() : 0L;
                origSize = com.excelliance.lbsdk.main.z.c.b(extras);
                double d2 = origSize;
                Double.isNaN(d2);
                origSize2 = ((long) (d2 * 1.2d)) + origSize;
            } else {
                new File(GameUtil.getResPatchPath(d, a, true)).delete();
                twLoadPath = D == 2 ? GameUtil.getTwLoadPath(d) : GameUtil.getZipPath(d);
                if (new File(twLoadPath.substring(0, twLoadPath.lastIndexOf(".dload"))).exists()) {
                    return;
                }
                length = new File(twLoadPath).exists() ? new File(twLoadPath).length() : 0L;
                origSize = GameUtil.getIntance().getOrigSize(true, D);
                origSize2 = GameUtil.getIntance().getOrigSize(true, D) + GameUtil.getIntance().getOrigSize(false, D);
            }
            long j3 = origSize2 - length;
            long j4 = length;
            String resRoot = GameUtil.getResRoot(context);
            long freeSpace = GameUtil.getFreeSpace(resRoot);
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "size:" + origSize + " required size:" + j3 + " downloaded size:" + j4 + " resRoot:" + resRoot + " free:" + freeSpace, new Object[0]);
            if (freeSpace < j3) {
                f(a(d).getString(a(d).getIdentifier("lebian_space_not_enough_error", "string", d.getPackageName())));
            } else {
                if (h.a()) {
                    return;
                }
                a(twLoadPath, D, extras, origSize);
            }
        }
    }

    private static void a(File file, String str, String str2, long j2) throws IOException {
        long j3 = j2;
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "merge file end = " + j3, new Object[0]);
        com.excelliance.lbsdk.main.s.al alVar = new com.excelliance.lbsdk.main.s.al(str);
        File file2 = new File(str2 + ".merge");
        FileInputStream fileInputStream = new FileInputStream(file);
        com.excelliance.lbsdk.main.s.af afVarA = alVar.a("assets/lbres/lbres.rdata.mp4");
        InputStream inputStreamA = alVar.a(afVarA);
        long compressedSize = afVarA.getCompressedSize() + j3;
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1048576];
        long j4 = 0;
        long j5 = 0;
        while (true) {
            int i2 = fileInputStream.read(bArr);
            if (i2 == -1 || j3 <= j4) {
                break;
            }
            if (i2 > j3) {
                i2 = (int) j3;
            }
            fileOutputStream.write(bArr, 0, i2);
            long j6 = i2;
            j5 += j6;
            j3 -= j6;
            a(j5, compressedSize);
            j4 = 0;
        }
        fileInputStream.close();
        while (true) {
            int i3 = inputStreamA.read(bArr);
            if (i3 == -1) {
                break;
            }
            fileOutputStream.write(bArr, 0, i3);
            j5 += (long) i3;
            a(j5, compressedSize);
        }
        inputStreamA.close();
        fileOutputStream.close();
        if (file.delete()) {
            file2.renameTo(new File(str2));
        }
    }

    private static void a(final String str, final int i2, final Bundle bundle, final long j2) throws Throwable {
        o(i2);
        if (aa.b(d)) {
            if (aa.a(d)) {
                E = 1;
            } else if (!aa.a(d)) {
                E = 2;
            }
        }
        final boolean z2 = E == 1;
        b();
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.q.7
            /* JADX WARN: Code duplicated, block: B:100:0x034b A[LOOP:0: B:26:0x00a5->B:100:0x034b, LOOP_END] */
            /* JADX WARN: Code duplicated, block: B:104:0x035d A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:105:0x035f A[Catch: all -> 0x0033, Exception -> 0x09da, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:107:0x036c A[Catch: all -> 0x09d5, Exception -> 0x09da, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x09da, blocks: (B:3:0x0011, B:5:0x002b, B:11:0x0039, B:14:0x0064, B:16:0x0068, B:20:0x008f, B:22:0x0093, B:105:0x035f, B:106:0x036b, B:107:0x036c, B:111:0x0373, B:117:0x03a0, B:119:0x03bd, B:121:0x03c3, B:122:0x0425, B:172:0x0588, B:175:0x05aa, B:177:0x05dd, B:178:0x05e0, B:125:0x0442, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:158:0x04eb, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:137:0x048c, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:19:0x008d), top: B:306:0x0011 }] */
            /* JADX WARN: Code duplicated, block: B:109:0x0370  */
            /* JADX WARN: Code duplicated, block: B:116:0x039f  */
            /* JADX WARN: Code duplicated, block: B:119:0x03bd A[Catch: all -> 0x0033, Exception -> 0x09da, TRY_ENTER, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:124:0x0440 A[ADDED_TO_REGION] */
            /* JADX WARN: Code duplicated, block: B:125:0x0442 A[Catch: all -> 0x09d5, Exception -> 0x09da, TRY_LEAVE, TryCatch #9 {Exception -> 0x09da, blocks: (B:3:0x0011, B:5:0x002b, B:11:0x0039, B:14:0x0064, B:16:0x0068, B:20:0x008f, B:22:0x0093, B:105:0x035f, B:106:0x036b, B:107:0x036c, B:111:0x0373, B:117:0x03a0, B:119:0x03bd, B:121:0x03c3, B:122:0x0425, B:172:0x0588, B:175:0x05aa, B:177:0x05dd, B:178:0x05e0, B:125:0x0442, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:158:0x04eb, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:137:0x048c, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:19:0x008d), top: B:306:0x0011 }] */
            /* JADX WARN: Code duplicated, block: B:127:0x0448 A[Catch: all -> 0x0033, Exception -> 0x09da, TRY_ENTER, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:139:0x0494 A[Catch: all -> 0x0033, Exception -> 0x09da, TRY_ENTER, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:160:0x0504 A[Catch: all -> 0x0033, Exception -> 0x09da, TRY_ENTER, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:162:0x0520 A[Catch: all -> 0x0033, Exception -> 0x09da, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:165:0x0536 A[Catch: all -> 0x0033, Exception -> 0x09da, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:167:0x0542 A[Catch: all -> 0x0033, Exception -> 0x09da, TRY_LEAVE, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:175:0x05aa A[Catch: all -> 0x09d5, Exception -> 0x09da, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x09da, blocks: (B:3:0x0011, B:5:0x002b, B:11:0x0039, B:14:0x0064, B:16:0x0068, B:20:0x008f, B:22:0x0093, B:105:0x035f, B:106:0x036b, B:107:0x036c, B:111:0x0373, B:117:0x03a0, B:119:0x03bd, B:121:0x03c3, B:122:0x0425, B:172:0x0588, B:175:0x05aa, B:177:0x05dd, B:178:0x05e0, B:125:0x0442, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:158:0x04eb, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:137:0x048c, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:19:0x008d), top: B:306:0x0011 }] */
            /* JADX WARN: Code duplicated, block: B:177:0x05dd A[Catch: all -> 0x0033, Exception -> 0x09da, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:185:0x0680 A[Catch: all -> 0x0733, Exception -> 0x073c, TRY_LEAVE, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:193:0x069e  */
            /* JADX WARN: Code duplicated, block: B:199:0x06fe A[Catch: all -> 0x0733, Exception -> 0x073c, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:200:0x0703 A[PHI: r22
  0x0703: PHI (r22v2 boolean) = (r22v1 boolean), (r22v4 boolean) binds: [B:190:0x0693, B:198:0x06fc] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:203:0x070d A[Catch: all -> 0x0733, Exception -> 0x073c, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:205:0x0715  */
            /* JADX WARN: Code duplicated, block: B:216:0x0744  */
            /* JADX WARN: Code duplicated, block: B:218:0x074c A[Catch: all -> 0x0733, Exception -> 0x073c, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:220:0x0760 A[Catch: all -> 0x0733, Exception -> 0x073c, TRY_LEAVE, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:227:0x0783 A[Catch: all -> 0x0733, Exception -> 0x073c, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:229:0x0797 A[Catch: all -> 0x0733, Exception -> 0x073c, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:231:0x07b7 A[Catch: all -> 0x0733, Exception -> 0x073c, TRY_LEAVE, TryCatch #6 {all -> 0x0733, blocks: (B:182:0x0669, B:183:0x067a, B:185:0x0680, B:201:0x0705, B:203:0x070d, B:206:0x0717, B:194:0x06a0, B:197:0x06ea, B:199:0x06fe, B:218:0x074c, B:220:0x0760, B:223:0x077b, B:227:0x0783, B:229:0x0797, B:231:0x07b7), top: B:301:0x0669 }] */
            /* JADX WARN: Code duplicated, block: B:234:0x07cc A[Catch: all -> 0x082b, Exception -> 0x082f, TRY_LEAVE, TryCatch #27 {Exception -> 0x082f, all -> 0x082b, blocks: (B:232:0x07c7, B:234:0x07cc), top: B:335:0x07c7 }] */
            /* JADX WARN: Code duplicated, block: B:243:0x0825  */
            /* JADX WARN: Code duplicated, block: B:254:0x083f  */
            /* JADX WARN: Code duplicated, block: B:258:0x0874 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:259:0x0893 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:261:0x0898 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:262:0x08d3 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:264:0x08d8 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:266:0x08eb A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:270:0x0907 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:274:0x0979 A[Catch: Exception -> 0x09d3, all -> 0x0a0d, TryCatch #17 {Exception -> 0x09d3, blocks: (B:256:0x0864, B:258:0x0874, B:272:0x0962, B:274:0x0979, B:275:0x0980, B:259:0x0893, B:261:0x0898, B:262:0x08d3, B:264:0x08d8, B:266:0x08eb, B:269:0x08f9, B:271:0x0946, B:270:0x0907, B:255:0x0846, B:277:0x09d2), top: B:319:0x043e }] */
            /* JADX WARN: Code duplicated, block: B:297:0x0150 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:299:0x04ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:301:0x0669 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:307:0x0194 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:327:0x068a A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:341:0x0345 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:342:0x06ea A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:343:0x072e A[EDGE_INSN: B:343:0x072e->B:210:0x072e BREAK  A[LOOP:1: B:183:0x067a->B:348:0x067a], SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:344:0x0693 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:345:0x06e2 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:346:0x0726 A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:347:0x071f A[SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:354:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:36:0x0103 A[Catch: all -> 0x0033, Exception -> 0x02d2, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:45:0x0174 A[Catch: all -> 0x0033, Exception -> 0x02d0, TRY_ENTER, TRY_LEAVE, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:50:0x019e A[Catch: all -> 0x0033, Exception -> 0x02a3, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:68:0x02a5  */
            /* JADX WARN: Code duplicated, block: B:86:0x02e9 A[Catch: all -> 0x0033, Exception -> 0x09da, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:88:0x02f9 A[Catch: all -> 0x0033, Exception -> 0x09da, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Code duplicated, block: B:90:0x02fd A[Catch: all -> 0x0033, Exception -> 0x09da, TryCatch #8 {all -> 0x0033, blocks: (B:5:0x002b, B:14:0x0064, B:16:0x0068, B:22:0x0093, B:28:0x00ae, B:30:0x00e7, B:105:0x035f, B:106:0x036b, B:119:0x03bd, B:121:0x03c3, B:177:0x05dd, B:127:0x0448, B:129:0x044d, B:131:0x0459, B:160:0x0504, B:162:0x0520, B:163:0x052f, B:165:0x0536, B:167:0x0542, B:135:0x0467, B:139:0x0494, B:141:0x049a, B:143:0x04aa, B:146:0x04d1, B:150:0x04d7, B:36:0x0103, B:38:0x012e, B:41:0x0150, B:45:0x0174, B:48:0x0194, B:50:0x019e, B:52:0x01a8, B:54:0x0209, B:55:0x0211, B:60:0x0295, B:62:0x029c, B:84:0x02da, B:86:0x02e9, B:87:0x02f3, B:96:0x0340, B:88:0x02f9, B:90:0x02fd, B:92:0x0308, B:93:0x0313, B:95:0x032e, B:59:0x0279, B:69:0x02a7, B:71:0x02b8), top: B:305:0x002b }] */
            /* JADX WARN: Instruction removed from duplicated block: B:220:0x0760, please report this as an issue */
            /* JADX WARN: Instruction removed from duplicated block: B:229:0x0797, please report this as an issue */
            /* JADX WARN: Instruction removed from duplicated block: B:261:0x0898, please report this as an issue */
            /* JADX WARN: Instruction removed from duplicated block: B:270:0x0907, please report this as an issue */
            /* JADX WARN: Instruction removed from duplicated block: B:45:0x0174, please report this as an issue */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v6, types: [com.excelliance.lbsdk.main.c] */
            /* JADX WARN: Type inference failed for: r1v70, types: [com.excelliance.lbsdk.main.c] */
            /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v1 */
            /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v3 */
            /* JADX WARN: Type inference failed for: r2v31 */
            /* JADX WARN: Type inference failed for: r2v32 */
            /* JADX WARN: Type inference failed for: r2v33 */
            /* JADX WARN: Type inference failed for: r2v34 */
            /* JADX WARN: Type inference failed for: r2v36 */
            /* JADX WARN: Type inference failed for: r2v37 */
            /* JADX WARN: Type inference failed for: r2v38 */
            /* JADX WARN: Type inference failed for: r2v39 */
            /* JADX WARN: Type inference failed for: r2v4 */
            /* JADX WARN: Type inference failed for: r2v41, types: [com.excelliance.lbsdk.main.q$7, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v5 */
            /* JADX WARN: Type inference failed for: r2v53 */
            /* JADX WARN: Type inference failed for: r2v54 */
            /* JADX WARN: Type inference failed for: r2v55 */
            /* JADX WARN: Type inference failed for: r2v56 */
            /* JADX WARN: Type inference failed for: r2v57 */
            /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v7 */
            /* JADX WARN: Type inference failed for: r3v1 */
            /* JADX WARN: Type inference failed for: r3v2, types: [boolean, int] */
            /* JADX WARN: Type inference failed for: r3v4, types: [com.excelliance.lbsdk.main.c] */
            /* JADX WARN: Type inference failed for: r3v88 */
            /* JADX WARN: Type inference failed for: r3v89 */
            @Override // java.lang.Runnable
            public void run() throws Exception {
                Throwable th;
                int i3;
                ?? r2;
                Exception exc;
                ?? r3;
                boolean z3;
                ?? r4;
                AnonymousClass7 anonymousClass7;
                boolean z4;
                AnonymousClass7 anonymousClass8;
                String strB;
                int i4;
                GameUtil gameUtil;
                Exception exc2;
                String str2;
                long j3;
                String packageName;
                String strL;
                boolean z5;
                GameUtil gameUtil2;
                boolean z6;
                AnonymousClass7 anonymousClass9;
                String cachePath;
                boolean zB;
                com.excelliance.lbsdk.main.ai.j jVar;
                ZipEntry zipEntryA;
                String cachePath2;
                String cachePath3;
                boolean z7;
                BufferedReader bufferedReader;
                boolean z8;
                boolean z9;
                String line;
                boolean z10;
                boolean z11;
                boolean z12;
                String strSubstring;
                File file;
                boolean z13;
                File file2;
                File file3;
                File file4;
                File file5;
                int i5;
                int i6;
                long j4;
                this = this;
                ?? r5 = "usePathBk";
                String str3 = "finally";
                GameUtil intance = GameUtil.getIntance();
                try {
                    try {
                        try {
                            q.z.a(this);
                            boolean unused = q.F = true;
                            boolean unused2 = q.J = false;
                            if (!ae.b(q.d, "lebian_module_dloadinfo", "downloadStartStatistics", false)) {
                                try {
                                    GameUtil.getIntance().bwbxDownloadStatistics(1);
                                } catch (Throwable th2) {
                                    th = th2;
                                    anonymousClass7 = this;
                                    z4 = false;
                                    anonymousClass8 = anonymousClass7;
                                    th = th;
                                    r2 = anonymousClass8;
                                    i3 = z4;
                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                    boolean unused3 = q.F = i3;
                                    boolean unused4 = q.I = i3;
                                    boolean unused5 = q.J = i3;
                                    q.ad.removeCallbacks(q.ag);
                                    q.z.b(r2);
                                    throw th;
                                }
                            }
                            int unused6 = q.p = ae.b(q.d, "lebian_module_domain", "usePathBk", 0);
                            Thread.sleep(3000L);
                            String strB2 = q.b(q.p, i2);
                            long origSize = GameUtil.getIntance().getOrigSize(true, i2);
                            if (origSize == j2 || i2 == 3) {
                                origSize = j2;
                            } else {
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "run: size changed size=" + j2 + " newSize" + origSize, new Object[0]);
                            }
                            if (i2 != 3) {
                                strB = strB2;
                                ae.a(q.d, "lebian_module_dloadinfo", "bwbxZipUrl", strB);
                            } else {
                                strB = strB2;
                            }
                            long j5 = origSize;
                            int i7 = 0;
                            Exception exc3 = null;
                            while (true) {
                                i4 = i7 + 1;
                                if (i7 >= 5) {
                                    gameUtil = intance;
                                    exc2 = exc3;
                                    str2 = "splitZip";
                                    j3 = j5;
                                    break;
                                }
                                try {
                                    com.excelliance.lbsdk.main.g.b.e("LBBwbxDownloader", "try download  (" + i4 + ")   reqUrl=" + strB + " dlUrl = " + q.p + " dlsZipType=" + i2, new Object[0]);
                                    if (z2) {
                                        try {
                                            if (aa.a(q.d)) {
                                                q.ae.set(false);
                                                Bundle bundle2 = new Bundle();
                                                exc2 = exc3;
                                                gameUtil = intance;
                                                try {
                                                    bundle2.putString("title", q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_dl_status_downloading", "string", q.d.getPackageName())));
                                                    bundle2.putInt("show_level", 1);
                                                    q.a(bundle2);
                                                    if (q.ab <= 0) {
                                                        try {
                                                            long unused7 = q.ab = ae.b(q.d, "lebian_module_dloadinfo", "bwbxOffset_" + q.a, 0L);
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "start offset : " + q.ab, new Object[0]);
                                                            if (i2 != 1) {
                                                                try {
                                                                    if (com.excelliance.lbsdk.main.p.d.b(q.d) || q.ab <= 0) {
                                                                        str2 = "splitZip";
                                                                        try {
                                                                            q.Z.d(i2);
                                                                            j4 = j5;
                                                                            try {
                                                                                q.Z.a(strB, str, j4);
                                                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                                j5 = j4;
                                                                            } catch (Exception e2) {
                                                                                exc3 = e2;
                                                                                j5 = j4;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                                                q.b(exc3);
                                                                                if (q.c) {
                                                                                    i4--;
                                                                                    q.c = false;
                                                                                    i5 = q.p;
                                                                                    i6 = i2;
                                                                                } else {
                                                                                    if (i4 % 2 == 0) {
                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                        do {
                                                                                            int unused8 = q.p = q.v() % 3;
                                                                                        } while (q.o.contains(Integer.valueOf(q.p)));
                                                                                        ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                                                        i5 = q.p;
                                                                                        i6 = i2;
                                                                                    }
                                                                                    i7 = i4;
                                                                                    if (q.b) {
                                                                                        i4 = i7;
                                                                                        exc2 = exc3;
                                                                                    } else {
                                                                                        intance = gameUtil;
                                                                                    }
                                                                                }
                                                                                strB = q.b(i5, i6);
                                                                                i7 = i4;
                                                                                if (q.b) {
                                                                                    i4 = i7;
                                                                                    exc2 = exc3;
                                                                                } else {
                                                                                    intance = gameUtil;
                                                                                }
                                                                            }
                                                                        } catch (Exception e3) {
                                                                            e = e3;
                                                                            exc3 = e;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                                            q.b(exc3);
                                                                            if (q.c) {
                                                                                i4--;
                                                                                q.c = false;
                                                                                i5 = q.p;
                                                                                i6 = i2;
                                                                            } else {
                                                                                if (i4 % 2 == 0) {
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    do {
                                                                                        int unused9 = q.p = q.v() % 3;
                                                                                    } while (q.o.contains(Integer.valueOf(q.p)));
                                                                                    ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                                                    i5 = q.p;
                                                                                    i6 = i2;
                                                                                }
                                                                                i7 = i4;
                                                                                if (q.b) {
                                                                                    i4 = i7;
                                                                                    exc2 = exc3;
                                                                                    j3 = j5;
                                                                                    if (i4 > 5) {
                                                                                        if (exc2 == null) {
                                                                                            throw exc2;
                                                                                        }
                                                                                        Exception exc4 = new Exception();
                                                                                        q.b(q.E);
                                                                                        throw exc4;
                                                                                    }
                                                                                    if (q.b) {
                                                                                        z4 = false;
                                                                                        try {
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                                            q.b = false;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                            boolean unused10 = q.F = false;
                                                                                            boolean unused11 = q.I = false;
                                                                                            boolean unused12 = q.J = false;
                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                            q.z.b(this);
                                                                                            return;
                                                                                        } catch (Throwable th3) {
                                                                                            th = th3;
                                                                                            anonymousClass8 = this;
                                                                                            th = th;
                                                                                            r2 = anonymousClass8;
                                                                                            i3 = z4;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                            boolean unused13 = q.F = i3;
                                                                                            boolean unused14 = q.I = i3;
                                                                                            boolean unused15 = q.J = i3;
                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                            q.z.b(r2);
                                                                                            throw th;
                                                                                        }
                                                                                    }
                                                                                    boolean unused16 = q.J = true;
                                                                                    boolean unused17 = q.P = true;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                                        boolean unused18 = q.R = true;
                                                                                        Intent intent = new Intent();
                                                                                        intent.putExtra("action", 16);
                                                                                        intent.putExtra("speed", q.Q[0]);
                                                                                        intent.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                                        intent.putExtra("speedBk", q.Q[1]);
                                                                                        intent.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                                        GameUtil.getIntance().notifyAction(intent);
                                                                                    }
                                                                                    packageName = q.d.getPackageName();
                                                                                    q.n(95);
                                                                                    int iB = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                                    try {
                                                                                        if (i2 == 1) {
                                                                                            if (q.q == null) {
                                                                                            }
                                                                                            strL = q.l(i2);
                                                                                            if (strL != null) {
                                                                                                try {
                                                                                                    try {
                                                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                                    } catch (IOException e4) {
                                                                                                        e4.printStackTrace();
                                                                                                    }
                                                                                                } catch (Exception e5) {
                                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "zip wrong:", e5, new Object[0]);
                                                                                                    z5 = true;
                                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                                    if (z5) {
                                                                                                        new File(str).delete();
                                                                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                                            q.o.add(Integer.valueOf(q.p));
                                                                                                        }
                                                                                                        if (q.K < 2) {
                                                                                                            q.D();
                                                                                                            q.b(i2, bundle);
                                                                                                        } else {
                                                                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                                            q.O();
                                                                                                        }
                                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                                        boolean unused19 = q.F = false;
                                                                                                        boolean unused20 = q.I = false;
                                                                                                        boolean unused21 = q.J = false;
                                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                                        q.z.b(this);
                                                                                                        return;
                                                                                                    }
                                                                                                    q.n(99);
                                                                                                    q.Z.c(100L, 0L);
                                                                                                    q.Z.i();
                                                                                                    boolean unused22 = q.I = true;
                                                                                                    if (i2 == 3) {
                                                                                                        com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                                                        cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                                        new File(cachePath).mkdirs();
                                                                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                                                                            q.R();
                                                                                                        }
                                                                                                        zB = q.b(bundle, q.d, str);
                                                                                                        q.ae.set(true);
                                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                                                        jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                                                        zipEntryA = jVar.a("patchcfg");
                                                                                                        gameUtil2 = gameUtil;
                                                                                                        cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                                                        cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                                        boolean zIsBigAsset = BwbxUtil.isBigAsset(q.d);
                                                                                                        StringBuilder sb = new StringBuilder();
                                                                                                        try {
                                                                                                            try {
                                                                                                                sb.append("needCopyMp4 = ");
                                                                                                                sb.append(zIsBigAsset);
                                                                                                                z7 = zIsBigAsset;
                                                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb.toString(), new Object[0]);
                                                                                                                try {
                                                                                                                    if (zipEntryA != null) {
                                                                                                                        try {
                                                                                                                            try {
                                                                                                                                bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                                                                                z8 = z7;
                                                                                                                                z9 = false;
                                                                                                                                while (true) {
                                                                                                                                    line = bufferedReader.readLine();
                                                                                                                                    if (line != null) {
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                    bufferedReader = bufferedReader;
                                                                                                                                    if (line.startsWith("p:")) {
                                                                                                                                        z12 = z8;
                                                                                                                                        int iIndexOf = line.indexOf(58) + 1;
                                                                                                                                        String strSubstring2 = line.substring(iIndexOf, line.indexOf(58, iIndexOf));
                                                                                                                                        strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                                                        file = new File(cachePath + strSubstring2);
                                                                                                                                        if (file.exists()) {
                                                                                                                                            if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                                                                z9 = z9;
                                                                                                                                            } else {
                                                                                                                                                file.delete();
                                                                                                                                                z9 = true;
                                                                                                                                            }
                                                                                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                                                                                z7 = false;
                                                                                                                                            } else {
                                                                                                                                                z7 = false;
                                                                                                                                            }
                                                                                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            z8 = z12;
                                                                                                                                            z9 = true;
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        try {
                                                                                                                                            if (line.startsWith("px:")) {
                                                                                                                                                z12 = z8;
                                                                                                                                                int iIndexOf2 = line.indexOf(58) + 1;
                                                                                                                                                String strSubstring3 = line.substring(iIndexOf2, line.indexOf(58, iIndexOf2));
                                                                                                                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                                                                file = new File(cachePath + strSubstring3);
                                                                                                                                                if (file.exists()) {
                                                                                                                                                    z8 = z12;
                                                                                                                                                    z9 = true;
                                                                                                                                                } else {
                                                                                                                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                                                                        file.delete();
                                                                                                                                                        z9 = true;
                                                                                                                                                    }
                                                                                                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                                                                                                        z7 = false;
                                                                                                                                                    } else {
                                                                                                                                                        z7 = false;
                                                                                                                                                    }
                                                                                                                                                    z8 = line.endsWith("lbres.rdata.mp4.idx") ? false : z12;
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                z12 = z8;
                                                                                                                                            }
                                                                                                                                            z9 = z9;
                                                                                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                                                                                z7 = false;
                                                                                                                                            } else {
                                                                                                                                                z7 = false;
                                                                                                                                            }
                                                                                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                                                            }
                                                                                                                                        } catch (Throwable th4) {
                                                                                                                                            th = th4;
                                                                                                                                            anonymousClass7 = this;
                                                                                                                                            str3 = "finally";
                                                                                                                                            z4 = false;
                                                                                                                                            anonymousClass8 = anonymousClass7;
                                                                                                                                            th = th;
                                                                                                                                            r2 = anonymousClass8;
                                                                                                                                            i3 = z4;
                                                                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                                                                            boolean unused110 = q.F = i3;
                                                                                                                                            boolean unused111 = q.I = i3;
                                                                                                                                            boolean unused112 = q.J = i3;
                                                                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                                                                            q.z.b(r2);
                                                                                                                                            throw th;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    str3 = "finally";
                                                                                                                                    exc = e;
                                                                                                                                    r4 = r5;
                                                                                                                                    q.f(q.b(q.d, exc));
                                                                                                                                    exc.printStackTrace();
                                                                                                                                    z3 = false;
                                                                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                                                                    r3 = r4;
                                                                                                                                    boolean unused23 = q.F = z3;
                                                                                                                                    boolean unused24 = q.I = z3;
                                                                                                                                    boolean unused25 = q.J = z3;
                                                                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                                                                    q.z.b(r3);
                                                                                                                                    return;
                                                                                                                                }
                                                                                                                                z10 = z8;
                                                                                                                                z11 = z9;
                                                                                                                            } catch (Throwable th5) {
                                                                                                                                th = th5;
                                                                                                                                z13 = false;
                                                                                                                                r2 = this;
                                                                                                                                th = th;
                                                                                                                                str3 = "finally";
                                                                                                                                i3 = z13;
                                                                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                                                                boolean unused113 = q.F = i3;
                                                                                                                                boolean unused114 = q.I = i3;
                                                                                                                                boolean unused115 = q.J = i3;
                                                                                                                                q.ad.removeCallbacks(q.ag);
                                                                                                                                q.z.b(r2);
                                                                                                                                throw th;
                                                                                                                            }
                                                                                                                        } catch (Exception e6) {
                                                                                                                            r4 = this;
                                                                                                                            exc = e6;
                                                                                                                            str3 = "finally";
                                                                                                                            q.f(q.b(q.d, exc));
                                                                                                                            exc.printStackTrace();
                                                                                                                            z3 = false;
                                                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                                                            r3 = r4;
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        z10 = z7;
                                                                                                                        z11 = false;
                                                                                                                    }
                                                                                                                    jVar.a();
                                                                                                                    if (zB) {
                                                                                                                        anonymousClass9 = this;
                                                                                                                        str3 = "finally";
                                                                                                                        z6 = false;
                                                                                                                    } else {
                                                                                                                        new File(cachePath).delete();
                                                                                                                        q.o.clear();
                                                                                                                        int unused26 = q.p = 0;
                                                                                                                        String unused27 = q.q = null;
                                                                                                                        String unused28 = q.r = null;
                                                                                                                        gameUtil2.clearChecked();
                                                                                                                        r5 = this;
                                                                                                                        try {
                                                                                                                            new File(str).delete();
                                                                                                                            gameUtil2.clearResPatchInfo();
                                                                                                                            q.b(1, (Bundle) null);
                                                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                                                            boolean unused29 = q.F = false;
                                                                                                                            boolean unused30 = q.I = false;
                                                                                                                            boolean unused31 = q.J = false;
                                                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                                                            q.z.b(r5);
                                                                                                                            return;
                                                                                                                        } catch (Exception e7) {
                                                                                                                            e = e7;
                                                                                                                        } catch (Throwable th6) {
                                                                                                                            th = th6;
                                                                                                                            str3 = "finally";
                                                                                                                            th = th;
                                                                                                                            i3 = 0;
                                                                                                                            r2 = r5;
                                                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                                                            boolean unused116 = q.F = i3;
                                                                                                                            boolean unused117 = q.I = i3;
                                                                                                                            boolean unused118 = q.J = i3;
                                                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                                                            q.z.b(r2);
                                                                                                                            throw th;
                                                                                                                        }
                                                                                                                    }
                                                                                                                } catch (Exception e8) {
                                                                                                                    e = e8;
                                                                                                                    r5 = this;
                                                                                                                } catch (Throwable th7) {
                                                                                                                    th = th7;
                                                                                                                    r5 = this;
                                                                                                                }
                                                                                                                if (z7) {
                                                                                                                    file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                                                                    file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                                                                    if (file4.exists()) {
                                                                                                                        file5.delete();
                                                                                                                        String str4 = "reSuccess = " + file4.renameTo(file5);
                                                                                                                        z13 = false;
                                                                                                                        try {
                                                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str4, new Object[0]);
                                                                                                                        } catch (Throwable th8) {
                                                                                                                            th = th8;
                                                                                                                            r2 = this;
                                                                                                                            th = th;
                                                                                                                            str3 = "finally";
                                                                                                                            i3 = z13;
                                                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                                                            boolean unused119 = q.F = i3;
                                                                                                                            boolean unused1110 = q.I = i3;
                                                                                                                            boolean unused1111 = q.J = i3;
                                                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                                                            q.z.b(r2);
                                                                                                                            throw th;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                                if (z10) {
                                                                                                                    file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                                                    file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                                                    if (file2.exists()) {
                                                                                                                        file3.delete();
                                                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                                                                    }
                                                                                                                }
                                                                                                                if (z11) {
                                                                                                                    ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                                                                }
                                                                                                            } catch (Throwable th9) {
                                                                                                                th = th9;
                                                                                                                r5 = this;
                                                                                                            }
                                                                                                        } catch (Exception e9) {
                                                                                                            e = e9;
                                                                                                            r5 = this;
                                                                                                        }
                                                                                                        str3 = "finally";
                                                                                                        exc = e;
                                                                                                        r4 = r5;
                                                                                                        q.f(q.b(q.d, exc));
                                                                                                        exc.printStackTrace();
                                                                                                        z3 = false;
                                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                                        r3 = r4;
                                                                                                        boolean unused210 = q.F = z3;
                                                                                                        boolean unused211 = q.I = z3;
                                                                                                        boolean unused212 = q.J = z3;
                                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                                        q.z.b(r3);
                                                                                                        return;
                                                                                                    }
                                                                                                    gameUtil2 = gameUtil;
                                                                                                    AnonymousClass7 anonymousClass10 = this;
                                                                                                    z6 = false;
                                                                                                    new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                                                    anonymousClass9 = anonymousClass10;
                                                                                                    boolean unused32 = q.I = z6;
                                                                                                    q.O();
                                                                                                    q.p(i2);
                                                                                                    if (i2 == 1) {
                                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                                    } else if (i2 == 2) {
                                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                                                    } else if (i2 == 3) {
                                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                                        } else {
                                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                                        }
                                                                                                        ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                                                        new File(str).delete();
                                                                                                        gameUtil2.clearResPatchInfo();
                                                                                                    }
                                                                                                    ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                                                    q.Z.e();
                                                                                                    if (i2 != 3) {
                                                                                                        q.c(str, i2);
                                                                                                    }
                                                                                                    q.n(100);
                                                                                                    q.O();
                                                                                                    q.r(i2);
                                                                                                    q.Z.e(100L);
                                                                                                    q.Z.i();
                                                                                                    Intent intent2 = new Intent();
                                                                                                    intent2.putExtra("action", 12);
                                                                                                    intent2.putExtra("vercode", String.valueOf(q.a));
                                                                                                    intent2.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                                                    GameUtil.getIntance().notifyAction(intent2);
                                                                                                    ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                                                    z3 = false;
                                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                                    r3 = anonymousClass9;
                                                                                                    boolean unused213 = q.F = z3;
                                                                                                    boolean unused214 = q.I = z3;
                                                                                                    boolean unused215 = q.J = z3;
                                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                                    q.z.b(r3);
                                                                                                    return;
                                                                                                }
                                                                                            } else {
                                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                            }
                                                                                            z5 = false;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                            if (z5) {
                                                                                                new File(str).delete();
                                                                                                if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                                    q.o.add(Integer.valueOf(q.p));
                                                                                                }
                                                                                                if (q.K < 2) {
                                                                                                    q.D();
                                                                                                    q.b(i2, bundle);
                                                                                                } else {
                                                                                                    q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                                    q.O();
                                                                                                }
                                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                                boolean unused120 = q.F = false;
                                                                                                boolean unused216 = q.I = false;
                                                                                                boolean unused217 = q.J = false;
                                                                                                q.ad.removeCallbacks(q.ag);
                                                                                                q.z.b(this);
                                                                                                return;
                                                                                            }
                                                                                        } else {
                                                                                            if (q.q == null) {
                                                                                            }
                                                                                            strL = q.l(i2);
                                                                                            if (strL != null) {
                                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                            } else {
                                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                            }
                                                                                            z5 = false;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                            if (z5) {
                                                                                                new File(str).delete();
                                                                                                if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                                    q.o.add(Integer.valueOf(q.p));
                                                                                                }
                                                                                                if (q.K < 2) {
                                                                                                    q.D();
                                                                                                    q.b(i2, bundle);
                                                                                                } else {
                                                                                                    q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                                    q.O();
                                                                                                }
                                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                                boolean unused121 = q.F = false;
                                                                                                boolean unused218 = q.I = false;
                                                                                                boolean unused219 = q.J = false;
                                                                                                q.ad.removeCallbacks(q.ag);
                                                                                                q.z.b(this);
                                                                                                return;
                                                                                            }
                                                                                        }
                                                                                        q.n(99);
                                                                                        q.Z.c(100L, 0L);
                                                                                        q.Z.i();
                                                                                        boolean unused220 = q.I = true;
                                                                                        if (i2 == 3) {
                                                                                            com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                                            cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                            new File(cachePath).mkdirs();
                                                                                            if (BwbxUtil.isBigAsset(q.d)) {
                                                                                                q.R();
                                                                                            }
                                                                                            zB = q.b(bundle, q.d, str);
                                                                                            q.ae.set(true);
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                                            jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                                            zipEntryA = jVar.a("patchcfg");
                                                                                            gameUtil2 = gameUtil;
                                                                                            cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                                            cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                            boolean zIsBigAsset2 = BwbxUtil.isBigAsset(q.d);
                                                                                            StringBuilder sb2 = new StringBuilder();
                                                                                            sb2.append("needCopyMp4 = ");
                                                                                            sb2.append(zIsBigAsset2);
                                                                                            z7 = zIsBigAsset2;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb2.toString(), new Object[0]);
                                                                                            if (zipEntryA != null) {
                                                                                                bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                                                z8 = z7;
                                                                                                z9 = false;
                                                                                                while (true) {
                                                                                                    line = bufferedReader.readLine();
                                                                                                    if (line != null) {
                                                                                                        break;
                                                                                                        break;
                                                                                                    }
                                                                                                    bufferedReader = bufferedReader;
                                                                                                    if (line.startsWith("p:")) {
                                                                                                        if (line.startsWith("px:")) {
                                                                                                            z12 = z8;
                                                                                                            int iIndexOf3 = line.indexOf(58) + 1;
                                                                                                            String strSubstring4 = line.substring(iIndexOf3, line.indexOf(58, iIndexOf3));
                                                                                                            strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                            file = new File(cachePath + strSubstring4);
                                                                                                            if (file.exists()) {
                                                                                                                z8 = z12;
                                                                                                                z9 = true;
                                                                                                            } else {
                                                                                                                if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                                    file.delete();
                                                                                                                    z9 = true;
                                                                                                                }
                                                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                                                    z7 = false;
                                                                                                                } else {
                                                                                                                    z7 = false;
                                                                                                                }
                                                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                                }
                                                                                                            }
                                                                                                        } else {
                                                                                                            z12 = z8;
                                                                                                        }
                                                                                                        z9 = z9;
                                                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                                                            z7 = false;
                                                                                                        } else {
                                                                                                            z7 = false;
                                                                                                        }
                                                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                        }
                                                                                                    } else {
                                                                                                        z12 = z8;
                                                                                                        int iIndexOf4 = line.indexOf(58) + 1;
                                                                                                        String strSubstring5 = line.substring(iIndexOf4, line.indexOf(58, iIndexOf4));
                                                                                                        strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                        file = new File(cachePath + strSubstring5);
                                                                                                        if (file.exists()) {
                                                                                                            z8 = z12;
                                                                                                            z9 = true;
                                                                                                        } else {
                                                                                                            if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                                file.delete();
                                                                                                                z9 = true;
                                                                                                            } else {
                                                                                                                z9 = z9;
                                                                                                            }
                                                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                                                z7 = false;
                                                                                                            } else {
                                                                                                                z7 = false;
                                                                                                            }
                                                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                z10 = z8;
                                                                                                z11 = z9;
                                                                                            } else {
                                                                                                z10 = z7;
                                                                                                z11 = false;
                                                                                            }
                                                                                            if (z7) {
                                                                                                file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                                                file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                                                if (file4.exists()) {
                                                                                                    file5.delete();
                                                                                                    String str5 = "reSuccess = " + file4.renameTo(file5);
                                                                                                    z13 = false;
                                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str5, new Object[0]);
                                                                                                }
                                                                                            }
                                                                                            if (z10) {
                                                                                                file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                                file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                                if (file2.exists()) {
                                                                                                    file3.delete();
                                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                                                }
                                                                                            }
                                                                                            if (z11) {
                                                                                                ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                                            }
                                                                                            jVar.a();
                                                                                            if (zB) {
                                                                                                new File(cachePath).delete();
                                                                                                q.o.clear();
                                                                                                int unused221 = q.p = 0;
                                                                                                String unused222 = q.q = null;
                                                                                                String unused223 = q.r = null;
                                                                                                gameUtil2.clearChecked();
                                                                                                r5 = this;
                                                                                                new File(str).delete();
                                                                                                gameUtil2.clearResPatchInfo();
                                                                                                q.b(1, (Bundle) null);
                                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                                boolean unused224 = q.F = false;
                                                                                                boolean unused33 = q.I = false;
                                                                                                boolean unused34 = q.J = false;
                                                                                                q.ad.removeCallbacks(q.ag);
                                                                                                q.z.b(r5);
                                                                                                return;
                                                                                            }
                                                                                            anonymousClass9 = this;
                                                                                            str3 = "finally";
                                                                                            z6 = false;
                                                                                        } else {
                                                                                            gameUtil2 = gameUtil;
                                                                                            AnonymousClass7 anonymousClass11 = this;
                                                                                            z6 = false;
                                                                                            new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                                            anonymousClass9 = anonymousClass11;
                                                                                        }
                                                                                        boolean unused35 = q.I = z6;
                                                                                        q.O();
                                                                                        q.p(i2);
                                                                                        if (i2 == 1) {
                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                        } else if (i2 == 2) {
                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                                        } else if (i2 == 3) {
                                                                                            ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                                            if (BwbxUtil.isBigAsset(q.d)) {
                                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                            } else {
                                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                            }
                                                                                            ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                                            new File(str).delete();
                                                                                            gameUtil2.clearResPatchInfo();
                                                                                        }
                                                                                        ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                                        q.Z.e();
                                                                                        if (i2 != 3) {
                                                                                            q.c(str, i2);
                                                                                        }
                                                                                        q.n(100);
                                                                                        q.O();
                                                                                        q.r(i2);
                                                                                        q.Z.e(100L);
                                                                                        q.Z.i();
                                                                                        Intent intent3 = new Intent();
                                                                                        intent3.putExtra("action", 12);
                                                                                        intent3.putExtra("vercode", String.valueOf(q.a));
                                                                                        intent3.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                                        GameUtil.getIntance().notifyAction(intent3);
                                                                                        ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                                        z3 = false;
                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                        r3 = anonymousClass9;
                                                                                    } catch (Exception e10) {
                                                                                        e = e10;
                                                                                    }
                                                                                    boolean unused2110 = q.F = z3;
                                                                                    boolean unused2111 = q.I = z3;
                                                                                    boolean unused2112 = q.J = z3;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(r3);
                                                                                    return;
                                                                                    th = th;
                                                                                    i3 = 0;
                                                                                    r2 = r5;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                    boolean unused1112 = q.F = i3;
                                                                                    boolean unused1113 = q.I = i3;
                                                                                    boolean unused1114 = q.J = i3;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(r2);
                                                                                    throw th;
                                                                                }
                                                                                intance = gameUtil;
                                                                            }
                                                                            strB = q.b(i5, i6);
                                                                            i7 = i4;
                                                                            if (q.b) {
                                                                                i4 = i7;
                                                                                exc2 = exc3;
                                                                                j3 = j5;
                                                                                if (i4 > 5) {
                                                                                    if (exc2 == null) {
                                                                                        throw exc2;
                                                                                    }
                                                                                    Exception exc5 = new Exception();
                                                                                    q.b(q.E);
                                                                                    throw exc5;
                                                                                }
                                                                                if (q.b) {
                                                                                    z4 = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                                    q.b = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                    boolean unused122 = q.F = false;
                                                                                    boolean unused123 = q.I = false;
                                                                                    boolean unused124 = q.J = false;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(this);
                                                                                    return;
                                                                                }
                                                                                boolean unused125 = q.J = true;
                                                                                boolean unused126 = q.P = true;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                                    boolean unused127 = q.R = true;
                                                                                    Intent intent4 = new Intent();
                                                                                    intent4.putExtra("action", 16);
                                                                                    intent4.putExtra("speed", q.Q[0]);
                                                                                    intent4.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                                    intent4.putExtra("speedBk", q.Q[1]);
                                                                                    intent4.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                                    GameUtil.getIntance().notifyAction(intent4);
                                                                                }
                                                                                packageName = q.d.getPackageName();
                                                                                q.n(95);
                                                                                int iB2 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                                if (i2 == 1) {
                                                                                    if (q.q == null) {
                                                                                    }
                                                                                    strL = q.l(i2);
                                                                                    if (strL != null) {
                                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                    } else {
                                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                    }
                                                                                    z5 = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                    if (z5) {
                                                                                        new File(str).delete();
                                                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                            q.o.add(Integer.valueOf(q.p));
                                                                                        }
                                                                                        if (q.K < 2) {
                                                                                            q.D();
                                                                                            q.b(i2, bundle);
                                                                                        } else {
                                                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                            q.O();
                                                                                        }
                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                        boolean unused128 = q.F = false;
                                                                                        boolean unused2113 = q.I = false;
                                                                                        boolean unused2114 = q.J = false;
                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                        q.z.b(this);
                                                                                        return;
                                                                                    }
                                                                                } else {
                                                                                    if (q.q == null) {
                                                                                    }
                                                                                    strL = q.l(i2);
                                                                                    if (strL != null) {
                                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                    } else {
                                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                    }
                                                                                    z5 = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                    if (z5) {
                                                                                        new File(str).delete();
                                                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                            q.o.add(Integer.valueOf(q.p));
                                                                                        }
                                                                                        if (q.K < 2) {
                                                                                            q.D();
                                                                                            q.b(i2, bundle);
                                                                                        } else {
                                                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                            q.O();
                                                                                        }
                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                        boolean unused129 = q.F = false;
                                                                                        boolean unused2115 = q.I = false;
                                                                                        boolean unused2116 = q.J = false;
                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                        q.z.b(this);
                                                                                        return;
                                                                                    }
                                                                                }
                                                                                q.n(99);
                                                                                q.Z.c(100L, 0L);
                                                                                q.Z.i();
                                                                                boolean unused225 = q.I = true;
                                                                                if (i2 == 3) {
                                                                                    com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                                    cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                    new File(cachePath).mkdirs();
                                                                                    if (BwbxUtil.isBigAsset(q.d)) {
                                                                                        q.R();
                                                                                    }
                                                                                    zB = q.b(bundle, q.d, str);
                                                                                    q.ae.set(true);
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                                    jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                                    zipEntryA = jVar.a("patchcfg");
                                                                                    gameUtil2 = gameUtil;
                                                                                    cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                                    cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                    boolean zIsBigAsset3 = BwbxUtil.isBigAsset(q.d);
                                                                                    StringBuilder sb3 = new StringBuilder();
                                                                                    sb3.append("needCopyMp4 = ");
                                                                                    sb3.append(zIsBigAsset3);
                                                                                    z7 = zIsBigAsset3;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb3.toString(), new Object[0]);
                                                                                    if (zipEntryA != null) {
                                                                                        bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                                        z8 = z7;
                                                                                        z9 = false;
                                                                                        while (true) {
                                                                                            line = bufferedReader.readLine();
                                                                                            if (line != null) {
                                                                                                break;
                                                                                                break;
                                                                                            }
                                                                                            bufferedReader = bufferedReader;
                                                                                            if (line.startsWith("p:")) {
                                                                                                if (line.startsWith("px:")) {
                                                                                                    z12 = z8;
                                                                                                    int iIndexOf5 = line.indexOf(58) + 1;
                                                                                                    String strSubstring6 = line.substring(iIndexOf5, line.indexOf(58, iIndexOf5));
                                                                                                    strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                    file = new File(cachePath + strSubstring6);
                                                                                                    if (file.exists()) {
                                                                                                        z8 = z12;
                                                                                                        z9 = true;
                                                                                                    } else {
                                                                                                        if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                            file.delete();
                                                                                                            z9 = true;
                                                                                                        }
                                                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                                                            z7 = false;
                                                                                                        } else {
                                                                                                            z7 = false;
                                                                                                        }
                                                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    z12 = z8;
                                                                                                }
                                                                                                z9 = z9;
                                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                                    z7 = false;
                                                                                                } else {
                                                                                                    z7 = false;
                                                                                                }
                                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                }
                                                                                            } else {
                                                                                                z12 = z8;
                                                                                                int iIndexOf6 = line.indexOf(58) + 1;
                                                                                                String strSubstring7 = line.substring(iIndexOf6, line.indexOf(58, iIndexOf6));
                                                                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                file = new File(cachePath + strSubstring7);
                                                                                                if (file.exists()) {
                                                                                                    z8 = z12;
                                                                                                    z9 = true;
                                                                                                } else {
                                                                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                        file.delete();
                                                                                                        z9 = true;
                                                                                                    } else {
                                                                                                        z9 = z9;
                                                                                                    }
                                                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                                                        z7 = false;
                                                                                                    } else {
                                                                                                        z7 = false;
                                                                                                    }
                                                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            str3 = "finally";
                                                                                            exc = e;
                                                                                            r4 = r5;
                                                                                            q.f(q.b(q.d, exc));
                                                                                            exc.printStackTrace();
                                                                                            z3 = false;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                            r3 = r4;
                                                                                            boolean unused2117 = q.F = z3;
                                                                                            boolean unused2118 = q.I = z3;
                                                                                            boolean unused2119 = q.J = z3;
                                                                                            q.ad.removeCallbacks(q.ag);
                                                                                            q.z.b(r3);
                                                                                            return;
                                                                                        }
                                                                                        z10 = z8;
                                                                                        z11 = z9;
                                                                                    } else {
                                                                                        z10 = z7;
                                                                                        z11 = false;
                                                                                    }
                                                                                    if (z7) {
                                                                                        file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                                        file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                                        if (file4.exists()) {
                                                                                            file5.delete();
                                                                                            String str6 = "reSuccess = " + file4.renameTo(file5);
                                                                                            z13 = false;
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str6, new Object[0]);
                                                                                        }
                                                                                    }
                                                                                    if (z10) {
                                                                                        file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                        file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                        if (file2.exists()) {
                                                                                            file3.delete();
                                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                                        }
                                                                                    }
                                                                                    if (z11) {
                                                                                        ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                                    }
                                                                                    jVar.a();
                                                                                    if (zB) {
                                                                                        new File(cachePath).delete();
                                                                                        q.o.clear();
                                                                                        int unused226 = q.p = 0;
                                                                                        String unused227 = q.q = null;
                                                                                        String unused228 = q.r = null;
                                                                                        gameUtil2.clearChecked();
                                                                                        r5 = this;
                                                                                        new File(str).delete();
                                                                                        gameUtil2.clearResPatchInfo();
                                                                                        q.b(1, (Bundle) null);
                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                        boolean unused229 = q.F = false;
                                                                                        boolean unused36 = q.I = false;
                                                                                        boolean unused37 = q.J = false;
                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                        q.z.b(r5);
                                                                                        return;
                                                                                    }
                                                                                    anonymousClass9 = this;
                                                                                    str3 = "finally";
                                                                                    z6 = false;
                                                                                    str3 = "finally";
                                                                                    exc = e;
                                                                                    r4 = r5;
                                                                                    q.f(q.b(q.d, exc));
                                                                                    exc.printStackTrace();
                                                                                    z3 = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                    r3 = r4;
                                                                                    boolean unused21110 = q.F = z3;
                                                                                    boolean unused21111 = q.I = z3;
                                                                                    boolean unused21112 = q.J = z3;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(r3);
                                                                                    return;
                                                                                }
                                                                                gameUtil2 = gameUtil;
                                                                                AnonymousClass7 anonymousClass12 = this;
                                                                                z6 = false;
                                                                                new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                                anonymousClass9 = anonymousClass12;
                                                                                boolean unused38 = q.I = z6;
                                                                                q.O();
                                                                                q.p(i2);
                                                                                if (i2 == 1) {
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                } else if (i2 == 2) {
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                                } else if (i2 == 3) {
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                                    if (BwbxUtil.isBigAsset(q.d)) {
                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                    } else {
                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                    }
                                                                                    ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                                    new File(str).delete();
                                                                                    gameUtil2.clearResPatchInfo();
                                                                                }
                                                                                ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                                q.Z.e();
                                                                                if (i2 != 3) {
                                                                                    q.c(str, i2);
                                                                                }
                                                                                q.n(100);
                                                                                q.O();
                                                                                q.r(i2);
                                                                                q.Z.e(100L);
                                                                                q.Z.i();
                                                                                Intent intent5 = new Intent();
                                                                                intent5.putExtra("action", 12);
                                                                                intent5.putExtra("vercode", String.valueOf(q.a));
                                                                                intent5.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                                GameUtil.getIntance().notifyAction(intent5);
                                                                                ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                                z3 = false;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                r3 = anonymousClass9;
                                                                                boolean unused21113 = q.F = z3;
                                                                                boolean unused21114 = q.I = z3;
                                                                                boolean unused21115 = q.J = z3;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                q.z.b(r3);
                                                                                return;
                                                                                th = th;
                                                                                i3 = 0;
                                                                                r2 = r5;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                                boolean unused1115 = q.F = i3;
                                                                                boolean unused1116 = q.I = i3;
                                                                                boolean unused1117 = q.J = i3;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                q.z.b(r2);
                                                                                throw th;
                                                                            }
                                                                            intance = gameUtil;
                                                                        }
                                                                    } else {
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "start offset : " + q.ab, new Object[0]);
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "bwbxOffset_" + q.a, q.ab);
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "bwbxCount_" + q.a, q.ac);
                                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                                            long jC = com.excelliance.lbsdk.main.bwbx.g.a().c();
                                                                            try {
                                                                                strB = q.m(q.p) + q.e(BwbxUtil.DOWNLOAD_MP4_FILE_NAME);
                                                                                com.excelliance.lbsdk.main.g.b.e("LBBwbxDownloader", "try download big asset split (" + i4 + ")   reqUrl=" + strB + " dlUrl = " + q.p + " dlsZipType=" + i2, new Object[0]);
                                                                                q.aa.a(q.p, str, jC);
                                                                                j5 = jC;
                                                                            } catch (Exception e11) {
                                                                                exc3 = e11;
                                                                                j5 = jC;
                                                                                str2 = "splitZip";
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                                                q.b(exc3);
                                                                                if (q.c) {
                                                                                    i4--;
                                                                                    q.c = false;
                                                                                    i5 = q.p;
                                                                                    i6 = i2;
                                                                                } else {
                                                                                    if (i4 % 2 == 0 && q.o.size() < 2) {
                                                                                        q.ad.removeCallbacks(q.ag);
                                                                                        do {
                                                                                            int unused39 = q.p = q.v() % 3;
                                                                                        } while (q.o.contains(Integer.valueOf(q.p)));
                                                                                        ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                                                        i5 = q.p;
                                                                                        i6 = i2;
                                                                                    }
                                                                                    i7 = i4;
                                                                                    if (q.b) {
                                                                                        i4 = i7;
                                                                                        exc2 = exc3;
                                                                                    } else {
                                                                                        intance = gameUtil;
                                                                                    }
                                                                                }
                                                                                strB = q.b(i5, i6);
                                                                                i7 = i4;
                                                                                if (q.b) {
                                                                                    i4 = i7;
                                                                                    exc2 = exc3;
                                                                                } else {
                                                                                    intance = gameUtil;
                                                                                }
                                                                            }
                                                                        } else {
                                                                            q.Z.d(i2);
                                                                            q.Z.a(strB, str, j5, q.ab);
                                                                        }
                                                                        str2 = "splitZip";
                                                                        try {
                                                                            ae.a(q.d, "lebian_module_dloadinfo", str2, 1);
                                                                        } catch (Exception e12) {
                                                                            e = e12;
                                                                            exc3 = e;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                                            q.b(exc3);
                                                                            if (q.c) {
                                                                                i4--;
                                                                                q.c = false;
                                                                                i5 = q.p;
                                                                                i6 = i2;
                                                                            } else {
                                                                                if (i4 % 2 == 0) {
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    do {
                                                                                        int unused310 = q.p = q.v() % 3;
                                                                                    } while (q.o.contains(Integer.valueOf(q.p)));
                                                                                    ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                                                    i5 = q.p;
                                                                                    i6 = i2;
                                                                                }
                                                                                i7 = i4;
                                                                                if (q.b) {
                                                                                    i4 = i7;
                                                                                    exc2 = exc3;
                                                                                } else {
                                                                                    intance = gameUtil;
                                                                                }
                                                                            }
                                                                            strB = q.b(i5, i6);
                                                                            i7 = i4;
                                                                            if (q.b) {
                                                                                i4 = i7;
                                                                                exc2 = exc3;
                                                                            } else {
                                                                                intance = gameUtil;
                                                                            }
                                                                        }
                                                                    }
                                                                } catch (Exception e13) {
                                                                    e = e13;
                                                                    str2 = "splitZip";
                                                                    exc3 = e;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                                    q.b(exc3);
                                                                    if (q.c) {
                                                                        i4--;
                                                                        q.c = false;
                                                                        i5 = q.p;
                                                                        i6 = i2;
                                                                    } else {
                                                                        if (i4 % 2 == 0) {
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            do {
                                                                                int unused311 = q.p = q.v() % 3;
                                                                            } while (q.o.contains(Integer.valueOf(q.p)));
                                                                            ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                                            i5 = q.p;
                                                                            i6 = i2;
                                                                        }
                                                                        i7 = i4;
                                                                        if (q.b) {
                                                                            i4 = i7;
                                                                            exc2 = exc3;
                                                                            j3 = j5;
                                                                            if (i4 > 5) {
                                                                                if (exc2 == null) {
                                                                                    throw exc2;
                                                                                }
                                                                                Exception exc6 = new Exception();
                                                                                q.b(q.E);
                                                                                throw exc6;
                                                                            }
                                                                            if (q.b) {
                                                                                z4 = false;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                                q.b = false;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                boolean unused1210 = q.F = false;
                                                                                boolean unused1211 = q.I = false;
                                                                                boolean unused1212 = q.J = false;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                q.z.b(this);
                                                                                return;
                                                                            }
                                                                            boolean unused1213 = q.J = true;
                                                                            boolean unused1214 = q.P = true;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                                boolean unused1215 = q.R = true;
                                                                                Intent intent6 = new Intent();
                                                                                intent6.putExtra("action", 16);
                                                                                intent6.putExtra("speed", q.Q[0]);
                                                                                intent6.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                                intent6.putExtra("speedBk", q.Q[1]);
                                                                                intent6.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                                GameUtil.getIntance().notifyAction(intent6);
                                                                            }
                                                                            packageName = q.d.getPackageName();
                                                                            q.n(95);
                                                                            int iB3 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                            if (i2 == 1) {
                                                                                if (q.q == null) {
                                                                                }
                                                                                strL = q.l(i2);
                                                                                if (strL != null) {
                                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                } else {
                                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                }
                                                                                z5 = false;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                if (z5) {
                                                                                    new File(str).delete();
                                                                                    if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                        q.o.add(Integer.valueOf(q.p));
                                                                                    }
                                                                                    if (q.K < 2) {
                                                                                        q.D();
                                                                                        q.b(i2, bundle);
                                                                                    } else {
                                                                                        q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                        q.O();
                                                                                    }
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                    boolean unused1216 = q.F = false;
                                                                                    boolean unused21116 = q.I = false;
                                                                                    boolean unused21117 = q.J = false;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(this);
                                                                                    return;
                                                                                }
                                                                            } else {
                                                                                if (q.q == null) {
                                                                                }
                                                                                strL = q.l(i2);
                                                                                if (strL != null) {
                                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                } else {
                                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                                }
                                                                                z5 = false;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                                if (z5) {
                                                                                    new File(str).delete();
                                                                                    if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                        q.o.add(Integer.valueOf(q.p));
                                                                                    }
                                                                                    if (q.K < 2) {
                                                                                        q.D();
                                                                                        q.b(i2, bundle);
                                                                                    } else {
                                                                                        q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                        q.O();
                                                                                    }
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                    boolean unused1217 = q.F = false;
                                                                                    boolean unused21118 = q.I = false;
                                                                                    boolean unused21119 = q.J = false;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(this);
                                                                                    return;
                                                                                }
                                                                            }
                                                                            q.n(99);
                                                                            q.Z.c(100L, 0L);
                                                                            q.Z.i();
                                                                            boolean unused2210 = q.I = true;
                                                                            if (i2 == 3) {
                                                                                com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                                cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                new File(cachePath).mkdirs();
                                                                                if (BwbxUtil.isBigAsset(q.d)) {
                                                                                    q.R();
                                                                                }
                                                                                zB = q.b(bundle, q.d, str);
                                                                                q.ae.set(true);
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                                jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                                zipEntryA = jVar.a("patchcfg");
                                                                                gameUtil2 = gameUtil;
                                                                                cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                                cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                                boolean zIsBigAsset4 = BwbxUtil.isBigAsset(q.d);
                                                                                StringBuilder sb4 = new StringBuilder();
                                                                                sb4.append("needCopyMp4 = ");
                                                                                sb4.append(zIsBigAsset4);
                                                                                z7 = zIsBigAsset4;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb4.toString(), new Object[0]);
                                                                                if (zipEntryA != null) {
                                                                                    bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                                    z8 = z7;
                                                                                    z9 = false;
                                                                                    while (true) {
                                                                                        line = bufferedReader.readLine();
                                                                                        if (line != null) {
                                                                                            break;
                                                                                            break;
                                                                                        }
                                                                                        bufferedReader = bufferedReader;
                                                                                        if (line.startsWith("p:")) {
                                                                                            if (line.startsWith("px:")) {
                                                                                                z12 = z8;
                                                                                                int iIndexOf7 = line.indexOf(58) + 1;
                                                                                                String strSubstring8 = line.substring(iIndexOf7, line.indexOf(58, iIndexOf7));
                                                                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                                file = new File(cachePath + strSubstring8);
                                                                                                if (file.exists()) {
                                                                                                    z8 = z12;
                                                                                                    z9 = true;
                                                                                                } else {
                                                                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                        file.delete();
                                                                                                        z9 = true;
                                                                                                    }
                                                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                                                        z7 = false;
                                                                                                    } else {
                                                                                                        z7 = false;
                                                                                                    }
                                                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                z12 = z8;
                                                                                            }
                                                                                            z9 = z9;
                                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                                z7 = false;
                                                                                            } else {
                                                                                                z7 = false;
                                                                                            }
                                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                            }
                                                                                        } else {
                                                                                            z12 = z8;
                                                                                            int iIndexOf8 = line.indexOf(58) + 1;
                                                                                            String strSubstring9 = line.substring(iIndexOf8, line.indexOf(58, iIndexOf8));
                                                                                            strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                            file = new File(cachePath + strSubstring9);
                                                                                            if (file.exists()) {
                                                                                                z8 = z12;
                                                                                                z9 = true;
                                                                                            } else {
                                                                                                if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                    file.delete();
                                                                                                    z9 = true;
                                                                                                } else {
                                                                                                    z9 = z9;
                                                                                                }
                                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                                    z7 = false;
                                                                                                } else {
                                                                                                    z7 = false;
                                                                                                }
                                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    z10 = z8;
                                                                                    z11 = z9;
                                                                                } else {
                                                                                    z10 = z7;
                                                                                    z11 = false;
                                                                                }
                                                                                if (z7) {
                                                                                    file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                                    file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                                    if (file4.exists()) {
                                                                                        file5.delete();
                                                                                        String str7 = "reSuccess = " + file4.renameTo(file5);
                                                                                        z13 = false;
                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str7, new Object[0]);
                                                                                    }
                                                                                }
                                                                                if (z10) {
                                                                                    file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                    file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                    if (file2.exists()) {
                                                                                        file3.delete();
                                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                                    }
                                                                                }
                                                                                if (z11) {
                                                                                    ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                                }
                                                                                jVar.a();
                                                                                if (zB) {
                                                                                    new File(cachePath).delete();
                                                                                    q.o.clear();
                                                                                    int unused2211 = q.p = 0;
                                                                                    String unused2212 = q.q = null;
                                                                                    String unused2213 = q.r = null;
                                                                                    gameUtil2.clearChecked();
                                                                                    r5 = this;
                                                                                    new File(str).delete();
                                                                                    gameUtil2.clearResPatchInfo();
                                                                                    q.b(1, (Bundle) null);
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                    boolean unused2214 = q.F = false;
                                                                                    boolean unused312 = q.I = false;
                                                                                    boolean unused313 = q.J = false;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(r5);
                                                                                    return;
                                                                                }
                                                                                anonymousClass9 = this;
                                                                                str3 = "finally";
                                                                                z6 = false;
                                                                            } else {
                                                                                gameUtil2 = gameUtil;
                                                                                AnonymousClass7 anonymousClass13 = this;
                                                                                z6 = false;
                                                                                new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                                anonymousClass9 = anonymousClass13;
                                                                            }
                                                                            boolean unused314 = q.I = z6;
                                                                            q.O();
                                                                            q.p(i2);
                                                                            if (i2 == 1) {
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                            } else if (i2 == 2) {
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                            } else if (i2 == 3) {
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                                if (BwbxUtil.isBigAsset(q.d)) {
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                } else {
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                                }
                                                                                ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                                new File(str).delete();
                                                                                gameUtil2.clearResPatchInfo();
                                                                            }
                                                                            ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                            q.Z.e();
                                                                            if (i2 != 3) {
                                                                                q.c(str, i2);
                                                                            }
                                                                            q.n(100);
                                                                            q.O();
                                                                            q.r(i2);
                                                                            q.Z.e(100L);
                                                                            q.Z.i();
                                                                            Intent intent7 = new Intent();
                                                                            intent7.putExtra("action", 12);
                                                                            intent7.putExtra("vercode", String.valueOf(q.a));
                                                                            intent7.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                            GameUtil.getIntance().notifyAction(intent7);
                                                                            ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                            z3 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                            r3 = anonymousClass9;
                                                                            boolean unused211110 = q.F = z3;
                                                                            boolean unused211111 = q.I = z3;
                                                                            boolean unused211112 = q.J = z3;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(r3);
                                                                            return;
                                                                            th = th;
                                                                            i3 = 0;
                                                                            r2 = r5;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                            boolean unused1118 = q.F = i3;
                                                                            boolean unused1119 = q.I = i3;
                                                                            boolean unused11110 = q.J = i3;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(r2);
                                                                            throw th;
                                                                        }
                                                                        intance = gameUtil;
                                                                    }
                                                                    strB = q.b(i5, i6);
                                                                    i7 = i4;
                                                                    if (q.b) {
                                                                        i4 = i7;
                                                                        exc2 = exc3;
                                                                        j3 = j5;
                                                                        if (i4 > 5) {
                                                                            if (exc2 == null) {
                                                                                throw exc2;
                                                                            }
                                                                            Exception exc7 = new Exception();
                                                                            q.b(q.E);
                                                                            throw exc7;
                                                                        }
                                                                        if (q.b) {
                                                                            z4 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                            q.b = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                            boolean unused1218 = q.F = false;
                                                                            boolean unused1219 = q.I = false;
                                                                            boolean unused12110 = q.J = false;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(this);
                                                                            return;
                                                                        }
                                                                        boolean unused12111 = q.J = true;
                                                                        boolean unused12112 = q.P = true;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                            boolean unused12113 = q.R = true;
                                                                            Intent intent8 = new Intent();
                                                                            intent8.putExtra("action", 16);
                                                                            intent8.putExtra("speed", q.Q[0]);
                                                                            intent8.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                            intent8.putExtra("speedBk", q.Q[1]);
                                                                            intent8.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                            GameUtil.getIntance().notifyAction(intent8);
                                                                        }
                                                                        packageName = q.d.getPackageName();
                                                                        q.n(95);
                                                                        int iB4 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                        if (i2 == 1) {
                                                                            if (q.q == null) {
                                                                            }
                                                                            strL = q.l(i2);
                                                                            if (strL != null) {
                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                            } else {
                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                            }
                                                                            z5 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                            if (z5) {
                                                                                new File(str).delete();
                                                                                if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                    q.o.add(Integer.valueOf(q.p));
                                                                                }
                                                                                if (q.K < 2) {
                                                                                    q.D();
                                                                                    q.b(i2, bundle);
                                                                                } else {
                                                                                    q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                    q.O();
                                                                                }
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                boolean unused12114 = q.F = false;
                                                                                boolean unused211113 = q.I = false;
                                                                                boolean unused211114 = q.J = false;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                q.z.b(this);
                                                                                return;
                                                                            }
                                                                        } else {
                                                                            if (q.q == null) {
                                                                            }
                                                                            strL = q.l(i2);
                                                                            if (strL != null) {
                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                            } else {
                                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                                            }
                                                                            z5 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                            if (z5) {
                                                                                new File(str).delete();
                                                                                if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                    q.o.add(Integer.valueOf(q.p));
                                                                                }
                                                                                if (q.K < 2) {
                                                                                    q.D();
                                                                                    q.b(i2, bundle);
                                                                                } else {
                                                                                    q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                    q.O();
                                                                                }
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                boolean unused12115 = q.F = false;
                                                                                boolean unused211115 = q.I = false;
                                                                                boolean unused211116 = q.J = false;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                q.z.b(this);
                                                                                return;
                                                                            }
                                                                        }
                                                                        q.n(99);
                                                                        q.Z.c(100L, 0L);
                                                                        q.Z.i();
                                                                        boolean unused2215 = q.I = true;
                                                                        if (i2 == 3) {
                                                                            com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                            cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                            new File(cachePath).mkdirs();
                                                                            if (BwbxUtil.isBigAsset(q.d)) {
                                                                                q.R();
                                                                            }
                                                                            zB = q.b(bundle, q.d, str);
                                                                            q.ae.set(true);
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                            jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                            zipEntryA = jVar.a("patchcfg");
                                                                            gameUtil2 = gameUtil;
                                                                            cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                            cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                            boolean zIsBigAsset5 = BwbxUtil.isBigAsset(q.d);
                                                                            StringBuilder sb5 = new StringBuilder();
                                                                            sb5.append("needCopyMp4 = ");
                                                                            sb5.append(zIsBigAsset5);
                                                                            z7 = zIsBigAsset5;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb5.toString(), new Object[0]);
                                                                            if (zipEntryA != null) {
                                                                                bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                                z8 = z7;
                                                                                z9 = false;
                                                                                while (true) {
                                                                                    line = bufferedReader.readLine();
                                                                                    if (line != null) {
                                                                                        break;
                                                                                        break;
                                                                                    }
                                                                                    bufferedReader = bufferedReader;
                                                                                    if (line.startsWith("p:")) {
                                                                                        if (line.startsWith("px:")) {
                                                                                            z12 = z8;
                                                                                            int iIndexOf9 = line.indexOf(58) + 1;
                                                                                            String strSubstring10 = line.substring(iIndexOf9, line.indexOf(58, iIndexOf9));
                                                                                            strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                            file = new File(cachePath + strSubstring10);
                                                                                            if (file.exists()) {
                                                                                                z8 = z12;
                                                                                                z9 = true;
                                                                                            } else {
                                                                                                if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                    file.delete();
                                                                                                    z9 = true;
                                                                                                }
                                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                                    z7 = false;
                                                                                                } else {
                                                                                                    z7 = false;
                                                                                                }
                                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                                }
                                                                                            }
                                                                                        } else {
                                                                                            z12 = z8;
                                                                                        }
                                                                                        z9 = z9;
                                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                                            z7 = false;
                                                                                        } else {
                                                                                            z7 = false;
                                                                                        }
                                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                        }
                                                                                    } else {
                                                                                        z12 = z8;
                                                                                        int iIndexOf10 = line.indexOf(58) + 1;
                                                                                        String strSubstring11 = line.substring(iIndexOf10, line.indexOf(58, iIndexOf10));
                                                                                        strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                        file = new File(cachePath + strSubstring11);
                                                                                        if (file.exists()) {
                                                                                            z8 = z12;
                                                                                            z9 = true;
                                                                                        } else {
                                                                                            if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                file.delete();
                                                                                                z9 = true;
                                                                                            } else {
                                                                                                z9 = z9;
                                                                                            }
                                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                                z7 = false;
                                                                                            } else {
                                                                                                z7 = false;
                                                                                            }
                                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    str3 = "finally";
                                                                                    exc = e;
                                                                                    r4 = r5;
                                                                                    q.f(q.b(q.d, exc));
                                                                                    exc.printStackTrace();
                                                                                    z3 = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                                    r3 = r4;
                                                                                    boolean unused211117 = q.F = z3;
                                                                                    boolean unused211118 = q.I = z3;
                                                                                    boolean unused211119 = q.J = z3;
                                                                                    q.ad.removeCallbacks(q.ag);
                                                                                    q.z.b(r3);
                                                                                    return;
                                                                                }
                                                                                z10 = z8;
                                                                                z11 = z9;
                                                                            } else {
                                                                                z10 = z7;
                                                                                z11 = false;
                                                                            }
                                                                            if (z7) {
                                                                                file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                                file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                                if (file4.exists()) {
                                                                                    file5.delete();
                                                                                    String str8 = "reSuccess = " + file4.renameTo(file5);
                                                                                    z13 = false;
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str8, new Object[0]);
                                                                                }
                                                                            }
                                                                            if (z10) {
                                                                                file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                                if (file2.exists()) {
                                                                                    file3.delete();
                                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                                }
                                                                            }
                                                                            if (z11) {
                                                                                ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                            }
                                                                            jVar.a();
                                                                            if (zB) {
                                                                                new File(cachePath).delete();
                                                                                q.o.clear();
                                                                                int unused2216 = q.p = 0;
                                                                                String unused2217 = q.q = null;
                                                                                String unused2218 = q.r = null;
                                                                                gameUtil2.clearChecked();
                                                                                r5 = this;
                                                                                new File(str).delete();
                                                                                gameUtil2.clearResPatchInfo();
                                                                                q.b(1, (Bundle) null);
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                                boolean unused2219 = q.F = false;
                                                                                boolean unused315 = q.I = false;
                                                                                boolean unused316 = q.J = false;
                                                                                q.ad.removeCallbacks(q.ag);
                                                                                q.z.b(r5);
                                                                                return;
                                                                            }
                                                                            anonymousClass9 = this;
                                                                            str3 = "finally";
                                                                            z6 = false;
                                                                            str3 = "finally";
                                                                            exc = e;
                                                                            r4 = r5;
                                                                            q.f(q.b(q.d, exc));
                                                                            exc.printStackTrace();
                                                                            z3 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                            r3 = r4;
                                                                            boolean unused2111110 = q.F = z3;
                                                                            boolean unused2111111 = q.I = z3;
                                                                            boolean unused2111112 = q.J = z3;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(r3);
                                                                            return;
                                                                        }
                                                                        gameUtil2 = gameUtil;
                                                                        AnonymousClass7 anonymousClass14 = this;
                                                                        z6 = false;
                                                                        new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                        anonymousClass9 = anonymousClass14;
                                                                        boolean unused317 = q.I = z6;
                                                                        q.O();
                                                                        q.p(i2);
                                                                        if (i2 == 1) {
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                        } else if (i2 == 2) {
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                        } else if (i2 == 3) {
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                            if (BwbxUtil.isBigAsset(q.d)) {
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                            } else {
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                            }
                                                                            ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                            new File(str).delete();
                                                                            gameUtil2.clearResPatchInfo();
                                                                        }
                                                                        ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                        q.Z.e();
                                                                        if (i2 != 3) {
                                                                            q.c(str, i2);
                                                                        }
                                                                        q.n(100);
                                                                        q.O();
                                                                        q.r(i2);
                                                                        q.Z.e(100L);
                                                                        q.Z.i();
                                                                        Intent intent9 = new Intent();
                                                                        intent9.putExtra("action", 12);
                                                                        intent9.putExtra("vercode", String.valueOf(q.a));
                                                                        intent9.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                        GameUtil.getIntance().notifyAction(intent9);
                                                                        ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                        z3 = false;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                        r3 = anonymousClass9;
                                                                        boolean unused2111113 = q.F = z3;
                                                                        boolean unused2111114 = q.I = z3;
                                                                        boolean unused2111115 = q.J = z3;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        q.z.b(r3);
                                                                        return;
                                                                        th = th;
                                                                        i3 = 0;
                                                                        r2 = r5;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                        boolean unused11111 = q.F = i3;
                                                                        boolean unused11112 = q.I = i3;
                                                                        boolean unused11113 = q.J = i3;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        q.z.b(r2);
                                                                        throw th;
                                                                    }
                                                                    intance = gameUtil;
                                                                }
                                                            } else {
                                                                str2 = "splitZip";
                                                                q.Z.d(i2);
                                                                j4 = j5;
                                                                q.Z.a(strB, str, j4);
                                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                j5 = j4;
                                                            }
                                                        } catch (Exception e14) {
                                                            exc3 = e14;
                                                            str2 = "splitZip";
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                            q.b(exc3);
                                                            if (q.c) {
                                                                i4--;
                                                                q.c = false;
                                                                i5 = q.p;
                                                                i6 = i2;
                                                            } else {
                                                                if (i4 % 2 == 0) {
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    do {
                                                                        int unused318 = q.p = q.v() % 3;
                                                                    } while (q.o.contains(Integer.valueOf(q.p)));
                                                                    ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                                    i5 = q.p;
                                                                    i6 = i2;
                                                                }
                                                                i7 = i4;
                                                                if (q.b) {
                                                                    i4 = i7;
                                                                    exc2 = exc3;
                                                                    j3 = j5;
                                                                    if (i4 > 5) {
                                                                        if (exc2 == null) {
                                                                            throw exc2;
                                                                        }
                                                                        Exception exc8 = new Exception();
                                                                        q.b(q.E);
                                                                        throw exc8;
                                                                    }
                                                                    if (q.b) {
                                                                        z4 = false;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                        q.b = false;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                        boolean unused12116 = q.F = false;
                                                                        boolean unused12117 = q.I = false;
                                                                        boolean unused12118 = q.J = false;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        q.z.b(this);
                                                                        return;
                                                                    }
                                                                    boolean unused12119 = q.J = true;
                                                                    boolean unused121110 = q.P = true;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                        boolean unused121111 = q.R = true;
                                                                        Intent intent10 = new Intent();
                                                                        intent10.putExtra("action", 16);
                                                                        intent10.putExtra("speed", q.Q[0]);
                                                                        intent10.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                        intent10.putExtra("speedBk", q.Q[1]);
                                                                        intent10.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                        GameUtil.getIntance().notifyAction(intent10);
                                                                    }
                                                                    packageName = q.d.getPackageName();
                                                                    q.n(95);
                                                                    int iB5 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                    if (i2 == 1) {
                                                                        if (q.q == null) {
                                                                        }
                                                                        strL = q.l(i2);
                                                                        if (strL != null) {
                                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                                        } else {
                                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                                        }
                                                                        z5 = false;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                        if (z5) {
                                                                            new File(str).delete();
                                                                            if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                q.o.add(Integer.valueOf(q.p));
                                                                            }
                                                                            if (q.K < 2) {
                                                                                q.D();
                                                                                q.b(i2, bundle);
                                                                            } else {
                                                                                q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                q.O();
                                                                            }
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                            boolean unused121112 = q.F = false;
                                                                            boolean unused2111116 = q.I = false;
                                                                            boolean unused2111117 = q.J = false;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(this);
                                                                            return;
                                                                        }
                                                                    } else {
                                                                        if (q.q == null) {
                                                                        }
                                                                        strL = q.l(i2);
                                                                        if (strL != null) {
                                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                                        } else {
                                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                                        }
                                                                        z5 = false;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                        if (z5) {
                                                                            new File(str).delete();
                                                                            if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                                q.o.add(Integer.valueOf(q.p));
                                                                            }
                                                                            if (q.K < 2) {
                                                                                q.D();
                                                                                q.b(i2, bundle);
                                                                            } else {
                                                                                q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                                q.O();
                                                                            }
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                            boolean unused121113 = q.F = false;
                                                                            boolean unused2111118 = q.I = false;
                                                                            boolean unused2111119 = q.J = false;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(this);
                                                                            return;
                                                                        }
                                                                    }
                                                                    q.n(99);
                                                                    q.Z.c(100L, 0L);
                                                                    q.Z.i();
                                                                    boolean unused22110 = q.I = true;
                                                                    if (i2 == 3) {
                                                                        com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                        cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                        new File(cachePath).mkdirs();
                                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                                            q.R();
                                                                        }
                                                                        zB = q.b(bundle, q.d, str);
                                                                        q.ae.set(true);
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                        jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                        zipEntryA = jVar.a("patchcfg");
                                                                        gameUtil2 = gameUtil;
                                                                        cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                        cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                        boolean zIsBigAsset6 = BwbxUtil.isBigAsset(q.d);
                                                                        StringBuilder sb6 = new StringBuilder();
                                                                        sb6.append("needCopyMp4 = ");
                                                                        sb6.append(zIsBigAsset6);
                                                                        z7 = zIsBigAsset6;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb6.toString(), new Object[0]);
                                                                        if (zipEntryA != null) {
                                                                            bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                            z8 = z7;
                                                                            z9 = false;
                                                                            while (true) {
                                                                                line = bufferedReader.readLine();
                                                                                if (line != null) {
                                                                                    break;
                                                                                    break;
                                                                                }
                                                                                bufferedReader = bufferedReader;
                                                                                if (line.startsWith("p:")) {
                                                                                    if (line.startsWith("px:")) {
                                                                                        z12 = z8;
                                                                                        int iIndexOf11 = line.indexOf(58) + 1;
                                                                                        String strSubstring12 = line.substring(iIndexOf11, line.indexOf(58, iIndexOf11));
                                                                                        strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                        file = new File(cachePath + strSubstring12);
                                                                                        if (file.exists()) {
                                                                                            z8 = z12;
                                                                                            z9 = true;
                                                                                        } else {
                                                                                            if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                                file.delete();
                                                                                                z9 = true;
                                                                                            }
                                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                                z7 = false;
                                                                                            } else {
                                                                                                z7 = false;
                                                                                            }
                                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        z12 = z8;
                                                                                    }
                                                                                    z9 = z9;
                                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                                        z7 = false;
                                                                                    } else {
                                                                                        z7 = false;
                                                                                    }
                                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                    }
                                                                                } else {
                                                                                    z12 = z8;
                                                                                    int iIndexOf12 = line.indexOf(58) + 1;
                                                                                    String strSubstring13 = line.substring(iIndexOf12, line.indexOf(58, iIndexOf12));
                                                                                    strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                    file = new File(cachePath + strSubstring13);
                                                                                    if (file.exists()) {
                                                                                        z8 = z12;
                                                                                        z9 = true;
                                                                                    } else {
                                                                                        if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                            file.delete();
                                                                                            z9 = true;
                                                                                        } else {
                                                                                            z9 = z9;
                                                                                        }
                                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                                            z7 = false;
                                                                                        } else {
                                                                                            z7 = false;
                                                                                        }
                                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            z10 = z8;
                                                                            z11 = z9;
                                                                        } else {
                                                                            z10 = z7;
                                                                            z11 = false;
                                                                        }
                                                                        if (z7) {
                                                                            file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                            file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                            if (file4.exists()) {
                                                                                file5.delete();
                                                                                String str9 = "reSuccess = " + file4.renameTo(file5);
                                                                                z13 = false;
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str9, new Object[0]);
                                                                            }
                                                                        }
                                                                        if (z10) {
                                                                            file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                            file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                            if (file2.exists()) {
                                                                                file3.delete();
                                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                            }
                                                                        }
                                                                        if (z11) {
                                                                            ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                        }
                                                                        jVar.a();
                                                                        if (zB) {
                                                                            new File(cachePath).delete();
                                                                            q.o.clear();
                                                                            int unused22111 = q.p = 0;
                                                                            String unused22112 = q.q = null;
                                                                            String unused22113 = q.r = null;
                                                                            gameUtil2.clearChecked();
                                                                            r5 = this;
                                                                            new File(str).delete();
                                                                            gameUtil2.clearResPatchInfo();
                                                                            q.b(1, (Bundle) null);
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                            boolean unused22114 = q.F = false;
                                                                            boolean unused319 = q.I = false;
                                                                            boolean unused3110 = q.J = false;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(r5);
                                                                            return;
                                                                        }
                                                                        anonymousClass9 = this;
                                                                        str3 = "finally";
                                                                        z6 = false;
                                                                    } else {
                                                                        gameUtil2 = gameUtil;
                                                                        AnonymousClass7 anonymousClass15 = this;
                                                                        z6 = false;
                                                                        new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                        anonymousClass9 = anonymousClass15;
                                                                    }
                                                                    boolean unused3111 = q.I = z6;
                                                                    q.O();
                                                                    q.p(i2);
                                                                    if (i2 == 1) {
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                    } else if (i2 == 2) {
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                    } else if (i2 == 3) {
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                        } else {
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                        }
                                                                        ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                        new File(str).delete();
                                                                        gameUtil2.clearResPatchInfo();
                                                                    }
                                                                    ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                    q.Z.e();
                                                                    if (i2 != 3) {
                                                                        q.c(str, i2);
                                                                    }
                                                                    q.n(100);
                                                                    q.O();
                                                                    q.r(i2);
                                                                    q.Z.e(100L);
                                                                    q.Z.i();
                                                                    Intent intent11 = new Intent();
                                                                    intent11.putExtra("action", 12);
                                                                    intent11.putExtra("vercode", String.valueOf(q.a));
                                                                    intent11.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                    GameUtil.getIntance().notifyAction(intent11);
                                                                    ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                    z3 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                    r3 = anonymousClass9;
                                                                    boolean unused21111110 = q.F = z3;
                                                                    boolean unused21111111 = q.I = z3;
                                                                    boolean unused21111112 = q.J = z3;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(r3);
                                                                    return;
                                                                    th = th;
                                                                    i3 = 0;
                                                                    r2 = r5;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                    boolean unused11114 = q.F = i3;
                                                                    boolean unused11115 = q.I = i3;
                                                                    boolean unused11116 = q.J = i3;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(r2);
                                                                    throw th;
                                                                }
                                                                intance = gameUtil;
                                                            }
                                                            strB = q.b(i5, i6);
                                                            i7 = i4;
                                                            if (q.b) {
                                                                i4 = i7;
                                                                exc2 = exc3;
                                                                j3 = j5;
                                                                if (i4 > 5) {
                                                                    if (exc2 == null) {
                                                                        throw exc2;
                                                                    }
                                                                    Exception exc9 = new Exception();
                                                                    q.b(q.E);
                                                                    throw exc9;
                                                                }
                                                                if (q.b) {
                                                                    z4 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                    q.b = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                    boolean unused121114 = q.F = false;
                                                                    boolean unused121115 = q.I = false;
                                                                    boolean unused121116 = q.J = false;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(this);
                                                                    return;
                                                                }
                                                                boolean unused121117 = q.J = true;
                                                                boolean unused121118 = q.P = true;
                                                                q.ad.removeCallbacks(q.ag);
                                                                if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                    boolean unused121119 = q.R = true;
                                                                    Intent intent12 = new Intent();
                                                                    intent12.putExtra("action", 16);
                                                                    intent12.putExtra("speed", q.Q[0]);
                                                                    intent12.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                    intent12.putExtra("speedBk", q.Q[1]);
                                                                    intent12.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                    GameUtil.getIntance().notifyAction(intent12);
                                                                }
                                                                packageName = q.d.getPackageName();
                                                                q.n(95);
                                                                int iB6 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                                if (i2 == 1) {
                                                                    if (q.q == null) {
                                                                    }
                                                                    strL = q.l(i2);
                                                                    if (strL != null) {
                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                    } else {
                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                    }
                                                                    z5 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                    if (z5) {
                                                                        new File(str).delete();
                                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                            q.o.add(Integer.valueOf(q.p));
                                                                        }
                                                                        if (q.K < 2) {
                                                                            q.D();
                                                                            q.b(i2, bundle);
                                                                        } else {
                                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                            q.O();
                                                                        }
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                        boolean unused1211110 = q.F = false;
                                                                        boolean unused21111113 = q.I = false;
                                                                        boolean unused21111114 = q.J = false;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        q.z.b(this);
                                                                        return;
                                                                    }
                                                                } else {
                                                                    if (q.q == null) {
                                                                    }
                                                                    strL = q.l(i2);
                                                                    if (strL != null) {
                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                    } else {
                                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                                    }
                                                                    z5 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                    if (z5) {
                                                                        new File(str).delete();
                                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                            q.o.add(Integer.valueOf(q.p));
                                                                        }
                                                                        if (q.K < 2) {
                                                                            q.D();
                                                                            q.b(i2, bundle);
                                                                        } else {
                                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                            q.O();
                                                                        }
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                        boolean unused1211111 = q.F = false;
                                                                        boolean unused21111115 = q.I = false;
                                                                        boolean unused21111116 = q.J = false;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        q.z.b(this);
                                                                        return;
                                                                    }
                                                                }
                                                                q.n(99);
                                                                q.Z.c(100L, 0L);
                                                                q.Z.i();
                                                                boolean unused22115 = q.I = true;
                                                                if (i2 == 3) {
                                                                    com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                    cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                    new File(cachePath).mkdirs();
                                                                    if (BwbxUtil.isBigAsset(q.d)) {
                                                                        q.R();
                                                                    }
                                                                    zB = q.b(bundle, q.d, str);
                                                                    q.ae.set(true);
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                    jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                    zipEntryA = jVar.a("patchcfg");
                                                                    gameUtil2 = gameUtil;
                                                                    cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                    cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                    boolean zIsBigAsset7 = BwbxUtil.isBigAsset(q.d);
                                                                    StringBuilder sb7 = new StringBuilder();
                                                                    sb7.append("needCopyMp4 = ");
                                                                    sb7.append(zIsBigAsset7);
                                                                    z7 = zIsBigAsset7;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb7.toString(), new Object[0]);
                                                                    if (zipEntryA != null) {
                                                                        bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                        z8 = z7;
                                                                        z9 = false;
                                                                        while (true) {
                                                                            line = bufferedReader.readLine();
                                                                            if (line != null) {
                                                                                break;
                                                                                break;
                                                                            }
                                                                            bufferedReader = bufferedReader;
                                                                            if (line.startsWith("p:")) {
                                                                                if (line.startsWith("px:")) {
                                                                                    z12 = z8;
                                                                                    int iIndexOf13 = line.indexOf(58) + 1;
                                                                                    String strSubstring14 = line.substring(iIndexOf13, line.indexOf(58, iIndexOf13));
                                                                                    strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                    file = new File(cachePath + strSubstring14);
                                                                                    if (file.exists()) {
                                                                                        z8 = z12;
                                                                                        z9 = true;
                                                                                    } else {
                                                                                        if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                            file.delete();
                                                                                            z9 = true;
                                                                                        }
                                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                                            z7 = false;
                                                                                        } else {
                                                                                            z7 = false;
                                                                                        }
                                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    z12 = z8;
                                                                                }
                                                                                z9 = z9;
                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                    z7 = false;
                                                                                } else {
                                                                                    z7 = false;
                                                                                }
                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                }
                                                                            } else {
                                                                                z12 = z8;
                                                                                int iIndexOf14 = line.indexOf(58) + 1;
                                                                                String strSubstring15 = line.substring(iIndexOf14, line.indexOf(58, iIndexOf14));
                                                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                file = new File(cachePath + strSubstring15);
                                                                                if (file.exists()) {
                                                                                    z8 = z12;
                                                                                    z9 = true;
                                                                                } else {
                                                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                        file.delete();
                                                                                        z9 = true;
                                                                                    } else {
                                                                                        z9 = z9;
                                                                                    }
                                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                                        z7 = false;
                                                                                    } else {
                                                                                        z7 = false;
                                                                                    }
                                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                    }
                                                                                }
                                                                            }
                                                                            str3 = "finally";
                                                                            exc = e;
                                                                            r4 = r5;
                                                                            q.f(q.b(q.d, exc));
                                                                            exc.printStackTrace();
                                                                            z3 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                            r3 = r4;
                                                                            boolean unused21111117 = q.F = z3;
                                                                            boolean unused21111118 = q.I = z3;
                                                                            boolean unused21111119 = q.J = z3;
                                                                            q.ad.removeCallbacks(q.ag);
                                                                            q.z.b(r3);
                                                                            return;
                                                                        }
                                                                        z10 = z8;
                                                                        z11 = z9;
                                                                    } else {
                                                                        z10 = z7;
                                                                        z11 = false;
                                                                    }
                                                                    if (z7) {
                                                                        file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                        file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                        if (file4.exists()) {
                                                                            file5.delete();
                                                                            String str10 = "reSuccess = " + file4.renameTo(file5);
                                                                            z13 = false;
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str10, new Object[0]);
                                                                        }
                                                                    }
                                                                    if (z10) {
                                                                        file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                        file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                        if (file2.exists()) {
                                                                            file3.delete();
                                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                        }
                                                                    }
                                                                    if (z11) {
                                                                        ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                    }
                                                                    jVar.a();
                                                                    if (zB) {
                                                                        new File(cachePath).delete();
                                                                        q.o.clear();
                                                                        int unused22116 = q.p = 0;
                                                                        String unused22117 = q.q = null;
                                                                        String unused22118 = q.r = null;
                                                                        gameUtil2.clearChecked();
                                                                        r5 = this;
                                                                        new File(str).delete();
                                                                        gameUtil2.clearResPatchInfo();
                                                                        q.b(1, (Bundle) null);
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                        boolean unused22119 = q.F = false;
                                                                        boolean unused3112 = q.I = false;
                                                                        boolean unused3113 = q.J = false;
                                                                        q.ad.removeCallbacks(q.ag);
                                                                        q.z.b(r5);
                                                                        return;
                                                                    }
                                                                    anonymousClass9 = this;
                                                                    str3 = "finally";
                                                                    z6 = false;
                                                                    str3 = "finally";
                                                                    exc = e;
                                                                    r4 = r5;
                                                                    q.f(q.b(q.d, exc));
                                                                    exc.printStackTrace();
                                                                    z3 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                    r3 = r4;
                                                                    boolean unused211111110 = q.F = z3;
                                                                    boolean unused211111111 = q.I = z3;
                                                                    boolean unused211111112 = q.J = z3;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(r3);
                                                                    return;
                                                                }
                                                                gameUtil2 = gameUtil;
                                                                AnonymousClass7 anonymousClass16 = this;
                                                                z6 = false;
                                                                new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                anonymousClass9 = anonymousClass16;
                                                                boolean unused3114 = q.I = z6;
                                                                q.O();
                                                                q.p(i2);
                                                                if (i2 == 1) {
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                } else if (i2 == 2) {
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                                } else if (i2 == 3) {
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                    if (BwbxUtil.isBigAsset(q.d)) {
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                    } else {
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                    }
                                                                    ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                    new File(str).delete();
                                                                    gameUtil2.clearResPatchInfo();
                                                                }
                                                                ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                                q.Z.e();
                                                                if (i2 != 3) {
                                                                    q.c(str, i2);
                                                                }
                                                                q.n(100);
                                                                q.O();
                                                                q.r(i2);
                                                                q.Z.e(100L);
                                                                q.Z.i();
                                                                Intent intent13 = new Intent();
                                                                intent13.putExtra("action", 12);
                                                                intent13.putExtra("vercode", String.valueOf(q.a));
                                                                intent13.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                                GameUtil.getIntance().notifyAction(intent13);
                                                                ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                                z3 = false;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                r3 = anonymousClass9;
                                                                boolean unused211111113 = q.F = z3;
                                                                boolean unused211111114 = q.I = z3;
                                                                boolean unused211111115 = q.J = z3;
                                                                q.ad.removeCallbacks(q.ag);
                                                                q.z.b(r3);
                                                                return;
                                                                th = th;
                                                                i3 = 0;
                                                                r2 = r5;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                                boolean unused11117 = q.F = i3;
                                                                boolean unused11118 = q.I = i3;
                                                                boolean unused11119 = q.J = i3;
                                                                q.ad.removeCallbacks(q.ag);
                                                                q.z.b(r2);
                                                                throw th;
                                                            }
                                                            intance = gameUtil;
                                                        }
                                                    } else {
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "start offset : " + q.ab, new Object[0]);
                                                        if (i2 != 1) {
                                                            str2 = "splitZip";
                                                            q.Z.d(i2);
                                                            j4 = j5;
                                                            q.Z.a(strB, str, j4);
                                                            ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                            j5 = j4;
                                                        } else if (com.excelliance.lbsdk.main.p.d.b(q.d)) {
                                                            str2 = "splitZip";
                                                            q.Z.d(i2);
                                                            j4 = j5;
                                                            q.Z.a(strB, str, j4);
                                                            ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                            j5 = j4;
                                                        } else {
                                                            str2 = "splitZip";
                                                            q.Z.d(i2);
                                                            j4 = j5;
                                                            q.Z.a(strB, str, j4);
                                                            ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                            j5 = j4;
                                                        }
                                                    }
                                                } catch (Exception e15) {
                                                    e = e15;
                                                    str2 = "splitZip";
                                                    exc3 = e;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                                    q.b(exc3);
                                                    if (q.c) {
                                                        i4--;
                                                        q.c = false;
                                                        i5 = q.p;
                                                        i6 = i2;
                                                    } else {
                                                        if (i4 % 2 == 0) {
                                                            q.ad.removeCallbacks(q.ag);
                                                            do {
                                                                int unused3115 = q.p = q.v() % 3;
                                                            } while (q.o.contains(Integer.valueOf(q.p)));
                                                            ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                            i5 = q.p;
                                                            i6 = i2;
                                                        }
                                                        i7 = i4;
                                                        if (q.b) {
                                                            i4 = i7;
                                                            exc2 = exc3;
                                                            j3 = j5;
                                                            if (i4 > 5) {
                                                                if (exc2 == null) {
                                                                    throw exc2;
                                                                }
                                                                Exception exc10 = new Exception();
                                                                q.b(q.E);
                                                                throw exc10;
                                                            }
                                                            if (q.b) {
                                                                z4 = false;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                                q.b = false;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                boolean unused1211112 = q.F = false;
                                                                boolean unused1211113 = q.I = false;
                                                                boolean unused1211114 = q.J = false;
                                                                q.ad.removeCallbacks(q.ag);
                                                                q.z.b(this);
                                                                return;
                                                            }
                                                            boolean unused1211115 = q.J = true;
                                                            boolean unused1211116 = q.P = true;
                                                            q.ad.removeCallbacks(q.ag);
                                                            if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                                boolean unused1211117 = q.R = true;
                                                                Intent intent14 = new Intent();
                                                                intent14.putExtra("action", 16);
                                                                intent14.putExtra("speed", q.Q[0]);
                                                                intent14.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                                intent14.putExtra("speedBk", q.Q[1]);
                                                                intent14.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                                GameUtil.getIntance().notifyAction(intent14);
                                                            }
                                                            packageName = q.d.getPackageName();
                                                            q.n(95);
                                                            int iB7 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                            if (i2 == 1) {
                                                                if (q.q == null) {
                                                                }
                                                                strL = q.l(i2);
                                                                if (strL != null) {
                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                } else {
                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                }
                                                                z5 = false;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                if (z5) {
                                                                    new File(str).delete();
                                                                    if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                        q.o.add(Integer.valueOf(q.p));
                                                                    }
                                                                    if (q.K < 2) {
                                                                        q.D();
                                                                        q.b(i2, bundle);
                                                                    } else {
                                                                        q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                        q.O();
                                                                    }
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                    boolean unused1211118 = q.F = false;
                                                                    boolean unused211111116 = q.I = false;
                                                                    boolean unused211111117 = q.J = false;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(this);
                                                                    return;
                                                                }
                                                            } else {
                                                                if (q.q == null) {
                                                                }
                                                                strL = q.l(i2);
                                                                if (strL != null) {
                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                } else {
                                                                    new com.excelliance.lbsdk.main.s.al(str).close();
                                                                }
                                                                z5 = false;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                                if (z5) {
                                                                    new File(str).delete();
                                                                    if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                        q.o.add(Integer.valueOf(q.p));
                                                                    }
                                                                    if (q.K < 2) {
                                                                        q.D();
                                                                        q.b(i2, bundle);
                                                                    } else {
                                                                        q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                        q.O();
                                                                    }
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                    boolean unused1211119 = q.F = false;
                                                                    boolean unused211111118 = q.I = false;
                                                                    boolean unused211111119 = q.J = false;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(this);
                                                                    return;
                                                                }
                                                            }
                                                            q.n(99);
                                                            q.Z.c(100L, 0L);
                                                            q.Z.i();
                                                            boolean unused221110 = q.I = true;
                                                            if (i2 == 3) {
                                                                com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                                cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                new File(cachePath).mkdirs();
                                                                if (BwbxUtil.isBigAsset(q.d)) {
                                                                    q.R();
                                                                }
                                                                zB = q.b(bundle, q.d, str);
                                                                q.ae.set(true);
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                                jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                                zipEntryA = jVar.a("patchcfg");
                                                                gameUtil2 = gameUtil;
                                                                cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                                cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                                boolean zIsBigAsset8 = BwbxUtil.isBigAsset(q.d);
                                                                StringBuilder sb8 = new StringBuilder();
                                                                sb8.append("needCopyMp4 = ");
                                                                sb8.append(zIsBigAsset8);
                                                                z7 = zIsBigAsset8;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb8.toString(), new Object[0]);
                                                                if (zipEntryA != null) {
                                                                    bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                    z8 = z7;
                                                                    z9 = false;
                                                                    while (true) {
                                                                        line = bufferedReader.readLine();
                                                                        if (line != null) {
                                                                            break;
                                                                            break;
                                                                        }
                                                                        bufferedReader = bufferedReader;
                                                                        if (line.startsWith("p:")) {
                                                                            if (line.startsWith("px:")) {
                                                                                z12 = z8;
                                                                                int iIndexOf15 = line.indexOf(58) + 1;
                                                                                String strSubstring16 = line.substring(iIndexOf15, line.indexOf(58, iIndexOf15));
                                                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                                file = new File(cachePath + strSubstring16);
                                                                                if (file.exists()) {
                                                                                    z8 = z12;
                                                                                    z9 = true;
                                                                                } else {
                                                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                        file.delete();
                                                                                        z9 = true;
                                                                                    }
                                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                                        z7 = false;
                                                                                    } else {
                                                                                        z7 = false;
                                                                                    }
                                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                z12 = z8;
                                                                            }
                                                                            z9 = z9;
                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                z7 = false;
                                                                            } else {
                                                                                z7 = false;
                                                                            }
                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                            }
                                                                        } else {
                                                                            z12 = z8;
                                                                            int iIndexOf16 = line.indexOf(58) + 1;
                                                                            String strSubstring17 = line.substring(iIndexOf16, line.indexOf(58, iIndexOf16));
                                                                            strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                            file = new File(cachePath + strSubstring17);
                                                                            if (file.exists()) {
                                                                                z8 = z12;
                                                                                z9 = true;
                                                                            } else {
                                                                                if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                    file.delete();
                                                                                    z9 = true;
                                                                                } else {
                                                                                    z9 = z9;
                                                                                }
                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                    z7 = false;
                                                                                } else {
                                                                                    z7 = false;
                                                                                }
                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    z10 = z8;
                                                                    z11 = z9;
                                                                } else {
                                                                    z10 = z7;
                                                                    z11 = false;
                                                                }
                                                                if (z7) {
                                                                    file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                    file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                    if (file4.exists()) {
                                                                        file5.delete();
                                                                        String str11 = "reSuccess = " + file4.renameTo(file5);
                                                                        z13 = false;
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str11, new Object[0]);
                                                                    }
                                                                }
                                                                if (z10) {
                                                                    file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                    file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                    if (file2.exists()) {
                                                                        file3.delete();
                                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                    }
                                                                }
                                                                if (z11) {
                                                                    ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                                }
                                                                jVar.a();
                                                                if (zB) {
                                                                    new File(cachePath).delete();
                                                                    q.o.clear();
                                                                    int unused221111 = q.p = 0;
                                                                    String unused221112 = q.q = null;
                                                                    String unused221113 = q.r = null;
                                                                    gameUtil2.clearChecked();
                                                                    r5 = this;
                                                                    new File(str).delete();
                                                                    gameUtil2.clearResPatchInfo();
                                                                    q.b(1, (Bundle) null);
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                    boolean unused221114 = q.F = false;
                                                                    boolean unused3116 = q.I = false;
                                                                    boolean unused3117 = q.J = false;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(r5);
                                                                    return;
                                                                }
                                                                anonymousClass9 = this;
                                                                str3 = "finally";
                                                                z6 = false;
                                                            } else {
                                                                gameUtil2 = gameUtil;
                                                                AnonymousClass7 anonymousClass17 = this;
                                                                z6 = false;
                                                                new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                                anonymousClass9 = anonymousClass17;
                                                            }
                                                            boolean unused3118 = q.I = z6;
                                                            q.O();
                                                            q.p(i2);
                                                            if (i2 == 1) {
                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                            } else if (i2 == 2) {
                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                            } else if (i2 == 3) {
                                                                ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                                if (BwbxUtil.isBigAsset(q.d)) {
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                } else {
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                                }
                                                                ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                                new File(str).delete();
                                                                gameUtil2.clearResPatchInfo();
                                                            }
                                                            ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                            q.Z.e();
                                                            if (i2 != 3) {
                                                                q.c(str, i2);
                                                            }
                                                            q.n(100);
                                                            q.O();
                                                            q.r(i2);
                                                            q.Z.e(100L);
                                                            q.Z.i();
                                                            Intent intent15 = new Intent();
                                                            intent15.putExtra("action", 12);
                                                            intent15.putExtra("vercode", String.valueOf(q.a));
                                                            intent15.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                            GameUtil.getIntance().notifyAction(intent15);
                                                            ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                            z3 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                            r3 = anonymousClass9;
                                                            boolean unused2111111110 = q.F = z3;
                                                            boolean unused2111111111 = q.I = z3;
                                                            boolean unused2111111112 = q.J = z3;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(r3);
                                                            return;
                                                            th = th;
                                                            i3 = 0;
                                                            r2 = r5;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                            boolean unused111110 = q.F = i3;
                                                            boolean unused111111 = q.I = i3;
                                                            boolean unused111112 = q.J = i3;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(r2);
                                                            throw th;
                                                        }
                                                        intance = gameUtil;
                                                    }
                                                    strB = q.b(i5, i6);
                                                    i7 = i4;
                                                    if (q.b) {
                                                        i4 = i7;
                                                        exc2 = exc3;
                                                        j3 = j5;
                                                        if (i4 > 5) {
                                                            if (exc2 == null) {
                                                                throw exc2;
                                                            }
                                                            Exception exc11 = new Exception();
                                                            q.b(q.E);
                                                            throw exc11;
                                                        }
                                                        if (q.b) {
                                                            z4 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                            q.b = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                            boolean unused12111110 = q.F = false;
                                                            boolean unused12111111 = q.I = false;
                                                            boolean unused12111112 = q.J = false;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(this);
                                                            return;
                                                        }
                                                        boolean unused12111113 = q.J = true;
                                                        boolean unused12111114 = q.P = true;
                                                        q.ad.removeCallbacks(q.ag);
                                                        if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                            boolean unused12111115 = q.R = true;
                                                            Intent intent16 = new Intent();
                                                            intent16.putExtra("action", 16);
                                                            intent16.putExtra("speed", q.Q[0]);
                                                            intent16.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                            intent16.putExtra("speedBk", q.Q[1]);
                                                            intent16.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                            GameUtil.getIntance().notifyAction(intent16);
                                                        }
                                                        packageName = q.d.getPackageName();
                                                        q.n(95);
                                                        int iB8 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                        if (i2 == 1) {
                                                            if (q.q == null) {
                                                            }
                                                            strL = q.l(i2);
                                                            if (strL != null) {
                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                            } else {
                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                            }
                                                            z5 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                            if (z5) {
                                                                new File(str).delete();
                                                                if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                    q.o.add(Integer.valueOf(q.p));
                                                                }
                                                                if (q.K < 2) {
                                                                    q.D();
                                                                    q.b(i2, bundle);
                                                                } else {
                                                                    q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                    q.O();
                                                                }
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                boolean unused12111116 = q.F = false;
                                                                boolean unused2111111113 = q.I = false;
                                                                boolean unused2111111114 = q.J = false;
                                                                q.ad.removeCallbacks(q.ag);
                                                                q.z.b(this);
                                                                return;
                                                            }
                                                        } else {
                                                            if (q.q == null) {
                                                            }
                                                            strL = q.l(i2);
                                                            if (strL != null) {
                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                            } else {
                                                                new com.excelliance.lbsdk.main.s.al(str).close();
                                                            }
                                                            z5 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                            if (z5) {
                                                                new File(str).delete();
                                                                if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                    q.o.add(Integer.valueOf(q.p));
                                                                }
                                                                if (q.K < 2) {
                                                                    q.D();
                                                                    q.b(i2, bundle);
                                                                } else {
                                                                    q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                    q.O();
                                                                }
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                boolean unused12111117 = q.F = false;
                                                                boolean unused2111111115 = q.I = false;
                                                                boolean unused2111111116 = q.J = false;
                                                                q.ad.removeCallbacks(q.ag);
                                                                q.z.b(this);
                                                                return;
                                                            }
                                                        }
                                                        q.n(99);
                                                        q.Z.c(100L, 0L);
                                                        q.Z.i();
                                                        boolean unused221115 = q.I = true;
                                                        if (i2 == 3) {
                                                            com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                            cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                            new File(cachePath).mkdirs();
                                                            if (BwbxUtil.isBigAsset(q.d)) {
                                                                q.R();
                                                            }
                                                            zB = q.b(bundle, q.d, str);
                                                            q.ae.set(true);
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                            jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                            zipEntryA = jVar.a("patchcfg");
                                                            gameUtil2 = gameUtil;
                                                            cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                            cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                            boolean zIsBigAsset9 = BwbxUtil.isBigAsset(q.d);
                                                            StringBuilder sb9 = new StringBuilder();
                                                            sb9.append("needCopyMp4 = ");
                                                            sb9.append(zIsBigAsset9);
                                                            z7 = zIsBigAsset9;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb9.toString(), new Object[0]);
                                                            if (zipEntryA != null) {
                                                                bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                                z8 = z7;
                                                                z9 = false;
                                                                while (true) {
                                                                    line = bufferedReader.readLine();
                                                                    if (line != null) {
                                                                        break;
                                                                        break;
                                                                    }
                                                                    bufferedReader = bufferedReader;
                                                                    if (line.startsWith("p:")) {
                                                                        if (line.startsWith("px:")) {
                                                                            z12 = z8;
                                                                            int iIndexOf17 = line.indexOf(58) + 1;
                                                                            String strSubstring18 = line.substring(iIndexOf17, line.indexOf(58, iIndexOf17));
                                                                            strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                            file = new File(cachePath + strSubstring18);
                                                                            if (file.exists()) {
                                                                                z8 = z12;
                                                                                z9 = true;
                                                                            } else {
                                                                                if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                    file.delete();
                                                                                    z9 = true;
                                                                                }
                                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                                    z7 = false;
                                                                                } else {
                                                                                    z7 = false;
                                                                                }
                                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                                }
                                                                            }
                                                                        } else {
                                                                            z12 = z8;
                                                                        }
                                                                        z9 = z9;
                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                            z7 = false;
                                                                        } else {
                                                                            z7 = false;
                                                                        }
                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                        }
                                                                    } else {
                                                                        z12 = z8;
                                                                        int iIndexOf18 = line.indexOf(58) + 1;
                                                                        String strSubstring19 = line.substring(iIndexOf18, line.indexOf(58, iIndexOf18));
                                                                        strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                        file = new File(cachePath + strSubstring19);
                                                                        if (file.exists()) {
                                                                            z8 = z12;
                                                                            z9 = true;
                                                                        } else {
                                                                            if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                file.delete();
                                                                                z9 = true;
                                                                            } else {
                                                                                z9 = z9;
                                                                            }
                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                z7 = false;
                                                                            } else {
                                                                                z7 = false;
                                                                            }
                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                            }
                                                                        }
                                                                    }
                                                                    str3 = "finally";
                                                                    exc = e;
                                                                    r4 = r5;
                                                                    q.f(q.b(q.d, exc));
                                                                    exc.printStackTrace();
                                                                    z3 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                                    r3 = r4;
                                                                    boolean unused2111111117 = q.F = z3;
                                                                    boolean unused2111111118 = q.I = z3;
                                                                    boolean unused2111111119 = q.J = z3;
                                                                    q.ad.removeCallbacks(q.ag);
                                                                    q.z.b(r3);
                                                                    return;
                                                                }
                                                                z10 = z8;
                                                                z11 = z9;
                                                            } else {
                                                                z10 = z7;
                                                                z11 = false;
                                                            }
                                                            if (z7) {
                                                                file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                                file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                                if (file4.exists()) {
                                                                    file5.delete();
                                                                    String str12 = "reSuccess = " + file4.renameTo(file5);
                                                                    z13 = false;
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str12, new Object[0]);
                                                                }
                                                            }
                                                            if (z10) {
                                                                file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                                file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                                if (file2.exists()) {
                                                                    file3.delete();
                                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                                }
                                                            }
                                                            if (z11) {
                                                                ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                                ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                            }
                                                            jVar.a();
                                                            if (zB) {
                                                                new File(cachePath).delete();
                                                                q.o.clear();
                                                                int unused221116 = q.p = 0;
                                                                String unused221117 = q.q = null;
                                                                String unused221118 = q.r = null;
                                                                gameUtil2.clearChecked();
                                                                r5 = this;
                                                                new File(str).delete();
                                                                gameUtil2.clearResPatchInfo();
                                                                q.b(1, (Bundle) null);
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                                boolean unused221119 = q.F = false;
                                                                boolean unused3119 = q.I = false;
                                                                boolean unused31110 = q.J = false;
                                                                q.ad.removeCallbacks(q.ag);
                                                                q.z.b(r5);
                                                                return;
                                                            }
                                                            anonymousClass9 = this;
                                                            str3 = "finally";
                                                            z6 = false;
                                                            str3 = "finally";
                                                            exc = e;
                                                            r4 = r5;
                                                            q.f(q.b(q.d, exc));
                                                            exc.printStackTrace();
                                                            z3 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                            r3 = r4;
                                                            boolean unused21111111110 = q.F = z3;
                                                            boolean unused21111111111 = q.I = z3;
                                                            boolean unused21111111112 = q.J = z3;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(r3);
                                                            return;
                                                        }
                                                        gameUtil2 = gameUtil;
                                                        AnonymousClass7 anonymousClass18 = this;
                                                        z6 = false;
                                                        new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                        anonymousClass9 = anonymousClass18;
                                                        boolean unused31111 = q.I = z6;
                                                        q.O();
                                                        q.p(i2);
                                                        if (i2 == 1) {
                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                        } else if (i2 == 2) {
                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                        } else if (i2 == 3) {
                                                            ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                            if (BwbxUtil.isBigAsset(q.d)) {
                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                            } else {
                                                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                            }
                                                            ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                            new File(str).delete();
                                                            gameUtil2.clearResPatchInfo();
                                                        }
                                                        ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                        q.Z.e();
                                                        if (i2 != 3) {
                                                            q.c(str, i2);
                                                        }
                                                        q.n(100);
                                                        q.O();
                                                        q.r(i2);
                                                        q.Z.e(100L);
                                                        q.Z.i();
                                                        Intent intent17 = new Intent();
                                                        intent17.putExtra("action", 12);
                                                        intent17.putExtra("vercode", String.valueOf(q.a));
                                                        intent17.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                        GameUtil.getIntance().notifyAction(intent17);
                                                        ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                        z3 = false;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                        r3 = anonymousClass9;
                                                        boolean unused21111111113 = q.F = z3;
                                                        boolean unused21111111114 = q.I = z3;
                                                        boolean unused21111111115 = q.J = z3;
                                                        q.ad.removeCallbacks(q.ag);
                                                        q.z.b(r3);
                                                        return;
                                                        th = th;
                                                        i3 = 0;
                                                        r2 = r5;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                        boolean unused111113 = q.F = i3;
                                                        boolean unused111114 = q.I = i3;
                                                        boolean unused111115 = q.J = i3;
                                                        q.ad.removeCallbacks(q.ag);
                                                        q.z.b(r2);
                                                        throw th;
                                                    }
                                                    intance = gameUtil;
                                                }
                                            } else {
                                                gameUtil = intance;
                                                exc2 = exc3;
                                                str2 = "splitZip";
                                                i4 = 6;
                                            }
                                        } catch (Exception e16) {
                                            exc3 = e16;
                                            gameUtil = intance;
                                            str2 = "splitZip";
                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "thread error", exc3, new Object[0]);
                                            q.b(exc3);
                                            if (q.c) {
                                                i4--;
                                                q.c = false;
                                                i5 = q.p;
                                                i6 = i2;
                                            } else {
                                                if (i4 % 2 == 0) {
                                                    q.ad.removeCallbacks(q.ag);
                                                    do {
                                                        int unused31112 = q.p = q.v() % 3;
                                                    } while (q.o.contains(Integer.valueOf(q.p)));
                                                    ae.a(q.d, "lebian_module_domain", "usePathBk", q.p);
                                                    i5 = q.p;
                                                    i6 = i2;
                                                }
                                                i7 = i4;
                                                if (q.b) {
                                                    i4 = i7;
                                                    exc2 = exc3;
                                                    j3 = j5;
                                                    if (i4 > 5) {
                                                        if (exc2 == null) {
                                                            throw exc2;
                                                        }
                                                        Exception exc12 = new Exception();
                                                        q.b(q.E);
                                                        throw exc12;
                                                    }
                                                    if (q.b) {
                                                        z4 = false;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                        q.b = false;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                        boolean unused12111118 = q.F = false;
                                                        boolean unused12111119 = q.I = false;
                                                        boolean unused121111110 = q.J = false;
                                                        q.ad.removeCallbacks(q.ag);
                                                        q.z.b(this);
                                                        return;
                                                    }
                                                    boolean unused121111111 = q.J = true;
                                                    boolean unused121111112 = q.P = true;
                                                    q.ad.removeCallbacks(q.ag);
                                                    if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                        boolean unused121111113 = q.R = true;
                                                        Intent intent18 = new Intent();
                                                        intent18.putExtra("action", 16);
                                                        intent18.putExtra("speed", q.Q[0]);
                                                        intent18.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                        intent18.putExtra("speedBk", q.Q[1]);
                                                        intent18.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                        GameUtil.getIntance().notifyAction(intent18);
                                                    }
                                                    packageName = q.d.getPackageName();
                                                    q.n(95);
                                                    int iB9 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                    if (i2 == 1) {
                                                        if (q.q == null) {
                                                        }
                                                        strL = q.l(i2);
                                                        if (strL != null) {
                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                        } else {
                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                        }
                                                        z5 = false;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                        if (z5) {
                                                            new File(str).delete();
                                                            if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                q.o.add(Integer.valueOf(q.p));
                                                            }
                                                            if (q.K < 2) {
                                                                q.D();
                                                                q.b(i2, bundle);
                                                            } else {
                                                                q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                q.O();
                                                            }
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                            boolean unused121111114 = q.F = false;
                                                            boolean unused21111111116 = q.I = false;
                                                            boolean unused21111111117 = q.J = false;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(this);
                                                            return;
                                                        }
                                                    } else {
                                                        if (q.q == null) {
                                                        }
                                                        strL = q.l(i2);
                                                        if (strL != null) {
                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                        } else {
                                                            new com.excelliance.lbsdk.main.s.al(str).close();
                                                        }
                                                        z5 = false;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                        if (z5) {
                                                            new File(str).delete();
                                                            if (!q.o.contains(Integer.valueOf(q.p))) {
                                                                q.o.add(Integer.valueOf(q.p));
                                                            }
                                                            if (q.K < 2) {
                                                                q.D();
                                                                q.b(i2, bundle);
                                                            } else {
                                                                q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                                q.O();
                                                            }
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                            boolean unused121111115 = q.F = false;
                                                            boolean unused21111111118 = q.I = false;
                                                            boolean unused21111111119 = q.J = false;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(this);
                                                            return;
                                                        }
                                                    }
                                                    q.n(99);
                                                    q.Z.c(100L, 0L);
                                                    q.Z.i();
                                                    boolean unused2211110 = q.I = true;
                                                    if (i2 == 3) {
                                                        com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                        cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                        new File(cachePath).mkdirs();
                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                            q.R();
                                                        }
                                                        zB = q.b(bundle, q.d, str);
                                                        q.ae.set(true);
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                        jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                        zipEntryA = jVar.a("patchcfg");
                                                        gameUtil2 = gameUtil;
                                                        cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                        cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                        boolean zIsBigAsset10 = BwbxUtil.isBigAsset(q.d);
                                                        StringBuilder sb10 = new StringBuilder();
                                                        sb10.append("needCopyMp4 = ");
                                                        sb10.append(zIsBigAsset10);
                                                        z7 = zIsBigAsset10;
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb10.toString(), new Object[0]);
                                                        if (zipEntryA != null) {
                                                            bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                            z8 = z7;
                                                            z9 = false;
                                                            while (true) {
                                                                line = bufferedReader.readLine();
                                                                if (line != null) {
                                                                    break;
                                                                    break;
                                                                }
                                                                bufferedReader = bufferedReader;
                                                                if (line.startsWith("p:")) {
                                                                    if (line.startsWith("px:")) {
                                                                        z12 = z8;
                                                                        int iIndexOf19 = line.indexOf(58) + 1;
                                                                        String strSubstring110 = line.substring(iIndexOf19, line.indexOf(58, iIndexOf19));
                                                                        strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                        file = new File(cachePath + strSubstring110);
                                                                        if (file.exists()) {
                                                                            z8 = z12;
                                                                            z9 = true;
                                                                        } else {
                                                                            if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                                file.delete();
                                                                                z9 = true;
                                                                            }
                                                                            if (line.contains("lbres.rdata.mp4:")) {
                                                                                z7 = false;
                                                                            } else {
                                                                                z7 = false;
                                                                            }
                                                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                            }
                                                                        }
                                                                    } else {
                                                                        z12 = z8;
                                                                    }
                                                                    z9 = z9;
                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                        z7 = false;
                                                                    } else {
                                                                        z7 = false;
                                                                    }
                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                    }
                                                                } else {
                                                                    z12 = z8;
                                                                    int iIndexOf110 = line.indexOf(58) + 1;
                                                                    String strSubstring111 = line.substring(iIndexOf110, line.indexOf(58, iIndexOf110));
                                                                    strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                    file = new File(cachePath + strSubstring111);
                                                                    if (file.exists()) {
                                                                        z8 = z12;
                                                                        z9 = true;
                                                                    } else {
                                                                        if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                            file.delete();
                                                                            z9 = true;
                                                                        } else {
                                                                            z9 = z9;
                                                                        }
                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                            z7 = false;
                                                                        } else {
                                                                            z7 = false;
                                                                        }
                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            z10 = z8;
                                                            z11 = z9;
                                                        } else {
                                                            z10 = z7;
                                                            z11 = false;
                                                        }
                                                        if (z7) {
                                                            file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                            file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                            if (file4.exists()) {
                                                                file5.delete();
                                                                String str13 = "reSuccess = " + file4.renameTo(file5);
                                                                z13 = false;
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str13, new Object[0]);
                                                            }
                                                        }
                                                        if (z10) {
                                                            file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                            file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                            if (file2.exists()) {
                                                                file3.delete();
                                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                            }
                                                        }
                                                        if (z11) {
                                                            ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                            ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                        }
                                                        jVar.a();
                                                        if (zB) {
                                                            new File(cachePath).delete();
                                                            q.o.clear();
                                                            int unused2211111 = q.p = 0;
                                                            String unused2211112 = q.q = null;
                                                            String unused2211113 = q.r = null;
                                                            gameUtil2.clearChecked();
                                                            r5 = this;
                                                            new File(str).delete();
                                                            gameUtil2.clearResPatchInfo();
                                                            q.b(1, (Bundle) null);
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                            boolean unused2211114 = q.F = false;
                                                            boolean unused31113 = q.I = false;
                                                            boolean unused31114 = q.J = false;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(r5);
                                                            return;
                                                        }
                                                        anonymousClass9 = this;
                                                        str3 = "finally";
                                                        z6 = false;
                                                    } else {
                                                        gameUtil2 = gameUtil;
                                                        AnonymousClass7 anonymousClass19 = this;
                                                        z6 = false;
                                                        new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                        anonymousClass9 = anonymousClass19;
                                                    }
                                                    boolean unused31115 = q.I = z6;
                                                    q.O();
                                                    q.p(i2);
                                                    if (i2 == 1) {
                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                    } else if (i2 == 2) {
                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                    } else if (i2 == 3) {
                                                        ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                        if (BwbxUtil.isBigAsset(q.d)) {
                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                        } else {
                                                            ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                            ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                            GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                        }
                                                        ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                        new File(str).delete();
                                                        gameUtil2.clearResPatchInfo();
                                                    }
                                                    ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                    q.Z.e();
                                                    if (i2 != 3) {
                                                        q.c(str, i2);
                                                    }
                                                    q.n(100);
                                                    q.O();
                                                    q.r(i2);
                                                    q.Z.e(100L);
                                                    q.Z.i();
                                                    Intent intent19 = new Intent();
                                                    intent19.putExtra("action", 12);
                                                    intent19.putExtra("vercode", String.valueOf(q.a));
                                                    intent19.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                    GameUtil.getIntance().notifyAction(intent19);
                                                    ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                    z3 = false;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                    r3 = anonymousClass9;
                                                    boolean unused211111111110 = q.F = z3;
                                                    boolean unused211111111111 = q.I = z3;
                                                    boolean unused211111111112 = q.J = z3;
                                                    q.ad.removeCallbacks(q.ag);
                                                    q.z.b(r3);
                                                    return;
                                                    th = th;
                                                    i3 = 0;
                                                    r2 = r5;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                    boolean unused111116 = q.F = i3;
                                                    boolean unused111117 = q.I = i3;
                                                    boolean unused111118 = q.J = i3;
                                                    q.ad.removeCallbacks(q.ag);
                                                    q.z.b(r2);
                                                    throw th;
                                                }
                                                intance = gameUtil;
                                            }
                                            strB = q.b(i5, i6);
                                            i7 = i4;
                                            if (q.b) {
                                                i4 = i7;
                                                exc2 = exc3;
                                                j3 = j5;
                                                if (i4 > 5) {
                                                    if (exc2 == null) {
                                                        throw exc2;
                                                    }
                                                    Exception exc13 = new Exception();
                                                    q.b(q.E);
                                                    throw exc13;
                                                }
                                                if (q.b) {
                                                    z4 = false;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                                    q.b = false;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                    boolean unused121111116 = q.F = false;
                                                    boolean unused121111117 = q.I = false;
                                                    boolean unused121111118 = q.J = false;
                                                    q.ad.removeCallbacks(q.ag);
                                                    q.z.b(this);
                                                    return;
                                                }
                                                boolean unused121111119 = q.J = true;
                                                boolean unused1211111110 = q.P = true;
                                                q.ad.removeCallbacks(q.ag);
                                                if (GameUtil.getIntance().getNotifySwitch(9)) {
                                                    boolean unused1211111111 = q.R = true;
                                                    Intent intent110 = new Intent();
                                                    intent110.putExtra("action", 16);
                                                    intent110.putExtra("speed", q.Q[0]);
                                                    intent110.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                                    intent110.putExtra("speedBk", q.Q[1]);
                                                    intent110.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                                    GameUtil.getIntance().notifyAction(intent110);
                                                }
                                                packageName = q.d.getPackageName();
                                                q.n(95);
                                                int iB10 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                                                if (i2 == 1) {
                                                    if (q.q == null) {
                                                    }
                                                    strL = q.l(i2);
                                                    if (strL != null) {
                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                    } else {
                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                    }
                                                    z5 = false;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                    if (z5) {
                                                        new File(str).delete();
                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                            q.o.add(Integer.valueOf(q.p));
                                                        }
                                                        if (q.K < 2) {
                                                            q.D();
                                                            q.b(i2, bundle);
                                                        } else {
                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                            q.O();
                                                        }
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                        boolean unused1211111112 = q.F = false;
                                                        boolean unused211111111113 = q.I = false;
                                                        boolean unused211111111114 = q.J = false;
                                                        q.ad.removeCallbacks(q.ag);
                                                        q.z.b(this);
                                                        return;
                                                    }
                                                } else {
                                                    if (q.q == null) {
                                                    }
                                                    strL = q.l(i2);
                                                    if (strL != null) {
                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                    } else {
                                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                                    }
                                                    z5 = false;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                                    if (z5) {
                                                        new File(str).delete();
                                                        if (!q.o.contains(Integer.valueOf(q.p))) {
                                                            q.o.add(Integer.valueOf(q.p));
                                                        }
                                                        if (q.K < 2) {
                                                            q.D();
                                                            q.b(i2, bundle);
                                                        } else {
                                                            q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                                            q.O();
                                                        }
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                        boolean unused1211111113 = q.F = false;
                                                        boolean unused211111111115 = q.I = false;
                                                        boolean unused211111111116 = q.J = false;
                                                        q.ad.removeCallbacks(q.ag);
                                                        q.z.b(this);
                                                        return;
                                                    }
                                                }
                                                q.n(99);
                                                q.Z.c(100L, 0L);
                                                q.Z.i();
                                                boolean unused2211115 = q.I = true;
                                                if (i2 == 3) {
                                                    com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                                    cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                    new File(cachePath).mkdirs();
                                                    if (BwbxUtil.isBigAsset(q.d)) {
                                                        q.R();
                                                    }
                                                    zB = q.b(bundle, q.d, str);
                                                    q.ae.set(true);
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                                    jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                                    zipEntryA = jVar.a("patchcfg");
                                                    gameUtil2 = gameUtil;
                                                    cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                                    cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                                    boolean zIsBigAsset11 = BwbxUtil.isBigAsset(q.d);
                                                    StringBuilder sb11 = new StringBuilder();
                                                    sb11.append("needCopyMp4 = ");
                                                    sb11.append(zIsBigAsset11);
                                                    z7 = zIsBigAsset11;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb11.toString(), new Object[0]);
                                                    if (zipEntryA != null) {
                                                        bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                                        z8 = z7;
                                                        z9 = false;
                                                        while (true) {
                                                            line = bufferedReader.readLine();
                                                            if (line != null) {
                                                                break;
                                                                break;
                                                            }
                                                            bufferedReader = bufferedReader;
                                                            if (line.startsWith("p:")) {
                                                                if (line.startsWith("px:")) {
                                                                    z12 = z8;
                                                                    int iIndexOf111 = line.indexOf(58) + 1;
                                                                    String strSubstring112 = line.substring(iIndexOf111, line.indexOf(58, iIndexOf111));
                                                                    strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                    file = new File(cachePath + strSubstring112);
                                                                    if (file.exists()) {
                                                                        z8 = z12;
                                                                        z9 = true;
                                                                    } else {
                                                                        if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                            file.delete();
                                                                            z9 = true;
                                                                        }
                                                                        if (line.contains("lbres.rdata.mp4:")) {
                                                                            z7 = false;
                                                                        } else {
                                                                            z7 = false;
                                                                        }
                                                                        if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                        }
                                                                    }
                                                                } else {
                                                                    z12 = z8;
                                                                }
                                                                z9 = z9;
                                                                if (line.contains("lbres.rdata.mp4:")) {
                                                                    z7 = false;
                                                                } else {
                                                                    z7 = false;
                                                                }
                                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                }
                                                            } else {
                                                                z12 = z8;
                                                                int iIndexOf112 = line.indexOf(58) + 1;
                                                                String strSubstring113 = line.substring(iIndexOf112, line.indexOf(58, iIndexOf112));
                                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                                file = new File(cachePath + strSubstring113);
                                                                if (file.exists()) {
                                                                    z8 = z12;
                                                                    z9 = true;
                                                                } else {
                                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                                        file.delete();
                                                                        z9 = true;
                                                                    } else {
                                                                        z9 = z9;
                                                                    }
                                                                    if (line.contains("lbres.rdata.mp4:")) {
                                                                        z7 = false;
                                                                    } else {
                                                                        z7 = false;
                                                                    }
                                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                                    }
                                                                }
                                                            }
                                                            str3 = "finally";
                                                            exc = e;
                                                            r4 = r5;
                                                            q.f(q.b(q.d, exc));
                                                            exc.printStackTrace();
                                                            z3 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                            r3 = r4;
                                                            boolean unused211111111117 = q.F = z3;
                                                            boolean unused211111111118 = q.I = z3;
                                                            boolean unused211111111119 = q.J = z3;
                                                            q.ad.removeCallbacks(q.ag);
                                                            q.z.b(r3);
                                                            return;
                                                        }
                                                        z10 = z8;
                                                        z11 = z9;
                                                    } else {
                                                        z10 = z7;
                                                        z11 = false;
                                                    }
                                                    if (z7) {
                                                        file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                                        file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                                        if (file4.exists()) {
                                                            file5.delete();
                                                            String str14 = "reSuccess = " + file4.renameTo(file5);
                                                            z13 = false;
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str14, new Object[0]);
                                                        }
                                                    }
                                                    if (z10) {
                                                        file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                                        file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                                        if (file2.exists()) {
                                                            file3.delete();
                                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                                        }
                                                    }
                                                    if (z11) {
                                                        ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                                        ae.a(q.d, "lebian_module_dloadinfo", "success");
                                                    }
                                                    jVar.a();
                                                    if (zB) {
                                                        new File(cachePath).delete();
                                                        q.o.clear();
                                                        int unused2211116 = q.p = 0;
                                                        String unused2211117 = q.q = null;
                                                        String unused2211118 = q.r = null;
                                                        gameUtil2.clearChecked();
                                                        r5 = this;
                                                        new File(str).delete();
                                                        gameUtil2.clearResPatchInfo();
                                                        q.b(1, (Bundle) null);
                                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                                        boolean unused2211119 = q.F = false;
                                                        boolean unused31116 = q.I = false;
                                                        boolean unused31117 = q.J = false;
                                                        q.ad.removeCallbacks(q.ag);
                                                        q.z.b(r5);
                                                        return;
                                                    }
                                                    anonymousClass9 = this;
                                                    str3 = "finally";
                                                    z6 = false;
                                                    str3 = "finally";
                                                    exc = e;
                                                    r4 = r5;
                                                    q.f(q.b(q.d, exc));
                                                    exc.printStackTrace();
                                                    z3 = false;
                                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                    r3 = r4;
                                                    boolean unused2111111111110 = q.F = z3;
                                                    boolean unused2111111111111 = q.I = z3;
                                                    boolean unused2111111111112 = q.J = z3;
                                                    q.ad.removeCallbacks(q.ag);
                                                    q.z.b(r3);
                                                    return;
                                                }
                                                gameUtil2 = gameUtil;
                                                AnonymousClass7 anonymousClass110 = this;
                                                z6 = false;
                                                new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                                                anonymousClass9 = anonymousClass110;
                                                boolean unused31118 = q.I = z6;
                                                q.O();
                                                q.p(i2);
                                                if (i2 == 1) {
                                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                } else if (i2 == 2) {
                                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                                                } else if (i2 == 3) {
                                                    ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                                    if (BwbxUtil.isBigAsset(q.d)) {
                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                    } else {
                                                        ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                                        ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                                        GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                                    }
                                                    ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                                    new File(str).delete();
                                                    gameUtil2.clearResPatchInfo();
                                                }
                                                ae.a(q.d, "lebian_module_domain", "zipUrl");
                                                q.Z.e();
                                                if (i2 != 3) {
                                                    q.c(str, i2);
                                                }
                                                q.n(100);
                                                q.O();
                                                q.r(i2);
                                                q.Z.e(100L);
                                                q.Z.i();
                                                Intent intent111 = new Intent();
                                                intent111.putExtra("action", 12);
                                                intent111.putExtra("vercode", String.valueOf(q.a));
                                                intent111.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                                                GameUtil.getIntance().notifyAction(intent111);
                                                ae.a(q.d, "lebian_module_domain", "usePathBk");
                                                z3 = false;
                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                                r3 = anonymousClass9;
                                                boolean unused2111111111113 = q.F = z3;
                                                boolean unused2111111111114 = q.I = z3;
                                                boolean unused2111111111115 = q.J = z3;
                                                q.ad.removeCallbacks(q.ag);
                                                q.z.b(r3);
                                                return;
                                                th = th;
                                                i3 = 0;
                                                r2 = r5;
                                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                                                boolean unused111119 = q.F = i3;
                                                boolean unused1111110 = q.I = i3;
                                                boolean unused1111111 = q.J = i3;
                                                q.ad.removeCallbacks(q.ag);
                                                q.z.b(r2);
                                                throw th;
                                            }
                                            intance = gameUtil;
                                        }
                                    } else {
                                        q.ae.set(false);
                                        Bundle bundle3 = new Bundle();
                                        exc2 = exc3;
                                        gameUtil = intance;
                                        bundle3.putString("title", q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_dl_status_downloading", "string", q.d.getPackageName())));
                                        bundle3.putInt("show_level", 1);
                                        q.a(bundle3);
                                        if (q.ab <= 0) {
                                            long unused40 = q.ab = ae.b(q.d, "lebian_module_dloadinfo", "bwbxOffset_" + q.a, 0L);
                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "start offset : " + q.ab, new Object[0]);
                                            if (i2 != 1) {
                                                str2 = "splitZip";
                                                q.Z.d(i2);
                                                j4 = j5;
                                                q.Z.a(strB, str, j4);
                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                j5 = j4;
                                            } else if (com.excelliance.lbsdk.main.p.d.b(q.d)) {
                                                str2 = "splitZip";
                                                q.Z.d(i2);
                                                j4 = j5;
                                                q.Z.a(strB, str, j4);
                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                j5 = j4;
                                            } else {
                                                str2 = "splitZip";
                                                q.Z.d(i2);
                                                j4 = j5;
                                                q.Z.a(strB, str, j4);
                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                j5 = j4;
                                            }
                                        } else {
                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "start offset : " + q.ab, new Object[0]);
                                            if (i2 != 1) {
                                                str2 = "splitZip";
                                                q.Z.d(i2);
                                                j4 = j5;
                                                q.Z.a(strB, str, j4);
                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                j5 = j4;
                                            } else if (com.excelliance.lbsdk.main.p.d.b(q.d)) {
                                                str2 = "splitZip";
                                                q.Z.d(i2);
                                                j4 = j5;
                                                q.Z.a(strB, str, j4);
                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                j5 = j4;
                                            } else {
                                                str2 = "splitZip";
                                                q.Z.d(i2);
                                                j4 = j5;
                                                q.Z.a(strB, str, j4);
                                                ae.a(q.d, "lebian_module_dloadinfo", str2, 0);
                                                j5 = j4;
                                            }
                                        }
                                    }
                                } catch (Exception e17) {
                                    e = e17;
                                    gameUtil = intance;
                                }
                                j3 = j5;
                                break;
                                intance = gameUtil;
                            }
                            if (i4 > 5) {
                                if (exc2 == null) {
                                    throw exc2;
                                }
                                Exception exc14 = new Exception();
                                q.b(q.E);
                                throw exc14;
                            }
                            if (q.b) {
                                z4 = false;
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "mCancelled", new Object[0]);
                                q.b = false;
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                boolean unused1211111114 = q.F = false;
                                boolean unused1211111115 = q.I = false;
                                boolean unused1211111116 = q.J = false;
                                q.ad.removeCallbacks(q.ag);
                                q.z.b(this);
                                return;
                            }
                            boolean unused1211111117 = q.J = true;
                            boolean unused1211111118 = q.P = true;
                            q.ad.removeCallbacks(q.ag);
                            if (GameUtil.getIntance().getNotifySwitch(9) && !q.R) {
                                boolean unused1211111119 = q.R = true;
                                Intent intent112 = new Intent();
                                intent112.putExtra("action", 16);
                                intent112.putExtra("speed", q.Q[0]);
                                intent112.putExtra("address", q.k.substring(0, q.k.indexOf("/", 7)));
                                intent112.putExtra("speedBk", q.Q[1]);
                                intent112.putExtra("addressBk", q.l.substring(0, q.l.indexOf("/", 7)));
                                GameUtil.getIntance().notifyAction(intent112);
                            }
                            packageName = q.d.getPackageName();
                            q.n(95);
                            int iB11 = ae.b(q.d, "lebian_module_dloadinfo", str2, 0);
                            if (i2 == 1 || iB11 == 0) {
                                if (q.q == null && i2 == 3) {
                                    String strComputeFileMd5 = GameUtil.getIntance().computeFileMd5(str);
                                    if (strComputeFileMd5 == null || !strComputeFileMd5.equals(q.q)) {
                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "downloaded file md5 mismatch: real=" + strComputeFileMd5 + " expected=" + q.q, new Object[0]);
                                        z5 = true;
                                    } else {
                                        z5 = false;
                                    }
                                } else {
                                    strL = q.l(i2);
                                    if (strL != null || strL.length() <= 0) {
                                        new com.excelliance.lbsdk.main.s.al(str).close();
                                    } else {
                                        String strComputeFileMd6 = GameUtil.getIntance().computeFileMd5(str);
                                        if (!TextUtils.equals(strL, strComputeFileMd6)) {
                                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "downloaded file md5 mismatch: real=" + strL + " downloadFile=" + strComputeFileMd6, new Object[0]);
                                            z5 = true;
                                        }
                                    }
                                    z5 = false;
                                }
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "download check:" + z5, new Object[0]);
                                if (z5) {
                                    new File(str).delete();
                                    if (!q.o.contains(Integer.valueOf(q.p))) {
                                        q.o.add(Integer.valueOf(q.p));
                                    }
                                    if (q.K < 2) {
                                        q.D();
                                        q.b(i2, bundle);
                                    } else {
                                        q.f(q.a(q.d).getString(q.a(q.d).getIdentifier("lebian_MD5_mismatch", "string", packageName)));
                                        q.O();
                                    }
                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                    boolean unused12111111110 = q.F = false;
                                    boolean unused2111111111116 = q.I = false;
                                    boolean unused2111111111117 = q.J = false;
                                    q.ad.removeCallbacks(q.ag);
                                    q.z.b(this);
                                    return;
                                }
                            }
                            q.n(99);
                            q.Z.c(100L, 0L);
                            q.Z.i();
                            boolean unused22111110 = q.I = true;
                            if (i2 == 3) {
                                com.excelliance.lbsdk.main.bwbx.compat.a.a(q.d, GameUtil.getIntance().getLastVer(q.d));
                                cachePath = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                new File(cachePath).mkdirs();
                                if (BwbxUtil.isBigAsset(q.d)) {
                                    q.R();
                                }
                                zB = q.b(bundle, q.d, str);
                                q.ae.set(true);
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchSucc = " + zB, new Object[0]);
                                jVar = new com.excelliance.lbsdk.main.ai.j(str, j3);
                                zipEntryA = jVar.a("patchcfg");
                                gameUtil2 = gameUtil;
                                cachePath2 = GameUtil.getCachePath(q.d, gameUtil2.getResourceCfg(q.d).get("lastVer"));
                                cachePath3 = GameUtil.getCachePath(q.d, String.valueOf(q.a));
                                boolean zIsBigAsset12 = BwbxUtil.isBigAsset(q.d);
                                StringBuilder sb12 = new StringBuilder();
                                sb12.append("needCopyMp4 = ");
                                sb12.append(zIsBigAsset12);
                                z7 = zIsBigAsset12;
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", sb12.toString(), new Object[0]);
                                if (zipEntryA != null) {
                                    bufferedReader = new BufferedReader(new InputStreamReader(jVar.a(zipEntryA)));
                                    z8 = z7;
                                    z9 = false;
                                    while (true) {
                                        line = bufferedReader.readLine();
                                        if (line != null) {
                                            break;
                                            break;
                                        }
                                        bufferedReader = bufferedReader;
                                        if (line.startsWith("p:")) {
                                            if (line.startsWith("px:")) {
                                                z12 = z8;
                                                int iIndexOf113 = line.indexOf(58) + 1;
                                                String strSubstring114 = line.substring(iIndexOf113, line.indexOf(58, iIndexOf113));
                                                strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                                file = new File(cachePath + strSubstring114);
                                                if (file.exists()) {
                                                    z8 = z12;
                                                    z9 = true;
                                                } else {
                                                    if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                        file.delete();
                                                        z9 = true;
                                                    }
                                                    if (line.contains("lbres.rdata.mp4:") || line.endsWith("lbres.rdata.mp4")) {
                                                        z7 = false;
                                                    }
                                                    if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                    }
                                                }
                                            } else {
                                                z12 = z8;
                                            }
                                            z9 = z9;
                                            if (line.contains("lbres.rdata.mp4:")) {
                                                z7 = false;
                                            } else {
                                                z7 = false;
                                            }
                                            if (line.endsWith("lbres.rdata.mp4.idx")) {
                                            }
                                        } else {
                                            z12 = z8;
                                            int iIndexOf114 = line.indexOf(58) + 1;
                                            String strSubstring115 = line.substring(iIndexOf114, line.indexOf(58, iIndexOf114));
                                            strSubstring = line.substring(line.lastIndexOf(95) + 1, line.lastIndexOf(46));
                                            file = new File(cachePath + strSubstring115);
                                            if (file.exists()) {
                                                z8 = z12;
                                                z9 = true;
                                            } else {
                                                if (strSubstring.equals(gameUtil2.computeInputStreamMd5(new BufferedInputStream(new FileInputStream(file))))) {
                                                    file.delete();
                                                    z9 = true;
                                                } else {
                                                    z9 = z9;
                                                }
                                                if (line.contains("lbres.rdata.mp4:")) {
                                                    z7 = false;
                                                } else {
                                                    z7 = false;
                                                }
                                                if (line.endsWith("lbres.rdata.mp4.idx")) {
                                                }
                                            }
                                        }
                                        str3 = "finally";
                                        exc = e;
                                        r4 = r5;
                                        q.f(q.b(q.d, exc));
                                        exc.printStackTrace();
                                        z3 = false;
                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                        r3 = r4;
                                        boolean unused2111111111118 = q.F = z3;
                                        boolean unused2111111111119 = q.I = z3;
                                        boolean unused21111111111110 = q.J = z3;
                                        q.ad.removeCallbacks(q.ag);
                                        q.z.b(r3);
                                        return;
                                    }
                                    z10 = z8;
                                    z11 = z9;
                                } else {
                                    z10 = z7;
                                    z11 = false;
                                }
                                if (z7) {
                                    file4 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4");
                                    file5 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4");
                                    if (file4.exists()) {
                                        file5.delete();
                                        String str15 = "reSuccess = " + file4.renameTo(file5);
                                        z13 = false;
                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str15, new Object[0]);
                                    }
                                }
                                if (z10) {
                                    file2 = new File(cachePath2, "assets/lbres/lbres.rdata.mp4.idx");
                                    file3 = new File(cachePath3, "assets/lbres/lbres.rdata.mp4.idx");
                                    if (file2.exists()) {
                                        file3.delete();
                                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "idx reSuccess = " + file2.renameTo(file3), new Object[0]);
                                    }
                                }
                                if (z11) {
                                    ae.a(q.d, "lebian_module_bwbxinfo", "resEnough");
                                    ae.a(q.d, "lebian_module_dloadinfo", "success");
                                }
                                jVar.a();
                                if (zB) {
                                    new File(cachePath).delete();
                                    q.o.clear();
                                    int unused22111111 = q.p = 0;
                                    String unused22111112 = q.q = null;
                                    String unused22111113 = q.r = null;
                                    gameUtil2.clearChecked();
                                    r5 = this;
                                    new File(str).delete();
                                    gameUtil2.clearResPatchInfo();
                                    q.b(1, (Bundle) null);
                                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "finally", new Object[0]);
                                    boolean unused22111114 = q.F = false;
                                    boolean unused31119 = q.I = false;
                                    boolean unused311110 = q.J = false;
                                    q.ad.removeCallbacks(q.ag);
                                    q.z.b(r5);
                                    return;
                                }
                                anonymousClass9 = this;
                                str3 = "finally";
                                z6 = false;
                                str3 = "finally";
                                exc = e;
                                r4 = r5;
                                q.f(q.b(q.d, exc));
                                exc.printStackTrace();
                                z3 = false;
                                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                                r3 = r4;
                                boolean unused21111111111111 = q.F = z3;
                                boolean unused21111111111112 = q.I = z3;
                                boolean unused21111111111113 = q.J = z3;
                                q.ad.removeCallbacks(q.ag);
                                q.z.b(r3);
                                return;
                            }
                            gameUtil2 = gameUtil;
                            AnonymousClass7 anonymousClass111 = this;
                            z6 = false;
                            new File(str).renameTo(new File(str.substring(0, str.lastIndexOf("."))));
                            anonymousClass9 = anonymousClass111;
                            boolean unused311111 = q.I = z6;
                            q.O();
                            q.p(i2);
                            if (i2 == 1) {
                                ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                            } else if (i2 == 2) {
                                ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getOrigSize(false, i2));
                            } else if (i2 == 3) {
                                ae.a(q.d, "lebian_module_dloadinfo", "resPatchDmd5");
                                if (BwbxUtil.isBigAsset(q.d) || ae.b(q.d, "lebian_module_bwbxinfo", "resEnough", false)) {
                                    ae.a(q.d, "lebian_module_dloadinfo", "success", true);
                                    ae.a(q.d, "lebian_module_dloadinfo", "twloadFinished-" + GameUtil.getFullPkgMd5(q.d), true);
                                    GameUtil.setDownloadedSize(q.d, Integer.toString(q.a), GameUtil.getIntance().getTotalOrigSize(false));
                                } else {
                                    GameUtil.removeDownloadedSize(q.d, String.valueOf(q.a));
                                }
                                ae.a(q.d, "lebian_module_bwbxinfo", "resPatchVer", String.valueOf(q.a));
                                new File(str).delete();
                                gameUtil2.clearResPatchInfo();
                            }
                            ae.a(q.d, "lebian_module_domain", "zipUrl");
                            q.Z.e();
                            if (i2 != 3) {
                                q.c(str, i2);
                            }
                            q.n(100);
                            q.O();
                            q.r(i2);
                            q.Z.e(100L);
                            q.Z.i();
                            Intent intent113 = new Intent();
                            intent113.putExtra("action", 12);
                            intent113.putExtra("vercode", String.valueOf(q.a));
                            intent113.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                            GameUtil.getIntance().notifyAction(intent113);
                            ae.a(q.d, "lebian_module_domain", "usePathBk");
                            z3 = false;
                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[0]);
                            r3 = anonymousClass9;
                            boolean unused21111111111114 = q.F = z3;
                            boolean unused21111111111115 = q.I = z3;
                            boolean unused21111111111116 = q.J = z3;
                            q.ad.removeCallbacks(q.ag);
                            q.z.b(r3);
                            return;
                        } catch (Exception e18) {
                            e = e18;
                            r5 = this;
                        }
                    } catch (Throwable th10) {
                        th = th10;
                        r5 = this;
                    }
                } catch (Throwable th11) {
                    th = th11;
                }
                th = th;
                i3 = 0;
                r2 = r5;
                com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", str3, new Object[i3]);
                boolean unused1111112 = q.F = i3;
                boolean unused1111113 = q.I = i3;
                boolean unused1111114 = q.J = i3;
                q.ad.removeCallbacks(q.ag);
                q.z.b(r2);
                throw th;
            }
        }, "LBBwbxDownloader:download").start();
    }

    public static boolean a() {
        return n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(int i2, int i3) {
        if (i3 != 3) {
            StringBuffer stringBuffer = new StringBuffer(m(i2));
            stringBuffer.append(e(i3 == 2 ? "twice_load.zip" : "lbres.zip"));
            return stringBuffer.toString();
        }
        String strM = m(i2);
        StringBuffer stringBuffer2 = new StringBuffer(strM.substring(0, strM.indexOf("gameres", 8)));
        stringBuffer2.append(r);
        String string = stringBuffer2.toString();
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "res patch targetUrl=" + string, new Object[0]);
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, Exception exc) {
        int identifier = a(context).getIdentifier("lebian_dl_dl_failed", "string", context.getPackageName());
        if (exc.getMessage() != null && exc.getMessage().contains("no space")) {
            identifier = a(context).getIdentifier("lebian_space_not_enough_error", "string", context.getPackageName());
        }
        return a(context).getString(identifier);
    }

    static void b() throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        String str = GameUtil.getDataRoot(d) + "userinfo.cfg";
        File file = new File(str);
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "clearDownloadStatus", new Object[0]);
        try {
            if (!file.exists()) {
                return;
            }
            File file2 = new File(str.replace(".cfg", ".tmp"));
            StringBuilder sb = new StringBuilder();
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i2 = fileInputStream.read(bArr);
                        if (i2 <= 0) {
                            break;
                        } else {
                            sb.append(new String(bArr, 0, i2, "UTF-8"));
                        }
                    }
                    fileInputStream.close();
                    while (true) {
                        String string = sb.toString();
                        int iIndexOf = string.indexOf("<info downloadstatus=");
                        if (iIndexOf >= 0) {
                            sb.delete(iIndexOf, string.indexOf(">", iIndexOf + 1) + 2);
                        } else {
                            try {
                                fileOutputStream = new FileOutputStream(file2);
                                try {
                                    RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                                    randomAccessFile.write(sb.toString().getBytes());
                                    randomAccessFile.close();
                                    fileOutputStream.close();
                                    file2.renameTo(file);
                                    return;
                                } catch (Throwable th) {
                                    th = th;
                                    fileOutputStream.close();
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = null;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = null;
            }
        } catch (Exception unused) {
        }
    }

    static void b(int i2) throws Throwable {
        FileInputStream fileInputStream;
        StringBuilder sb;
        FileOutputStream fileOutputStream;
        String str = GameUtil.getDataRoot(d) + "userinfo.cfg";
        File file = new File(str);
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "saveDownloadStatus status = " + i2, new Object[0]);
        try {
            File file2 = new File(str.replace(".cfg", ".tmp"));
            StringBuilder sb2 = new StringBuilder();
            if (file2.exists()) {
                file2.delete();
            } else {
                file2.getParentFile().mkdirs();
            }
            file2.createNewFile();
            try {
                try {
                    if (file.exists()) {
                        try {
                            fileInputStream = new FileInputStream(file);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i3 = fileInputStream.read(bArr);
                                    if (i3 <= 0) {
                                        break;
                                    } else {
                                        sb2.append(new String(bArr, 0, i3, "UTF-8"));
                                    }
                                }
                                fileInputStream.close();
                                int iLastIndexOf = sb2.toString().lastIndexOf("</userinfo>");
                                if (iLastIndexOf > 0) {
                                    sb2.insert(iLastIndexOf, "<info downloadstatus=\"" + i2 + "\"/>\n");
                                } else {
                                    sb = new StringBuilder();
                                    sb.append("<info downloadstatus=\"");
                                    sb.append(i2);
                                    sb.append("\"/>\n");
                                }
                                fileOutputStream = new FileOutputStream(file2);
                                RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                                randomAccessFile.write(sb2.toString().getBytes());
                                randomAccessFile.close();
                                fileOutputStream.close();
                                file2.renameTo(file);
                                return;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream.close();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = null;
                        }
                    } else {
                        sb = new StringBuilder();
                        sb.append("<info downloadstatus=\"");
                        sb.append(i2);
                        sb.append("\"/>\n");
                    }
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
                    randomAccessFile2.write(sb2.toString().getBytes());
                    randomAccessFile2.close();
                    fileOutputStream.close();
                    file2.renameTo(file);
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream.close();
                    throw th;
                }
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
            }
            sb2.append(sb.toString());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i2, Bundle bundle) {
        while (o.contains(Integer.valueOf(p))) {
            try {
                int i3 = p + 1;
                p = i3;
                p = i3 % 3;
            } catch (InterruptedException e2) {
                e2.printStackTrace();
                return;
            }
        }
        ae.a(d, "lebian_module_domain", "usePathBk", p);
        int identifier = a(d).getIdentifier("lebian_Download_Error_retry", "string", d.getPackageName());
        f(identifier > 0 ? a(d).getString(identifier) : "Error, retrying");
        Thread.sleep(3000L);
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setComponent(new ComponentName(d, BGService.class.getName()));
        intent.setPackage(d.getPackageName());
        intent.putExtra("dlsType", 2);
        intent.putExtra("dlsZipType", i2);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        b(intent);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.excelliance.lbsdk.main.q$2] */
    public static void b(Context context) {
        if (d == null && context != null) {
            d = context;
        }
        new Thread("LBBwbxDownloader:updateResourceBase2") { // from class: com.excelliance.lbsdk.main.q.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (!aa.b(q.d)) {
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                q.M();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, Bundle bundle, boolean z2) throws Throwable {
        Intent intent;
        ComponentName componentName;
        GameUtil intance = GameUtil.getIntance();
        String runningVersionCode = intance.getRunningVersionCode(false, true);
        long downloadedSize = GameUtil.getDownloadedSize(d, runningVersionCode);
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "handleResPatch: reqsucc:" + bundle.getBoolean("reqSucc", false) + " rUrl:" + bundle.getString("rUrl") + " downloaded:" + downloadedSize + " getTotalOrigSize:" + intance.getTotalOrigSize(false), new Object[0]);
        boolean z3 = bundle.getBoolean("reqSucc", false);
        if (!z3 || bundle.getString("rUrl") == null || downloadedSize >= intance.getTotalOrigSize(false)) {
            if (z2 && z3) {
                intance.clearResPatchInfo();
                return;
            }
            return;
        }
        String string = bundle.getString("rDmd5");
        Long lValueOf = Long.valueOf(com.excelliance.lbsdk.main.z.c.b(bundle));
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchZip size:" + lValueOf, new Object[0]);
        boolean zExists = new File(GameUtil.getResPatchPath(context, Integer.parseInt(runningVersionCode), false)).exists();
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "resPatchZip exist:" + zExists, new Object[0]);
        if (zExists && ae.b(context, "lebian_module_dloadinfo", "resPatchDmd5", (String) null) == null) {
            intance.clearResPatchInfo();
            return;
        }
        File file = new File(GameUtil.getResPatchPath(d, Integer.parseInt(runningVersionCode), true));
        if (file.exists()) {
            String strB = ae.b(context, "lebian_module_dloadinfo", "resPatchDmd5", (String) null);
            if (strB == null) {
                file.delete();
            } else if (string != null && strB != null && !string.equals(strB)) {
                file.delete();
                F = false;
                if (F) {
                    Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
                    intent2.setPackage(d.getPackageName());
                    intent2.setComponent(new ComponentName(d, BGService.class.getName()));
                    intent2.putExtra("dlsType", 2);
                    intent2.putExtra("zipCancel", true);
                    b(intent2);
                }
            }
        }
        if (!h.f()) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "patch file loaded failed", new Object[0]);
            return;
        }
        if (lValueOf.longValue() > GameUtil.getFreeSpace(GameUtil.getResRoot(context))) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "space not enough", new Object[0]);
            return;
        }
        boolean z4 = !aa.a(d);
        if (intance.requireResZip(d) > 0) {
            bundle.putBoolean("forceDl", true);
        }
        bundle.putBoolean("dataConnect", z4);
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "rSize = " + lValueOf + " url = " + bundle.getString("rUrl"), new Object[0]);
        boolean zD = al.d(d) ^ true;
        if (al.b(d)) {
            if (zD) {
                if (!z4 && !m.a(context, "SHOW_DIALOG_RESPATCH_IN_WIFI", false)) {
                    GameUtil.switchToDownloadFullRes(d, 3, bundle);
                    return;
                }
                intent = new Intent("com.excelliance.open.action.fw");
                intent.setPackage(d.getPackageName());
                intent.setComponent(new ComponentName(d, BGService.class.getName()));
                intent.putExtra("dialogtype", 12);
            } else {
                if (F) {
                    return;
                }
                intent = new Intent("com.excelliance.open.action.bwbxdla");
                intent.setPackage(d.getPackageName());
                componentName = new ComponentName(d, BGService.class.getName());
            }
            intent.putExtra("dlsZipType", 3);
            intent.putExtras(bundle);
            b(intent);
        }
        if (z2) {
            return;
        }
        intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(d.getPackageName());
        componentName = new ComponentName(d, BGService.class.getName());
        intent.setComponent(componentName);
        intent.putExtra("dlsType", 2);
        intent.putExtra("dlsZipType", 3);
        intent.putExtras(bundle);
        b(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Intent intent) {
        GameUtil.startService(intent, d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Exception exc) throws Exception {
        if (exc.getMessage() != null) {
            if (exc.getMessage().contains("no space") || exc.getMessage().contains("ENOSPC")) {
                throw exc;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Bundle bundle, Context context, String str) {
        String strA = com.excelliance.lbsdk.main.q.b.a(bundle, context);
        String strB = com.excelliance.lbsdk.main.q.b.b(bundle, context);
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "upgradeSmallByPatchFile, old cache path(%s), new cache path(%s)", strA, strB);
        boolean zUpgradeSmallByPatchFile = NativeUtil.upgradeSmallByPatchFile(strA, strB, str, 0, 0);
        if (zUpgradeSmallByPatchFile) {
            new com.excelliance.lbsdk.main.q.b(context).a(bundle);
        }
        return zUpgradeSmallByPatchFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, int i2) {
        boolean zB = ae.b(d, "lebian_module_bwbxinfo", "checkCacheInstalled", false);
        if (i2 == 3 && zB) {
            return;
        }
        d(str.substring(0, str.lastIndexOf(".")), i2);
        q(i2);
    }

    public static boolean c() {
        return D == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, int i2) {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "extractZip type = " + i2, new Object[0]);
        GameUtil.getIntance().killThirdProcesses();
        try {
            G = true;
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "extractZip mIsBigAssetTwiceLoad :" + B, new Object[0]);
            ae.a(d, "lebian_module_dloadinfo", "bwbxZipExtracting", true);
            if (B && i2 == 2) {
                GameUtil.unzipForBigRes(d, new File(str), GameUtil.getCachePath(d, String.valueOf(a)), 3);
            } else if (i2 == 1 && (BwbxUtil.isBigAsset(d) || B)) {
                if (ae.b(d, "lebian_module_dloadinfo", "splitZip", 0) == 1) {
                    ab = ae.b(d, "lebian_module_dloadinfo", "bwbxOffset_" + a, 0L);
                    ac = ae.b(d, "lebian_module_dloadinfo", "bwbxCount_" + a, 0);
                    aa.a(new File(str));
                } else if (B) {
                    g(str);
                } else {
                    GameUtil.unzip(d, new File(str), GameUtil.getCachePath(d, String.valueOf(a)), 3);
                }
            } else if (ae.b(d, "lebian_module_dloadinfo", "splitZip", 0) == 1) {
                ab = ae.b(d, "lebian_module_dloadinfo", "bwbxOffset_" + a, 0L);
                GameUtil.unzipForSplitZip(d, new File(str), GameUtil.getCachePath(d, String.valueOf(a)), 3, false, ab);
                ae.a(d, "lebian_module_dloadinfo", "split_zip_success", true);
                ae.a(d, "lebian_module_dloadinfo", "bwbxOffset_" + a);
            } else {
                int iUnzip = GameUtil.unzip(d, new File(str), GameUtil.getCachePath(d, String.valueOf(a)), 3);
                if (i2 == 2) {
                    ae.a(d, "lebian_module_dloadinfo", "twice_entry_size", iUnzip);
                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "extractZip: twice size:" + iUnzip, new Object[0]);
                }
            }
            ae.a(d, "lebian_module_dloadinfo", "bwbxZipExtracting", false);
            ae.a(d, "lebian_module_dloadinfo", "bwbxZipUrl");
            ae.a(d, "lebian_module_dloadinfo", "splitZip");
            ae.a(d, "lebian_module_dloadinfo", "zipBigFileOffset");
            ae.a(d, "lebian_module_dloadinfo", "bwbxBigFileOffset");
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "extract end", new Object[0]);
            if (new File(GameUtil.getZipPath(d)).exists()) {
                new File(GameUtil.getZipPath(d)).delete();
            }
            G = false;
            new File(str).delete();
        } catch (Exception e2) {
            e2.printStackTrace();
            G = false;
            f(a(d).getString(a(d).getIdentifier("lebian_err_loadres_extract_fail", "string", d.getPackageName())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String e(String str) {
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
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "encodeURL=" + stringBuffer.toString(), new Object[0]);
        return stringBuffer.toString().replaceAll("\\+", "%20");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(String str) {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "publishError error=" + str, new Object[0]);
        Intent intent = new Intent(d.getPackageName() + "com.excelliance.open.downloadcomponent.error");
        intent.putExtra("error", str);
        s.a(d, intent);
    }

    private static void g(String str) throws IOException {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "merge Mp4 And Idx start", new Object[0]);
        String cachePath = GameUtil.getCachePath(d, String.valueOf(a));
        File file = new File(cachePath + "assets/lbres/lbres.rdata.mp4");
        String str2 = cachePath + "assets/lbres/lbres.rdata.mp4";
        File file2 = new File(str2 + ".merge");
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        if (file.exists()) {
            a(file, str, str2, ae.b(d, "lebian_module_dloadinfo", "bigAssetTwiceMp4Size", 0L));
        }
        if (file2.exists()) {
            file.delete();
            file2.renameTo(file);
        }
        GameUtil.getIntance().extractFile(str, cachePath, "assets/lbres/lbres.rdata.mp4.idx", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String l(int i2) {
        String str;
        String str2;
        String line = "";
        try {
            String strE = an.a().e();
            if (strE == null) {
                try {
                    InputStream inputStreamC = e.c(d);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStreamC, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    line = bufferedReader.readLine();
                    String[] strArrSplit = line.split(" ");
                    if (i2 == 2) {
                        str = strArrSplit[1];
                    } else {
                        if (i2 == 1) {
                            str = strArrSplit[0];
                        }
                        inputStreamReader.close();
                        inputStreamC.close();
                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "path=" + line, new Object[0]);
                    }
                    line = str;
                    inputStreamReader.close();
                    inputStreamC.close();
                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "path=" + line, new Object[0]);
                } catch (IOException unused) {
                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "no tryflag", new Object[0]);
                }
            } else if (strE != null && new File(strE).exists()) {
                try {
                    ZipFile zipFile = new ZipFile(strE);
                    ZipEntry entry = zipFile.getEntry("assets/tryflag");
                    if (entry != null) {
                        InputStreamReader inputStreamReader2 = new InputStreamReader(zipFile.getInputStream(entry), "UTF-8");
                        BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                        bufferedReader2.readLine();
                        bufferedReader2.readLine();
                        line = bufferedReader2.readLine();
                        String[] strArrSplit2 = line.split(" ");
                        if (i2 == 2) {
                            str2 = strArrSplit2[1];
                        } else {
                            if (i2 == 1) {
                                str2 = strArrSplit2[0];
                            }
                            inputStreamReader2.close();
                            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "path1=" + line, new Object[0]);
                        }
                        line = str2;
                        inputStreamReader2.close();
                        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "path1=" + line, new Object[0]);
                    }
                    zipFile.close();
                } catch (IOException unused2) {
                    com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "no tryflag1", new Object[0]);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return line;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String m(int i2) {
        String str;
        String str2 = k;
        String strB = null;
        if (str2 != null && !str2.contains(GameUtil.getRemoteResPathZip(d))) {
            com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "reset url", new Object[0]);
            h = null;
            i = null;
            j = null;
            k = null;
            l = null;
            m = null;
            ae.b(d, "lebian_module_domain");
        }
        if (i2 == 1) {
            if (l == null) {
                l = ae.b(d, "lebian_module_domain", "baseUrlBkZip_" + a, (String) null);
            }
            str = l;
        } else if (i2 == 2) {
            if (m == null) {
                m = ae.b(d, "lebian_module_domain", "baseUrlBkSecZip_" + a, (String) null);
            }
            str = m;
        } else {
            if (k == null) {
                k = ae.b(d, "lebian_module_domain", "baseUrlZip_" + a, (String) null);
            }
            str = k;
        }
        if (str == null || "".equals(str.trim())) {
            String strC = S().c();
            if (strC != null) {
                strB = com.excelliance.lbsdk.main.ai.i.b(strC, GameUtil.getRemoteResPathZip(d));
            }
        } else {
            strB = str;
        }
        if (strB != null) {
            return strB;
        }
        n = true;
        StringBuffer stringBuffer = new StringBuffer(GameUtil.getBkDomain(d, i2));
        stringBuffer.append(GameUtil.getRemoteResPathZip(d));
        k = stringBuffer.toString();
        String str3 = k;
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "fallback to default baseUrlZip=" + k, new Object[0]);
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n(int i2) {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "publishProgress progress:" + i2, new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.downloadcomponent.progress");
        intent.setPackage(d.getPackageName());
        intent.putExtra(BwbxUtil.ACTION_PROGRESS, i2);
        s.a(d, intent);
    }

    private static void o(int i2) {
        a(i2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void p(int i2) {
        a(i2, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void q(int i2) {
        com.excelliance.lbsdk.main.g.b.c("LBBwbxDownloader", "extractFinish", new Object[0]);
        Intent intent = new Intent(d.getPackageName() + BwbxUtil.ACTION_EXTRACT_FINISHED);
        intent.setPackage(d.getPackageName());
        s.a(d, intent);
        Intent intent2 = new Intent(d.getPackageName() + ".action.downloadcomponent.extracted");
        intent2.setPackage(d.getPackageName());
        intent2.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
        s.a(d, intent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void r(int i2) {
        if (i2 == 1 || i2 == 3) {
            GameUtil.getIntance().bwbxDownloadStatistics(2);
            ae.a(d, "lebian_module_dloadinfo", "downloadStartStatistics", false);
        }
    }

    static /* synthetic */ int v() {
        int i2 = p + 1;
        p = i2;
        return i2;
    }
}
