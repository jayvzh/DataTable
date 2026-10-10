package com.excelliance.lbsdk.main;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class ap {
    private HashMap<String, a> c = new HashMap<>();
    public HashMap<File, ZipFile> a = new HashMap<>();
    ByteBuffer b = ByteBuffer.allocate(4);

    public static final class a {
        public final File a;
        public final String b;
        public final String c;
        public long d;
        public int e;
        public long f;
        public long g;
        public long h;
        public long i;
        public long j;
        public boolean k;

        public a(String str, File file, String str2) {
            this.j = -1L;
            this.k = false;
            this.b = str2;
            this.c = str;
            this.a = file;
            this.k = false;
        }

        public a(String str, File file, String str2, boolean z) {
            this.j = -1L;
            this.k = false;
            this.b = str2;
            this.c = str;
            this.a = file;
            this.k = z;
            this.j = 0L;
            this.h = this.a.length();
            this.i = this.h;
        }

        public long a() {
            return this.j;
        }

        public void a(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) {
            long j = this.d;
            try {
                randomAccessFile.seek(j);
                randomAccessFile.readFully(byteBuffer.array());
                if (byteBuffer.getInt(0) != 67324752) {
                    com.excelliance.lbsdk.main.g.b.d("zipro", "didn't find signature at start of lfh", new Object[0]);
                    throw new IOException();
                }
                this.j = j + 30 + ((long) (byteBuffer.getShort(26) & 65535)) + ((long) (byteBuffer.getShort(28) & 65535));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        public boolean b() {
            return this.e == 0;
        }

        public AssetFileDescriptor c() {
            if (this.e != 0) {
                return null;
            }
            try {
                return new AssetFileDescriptor(ParcelFileDescriptor.open(this.a, 268435456), a(), this.i);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        public File d() {
            return this.a;
        }
    }

    public ap(String str) throws IOException {
        c(str);
    }

    private static int a(int i) {
        return ((i & 255) << 24) + ((65280 & i) << 8) + ((16711680 & i) >>> 8) + ((i >>> 24) & 255);
    }

    private static int a(RandomAccessFile randomAccessFile) {
        return a(randomAccessFile.readInt());
    }

    public InputStream a(String str) {
        a aVar = this.c.get(str);
        if (aVar == null) {
            return null;
        }
        if (aVar.k) {
            return new FileInputStream(aVar.a);
        }
        if (aVar.b()) {
            return aVar.i > 2147483647L ? new ah(aVar.d(), aVar.i, aVar.j) : aVar.c().createInputStream();
        }
        ZipFile zipFile = this.a.get(aVar.d());
        if (zipFile == null) {
            zipFile = new ZipFile(aVar.d(), 1);
            this.a.put(aVar.d(), zipFile);
        }
        ZipEntry entry = zipFile.getEntry(str);
        if (entry != null) {
            return zipFile.getInputStream(entry);
        }
        return null;
    }

    public a[] a() {
        Collection<a> collectionValues = this.c.values();
        return (a[]) collectionValues.toArray(new a[collectionValues.size()]);
    }

    void b(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                bufferedReader.close();
                return;
            }
            String strD = com.excelliance.lbsdk.main.ae.c.d(line);
            if (strD != null && !strD.equals(BwbxUtil.NO_EXIST)) {
                File file = new File(strD);
                int iIndexOf = strD.indexOf("assets/");
                if (iIndexOf == -1) {
                    iIndexOf = strD.indexOf("lib/");
                }
                String strSubstring = strD.substring(iIndexOf);
                this.c.put(strSubstring, new a(str, file, strSubstring, true));
            } else if (strD != null) {
                int iIndexOf2 = line.indexOf("assets/");
                if (iIndexOf2 == -1) {
                    iIndexOf2 = line.indexOf("lib/");
                }
                this.c.remove(line.substring(iIndexOf2));
            }
        }
    }

    void c(String str) throws IOException {
        File file = new File(str);
        com.excelliance.lbsdk.main.s.al alVar = new com.excelliance.lbsdk.main.s.al(str);
        Enumeration<com.excelliance.lbsdk.main.s.af> enumerationA = alVar.a();
        while (enumerationA.hasMoreElements()) {
            com.excelliance.lbsdk.main.s.af afVarNextElement = enumerationA.nextElement();
            String name = afVarNextElement.getName();
            if (name.contains("../")) {
                throw new IOException("entry name contains illegal characters ../");
            }
            if (!this.c.containsKey(afVarNextElement.getName())) {
                a aVar = new a(str, file, name);
                aVar.e = afVarNextElement.getMethod();
                aVar.f = afVarNextElement.i().getTime();
                aVar.g = afVarNextElement.getCrc();
                aVar.h = afVarNextElement.getCompressedSize();
                aVar.i = afVarNextElement.getSize();
                com.excelliance.lbsdk.main.s.al.b bVar = (com.excelliance.lbsdk.main.s.al.b) afVarNextElement;
                aVar.d = bVar.k().a;
                aVar.j = bVar.k().b;
                this.c.put(name, aVar);
            }
        }
        alVar.close();
    }

    void d(String str) throws IOException {
        String str2 = str;
        File file = new File(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        if (length < 22) {
            throw new IOException();
        }
        long j = 65557 > length ? length : 65557L;
        randomAccessFile.seek(0L);
        int iA = a(randomAccessFile);
        if (iA == 101010256) {
            com.excelliance.lbsdk.main.g.b.c("zipro", "Found Zip archive, but it looks empty", new Object[0]);
            throw new IOException();
        }
        if (iA != 67324752) {
            com.excelliance.lbsdk.main.g.b.a("zipro", "Not a Zip archive", new Object[0]);
            throw new IOException();
        }
        randomAccessFile.seek(length - j);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) j);
        byte[] bArrArray = byteBufferAllocate.array();
        randomAccessFile.readFully(bArrArray);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        int length2 = bArrArray.length - 22;
        while (length2 >= 0 && (bArrArray[length2] != 80 || byteBufferAllocate.getInt(length2) != 101010256)) {
            length2--;
        }
        if (length2 < 0) {
            com.excelliance.lbsdk.main.g.b.c("zipro", "Zip: EOCD not found, " + str2 + " is not zip", new Object[0]);
        }
        int i = byteBufferAllocate.getShort(length2 + 8) & 65535;
        long j2 = ((long) byteBufferAllocate.getInt(length2 + 12)) & 4294967295L;
        long j3 = ((long) byteBufferAllocate.getInt(length2 + 16)) & 4294967295L;
        if (j3 + j2 > length) {
            com.excelliance.lbsdk.main.g.b.d("zipro", "bad offsets (dir " + j3 + ", size " + j2 + ", eocd " + length2 + ")", new Object[0]);
            throw new IOException();
        }
        if (i == 0) {
            com.excelliance.lbsdk.main.g.b.d("zipro", "empty archive?", new Object[0]);
            throw new IOException();
        }
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate((int) j2);
        randomAccessFile.seek(j3);
        randomAccessFile.readFully(byteBufferAllocate2.array());
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        byte[] bArr = new byte[65535];
        ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(30);
        byteBufferAllocate3.order(ByteOrder.LITTLE_ENDIAN);
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            if (byteBufferAllocate2.getInt(i3) != 33639248) {
                com.excelliance.lbsdk.main.g.b.d("zipro", "Missed a central dir sig (at " + i3 + ")", new Object[0]);
                throw new IOException();
            }
            int i4 = byteBufferAllocate2.getShort(i3 + 28) & 65535;
            int i5 = byteBufferAllocate2.getShort(i3 + 30) & 65535;
            int i6 = byteBufferAllocate2.getShort(i3 + 32) & 65535;
            byteBufferAllocate2.position(i3 + 46);
            byteBufferAllocate2.get(bArr, 0, i4);
            byteBufferAllocate2.position(0);
            int i7 = i;
            String str3 = new String(bArr, 0, i4);
            a aVar = new a(str2, file, str3);
            aVar.e = byteBufferAllocate2.getShort(i3 + 10) & 65535;
            File file2 = file;
            aVar.f = ((long) byteBufferAllocate2.getInt(i3 + 12)) & 4294967295L;
            aVar.g = byteBufferAllocate2.getLong(i3 + 16) & 4294967295L;
            aVar.h = byteBufferAllocate2.getLong(i3 + 20) & 4294967295L;
            aVar.i = byteBufferAllocate2.getLong(i3 + 24) & 4294967295L;
            aVar.d = ((long) byteBufferAllocate2.getInt(i3 + 42)) & 4294967295L;
            byteBufferAllocate3.clear();
            aVar.a(randomAccessFile, byteBufferAllocate3);
            if (!this.c.containsKey(str3)) {
                this.c.put(str3, aVar);
            }
            i3 += i4 + 46 + i5 + i6;
            i2++;
            str2 = str;
            i = i7;
            file = file2;
        }
    }
}
