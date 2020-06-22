package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Project {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(int i);

        public abstract Builder a(SessionStep sessionStep);

        public abstract Builder a(String str);

        public abstract Project a();

        public abstract Builder b(int i);

        public abstract Builder b(String str);
    }

    public static Builder a() {
        return null;
//        return new AutoValue_Project.Builder();
    }

    public abstract SessionStep b();

    public abstract int c();

    public abstract String d();

    public abstract String e();

    public abstract int f();

    public abstract Builder g();
}
