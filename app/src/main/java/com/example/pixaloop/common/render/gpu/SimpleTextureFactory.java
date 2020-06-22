package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.ResourceFactory;
import com.example.pixaloop.common.render.gpu.Texture;

public class SimpleTextureFactory implements ResourceFactory<Texture> {
    public int a;
    public int b;
    public Texture.Type c;
    public Vector4 d;

    public Texture create() {
        Texture texture = new Texture(this.a, this.b, this.c, true);
        if (this.d != null) {
            Fbo fbo = new Fbo(texture);
            fbo.a(this.d);
            fbo.dispose();
        }
        return texture;
    }
}
