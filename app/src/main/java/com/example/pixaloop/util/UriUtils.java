package com.example.pixaloop.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;

public class UriUtils {
    public static Uri a(@NonNull Context context, @AnyRes int i) {
        Resources resources = context.getResources();
        return new Uri.Builder().scheme("android.resource").authority(resources.getResourcePackageName(i)).appendPath(resources.getResourceTypeName(i)).appendPath(resources.getResourceEntryName(i)).build();
    }
}
