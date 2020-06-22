package com.example.pixaloop.features;

import android.graphics.Bitmap;

public class ProjectWithImage {
    public final Project a;
    public final Bitmap b;

    public ProjectWithImage(Project project, Bitmap bitmap) {
        this.a = project;
        this.b = bitmap;
    }
}
