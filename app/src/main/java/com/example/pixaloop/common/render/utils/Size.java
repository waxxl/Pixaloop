package com.example.pixaloop.common.render.utils;

import java.util.Objects;

public class Size {
    public int a;
    public int b;

    public Size() {
        this.a = 0;
        this.b = 0;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Size.class != obj.getClass()) {
            return false;
        }
        Size size = (Size) obj;
        if (this.a == size.a && this.b == size.b) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
    }

    public String toString() {
        return "[" + this.a + "x" + this.b + "]";
    }

    public Size(int i, int i2) {
        this.a = i;
        this.b = i2;
    }
}
