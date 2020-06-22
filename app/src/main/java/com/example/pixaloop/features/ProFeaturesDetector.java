//package com.example.pixaloop.features;
//
//import android.graphics.PointF;
//import com.google.common.base.Optional;
//import com.google.common.collect.ImmutableList;
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//@Singleton
//public class ProFeaturesDetector {
//    public final ProFeaturesConfiguration a;
//
//    @Inject
//    public ProFeaturesDetector(ProFeaturesConfiguration proFeaturesConfiguration) {
//        this.a = proFeaturesConfiguration;
//    }
//
//    public static boolean b(SessionState sessionState, ProFeaturesConfiguration proFeaturesConfiguration) {
//        ImmutableList<ImmutableList<PointF>> e;
//        ImmutableList<PointF> b;
//        ImmutableList<PathArrowModel> c;
//        if (proFeaturesConfiguration.j() && (c = sessionState.c().c()) != null && c.size() > 0) {
//            return true;
//        }
//        if (proFeaturesConfiguration.g() && (b = sessionState.c().b()) != null && b.size() > 0) {
//            return true;
//        }
//        if (proFeaturesConfiguration.h() && (e = sessionState.c().e()) != null && e.size() > 0) {
//            return true;
//        }
//        AnimateModel b2 = AnimateModel.a().b();
//        if (proFeaturesConfiguration.k() && b2.i() != sessionState.c().i()) {
//            return true;
//        }
//        if (!proFeaturesConfiguration.i() || b2.g() == sessionState.c().g()) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean c(SessionState sessionState, ProFeaturesConfiguration proFeaturesConfiguration) {
//        Optional<String> b = sessionState.d().b();
//        return b.c() && proFeaturesConfiguration.b().contains(b.b());
//    }
//
//    public static boolean d(SessionState sessionState, ProFeaturesConfiguration proFeaturesConfiguration) {
//        ImmutableList<OverlayItemModel> b = sessionState.e().b();
//        if (b == null || b.size() == 0) {
//            return false;
//        }
//        return proFeaturesConfiguration.c().contains(b.get(0).c());
//    }
//
//    public static boolean e(SessionState sessionState, ProFeaturesConfiguration proFeaturesConfiguration) {
//        ImmutableList<OverlayItemModel> b = sessionState.f().b();
//        if (b == null || b.size() == 0) {
//            return false;
//        }
//        return proFeaturesConfiguration.d().contains(b.get(0).c());
//    }
//
//    public static boolean f(SessionState sessionState, ProFeaturesConfiguration proFeaturesConfiguration) {
//        Optional<String> e = sessionState.g().e();
//        return e.c() && proFeaturesConfiguration.e().contains(e.b());
//    }
//
//    public boolean a(SessionState sessionState) {
//        if (!b(sessionState, this.a) && !f(sessionState, this.a) && !c(sessionState, this.a) && !e(sessionState, this.a) && !d(sessionState, this.a) && !a(sessionState, this.a)) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean a(SessionState sessionState, ProFeaturesConfiguration proFeaturesConfiguration) {
//        if (!proFeaturesConfiguration.f()) {
//            return false;
//        }
//        return !AdjustModel.a().b().equals(sessionState.b());
//    }
//}
