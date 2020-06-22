package com.example.pixaloop.render;

import android.renderscript.Matrix4f;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Adjust {
    public static final Matrix4f a = a();
    public static final Matrix4f b = b();

    public static Matrix4f a() {
        Matrix4f matrix4f = new Matrix4f(new float[]{0.299f, 0.596f, 0.212f, 0.0f, 0.587f, -0.274f, -0.523f, 0.0f, 0.114f, -0.322f, 0.311f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        matrix4f.transpose();
        return matrix4f;
    }

    public static Matrix4f b() {
        Matrix4f matrix4f = new Matrix4f(new float[]{1.0f, 1.0f, 1.0f, 0.0f, 0.9563f, -0.2721f, -1.107f, 0.0f, 0.621f, -0.6474f, 1.7046f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        matrix4f.transpose();
        return matrix4f;
    }

    public static float c(float f) {
        return f < 0.0f ? f + 1.0f : ((f / 2.0f) * 1.5f) + 1.0f;
    }

    public static float d(float f) {
        return f < 0.0f ? f * 0.12f : f * 0.12f * 0.7f;
    }

    public static Mat a(float f, float f2) {
        Mat mat = new Mat();
        Mat b2 = b(f2);
        Mat a2 = a(f);
        Core.LUT(b2, a2, mat);
        b2.release();
        a2.release();
        return mat;
    }

    public static Matrix4f b(float f, float f2) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.loadIdentity();
        matrix4f.set(1, 3, d(f));
        Matrix4f matrix4f2 = new Matrix4f();
        matrix4f2.loadIdentity();
        float c = c(f2);
        matrix4f2.set(1, 1, c);
        matrix4f2.set(2, 2, c);
        Matrix4f matrix4f3 = new Matrix4f();
        matrix4f3.loadMultiply(a, matrix4f);
        matrix4f3.multiply(matrix4f2);
        matrix4f3.multiply(b);
        matrix4f3.transpose();
        return matrix4f3;
    }

    public static Mat a(float f) {
        Mat mat;
        Mat mat2 = new Mat();
        if (f >= 0.0f) {
            mat = AdjustMatrices.d();
        } else {
            mat = AdjustMatrices.b();
        }
        Mat mat3 = mat;
        double abs = (double) Math.abs(f);
        Mat a2 = AdjustMatrices.a();
        Core.addWeighted(mat3, abs, a2, 1.0d - abs, 0.0d, mat2);
        a2.release();
        mat3.release();
        return mat2;
    }

    public static Mat b(float f) {
        Mat mat = new Mat();
        Mat e = f >= 0.0f ? AdjustMatrices.e() : AdjustMatrices.c();
        double abs = (double) Math.abs(f);
        Mat a2 = AdjustMatrices.a();
        Core.addWeighted(e, abs, a2, 1.0d - abs, 0.0d, mat);
        e.release();
        a2.release();
        return mat;
    }
}
