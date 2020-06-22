package com.example.pixaloop.common.render.utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

public class BackgroundFactory {
    public static Mat a(int i, int i2, Scalar scalar, Scalar scalar2) {
        Mat mat = new Mat(new Size((double) i, (double) i2), CvType.CV_8UC3);
        Scalar scalar3 = new Scalar(0.0d, 0.0d, 0.0d);
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                a(((float) (i3 + i4)) / ((float) (((i - 1) + i2) - 1)), scalar, scalar2, scalar3);
                double[] dArr = scalar3.val;
                mat.put(i3, i4, dArr[0], dArr[1], dArr[2]);
            }
        }
        return mat;
    }

    public static void a(float f, Scalar scalar, Scalar scalar2, Scalar scalar3) {
        double d = (double) f;
        scalar3.val[0] = MathUtils.b(d, scalar.val[0], scalar2.val[0]);
        scalar3.val[1] = MathUtils.b(d, scalar.val[1], scalar2.val[1]);
        scalar3.val[2] = MathUtils.b(d, scalar.val[2], scalar2.val[2]);
    }
}
