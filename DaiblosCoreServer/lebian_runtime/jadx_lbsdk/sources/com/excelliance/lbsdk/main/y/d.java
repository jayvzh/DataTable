package com.excelliance.lbsdk.main.y;

import com.excelliance.lbsdk.main.ao;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static String a(File file) {
        return c.a(file, 1281704553);
    }

    public static ByteBuffer a(Map<Integer, ByteBuffer> map) {
        if (map == null || map.isEmpty()) {
            throw new RuntimeException("id value map is empty");
        }
        long jRemaining = 24;
        Iterator<Map.Entry<Integer, ByteBuffer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            jRemaining += (long) (it.next().getValue().remaining() + 12);
        }
        if (map.containsKey(1114793335)) {
            jRemaining -= (long) (map.get(1114793335).remaining() + 12);
            map.remove(1114793335);
            int i = (int) ((jRemaining + 8) % 4096);
            if (i != 0) {
                int i2 = 4096 - i;
                if (i2 < 12) {
                    i2 += 4096;
                }
                jRemaining += (long) i2;
                map.put(1114793335, ByteBuffer.allocate((i2 - 8) - 4).order(ByteOrder.LITTLE_ENDIAN));
            }
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) (8 + jRemaining));
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putLong(jRemaining);
        for (Map.Entry<Integer, ByteBuffer> entry : map.entrySet()) {
            ByteBuffer value = entry.getValue();
            byteBufferAllocate.putLong(value.remaining() + 4);
            byteBufferAllocate.putInt(entry.getKey().intValue());
            byteBufferAllocate.put(value.array(), value.arrayOffset() + value.position(), value.remaining());
        }
        byteBufferAllocate.putLong(jRemaining);
        byteBufferAllocate.putLong(2334950737559900225L);
        byteBufferAllocate.putLong(3617552046287187010L);
        if (byteBufferAllocate.remaining() > 0) {
            throw new RuntimeException("generateNewApkV2SchemeBlock error");
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    public static void a(String str, String str2, String str3) throws IOException {
        a(str, str2, true, str3);
    }

    private static void a(String str, String str2, boolean z, String str3) throws IOException {
        ao.c("V2SignUtils", "copySignBlock start", new Object[0]);
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile(str2, "rw");
        b<ByteBuffer, Long> bVarB = e.b(randomAccessFile);
        try {
            try {
                Map<Integer, ByteBuffer> mapA = c.a(bVarB.a());
                b<ByteBuffer, Long> bVarB2 = e.b(randomAccessFile2);
                long jRemaining = bVarB2.a().remaining();
                Map<Integer, ByteBuffer> mapA2 = z ? c.a(bVarB2.a()) : c.b(bVarB2.a());
                boolean z2 = true;
                for (Integer num : mapA.keySet()) {
                    if (num.intValue() != 1896449818 && num.intValue() != -262969152) {
                        if (!mapA.get(num).equals(mapA2.get(num))) {
                            z2 = false;
                        }
                        mapA2.put(num, mapA.get(num));
                    }
                }
                if (z2) {
                    ao.c("V2SignUtils", "copySignBlock has no channel info", new Object[0]);
                    randomAccessFile.close();
                    randomAccessFile2.close();
                    return;
                }
                if (str3 != null) {
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(str3.getBytes("UTF-8"));
                    byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                    mapA2.put(1281704553, byteBufferWrap);
                }
                ByteBuffer byteBufferA = a(mapA2);
                if (byteBufferA.remaining() == jRemaining) {
                    randomAccessFile2.seek(bVarB2.b().longValue());
                    randomAccessFile2.write(byteBufferA.array());
                } else {
                    a aVarD = e.d(randomAccessFile2);
                    long jLongValue = aVarD.b.b().longValue();
                    long jRemaining2 = (int) (((long) byteBufferA.remaining()) - jRemaining);
                    long j = aVarD.a + jRemaining2;
                    ByteBuffer byteBufferA2 = aVarD.c.a();
                    ByteBuffer byteBufferA3 = aVarD.d.a();
                    e.a(byteBufferA3, jRemaining2 + aVarD.c.b().longValue());
                    randomAccessFile2.seek(jLongValue);
                    randomAccessFile2.write(byteBufferA.array(), byteBufferA.arrayOffset() + byteBufferA.position(), byteBufferA.remaining());
                    randomAccessFile2.write(byteBufferA2.array(), byteBufferA2.arrayOffset() + byteBufferA2.position(), byteBufferA2.remaining());
                    randomAccessFile2.write(byteBufferA3.array(), byteBufferA3.arrayOffset() + byteBufferA3.position(), byteBufferA3.remaining());
                    if (randomAccessFile2.getFilePointer() != j) {
                        throw new RuntimeException("use old channel write file size wrong");
                    }
                    randomAccessFile2.setLength(j);
                }
                randomAccessFile.close();
                randomAccessFile2.close();
            } catch (e.a unused) {
                ao.c("V2SignUtils", "copy old sign section", new Object[0]);
                a aVarD2 = e.d(randomAccessFile2);
                long jLongValue2 = aVarD2.c.b().longValue();
                long jRemaining3 = bVarB.a().remaining();
                ao.c("V2SignUtils", "copy old sign section apkChangeSize:" + jRemaining3, new Object[0]);
                long j2 = aVarD2.a + jRemaining3;
                ao.c("V2SignUtils", "copy old sign section apkLength:" + j2, new Object[0]);
                randomAccessFile2.setLength(j2);
                ByteBuffer byteBufferA4 = aVarD2.c.a();
                ByteBuffer byteBufferA5 = aVarD2.d.a();
                long jLongValue3 = aVarD2.c.b().longValue();
                ao.c("V2SignUtils", "copy old sign section centralDirOffset:" + jLongValue3, new Object[0]);
                e.a(byteBufferA5, jLongValue3 + jRemaining3);
                randomAccessFile2.seek(jLongValue2);
                ByteBuffer byteBufferA6 = bVarB.a();
                ao.c("V2SignUtils", "copy old sign section off:" + byteBufferA6.arrayOffset() + byteBufferA6.position() + " count:" + byteBufferA6.remaining(), new Object[0]);
                randomAccessFile2.write(byteBufferA6.array(), byteBufferA6.arrayOffset() + byteBufferA6.position(), byteBufferA6.remaining());
                randomAccessFile2.write(byteBufferA4.array(), byteBufferA4.arrayOffset() + byteBufferA4.position(), byteBufferA4.remaining());
                randomAccessFile2.write(byteBufferA5.array(), byteBufferA5.arrayOffset() + byteBufferA5.position(), byteBufferA5.remaining());
            }
        } catch (Throwable th) {
            randomAccessFile.close();
            randomAccessFile2.close();
            throw th;
        }
    }
}
