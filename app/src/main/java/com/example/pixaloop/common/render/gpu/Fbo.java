package com.example.pixaloop.common.render.gpu;

import android.graphics.Rect;
import android.opengl.GLES20;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Texture;
import com.google.common.base.Preconditions;

public class Fbo implements DisposableResource {
    public static final Vector4 a = new Vector4(0.0f, 0.0f, 0.0f, 1.0f);
    public final int b;
    public final int c;
    public final Texture d;
    public final Renderbuffer e;
    public int f;
    public final int[] g;
    public final int[] h;
    public boolean i;
    public final int[] j;
    public boolean k;
    public boolean l;

    public Fbo(int i2, int i3) {
        this(new Texture(i2, i3, Texture.Type.RGBA8Unorm, true));
    }

    public void a() {
        a((Rect) null, (Rect) null);
    }

    public void b() {
        a(a);
    }

    public void c() {
        Preconditions.checkNotNull(this.i, (Object) "FBO not bounded");
        if (this.l) {
            int[] iArr = this.j;
            GLES20.glScissor(iArr[0], iArr[1], iArr[2], iArr[3]);
        }
        if (this.k) {
            GLES20.glEnable(3089);
        } else {
            GLES20.glDisable(3089);
        }
        GLES20.glBindFramebuffer(36160, this.h[0]);
        int[] iArr2 = this.g;
        GLES20.glViewport(iArr2[0], iArr2[1], iArr2[2], iArr2[3]);
        if (this.e != null) {
            GLES20.glDisable(2929);
        }
        this.i = false;
    }

    public int d() {
        return this.c;
    }

    @Override
    public void close() {
        dispose();
    }

    public void dispose() {
        if (this.i) {
            Log.w("Fbo", "Disposing bounded fbo");
            c();
        }
        int i2 = this.f;
        if (i2 != 0) {
            GLES20.glDeleteFramebuffers(1, new int[]{i2}, 0);
            this.f = 0;
        }
    }

    public Texture e() {
        return this.d;
    }

    public int f() {
        return this.b;
    }

    public final void g() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        this.f = iArr[0];
        if (this.f > 0) {
            GLES20.glGetIntegerv(36006, this.h, 0);
            GLES20.glBindFramebuffer(36160, this.f);
            this.d.a();
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.d.h(), 0);
            this.d.q();
            if (this.e != null) {
                Preconditions.checkNotNull(this.d.l() == this.e.d());
                Preconditions.checkNotNull(this.d.i() == this.e.b());
                this.e.a();
                GLES20.glFramebufferRenderbuffer(36160, 36096, 36161, this.e.c());
                this.e.e();
            }
            int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
            if (glCheckFramebufferStatus == 36053) {
                GLES20.glBindFramebuffer(36160, this.h[0]);
                return;
            }
            throw new RuntimeException(String.format("Framebuffer incomplete: %x (glError()=%x)", new Object[]{Integer.valueOf(glCheckFramebufferStatus), Integer.valueOf(GLES20.glGetError())}));
        }
        Log.e("GL_ERROR", "error:" + GLES20.glGetError());
        throw new RuntimeException("No framebuffer");
    }

    public Fbo(Texture texture) {
        this(texture, (Renderbuffer) null);
    }

    public void a(@Nullable Rect rect, @Nullable Rect rect2) {
        Preconditions.checkNotNull(!this.i, (Object) "FBO already bounded");
        GLES20.glGetIntegerv(36006, this.h, 0);
        GLES20.glGetIntegerv(2978, this.g, 0);
        this.k = GLES20.glIsEnabled(3089);
        this.l = this.k || rect2 != null;
        if (this.l) {
            GLES20.glGetIntegerv(3088, this.j, 0);
        }
        if (rect2 != null) {
            GLES20.glScissor(rect2.left, rect2.top, rect2.width(), rect2.height());
            GLES20.glEnable(3089);
        } else {
            GLES20.glDisable(3089);
        }
        GLES20.glBindFramebuffer(36160, this.f);
        if (rect == null) {
            GLES20.glViewport(0, 0, this.b, this.c);
        } else {
            GLES20.glViewport(rect.left, rect.top, rect.width(), rect.height());
        }
        if (this.e != null) {
            GLES20.glEnable(2929);
            GLES20.glDepthFunc(518);
            GLES20.glDepthRangef(0.0f, 1.0f);
        }
        this.i = true;
    }

    public Fbo(Texture texture, Renderbuffer renderbuffer) {
        this.g = new int[4];
        boolean z = true;
        this.h = new int[1];
        this.j = new int[4];
        Preconditions.checkNotNull(texture);
        this.d = texture;
        this.e = renderbuffer;
        this.b = texture.l();
        this.c = texture.i();
        if (renderbuffer != null) {
            Preconditions.checkNotNull(texture.l() == renderbuffer.d());
            Preconditions.checkNotNull(texture.i() != renderbuffer.b() ? false : z);
        }
        g();
    }

    public void a(Vector4 vector4) {
        boolean z;
        if (!this.i) {
            a();
            z = true;
        } else {
            z = false;
        }
        float[] fArr = new float[4];
        GLES20.glGetFloatv(3106, fArr, 0);
        GLES20.glClearColor(vector4.b(), vector4.c(), vector4.d(), vector4.a());
        GLES20.glClear(16384);
        GLES20.glClearColor(fArr[0], fArr[1], fArr[2], fArr[3]);
        if (z) {
            c();
        }
    }
}
