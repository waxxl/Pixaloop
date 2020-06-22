package com.example.pixaloop.features;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class EditModel {

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder a(Project project);

        public abstract Builder a(@Nullable String str);

        public abstract EditModel a();

        public abstract Project.Builder b();
    }

    public static Builder a() {
        return null;
//        return new AutoValue_EditModel.Builder();
    }

    @Nullable
    public abstract String b();

    public abstract Project d();

    public abstract Builder e();

    public Builder a(SessionStep sessionStep) {
        Builder e = e();
        Project d = d();
        Project.Builder a = e.b().a(sessionStep);
        if (sessionStep.c() != null) {
            int c = d.c() + 1;
            a.a(c).b(c);
        }
        return e;
    }
}
