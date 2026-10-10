package com.excelliance.lbsdk.main;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h extends i {
    private static boolean A = false;
    private static boolean B = false;
    private static int C = 0;
    private static boolean D = false;
    private static boolean E = false;
    private static boolean F = false;
    private static String M = null;
    private static boolean N = false;
    private static boolean O = false;
    private static int P = 0;
    private static boolean Q = false;
    private static int T = 0;
    private static boolean V = false;
    private static int W = 0;
    private static int X = 0;
    private static int Y = 0;
    private static int Z = 0;
    private static ArrayList<Integer> aa = null;
    private static ArrayList<Integer> ab = null;
    private static ArrayList<Integer> ac = null;
    private static ArrayList<Integer> ad = null;
    private static boolean ae = false;
    private static volatile boolean af = false;
    private static boolean ag = false;
    private static boolean ah = false;
    private static int aj = 0;
    private static int ak = 0;
    private static boolean al = false;
    private static int am = 0;
    private static boolean ao = false;
    private static boolean aq = false;
    private static final String av;
    public static int c = 0;
    private static boolean e = true;
    private static boolean f;
    private static final Map<String, l> g;
    private static final Map<String, l> h;
    private static final Map<String, l> i;
    private static final HashSet<String> j;
    private static String k;
    private static Object l;
    private static boolean n;
    private static long[] o;
    private static boolean p;
    private static int v;
    private static Context w;
    private volatile boolean G;
    private int H;
    private String I;
    private String J;
    private int K;
    private boolean L;
    private Handler R;
    private HashMap<Integer, l> S;
    private int U;
    Notification.Builder a;
    private final AtomicLong aA;
    private AtomicLong aB;
    private int aC;
    private final Runnable aD;
    private long ai;
    private boolean an;
    private boolean ap;
    private BroadcastReceiver ar;
    private AtomicBoolean as;
    private long at;
    private final Runnable au;
    private long aw;
    private Map<Integer, Long> ax;
    private volatile boolean ay;
    private a[] az;
    Notification b;
    Thread d;
    private BGService m;
    private boolean q;
    private long r;
    private int s;
    private long t;
    private int u;
    private c x;
    private int y;
    private final int z;

    class a extends Thread {
        private File b;
        private URL c;
        private long d;
        private long e;
        private int f;
        private long g;
        private boolean h;
        private b i;
        private long j;
        private long k;
        private long l;
        private long m;

        public a(b bVar, URL url, File file, long j, long j2, long j3, int i) {
            super("DFT" + i);
            this.f = -1;
            this.h = false;
            this.i = null;
            this.j = 0L;
            this.k = 0L;
            this.l = 0L;
            this.m = 0L;
            this.c = url;
            this.b = file;
            this.d = j;
            this.e = j2;
            this.f = i;
            this.g = j3;
            this.i = bVar;
        }

        public long a() {
            return this.g;
        }

        public boolean b() {
            return this.h;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            h.this.ay = false;
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "force update:" + h.E, new Object[0]);
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "begin=" + this.d + ", start=" + (this.d + this.g) + ", end=" + this.e, new Object[0]);
            if (this.d + this.g >= this.e) {
                this.h = true;
                return;
            }
            try {
                try {
                    HttpURLConnection httpURLConnectionA = aa.a(this.c, h.w);
                    httpURLConnectionA.setConnectTimeout(60000);
                    httpURLConnectionA.setReadTimeout(60000);
                    httpURLConnectionA.setRequestProperty("Accept-Encoding", "identity");
                    long j = this.d + this.g;
                    httpURLConnectionA.setRequestProperty("Range", "bytes=" + j + "-" + (this.e - 1));
                    InputStream inputStream = httpURLConnectionA.getInputStream();
                    int i = 1024;
                    byte[] bArr = new byte[1024];
                    RandomAccessFile randomAccessFile = new RandomAccessFile(this.b, "rw");
                    randomAccessFile.seek(j);
                    while (true) {
                        if (this.l <= 0) {
                            this.l = System.currentTimeMillis();
                        }
                        if (this.j <= 0) {
                            this.j = System.currentTimeMillis();
                        }
                        int i2 = inputStream.read(bArr, 0, i);
                        if (i2 == -1 || h.this.ay || Thread.interrupted()) {
                            break;
                            break;
                            break;
                        }
                        randomAccessFile.write(bArr, 0, i2);
                        long j2 = i2;
                        this.g += j2;
                        byte[] bArr2 = bArr;
                        h.this.ax.put(Integer.valueOf(this.f), Long.valueOf(this.g));
                        this.k += j2;
                        if (!h.E) {
                            if (h.this.aB.get() > 0) {
                                if (h.this.as.get()) {
                                    this.m = 0L;
                                    this.j = 0L;
                                } else {
                                    this.m += j2;
                                    long jCurrentTimeMillis = System.currentTimeMillis() - this.j;
                                    if (this.m > h.this.aB.get() / 10) {
                                        try {
                                            Thread.sleep(100 - jCurrentTimeMillis);
                                        } catch (Exception unused) {
                                        }
                                        this.j = 0L;
                                        this.m = 0L;
                                    }
                                }
                                long jCurrentTimeMillis2 = System.currentTimeMillis() - this.l;
                                if (jCurrentTimeMillis2 >= 1000) {
                                    long j3 = (this.k * 1000) / (jCurrentTimeMillis2 * 1024);
                                    this.k = 0L;
                                    this.l = 0L;
                                }
                            }
                            long jCurrentTimeMillis3 = System.currentTimeMillis() - this.l;
                            if (h.this.aB.get() == 0 && jCurrentTimeMillis3 >= 5000) {
                                long j4 = (this.k * 1000) / jCurrentTimeMillis3;
                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "test speed speedOneSeconds:" + j4 + " byte/s", new Object[0]);
                                this.k = 0L;
                                this.j = 0L;
                                this.l = 0L;
                                h.this.aB.set(j4 > 1048576 ? j4 - 524288 : j4 / 2);
                                h.this.a(h.this.aB.get());
                            }
                        }
                        bArr = bArr2;
                        i = 1024;
                    }
                    randomAccessFile.close();
                    inputStream.close();
                    this.h = true;
                } catch (IOException e) {
                    com.excelliance.lbsdk.main.g.b.e("DownloadComponent", "print e in io:", e, new Object[0]);
                    long j5 = (e.getMessage() == null || !e.getMessage().contains("ENOSPC")) ? -1L : -2L;
                    this.g = j5;
                }
            } catch (Exception e2) {
                com.excelliance.lbsdk.main.g.b.e("DownloadComponent", "print e in e:", e2, new Object[0]);
                this.g = j5;
            }
        }
    }

    class b {
        String a;

        private b(int i) {
            String str;
            this.a = "lebian_module_dloadcom_multi_thread3";
            if (i == 1) {
                str = "lebian_module_dloadcom_multi_thread1";
            } else if (i != 2) {
                return;
            } else {
                str = "lebian_module_dloadcom_multi_thread2";
            }
            this.a = str;
        }

        public Map<Integer, Long> a() {
            Map<String, String> mapA = ae.a(h.w, this.a);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            if (mapA != null && mapA.size() > 0) {
                for (Map.Entry<String, String> entry : mapA.entrySet()) {
                    try {
                        concurrentHashMap.put(Integer.valueOf(entry.getKey()), Long.valueOf(entry.getValue()));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            return concurrentHashMap;
        }

        public void a(Map<Integer, Long> map) {
            for (Map.Entry<Integer, Long> entry : map.entrySet()) {
                ae.a(h.w, this.a, String.valueOf(entry.getKey()), entry.getValue().longValue());
            }
        }

        public void b() {
            ae.b(h.w, this.a);
        }
    }

    static {
        try {
            System.loadLibrary("applypatch");
        } catch (UnsatisfiedLinkError e2) {
            e = false;
            e2.printStackTrace();
        }
        f = false;
        g = new HashMap();
        h = new HashMap();
        i = new HashMap();
        j = new HashSet<>();
        k = null;
        l = new Object();
        n = false;
        o = new long[2];
        p = true;
        v = 1;
        A = false;
        B = false;
        C = -1;
        D = false;
        E = false;
        F = false;
        c = 0;
        M = null;
        N = false;
        O = false;
        P = 0;
        Q = false;
        T = 1;
        V = true;
        W = 0;
        X = 0;
        Y = 0;
        Z = 0;
        aa = new ArrayList<>();
        ab = new ArrayList<>();
        ac = new ArrayList<>();
        ad = new ArrayList<>();
        ae = true;
        ag = false;
        ah = false;
        aj = 0;
        ak = 0;
        al = false;
        am = 0;
        ao = false;
        aq = false;
        av = h.class.getSimpleName();
    }

    public h(c cVar, Context context) {
        super(context);
        this.q = false;
        this.r = 0L;
        this.s = 0;
        this.t = 0L;
        this.u = -1;
        this.y = 0;
        this.z = 60000;
        this.d = null;
        this.H = 0;
        this.I = null;
        this.J = null;
        this.K = 0;
        this.R = new Handler() { // from class: com.excelliance.lbsdk.main.h.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Toast toastMakeText;
                Intent intent;
                int i2 = message.what;
                if (i2 == 1) {
                    toastMakeText = Toast.makeText((Context) message.obj, h.this.N().getString(h.this.N().getIdentifier("lebian_demo_tips", "string", h.w.getPackageName())), 1);
                } else {
                    if (i2 == 2) {
                        Intent intent2 = (Intent) message.obj;
                        l lVar = (l) intent2.getSerializableExtra("detail");
                        if (h.P == 0 && lVar != null && lVar.patch != 3) {
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "PATCH_TIME_OUT retry", new Object[0]);
                            int unused = h.P = 1;
                            NativeUtil.setInterruptApplyPatch(true);
                            return;
                        }
                        if (h.P == 2) {
                            int unused2 = h.P = 3;
                            intent2.putExtra("detail", (Serializable) null);
                            h.this.a(intent2);
                            return;
                        }
                        int unused3 = h.P = 0;
                        NativeUtil.setInterruptApplyPatch(false);
                        if (message.obj != null) {
                            try {
                                intent = (Intent) message.obj;
                            } catch (Exception unused4) {
                                intent = null;
                            }
                        } else {
                            intent = null;
                        }
                        Bundle data = message.getData();
                        h.this.a(data.getString("gameId"), h.this.N().getString(h.this.N().getIdentifier("lebian_apply_patch_failed", "string", h.w.getPackageName())), 106, data.getString("dmd5"), intent);
                        h.this.a(data.getString("nmd5"));
                        return;
                    }
                    if (i2 != 3) {
                        if (i2 != 4) {
                            return;
                        }
                        Process.killProcess(Process.myPid());
                        return;
                    }
                    toastMakeText = Toast.makeText(h.w, message.obj.toString(), 1);
                }
                toastMakeText.show();
            }
        };
        this.S = new LinkedHashMap();
        this.U = -1;
        this.ap = false;
        this.as = new AtomicBoolean(false);
        this.at = 0L;
        this.au = new Runnable() { // from class: com.excelliance.lbsdk.main.h.8
            @Override // java.lang.Runnable
            public void run() {
                h.k(h.this);
                h.this.R.removeCallbacks(h.this.au);
                boolean zB = al.b(h.w);
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "mUpdateCallBack enter, bgCheck=" + h.this.y + ", top=" + zB, new Object[0]);
                if (h.this.y < 3 || zB) {
                    if (zB) {
                        h.this.y = 0;
                    }
                    h.this.R.postDelayed(h.this.au, 60000L);
                    return;
                }
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "mUpdateCallBack is not topActivity", new Object[0]);
                h.this.y = 0;
                GameUtil.getIntance().killThirdProcesses();
                List<ResolveInfo> listQueryIntentServices = h.w.getPackageManager().queryIntentServices(new Intent("com.excelliance.open.action.pus"), 64);
                if (listQueryIntentServices != null && listQueryIntentServices.size() > 0) {
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "found pus", new Object[0]);
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(h.w, "com.excelliance.lbsdk.base.AssistService"));
                    h.this.b(intent);
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                    Intent intent2 = new Intent();
                    intent2.setAction("com.excelliance.open.action.pus");
                    intent2.setPackage(h.w.getPackageName());
                    ad.a(h.w).a(0, intent2);
                }
                h.this.x.b(h.this, 1);
            }
        };
        this.aw = 0L;
        this.ay = false;
        this.aA = new AtomicLong();
        this.aB = new AtomicLong(0L);
        this.aC = 0;
        this.aD = new Runnable() { // from class: com.excelliance.lbsdk.main.h.11
            @Override // java.lang.Runnable
            public void run() {
                h.this.R.removeCallbacks(h.this.aD);
                int i2 = 1;
                if (h.W == 2) {
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "change to second bk,no need to test speed", new Object[0]);
                    boolean unused = h.n = true;
                    boolean unused2 = h.p = true;
                    return;
                }
                Map<Integer, Long> mapA = new b(i2).a();
                long jLongValue = 0;
                int i3 = 0;
                while (i3 < mapA.size()) {
                    i3++;
                    jLongValue += mapA.get(Integer.valueOf(i3)).longValue();
                }
                long j2 = jLongValue - h.this.r;
                h.this.r = jLongValue;
                h.this.t += (j2 / 1024) / 10;
                h.o[h.W] = h.this.t / ((long) h.z(h.this));
                int iD = aa.d(h.w);
                if (h.this.u == -1) {
                    h.this.u = iD;
                } else if (h.this.u != iD && iD != -1) {
                    h.this.u = iD;
                    h.this.s = 0;
                    h.this.t = 0L;
                    h.o[0] = 0;
                    h.o[1] = 0;
                }
                if (h.this.s < 6) {
                    h.this.R.postDelayed(h.this.aD, 10000L);
                    return;
                }
                int i4 = h.W;
                if (h.o[h.W] < 500) {
                    i4 = (i4 + 1) % 2;
                    if (h.o[0] != 0 && h.o[1] != 0) {
                        boolean unused3 = h.n = true;
                        boolean unused4 = h.p = false;
                        double d = h.o[1];
                        double d2 = h.o[0];
                        Double.isNaN(d2);
                        i4 = d < d2 * 1.3d ? 0 : 1;
                    }
                } else {
                    boolean unused5 = h.n = true;
                }
                if (i4 != h.W) {
                    int unused6 = h.W = i4;
                    h.this.q = true;
                    ae.a(h.w, "lebian_module_gameinfo", "usePathBk", h.W);
                }
            }
        };
        w = context;
        this.m = (BGService) context;
        this.x = cVar;
        this.L = GameUtil.getIntance().isHomeApp();
        if (!e) {
            String str = w.getApplicationInfo().dataDir + "/.lebiansdk/";
            if (n.a(str + GameUtil.MAIN_JAR, str + "lib", true)) {
                try {
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "" + str + "lib/libapplypatch.so", new Object[0]);
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append("lib/libapplypatch.so");
                    System.load(sb.toString());
                    e = true;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        NativeUtil.setWorkSpace(GameUtil.getDataRoot(w));
        this.ar = new BroadcastReceiver() { // from class: com.excelliance.lbsdk.main.h.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent.getAction().equals("com.excellience.yungame.ACTION_HIGH_SPEED")) {
                    h.this.as.set(intent.getBooleanExtra("force", false));
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "high speed switch force:" + h.this.as.get(), new Object[0]);
                }
            }
        };
    }

    static /* synthetic */ long[] A() {
        return o;
    }

    static /* synthetic */ String B() {
        return M;
    }

    static /* synthetic */ boolean C() {
        return V;
    }

    static /* synthetic */ boolean D() {
        return e;
    }

    static /* synthetic */ Map E() {
        return h;
    }

    static /* synthetic */ boolean F() {
        return N;
    }

    static /* synthetic */ boolean G() {
        return aq;
    }

    static /* synthetic */ HashSet H() {
        return j;
    }

    static /* synthetic */ boolean I() {
        return A;
    }

    static /* synthetic */ boolean J() {
        return B;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        for (l lVar : i.values()) {
            if (lVar != null) {
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMergPending start", new Object[0]);
                Intent intent = new Intent("com.excelliance.open.action.gameverchk");
                intent.setPackage(M());
                intent.setComponent(new ComponentName(w, BGService.class.getName()));
                intent.putExtra("detail", lVar);
                intent.putExtra("autodl", lVar.autodl);
                intent.putExtra("ISINSTALL", lVar.isInstall);
                b(intent);
            } else {
                l(5);
            }
        }
    }

    private void Q() {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "cancel lbres", new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.bwbxdla");
        intent.setPackage(M());
        intent.setComponent(new ComponentName(w, BGService.class.getName()));
        intent.putExtra("dlsType", 2);
        intent.putExtra("zipCancel", true);
        b(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    h.this.x.a(this);
                    h.this.ap = false;
                    int unused = h.am = 0;
                    for (int i2 = 0; !h.this.ap && i2 < 75; i2++) {
                        h.a(i2, false);
                        Thread.sleep(1500L);
                    }
                    h.this.x.b(this);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, "startUpgradePatch").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S() {
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    h.this.x.a(this);
                    h.this.an = false;
                    for (int i2 = h.N ? 25 : 80; !h.this.an && i2 < 100; i2++) {
                        h.a(i2, false);
                        Thread.sleep(3000L);
                    }
                    h.this.x.b(this);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, "startPreInstall").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String T() {
        String dealCommitPath = GameUtil.getIntance().getDealCommitPath();
        o oVarA = aa.a(dealCommitPath, w);
        if (oVarA == null || oVarA.a != 200 || TextUtils.isEmpty(oVarA.b)) {
            return dealCommitPath;
        }
        return (oVarA.b.startsWith("http://") || oVarA.b.startsWith("https://")) ? oVarA.b : dealCommitPath;
    }

    static /* synthetic */ int a(h hVar, int i2) {
        hVar.U = i2;
        return i2;
    }

    static /* synthetic */ long a(h hVar, long j2) {
        hVar.at = j2;
        return j2;
    }

    static /* synthetic */ Object a(h hVar, String str, String str2, String str3) {
        return hVar.a(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:59:0x0086 A[Catch: IOException -> 0x0082, TRY_LEAVE, TryCatch #6 {IOException -> 0x0082, blocks: (B:55:0x007e, B:59:0x0086), top: B:65:0x007e }] */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x007b: MOVE (r4 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:53:0x007a */
    public Object a(String str, String str2, String str3) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        ZipFile zipFile;
        BufferedReader bufferedReader2;
        Object obj;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                if (str2 == null) {
                    return null;
                }
                try {
                    zipFile = new ZipFile(str2);
                    try {
                        ZipEntry entry = zipFile.getEntry("extra.json");
                        if (entry != null) {
                            bufferedReader2 = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
                            try {
                                obj = new JSONObject(bufferedReader2.readLine()).get(str3);
                                bufferedReader3 = bufferedReader2;
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (zipFile == null) {
                                    return null;
                                }
                                zipFile.close();
                                return null;
                            } catch (JSONException e3) {
                                e = e3;
                                e.printStackTrace();
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (zipFile == null) {
                                    return null;
                                }
                                zipFile.close();
                                return null;
                            }
                        } else {
                            obj = null;
                        }
                        if (bufferedReader3 != null) {
                            try {
                                bufferedReader3.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        zipFile.close();
                        return obj;
                    } catch (IOException e5) {
                        e = e5;
                        bufferedReader2 = null;
                    } catch (JSONException e6) {
                        e = e6;
                        bufferedReader2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader3 != null) {
                            try {
                                bufferedReader3.close();
                                if (zipFile != null) {
                                    zipFile.close();
                                }
                            } catch (IOException e7) {
                                e7.printStackTrace();
                                throw th;
                            }
                        } else if (zipFile != null) {
                            zipFile.close();
                        }
                        throw th;
                    }
                } catch (IOException e8) {
                    e = e8;
                    zipFile = null;
                    bufferedReader2 = null;
                } catch (JSONException e9) {
                    e = e9;
                    zipFile = null;
                    bufferedReader2 = null;
                } catch (Throwable th3) {
                    th = th3;
                    zipFile = null;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader3 = bufferedReader;
            }
        } catch (IOException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(Context context, Exception exc) {
        Resources resourcesN = N();
        int identifier = resourcesN.getIdentifier("lebian_dl_dl_failed", "string", context.getPackageName());
        if (exc.getMessage() != null && (exc.getMessage().contains("no space") || exc.getMessage().contains("ENOSPC"))) {
            identifier = resourcesN.getIdentifier("lebian_space_not_enough_error", "string", context.getPackageName());
        }
        return resourcesN.getString(identifier);
    }

    static /* synthetic */ String a(h hVar, Context context, Exception exc) {
        return hVar.a(context, exc);
    }

    private void a(int i2, int i3, long j2, long j3, long j4, String str, long j5, String str2, String str3, boolean z, boolean z2) {
        if (i3 < j2 || i2 >= j2) {
            long j6 = j2 - j3;
            if (j6 >= j4) {
                a(i2, i3, j6, j3, j4, str, j5, str2, str3, z, z2);
                return;
            }
            return;
        }
        int iB = z2 ? (int) (j5 - ((long) ae.b(w, str, "lastNotifyDownloaded", 0))) : 0;
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "judge: in=" + j2 + " currentPercentage=" + i3 + " lastPercentage=" + i2 + "downloaded=" + j5 + " increment=" + iB, new Object[0]);
        a(str2, 3, j2, (long) iB, str3, z, z2, str, j5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3, Intent intent) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        String str;
        int i4;
        String str2;
        String str3;
        int i5;
        ae.a(w, "lebian_module_gameinfo", "isExistNotification", true);
        final String packageName = w.getPackageName();
        try {
            packageManager = w.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            } catch (PackageManager.NameNotFoundException unused) {
                applicationInfo = null;
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        String str4 = (String) packageManager.getApplicationLabel(applicationInfo);
        int identifier = Build.VERSION.SDK_INT >= 24 ? PlatformResources.getApkResources(w).getIdentifier("lebian_default_icon", "drawable", packageName) : 0;
        if (identifier == 0) {
            try {
                identifier = applicationInfo.icon;
            } catch (Exception unused3) {
            }
        }
        int i6 = identifier;
        String stringExtra = intent != null ? intent.getStringExtra("notifytitle") : null;
        String stringExtra2 = intent != null ? intent.getStringExtra("notifymsg") : null;
        boolean booleanExtra = intent.getBooleanExtra("ISINSTALL", false);
        l lVar = (l) intent.getSerializableExtra("detail");
        if (lVar != null) {
            stringExtra = lVar.notifyTitle;
            stringExtra2 = lVar.notifyMsg;
            booleanExtra = lVar.isInstall;
        }
        final NotificationManager notificationManager = (NotificationManager) w.getSystemService("notification");
        if (stringExtra2 == null || stringExtra2.length() <= 0) {
            Resources resourcesN = N();
            Resources resourcesN2 = N();
            if (i2 < 100) {
                str = "lebian_downloading_new_ver";
            } else {
                str = !booleanExtra ? "lebian_dl_status_downloaded" : "lebian_dl_status_downloaded_install";
            }
            stringExtra2 = resourcesN.getString(resourcesN2.getIdentifier(str, "string", packageName));
        }
        if (stringExtra == null || stringExtra.length() <= 0) {
            i4 = 100;
        } else {
            i4 = 100;
            if (i2 == 100) {
                str4 = stringExtra;
            }
        }
        if (i2 < i4) {
            str2 = "正在下载新版本...";
        } else if (stringExtra2 == null || stringExtra2.length() == 0) {
            str2 = !booleanExtra ? "下载完成，进入游戏?" : "下载完成，点击安装?";
        } else {
            str2 = stringExtra2;
        }
        boolean zCheckJJ = GameUtil.getIntance().checkJJ(w);
        if (this.a == null || i2 == 100) {
            Context context = w;
            if (zCheckJJ) {
                str4 = null;
            }
            this.a = GameUtil.createNotificationBuilder(context, true, i6, str4, zCheckJJ ? null : str2, null);
            if (this.a == null) {
                return;
            }
        }
        if (i2 > 0) {
            this.aC = i2;
        }
        if (i2 != 100) {
            this.a.setProgress(100, this.aC, false);
            String stringExtra3 = intent.getStringExtra("error");
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "showViewNotification: err=" + stringExtra3 + ",progress=" + i2 + ",type=" + i3, new Object[0]);
            if (stringExtra3 != null) {
                this.a.setContentText(stringExtra3);
            } else if (i3 != 2 && i2 >= 0) {
                if (this.S.size() > 1) {
                    str3 = " (" + T + "/" + this.S.size() + ")";
                } else {
                    str3 = "";
                }
                this.a.setContentText(str2 + " " + i2 + "%" + str3);
            }
            int i7 = -1;
            if (lVar != 0) {
                i7 = lVar.patch;
                i5 = lVar.expanPatch;
            } else {
                i5 = -1;
            }
            if (i2 == 99 && (i7 > 0 || i5 > 0)) {
                af = true;
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "mOrigApk:" + this.I + " mPatchPath:" + this.J + " Unziping=" + af, new Object[0]);
                String str5 = this.I;
                long length = str5 != null ? new File(str5).length() / 1048576 : 0L;
                String str6 = this.J;
                final long length2 = (((length + (str6 != null ? new File(str6).length() / 1048576 : 0L)) / 50) + 1) * 200;
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "speed:" + length2, new Object[0]);
                this.I = null;
                this.J = null;
                String string = N().getString(N().getIdentifier("lebian_dl_status_extract", "string", packageName));
                if (i3 != 2) {
                    this.a.setContentText(string);
                }
                if (i3 != 2) {
                    this.a.setProgress(100, 0, false);
                }
                this.d = new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.10
                    @Override // java.lang.Runnable
                    public void run() {
                        h.this.G = false;
                        while (!h.this.G) {
                            try {
                                h.this.H++;
                                Thread.sleep(length2);
                                if (h.this.H <= 100) {
                                    h.this.a.setContentText(h.this.N().getString(h.this.N().getIdentifier("lebian_dl_status_extract", "string", packageName)));
                                    h.this.a.setProgress(100, h.this.H, false);
                                    h.this.b = GameUtil.createNotification(h.this.a);
                                    notificationManager.notify(99, h.this.b);
                                    if (h.this.K == 100) {
                                        h.this.K = 0;
                                        h.this.m.stopForeground(true);
                                    }
                                }
                            } catch (InterruptedException unused4) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                }, "DownloadComponentService:showViewNotification99");
                this.d.start();
            }
        }
        if (i2 == 100) {
            af = false;
            this.K = 100;
            this.G = true;
            Thread thread = this.d;
            this.d = null;
            if (thread != null) {
                thread.interrupt();
            }
            this.m.stopForeground(true);
            ae.a(w, "lebian_module_gameinfo", "isExistNotification", false);
            return;
        }
        this.b = GameUtil.createNotification(this.a);
        this.b.flags |= 32;
        Notification notification = this.b;
        notification.defaults = 0;
        if (this.aC == 0) {
            this.m.startForeground(99, notification);
        } else if (notificationManager != null) {
            notificationManager.notify(99, notification);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:103:0x02c4 A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:107:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:108:0x0302 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:109:0x0304  */
    /* JADX WARN: Code duplicated, block: B:112:0x030d  */
    /* JADX WARN: Code duplicated, block: B:114:0x0316 A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:116:0x0322  */
    /* JADX WARN: Code duplicated, block: B:119:0x032e  */
    /* JADX WARN: Code duplicated, block: B:120:0x0331  */
    /* JADX WARN: Code duplicated, block: B:123:0x033d A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:126:0x0365 A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:128:0x036c  */
    /* JADX WARN: Code duplicated, block: B:131:0x0373 A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:147:0x03dd A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:148:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:150:0x03e9  */
    /* JADX WARN: Code duplicated, block: B:153:0x03ef A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:162:0x040c  */
    /* JADX WARN: Code duplicated, block: B:167:0x0425  */
    /* JADX WARN: Code duplicated, block: B:173:0x0444 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:174:0x0446  */
    /* JADX WARN: Code duplicated, block: B:181:0x045f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:182:0x0461 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:184:0x0466 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:185:0x0468 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:187:0x046b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:188:0x046d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:190:0x0472 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:191:0x0474 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:193:0x0479 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:194:0x047b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:196:0x0480  */
    /* JADX WARN: Code duplicated, block: B:198:0x0484 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:200:0x0489  */
    /* JADX WARN: Code duplicated, block: B:202:0x048d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:204:0x0492  */
    /* JADX WARN: Code duplicated, block: B:206:0x0496 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:208:0x0499  */
    /* JADX WARN: Code duplicated, block: B:210:0x049c A[Catch: all -> 0x05ea, TRY_LEAVE, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:212:0x04be  */
    /* JADX WARN: Code duplicated, block: B:218:0x04eb  */
    /* JADX WARN: Code duplicated, block: B:223:0x0508  */
    /* JADX WARN: Code duplicated, block: B:226:0x0522  */
    /* JADX WARN: Code duplicated, block: B:245:0x0593  */
    /* JADX WARN: Code duplicated, block: B:251:0x05c9 A[Catch: all -> 0x05e8, TRY_LEAVE, TryCatch #4 {all -> 0x05e8, blocks: (B:224:0x0511, B:227:0x0524, B:231:0x052a, B:233:0x0541, B:234:0x054c, B:235:0x054d, B:250:0x059b, B:251:0x05c9, B:244:0x0564, B:255:0x05e0, B:256:0x05e7), top: B:273:0x0099, inners: #3 }] */
    /* JADX WARN: Code duplicated, block: B:289:0x0348 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:290:0x0541 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:294:0x052d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:300:0x040b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:301:0x0428 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:305:0x0430 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x0209 A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0214 A[Catch: all -> 0x05ea, LOOP:2: B:80:0x020f->B:82:0x0214, LOOP_END, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:92:0x025c  */
    /* JADX WARN: Code duplicated, block: B:96:0x0263 A[Catch: all -> 0x05ea, TryCatch #6 {all -> 0x05ea, blocks: (B:4:0x000f, B:7:0x0030, B:9:0x0035, B:11:0x003b, B:13:0x0045, B:15:0x004f, B:16:0x0061, B:17:0x0064, B:19:0x009b, B:21:0x00a8, B:22:0x00b2, B:24:0x00c0, B:25:0x00c9, B:27:0x00d5, B:29:0x00e6, B:32:0x00f8, B:34:0x00fd, B:35:0x0116, B:39:0x0120, B:47:0x0145, B:51:0x0161, B:53:0x0169, B:57:0x019c, B:61:0x01ce, B:63:0x01d4, B:66:0x01df, B:67:0x01e2, B:77:0x01fe, B:79:0x0209, B:80:0x020f, B:82:0x0214, B:83:0x0224, B:85:0x0230, B:87:0x0234, B:89:0x0239, B:91:0x0243, B:94:0x025e, B:96:0x0263, B:98:0x0279, B:100:0x027f, B:102:0x02bc, B:103:0x02c4, B:105:0x02d8, B:114:0x0316, B:117:0x0328, B:121:0x0333, B:123:0x033d, B:125:0x0348, B:126:0x0365, B:129:0x036e, B:131:0x0373, B:133:0x0379, B:135:0x0383, B:137:0x0391, B:140:0x03a1, B:141:0x03b0, B:142:0x03b1, B:143:0x03ba, B:144:0x03bb, B:145:0x03d7, B:147:0x03dd, B:151:0x03ea, B:153:0x03ef, B:170:0x0430, B:156:0x03f6, B:159:0x0401, B:163:0x040e, B:169:0x0428, B:171:0x0433, B:210:0x049c, B:76:0x01f4, B:58:0x01a6, B:28:0x00dc), top: B:276:0x000f }] */
    public synchronized void a(int i2, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i3, Intent intent, int i4, int i5, int i6, boolean z2, boolean z3, long j2, String str8, boolean z4, int i7) throws Throwable {
        File file;
        boolean z5;
        URL url;
        b bVar;
        int i8;
        a aVar;
        File file2;
        long j3;
        int i9;
        int i10;
        int i11;
        b bVar2;
        Bundle bundle;
        String str9;
        boolean z6;
        String str10;
        long j4;
        long j5;
        boolean z7;
        boolean z8;
        long j6;
        boolean z9;
        int i12;
        int i13;
        int i14;
        boolean z10;
        boolean z11;
        int i15;
        String str11;
        int i16;
        int i17;
        int i18;
        int i19;
        boolean z12;
        int i20;
        int i21;
        File file3;
        int i22;
        h hVar = this;
        synchronized (this) {
            try {
                M = str;
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "download resType:" + i2, new Object[0]);
                if (hVar.az != null) {
                    for (int i23 = 0; i23 < hVar.az.length; i23++) {
                        if (hVar.az[i23] != null && !hVar.az[i23].b() && hVar.az[i23].isAlive()) {
                            hVar.az[i23].interrupt();
                            hVar.az[i23].join();
                            hVar.az[i23] = null;
                        }
                    }
                }
                hVar.az = new a[i4];
                b bVar3 = new b(i5);
                hVar.ax = new ConcurrentHashMap();
                B = false;
                URL url2 = new URL(str);
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "size:" + j2, new Object[0]);
                try {
                    if (j2 <= 0) {
                        throw new RuntimeException("moreThreadDownloadFile: Unkown file size ");
                    }
                    File file4 = new File(str2);
                    if (!file4.exists()) {
                        file4.getParentFile().mkdirs();
                        bVar3.b();
                    }
                    hVar.ax = bVar3.a();
                    if (hVar.ax.size() > 0) {
                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "has download file", new Object[0]);
                    }
                    long length = j2 % ((long) hVar.az.length) == 0 ? j2 / ((long) hVar.az.length) : (j2 / ((long) hVar.az.length)) + 1;
                    hVar.aw = 0L;
                    if (hVar.ax.size() == hVar.az.length) {
                        int i24 = 0;
                        while (i24 < hVar.az.length) {
                            i24++;
                            hVar.aw += hVar.ax.get(Integer.valueOf(i24)).longValue();
                        }
                    }
                    boolean z13 = hVar.aw == j2;
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "always downloaded:" + z13, new Object[0]);
                    boolean z14 = i2 == 2 && (!z4 || i7 == 1 || i7 == 4);
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "download isObb:" + z14, new Object[0]);
                    if (z13) {
                        return;
                    }
                    if (hVar.aw == 0) {
                        file = file4;
                        z5 = z14;
                        url = url2;
                        a(str3, 0, (Bundle) null, i3, intent);
                        bVar = bVar3;
                        aVar = null;
                        a(str3, 0L, j2, i5, i6);
                        hVar.ai = 0L;
                        if (i2 == 1 || z5) {
                            i8 = i5;
                            a(str3, 1, 0L, 0L, str7, z, false, null, 0L);
                        } else {
                            GameUtil.getIntance().bwbxDownloadStatistics(1, str8);
                            i8 = i5;
                        }
                    } else {
                        file = file4;
                        z5 = z14;
                        url = url2;
                        bVar = bVar3;
                        i8 = i5;
                        aVar = null;
                    }
                    try {
                        file2 = file;
                        try {
                            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                            j2 = j2;
                            j3 = 0;
                            if (j2 > 0) {
                                try {
                                    randomAccessFile.setLength(j2);
                                } catch (Exception e2) {
                                    e = e2;
                                    i9 = 0;
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "read file error", e, new Object[0]);
                                }
                            }
                            randomAccessFile.close();
                            i9 = 0;
                        } catch (Exception e3) {
                            e = e3;
                            j3 = 0;
                            i9 = 0;
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "read file error", e, new Object[0]);
                            if (hVar.ax.size() != hVar.az.length) {
                                hVar.ax.clear();
                                i22 = 0;
                                while (i22 < hVar.az.length) {
                                    i22++;
                                    hVar.ax.put(Integer.valueOf(i22), Long.valueOf(j3));
                                }
                            }
                            if (!GameUtil.getIntance().getNotifySwitch(8)) {
                            }
                            i10 = 0;
                            while (i10 < hVar.az.length) {
                                i20 = i10 + 1;
                                if (hVar.ax.get(Integer.valueOf(i20)).longValue() < length) {
                                    i21 = i20;
                                    file3 = file2;
                                    hVar.az[i10] = aVar;
                                } else {
                                    i21 = i20;
                                    file3 = file2;
                                    hVar.az[i10] = aVar;
                                }
                                file2 = file3;
                                j2 = j2;
                                i10 = i21;
                            }
                            i11 = 2;
                            bVar2 = bVar;
                            bVar2.a(hVar.ax);
                            bundle = new Bundle();
                            bundle.putString("version", str4);
                            bundle.putString("level", str5);
                            bundle.putString("pkg", str6);
                            str9 = "lebian_module_dloadcom_multi_thread3";
                            z6 = true;
                            if (i8 == 1) {
                                str9 = "lebian_module_dloadcom_multi_thread1";
                            } else if (i8 == 2) {
                                str9 = "lebian_module_dloadcom_multi_thread2";
                            }
                            str10 = str9;
                            if (z2) {
                                j4 = j2;
                                j5 = j4 >= 52428800 ? 100 / (j4 / 10485760) : 10L;
                                z7 = true;
                            } else {
                                j4 = j2;
                                j5 = 10;
                                z7 = false;
                            }
                            if (hVar.aw == j4) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            long j7 = 100 - j5;
                            j6 = 0;
                            long j8 = j5 + 0;
                            z9 = true;
                            i12 = 0;
                            while (z9) {
                                hVar.aw = j6;
                                bVar2.a(hVar.ax);
                                if (f) {
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "downloadFile canceled gameId " + str3, new Object[0]);
                                    break;
                                }
                                if (!hVar.q) {
                                    hVar.ay = true;
                                    throw new RuntimeException("speed is too slowly, change url");
                                }
                                i14 = 0;
                                z10 = false;
                                while (i14 < hVar.az.length) {
                                    if (hVar.az[i14] == null) {
                                    }
                                    i14++;
                                    hVar.aw += hVar.ax.get(Integer.valueOf(i14)).longValue();
                                    z6 = true;
                                }
                                if (hVar.aw == j4) {
                                    bVar2.a(hVar.ax);
                                    z11 = false;
                                } else {
                                    z11 = z10;
                                }
                                if (!z3) {
                                    while (i19 < hVar.az.length) {
                                        if (hVar.az[i19] != null) {
                                            if (!hVar.az[i19].isAlive()) {
                                                break;
                                            }
                                            if (i19 > 0) {
                                                z12 = true;
                                            } else {
                                                z12 = true;
                                            }
                                            if (z12) {
                                                hVar.az[i19].start();
                                                break;
                                            }
                                        }
                                    }
                                }
                                i15 = (int) ((hVar.aw / j4) * 100.0f * 0.9f);
                                if (i15 != i12) {
                                    if (z8) {
                                        i17 = i2;
                                    } else {
                                        i17 = i2;
                                        if (i17 == i11) {
                                            i18 = 70;
                                            if (i12 >= 90) {
                                                if (i12 >= 80) {
                                                    if (i12 < 70) {
                                                        if (i12 >= 60) {
                                                            if (i12 >= 50) {
                                                                if (i12 >= 40) {
                                                                    if (i12 >= 30) {
                                                                        if (i12 < 20) {
                                                                            i18 = 10;
                                                                            if (i12 < 10) {
                                                                                i18 = 0;
                                                                            } else {
                                                                                i18 = 0;
                                                                            }
                                                                        } else {
                                                                            i18 = 10;
                                                                            if (i12 < 10) {
                                                                                i18 = 0;
                                                                            } else {
                                                                                i18 = 0;
                                                                            }
                                                                        }
                                                                    } else if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 >= 30) {
                                                                    if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 >= 40) {
                                                                if (i12 >= 30) {
                                                                    if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 50) {
                                                            if (i12 >= 40) {
                                                                if (i12 >= 30) {
                                                                    if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 60) {
                                                        if (i12 >= 50) {
                                                            if (i12 >= 40) {
                                                                if (i12 >= 30) {
                                                                    if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 50) {
                                                        if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 70) {
                                                    if (i12 >= 60) {
                                                        if (i12 >= 50) {
                                                            if (i12 >= 40) {
                                                                if (i12 >= 30) {
                                                                    if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 50) {
                                                        if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 60) {
                                                    if (i12 >= 50) {
                                                        if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 50) {
                                                    if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 40) {
                                                    if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 30) {
                                                    if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 >= 80) {
                                                if (i12 < 70) {
                                                    if (i12 >= 60) {
                                                        if (i12 >= 50) {
                                                            if (i12 >= 40) {
                                                                if (i12 >= 30) {
                                                                    if (i12 < 20) {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    } else {
                                                                        i18 = 10;
                                                                        if (i12 < 10) {
                                                                            i18 = 0;
                                                                        } else {
                                                                            i18 = 0;
                                                                        }
                                                                    }
                                                                } else if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 50) {
                                                        if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 60) {
                                                    if (i12 >= 50) {
                                                        if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 50) {
                                                    if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 40) {
                                                    if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 30) {
                                                    if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 < 70) {
                                                if (i12 >= 60) {
                                                    if (i12 >= 50) {
                                                        if (i12 >= 40) {
                                                            if (i12 >= 30) {
                                                                if (i12 < 20) {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                } else {
                                                                    i18 = 10;
                                                                    if (i12 < 10) {
                                                                        i18 = 0;
                                                                    } else {
                                                                        i18 = 0;
                                                                    }
                                                                }
                                                            } else if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 50) {
                                                    if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 40) {
                                                    if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 30) {
                                                    if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 >= 60) {
                                                if (i12 >= 50) {
                                                    if (i12 >= 40) {
                                                        if (i12 >= 30) {
                                                            if (i12 < 20) {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            } else {
                                                                i18 = 10;
                                                                if (i12 < 10) {
                                                                    i18 = 0;
                                                                } else {
                                                                    i18 = 0;
                                                                }
                                                            }
                                                        } else if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 40) {
                                                    if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 30) {
                                                    if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 >= 50) {
                                                if (i12 >= 40) {
                                                    if (i12 >= 30) {
                                                        if (i12 < 20) {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        } else {
                                                            i18 = 10;
                                                            if (i12 < 10) {
                                                                i18 = 0;
                                                            } else {
                                                                i18 = 0;
                                                            }
                                                        }
                                                    } else if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 >= 30) {
                                                    if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 >= 40) {
                                                if (i12 >= 30) {
                                                    if (i12 < 20) {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    } else {
                                                        i18 = 10;
                                                        if (i12 < 10) {
                                                            i18 = 0;
                                                        } else {
                                                            i18 = 0;
                                                        }
                                                    }
                                                } else if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 >= 30) {
                                                if (i12 < 20) {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                } else {
                                                    i18 = 10;
                                                    if (i12 < 10) {
                                                        i18 = 0;
                                                    } else {
                                                        i18 = 0;
                                                    }
                                                }
                                            } else if (i12 < 20) {
                                                i18 = 10;
                                                if (i12 < 10) {
                                                    i18 = 0;
                                                } else {
                                                    i18 = 0;
                                                }
                                            } else {
                                                i18 = 10;
                                                if (i12 < 10) {
                                                    i18 = 0;
                                                } else {
                                                    i18 = 0;
                                                }
                                            }
                                            if (i18 > 0) {
                                                Intent intent2 = new Intent();
                                                intent2.putExtra("action", 12);
                                                intent2.putExtra("vercode", String.valueOf(str8));
                                                intent2.putExtra(BwbxUtil.ACTION_PROGRESS, i18);
                                                GameUtil.getIntance().notifyAction(intent2);
                                            }
                                        }
                                    }
                                    if (z8) {
                                        str11 = str10;
                                    } else {
                                        str11 = str10;
                                    }
                                    a(str3, i15, bundle, i3, intent);
                                    i16 = i15;
                                } else {
                                    str11 = str10;
                                    bVar2 = bVar2;
                                    bundle = bundle;
                                    i16 = i12;
                                }
                                a(str3, this.aw, j2, i5, i6);
                                if (z11) {
                                    try {
                                        Thread.sleep(200L);
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                str10 = str11;
                                j4 = j2;
                                i12 = i16;
                                hVar = this;
                                bVar2 = bVar2;
                                z9 = z11;
                                bundle = bundle;
                                j6 = 0;
                                z6 = true;
                                i11 = 2;
                            }
                            String str12 = str10;
                            if (hVar.aw == j2) {
                                i13 = i2;
                            } else {
                                i13 = i2;
                            }
                            if (i13 != 1) {
                                a(str3, 3, 100L, 0L, str7, z, false, null, 0L);
                            } else {
                                a(str3, 3, 100L, 0L, str7, z, false, null, 0L);
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                        file2 = file;
                    }
                    if (hVar.ax.size() != hVar.az.length) {
                        hVar.ax.clear();
                        i22 = 0;
                        while (i22 < hVar.az.length) {
                            i22++;
                            hVar.ax.put(Integer.valueOf(i22), Long.valueOf(j3));
                        }
                    }
                    if (!GameUtil.getIntance().getNotifySwitch(8) && !n) {
                        if (W != 2 && o[W] == j3) {
                            hVar.s = i9;
                            hVar.t = j3;
                            hVar.r = hVar.aw;
                            hVar.R.removeCallbacks(hVar.aD);
                            hVar.R.postDelayed(hVar.aD, 10000L);
                        }
                    }
                    i10 = 0;
                    while (i10 < hVar.az.length) {
                        i20 = i10 + 1;
                        if (hVar.ax.get(Integer.valueOf(i20)).longValue() < length || hVar.aw >= j2) {
                            i21 = i20;
                            file3 = file2;
                            hVar.az[i10] = aVar;
                        } else {
                            i21 = i20;
                            file3 = file2;
                            hVar.az[i10] = new a(bVar, url, file2, length * ((long) i10), Math.min(((long) i20) * length, j2), hVar.ax.get(Integer.valueOf(i20)).longValue(), i21);
                            if (z3) {
                                hVar.az[i10].start();
                            }
                        }
                        file2 = file3;
                        j2 = j2;
                        i10 = i21;
                    }
                    i11 = 2;
                    bVar2 = bVar;
                    bVar2.a(hVar.ax);
                    bundle = new Bundle();
                    bundle.putString("version", str4);
                    bundle.putString("level", str5);
                    bundle.putString("pkg", str6);
                    str9 = "lebian_module_dloadcom_multi_thread3";
                    z6 = true;
                    if (i8 == 1) {
                        str9 = "lebian_module_dloadcom_multi_thread1";
                    } else if (i8 == 2) {
                        str9 = "lebian_module_dloadcom_multi_thread2";
                    }
                    str10 = str9;
                    if (z2) {
                        j4 = j2;
                        j5 = j4 >= 52428800 ? 100 / (j4 / 10485760) : 10L;
                        z7 = true;
                    } else {
                        j4 = j2;
                        j5 = 10;
                        z7 = false;
                    }
                    if (hVar.aw == j4) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    long j9 = 100 - j5;
                    j6 = 0;
                    long j10 = j5 + 0;
                    z9 = true;
                    i12 = 0;
                    while (z9) {
                        hVar.aw = j6;
                        bVar2.a(hVar.ax);
                        if (f) {
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "downloadFile canceled gameId " + str3, new Object[0]);
                            break;
                        }
                        if (!hVar.q) {
                            hVar.ay = true;
                            throw new RuntimeException("speed is too slowly, change url");
                        }
                        i14 = 0;
                        z10 = false;
                        while (i14 < hVar.az.length) {
                            if (hVar.az[i14] == null && !hVar.az[i14].b()) {
                                if (hVar.az[i14].a() == -1) {
                                    hVar.ay = z6;
                                    throw new RuntimeException("thread error");
                                }
                                if (hVar.az[i14].a() == -2) {
                                    hVar.ay = z6;
                                    GameUtil.sendUpdateInfo(w, 5);
                                    throw new RuntimeException("no space");
                                }
                                z10 = true;
                            }
                            i14++;
                            hVar.aw += hVar.ax.get(Integer.valueOf(i14)).longValue();
                            z6 = true;
                        }
                        if (hVar.aw == j4) {
                            bVar2.a(hVar.ax);
                            z11 = false;
                        } else {
                            z11 = z10;
                        }
                        if (!z3) {
                            for (i19 = 0; i19 < hVar.az.length; i19++) {
                                if (hVar.az[i19] != null && !hVar.az[i19].b()) {
                                    if (!hVar.az[i19].isAlive()) {
                                        break;
                                        break;
                                    }
                                    if (i19 > 0 || hVar.ax.get(Integer.valueOf(i19)).longValue() == length) {
                                        z12 = true;
                                    } else {
                                        z12 = false;
                                    }
                                    if (z12) {
                                        hVar.az[i19].start();
                                        break;
                                    }
                                }
                            }
                        }
                        i15 = (int) ((hVar.aw / j4) * 100.0f * 0.9f);
                        if (i15 != i12) {
                            if (z8) {
                                i17 = i2;
                                if (i17 == i11 && !z5) {
                                    i18 = 70;
                                    if (i12 >= 90 && i15 >= 90) {
                                        i18 = 90;
                                    } else if (i12 >= 80 && i15 >= 80) {
                                        i18 = 80;
                                    } else if (i12 < 70 || i15 < 70) {
                                        if (i12 >= 60 && i15 >= 60) {
                                            i18 = 60;
                                        } else if (i12 >= 50 && i15 >= 50) {
                                            i18 = 50;
                                        } else if (i12 >= 40 && i15 >= 40) {
                                            i18 = 40;
                                        } else if (i12 >= 30 && i15 >= 30) {
                                            i18 = 30;
                                        } else if (i12 < 20 || i15 < 20) {
                                            i18 = 10;
                                            if (i12 < 10 || i15 < 10) {
                                                i18 = 0;
                                            }
                                        } else {
                                            i18 = 20;
                                        }
                                    }
                                    if (i18 > 0) {
                                        Intent intent3 = new Intent();
                                        intent3.putExtra("action", 12);
                                        intent3.putExtra("vercode", String.valueOf(str8));
                                        intent3.putExtra(BwbxUtil.ACTION_PROGRESS, i18);
                                        GameUtil.getIntance().notifyAction(intent3);
                                    }
                                }
                            } else {
                                i17 = i2;
                            }
                            if (z8 || !(i17 == 1 || z5)) {
                                str11 = str10;
                            } else {
                                try {
                                    str11 = str10;
                                    a(i12, i15, j9, j5, j10, str11, hVar.aw, str3, str7, z, z7);
                                } catch (Throwable th) {
                                    th = th;
                                    throw th;
                                }
                            }
                            a(str3, i15, bundle, i3, intent);
                            i16 = i15;
                        } else {
                            str11 = str10;
                            bVar2 = bVar2;
                            bundle = bundle;
                            i16 = i12;
                        }
                        a(str3, this.aw, j2, i5, i6);
                        if (z11) {
                            Thread.sleep(200L);
                        }
                        str10 = str11;
                        j4 = j2;
                        i12 = i16;
                        hVar = this;
                        bVar2 = bVar2;
                        z9 = z11;
                        bundle = bundle;
                        j6 = 0;
                        z6 = true;
                        i11 = 2;
                    }
                    String str13 = str10;
                    if (hVar.aw == j2 || !z2 || z8) {
                        i13 = i2;
                    } else if (i2 == 1 || z5) {
                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "download finish uodate 99: ", new Object[0]);
                        i13 = i2;
                        a(str3, 3, 99L, (int) (j2 - ae.b(w, str13, "lastNotifyDownloaded", 0L)), str7, z, true, str13, 0L);
                    } else {
                        i13 = i2;
                    }
                    if (i13 != 1 || z5) {
                        a(str3, 3, 100L, 0L, str7, z, false, null, 0L);
                    } else {
                        Intent intent4 = new Intent();
                        intent4.putExtra("action", 12);
                        intent4.putExtra("vercode", String.valueOf(str8));
                        intent4.putExtra(BwbxUtil.ACTION_PROGRESS, 100);
                        GameUtil.getIntance().notifyAction(intent4);
                        GameUtil.getIntance().bwbxDownloadStatistics(2, str8);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static void a(int i2, boolean z) {
        if (z) {
            i2 = (i2 * 3) / 4;
        }
        HashSet<String> hashSet = j;
        boolean z2 = !(hashSet == null || hashSet.isEmpty()) || N || Q;
        if (z2 || q.c()) {
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "merge progress:" + i2, new Object[0]);
            Intent intent = new Intent(w.getPackageName() + ".action.downloadcomponent.extracted");
            intent.setPackage(w.getPackageName());
            if (j.size() > 1) {
                int size = i2 / j.size();
                int i3 = ak;
                if (i3 > size) {
                    if (aj == 0) {
                        aj = i3;
                    }
                    size += aj;
                }
                ak = size;
                am = size;
                intent.putExtra(BwbxUtil.ACTION_PROGRESS, size);
            } else {
                am = i2;
                intent.putExtra(BwbxUtil.ACTION_PROGRESS, i2);
            }
            intent.putExtra("merge", true);
            if (z2) {
                intent.putExtra("gameid", "20000");
            }
            if (i2 == 100) {
                al = true;
            }
            s.a(w, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, boolean z) {
        if (!an.a().b(context) || z) {
            return;
        }
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "preInstallPackage success", new Object[0]);
        d(new Intent(context.getPackageName() + "com.excelliance.open.action.restart"));
    }

    static /* synthetic */ void a(h hVar, int i2, int i3, Intent intent) {
        hVar.a(i2, i3, intent);
    }

    static /* synthetic */ void a(h hVar, int i2, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i3, Intent intent, int i4, int i5, int i6, boolean z2, boolean z3, long j2, String str8, boolean z4, int i7) throws Throwable {
        hVar.a(i2, str, str2, str3, str4, str5, z, str6, str7, i3, intent, i4, i5, i6, z2, z3, j2, str8, z4, i7);
    }

    static /* synthetic */ void a(h hVar, Context context, boolean z) {
        hVar.a(context, z);
    }

    static /* synthetic */ void a(h hVar, Intent intent) throws Throwable {
        hVar.e(intent);
    }

    static /* synthetic */ void a(h hVar, l lVar) {
        hVar.a(lVar);
    }

    static /* synthetic */ void a(h hVar, Exception exc) throws Exception {
        hVar.a(exc);
    }

    static /* synthetic */ void a(h hVar, String str, int i2) {
        hVar.a(str, i2);
    }

    static /* synthetic */ void a(h hVar, String str, int i2, long j2, long j3, String str2, boolean z, Map map, boolean z2, String str3, long j4, String str4) {
        hVar.a(str, i2, j2, j3, str2, z, (Map<String, String>) map, z2, str3, j4, str4);
    }

    static /* synthetic */ void a(h hVar, String str, int i2, long j2, long j3, String str2, boolean z, boolean z2, String str3, long j4) {
        hVar.a(str, i2, j2, j3, str2, z, z2, str3, j4);
    }

    static /* synthetic */ void a(h hVar, String str, int i2, long j2, String str2, boolean z, String str3) {
        hVar.a(str, i2, j2, str2, z, str3);
    }

    static /* synthetic */ void a(h hVar, String str, int i2, long j2, String str2, boolean z, String str3, Map map) {
        hVar.a(str, i2, j2, str2, z, str3, (Map<String, String>) map);
    }

    static /* synthetic */ void a(h hVar, String str, int i2, Bundle bundle, int i3, Intent intent) {
        hVar.a(str, i2, bundle, i3, intent);
    }

    static /* synthetic */ void a(h hVar, String str, String str2) {
        hVar.a(str, str2);
    }

    static /* synthetic */ void a(h hVar, String str, String str2, int i2, String str3, Intent intent) {
        hVar.a(str, str2, i2, str3, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(l lVar) {
        an anVarA = an.a();
        anVarA.a(w);
        if ((!anVarA.b(w) || lVar.isInstall) && al.b(w) && a(lVar.flag, 262144) && !lVar.forceUpdate) {
            Intent intent = new Intent("com.excelliance.open.action.fw");
            intent.setPackage(w.getPackageName());
            intent.setComponent(new ComponentName(w, BGService.class.getName()));
            intent.putExtra("dialogtype", 2);
            intent.putExtra("detail", lVar);
            b(intent);
        }
        if (lVar.isInstall && anVarA.b(w) && al.b(w) && a(lVar.flag, 262144) && !lVar.forceUpdate) {
            Intent intent2 = new Intent(w.getPackageName() + "com.excelliance.open.action.overlay_install");
            intent2.putExtra("isinstall", lVar.isInstall);
            intent2.putExtra("apkpath", lVar.savePath);
            d(intent2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(l lVar, boolean z, boolean z2) {
        boolean z3 = false;
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "switchToUpdate:" + lVar.gameId + " autoMerge:" + lVar.autoMerge, new Object[0]);
        boolean zA = m.a(w, "RESTART_NEW_RAW", false);
        Intent intent = new Intent("com.excelliance.open.action.startNextChapter");
        intent.setPackage(M());
        intent.setFlags((!lVar.rpOnly || zA) ? 268468224 : 268435456);
        intent.putExtra("gameRunning", true);
        intent.putExtra("detail", lVar);
        intent.putExtra("dlType", "update");
        intent.putExtra("gameId", lVar.gameId);
        intent.putExtra("savePath", lVar.savePath);
        intent.putExtra("forceUpdate", lVar.forceUpdate);
        intent.putExtra(BwbxUtil.ACTION_SIZE, lVar.size);
        intent.putExtra("psize", lVar.hotResDSize);
        intent.putExtra("expsize", lVar.expanSize);
        intent.putExtra("tsize", lVar.tSize);
        intent.putExtra("ISINSTALL", lVar.isInstall);
        intent.putExtra("patch", lVar.patch);
        intent.putExtra("quitKillSelf", z);
        intent.putExtra("stopOtherDl", z2);
        intent.putExtra("exppatch", lVar.expanPatch);
        intent.putExtra("rppatch", lVar.hotResPatch);
        intent.putExtra("notifyTitle", lVar.notifyTitle);
        intent.putExtra("notifyMsg", lVar.notifyMsg);
        intent.putExtra("extra.lbsdk.hostintent", true);
        if (("vivo".equalsIgnoreCase(Build.BRAND) || "oppo".equalsIgnoreCase(Build.BRAND)) && al.c(w) && GameUtil.getIntance().remoteRunningPid() == 0) {
            z3 = true;
        }
        if (z3) {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Intent intent2 = new Intent(NextChapterParcel.ACTION_NCGENERIC);
            intent2.setPackage(M());
            intent2.putExtra("method", 1);
            intent2.putExtra("param", intent);
            d(intent2);
        } else {
            c(intent);
        }
        if (!lVar.rpOnly || zA) {
            GameUtil.getIntance().killThirdProcesses();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(l lVar, boolean z, boolean z2, boolean z3, boolean z4) {
        if (lVar.forceUpdate) {
            ae.a(w, "lebian_module_prompt", "showUpdate", true);
        }
        Intent intent = new Intent("com.excelliance.open.action.fw");
        intent.setPackage(w.getPackageName());
        intent.setComponent(new ComponentName(w, BGService.class.getName()));
        intent.putExtra("detail", lVar);
        intent.putExtra("ignoreOwnActivity", z);
        intent.putExtra("quitKillSelf", z2);
        intent.putExtra("stopOtherDl", z4);
        intent.putExtra("dataConnection", z3);
        b(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Exception exc) throws Exception {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "downloadExceptionHandle: " + exc.getMessage(), new Object[0]);
        if (exc.getMessage() != null) {
            if (exc.getMessage().contains("no space") || exc.getMessage().contains("ENOSPC")) {
                throw exc;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        com.excelliance.lbsdk.main.k.c.a(str, am, w, new com.excelliance.lbsdk.main.k.a() { // from class: com.excelliance.lbsdk.main.h.9
            @Override // com.excelliance.lbsdk.main.k.a
            public void a(com.excelliance.lbsdk.main.k.d dVar) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2) {
        Intent intent = new Intent(w.getPackageName() + ".action.downloadcomponent.computeMD5");
        intent.setPackage(w.getPackageName());
        intent.putExtra(BwbxUtil.ACTION_PROGRESS, i2);
        intent.putExtra("gameid", str);
        d(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, long j2, long j3, String str2, boolean z, Map<String, String> map, boolean z2, String str3, long j4, String str4) {
        int i3;
        try {
            an anVarA = an.a();
            StringBuilder sb = new StringBuilder(GameUtil.getIntance().getBaseUrl("s") + "sdkstatistics.php");
            sb.append("?action=" + i2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("chid", GameUtil.getIntance().getApkMainCh());
            jSONObject.put("md5", str2);
            String strB = anVarA.b();
            if (strB != null) {
                jSONObject.put("uid", strB);
            }
            jSONObject.put("nettype", aa.c(w));
            if (i2 == 2) {
                jSONObject.put("auto", z);
                jSONObject.put("gver", j2);
                if (map != null) {
                    jSONObject.put("pkg", map.get("pkg"));
                    jSONObject.put("cpmeta", map.get("cpmeta"));
                }
            } else if (i2 == 7) {
                jSONObject.put("errType", j2);
                if (str4 != null) {
                    jSONObject.put("excpt", str4);
                }
            } else if (i2 == 3) {
                jSONObject.put(BwbxUtil.ACTION_PROGRESS, j2);
            } else if ((i2 == 48 || i2 == 49) && map != null) {
                jSONObject.put("pkg", map.get("pkg"));
                jSONObject.put("cpmeta", map.get("cpmeta"));
                jSONObject.put("vercode", map.get("vercode"));
                jSONObject.put("rmd5", map.get("rmd5"));
                if (i2 == 48) {
                    jSONObject.put("tp", map.get("tp"));
                }
            }
            if (z2) {
                if (str3 != null) {
                    ae.a(w, str3, "lastNotifyDownloaded", j4);
                }
                this.ai += j3;
                jSONObject.put("increment", j3 / 1024);
            }
            i3 = 0;
            try {
                sb.append("&data=" + URLEncoder.encode(Base64.encodeToString(jSONObject.toString().getBytes(), 0), "UTF-8"));
                i3 = 0;
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "notifyDlResult statusCode:" + aa.a(sb.toString(), w).a, new Object[0]);
            } catch (Exception e2) {
                e = e2;
                com.excelliance.lbsdk.main.g.b.e("DownloadComponent", "notifyDlResult HttpPost Exception:", e, new Object[i3]);
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, long j2, long j3, String str2, boolean z, boolean z2, String str3, long j4) {
        a(str, i2, j2, j3, str2, z, (Map<String, String>) null, z2, str3, j4, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, long j2, String str2, boolean z, String str3) {
        a(str, i2, j2, 0L, str2, z, (Map<String, String>) null, false, (String) null, 0L, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, long j2, String str2, boolean z, String str3, Map<String, String> map) {
        a(str, i2, j2, 0L, str2, z, map, false, (String) null, 0L, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i2, Bundle bundle, int i3, Intent intent) {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "publishProgress progress:" + i2 + " gameId:" + str + " nt:" + i3 + ", mCancel=" + f, new Object[0]);
        if (i2 == 100) {
            ae.a(w, "lebian_module_gameinfo", "startDownload");
            boolean booleanExtra = intent.getBooleanExtra("PREDOWNLOAD", false);
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "isPred:" + booleanExtra, new Object[0]);
            if (!booleanExtra) {
                ae.a(w, "lebian_module_gameinfo", "downloadFinish", true);
            }
        }
        Intent intent2 = new Intent("com.excelliance.open.action.downloadcomponent.progress");
        intent2.setPackage(M());
        intent2.replaceExtras(bundle);
        intent2.putExtra("gameid", str);
        intent2.putExtra(BwbxUtil.ACTION_PROGRESS, i2);
        intent2.putExtra("ISINSTALL", intent.getBooleanExtra("ISINSTALL", false));
        intent2.putExtra("merge", al);
        d(intent2);
        if (i3 >= 0) {
            if (!f || i2 == 100 || i2 == 99) {
                intent.putExtra("downloadprogress", i2);
                a(i2, i3, intent);
            }
        }
    }

    private void a(String str, long j2, long j3, int i2, int i3) {
        synchronized (g) {
            Intent intent = new Intent(M() + ".action.downloadcomponent.downloaded");
            intent.putExtra("gameid", str);
            intent.putExtra("downloaded", j2);
            intent.putExtra(BwbxUtil.ACTION_SIZE, j3);
            intent.putExtra("currResNum", i2);
            intent.putExtra("totalResNum", i3);
            d(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            ae.a(w, "lebian_module_md5info", GameUtil.getIntance().computeInputStreamMd5(new ByteArrayInputStream((str + String.valueOf(file.lastModified())).getBytes())), str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, int i2, String str3, Intent intent) {
        synchronized (g) {
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "publishError gameId:" + str + ", error=" + str2 + ", errType=" + i2 + ", dmd5=" + str3, new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append(M());
            sb.append("com.excelliance.open.downloadcomponent.error");
            Intent intent2 = new Intent(sb.toString());
            intent2.putExtra("gameid", str);
            intent2.putExtra("error", str2);
            intent2.putExtra("errType", i2);
            intent2.putExtra("dmd5", str3);
            d(intent2);
            B = true;
            this.G = true;
            if (C >= 0 && !f && intent != null) {
                intent.putExtra("error", str2);
                intent.putExtra("errType", i2);
                a(-1, 2, intent);
            }
        }
    }

    public static boolean a() {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "exclusiveMode:dl:" + D + ", fu:" + E + ", pd:" + F, new Object[0]);
        return D && E && !F && Q;
    }

    public static boolean a(int i2, int i3) {
        return (i2 & i3) != 0;
    }

    static /* synthetic */ boolean a(h hVar, String str, String str2, HashMap map) {
        return hVar.a(str, str2, (HashMap<String, String>) map);
    }

    static /* synthetic */ boolean a(h hVar, boolean z) {
        hVar.an = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str, String str2, HashMap<String, String> map) {
        File file;
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "resOnlyCheckFile: start", new Object[0]);
        int i2 = 1;
        if (ae.b(w, "lebian_module_extractinfo", "resonly_check", false)) {
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "resOnlyCheckFile: flag return", new Object[0]);
            return true;
        }
        File file2 = new File(str2);
        HashMap<String, com.excelliance.lbsdk.main.ae.b> mapA = com.excelliance.lbsdk.main.ae.c.a().a(w);
        for (String str3 : mapA.keySet()) {
            com.excelliance.lbsdk.main.ae.b bVar = mapA.get(str3);
            if (bVar != null) {
                map.put(str3, bVar.d() + ":" + str3);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(10);
        try {
            ZipFile zipFile = new ZipFile(str);
            ZipEntry entry = zipFile.getEntry("patchcfg");
            if (entry != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        for (String str4 : map.keySet()) {
                            if (map.get(str4).startsWith("-f")) {
                                new File(file2, str4).delete();
                            }
                        }
                        break;
                    }
                    if (line.length() != 0 && !line.startsWith("de:") && !line.startsWith("st:")) {
                        String strSubstring = null;
                        if (line.startsWith("p:") || line.startsWith("pc:") || line.startsWith("px:") || line.startsWith("cx:")) {
                            int iIndexOf = line.indexOf(58) + i2;
                            strSubstring = line.substring(iIndexOf, line.indexOf(58, iIndexOf));
                            if (strSubstring.startsWith("assets") || strSubstring.startsWith("lib")) {
                                file = new File(file2, strSubstring);
                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "newFileMd5Mismatch p tmpFile=" + file, new Object[0]);
                                String strSubstring2 = line.substring(line.lastIndexOf(95) + i2, line.lastIndexOf(46));
                                if (file.exists()) {
                                    FileInputStream fileInputStream = new FileInputStream(file);
                                    String strComputeInputStreamMd5 = GameUtil.getIntance().computeInputStreamMd5(fileInputStream);
                                    fileInputStream.close();
                                    if (!strSubstring2.equals(strComputeInputStreamMd5)) {
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "newFileMd5Mismatch name=" + strSubstring + ", expect=" + strSubstring2 + ", real=" + strComputeInputStreamMd5, new Object[0]);
                                        return false;
                                    }
                                }
                            }
                        } else {
                            file = null;
                        }
                        if (strSubstring == null) {
                            strSubstring = line.substring(line.indexOf(58) + 1);
                        }
                        if (line.startsWith("k:")) {
                            linkedHashSet.add(strSubstring);
                        } else {
                            if (file == null) {
                                file = new File(file2, strSubstring);
                            }
                            if (line.startsWith("-f:")) {
                                Iterator it = linkedHashSet.iterator();
                                String str5 = strSubstring;
                                while (it.hasNext()) {
                                    if (!str5.startsWith((String) it.next())) {
                                        str5 = "-f:" + str5;
                                        map.put(strSubstring, str5);
                                    }
                                }
                            } else if (line.startsWith("-d:")) {
                                ZipFile zipFile2 = new ZipFile(w.getPackageCodePath());
                                Enumeration<? extends ZipEntry> enumerationEntries = zipFile2.entries();
                                while (enumerationEntries.hasMoreElements()) {
                                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                                    String name = zipEntryNextElement.getName();
                                    if (!zipEntryNextElement.isDirectory() && name.contains(strSubstring)) {
                                        Iterator it2 = linkedHashSet.iterator();
                                        while (it2.hasNext()) {
                                            if (!name.startsWith((String) it2.next())) {
                                                map.put(name, "-f:" + name);
                                            }
                                        }
                                    }
                                }
                                zipFile2.close();
                                for (String str6 : mapA.keySet()) {
                                    if (str6.contains(strSubstring)) {
                                        Iterator it3 = linkedHashSet.iterator();
                                        while (it3.hasNext()) {
                                            if (!str6.startsWith((String) it3.next())) {
                                                map.put(str6, "-f:" + str6);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (!file.exists()) {
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "newFileMd5Mismatch tmpFile no exist=" + file.getAbsolutePath(), new Object[0]);
                                    return false;
                                }
                                map.put(strSubstring, "up:" + strSubstring);
                            }
                        }
                        i2 = 1;
                    }
                }
            }
            zipFile.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ c b(h hVar) {
        return hVar.x;
    }

    public static void b(int i2) {
        if (ao) {
            return;
        }
        a(i2, true);
    }

    static /* synthetic */ boolean b(h hVar, boolean z) {
        hVar.q = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(l lVar) {
        if (aa.a(w) && a(lVar.flag, 65536)) {
            return true;
        }
        return (!aa.a(w) && a(lVar.flag, 131072)) || a(lVar.flag, 2097152);
    }

    static /* synthetic */ boolean b(boolean z) {
        D = z;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String[] b(String str) {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "dirStr: " + str, new Object[0]);
        if (TextUtils.isEmpty(str)) {
            return new String[0];
        }
        String[] strArrSplit = str.split(";");
        for (String str2 : strArrSplit) {
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "splitSpecialDir: " + str2, new Object[0]);
        }
        return strArrSplit;
    }

    static /* synthetic */ int c(int i2) {
        P = i2;
        return i2;
    }

    static /* synthetic */ String c(h hVar, String str) {
        hVar.I = str;
        return str;
    }

    static /* synthetic */ void c(h hVar) {
        hVar.S();
    }

    public static boolean c() {
        return ae;
    }

    static /* synthetic */ boolean c(h hVar, boolean z) {
        hVar.G = z;
        return z;
    }

    static /* synthetic */ boolean c(boolean z) {
        E = z;
        return z;
    }

    static /* synthetic */ int d(int i2) {
        W = i2;
        return i2;
    }

    static /* synthetic */ BGService d(h hVar) {
        return hVar.m;
    }

    static /* synthetic */ String d(h hVar, String str) {
        hVar.J = str;
        return str;
    }

    public static boolean d() {
        return ag;
    }

    static /* synthetic */ boolean d(h hVar, boolean z) {
        hVar.ap = z;
        return z;
    }

    static /* synthetic */ boolean d(boolean z) {
        F = z;
        return z;
    }

    static /* synthetic */ int e(int i2) {
        X = i2;
        return i2;
    }

    static /* synthetic */ HashMap e(h hVar) {
        return hVar.S;
    }

    public static void e() {
        ah = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Intent intent) throws Throwable {
        an anVar;
        PackageManager packageManagerO;
        ApplicationInfo applicationInfo;
        h hVar;
        an anVar2;
        Intent intent2;
        String string;
        Object[] objArr;
        h hVar2 = this;
        Intent intent3 = intent;
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "pendingMap.size=" + h.size(), new Object[0]);
        synchronized (h) {
            try {
                try {
                    an anVarA = an.a();
                    int i2 = 1;
                    boolean z = !aa.a(w);
                    Iterator<Map.Entry<String, l>> it = h.entrySet().iterator();
                    l lVar = null;
                    while (it.hasNext()) {
                        l value = it.next().getValue();
                        if (!z || a(value.flag, 4) || !a(value.flag, 131072) || c <= i2) {
                            int identifier = N().getIdentifier("lebian_storage_space_requirement_dload", "string", M());
                            int identifier2 = N().getIdentifier("lebian_storage_phone", "string", M());
                            int identifier3 = N().getIdentifier("lebian_not_enought_space", "string", M());
                            if (value.savePath != null) {
                                File parentFile = new File(value.savePath).getParentFile();
                                parentFile.mkdirs();
                                try {
                                    if (parentFile != null) {
                                        anVar2 = anVarA;
                                        if (GameUtil.getFreeSpace(parentFile) >= (((value.size + value.expanSize) + value.tSize) + value.hotResDSize) - new File(value.savePath).length()) {
                                            synchronized (g) {
                                                if (g.containsKey(value.dmd5)) {
                                                    it.remove();
                                                    intent2 = intent3;
                                                    hVar = hVar2;
                                                    i2 = 1;
                                                } else {
                                                    if (lVar == null) {
                                                        lVar = value;
                                                    }
                                                    anVarA = anVar2;
                                                    i2 = 1;
                                                }
                                            }
                                        }
                                    } else {
                                        anVar2 = anVarA;
                                    }
                                    objArr[0] = Float.valueOf((((value.size + value.expanSize) + value.tSize) - new File(value.savePath).length()) / 1048576.0f);
                                    objArr[1] = N().getString(identifier2);
                                    String str = String.format(string, objArr);
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "left=" + GameUtil.getFreeSpace(parentFile) + ", value.size=" + value.size + " savePath = " + value.savePath + ", value.expanSize = " + value.expanSize + ", downloaded = " + new File(value.savePath).length(), new Object[0]);
                                    ae.a(w, "lebian_module_dloaderror", "spaceError", 1);
                                    ae.a(w, "lebian_module_dloaderror", BwbxUtil.ACTION_SIZE, value.size);
                                    ae.a(w, "lebian_module_dloaderror", "expsize", value.expanSize);
                                    ae.a(w, "lebian_module_dloaderror", "tsize", value.tSize);
                                    ae.a(w, "lebian_module_dloaderror", "psize", value.hotResDSize);
                                    ae.a(w, "lebian_module_dloaderror", "forceUpdate", value.forceUpdate);
                                    ae.a(w, "lebian_module_dloaderror", "preDownload", value.preDownload);
                                    hVar = this;
                                    intent2 = intent;
                                    hVar.a(value.gameId, N().getString(identifier3) + ", " + str, intent2);
                                    it.remove();
                                    i2 = 1;
                                } catch (Throwable th) {
                                    th = th;
                                    throw th;
                                }
                                string = N().getString(identifier);
                                objArr = new Object[2];
                            } else {
                                hVar = hVar2;
                                anVar2 = anVarA;
                                intent2 = intent3;
                                i2 = 1;
                                String str2 = String.format(N().getString(identifier), Float.valueOf((((value.size + value.expanSize) + value.tSize) + value.hotResDSize) / 1048576.0f), N().getString(identifier2));
                                ae.a(w, "lebian_module_dloaderror", "spaceError", 1);
                                ae.a(w, "lebian_module_dloaderror", BwbxUtil.ACTION_SIZE, value.size);
                                ae.a(w, "lebian_module_dloaderror", "expsize", value.expanSize);
                                ae.a(w, "lebian_module_dloaderror", "tsize", value.tSize);
                                ae.a(w, "lebian_module_dloaderror", "psize", value.hotResDSize);
                                ae.a(w, "lebian_module_dloaderror", "forceUpdate", value.forceUpdate);
                                ae.a(w, "lebian_module_dloaderror", "preDownload", value.preDownload);
                                hVar.a(value.gameId, N().getString(identifier3) + ", " + str2, intent2);
                                it.remove();
                            }
                        } else {
                            it.remove();
                            hVar = hVar2;
                            anVar2 = anVarA;
                            intent2 = intent3;
                        }
                        hVar2 = hVar;
                        intent3 = intent2;
                        z = z;
                        anVarA = anVar2;
                    }
                    h hVar3 = hVar2;
                    an anVar3 = anVarA;
                    if (lVar != null) {
                        if (TextUtils.isEmpty(lVar.hotResUrl) || TextUtils.isEmpty(lVar.hotResCfg)) {
                            anVar = anVar3;
                        } else {
                            new File(lVar.hotResCfg).delete();
                            anVar = anVar3;
                            anVar.a(lVar.hotResCfg, lVar);
                        }
                        if (!lVar.expansionOnly && !lVar.rpOnly) {
                            new File(lVar.cfgPath).delete();
                            anVar.a(lVar.cfgPath, lVar);
                        }
                        Intent intent4 = new Intent("com.excelliance.open.action.gameverchk");
                        intent4.setPackage(M());
                        intent4.setComponent(new ComponentName(w, BGService.class.getName()));
                        intent4.putExtra("detail", lVar);
                        intent4.putExtra("autodl", lVar.autodl);
                        intent4.putExtra("ISINSTALL", lVar.isInstall);
                        if (hVar3.L && hVar3.b(lVar)) {
                            intent4.putExtra("notificationdownload", 0);
                            String strM = M();
                            try {
                                packageManagerO = O();
                                try {
                                    applicationInfo = packageManagerO.getApplicationInfo(strM, 0);
                                } catch (PackageManager.NameNotFoundException unused) {
                                    applicationInfo = null;
                                }
                            } catch (PackageManager.NameNotFoundException unused2) {
                                packageManagerO = null;
                            }
                            String str3 = (String) packageManagerO.getApplicationLabel(applicationInfo);
                            int identifier4 = Build.VERSION.SDK_INT >= 24 ? PlatformResources.getApkResources(w).getIdentifier("lebian_default_icon", "drawable", strM) : 0;
                            if (identifier4 == 0) {
                                try {
                                    identifier4 = O().getApplicationInfo(strM, 0).icon;
                                } catch (Exception unused3) {
                                }
                            }
                            Notification notificationCreateNotification = GameUtil.createNotification(w, false, identifier4, str3, N().getString(N().getIdentifier("lebian_update_available", "string", strM)), PendingIntent.getService(w, 0, intent4, com.excelliance.lbsdk.main.b.a(134217728)));
                            if (notificationCreateNotification != null) {
                                notificationCreateNotification.defaults = 7;
                                notificationCreateNotification.flags |= 16;
                                hVar3.m.startForeground(99, notificationCreateNotification);
                            }
                            hVar3.l(5);
                        } else {
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "start an autodownload " + lVar.gameId + ", dmd5=" + lVar.dmd5, new Object[0]);
                            hVar3.b(intent4);
                        }
                        h.remove(lVar.dmd5);
                    } else {
                        hVar3.l(5);
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    static /* synthetic */ boolean e(boolean z) {
        O = z;
        return z;
    }

    static /* synthetic */ int f(int i2) {
        Y = i2;
        return i2;
    }

    public static boolean f() {
        return e;
    }

    static /* synthetic */ boolean f(h hVar) {
        return hVar.q;
    }

    static /* synthetic */ boolean f(boolean z) {
        f = z;
        return z;
    }

    static /* synthetic */ int g() {
        return P;
    }

    static /* synthetic */ int g(int i2) {
        Z = i2;
        return i2;
    }

    static /* synthetic */ Runnable g(h hVar) {
        return hVar.aD;
    }

    static /* synthetic */ boolean g(boolean z) {
        n = z;
        return z;
    }

    static /* synthetic */ int h(int i2) {
        T = i2;
        return i2;
    }

    static /* synthetic */ Context h() {
        return w;
    }

    static /* synthetic */ Handler h(h hVar) {
        return hVar.R;
    }

    static /* synthetic */ boolean h(boolean z) {
        p = z;
        return z;
    }

    static /* synthetic */ boolean i(boolean z) {
        V = z;
        return z;
    }

    static /* synthetic */ int j() {
        return C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2) {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "startAlarm " + i2, new Object[0]);
        long jK = k(i2);
        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
        intent.setPackage(M());
        intent.putExtra("alarmcheck", true);
        intent.putExtra("alarmid", i2);
        PendingIntent service = PendingIntent.getService(w, i2, intent, com.excelliance.lbsdk.main.b.a(268435456));
        AlarmManager alarmManager = (AlarmManager) w.getSystemService("alarm");
        alarmManager.cancel(service);
        alarmManager.setRepeating(0, System.currentTimeMillis() + jK, jK, service);
    }

    static /* synthetic */ void j(h hVar) {
        hVar.R();
    }

    static /* synthetic */ boolean j(boolean z) {
        ae = z;
        return z;
    }

    static /* synthetic */ int k(h hVar) {
        int i2 = hVar.y;
        hVar.y = i2 + 1;
        return i2;
    }

    private long k(int i2) {
        if (i2 == 5) {
            return 20000L;
        }
        return i2 == 6 ? 28800000L : 600000L;
    }

    static /* synthetic */ Map k() {
        return g;
    }

    static /* synthetic */ boolean k(boolean z) {
        N = z;
        return z;
    }

    static /* synthetic */ int l() {
        return W;
    }

    static /* synthetic */ Runnable l(h hVar) {
        return hVar.au;
    }

    private void l(int i2) {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "cancelAlarm " + i2, new Object[0]);
        Intent intent = new Intent("com.excelliance.open.action.gameverchk");
        intent.setPackage(M());
        intent.putExtra("alarmcheck", true);
        ((AlarmManager) w.getSystemService("alarm")).cancel(PendingIntent.getService(w, i2, intent, com.excelliance.lbsdk.main.b.a(0)));
    }

    static /* synthetic */ boolean l(boolean z) {
        ao = z;
        return z;
    }

    static /* synthetic */ int m() {
        return X;
    }

    static /* synthetic */ boolean m(h hVar) {
        return hVar.L;
    }

    static /* synthetic */ boolean m(boolean z) {
        ag = z;
        return z;
    }

    static /* synthetic */ int n() {
        return Y;
    }

    static /* synthetic */ int n(h hVar) {
        return hVar.U;
    }

    static /* synthetic */ boolean n(boolean z) {
        B = z;
        return z;
    }

    static /* synthetic */ int o() {
        return Z;
    }

    static /* synthetic */ boolean o(boolean z) {
        A = z;
        return z;
    }

    static /* synthetic */ int p() {
        int i2 = W + 1;
        W = i2;
        return i2;
    }

    static /* synthetic */ ArrayList q() {
        return aa;
    }

    static /* synthetic */ int r() {
        int i2 = X + 1;
        X = i2;
        return i2;
    }

    static /* synthetic */ ArrayList s() {
        return ab;
    }

    static /* synthetic */ int t() {
        int i2 = Y + 1;
        Y = i2;
        return i2;
    }

    static /* synthetic */ ArrayList u() {
        return ac;
    }

    static /* synthetic */ int v() {
        int i2 = Z + 1;
        Z = i2;
        return i2;
    }

    static /* synthetic */ ArrayList w() {
        return ad;
    }

    static /* synthetic */ boolean x() {
        return f;
    }

    static /* synthetic */ int y() {
        return v;
    }

    static /* synthetic */ int z(h hVar) {
        int i2 = hVar.s + 1;
        hVar.s = i2;
        return i2;
    }

    static /* synthetic */ boolean z() {
        return p;
    }

    void a(int i2) throws Throwable {
        FileInputStream fileInputStream;
        StringBuilder sb;
        FileOutputStream fileOutputStream;
        String str = GameUtil.getDataRoot(w) + "userinfo.cfg";
        File file = new File(str);
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "saveDownloadStatus status = " + i2, new Object[0]);
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

    public void a(long j2) {
        Intent intent = new Intent(M() + ".action.downloadcomponent.speed.max");
        intent.setPackage(M());
        intent.putExtra("max", j2);
        d(intent);
    }

    public void a(Context context) {
        if (this.ar != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.excellience.yungame.ACTION_HIGH_SPEED");
            s.a(context, this.ar, intentFilter);
        }
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0498 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:107:0x04a5 A[Catch: all -> 0x04d5, TRY_ENTER, TryCatch #2 {all -> 0x04d5, blocks: (B:94:0x0465, B:107:0x04a5, B:108:0x04a7, B:97:0x0482), top: B:172:0x0465 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x04b0 A[Catch: all -> 0x04cc, TryCatch #3 {all -> 0x04cc, blocks: (B:109:0x04a8, B:111:0x04b0, B:115:0x04bb), top: B:174:0x04a8 }] */
    /* JADX WARN: Code duplicated, block: B:115:0x04bb A[Catch: all -> 0x04cc, TRY_LEAVE, TryCatch #3 {all -> 0x04cc, blocks: (B:109:0x04a8, B:111:0x04b0, B:115:0x04bb), top: B:174:0x04a8 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x04d2  */
    /* JADX WARN: Code duplicated, block: B:131:0x04d9 A[Catch: all -> 0x0730, TryCatch #1 {all -> 0x0730, blocks: (B:92:0x045d, B:127:0x04d3, B:123:0x04cf, B:100:0x0494, B:105:0x049e, B:131:0x04d9, B:133:0x04e2, B:134:0x04e6, B:137:0x058f, B:138:0x05b4, B:144:0x05c5, B:147:0x05f3, B:149:0x05fb, B:151:0x0638, B:153:0x0640, B:154:0x065d, B:155:0x0676, B:166:0x0731), top: B:171:0x045d }] */
    /* JADX WARN: Code duplicated, block: B:133:0x04e2 A[Catch: all -> 0x0730, TryCatch #1 {all -> 0x0730, blocks: (B:92:0x045d, B:127:0x04d3, B:123:0x04cf, B:100:0x0494, B:105:0x049e, B:131:0x04d9, B:133:0x04e2, B:134:0x04e6, B:137:0x058f, B:138:0x05b4, B:144:0x05c5, B:147:0x05f3, B:149:0x05fb, B:151:0x0638, B:153:0x0640, B:154:0x065d, B:155:0x0676, B:166:0x0731), top: B:171:0x045d }] */
    /* JADX WARN: Code duplicated, block: B:136:0x058d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:140:0x05ba  */
    /* JADX WARN: Code duplicated, block: B:142:0x05bf  */
    /* JADX WARN: Code duplicated, block: B:145:0x05ec  */
    /* JADX WARN: Code duplicated, block: B:146:0x05ef  */
    /* JADX WARN: Code duplicated, block: B:149:0x05fb A[Catch: all -> 0x0730, TryCatch #1 {all -> 0x0730, blocks: (B:92:0x045d, B:127:0x04d3, B:123:0x04cf, B:100:0x0494, B:105:0x049e, B:131:0x04d9, B:133:0x04e2, B:134:0x04e6, B:137:0x058f, B:138:0x05b4, B:144:0x05c5, B:147:0x05f3, B:149:0x05fb, B:151:0x0638, B:153:0x0640, B:154:0x065d, B:155:0x0676, B:166:0x0731), top: B:171:0x045d }] */
    /* JADX WARN: Code duplicated, block: B:150:0x0630  */
    /* JADX WARN: Code duplicated, block: B:153:0x0640 A[Catch: all -> 0x0730, TryCatch #1 {all -> 0x0730, blocks: (B:92:0x045d, B:127:0x04d3, B:123:0x04cf, B:100:0x0494, B:105:0x049e, B:131:0x04d9, B:133:0x04e2, B:134:0x04e6, B:137:0x058f, B:138:0x05b4, B:144:0x05c5, B:147:0x05f3, B:149:0x05fb, B:151:0x0638, B:153:0x0640, B:154:0x065d, B:155:0x0676, B:166:0x0731), top: B:171:0x045d }] */
    /* JADX WARN: Code duplicated, block: B:158:0x067f  */
    /* JADX WARN: Code duplicated, block: B:160:0x0687  */
    /* JADX WARN: Code duplicated, block: B:161:0x068b  */
    /* JADX WARN: Code duplicated, block: B:163:0x0693  */
    /* JADX WARN: Code duplicated, block: B:171:0x045d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:172:0x0465 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x04a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x02e2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:89:0x02ea  */
    /* JADX WARN: Code duplicated, block: B:96:0x0480 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:97:0x0482 A[Catch: all -> 0x04d5, TRY_LEAVE, TryCatch #2 {all -> 0x04d5, blocks: (B:94:0x0465, B:107:0x04a5, B:108:0x04a7, B:97:0x0482), top: B:172:0x0465 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x0492 A[ADDED_TO_REGION] */
    /* JADX WARN: Instruction removed from duplicated block: B:89:0x02ea, please report this as an issue */
    /* JADX WARN: Type inference failed for: r14v6, types: [com.excelliance.lbsdk.main.h$7] */
    public void a(final Intent intent) {
        h hVar;
        h hVar2;
        l lVar;
        int i2;
        String str;
        final l lVar2 = (l) intent.getSerializableExtra("detail");
        String str2 = lVar2 != null ? lVar2.gameId : null;
        String str3 = lVar2 != null ? lVar2.gameLib : null;
        String str4 = lVar2 != null ? lVar2.version : null;
        final String str5 = lVar2 != null ? lVar2.level : null;
        boolean z = lVar2 != null ? lVar2.rpOnly : false;
        final boolean booleanExtra = intent.getBooleanExtra("check", false);
        if (!f || !D) {
            f = intent.getBooleanExtra("canceldownloading", false);
        }
        final boolean booleanExtra2 = intent.getBooleanExtra("checkPeriod", false);
        final boolean booleanExtra3 = intent.getBooleanExtra("queryUpdate", false);
        final boolean booleanExtra4 = intent.getBooleanExtra("startQuery", false);
        final String stringExtra = intent.getStringExtra("customChannel");
        Bundle bundleExtra = intent.getBundleExtra("custParams");
        if (bundleExtra != null) {
            ae.a(w, "lebian_module_queryinfo", "forceUpdate", bundleExtra.getInt("forceUpdate", -1));
        }
        final boolean booleanExtra5 = intent.getBooleanExtra("alarmcheck", false);
        final int intExtra = intent.getIntExtra("alarmid", 0);
        int intExtra2 = intent.getIntExtra("notificationdownload", -1);
        boolean zB = ae.b(w, "lebian_module_gameinfo", "isExistNotification", false);
        final int intExtra3 = intent.getIntExtra("downloadprogress", -1);
        boolean booleanExtra6 = intent.getBooleanExtra("stopOtherDl", false);
        final boolean booleanExtra7 = intent.getBooleanExtra("autodl", false);
        final Bundle bundle = new Bundle();
        bundle.putString("version", str4);
        bundle.putString("level", str5);
        bundle.putString("pkg", str3);
        bundle.putBoolean("rponly", z);
        final String str6 = str3;
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "onHandleIntent: dd = " + lVar2, new Object[0]);
        if (booleanExtra6 && !ag) {
            ag = true;
            Q();
        }
        if (f) {
            this.ay = true;
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "gameId:" + str2 + " mCancel:" + f + ", now = " + D + ", nd = " + intExtra2, new Object[0]);
            ae.a(w, "lebian_module_gameinfo", "isExistNotification", false);
            if (!D) {
                f = false;
            }
            if (intExtra2 == 3) {
                A = false;
                this.m.stopForeground(true);
                NotificationManager notificationManager = (NotificationManager) w.getSystemService("notification");
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "NOTE_DOWNLOAD_STOP:cancel DOWNLOAD_PROGRESS_NOTE_ID", new Object[0]);
                notificationManager.cancel(99);
            } else {
                if (intExtra2 < 0 || af) {
                    return;
                }
                a(-1, 2, intent);
                A = true;
            }
            a(str2, -2, bundle, -1, intent);
            return;
        }
        if (C >= 0) {
            A = true;
        }
        final GameUtil intance = GameUtil.getIntance();
        final an anVarA = an.a();
        anVarA.a(this.x);
        anVarA.a(w);
        if (str2 == null) {
            new Thread("DownloadComponentService:DownloadComponentService2") { // from class: com.excelliance.lbsdk.main.h.7
                /* JADX WARN: Code duplicated, block: B:107:0x02fd  */
                /* JADX WARN: Code duplicated, block: B:108:0x0304 A[DONT_INVERT] */
                /* JADX WARN: Code duplicated, block: B:109:0x0306  */
                /* JADX WARN: Code duplicated, block: B:167:0x045a  */
                /* JADX WARN: Code duplicated, block: B:184:0x047f  */
                /* JADX WARN: Code duplicated, block: B:197:0x050e  */
                /* JADX WARN: Code duplicated, block: B:198:0x051f  */
                /* JADX WARN: Code duplicated, block: B:203:0x0593  */
                /* JADX WARN: Code duplicated, block: B:206:0x05d7  */
                /* JADX WARN: Code duplicated, block: B:208:0x05df  */
                /* JADX WARN: Code duplicated, block: B:210:0x05e7  */
                /* JADX WARN: Code duplicated, block: B:226:0x0620 A[Catch: all -> 0x0630, TryCatch #0 {, blocks: (B:224:0x0614, B:226:0x0620, B:228:0x0624, B:229:0x062d), top: B:266:0x0614, outer: #3 }] */
                /* JADX WARN: Code duplicated, block: B:255:0x0685  */
                /* JADX WARN: Code duplicated, block: B:266:0x0614 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:268:0x0522 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:270:0x0290 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:272:0x060f A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:280:0x021a A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:284:0x0465 A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:285:0x047d A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:288:0x0215 A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:289:0x0636 A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:295:0x065e A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:297:0x045f A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:298:0x0477 A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:301:0x046d A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:302:0x047d A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:304:0x0187 A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:306:0x0187 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:311:0x0187 A[SYNTHETIC] */
                /* JADX WARN: Code duplicated, block: B:49:0x012b  */
                /* JADX WARN: Code duplicated, block: B:52:0x013d  */
                /* JADX WARN: Code duplicated, block: B:55:0x0145  */
                /* JADX WARN: Code duplicated, block: B:60:0x015e  */
                /* JADX WARN: Code duplicated, block: B:62:0x016b  */
                /* JADX WARN: Code duplicated, block: B:65:0x018d  */
                /* JADX WARN: Code duplicated, block: B:78:0x01f5  */
                /* JADX WARN: Code duplicated, block: B:88:0x0224 A[Catch: all -> 0x025b, TryCatch #8 {, blocks: (B:86:0x021a, B:88:0x0224, B:89:0x0250, B:90:0x0259), top: B:280:0x021a }] */
                /* JADX WARN: Code duplicated, block: B:95:0x025e  */
                /* JADX WARN: Code duplicated, block: B:96:0x0280  */
                /* JADX WARN: Instruction removed from duplicated block: B:95:0x025e, please report this as an issue */
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws Throwable {
                    int i3;
                    List<l> listA;
                    boolean zContainsKey;
                    boolean z2;
                    String strM;
                    PackageManager packageManagerO;
                    int identifier;
                    Notification notificationCreateNotification;
                    h.this.x.a(this);
                    if (ae.b(h.w, "lebian_module_updateinfo", "lastCheckTime", 0L) == 0) {
                        h.this.j(6);
                        ae.a(h.w, "lebian_module_updateinfo", "lastCheckTime", System.currentTimeMillis());
                    }
                    boolean z3 = !aa.a(h.w);
                    if (booleanExtra5 || booleanExtra) {
                        if (intExtra == 6) {
                            Intent intent2 = new Intent("com.excelliance.open.action.bwbxdla");
                            intent2.setPackage(h.this.M());
                            intent2.setComponent(new ComponentName(h.w, BGService.class.getName()));
                            intent2.putExtra("downloadAfterQuit", true);
                            h.this.b(intent2);
                            ae.a(h.w, "lebian_module_updateinfo", "lastCheckTime", System.currentTimeMillis());
                        } else {
                            synchronized (h.g) {
                                if (h.g.size() > 0) {
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "still alive", new Object[0]);
                                    h.this.x.b(this);
                                    return;
                                } else {
                                    synchronized (h.i) {
                                        if (h.i.size() > 0) {
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "auto merge still alive", new Object[0]);
                                            h.this.x.b(this);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    h.this.c(h.w);
                    if (intance.getClientChId().equals("PATCH_V3") && intance.getApkMainCh() < 800000) {
                        Message message = new Message();
                        message.obj = "PATCH_V3的包只能上传至乐变后台，不能外发给渠道!";
                        message.what = 3;
                        h.this.R.sendMessage(message);
                    }
                    int i4 = aa.a(h.w) ? 2 : 0;
                    if (!booleanExtra2) {
                        if (booleanExtra3) {
                            i3 = 1;
                        } else if (booleanExtra4) {
                            i3 = 2;
                        }
                        if (!h.e) {
                            i4 = i4 | 4 | 8 | 16;
                        }
                        if (anVarA.b(h.w)) {
                            i4 |= 4;
                        }
                        if (h.P > 0) {
                            i4 |= 4;
                            h.g.clear();
                        }
                        listA = anVarA.a(i4, i3, stringExtra);
                        if (listA != null || listA.size() == 0) {
                            ae.a(h.w, "lebian_module_gameinfo", "queryUpdating");
                        }
                        if (listA != null) {
                            ae.a(h.w, "lebian_module_gameinfo", "queryType", i3);
                            ae.a(h.w, "lebian_module_gameinfo", "customChannel", stringExtra);
                            for (l lVar3 : listA) {
                                if (lVar3.isInstall || lVar3.sign || !booleanExtra4) {
                                    if (!lVar3.forceUpdate) {
                                        ae.a(h.w, "lebian_module_gameinfo", "isExistNotification");
                                    } else if (GameUtil.isBwbx(h.w) && anVarA.i()) {
                                        if (!ae.b(h.w, "lebian_module_dloadinfo", "success", false)) {
                                            synchronized (h.g) {
                                                if (h.g.size() > 0) {
                                                    Intent intent3 = new Intent("com.excelliance.open.action.gameverchk");
                                                    intent3.setPackage(h.this.M());
                                                    intent3.setComponent(new ComponentName(h.w, BGService.class.getName()));
                                                    intent3.putExtra("canceldownloading", true);
                                                    h.this.b(intent3);
                                                }
                                                h.this.x.b(this);
                                            }
                                            return;
                                        }
                                        h.this.d(new Intent(h.this.M() + BwbxUtil.ACTION_CANCEL_DOWNLOAD_ALL));
                                    }
                                    synchronized (h.g) {
                                        zContainsKey = h.g.containsKey(lVar3.dmd5);
                                    }
                                    boolean zD = al.d(h.w);
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "showed L" + zD + ", inDownload=" + zContainsKey, new Object[0]);
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge=" + lVar3.autoMerge + ", preDownload=" + lVar3.preDownload + " checkPeriod=" + booleanExtra2, new Object[0]);
                                    if (!lVar3.autoMerge && !lVar3.preDownload && !booleanExtra2) {
                                        h.this.a(lVar3, false, true);
                                        z2 = false;
                                    } else if (i3 > 0 || lVar3.preDownload || booleanExtra2 || h.this.L || lVar3.autoMerge || lVar3.rpred != 0) {
                                        z2 = false;
                                    } else {
                                        if ((!h.this.b(lVar3) || zContainsKey) && lVar3.forceUpdate && al.b(h.w)) {
                                            if (!zD) {
                                                h.this.a(lVar3, true, h.ah);
                                            } else if (h.ah) {
                                                boolean unused = h.ah = false;
                                                if (al.e(h.w)) {
                                                    h.this.a(lVar3, true, false, z3, true);
                                                } else {
                                                    h.this.a(lVar3, false, true);
                                                }
                                            }
                                        } else if (!zContainsKey && h.this.b(lVar3)) {
                                            int iB = ae.b(h.w, "lebian_module_prompt", "lastQueryPid", -1);
                                            int iB2 = ae.b(h.w, "lebian_module_prompt", "queryPid", -1);
                                            boolean zB2 = ae.b(h.w, "lebian_module_prompt", "refusedByUser", false);
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "run: lastQueryPid=" + iB + "run: queryPid=" + iB2 + "run: refusedByUser=" + zB2, new Object[0]);
                                            if (iB != iB2) {
                                                ae.a(h.w, "lebian_module_prompt", "refusedByUser", false);
                                            }
                                            boolean zB3 = anVarA.b(h.w);
                                            if (booleanExtra3 && !lVar3.forceUpdate && zB2 && iB == iB2 && iB2 != -1) {
                                                zB3 = true;
                                            }
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "run: ignoreByUser =" + zB3, new Object[0]);
                                            if (!ae.b(h.w, "lebian_module_prompt", "gameUpdate", false) && !zB3) {
                                                if (!zD) {
                                                    if (!h.ah || z3) {
                                                        h.this.a(lVar3, false, true, z3, lVar3.forceUpdate);
                                                    } else {
                                                        h.this.a(lVar3, false, true);
                                                    }
                                                    boolean unused2 = h.ah = false;
                                                    ae.a(h.w, "lebian_module_prompt", "lastQueryPid", iB2);
                                                } else if (lVar3.forceUpdate) {
                                                    if (al.e(h.w)) {
                                                        h.this.a(lVar3, true, false, z3, true);
                                                    } else if (h.ah) {
                                                        h.this.a(lVar3, false, true);
                                                    }
                                                    boolean unused3 = h.ah = false;
                                                }
                                                z2 = true;
                                            }
                                        }
                                        z2 = false;
                                    }
                                    if (lVar3.rpOnly) {
                                        if (lVar3.hotResSavePath == null) {
                                            continue;
                                        } else {
                                            if (!zContainsKey && h.this.L && h.this.b(lVar3) && lVar3.gameId.equals("20000")) {
                                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "show  notify", new Object[0]);
                                                Intent intent4 = new Intent("com.excelliance.open.action.gameverchk");
                                                intent4.setPackage(h.this.M());
                                                intent4.putExtra("detail", lVar3);
                                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "notify omd5=" + lVar3.omd5 + ", nmd5=" + lVar3.nmd5 + ", dmd5=" + lVar3.dmd5, new Object[0]);
                                                intent4.putExtra("autodl", false);
                                                intent4.putExtra("notificationdownload", 0);
                                                strM = h.this.M();
                                                ApplicationInfo applicationInfo = null;
                                                try {
                                                    packageManagerO = h.this.O();
                                                    try {
                                                        applicationInfo = packageManagerO.getApplicationInfo(strM, 0);
                                                    } catch (PackageManager.NameNotFoundException unused4) {
                                                    }
                                                } catch (PackageManager.NameNotFoundException unused5) {
                                                    packageManagerO = null;
                                                }
                                                if (Build.VERSION.SDK_INT >= 24) {
                                                    identifier = PlatformResources.getApkResources(h.w).getIdentifier("lebian_default_icon", "drawable", strM);
                                                } else {
                                                    identifier = 0;
                                                }
                                                if (identifier == 0) {
                                                    try {
                                                        identifier = h.this.O().getApplicationInfo(strM, 0).icon;
                                                    } catch (Exception unused6) {
                                                    }
                                                }
                                                notificationCreateNotification = GameUtil.createNotification(h.w, false, identifier, h.this.N().getString(h.this.N().getIdentifier("lebian_update_available", "string", strM)), String.format(h.this.N().getString(h.this.N().getIdentifier("lebian_dl_update_wifi", "string", strM)), Float.valueOf((((lVar3.size + lVar3.expanSize) + lVar3.tSize) + lVar3.hotResDSize) / 1048576.0f)), PendingIntent.getService(h.w, 0, intent4, com.excelliance.lbsdk.main.b.a(134217728)));
                                                if (notificationCreateNotification != null) {
                                                    notificationCreateNotification.defaults = 7;
                                                    notificationCreateNotification.flags |= 16;
                                                    h.this.m.startForeground(99, notificationCreateNotification);
                                                }
                                            }
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "startDownload:" + ae.b(h.w, "lebian_module_gameinfo", "startDownload", false) + ", checkReq:" + booleanExtra, new Object[0]);
                                            if (!z2) {
                                                if (((h.this.L || !h.this.b(lVar3)) && (h.this.L || !z3)) || h.a(lVar3.flag, 4) || !h.a(lVar3.flag, 131072) || booleanExtra) {
                                                    synchronized (h.h) {
                                                        synchronized (h.g) {
                                                            if (!h.g.containsKey(lVar3.dmd5) && !lVar3.autoMerge) {
                                                                h.h.put(lVar3.dmd5, lVar3);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge:" + lVar3.autoMerge + ", preDownload:" + lVar3.preDownload, new Object[0]);
                                            if (!lVar3.autoMerge && !booleanExtra) {
                                                synchronized (h.i) {
                                                    if (!h.i.containsKey(lVar3.nmd5)) {
                                                        h.i.put(lVar3.nmd5, lVar3);
                                                    }
                                                }
                                            }
                                        }
                                    } else if (TextUtils.isEmpty(lVar3.hotResUrl)) {
                                        if (lVar3.savePath == null) {
                                            continue;
                                        } else {
                                            if (!zContainsKey) {
                                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "show  notify", new Object[0]);
                                                Intent intent5 = new Intent("com.excelliance.open.action.gameverchk");
                                                intent5.setPackage(h.this.M());
                                                intent5.putExtra("detail", lVar3);
                                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "notify omd5=" + lVar3.omd5 + ", nmd5=" + lVar3.nmd5 + ", dmd5=" + lVar3.dmd5, new Object[0]);
                                                intent5.putExtra("autodl", false);
                                                intent5.putExtra("notificationdownload", 0);
                                                strM = h.this.M();
                                                ApplicationInfo applicationInfo2 = null;
                                                packageManagerO = h.this.O();
                                                applicationInfo2 = packageManagerO.getApplicationInfo(strM, 0);
                                                if (Build.VERSION.SDK_INT >= 24) {
                                                    identifier = PlatformResources.getApkResources(h.w).getIdentifier("lebian_default_icon", "drawable", strM);
                                                } else {
                                                    identifier = 0;
                                                }
                                                if (identifier == 0) {
                                                    identifier = h.this.O().getApplicationInfo(strM, 0).icon;
                                                }
                                                notificationCreateNotification = GameUtil.createNotification(h.w, false, identifier, h.this.N().getString(h.this.N().getIdentifier("lebian_update_available", "string", strM)), String.format(h.this.N().getString(h.this.N().getIdentifier("lebian_dl_update_wifi", "string", strM)), Float.valueOf((((lVar3.size + lVar3.expanSize) + lVar3.tSize) + lVar3.hotResDSize) / 1048576.0f)), PendingIntent.getService(h.w, 0, intent5, com.excelliance.lbsdk.main.b.a(134217728)));
                                                if (notificationCreateNotification != null) {
                                                    notificationCreateNotification.defaults = 7;
                                                    notificationCreateNotification.flags |= 16;
                                                    h.this.m.startForeground(99, notificationCreateNotification);
                                                }
                                            }
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "startDownload:" + ae.b(h.w, "lebian_module_gameinfo", "startDownload", false) + ", checkReq:" + booleanExtra, new Object[0]);
                                            if (!z2) {
                                                if (h.this.L) {
                                                }
                                                synchronized (h.h) {
                                                    synchronized (h.g) {
                                                        if (!h.g.containsKey(lVar3.dmd5)) {
                                                            h.h.put(lVar3.dmd5, lVar3);
                                                        }
                                                    }
                                                }
                                            }
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge:" + lVar3.autoMerge + ", preDownload:" + lVar3.preDownload, new Object[0]);
                                            if (!lVar3.autoMerge) {
                                                continue;
                                            }
                                        }
                                    } else if (lVar3.hotResSavePath != null && lVar3.savePath != null) {
                                        if (!zContainsKey) {
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "show  notify", new Object[0]);
                                            Intent intent6 = new Intent("com.excelliance.open.action.gameverchk");
                                            intent6.setPackage(h.this.M());
                                            intent6.putExtra("detail", lVar3);
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "notify omd5=" + lVar3.omd5 + ", nmd5=" + lVar3.nmd5 + ", dmd5=" + lVar3.dmd5, new Object[0]);
                                            intent6.putExtra("autodl", false);
                                            intent6.putExtra("notificationdownload", 0);
                                            strM = h.this.M();
                                            ApplicationInfo applicationInfo3 = null;
                                            packageManagerO = h.this.O();
                                            applicationInfo3 = packageManagerO.getApplicationInfo(strM, 0);
                                            if (Build.VERSION.SDK_INT >= 24) {
                                                identifier = PlatformResources.getApkResources(h.w).getIdentifier("lebian_default_icon", "drawable", strM);
                                            } else {
                                                identifier = 0;
                                            }
                                            if (identifier == 0) {
                                                identifier = h.this.O().getApplicationInfo(strM, 0).icon;
                                            }
                                            notificationCreateNotification = GameUtil.createNotification(h.w, false, identifier, h.this.N().getString(h.this.N().getIdentifier("lebian_update_available", "string", strM)), String.format(h.this.N().getString(h.this.N().getIdentifier("lebian_dl_update_wifi", "string", strM)), Float.valueOf((((lVar3.size + lVar3.expanSize) + lVar3.tSize) + lVar3.hotResDSize) / 1048576.0f)), PendingIntent.getService(h.w, 0, intent6, com.excelliance.lbsdk.main.b.a(134217728)));
                                            if (notificationCreateNotification != null) {
                                                notificationCreateNotification.defaults = 7;
                                                notificationCreateNotification.flags |= 16;
                                                h.this.m.startForeground(99, notificationCreateNotification);
                                            }
                                        }
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "startDownload:" + ae.b(h.w, "lebian_module_gameinfo", "startDownload", false) + ", checkReq:" + booleanExtra, new Object[0]);
                                        if (!z2) {
                                            if (h.this.L) {
                                            }
                                            synchronized (h.h) {
                                                synchronized (h.g) {
                                                    if (!h.g.containsKey(lVar3.dmd5)) {
                                                        h.h.put(lVar3.dmd5, lVar3);
                                                    }
                                                }
                                            }
                                        }
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge:" + lVar3.autoMerge + ", preDownload:" + lVar3.preDownload, new Object[0]);
                                        if (!lVar3.autoMerge) {
                                            continue;
                                        }
                                    }
                                } else if (!lVar3.preDownload) {
                                    Intent intent7 = new Intent("com.excelliance.open.action.fw");
                                    intent7.setPackage(h.w.getPackageName());
                                    intent7.setComponent(new ComponentName(h.w, BGService.class.getName()));
                                    intent7.putExtra("forceUpdate", lVar3.forceUpdate);
                                    intent7.putExtra("dialogtype", 11);
                                    intent7.putExtra("dealpath", h.this.T());
                                    h.this.b(intent7);
                                }
                            }
                            h.this.e(intent);
                            h.this.P();
                            ac acVarA = ac.a(h.w);
                            acVarA.a(acVarA.d());
                            anVarA.q();
                        } else {
                            h.this.e(intent);
                            h.this.P();
                            ac acVarA2 = ac.a(h.w);
                            acVarA2.a(acVarA2.d());
                            anVarA.q();
                        }
                        h.this.x.b(this);
                    }
                    i4 |= 1;
                    i3 = 0;
                    if (!h.e) {
                        i4 = i4 | 4 | 8 | 16;
                    }
                    if (anVarA.b(h.w)) {
                        i4 |= 4;
                    }
                    if (h.P > 0) {
                        i4 |= 4;
                        h.g.clear();
                    }
                    listA = anVarA.a(i4, i3, stringExtra);
                    if (listA != null) {
                        ae.a(h.w, "lebian_module_gameinfo", "queryUpdating");
                    } else {
                        ae.a(h.w, "lebian_module_gameinfo", "queryUpdating");
                    }
                    if (listA != null) {
                        ae.a(h.w, "lebian_module_gameinfo", "queryType", i3);
                        ae.a(h.w, "lebian_module_gameinfo", "customChannel", stringExtra);
                        while (r0.hasNext()) {
                            if (lVar3.isInstall) {
                            }
                            if (!lVar3.forceUpdate) {
                                ae.a(h.w, "lebian_module_gameinfo", "isExistNotification");
                            } else if (GameUtil.isBwbx(h.w)) {
                                if (!ae.b(h.w, "lebian_module_dloadinfo", "success", false)) {
                                    synchronized (h.g) {
                                        if (h.g.size() > 0) {
                                            Intent intent8 = new Intent("com.excelliance.open.action.gameverchk");
                                            intent8.setPackage(h.this.M());
                                            intent8.setComponent(new ComponentName(h.w, BGService.class.getName()));
                                            intent8.putExtra("canceldownloading", true);
                                            h.this.b(intent8);
                                        }
                                        h.this.x.b(this);
                                        return;
                                    }
                                }
                                h.this.d(new Intent(h.this.M() + BwbxUtil.ACTION_CANCEL_DOWNLOAD_ALL));
                            }
                            synchronized (h.g) {
                                zContainsKey = h.g.containsKey(lVar3.dmd5);
                                boolean zD2 = al.d(h.w);
                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "showed L" + zD2 + ", inDownload=" + zContainsKey, new Object[0]);
                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge=" + lVar3.autoMerge + ", preDownload=" + lVar3.preDownload + " checkPeriod=" + booleanExtra2, new Object[0]);
                                if (!lVar3.autoMerge) {
                                    if (i3 > 0) {
                                        z2 = false;
                                    } else {
                                        z2 = false;
                                    }
                                } else if (i3 > 0) {
                                    z2 = false;
                                } else {
                                    z2 = false;
                                }
                                if (lVar3.rpOnly) {
                                    if (lVar3.hotResSavePath == null) {
                                        continue;
                                    } else {
                                        if (!zContainsKey) {
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "show  notify", new Object[0]);
                                            Intent intent9 = new Intent("com.excelliance.open.action.gameverchk");
                                            intent9.setPackage(h.this.M());
                                            intent9.putExtra("detail", lVar3);
                                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "notify omd5=" + lVar3.omd5 + ", nmd5=" + lVar3.nmd5 + ", dmd5=" + lVar3.dmd5, new Object[0]);
                                            intent9.putExtra("autodl", false);
                                            intent9.putExtra("notificationdownload", 0);
                                            strM = h.this.M();
                                            ApplicationInfo applicationInfo4 = null;
                                            packageManagerO = h.this.O();
                                            applicationInfo4 = packageManagerO.getApplicationInfo(strM, 0);
                                            if (Build.VERSION.SDK_INT >= 24) {
                                                identifier = PlatformResources.getApkResources(h.w).getIdentifier("lebian_default_icon", "drawable", strM);
                                            } else {
                                                identifier = 0;
                                            }
                                            if (identifier == 0) {
                                                identifier = h.this.O().getApplicationInfo(strM, 0).icon;
                                            }
                                            notificationCreateNotification = GameUtil.createNotification(h.w, false, identifier, h.this.N().getString(h.this.N().getIdentifier("lebian_update_available", "string", strM)), String.format(h.this.N().getString(h.this.N().getIdentifier("lebian_dl_update_wifi", "string", strM)), Float.valueOf((((lVar3.size + lVar3.expanSize) + lVar3.tSize) + lVar3.hotResDSize) / 1048576.0f)), PendingIntent.getService(h.w, 0, intent9, com.excelliance.lbsdk.main.b.a(134217728)));
                                            if (notificationCreateNotification != null) {
                                                notificationCreateNotification.defaults = 7;
                                                notificationCreateNotification.flags |= 16;
                                                h.this.m.startForeground(99, notificationCreateNotification);
                                            }
                                        }
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "startDownload:" + ae.b(h.w, "lebian_module_gameinfo", "startDownload", false) + ", checkReq:" + booleanExtra, new Object[0]);
                                        if (!z2) {
                                            if (h.this.L) {
                                            }
                                            synchronized (h.h) {
                                                synchronized (h.g) {
                                                    if (!h.g.containsKey(lVar3.dmd5)) {
                                                        h.h.put(lVar3.dmd5, lVar3);
                                                    }
                                                }
                                            }
                                        }
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge:" + lVar3.autoMerge + ", preDownload:" + lVar3.preDownload, new Object[0]);
                                        if (!lVar3.autoMerge) {
                                            continue;
                                        }
                                    }
                                } else if (TextUtils.isEmpty(lVar3.hotResUrl)) {
                                    if (lVar3.hotResSavePath != null) {
                                        continue;
                                    }
                                } else if (lVar3.savePath == null) {
                                    continue;
                                } else {
                                    if (!zContainsKey) {
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "show  notify", new Object[0]);
                                        Intent intent10 = new Intent("com.excelliance.open.action.gameverchk");
                                        intent10.setPackage(h.this.M());
                                        intent10.putExtra("detail", lVar3);
                                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "notify omd5=" + lVar3.omd5 + ", nmd5=" + lVar3.nmd5 + ", dmd5=" + lVar3.dmd5, new Object[0]);
                                        intent10.putExtra("autodl", false);
                                        intent10.putExtra("notificationdownload", 0);
                                        strM = h.this.M();
                                        ApplicationInfo applicationInfo5 = null;
                                        packageManagerO = h.this.O();
                                        applicationInfo5 = packageManagerO.getApplicationInfo(strM, 0);
                                        if (Build.VERSION.SDK_INT >= 24) {
                                            identifier = PlatformResources.getApkResources(h.w).getIdentifier("lebian_default_icon", "drawable", strM);
                                        } else {
                                            identifier = 0;
                                        }
                                        if (identifier == 0) {
                                            identifier = h.this.O().getApplicationInfo(strM, 0).icon;
                                        }
                                        notificationCreateNotification = GameUtil.createNotification(h.w, false, identifier, h.this.N().getString(h.this.N().getIdentifier("lebian_update_available", "string", strM)), String.format(h.this.N().getString(h.this.N().getIdentifier("lebian_dl_update_wifi", "string", strM)), Float.valueOf((((lVar3.size + lVar3.expanSize) + lVar3.tSize) + lVar3.hotResDSize) / 1048576.0f)), PendingIntent.getService(h.w, 0, intent10, com.excelliance.lbsdk.main.b.a(134217728)));
                                        if (notificationCreateNotification != null) {
                                            notificationCreateNotification.defaults = 7;
                                            notificationCreateNotification.flags |= 16;
                                            h.this.m.startForeground(99, notificationCreateNotification);
                                        }
                                    }
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "startDownload:" + ae.b(h.w, "lebian_module_gameinfo", "startDownload", false) + ", checkReq:" + booleanExtra, new Object[0]);
                                    if (!z2) {
                                        if (h.this.L) {
                                        }
                                        synchronized (h.h) {
                                            synchronized (h.g) {
                                                if (!h.g.containsKey(lVar3.dmd5)) {
                                                    h.h.put(lVar3.dmd5, lVar3);
                                                }
                                            }
                                        }
                                    }
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "autoMerge:" + lVar3.autoMerge + ", preDownload:" + lVar3.preDownload, new Object[0]);
                                    if (!lVar3.autoMerge) {
                                        continue;
                                    }
                                }
                            }
                        }
                        h.this.e(intent);
                        h.this.P();
                        ac acVarA3 = ac.a(h.w);
                        acVarA3.a(acVarA3.d());
                        anVarA.q();
                    } else {
                        h.this.e(intent);
                        h.this.P();
                        ac acVarA4 = ac.a(h.w);
                        acVarA4.a(acVarA4.d());
                        anVarA.q();
                    }
                    h.this.x.b(this);
                }
            }.start();
            return;
        }
        final String str7 = lVar2.url;
        final String str8 = lVar2.urlBk;
        final String str9 = lVar2.urlBkSecond;
        final String str10 = lVar2.savePath;
        final String str11 = lVar2.omd5;
        final String str12 = lVar2.nmd5;
        final String str13 = lVar2.dmd5;
        final int i3 = lVar2.patch;
        final String str14 = str4;
        final long j2 = lVar2.size;
        final boolean z2 = lVar2.forceUpdate;
        final int i4 = lVar2.flag;
        final String str15 = lVar2.notifyTitle;
        final String str16 = lVar2.notifyMsg;
        final boolean z3 = lVar2.isInstall;
        final boolean z4 = lVar2.preDownload;
        final boolean z5 = lVar2.sign;
        final String str17 = lVar2.expanOmd5;
        final String str18 = lVar2.expanNmd5;
        final String str19 = lVar2.expanDmd5;
        final String str20 = lVar2.expanUrl;
        final String str21 = lVar2.expanUrlBk;
        String str22 = lVar2.expanSavePath;
        int i5 = lVar2.expanPatch;
        final long j3 = lVar2.expanSize;
        final boolean z6 = lVar2.prepay;
        final String str23 = lVar2.twiceLoadUrl;
        final String str24 = lVar2.twiceLoadUrlBk;
        final String str25 = lVar2.tmd5;
        final long j4 = lVar2.tSize;
        final String str26 = lVar2.expanSavePath + ".twice";
        final String str27 = lVar2.apkVercode;
        boolean z7 = lVar2.autoMerge;
        final String str28 = lVar2.includeDir;
        final String str29 = lVar2.excludeDir;
        final String str30 = lVar2.hotResUrl;
        final String str31 = lVar2.hotResUrlBk;
        String str32 = lVar2.hotResOmd5;
        final String str33 = lVar2.hotResDmd5;
        final String str34 = lVar2.hotResNmd5;
        final long j5 = lVar2.hotResDSize;
        long j6 = lVar2.hotResNSize;
        final String str35 = lVar2.hotResCfg;
        final String str36 = lVar2.hotResSavePath;
        String str37 = lVar2.hotResVersionCode;
        final int i6 = lVar2.hotResPatch;
        final int i7 = lVar2.rpred;
        final boolean z8 = lVar2.rpOnly;
        if (z7) {
            final String str38 = str2;
            new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.5
                @Override // java.lang.Runnable
                public void run() {
                    String str39;
                    String str40;
                    h.this.x.a(this);
                    boolean unused = h.Q = true;
                    synchronized (h.i) {
                        if (h.i.get(str12) == null) {
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "auto merge has done", new Object[0]);
                            return;
                        }
                        if (!new File(str10).exists()) {
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "old version is not exist", new Object[0]);
                            return;
                        }
                        if (intance.getVerCodeFromApk(str10, true) >= intance.getVerCodeFromApk(h.w.getPackageCodePath(), true)) {
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "auto merge apk is exist", new Object[0]);
                            return;
                        }
                        GameUtil.sendUpdateInfo(h.w, 7);
                        h.this.a(str38, 99, bundle, h.C, intent);
                        long j7 = j2;
                        String strA = anVarA.a(j7, false, str10);
                        if (strA != null) {
                            String str41 = strA + "base" + k.a(lVar2);
                            File file = new File(str41);
                            if (file.isDirectory()) {
                                file.mkdirs();
                            } else {
                                file.getParentFile().mkdirs();
                                file.delete();
                            }
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "targetApkPath: " + str41, new Object[0]);
                            if (h.this.a(anVarA.f(), str10, str41, h.this.b(str28), h.this.b(str29))) {
                                try {
                                    str40 = h.this.O().getApplicationInfo(h.this.M(), 0).sourceDir;
                                } catch (Exception unused2) {
                                    str40 = null;
                                }
                                ac acVarA = ac.a(h.w);
                                acVarA.a(null, str41, i3, str12);
                                acVarA.b(str40, str41);
                                acVarA.a(str40, str41);
                                String str42 = str41.substring(0, str41.lastIndexOf("/") + 1) + str6 + ".cfg";
                                String str43 = lVar2.savePath;
                                lVar2.savePath = str41;
                                anVarA.a(str42, lVar2, true);
                                ae.a(h.w, "lebian_module_extractinfo", "preinstall");
                                ae.a(h.w, "lebian_module_extractinfo", "installed");
                                ae.a(h.w, "lebian_module_extractinfo", "next", str42);
                                File parentFile = new File(str43).getParentFile();
                                if (parentFile != null) {
                                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "deleteDir=" + parentFile.getAbsolutePath(), new Object[0]);
                                    GameUtil.deleteDir(parentFile);
                                }
                                h.this.S();
                                anVarA.a(str42, str41);
                                h.this.an = true;
                                h.this.a(h.w, z4);
                                h.a(100, false);
                                h.this.a(str38, 100, bundle, h.C, intent);
                            }
                        } else {
                            int identifier = h.this.N().getIdentifier("lebian_space_not_enough_for_generating_new_apk_file_new", "string", h.this.M());
                            if (identifier != 0) {
                                str39 = String.format(h.this.N().getString(identifier), Float.valueOf((j7 + 102400) / 1048576.0f));
                            } else {
                                str39 = String.format(h.this.N().getString(h.this.N().getIdentifier("lebian_space_not_enough_for_generating_new_apk_file", "string", h.this.M())) + "(%.2fMB)", Float.valueOf((j7 + 102400) / 1048576.0f));
                            }
                            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "space not enough for generating new apk file", new Object[0]);
                            h.this.a(str38, str39, intent);
                        }
                        h.i.remove(str12);
                        h.this.m.stopForeground(true);
                        h.this.x.b(this);
                    }
                }
            }).start();
            return;
        }
        boolean z9 = aa.a(w) && !a(i4, 4) && a(i4, 131072);
        if (a(i4, 2097152)) {
            return;
        }
        if (!z4) {
            if (i7 == 1) {
                intExtra2 = -1;
            } else {
                if (intExtra2 < 0) {
                    if (z2) {
                        if (z2) {
                            hVar = this;
                            if (hVar.L) {
                            }
                            intExtra2 = 1;
                        } else {
                            hVar = this;
                        }
                        if (zB) {
                            intExtra2 = 1;
                        }
                    } else {
                        hVar = this;
                    }
                    if (booleanExtra7) {
                        if (zB) {
                        }
                    }
                    intExtra2 = 1;
                }
                C = intExtra2;
                if (i3 != 5 || z8) {
                    aq = true;
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "noApkGenerate:" + aq, new Object[0]);
                }
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "gameId:" + str2 + " gsize:" + j2 + " expSize:" + j3 + " queryUpdate:" + booleanExtra3 + " startQuery:" + booleanExtra4 + " check:" + booleanExtra + " gameLib:" + str6 + " url:" + str7 + " urlBk:" + str8 + " urlBkSecond:" + str9 + "  savePath:" + str10 + " dmd5:" + str13 + " autodl:" + booleanExtra7 + " alarmId:" + intExtra + " alarmCheck:" + booleanExtra5 + " nt:" + C + " isInstall:" + z3 + " preDownload:" + z4 + " patch:" + i3 + " expurl:" + str20 + " expurlbk:" + str21 + " expOmd5:" + str17 + " expDmd5:" + str19 + " expNmd5:" + str18 + " expSavePath:" + str22 + " expPatch:" + i5 + " prepay:" + z6 + " hotResUrl:" + str30 + " hotResUrlBk:" + str31 + " hotResMd5:" + str33 + " hotResSize:" + j5 + " hotResVersion:" + str37, new Object[0]);
                synchronized (g) {
                    try {
                        if (g.containsKey(str13)) {
                            try {
                                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "already in map " + str2, new Object[0]);
                                if (booleanExtra7 || z2) {
                                    l lVar3 = g.get(str13);
                                    lVar3.autodl = booleanExtra7;
                                    lVar3.flag = i4;
                                    lVar3.forceUpdate = z2;
                                    if ((z2 || (z2 && hVar.L)) && !booleanExtra7 && !z4 && i7 == 0) {
                                        C = 1;
                                        A = true;
                                    }
                                }
                                if (!booleanExtra) {
                                    try {
                                        synchronized (j) {
                                            try {
                                                if (j.contains(str13) || j.contains(str19)) {
                                                    a(str2, 99, bundle, C, intent);
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                                return;
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        }
                        hVar2 = hVar;
                        if (g.size() == 0) {
                            hVar2.j(5);
                        }
                        lVar = new l(str2, str6, str7, str8, str9, str10, null, 0L, str14, str5, "1", z2, i3, str11, str12, str13, booleanExtra7, i4, str15, str16, z3, z4, z5, str17, str19, str18, 0L, str20, str21, str22, i5, z6, str23, str24, str25, j4, lVar2.rc, str27, str28, str29, str30, str31, str32, str33, str34, j6, j5, str37, i6, str36, str35, i7, z8);
                        g.put(str13, lVar);
                        if (i3 != -1 && !z8) {
                            hVar2.S.put(1, new l(1, str7, str8, str9, str10, i3, str11, str12, str13, z6));
                        }
                        if (TextUtils.isEmpty(str20)) {
                            i2 = i5;
                        } else {
                            i2 = i5;
                            if (i2 == -1 && j3 != 0) {
                                hVar2.S.put(2, new l(2, str20, str21, str20, str22, i2, str17, str19, str18, z6));
                            }
                        }
                        if (TextUtils.isEmpty(lVar.twiceLoadUrl)) {
                            str = str22;
                        } else {
                            HashMap<Integer, l> map = hVar2.S;
                            StringBuilder sb = new StringBuilder();
                            str = str22;
                            sb.append(str);
                            sb.append(".twice");
                            map.put(3, new l(3, str23, str24, str25, j4, sb.toString()));
                        }
                        if (!TextUtils.isEmpty(lVar.hotResUrl)) {
                            hVar2.S.put(4, new l(4, str30, str31, str33, j5, str36));
                        }
                        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "put into downloadMap " + str13, new Object[0]);
                        if (aa.b(w)) {
                            if (aa.a(w)) {
                                v = 1;
                            } else if (!aa.a(w)) {
                                v = 2;
                            }
                        }
                        b();
                        GameUtil.sendUpdateInfo(w, 6);
                        final String str39 = str2;
                        final int i8 = i2;
                        final String str40 = str;
                        final boolean z10 = z9;
                        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.6
                            /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
                            /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
                                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.instructions.PhiInsn.getResult()" is null
                                	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:117)
                                	at jadx.core.dex.visitors.InitCodeVariables.lambda$collectConnectedVars$1(InitCodeVariables.java:124)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:121)
                                	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
                                */
                            @Override // java.lang.Runnable
                            public void run() {
                                /*
                                    Method dump skipped, instruction units count: 16761
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.h.AnonymousClass6.run():void");
                            }
                        }, "DownloadComponentService:DownloadComponentService1").start();
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            }
            hVar = this;
            C = intExtra2;
            if (i3 != 5) {
                aq = true;
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "noApkGenerate:" + aq, new Object[0]);
            } else {
                aq = true;
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "noApkGenerate:" + aq, new Object[0]);
            }
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "gameId:" + str2 + " gsize:" + j2 + " expSize:" + j3 + " queryUpdate:" + booleanExtra3 + " startQuery:" + booleanExtra4 + " check:" + booleanExtra + " gameLib:" + str6 + " url:" + str7 + " urlBk:" + str8 + " urlBkSecond:" + str9 + "  savePath:" + str10 + " dmd5:" + str13 + " autodl:" + booleanExtra7 + " alarmId:" + intExtra + " alarmCheck:" + booleanExtra5 + " nt:" + C + " isInstall:" + z3 + " preDownload:" + z4 + " patch:" + i3 + " expurl:" + str20 + " expurlbk:" + str21 + " expOmd5:" + str17 + " expDmd5:" + str19 + " expNmd5:" + str18 + " expSavePath:" + str22 + " expPatch:" + i5 + " prepay:" + z6 + " hotResUrl:" + str30 + " hotResUrlBk:" + str31 + " hotResMd5:" + str33 + " hotResSize:" + j5 + " hotResVersion:" + str37, new Object[0]);
            synchronized (g) {
                if (g.containsKey(str13)) {
                    com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "already in map " + str2, new Object[0]);
                    if (booleanExtra7) {
                        l lVar4 = g.get(str13);
                        lVar4.autodl = booleanExtra7;
                        lVar4.flag = i4;
                        lVar4.forceUpdate = z2;
                        if (z2) {
                            C = 1;
                            A = true;
                        } else {
                            C = 1;
                            A = true;
                        }
                    } else {
                        l lVar5 = g.get(str13);
                        lVar5.autodl = booleanExtra7;
                        lVar5.flag = i4;
                        lVar5.forceUpdate = z2;
                        if (z2) {
                            C = 1;
                            A = true;
                        } else {
                            C = 1;
                            A = true;
                        }
                    }
                    if (!booleanExtra) {
                        synchronized (j) {
                            if (j.contains(str13)) {
                                a(str2, 99, bundle, C, intent);
                            } else {
                                a(str2, 99, bundle, C, intent);
                            }
                        }
                    }
                    return;
                }
                hVar2 = hVar;
                if (g.size() == 0) {
                    hVar2.j(5);
                }
                lVar = new l(str2, str6, str7, str8, str9, str10, null, 0L, str14, str5, "1", z2, i3, str11, str12, str13, booleanExtra7, i4, str15, str16, z3, z4, z5, str17, str19, str18, 0L, str20, str21, str22, i5, z6, str23, str24, str25, j4, lVar2.rc, str27, str28, str29, str30, str31, str32, str33, str34, j6, j5, str37, i6, str36, str35, i7, z8);
                g.put(str13, lVar);
                if (i3 != -1) {
                    hVar2.S.put(1, new l(1, str7, str8, str9, str10, i3, str11, str12, str13, z6));
                }
                if (TextUtils.isEmpty(str20)) {
                    i2 = i5;
                    if (i2 == -1) {
                    }
                } else {
                    i2 = i5;
                }
                if (TextUtils.isEmpty(lVar.twiceLoadUrl)) {
                    HashMap<Integer, l> map2 = hVar2.S;
                    StringBuilder sb2 = new StringBuilder();
                    str = str22;
                    sb2.append(str);
                    sb2.append(".twice");
                    map2.put(3, new l(3, str23, str24, str25, j4, sb2.toString()));
                } else {
                    str = str22;
                }
                if (!TextUtils.isEmpty(lVar.hotResUrl)) {
                    hVar2.S.put(4, new l(4, str30, str31, str33, j5, str36));
                }
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "put into downloadMap " + str13, new Object[0]);
                if (aa.b(w)) {
                    if (aa.a(w)) {
                        v = 1;
                    } else if (!aa.a(w)) {
                        v = 2;
                    }
                }
                b();
                GameUtil.sendUpdateInfo(w, 6);
                final String str310 = str2;
                final int i9 = i2;
                final String str41 = str;
                final boolean z11 = z9;
                new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.6
                    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
                    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
                        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.instructions.PhiInsn.getResult()" is null
                        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:117)
                        	at jadx.core.dex.visitors.InitCodeVariables.lambda$collectConnectedVars$1(InitCodeVariables.java:124)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:121)
                        */
                    @Override // java.lang.Runnable
                    public void run() {
                        /*
                            Method dump skipped, instruction units count: 16761
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.h.AnonymousClass6.run():void");
                    }
                }, "DownloadComponentService:DownloadComponentService1").start();
            }
        }
        hVar = this;
        intExtra2 = -1;
        C = intExtra2;
        if (i3 != 5) {
            aq = true;
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "noApkGenerate:" + aq, new Object[0]);
        } else {
            aq = true;
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "noApkGenerate:" + aq, new Object[0]);
        }
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "gameId:" + str2 + " gsize:" + j2 + " expSize:" + j3 + " queryUpdate:" + booleanExtra3 + " startQuery:" + booleanExtra4 + " check:" + booleanExtra + " gameLib:" + str6 + " url:" + str7 + " urlBk:" + str8 + " urlBkSecond:" + str9 + "  savePath:" + str10 + " dmd5:" + str13 + " autodl:" + booleanExtra7 + " alarmId:" + intExtra + " alarmCheck:" + booleanExtra5 + " nt:" + C + " isInstall:" + z3 + " preDownload:" + z4 + " patch:" + i3 + " expurl:" + str20 + " expurlbk:" + str21 + " expOmd5:" + str17 + " expDmd5:" + str19 + " expNmd5:" + str18 + " expSavePath:" + str22 + " expPatch:" + i5 + " prepay:" + z6 + " hotResUrl:" + str30 + " hotResUrlBk:" + str31 + " hotResMd5:" + str33 + " hotResSize:" + j5 + " hotResVersion:" + str37, new Object[0]);
        synchronized (g) {
            if (g.containsKey(str13)) {
                com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "already in map " + str2, new Object[0]);
                if (booleanExtra7) {
                    l lVar6 = g.get(str13);
                    lVar6.autodl = booleanExtra7;
                    lVar6.flag = i4;
                    lVar6.forceUpdate = z2;
                    if (z2) {
                        C = 1;
                        A = true;
                    } else {
                        C = 1;
                        A = true;
                    }
                } else {
                    l lVar7 = g.get(str13);
                    lVar7.autodl = booleanExtra7;
                    lVar7.flag = i4;
                    lVar7.forceUpdate = z2;
                    if (z2) {
                        C = 1;
                        A = true;
                    } else {
                        C = 1;
                        A = true;
                    }
                }
                if (!booleanExtra) {
                    synchronized (j) {
                        if (j.contains(str13)) {
                            a(str2, 99, bundle, C, intent);
                        } else {
                            a(str2, 99, bundle, C, intent);
                        }
                    }
                }
                return;
            }
            hVar2 = hVar;
            if (g.size() == 0) {
                hVar2.j(5);
            }
            lVar = new l(str2, str6, str7, str8, str9, str10, null, 0L, str14, str5, "1", z2, i3, str11, str12, str13, booleanExtra7, i4, str15, str16, z3, z4, z5, str17, str19, str18, 0L, str20, str21, str22, i5, z6, str23, str24, str25, j4, lVar2.rc, str27, str28, str29, str30, str31, str32, str33, str34, j6, j5, str37, i6, str36, str35, i7, z8);
            g.put(str13, lVar);
            if (i3 != -1) {
                hVar2.S.put(1, new l(1, str7, str8, str9, str10, i3, str11, str12, str13, z6));
            }
            if (TextUtils.isEmpty(str20)) {
                i2 = i5;
                if (i2 == -1) {
                }
            } else {
                i2 = i5;
            }
            if (TextUtils.isEmpty(lVar.twiceLoadUrl)) {
                HashMap<Integer, l> map3 = hVar2.S;
                StringBuilder sb3 = new StringBuilder();
                str = str22;
                sb3.append(str);
                sb3.append(".twice");
                map3.put(3, new l(3, str23, str24, str25, j4, sb3.toString()));
            } else {
                str = str22;
            }
            if (!TextUtils.isEmpty(lVar.hotResUrl)) {
                hVar2.S.put(4, new l(4, str30, str31, str33, j5, str36));
            }
            com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "put into downloadMap " + str13, new Object[0]);
            if (aa.b(w)) {
                if (aa.a(w)) {
                    v = 1;
                } else if (!aa.a(w)) {
                    v = 2;
                }
            }
            b();
            GameUtil.sendUpdateInfo(w, 6);
            final String str311 = str2;
            final int i10 = i2;
            final String str42 = str;
            final boolean z12 = z9;
            new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.h.6
                /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
                /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
                    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.instructions.PhiInsn.getResult()" is null
                    	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:117)
                    	at jadx.core.dex.visitors.InitCodeVariables.lambda$collectConnectedVars$1(InitCodeVariables.java:124)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                    */
                @Override // java.lang.Runnable
                public void run() {
                    /*
                        Method dump skipped, instruction units count: 16761
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.h.AnonymousClass6.run():void");
                }
            }, "DownloadComponentService:DownloadComponentService1").start();
        }
    }

    public void a(Bundle bundle) {
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "setStartForegroundBgServiceWithTitle : " + bundle, new Object[0]);
        Intent intent = new Intent(w, (Class<?>) BGService.class);
        intent.putExtra("startForground", true);
        if (al.b(w) && bundle != null) {
            intent.putExtra("notificationMsg", bundle);
        }
        b(intent);
    }

    public void a(String str, String str2, Intent intent) {
        a(str, str2, -1, (String) null, intent);
    }

    public boolean a(String str, String str2, int i2) {
        return NativeUtil.addInApkInfo(str, str2, i2);
    }

    public boolean a(String str, String str2, String str3, int i2, int i3) {
        return NativeUtil.upgradeSmallByPatchFile(str, str2, str3, i2, i3);
    }

    public boolean a(String str, String str2, String str3, int i2, int i3, int i4) {
        return NativeUtil.upgradeHotResByPatchFile(str, str2, str3, i2, i3, i4);
    }

    public boolean a(String str, String str2, String str3, String str4, String str5, int i2) {
        return NativeUtil.upgradeByPatch(str, str2, str3, str4, str5, i2);
    }

    public boolean a(String[] strArr, String str, String str2) {
        return NativeUtil.upgradeByPatchNoGenApk(strArr, str, str2);
    }

    public boolean a(String[] strArr, String str, String str2, String str3, String str4, int i2, int i3) {
        return NativeUtil.upgradeByPatchFile(strArr, str, str2, str3, str4, i2, i3);
    }

    public boolean a(String[] strArr, String str, String str2, String[] strArr2, String[] strArr3) {
        return NativeUtil.mergeApkWithUpdatedVersion(strArr, str, str2, strArr2, strArr3);
    }

    void b() throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        String str = GameUtil.getDataRoot(w) + "userinfo.cfg";
        File file = new File(str);
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "clearDownloadStatus", new Object[0]);
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
                    String string = sb.toString();
                    int iIndexOf = string.indexOf("<info downloadstatus=");
                    while (iIndexOf >= 0) {
                        int iIndexOf2 = string.indexOf(">", iIndexOf + 1);
                        sb.delete(iIndexOf, iIndexOf2 + 2);
                        String string2 = sb.toString();
                        iIndexOf = string2.indexOf("<info downloadstatus=", iIndexOf2 + 1);
                        string = string2;
                    }
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                        try {
                            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, "rw");
                            randomAccessFile.write(sb.toString().getBytes());
                            randomAccessFile.close();
                            fileOutputStream.close();
                            file2.renameTo(file);
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream.close();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
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

    public void b(Context context) {
        s.a(context, this.ar);
    }

    public boolean b(String[] strArr, String str, String str2, String str3, String str4, int i2, int i3) {
        return NativeUtil.upgradeByPatchFileAndRemoveSign(strArr, str, str2, str3, str4, i2, i3);
    }

    public void c(Context context) {
        boolean z;
        try {
            InputStream inputStreamOpen = context.getAssets().open("lbbwbxdemo");
            if (inputStreamOpen != null) {
                inputStreamOpen.close();
            }
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        com.excelliance.lbsdk.main.g.b.c("DownloadComponent", "is demo:" + z, new Object[0]);
        if (z) {
            Message message = new Message();
            message.obj = context;
            message.what = 1;
            this.R.sendMessage(message);
        }
    }
}
