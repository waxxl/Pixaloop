package com.example.pixaloop.common.render.gpu;

import android.opengl.GLES20;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.utils.BufferUtils;
import java.nio.Buffer;

public class ArrayBuffer implements DisposableResource {
    public final int a;
    public int b = GLUtils.b();
    public int c;
    public Buffer d;

    public ArrayBuffer(int i, int i2) {
        b(i2);
        this.a = i;
        this.c = i2;
    }

    public static ArrayBuffer a(int i) {
        return new ArrayBuffer(34962, i);
    }

    public static void b(int i) {
        if (i != 35044 && i != 35048 && i != 35040) {
            throw new IllegalArgumentException("Unsupported buffer usage");
        }
    }

    @Override
    public void close() {
        dispose();
    }

    public void dispose() {
        int i = this.b;
        if (i != 0) {
            GLUtils.a(i);
            this.b = 0;
        }
    }

    public void a() {
        GLES20.glBindBuffer(this.a, this.b);
    }

    public void b() {
        GLES20.glBindBuffer(this.a, 0);
    }

    public void a(Buffer buffer) {
        this.d = buffer;
        a();
        GLES20.glBufferData(this.a, BufferUtils.a(buffer), buffer, this.c);
        b();
    }
}
