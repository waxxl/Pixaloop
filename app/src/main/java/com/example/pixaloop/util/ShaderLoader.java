package com.example.pixaloop.util;

import com.google.common.base.Preconditions;

public class ShaderLoader {
    public static String a(String str) {
        Preconditions.checkNotNull(str);
        return GLESUtilsV2.glLog(str);
    }
}
