package com.example.pixaloop.features;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SkyModel {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(float f);

        public abstract Builder a(@Nullable String str);

        public abstract SkyModel a();

        public abstract Builder b(float f);

        public abstract Builder c(float f);

        public abstract Builder d(float f);

        public abstract Builder e(float f);
    }

    public static TypeAdapter<SkyModel> a(Gson gson) {
        return null;
//        return new AutoValue_SkyModel.GsonTypeAdapter(gson);
    }

    public abstract float b();

    public abstract float c();

    public abstract float d();

    public abstract Optional<String> e();

    public abstract float f();

    public abstract float g();

    public boolean h() {
        return e().isPresent();
    }

    public abstract Builder i();

    public static Builder a() {
        return null;
//        return new C$AutoValue_SkyModel.Builder().c(0.25f).a(0.5f).b(0.25f).d(1.0f).e(0.5f);
    }
}
