package com.example.pixaloop.Cclass;

import android.animation.ValueAnimator;

import com.example.pixaloop.common.render.AnimationClock;

public final /* synthetic */ class C0126kb implements ValueAnimator.AnimatorUpdateListener {
    private final /* synthetic */ AnimationClock a;

    public /* synthetic */ C0126kb(AnimationClock animationClock) {
        this.a = animationClock;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.a.a(valueAnimator);
    }
}