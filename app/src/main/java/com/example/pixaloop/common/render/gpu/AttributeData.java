package com.example.pixaloop.common.render.gpu;

import com.google.common.base.Preconditions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class AttributeData {
    @NotNull
    public final Buffer a;
    @NotNull
    public final GpuStruct b;

    public AttributeData(@NotNull Buffer buffer, @NotNull GpuStruct gpuStruct) {
        Intrinsics.checkNotNull(buffer, "data");
        Intrinsics.checkNotNull(gpuStruct, "gpuStruct");
        this.a = buffer;
        this.b = gpuStruct;
        Buffer buffer2 = this.a;
        Preconditions.checkNotNull((buffer2 instanceof ByteBuffer) || (buffer2 instanceof FloatBuffer) || (buffer2 instanceof IntBuffer) || (buffer2 instanceof ShortBuffer), "Unsupported data type", new Object[0]);
    }

    @NotNull
    public final Buffer a() {
        return this.a;
    }

    @NotNull
    public final GpuStruct b() {
        return this.b;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AttributeData)) {
            return false;
        }
        AttributeData attributeData = (AttributeData) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) attributeData.a);
    }

    public int hashCode() {
        Buffer buffer = this.a;
        int i = 0;
        int hashCode = (buffer != null ? buffer.hashCode() : 0) * 31;
        GpuStruct gpuStruct = this.b;
        if (gpuStruct != null) {
            i = gpuStruct.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "AttributeData(data=" + this.a + ", gpuStruct=" + this.b + ")";
    }
}
