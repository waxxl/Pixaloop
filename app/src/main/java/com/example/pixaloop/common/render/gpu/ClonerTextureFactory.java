package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.ResourceFactory;

public class ClonerTextureFactory implements ResourceFactory<Texture> {
    public  Texture a;

    public Texture create() {
        return this.a.b();
    }
}
