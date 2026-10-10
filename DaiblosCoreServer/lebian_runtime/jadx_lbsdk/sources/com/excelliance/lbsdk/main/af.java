package com.excelliance.lbsdk.main;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class af {
    private static af a;
    private File b;
    private Context c;

    private af(Context context) {
        this.b = null;
        this.c = context;
        this.b = new File(context.getApplicationInfo().dataDir + "/lebian/proc/");
        if (this.b.exists()) {
            return;
        }
        this.b.mkdirs();
    }

    public static af a(Context context) {
        if (a == null) {
            a = new af(context);
        }
        return a;
    }

    public int a(int i) throws Throwable {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("/proc/" + i + "/stat");
            try {
                byte[] bArr = new byte[256];
                int i2 = 0;
                while (true) {
                    int i3 = fileInputStream.read();
                    if (i3 <= 0 || i2 >= bArr.length) {
                        break;
                    }
                    bArr[i2] = (byte) i3;
                    i2++;
                }
                if (i2 > 0) {
                    String str = new String(bArr, 0, i2, "UTF-8");
                    String[] strArrSplit = str.split(" ");
                    com.excelliance.lbsdk.main.g.b.c("ProcessManager", "getProcessStat: line:" + str, new Object[0]);
                    if (strArrSplit.length > 2) {
                        String str2 = strArrSplit[2];
                        if ("R".equals(str2) || "S".equals(str2) || "D".equals(str2)) {
                            try {
                                fileInputStream.close();
                                return 1;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return 1;
                            }
                        }
                    }
                }
                try {
                    fileInputStream.close();
                    return -1;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return -1;
                }
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    @TargetApi(19)
    public int a(String str) {
        int i;
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File(this.b, str)));
            try {
                i = dataInputStream.readInt();
                try {
                    dataInputStream.close();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable unused2) {
                }
                throw th;
            }
        } catch (Exception unused3) {
            i = 0;
        }
        com.excelliance.lbsdk.main.g.b.c("ProcessManager", "getPid: name:" + str + " pid:" + i, new Object[0]);
        return i;
    }

    public int b(String str) throws Throwable {
        int iA = a(str);
        if (iA == 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.c.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo.pid;
                }
            }
        }
        int iA2 = a(iA);
        if (iA2 == -2) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo2 : ((ActivityManager) this.c.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo2.processName.equals(str)) {
                    return runningAppProcessInfo2.pid;
                }
            }
        }
        return iA2;
    }
}
