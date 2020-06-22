package com.example.pixaloop.common.render.ltview;


import com.example.pixaloop.common.render.gpu.Texture;

/* renamed from: ob  reason: default package and case insensitive filesystem */
/* compiled from: lambda */
public final /* synthetic */ class C0154ob implements Runnable {
    private final /* synthetic */ LTView a;
    private final /* synthetic */ Texture texture;

    public /* synthetic */ C0154ob(LTView lTView, Texture texture) {
        this.a = lTView;
        this.texture = texture;
    }

    public final void run() {
        this.a.a(this.texture);
    }
}