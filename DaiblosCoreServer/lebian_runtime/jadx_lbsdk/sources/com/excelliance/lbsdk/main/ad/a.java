package com.excelliance.lbsdk.main.ad;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private File a;
    private long b = -1;
    private List<b> c;

    public a(String str) {
        this.a = new File(str + ".ini");
        c();
    }

    private long a(ByteArrayOutputStream byteArrayOutputStream, InputStream inputStream) throws IOException {
        String strB = b(byteArrayOutputStream, inputStream);
        if (strB != null) {
            return Long.parseLong(strB);
        }
        return -1L;
    }

    private void a(PrintWriter printWriter, long j) {
        printWriter.write(String.valueOf(this.b));
        printWriter.write(44);
    }

    private void a(PrintWriter printWriter, b bVar) {
        printWriter.write(String.valueOf(bVar.a()) + '-' + String.valueOf(bVar.b()));
        printWriter.write(44);
    }

    private void a(PrintWriter printWriter, List<b> list) {
        for (int i = 0; i < list.size(); i++) {
            a(printWriter, list.get(i));
        }
    }

    private void a(List<b> list, int i) {
        while (i < list.size() - 1) {
            b bVar = list.get(i);
            int i2 = i + 1;
            b bVar2 = list.get(i2);
            if (bVar.b() + 1 < bVar2.a()) {
                return;
            }
            bVar.b(bVar2.b());
            list.remove(i2);
        }
    }

    private boolean a(long j, long j2, long j3, long j4) {
        return ((j2 - j) + 1) + ((j4 - j3) + 1) > (Math.max(j2, j4) - Math.min(j, j3)) + 1;
    }

    private String b(ByteArrayOutputStream byteArrayOutputStream, InputStream inputStream) throws IOException {
        while (true) {
            int i = inputStream.read();
            if (-1 == i || 44 == i) {
                break;
            }
            byteArrayOutputStream.write(i);
        }
        if (byteArrayOutputStream.size() > 0) {
            return byteArrayOutputStream.toString();
        }
        return null;
    }

    private void b(List<b> list, int i) {
        while (i > 0) {
            b bVar = list.get(i);
            b bVar2 = list.get(i - 1);
            if (bVar2.b() + 1 < bVar.a()) {
                return;
            }
            bVar2.b(bVar.b());
            list.remove(i);
            i--;
        }
    }

    private b c(ByteArrayOutputStream byteArrayOutputStream, InputStream inputStream) throws IOException {
        String strB = b(byteArrayOutputStream, inputStream);
        if (strB == null) {
            return null;
        }
        int iIndexOf = strB.indexOf(45);
        return new b(Long.parseLong(strB.substring(0, iIndexOf)), Long.parseLong(strB.substring(iIndexOf + 1)));
    }

    private void c() {
        try {
            if (!this.a.exists()) {
                this.a.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(this.a);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.b = a(byteArrayOutputStream, fileInputStream);
            byteArrayOutputStream.reset();
            this.c = d(byteArrayOutputStream, fileInputStream);
        } catch (FileNotFoundException | IOException unused) {
        }
    }

    private void c(long j, long j2) throws Throwable {
        a aVar = this;
        List<b> list = aVar.c;
        if (list.size() < 1) {
            list.add(new b(j, j2));
        }
        int i = 0;
        while (i < list.size()) {
            b bVar = list.get(i);
            long jA = bVar.a();
            long jB = bVar.b();
            long j3 = j2 + 1;
            if (j3 >= jA) {
                if (j3 == jA) {
                    bVar.a(j);
                } else {
                    if (1 + jB == j) {
                        bVar.b(j2);
                        aVar.a(list, i);
                        break;
                    }
                    List<b> list2 = list;
                    int i2 = i;
                    if (a(jA, jB, j, j2)) {
                        bVar.a(Math.min(jA, j));
                        bVar.b(Math.max(jB, j2));
                        a(list2, i2);
                        b(list2, i2);
                    } else {
                        if (i2 == list2.size() - 1) {
                            list2.add(new b(j, j2));
                        }
                        i = i2 + 1;
                        aVar = this;
                        list = list2;
                    }
                }
                d();
            }
            list.add(i, new b(j, j2));
            aVar.b(list, i);
        }
        d();
    }

    private List<b> d(ByteArrayOutputStream byteArrayOutputStream, InputStream inputStream) throws IOException {
        LinkedList linkedList = new LinkedList();
        while (true) {
            b bVarC = c(byteArrayOutputStream, inputStream);
            if (bVarC == null) {
                return linkedList;
            }
            linkedList.add(bVarC);
            byteArrayOutputStream.reset();
        }
    }

    private void d() throws Throwable {
        PrintWriter printWriter;
        Throwable th;
        try {
            printWriter = new PrintWriter(new FileOutputStream(this.a));
            try {
                a(printWriter, this.b);
                a(printWriter, this.c);
                printWriter.flush();
                printWriter.close();
            } catch (FileNotFoundException unused) {
                if (printWriter == null) {
                    return;
                }
            } catch (IOException unused2) {
                if (printWriter == null) {
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            printWriter = null;
        } catch (IOException unused4) {
            printWriter = null;
        } catch (Throwable th3) {
            printWriter = null;
            th = th3;
        }
        printWriter.close();
    }

    public long a() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(long j, long j2, List<b> list, List<b> list2) {
        List<b> list3 = this.c;
        int i = 0;
        while (j <= j2 && i < list3.size()) {
            b bVar = list3.get(i);
            long jA = bVar.a();
            long jB = bVar.b();
            if (jB >= j) {
                if (j < jA) {
                    long jMin = Math.min(j2, jA - 1);
                    list2.add(new b(j, jMin));
                    i--;
                    j = jMin + 1;
                } else {
                    list.add(new b(j, Math.min(j2, jB)));
                    j = jB + 1;
                }
            }
            i++;
        }
        if (j <= j2) {
            list2.add(new b(j, j2));
        }
    }

    public boolean a(long j, long j2) throws Throwable {
        c(j, j2);
        return true;
    }

    public List<b> b(long j, long j2) {
        List<b> list = this.c;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; j <= j2 && i < list.size(); i++) {
            b bVar = list.get(i);
            long jA = bVar.a();
            long jB = bVar.b();
            if (jA <= j) {
                j = Math.max(j, jB + 1);
            } else {
                arrayList.add(new b(j, Math.min(j2, jA - 1)));
                j = jB + 1;
            }
        }
        if (j <= j2) {
            arrayList.add(new b(j, j2));
        }
        return arrayList;
    }

    public void b() {
        File file = this.a;
        if (file != null) {
            file.delete();
        }
    }
}
