package com.example.pixaloop.common.render.utils;

import android.graphics.RectF;

public final class MathUtils {
    public static float a(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    public static float b(float f, float f2, float f3) {
        return f2 + (a(f, 0.0f, 1.0f) * (f3 - f2));
    }

    public static double a(double d, double d2, double d3) {
        return Math.min(Math.max(d, d2), d3);
    }

    public static double b(double d, double d2, double d3) {
        return d2 + (a(d, 0.0d, 1.0d) * (d3 - d2));
    }

    public static float a(float f) {
        return Math.max(f, 0.0f);
    }

    public static void a(RectF rectF) {
        float f = rectF.left;
        float f2 = rectF.right;
        if (f > f2) {
            rectF.left = f2;
            rectF.right = f;
        }
        float f3 = rectF.top;
        float f4 = rectF.bottom;
        if (f3 > f4) {
            rectF.top = f4;
            rectF.bottom = f3;
        }
    }
}
