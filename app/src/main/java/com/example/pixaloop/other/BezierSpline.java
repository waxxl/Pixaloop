package com.example.pixaloop.other;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BezierSpline {
    public static List<Double> a(List<Double> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        a(list, arrayList, arrayList2, arrayList3, arrayList4);
        b(arrayList5, arrayList, arrayList2, arrayList3, arrayList4);
        return arrayList5;
    }

    public static Pair<List<Double>, List<Double>> b(List<Double> list) {
        List<Double> list2 = list;
        if (list.size() < 4) {
            throw new IllegalArgumentException("Curve must contain at least 2 points. ");
        } else if (list.size() % 2 == 0) {
            if (list.size() < 8) {
                if (list.size() == 4) {
                    list2.add(2, Double.valueOf(((list2.get(0).doubleValue() * 2.0d) + list2.get(2).doubleValue()) / 3.0d));
                    list2.add(3, Double.valueOf(((list2.get(1).doubleValue() * 2.0d) + list2.get(3).doubleValue()) / 3.0d));
                    list2.add(4, Double.valueOf((list2.get(0).doubleValue() + (list2.get(2).doubleValue() * 2.0d)) / 3.0d));
                    list2.add(5, Double.valueOf((list2.get(1).doubleValue() + (list2.get(3).doubleValue() * 2.0d)) / 3.0d));
                } else if (list.size() == 6) {
                    list2.add(2, Double.valueOf((list2.get(0).doubleValue() + list2.get(2).doubleValue()) / 2.0d));
                    list2.add(3, Double.valueOf((list2.get(1).doubleValue() + list2.get(3).doubleValue()) / 2.0d));
                }
            }
            List<Double> a = a(list);
            return new Pair<>(a, a(list2, a));
        } else {
            throw new IllegalArgumentException("Curve must have even number of elements. ");
        }
    }

    public static void a(List<Double> list, List<Double> list2, List<Double> list3, List<Double> list4, List<Double> list5) {
        double d;
        List<Double> list6 = list;
        List<Double> list7 = list2;
        List<Double> list8 = list3;
        List<Double> list9 = list4;
        List<Double> list10 = list5;
        int size = (list.size() / 2) - 1;
        for (int i = 0; i < size; i++) {
            int i2 = i * 2;
            double doubleValue = list6.get(i2).doubleValue();
            double doubleValue2 = list6.get(i2 + 1).doubleValue();
            double doubleValue3 = list6.get(i2 + 2).doubleValue();
            double doubleValue4 = list6.get(i2 + 3).doubleValue();
            if (i == 0) {
                list7.add(Double.valueOf(0.0d));
                list8.add(Double.valueOf(2.0d));
                list9.add(Double.valueOf(1.0d));
                d = doubleValue + (doubleValue3 * 2.0d);
            } else if (i < size - 1) {
                list7.add(Double.valueOf(1.0d));
                list8.add(Double.valueOf(4.0d));
                list9.add(Double.valueOf(1.0d));
                d = (doubleValue * 4.0d) + (doubleValue3 * 2.0d);
                doubleValue2 *= 4.0d;
            } else {
                list7.add(Double.valueOf(2.0d));
                list8.add(Double.valueOf(7.0d));
                list9.add(Double.valueOf(0.0d));
                d = (doubleValue * 8.0d) + doubleValue3;
                doubleValue2 *= 8.0d;
                double d2 = doubleValue2 + doubleValue4;
                list10.add(Double.valueOf(d));
                list10.add(Double.valueOf(d2));
            }
            doubleValue4 *= 2.0d;
            double d22 = doubleValue2 + doubleValue4;
            list10.add(Double.valueOf(d));
            list10.add(Double.valueOf(d22));
        }
    }

    public static void b(List<Double> list, List<Double> list2, List<Double> list3, List<Double> list4, List<Double> list5) {
        int size = list2.size();
        a(list2, list3, list4, list5);
        int i = size - 1;
        double doubleValue = list5.get(i * 2).doubleValue() / list3.get(i).doubleValue();
        list.add(Double.valueOf(list5.get((size * 2) - 1).doubleValue() / list3.get(i).doubleValue()));
        list.add(Double.valueOf(doubleValue));
        b(list, list3, list4, list5);
        Collections.reverse(list);
    }

    public static void a(List<Double> list, List<Double> list2, List<Double> list3, List<Double> list4) {
        List<Double> list5 = list2;
        List<Double> list6 = list4;
        int size = list.size();
        int i = 1;
        while (i < size) {
            int i2 = i * 2;
            double doubleValue = list6.get(i2).doubleValue();
            int i3 = i2 + 1;
            double doubleValue2 = list6.get(i3).doubleValue();
            double doubleValue3 = list6.get(i2 - 2).doubleValue();
            double doubleValue4 = list6.get(i2 - 1).doubleValue();
            int i4 = size;
            int i5 = i - 1;
            double doubleValue5 = list.get(i).doubleValue() / list5.get(i5).doubleValue();
            list5.set(i, Double.valueOf(list5.get(i).doubleValue() - (list3.get(i5).doubleValue() * doubleValue5)));
            list6.set(i2, Double.valueOf(doubleValue - (doubleValue3 * doubleValue5)));
            list6.set(i3, Double.valueOf(doubleValue2 - (doubleValue5 * doubleValue4)));
            i++;
            size = i4;
        }
    }

    public static void b(List<Double> list, List<Double> list2, List<Double> list3, List<Double> list4) {
        int size = list2.size() - 2;
        for (int i = size; i >= 0; i--) {
            int i2 = i * 2;
            int i3 = (size - i) * 2;
            double doubleValue = (list4.get(i2).doubleValue() - (list3.get(i).doubleValue() * list.get(i3 + 1).doubleValue())) / list2.get(i).doubleValue();
            list.add(Double.valueOf((list4.get(i2 + 1).doubleValue() - (list3.get(i).doubleValue() * list.get(i3).doubleValue())) / list2.get(i).doubleValue()));
            list.add(Double.valueOf(doubleValue));
        }
    }

    public static List<Double> a(List<Double> list, List<Double> list2) {
        int size = list2.size() / 2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = size - 1;
            if (i < i2) {
                int i3 = i * 2;
                int i4 = i3 + 2;
                int i5 = i3 + 3;
                double doubleValue = list2.get(i4).doubleValue();
                double doubleValue2 = (list.get(i5).doubleValue() * 2.0d) - list2.get(i5).doubleValue();
                arrayList.add(Double.valueOf((list.get(i4).doubleValue() * 2.0d) - doubleValue));
                arrayList.add(Double.valueOf(doubleValue2));
                i++;
            } else {
                int i6 = size * 2;
                double doubleValue3 = list2.get(i2 * 2).doubleValue();
                double doubleValue4 = list2.get(i6 - 1).doubleValue();
                arrayList.add(Double.valueOf((list.get(i6).doubleValue() + doubleValue3) * 0.5d));
                arrayList.add(Double.valueOf((list.get(i6 + 1).doubleValue() + doubleValue4) * 0.5d));
                return arrayList;
            }
        }
    }
}