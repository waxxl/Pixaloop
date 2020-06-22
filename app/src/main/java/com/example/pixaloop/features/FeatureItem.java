package com.example.pixaloop.features;

import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import java.util.Optional;

import io.reactivex.rxjava3.core.Single;

@AutoValue
public abstract class FeatureItem {

    public enum ActivationPolicy {
        TAP_TO_ENTER,
        SELECT_AND_TAP_TO_ENTER,
        SELECT_AND_DESELECT,
        SELECT_NO_DESELECT,
        NONE
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(Uri uri);

        public abstract Builder a(ActivationPolicy activationPolicy);

        public abstract Builder a(FeatureItemSlider featureItemSlider);

        public abstract Builder a(ItemClickedHandler itemClickedHandler);

        public abstract Builder a(SelectedChildProvider selectedChildProvider);

        public abstract Builder a(@Nullable FeatureItemsPackInfo featureItemsPackInfo);

        public abstract Builder a(@DrawableRes Integer num);

        public abstract Builder a(@NonNull String str);

        public abstract Builder a(boolean z);

        public abstract FeatureItem a();

        public abstract Builder b(@DrawableRes Integer num);

        public abstract Builder b(@NonNull String str);

        public abstract Builder b(boolean z);

        public abstract String b();
    }

    @AutoValue
    public static abstract class FeatureItemSlider {

        @AutoValue.Builder
        public static abstract class Builder {
            public abstract Builder a(float f);

            public abstract Builder a(ValueProvider valueProvider);

            public abstract Builder a(ValueUpdater valueUpdater);

            public abstract FeatureItemSlider a();

            public abstract Builder b(float f);
        }

        public interface ValueProvider {
            float a(SessionState sessionState);
        }

        public interface ValueUpdater {
            @Nullable
            SessionState a(float f, SessionState sessionState);

            SessionStepCaption a(SessionState sessionState);
        }

        public static Builder a() {
            return null;
//            return new AutoValue_FeatureItem_FeatureItemSlider.Builder();
        }

        public abstract float b();

        public abstract float c();

        @NonNull
        public abstract ValueProvider d();

        @NonNull
        public abstract ValueUpdater e();
    }

    public interface ItemClickedHandler {
        @Nullable
        Single<Optional<SessionStep>> a(String str, SessionState sessionState, Resources resources);
    }

    public interface SelectedChildProvider {
        @Nullable
        String a(SessionState sessionState);
    }

    public static Builder a() {
        return  null;
//        return new AutoValue_FeatureItem.Builder().a(ActivationPolicy.TAP_TO_ENTER).a(false).b(false).a((SelectedChildProvider) new SelectedChildProvider() {
//        }).a((ItemClickedHandler) new ItemClickedHandler() {
//        });
    }

    @NonNull
    public abstract ActivationPolicy b();

    @DrawableRes
    @Nullable
    public abstract Integer c();

    @Nullable
    public abstract FeatureItemSlider d();

    @DrawableRes
    @Nullable
    public abstract Integer e();

    @NonNull
    public abstract String f();

    @NonNull
    public abstract ItemClickedHandler g();

    @Nullable
    public abstract FeatureItemsPackInfo h();

    @NonNull
    public abstract SelectedChildProvider i();

    @Nullable
    public abstract Uri k();

    @NonNull
    public abstract String l();

    public abstract boolean m();

    public abstract boolean n();
}
