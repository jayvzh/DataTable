package com.excelliance.lbsdk.main.s;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum an {
    STORED(0),
    UNSHRINKING(1),
    EXPANDING_LEVEL_1(2),
    EXPANDING_LEVEL_2(3),
    EXPANDING_LEVEL_3(4),
    EXPANDING_LEVEL_4(5),
    IMPLODING(6),
    TOKENIZATION(7),
    DEFLATED(8),
    ENHANCED_DEFLATED(9),
    PKWARE_IMPLODING(10),
    BZIP2(12),
    LZMA(14),
    JPEG(96),
    WAVPACK(97),
    PPMD(98),
    AES_ENCRYPTED(99),
    UNKNOWN;

    private static final Map<Integer, an> b;
    private final int a;

    static {
        HashMap map = new HashMap();
        for (an anVar : values()) {
            map.put(Integer.valueOf(anVar.getCode()), anVar);
        }
        b = Collections.unmodifiableMap(map);
    }

    an() {
        this(-1);
    }

    an(int i) {
        this.a = i;
    }

    public static an getMethodByCode(int i) {
        return b.get(Integer.valueOf(i));
    }

    public int getCode() {
        return this.a;
    }
}
