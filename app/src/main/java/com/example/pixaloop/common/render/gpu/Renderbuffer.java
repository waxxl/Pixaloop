package com.example.pixaloop.common.render.gpu;

import android.opengl.GLES20;
import com.example.pixaloop.common.render.DisposableResource;

public class Renderbuffer implements DisposableResource {
    public int a;
    public  int b;
    public  int c;
    public boolean d;

    @Override
    public void close() throws Exception {
        dispose();
    }

    public enum Type {
        RGBA4(32854),
        DEPTH(33189);
        
        public int d;

        /* access modifiers changed from: public */
        Type(int i) {
            this.d = i;
        }
    }

    public void a() {
        if (!this.d) {
            GLES20.glBindRenderbuffer(36161, this.a);
            this.d = true;
        }
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.b;
    }

    public void dispose() {
        int i = this.a;
        if (i != 0) {
            GLES20.glDeleteRenderbuffers(1, new int[]{i}, 0);
            this.a = 0;
        }
    }

    public void e() {
        if (this.d) {
            GLES20.glBindRenderbuffer(36161, 0);
            this.d = false;
        }
    }
}
