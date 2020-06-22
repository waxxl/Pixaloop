//package com.example.pixaloop.pixaloop.nn;
//
//import android.graphics.Bitmap;
//
//import com.example.pixaloop.common.nn.NeuralNetworkModelBuilder;
//import com.example.pixaloop.tensorflow.lite.Interpreter;
//import com.google.common.base.Preconditions;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.PriorityQueue;
//import org.opencv.android.Utils;
//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.ximgproc.Ximgproc;
//
//public class SkyNNBinaryModel implements NeuralNetworkModelBuilder.NeuralNetworkModel, AutoCloseable {
//    public final Interpreter a;
//    public Mat b;
//
//    public SkyNNBinaryModel(ByteBuffer byteBuffer) {
//        Interpreter.Options options = new Interpreter.Options();
//        options.a(false);
//        this.a = new Interpreter(byteBuffer, options);
//    }
//
//    public final void a(Bitmap bitmap) {
//        Throwable th;
//        this.b = new Mat();
//        Mat mat = new Mat();
//        AutoCloseableMatWrapper autoCloseableMatWrapper = new AutoCloseableMatWrapper(mat);
//        try {
//            Utils.a(bitmap, mat);
//            Imgproc.a(mat, this.b, new Size(512.0d, 512.0d));
//            autoCloseableMatWrapper.close();
//            return;
//        } catch (Throwable th2) {
//
//        }
//    }
//
//    public NeuralNetworkModelBuilder.Mask b(Bitmap bitmap) {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        a(bitmap);
//        if (!(height == 512 && width == 512)) {
//            bitmap = b();
//        }
//        ByteBuffer a2 = Bitmaps.a(bitmap);
//        ByteBuffer a3 = Bitmaps.a(bitmap, 2);
//        this.a.a((Object) a2, (Object) a3);
//        return new SkyMask(a3, width, height, this.b);
//    }
//
//    public void close() {
//        this.a.close();
//    }
//
//    public static class SkyMask implements NeuralNetworkModelBuilder.Mask {
//        public final int a;
//        public final int b;
//        public Mat c = new Mat();
//        public List<Mat> d;
//        public double e;
//
//        public SkyMask(ByteBuffer byteBuffer, int i, int i2, Mat mat) {
//            Throwable th;
//            this.a = i;
//            this.b = i2;
//            Imgproc.a(mat, this.c, 1);
//            Mat mat2 = new Mat(Database.MAX_BLOB_LENGTH, Database.MAX_BLOB_LENGTH, CvType.v, byteBuffer);
//            AutoCloseableMatWrapper autoCloseableMatWrapper = new AutoCloseableMatWrapper(mat2);
//            try {
//                this.d = new ArrayList(2);
//                Core.a(mat2, this.d);
//                b(mat2);
//                autoCloseableMatWrapper.close();
//                return;
//            } catch (Throwable th2) {
//                th.addSuppressed(th2);
//            }
//        }
//
//        public final Mat a(Mat mat) {
//            Mat mat2 = new Mat();
//            Ximgproc.a(this.c, mat, mat2, 8, 0.001d, -1);
//            return mat2;
//        }
//
//        public final void b(Mat mat) {
//            Throwable th;
//            Mat mat2 = new Mat();
//            AutoCloseableMatWrapper autoCloseableMatWrapper = new AutoCloseableMatWrapper(mat2);
//            try {
//                mat.a(mat2, CvType.a, 255.0d);
//                PriorityQueue priorityQueue = new PriorityQueue(mat.a(), Collections.reverseOrder());
//                for (int i = 0; i < mat2.a(); i++) {
//                    int h = mat2.h() - 1;
//                    while (true) {
//                        if (h < 0) {
//                            break;
//                        } else if (mat2.b(h, i)[0] >= 127.0d) {
//                            priorityQueue.add(Double.valueOf((double) h));
//                            break;
//                        } else {
//                            h--;
//                        }
//                    }
//                }
//                int floor = (int) Math.floor(((double) priorityQueue.size()) * 0.2d);
//                double d2 = 0.0d;
//                for (int i2 = 0; i2 < floor; i2++) {
//                    d2 += ((Double) priorityQueue.poll()).doubleValue();
//                }
//                this.e = 1.0d - ((d2 / ((double) floor)) / ((double) mat2.h()));
//                autoCloseableMatWrapper.close();
//                return;
//            } catch (Throwable th2) {
//                th.addSuppressed(th2);
//            }
//            throw th;
//        }
//
//        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
//            r1 = null;
//         */
//        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
//            r1 = move-exception;
//         */
//        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
//            r8 = r1;
//            r1 = r0;
//            r0 = r8;
//         */
//        /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
//            r0 = th;
//         */
//        public Texture c() {
//            Throwable th;
//            Throwable th2;
//            Mat b2 = b();
//            Mat mat = new Mat();
//            AutoCloseableMatWrapper autoCloseableMatWrapper = new AutoCloseableMatWrapper(b2);
//            try {
//                AutoCloseableMatWrapper autoCloseableMatWrapper2 = new AutoCloseableMatWrapper(mat);
//                b2.a(mat, CvType.a, 255.0d);
//                Texture texture = new Texture(Texture.Type.R8Unorm, mat);
//                autoCloseableMatWrapper2.close();
//                autoCloseableMatWrapper.close();
//                return texture;
//                throw th2;
//                if (th != null) {
//                    autoCloseableMatWrapper2.close();
//                } else {
//                    autoCloseableMatWrapper2.close();
//                }
//                throw th2;
//                throw th;
//            } catch (Throwable th3) {
//                Throwable th4 = th3;
//                try {
//                    throw th4;
//                } catch (Throwable th5) {
//                    th4.addSuppressed(th5);
//                }
//            }
//        }
//
//        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
//            r3 = th;
//         */
//        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
//            r3 = th;
//         */
//        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
//            r1 = null;
//         */
//        public final Mat a(int i) {
//            Preconditions.a(i, 2);
//            Mat mat = this.d.get(i);
//            AutoCloseableMatWrapper autoCloseableMatWrapper = new AutoCloseableMatWrapper(mat);
//            Mat a2 = a(mat);
//            autoCloseableMatWrapper.close();
//            return a2;
//            throw th;
//            if (r1 != null) {
//                try {
//                    autoCloseableMatWrapper.close();
//                } catch (Throwable th) {
//                    r1.addSuppressed(th);
//                }
//            } else {
//                autoCloseableMatWrapper.close();
//            }
//            throw th;
//        }
//
//        public float a() {
//            return (float) this.e;
//        }
//
//        public Mat b() {
//            return a(0);
//        }
//    }
//
//    public final Bitmap b() {
//        Bitmap createBitmap = Bitmap.createBitmap(Database.MAX_BLOB_LENGTH, Database.MAX_BLOB_LENGTH, Bitmap.Config.ARGB_8888);
//        Utils.a(this.b, createBitmap);
//        return createBitmap;
//    }
//}
