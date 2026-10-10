package com.excelliance.lbsdk.main.l;

import android.content.Context;
import com.excelliance.lbsdk.library.UploadResult;
import com.excelliance.lbsdk.main.bwbx.AssetRedirector;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.excelliance.lbsdk.main.b.b {
    private Context a;
    private com.excelliance.lbsdk.main.af.b b;

    private com.excelliance.lbsdk.main.af.b j() {
        com.excelliance.lbsdk.main.af.b bVar = this.b;
        if (bVar != null) {
            return bVar;
        }
        com.excelliance.lbsdk.main.af.b bVar2 = new com.excelliance.lbsdk.main.af.b(this.a);
        this.b = bVar2;
        return bVar2;
    }

    public UploadResult a(Context context, int i, String str) {
        com.excelliance.lbsdk.main.af.b bVarJ = j();
        return new com.excelliance.lbsdk.main.af.c(bVarJ).a(context, i, bVarJ.c(), str);
    }

    @Override // com.excelliance.lbsdk.main.b.b
    public void a(Context context) {
        super.a(context);
        this.a = context;
    }

    public void a(String str, boolean z) {
        j().a(str, z);
    }

    public int b(Context context) {
        return new com.excelliance.lbsdk.main.af.c(j()).a(context);
    }

    @Override // com.excelliance.lbsdk.main.b.b
    public void b() {
        super.b();
        e eVar = (e) a("Runtime");
        if (BwbxUtil.isRecordPkg(this.a) && eVar.a()) {
            a("DryRun", j());
        }
    }

    @Override // com.excelliance.lbsdk.main.b.b
    protected Map<String, Class<? extends com.excelliance.lbsdk.main.b.d>> c() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Map<String, Class<? extends com.excelliance.lbsdk.main.b.d>> mapC = super.c();
        if (mapC != null && mapC.size() > 0) {
            linkedHashMap.putAll(mapC);
        }
        linkedHashMap.put("DebugModule", com.excelliance.lbsdk.main.d.a.class);
        linkedHashMap.put("Log", d.class);
        linkedHashMap.put("Support", f.class);
        linkedHashMap.put("Runtime", e.class);
        linkedHashMap.put("GlobalSettings", com.excelliance.lbsdk.main.ab.a.class);
        linkedHashMap.put("LocalSetting", com.excelliance.lbsdk.main.e.b.class);
        linkedHashMap.put("PreferenceModule", com.excelliance.lbsdk.main.ah.b.class);
        linkedHashMap.put("TryFlag", com.excelliance.lbsdk.main.ab.d.class);
        linkedHashMap.put("Download", b.class);
        linkedHashMap.put(AssetRedirector.SERVICE_NAME, AssetRedirector.class);
        linkedHashMap.put("AsyncTask", a.class);
        return linkedHashMap;
    }
}
