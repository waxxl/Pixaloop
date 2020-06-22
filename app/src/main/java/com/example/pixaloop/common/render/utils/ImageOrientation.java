package com.example.pixaloop.common.render.utils;

import android.graphics.Matrix;

public enum ImageOrientation {
    NORMAL,
    FLIP_HORIZONTAL,
    FLIP_VERTICAL,
    TRANSVERSE,
    TRANSPOSE,
    ROTATE_90,
    ROTATE_180,
    ROTATE_270;

    /* renamed from: com.example.pixaloop.common.render.utils.ImageOrientation$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static /* synthetic */ int[] a = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            a = new int[ImageOrientation.values().length];
            a[ImageOrientation.ROTATE_90.ordinal()] = 1;
            a[ImageOrientation.ROTATE_270.ordinal()] = 2;
            a[ImageOrientation.TRANSPOSE.ordinal()] = 3;
            a[ImageOrientation.TRANSVERSE.ordinal()] = 4;
            a[ImageOrientation.NORMAL.ordinal()] = 5;
            a[ImageOrientation.FLIP_HORIZONTAL.ordinal()] = 6;
            a[ImageOrientation.ROTATE_180.ordinal()] = 7;
            try {
                a[ImageOrientation.FLIP_VERTICAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public Matrix a() {
        Matrix matrix = new Matrix();
        matrix.setValues(c().e());
        return matrix;
    }

    public IsotropicTransform2D c() {
        IsotropicTransform2D f = new IsotropicTransform2D().f();
        switch (AnonymousClass1.a[ordinal()]) {
            case 1:
                f.a(-90.0f, 0.0f, 0.0f);
                break;
            case 2:
                f.a(90.0f, 0.0f, 0.0f);
                break;
            case 3:
                f.a(90.0f, 0.0f, 0.0f);
                f.b(-1.0f, 1.0f);
                break;
            case 4:
                f.a(-90.0f, 0.0f, 0.0f);
                f.b(-1.0f, 1.0f);
                break;
            case 6:
                f.b(-1.0f, 1.0f);
                break;
            case 7:
                f.b(-1.0f, -1.0f);
                break;
            case 8:
                f.b(1.0f, -1.0f);
                break;
        }
        return f;
    }
}
