package com.excelliance.lbsdk.main.b;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class f implements d {
    private c mApplicationModule;

    @Override // com.excelliance.lbsdk.main.b.d
    public void attach(c cVar) {
        this.mApplicationModule = cVar;
    }

    public Application getAndroidApplication() {
        return this.mApplicationModule.i();
    }

    public c getApplicationModule() {
        return this.mApplicationModule;
    }

    public Context getContext() {
        return this.mApplicationModule.a();
    }

    public <T> T getService(String str) {
        return (T) getApplicationModule().a(str);
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void initialize() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void onCreate() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void overlayInstallation() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void postInitialize() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void postLoadSo() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void preInitialize() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void preLoadSo() {
    }

    @Override // com.excelliance.lbsdk.main.b.d
    public void upgrade() {
    }
}
