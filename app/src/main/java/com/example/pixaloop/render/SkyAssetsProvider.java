//package com.example.pixaloop.render;
//
//import androidx.annotation.DrawableRes;
//
//import com.example.pixaloop.R;
//import com.google.common.collect.ImmutableMap;
//import java.util.Map;
//import javax.annotation.Nullable;
//
//public final class SkyAssetsProvider {
//    public static final Map<String, Integer> a = ImmutableMap.b().a("sky_essential_sn01", Integer.valueOf(R.drawable.sky_sn01)).a("sky_essential_sn02", Integer.valueOf(R.drawable.sky_sn02)).a("sky_essential_sn03", Integer.valueOf(R.drawable.sky_sn03)).a("sky_blue_bl01", Integer.valueOf(R.drawable.sky_bl01)).a("sky_blue_bl02", Integer.valueOf(R.drawable.sky_bl02)).a("sky_blue_bl03", Integer.valueOf(R.drawable.sky_bl03)).a("sky_blue_bl04", Integer.valueOf(R.drawable.sky_bl04)).a("sky_dusk_du01", Integer.valueOf(R.drawable.sky_du01)).a("sky_dusk_du02", Integer.valueOf(R.drawable.sky_du02)).a("sky_dusk_du03", Integer.valueOf(R.drawable.sky_du03)).a("sky_dusk_du04", Integer.valueOf(R.drawable.sky_du04)).a("sky_dusk_du05", Integer.valueOf(R.drawable.sky_du05)).a("sky_aurora_au01", Integer.valueOf(R.drawable.sky_au01)).a("sky_aurora_au02", Integer.valueOf(R.drawable.sky_au02)).a("sky_aurora_au03", Integer.valueOf(R.drawable.sky_au03)).a("sky_aurora_au04", Integer.valueOf(R.drawable.sky_au04)).a("sky_fantasy_fa01", Integer.valueOf(R.drawable.sky_fa01)).a("sky_fantasy_fa02", Integer.valueOf(R.drawable.sky_fa02)).a("sky_fantasy_fa03", Integer.valueOf(R.drawable.sky_fa03)).a("sky_fantasy_fa04", Integer.valueOf(R.drawable.sky_fa04)).a("sky_fantasy_fa05", Integer.valueOf(R.drawable.sky_fa05)).a();
//
//    @Nullable
//    @DrawableRes
//    public static Integer a(SkyModel skyModel) {
//        if (!skyModel.h()) {
//            return null;
//        }
//        return a.get(skyModel.e().b());
//    }
//}
