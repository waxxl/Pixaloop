package com.example.pixaloop.Cclass;

import com.example.pixaloop.features.SessionState;
import com.example.pixaloop.render.PixaloopAnimator;

public final /* synthetic */ class C0091eh implements Runnable {
    private final /* synthetic */ PixaloopAnimator a;
    private final /* synthetic */ SessionState b;
    private final /* synthetic */ boolean c;

    public /* synthetic */ C0091eh(PixaloopAnimator pixaloopAnimator, SessionState sessionState, boolean z) {
        this.a = pixaloopAnimator;
        this.b = sessionState;
        this.c = z;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}