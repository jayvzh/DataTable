package com.excelliance.lbsdk.main.k;

import android.util.Xml;
import com.excelliance.lbsdk.main.ao;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class k<T> extends b {
    @Override // com.excelliance.lbsdk.main.k.b
    public d<T> a(String str) {
        d<T> dVar = new d<>();
        if (str == null) {
            return dVar;
        }
        if (str != null) {
            str = str.trim();
        }
        if (str == null || str.length() == 0) {
            return dVar;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
        try {
            try {
                try {
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                    dVar.a(a(xmlPullParserNewPullParser));
                    dVar.a(0);
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e) {
                        ao.e("XmlParser", "", e, new Object[0]);
                    }
                } catch (IOException e2) {
                    ao.e("XmlParser", "", e2, new Object[0]);
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e3) {
                        ao.e("XmlParser", "", e3, new Object[0]);
                    }
                }
            } catch (XmlPullParserException e4) {
                ao.e("XmlParser", "", e4, new Object[0]);
                try {
                    byteArrayInputStream.close();
                } catch (IOException e5) {
                    ao.e("XmlParser", "", e5, new Object[0]);
                }
            }
            return dVar;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (IOException e6) {
                ao.e("XmlParser", "", e6, new Object[0]);
            }
            throw th;
        }
    }

    protected T a(XmlPullParser xmlPullParser) {
        return null;
    }
}
