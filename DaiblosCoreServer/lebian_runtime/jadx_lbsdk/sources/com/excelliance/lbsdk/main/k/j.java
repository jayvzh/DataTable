package com.excelliance.lbsdk.main.k;

import android.os.Bundle;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class j extends k {
    @Override // com.excelliance.lbsdk.main.k.k
    protected Object a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        Bundle bundle = new Bundle();
        while (eventType != 1) {
            if (eventType != 0 && eventType == 2 && xmlPullParser.getName().equalsIgnoreCase("resinfo")) {
                String attributeValue = xmlPullParser.getAttributeValue(null, "rp");
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "rm");
                String attributeValue3 = xmlPullParser.getAttributeValue(null, "rs");
                if (attributeValue != null && attributeValue2 != null && attributeValue3 != null) {
                    bundle.putString("rUrl", attributeValue);
                    bundle.putString("rp", attributeValue);
                    bundle.putString("rDmd5", attributeValue2);
                    bundle.putString("rm", attributeValue);
                    bundle.putString("rSize", attributeValue3);
                    bundle.putString("rs", attributeValue3);
                }
                String attributeValue4 = xmlPullParser.getAttributeValue(null, "od");
                if (attributeValue4 != null) {
                    bundle.putString("od", attributeValue4);
                }
                String attributeValue5 = xmlPullParser.getAttributeValue(null, "nd");
                if (attributeValue5 != null) {
                    bundle.putString("nd", attributeValue5);
                }
            }
            eventType = xmlPullParser.next();
        }
        if (bundle.isEmpty()) {
            return null;
        }
        return new com.excelliance.lbsdk.main.z.c(bundle);
    }
}
