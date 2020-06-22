package com.example.pixaloop.remoteResources.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PackDescriptor {
    @SerializedName("members")
    public List<AssetDescriptor> assetDescriptors;
    @SerializedName("available_to_countries")
    public List<String> availableToCountries;
    @SerializedName("is_pro")
    public Boolean isPro;
    @SerializedName("name")
    public String name;
    @SerializedName("relative_position")
    public RelativePosition relativePosition;
    @SerializedName("short_name")
    public String shortName;

    public enum RelativePosition {
        BEFORE_ESSENTIAL_PACK,
        AFTER_ESSENTIAL_PACK,
        BEFORE_LOCAL_PACKS,
        AFTER_LOCAL_PACKS
    }
}
