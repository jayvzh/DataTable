package com.excelliance.lbsdk.main.s;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class aj {
    static final ai a;
    private static final Map<String, a> b;
    private static final byte[] c;

    private static class a {
        private final char[] a;
        private m b;

        a(char[] cArr) {
            this.a = cArr;
        }

        public synchronized m a() {
            if (this.b == null) {
                this.b = new m(this.a);
            }
            return this.b;
        }
    }

    static {
        HashMap map = new HashMap();
        a aVar = new a(new char[]{199, 252, 233, 226, 228, 224, 229, 231, 234, 235, 232, 239, 238, 236, 196, 197, 201, 230, 198, 244, 246, 242, 251, 249, 255, 214, 220, 162, 163, 165, 8359, 402, 225, 237, 243, 250, 241, 209, 170, 186, 191, 8976, 172, 189, 188, 161, 171, 187, 9617, 9618, 9619, 9474, 9508, 9569, 9570, 9558, 9557, 9571, 9553, 9559, 9565, 9564, 9563, 9488, 9492, 9524, 9516, 9500, 9472, 9532, 9566, 9567, 9562, 9556, 9577, 9574, 9568, 9552, 9580, 9575, 9576, 9572, 9573, 9561, 9560, 9554, 9555, 9579, 9578, 9496, 9484, 9608, 9604, 9612, 9616, 9600, 945, 223, 915, 960, 931, 963, 181, 964, 934, 920, 937, 948, 8734, 966, 949, 8745, 8801, 177, 8805, 8804, 8992, 8993, 247, 8776, 176, 8729, 183, 8730, 8319, 178, 9632, 160});
        map.put("CP437", aVar);
        map.put("Cp437", aVar);
        map.put("cp437", aVar);
        map.put("IBM437", aVar);
        map.put("ibm437", aVar);
        a aVar2 = new a(new char[]{199, 252, 233, 226, 228, 224, 229, 231, 234, 235, 232, 239, 238, 236, 196, 197, 201, 230, 198, 244, 246, 242, 251, 249, 255, 214, 220, 248, 163, 216, 215, 402, 225, 237, 243, 250, 241, 209, 170, 186, 191, 174, 172, 189, 188, 161, 171, 187, 9617, 9618, 9619, 9474, 9508, 193, 194, 192, 169, 9571, 9553, 9559, 9565, 162, 165, 9488, 9492, 9524, 9516, 9500, 9472, 9532, 227, 195, 9562, 9556, 9577, 9574, 9568, 9552, 9580, 164, 240, 208, 202, 203, 200, 305, 205, 206, 207, 9496, 9484, 9608, 9604, 166, 204, 9600, 211, 223, 212, 210, 245, 213, 181, 254, 222, 218, 219, 217, 253, 221, 175, 180, 173, 177, 8215, 190, 182, 167, 247, 184, 176, 168, 183, 185, 179, 178, 9632, 160});
        map.put("CP850", aVar2);
        map.put("Cp850", aVar2);
        map.put("cp850", aVar2);
        map.put("IBM850", aVar2);
        map.put("ibm850", aVar2);
        b = Collections.unmodifiableMap(map);
        c = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
        a = new h("UTF8");
    }

    public static ai a(String str) {
        if (b(str)) {
            return a;
        }
        if (str == null) {
            return new h();
        }
        a aVar = b.get(str);
        if (aVar != null) {
            return aVar.a();
        }
        try {
            return new k(Charset.forName(str));
        } catch (UnsupportedCharsetException unused) {
            return new h(str);
        }
    }

    static ByteBuffer a(ByteBuffer byteBuffer, int i) {
        byteBuffer.limit(byteBuffer.position());
        byteBuffer.rewind();
        int iCapacity = byteBuffer.capacity() * 2;
        if (iCapacity >= i) {
            i = iCapacity;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        byteBufferAllocate.put(byteBuffer);
        return byteBufferAllocate;
    }

    static void a(ByteBuffer byteBuffer, char c2) {
        byteBuffer.put((byte) 37);
        byteBuffer.put((byte) 85);
        byteBuffer.put(c[(c2 >> '\f') & 15]);
        byteBuffer.put(c[(c2 >> '\b') & 15]);
        byteBuffer.put(c[(c2 >> 4) & 15]);
        byteBuffer.put(c[c2 & 15]);
    }

    static boolean b(String str) {
        if (str == null) {
            str = Charset.defaultCharset().name();
        }
        if (com.excelliance.lbsdk.main.w.b.f.name().equalsIgnoreCase(str)) {
            return true;
        }
        Iterator<String> it = com.excelliance.lbsdk.main.w.b.f.aliases().iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
