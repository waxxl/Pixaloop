package com.example.pixaloop.remoteResources.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AssetDescriptor {
    @SerializedName("asset_type")
    public AssetType assetType;
    @SerializedName("id")
    public String id;
    @SerializedName("thumbnail_url")
    public String thumbnailUrl;
    @SerializedName("videos")
    public List<Video> videos;

    public enum AssetType {
        VIDEO
    }

    public enum BlendMode {
        SCREEN,
        NORMAL
    }

    public static class Video {
        @SerializedName("blend_mode")
        public BlendMode blendMode;
        @SerializedName("chroma_key_color")
        public String chromaKeyColor;
        @SerializedName("height")
        public Integer height;
        @SerializedName("representative_frame_time")
        public Double representativeFrameTime;
        @SerializedName("url")
        public String url;
        @SerializedName("width")
        public Integer width;
    }
}
