package com.example.pixaloop.render;

import android.graphics.Bitmap;

import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.Texture;


public class RenderingResources implements DisposableResource {
    public Texture a;
    public Texture b;

    public RenderingResources(Bitmap bitmap) {
        RenderEngine.b().check();
        this.a = new Texture(bitmap);
        b = a.b();
    }

    public void dispose() {
        RenderEngine.b().check();
        this.a.dispose();
        this.b.dispose();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
