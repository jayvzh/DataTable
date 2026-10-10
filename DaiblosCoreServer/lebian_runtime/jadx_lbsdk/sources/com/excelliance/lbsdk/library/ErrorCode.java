package com.excelliance.lbsdk.library;

/* JADX INFO: loaded from: classes.dex */
public enum ErrorCode {
    NET_ERROR(1001),
    CONNECT_ERROR(1002);

    private final int mCode;

    ErrorCode(int i) {
        this.mCode = i;
    }

    public int getCode() {
        return this.mCode;
    }
}
