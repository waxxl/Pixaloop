package com.example.pixaloop.features;

import android.graphics.PointF;

import com.example.pixaloop.other.PainterMode;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.UUID;
import javax.annotation.Nullable;

@AutoValue
public abstract class StrokeData {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(float f);

        public abstract Builder a(PointF pointF);

        public abstract Builder a(PainterMode painterMode);

        public abstract StrokeData a();

        public abstract Builder b(PointF pointF);

        public StrokeData b() {
            return a();
        }
    }

    public static TypeAdapter<StrokeData> a(Gson gson) {
        return null;
//        return new AutoValue_StrokeData.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract PointF b();

    public abstract UUID c();

    public abstract PainterMode d();

    public abstract float e();

    @Nullable
    public abstract PointF f();

    public static Builder a() {
        return null;
//        return new C$AutoValue_StrokeData.Builder().a(UUID.randomUUID()).b((PointF) null).a((PointF) null).a(PainterMode.PAINT).a(1.0f);
    }
}
