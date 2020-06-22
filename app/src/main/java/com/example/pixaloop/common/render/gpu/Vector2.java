package com.example.pixaloop.common.render.gpu;

import android.graphics.PointF;
import java.util.Objects;

public class Vector2 {
    public static Vector2 a = new Vector2(0.0f, 0.0f);
    public static Vector2 b = new Vector2(1.0f, 1.0f);
    public static Vector2 c = new Vector2(1.0f, 0.0f);
    public static Vector2 d = new Vector2(0.0f, 1.0f);
    public final float e;
    public final float f;

    public Vector2(float f2, float f3) {
        this.e = f2;
        this.f = f3;
    }

    public static int e() {
        return 8;
    }

    public Vector2 a(Vector2 vector2) {
        return new Vector2(this.e + vector2.e, this.f + vector2.f);
    }

    public Vector2 b(Vector2 vector2) {
        return new Vector2(this.e - vector2.e, this.f - vector2.f);
    }

    public float c() {
        return (float) Math.hypot((double) this.e, (double) this.f);
    }

    public Vector2 d() {
        float c2 = c();
        return new Vector2(this.e / c2, this.f / c2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Vector2.class != obj.getClass()) {
            return false;
        }
        Vector2 vector2 = (Vector2) obj;
        if (Float.compare(vector2.e, this.e) == 0 && Float.compare(vector2.f, this.f) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.e), Float.valueOf(this.f)});
    }

    public String toString() {
        return "Vector2(" + this.e + "," + this.f + ")";
    }

    public Vector2 a(float f2) {
        return new Vector2(this.e * f2, this.f * f2);
    }

    public float b() {
        return this.f;
    }

    public float a() {
        return this.e;
    }

    public Vector2(PointF pointF) {
        this.e = pointF.x;
        this.f = pointF.y;
    }

    public int a(float[] fArr, int i) {
        fArr[i] = this.e;
        fArr[i + 1] = this.f;
        return i + 2;
    }
}
