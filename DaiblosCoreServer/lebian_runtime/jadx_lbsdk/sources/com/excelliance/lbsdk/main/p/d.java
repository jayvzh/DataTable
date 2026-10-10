package com.excelliance.lbsdk.main.p;

import android.content.Context;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.LBSdkHelper;
import com.excelliance.lbsdk.main.an;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static boolean a = false;

    public static InputStream a(Context context, String str) throws FileNotFoundException {
        an anVarA = an.a();
        if (!anVarA.c()) {
            return com.excelliance.lbsdk.main.e.a(context, str);
        }
        String strE = anVarA.e();
        if (strE == null || !new File(strE).exists()) {
            return null;
        }
        ZipFile zipFile = new ZipFile(strE);
        ZipEntry entry = zipFile.getEntry("assets/" + str);
        if (entry != null) {
            return new BufferedInputStream(zipFile.getInputStream(entry));
        }
        throw new FileNotFoundException("file is not found:lebian/commonCentral.txt");
    }

    public static boolean a(Context context) {
        try {
            a(context, BwbxUtil.isBigAsset(context) ? "lebian/commonCentralBa.txt" : "lebian/commonCentral.txt");
            return true;
        } catch (Exception unused) {
            com.excelliance.lbsdk.main.g.b.e("BwbxFileUtils", "lebian/commonCentral.txt not exits", new Object[0]);
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x003f  */
    /* JADX WARN: Code duplicated, block: B:9:0x0041  */
    public static boolean b(Context context) {
        boolean z;
        int originalMainVer = GameUtil.getIntance().getOriginalMainVer();
        boolean z2 = false;
        com.excelliance.lbsdk.main.g.b.c("BwbxFileUtils", "downloadFullRes original ver:" + originalMainVer, new Object[0]);
        if (BwbxUtil.isBigAsset(context)) {
            com.excelliance.lbsdk.main.e.b bVar = (com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting");
            if (originalMainVer < 260002 || bVar.d()) {
                z = false;
            } else {
                z = true;
            }
        } else if (originalMainVer >= 250300) {
            z = true;
        } else {
            z = false;
        }
        if (a(context) && z) {
            z2 = true;
        }
        a = z2;
        return a;
    }
}
