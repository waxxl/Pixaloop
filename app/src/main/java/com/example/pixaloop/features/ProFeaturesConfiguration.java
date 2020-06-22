package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableSet;

@AutoValue
public abstract class ProFeaturesConfiguration {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(ImmutableSet<String> immutableSet);

        public abstract Builder a(boolean z);

        public abstract ProFeaturesConfiguration a();

        public abstract Builder b(ImmutableSet<String> immutableSet);

        public abstract Builder b(boolean z);

        public abstract Builder c(ImmutableSet<String> immutableSet);

        public abstract Builder c(boolean z);

        public abstract Builder d(ImmutableSet<String> immutableSet);

        public abstract Builder d(boolean z);

        public abstract Builder e(boolean z);
    }

    public static Builder a() {
        return null;
//        return new AutoValue_ProFeaturesConfiguration.Builder().f(false).b(false).c(true).e(false).d(false).d(FeatureItemIDs.a).c(FeatureItemIDs.b).b(FeatureItemIDs.c).a(FeatureItemIDs.d).a(true);
    }

    public abstract ImmutableSet<String> b();

    public abstract ImmutableSet<String> c();

    public abstract ImmutableSet<String> d();

    public abstract ImmutableSet<String> e();

    public abstract boolean f();

    public abstract boolean g();

    public abstract boolean h();

    public abstract boolean i();

    public abstract boolean j();

    public abstract boolean k();
}
