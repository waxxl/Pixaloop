package com.example.pixaloop.common.render.ltview;

import android.content.res.Resources;
import android.graphics.RectF;

import com.example.pixaloop.R;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Shader;
import com.example.pixaloop.common.render.utils.MathUtils;

public class PixelGrid implements DisposableResource {
    static {
        //System.loadLibrary("pixaloop");
        System.loadLibrary("native-lib");
    }
    public final float a;
    public final float b;
    public final float c = 0.0f;
    public final float d = 0.8f;
    public long e;
    public final float[] f = new float[16];
    public final RectF rectF = new RectF();
    public final LTView ltView;

    public PixelGrid(LTView lTView) {
        this.ltView = lTView;
        Resources resources = lTView.getResources();
        this.a = resources.getDimension(R.dimen.pixelgrid_scale_of_appearence);
        this.b = resources.getDimension(R.dimen.pixelgrid_scale_of_opaqueness);
        NavigationModel nextFrameNavigationModel = lTView.getNextFrameNavigationModel();
        RectF b2 = nextFrameNavigationModel.b();
        this.e =1024;
//        this.e = nativeCreate(new Shader("uniform highp mat4 modelview;\nuniform highp mat4 projection;\nuniform highp vec2 pixelSize;\nuniform highp float width;\n\nattribute highp vec4 position;\nattribute highp vec2 offset;\n\nvoid main() {\n  highp vec4 new_position = position - vec4(offset, 0.0, 0.0);\n  new_position = projection * modelview * new_position;\n  new_position.xy += offset * pixelSize * width * new_position.w;\n  gl_Position = new_position;\n}", "uniform mediump vec4 color;\n\nvoid main() {\n  gl_FragColor = color;\n}").c(), b2.left, b2.top, b2.right, b2.bottom);
//        if (this.e != 0) {
//            nativeSetProjection(this.e, nextFrameNavigationModel.e());
//            return;
//        }
//        throw new RuntimeException();
    }

    public static native long nativeCreate(int i, float f2, float f3, float f4, float f5);

    public static native void nativeDestroy(long j);

    public static native void nativeDraw(long j);

    public static native void nativeSetColor(long j, float f2, float f3, float f4, float f5);

    public static native void nativeSetFrameSize(long j, float f2, float f3);

    public static native void nativeSetModelview(long j, float[] fArr);

    public static native void nativeSetProjection(long j, float[] fArr);

    public void a(float[] fArr) {
        long j = this.e;
        if (j != 0) {
//            nativeSetProjection(j, fArr);
            return;
        }
        //throw new IllegalStateException();
    }

    public boolean b() {
        return this.ltView.getCurrentFrameNavigationModel().f() >= this.a;
    }

    @Override
    public void close() {
        dispose();
    }

    public void dispose() {
        long j = this.e;
        if (j != 0) {
            //nativeDestroy(j);
        }
        this.e = 0;
    }

    public void a() {
        if (this.e == 0) {
            throw new IllegalStateException();
        } else if (b()) {
            NavigationModel currentFrameNavigationModel = this.ltView.getCurrentFrameNavigationModel();
            float f2 = currentFrameNavigationModel.f();
            float f3 = this.a;
            float a2 = (MathUtils.a((f2 - f3) / (this.b - f3), 0.0f, 1.0f) * 0.8f) + 0.0f;
            currentFrameNavigationModel.a(this.f);
//            nativeSetColor(this.e, 0.2f, 0.2f, 0.2f, a2);
//            nativeSetModelview(this.e, this.f);
//            currentFrameNavigationModel.f(this.g);
//            nativeSetFrameSize(this.e, this.g.width(), this.g.height());
//            nativeDraw(this.e);
        }
    }
}
