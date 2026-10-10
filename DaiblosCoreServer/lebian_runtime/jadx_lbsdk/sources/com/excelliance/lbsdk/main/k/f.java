package com.excelliance.lbsdk.main.k;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
class f<T> extends k<T> {
    f() {
    }

    protected T a(String str, XmlPullParser xmlPullParser) {
        return null;
    }

    @Override // com.excelliance.lbsdk.main.k.k
    protected T a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        T tA = null;
        while (eventType != 1) {
            if (eventType != 0) {
                if (eventType == 2) {
                    tA = a(xmlPullParser.getName(), xmlPullParser);
                } else if (eventType == 3) {
                    a(xmlPullParser.getName(), tA);
                }
            }
            if (tA != null) {
                break;
            }
            eventType = xmlPullParser.next();
        }
        return tA;
    }

    protected void a(String str, T t) {
    }
}
