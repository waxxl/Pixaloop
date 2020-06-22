package com.example.pixaloop.common.render.gpu;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.utils.Size;
import com.google.common.base.Preconditions;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import javax.microedition.khronos.opengles.GL10;

public class Texture implements DisposableResource {
    public int texture;
    public Type b;
    public int c;
    public int d;
    public int width;
    public int height;

    public enum Type {
        R8Unorm(5121, 6403, 33321, 1, 0),
        R16Float(5126, 6403, 33325, 4, CvType.CV_32FC1),
        R32Float(5126, 6403, 33326, 4, CvType.CV_32FC1),
        RG8Unorm(5121, 33319, 33323, 2, CvType.CV_8UC3),
        RG16Float(5126, 33319, 33327, 8, CvType.CV_32FC2),
        RG32Float(5126, 33319, 33328, 8, CvType.CV_32FC2),
        RGB8Unorm(5121, 6407, 32849, 3, CvType.CV_8UC3),
        RGB16Float(5126, 6407, 34843, 12, CvType.CV_32FC3),
        RGB32Float(5126, 6407, 34837, 12, CvType.CV_32FC3),
        RGBA8Unorm(5121, 6408, 32856, 4, CvType.CV_8UC4),
        RGBA16Float(5126, 6408, 34842, 16, CvType.CV_32FC4),
        RGBA32Float(5126, 6408, 34836, 16, CvType.CV_32FC4),
        RG32Int(5124, 33320, 33339, 16, CvType.CV_32FC2),
        Depth16Unorm(5123, 6402, 33189, 2, CvType.CV_8UC1);
        
        public final int p;
        public final int q;
        public final int r;
        public final int s;
        public final int t;

        Type(int i, int i2, int i3, int i4, int i5) {
            this.p = i;
            this.q = i2;
            this.r = i3;
            this.s = i4;
            this.t = i5;
        }
    }

    public Texture(Bitmap bitmap) {
        this.texture = d();
        a(bitmap);
    }

    public static Texture a(Texture texture) {
        return new Texture(texture.width, texture.height, texture.b, true);
    }

    public static int d() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public static native void nativeLoadMat(long j, int i, int i2, int i3);

    public static native void nativeReadPixelsToMat(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public void b(int i) {
        this.c = i;
        int f2 = f();
        a();
        GLES20.glTexParameteri(3553, 10242, i);
        GLES20.glTexParameteri(3553, 10243, i);
        GLES20.glBindTexture(3553, f2);
    }

    public final int c() {
        int i = this.width * this.height;
        Type type = this.b;
        return i * type.s * CvType.channels(type.t);
    }

    @Override
    public void close() {
        dispose();
    }

    public void dispose() {
        int i = this.texture;
        if (i != 0) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
            this.texture = 0;
        }
    }

    public synchronized RectF e() {
        return new RectF(0.0f, 0.0f, (float) this.width, (float) this.height);
    }

    public final int f() {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(32873, iArr, 0);
        return iArr[0];
    }

    public int g() {
        return this.d;
    }

    public int h() {
        return this.texture;
    }

    public synchronized int i() {
        return this.height;
    }

    public synchronized Size j() {
        return new Size(l(), i());
    }

    public Type k() {
        return this.b;
    }

    public synchronized int l() {
        return this.width;
    }

    public int m() {
        return this.c;
    }

    public Bitmap n() {
        Bitmap createBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        int i = iArr[0];
        if (i > 0) {
            GLES20.glBindFramebuffer(36160, i);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.texture, 0);
            int glCheckFramebufferStatus = GLES20.glCheckFramebufferStatus(36160);
            if (glCheckFramebufferStatus == 36053) {
                if ((createBitmap = createBitmapFromGLSurface()) != null) {
//                if (nativeReadPixelsToBitmap(0, 0, this.width, this.height, 6408, this.b.p, createBitmap) != 0) {
                    GLUtils.a();
                    GLES20.glDeleteFramebuffers(1, iArr, 0);
                    return createBitmap;
                }
                GLES20.glDeleteFramebuffers(1, iArr, 0);
                createBitmap.recycle();
                throw new RuntimeException("Failed reading from texture");
            }
            Log.e("Texture", String.format("Failed to make complete framebuffer object %x", new Object[]{Integer.valueOf(glCheckFramebufferStatus)}));
            GLES20.glDeleteFramebuffers(1, iArr, 0);
            createBitmap.recycle();
            throw new RuntimeException("Could not generate framebuffer");
        }
        createBitmap.recycle();
        throw new RuntimeException("Could not generate framebuffer");
    }

    public final native int nativeReadPixelsToBitmap(int i, int i2, int i3, int i4, int i5, int i6, Bitmap bitmap);

    public Mat o() {
        return a(new Rect(0, 0, this.width, this.height));
    }

    public final void p() {
        GLES20.glTexParameteri(3553, 10240, this.d);
        GLES20.glTexParameteri(3553, 10241, this.d);
        GLES20.glTexParameteri(3553, 10242, this.c);
        GLES20.glTexParameteri(3553, 10243, this.c);
    }

    public void q() {
        GLES20.glBindTexture(3553, 0);
    }

    public String toString() {
        return "";
        //return MoreObjects.a("Texture").a("id", this.a).a(SessionEventTransform.TYPE_KEY, (Object) this.b).a(SettingsJsonConstants.ICON_WIDTH_KEY, this.e).a(SettingsJsonConstants.ICON_HEIGHT_KEY, this.f).a("vram", (Object) String.format(Locale.US, "%.2f Mb", new Object[]{Double.valueOf(((double) c()) / 1048576.0d)})).toString();
    }

    public void a(Buffer buffer) {
        a();
        GLES30.glPixelStorei(3317, 1);
        GLES30.glPixelStorei(3314, 0);
        Type type = this.b;
        GLES20.glTexImage2D(3553, 0, type.r, this.width, this.height, 0, type.q, type.p, buffer);
        GLUtils.a();
        q();
    }

    public Texture(int i, int i2, Type type, boolean z) {
        this(i, i2, type, z, 9729);
    }

    public Texture(int i, int i2, Type type, boolean z, int i3) {
        a(i, i2);
        this.texture = d();
        this.b = type;
        synchronized (this) {
            this.width = i;
            this.height = i2;
        }
        this.c = 33071;
        this.d = i3;
        a();
        p();
        if (z) {
            GLES20.glTexImage2D(3553, 0, type.r, i, i2, 0, type.q, type.p, (Buffer) null);
        }
        q();
    }

    public Texture b() {
        Texture texture = new Texture(this.width, this.height, this.b, true);
        texture.a(this.d);
        texture.b(this.c);
        Fbo fbo = new Fbo(texture);
        TexturedRect texturedRect = new TexturedRect(this);
        int m = m();
        int g = g();
        a(9728);
        b(33071);
        fbo.a();
        texturedRect.a();
        fbo.c();
        texturedRect.dispose();
        fbo.dispose();
        a(g);
        b(m);
        return texture;
    }

    public void a(byte[] bArr) {
        ByteBuffer order = ByteBuffer.allocateDirect(bArr.length).order(ByteOrder.nativeOrder());
        order.put(bArr).position(0);
        a((Buffer) order);
    }

    public void a(Mat mat) {
        if (this.b.t == mat.type()) {
            synchronized (this) {
                this.width = mat.width();
                this.height = mat.height();
            }
            a(this.width, this.height);
            a();
            p();
            long j = mat.nativeObj;
            Type type = this.b;
            Log.d("xxl" ,"glTexImage2D");
            //nativeLoadMat(j, type.r, type.q, type.p);
            byte[] bArr = new byte[mat.rows() * mat.cols() * 4];
            mat.get(0, 0 , bArr);
            ByteBuffer order = ByteBuffer.allocateDirect(bArr.length).order(ByteOrder.nativeOrder());
            order.put(bArr).position(0);
            GLES20.glTexImage2D(3553, 0, type.r, mat.cols(), mat.rows(), 0, type.q, type.p, (Buffer)order);
            GLUtils.a();
            q();
            return;
        }
        throw new IllegalArgumentException("The type of the given mat (CvType " + mat.type() + ") doesn't fit current texture type (" + this.b + ")");
    }

    public Texture(Type type, Mat mat) {
        this.c = 33071;
        this.d = 9728;
        this.texture = d();
        this.b = type;
        a(mat);
    }

    public void a(Bitmap bitmap) {
        if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
            synchronized (this) {
                this.width = bitmap.getWidth();
                this.height = bitmap.getHeight();
            }
            this.c = 33071;
            this.d = 9728;
            this.b = Type.RGBA8Unorm;
            a(this.width, this.height);
            a();
            p();
            GLES30.glPixelStorei(3317, 1);
            GLES30.glPixelStorei(3314, 0);
            android.opengl.GLUtils.texImage2D(3553, 0, bitmap, 0);
            GLUtils.a();
            q();
            return;
        }
        throw new IllegalArgumentException();
    }

    public final void a(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Wrong dimensions (" + i + "x" + i2 + ")");
        }
        int h = RenderEngine.b().c().h();
        if (i > h || i2 > h) {
            throw new IllegalArgumentException("Dimensions too large (" + i + "x" + i2 + ", max=" + h + ")");
        }
    }

    public Mat a(Rect rect) {
        Preconditions.checkNotNull(this.texture != 0, (Object) "Non existent texture");
        Preconditions.checkNotNull(!rect.isEmpty());
        Preconditions.checkNotNull(e().contains((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom));
        Mat mat = new Mat(rect.height(), rect.width(), this.b.t);
        Fbo fbo = new Fbo(this);
        fbo.a();
        int left = rect.left;
        int top = rect.top;
        int width = rect.width();
        int height = rect.height();
        Type type = this.b;
//        nativeReadPixelsToMat(left, top, width, height, type.q, type.p, mat.nativeObj);
        mat = createBitmapFromGLSurface2(b.t);
        fbo.c();
        fbo.dispose();
        return mat;
    }

    private Mat createBitmapFromGLSurface2(int type)
            throws OutOfMemoryError {
        Bitmap bitmap = createBitmapFromGLSurface();
        Mat mat = new Mat(width,height, type);
        Utils.bitmapToMat(bitmap, mat);
        return mat;
    }

    public void a() {
        GLES20.glBindTexture(3553, this.texture);
    }

    public void a(int i) {
        this.d = i;
        int f2 = f();
        a();
        GLES20.glTexParameteri(3553, 10240, i);
        GLES20.glTexParameteri(3553, 10241, i);
        GLES20.glBindTexture(3553, f2);
    }

    private Bitmap createBitmapFromGLSurface()
            throws OutOfMemoryError {
        int[] bitmapBuffer = new int[width * height];
        int[] bitmapSource = new int[width * height];
        IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
        intBuffer.position(0);

        try {
            GLES20.glReadPixels(0, 0, width, height, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, intBuffer);
            int offset1, offset2;
            for (int i = 0; i < height; i++) {
                offset1 = i * width;
                offset2 = (height - i - 1) * width;
                for (int j = 0; j < width; j++) {
                    int texturePixel = bitmapBuffer[offset1 + j];
                    int blue = (texturePixel >> 16) & 0xff;
                    int red = (texturePixel << 16) & 0x00ff0000;
                    int pixel = (texturePixel & 0xff00ff00) | red | blue;
                    bitmapSource[offset2 + j] = pixel;
                }
            }
        } catch (Exception e) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(bitmapSource, width, height, Bitmap.Config.ARGB_8888);
        return bitmap;
    }
}
