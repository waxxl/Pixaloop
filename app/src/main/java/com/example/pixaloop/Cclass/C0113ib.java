package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.RenderEngine;

public final /* synthetic */ class C0113ib implements Runnable {
    private final /* synthetic */ RenderEngine a;
    private final /* synthetic */ RenderEngine.RenderEngineDelegate b;

    public /* synthetic */ C0113ib(RenderEngine renderEngine, RenderEngine.RenderEngineDelegate renderEngineDelegate) {
        this.a = renderEngine;
        this.b = renderEngineDelegate;
    }

    public final void run() {
        this.a.c(this.b);
    }
}