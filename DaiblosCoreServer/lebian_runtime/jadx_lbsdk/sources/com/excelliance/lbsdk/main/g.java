package com.excelliance.lbsdk.main;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.Xml;
import com.excelliance.lbsdk.main.bwbx.AssetRedirector;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private static int C;
    private static HashMap<Integer, ArrayList<Integer>> G;
    private static g a;
    private HandlerThread A;
    private Handler B;
    private HandlerThread D;
    private Handler E;
    private double I;
    private double J;
    private int M;
    private int Q;
    private Context b;
    private c k;
    private Handler z;
    private volatile int c = 0;
    private volatile int d = 0;
    private volatile String e = null;
    private volatile long f = 600;
    private volatile int g = 1000;
    private volatile String h = null;
    private volatile String i = null;
    private volatile int j = 0;
    private ArrayList<Integer> l = new ArrayList<>();
    private ArrayList<Integer> m = new ArrayList<>();
    private CopyOnWriteArrayList<String> n = new CopyOnWriteArrayList<>();
    private List<Integer> o = new ArrayList();
    private volatile int p = 0;
    private ExecutorService q = Executors.newCachedThreadPool();
    private volatile int r = 0;
    private HashMap<String, Integer> s = new HashMap<>();
    private List<String> t = new ArrayList();
    private HashSet<Integer> u = new HashSet<>();
    private int v = 0;
    private long w = 604800000;
    private long x = 0;
    private AtomicInteger F = new AtomicInteger(-1);
    private AtomicInteger H = new AtomicInteger(0);
    private int K = -1;
    private int L = -1;
    private SparseArray<Double> N = new SparseArray<>();
    private final Object O = new Object();
    private Map<String, BigFileInfo> P = new LinkedHashMap();
    private volatile long R = 60;
    private volatile int S = 300;
    private volatile int T = -1;
    private AtomicInteger U = new AtomicInteger(-1);
    private HandlerThread y = new HandlerThread("dplm");

    private class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                g.this.b(false);
                g.this.m();
            }
        }
    }

    private static class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                g.a().f();
                g.a().g();
            } else if (i == 1) {
                g.a().b();
                g.a().h();
            } else {
                if (i != 2) {
                    return;
                }
                g.a().c((String) message.obj);
            }
        }
    }

    public interface c {
        void a(List<String> list);
    }

    private static class d extends Handler {
        public d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            g.a().p();
            g.a().i();
        }
    }

    public g() {
        this.y.start();
        this.z = new b(this.y.getLooper());
        this.A = new HandlerThread("mutlti_file_record");
        this.A.start();
        this.B = new d(this.A.getLooper());
        this.D = new HandlerThread("calculate_variance");
        this.D.start();
        this.E = new a(this.D.getLooper());
    }

    public static g a() {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g();
                }
            }
        }
        return a;
    }

    private List<String> a(InputStream inputStream) {
        ArrayList<String> arrayList = new ArrayList();
        if (inputStream != null) {
            try {
                for (String str : new BufferedReader(new InputStreamReader(inputStream)).readLine().split(",")) {
                    int i = Integer.parseInt(str.trim());
                    if (this.u.contains(Integer.valueOf(i))) {
                        arrayList.add(this.t.get(i - 1));
                    }
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("dplm", "new fileset gen:*****************start**************", new Object[0]);
                for (String str2 : arrayList) {
                    com.excelliance.lbsdk.main.g.b.b("dplm", this.s.get(str2) + ":" + str2, new Object[0]);
                }
                com.excelliance.lbsdk.main.g.b.b("dplm", "new fileset gen:*****************end**************", new Object[0]);
            }
        }
        return arrayList;
    }

    private void a(File file, String str) {
        com.excelliance.lbsdk.main.g.b.c("dplm", "unZipNewStrategy start", new Object[0]);
        try {
            GameUtil.getIntance().unZipFile(file, str, true);
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(boolean z) {
        try {
            AssetRedirector.setDeepLearnStop(z);
        } catch (Throwable unused) {
            com.excelliance.lbsdk.main.g.b.c("dplm", "so my not load success", new Object[0]);
        }
    }

    /* JADX WARN: Code duplicated, block: B:127:0x01dc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:131:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:0x01f9  */
    private boolean a(Context context, String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        boolean z;
        String strComputeFileMd5;
        boolean zEndsWith = str.endsWith(".zip");
        if (zEndsWith) {
            sb = new StringBuilder();
            sb.append(GameUtil.getLbDeepLearnPath(context));
            str4 = "newFileset.zip";
        } else {
            sb = new StringBuilder();
            sb.append(GameUtil.getLbDeepLearnPath(context));
            str4 = "newFileset.txt";
        }
        sb.append(str4);
        String string = sb.toString();
        String str5 = string + ".dload";
        File file = new File(str5);
        try {
            if (file.exists()) {
                file.delete();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        com.excelliance.lbsdk.main.g.b.c("dplm", "downloadNewFileSet path = " + string, new Object[0]);
        String str6 = str;
        boolean z2 = false;
        boolean z3 = false;
        while (!z2) {
            try {
                try {
                    try {
                        HttpURLConnection httpURLConnectionA = aa.a(new URL(str6), context);
                        httpURLConnectionA.setConnectTimeout(30000);
                        httpURLConnectionA.setReadTimeout(30000);
                        httpURLConnectionA.setRequestProperty("Accept-Encoding", "gzip,deflate");
                        if (httpURLConnectionA.getContentLength() + 1048576 > BwbxUtil.getFreeSpace(string)) {
                            if (!file.exists()) {
                                break;
                            }
                            file.delete();
                            break;
                        }
                        try {
                            if (httpURLConnectionA.getResponseCode() == 200) {
                                byte[] bArr = new byte[8192];
                                InputStream bufferedInputStream = new BufferedInputStream(httpURLConnectionA.getInputStream());
                                String contentEncoding = httpURLConnectionA.getContentEncoding();
                                if (contentEncoding != null && contentEncoding.contains("gzip")) {
                                    bufferedInputStream = new GZIPInputStream(bufferedInputStream);
                                }
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str5));
                                try {
                                    while (true) {
                                        int i = bufferedInputStream.read(bArr);
                                        z = z2;
                                        if (i == -1) {
                                            break;
                                        }
                                        try {
                                            bufferedOutputStream.write(bArr, 0, i);
                                            z2 = z;
                                        } catch (Exception e2) {
                                            e = e2;
                                        }
                                        e.printStackTrace();
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        str6 = str2;
                                        z2 = z;
                                    }
                                    if (str3.equals(strComputeFileMd5)) {
                                        File file2 = new File(string);
                                        file2.delete();
                                        file.renameTo(file2);
                                        try {
                                            GameUtil.deleteDir(file2.getParentFile(), file2.getName());
                                            if (zEndsWith) {
                                                try {
                                                    a(file2, GameUtil.getLbDeepLearnPath(context));
                                                    com.excelliance.lbsdk.main.g.b.c("dplm", "downloadNewFileSet unzip", new Object[0]);
                                                    this.T = 2;
                                                    synchronized (this.O) {
                                                        try {
                                                            k();
                                                            this.U.set(this.F.get());
                                                            l();
                                                            m();
                                                        } catch (Throwable th) {
                                                            throw th;
                                                        }
                                                    }
                                                    b(true);
                                                    try {
                                                        com.excelliance.lbsdk.main.g.b.c("dplm", "multi linear use new st", new Object[0]);
                                                    } catch (Exception e3) {
                                                        e = e3;
                                                        z3 = true;
                                                        z = true;
                                                        e.printStackTrace();
                                                        if (file.exists()) {
                                                            file.delete();
                                                        }
                                                        str6 = str2;
                                                        z2 = z;
                                                    }
                                                } catch (Exception e4) {
                                                    e = e4;
                                                }
                                            } else {
                                                o();
                                                this.U.set(0);
                                                this.F.set(0);
                                                this.T = 1;
                                                this.k.a(d(string));
                                            }
                                            z3 = true;
                                            z = true;
                                        } catch (Exception e5) {
                                            e = e5;
                                        }
                                    } else {
                                        com.excelliance.lbsdk.main.g.b.e("dplm", "new fileset md5 is wrong", new Object[0]);
                                        file.delete();
                                        if (str6.equals(str2)) {
                                            z = true;
                                        }
                                    }
                                    z2 = z;
                                    httpURLConnectionA.disconnect();
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                } catch (Exception e6) {
                                    e = e6;
                                }
                                bufferedInputStream.close();
                                bufferedOutputStream.flush();
                                bufferedOutputStream.close();
                                strComputeFileMd5 = BwbxUtil.computeFileMd5(str5);
                                com.excelliance.lbsdk.main.g.b.c("dplm", "new fileSet md5 = " + strComputeFileMd5, new Object[0]);
                            } else {
                                z = z2;
                                if (str6.equals(str2)) {
                                    z = true;
                                }
                            }
                            httpURLConnectionA.disconnect();
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (Exception e7) {
                            e = e7;
                            z = z2;
                            e.printStackTrace();
                            if (file.exists()) {
                                file.delete();
                            }
                            str6 = str2;
                            z2 = z;
                        }
                        str6 = str2;
                        z2 = z;
                    } catch (Exception e8) {
                        e = e8;
                        z = z2;
                        e.printStackTrace();
                        if (file.exists()) {
                            file.delete();
                        }
                        str6 = str2;
                        z2 = z;
                    }
                } catch (Exception e9) {
                    e = e9;
                }
            } catch (Throwable th2) {
                if (file.exists()) {
                    file.delete();
                }
                throw th2;
            }
        }
        return z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:122:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:125:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:127:0x02f0  */
    /* JADX WARN: Code duplicated, block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:150:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x0195  */
    public void b(Context context, int i, String str, c cVar) {
        StringBuilder sb;
        String str2;
        String str3;
        boolean z;
        String attributeValue;
        String attributeValue2;
        boolean z2;
        String str4;
        boolean z3;
        boolean z4;
        File file = new File(GameUtil.getCachePath(this.b) + "newFileset.txt");
        if (file.exists()) {
            File file2 = new File(GameUtil.getLbDeepLearnPath(this.b) + "newFileset.txt");
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            file.renameTo(file2);
        }
        int i2 = 1;
        this.v = 1;
        GameUtil intance = GameUtil.getIntance();
        String str5 = intance.getBaseUrl("f") + "pickstream.php";
        this.k = cVar;
        this.i = str;
        StringBuilder sb2 = new StringBuilder(str5);
        String str6 = null;
        if (this.e == null) {
            this.e = ae.b(context, "lebian_module_userinfo", "uid", (String) null);
        }
        if (this.e == null) {
            sb = new StringBuilder();
            str2 = "?chid=";
        } else {
            sb2.append("?uid=" + this.e);
            sb = new StringBuilder();
            str2 = "&chid=";
        }
        sb.append(str2);
        sb.append(intance.getApkMainCh());
        sb2.append(sb.toString());
        sb2.append("&vercode=" + i);
        sb2.append("&sdkver=" + intance.getMainVer());
        sb2.append("&md5=" + str);
        sb2.append("&cpmeta=" + intance.getClientChId());
        sb2.append("&stcode=" + this.c);
        sb2.append("&stversion=" + this.d);
        HashMap<String, Object> mapB = aa.b(sb2.toString(), context);
        if (((Integer) mapB.get("code")).intValue() == 200) {
            String str7 = (String) mapB.get("response");
            if (str7 == null || str7.length() == 0) {
                return;
            }
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            try {
                xmlPullParserNewPullParser.setInput(new StringReader(str7));
                int eventType = xmlPullParserNewPullParser.getEventType();
                String str8 = null;
                attributeValue = null;
                attributeValue2 = null;
                boolean z5 = false;
                while (eventType != i2) {
                    if (eventType == 0 || eventType != 2) {
                        z5 = z5;
                    } else {
                        try {
                            if (xmlPullParserNewPullParser.getName().equalsIgnoreCase("stinfo")) {
                                this.h = xmlPullParserNewPullParser.getAttributeValue(str6, "up");
                                String attributeValue3 = xmlPullParserNewPullParser.getAttributeValue(str6, "dpath");
                                try {
                                    attributeValue = xmlPullParserNewPullParser.getAttributeValue(str6, "dpathbk");
                                    String attributeValue4 = xmlPullParserNewPullParser.getAttributeValue(str6, "shutoff");
                                    String attributeValue5 = xmlPullParserNewPullParser.getAttributeValue(str6, "shuttime");
                                    String attributeValue6 = xmlPullParserNewPullParser.getAttributeValue(str6, "time");
                                    String attributeValue7 = xmlPullParserNewPullParser.getAttributeValue(str6, "number");
                                    String attributeValue8 = xmlPullParserNewPullParser.getAttributeValue(str6, "stcode");
                                    String attributeValue9 = xmlPullParserNewPullParser.getAttributeValue(str6, "stversion");
                                    String attributeValue10 = xmlPullParserNewPullParser.getAttributeValue(str6, "revisetime");
                                    z2 = z5;
                                    try {
                                        String attributeValue11 = xmlPullParserNewPullParser.getAttributeValue(str6, "revisenumber");
                                        str4 = attributeValue3;
                                        try {
                                            attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(str6, "stmd5");
                                            if (attributeValue6 != null) {
                                                this.f = Long.parseLong(attributeValue6);
                                            }
                                            if (attributeValue7 != null) {
                                                this.g = Integer.parseInt(attributeValue7);
                                            }
                                            if (attributeValue5 != null) {
                                                this.w = Long.parseLong(attributeValue5) * 1000;
                                            }
                                            int i3 = attributeValue8 != null ? Integer.parseInt(attributeValue8) : 0;
                                            int i4 = attributeValue9 != null ? Integer.parseInt(attributeValue9) : 0;
                                            if (this.c != i3) {
                                                try {
                                                    this.c = i3;
                                                    z3 = true;
                                                } catch (IOException e) {
                                                    e = e;
                                                    str3 = str4;
                                                    z = true;
                                                    e.printStackTrace();
                                                    g();
                                                    h();
                                                    if (e()) {
                                                        m();
                                                        i();
                                                    }
                                                    a(c());
                                                    if (z) {
                                                        com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                        if (a(context, str3, attributeValue, attributeValue2)) {
                                                            ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                            ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                        }
                                                    }
                                                } catch (XmlPullParserException e2) {
                                                    e = e2;
                                                    str3 = str4;
                                                    z = true;
                                                    e.printStackTrace();
                                                    g();
                                                    h();
                                                    if (e()) {
                                                        m();
                                                        i();
                                                    }
                                                    a(c());
                                                    if (z) {
                                                        com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                        if (a(context, str3, attributeValue, attributeValue2)) {
                                                            ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                            ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                        }
                                                    }
                                                }
                                            } else {
                                                z3 = z2;
                                            }
                                            try {
                                                if (this.d != i4) {
                                                    this.d = i4;
                                                    z4 = true;
                                                } else {
                                                    z4 = z3;
                                                }
                                                if (attributeValue4 != null) {
                                                    try {
                                                        this.v = Integer.parseInt(attributeValue4);
                                                    } catch (IOException e3) {
                                                        e = e3;
                                                        str3 = str4;
                                                        z = z4;
                                                        e.printStackTrace();
                                                        g();
                                                        h();
                                                        if (e()) {
                                                            m();
                                                            i();
                                                        }
                                                        a(c());
                                                        if (z) {
                                                            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                            if (a(context, str3, attributeValue, attributeValue2)) {
                                                                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                            }
                                                        }
                                                    } catch (XmlPullParserException e4) {
                                                        e = e4;
                                                        str3 = str4;
                                                        z = z4;
                                                        e.printStackTrace();
                                                        g();
                                                        h();
                                                        if (e()) {
                                                            m();
                                                            i();
                                                        }
                                                        a(c());
                                                        if (z) {
                                                            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                            if (a(context, str3, attributeValue, attributeValue2)) {
                                                                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                            }
                                                        }
                                                    }
                                                }
                                                if (attributeValue10 != 0) {
                                                    this.R = Long.parseLong(attributeValue10);
                                                }
                                                if (attributeValue11 != null) {
                                                    this.S = Integer.parseInt(attributeValue11);
                                                }
                                                str8 = str4;
                                                z5 = z4;
                                            } catch (IOException e5) {
                                                e = e5;
                                                z = z3;
                                                str3 = str4;
                                                e.printStackTrace();
                                                g();
                                                h();
                                                if (e()) {
                                                    m();
                                                    i();
                                                }
                                                a(c());
                                                if (z) {
                                                    com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                    if (a(context, str3, attributeValue, attributeValue2)) {
                                                        ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                        ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                    }
                                                }
                                            } catch (XmlPullParserException e6) {
                                                e = e6;
                                                z = z3;
                                                str3 = str4;
                                                e.printStackTrace();
                                                g();
                                                h();
                                                if (e()) {
                                                    m();
                                                    i();
                                                }
                                                a(c());
                                                if (z) {
                                                    com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                    if (a(context, str3, attributeValue, attributeValue2)) {
                                                        ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                        ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                    }
                                                }
                                            }
                                        } catch (IOException e7) {
                                            e = e7;
                                            z = z2;
                                            str3 = str4;
                                            e.printStackTrace();
                                            g();
                                            h();
                                            if (e()) {
                                                m();
                                                i();
                                            }
                                            a(c());
                                            if (z) {
                                                com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                if (a(context, str3, attributeValue, attributeValue2)) {
                                                    ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                    ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                }
                                            }
                                        } catch (XmlPullParserException e8) {
                                            e = e8;
                                            z = z2;
                                            str3 = str4;
                                            e.printStackTrace();
                                            g();
                                            h();
                                            if (e()) {
                                                m();
                                                i();
                                            }
                                            a(c());
                                            if (z) {
                                                com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                                if (a(context, str3, attributeValue, attributeValue2)) {
                                                    ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                    ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                                }
                                            }
                                        }
                                    } catch (IOException e9) {
                                        e = e9;
                                        str4 = attributeValue3;
                                        z = z2;
                                        str3 = str4;
                                        e.printStackTrace();
                                        g();
                                        h();
                                        if (e()) {
                                            m();
                                            i();
                                        }
                                        a(c());
                                        if (z) {
                                            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                            if (a(context, str3, attributeValue, attributeValue2)) {
                                                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                            }
                                        }
                                    } catch (XmlPullParserException e10) {
                                        e = e10;
                                        str4 = attributeValue3;
                                        z = z2;
                                        str3 = str4;
                                        e.printStackTrace();
                                        g();
                                        h();
                                        if (e()) {
                                            m();
                                            i();
                                        }
                                        a(c());
                                        if (z) {
                                            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                            if (a(context, str3, attributeValue, attributeValue2)) {
                                                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                            }
                                        }
                                    }
                                } catch (IOException e11) {
                                    e = e11;
                                    z2 = z5;
                                } catch (XmlPullParserException e12) {
                                    e = e12;
                                    z2 = z5;
                                }
                            } else {
                                z5 = z5;
                            }
                        } catch (IOException e13) {
                            e = e13;
                            z = z5;
                            str3 = str8;
                            e.printStackTrace();
                            g();
                            h();
                            if (e()) {
                                m();
                                i();
                            }
                            a(c());
                            if (z) {
                                com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                if (a(context, str3, attributeValue, attributeValue2)) {
                                    ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                    ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                }
                            }
                        } catch (XmlPullParserException e14) {
                            e = e14;
                            z = z5;
                            str3 = str8;
                            e.printStackTrace();
                            g();
                            h();
                            if (e()) {
                                m();
                                i();
                            }
                            a(c());
                            if (z) {
                                com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                                if (a(context, str3, attributeValue, attributeValue2)) {
                                    ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                    ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                                }
                            }
                        }
                    }
                    try {
                        eventType = xmlPullParserNewPullParser.next();
                        i2 = 1;
                        str6 = null;
                    } catch (IOException e15) {
                        e = e15;
                        z = z5;
                        str3 = str8;
                        e.printStackTrace();
                        g();
                        h();
                        if (e()) {
                            m();
                            i();
                        }
                        a(c());
                        if (z) {
                            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                            if (a(context, str3, attributeValue, attributeValue2)) {
                                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                            }
                        }
                    } catch (XmlPullParserException e16) {
                        e = e16;
                        z = z5;
                        str3 = str8;
                        e.printStackTrace();
                        g();
                        h();
                        if (e()) {
                            m();
                            i();
                        }
                        a(c());
                        if (z) {
                            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
                            if (a(context, str3, attributeValue, attributeValue2)) {
                                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
                            }
                        }
                    }
                }
                z = z5;
                str3 = str8;
            } catch (IOException e17) {
                e = e17;
                str3 = null;
                z = false;
                attributeValue = null;
                attributeValue2 = null;
            } catch (XmlPullParserException e18) {
                e = e18;
                str3 = null;
                z = false;
                attributeValue = null;
                attributeValue2 = null;
            }
        } else {
            str3 = null;
            z = false;
            attributeValue = null;
            attributeValue2 = null;
        }
        g();
        h();
        if (e()) {
            m();
            i();
        }
        a(c());
        if (z) {
            com.excelliance.lbsdk.main.g.b.c("dplm", "need reset new filset", new Object[0]);
            if (a(context, str3, attributeValue, attributeValue2)) {
                ae.a(this.b, "lebian_module_bwbxinfo", "stcode", this.c);
                ae.a(this.b, "lebian_module_bwbxinfo", "stver", this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        synchronized (this.O) {
            ArrayList arrayList = new ArrayList(this.o);
            this.J = -1.0d;
            if (z) {
                this.p = 0;
                this.N.clear();
            }
            if (G != null && arrayList.size() > 0 && G.size() > 0) {
                int i = this.p;
                this.p = arrayList.size();
                List listSubList = arrayList.subList(i, arrayList.size());
                com.excelliance.lbsdk.main.g.b.c("dplm", "Last Calculate Position = " + i, new Object[0]);
                com.excelliance.lbsdk.main.g.b.c("dplm", "count = " + j(), new Object[0]);
                for (int i2 = 0; i2 < j(); i2++) {
                    this.I = 0.0d;
                    ArrayList<Integer> arrayList2 = G.get(Integer.valueOf(i2));
                    for (int i3 = 0; i3 < listSubList.size(); i3++) {
                        Integer num = (Integer) listSubList.get(i3);
                        int i4 = i + i3 + 1;
                        int iIndexOf = arrayList2.indexOf(num) + 1;
                        if (iIndexOf == 0) {
                            if (com.excelliance.lbsdk.main.g.b.a()) {
                                com.excelliance.lbsdk.main.g.b.b("dplm", "index = -1", new Object[0]);
                            }
                            iIndexOf = this.H.get();
                        }
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("dplm", "deep learn (" + i2 + ") fileset ind = " + num + " index 1 = " + i4 + " index 2 = " + iIndexOf, new Object[0]);
                        }
                        this.I += Math.pow(Math.abs(iIndexOf - i4), 2.0d);
                    }
                    Double d2 = this.N.get(i2);
                    double dDoubleValue = this.I;
                    if (d2 != null) {
                        dDoubleValue += d2.doubleValue();
                    }
                    this.N.put(i2, Double.valueOf(dDoubleValue));
                    double d3 = this.p;
                    Double.isNaN(d3);
                    double dSqrt = Math.sqrt(dDoubleValue / d3);
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("dplm", "result =" + dSqrt + " i = " + i2, new Object[0]);
                    }
                    if (this.J == -1.0d || this.J - dSqrt > 0.0d) {
                        this.J = dSqrt;
                        this.K = i2 + 1;
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("dplm", "result =" + dSqrt + ", mResult = " + this.J + ", index = " + this.K, new Object[0]);
                        }
                    }
                }
                this.L = ae.b(this.b, "lebian_module_bwbxinfo", "deep_learn_need_idx", this.L);
                if (this.K != this.L) {
                    this.L = this.K;
                    this.M = ((Integer) listSubList.get(listSubList.size() - 1)).intValue();
                    this.n.add(this.M + ":" + this.K);
                    ae.a(this.b, "lebian_module_bwbxinfo", "deep_learn_need_idx", this.K);
                    com.excelliance.lbsdk.main.g.b.c("dplm", "calculateVariance use multi line index:" + this.K, new Object[0]);
                    this.k.a(d(d() ? GameUtil.getLbDeepLearnPath(this.b) + "origFiles" + this.K + ".txt" : "origFiles" + this.K + ".txt"));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        this.m.add(this.s.get(str));
        this.r++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> d(String str) {
        InputStream inputStreamOpen;
        try {
            inputStreamOpen = new FileInputStream(str);
        } catch (Exception unused) {
            com.excelliance.lbsdk.main.g.b.c("dplm", str + " is not exist", new Object[0]);
            try {
                File file = new File(str);
                inputStreamOpen = this.b.getAssets().open("lebian/" + file.getName());
            } catch (Exception unused2) {
                com.excelliance.lbsdk.main.g.b.c("dplm", str + " is not exist", new Object[0]);
                inputStreamOpen = null;
            }
        }
        return a(inputStreamOpen);
    }

    private ArrayList<Integer> e(String str) {
        InputStream inputStreamOpen;
        String line;
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (this.b == null) {
            return null;
        }
        if (arrayList.isEmpty()) {
            try {
                if (d()) {
                    inputStreamOpen = new FileInputStream(GameUtil.getLbDeepLearnPath(this.b) + str);
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("dplm", "getFileSetsOrder use new file:" + str, new Object[0]);
                    }
                } else {
                    inputStreamOpen = this.b.getAssets().open("lebian/" + str);
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("dplm", "getFileSetsOrder use old file:" + str, new Object[0]);
                    }
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen));
                do {
                    line = bufferedReader.readLine();
                    if (line != null) {
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("dplm", "getFileSetsOrder str line:" + line, new Object[0]);
                        }
                        for (String str2 : line.split(",")) {
                            arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
                        }
                    }
                } while (line != null);
                bufferedReader.close();
            } catch (Exception unused) {
                com.excelliance.lbsdk.main.g.b.c("dplm", "not find fileSet:" + str, new Object[0]);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.l.size() > 0) {
            GameUtil intance = GameUtil.getIntance();
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.e == null) {
                    this.e = ae.b(this.b, "lebian_module_userinfo", "uid", (String) null);
                }
                if (this.e == null) {
                    this.e = an.a().b();
                }
                jSONObject.put("uid", this.e);
                jSONObject.put("chid", intance.getApkMainCh());
                jSONObject.put("dpver", 1);
                jSONObject.put("vercode", this.j);
                jSONObject.put("cpmeta", intance.getClientChId());
                jSONObject.put("md5", this.i);
                jSONObject.put("stcode", this.c);
                jSONObject.put("stversion", this.d);
                jSONObject.put("miss", this.r);
                jSONObject.put("missarrary", new JSONArray((Collection) this.m));
                jSONObject.put("index", new JSONArray((Collection) this.l));
                jSONObject.put("node", new JSONArray((Collection) this.n));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("dplm", "report order start json:" + jSONObject, new Object[0]);
            }
            if (this.h == null || aa.a(this.h, jSONObject, this.b).a != 200) {
                return;
            }
            this.r = 0;
            this.l.clear();
            this.m.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (c()) {
            a(true);
            this.z.removeMessages(0);
        } else {
            Message messageObtainMessage = this.z.obtainMessage();
            messageObtainMessage.what = 0;
            this.z.sendEmptyMessageDelayed(messageObtainMessage.what, this.f * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (c()) {
            a(true);
            this.z.removeMessages(1);
        } else {
            Message messageObtainMessage = this.z.obtainMessage();
            messageObtainMessage.what = 1;
            this.z.sendEmptyMessageDelayed(messageObtainMessage.what, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (c()) {
            this.B.removeMessages(0);
            return;
        }
        Message messageObtainMessage = this.B.obtainMessage();
        messageObtainMessage.what = 0;
        this.B.sendEmptyMessageDelayed(messageObtainMessage.what, 60000L);
    }

    private int j() {
        if (this.b == null) {
            com.excelliance.lbsdk.main.g.b.e("dplm", "fileset count mContext = null", new Object[0]);
            return 0;
        }
        if (this.F.get() < 0) {
            k();
            com.excelliance.lbsdk.main.g.b.e("dplm", "fileset count = " + this.F, new Object[0]);
        }
        return this.F.get();
    }

    private synchronized void k() {
        String[] list;
        int i = 0;
        try {
            if (d()) {
                File file = new File(GameUtil.getLbDeepLearnPath(this.b));
                if (!file.exists()) {
                    file.mkdirs();
                }
                list = file.list();
            } else {
                list = this.b.getAssets().list("lebian");
            }
            if (list != null) {
                int i2 = 0;
                for (int i3 = 0; i3 < list.length; i3++) {
                    try {
                        com.excelliance.lbsdk.main.g.b.c("dplm", "fileset count start =" + list[i3], new Object[0]);
                        if (list[i3].startsWith("fileSet")) {
                            i2++;
                        }
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        e.printStackTrace();
                    }
                }
                i = i2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        this.F.set(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        G = new HashMap<>();
        int i = 0;
        while (i < j()) {
            StringBuilder sb = new StringBuilder();
            sb.append("fileSet");
            int i2 = i + 1;
            sb.append(i2);
            sb.append(".txt");
            ArrayList<Integer> arrayListE = e(sb.toString());
            arrayListE.addAll(e("origFiles" + i2 + ".txt"));
            G.put(Integer.valueOf(i), arrayListE);
            i = i2;
        }
        this.H.set(this.t.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (c()) {
            o();
        } else {
            this.E.removeCallbacksAndMessages(null);
            this.E.sendEmptyMessageDelayed(1, this.R * 1000);
        }
    }

    private void n() {
        this.E.removeCallbacksAndMessages(null);
        this.E.sendEmptyMessage(1);
    }

    private void o() {
        this.E.removeCallbacksAndMessages(null);
        synchronized (this.O) {
            if (G != null) {
                G.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void p() {
        try {
            File file = new File(GameUtil.getResRoot(this.b) + "lastSet");
            if (!file.exists()) {
                file.createNewFile();
            }
            boolean z = this.Q > 0;
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file, z));
            if (this.o.size() > this.Q) {
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("dplm", "================= writeSetsToLocal start ================= append:" + z + " mLastRecordPos:" + this.Q + " size:" + this.o.size(), new Object[0]);
                }
                for (Integer num : this.o.subList(this.Q, this.o.size())) {
                    if (num != null) {
                        dataOutputStream.writeInt(num.intValue());
                        if (com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("dplm", "write int = " + num, new Object[0]);
                        }
                    }
                }
                this.Q = this.o.size();
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("dplm", "================= writeSetsToLocal end ================= mLastRecordPos:" + this.Q, new Object[0]);
                }
                dataOutputStream.flush();
            }
            dataOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        try {
            File file = new File(GameUtil.getResRoot(this.b) + "lastSet");
            if (file.exists()) {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("dplm", "================= readSetFromLocal start =================", new Object[0]);
                }
                while (dataInputStream.available() > 0) {
                    int i = dataInputStream.readInt();
                    this.o.add(Integer.valueOf(i));
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("dplm", "read int = " + i, new Object[0]);
                    }
                }
                this.Q = this.o.size();
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("dplm", "================= readSetFromLocal end ================= mLastRecordPos:" + this.Q, new Object[0]);
                }
                dataInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(final Context context, final int i, final String str, final c cVar) {
        this.j = i;
        if (this.b == null) {
            this.b = context;
        }
        this.x = ae.b(this.b, "lebian_module_bwbxinfo", "dlstarttime", 0L);
        if (this.x == 0) {
            this.x = System.currentTimeMillis();
            ae.a(this.b, "lebian_module_bwbxinfo", "dlstarttime", this.x);
        }
        int iB = ae.b(this.b, "lebian_module_bwbxinfo", "deeplearnversion", -1);
        if (iB != -1 && iB != i) {
            ae.a(this.b, "lebian_module_bwbxinfo", "stcode");
            ae.a(this.b, "lebian_module_bwbxinfo", "stver");
        }
        this.c = ae.b(this.b, "lebian_module_bwbxinfo", "stcode", 0);
        this.d = ae.b(this.b, "lebian_module_bwbxinfo", "stver", 0);
        ae.a(this.b, "lebian_module_bwbxinfo", "deeplearnversion", i);
        this.q.execute(new Runnable() { // from class: com.excelliance.lbsdk.main.g.1
            @Override // java.lang.Runnable
            public void run() {
                if (g.this.e()) {
                    g.this.l();
                }
                g.this.b(context, i, str, cVar);
            }
        });
    }

    public void a(final Context context, final List<String> list, final HashMap<String, BigFileInfo> map, final c cVar) {
        this.q.execute(new Runnable() { // from class: com.excelliance.lbsdk.main.g.2
            @Override // java.lang.Runnable
            public void run() {
                g gVar;
                String str;
                String line;
                synchronized (g.this) {
                    g.this.b = context;
                    Set<String> setEmptySet = Collections.emptySet();
                    List<String> listEmptyList = Collections.emptyList();
                    LinkedHashSet linkedHashSet = new LinkedHashSet(list);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(e.e(context)));
                        boolean zIsBigAsset = BwbxUtil.isBigAsset(context);
                        if (zIsBigAsset) {
                            com.excelliance.lbsdk.main.g.b.c("dplm", "getBig", new Object[0]);
                            setEmptySet = GameUtil.getIntance().fileOrder();
                            listEmptyList = new ArrayList(setEmptySet);
                        }
                        g.this.P.putAll(map);
                        int i = 1;
                        do {
                            line = bufferedReader.readLine();
                            if (line != null) {
                                BigFileInfo bigFileInfo = (BigFileInfo) map.get(line);
                                if (bigFileInfo != null) {
                                    int i2 = bigFileInfo.b;
                                    int i3 = i;
                                    for (int i4 = 0; i4 < i2; i4++) {
                                        String str2 = line + ":" + i4;
                                        if (com.excelliance.lbsdk.main.g.b.a()) {
                                            com.excelliance.lbsdk.main.g.b.b("dplm", "parseLbStream: " + str2, new Object[0]);
                                        }
                                        g.this.s.put(str2, Integer.valueOf(i3));
                                        g.this.t.add(str2);
                                        g.this.u.add(Integer.valueOf(i3));
                                        i3++;
                                        if (!setEmptySet.contains(str2)) {
                                            listEmptyList.add(str2);
                                        }
                                    }
                                    i = i3;
                                } else {
                                    g.this.s.put(line, Integer.valueOf(i));
                                    g.this.t.add(line);
                                    if (linkedHashSet.contains(line)) {
                                        g.this.u.add(Integer.valueOf(i));
                                    }
                                    if (zIsBigAsset && !setEmptySet.contains(line) && linkedHashSet.contains(line)) {
                                        listEmptyList.add(line);
                                    }
                                    i++;
                                }
                            }
                        } while (line != null);
                        bufferedReader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("dplm", "new fileset:*****************start**************", new Object[0]);
                        for (String str3 : g.this.t) {
                            com.excelliance.lbsdk.main.g.b.b("dplm", g.this.s.get(str3) + ":" + str3, new Object[0]);
                        }
                        com.excelliance.lbsdk.main.g.b.b("dplm", "new fileset:*****************end**************", new Object[0]);
                    }
                    if (g.this.e()) {
                        int iB = ae.b(g.this.b, "lebian_module_bwbxinfo", "deep_learn_need_idx", 1);
                        com.excelliance.lbsdk.main.g.b.c("dplm", "use new fileset 1:" + iB, new Object[0]);
                        if (g.this.d()) {
                            gVar = g.this;
                            str = GameUtil.getLbDeepLearnPath(g.this.b) + "origFiles" + iB + ".txt";
                        } else {
                            gVar = g.this;
                            str = "origFiles" + iB + ".txt";
                        }
                        List<String> listD = gVar.d(str);
                        if (listD.size() > 0) {
                            com.excelliance.lbsdk.main.g.b.c("dplm", "use new fileset:" + iB, new Object[0]);
                            cVar.a(listD);
                        }
                        g.a().q();
                    } else {
                        List<String> listD2 = g.this.d(GameUtil.getLbDeepLearnPath(context) + "newFileset.txt");
                        if (listD2.size() > 0) {
                            com.excelliance.lbsdk.main.g.b.c("dplm", "use new fileset", new Object[0]);
                            cVar.a(listD2);
                        } else if (listEmptyList.size() > 0) {
                            cVar.a(listEmptyList);
                        }
                    }
                    com.excelliance.lbsdk.main.g.b.c("dplm", "parseLbStream fileSetDl size: " + list.size(), new Object[0]);
                }
            }
        });
    }

    public void a(String str) {
        this.z.sendMessage(this.z.obtainMessage(2, str));
    }

    public synchronized void b() {
        int size;
        String dlVisitedFiles = AssetRedirector.getDlVisitedFiles(C);
        if (dlVisitedFiles != null && !TextUtils.isEmpty(dlVisitedFiles)) {
            String[] strArrSplit = dlVisitedFiles.split(",");
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("dplm", "record visited file get file start = " + strArrSplit[0], new Object[0]);
                com.excelliance.lbsdk.main.g.b.b("dplm", "record visited file get file end = " + strArrSplit[strArrSplit.length + (-1)], new Object[0]);
            }
            if (strArrSplit.length > 0 && !TextUtils.isEmpty(strArrSplit[0])) {
                C += strArrSplit.length;
                if (com.excelliance.lbsdk.main.g.b.a()) {
                    com.excelliance.lbsdk.main.g.b.b("dplm", "record visited file last pos = " + C, new Object[0]);
                }
                for (String str : strArrSplit) {
                    if (this.s.size() > 0) {
                        Integer num = this.s.get(str);
                        if (num == null && com.excelliance.lbsdk.main.g.b.a()) {
                            com.excelliance.lbsdk.main.g.b.b("dplm", "collect res: (null:" + str + ")", new Object[0]);
                        }
                        if (num != null && !this.l.contains(num)) {
                            this.l.add(num);
                            if (!this.o.contains(num)) {
                                this.o.add(num);
                            }
                            if (com.excelliance.lbsdk.main.g.b.a()) {
                                com.excelliance.lbsdk.main.g.b.b("dplm", "collect res: (" + num + ":" + str + ")", new Object[0]);
                            }
                        }
                    }
                }
                if (this.U.get() != 0) {
                    synchronized (this.O) {
                        size = this.o.size() - this.p;
                    }
                    if (size > this.S) {
                        com.excelliance.lbsdk.main.g.b.c("dplm", "start calculate cause number:" + size, new Object[0]);
                        n();
                    }
                }
                if (this.l.size() > this.g) {
                    f();
                }
            }
        }
    }

    public void b(final String str) {
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("dplm", "collect res: " + str + " from WWW", new Object[0]);
        }
        if (c()) {
            return;
        }
        this.q.execute(new Runnable() { // from class: com.excelliance.lbsdk.main.g.3
            @Override // java.lang.Runnable
            public void run() {
                AssetRedirector.recordWwwVisitedFile(str);
            }
        });
    }

    public boolean c() {
        if (this.v == 1) {
            return true;
        }
        if (this.x == 0 || System.currentTimeMillis() - this.x <= this.w) {
            return false;
        }
        this.v = 1;
        com.excelliance.lbsdk.main.g.b.c("dplm", "shut off now", new Object[0]);
        return true;
    }

    public boolean d() {
        com.excelliance.lbsdk.main.g.b.c("dplm", "isUsedNewStrategy start = " + this.T, new Object[0]);
        if (this.T != -1) {
            return this.T != 0;
        }
        if (new File(GameUtil.getLbDeepLearnPath(this.b) + "newFileset.txt").exists()) {
            this.T = 1;
            com.excelliance.lbsdk.main.g.b.c("dplm", "isUsedNewStrategy  end = " + this.T, new Object[0]);
            return this.T != 0;
        }
        File file = new File(GameUtil.getLbDeepLearnPath(this.b));
        if (file.exists() && file.list().length > 0) {
            this.T = 2;
            com.excelliance.lbsdk.main.g.b.c("dplm", "isUsedNewStrategy  end = " + this.T, new Object[0]);
            return this.T != 0;
        }
        if (this.T == -1) {
            this.T = 0;
        }
        com.excelliance.lbsdk.main.g.b.c("dplm", "isUsedNewStrategy  end = " + this.T, new Object[0]);
        return this.T != 0;
    }

    public boolean e() {
        if (this.U.get() == -1) {
            this.U.set(j());
        }
        return this.U.get() != 0;
    }
}
