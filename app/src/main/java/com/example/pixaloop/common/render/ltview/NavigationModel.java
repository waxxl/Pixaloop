package com.example.pixaloop.common.render.ltview;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.Matrix;

import androidx.core.util.Preconditions;

import com.example.pixaloop.common.render.utils.IsotropicTransform2D;
import com.example.pixaloop.common.render.utils.MathUtils;

public class NavigationModel {
    public static final RectF a = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
    public final IsotropicTransform2D b;
    public final boolean c;
    public final int d;
    public final RectF e;
    public final RectF f;
    public final RectF g;

    public NavigationModel() {
        this.e = new RectF(a);
        this.f = new RectF(a);
        this.g = new RectF(a);
        this.b = new IsotropicTransform2D();
        this.d = 0;
        this.c = false;
    }

    public NavigationModel a(float f2, float f3, float f4) {
        IsotropicTransform2D h = h();
        h.b(f2, f3, f4);
        return new NavigationModel(this.e, this.f, this.g, h, this.d, this.c);
    }

    @SuppressLint("RestrictedApi")
    public NavigationModel b(int i) {
        Preconditions.checkState(i >= 0 && i <= 4);
        return a(i - this.d);
    }

    public NavigationModel c(float f2, float f3) {
        IsotropicTransform2D h = h();
        h.c(f2, f3);
        return new NavigationModel(this.e, this.f, this.g, h, this.d, this.c);
    }

    public RectF d() {
        return new RectF(this.g);
    }

    public float[] e() {
        float[] fArr = new float[16];
        b(fArr);
        return fArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NavigationModel.class != obj.getClass()) {
            return false;
        }
        NavigationModel navigationModel = (NavigationModel) obj;
        if (this.c == navigationModel.c && this.d == navigationModel.d && this.b.equals(navigationModel.b) && this.e.equals(navigationModel.e) && this.f.equals(navigationModel.f)) {
            RectF rectF = this.g;
            if (rectF.equals(rectF)) {
                return true;
            }
        }
        return false;
    }

    public float f() {
        return this.b.a();
    }

    public void g(RectF rectF) {
        this.b.a(this.e, rectF);
        MathUtils.a(rectF);
        if (!rectF.intersect(this.g)) {
            rectF.setEmpty();
        }
    }

    public IsotropicTransform2D h() {
        return new IsotropicTransform2D(this.b);
    }

    public float i() {
        return this.b.b();
    }

    public float j() {
        return this.b.c();
    }

    public RectF k() {
        return new RectF(this.f);
    }

    public RectF l() {
        RectF rectF = new RectF();
        g(rectF);
        return rectF;
    }

    public boolean m() {
        return this.e.equals(a);
    }

    public boolean n() {
        return !this.e.equals(a) && !this.f.equals(a) && !this.g.equals(a);
    }

    public NavigationModel o() {
        return l(c());
    }


    public void d(RectF rectF) {
        rectF.set(this.g);
    }

    public void f(RectF rectF) {
        rectF.set(this.f);
    }

    public NavigationModel h(RectF rectF) {
        if (rectF == null) {
            rectF = new RectF(a);
        }
        NavigationModel navigationModel = new NavigationModel(new RectF(rectF), this.f, this.g, this.b, this.d, this.c);
        return navigationModel.n() ? navigationModel.k(navigationModel.e) : navigationModel;
    }

    @SuppressLint("RestrictedApi")
    public NavigationModel i(RectF rectF) {
        Preconditions.checkState(this.f.contains(rectF) || (this.f.isEmpty() && rectF.isEmpty()));
        NavigationModel navigationModel = new NavigationModel(this.e, this.f, rectF, this.b, this.d, this.c);
        return navigationModel.n() ? navigationModel.k(navigationModel.e) : navigationModel;
    }

    public NavigationModel j(RectF rectF) {
        if (rectF == null) {
            rectF = new RectF(a);
        }
        NavigationModel navigationModel = new NavigationModel(this.e, new RectF(rectF), this.g, this.b, this.d, this.c);
        return navigationModel.n() ? navigationModel.k(navigationModel.e) : navigationModel;
    }

    @SuppressLint("RestrictedApi")
    public NavigationModel k(RectF rectF) {
        Preconditions.checkState(!rectF.isEmpty() && this.e.contains(rectF));
        NavigationModel b2 = b(e(rectF));
        RectF a2 = b2.b.a(rectF);
        MathUtils.a(a2);
        return b2.l(a2);
    }

    public void b(float[] fArr) {
        RectF rectF = this.f;
        Matrix.orthoM(fArr, 0, rectF.left, rectF.right, rectF.bottom, rectF.top, -1.0f, 1.0f);
    }

    public float e(RectF rectF) {
        float width = this.g.width();
        float height = this.g.height();
        float width2 = rectF.width();
        float height2 = rectF.height();
        if (this.d % 2 == 0) {
            return Math.min(width / width2, height / height2);
        }
        return Math.min(width / height2, height / width2);
    }

    public NavigationModel l(RectF rectF) {
        PointF a2 = a(rectF);
        return c(a2.x, a2.y);
    }

    public NavigationModel a(float f2) {
        RectF l = l();
        return a(f2, l.centerX(), l.centerY());
    }

    public NavigationModel b(float f2) {
        IsotropicTransform2D h = h();
        h.c(f2);
        return new NavigationModel(this.e, this.f, this.g, h, this.d, this.c);
    }

    public void c(RectF rectF) {
        this.b.a(this.e, rectF);
        MathUtils.a(rectF);
    }

    public float g() {
        return e(this.e);
    }

    public NavigationModel a(boolean z) {
        if (this.c == z) {
            return this;
        }
        return a();
    }

    public RectF c() {
        RectF rectF = new RectF();
        c(rectF);
        return rectF;
    }

    public NavigationModel b(float f2, float f3) {
        IsotropicTransform2D h = h();
        h.d(f2);
        h.e(f3);
        return new NavigationModel(this.e, this.f, this.g, h, this.d, this.c);
    }

    public NavigationModel(RectF rectF, RectF rectF2, RectF rectF3, IsotropicTransform2D isotropicTransform2D, int i, boolean z) {
        this.e = new RectF(rectF);
        this.f = new RectF(rectF2);
        this.g = new RectF(rectF3);
        this.b = new IsotropicTransform2D(isotropicTransform2D);
        this.d = i;
        this.c = z;
    }

    public NavigationModel a() {
        IsotropicTransform2D h = h();
        h.a(this.f.centerX());
        return new NavigationModel(this.e, this.f, this.g, h, this.d, !this.c);
    }

    public void b(float f2, float f3, PointF pointF) {
        this.b.d().a(f2, f3, pointF);
    }

    public final NavigationModel a(int i) {
        if (i % 4 == 0) {
            return this;
        }
        IsotropicTransform2D h = h();
        h.a((float) (i * 90), this.f.centerX(), this.f.centerY());
        return new NavigationModel(this.e, this.f, this.g, h, (((this.d + (i * (this.c ? -1 : 1))) % 4) + 4) % 4, this.c);
    }

    public RectF b() {
        return new RectF(this.e);
    }

    public void b(RectF rectF) {
        rectF.set(this.e);
    }

    public void a(float[] fArr) {
        this.b.b(fArr);
    }

    public NavigationModel a(IsotropicTransform2D isotropicTransform2D) {
        IsotropicTransform2D h = h();
        h.a(isotropicTransform2D);
        return new NavigationModel(this.e, this.f, this.g, h, this.d, this.c);
    }

    public void a(float f2, float f3, PointF pointF) {
        this.b.a(f2, f3, pointF);
    }

    public final PointF a(RectF rectF) {
        float f2 = rectF.left;
        RectF rectF2 = this.g;
        float f3 = f2 - rectF2.left;
        float f4 = rectF.top - rectF2.top;
        float f5 = rectF2.right - rectF.right;
        float f6 = rectF2.bottom - rectF.bottom;
        float abs = Math.abs(MathUtils.a(f5) - MathUtils.a(f3));
        float abs2 = Math.abs(MathUtils.a(f6) - MathUtils.a(f4));
        float f7 = (f5 - f3) * 0.5f;
        float f8 = (f6 - f4) * 0.5f;
        return new PointF(Math.signum(f7) * Math.min(Math.abs(f7), abs), Math.signum(f8) * Math.min(Math.abs(f8), abs2));
    }

    public boolean a(float f2, float f3) {
        RectF rectF = this.e;
        return f2 >= rectF.left && f2 <= rectF.right && f3 >= rectF.top && f3 <= rectF.bottom;
    }
}
