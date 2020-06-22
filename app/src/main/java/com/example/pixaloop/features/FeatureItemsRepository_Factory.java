//package com.example.pixaloop.features;
//
//import android.content.Context;
//import com.lightricks.pixaloop.remoteResources.RemoteAssetsManager;
//import dagger.internal.Factory;
//import javax.inject.Provider;
//
//public final class FeatureItemsRepository_Factory implements Factory<FeatureItemsRepository> {
//    public final Provider<ProFeaturesConfiguration> a;
//    public final Provider<RemoteAssetsManager> b;
//    public final Provider<Context> c;
//
//    public FeatureItemsRepository_Factory(Provider<ProFeaturesConfiguration> provider, Provider<RemoteAssetsManager> provider2, Provider<Context> provider3) {
//        this.a = provider;
//        this.b = provider2;
//        this.c = provider3;
//    }
//
//    public static FeatureItemsRepository_Factory a(Provider<ProFeaturesConfiguration> provider, Provider<RemoteAssetsManager> provider2, Provider<Context> provider3) {
//        return new FeatureItemsRepository_Factory(provider, provider2, provider3);
//    }
//
//    public FeatureItemsRepository get() {
//        return new FeatureItemsRepository(this.a.get(), this.b.get(), this.c.get());
//    }
//}
