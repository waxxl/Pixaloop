package com.example.pixaloop.features;

import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class OverlayItemModel {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(float f);

        public abstract Builder a(RectF rectF);

        public abstract Builder a(String str);

        public abstract OverlayItemModel a();

        public abstract Builder b(float f);
    }

    public static TypeAdapter<OverlayItemModel> a(Gson gson) {
        return null;
//        return new AutoValue_OverlayItemModel.GsonTypeAdapter(gson);
    }

    public abstract float b();

    public abstract String c();

    public abstract float d();

    @Nullable
    public abstract RectF e();

    public abstract Builder f();

    public static Builder a() {
        return null;
//        return new C$AutoValue_OverlayItemModel.Builder().b(1.0f).a(0.0f);
    }
}
