package com.example.pixaloop.render;

import android.util.Pair;

import com.example.pixaloop.features.AnimateModel;

public class Animate {

    /* renamed from: com.lightricks.pixaloop.render.Animate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] a = new int[AnimateModel.MotionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            a[AnimateModel.MotionType.SEAMLESS_LOOP.ordinal()] = 1;
            a[AnimateModel.MotionType.BOUNCE.ordinal()] = 2;
            try {
                a[AnimateModel.MotionType.LOOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static float a(float f, AnimateModel.MotionType motionType) {
        if (motionType == AnimateModel.MotionType.SEAMLESS_LOOP) {
            return Math.abs(f);
        }
        return 0.0f;
    }

    public static Pair<Float, Float> b(float f, AnimateModel.MotionType motionType) {
        int i = AnonymousClass1.a[motionType.ordinal()];
        float f2 = 0.0f;
        if (i != 1) {
            f = i != 2 ? i != 3 ? 0.0f : (f + 1.0f) / 2.0f : f <= 0.0f ? f + 1.0f : 1.0f - f;
        } else {
            f2 = (f > 0.0f ? 1.0f : -1.0f) * (Math.abs(f) - 1.0f);
        }
        return new Pair<>(Float.valueOf(f), Float.valueOf(f2));
    }
}
