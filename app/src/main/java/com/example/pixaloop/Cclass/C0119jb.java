package com.example.pixaloop.Cclass;


import com.example.pixaloop.common.render.RenderEngine;

/* renamed from: jb  reason: default package and case insensitive filesystem */
/* compiled from: lambda */
public final /* synthetic */ class C0119jb implements Runnable {
    private final /* synthetic */ RenderEngine a;
    private final /* synthetic */ RenderEngine.RenderEngineDelegate b;

    public /* synthetic */ C0119jb(RenderEngine renderEngine, RenderEngine.RenderEngineDelegate renderEngineDelegate) {
        this.a = renderEngine;
        this.b = renderEngineDelegate;
    }

    public final void run() {
        this.a.d(this.b);
    }
}