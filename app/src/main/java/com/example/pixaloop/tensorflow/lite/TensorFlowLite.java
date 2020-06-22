package com.example.pixaloop.tensorflow.lite;

import java.io.PrintStream;

public final class TensorFlowLite {
    static {
        a();
    }

    public static boolean a() {
//        try {
//            System.loadLibrary("tensorflowlite_jni");
//            return true;
//        } catch (UnsatisfiedLinkError e) {
//            try {
//                System.loadLibrary("tensorflowlite_flex_jni");
//                return true;
//            } catch (UnsatisfiedLinkError unused) {
//                PrintStream printStream = System.err;
//                printStream.println("TensorFlowLite: failed to load native library: " + e.getMessage());
//                return false;
//            }
//        }
        return false;
    }

    public static native String runtimeVersion();
}
