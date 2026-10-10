package com.excelliance.lbsdk.main;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ab implements Serializable {
    public String id;
    public List<String> overlayList;
    public boolean overlayMetaData;
    public String overlayMetaDatas;
    public boolean overlayMetaInf;
    public boolean overlaySign;
    public boolean overlaySignV2Channel;

    public ab(String str, List<String> list, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.id = str;
        this.overlayList = list;
        this.overlayMetaData = z;
        this.overlayMetaDatas = str2;
        this.overlayMetaInf = z2;
        this.overlaySign = z3;
        this.overlaySignV2Channel = z4;
    }
}
