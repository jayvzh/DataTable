package com.excelliance.lbsdk.library;

/* JADX INFO: loaded from: classes.dex */
public class UploadResult {
    private int code;
    private String msg;
    private String userId;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getUserId() {
        return this.userId;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
