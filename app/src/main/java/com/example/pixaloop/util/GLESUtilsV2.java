package com.example.pixaloop.util;

import com.example.pixaloop.common.render.gpu.Shader;

import java.nio.Buffer;

public final class GLESUtilsV2 {
    static {
        //System.loadLibrary("pixaloop");
    }

//    public static native void db(Buffer buffer);
//
//    public static native String glLog(String str);
//
//    public static native boolean vy(byte[] bArr, byte[] bArr2, byte[] bArr3);
//
//    public static native String zl();

    public static void db(Buffer buffer) {

    };

    public static String glLog(String str) {
        if(str.endsWith("fsh")) {
            return Shader.NO_FILTER_FRAGMENT_SHADER;
        } else {
            return Shader.NO_FILTER_VERTEX_SHADER;
        }
    };

    public static boolean vy(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return true;
    };

    public static String zl() {
        return "";
    };
}
