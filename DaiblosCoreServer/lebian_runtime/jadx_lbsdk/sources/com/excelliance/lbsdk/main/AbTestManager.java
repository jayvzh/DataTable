package com.excelliance.lbsdk.main;

import android.content.Context;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class AbTestManager {
    public static final int ABTEST_TARGET_FIRST_DIALOG = 0;
    public static final int ABTEST_TARGET_OLD_USER_DIALOG = 2;
    public static final int ABTEST_TARGET_SECOND_DIALOG = 1;
    public static final int AB_VER = 1;
    public static AbTestManager instance;
    private volatile int a = -1;
    private String b = null;
    private Context c;

    public interface a {
        public static final String[][] a = {new String[]{"0", "A1"}, new String[]{"1", "B1"}, new String[]{"2", "C1"}, new String[]{"3", "D1"}, new String[]{"4", "E1"}, new String[]{"5", "F1"}, new String[]{"6", "G1"}};
        public static final int[][] b = {new int[]{1, 3}, new int[]{4, 5}, new int[]{6, 6}};
    }

    private AbTestManager(Context context) {
        this.c = null;
        this.c = context;
        assignGroup();
    }

    public static AbTestManager getInstance(Context context) {
        if (instance == null) {
            synchronized (AbTestManager.class) {
                if (instance == null) {
                    instance = new AbTestManager(context);
                }
            }
        }
        return instance;
    }

    public void assignGroup() {
        if (this.a == -1) {
            this.a = ae.b(this.c, "lebian_module_abtest", "testgroup", -1);
            this.b = ae.b(this.c, "lebian_module_abtest", "testbranch", (String) null);
        }
        if (this.a == -1) {
            int iNextInt = new Random().nextInt(a.a.length * 1000) / 1000;
            this.a = Integer.parseInt(a.a[iNextInt][0]);
            this.b = a.a[iNextInt][1];
            ae.a(this.c, "lebian_module_abtest", "testgroup", this.a);
            ae.a(this.c, "lebian_module_abtest", "testbranch", this.b);
            setTarget(this.a);
        }
        com.excelliance.lbsdk.main.g.b.c("AbTestManager", "assign branch is: " + this.b, new Object[0]);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0061  */
    public int getBehavior(int i) {
        int iB;
        if (GameUtil.getIntance().getContext() == null) {
            GameUtil.getIntance().setContext(this.c);
        }
        int iB2 = ae.b(this.c, "lebian_module_abtest", "const_target_" + i, -2);
        if (iB2 != -2) {
            return iB2;
        }
        if (!GameUtil.getIntance().getNotifySwitch(12)) {
            iB = -2;
        } else if (i >= 0 && i < a.b.length) {
            iB = ae.b(this.c, "lebian_module_abtest", "target_" + i, -1);
        } else if (i == -1) {
            iB = this.a;
        } else {
            iB = -2;
        }
        if (iB == -2) {
            iB = -1;
        }
        ae.a(this.c, "lebian_module_abtest", "const_target_" + i, iB);
        return iB;
    }

    public int getBehaviorWithBranch(String str) {
        for (String[] strArr : a.a) {
            if (strArr[1].equals(str)) {
                return Integer.parseInt(strArr[0]);
            }
        }
        return -1;
    }

    public String getBranch() {
        return this.b;
    }

    public int getTarget(int i) {
        for (int i2 = 0; i2 < a.b.length; i2++) {
            if (i >= a.b[i2][0] && i <= a.b[i2][1]) {
                return i2;
            }
        }
        return -1;
    }

    public void resetBehaviors() {
        for (int i = 0; i < a.b.length; i++) {
            ae.a(this.c, "lebian_module_abtest", "target_" + i, -1);
        }
    }

    public void setTarget(int i) {
        for (int i2 = 0; i2 < a.b.length; i2++) {
            if (i >= a.b[i2][0] && i <= a.b[i2][1]) {
                ae.a(this.c, "lebian_module_abtest", "target_" + i2, i);
            }
        }
    }

    public void update(String str) {
        com.excelliance.lbsdk.main.g.b.c("AbTestManager", "update: news:" + str, new Object[0]);
        if (ae.b(this.c, "lebian_module_abtest", "updated", false)) {
            return;
        }
        ae.a(this.c, "lebian_module_abtest", "updated", true);
        if (str == null || str.length() == 0) {
            return;
        }
        String[] strArrSplit = str.split(";");
        if (strArrSplit.length <= 1) {
            int behaviorWithBranch = getBehaviorWithBranch(strArrSplit[0]);
            if (behaviorWithBranch >= 0) {
                resetBehaviors();
                setTarget(behaviorWithBranch);
                return;
            }
            return;
        }
        resetBehaviors();
        for (String str2 : strArrSplit) {
            setTarget(getBehaviorWithBranch(str2));
        }
    }
}
