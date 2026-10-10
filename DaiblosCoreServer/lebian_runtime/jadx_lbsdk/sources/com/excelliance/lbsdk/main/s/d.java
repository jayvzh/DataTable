package com.excelliance.lbsdk.main.s;

import java.io.InputStream;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
class d extends com.excelliance.lbsdk.main.w.a {
    d(InputStream inputStream) {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
    }

    int a() {
        return (int) b(1);
    }

    long a(int i) {
        return b(i);
    }

    int b() {
        return (int) b(8);
    }
}
