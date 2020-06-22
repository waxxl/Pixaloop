package com.example.pixaloop.common.render.painter;

import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.Vector4;
import com.example.pixaloop.common.render.painter.Painter;

public class PainterParams {
    public int a = -1;
    public int b = -1;
    public Texture c = null;
    public float d = 100.0f;
    public Brush e = null;
    public Brush f = null;
    public float g = 0.0f;
    public float h = 1.0f;
    public Vector4 i = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);
    public Vector4 j = new Vector4(1.0f, 1.0f, 1.0f, 1.0f);
    public float k = 100.0f;
    public float l = 100.0f;
    public boolean m = false;
    public Texture n = null;
    public Painter.PainterMode o = Painter.PainterMode.PAINT;
    public boolean p = false;
    public boolean q = false;

    public PainterParams a(int i2, int i3) {
        if (i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("negative size");
        }
        this.a = i2;
        this.b = i3;
        return this;
    }

    public PainterParams b(Brush brush) {
        if (brush != null) {
            this.e = brush;
            return this;
        }
        throw new NullPointerException();
    }

    public PainterParams c(float f2) {
        if (f2 >= 0.0f) {
            this.d = f2;
            return this;
        }
        throw new IllegalArgumentException("negative sigma");
    }

    public PainterParams b(float f2) {
        if (f2 > 0.0f) {
            this.k = f2;
            return this;
        }
        throw new IllegalArgumentException("non positive size");
    }

    public PainterParams c(boolean z) {
        this.m = z;
        return this;
    }

    public PainterParams a(float f2, float f3) {
        this.g = f2;
        this.h = f3;
        return this;
    }

    public PainterParams b(boolean z) {
        this.p = z;
        return this;
    }

    public PainterParams a(Vector4 vector4, Vector4 vector42) {
        this.i = vector4;
        this.j = vector42;
        return this;
    }

    public PainterParams a(Brush brush) {
        if (brush != null) {
            this.f = brush;
            return this;
        }
        throw new NullPointerException();
    }

    public PainterParams a(float f2) {
        if (f2 > 0.0f) {
            this.l = f2;
            return this;
        }
        throw new IllegalArgumentException("non positive size");
    }

    public PainterParams a(Painter.PainterMode painterMode) {
        if (painterMode != null) {
            this.o = painterMode;
            return this;
        }
        throw new NullPointerException();
    }

    public PainterParams a(Texture texture) {
        if (texture != null) {
            this.n = texture;
            return this;
        }
        throw new NullPointerException();
    }

    public PainterParams a(boolean z) {
        this.q = z;
        return this;
    }
}
