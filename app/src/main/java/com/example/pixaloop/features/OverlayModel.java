package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.ArrayList;
import java.util.List;

@AutoValue
public abstract class OverlayModel {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(List<OverlayItemModel> list);

        public abstract OverlayModel a();
    }

    public static TypeAdapter<OverlayModel> a(Gson gson) {
        return null;
//        return new AutoValue_OverlayModel.GsonTypeAdapter(gson);
    }

    public abstract ImmutableList<OverlayItemModel> b();

    public abstract Builder c();

    public static Builder a() {
        return null;
//        return new C$AutoValue_OverlayModel.Builder().a(ImmutableList.o());
    }

    public OverlayModel a(OverlayItemModel overlayItemModel) {
        ArrayList arrayList = new ArrayList(b());
        arrayList.remove((OverlayItemModel) Iterables.getLast(b()));
        arrayList.add(overlayItemModel);
        return c().a(ImmutableList.copyOf(arrayList)).a();
    }
}
