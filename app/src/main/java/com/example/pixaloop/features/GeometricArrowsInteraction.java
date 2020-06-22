package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class GeometricArrowsInteraction {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(int i);

        public abstract GeometricArrowsInteraction a();

        public abstract Builder b(int i);
    }

    public static TypeAdapter<GeometricArrowsInteraction> a(Gson gson) {
        return null;
//        return new AutoValue_GeometricArrowsInteraction.GsonTypeAdapter(gson);
    }

    public  int b() {
        return 1;
    };

    public  int c() {
        return 1;
    };

    public boolean d() {
        return b() != -1;
    }

    public boolean e() {
        return d() && c() != -1;
    }

    public abstract Builder f();

    public static Builder a() {
        return null;
//        return new C$AutoValue_GeometricArrowsInteraction.Builder().a(-1).b(-1);
    }
}
