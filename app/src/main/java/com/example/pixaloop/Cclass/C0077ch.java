package com.example.pixaloop.Cclass;

import com.example.pixaloop.util.LogU;

import io.reactivex.rxjava3.functions.Consumer;

public final /* synthetic */ class C0077ch implements Consumer {
    public static final /* synthetic */ C0077ch a = new C0077ch();

    private /* synthetic */ C0077ch() {
    }

    public final void accept(Object obj) {
        LogU.a("PixaloopAnimator", "Failed to publish new frame", (Throwable) obj);
    }
}