package com.excelliance.lbsdk.main.s;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private static final Map<ao, Class<?>> a = new ConcurrentHashMap();

    public static final class a {
        public static final a a = new a(0);
        public static final a b = new a(1);
        public static final a c = new a(2);
        private final int d;

        private a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    static {
        a((Class<?>) b.class);
        a((Class<?>) aa.class);
        a((Class<?>) ab.class);
        a((Class<?>) j.class);
        a((Class<?>) p.class);
        a((Class<?>) o.class);
        a((Class<?>) ac.class);
        a((Class<?>) u.class);
        a((Class<?>) v.class);
        a((Class<?>) w.class);
        a((Class<?>) x.class);
        a((Class<?>) y.class);
        a((Class<?>) z.class);
    }

    public static ak a(ao aoVar) {
        Class<?> cls = a.get(aoVar);
        if (cls != null) {
            return (ak) cls.newInstance();
        }
        r rVar = new r();
        rVar.a(aoVar);
        return rVar;
    }

    public static void a(Class<?> cls) {
        try {
            a.put(((ak) cls.newInstance()).getHeaderId(), cls);
        } catch (ClassCastException unused) {
            throw new RuntimeException(cls + " doesn't implement ZipExtraField");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException(cls + "'s no-arg constructor is not public");
        } catch (InstantiationException unused3) {
            throw new RuntimeException(cls + " is not a concrete class");
        }
    }

    public static byte[] a(ak[] akVarArr) {
        byte[] localFileDataData;
        boolean z = akVarArr.length > 0 && (akVarArr[akVarArr.length - 1] instanceof q);
        int length = z ? akVarArr.length - 1 : akVarArr.length;
        int value = length * 4;
        for (ak akVar : akVarArr) {
            value += akVar.getLocalFileDataLength().getValue();
        }
        byte[] bArr = new byte[value];
        int length2 = 0;
        for (int i = 0; i < length; i++) {
            System.arraycopy(akVarArr[i].getHeaderId().getBytes(), 0, bArr, length2, 2);
            System.arraycopy(akVarArr[i].getLocalFileDataLength().getBytes(), 0, bArr, length2 + 2, 2);
            length2 += 4;
            byte[] localFileDataData2 = akVarArr[i].getLocalFileDataData();
            if (localFileDataData2 != null) {
                System.arraycopy(localFileDataData2, 0, bArr, length2, localFileDataData2.length);
                length2 += localFileDataData2.length;
            }
        }
        if (z && (localFileDataData = akVarArr[akVarArr.length - 1].getLocalFileDataData()) != null) {
            System.arraycopy(localFileDataData, 0, bArr, length2, localFileDataData.length);
        }
        return bArr;
    }

    public static ak[] a(byte[] bArr, boolean z, a aVar) throws ZipException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i <= bArr.length - 4) {
            ao aoVar = new ao(bArr, i);
            int value = new ao(bArr, i + 2).getValue();
            int i2 = i + 4;
            if (i2 + value > bArr.length) {
                int iA = aVar.a();
                if (iA == 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("bad extra field starting at ");
                    sb.append(i);
                    sb.append(".  Block length of ");
                    sb.append(value);
                    sb.append(" bytes exceeds remaining data of ");
                    sb.append((bArr.length - i) - 4);
                    sb.append(" bytes.");
                    throw new ZipException(sb.toString());
                }
                if (iA == 1) {
                    break;
                }
                if (iA != 2) {
                    throw new ZipException("unknown UnparseableExtraField key: " + aVar.a());
                }
                q qVar = new q();
                if (z) {
                    qVar.parseFromLocalFileData(bArr, i, bArr.length - i);
                } else {
                    qVar.parseFromCentralDirectoryData(bArr, i, bArr.length - i);
                }
                arrayList.add(qVar);
                break;
            }
            try {
                ak akVarA = a(aoVar);
                if (z) {
                    akVarA.parseFromLocalFileData(bArr, i2, value);
                } else {
                    akVarA.parseFromCentralDirectoryData(bArr, i2, value);
                }
                arrayList.add(akVarA);
                i += value + 4;
            } catch (IllegalAccessException e) {
                throw ((ZipException) new ZipException(e.getMessage()).initCause(e));
            } catch (InstantiationException e2) {
                throw ((ZipException) new ZipException(e2.getMessage()).initCause(e2));
            }
        }
        return (ak[]) arrayList.toArray(new ak[arrayList.size()]);
    }

    public static byte[] b(ak[] akVarArr) {
        byte[] centralDirectoryData;
        boolean z = akVarArr.length > 0 && (akVarArr[akVarArr.length - 1] instanceof q);
        int length = z ? akVarArr.length - 1 : akVarArr.length;
        int value = length * 4;
        for (ak akVar : akVarArr) {
            value += akVar.getCentralDirectoryLength().getValue();
        }
        byte[] bArr = new byte[value];
        int length2 = 0;
        for (int i = 0; i < length; i++) {
            System.arraycopy(akVarArr[i].getHeaderId().getBytes(), 0, bArr, length2, 2);
            System.arraycopy(akVarArr[i].getCentralDirectoryLength().getBytes(), 0, bArr, length2 + 2, 2);
            length2 += 4;
            byte[] centralDirectoryData2 = akVarArr[i].getCentralDirectoryData();
            if (centralDirectoryData2 != null) {
                System.arraycopy(centralDirectoryData2, 0, bArr, length2, centralDirectoryData2.length);
                length2 += centralDirectoryData2.length;
            }
        }
        if (z && (centralDirectoryData = akVarArr[akVarArr.length - 1].getCentralDirectoryData()) != null) {
            System.arraycopy(centralDirectoryData, 0, bArr, length2, centralDirectoryData.length);
        }
        return bArr;
    }
}
