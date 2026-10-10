package com.excelliance.lbsdk.main.ah;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.excelliance.lbsdk.main.i.a {
    private a a;

    private a c() {
        a aVar = this.a;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(getContext(), "lebian_module_base");
        this.a = aVar2;
        return aVar2;
    }

    public boolean a() {
        return c().a("hotupgrade", false);
    }

    public boolean b() {
        return c().a("overlayinstall", false);
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void overlayInstallation() {
        com.excelliance.lbsdk.main.g.b.c("Preference", "overlayInstallation", new Object[0]);
        super.overlayInstallation();
        a aVarC = c();
        aVarC.a("hotupgrade");
        aVarC.b("overlayinstall", true);
    }

    @Override // com.excelliance.lbsdk.main.b.f, com.excelliance.lbsdk.main.b.d
    public void upgrade() {
        com.excelliance.lbsdk.main.g.b.c("Preference", "upgrade", new Object[0]);
        super.upgrade();
        a aVarC = c();
        aVarC.a("overlayinstall");
        aVarC.b("hotupgrade", true);
    }
}
