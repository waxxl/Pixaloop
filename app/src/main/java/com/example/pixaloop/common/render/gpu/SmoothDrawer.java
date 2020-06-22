package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.RenderEngine;

public class SmoothDrawer implements DisposableResource {
    public long a = nativeCreate(new Shader("varying highp float vOffset;\nvarying highp float vIntensity;\n\nuniform highp mat4 modelview;\nuniform highp mat4 projection;\n\nattribute highp vec4 position;\nattribute highp float offset;\nattribute highp float intensity;\n\nvoid main() {\n  vec4 newpos = vec4(position.xy, 0.0, 1.0);\n  gl_Position = projection * modelview * newpos;\n  vOffset = offset;\n  vIntensity = intensity;\n}\n", "varying highp float vOffset;\nvarying highp float vIntensity;\n\nuniform lowp vec3 shadowColor;\nuniform lowp vec3 strokeColor;\nuniform highp float opacity;\nuniform highp float lineWidth;\nuniform highp float shadowRadius;\nuniform highp float blendRadius;\n\nvoid main() {\n  highp float lineRadius = lineWidth * 0.5;\n  highp vec4 target = vec4(shadowColor, 1.0 - smoothstep(lineRadius - blendRadius,\n                                                         lineRadius + shadowRadius, abs(vOffset)));\n  target.rgb = mix(strokeColor, target.rgb, smoothstep(0.0, lineRadius, abs(vOffset)));\n  target.a *= opacity * vIntensity;\n  gl_FragColor = target;\n}").c());

    @Override
    public void close() throws Exception {
        dispose();
    }

    public static class Vertex {
    }

    public static native long nativeCreate(int i);

    public static native void nativeDestroy(long j);

    public void dispose() {
        RenderEngine.b().check();
        long j = this.a;
        if (j != 0) {
            nativeDestroy(j);
            this.a = 0;
        }
    }
}
