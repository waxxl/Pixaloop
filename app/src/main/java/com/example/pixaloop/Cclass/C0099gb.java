package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.RenderEngine;

import java.util.concurrent.Callable;

public final /* synthetic */ class C0099gb implements Callable {
    private final /* synthetic */ RenderEngine a;

    public /* synthetic */ C0099gb(RenderEngine renderEngine) {
        this.a = renderEngine;
    }

    public final Object call() {
        return this.a.i();
    }
}