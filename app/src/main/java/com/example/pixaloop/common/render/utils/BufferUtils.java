package com.example.pixaloop.common.render.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class BufferUtils {
    public static final Companion a = new Companion();

    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        public final int a(@NotNull Buffer buffer) {
            int capacity;
            Intrinsics.checkNotNull(buffer, "buffer");
            if (buffer instanceof ByteBuffer) {
                return buffer.capacity();
            }
            if (buffer instanceof ShortBuffer) {
                return buffer.capacity() * 2;
            }
            if (buffer instanceof FloatBuffer) {
                capacity = buffer.capacity();
            } else if (buffer instanceof IntBuffer) {
                capacity = buffer.capacity();
            } else {
                throw new IllegalArgumentException("Unsupported data type");
            }
            return capacity * 4;
        }
    }

    @JvmStatic
    public static final int a(@NotNull Buffer buffer) {
        return a.a(buffer);
    }
}
