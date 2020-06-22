package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.AnimationClock;

public final /* synthetic */ class _a implements Runnable {
    private final /* synthetic */ AnimationClock a;
    private final /* synthetic */ double b;

    public /* synthetic */ _a(AnimationClock animationClock, double d) {
        this.a = animationClock;
        this.b = d;
    }

    public final void run() {
        this.a.a(this.b);
    }
}