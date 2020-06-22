package com.example.pixaloop.features;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SessionStep {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(SessionState sessionState);

        public abstract Builder a(@Nullable SessionStepCaption sessionStepCaption);

        public abstract SessionStep a();
    }

    public static TypeAdapter<SessionStep> a(Gson gson) {
        return null;
//        return new AutoValue_SessionStep.GsonTypeAdapter(gson);
    }

    public abstract SessionState b();

    @Nullable
    public abstract SessionStepCaption c();

    public boolean d() {
        return c() == null;
    }

    public static Builder a() {
        return null;
//        return new C$AutoValue_SessionStep.Builder().a(SessionState.a().c());
    }
}
