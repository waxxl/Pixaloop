package com.example.pixaloop.common.render.gpu;

import android.opengl.GLES20;
import android.util.Log;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.Map;

public class GLUtils {
    public static final Map<Integer, String> map = new ImmutableMap.Builder<Integer, String>()
            .put(1280, "GL_INVALID_ENUM")
            .put(1281, "GL_INVALID_VALUE")
            .put(1282, "GL_INVALID_OPERATION")
            .put(1285, "GL_OUT_OF_MEMORY")
            .build();

    static {
//        System.loadLibrary("render");
//        nativeInit();
    }

    public static boolean a() {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str = map.get(Integer.valueOf(glGetError));
            if (str == null) {
                str = "LOG_PRIORITY_NAME_UNKNOWN";
            }
            Log.e("GLUtils", String.format(Locale.ENGLISH, "glGetError() = 0x%X (%s) at %s.%s:%d", new Object[]{Integer.valueOf(glGetError), str, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())}));
        }
        if (glGetError == 0) {
            return true;
        }
        return false;
    }

    public static int b() {
        int[] iArr = new int[1];
        GLES20.glGenBuffers(1, iArr, 0);
        return iArr[0];
    }

    public static native boolean eglDestroyImageKHR(long j);

    public static native void nativeInit();

    public static void a(int i) {
        GLES20.glDeleteBuffers(1, new int[]{i}, 0);
    }
}
