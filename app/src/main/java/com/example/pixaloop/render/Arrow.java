package com.example.pixaloop.render;

import android.graphics.PointF;

public class Arrow {
    public PointF a;
    public PointF b;

    public Arrow(PointF pointF, PointF pointF2) {
        this.a = pointF;
        this.b = pointF2;
    }

    public static Arrow a(PointF pointF) {
        return new Arrow(pointF, new PointF(pointF.x, pointF.y));
    }
}
