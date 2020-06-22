package com.example.pixaloop.common.render.utils;

import android.animation.TypeEvaluator;

public class IsotropicTransform2DEvaluator implements TypeEvaluator<IsotropicTransform2D> {
    /* renamed from: a */
    public IsotropicTransform2D evaluate(float f, IsotropicTransform2D isotropicTransform2D, IsotropicTransform2D isotropicTransform2D2) {
        return IsotropicTransform2D.a(f, isotropicTransform2D, isotropicTransform2D2);
    }
}
