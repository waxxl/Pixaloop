package com.example.pixaloop.common.render.gpu;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GpuStructField {
    public static final Companion a = new Companion();
    @NotNull
    public final String b;
    public final int c;
    public final int d;
    public final boolean e;

    public static final class Companion {
        public Companion() {
        }

    }

    public GpuStructField(@NotNull String str, int i, int i2, boolean z) {
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = z;
    }

    public final int a() {
        return this.c;
    }

    @NotNull
    public final String b() {
        return this.b;
    }

    public final boolean c() {
        return this.e;
    }

    public final int d() {
        return this.d;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof GpuStructField) {
                GpuStructField gpuStructField = (GpuStructField) obj;
                if (Intrinsics.areEqual((Object) this.b, (Object) gpuStructField.b)) {
                    if (this.c == gpuStructField.c) {
                        if (this.d == gpuStructField.d) {
                            if (this.e == gpuStructField.e) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.b;
        int hashCode = (((((str != null ? str.hashCode() : 0) * 31) + this.c) * 31) + this.d) * 31;
        boolean z = this.e;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    @NotNull
    public String toString() {
        return "GpuStructField(name=" + this.b + ", componentCount=" + this.c + ", type=" + this.d + ", normalized=" + this.e + ")";
    }
}
