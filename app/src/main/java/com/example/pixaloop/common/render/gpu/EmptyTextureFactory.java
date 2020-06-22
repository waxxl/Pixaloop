package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.ResourceFactory;
import com.example.pixaloop.common.render.gpu.Texture;

public class EmptyTextureFactory implements ResourceFactory<Texture> {
    public  int a;
    public  int b;
    public  Texture.Type c;
    public  boolean d;

    public Texture create() {
        return new Texture(this.a, this.b, this.c, this.d);
    }
}
