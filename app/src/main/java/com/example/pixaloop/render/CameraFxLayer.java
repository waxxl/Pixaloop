package com.example.pixaloop.render;

import android.graphics.Matrix;
import android.renderscript.Matrix3f;
import android.renderscript.Matrix4f;

public class CameraFxLayer {
    public final float a;
    public final float b;
    public final LottieTransformInterpolator c;

    public CameraFxLayer(LottieTransformInterpolator lottieTransformInterpolator, float f, float f2) {
        this.a = f;
        this.b = f2;
        this.c = lottieTransformInterpolator;
    }

    public Matrix4f a(float f, float f2) {
        Matrix a2 = a(this.c.a(f));
        Matrix matrix = new Matrix();
        matrix.setValues(new float[]{f2, 0.0f, (float) (((double) (1.0f - f2)) * 0.5d), 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        Matrix matrix2 = new Matrix();
        matrix.invert(matrix2);
        matrix.preConcat(a2);
        matrix.preConcat(matrix2);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        Matrix3f matrix3f = new Matrix3f(fArr);
        matrix3f.transpose();
        return a(matrix3f);
    }

    public final Matrix a(Matrix matrix) {
        Matrix matrix2 = new Matrix();
        matrix.invert(matrix2);
        float[] fArr = new float[9];
        matrix2.getValues(fArr);
        Matrix matrix3 = new Matrix();
        matrix3.setValues(new float[]{fArr[0], fArr[1], fArr[2] / this.a, fArr[3], fArr[4], fArr[5] / this.b, 0.0f, 0.0f, 1.0f});
        return matrix3;
    }

    public static Matrix4f a(Matrix3f matrix3f) {
        float[] array = matrix3f.getArray();
        return new Matrix4f(new float[]{array[0], array[1], 0.0f, array[2], array[3], array[4], 0.0f, array[5], 0.0f, 0.0f, 1.0f, 0.0f, array[6], array[7], 0.0f, array[8]});
    }
}
