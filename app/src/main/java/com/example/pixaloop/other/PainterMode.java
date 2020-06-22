package com.example.pixaloop.other;

import com.example.pixaloop.common.render.painter.Painter;

public enum PainterMode {
    PAINT(Painter.PainterMode.PAINT),
    ERASE(Painter.PainterMode.ERASE);
    
    public Painter.PainterMode b;

    /* access modifiers changed from: public */
    PainterMode(Painter.PainterMode painterMode) {
        this.b = painterMode;
    }

    public Painter.PainterMode a() {
        return this.b;
    }
}