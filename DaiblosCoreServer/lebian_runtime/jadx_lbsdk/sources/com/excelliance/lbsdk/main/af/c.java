package com.excelliance.lbsdk.main.af;

import android.content.Context;
import com.excelliance.lbsdk.library.UploadResult;
import com.excelliance.lbsdk.main.GameUtil;
import com.excelliance.lbsdk.main.aa.g;
import com.excelliance.lbsdk.main.ai.f;
import com.excelliance.lbsdk.main.ao;
import com.excelliance.lbsdk.main.k.d;
import com.excelliance.lbsdk.main.k.e;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class c {
    private static String a;
    private b b;

    public c(b bVar) {
        this.b = bVar;
    }

    private void a(Context context, String str) {
        new com.excelliance.lbsdk.main.ab.b(context).a(str);
    }

    private String b(Context context) {
        return c(context) + "api/fileAccessList";
    }

    private String c(Context context) {
        String str = a;
        if (str != null) {
            return str;
        }
        d<com.excelliance.lbsdk.main.z.b> dVarC = e.c(context);
        String str2 = null;
        if (dVarC.b()) {
            String strA = dVarC.c().a();
            if (strA != null && !strA.endsWith("/")) {
                strA = strA + "/";
            }
            str2 = strA;
        }
        a = str2;
        return str2;
    }

    public int a(Context context) {
        new File(com.excelliance.lbsdk.main.ab.c.a(context)).delete();
        new File(com.excelliance.lbsdk.main.ab.c.b(context)).delete();
        this.b.d();
        com.excelliance.lbsdk.main.ai.a.c(context);
        return 0;
    }

    public UploadResult a(Context context, int i, long j, String str) throws Throwable {
        UploadResult uploadResult = new UploadResult();
        File file = new File(com.excelliance.lbsdk.main.ab.c.a(context));
        if (!file.isFile()) {
            uploadResult.setCode(1);
            uploadResult.setMsg("未记录游戏有访问资源, 不需要上传");
            return uploadResult;
        }
        HashMap map = new HashMap();
        map.put("chid", com.excelliance.lbsdk.main.ai.e.a(context));
        String strB = com.excelliance.lbsdk.main.ai.a.b(context);
        map.put("uid", strB);
        map.put("ftype", String.valueOf(i));
        map.put("model", com.excelliance.lbsdk.main.ai.a.a(context));
        map.put("prediction_size", String.valueOf(j));
        map.put("note", str);
        map.put("md5", f.a(context, context.getApplicationContext().getPackageResourcePath()));
        map.put("pkgname", context.getPackageName());
        map.put("vercode", GameUtil.getIntance().getRunningVersionCode());
        String strA = g.a(b(context), map, "file", "text/plain; charset=utf-8", file);
        ao.b("SelfFenbao", "uploadRecord(), result: ", strA);
        d dVarA = new com.excelliance.lbsdk.main.k.g().a(strA);
        if (dVarA.b()) {
            if (i == 2) {
                a(context, strB);
            }
            uploadResult.setCode(0);
            uploadResult.setUserId(strB);
        } else {
            uploadResult.setCode(1);
            String strA2 = dVarA.a();
            if (strA2 == null || strA2.length() < 1) {
                strA2 = "数据上传失败";
            }
            uploadResult.setMsg(strA2);
        }
        return uploadResult;
    }
}
