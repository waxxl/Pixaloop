package com.example.pixaloop.common.render.ltview;

/* renamed from: mb  reason: default package and case insensitive filesystem */
/* compiled from: lambda */
public final /* synthetic */ class C0140mb implements Runnable {
    private final /* synthetic */ LTView a;
    private final /* synthetic */ DrawDelegate b;

    public /* synthetic */ C0140mb(LTView lTView, DrawDelegate drawDelegate) {
        this.a = lTView;
        this.b = drawDelegate;
    }

    public final void run() {
        this.a.a(this.b);
    }
}