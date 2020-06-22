package com.example.pixaloop.common.render;

import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.animation.LinearInterpolator;

import com.example.pixaloop.Cclass.C0057ab;
import com.example.pixaloop.Cclass.C0064bb;
import com.example.pixaloop.Cclass.C0071cb;
import com.example.pixaloop.Cclass.C0126kb;
import com.example.pixaloop.Cclass.Za;
import com.example.pixaloop.Cclass._a;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class AnimationClock implements DisposableResource {
    public final Handler a;
    public double b;
    public ValueAnimator c;
    public BehaviorSubject<Float> d = BehaviorSubject.create();

    public AnimationClock(Handler handler, long j) {
        this.a = handler;
        this.b = (double) j;
        handler.post(new C0057ab(this, j));
    }

    public /* synthetic */ void a(long j) {
        this.c = ValueAnimator.ofFloat(new float[]{-1.0f, 1.0f});
        this.c.setDuration(j * 1000);
        this.c.setRepeatMode(ValueAnimator.RESTART);
        this.c.setRepeatCount(-1);
        this.c.setInterpolator(new LinearInterpolator());
        this.c.addUpdateListener(new C0126kb(this));
        this.d.onNext(Float.valueOf(0.0f));
    }

    public Observable<Float> b() {
        return this.d;
    }

    public /* synthetic */ void c() {
        this.c.removeUpdateListener(new C0126kb(this));
        this.d.onComplete();
    }

    public /* synthetic */ void d() {
        if (this.c.isPaused()) {
            this.c.resume();
        } else {
            this.c.start();
        }
    }

    public void dispose() {
        this.a.post(new Za(this));
    }

    public /* synthetic */ void e() {
        this.c.cancel();
        this.c.setCurrentPlayTime(0);
    }

    public void f() {
        this.a.post(new C0064bb(this));
    }

    public void g() {
        this.a.post(new C0071cb(this));
    }

    public void b(double d2) {
        this.a.post(new _a(this, d2));
    }

    public final void a(ValueAnimator valueAnimator) {
        try {
            this.d.onNext(Float.valueOf(valueAnimator.getAnimatedFraction() * 100.0f));
        } catch (Exception e) {
            //this.d.a((Throwable) e);
        }
    }

    public double a() {
        return this.b;
    }

    public /* synthetic */ void a(double d2) {
        this.b = d2;
        this.c.setDuration((long) (d2 * 1000.0d));
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
