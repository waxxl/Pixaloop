package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.AnimationClock;

public final /* synthetic */ class C0057ab implements Runnable {
    private final /* synthetic */ AnimationClock a;
    private final /* synthetic */ long b;

    public /* synthetic */ C0057ab(AnimationClock animationClock, long j) {
        this.a = animationClock;
        this.b = j;
    }

    public final void run() {
        this.a.a(this.b);
    }
}