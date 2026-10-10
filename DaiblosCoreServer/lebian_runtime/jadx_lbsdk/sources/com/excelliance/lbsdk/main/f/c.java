package com.excelliance.lbsdk.main.f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private File a;
    private List<a> b;
    private int c;

    public interface a {
        void a(File file);
    }

    public c(File file) {
        this.a = file;
    }

    public c(File file, int i) {
        this.a = file;
        this.c = i;
    }

    private void a(File file) {
        List<a> list = this.b;
        int size = list != null ? list.size() : 0;
        for (int i = 0; i < size; i++) {
            a aVar = list.get(i);
            if (aVar != null) {
                aVar.a(file);
            }
        }
    }

    private void a(Stack<File> stack) {
        if (stack == null) {
            return;
        }
        while (!stack.isEmpty()) {
            File filePop = stack.pop();
            if (b(filePop)) {
                a(filePop);
            }
            File[] fileArrListFiles = filePop.listFiles();
            for (int length = (fileArrListFiles != null ? fileArrListFiles.length : 0) - 1; length >= 0; length--) {
                stack.push(fileArrListFiles[length]);
            }
        }
    }

    private boolean b(File file) {
        return 1 != this.c || file.isFile();
    }

    public void a() {
        File file = this.a;
        if (file == null || !file.exists()) {
            return;
        }
        Stack<File> stack = new Stack<>();
        stack.push(file);
        a(stack);
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        List arrayList = this.b;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.b = arrayList;
        }
        if (arrayList.contains(aVar)) {
            return;
        }
        arrayList.add(aVar);
    }
}
