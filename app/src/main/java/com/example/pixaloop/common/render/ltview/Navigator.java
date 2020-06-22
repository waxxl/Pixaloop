package com.example.pixaloop.common.render.ltview;

import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.OverScroller;

import com.example.pixaloop.R;
import com.example.pixaloop.common.render.ltview.LTView;
import com.example.pixaloop.common.render.utils.GenericGestureDetector;
import com.example.pixaloop.common.render.utils.IsotropicTransform2D;
import com.example.pixaloop.common.render.utils.IsotropicTransform2DEvaluator;
import com.example.pixaloop.common.render.utils.MathUtils;

public class Navigator implements View.OnTouchListener {
    public final LTView a;
    public final GenericGestureDetector b;
    public final ScaleGestureDetector c;
    public final OverScroller d;
    public final ObjectAnimator e;
    public final float f;
    public boolean g = false;
    public EventFilter h;
    public final RectF i = new RectF();
    public final RectF j = new RectF();
    public final RectF k = new RectF();
    public final RectF l = new RectF();
    public final PointF m = new PointF();
    public final Runnable n = new Runnable() {
        public void run() {
            if (Navigator.this.d.computeScrollOffset()) {
                Navigator.this.a.setNextFrameNavigationModel(Navigator.this.a.getNextFrameNavigationModel().b((float) Navigator.this.d.getCurrX(), (float) Navigator.this.d.getCurrY()));
                Navigator.this.h();
                Navigator.this.a.postOnAnimation(this);
            }
        }
    };
    public final ScaleGestureDetector.OnScaleGestureListener o = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!Navigator.this.h.a(scaleGestureDetector)) {
                return false;
            }
            Navigator navigator = Navigator.this;
            navigator.b(navigator.a(scaleGestureDetector.getScaleFactor()), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return Navigator.this.h.a(scaleGestureDetector);
        }
    };
    public final GenericGestureDetector.OnGestureListener p = new GenericGestureDetector.SimpleOnGestureListener() {
        public void a(MotionEvent motionEvent) {
            Navigator.this.a();
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (!Navigator.this.h.a(motionEvent)) {
                return false;
            }
            if (motionEvent.getAction() == 1) {
                Navigator.this.a(motionEvent.getX(), motionEvent.getY());
            }
            return true;
        }

        public boolean onDown(MotionEvent motionEvent) {
            Navigator.this.c();
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!Navigator.this.h.a(motionEvent, motionEvent2)) {
                return false;
            }
            Navigator.this.b(f, f2);
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!Navigator.this.h.b(motionEvent, motionEvent2)) {
                return false;
            }
            Navigator.this.c(-f, -f2);
            return true;
        }
    };

    /* renamed from: com.example.pixaloop.common.render.ltview.Navigator$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] a = new int[LTView.NavigationMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            a[LTView.NavigationMode.NONE.ordinal()] = 1;
            a[LTView.NavigationMode.FULL.ordinal()] = 2;
            a[LTView.NavigationMode.TWO_FINGERS.ordinal()] = 3;
            a[LTView.NavigationMode.NONE_BOUNCE_BACK.ordinal()] = 4;
            a[LTView.NavigationMode.FULL_BOUNCE_BACK.ordinal()] = 5;
        }
    }

    private static class AcceptAllFilter implements EventFilter {
        public AcceptAllFilter() {
        }

        public boolean a(MotionEvent motionEvent) {
            return true;
        }

        public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return true;
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return true;
        }
    }

    private interface EventFilter {
        boolean a(MotionEvent motionEvent);

        boolean a(MotionEvent motionEvent, MotionEvent motionEvent2);

        boolean a(ScaleGestureDetector scaleGestureDetector);

        boolean b(MotionEvent motionEvent, MotionEvent motionEvent2);
    }

    private static class RejectAllFilter implements EventFilter {
        public RejectAllFilter() {
        }

        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return false;
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return false;
        }
    }

    private static class RejectDoubleTapFilter implements EventFilter {
        public RejectDoubleTapFilter() {
        }

        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return true;
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return true;
        }
    }

    public Navigator(LTView lTView) {
        this.a = lTView;
        this.b = new GenericGestureDetector(lTView.getContext(), this.p);
        this.c = new ScaleGestureDetector(lTView.getContext(), this.o);
        this.c.setQuickScaleEnabled(false);
        this.d = new OverScroller(lTView.getContext());
        this.e = ObjectAnimator.ofObject(lTView, new Property<LTView, IsotropicTransform2D>(IsotropicTransform2D.class, "transform") {
            /* renamed from: a */
            public IsotropicTransform2D get(LTView lTView) {
                return lTView.getCurrentFrameNavigationModel().h();
            }

            /* renamed from: a */
            public void set(LTView lTView, IsotropicTransform2D isotropicTransform2D) {
                lTView.setNextFrameNavigationModel(lTView.getNextFrameNavigationModel().a(isotropicTransform2D));
                Navigator.this.h();
            }
        }, new IsotropicTransform2DEvaluator(), new IsotropicTransform2D[]{new IsotropicTransform2D()});
        this.e.setDuration(500);
        this.e.setInterpolator(new DecelerateInterpolator(2.0f));
        this.f = lTView.getResources().getDimension(R.dimen.ltview_navigator_max_scale);
    }

    public final void g() {
        this.a.postOnAnimation(this.n);
    }

    public final void h() {
        this.a.b();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.b.a(motionEvent) || this.c.onTouchEvent(motionEvent);
    }

    private static class AcceptTwoFingersFilter implements EventFilter {
        public AcceptTwoFingersFilter() {
        }

        public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return (motionEvent != null && motionEvent.getPointerCount() >= 2) || (motionEvent2 != null && motionEvent2.getPointerCount() >= 2);
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2) {
            return (motionEvent != null && motionEvent.getPointerCount() >= 2) || (motionEvent2 != null && motionEvent2.getPointerCount() >= 2);
        }

        public boolean a(MotionEvent motionEvent) {
            return motionEvent != null && motionEvent.getPointerCount() >= 2;
        }
    }

    public final NavigationModel d() {
        return this.a.getNextFrameNavigationModel();
    }

    public final int e() {
        for (int i2 = 0; i2 < 3; i2++) {
            if (Math.abs(d().f() - a(i2)) < 0.01f) {
                return (i2 + 1) % 3;
            }
        }
        return 0;
    }

    public final boolean f() {
        d().d(this.j);
        d().g(this.k);
        this.l.left = Math.max(this.k.left - this.j.left, 0.0f);
        this.l.top = Math.max(this.k.top - this.j.top, 0.0f);
        this.l.right = Math.max(this.j.right - this.k.right, 0.0f);
        this.l.bottom = Math.max(this.j.bottom - this.k.bottom, 0.0f);
        float f2 = d().f();
        float g2 = d().g();
        RectF rectF = this.l;
        if (Math.abs(rectF.left - rectF.right) <= 0.01f) {
            RectF rectF2 = this.l;
            return Math.abs(rectF2.top - rectF2.bottom) <= 0.01f && f2 >= g2 && (!this.g ? f2 <= this.f : f2 <= g2);
        }
        return false;
    }

    public final float b(float f2) {
        float f3;
        double pow;
        float g2 = d().g();
        float f4 = d().f() * f2;
        float a2 = MathUtils.a(f4, g2, this.f);
        if (f2 > 1.0f && f4 > a2) {
            f3 = f2 - 1.0f;
            pow = Math.pow((double) (a2 / f4), 4.0d);
        } else if (f2 >= 1.0f || f4 >= a2) {
            return f2;
        } else {
            f3 = f2 - 1.0f;
            pow = Math.pow((double) (f4 / a2), 4.0d);
        }
        return (f3 * ((float) pow)) + 1.0f;
    }

    public final void c(float f2, float f3) {
        if (!d().m()) {
            a(f2, f3, this.m);
            LTView lTView = this.a;
            NavigationModel nextFrameNavigationModel = lTView.getNextFrameNavigationModel();
            PointF pointF = this.m;
            lTView.setNextFrameNavigationModel(nextFrameNavigationModel.c(pointF.x, pointF.y));
            h();
        }
    }

    public void a(LTView.NavigationMode navigationMode) {
        int i2 = AnonymousClass5.a[navigationMode.ordinal()];
        if (i2 == 1) {
            this.h = new RejectAllFilter();
            this.g = false;
        } else if (i2 == 2) {
            this.h = new AcceptAllFilter();
            this.g = false;
        } else if (i2 == 3) {
            this.h = new AcceptTwoFingersFilter();
            this.g = false;
        } else if (i2 == 4) {
            this.h = new RejectAllFilter();
            this.g = true;
        } else if (i2 == 5) {
            this.h = new RejectDoubleTapFilter();
            this.g = true;
        }
        b();
    }

    public final void b(float f2, float f3, float f4) {
        if (!d().m()) {
            LTView lTView = this.a;
            lTView.setNextFrameNavigationModel(lTView.getNextFrameNavigationModel().a(b(f2), f3, f4));
            h();
        }
    }

    public final void c() {
        this.d.forceFinished(true);
        this.a.removeCallbacks(this.n);
        this.e.cancel();
    }

    public final void b(float f2, float f3) {
        if (d().m()) {
            Log.w("Navigator", "navigateContent without content");
        } else if (f()) {
            float f4 = d().f();
            d().b(this.i);
            d().d(this.j);
            float width = this.i.width() * f4;
            float height = f4 * this.i.height();
            RectF rectF = this.j;
            float f5 = rectF.left;
            float f6 = rectF.top;
            float width2 = (f5 - width) + rectF.width();
            float height2 = (f6 - height) + this.j.height();
            this.d.fling((int) d().i(), (int) d().j(), (int) f2, (int) f3, (int) width2, (int) f5, (int) height2, (int) f6, (int) (this.j.width() / 4.0f), (int) (this.j.height() / 4.0f));
            g();
        }
    }

    public void a(NavigationModel navigationModel) {
        b(a(navigationModel, this.g), false);
    }

    public final void a(float f2, float f3, PointF pointF) {
        RectF d2 = d().d();
        RectF c2 = d().c();
        float f4 = c2.left - d2.left;
        float f5 = c2.top - d2.top;
        float f6 = d2.right - c2.right;
        float f7 = d2.bottom - c2.bottom;
        float a2 = MathUtils.a(f6) - MathUtils.a(f4);
        float a3 = MathUtils.a(f7) - MathUtils.a(f5);
        float f8 = 1.0f;
        float pow = ((a2 <= 0.0f || f2 >= 0.0f) && (a2 >= 0.0f || f2 <= 0.0f)) ? 1.0f : (float) Math.pow((double) (1.0f - MathUtils.a(Math.abs(a2) / d2.width(), 0.0f, 1.0f)), 4.0d);
        if ((a3 > 0.0f && f3 < 0.0f) || (a3 < 0.0f && f3 > 0.0f)) {
            f8 = (float) Math.pow((double) (1.0f - MathUtils.a(Math.abs(a3) / d2.height(), 0.0f, 1.0f)), 4.0d);
        }
        pointF.set(f2 * pow, f3 * f8);
    }

    public final void b() {
        if (d().m()) {
            Log.w("Navigator", "bounceBack without content");
        } else {
            b(a(d(), this.g), true);
        }
    }

    public final float a(float f2) {
        return (float) Math.pow((double) f2, 1.6d);
    }

    public final void a(float f2, float f3) {
        if (!d().m()) {
            a(a(e()), f2, f3);
        }
    }

    public final void b(NavigationModel navigationModel, boolean z) {
        c();
        boolean z2 = navigationModel.n() && d().n();
        if (!z || !z2) {
            LTView lTView = this.a;
            lTView.setNextFrameNavigationModel(lTView.getNextFrameNavigationModel().h(navigationModel.b()).a(navigationModel.h()));
            h();
            return;
        }
        this.e.setObjectValues(new Object[]{d().h(), navigationModel.h()});
        this.e.start();
    }

    public final float a(int i2) {
        return (float) (((double) d().g()) * Math.pow(3.0d, (double) i2));
    }

    public final void a(float f2, float f3, float f4) {
        NavigationModel navigationModel;
        float f5 = d().f();
        if (f5 < f2) {
            navigationModel = d().a(b(f2 / f5), f3, f4);
        } else {
            navigationModel = d().b(f2).o();
        }
        this.e.cancel();
        this.e.setObjectValues(new Object[]{d().h(), navigationModel.h()});
        this.e.start();
    }

    public final void a() {
        if (this.d.isFinished() && !f() && !this.e.isRunning()) {
            b();
        }
    }

    public final NavigationModel a(NavigationModel navigationModel, boolean z) {
        if (navigationModel.m()) {
            return navigationModel;
        }
        float f2 = navigationModel.f();
        float g2 = navigationModel.g();
        if (!z) {
            g2 = MathUtils.a(f2, g2, this.f);
        }
        return navigationModel.a(g2 / f2).o();
    }
}
