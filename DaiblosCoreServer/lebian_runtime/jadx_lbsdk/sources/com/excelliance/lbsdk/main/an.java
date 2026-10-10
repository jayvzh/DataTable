package com.excelliance.lbsdk.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Xml;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.http.params.HttpParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class an {
    private static an b;
    private String g;
    private c h;
    private long j;
    private Context c = null;
    private String d = null;
    private Object e = new Object();
    private j f = null;
    public int a = 1;
    private Object i = new Object();
    private Object k = new Object();
    private Object l = new Object();

    private an() {
    }

    private Intent a(int i, String str, Exception exc) {
        Intent intent = new Intent();
        intent.putExtra("action", 7);
        intent.putExtra("package", this.c.getPackageName());
        intent.putExtra("errType", i);
        StringBuffer stringBuffer = new StringBuffer();
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append(str);
            stringBuffer.append("\r\n");
        }
        if (exc != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            exc.printStackTrace(printWriter);
            for (Throwable cause = exc.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
                printWriter.append("\r\n");
            }
            printWriter.close();
            stringBuffer.append(stringWriter.toString());
        }
        intent.putExtra("excpt", stringBuffer.toString());
        return intent;
    }

    public static synchronized an a() {
        if (b == null) {
            b = new an();
        }
        return b;
    }

    static /* synthetic */ c a(an anVar) {
        return anVar.h;
    }

    static /* synthetic */ Object b(an anVar) {
        return anVar.l;
    }

    private void b(int i) {
        Intent intent = new Intent();
        intent.putExtra("action", i);
        GameUtil.getIntance().notifyAction(intent);
    }

    private void b(l lVar) {
        if (lVar == null || lVar.savePath == null) {
            return;
        }
        GameUtil.deleteDir(new File(lVar.savePath).getParentFile());
    }

    public static void b(String str, String str2) {
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "copyFile: " + str + "\n newFile" + str2, new Object[0]);
        try {
            if (!new File(str).exists()) {
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "copyFile: old is not exist", new Object[0]);
                return;
            }
            File file = new File(str2 + ".tmp");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[51200];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                }
            }
            bufferedInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            File file2 = new File(str2);
            if (file2.exists()) {
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "copyFile: new path exist", new Object[0]);
                file2.delete();
            }
            file.renameTo(file2);
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "copyFile: " + file2.exists(), new Object[0]);
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "copyFile: error" + e, new Object[0]);
        }
    }

    private int c(int i) {
        return b.b(this.c) ? ((i ^ 131072) ^ 65536) | 32768 | 8 | 4 : i;
    }

    static /* synthetic */ Context c(an anVar) {
        return anVar.c;
    }

    public static String c(Context context) {
        String strN = a().n();
        String line = "";
        if (strN == null) {
            try {
                InputStream inputStreamOpen = context.getAssets().open("tryflag");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStreamOpen, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                bufferedReader.readLine();
                line = bufferedReader.readLine();
                inputStreamReader.close();
                inputStreamOpen.close();
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "path=" + line, new Object[0]);
                return line;
            } catch (IOException unused) {
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "no tryflag", new Object[0]);
                return line;
            }
        }
        if (strN == null || !new File(strN).exists()) {
            return "";
        }
        try {
            ZipFile zipFile = new ZipFile(strN);
            ZipEntry entry = zipFile.getEntry("assets/tryflag");
            if (entry != null) {
                InputStreamReader inputStreamReader2 = new InputStreamReader(zipFile.getInputStream(entry), "UTF-8");
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                bufferedReader2.readLine();
                String line2 = bufferedReader2.readLine();
                try {
                    inputStreamReader2.close();
                    com.excelliance.lbsdk.main.g.b.c("VersionManager", "path1=" + line2, new Object[0]);
                    line = line2;
                } catch (IOException unused2) {
                    line = line2;
                    com.excelliance.lbsdk.main.g.b.c("VersionManager", "no tryflag1", new Object[0]);
                    return line;
                }
            }
            zipFile.close();
            return line;
        } catch (IOException unused3) {
        }
    }

    private long j(String str) {
        long contentLength = 0;
        try {
            HttpURLConnection httpURLConnectionA = aa.a(new URL(str), (Context) null);
            contentLength = httpURLConnectionA.getContentLength();
            httpURLConnectionA.disconnect();
            return contentLength;
        } catch (Exception e) {
            e.printStackTrace();
            return contentLength;
        }
    }

    private Map<String, l> t() {
        File[] fileArrListFiles;
        boolean z;
        HashMap map = new HashMap();
        String packageName = this.c.getPackageName();
        ArrayList arrayList = new ArrayList();
        if (GameUtil.getIntance().hasExternalStorage()) {
            arrayList.add(GameUtil.getExternalDataPath(this.c) + "/lebian/downloading/");
        }
        arrayList.add(this.g + "downloading/");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            if (file.exists() && (fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.excelliance.lbsdk.main.an.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.endsWith(".cfg");
                }
            })) != null) {
                for (File file2 : fileArrListFiles) {
                    l lVarA = a(file2.getPath());
                    File file3 = new File(file2.getParent(), packageName + ".dload");
                    File file4 = new File(file2.getParent(), packageName + ".expansion.dload");
                    if (lVarA != null) {
                        if (packageName.equals(lVarA.gameLib)) {
                            if (file3.exists()) {
                                lVarA.savePath = file3.getAbsolutePath();
                                z = true;
                            } else {
                                z = false;
                            }
                            if (file4.exists()) {
                                lVarA.expanSavePath = file4.getAbsolutePath();
                                z = true;
                            }
                            if (z) {
                                lVarA.cfgPath = file2.getPath();
                                l lVar = (l) map.get(lVarA.gameId);
                                if (lVar != null) {
                                    new File(lVar.cfgPath).delete();
                                    new File(lVar.savePath).delete();
                                    new File(lVar.expanSavePath).delete();
                                }
                                map.put(lVarA.gameId, lVarA);
                            }
                        } else {
                            com.excelliance.lbsdk.main.g.b.c("VersionManager", "useless partial " + lVarA.gameLib, new Object[0]);
                            new File(lVarA.cfgPath).delete();
                            new File(lVarA.savePath).delete();
                            new File(lVarA.expanSavePath).delete();
                        }
                    }
                }
            }
        }
        return map;
    }

    private String u() {
        boolean zB = ae.b(this.c, "lebian_module_dloadinfo", "success", false);
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "getLbresOldDir success:" + zB, new Object[0]);
        long downloadedSize = GameUtil.getDownloadedSize(this.c, GameUtil.getIntance().getRunningVersionCode(false, true));
        long origSize = GameUtil.getIntance().getOrigSize(false) + GameUtil.getIntance().getOrigSize(false, 2);
        if (!BwbxUtil.isBigAsset(this.c)) {
            double d = downloadedSize;
            double d2 = origSize;
            Double.isNaN(d2);
            zB = d >= d2 * 0.5d;
        }
        String remoteResPathZip = (zB && h.c()) ? GameUtil.getRemoteResPathZip(this.c) : null;
        if (downloadedSize >= origSize) {
            ae.a(this.c, "lebian_module_bwbxinfo", "resEnough", true);
        }
        return remoteResPathZip;
    }

    public int a(String str, String str2) {
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "before preinst", new Object[0]);
        synchronized (this.i) {
            if (new File(str2).lastModified() == this.j) {
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "someone else installed", new Object[0]);
                return -1;
            }
            int iA = ad.a(this.c).a(0, str2, 65538);
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "preinst=" + iA, new Object[0]);
            if (iA == 1 || iA == -1) {
                this.j = new File(str2).lastModified();
                ae.a(this.c, "lebian_module_extractinfo", "preinstall", str);
            }
            return iA;
        }
    }

    /* JADX WARN: Code duplicated, block: B:110:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:29:0x0093  */
    /* JADX WARN: Code duplicated, block: B:38:0x00a9  */
    public l a(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String str2;
        String str3;
        String str4 = str;
        l lVar = null;
        String str5 = null;
        l lVar2 = null;
        if (str4 == null) {
            return null;
        }
        File file = new File(str4);
        if (!file.exists()) {
            return null;
        }
        String str6 = "VersionManager";
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "parseLocalConfig:" + str4, new Object[0]);
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                sb.append(new String(bArr, 0, i, "UTF-8"));
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String string = sb.toString();
        if (string == null || string.length() == 0) {
            return null;
        }
        if (string != null) {
            try {
                if (string.trim().equals("")) {
                    byteArrayInputStream = null;
                } else {
                    byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
                }
            } catch (Exception e2) {
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "parseLocalConfig e" + e2, new Object[0]);
                e2.printStackTrace();
                return null;
            }
        } else {
            byteArrayInputStream = null;
        }
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        try {
            xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
            int eventType = xmlPullParserNewPullParser.getEventType();
            l lVar3 = null;
            while (eventType != 1) {
                if (eventType == 0 || eventType != 2) {
                    str2 = str5;
                } else {
                    try {
                        if (xmlPullParserNewPullParser.getName().equalsIgnoreCase("ginfo")) {
                            String attributeValue = xmlPullParserNewPullParser.getAttributeValue(str5, "id");
                            String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(str5, "version");
                            String attributeValue3 = xmlPullParserNewPullParser.getAttributeValue(str5, "abbr");
                            String attributeValue4 = xmlPullParserNewPullParser.getAttributeValue(str5, "savepath");
                            String attributeValue5 = xmlPullParserNewPullParser.getAttributeValue(str5, BwbxUtil.ACTION_URL);
                            String attributeValue6 = xmlPullParserNewPullParser.getAttributeValue(str5, "urlBk");
                            String attributeValue7 = xmlPullParserNewPullParser.getAttributeValue(str5, "urlbksecond");
                            String attributeValue8 = xmlPullParserNewPullParser.getAttributeValue(str5, "patch");
                            String attributeValue9 = xmlPullParserNewPullParser.getAttributeValue(str5, "omd5");
                            String attributeValue10 = xmlPullParserNewPullParser.getAttributeValue(str5, "nmd5");
                            String attributeValue11 = xmlPullParserNewPullParser.getAttributeValue(str5, "dmd5");
                            String attributeValue12 = xmlPullParserNewPullParser.getAttributeValue(str5, BwbxUtil.ACTION_SIZE);
                            String attributeValue13 = xmlPullParserNewPullParser.getAttributeValue(str5, "expomd5");
                            String attributeValue14 = xmlPullParserNewPullParser.getAttributeValue(str5, "expdmd5");
                            String attributeValue15 = xmlPullParserNewPullParser.getAttributeValue(str5, "expnmd5");
                            String attributeValue16 = xmlPullParserNewPullParser.getAttributeValue(str5, "expsize");
                            String attributeValue17 = xmlPullParserNewPullParser.getAttributeValue(str5, "expurl");
                            String attributeValue18 = xmlPullParserNewPullParser.getAttributeValue(str5, "expurlbk");
                            String attributeValue19 = xmlPullParserNewPullParser.getAttributeValue(str5, "expsavepath");
                            String attributeValue20 = xmlPullParserNewPullParser.getAttributeValue(str5, "exppatch");
                            String attributeValue21 = xmlPullParserNewPullParser.getAttributeValue(str5, "tpath");
                            String attributeValue22 = xmlPullParserNewPullParser.getAttributeValue(str5, "tpathbk");
                            String attributeValue23 = xmlPullParserNewPullParser.getAttributeValue(str5, "tmd5");
                            String attributeValue24 = xmlPullParserNewPullParser.getAttributeValue(str5, "tsize");
                            String attributeValue25 = xmlPullParserNewPullParser.getAttributeValue(str5, "rc");
                            String attributeValue26 = xmlPullParserNewPullParser.getAttributeValue(str5, "gvercode");
                            String attributeValue27 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresurl");
                            String attributeValue28 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresurlbk");
                            String attributeValue29 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresomd5");
                            String attributeValue30 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresdmd5");
                            String attributeValue31 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresnmd5");
                            String attributeValue32 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresdsize");
                            String attributeValue33 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresnsize");
                            String attributeValue34 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotrescfg");
                            String attributeValue35 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresavepath");
                            String attributeValue36 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotrespatch");
                            String attributeValue37 = xmlPullParserNewPullParser.getAttributeValue(str5, "hotresversioncode");
                            String attributeValue38 = xmlPullParserNewPullParser.getAttributeValue(str5, "rpred");
                            String attributeValue39 = xmlPullParserNewPullParser.getAttributeValue(str5, "rponly");
                            if (attributeValue12 == null) {
                                attributeValue12 = "0";
                            }
                            if (attributeValue16 == null) {
                                attributeValue16 = "0";
                            }
                            if (attributeValue24 == null) {
                                attributeValue24 = "0";
                            }
                            if (attributeValue32 == null) {
                                attributeValue32 = "0";
                            }
                            if (attributeValue33 == null) {
                                attributeValue33 = "0";
                            }
                            if (attributeValue38 == null) {
                                attributeValue38 = "0";
                            }
                            if (attributeValue39 == null) {
                                attributeValue39 = "false";
                            }
                            String str7 = attributeValue39;
                            String attributeValue40 = xmlPullParserNewPullParser.getAttributeValue(str5, "flag");
                            int i2 = attributeValue40 != null ? Integer.parseInt(attributeValue40) : 0;
                            String attributeValue41 = xmlPullParserNewPullParser.getAttributeValue(str5, "ntftitle");
                            String attributeValue42 = xmlPullParserNewPullParser.getAttributeValue(str5, "ntfmsg");
                            String attributeValue43 = xmlPullParserNewPullParser.getAttributeValue(str5, "level");
                            if (attributeValue43 == null) {
                                attributeValue43 = "1";
                            }
                            String str8 = attributeValue32;
                            String attributeValue44 = xmlPullParserNewPullParser.getAttributeValue(null, "metaversion");
                            String str9 = attributeValue44 == null ? "1" : attributeValue44;
                            boolean zEquals = "1".equals(xmlPullParserNewPullParser.getAttributeValue(null, "gfup"));
                            int i3 = (attributeValue8 == null || attributeValue8.length() <= 0) ? 0 : Integer.parseInt(attributeValue8);
                            int i4 = attributeValue20 != null ? Integer.parseInt(attributeValue20) : 0;
                            String attributeValue45 = xmlPullParserNewPullParser.getAttributeValue(null, "adl");
                            boolean z = attributeValue45 != null && attributeValue45.equals("1");
                            String attributeValue46 = xmlPullParserNewPullParser.getAttributeValue(null, "install");
                            boolean z2 = attributeValue46 != null && attributeValue46.equals("1");
                            String attributeValue47 = xmlPullParserNewPullParser.getAttributeValue(null, "predl");
                            boolean z3 = attributeValue47 != null && attributeValue47.equals("1");
                            String attributeValue48 = xmlPullParserNewPullParser.getAttributeValue(null, "sign");
                            boolean zEquals2 = attributeValue48 != null ? attributeValue48.equals("1") : true;
                            boolean zEquals3 = "1".equals(xmlPullParserNewPullParser.getAttributeValue(null, "prepay"));
                            if (TextUtils.isEmpty(attributeValue36)) {
                                attributeValue36 = "0";
                            }
                            if (attributeValue4 == null || !new File(attributeValue4).exists()) {
                                String strReplace = str4.replace("/config/", "/jar/");
                                String str10 = strReplace.substring(0, strReplace.lastIndexOf(46)) + ".odex.apk";
                                if (new File(str10).exists()) {
                                    str3 = str10;
                                } else {
                                    str3 = attributeValue4;
                                }
                            } else {
                                str3 = attributeValue4;
                            }
                            String attributeValue49 = xmlPullParserNewPullParser.getAttributeValue(null, "udir");
                            String attributeValue50 = xmlPullParserNewPullParser.getAttributeValue(null, "kdir");
                            com.excelliance.lbsdk.main.g.b.c(str6, "parseLocalConfig: :udir" + attributeValue49 + "  kdir:" + attributeValue50, new Object[0]);
                            com.excelliance.lbsdk.main.g.b.c(str6, "gameId=" + attributeValue + " version=" + attributeValue2 + " level=" + attributeValue43 + " gameLib=" + attributeValue3, new Object[0]);
                            str2 = null;
                            lVar3 = new l(attributeValue, attributeValue3, attributeValue5, attributeValue6, attributeValue7, str3, str, Long.parseLong(attributeValue12), attributeValue2, attributeValue43, str9, zEquals, i3, attributeValue9, attributeValue10, attributeValue11, z, i2, attributeValue41, attributeValue42, z2, z3, zEquals2, attributeValue13, attributeValue14, attributeValue15, Long.parseLong(attributeValue16), attributeValue17, attributeValue18, attributeValue19, i4, zEquals3, attributeValue21, attributeValue22, attributeValue23, (long) Integer.parseInt(attributeValue24), attributeValue25, attributeValue26, attributeValue49, attributeValue50, attributeValue27, attributeValue28, attributeValue29, attributeValue30, attributeValue31, Long.parseLong(attributeValue33), Long.parseLong(str8), attributeValue37, Integer.parseInt(attributeValue36), attributeValue35, attributeValue34, Integer.parseInt(attributeValue38), Boolean.parseBoolean(str7));
                        } else {
                            str2 = str5;
                        }
                    } catch (XmlPullParserException e3) {
                        e = e3;
                        lVar2 = lVar3;
                        e.printStackTrace();
                        return lVar2;
                    } catch (Exception e4) {
                        e = e4;
                        lVar = lVar3;
                        e.printStackTrace();
                        return lVar;
                    }
                }
                eventType = xmlPullParserNewPullParser.next();
                str4 = str;
                byteArrayInputStream = byteArrayInputStream;
                xmlPullParserNewPullParser = xmlPullParserNewPullParser;
                str6 = str6;
                str5 = str2;
            }
            byteArrayInputStream.close();
            return lVar3;
        } catch (XmlPullParserException e5) {
            e = e5;
        } catch (Exception e6) {
            e = e6;
        }
    }

    public String a(long j) {
        String str;
        String str2 = null;
        if (!GameUtil.getIntance().hasExternalStorage() || GameUtil.getFreeSpace(Environment.getExternalStorageDirectory()) <= 102400 + j) {
            str = null;
        } else {
            str = GameUtil.getExternalDataPath(this.c) + "/lebian/";
            try {
                File file = new File(str, "writeTest" + Process.myPid() + Process.myTid());
                if (!file.exists()) {
                    new File(str).mkdirs();
                    file.createNewFile();
                }
                if (file.exists()) {
                    try {
                        file.delete();
                        str2 = str;
                    } catch (Exception unused) {
                        com.excelliance.lbsdk.main.g.b.c("VersionManager", "external storage write error", new Object[0]);
                        str2 = str;
                        str = null;
                    }
                } else {
                    com.excelliance.lbsdk.main.g.b.c("VersionManager", "external storage can not write", new Object[0]);
                    str = null;
                }
            } catch (Exception unused2) {
                str = null;
            }
        }
        if (str2 != null) {
            return str2;
        }
        if (GameUtil.getFreeSpace(this.g) > j + 20971520) {
            return this.g;
        }
        return str != null ? str : str2;
    }

    public String a(long j, boolean z) {
        return a(j, z, (String) null);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0057  */
    public String a(long j, boolean z, String str) {
        String strB = !z ? b(j) : a(j);
        if (strB == null) {
            return strB;
        }
        String strD = com.excelliance.lbsdk.main.ae.c.d(this.c);
        boolean z2 = false;
        if (strD == null) {
            if (str == null) {
                str = ae.b(this.c, "lebian_module_extractinfo", "current", (String) null);
            }
            if (str != null && str.startsWith(strB) && str.contains("-1")) {
                z2 = true;
            }
        } else if (strD.startsWith(strB)) {
            if (strD.contains(this.c.getPackageName() + "-1")) {
                z2 = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strB);
        sb.append(this.c.getPackageName());
        sb.append(z2 ? "-2/" : "-1/");
        return sb.toString();
    }

    public String a(boolean z) {
        String[] strArrF = f();
        ArrayList arrayList = new ArrayList();
        try {
            if (strArrF.length == 1) {
                return GameUtil.getIntance().computeFileMd5(strArrF[0], z, true);
            }
            for (String str : strArrF) {
                com.excelliance.lbsdk.main.x.b bVar = new com.excelliance.lbsdk.main.x.b(str);
                com.excelliance.lbsdk.main.x.a computeMd5SkipSize = GameUtil.getIntance().getComputeMd5SkipSize(str);
                bVar.skip(computeMd5SkipSize.a());
                bVar.a(computeMd5SkipSize.b());
                bVar.a(2);
                arrayList.add(bVar);
            }
            SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(arrayList));
            String strComputeInputStreamMd5 = GameUtil.getIntance().computeInputStreamMd5(sequenceInputStream);
            sequenceInputStream.close();
            return strComputeInputStreamMd5;
        } catch (Throwable th) {
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "getLBPackageMd5 fail ", th, new Object[0]);
            return null;
        }
    }

    public List<l> a(int i) {
        return a(i, (HttpParams) null, -1, (String) null);
    }

    public List<l> a(int i, int i2, String str) {
        try {
            return a(i, (HttpParams) null, i2, str);
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("VersionManager", "queryDlList: ", e, new Object[0]);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:132:0x0570 A[Catch: Exception -> 0x1c45, TryCatch #20 {Exception -> 0x1c45, blocks: (B:77:0x022c, B:79:0x02ae, B:81:0x02c4, B:82:0x02ec, B:84:0x0339, B:85:0x0353, B:104:0x04c3, B:107:0x04cd, B:130:0x0565, B:132:0x0570, B:133:0x0588, B:135:0x05a5, B:137:0x05a9, B:141:0x05b3, B:143:0x05bf, B:145:0x05ca, B:108:0x04d2, B:112:0x04e7, B:115:0x04ff, B:121:0x0520, B:123:0x0526, B:125:0x0545, B:127:0x054b, B:129:0x0551, B:116:0x0503, B:120:0x0518, B:103:0x04b6), top: B:998:0x022c }] */
    /* JADX WARN: Code duplicated, block: B:135:0x05a5 A[Catch: Exception -> 0x1c45, TryCatch #20 {Exception -> 0x1c45, blocks: (B:77:0x022c, B:79:0x02ae, B:81:0x02c4, B:82:0x02ec, B:84:0x0339, B:85:0x0353, B:104:0x04c3, B:107:0x04cd, B:130:0x0565, B:132:0x0570, B:133:0x0588, B:135:0x05a5, B:137:0x05a9, B:141:0x05b3, B:143:0x05bf, B:145:0x05ca, B:108:0x04d2, B:112:0x04e7, B:115:0x04ff, B:121:0x0520, B:123:0x0526, B:125:0x0545, B:127:0x054b, B:129:0x0551, B:116:0x0503, B:120:0x0518, B:103:0x04b6), top: B:998:0x022c }] */
    /* JADX WARN: Code duplicated, block: B:144:0x05c9  */
    /* JADX WARN: Code duplicated, block: B:162:0x0644 A[Catch: Exception -> 0x1a8c, TRY_ENTER, TryCatch #4 {Exception -> 0x1a8c, blocks: (B:154:0x061b, B:163:0x0656, B:162:0x0644), top: B:968:0x061b }] */
    /* JADX WARN: Code duplicated, block: B:165:0x065e A[Catch: Exception -> 0x0637, TRY_ENTER, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:167:0x067f A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:170:0x06b3 A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:173:0x06c8 A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:176:0x06da A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:190:0x072e A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:191:0x074b  */
    /* JADX WARN: Code duplicated, block: B:196:0x0788 A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:205:0x07a9 A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:207:0x07af A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:209:0x07b5 A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:211:0x07e8  */
    /* JADX WARN: Code duplicated, block: B:212:0x07eb  */
    /* JADX WARN: Code duplicated, block: B:215:0x084a A[Catch: Exception -> 0x0637, TryCatch #9 {Exception -> 0x0637, blocks: (B:156:0x0627, B:165:0x065e, B:167:0x067f, B:168:0x0683, B:170:0x06b3, B:171:0x06b7, B:173:0x06c8, B:174:0x06cc, B:176:0x06da, B:178:0x06e4, B:181:0x06ee, B:183:0x06fd, B:185:0x0711, B:194:0x0759, B:196:0x0788, B:198:0x078e, B:200:0x0794, B:202:0x079a, B:204:0x07a0, B:205:0x07a9, B:207:0x07af, B:209:0x07b5, B:213:0x07ed, B:215:0x084a, B:219:0x08a4, B:220:0x08ad, B:188:0x0720, B:190:0x072e), top: B:978:0x0627 }] */
    /* JADX WARN: Code duplicated, block: B:217:0x08a1  */
    /* JADX WARN: Code duplicated, block: B:218:0x08a3  */
    /* JADX WARN: Code duplicated, block: B:223:0x08cf A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:225:0x0901 A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:226:0x090e A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:229:0x091e A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:233:0x0927 A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:235:0x0937 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:236:0x0939 A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:237:0x099d A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:239:0x09a5 A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:241:0x09ab A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:244:0x09c9  */
    /* JADX WARN: Code duplicated, block: B:249:0x0a16 A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:250:0x0a34  */
    /* JADX WARN: Code duplicated, block: B:254:0x0a64  */
    /* JADX WARN: Code duplicated, block: B:256:0x0a6a A[Catch: Exception -> 0x0a58, TryCatch #6 {Exception -> 0x0a58, blocks: (B:247:0x09ed, B:249:0x0a16, B:256:0x0a6a, B:258:0x0a74, B:260:0x0a7c, B:265:0x0abf, B:267:0x0ac7, B:269:0x0acf, B:271:0x0aee, B:272:0x0afa, B:276:0x0b1f, B:278:0x0b27, B:280:0x0b2f, B:282:0x0b4e, B:284:0x0b5b, B:286:0x0b61, B:292:0x0b7a, B:222:0x08c8, B:223:0x08cf, B:225:0x0901, B:227:0x091a, B:229:0x091e, B:236:0x0939, B:233:0x0927, B:226:0x090e, B:237:0x099d, B:239:0x09a5, B:241:0x09ab, B:242:0x09c3), top: B:972:0x09ed }] */
    /* JADX WARN: Code duplicated, block: B:263:0x0ab9  */
    /* JADX WARN: Code duplicated, block: B:288:0x0b6c  */
    /* JADX WARN: Code duplicated, block: B:290:0x0b72 A[Catch: Exception -> 0x1a85, TRY_ENTER, TRY_LEAVE, TryCatch #19 {Exception -> 0x1a85, blocks: (B:245:0x09e3, B:290:0x0b72, B:294:0x0b82), top: B:996:0x09e3 }] */
    /* JADX WARN: Code duplicated, block: B:392:0x0db8  */
    /* JADX WARN: Code duplicated, block: B:534:0x112e  */
    /* JADX WARN: Code duplicated, block: B:535:0x1131  */
    /* JADX WARN: Code duplicated, block: B:538:0x1139 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:539:0x113b A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:540:0x114c A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:541:0x115a  */
    /* JADX WARN: Code duplicated, block: B:545:0x1178 A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:547:0x117e A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:548:0x1188 A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:551:0x11ad A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:562:0x1226  */
    /* JADX WARN: Code duplicated, block: B:565:0x122b A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:567:0x1233 A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:568:0x123b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:569:0x123d A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:572:0x124c A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:573:0x126e  */
    /* JADX WARN: Code duplicated, block: B:576:0x1278 A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:577:0x1281  */
    /* JADX WARN: Code duplicated, block: B:580:0x128e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:581:0x1290 A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:582:0x1294  */
    /* JADX WARN: Code duplicated, block: B:583:0x1296  */
    /* JADX WARN: Code duplicated, block: B:586:0x129b  */
    /* JADX WARN: Code duplicated, block: B:594:0x12bd A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:624:0x13ca A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:642:0x145f  */
    /* JADX WARN: Code duplicated, block: B:644:0x1463 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:645:0x1465 A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:651:0x1478  */
    /* JADX WARN: Code duplicated, block: B:653:0x1482  */
    /* JADX WARN: Code duplicated, block: B:655:0x1486  */
    /* JADX WARN: Code duplicated, block: B:657:0x148b A[Catch: Exception -> 0x1457, TryCatch #8 {Exception -> 0x1457, blocks: (B:631:0x13f9, B:635:0x1423, B:637:0x1427, B:639:0x144c, B:645:0x1465, B:648:0x146d, B:652:0x147a, B:657:0x148b, B:659:0x149c, B:661:0x14a4, B:664:0x14bb, B:666:0x14c1, B:668:0x14c5, B:670:0x14d1, B:672:0x14d5, B:673:0x14df, B:674:0x14e7, B:677:0x1500, B:682:0x1509, B:681:0x1506, B:683:0x151d, B:685:0x1522, B:693:0x152e, B:705:0x157f, B:709:0x15d2, B:711:0x15dc, B:532:0x112a, B:536:0x1133, B:539:0x113b, B:543:0x1160, B:545:0x1178, B:547:0x117e, B:549:0x1194, B:551:0x11ad, B:553:0x11b5, B:555:0x11bd, B:557:0x11c8, B:559:0x11fa, B:563:0x1227, B:565:0x122b, B:567:0x1233, B:570:0x1242, B:572:0x124c, B:574:0x1272, B:576:0x1278, B:578:0x128a, B:581:0x1290, B:590:0x12a1, B:592:0x12a9, B:593:0x12b9, B:594:0x12bd, B:569:0x123d, B:548:0x1188, B:540:0x114c, B:525:0x10df, B:527:0x10e7, B:529:0x10ef, B:596:0x12d3, B:598:0x12dd, B:600:0x12eb, B:601:0x12f5, B:605:0x1316, B:607:0x1327, B:608:0x1335, B:613:0x136b, B:615:0x1373, B:622:0x1394, B:624:0x13ca, B:618:0x1380, B:620:0x138a), top: B:976:0x13f9, inners: #2 }] */
    /* JADX WARN: Code duplicated, block: B:695:0x1543  */
    /* JADX WARN: Code duplicated, block: B:698:0x1548  */
    /* JADX WARN: Code duplicated, block: B:699:0x1564  */
    /* JADX WARN: Code duplicated, block: B:702:0x1570 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:721:0x1625  */
    /* JADX WARN: Code duplicated, block: B:723:0x1629 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:728:0x1661  */
    /* JADX WARN: Code duplicated, block: B:730:0x1667 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:731:0x16d5 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:736:0x16fb  */
    /* JADX WARN: Code duplicated, block: B:739:0x1709 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:741:0x1742 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:742:0x1747  */
    /* JADX WARN: Code duplicated, block: B:743:0x174a  */
    /* JADX WARN: Code duplicated, block: B:746:0x1758 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:747:0x175a A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:748:0x1761 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:750:0x1769 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:751:0x17a3  */
    /* JADX WARN: Code duplicated, block: B:753:0x17bf  */
    /* JADX WARN: Code duplicated, block: B:755:0x17c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:758:0x17cd A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:765:0x183c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:769:0x1843 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:772:0x18a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:778:0x18e0 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:783:0x1911 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:794:0x192d A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:796:0x1932 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:800:0x193e A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:806:0x1958  */
    /* JADX WARN: Code duplicated, block: B:809:0x1973 A[Catch: Exception -> 0x19b7, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:810:0x197a  */
    /* JADX WARN: Code duplicated, block: B:815:0x19a0 A[Catch: Exception -> 0x19b7, TRY_LEAVE, TryCatch #7 {Exception -> 0x19b7, blocks: (B:485:0x1010, B:487:0x1016, B:493:0x102b, B:495:0x1035, B:497:0x104c, B:700:0x1566, B:703:0x1572, B:707:0x158c, B:716:0x15f4, B:718:0x1601, B:720:0x160b, B:723:0x1629, B:725:0x164b, B:727:0x1655, B:730:0x1667, B:747:0x175a, B:750:0x1769, B:752:0x17a5, B:756:0x17c5, B:759:0x17cf, B:761:0x17dd, B:762:0x17f8, B:763:0x1811, B:770:0x1899, B:773:0x18a3, B:775:0x18af, B:776:0x18d0, B:778:0x18e0, B:780:0x18e8, B:781:0x190d, B:783:0x1911, B:785:0x1915, B:787:0x191c, B:792:0x1927, B:800:0x193e, B:802:0x194f, B:807:0x195a, B:809:0x1973, B:811:0x197c, B:813:0x1985, B:815:0x19a0, B:794:0x192d, B:796:0x1932, B:798:0x1936, B:769:0x1843, B:748:0x1761, B:731:0x16d5, B:733:0x16e3, B:735:0x16ed, B:737:0x16ff, B:739:0x1709, B:741:0x1742, B:506:0x1085, B:508:0x109f, B:511:0x10ab, B:513:0x10af, B:517:0x10b7), top: B:974:0x102b }] */
    /* JADX WARN: Code duplicated, block: B:910:0x1bf6 A[Catch: Exception -> 0x1c43, TryCatch #23 {Exception -> 0x1c43, blocks: (B:866:0x1ad7, B:867:0x1adf, B:869:0x1ae5, B:871:0x1af3, B:873:0x1b16, B:875:0x1b1c, B:877:0x1b27, B:880:0x1b33, B:885:0x1b41, B:888:0x1b54, B:890:0x1b61, B:892:0x1b6c, B:893:0x1b86, B:894:0x1b8f, B:896:0x1b95, B:898:0x1ba6, B:900:0x1bac, B:902:0x1bb0, B:904:0x1bbe, B:906:0x1bd7, B:908:0x1bee, B:911:0x1bf9, B:910:0x1bf6, B:912:0x1bfe, B:913:0x1c05, B:915:0x1c0b, B:921:0x1c25, B:922:0x1c2b, B:923:0x1c32, B:924:0x1c33, B:925:0x1c42), top: B:1004:0x05a3 }] */
    /* JADX WARN: Code duplicated, block: B:972:0x09ed A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v109 */
    /* JADX WARN: Type inference failed for: r10v116, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v117 */
    /* JADX WARN: Type inference failed for: r10v122 */
    /* JADX WARN: Type inference failed for: r10v126 */
    /* JADX WARN: Type inference failed for: r10v127 */
    /* JADX WARN: Type inference failed for: r10v157 */
    /* JADX WARN: Type inference failed for: r10v158 */
    /* JADX WARN: Type inference failed for: r10v159 */
    /* JADX WARN: Type inference failed for: r10v160 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v39 */
    /* JADX WARN: Type inference failed for: r10v52 */
    /* JADX WARN: Type inference failed for: r11v100, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v127 */
    /* JADX WARN: Type inference failed for: r11v128 */
    /* JADX WARN: Type inference failed for: r11v129 */
    /* JADX WARN: Type inference failed for: r11v130 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v96 */
    /* JADX WARN: Type inference failed for: r12v118 */
    /* JADX WARN: Type inference failed for: r12v119 */
    /* JADX WARN: Type inference failed for: r12v120 */
    /* JADX WARN: Type inference failed for: r12v121 */
    /* JADX WARN: Type inference failed for: r12v122 */
    /* JADX WARN: Type inference failed for: r12v123 */
    /* JADX WARN: Type inference failed for: r12v124 */
    /* JADX WARN: Type inference failed for: r12v125 */
    /* JADX WARN: Type inference failed for: r12v69, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v70 */
    /* JADX WARN: Type inference failed for: r12v71 */
    /* JADX WARN: Type inference failed for: r12v72 */
    /* JADX WARN: Type inference failed for: r12v75 */
    /* JADX WARN: Type inference failed for: r12v76 */
    /* JADX WARN: Type inference failed for: r12v83 */
    /* JADX WARN: Type inference failed for: r12v86, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v92 */
    /* JADX WARN: Type inference failed for: r19v18 */
    /* JADX WARN: Type inference failed for: r19v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.excelliance.lbsdk.main.an] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List, java.util.List<com.excelliance.lbsdk.main.l>] */
    /* JADX WARN: Type inference failed for: r1v100 */
    /* JADX WARN: Type inference failed for: r1v101 */
    /* JADX WARN: Type inference failed for: r1v102 */
    /* JADX WARN: Type inference failed for: r1v103 */
    /* JADX WARN: Type inference failed for: r1v104 */
    /* JADX WARN: Type inference failed for: r1v105 */
    /* JADX WARN: Type inference failed for: r1v106 */
    /* JADX WARN: Type inference failed for: r1v107 */
    /* JADX WARN: Type inference failed for: r1v108 */
    /* JADX WARN: Type inference failed for: r1v109 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v110 */
    /* JADX WARN: Type inference failed for: r1v111 */
    /* JADX WARN: Type inference failed for: r1v112 */
    /* JADX WARN: Type inference failed for: r1v113 */
    /* JADX WARN: Type inference failed for: r1v114 */
    /* JADX WARN: Type inference failed for: r1v115 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List, java.util.List<com.excelliance.lbsdk.main.l>] */
    /* JADX WARN: Type inference failed for: r1v36, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v40, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v54 */
    /* JADX WARN: Type inference failed for: r1v55 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v63 */
    /* JADX WARN: Type inference failed for: r1v64 */
    /* JADX WARN: Type inference failed for: r1v72, types: [java.util.List, java.util.List<com.excelliance.lbsdk.main.l>] */
    /* JADX WARN: Type inference failed for: r1v78 */
    /* JADX WARN: Type inference failed for: r1v86 */
    /* JADX WARN: Type inference failed for: r1v87 */
    /* JADX WARN: Type inference failed for: r1v89 */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.excelliance.lbsdk.main.an] */
    /* JADX WARN: Type inference failed for: r1v90 */
    /* JADX WARN: Type inference failed for: r1v91 */
    /* JADX WARN: Type inference failed for: r1v92 */
    /* JADX WARN: Type inference failed for: r1v93 */
    /* JADX WARN: Type inference failed for: r1v94 */
    /* JADX WARN: Type inference failed for: r1v95 */
    /* JADX WARN: Type inference failed for: r1v96 */
    /* JADX WARN: Type inference failed for: r1v97 */
    /* JADX WARN: Type inference failed for: r1v98 */
    /* JADX WARN: Type inference failed for: r1v99 */
    /* JADX WARN: Type inference failed for: r2v122, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v75, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r39v1 */
    /* JADX WARN: Type inference failed for: r3v122 */
    /* JADX WARN: Type inference failed for: r3v125 */
    /* JADX WARN: Type inference failed for: r3v127 */
    /* JADX WARN: Type inference failed for: r3v128 */
    /* JADX WARN: Type inference failed for: r3v129 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v130 */
    /* JADX WARN: Type inference failed for: r3v131 */
    /* JADX WARN: Type inference failed for: r3v132 */
    /* JADX WARN: Type inference failed for: r3v133 */
    /* JADX WARN: Type inference failed for: r3v134 */
    /* JADX WARN: Type inference failed for: r3v135 */
    /* JADX WARN: Type inference failed for: r3v136 */
    /* JADX WARN: Type inference failed for: r3v137 */
    /* JADX WARN: Type inference failed for: r3v138 */
    /* JADX WARN: Type inference failed for: r3v139 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v140 */
    /* JADX WARN: Type inference failed for: r3v141 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19, types: [com.excelliance.lbsdk.main.an] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.excelliance.lbsdk.main.an] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57, types: [com.excelliance.lbsdk.main.an] */
    /* JADX WARN: Type inference failed for: r3v61, types: [com.excelliance.lbsdk.main.an] */
    /* JADX WARN: Type inference failed for: r3v62 */
    /* JADX WARN: Type inference failed for: r3v72 */
    /* JADX WARN: Type inference failed for: r3v73 */
    /* JADX WARN: Type inference failed for: r4v140, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v157, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v75, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v81 */
    /* JADX WARN: Type inference failed for: r4v82, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v84 */
    /* JADX WARN: Type inference failed for: r5v22, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v63 */
    /* JADX WARN: Type inference failed for: r6v64, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v69 */
    /* JADX WARN: Type inference failed for: r74v0 */
    /* JADX WARN: Type inference failed for: r74v1 */
    /* JADX WARN: Type inference failed for: r74v2 */
    /* JADX WARN: Type inference failed for: r74v3 */
    /* JADX WARN: Type inference failed for: r74v4 */
    /* JADX WARN: Type inference failed for: r7v63, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r96v0 */
    /* JADX WARN: Type inference failed for: r96v1 */
    /* JADX WARN: Type inference failed for: r96v4 */
    /* JADX WARN: Type inference failed for: r96v5 */
    /* JADX WARN: Type inference failed for: r9v106 */
    /* JADX WARN: Type inference failed for: r9v107, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r9v108, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r9v118 */
    /* JADX WARN: Type inference failed for: r9v153 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:148:0x05f4
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:111)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public java.util.List<com.excelliance.lbsdk.main.l> a(int r108, org.apache.http.params.HttpParams r109, int r110, java.lang.String r111) {
        /*
            Method dump skipped, instruction units count: 7428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.an.a(int, org.apache.http.params.HttpParams, int, java.lang.String):java.util.List");
    }

    public void a(Context context) {
        if (context != null) {
            this.c = context;
        } else {
            context = this.c;
        }
        this.g = context.getApplicationInfo().dataDir + "/lebian/";
        if (GameUtil.getIntance().getContext() == null) {
            GameUtil.getIntance().setContext(context);
        }
    }

    public void a(c cVar) {
        this.h = cVar;
    }

    public void a(l lVar) {
        if (lVar == null || !lVar.isInstall || lVar.savePath == null || !new File(lVar.savePath).exists() || !al.b(this.c) || al.d(this.c)) {
            return;
        }
        if (!b(this.c) || lVar.isInstall) {
            Intent intent = new Intent("com.excelliance.open.action.startPromptActivity");
            intent.setPackage(this.c.getPackageName());
            intent.setFlags(268435456);
            intent.putExtra("dialogtype", 3);
            intent.putExtra("apkPath", lVar.savePath);
            intent.putExtra("forceUpdate", lVar.forceUpdate);
            this.c.startActivity(intent);
            Intent intent2 = new Intent(BwbxUtil.ACTION_PLAT_DO);
            intent2.setPackage(this.c.getPackageName());
            intent2.setComponent(new ComponentName(this.c.getPackageName(), BGService.class.getName()));
            intent2.putExtra("activityIntent", intent);
            intent2.putExtra(BwbxUtil.ACTION_TYPE, 7);
            GameUtil.startService(intent2, this.c);
        }
        if (b(this.c) && lVar.isInstall) {
            Intent intent3 = new Intent(this.c.getPackageName() + "com.excelliance.open.action.overlay_install");
            intent3.putExtra("isinstall", lVar.isInstall);
            intent3.putExtra("apkpath", lVar.savePath);
            s.a(this.c, intent3);
        }
    }

    public void a(String str, l lVar) {
        a(str, lVar, true);
    }

    public void a(String str, l lVar, boolean z) {
        if (lVar.expansionOnly) {
            return;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            } else {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            String str2 = lVar.savePath;
            if (lVar.savePath == null || lVar.savePath.length() == 0 || lVar.savePath.equals("null")) {
                String strSubstring = str.substring(0, str.lastIndexOf("/"));
                String strSubstring2 = strSubstring.substring(0, strSubstring.lastIndexOf("/"));
                if (lVar.url != null && lVar.url.length() > 0) {
                    str2 = strSubstring2 + "/jar/" + lVar.url.substring(lVar.url.lastIndexOf("/") + 1) + ".dload";
                }
            }
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "generateCfgFile srcFile = " + str + ",savePath = " + str2 + ", gDetail.savePath = " + lVar.savePath + ", gDetail.url = " + lVar.url + ", gDetail.urlBk = " + lVar.urlBk + ", gDetail.urlBkSec = " + lVar.urlBkSecond + ", full=" + z, new Object[0]);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
            sb.append("<game>\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<ginfo id=\"");
            sb2.append(lVar.gameId);
            sb2.append("\" ");
            sb.append(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append("version=\"");
            sb3.append(lVar.version);
            sb3.append("\" ");
            sb.append(sb3.toString());
            sb.append("level=\"" + lVar.level + "\" ");
            sb.append("metaversion=\"" + lVar.metaVersion + "\" ");
            StringBuilder sb4 = new StringBuilder();
            sb4.append("gfup=\"");
            String str3 = "1";
            sb4.append(lVar.forceUpdate ? "1" : "0");
            sb4.append("\" ");
            sb.append(sb4.toString());
            sb.append("abbr=\"" + lVar.gameLib + "\" ");
            sb.append("savepath=\"" + str2 + "\" ");
            sb.append("flag=\"" + lVar.flag + "\" ");
            sb.append("size=\"" + lVar.size + "\" ");
            sb.append("dmd5=\"" + lVar.dmd5 + "\" ");
            sb.append("url=\"" + lVar.url + "\" ");
            sb.append("urlBk=\"" + lVar.urlBk + "\" ");
            sb.append("urlbksecond=\"" + lVar.urlBkSecond + "\" ");
            sb.append("omd5=\"" + lVar.omd5 + "\" ");
            sb.append("nmd5=\"" + lVar.nmd5 + "\" ");
            sb.append("patch=\"" + lVar.patch + "\" ");
            StringBuilder sb5 = new StringBuilder();
            sb5.append("adl=\"");
            sb5.append(lVar.autodl ? "1" : "0");
            sb5.append("\" ");
            sb.append(sb5.toString());
            sb.append("expomd5=\"" + lVar.expanOmd5 + "\" ");
            sb.append("expdmd5=\"" + lVar.expanDmd5 + "\" ");
            sb.append("expnmd5=\"" + lVar.expanNmd5 + "\" ");
            sb.append("expsize=\"" + lVar.expanSize + "\" ");
            sb.append("expurl=\"" + lVar.expanUrl + "\" ");
            sb.append("expurlbk=\"" + lVar.expanUrlBk + "\" ");
            sb.append("expsavepath=\"" + lVar.expanSavePath + "\" ");
            sb.append("exppatch=\"" + lVar.expanPatch + "\" ");
            StringBuilder sb6 = new StringBuilder();
            sb6.append("prepay=\"");
            sb6.append(lVar.prepay ? "1" : "0");
            sb6.append("\" ");
            sb.append(sb6.toString());
            StringBuilder sb7 = new StringBuilder();
            sb7.append("rc=\"");
            sb7.append(lVar.rc == null ? "0" : lVar.rc);
            sb7.append("\" ");
            sb.append(sb7.toString());
            sb.append("gvercode=\"" + lVar.apkVercode + "\"");
            if (lVar.twiceLoadUrl != null) {
                sb.append("tpath=\"" + lVar.twiceLoadUrl + "\" ");
                sb.append("tpathbk=\"" + lVar.twiceLoadUrlBk + "\" ");
                sb.append("tmd5=\"" + lVar.tmd5 + "\" ");
                sb.append("tsize=\"" + lVar.tSize + "\" ");
            }
            if (lVar.hotResUrl != null) {
                sb.append("hotresurl=\"" + lVar.hotResUrl + "\" ");
                sb.append("hotresurlbk=\"" + lVar.hotResUrlBk + "\" ");
                sb.append("hotresnmd5=\"" + lVar.hotResNmd5 + "\" ");
                sb.append("hotresomd5=\"" + lVar.hotResOmd5 + "\" ");
                sb.append("hotresdmd5=\"" + lVar.hotResDmd5 + "\" ");
                sb.append("hotresdsize=\"" + lVar.hotResDSize + "\" ");
                sb.append("hotresnsize=\"" + lVar.hotResNSize + "\" ");
                sb.append("hotrescfg=\"" + lVar.hotResCfg + "\" ");
                sb.append("hotresavepath=\"" + lVar.hotResSavePath + "\" ");
                sb.append("hotrespatch=\"" + lVar.hotResPatch + "\" ");
                sb.append("rpred=\"" + lVar.rpred + "\" ");
                sb.append("rponly=\"" + lVar.rpOnly + "\" ");
                sb.append("hotresversioncode=\"" + lVar.hotResVersionCode + "\" ");
            }
            if (lVar.notifyTitle != null) {
                sb.append("ntftitle=\"" + lVar.notifyTitle + "\" ");
            }
            if (lVar.notifyMsg != null) {
                sb.append("ntfmsg=\"" + lVar.notifyMsg + "\" ");
            }
            StringBuilder sb8 = new StringBuilder();
            sb8.append("install=\"");
            sb8.append(lVar.isInstall ? "1" : "0");
            sb8.append("\" ");
            sb.append(sb8.toString());
            StringBuilder sb9 = new StringBuilder();
            sb9.append("predl=\"");
            sb9.append(lVar.preDownload ? "1" : "0");
            sb9.append("\" ");
            sb.append(sb9.toString());
            StringBuilder sb10 = new StringBuilder();
            sb10.append("sign=\"");
            if (!lVar.sign) {
                str3 = "0";
            }
            sb10.append(str3);
            sb10.append("\" ");
            sb.append(sb10.toString());
            if (lVar.includeDir != null) {
                sb.append("udir=\"" + lVar.includeDir + "\" ");
            }
            if (lVar.excludeDir != null) {
                sb.append("kdir=\"" + lVar.excludeDir + "\" ");
            }
            sb.append(" />\n</game>\n");
            randomAccessFile.write(sb.toString().getBytes());
            randomAccessFile.close();
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("VersionManager", "writeToFile Exception", e, new Object[0]);
        }
    }

    public void a(final String[] strArr, final String str, final int i, final String str2) {
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.an.3
            /*  JADX ERROR: Type inference failed
                jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 8551. Try increasing type updates limit count.
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
                */
            @Override // java.lang.Runnable
            public void run() {
                /*
                    Method dump skipped, instruction units count: 855
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.an.AnonymousClass3.run():void");
            }
        }, "VersionManager:startTransfer").start();
    }

    public String b() {
        synchronized (this.e) {
            if (this.d != null) {
                return this.d;
            }
            this.d = ae.b(this.c, "lebian_module_userinfo", "uid", (String) null);
            if (this.d != null) {
                return this.d;
            }
            try {
                GameUtil intance = GameUtil.getIntance();
                StringBuilder sb = new StringBuilder(intance.getBaseUrl("s") + "gensdkuser.php");
                sb.append("?chid=");
                sb.append(intance.getApkMainCh());
                sb.append("&cpmeta=");
                sb.append(URLEncoder.encode(intance.getClientChId(), "UTF-8"));
                sb.append("&vercode=");
                sb.append(intance.getRunningVersionCode(false, true));
                sb.append("&md5=" + a(true));
                sb.append("&type=" + intance.getPlatformType());
                sb.append("&sdkver=" + intance.getOTAVersion());
                sb.append("&pkg=" + this.c.getPackageName());
                sb.append("&abbranch=" + AbTestManager.getInstance(this.c).getBranch());
                try {
                    com.excelliance.lbsdk.main.z.a aVar = new com.excelliance.lbsdk.main.z.a();
                    aVar.a(Build.VERSION.SDK_INT);
                    aVar.a(Build.VERSION.RELEASE);
                    aVar.c(URLEncoder.encode(Build.BRAND, "UTF-8").replaceAll("\\+", "%20"));
                    aVar.d(URLEncoder.encode(Build.MANUFACTURER, "UTF-8").replaceAll("\\+", "%20"));
                    aVar.e(URLEncoder.encode(com.excelliance.lbsdk.main.ai.a.a(this.c), "UTF-8").replaceAll("\\+", "%20"));
                    aVar.f(URLEncoder.encode(Build.PRODUCT, "UTF-8").replaceAll("\\+", "%20"));
                    aVar.m(intance.getApiLvl());
                    aVar.n(intance.getAndroidVersion());
                    aVar.g(intance.getMd5AndroidId());
                    aVar.o(intance.getScreenDensity(this.c));
                    aVar.q(aa.c(this.c));
                    aVar.p(intance.getTotalMemory());
                    aVar.r(this.c.getPackageName());
                    aVar.s(String.valueOf(intance.getVerCodeFromApk(this.c.getPackageResourcePath())));
                    sb.append("&data=" + URLEncoder.encode(com.excelliance.lbsdk.main.ai.a.a(aVar), "UTF-8"));
                } catch (Exception unused) {
                }
                String string = sb.toString();
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "getuser URL:" + string, new Object[0]);
                o oVarA = aa.a(string, this.c);
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "genUser=" + oVarA.a, new Object[0]);
                if (oVarA.a == 200) {
                    String str = oVarA.b;
                    if (str != null && str.length() != 0) {
                        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                        xmlPullParserNewPullParser.setInput(new StringReader(str));
                        for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                            if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equalsIgnoreCase("info")) {
                                this.d = xmlPullParserNewPullParser.getAttributeValue(null, "uid");
                                com.excelliance.lbsdk.main.g.b.c("VersionManager", "uid=" + this.d, new Object[0]);
                            }
                        }
                    }
                    return null;
                }
                if (this.d != null && this.d.length() > 0 && !this.d.equals("0")) {
                    ae.a(this.c, "lebian_module_userinfo", "uid", this.d);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return this.d;
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00ad  */
    public String b(long j) {
        String str;
        String absolutePath;
        String str2 = null;
        if (GameUtil.getIntance().hasExternalStorage()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            absolutePath = externalStorageDirectory.getAbsolutePath();
            if (GameUtil.getFreeSpace(externalStorageDirectory) > 102400 + j) {
                str = GameUtil.getExternalDataPath(this.c) + "/lebian/";
                try {
                    File file = new File(str, "writeTest" + Process.myPid() + Process.myTid());
                    if (!file.exists()) {
                        new File(str).mkdirs();
                        file.createNewFile();
                    }
                    if (!file.exists()) {
                        com.excelliance.lbsdk.main.g.b.c("VersionManager", "external storage can not write", new Object[0]);
                        str = null;
                    }
                } catch (Exception unused) {
                    com.excelliance.lbsdk.main.g.b.c("VersionManager", "external storage write error", new Object[0]);
                }
            } else {
                str = null;
            }
        } else {
            str = null;
            absolutePath = null;
        }
        if (Build.VERSION.SDK_INT < 26) {
            int iA = m.a(this.c, "NEWV_LOCATION", 4);
            if (iA != 4) {
                if (iA == 5) {
                    str2 = str;
                }
            } else if ((absolutePath == null || !absolutePath.startsWith("/storage/emulated/0")) && j >= 52428800) {
                str2 = str;
            }
        }
        if (str2 == null) {
            return GameUtil.getFreeSpace(this.g) > j + 20971520 ? this.g : str;
        }
        return str2;
    }

    public List<l> b(String str) {
        StringBuilder sb;
        String absolutePath;
        GameUtil intance = GameUtil.getIntance();
        String packageName = this.c.getPackageName();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(this.g + packageName + "-1/");
        arrayList.add(this.g + packageName + "-2/");
        if (intance.hasExternalStorage()) {
            String str2 = GameUtil.getExternalDataPath(this.c) + "/lebian/";
            arrayList.add(str2 + packageName + "-1/");
            arrayList.add(str2 + packageName + "-2/");
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str3 : arrayList) {
            String str4 = str3 + str + ".cfg";
            l lVarA = a(str3 + str + ".cfg");
            File file = new File(str3, "updated");
            File file2 = null;
            if (file.exists()) {
                File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.excelliance.lbsdk.main.an.1
                    @Override // java.io.FilenameFilter
                    public boolean accept(File file3, String str5) {
                        return str5.startsWith("lebian_hu_list-");
                    }
                });
                if (fileArrListFiles.length > 0) {
                    file2 = fileArrListFiles[0];
                }
            }
            if (file2 == null) {
                file2 = new File(str3, "base" + k.a(lVarA));
            }
            if (lVarA == null || !file2.exists()) {
                new File(str4).delete();
            } else {
                lVarA.savePath = file2.getAbsolutePath();
                lVarA.cfgPath = str4;
                if (lVarA.savePath.contains("lebian_hu_list-")) {
                    sb = new StringBuilder();
                    sb.append("main.");
                    absolutePath = this.c.getApplicationContext().getPackageCodePath();
                } else {
                    sb = new StringBuilder();
                    sb.append("main.");
                    absolutePath = file2.getAbsolutePath();
                }
                sb.append(intance.getVerCodeFromApk(absolutePath, true));
                sb.append(".");
                sb.append(packageName);
                sb.append(".obb");
                File file3 = new File(str3, sb.toString());
                if (file3.exists()) {
                    lVarA.expanSavePath = file3.getAbsolutePath();
                }
                arrayList2.add(lVarA);
            }
        }
        return arrayList2;
    }

    public void b(final String[] strArr, final String str, final int i, final String str2) {
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.an.4
            /*  JADX ERROR: Type inference failed
                jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 9521. Try increasing type updates limit count.
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
                */
            @Override // java.lang.Runnable
            public void run() {
                /*
                    Method dump skipped, instruction units count: 952
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.an.AnonymousClass4.run():void");
            }
        }, "VersionManager:startTransferPkgMd5Info").start();
    }

    public boolean b(Context context) throws IOException {
        boolean z = true;
        try {
            if (c()) {
                String strE = e();
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallClient filePath:" + strE, new Object[0]);
                if (strE == null || !new File(strE).exists()) {
                    z = false;
                } else {
                    ZipFile zipFile = new ZipFile(strE);
                    try {
                        ZipEntry entry = zipFile.getEntry("assets/lebian/KW");
                        com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg entry:" + entry, new Object[0]);
                        z = entry != null;
                        zipFile.close();
                    } catch (Throwable th) {
                        try {
                            zipFile.close();
                        } catch (Throwable unused) {
                        }
                        throw th;
                    }
                }
            } else {
                InputStream inputStreamOpen = context.getAssets().open("lebian/KW");
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException | Exception unused2) {
                    }
                }
            }
        } catch (IOException | Exception unused3) {
            z = false;
        }
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallClient : " + z, new Object[0]);
        return z;
    }

    public String[] b(boolean z) {
        String strD;
        String[] strArr;
        String strE = e();
        if (strE != null) {
            return new String[]{strE};
        }
        Context context = this.c;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(context.getPackageCodePath());
        if (Build.VERSION.SDK_INT >= 21 && (strArr = context.getApplicationInfo().splitSourceDirs) != null) {
            linkedHashSet.addAll(Arrays.asList(strArr));
        }
        if (i()) {
            String str = GameUtil.getObbPath(context) + "/main." + GameUtil.getIntance().getVerCodeFromApk(context.getPackageCodePath(), true) + "." + context.getPackageName() + ".obb";
            if (new File(str).exists()) {
                linkedHashSet.add(str);
            }
        }
        if (z && (strD = com.excelliance.lbsdk.main.ae.c.d(context)) != null) {
            linkedHashSet.add(strD);
        }
        return (String[]) linkedHashSet.toArray(new String[0]);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004d  */
    public String c(long j) {
        String strA = a(j);
        if (strA == null) {
            return strA;
        }
        String strD = com.excelliance.lbsdk.main.ae.c.d(this.c);
        boolean z = false;
        if (strD == null) {
            String strB = ae.b(this.c, "lebian_module_extractinfo", "current", (String) null);
            if (strB != null && strB.startsWith(strA) && strB.contains("-1")) {
                z = true;
            }
        } else if (strD.startsWith(strA)) {
            if (strD.contains(this.c.getPackageName() + "-1")) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strA);
        sb.append(this.c.getPackageName());
        sb.append(z ? "-2/" : "-1/");
        return sb.toString();
    }

    public void c(final String[] strArr, final String str, final int i, final String str2) {
        new Thread(new Runnable() { // from class: com.excelliance.lbsdk.main.an.5
            /*  JADX ERROR: Type inference failed
                jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 9851. Try increasing type updates limit count.
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
                */
            @Override // java.lang.Runnable
            public void run() {
                /*
                    Method dump skipped, instruction units count: 985
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.excelliance.lbsdk.main.an.AnonymousClass5.run():void");
            }
        }, "VersionManager:startTransferPkgZipInfo").start();
    }

    public boolean c() {
        return e() != null;
    }

    public boolean c(String str) {
        return GameUtil.getIntance().remoteRunningPid() > 0;
    }

    public l d() {
        l lVarA = a(ae.b(this.c, "lebian_module_extractinfo", "current", (String) null));
        if (lVarA == null || lVarA.savePath == null || !new File(lVarA.savePath).exists()) {
            return null;
        }
        return lVarA;
    }

    public String d(long j) {
        String str = this.c.getApplicationInfo().dataDir + "/lebian/";
        if (GameUtil.getFreeSpace(str) > j + 20971520) {
            return str;
        }
        return null;
    }

    public boolean d(String str) {
        boolean z = false;
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg filePath:" + str, new Object[0]);
        if (str != null && new File(str).exists()) {
            try {
                ZipFile zipFile = new ZipFile(str);
                try {
                    ZipEntry entry = zipFile.getEntry("assets/tryflag");
                    com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg entry:" + entry, new Object[0]);
                    z = entry != null;
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
        }
        return z;
    }

    public String e() {
        l lVarA = a(ae.b(this.c, "lebian_module_extractinfo", "current", (String) null));
        if (lVarA == null || lVarA.savePath == null || !new File(lVarA.savePath).exists() || lVarA.isInstall) {
            return null;
        }
        return lVarA.savePath;
    }

    public String e(String str) {
        return m() + "lbapk_" + str + ".bak";
    }

    public void f(String str) {
        if (n.b()) {
            b(str, e(String.valueOf(GameUtil.getIntance().getVerCodeFromApk(str, false, true))));
        }
    }

    public String[] f() {
        return b(false);
    }

    public String g(String str) {
        GameUtil.getIntance();
        return GameUtil.getResRoot(this.c) + "newRaw/" + str + "/";
    }

    public String[] g() throws IOException {
        String[] strArr;
        String strE = e();
        if (strE != null) {
            return new String[]{strE};
        }
        Context context = this.c;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(context.getPackageCodePath());
        if (Build.VERSION.SDK_INT >= 21 && (strArr = context.getApplicationInfo().splitSourceDirs) != null) {
            for (String str : strArr) {
                if (!str.startsWith("split_config.")) {
                    linkedHashSet.add(str);
                }
            }
        }
        if (i() && !m.a(context, "START_CHECK_PRIVACY_PERMISSION", false)) {
            String str2 = GameUtil.getObbPath(context) + "/main." + GameUtil.getIntance().getVerCodeFromApk(context.getPackageCodePath(), true) + "." + context.getPackageName() + ".obb";
            if (new File(str2).exists()) {
                linkedHashSet.add(str2);
            }
        }
        return (String[]) linkedHashSet.toArray(new String[0]);
    }

    public String h(String str) {
        return this.g + "newRawCfg/" + str + "/.cfg";
    }

    public void h() {
        this.j = 0L;
    }

    public String i(String str) {
        return this.g + "newRaw/" + str + "/.dload";
    }

    public boolean i() throws IOException {
        try {
            if (!c()) {
                InputStream inputStreamOpen = this.c.getAssets().open("tryflag");
                if (inputStreamOpen == null) {
                    return true;
                }
                try {
                    inputStreamOpen.close();
                    return true;
                } catch (IOException | Exception unused) {
                    return true;
                }
            }
            String strE = e();
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg filePath:" + strE, new Object[0]);
            if (strE != null && new File(strE).exists()) {
                ZipFile zipFile = new ZipFile(strE);
                try {
                    ZipEntry entry = zipFile.getEntry("assets/tryflag");
                    com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg entry:" + entry, new Object[0]);
                    boolean z = entry != null;
                    zipFile.close();
                    return z;
                } catch (Throwable th) {
                    try {
                        zipFile.close();
                    } catch (Throwable unused2) {
                    }
                    throw th;
                }
            }
            return false;
        } catch (IOException | Exception unused3) {
        }
    }

    public boolean j() {
        boolean z;
        try {
            InputStream inputStreamOpen = this.c.getAssets().open("lebian/KW");
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException unused) {
                }
            }
            z = true;
        } catch (IOException unused2) {
            z = false;
        }
        return c() && z;
    }

    public boolean k() throws IOException {
        boolean zI = i();
        if (o()) {
            return zI || d(n());
        }
        return zI;
    }

    public boolean l() {
        boolean z;
        if (!c()) {
            return false;
        }
        String strE = e();
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg filePath:" + strE, new Object[0]);
        if (strE == null || !new File(strE).exists()) {
            return false;
        }
        try {
            ZipFile zipFile = new ZipFile(strE);
            ZipEntry entry = zipFile.getEntry("assets/tryflag.bak");
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "isSmallPkg entry:" + entry, new Object[0]);
            z = entry != null;
            try {
                zipFile.close();
                return z;
            } catch (Exception e) {
                e = e;
                com.excelliance.lbsdk.main.g.b.c("VersionManager", "isMergePkg: " + e, new Object[0]);
                return z;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
    }

    public String m() {
        return GameUtil.getResRoot(this.c) + "backup/";
    }

    public String n() {
        return e(GameUtil.getIntance().getRunningVersionCode(false, true));
    }

    public boolean o() {
        return new File(n()).exists();
    }

    public String p() {
        return ae.b(this.c, "lebian_module_dloadinfo", "pickurl", (String) null);
    }

    public void q() {
        String str;
        com.excelliance.lbsdk.main.g.b.c("VersionManager", "queryVmFlag entered", new Object[0]);
        GameUtil intance = GameUtil.getIntance();
        StringBuilder sb = new StringBuilder(intance.getBaseUrl() + "getVmFlag.php");
        sb.append("?chid=" + intance.getApkMainCh());
        try {
            sb.append("&cpmeta=" + URLEncoder.encode(intance.getClientChId(), "UTF-8"));
        } catch (Exception unused) {
        }
        sb.append("&vercode=" + intance.getRunningVersionCode(false, true));
        sb.append("&pkg=" + this.c.getPackageName());
        try {
            o oVarA = aa.a(sb.toString(), this.c);
            if (oVarA.a == 200 && (str = oVarA.b) != null && str.length() != 0) {
                ByteArrayInputStream byteArrayInputStream = (str == null || str.trim().equals("")) ? null : new ByteArrayInputStream(str.getBytes());
                XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                    if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equalsIgnoreCase("listpack")) {
                        String attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, "sandbox_flag");
                        String attributeValue2 = xmlPullParserNewPullParser.getAttributeValue(null, "cap_flag");
                        int i = !TextUtils.isEmpty(attributeValue) ? Integer.parseInt(attributeValue) : 0;
                        if (i != ae.b(this.c, "lebian_module_extractinfo", "sandbox_flag", 0)) {
                            ae.a(this.c, "lebian_module_extractinfo", "sandbox_flag", i);
                        }
                        long j = !TextUtils.isEmpty(attributeValue2) ? Long.parseLong(attributeValue2) : 0L;
                        if (j != ae.b(this.c, "lebian_module_extractinfo", "cap_flag_last", 0L)) {
                            ae.a(this.c, "lebian_module_extractinfo", "cap_flag", j);
                        }
                    }
                }
                byteArrayInputStream.close();
            }
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.c("VersionManager", "queryVmFlag e=", e, new Object[0]);
        }
    }

    public String r() {
        return ae.b(this.c, "lebian_module_game_res_info", "hulistcurrent", (String) null);
    }

    public String s() {
        return ae.b(this.c, "lebian_module_game_res_info", "hulistnext", (String) null);
    }
}
