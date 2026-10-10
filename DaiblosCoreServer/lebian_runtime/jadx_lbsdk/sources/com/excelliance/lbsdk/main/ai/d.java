package com.excelliance.lbsdk.main.ai;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private static final ThreadLocal<byte[]> a = new ThreadLocal<>();

    private static void a(File file, Stack<File> stack) {
        File[] fileArrListFiles = file.listFiles();
        int length = fileArrListFiles != null ? fileArrListFiles.length : 0;
        for (int i = 0; i < length; i++) {
            stack.push(fileArrListFiles[i]);
        }
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        return new File(str).exists();
    }

    public static boolean a(String str, int i) {
        File file = new File(str);
        boolean zDelete = false;
        int i2 = 0;
        while (!zDelete) {
            int i3 = i2 + 1;
            if (i2 >= i) {
                break;
            }
            zDelete = file.delete();
            i2 = i3;
        }
        return zDelete;
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        if (a(str, 1)) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        Stack stack = new Stack();
        stack.add(file);
        while (!stack.isEmpty()) {
            File file2 = (File) stack.pop();
            if (!file2.delete() && file2.isDirectory()) {
                stack.push(file2);
                a(file2, (Stack<File>) stack);
            }
        }
        return true;
    }

    public static boolean c(String str) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        if (!file.getParentFile().mkdirs()) {
            return false;
        }
        file.createNewFile();
        return true;
    }
}
