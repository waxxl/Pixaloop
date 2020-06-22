package com.example.pixaloop.common.render.gpu;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GpuStruct {
    public static final Map<Integer, Integer> a = new ImmutableMap.Builder()
            .put(5121,1).put(5122, 2).put(5123, 2).put(5131, 2)
            .put(5124, 4).put(5125, 4).put(5126, 4).put(5132, 4).put(36255, 4).put(33640, 4).build();
            //5126, 4), new Pair(5132, 4), new Pair(36255, 4), new Pair(33640, 4)

        //new Pair(5120, 1), new Pair(5121, 1), new Pair(5122, 2), new Pair(5123, 2), new Pair(5131, 2), new Pair(5124, 4), new Pair(5125, 4), new Pair(});
    public static final Companion b = new Companion();
    public final int c;
    public final Map<String, Integer> d = new LinkedHashMap();
    @NotNull
    public final String e;
    @NotNull
    public final List<GpuStructField> f;

    public static final class Companion {
        public Companion() {
        }

        public final int a(int i) {
            Integer num = (Integer) GpuStruct.a.get(Integer.valueOf(i));
            if (num != null) {
                return num.intValue();
            }
            throw new IllegalStateException("Unsupported GL component type");
        }
    }

    public GpuStruct(@NotNull String str, @NotNull List<GpuStructField> list) {
        this.e = str;
        this.f = list;
        int i = 0;
        for (GpuStructField next : this.f) {
            this.d.put(next.b(), Integer.valueOf(i));
            i += next.a() * b.a(next.d());
        }
        this.c = i;
    }

    @NotNull
    public final List<GpuStructField> b() {
        return this.f;
    }

    @NotNull
    public final String c() {
        return this.e;
    }

    public final int d() {
        return this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GpuStruct)) {
            return false;
        }
        GpuStruct gpuStruct = (GpuStruct) obj;
        return Intrinsics.areEqual((Object) this.e, (Object) gpuStruct.e) && Intrinsics.areEqual((Object) this.f, (Object) gpuStruct.f);
    }

    public int hashCode() {
        String str = this.e;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<GpuStructField> list = this.f;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "GpuStruct(name=" + this.e + ", fields=" + this.f + ")";
    }

    public final int a(@NotNull String str) {
        Integer num = this.d.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Given filed name: " + str + " is not defined in " + "this struct");
    }
}
