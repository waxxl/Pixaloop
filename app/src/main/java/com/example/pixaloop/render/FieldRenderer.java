package com.example.pixaloop.render;

import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.renderscript.Matrix4f;
import android.util.Log;

import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Fbo;
import com.example.pixaloop.common.render.gpu.Shader;
import com.example.pixaloop.common.render.gpu.Texture;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.List;

import org.opencv.core.MatOfFloat6;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Subdiv2D;

public class FieldRenderer implements DisposableResource {
    public final int a;
    public final int b;
    public final Shader c;
    public final Texture d;
    public Fbo e;

    public FieldRenderer(int i, int i2, Shader shader) {
        this.c = shader;
        this.a = i;
        this.b = i2;
        this.d = new Texture(Math.max(Math.round(((float) i) * 0.125f), 1), Math.max(Math.round(((float) i2) * 0.125f), 1), Texture.Type.RGBA8Unorm, true, 9729);
        e = new Fbo(d);
    }

    public void a(List<Arrow> list2) {
        MatOfFloat6 matOfFloat6;
        MatOfFloat6 matOfFloat62;
        MatOfFloat6 matOfFloat63;
        Subdiv2D subdiv2D = new Subdiv2D();
        Log.d("xxl", "a: " + a + " b : " + b);
        Rect rect = new Rect(new Point(-10.0d, -10.0d), new Point((double) (this.a + 10), (double) (this.b + 10)));
        subdiv2D.initDelaunay(rect);
        list2.addAll(a());
        HashMap<Integer, Point> a2 = a(list2, subdiv2D, rect);
        try {
            matOfFloat62 = new MatOfFloat6();
            try {
                matOfFloat6 = new MatOfFloat6();
            } catch (Throwable th) {
                th = th;
                matOfFloat6 = null;
                if (matOfFloat62 != null) {
                    matOfFloat62.release();
                }
                if (matOfFloat6 != null) {
                    matOfFloat6.release();
                }
                throw th;
            }
            try {
                subdiv2D.getTriangleList(matOfFloat6);
                int i = 0;
                int i2 = 0;
                while (i2 < matOfFloat6.rows()) {
                    float[] fArr = new float[6];
                    fArr[i] = 0.0f;
                    fArr[1] = 0.0f;
                    fArr[2] = 0.0f;
                    fArr[3] = 0.0f;
                    fArr[4] = 0.0f;
                    fArr[5] = 0.0f;
                    float[] fArr2 = new float[6];
                    matOfFloat6.put(i2, i, fArr2);
                    int i3 = i;
                    while (i3 < 6) {
                        int i4 = i3 + 1;
                        int i5 = i2;
                        Point point = new Point((double) fArr2[i3], (double) fArr2[i4]);
                        if (rect.contains(point)) {
                            Log.d("xxl", "rect.contains(point) true");
                            Point point2 = a2.get(Integer.valueOf(subdiv2D.findNearest(point)));
                            fArr[i3] = (float) point2.x;
                            fArr[i4] = (float) point2.y;
                        } else {
                            Log.d("xxl", "rect.contains(point) false");
                        }
                        i3 += 2;
                        i2 = i5;
                    }
                    int i6 = i2;
                    try {
                        matOfFloat63 = new MatOfFloat6();
                        matOfFloat63.fromArray(fArr);
                        matOfFloat62.push_back(matOfFloat63);
                        matOfFloat63.release();
                        i2 = i6 + 1;
                        i = 0;
                    } catch (Throwable th2) {
                        matOfFloat63 = null;
                        if (matOfFloat63 != null) {
                        }
                    }
                }
                a(matOfFloat6.toArray(), matOfFloat62.toArray());
                matOfFloat62.release();
                matOfFloat6.release();
            } catch (Throwable th3) {
                if (matOfFloat62 != null) {
                }
                if (matOfFloat6 != null) {
                }
            }
        } catch (Throwable th4) {
            matOfFloat62 = null;
            matOfFloat6 = null;
            if (matOfFloat62 != null) {
            }
            if (matOfFloat6 != null) {
            }
        }
    }

    public Texture b() {
        return this.d;
    }

    public void dispose() {
        Fbo fbo = this.e;
        if (fbo != null) {
            fbo.dispose();
        }
        Texture texture = this.d;
        if (texture != null) {
            texture.dispose();
        }
    }

    public final HashMap<Integer, Point> a(List<Arrow> list, Subdiv2D subdiv2D, Rect rect) {
        HashMap<Integer, Point> hashMap = new HashMap<>();
        for (Arrow next : list) {
            Point a2 = a(next.a);
            if (rect.contains(a2)) {
                PointF pointF = next.a;
                float f = pointF.x;
                PointF pointF2 = next.b;
                hashMap.put(Integer.valueOf(subdiv2D.insert(a2)), new Point((double) ((f - pointF2.x) / ((float) this.a)), (double) ((pointF.y - pointF2.y) / ((float) this.b))));
            } else {
                Log.e("FieldRenderer", "Subdiv - point not in rect: point - (" + a2.x + "," + a2.y + "), rect - " + rect.toString());
            }
        }
        return hashMap;
    }

    public Point a(PointF pointF) {
        return new Point((double) pointF.x, (double) pointF.y);
    }

    public final List<Arrow> a() {
        return ImmutableList.of(Arrow.a(new PointF(-9.0f, -9.0f)), Arrow.a(new PointF((float) (this.a + 9), -9.0f)), Arrow.a(new PointF(-9.0f, (float) (this.b + 9))), Arrow.a(new PointF((float) (this.a + 9), (float) (this.b + 9))));
    }

    public final void a(float[] fArr, float[] fArr2) {
        float[] fArr3 = fArr;
        float[] fArr4 = fArr2;
        boolean z = true;
        Preconditions.checkNotNull(fArr3.length == fArr4.length, (Object) "Triangles coordinates length don't match directions length!");
        if (fArr3.length % 6 != 0) {
            z = false;
        }
        Preconditions.checkNotNull(z, (Object) "Array is not of triangles (i.e. set of 3 points)!");
        this.e.a();
        this.e.b();
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.loadScale(0.125f, 0.125f, 0.125f);
        this.c.c("scale", matrix4f.getArray());
        float[] fArr5 = new float[16];
        Matrix.orthoM(fArr5, 0, 0.0f, (float) this.e.f(), 0.0f, (float) this.e.d(), -1.0f, 1.0f);
        this.c.c("projection", fArr5);
        this.c.a();
        ByteBuffer order = ByteBuffer.allocateDirect(fArr3.length * 4).order(ByteOrder.nativeOrder());
        order.asFloatBuffer().put(fArr3);
        ByteBuffer order2 = ByteBuffer.allocateDirect(fArr4.length * 4).order(ByteOrder.nativeOrder());
        order2.asFloatBuffer().put(fArr4);
        int a2 = this.c.a("position");
        int a3 = this.c.a("vector");
        GLES20.glEnableVertexAttribArray(a2);
        GLES20.glEnableVertexAttribArray(a3);
        GLES20.glVertexAttribPointer(a2, 2, 5126, false, 8, order);
        GLES20.glVertexAttribPointer(a3, 2, 5126, false, 8, order2);
        GLES20.glDrawArrays(4, 0, fArr3.length / 2);
        GLES20.glDisableVertexAttribArray(a2);
        GLES20.glDisableVertexAttribArray(a3);
        this.c.d();
        this.e.c();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
