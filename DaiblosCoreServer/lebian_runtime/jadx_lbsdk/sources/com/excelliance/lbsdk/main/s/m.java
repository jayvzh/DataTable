package com.excelliance.lbsdk.main.s;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class m implements ai {
    private final char[] a;
    private final List<a> b;

    private static final class a implements Comparable<a> {
        public final char a;
        public final byte b;

        a(byte b, char c) {
            this.b = b;
            this.a = c;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            return this.a - aVar.a;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b;
        }

        public int hashCode() {
            return this.a;
        }

        public String toString() {
            return "0x" + Integer.toHexString(this.a & 65535) + "->0x" + Integer.toHexString(this.b & 255);
        }
    }

    public m(char[] cArr) {
        this.a = (char[]) cArr.clone();
        ArrayList arrayList = new ArrayList(this.a.length);
        byte b = 127;
        for (char c : this.a) {
            b = (byte) (b + 1);
            arrayList.add(new a(b, c));
        }
        Collections.sort(arrayList);
        this.b = Collections.unmodifiableList(arrayList);
    }

    private a b(char c) {
        int size = this.b.size();
        int i = 0;
        while (size > i) {
            int i2 = ((size - i) / 2) + i;
            a aVar = this.b.get(i2);
            if (aVar.a == c) {
                return aVar;
            }
            if (aVar.a < c) {
                i = i2 + 1;
            } else {
                size = i2;
            }
        }
        if (i >= this.b.size()) {
            return null;
        }
        a aVar2 = this.b.get(i);
        if (aVar2.a != c) {
            return null;
        }
        return aVar2;
    }

    public char a(byte b) {
        return b >= 0 ? (char) b : this.a[b + 128];
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public String a(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i] = a(bArr[i]);
        }
        return new String(cArr);
    }

    public boolean a(char c) {
        return (c >= 0 && c < 128) || b(c) != null;
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean a(ByteBuffer byteBuffer, char c) {
        byte b;
        if (c < 0 || c >= 128) {
            a aVarB = b(c);
            if (aVarB == null) {
                return false;
            }
            b = aVarB.b;
        } else {
            b = (byte) c;
        }
        byteBuffer.put(b);
        return true;
    }

    @Override // com.excelliance.lbsdk.main.s.ai
    public ByteBuffer b(String str) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(str.length() + 6 + ((str.length() + 1) / 2));
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (byteBufferAllocate.remaining() < 6) {
                byteBufferAllocate = aj.a(byteBufferAllocate, byteBufferAllocate.position() + 6);
            }
            if (!a(byteBufferAllocate, cCharAt)) {
                aj.a(byteBufferAllocate, cCharAt);
            }
        }
        byteBufferAllocate.limit(byteBufferAllocate.position());
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }
}
