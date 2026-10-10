package com.excelliance.lbsdk.main.s;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class l implements ak {
    private final ao a;
    private byte[] b;
    private byte[] c;

    public enum a {
        DES(26113),
        RC2pre52(26114),
        TripleDES168(26115),
        TripleDES192(26121),
        AES128(26126),
        AES192(26127),
        AES256(26128),
        RC2(26370),
        RC4(26625),
        UNKNOWN(65535);

        private static final Map<Integer, a> b;
        private final int a;

        static {
            HashMap map = new HashMap();
            for (a aVar : values()) {
                map.put(Integer.valueOf(aVar.getCode()), aVar);
            }
            b = Collections.unmodifiableMap(map);
        }

        a(int i) {
            this.a = i;
        }

        public static a getAlgorithmByCode(int i) {
            return b.get(Integer.valueOf(i));
        }

        public int getCode() {
            return this.a;
        }
    }

    public enum b {
        NONE(0),
        CRC32(1),
        MD5(32771),
        SHA1(32772),
        RIPEND160(32775),
        SHA256(32780),
        SHA384(32781),
        SHA512(32782);

        private static final Map<Integer, b> b;
        private final int a;

        static {
            HashMap map = new HashMap();
            for (b bVar : values()) {
                map.put(Integer.valueOf(bVar.getCode()), bVar);
            }
            b = Collections.unmodifiableMap(map);
        }

        b(int i) {
            this.a = i;
        }

        public static b getAlgorithmByCode(int i) {
            return b.get(Integer.valueOf(i));
        }

        public int getCode() {
            return this.a;
        }
    }

    protected l(ao aoVar) {
        this.a = aoVar;
    }

    public void a(byte[] bArr) {
        this.b = ap.b(bArr);
    }

    public void b(byte[] bArr) {
        this.c = ap.b(bArr);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getCentralDirectoryData() {
        byte[] bArr = this.c;
        return bArr != null ? ap.b(bArr) : getLocalFileDataData();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getCentralDirectoryLength() {
        byte[] bArr = this.c;
        return bArr != null ? new ao(bArr.length) : getLocalFileDataLength();
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getHeaderId() {
        return this.a;
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public byte[] getLocalFileDataData() {
        return ap.b(this.b);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public ao getLocalFileDataLength() {
        byte[] bArr = this.b;
        return new ao(bArr != null ? bArr.length : 0);
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        b(bArr2);
        if (this.b == null) {
            a(bArr2);
        }
    }

    @Override // com.excelliance.lbsdk.main.s.ak
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        a(bArr2);
    }
}
