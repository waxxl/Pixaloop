package com.example.pixaloop.remoteResources.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AssetsConfigurationDescriptor {
    @SerializedName("features")
    public List<FeaturePacksDescriptor> featurePacksDescriptors;
}
