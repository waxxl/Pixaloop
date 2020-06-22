package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.RenderEngine;

public final /* synthetic */ class C0092fb implements Runnable {
    private final /* synthetic */ RenderEngine a;
    private final /* synthetic */ Object b;

    public /* synthetic */ C0092fb(RenderEngine renderEngine, Object obj) {
        this.a = renderEngine;
        this.b = obj;
    }

    public final void run() {
        this.a.c(this.b);
    }
}