package com.example.pixaloop.Cclass;


import com.example.pixaloop.util.LogU;

import io.reactivex.rxjava3.functions.Consumer;

/* renamed from: ih  reason: default package and case insensitive filesystem */
/* compiled from: lambda */
public final /* synthetic */ class C0118ih implements Consumer {
    public static final /* synthetic */ C0118ih a = new C0118ih();

    private /* synthetic */ C0118ih() {
    }

    public final void accept(Object obj) {
        LogU.a("PixaloopAnimator", "Failed to publish new frame", (Throwable) obj);
    }
}