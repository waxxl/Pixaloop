package com.example.pixaloop.util;

import android.content.Context;
import android.graphics.RectF;

import com.example.pixaloop.R;

public final class ElementUtil {
    public static RectF a(Context context, float f, float f2, float f3, float f4) {
        return RectUtil.a(RectUtil.a(new RectF(0.0f, 0.0f, f, f2), new RectF(0.0f, 0.0f, f3, f4)), DimenUtils.a(R.dimen.element_view_default_scale_relative_to_content_rect, context.getResources()));
    }
}
