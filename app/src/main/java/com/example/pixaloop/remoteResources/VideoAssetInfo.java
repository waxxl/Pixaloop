package com.example.pixaloop.remoteResources;


import com.example.pixaloop.remoteResources.model.AssetDescriptor;

public class VideoAssetInfo {
    public final String a;
    public final Integer b;
    public final Integer c;
    public final AssetDescriptor.BlendMode d;
    public final Double e;
    public final String f;

    public VideoAssetInfo(String str, Integer num, Integer num2, AssetDescriptor.BlendMode blendMode, Double d2, String str2) {
        this.a = str;
        this.b = num;
        this.c = num2;
        this.d = blendMode;
        this.e = d2;
        this.f = str2;
    }

    public AssetDescriptor.BlendMode a() {
        return this.d;
    }

    public String b() {
        return this.f;
    }

    public String c() {
        return this.a;
    }
}
