package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import javax.annotation.Nullable;

@AutoValue
public abstract class CameraFxModel {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(@Nullable String str);

        public abstract CameraFxModel a();
    }

    public abstract Optional<String> b();

    public boolean c() {
        return b().isPresent();
    }

    public abstract Builder d();

    public static Builder a() {
        return null;
        //return new C$AutoValue_CameraFxModel.Builder();
    }
}
