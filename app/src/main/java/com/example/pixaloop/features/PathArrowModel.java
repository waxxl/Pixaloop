package com.example.pixaloop.features;

import android.graphics.Point;
import android.graphics.PointF;
import com.google.auto.value.AutoValue;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;


@AutoValue
public  class PathArrowModel {

    @AutoValue.Builder
    public  abstract class Builder {
        public abstract Builder a(float f);

        public abstract Builder a(ImmutableList<PointF> immutableList);

        public abstract PathArrowModel a();

        public PathArrowModel b() {
            PathArrowModel a = a();
            Preconditions.checkNotNull(a.b().size() >= 2, (Object) "Valid arrow is at least 2 points long");
            return a;
        }
    }



    public  ImmutableList<PointF> b() {
        ImmutableList<PointF> list = ImmutableList.of(new PointF(10,300), new PointF(300, 10)
                , new PointF(10, 300), new PointF(100, 300), new PointF(30, 100), new PointF(30, 10), new PointF(100, 10), new PointF(20, 100), new PointF(1, -1));
        return list;
    };

    public  float c() {
        return 30.0f;
    };



}
