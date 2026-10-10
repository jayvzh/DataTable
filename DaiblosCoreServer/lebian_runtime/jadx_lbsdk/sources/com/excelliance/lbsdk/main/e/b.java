package com.excelliance.lbsdk.main.e;

import android.content.Context;
import com.excelliance.lbsdk.main.b.c;
import com.excelliance.lbsdk.main.b.f;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class b extends f {
    private HashMap<String, String> a;
    private Context b;

    private void a(Context context) {
        this.a = new HashMap<>();
        try {
            InputStream inputStreamG = g();
            if (inputStreamG == null) {
                inputStreamG = context.getAssets().open("lebian/localSettings.xml");
            }
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamG, "UTF-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equals("setting")) {
                    this.a.put(xmlPullParserNewPullParser.getAttributeValue(null, "key"), xmlPullParserNewPullParser.getAttributeValue(null, "value"));
                }
            }
            inputStreamG.close();
        } catch (Exception e) {
            com.excelliance.lbsdk.main.g.b.e("LocalSetting", "parse settings error", e, new Object[0]);
        }
    }

    private InputStream g() {
        com.excelliance.lbsdk.main.d.a aVar = (com.excelliance.lbsdk.main.d.a) getService("DebugModule");
        if (!aVar.a()) {
            return null;
        }
        File file = new File(aVar.c(), "lebian/localSettings.xml");
        if (file.isFile()) {
            return new FileInputStream(file);
        }
        return null;
    }

    public String a() {
        String strA = a("game_cache_path");
        if (strA == null) {
            strA = "Android/data/%s/files";
        }
        return strA.contains("%s") ? String.format(strA, this.b.getPackageName()) : strA;
    }

    protected String a(String str) {
        if (this.a == null) {
            a(this.b);
        }
        HashMap<String, String> map = this.a;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public boolean a(String str, boolean z) {
        if (str != null) {
            str = str.toLowerCase();
        }
        String strA = a(str);
        return (strA == null || "".equals(strA.trim())) ? z : "true".equalsIgnoreCase(strA);
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void attach(c cVar) {
        super.attach(cVar);
        this.b = cVar.a();
    }

    public String b() {
        String strA = a("game_apk_path");
        return strA == null ? "assets/" : strA;
    }

    public boolean b(String str) {
        return a(str, false);
    }

    public boolean c() {
        return a("streaming_download_from_zip", true);
    }

    public boolean d() {
        return b("streaming_multi_obb_pak");
    }

    public String e() {
        return a("streaming_external_files_pak_dir");
    }

    public String f() {
        return a("streaming_pak_asset_dir");
    }
}
