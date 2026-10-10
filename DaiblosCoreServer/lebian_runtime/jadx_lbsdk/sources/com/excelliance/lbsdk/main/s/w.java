package com.excelliance.lbsdk.main.s;

/* JADX INFO: loaded from: classes.dex */
public class w extends l {
    private int a;
    private l.b b;

    public w() {
        super(new ao(21));
    }

    @Override // com.excelliance.lbsdk.main.s.l, com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        super.parseFromCentralDirectoryData(bArr, i, i2);
        this.a = ao.getValue(bArr, i);
        this.b = l.b.getAlgorithmByCode(ao.getValue(bArr, i + 2));
    }
}
