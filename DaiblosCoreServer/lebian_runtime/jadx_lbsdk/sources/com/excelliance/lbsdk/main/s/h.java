package com.excelliance.lbsdk.main.s;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
class h implements ai {
    private final String a;

    public h() {
        this.a = null;
    }

    public h(String str) {
        this.a = str;
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public String a(byte[] bArr) {
        String str = this.a;
        return str == null ? new String(bArr) : new String(bArr, str);
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public boolean a(String str) {
        return true;
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public ByteBuffer b(String str) {
        String str2 = this.a;
        return str2 == null ? ByteBuffer.wrap(str.getBytes()) : ByteBuffer.wrap(str.getBytes(str2));
    }
}
