//package com.example.pixaloop.features;
//
//import dagger.internal.Factory;
//import javax.inject.Provider;
//
//public final class ProFeaturesDetector_Factory implements Factory<ProFeaturesDetector> {
//    public final Provider<ProFeaturesConfiguration> a;
//
//    public ProFeaturesDetector_Factory(Provider<ProFeaturesConfiguration> provider) {
//        this.a = provider;
//    }
//
//    public static ProFeaturesDetector_Factory a(Provider<ProFeaturesConfiguration> provider) {
//        return new ProFeaturesDetector_Factory(provider);
//    }
//
//    public ProFeaturesDetector get() {
//        return new ProFeaturesDetector(this.a.get());
//    }
//}
