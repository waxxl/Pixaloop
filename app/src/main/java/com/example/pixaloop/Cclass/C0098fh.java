package com.example.pixaloop.Cclass;

import com.example.pixaloop.render.PixaloopAnimator;

import io.reactivex.rxjava3.functions.Consumer;

public final /* synthetic */ class C0098fh implements Consumer {
    private final /* synthetic */ PixaloopAnimator a;

    public /* synthetic */ C0098fh(PixaloopAnimator pixaloopAnimator) {
        this.a = pixaloopAnimator;
    }

    public final void accept(Object obj) {
        this.a.b((Float) obj);
    }
}