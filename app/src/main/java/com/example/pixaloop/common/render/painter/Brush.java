package com.example.pixaloop.common.render.painter;

import com.example.pixaloop.common.render.gpu.Texture;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Brush {
    public final Mat mat;

    public Brush(Mat mat) {
        this.mat = mat;
    }

//    public static native void nativeEncodeRGChannels(long j, long j2);
//
//    public static native void nativeGaussianBrush(long j, int i, float f, float f2);

    public static  void nativeEncodeRGChannels(long j, long j2) {};

    public static  void nativeGaussianBrush(long j, int i, float f, float f2) {};

    public Texture a() {
        Mat mat = new Mat();
        this.mat.convertTo(mat, CvType.CV_8UC1, 255.0d);
        Mat b = Mat.zeros(this.mat.rows(), this.mat.cols(), CvType.CV_8UC1);
        Mat mat2 = new Mat();
        ArrayList arrayList = new ArrayList();
        arrayList.add(mat);
        arrayList.add(b);
        arrayList.add(b);
        arrayList.add(b);
        Core.merge((List<Mat>) arrayList, mat2);
        mat.release();
        b.release();
        return new Texture(Texture.Type.RGBA8Unorm, mat2);
    }

    public Texture b() {
        Mat mat = new Mat();
        nativeEncodeRGChannels(this.mat.nativeObj, mat.nativeObj);
        return new Texture(Texture.Type.RGBA8Unorm, mat);
    }

    public static Brush a(int i, float f, float f2) {
        if (i < 0) {
            throw new IllegalArgumentException("Radius must be positive");
        } else if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("Intensity must be between 0 and 1)");
        } else if (f >= 0.0f) {
            int i2 = (i * 2) + 1;
            Mat mat = new Mat(i2, i2, 5);
            nativeGaussianBrush(mat.nativeObj, i, f, f2);
            return new Brush(mat);
        } else {
            throw new IllegalArgumentException("Sigma must be positive");
        }
    }
}
