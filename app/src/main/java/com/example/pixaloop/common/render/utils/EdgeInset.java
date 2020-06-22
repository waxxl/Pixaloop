package com.example.pixaloop.common.render.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EdgeInset {
    public  float a;
    public  float b;
    public  float c;
    public  float d;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EdgeInset)) {
            return false;
        }
        EdgeInset edgeInset = (EdgeInset) obj;
        return Float.compare(this.a, edgeInset.a) == 0 && Float.compare(this.b, edgeInset.b) == 0 && Float.compare(this.c, edgeInset.c) == 0 && Float.compare(this.d, edgeInset.d) == 0;
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b)) * 31) + Float.floatToIntBits(this.c)) * 31) + Float.floatToIntBits(this.d);
    }

    @NotNull
    public String toString() {
        return "EdgeInset(top=" + this.a + ", left=" + this.b + ", bottom=" + this.c + ", right=" + this.d + ")";
    }
}
