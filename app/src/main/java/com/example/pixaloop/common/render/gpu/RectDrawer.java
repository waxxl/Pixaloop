package com.example.pixaloop.common.render.gpu;

import android.graphics.RectF;
import com.example.pixaloop.common.render.DisposableResource;

public class RectDrawer implements DisposableResource {
    public final Shader a = new Shader("varying highp vec2 vTexcoord;\n\nuniform highp mat4 modelview;\nuniform highp mat4 projection;\nuniform highp mat3 textureTransform;\n\nattribute highp vec4 position;\nattribute highp vec2 texcoord;\n\nvoid main() {\n  gl_Position = projection * modelview * position;\n  vTexcoord = (textureTransform * vec3(texcoord, 1.0)).xy;\n}", "varying highp vec2 vTexcoord;\nuniform lowp sampler2D texture;\n\nvoid main() {\n  gl_FragColor = texture2D(texture, vTexcoord);\n}");
    public long b;

    public RectDrawer(Texture texture) {
        this.b = nativeCreate(this.a.c(), texture.h(), texture.l(), texture.i());
        if (this.b == 0) {
            throw new RuntimeException();
        }
    }

    public static native long nativeCreate(int i, int i2, int i3, int i4);

    public static native void nativeDestroy(long j);

    public static native void nativeDraw(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8);

    public static native void nativeSetProjection(long j, float[] fArr);

    public void a(float[] fArr) {
        if (fArr.length == 16) {
            nativeSetProjection(this.b, fArr);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void dispose() {
        long j = this.b;
        if (j != 0) {
            nativeDestroy(j);
            this.b = 0;
        }
    }

    public void a(RectF rectF, RectF rectF2) {
        nativeDraw(this.b, rectF.left, rectF.top, rectF.right, rectF.bottom, rectF2.left, rectF2.top, rectF2.right, rectF2.bottom);
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
