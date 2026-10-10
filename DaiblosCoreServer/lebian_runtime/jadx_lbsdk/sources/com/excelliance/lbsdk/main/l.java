package com.excelliance.lbsdk.main;

import android.text.TextUtils;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class l implements Serializable {
    private static final long serialVersionUID = 1;
    public String apkVercode;
    public boolean autoMerge;
    public boolean autodl;
    public String cfgPath;
    public int dlResType;
    public String dmd5;
    public String excludeDir;
    public String expanDmd5;
    public String expanNmd5;
    public String expanOmd5;
    public int expanPatch;
    public String expanSavePath;
    public long expanSize;
    public String expanUrl;
    public String expanUrlBk;
    public boolean expansionOnly;
    public int flag;
    public boolean forceUpdate;
    public String gameId;
    public String gameLib;
    public String hotResCfg;
    public long hotResDSize;
    public String hotResDmd5;
    public long hotResNSize;
    public String hotResNmd5;
    public String hotResOmd5;
    public int hotResPatch;
    public String hotResSavePath;
    public String hotResUrl;
    public String hotResUrlBk;
    public String hotResVersionCode;
    public String includeDir;
    public boolean isInstall;
    public String level;
    public String metaVersion;
    public String nmd5;
    public String notifyMsg;
    public String notifyTitle;
    public String omd5;
    public int patch;
    public boolean preDownload;
    public boolean prepay;
    public String rc;
    public boolean rpOnly;
    public int rpred;
    public String savePath;
    public boolean sign;
    public long size;
    public long tSize;
    public String tmd5;
    public String twiceLoadUrl;
    public String twiceLoadUrlBk;
    public String url;
    public String urlBk;
    public String urlBkSecond;
    public String version;

    public l(int i, String str, String str2, String str3, long j, String str4) {
        this.dlResType = i;
        this.twiceLoadUrl = str;
        this.twiceLoadUrlBk = str2;
        this.tmd5 = str3;
        this.size = j;
        this.savePath = str4;
    }

    public l(int i, String str, String str2, String str3, String str4, int i2, String str5, String str6, String str7, boolean z) {
        this.dlResType = i;
        this.url = str;
        this.urlBk = str2;
        this.urlBkSecond = str3;
        this.savePath = str4;
        this.patch = i2;
        this.omd5 = str5;
        this.nmd5 = str6;
        this.dmd5 = str7;
        this.prepay = z;
    }

    public l(String str, String str2, String str3, String str4, String str5, int i, String str6, String str7, String str8, boolean z, boolean z2) {
        this.gameId = str;
        this.gameLib = str2;
        this.url = str3;
        this.savePath = str4;
        this.cfgPath = str5;
        this.size = i;
        this.version = str6;
        this.level = str7;
        this.metaVersion = str8;
        this.forceUpdate = z;
        this.autodl = false;
        this.flag = 0;
        this.prepay = z2;
    }

    public l(String str, String str2, String str3, String str4, String str5, int i, boolean z) {
        this.gameId = str;
        this.version = str2;
        this.level = str3;
        this.metaVersion = str4;
        this.gameLib = str5;
        this.flag = i;
        this.prepay = z;
    }

    public l(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, String str8, boolean z, int i, String str9, String str10, String str11, boolean z2, int i2, String str12, String str13, boolean z3, boolean z4, boolean z5) {
        this.gameId = str;
        this.gameLib = str2;
        this.url = str3;
        this.savePath = str4;
        this.cfgPath = str5;
        this.size = j;
        this.version = str6;
        this.level = str7;
        this.metaVersion = str8;
        this.forceUpdate = z;
        this.patch = i;
        this.omd5 = str9;
        this.nmd5 = str10;
        this.dmd5 = str11;
        this.autodl = z2;
        this.flag = i2;
        this.notifyTitle = str12;
        this.notifyMsg = str13;
        this.isInstall = z3;
        this.preDownload = z4;
        this.prepay = z5;
    }

    public l(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, boolean z, int i, String str11, String str12, String str13, boolean z2, int i2, String str14, String str15, boolean z3, boolean z4, boolean z5, String str16, String str17, String str18, long j2, String str19, String str20, String str21, int i3, boolean z6, String str22, String str23, String str24, long j3, String str25, String str26, String str27, String str28) {
        this.gameId = str;
        this.gameLib = str2;
        this.url = str3;
        this.urlBk = str4;
        this.urlBkSecond = str5;
        this.savePath = str6;
        this.cfgPath = str7;
        this.size = j;
        this.version = str8;
        this.level = str9;
        this.metaVersion = str10;
        this.forceUpdate = z;
        this.patch = i;
        this.omd5 = str11;
        this.nmd5 = str12;
        this.dmd5 = str13;
        this.autodl = z2;
        this.flag = i2;
        this.notifyTitle = str14;
        this.notifyMsg = str15;
        this.isInstall = z3;
        this.preDownload = z4;
        this.sign = z5;
        this.expanOmd5 = str16;
        this.expanDmd5 = str17;
        this.expanNmd5 = str18;
        this.expanSize = j2;
        this.expanUrl = str19;
        this.expanUrlBk = str20;
        this.expanSavePath = str21;
        this.expanPatch = i3;
        this.prepay = z6;
        this.twiceLoadUrl = str22;
        this.twiceLoadUrlBk = str23;
        this.tmd5 = str24;
        this.tSize = j3;
        this.rc = str25;
        this.expansionOnly = TextUtils.equals(str25, "9999");
        this.apkVercode = str26;
        this.includeDir = str27;
        this.excludeDir = str28;
    }

    public l(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j, String str8, String str9, String str10, boolean z, int i, String str11, String str12, String str13, boolean z2, int i2, String str14, String str15, boolean z3, boolean z4, boolean z5, String str16, String str17, String str18, long j2, String str19, String str20, String str21, int i3, boolean z6, String str22, String str23, String str24, long j3, String str25, String str26, String str27, String str28, String str29, String str30, String str31, String str32, String str33, long j4, long j5, String str34, int i4, String str35, String str36, int i5, boolean z7) {
        this.gameId = str;
        this.gameLib = str2;
        this.url = str3;
        this.urlBk = str4;
        this.urlBkSecond = str5;
        this.savePath = str6;
        this.cfgPath = str7;
        this.size = j;
        this.version = str8;
        this.level = str9;
        this.metaVersion = str10;
        this.forceUpdate = z;
        this.patch = i;
        this.omd5 = str11;
        this.nmd5 = str12;
        this.dmd5 = str13;
        this.autodl = z2;
        this.flag = i2;
        this.notifyTitle = str14;
        this.notifyMsg = str15;
        this.isInstall = z3;
        this.preDownload = z4;
        this.sign = z5;
        this.expanOmd5 = str16;
        this.expanDmd5 = str17;
        this.expanNmd5 = str18;
        this.expanSize = j2;
        this.expanUrl = str19;
        this.expanUrlBk = str20;
        this.expanSavePath = str21;
        this.expanPatch = i3;
        this.prepay = z6;
        this.twiceLoadUrl = str22;
        this.twiceLoadUrlBk = str23;
        this.tmd5 = str24;
        this.tSize = j3;
        this.rc = str25;
        this.expansionOnly = TextUtils.equals(str25, "9999");
        this.apkVercode = str26;
        this.includeDir = str27;
        this.excludeDir = str28;
        this.hotResUrl = str29;
        this.hotResUrlBk = str30;
        this.hotResOmd5 = str31;
        this.hotResDmd5 = str32;
        this.hotResNmd5 = str33;
        this.hotResDSize = j5;
        this.hotResNSize = j4;
        this.hotResVersionCode = str34;
        this.hotResPatch = i4;
        this.hotResSavePath = str35;
        this.hotResCfg = str36;
        this.rpred = i5;
        this.rpOnly = z7;
    }

    public String toString() {
        return "{pkg=" + this.gameLib + ", cfgPath=" + this.cfgPath + ", savePath=" + this.savePath + ", force=" + this.forceUpdate + ", inst=" + this.isInstall + ", predl=" + this.preDownload + ", flag=" + this.flag + ", hotResVersionCode=" + this.hotResVersionCode + "}";
    }
}
