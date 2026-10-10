package com.excelliance.lbsdk.main.e;

import android.content.Context;
import com.excelliance.lbsdk.main.b.c;
import com.excelliance.lbsdk.main.b.f;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class a extends f {
    private static HashMap<String, String> c = new HashMap<>();
    protected Context a;
    private HashMap<String, String> b;
    private boolean d;

    static {
        c.put("use_bwbx", "use_streaming");
        c.put("handle_assetmanager_bwbx", "streaming_handle_assetmanager");
        c.put("show_loading_progress_bwbx", "streaming_show_loading_progress");
        c.put("show_loading_after_download_missing_time", "streaming_show_loading_after_download_missing_time");
        c.put("show_first_dialog_without_wifi_bwbx", "streaming_show_first_dialog_without_wifi");
        c.put("show_first_dialog_always_bwbx", "streaming_show_first_dialog_always");
        c.put("check_old_user_auto", "streaming_check_old_user_auto");
        c.put("old_user_missing_condition", "streaming_old_user_missing_condition");
        c.put("show_old_user_count", "streaming_show_old_user_count");
        c.put("choose_by_user_bwbx", "streaming_twiceload");
        c.put("twload_api_control", "streaming_twload_api_control");
        c.put("choose_by_user_bwbx_force", "streaming_twiceload_force");
        c.put("download_full_after_second_dialog_bwbx", "streaming_download_full_after_second_dialog");
        c.put("show_dialog_network_weak_bwbx", "streaming_network_weak");
        c.put("show_dialog_with_wifi_when_twload_bwbx", "streaming_show_dialog_with_wifi_when_twload");
        c.put("second_dialog_interval_bwbx", "streaming_second_dialog_interval");
        c.put("show_dialog_button_by_old_user", "streaming_show_dialog_button_by_old_user");
        c.put("choose_by_user_bwbx_later", "streaming_twiceload_later");
        c.put("show_dialog_without_wifi_when_lbres_later", "streaming_show_dialog_without_wifi_when_twiceload_later");
        c.put("show_dialog_respatch_in_wifi", "streaming_show_dailog_respatch_in_wifi");
        c.put("show_progress_in_downloading_animation", "streaming_show_progress_in_downloading_animation");
        c.put("loading_local_res", "streaming_load_local_res");
        c.put("missing_size_limit", "streaming_missing_size_limit");
        c.put("bwbx_download_speed_limit", "streaming_bwbx_download_speed_limit");
        c.put("bwbx_download_use_multi_thread", "streaming_bwbx_download_use_multi_thread");
        c.put("start_check_privacy_permission", "must_check_privacy");
        c.put("control_first_dialog_bwbx", "streaming_control_first_dialog");
        c.put("show_bwbx_float_window", "streaming_bwbx_float_window");
    }

    private InputStream g() {
        com.excelliance.lbsdk.main.d.a aVar = (com.excelliance.lbsdk.main.d.a) getService("DebugModule");
        if (!aVar.a()) {
            return null;
        }
        File file = new File(aVar.c(), "lebian/globalSettings.xml");
        if (file.isFile()) {
            return new FileInputStream(file);
        }
        return null;
    }

    public int a(String str, int i) {
        if (str != null) {
            str = str.toLowerCase();
        }
        String strA = a(str);
        if (strA == null || "".equals(strA.trim())) {
            return i;
        }
        try {
            return Integer.parseInt(strA);
        } catch (NumberFormatException e) {
            com.excelliance.lbsdk.main.g.b.a("GlobalSettings", e);
            return i;
        }
    }

    public String a(String str) {
        String str2;
        b();
        String str3 = this.b.get(str);
        return ((str3 == null || "".equals(str3)) && (str2 = c.get(str)) != null) ? this.b.get(str2) : str3;
    }

    protected void a(Map<String, String> map) {
    }

    public boolean a() {
        return b("streaming_bwbx_single_request");
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
        this.a = cVar.a();
    }

    protected synchronized void b() {
        if (this.b != null) {
            return;
        }
        this.d = ((com.excelliance.lbsdk.main.d.a) getService("DebugModule")).a();
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        try {
            InputStream inputStreamG = g();
            if (inputStreamG == null) {
                inputStreamG = this.a.getAssets().open("lebian/globalSettings.xml");
            }
            XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParserNewPullParser.setInput(inputStreamG, "UTF-8");
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equals("setting")) {
                    this.b.put(xmlPullParserNewPullParser.getAttributeValue(null, "key"), xmlPullParserNewPullParser.getAttributeValue(null, "value"));
                }
            }
        } catch (IOException e) {
            com.excelliance.lbsdk.main.g.b.e("GlobalSettings", "parse settings error", e, new Object[0]);
        } catch (Exception e2) {
            com.excelliance.lbsdk.main.g.b.e("GlobalSettings", "parse settings error", e2, new Object[0]);
        }
        a(this.b);
    }

    public boolean b(String str) {
        return a(str, false);
    }

    public String c() {
        String strA = a("streaming_resource_cdn");
        if (strA != null) {
            strA = strA.trim();
        }
        if ("".equals(strA)) {
            return null;
        }
        return strA;
    }

    public boolean d() {
        return a("must_check_privacy", false);
    }

    public int e() {
        return a("selected_area", 0);
    }

    public boolean f() {
        return a("streaming_twiceload", false);
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void initialize() {
        super.initialize();
        com.excelliance.lbsdk.main.d.a aVar = (com.excelliance.lbsdk.main.d.a) getService("DebugModule");
        if (!aVar.a() || this.b == null || this.d || !new File(aVar.c(), "lebian/globalSettings.xml").isFile()) {
            return;
        }
        this.b = null;
        b();
    }
}
