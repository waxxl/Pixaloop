package com.example.pixaloop.common.render.gpu;

import android.graphics.RectF;
import android.renderscript.Matrix4f;
import android.util.Pair;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Texture;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TexturedRect implements DisposableResource {
    public static final Map<String, String> a = Maps.newHashMap();
    public static final Map<Texture.Type, String> b = Maps.newHashMap();
    public static final GpuStruct c = new GpuStruct("texturedRectVertexStruct", Lists.newArrayList(new GpuStructField[]{new GpuStructField("position", 2, 5126, false), new GpuStructField("texcoord", 2, 5126, false)}));
    public DynamicDrawer d;
    public Shader e;
    public Map<String, Texture> f;
    public Texture g;
    public RectF h;
    public RectF i;
    public Matrix4f j;
    public boolean k;
    public float[] l;

    static {
        a.put("LTTextureShader.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout lowp vec4 fragmentColor;\nuniform lowp sampler2D sourceTexture;\n\nvoid main() {\n  fragmentColor = texture(sourceTexture, vTexcoord);\n}\n");
        a.put("LTTextureShaderHalfFloat.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout mediump ivec4 fragmentColor;\nuniform mediump sampler2D sourceTexture;\n\nvoid main() {\n  mediump vec3 pixel = texture(sourceTexture, vTexcoord).rgb;\n  fragmentColor = vec4(pixel.rgb, 1.0);\n}");
        a.put("LTTextureShaderFloat.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout highp vec4 fragmentColor;\nuniform highp sampler2D sourceTexture;\n\nvoid main() {\n  highp vec3 pixel = texture(sourceTexture, vTexcoord).rgb;\n  fragmentColor = vec4(pixel.rgb, 1.0);\n}");
        a.put("LTTextureShaderHalfInt.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout mediump ivec4 fragmentColor;\nuniform mediump isampler2D sourceTexture;\n\nvoid main() {\n  mediump ivec3 pixel = texture(sourceTexture, vTexcoord).rgb;\n  fragmentColor = ivec4(pixel.rgb, 1.0);\n}");
        a.put("LTTextureShaderInt.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout highp ivec4 fragmentColor;\nuniform highp isampler2D sourceTexture;\n\nvoid main() {\n  highp ivec3 pixel = texture(sourceTexture, vTexcoord).rgb;\n  fragmentColor = ivec4(pixel.rgb, 1.0);\n}");
        a.put("LTTextureShaderUnsignedShort.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout mediump uvec4 fragmentColor;\nuniform mediump usampler2D sourceTexture;\n\nvoid main() {\n  mediump uvec3 pixel = texture(sourceTexture, vTexcoord).rgb;\n  fragmentColor = uvec4(pixel.rgb, 1.0);\n}");
        a.put("LTTextureShaderUnsignedInt.fsh", "#version 300 es\n\nin highp vec2 vTexcoord;\nout highp uvec4 fragmentColor;\nuniform highp usampler2D sourceTexture;\n\nvoid main() {\n  highp uvec3 pixel = texture(sourceTexture, vTexcoord).rgb;\n  fragmentColor = uvec4(pixel.rgb, 1.0);\n}");
        b.put(Texture.Type.R8Unorm, "LTTextureShader.fsh");
        b.put(Texture.Type.R16Float, "LTTextureShaderHalfFloat.fsh");
        b.put(Texture.Type.R32Float, "LTTextureShaderFloat.fsh");
        b.put(Texture.Type.RG8Unorm, "LTTextureShader.fsh");
        b.put(Texture.Type.RG16Float, "LTTextureShaderHalfFloat.fsh");
        b.put(Texture.Type.RG32Float, "LTTextureShaderFloat.fsh");
        b.put(Texture.Type.RGB8Unorm, "LTTextureShader.fsh");
        b.put(Texture.Type.RGB16Float, "LTTextureShaderHalfFloat.fsh");
        b.put(Texture.Type.RGB32Float, "LTTextureShaderFloat.fsh");
        b.put(Texture.Type.RGBA8Unorm, "LTTextureShader.fsh");
        b.put(Texture.Type.RGBA16Float, "LTTextureShaderHalfFloat.fsh");
        b.put(Texture.Type.RGBA32Float, "LTTextureShaderFloat.fsh");
        b.put(Texture.Type.RG32Int, "LTTextureShaderInt.fsh");
    }

    public TexturedRect(Texture texture) {
        this(texture, "sourceTexture");
    }

    public final void a(Map<String, Texture> map, String str, Shader shader) {
        this.e = shader;
        this.d = new DynamicDrawer(shader, Lists.newArrayList( new GpuStruct[]{c}));
        a(map, str);
        this.j = new Matrix4f();
    }

    public final void b(RectF rectF) {
        if (!rectF.equals(this.i)) {
            float min = Math.min(rectF.left, rectF.right);
            float max = Math.max(rectF.left, rectF.right);
            float min2 = Math.min(rectF.top, rectF.bottom);
            float max2 = Math.max(rectF.top, rectF.bottom);
            float[] fArr = this.l;
            fArr[0] = min;
            fArr[1] = min2;
            fArr[2] = min / this.h.width();
            this.l[3] = min2 / this.h.height();
            float[] fArr2 = this.l;
            fArr2[4] = max;
            fArr2[5] = min2;
            fArr2[6] = max / this.h.width();
            this.l[7] = min2 / this.h.height();
            float[] fArr3 = this.l;
            fArr3[8] = min;
            fArr3[9] = max2;
            fArr3[10] = min / this.h.width();
            this.l[11] = max2 / this.h.height();
            float[] fArr4 = this.l;
            fArr4[12] = max;
            fArr4[13] = max2;
            fArr4[14] = max / this.h.width();
            this.l[15] = max2 / this.h.height();
            if (this.k) {
                for (int i2 = 0; i2 < 4; i2++) {
                    float[] fArr5 = this.l;
                    int i3 = (i2 * 4) + 3;
                    fArr5[i3] = 1.0f - fArr5[i3];
                }
            }
            this.i = new RectF(rectF);
        }
    }

    @Override
    public void close() {
        dispose();
    }

    public void dispose() {
        this.d.dispose();
    }

    public TexturedRect(Texture texture, String str) {
        this.k = false;
        this.l = new float[16];
        HashMap c2 = Maps.newHashMap();
        c2.put(str, texture);
        a(c2, str, a(texture.k()));
    }

    public static Shader a(Texture.Type type) {
        return new Shader("#version 300 es\n\nout highp vec2 vTexcoord;\n\nuniform highp mat4 modelview;\nuniform highp mat4 projection;\n\nin highp vec4 position;\nin highp vec2 texcoord;\n\nvoid main() {\n  vec4 newpos = vec4(position.xy, 0.0, 1.0);\n  gl_Position = projection * modelview * newpos;\n  vTexcoord = texcoord;\n}\n", a.get(b.get(type)));
    }

    public void a() {
        a(this.h);
    }

    public void a(RectF rectF) {
        this.j = new Matrix4f();
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.loadOrtho(0.0f, (float) this.g.l(), 0.0f, (float) this.g.i(), -1.0f, 1.0f);
        b(rectF);
        a(matrix4f, new AttributeData(FloatBuffer.wrap(this.l), c));
    }

    public TexturedRect(Map<String, Texture> map, String str, Shader shader) {
        this.k = false;
        this.l = new float[16];
        a(map, str, shader);
    }

    public void a(RectF rectF, RectF rectF2) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.loadOrtho(rectF2.left, rectF2.right, rectF2.bottom, rectF2.top, -1.0f, 1.0f);
        b(rectF);
        a(matrix4f, new AttributeData(FloatBuffer.wrap(this.l), c));
    }

    public final void a(Matrix4f matrix4f, AttributeData attributeData) {
        Map<String, Texture> map = this.f;
        ArrayList a2 = Lists.newArrayList( new Pair[]{new Pair("modelview", this.j), new Pair("projection", matrix4f)});
        this.e.a();
        this.d.a(5, 4, a2, map, Lists.newArrayList( new AttributeData[]{attributeData}));
        this.e.d();
    }

    public void a(float[] fArr) {
        this.j = new Matrix4f(fArr);
    }

    public void a(Map<String, Texture> map, String str) {
        this.f = map;
        this.g = map.get(str);
        Texture texture = this.g;
        if (texture != null) {
            this.h = new RectF(0.0f, 0.0f, (float) texture.l(), (float) this.g.i());
            return;
        }
        throw new IllegalArgumentException();
    }
}
