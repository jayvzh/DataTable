package com.excelliance.lbsdk.main.bwbx;

import android.content.Context;
import android.util.Xml;
import com.excelliance.lbsdk.main.an;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static int a = -1;
    private final Context b;
    private final String c;
    private List<C0001a> d = new ArrayList();

    /* JADX INFO: renamed from: com.excelliance.lbsdk.main.bwbx.a$a, reason: collision with other inner class name */
    public static class C0001a {
        boolean a;
        short b;
        short c;
        long d;
        int e;
        int f;

        public C0001a(long j) {
            this.d = j;
        }

        public C0001a(boolean z, short s, short s2, long j, int i, int i2) {
            this.a = z;
            this.b = s;
            this.c = s2;
            this.d = j;
            this.e = i;
            this.f = i2;
        }

        public static long a(short s, short s2) {
            return ((long) s2) | (((long) s) << 16);
        }

        public static byte[] a(long j) {
            return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)};
        }

        public long a() {
            return this.d;
        }

        public void a(FileOutputStream fileOutputStream) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.a ? 24 : 16);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.putShort(this.b);
            byteBufferAllocate.putShort(this.c);
            if (this.a) {
                byteBufferAllocate.putInt(0);
                byteBufferAllocate.putLong(this.d);
            } else {
                byteBufferAllocate.put(a(this.d));
            }
            byteBufferAllocate.putInt(this.e);
            byteBufferAllocate.putInt(this.f);
            byteBufferAllocate.flip();
            try {
                fileOutputStream.write(byteBufferAllocate.array());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int b() {
            return this.f;
        }

        public short c() {
            return this.b;
        }

        public short d() {
            return this.c;
        }

        public long e() {
            return a(c(), d());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0001a)) {
                return super.equals(obj);
            }
            C0001a c0001a = (C0001a) obj;
            return this.b == c0001a.b && this.c == c0001a.c;
        }

        public String toString() {
            return "BigAssetIdxElement{nidx=" + ((int) this.b) + ", blockNumber=" + ((int) this.c) + ", offset=" + this.d + ", compressSize=" + this.e + ", unCompressSize=" + this.f;
        }
    }

    public a(Context context, String str) {
        this.b = context;
        this.c = str;
    }

    private void c() throws IOException {
        long j;
        com.excelliance.lbsdk.main.g.b.c("BigAssetBlockReader", "read start :" + this.c, new Object[0]);
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.c, "r");
        MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
        map.order(ByteOrder.LITTLE_ENDIAN);
        while (map.hasRemaining()) {
            short s = map.getShort();
            short s2 = map.getShort();
            if (d()) {
                map.getInt();
                j = map.getLong();
            } else {
                j = map.getInt();
            }
            C0001a c0001a = new C0001a(d(), s, s2, j, map.getInt(), map.getInt());
            this.d.add(c0001a);
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("BigAssetBlockReader", "read: " + c0001a, new Object[0]);
            }
        }
        randomAccessFile.close();
        map.clear();
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0057  */
    private boolean d() {
        if (a < 0) {
            try {
                try {
                    InputStream inputStreamOpen = this.b.getAssets().open("lebianFlag.xml");
                    XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                    xmlPullParserNewPullParser.setInput(inputStreamOpen, "UTF-8");
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0 && eventType == 2 && xmlPullParserNewPullParser.getName().equalsIgnoreCase("bigasset")) {
                            a = Integer.parseInt(xmlPullParserNewPullParser.getAttributeValue(null, "flag"));
                        }
                    }
                    inputStreamOpen.close();
                    if (a < 1) {
                        a = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (a < 1) {
                        a = 0;
                    }
                }
            } catch (Throwable th) {
                if (a < 1) {
                    a = 0;
                }
                throw th;
            }
        }
        return a == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v3, types: [int] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.StringBuilder] */
    public int a(String str) throws Throwable {
        ?? E;
        int i;
        int i2 = d() ? 24 : 16;
        try {
            if (an.a().c()) {
                E = an.a().e();
                com.excelliance.lbsdk.main.g.b.c("BigAssetBlockReader", "filePath:" + E, new Object[0]);
                if (E == 0 || !new File((String) E).exists()) {
                    E = 0;
                } else {
                    try {
                        try {
                            ZipFile zipFile = new ZipFile((String) E);
                            try {
                                ZipEntry entry = zipFile.getEntry("assets/lbres/lbres.rdata.mp4.idx");
                                if (entry != null) {
                                    int size = (int) (entry.getSize() / ((long) i2));
                                    try {
                                        com.excelliance.lbsdk.main.g.b.c("BigAssetBlockReader", "getCount2: " + size + " structSize:" + i2, new Object[0]);
                                        i = size;
                                    } catch (Throwable th) {
                                        th = th;
                                        try {
                                            zipFile.close();
                                        } catch (Throwable unused) {
                                        }
                                        throw th;
                                    }
                                } else {
                                    i = 0;
                                }
                                zipFile.close();
                                E = i;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Exception e) {
                            e = e;
                            E = 0;
                            try {
                                e.printStackTrace();
                                E = E;
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        E = E;
                    }
                }
            } else {
                E = this.b.getAssets().open(str).available() / i2;
            }
        } catch (Exception e4) {
            e = e4;
            E = 0;
        }
        com.excelliance.lbsdk.main.g.b.c("BigAssetBlockReader", "getCount1: " + E + " structSize:" + i2, new Object[0]);
        return E;
    }

    public C0001a a(int i) {
        if (this.d.isEmpty()) {
            try {
                c();
            } catch (IOException e) {
                e.printStackTrace();
                return new C0001a(0L);
            }
        }
        return i >= this.d.size() ? new C0001a(-1L) : this.d.get(i);
    }

    public List<C0001a> a() {
        if (this.d.isEmpty()) {
            try {
                c();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.d;
    }

    public int b() {
        int length = (int) (new File(this.c).length() / ((long) (d() ? 24 : 16)));
        com.excelliance.lbsdk.main.g.b.c("BigAssetBlockReader", "getCount: " + length, new Object[0]);
        return length;
    }
}
