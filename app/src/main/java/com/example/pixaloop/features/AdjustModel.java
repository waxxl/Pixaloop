package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class AdjustModel {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(float f);

        public abstract AdjustModel a();

        public abstract Builder b(float f);

        public AdjustModel b() {
            AdjustModel a = a();
            boolean z = true;
            Preconditions.checkNotNull(a.b() >= -1.0f && a.b() <= 1.0f, (Object) "brightness must be between [-1, 1]");
            Preconditions.checkNotNull(a.c() >= -1.0f && a.c() <= 1.0f, (Object) "contrast must be between [-1, 1]");
            Preconditions.checkNotNull(a.d() >= -1.0f && a.d() <= 1.0f, (Object) "saturation must be between [-1, 1]");
            if (a.e() < -1.0f || a.e() > 1.0f) {
                z = false;
            }
            Preconditions.checkNotNull(z, (Object) "temperature must be between [-1, 1]");
            return a;
        }

        public abstract Builder c(float f);

        public abstract Builder d(float f);
    }

    public float b() {
        return 1;
    };

    public float c() {
        return 1;
    };

    public float d() {
        return 1;
    };

    public float e() {
        return 1;
    };

    public abstract Builder f();

    public static Builder a() {
        return null;
        //return new C$AutoValue_AdjustModel.Builder().a(0.0f).b(0.0f).c(0.0f).d(0.0f);
    }
}
