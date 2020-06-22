//package com.example.pixaloop.render;
//
//import android.content.Context;
//
//import com.example.pixaloop.util.Log;
//import com.google.common.collect.ImmutableMap;
//
//import java.io.File;
//import java.util.Map;
//
//public class CameraFxProvider {
//    public static final Map<String, String> a = ImmutableMap.b().a("camera_fx_01", "fx01").a("camera_fx_02", "fx02").a("camera_fx_03", "fx03").a("camera_fx_04", "fx04").a("camera_fx_05", "fx05").a("camera_fx_06", "fx06").a();
//    public final Context b;
//
//    public CameraFxProvider(Context context) {
//        this.b = context;
//    }
//
//    public CameraFxLayer a(String str) {
//        String str2 = a.get(str);
//        String str3 = "camera_fx" + File.separator + str2 + CrashlyticsController.SESSION_JSON_SUFFIX;
//        LottieResult<LottieComposition> b2 = LottieCompositionFactory.b(this.b, str3);
//        if (b2.a() != null) {
//            Log.a("CameraFxAssetsProvider", "Failed loading asset:" + str3, b2.a());
//            return null;
//        }
//        LottieComposition b3 = b2.b();
//        if (b3.i().size() == 0) {
//            return null;
//        } else if (b3.i().size() > 1) {
//            return null;
//        } else {
//            try {
//                return new CameraFxLayer(new LottieTransformInterpolator(b3.i().get(0)), (float) b3.a().width(), (float) b3.a().height());
//            } catch (ReflectiveOperationException e) {
//                Log.a("CameraFxAssetsProvider", "Failed creating lottie transform interpolator.", (Throwable) e);
//                return null;
//            }
//        }
//    }
//}
