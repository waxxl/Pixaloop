package com.example.pixaloop.common.render.painter;

import com.example.pixaloop.common.render.ResourceFactory;

public class SimplePainterFactory implements ResourceFactory<Painter> {
    public  PainterParams a;

    public Painter create() {
        return new Painter(this.a);
    }
}
