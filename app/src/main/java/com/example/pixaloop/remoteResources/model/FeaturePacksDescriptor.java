package com.example.pixaloop.remoteResources.model;

import com.example.pixaloop.remoteResources.RemoteFeatureType;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FeaturePacksDescriptor {
    @SerializedName("feature_type")
    public RemoteFeatureType featureType;
    @SerializedName("collections")
    public List<PackDescriptor> packs;
}
