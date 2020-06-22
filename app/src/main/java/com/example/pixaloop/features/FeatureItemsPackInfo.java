package com.example.pixaloop.features;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FeatureItemsPackInfo {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(@ColorRes int i);

        public abstract Builder a(@Nullable String str);

        public abstract FeatureItemsPackInfo a();
    }

    public static Builder a() {
        return null;
//        return new AutoValue_FeatureItemsPackInfo.Builder();
    }

    @ColorRes
    public abstract int b();

    @Nullable
    public abstract String c();
}
