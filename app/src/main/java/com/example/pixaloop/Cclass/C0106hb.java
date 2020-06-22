package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.RenderEngine;

public final /* synthetic */ class C0106hb implements Runnable {
    private final /* synthetic */ RenderEngine a;
    private final /* synthetic */ Object b;
    private final /* synthetic */ int c;
    private final /* synthetic */ int d;

    public /* synthetic */ C0106hb(RenderEngine renderEngine, Object obj, int i, int i2) {
        this.a = renderEngine;
        this.b = obj;
        this.c = i;
        this.d = i2;
    }

    public final void run() {
        this.a.c(this.b, this.c, this.d);
    }
}