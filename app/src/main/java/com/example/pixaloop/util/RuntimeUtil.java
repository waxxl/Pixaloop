package com.example.pixaloop.util;

public final class RuntimeUtil {
    public static boolean a() {
        try {
            Class.forName("androidx.test.espresso.Espresso");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
