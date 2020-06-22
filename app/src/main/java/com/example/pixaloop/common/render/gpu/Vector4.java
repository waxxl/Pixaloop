package com.example.pixaloop.common.render.gpu;

import java.util.Objects;

public class Vector4 {
    public static final Vector4 a = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Vector4 b = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);
    public final float c;
    public final float d;
    public final float e;
    public final float f;

    public Vector4(float f2, float f3, float f4, float f5) {
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
    }

    public static int e() {
        return 16;
    }

    public Vector4 a(float f2) {
        return new Vector4(this.c * f2, this.d * f2, this.e * f2, this.f * f2);
    }

    public float b() {
        return this.c;
    }

    public float c() {
        return this.d;
    }

    public float d() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Vector4.class != obj.getClass()) {
            return false;
        }
        Vector4 vector4 = (Vector4) obj;
        if (Float.compare(vector4.c, this.c) == 0 && Float.compare(vector4.d, this.d) == 0 && Float.compare(vector4.e, this.e) == 0 && Float.compare(vector4.f, this.f) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.c), Float.valueOf(this.d), Float.valueOf(this.e), Float.valueOf(this.f)});
    }

    public String toString() {
        return String.format("Vector4(%g,%g,%g,%g)", new Object[]{Float.valueOf(this.c), Float.valueOf(this.d), Float.valueOf(this.e), Float.valueOf(this.f)});
    }

    public float a() {
        return this.f;
    }

    public int a(float[] fArr, int i) {
        fArr[i] = this.c;
        fArr[i + 1] = this.d;
        fArr[i + 2] = this.e;
        fArr[i + 3] = this.f;
        return i + 4;
    }
}
