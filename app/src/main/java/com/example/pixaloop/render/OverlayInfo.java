package com.example.pixaloop.render;

public class OverlayInfo {
    public final String a;
    public final int b;
    public final boolean c;
    public final int d;
    public final StorageType e;

    public enum StorageType {
        APPLICATION_ASSET,
        INTERNAL_STORAGE
    }

    public OverlayInfo(String str, StorageType storageType, int i, boolean z, int i2) {
        this.a = str;
        this.e = storageType;
        this.b = i;
        this.c = z;
        this.d = i2;
    }

    public static OverlayInfo a(String str, int i, boolean z, int i2) {
        return new OverlayInfo(str, StorageType.APPLICATION_ASSET, i, z, i2);
    }
}
