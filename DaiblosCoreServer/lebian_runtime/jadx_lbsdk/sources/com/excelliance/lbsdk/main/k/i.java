package com.excelliance.lbsdk.main.k;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class i extends f<com.excelliance.lbsdk.main.z.b> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.excelliance.lbsdk.main.k.f
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public com.excelliance.lbsdk.main.z.b a(String str, XmlPullParser xmlPullParser) {
        if (!"splitinfo".equalsIgnoreCase(str)) {
            return null;
        }
        com.excelliance.lbsdk.main.z.b bVar = new com.excelliance.lbsdk.main.z.b();
        bVar.a(xmlPullParser.getAttributeValue(null, "domain"));
        return bVar;
    }
}
