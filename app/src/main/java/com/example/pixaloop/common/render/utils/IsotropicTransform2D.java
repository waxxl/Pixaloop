package com.example.pixaloop.common.render.utils;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import com.google.common.base.Preconditions;

public class IsotropicTransform2D {
    public final Matrix a = new Matrix();

    public IsotropicTransform2D() {
    }

    public void a(IsotropicTransform2D isotropicTransform2D) {
        this.a.set(isotropicTransform2D.a);
    }

    public float b() {
        return a(0.0f, 0.0f).x;
    }

    public void c(float f) {
        b(f / a());
    }

    public void d(float f) {
        this.a.postTranslate(f - b(), 0.0f);
    }

    public void e(float f) {
        this.a.postTranslate(0.0f, f - c());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IsotropicTransform2D) && this.a.equals(((IsotropicTransform2D) obj).a);
    }

    public IsotropicTransform2D f() {
        this.a.reset();
        return this;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public float a() {
        float[] fArr = new float[9];
        this.a.getValues(fArr);
        return (float) Math.sqrt((double) Math.abs((fArr[0] * fArr[4]) + (fArr[1] * fArr[3])));
    }

    public IsotropicTransform2D b(float f, float f2, float f3) {
        this.a.postScale(f, f, f2, f3);
        return this;
    }

    public float c() {
        return a(0.0f, 0.0f).y;
    }

    public IsotropicTransform2D d() {
        Matrix matrix = new Matrix();
        if (this.a.invert(matrix)) {
            return new IsotropicTransform2D(matrix);
        }
        throw new ArithmeticException();
    }

    public float[] e() {
        float[] fArr = new float[9];
        a(fArr);
        return fArr;
    }

    public IsotropicTransform2D(IsotropicTransform2D isotropicTransform2D) {
        a(isotropicTransform2D);
    }

    public IsotropicTransform2D b(float f) {
        return b(f, f);
    }

    public IsotropicTransform2D c(float f, float f2) {
        this.a.postTranslate(f, f2);
        return this;
    }

    public IsotropicTransform2D b(float f, float f2) {
        this.a.postScale(f, f2);
        return this;
    }

    public IsotropicTransform2D a(float f) {
        this.a.postScale(-1.0f, 1.0f, f, 0.0f);
        return this;
    }

    public void b(float[] fArr) {
        Preconditions.checkArgument(fArr.length == 16);
        float[] fArr2 = new float[9];
        this.a.getValues(fArr2);
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[3];
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        fArr[4] = fArr2[1];
        fArr[5] = fArr2[4];
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = fArr2[8];
        fArr[11] = 0.0f;
        fArr[12] = fArr2[2];
        fArr[13] = fArr2[5];
        fArr[14] = 0.0f;
        fArr[15] = 1.0f;
    }

    public IsotropicTransform2D(Matrix matrix) {
        this.a.set(matrix);
    }

    public IsotropicTransform2D a(float f, float f2, float f3) {
        this.a.postRotate(f, f2, f3);
        return this;
    }

    public void a(float f, float f2, PointF pointF) {
        float[] fArr = {f, f2};
        this.a.mapPoints(fArr);
        pointF.set(fArr[0], fArr[1]);
    }

    public PointF a(float f, float f2) {
        float[] fArr = {f, f2};
        this.a.mapPoints(fArr);
        return new PointF(fArr[0], fArr[1]);
    }

    public void a(RectF rectF, RectF rectF2) {
        this.a.mapRect(rectF2, rectF);
    }

    public RectF a(RectF rectF) {
        RectF rectF2 = new RectF(rectF);
        this.a.mapRect(rectF2);
        return rectF2;
    }

    public void a(float[] fArr) {
        Preconditions.checkArgument(fArr.length == 9);
        float[] fArr2 = new float[9];
        this.a.getValues(fArr2);
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[3];
        fArr[2] = fArr2[6];
        fArr[3] = fArr2[1];
        fArr[4] = fArr2[4];
        fArr[5] = fArr2[7];
        fArr[6] = fArr2[2];
        fArr[7] = fArr2[5];
        fArr[8] = fArr2[8];
    }

    public static IsotropicTransform2D a(float f, IsotropicTransform2D isotropicTransform2D, IsotropicTransform2D isotropicTransform2D2) {
        float[] fArr = new float[9];
        float[] fArr2 = new float[9];
        float[] fArr3 = new float[9];
        isotropicTransform2D.a.getValues(fArr2);
        isotropicTransform2D2.a.getValues(fArr3);
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = MathUtils.b(f, fArr2[i], fArr3[i]);
        }
        Matrix matrix = new Matrix();
        matrix.setValues(fArr);
        return new IsotropicTransform2D(matrix);
    }
}
