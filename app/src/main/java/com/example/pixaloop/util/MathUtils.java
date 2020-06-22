package com.example.pixaloop.util;

import android.graphics.PointF;
import java.util.List;

public class MathUtils {
    public static double a(List<PointF> list, PointF pointF) {
        List<PointF> list2 = list;
        PointF pointF2 = pointF;
        int i = 1;
        if (list.size() < 1) {
            throw new IllegalArgumentException("Curve must contain points");
        } else if (list.size() == 1) {
            return a(list2.get(0), pointF2);
        } else {
            double d = Double.MAX_VALUE;
            while (i < list.size()) {
                int i2 = i - 1;
                int i3 = i;
                double a = a((double) list2.get(i2).x, (double) list2.get(i2).y, (double) list2.get(i).x, (double) list2.get(i).y, (double) pointF2.x, (double) pointF2.y);
                if (a < d) {
                    d = a;
                }
                i = i3 + 1;
                list2 = list;
                pointF2 = pointF;
            }
            return d;
        }
    }

    public static double b(double d, double d2, double d3, double d4) {
        return Math.sqrt(c(d, d2, d3, d4));
    }

    public static double c(double d, double d2, double d3, double d4) {
        return Math.pow(d - d3, 2.0d) + Math.pow(d2 - d4, 2.0d);
    }

    public static double d(double d, double d2, double d3, double d4) {
        return (d * d3) + (d2 * d4);
    }

    public static PointF b(PointF pointF, PointF pointF2) {
        float f = pointF2.x - pointF.x;
        float f2 = pointF2.y - pointF.y;
        float sqrt = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        return new PointF(f / sqrt, f2 / sqrt);
    }

    public static double a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d3 - d;
        double d8 = d4 - d2;
        if ((d7 * d7) + (d8 * d8) == 0.0d) {
            return b(d5, d6, d, d2);
        }
        double a = a(d5 - d, d6 - d2, d7, d8);
        return b(d5, d6, d + (d7 * a), (a * d8) + d2);
    }

    public static double a(double d, double d2, double d3, double d4) {
        return Math.max(0.0d, Math.min(1.0d, d(d, d2, d3, d4) / ((d3 * d3) + (d4 * d4))));
    }

    public static float a(float f, float f2) {
        return Math.abs(f - f2);
    }

    public static double a(PointF pointF, PointF pointF2) {
        return b((double) pointF.x, (double) pointF.y, (double) pointF2.x, (double) pointF2.y);
    }

    public static boolean a(Double d, Double d2, Double d3) {
        if (Double.isNaN(d.doubleValue())) {
            return false;
        }
        if (Double.isNaN(d2.doubleValue())) {
            if (d.doubleValue() < d3.doubleValue() || d3.isNaN()) {
                return true;
            }
            return false;
        } else if (Double.isNaN(d3.doubleValue())) {
            if (d.doubleValue() < d2.doubleValue() || d2.isNaN()) {
                return true;
            }
            return false;
        } else if (d.doubleValue() >= d2.doubleValue() || d.doubleValue() >= d3.doubleValue()) {
            return false;
        } else {
            return true;
        }
    }

    public static int a(float f, int i) {
        return a((int) Math.ceil((double) f), i);
    }

    public static int a(int i, int i2) {
        int i3 = i % i2;
        return i3 == 0 ? i : (i + i2) - i3;
    }
}
