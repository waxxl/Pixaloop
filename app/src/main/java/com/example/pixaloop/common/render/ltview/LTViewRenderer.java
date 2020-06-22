package com.example.pixaloop.common.render.ltview;

import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Pair;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.Fbo;
import com.example.pixaloop.common.render.gpu.GLUtils;
import com.example.pixaloop.common.render.gpu.RectDrawer;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.TexturedRect;
import com.example.pixaloop.common.render.utils.BackgroundFactory;
import java.util.HashMap;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class LTViewRenderer implements RenderEngine.RenderEngineDelegate {
    public static final DrawDelegate a = new DrawDelegate() {
        @Override
        public void a(LTView lTView, RectF rectF) {

        }

        @Override
        public boolean a(LTView lTView) {
            return false;
        }

        @Override
        public Texture b(LTView lTView) {
            return null;
        }

        @Override
        public void b(LTView lTView, RectF rectF) {

        }

        @Override
        public void c(LTView lTView, RectF rectF) {

        }

        @Override
        public boolean c(LTView lTView) {
            return false;
        }

        @Override
        public boolean d(LTView lTView) {
            return false;
        }

        @Override
        public boolean d(LTView lTView, RectF rectF) {
            return false;
        }
    };
    public final LTView b;
    public final RenderEngine c = RenderEngine.b();
    public DrawDelegate d = a;
    public Pair<Scalar, Scalar> e;
    public Texture f;
    public RectDrawer g;
    public Texture h;
    public Fbo i;
    public TexturedRect j;
    public final RectF k = new RectF();
    public final float[] l = new float[16];
    public final float[] m = new float[16];
    public final RectF n = new RectF();
    public final RectF o = new RectF();
    public PixelGrid p;
    public final Map<String, Texture> q = new HashMap();

    public LTViewRenderer(LTView lTView) {
        this.b = lTView;
        Matrix.setIdentityM(this.l, 0);
        Matrix.setIdentityM(this.m, 0);
    }

    public void a(DrawDelegate drawDelegate) {
        if (drawDelegate == null) {
            drawDelegate = a;
        }
        this.d = drawDelegate;
    }

    public void b() {
        GLES20.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        GLES20.glDisable(2929);
        GLES20.glDisable(3024);
        GLES20.glDisable(3042);
        GLES20.glDisable(2960);
        GLES20.glDisable(2884);
        GLES20.glBlendEquation(32774);
        GLES20.glBlendFunc(770, 771);
        if (this.h != null) {
            Fbo fbo = this.i;
            if (fbo != null) {
                fbo.dispose();
                this.i = null;
            }
            TexturedRect texturedRect = this.j;
            if (texturedRect != null) {
                texturedRect.dispose();
                this.j = null;
            }
            PixelGrid pixelGrid = this.p;
            if (pixelGrid != null) {
                pixelGrid.dispose();
                this.p = null;
            }
            this.i = new Fbo(this.h);
            this.j = new TexturedRect(this.h);
            this.p = new PixelGrid(this.b);
        }
        i();
    }

    public void c() {
        d();
    }

    public void d() {
        Fbo fbo = this.i;
        if (fbo != null) {
            fbo.dispose();
            this.i = null;
        }
        TexturedRect texturedRect = this.j;
        if (texturedRect != null) {
            texturedRect.dispose();
            this.j = null;
        }
        Texture texture = this.f;
        if (texture != null) {
            texture.dispose();
            this.f = null;
        }
        RectDrawer rectDrawer = this.g;
        if (rectDrawer != null) {
            rectDrawer.dispose();
            this.g = null;
        }
        PixelGrid pixelGrid = this.p;
        if (pixelGrid != null) {
            pixelGrid.dispose();
            this.p = null;
        }
    }

    public final NavigationModel e() {
        return f().getCurrentFrameNavigationModel();
    }

    public LTView f() {
        return this.b;
    }

    public final NavigationModel g() {
        return f().getNextFrameNavigationModel();
    }

    public void h() {
        this.c.j();
    }

    public final void i() {
        RectDrawer rectDrawer = this.g;
        if (rectDrawer != null) {
            rectDrawer.dispose();
            this.g = null;
        }
        Texture texture = this.f;
        if (texture != null) {
            texture.dispose();
            this.f = null;
        }
        Pair<Scalar, Scalar> pair = this.e;
        if (pair != null) {
            Mat a2 = BackgroundFactory.a(64, 64, (Scalar) pair.first, (Scalar) pair.second);
            this.f = new Texture(Texture.Type.RGB8Unorm, a2);
            this.f.a(9729);
            this.g = new RectDrawer(this.f);
            this.g.a((float[]) this.m.clone());
            a2.release();
        }
    }

    public void a(Texture texture) {
        this.h = texture;
        Fbo fbo = this.i;
        if (fbo != null) {
            fbo.dispose();
            this.i = null;
        }
        TexturedRect texturedRect = this.j;
        if (texturedRect != null) {
            texturedRect.dispose();
            this.j = null;
        }
        PixelGrid pixelGrid = this.p;
        if (pixelGrid != null) {
            pixelGrid.dispose();
            this.p = null;
        }
        if (texture != null) {
            this.o.set(0.0f, 0.0f, (float) texture.l(), (float) texture.i());
            this.i = new Fbo(texture);
            this.j = new TexturedRect(texture);
            this.p = new PixelGrid(this.b);
        }
    }

    public void a(Pair<Scalar, Scalar> pair) {
        this.e = pair != null ? new Pair<>(((Scalar) pair.first).clone(), ((Scalar) pair.second).clone()) : null;
        i();
    }

    public void a() {
        RectF rectF;
        GLES20.glClear(17664);
        boolean z = this.h != null;
        f().a();
        synchronized (this.k) {
            if (z) {
                if (!this.k.isEmpty()) {
                    rectF = new RectF(this.k);
                    this.k.setEmpty();
                }
            }
            rectF = null;
        }
        if (rectF != null && this.d.a(this.b)) {
            this.i.a();
            this.d.c(this.b, rectF);
            this.i.c();
        }
        Texture texture = this.f;
        if (texture != null) {
            this.g.a(texture.e(), this.n);
        }
        if (this.d.d(this.b)) {
            this.d.b(this.b, this.n);
        }
        Texture b2 = this.d.b(this.b);
        if (b2 == null) {
            b2 = this.h;
        }
        if (!this.d.d(this.b, (RectF) null) && b2 != null) {
            int g2 = b2.g();
            b2.a(this.p.b() ? 9728 : 9729);
            this.q.put("sourceTexture", b2);
            this.j.a(this.q, "sourceTexture");
            e().a(this.l);
            this.j.a(this.l);
            this.j.a(e().b(), this.n);
            b2.a(g2);
        }
        PixelGrid pixelGrid = this.p;
        if (pixelGrid != null) {
            pixelGrid.a();
        }
        if (this.d.c(this.b)) {
            this.d.a(this.b, (RectF) null);
        }
        GLUtils.a();
    }

    public void b(RectF rectF) {
        synchronized (this.k) {
            if (rectF != null) {
                this.k.union(rectF);
                this.k.intersect(this.o);
            } else {
                this.k.set(this.o);
            }
        }
        h();
    }

    public void a(int i2, int i3) {
        this.n.set(0.0f, 0.0f, (float) i2, (float) i3);
        GLES20.glViewport(0, 0, i2, i3);
        f().setNextFrameNavigationModel(f().getNextFrameNavigationModel().j(this.n).i(this.n));
        g().b(this.m);
        PixelGrid pixelGrid = this.p;
        if (pixelGrid != null) {
            pixelGrid.a(this.m);
        }
        RectDrawer rectDrawer = this.g;
        if (rectDrawer != null) {
            rectDrawer.a(this.m);
        }
    }

    public void a(RectF rectF) {
        this.c.j();
    }
}
