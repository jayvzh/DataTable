package com.excelliance.lbsdk.main.s;

import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class t extends ZipException {
    private static final long serialVersionUID = 20130101;
    private final a a;
    private final af b;

    public static class a {
        public static final a a = new a("encryption");
        public static final a b = new a("compression method");
        public static final a c = new a("data descriptor");
        public static final a d = new a("splitting");
        private final String e;

        private a(String str) {
            this.e = str;
        }

        public String toString() {
            return this.e;
        }
    }

    public t(an anVar, af afVar) {
        super("unsupported feature method '" + anVar.name() + "' used in entry " + afVar.getName());
        this.a = a.b;
        this.b = afVar;
    }

    public t(a aVar) {
        super("unsupported feature " + aVar + " used in archive.");
        this.a = aVar;
        this.b = null;
    }

    public t(a aVar, af afVar) {
        super("unsupported feature " + aVar + " used in entry " + afVar.getName());
        this.a = aVar;
        this.b = afVar;
    }

    public af getEntry() {
        return this.b;
    }

    public a getFeature() {
        return this.a;
    }
}
