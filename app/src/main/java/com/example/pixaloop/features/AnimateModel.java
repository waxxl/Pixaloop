package com.example.pixaloop.features;

import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.ArrayList;
import java.util.Collection;

@AutoValue
public abstract class AnimateModel {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder a(float f);

        public abstract Builder a(ImmutableList<PointF> immutableList);

        public abstract Builder a(MotionType motionType);

        public abstract Builder a(GeometricArrowsInteraction geometricArrowsInteraction);

        public abstract AnimateModel a();

        public abstract Builder b(ImmutableList<PathArrowModel> immutableList);

        public AnimateModel b() {
            AnimateModel a = a();
            Preconditions.checkNotNull(a.i() >= 0.0f && a.i() <= 1.0f, (Object) "speed must be between [0, 1]");
            return a;
        }

        public abstract Builder c(ImmutableList<StrokeData> immutableList);

        public abstract Builder d(ImmutableList<ImmutableList<PointF>> immutableList);
    }


    public enum MotionType {
        LOOP,
        BOUNCE,
        SEAMLESS_LOOP
    }

    public abstract ImmutableList<PointF> b();

    public AnimateModel b(PathArrowModel pathArrowModel) {
        ArrayList arrayList = new ArrayList(c());
        arrayList.remove(arrayList.size() - 1);
        arrayList.add(pathArrowModel);
        return j().b(ImmutableList.copyOf(arrayList)).b();
    }

    public abstract ImmutableList<PathArrowModel> c();

    public AnimateModel c(PathArrowModel pathArrowModel) {
        return j().b(new ImmutableList.Builder().add(c()).add((Object) pathArrowModel).build()).b();
    }

    public abstract ImmutableList<StrokeData> d();

    public abstract ImmutableList<ImmutableList<PointF>> e();

    public abstract GeometricArrowsInteraction f();

    public abstract MotionType g();

    public ImmutableList<ImmutableList<PointF>> h() {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<ImmutableList<PointF>> it = e().iterator();
        while (it.hasNext()) {
            ImmutableList next = it.next();
            if (next.size() > 1) {
                arrayList.add(next);
            }
        }
        return ImmutableList.copyOf(arrayList);
    }

    public abstract float i();

    public abstract Builder j();

    public static double a(int i, int i2, float f) {
        double d = (double) i;
        double d2 = (double) i2;
        return d / Math.ceil((((double) f) + (1.0d / d2)) * d2);
    }

    public AnimateModel a(PointF pointF) {
        return j().a((ImmutableList<PointF>) new ImmutableList.Builder().add(b()).add((Object) pointF).build()).b();
    }

    public AnimateModel a(int i, PointF pointF) {
        Preconditions.checkNotNull(i < b().size());
        ArrayList arrayList = new ArrayList(b());
        arrayList.remove(i);
        arrayList.add(i, pointF);
        return j().a((ImmutableList<PointF>) ImmutableList.copyOf(arrayList)).b();
    }

    public AnimateModel b(ImmutableList<PointF> immutableList) {
        ImmutableList a = new ImmutableList.Builder().add(e()).add((Object) immutableList).build();
        return j().d(a).a(GeometricArrowsInteraction.a().a(a.size() - 1).b(immutableList.size() - 1).a()).b();
    }

    public AnimateModel a(GeometricArrowsInteraction geometricArrowsInteraction) {
        return j().a(geometricArrowsInteraction).b();
    }

    public AnimateModel a(PointF pointF, int i) {
        ArrayList arrayList = new ArrayList(e());
        ImmutableList a = new ImmutableList.Builder().add((ImmutableList) arrayList.remove(i)).add((Object) pointF).build();
        arrayList.add(i, a);
        return j().d(new ImmutableList.Builder().add(arrayList).build()).a(f().f().b(a.size() - 1).a()).b();
    }

    public AnimateModel b(PointF pointF) {
        ArrayList arrayList = new ArrayList(b());
        arrayList.remove(pointF);
        return j().a((ImmutableList<PointF>) ImmutableList.copyOf(arrayList)).b();
    }

    public AnimateModel a(int i, int i2, PointF pointF) {
        ArrayList arrayList = new ArrayList(e());
        ArrayList arrayList2 = new ArrayList((Collection) arrayList.get(i));
        arrayList2.remove(i2);
        arrayList2.add(i2, pointF);
        arrayList.remove(i);
        arrayList.add(i, ImmutableList.copyOf(arrayList2));
        return j().d(ImmutableList.copyOf(arrayList)).b();
    }

    public AnimateModel a(StrokeData strokeData) {
        ArrayList arrayList = new ArrayList(d());
        arrayList.add(strokeData);
        return j().c(ImmutableList.copyOf(arrayList)).b();
    }

    public AnimateModel a(PathArrowModel pathArrowModel) {
        ArrayList arrayList = new ArrayList(c());
        arrayList.remove(pathArrowModel);
        return j().b(ImmutableList.copyOf(arrayList)).b();
    }

    public AnimateModel a(ImmutableList<PointF> immutableList) {
        ArrayList arrayList = new ArrayList(e());
        arrayList.remove(immutableList);
        return j().d(ImmutableList.copyOf(arrayList)).a(GeometricArrowsInteraction.a().a()).b();
    }

    public static Builder a() {
        return null;
//        return new C$AutoValue_AnimateModel.Builder().a(MotionType.SEAMLESS_LOOP).a(0.0f).a((ImmutableList<PointF>) ImmutableList.o()).b(ImmutableList.o()).d(ImmutableList.o()).c(ImmutableList.o()).a(GeometricArrowsInteraction.a().a());
    }
}
