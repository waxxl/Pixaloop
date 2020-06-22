package com.example.pixaloop.Cclass;

import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.GpuDeviceInfo;
import com.google.common.base.Function;

/* renamed from: Ya  reason: default package */
/* compiled from: lambda */
public final /* synthetic */ class Ya implements Function {
    private final /* synthetic */ RenderEngine a;

    public /* synthetic */ Ya(RenderEngine renderEngine) {
        this.a = renderEngine;
    }

    public final Object apply(Object obj) {
        return this.a.a((GpuDeviceInfo) obj);
    }
}