package com.example.pixaloop.render;

import android.content.Context;
import android.graphics.Color;
import android.util.Size;

import com.example.pixaloop.remoteResources.RemoteAssetsManager;
import com.example.pixaloop.remoteResources.VideoAssetInfo;
import com.example.pixaloop.remoteResources.model.AssetDescriptor;
import com.example.pixaloop.util.VideoUtil;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;

//@Singleton
public class OverlayInfoProvider {
    public static final int a = Color.rgb(0, 0, 0);
    public static final Map<String, Size> b = new HashMap();
    public static final HashMap<String, OverlayInfo> c = new HashMap<>();
    public static final ImmutableMap<String, OverlayInfo> d = new ImmutableMap.Builder<String, OverlayInfo>()
            .put("overlay_es01", OverlayInfo.a("overlays/ES09.mp4", 6, false, 0))
            .put("overlay_es02", OverlayInfo.a("overlays/ES08.mp4", 6, false, 0))
            .put("overlay_es03", OverlayInfo.a("overlays/SK05.mp4", 6, false, 0))
            .put("overlay_es04", OverlayInfo.a("overlays/ES05.mp4", 6, false, 0))
            .put("overlay_es05", OverlayInfo.a("overlays/ES04.mp4", 6, false, 0))
            .put("overlay_es06", OverlayInfo.a("overlays/ES02.mp4", 6, false, 0))
            .put("overlay_es07", OverlayInfo.a("overlays/ES15.mp4", 6, false, 0))
            .put("overlay_es08", OverlayInfo.a("overlays/ES06.mp4", 6, false, 0))
            .put("overlay_gc01", OverlayInfo.a("overlays/GC01.mp4", 6, false, 0))
            .put("overlay_gc02", OverlayInfo.a("overlays/GC02.mp4", 6, false, 0))
            .put("overlay_gc03", OverlayInfo.a("overlays/GC03.mp4", 6, false, 0))
            .put("overlay_gc04", OverlayInfo.a("overlays/GC04.mp4", 6, false, 0)).put("overlay_gc05", OverlayInfo.a("overlays/GC05.mp4", 6, false, 0)).put("overlay_gc06", OverlayInfo.a("overlays/GC06.mp4", 6, false, 0)).put("overlay_gc07", OverlayInfo.a("overlays/ES03.mp4", 6, false, 0)).put("overlay_sp01", OverlayInfo.a("overlays/SP01.mp4", 6, false, 0)).put("overlay_sp02", OverlayInfo.a("overlays/SP02.mp4", 6, false, 0)).put("overlay_sp03", OverlayInfo.a("overlays/SP03.mp4", 6, false, 0)).put("overlay_sp04", OverlayInfo.a("overlays/SP04.mp4", 6, false, 0)).put("overlay_sp05", OverlayInfo.a("overlays/SP05.mp4", 6, false, 0)).put("overlay_sp06", OverlayInfo.a("overlays/SP06.mp4", 6, false, 0)).put("overlay_sp07", OverlayInfo.a("overlays/SP07.mp4", 6, false, 0)).put("overlay_sp08", OverlayInfo.a("overlays/ES11.mp4", 6, false, 0))
            .put("overlay_lf01", OverlayInfo.a("overlays/LF01.mp4", 6, false, 0)).put("overlay_lf02", OverlayInfo.a("overlays/LF02.mp4", 6, false, 0)).put("overlay_lf03", OverlayInfo.a("overlays/LF03.mp4", 6, false, 0)).put("overlay_lf04", OverlayInfo.a("overlays/LF04.mp4", 6, false, 0)).put("overlay_lf05", OverlayInfo.a("overlays/LF05.mp4", 6, false, 0)).put("overlay_lf06", OverlayInfo.a("overlays/ES13.mp4", 6, false, 0)).put("overlay_lf07", OverlayInfo.a("overlays/ES12.mp4", 6, false, 0)).put("overlay_wt01", OverlayInfo.a("overlays/WT01.mp4", 6, false, 0)).put("overlay_wt02", OverlayInfo.a("overlays/WT02.mp4", 6, false, 0)).put("overlay_wt03", OverlayInfo.a("overlays/WT03.mp4", 6, false, 0)).put("overlay_wt04", OverlayInfo.a("overlays/WT04.mp4", 6, false, 0)).put("overlay_wt05", OverlayInfo.a("overlays/WT05.mp4", 6, false, 0))
            .put("overlay_wt06", OverlayInfo.a("overlays/WT06.mp4", 6, false, 0)).put("overlay_wt07", OverlayInfo.a("overlays/WT07.mp4", 6, false, 0)).put("overlay_wt08", OverlayInfo.a("overlays/WT08.mp4", 6, false, 0)).put("overlay_st01", OverlayInfo.a("overlays/ST01.mp4", 0, true, a)).put("overlay_st02", OverlayInfo.a("overlays/ST02.mp4", 0, true, a)).put("overlay_st03", OverlayInfo.a("overlays/ST03.mp4", 0, true, a)).put("overlay_st04", OverlayInfo.a("overlays/ST04.mp4", 0, true, a)).put("overlay_st05", OverlayInfo.a("overlays/ST05.mp4", 0, true, a)).put("overlay_st06", OverlayInfo.a("overlays/ST06.mp4", 0, true, a)).put("element_es01", OverlayInfo.a("elements/EL_ES01.mp4", 0, true, -16711936)).put("element_es02", OverlayInfo.a("elements/EL_ES02.mp4", 0, true, -16711936)).put("element_es03", OverlayInfo.a("elements/EL_ES03.mp4", 6, false, 0))
            .put("element_es04", OverlayInfo.a("elements/EL_ES04.mp4", 0, true, -16711936))
            .put("element_es05", OverlayInfo.a("elements/EL_ES05.mp4", 6, false, 0))
            .put("element_es06", OverlayInfo.a("elements/EL_ES06.mp4", 6, false, 0))
            .put("element_es07", OverlayInfo.a("elements/EL_ES07.mp4", 6, false, 0))
            .put("element_bt01", OverlayInfo.a("elements/EL_BT01.mp4", 0, true, -16711936))
            .put("element_bt02", OverlayInfo.a("elements/EL_BT02.mp4", 0, true, -16711936))
            .put("element_bt03", OverlayInfo.a("elements/EL_BT03.mp4", 0, true, -16711936))
            .put("element_bt04", OverlayInfo.a("elements/EL_BT04.mp4", 0, true, -16711936))
            .put("element_bt05", OverlayInfo.a("elements/EL_BT05.mp4", 0, true, -16711936))
            .put("element_bt06", OverlayInfo.a("elements/EL_BT06.mp4", 0, true, -16711936)).build();
    public final RemoteAssetsManager remoteAssetsManager;

//    @Inject
    public OverlayInfoProvider(RemoteAssetsManager remoteAssetsManager) {
        this.remoteAssetsManager = remoteAssetsManager;
    }

    public final OverlayInfo a(String str) {
        if (c.containsKey(str)) {
            return c.get(str);
        }
        VideoAssetInfo c2 = this.remoteAssetsManager.c(str);
        if (c2 == null) {
            return null;
        }
        OverlayInfo a2 = a(c2);
        c.put(str, a2);
        return a2;
    }

    public OverlayInfo b(String str) {
        OverlayInfo c2 = c(str);
        if (c2 != null) {
            return c2;
        }
        return a(str);
    }

    public final OverlayInfo c(String str) {
        return d.get(str);
    }

    public final OverlayInfo a(VideoAssetInfo videoAssetInfo) {
        int i = 0;
        boolean z = videoAssetInfo.b() != null;
        int i2 = videoAssetInfo.a() == AssetDescriptor.BlendMode.SCREEN ? 6 : 0;
        if (z) {
            i = Color.parseColor(videoAssetInfo.b());
        }
        return new OverlayInfo(videoAssetInfo.c(), OverlayInfo.StorageType.INTERNAL_STORAGE, i2, z, i);
    }

    public static Size a(Context context, String str) {
        if (b.containsKey(str)) {
            return b.get(str);
        }
        Size a2 = VideoUtil.a(context, str);
        b.put(str, a2);
        return a2;
    }
}
