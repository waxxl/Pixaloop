package com.example.pixaloop.common.render.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;


import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

import com.google.common.base.MoreObjects;
import com.google.common.io.ByteSource;
import com.google.common.io.Closeables;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.math.IntMath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ImageIO {

    public static class LoadedImage {
        public Bitmap a;
        public ImageInfo b;
        public ImageInfo c;
        public ImageInfo d;
    }

    public interface ScalingStrategy {
        double a(ImageInfo imageInfo, ImageInfo imageInfo2);
    }

    public interface SubsamplingStrategy {
        int a(int i, int i2);
    }

    @SuppressLint("RestrictedApi")
    public static SubsamplingStrategy a(final int i, final int i2, final long j) {
        boolean z = true;
        Preconditions.checkState(i > 0);
        Preconditions.checkState(i2 > 0);
        if (j <= 0) {
            z = false;
        }
        Preconditions.checkState(z);
        return new SubsamplingStrategy() {
            public int a(int i, int i2) {
                int i3 = i * i2;
                return IntMath.ceilingPowerOfTwo(Math.max(Math.max((int) Math.ceil(((double) i) / ((double) i)), (int) Math.ceil(((double) i2) / ((double) i2))), (int) Math.ceil(Math.sqrt(((double) i3) / ((double) j)))));
            }

            public String toString() {
                return MoreObjects.toStringHelper("LimitSizeStrategy").add("maxWidth", i).add("maxHeight", i2).add("maxPixels", j).toString();
            }
        };
    }

    @SuppressLint("RestrictedApi")
    public static ExifInterface b(ByteSource byteSource, File file) {
        Preconditions.checkNotNull(file);
        try {
            //byteSource.c(Files.map(file, new FileWriteMode[0]));
            return new ExifInterface(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
        return null;
    }

    @SuppressLint("RestrictedApi")
    public static ScalingStrategy a(final int i, final int i2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0);
        if (i2 <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return new ScalingStrategy() {
            public double a(ImageInfo imageInfo, ImageInfo imageInfo2) {
                int d = imageInfo2.d();
                int a2 = imageInfo2.a();
                return Math.min(MathUtils.a(Math.sqrt(((double) i2) / ((double) (d * a2))), 0.0d, 1.0d), MathUtils.a(((double) i) / ((double) Math.max(d, a2)), 0.0d, 1.0d));
            }

            public String toString() {
                return MoreObjects.toStringHelper("downscaleStrategy").add("maxDim", i).add("maxPixels", i2).toString();
            }
        };
    }

    public static ImageInfo a(ByteSource byteSource, File file) throws ImageException {
        ImageInfo a = a(byteSource);
        int d = a.d();
        int a2 = a.a();
        String b = a.b();
        ExifInterface b2 = b(byteSource, file);
        return new ImageInfo(d, a2, b, ExifInterfaceUtils.b(b2), ExifInterfaceUtils.a(b2));
    }

    public static LoadedImage a(ByteSource byteSource, @Nullable ImageInfo imageInfo, SubsamplingStrategy subsamplingStrategy, boolean z) {
        Bitmap a = a(byteSource, a(imageInfo, subsamplingStrategy, z));
        if (imageInfo == null) {
            imageInfo = ImageInfo.a(a);
        }
        LoadedImage loadedImage = new LoadedImage();
        loadedImage.a = a;
        loadedImage.b = imageInfo;
        loadedImage.c = imageInfo.a(a.getWidth(), a.getHeight());
        return loadedImage;
    }

    @SuppressLint("RestrictedApi")
    public static BitmapFactory.Options a(@Nullable ImageInfo imageInfo, SubsamplingStrategy subsamplingStrategy, boolean z) {
        Preconditions.checkNotNull(subsamplingStrategy);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inPreferQualityOverSpeed = z;
        if (imageInfo != null && imageInfo.d() > 0 && imageInfo.a() > 0) {
            options.inSampleSize = subsamplingStrategy.a(imageInfo.d(), imageInfo.a());
        }
        return options;
    }

    @SuppressLint("RestrictedApi")
    public static Bitmap a(ByteSource byteSource, BitmapFactory.Options options) {
        InputStream inputStream;
        Preconditions.checkNotNull(byteSource);
        try {
            inputStream = byteSource.openStream();
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                if (decodeStream != null) {
                    Closeables.close(inputStream, true);
                    return decodeStream;
                }
                throw new ImageException("Error loading image");
            } catch (Throwable th) {
                Closeables.close(inputStream, true);

            }
        } catch (Exception th2) {

            inputStream = null;
            try {
                Closeables.close(inputStream, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @SuppressLint("RestrictedApi")
    public static void a(LoadedImage loadedImage, ScalingStrategy scalingStrategy) {
        Preconditions.checkNotNull(loadedImage);
        Preconditions.checkNotNull(scalingStrategy);
        double a = scalingStrategy.a(loadedImage.b, loadedImage.c);
        Matrix c = loadedImage.c.c();
        float f = (float) a;
        c.postScale(f, f);
        boolean z = Math.abs((a - 1.0d) * ((double) loadedImage.c.d())) > 1.0d;
        Bitmap bitmap = loadedImage.a;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), loadedImage.a.getHeight(), c, z);
        Bitmap bitmap2 = loadedImage.a;
        if (createBitmap != bitmap2) {
            bitmap2.recycle();
            loadedImage.a = createBitmap;
        }
        loadedImage.d = loadedImage.c.a(createBitmap.getWidth(), createBitmap.getHeight()).e();
    }

    public static ImageInfo a(ByteSource byteSource) {
        InputStream inputStream;
        Throwable th;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            inputStream = byteSource.openStream();
            try {
                options.inScaled = false;
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                if (options.outWidth <= 0 || options.outHeight <= 0) {
                    throw new ImageException("Failed reading image size");
                }
                ImageInfo imageInfo = new ImageInfo(options.outWidth, options.outHeight, options.outMimeType, ImageOrientation.NORMAL, (HashMap<String, String>) null);
                Closeables.close(inputStream, true);
                return imageInfo;
            } catch (Throwable th2) {
                th = th2;
                Closeables.close(inputStream, true);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            try {
                Closeables.close(inputStream, true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
