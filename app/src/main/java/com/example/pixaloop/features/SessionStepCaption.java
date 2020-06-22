package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SessionStepCaption {
    public static SessionStepCaption a(String str) {
        return null;
//        return new AutoValue_SessionStepCaption(str);
    }

    public abstract String a();

    public static TypeAdapter<SessionStepCaption> a(Gson gson) {
        return null;
//        return new AutoValue_SessionStepCaption.GsonTypeAdapter(gson);
    }
}
