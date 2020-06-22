package com.example.pixaloop.common.render.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.pixaloop.SettingsJsonConstants;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.HashMap;
import javax.annotation.Nullable;

public class ImageInfo {
    public final int a;
    public final int b;
    public final String c;
    public final ImageOrientation d;
    public final HashMap<String, String> e;

    public ImageInfo(int i, int i2, @Nullable String str, ImageOrientation imageOrientation, HashMap<String, String> hashMap) {
        this.a = i;
        this.b = i2;
        this.c = str == null ? "" : str;
        this.d = imageOrientation;
        this.e = hashMap;
    }

    public static ImageInfo a(Bitmap bitmap) {
        Preconditions.checkNotNull(!bitmap.isRecycled(), (Object) "Recycled bitmap");
        return new ImageInfo(bitmap.getWidth(), bitmap.getHeight(), (String) null, ImageOrientation.NORMAL, Maps.<String, String>newHashMap());
    }

    public String b() {
        return this.c;
    }

    public Matrix c() {
        return this.d.a();
    }

    public int d() {
        return this.a;
    }

    public ImageInfo e() {
        HashMap hashMap = (HashMap) this.e.clone();
        hashMap.put("Orientation", "-1");
        return new ImageInfo(this.a, this.b, this.c, ImageOrientation.NORMAL, hashMap);
    }

    public String toString() {
        return MoreObjects.toStringHelper("ImageInfo").add(SettingsJsonConstants.ICON_WIDTH_KEY, this.a).add(SettingsJsonConstants.ICON_HEIGHT_KEY, this.b).add("mime", (Object) this.c).add("exifOrientation", (Object) this.d).toString();
    }

    public ImageInfo a(int i, int i2) {
        return new ImageInfo(i, i2, this.c, this.d, this.e);
    }

    public int a() {
        return this.b;
    }
}
