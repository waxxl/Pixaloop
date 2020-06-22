package com.example.pixaloop.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.pixaloop.R;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.GpuDeviceInfo;
import com.example.pixaloop.common.render.utils.ImageException;
import com.example.pixaloop.common.render.utils.ImageIO;
import com.example.pixaloop.common.render.utils.ImageInfo;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Range;
import com.google.common.io.ByteSource;
import java.io.File;
import java.util.Locale;
import java.util.Map;

import io.reactivex.rxjava3.core.SingleEmitter;

public class ImageLoader {
    public static final Map<Range<Long>, Integer> a = new ImmutableMap.Builder().put(Range.atMost(102400L), Integer.valueOf(1048576)).put(Range.atMost(307200L), 2097152).put(Range.atMost(409600L), 4194304).put(Range.atMost(716800L), 5242880).put(Range.atMost(1024000L), 8388608).put(Range.all(), 15728640).build();


    public static int b() {
        GpuDeviceInfo c = RenderEngine.b().c();
        return Math.min(Math.min(c.h(), c.m()), Math.min(c.h(), c.n()));
    }

    public static int c(Context context) {
        GpuDeviceInfo c = RenderEngine.b().c();
        long b = MemoryInfo.c().b();
        int a2 = b > 0 ? a(b) : Integer.MAX_VALUE;
        int o = c.o();
        LogU.a("ImageLoader", String.format(Locale.US, "RAM limit: %.02f Mp, GPU limit: %.02f Mp, Total RAM: %.02f Gb", new Object[]{Double.valueOf(((double) a2) / 1048576.0d), Double.valueOf(((double) o) / 1048576.0d), Double.valueOf(((double) b) / 1048576.0d)}));
        int b2 = b(context);
        LogU.a("ImageLoader", String.format(Locale.US, "Config limit: %.02f Mp", new Object[]{Double.valueOf(((double) b2) / 1048576.0d)}));
        return Math.min(Math.min(a2, o), b2);
    }


    public static Bitmap a(Bitmap bitmap, Context context) {
        ImageInfo a2 = ImageInfo.a(bitmap);
        ImageIO.LoadedImage loadedImage = new ImageIO.LoadedImage();
        loadedImage.a = bitmap;
        loadedImage.b = a2;
        loadedImage.c = a2;
        ImageIO.a(loadedImage, a(context));
        return loadedImage.a;
    }

    public static int b(Context context) {
        int integer = context.getResources().getInteger(R.integer.image_size_limit_in_mp);
        if (integer > 0) {
            return integer * 1024 * 1024;
        }
        return Integer.MAX_VALUE;
    }

    public static Bitmap a(Context context, ByteSource byteSource, ImageIO.SubsamplingStrategy subsamplingStrategy, ImageIO.ScalingStrategy scalingStrategy) {
        File a2 = Storage.a(context, "import", (String) null);
        LogU.a("ImageLoader", "Inspecting image: " + byteSource);
        ImageInfo a3 = null;
        try {
            a3 = ImageIO.a(byteSource, a2);
        } catch (ImageException e) {
            e.printStackTrace();
        }
        LogU.a("ImageLoader", "Image is " + a3);
        LogU.a("ImageLoader", "Loading " + byteSource);
        ImageIO.LoadedImage a4 = ImageIO.a(byteSource, a3, subsamplingStrategy, true);
        LogU.a("ImageLoader", "Image loaded: " + a4.c);
        ImageIO.a(a4, scalingStrategy);
        LogU.a("ImageLoader", "Image transformed: " + a4.d);
        return a4.a;
    }

    public static ImageIO.SubsamplingStrategy a() {
        int b = b() * 3;
        long j = (long) b;
        return ImageIO.a(b, b, j * j);
    }

    public static ImageIO.ScalingStrategy a(Context context) {
        return ImageIO.a(b(), c(context));
    }

    public static int a(long j) {
        int i = Integer.MAX_VALUE;
        for (Map.Entry next : a.entrySet()) {
            if (((Range) next.getKey()).contains(Long.valueOf(j))) {
                i = Math.min(i, ((Integer) next.getValue()).intValue());
            }
        }
        return i;
    }
}
