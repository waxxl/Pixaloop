package com.example.pixaloop.common.render.gpu;

import java.util.Objects;

public class Vector3 {
    public static final Vector3 a = new Vector3(1.0f, 1.0f, 1.0f);
    public static final Vector3 b = new Vector3(0.0f, 0.0f, 0.0f);
    public final float c;
    public final float d;
    public final float e;

    public Vector3(float f, float f2, float f3) {
        this.c = f;
        this.d = f2;
        this.e = f3;
    }

    public float a() {
        return this.c;
    }

    public float b() {
        return this.d;
    }

    public float c() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Vector3.class != obj.getClass()) {
            return false;
        }
        Vector3 vector3 = (Vector3) obj;
        if (Float.compare(vector3.c, this.c) == 0 && Float.compare(vector3.d, this.d) == 0 && Float.compare(vector3.e, this.e) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.c), Float.valueOf(this.d), Float.valueOf(this.e)});
    }

    public String toString() {
        return String.format("Vector3(%g,%g,%g)", new Object[]{Float.valueOf(this.c), Float.valueOf(this.d), Float.valueOf(this.e)});
    }
}
