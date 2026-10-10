package com.excelliance.lbsdk.main.bwbx;

import android.content.Context;
import com.excelliance.lbsdk.main.LBSdkHelper;

/* JADX INFO: loaded from: classes.dex */
public class BwbxBridge {
    public static final String TAG = "BwbxBridge";
    private Context context;
    private com.excelliance.lbsdk.main.ag.a lebianRuntime;
    private com.excelliance.lbsdk.main.ag.b nativeContext;
    private com.excelliance.lbsdk.main.b.f serviceModule;

    public BwbxBridge(AssetRedirector assetRedirector) {
        this.serviceModule = assetRedirector;
        this.context = assetRedirector.getContext();
    }

    private com.excelliance.lbsdk.main.ag.a getNotNullLebianRuntime() {
        com.excelliance.lbsdk.main.ag.a aVar = this.lebianRuntime;
        if (aVar != null) {
            return aVar;
        }
        com.excelliance.lbsdk.main.ag.a aVar2 = new com.excelliance.lbsdk.main.ag.a(this.context);
        this.lebianRuntime = aVar2;
        return aVar2;
    }

    private com.excelliance.lbsdk.main.e.b getNotNullLocalSetting() {
        return (com.excelliance.lbsdk.main.e.b) LBSdkHelper.application().a("LocalSetting");
    }

    private com.excelliance.lbsdk.main.ag.b getNotNullNativeContext() {
        com.excelliance.lbsdk.main.ag.b bVar = this.nativeContext;
        if (bVar != null) {
            return bVar;
        }
        com.excelliance.lbsdk.main.ag.b bVar2 = new com.excelliance.lbsdk.main.ag.b(this.context);
        this.nativeContext = bVar2;
        return bVar2;
    }

    private String jniGetCachePath() {
        return getNotNullLebianRuntime().b();
    }

    private String jniGetDryRunCacheDir() {
        return getNotNullLebianRuntime().d();
    }

    private String jniGetExternalDataPath() {
        return getNotNullLebianRuntime().a();
    }

    private String jniGetExternalFilesDir() {
        return getNotNullNativeContext().c();
    }

    private String jniGetExternalFilesPakDir() {
        return getNotNullLocalSetting().e();
    }

    private String jniGetFilesDir() {
        return getNotNullNativeContext().d();
    }

    private String jniGetPackageCodePath() {
        return getNotNullNativeContext().b();
    }

    private String jniGetPackageName() {
        return getNotNullNativeContext().a();
    }

    private String jniGetPakAssetDir() {
        return getNotNullLocalSetting().f();
    }

    private String jniGetSmallRunCacheDir() {
        return getNotNullLebianRuntime().e();
    }

    private boolean jniIsMultiObbOrPak() {
        return getNotNullLocalSetting().d();
    }

    private boolean jniIsRecordMode() {
        return ((com.excelliance.lbsdk.main.l.e) this.serviceModule.getService("Runtime")).e();
    }

    private boolean jniIsSmallPkg() {
        return getNotNullLebianRuntime().c();
    }

    private void jniRecordAccess(String str, boolean z) {
        com.excelliance.lbsdk.main.af.b bVar = (com.excelliance.lbsdk.main.af.b) this.serviceModule.getService("DryRun");
        if (bVar != null) {
            bVar.a(str, z);
        }
    }
}
