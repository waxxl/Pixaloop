package com.example.pixaloop.util;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

public final class RectUtil {
    public static boolean a(RectF rectF, float f, RectF rectF2) {
        Path path = new Path();
        path.addRect(rectF, Path.Direction.CW);
        Matrix matrix = new Matrix();
        matrix.setRotate(f, rectF.centerX(), rectF.centerY());
        path.transform(matrix);
        Path path2 = new Path();
        path2.addRect(rectF2, Path.Direction.CW);
        path2.op(path, Path.Op.INTERSECT);
        return !path2.isEmpty();
    }

    public static RectF b(RectF rectF, RectF rectF2) {
        float width = ((rectF2.width() - rectF.width()) / 2.0f) + rectF2.left;
        float height = ((rectF2.height() - rectF.height()) / 2.0f) + rectF2.top;
        RectF rectF3 = new RectF(rectF);
        rectF3.offset(width, height);
        return rectF3;
    }

    public static RectF a(RectF rectF, float f) {
        RectF rectF2 = new RectF(rectF);
        Matrix matrix = new Matrix();
        matrix.setScale(f, f, rectF.centerX(), rectF.centerY());
        matrix.mapRect(rectF2);
        return rectF2;
    }

    public static RectF a(RectF rectF, RectF rectF2) {
        float min = Math.min(rectF2.width() / rectF.width(), rectF2.height() / rectF.height());
        return b(new RectF(0.0f, 0.0f, rectF.width() * min, min * rectF.height()), rectF2);
    }

    public static RectF a(float f, RectF rectF) {
        return a(new RectF(0.0f, 0.0f, f, 1.0f), rectF);
    }

    public static RectF a(RectF rectF, float f, RectF rectF2, float f2, float f3) {
        return a(rectF, Math.min(f, a(rectF, rectF2, f, f2, f3)));
    }

    public static RectF a(RectF rectF, float f, float f2, float f3, RectF rectF2) {
        RectF rectF3 = new RectF(rectF);
        rectF3.offset(f2, 0.0f);
        if (!a(rectF3, f, rectF2)) {
            rectF3.offset(-f2, 0.0f);
        }
        rectF3.offset(0.0f, f3);
        if (!a(rectF3, f, rectF2)) {
            rectF3.offset(0.0f, -f3);
        }
        return rectF3;
    }

    public static Rect a(RectF rectF) {
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    public static float a(RectF rectF, RectF rectF2, float f, float f2, float f3) {
        return Math.min((f2 / ((rectF.width() * f) / rectF2.width())) * f, (f3 / ((rectF.height() * f) / rectF2.height())) * f);
    }
}
