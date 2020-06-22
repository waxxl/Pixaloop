package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.AnimationClock;

public final /* synthetic */ class Za implements Runnable {
    private final /* synthetic */ AnimationClock a;

    public /* synthetic */ Za(AnimationClock animationClock) {
        this.a = animationClock;
    }

    public final void run() {
        this.a.c();
    }
}