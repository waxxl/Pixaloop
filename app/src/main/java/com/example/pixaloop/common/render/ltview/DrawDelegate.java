package com.example.pixaloop.common.render.ltview;

import android.graphics.RectF;
import com.example.pixaloop.common.render.gpu.Texture;

public interface DrawDelegate {
    void a(LTView lTView, RectF rectF);

    boolean a(LTView lTView);

    Texture b(LTView lTView);

    void b(LTView lTView, RectF rectF);

    void c(LTView lTView, RectF rectF);

    boolean c(LTView lTView);

    boolean d(LTView lTView);

    boolean d(LTView lTView, RectF rectF);
}
