package com.example.pixaloop.common.render.ltview;

import android.util.Pair;

/* renamed from: nb  reason: default package and case insensitive filesystem */
/* compiled from: lambda */
public final /* synthetic */ class C0147nb implements Runnable {
    private final /* synthetic */ LTView a;
    private final /* synthetic */ Pair b;

    public /* synthetic */ C0147nb(LTView lTView, Pair pair) {
        this.a = lTView;
        this.b = pair;
    }

    public final void run() {
        this.a.a(this.b);
    }
}