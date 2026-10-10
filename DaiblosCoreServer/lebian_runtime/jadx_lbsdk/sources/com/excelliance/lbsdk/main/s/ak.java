package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public interface ak {
    byte[] getCentralDirectoryData();

    ao getCentralDirectoryLength();

    ao getHeaderId();

    byte[] getLocalFileDataData();

    ao getLocalFileDataLength();

    void parseFromCentralDirectoryData(byte[] bArr, int i, int i2);

    void parseFromLocalFileData(byte[] bArr, int i, int i2);
}
