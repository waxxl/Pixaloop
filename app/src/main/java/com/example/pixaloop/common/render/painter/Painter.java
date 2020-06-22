package com.example.pixaloop.common.render.painter;

import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.gpu.Fbo;
import com.example.pixaloop.common.render.gpu.GLUtils;
import com.example.pixaloop.common.render.gpu.Shader;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.Vector2;
import com.example.pixaloop.common.render.gpu.Vector4;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Painter implements DisposableResource {
    public static Map<String, String> a = new HashMap();
    public final int A;
    public final float[] B;
    public float C;
    public final boolean D;
    public final boolean E;
    public boolean F;
    public boolean G;
    public Stroke H;
    public int I;
    public Stroke J;
    public List<Stroke> K;
    public List<VertexStruct> L;
    public PainterMode b;
    public float c;
    public double d;
    public double e;
    public float f;
    public float g;
    public Vector4 h;
    public Vector4 i;
    public Texture j;
    public boolean k;
    public final boolean l;
    public final boolean m;
    public final Texture n;
    public final Texture o;
    public final Fbo p;
    public final Fbo q;
    public final Texture r;
    public final Texture s;
    public final Texture t;
    public Shader u;
    public Shader v;
    public Shader w;
    public Shader x;
    public final int[] y;
    public final int z;

    @Override
    public void close() throws Exception {
        dispose();
    }

    public enum PainterMode {
        PAINT,
        ERASE
    }

    private static class Stroke {
        public PainterMode a;
        public float b;
        public float c;
        public Vector4 d;
        public List<Vector2> e = new ArrayList();
        public List<Integer> f = new ArrayList();

        public Stroke(PainterMode painterMode, float f2, float f3, Vector4 vector4) {
            this.a = painterMode;
            this.b = f2;
            this.c = f3;
            this.d = vector4;
        }
    }

    private static class VertexStruct {
        public Vector2 a;
        public Vector2 b;
        public Vector2 c;
        public Vector4 d;

        public VertexStruct() {
        }

        public static int a() {
            return Vector2.e() + Vector2.e() + Vector2.e() + Vector4.e();
        }

        public static void b(Shader shader) {
            int a2 = a();
            int a3 = shader.a("position");
            GLES20.glEnableVertexAttribArray(a3);
            GLES20.glVertexAttribPointer(a3, 2, 5126, false, a2, 0);
            int a4 = shader.a("texcoord");
            if (a4 != -1) {
                GLES20.glEnableVertexAttribArray(a4);
                GLES20.glVertexAttribPointer(a4, 2, 5126, false, a2, Vector2.e());
            }
            int a5 = shader.a("centercoord");
            if (a5 != -1) {
                GLES20.glEnableVertexAttribArray(a5);
                GLES20.glVertexAttribPointer(shader.a("centercoord"), 2, 5126, false, a2, Vector2.e() * 2);
            }
            int a6 = shader.a("intensity");
            if (a6 != -1) {
                GLES20.glEnableVertexAttribArray(a6);
                GLES20.glVertexAttribPointer(a6, 3, 5126, false, a2, Vector2.e() * 3);
            }
        }

        public int a(float[] fArr, int i) {
            return this.d.a(fArr, this.c.a(fArr, this.b.a(fArr, this.a.a(fArr, i))));
        }

        public static void a(Shader shader) {
            GLES20.glDisableVertexAttribArray(shader.a("position"));
            int a2 = shader.a("texcoord");
            if (a2 != -1) {
                GLES20.glDisableVertexAttribArray(a2);
            }
            int a3 = shader.a("centercoord");
            if (a3 != -1) {
                GLES20.glDisableVertexAttribArray(a3);
            }
            int a4 = shader.a("intensity");
            if (a4 != -1) {
                GLES20.glDisableVertexAttribArray(a4);
            }
        }
    }

    static {
        a.put("LTPainterShaderPaint.vsh", "uniform mat4 mvp;\nuniform highp vec2 imageSize;\n\n// The unused attributes are here to make both the erase shader and the paint shader have the same\n// signature.\nattribute mediump vec4 intensity;\nattribute highp vec4 position;\nattribute highp vec2 texcoord;\nattribute highp vec2 centercoord;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nvoid main() {\n  vec4 newpos = vec4(position.xy, 0.0, 1.0);\n  gl_Position = mvp * newpos;\n  vTexcoord = texcoord;\n  vImgcoord = position.xy / imageSize;\n  vIntensity = intensity;\n  \n  // Avoid warnings regarding unused attributes.\n  centercoord;\n}\n");
        a.put("LTPainterShaderPaint.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  highp vec4 target = brushFactor * vIntensity;\n  \n  gl_FragColor = clamp(previousPixel + weight * target, minClamp, maxClamp);\n}");
        a.put("LTPainterShaderPaintWithHighBytePrecision.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp float inv256 = 1.0 / 256.0;\nconst highp vec2 toFixed = vec2(255.0/256.0);\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  // Decode the RG channels into a float.\n  highp vec2 previousPixel = texture2D(previous, vImgcoord).rg;\n  highp float hpPixel = dot(previousPixel * fromFixed, vec2(1.0, inv255));\n\n  // Map the value to the range [minClamp,maxClamp].\n  hpPixel = hpPixel * (maxClamp - minClamp) + minClamp;\n  \n  // Increase the pixel value, according to the brush and intensity.\n  hpPixel = clamp(hpPixel + weight * brushFactor * vIntensity.r, minClamp, maxClamp);\n  \n  // Map the value to the range [0,1].\n  hpPixel = (hpPixel - minClamp) / (maxClamp - minClamp);\n    \n  // Encode the float into two byte channels, and store in the RG channels.\n  highp vec2 enc = fract(vec2(1.0, 255.0) * toFixed * hpPixel);\n  enc.r -= enc.g * inv256;\n  gl_FragColor = vec4(enc, 0.0, 0.0);\n}");
        a.put("LTPainterShaderPaintWithHighBytePrecision_RGBrush.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp float inv256 = 1.0 / 256.0;\nconst highp vec2 toFixed = vec2(255.0/256.0);\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  // Decode the RG channels into a float.\n  highp vec2 previousPixel = texture2D(previous, vImgcoord).rg;\n  highp float hpPixel = dot(previousPixel * fromFixed, vec2(1.0, inv255));\n\n  // Map the value to the range [minClamp,maxClamp].\n  hpPixel = hpPixel * (maxClamp - minClamp) + minClamp;\n  \n  // Increase the pixel value, according to the brush and intensity.\n  hpPixel = clamp(hpPixel + weight * brushFactor * vIntensity.r, minClamp, maxClamp);\n  \n  // Map the value to the range [0,1].\n  hpPixel = (hpPixel - minClamp) / (maxClamp - minClamp);\n    \n  // Encode the float into two byte channels, and store in the RG channels.\n  highp vec2 enc = fract(vec2(1.0, 255.0) * toFixed * hpPixel);\n  enc.r -= enc.g * inv256;\n  gl_FragColor = vec4(enc, 0.0, 0.0);\n}");
        a.put("LTPainterShaderPaintIPad.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  highp vec4 target = brushFactor * vIntensity;\n  \n  gl_FragColor = clamp(previousPixel + weight * target, vec4(minClamp), \n                       max(previousPixel, vec4(maxClamp)));\n}");
        a.put("LTPainterShaderPaintEA.vsh", "uniform mat4 mvp;\nuniform mediump float pointSize;\nuniform highp vec2 imageSize;\n\nattribute mediump vec4 intensity;\nattribute highp vec4 position;\nattribute highp vec2 texcoord;\nattribute highp vec2 centercoord;\n\nvarying mediump vec4 vIntensity;\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying highp vec2 vCentercoord;\nvarying highp vec2 vCentercoordUL;\nvarying highp vec2 vCentercoordUR;\nvarying highp vec2 vCentercoordBL;\nvarying highp vec2 vCentercoordBR;\n\n#define MULTISAMPLE_OFFSET_FACTOR (50.0)\n\nvoid main() {\n  vec4 newpos = vec4(position.xy, 0.0, 1.0);\n  gl_Position = mvp * newpos;\n  vTexcoord = texcoord;\n  vImgcoord = position.xy / imageSize;\n  \n  highp float offset = ceil(pointSize / MULTISAMPLE_OFFSET_FACTOR);\n  vCentercoord = centercoord / imageSize;\n  vCentercoordUL = (centercoord + vec2(-offset, -offset)) / imageSize;\n  vCentercoordUR = (centercoord + vec2(offset, -offset)) / imageSize;\n  vCentercoordBL = (centercoord + vec2(-offset, offset)) / imageSize;\n  vCentercoordBR = (centercoord + vec2(offset, offset)) / imageSize;\n  vIntensity = intensity;\n}");
        a.put("LTPainterShaderPaintEA.fsh", "uniform highp float sigma;\nuniform lowp sampler2D image;\nuniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying mediump vec4 vIntensity;\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying highp vec2 vCentercoord;\nvarying highp vec2 vCentercoordUL;\nvarying highp vec2 vCentercoordUR;\nvarying highp vec2 vCentercoordBL;\nvarying highp vec2 vCentercoordBR;\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  highp vec3 imgPixel = texture2D(image, vImgcoord).rgb;\n  highp vec3 centerPixel = imgPixel - texture2D(image, vCentercoord).rgb;\n  highp vec3 centerPixelUL = imgPixel - texture2D(image, vCentercoordUL).rgb;\n  highp vec3 centerPixelUR = imgPixel - texture2D(image, vCentercoordUR).rgb;\n  highp vec3 centerPixelBL = imgPixel - texture2D(image, vCentercoordBL).rgb;\n  highp vec3 centerPixelBR = imgPixel - texture2D(image, vCentercoordBR).rgb;\n  \n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  \n  highp float rgbDist = dot(centerPixel, centerPixel);\n  highp float rgbDistUL = dot(centerPixelUL, centerPixelUL);\n  highp float rgbDistUR = dot(centerPixelUR, centerPixelUR);\n  highp float rgbDistBL = dot(centerPixelBL, centerPixelBL);\n  highp float rgbDistBR = dot(centerPixelBR, centerPixelBR);\n  \n  // The rgb (range) falloff of the current pixel is a minimum of the distances from the points\n  // sampled at and around the center of the kernel.\n  rgbDist = sqrt(min(rgbDist, min(min(rgbDistUL, rgbDistUR), min(rgbDistBL, rgbDistBR))));\n  // After the range falloff is computed, we combine it with a spatial falloff.\n  // We experimented with three options here. First option is sample the brush texture that stores\n  // the spatial falloff values and multiply it with the range falloff:\n  // highp float factor =  brushFactor * exp(-rgbDist/sigma);\n  // The problem of this approach that is that the pixels within a certain radius from the center\n  // that fall under the user's finger can get a very low value due to a range attenuation.\n  // This is an undesired behaviour.\n  // The second option, applies a range attenuation only in the ring around the circle:\n  // highp float factor = mix(brushFactor * exp(-rgbDist/sigma), brushFactor,\n  // step(0.99, brushFactor));\n  // The problem of this approach is that it creates a visible transition between the\n  // edge-preserving and non-edge-preserving part.\n  // Third appraoach and our current approach of choice is to vary the range sigma smoothly over\n  // the painted region. The closer we are to the center of the stroke, the higher the sigma\n  // becomes (= less edge-preserving).\n  highp float factor = brushFactor * exp(-rgbDist/max(brushFactor * sigma * 2.0, sigma));\n  // IMPORTANT: If we will need a further optimization of the alpha painting process, consider to\n  // remove one of the sampling points, which is less crucial once we have a constant region with\n  // no range falloff. Another opportunity is to use cheaper attenuation functions, such as\n  // multiplicative inverse: brushFactor * 0.1/(rgbDist+0.1) or smoothstep. In the case of the\n  // inverse, normalization of the max value to one should be accomplished.\n  \n  gl_FragColor = clamp(previousPixel + weight * (factor * vIntensity), minClamp, maxClamp);\n}");
        a.put("LTPainterShaderPaintWithHighBytePrecisionEA.fsh", "uniform highp float sigma;\nuniform lowp sampler2D image;\nuniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying mediump vec4 vIntensity;\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying highp vec2 vCentercoord;\nvarying highp vec2 vCentercoordUL;\nvarying highp vec2 vCentercoordUR;\nvarying highp vec2 vCentercoordBL;\nvarying highp vec2 vCentercoordBR;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp float inv256 = 1.0 / 256.0;\nconst highp vec2 toFixed = vec2(255.0/256.0);\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  highp vec3 imgPixel = texture2D(image, vImgcoord).rgb;\n  highp vec3 centerPixel = imgPixel - texture2D(image, vCentercoord).rgb;\n  highp vec3 centerPixelUL = imgPixel - texture2D(image, vCentercoordUL).rgb;\n  highp vec3 centerPixelUR = imgPixel - texture2D(image, vCentercoordUR).rgb;\n  highp vec3 centerPixelBL = imgPixel - texture2D(image, vCentercoordBL).rgb;\n  highp vec3 centerPixelBR = imgPixel - texture2D(image, vCentercoordBR).rgb;\n  \n  highp float rgbDist = dot(centerPixel, centerPixel);\n  highp float rgbDistUL = dot(centerPixelUL, centerPixelUL);\n  highp float rgbDistUR = dot(centerPixelUR, centerPixelUR);\n  highp float rgbDistBL = dot(centerPixelBL, centerPixelBL);\n  highp float rgbDistBR = dot(centerPixelBR, centerPixelBR);\n\n  rgbDist = sqrt(min(rgbDist, min(min(rgbDistUL, rgbDistUR), min(rgbDistBL, rgbDistBR))));\n  \n  // Decode the RG channels into a float.\n  highp vec2 previousPixel = texture2D(previous, vImgcoord).rg;\n  highp float hpPixel = dot(previousPixel * fromFixed, vec2(1.0, inv255));\n\n  // Map the value to the range [minClamp,maxClamp].\n  hpPixel = hpPixel * (maxClamp - minClamp) + minClamp;\n  \n  highp float factor = mix(brushFactor * exp(-rgbDist/sigma), 1.0, step(0.99, brushFactor));\n  \n  // Increase the pixel value, according to the brush, intensity and distance function.\n  hpPixel = clamp(hpPixel + weight * (factor * vIntensity.r), minClamp, maxClamp);\n\n  // Map the value to the range [0,1].\n  hpPixel = (hpPixel - minClamp) / (maxClamp - minClamp);\n  \n  // Encode the float into two byte channels, and store in the RG channels.\n  highp vec2 enc = fract(vec2(1.0, 255.0) * toFixed * hpPixel);\n  enc.r -= enc.g * inv256;\n  gl_FragColor = vec4(enc, 0.0, 0.0);\n}\n");
        a.put("LTPainterShaderPaintEA_RGBrush.fsh", "uniform highp float sigma;\nuniform lowp sampler2D image;\nuniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying mediump vec4 vIntensity;\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying highp vec2 vCentercoord;\nvarying highp vec2 vCentercoordUL;\nvarying highp vec2 vCentercoordUR;\nvarying highp vec2 vCentercoordBL;\nvarying highp vec2 vCentercoordBR;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp vec2 codedBrush = texture2D(brush, vTexcoord).ra;\n  highp float brushFactor = dot(codedBrush * fromFixed, vec2(1.0, inv255));\n  \n  //highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  highp vec3 imgPixel = texture2D(image, vImgcoord).rgb;\n  highp vec3 centerPixel = imgPixel - texture2D(image, vCentercoord).rgb;\n  highp vec3 centerPixelUL = imgPixel - texture2D(image, vCentercoordUL).rgb;\n  highp vec3 centerPixelUR = imgPixel - texture2D(image, vCentercoordUR).rgb;\n  highp vec3 centerPixelBL = imgPixel - texture2D(image, vCentercoordBL).rgb;\n  highp vec3 centerPixelBR = imgPixel - texture2D(image, vCentercoordBR).rgb;\n  \n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  \n  highp float rgbDist = dot(centerPixel, centerPixel);\n  highp float rgbDistUL = dot(centerPixelUL, centerPixelUL);\n  highp float rgbDistUR = dot(centerPixelUR, centerPixelUR);\n  highp float rgbDistBL = dot(centerPixelBL, centerPixelBL);\n  highp float rgbDistBR = dot(centerPixelBR, centerPixelBR);\n  \n  // The rgb (range) falloff of the current pixel is a minimum of the distances from the points\n  // sampled at and around the center of the kernel.\n  rgbDist = sqrt(min(rgbDist, min(min(rgbDistUL, rgbDistUR), min(rgbDistBL, rgbDistBR))));\n  // After the range falloff is computed, we combine it with a spatial falloff.\n  // We experimented with three options here. First option is sample the brush texture that stores\n  // the spatial falloff values and multiply it with the range falloff:\n  // highp float factor =  brushFactor * exp(-rgbDist/sigma);\n  // The problem of this approach that is that the pixels within a certain radius from the center\n  // that fall under the user's finger can get a very low value due to a range attenuation.\n  // This is an undesired behaviour.\n  // The second option, applies a range attenuation only in the ring around the circle:\n  // highp float factor = mix(brushFactor * exp(-rgbDist/sigma), brushFactor,\n  // step(0.99, brushFactor));\n  // The problem of this approach is that it creates a visible transition between the\n  // edge-preserving and non-edge-preserving part.\n  // Third appraoach and our current approach of choice is to vary the range sigma smoothly over\n  // the painted region. The closer we are to the center of the stroke, the higher the sigma\n  // becomes (= less edge-preserving).\n  highp float factor = brushFactor * exp(-rgbDist/max(brushFactor * sigma * 2.0, sigma));\n  // IMPORTANT: If we will need a further optimization of the alpha painting process, consider to\n  // remove one of the sampling points, which is less crucial once we have a constant region with\n  // no range falloff. Another opportunity is to use cheaper attenuation functions, such as\n  // multiplicative inverse: brushFactor * 0.1/(rgbDist+0.1) or smoothstep. In the case of the\n  // inverse, normalization of the max value to one should be accomplished.\n  \n  gl_FragColor = clamp(previousPixel + weight * (factor * vIntensity), minClamp, maxClamp);\n}");
        a.put("LTPainterShaderPaintWithHighBytePrecisionEA_RGBrush.fsh", "uniform highp float sigma;\nuniform lowp sampler2D image;\nuniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying mediump vec4 vIntensity;\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying highp vec2 vCentercoord;\nvarying highp vec2 vCentercoordUL;\nvarying highp vec2 vCentercoordUR;\nvarying highp vec2 vCentercoordBL;\nvarying highp vec2 vCentercoordBR;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp float inv256 = 1.0 / 256.0;\nconst highp vec2 toFixed = vec2(255.0/256.0);\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  highp vec3 imgPixel = texture2D(image, vImgcoord).rgb;\n  highp vec3 centerPixel = imgPixel - texture2D(image, vCentercoord).rgb;\n  highp vec3 centerPixelUL = imgPixel - texture2D(image, vCentercoordUL).rgb;\n  highp vec3 centerPixelUR = imgPixel - texture2D(image, vCentercoordUR).rgb;\n  highp vec3 centerPixelBL = imgPixel - texture2D(image, vCentercoordBL).rgb;\n  highp vec3 centerPixelBR = imgPixel - texture2D(image, vCentercoordBR).rgb;\n  \n  highp float rgbDist = dot(centerPixel, centerPixel);\n  highp float rgbDistUL = dot(centerPixelUL, centerPixelUL);\n  highp float rgbDistUR = dot(centerPixelUR, centerPixelUR);\n  highp float rgbDistBL = dot(centerPixelBL, centerPixelBL);\n  highp float rgbDistBR = dot(centerPixelBR, centerPixelBR);\n\n  rgbDist = sqrt(min(rgbDist, min(min(rgbDistUL, rgbDistUR), min(rgbDistBL, rgbDistBR))));\n  \n  // Decode the RG channels into a float.\n  highp vec2 previousPixel = texture2D(previous, vImgcoord).rg;\n  highp float hpPixel = dot(previousPixel * fromFixed, vec2(1.0, inv255));\n\n  // Map the value to the range [minClamp,maxClamp].\n  hpPixel = hpPixel * (maxClamp - minClamp) + minClamp;\n  \n  highp float factor = mix(brushFactor * exp(-rgbDist/sigma), 1.0, step(0.99, brushFactor));\n  \n  // Increase the pixel value, according to the brush, intensity and distance function.\n  hpPixel = clamp(hpPixel + weight * (factor * vIntensity.r), minClamp, maxClamp);\n\n  // Map the value to the range [0,1].\n  hpPixel = (hpPixel - minClamp) / (maxClamp - minClamp);\n  \n  // Encode the float into two byte channels, and store in the RG channels.\n  highp vec2 enc = fract(vec2(1.0, 255.0) * toFixed * hpPixel);\n  enc.r -= enc.g * inv256;\n  gl_FragColor = vec4(enc, 0.0, 0.0);\n}\n");
        a.put("LTPainterShaderPaintEAIPad.fsh", "uniform highp float sigma;\nuniform lowp sampler2D image;\nuniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying mediump vec4 vIntensity;\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying highp vec2 vCentercoord;\nvarying highp vec2 vCentercoordUL;\nvarying highp vec2 vCentercoordUR;\nvarying highp vec2 vCentercoordBL;\nvarying highp vec2 vCentercoordBR;\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  highp vec3 imgPixel = texture2D(image, vImgcoord).rgb;\n  highp vec3 centerPixel = imgPixel - texture2D(image, vCentercoord).rgb;\n  highp vec3 centerPixelUL = imgPixel - texture2D(image, vCentercoordUL).rgb;\n  highp vec3 centerPixelUR = imgPixel - texture2D(image, vCentercoordUR).rgb;\n  highp vec3 centerPixelBL = imgPixel - texture2D(image, vCentercoordBL).rgb;\n  highp vec3 centerPixelBR = imgPixel - texture2D(image, vCentercoordBR).rgb;\n  \n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  \n  highp float rgbDist = dot(centerPixel, centerPixel);\n  highp float rgbDistUL = dot(centerPixelUL, centerPixelUL);\n  highp float rgbDistUR = dot(centerPixelUR, centerPixelUR);\n  highp float rgbDistBL = dot(centerPixelBL, centerPixelBL);\n  highp float rgbDistBR = dot(centerPixelBR, centerPixelBR);\n  \n  // The rgb (range) falloff of the current pixel is a minimum of the distances from the points\n  // sampled at and around the center of the kernel.\n  rgbDist = sqrt(min(rgbDist, min(min(rgbDistUL, rgbDistUR), min(rgbDistBL, rgbDistBR))));\n  // After the range falloff is computed, we combine it with a spatial falloff.\n  // We experimented with three options here. First option is sample the brush texture that stores\n  // the spatial falloff values and multiply it with the range falloff:\n  // highp float factor =  brushFactor * exp(-rgbDist/sigma);\n  // The problem of this approach that is that the pixels within a certain radius from the center\n  // that fall under the user's finger can get a very low value due to a range attenuation.\n  // This is an undesired behaviour.\n  // The second option, applies a range attenuation only in the ring around the circle:\n  // highp float factor = mix(brushFactor * exp(-rgbDist/sigma), brushFactor,\n  // step(0.99, brushFactor));\n  // The problem of this approach is that it creates a visible transition between the\n  // edge-preserving and non-edge-preserving part.\n  // Third appraoach and our current approach of choice is to vary the range sigma smoothly over\n  // the painted region. The closer we are to the center of the stroke, the higher the sigma\n  // becomes (= less edge-preserving).\n  highp float factor = brushFactor * exp(-rgbDist/max(brushFactor * sigma * 2.0, sigma));\n  // IMPORTANT: If we will need a further optimization of the alpha painting process, consider to\n  // remove one of the sampling points, which is less crucial once we have a constant region with\n  // no range falloff. Another opportunity is to use cheaper attenuation functions, such as\n  // multiplicative inverse: brushFactor * 0.1/(rgbDist+0.1) or smoothstep. In the case of the\n  // inverse, normalization of the max value to one should be accomplished.\n  \n  // The new value shouldn't be higher than the maximum between maxClamp and the existing value.\n  gl_FragColor = clamp(previousPixel + weight * (factor * vIntensity), vec4(minClamp),\n                       max(previousPixel, vec4(maxClamp)));\n}\n");
        a.put("LTPainterShaderErase.vsh", "uniform mat4 mvp;\nuniform highp vec2 imageSize;\n\n// The unused attributes are here to make both the erase shader and the paint shader have the same\n// signature.\nattribute mediump vec4 intensity;\nattribute highp vec4 position;\nattribute highp vec2 texcoord;\nattribute highp vec2 centercoord;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nvoid main() {\n  vec4 newpos = vec4(position.xy, 0.0, 1.0);\n  gl_Position = mvp * newpos;\n  vTexcoord = texcoord;\n  vImgcoord = position.xy / imageSize;\n  vIntensity = intensity;\n  \n  // Avoid warnings regarding unused attributes.\n  centercoord;\n}");
        a.put("LTPainterShaderErase.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  highp vec4 target = brushFactor * vIntensity;\n  \n  gl_FragColor = clamp(previousPixel - weight * target, minClamp, maxClamp);\n}\n");
        a.put("LTPainterShaderEraseIPad.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  highp vec4 target = brushFactor * vIntensity;\n  \n  // Each new negative contribution weight * target, should be not larger than maxClamp.\n  gl_FragColor = clamp(previousPixel - clamp(weight * target, minClamp, maxClamp), 0.0, 1.0);\n}");
        a.put("LTPainterShaderEraseWithHighBytePrecision.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp float inv256 = 1.0 / 256.0;\nconst highp vec2 toFixed = vec2(255.0/256.0);\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  // Decode the RG channels into a float.\n  highp vec2 previousPixel = texture2D(previous, vImgcoord).rg;\n  highp float hpPixel = dot(previousPixel * fromFixed, vec2(1.0, inv255));\n\n  // Map the value to the range [minClamp,maxClamp].\n  hpPixel = hpPixel * (maxClamp - minClamp) + minClamp;\n  \n  // Decrease the pixel value, according to the brush and intensity.\n  hpPixel = clamp(hpPixel - weight * brushFactor * vIntensity.r, minClamp, maxClamp);\n  \n  // Map the value to the range [0,1].\n  hpPixel = (hpPixel - minClamp) / (maxClamp - minClamp);\n\n  // Encode the float into two byte channels, and store in the RG channels.\n  highp vec2 enc = fract(vec2(1.0, 255.0) * toFixed * hpPixel);\n  enc.r -= enc.g * inv256;\n  gl_FragColor = vec4(enc, 0.0, 0.0);\n}");
        a.put("LTPainterShaderEraseWithHighBytePrecision_RGBrush.fsh", "uniform mediump sampler2D brush;\nuniform mediump sampler2D previous;\nuniform highp float weight;\nuniform highp float minClamp;\nuniform highp float maxClamp;\n\nvarying highp vec2 vTexcoord;\nvarying highp vec2 vImgcoord;\nvarying mediump vec4 vIntensity;\n\nconst highp float inv255 = 1.0 / 255.0;\nconst highp float inv256 = 1.0 / 256.0;\nconst highp vec2 toFixed = vec2(255.0/256.0);\nconst highp vec2 fromFixed = vec2(256.0/255.0);\n\nvoid main() {\n  highp float brushFactor = texture2D(brush, vTexcoord).r;\n  \n  // Decode the RG channels into a float.\n  highp vec2 previousPixel = texture2D(previous, vImgcoord).rg;\n  highp float hpPixel = dot(previousPixel * fromFixed, vec2(1.0, inv255));\n\n  // Map the value to the range [minClamp,maxClamp].\n  hpPixel = hpPixel * (maxClamp - minClamp) + minClamp;\n  \n  // Decrease the pixel value, according to the brush and intensity.\n  hpPixel = clamp(hpPixel - weight * brushFactor * vIntensity.r, minClamp, maxClamp);\n  \n  // Map the value to the range [0,1].\n  hpPixel = (hpPixel - minClamp) / (maxClamp - minClamp);\n\n  // Encode the float into two byte channels, and store in the RG channels.\n  highp vec2 enc = fract(vec2(1.0, 255.0) * toFixed * hpPixel);\n  enc.r -= enc.g * inv256;\n  gl_FragColor = vec4(enc, 0.0, 0.0);\n}");
        a.put("LTPainterShaderClone.vsh", "uniform mat4 mvp;\nuniform highp vec2 imageSize;\n\n// The unused attributes are here to make both the erase shader and the paint shader have the same\n// signature.\nattribute mediump vec4 intensity;\nattribute highp vec4 position;\nattribute highp vec2 texcoord;\nattribute highp vec2 centercoord;\n\nvarying highp vec2 vImgcoord;\n\nvoid main() {\n  vec4 newpos = vec4(position.xy, 0.0, 1.0);\n  gl_Position = vec4((mvp * newpos).xy, -0.5, 1.0);\n  vImgcoord = position.xy / imageSize;\n  \n  // Avoid warnings regarding unused attributes.\n  centercoord;\n  texcoord;\n  intensity;\n}\n");
        a.put("LTPainterShaderClone.fsh", "uniform mediump sampler2D previous;\n\nvarying highp vec2 vImgcoord;\n\nvoid main() {\n  highp vec4 previousPixel = texture2D(previous, vImgcoord);\n  gl_FragColor = previousPixel;\n}");
    }

    public Painter(int i2, int i3, Texture texture, float f2, Brush brush, Brush brush2, double d2, double d3, boolean z2, Texture texture2, boolean z3, boolean z4) {
        int i4 = i2;
        int i5 = i3;
        double d4 = d2;
        double d5 = d3;
        boolean z5 = z2;
        Texture texture3 = texture2;
        boolean z6 = z3;
        this.y = new int[4];
        this.B = new float[16];
        this.K = new ArrayList();
        this.L = new ArrayList();
        if (i5 < 0 || i4 < 0) {
            throw new IllegalArgumentException("Invalid Painter size");
        } else if (d4 <= 0.0d) {
            throw new IllegalArgumentException("Invalid painter brush size");
        } else if (d5 > 0.0d) {
            this.z = i4;
            this.A = i5;
            c(f2);
            a(texture);
            this.f = 0.0f;
            this.g = 1.0f;
            this.C = 1.0f;
            this.D = z6;
            this.E = z4;
            if (texture3 != null) {
                this.n = texture3;
                this.p = new Fbo(texture3);
            } else {
                this.p = new Fbo(i4, i5);
                this.n = this.p.e();
            }
            if (z6) {
                this.o = new Texture(this.n.l(), this.n.i(), Texture.Type.RGBA8Unorm, true);
                this.q = new Fbo(this.o);
                this.q.b();
            } else {
                this.o = null;
                this.q = null;
            }
            if (z6) {
                this.o.a(9728);
            }
            this.l = z5;
            this.m = z5;
            this.t = new Texture(1, 1, Texture.Type.RGBA8Unorm, true);
            this.t.a(new byte[]{0, 0, 0, 0});
            this.t.a(9728);
            if (this.m) {
                this.r = brush.b();
                this.s = brush2.b();
                this.r.a(9728);
                this.s.a(9728);
            } else {
                this.r = brush.a();
                this.s = brush2.a();
                this.r.a(9729);
                this.s.a(9729);
            }
            this.d = d4;
            this.e = d5;
            Vector4 vector4 = Vector4.a;
            this.h = vector4;
            this.i = vector4;
            Matrix.orthoM(this.B, 0, 0.0f, (float) i4, 0.0f, (float) i5, -1.0f, 1.0f);
            GLES20.glFlush();
            info();
            h();
            GLUtils.a();
        } else {
            throw new IllegalArgumentException("Invalid eraser brush size");
        }
    }

    public static Texture a(int i2, int i3) {
        Texture texture = new Texture(i2, i3, Texture.Type.RGBA8Unorm, true);
        Fbo fbo = new Fbo(texture);
        fbo.b();
        fbo.dispose();
        return texture;
    }

    public final void b() {
        Texture texture;
        if (this.L.size() != 0) {
            GLES20.glUseProgram(this.x.c());
            GLES20.glUniformMatrix4fv(this.x.b("mvp"), 1, false, this.B, 0);
            GLES20.glUniform2f(this.x.b("imageSize"), (float) this.z, (float) this.A);
            GLES20.glActiveTexture(33986);
            if (this.F) {
                texture = this.p.e();
            } else {
                texture = this.q.e();
            }
            GLES20.glBindTexture(3553, texture.h());
            GLES20.glUniform1i(this.x.b("previous"), 2);
            GLES20.glBindBuffer(34962, this.y[3]);
            float[] fArr = new float[((this.L.size() * VertexStruct.a()) / 4)];
            int i2 = 0;
            for (VertexStruct a2 : this.L) {
                i2 = a2.a(fArr, i2);
            }
            int length = fArr.length * 4;
            ByteBuffer order = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
            order.asFloatBuffer().put(fArr);
            order.position(0);
            GLES20.glBufferData(34962, length, order, 35040);
            VertexStruct.b(this.x);
            GLES20.glDrawArrays(5, 0, this.L.size());
            VertexStruct.a(this.x);
            GLES20.glBindBuffer(34962, 0);
            GLES20.glUseProgram(0);
        }
    }

    public void c(float f2) {
        if (f2 <= 0.0f || f2 > 1000.0f) {
            throw new IllegalArgumentException("wrong sigma value");
        }
        this.c = f2;
    }

    public void d() {
        Stroke stroke;
        if (this.H != null) {
            this.H = null;
        } else if (this.G && (stroke = this.J) != null && stroke.e.size() > 0) {
            this.I++;
        }
        this.G = false;
    }

    public void dispose() {
        this.p.dispose();
        this.r.dispose();
        this.s.dispose();
        this.t.dispose();
        if (this.D) {
            this.q.dispose();
            this.o.dispose();
        }
        int[] iArr = this.y;
        GLES20.glDeleteBuffers(iArr.length, iArr, 0);
    }

    public final Stroke e() {
        if (this.I < this.K.size()) {
            return this.K.get(this.I);
        }
        Log.w("Painter", "currentStrokeIndex=" + this.I + ", size=" + this.K.size());
        return null;
    }

    public Texture f() {
        return this.n;
    }

    public void g() {
        this.p.b();
        if (this.D) {
            this.q.b();
        }
        this.K.clear();
        this.I = 0;
        this.F = false;
        this.G = false;
    }

    public final void h() {
        int[] iArr = this.y;
        GLES20.glGenBuffers(iArr.length, iArr, 0);
    }

    public final void info() {
        String str = "";
        String str2 = this.l ? "WithHighBytePrecision" : str;
        if (this.m) {
            str = "_RGBrush";
        }
        Log.d("Painter", "Loading shaders: hbp=" + this.l + " rgbrush=" + this.m + "brushControl=" + this.E);
        if (!this.E) {
            this.u = a("LTPainterShaderPaint.vsh", "LTPainterShaderPaint" + str2 + str + ".fsh");
            this.v = a("LTPainterShaderPaintEA.vsh", "LTPainterShaderPaint" + str2 + "EA" + str + ".fsh");
            StringBuilder sb = new StringBuilder();
            sb.append("LTPainterShaderErase");
            sb.append(str2);
            sb.append(str);
            sb.append(".fsh");
            this.w = a("LTPainterShaderErase.vsh", sb.toString());
        } else {
            this.u = a("LTPainterShaderPaint.vsh", "LTPainterShaderPaintIPad.fsh");
            this.v = a("LTPainterShaderPaintEA.vsh", "LTPainterShaderPaintEAIPad.fsh");
            this.w = a("LTPainterShaderErase.vsh", "LTPainterShaderEraseIPad.fsh");
        }
        this.x = a("LTPainterShaderClone.vsh", "LTPainterShaderClone.fsh");
        a();
    }

    public Vector4 c() {
        return this.b == PainterMode.PAINT ? this.h : this.i;
    }

    public final Shader a(String str, String str2) {
        return new Shader(a.get(str), a.get(str2));
    }

    public final void a() {
        this.u.a("weight", this.C);
        this.u.a("minClamp", this.f);
        this.u.a("maxClamp", this.g);
        this.v.a("weight", this.C);
        this.v.a("minClamp", this.f);
        this.v.a("maxClamp", this.g);
        this.w.a("weight", this.C);
        this.w.a("minClamp", this.f);
        this.w.a("maxClamp", this.g);
    }

    public void a(double d2) {
        if (this.G) {
            Log.w("Painter", "stroke in stroke");
            d();
        }
        this.G = true;
        double d3 = (this.b == PainterMode.PAINT ? this.d : this.e) / d2;
        this.H = new Stroke(this.b, (float) d3, (float) (d3 / 5.0d), c());
    }

    public RectF a(PointF pointF, PointF pointF2, List<Vector2> list) {
        if (this.H != null) {
            this.I = Math.min(this.K.size(), this.I);
            List<Stroke> list2 = this.K;
            list2.subList(this.I, list2.size()).clear();
            this.K.add(this.H);
            this.H = null;
        }
        a();
        Stroke e2 = e();
        if (e2 == null || !this.G) {
            Log.w("Painter", "can paint only between start and end");
            if (list != null) {
                list.clear();
            }
            return new RectF();
        }
        float f2 = e2.c;
        int size = e2.e.size();
        a(pointF, pointF2, f2, e2.e);
        int size2 = e2.e.size() - 1;
        e2.f.add(Integer.valueOf(size2));
        this.n.a(9728);
        Fbo fbo = this.D ? this.q : this.p;
        this.F = this.D;
        fbo.a();
        a(e2, size, size2, this.D, list);
        fbo.c();
        GLES20.glClear(17664);
        if (this.D) {
            this.F = false;
            this.p.a();
            b();
            this.p.c();
            GLES20.glClear(17664);
        }
        RectF rectF = new RectF(Math.min(pointF.x, pointF2.x), Math.min(pointF.y, pointF2.y), Math.max(pointF.x, pointF2.x), Math.min(pointF.y, pointF2.y));
        float f3 = -(e2.b / 2.0f);
        rectF.inset(f3, f3);
        return rectF;
    }

    public void b(Vector4 vector4) {
        this.h = vector4;
    }

    public void b(float f2) {
        this.f = f2;
        a();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    public Painter(PainterParams painterParams) {
        this(painterParams.a, painterParams.b, painterParams.c, painterParams.d, painterParams.e, painterParams.f, (double) painterParams.k, (double) painterParams.l, painterParams.m, painterParams.n, painterParams.p, painterParams.q);
        PainterParams painterParams2 = painterParams;
        PainterParams painterParams3 = painterParams2;
        PainterParams painterParams4 = painterParams3;
        b(painterParams3.g);
        a(painterParams4.h);
        b(painterParams4.i);
        a(painterParams4.j);
        a(painterParams4.o);
    }

    public final void a(PointF pointF, PointF pointF2, float f2, List<Vector2> list) {
        float f3 = pointF2.x;
        float f4 = pointF.x;
        float f5 = (f3 - f4) * (f3 - f4);
        float f6 = pointF2.y;
        float f7 = pointF.y;
        int max = (int) Math.max(Math.ceil(Math.sqrt((double) (f5 + ((f6 - f7) * (f6 - f7)))) / ((double) f2)), 1.0d);
        for (int i2 = 0; i2 < max; i2++) {
            float f8 = pointF.x;
            float f9 = ((float) i2) / ((float) max);
            float f10 = f8 + ((pointF2.x - f8) * f9);
            float f11 = pointF.y;
            list.add(new Vector2(f10, f11 + ((pointF2.y - f11) * f9)));
        }
        if (((double) PointF.length(pointF.x - pointF2.x, pointF.y - pointF2.y)) > 1.0d) {
            list.add(new Vector2(pointF2));
        }
    }

    public final void a(Stroke stroke, int i2, int i3, List<VertexStruct> list) {
        Vector2 vector2;
        Vector4 vector4;
        Stroke stroke2 = stroke;
        int i4 = i2;
        int i5 = i3;
        List<VertexStruct> list2 = list;
        list.clear();
        if (i5 >= i4) {
            float f2 = stroke2.b / 2.0f;
            int i6 = (i5 - i4) + 1;
            Vector2 vector22 = stroke2.e.get(i4);
            Vector2 vector23 = stroke2.e.get(i5);
            if (i6 == 1) {
                vector2 = Vector2.d;
            } else {
                vector2 = vector23.b(vector22).d();
            }
            Vector2 vector24 = new Vector2(vector2.b(), -vector2.a());
            Vector2 a2 = vector2.a(f2);
            Vector2 a3 = vector24.a(f2);
            if (stroke2.a == PainterMode.PAINT) {
                vector4 = this.h.a(1.0f);
            } else {
                vector4 = this.i.a(((!this.k || this.j == null) ? 1.0f : -1.0f) * 1.0f);
            }
            if (!this.l || (vector4.c() == 0.0f && vector4.d() == 0.0f && vector4.a() == 0.0f)) {
                float f3 = (float) (i6 / 2);
                VertexStruct vertexStruct = new VertexStruct();
                VertexStruct vertexStruct2 = new VertexStruct();
                vertexStruct.a = vector22.b(a2).b(a3);
                vertexStruct2.a = vector22.b(a2).a(a3);
                vertexStruct.b = Vector2.a;
                vertexStruct2.b = Vector2.c;
                Vector4 a4 = vector4.a(0.5f);
                vertexStruct2.d = a4;
                vertexStruct.d = a4;
                vertexStruct.c = vector22;
                vertexStruct2.c = vector22;
                list2.add(vertexStruct);
                list2.add(vertexStruct2);
                for (int i7 = i4 + 1; i7 < i5; i7++) {
                    VertexStruct vertexStruct3 = new VertexStruct();
                    VertexStruct vertexStruct4 = new VertexStruct();
                    Vector2 vector25 = stroke2.e.get(i7);
                    vertexStruct3.a = vector25.b(a3);
                    vertexStruct4.a = vector25.a(a3);
                    vertexStruct3.b = new Vector2(0.0f, 0.5f);
                    vertexStruct4.b = new Vector2(1.0f, 0.5f);
                    Vector4 a5 = vector4.a(f3);
                    vertexStruct4.d = a5;
                    vertexStruct3.d = a5;
                    vertexStruct4.c = vector25;
                    vertexStruct3.c = vector25;
                    list2.add(vertexStruct3);
                    list2.add(vertexStruct4);
                }
                VertexStruct vertexStruct5 = new VertexStruct();
                VertexStruct vertexStruct6 = new VertexStruct();
                vertexStruct5.a = vector23.a(a2).b(a3);
                vertexStruct6.a = vector23.a(a2).a(a3);
                vertexStruct5.b = Vector2.d;
                vertexStruct6.b = Vector2.b;
                Vector4 a6 = vector4.a(0.5f);
                vertexStruct6.d = a6;
                vertexStruct5.d = a6;
                vertexStruct5.c = vector23;
                vertexStruct6.c = vector23;
                list2.add(vertexStruct5);
                list2.add(vertexStruct6);
                return;
            }
            throw new RuntimeException("Only the first channel of the Painter/Eraser intensity can be non-zero when using high byte precision textures");
        }
    }

    public final void a(Stroke stroke, int i2, int i3, boolean z2, List<Vector2> list) {
        int i4;
        Shader shader;
        Fbo fbo;
        if (stroke.a == PainterMode.PAINT) {
            shader = this.j != null ? this.v : this.u;
            i4 = this.r.h();
        } else if (this.k) {
            shader = this.j != null ? this.v : this.w;
            i4 = this.s.h();
        } else {
            shader = this.w;
            i4 = this.s.h();
        }
        GLES20.glUseProgram(shader.c());
        GLES20.glUniformMatrix4fv(shader.b("mvp"), 1, false, this.B, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i4);
        GLES20.glUniform1i(shader.b("brush"), 0);
        GLES20.glUniform2f(shader.b("imageSize"), (float) this.z, (float) this.A);
        if (shader == this.v) {
            GLES20.glUniform1f(shader.b("pointSize"), stroke.b);
            GLES20.glActiveTexture(33985);
            GLES20.glUniform1i(shader.b("image"), 1);
            Texture texture = this.j;
            if (texture != null) {
                GLES20.glBindTexture(3553, texture.h());
                GLES20.glUniform1f(shader.b("sigma"), this.c);
            } else {
                GLES20.glBindTexture(3553, this.t.h());
                GLES20.glUniform1f(shader.b("sigma"), 1000.0f);
            }
        }
        GLES20.glActiveTexture(33986);
        if (z2) {
            if (this.F) {
                fbo = this.p;
            } else {
                fbo = this.q;
            }
            GLES20.glBindTexture(3553, fbo.e().h());
        } else {
            GLES20.glBindTexture(3553, this.p.e().h());
        }
        GLES20.glUniform1i(shader.b("previous"), 2);
        if (shader == this.v) {
            GLES20.glBindBuffer(34962, this.y[0]);
        } else if (shader == this.u) {
            GLES20.glBindBuffer(34962, this.y[1]);
        } else {
            GLES20.glBindBuffer(34962, this.y[2]);
        }
        a(stroke, i2, i3, this.L);
        if (this.L.size() > 0) {
            float[] fArr = new float[((this.L.size() * VertexStruct.a()) / 4)];
            int i5 = 0;
            for (VertexStruct a2 : this.L) {
                a2.a(fArr, i5);
                i5 += VertexStruct.a() / 4;
            }
            int length = fArr.length * 4;
            ByteBuffer order = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
            order.asFloatBuffer().put(fArr);
            GLES20.glBufferData(34962, length, order, 35040);
            VertexStruct.b(shader);
            GLES20.glDrawArrays(5, 0, this.L.size());
            VertexStruct.a(shader);
        }
        GLES20.glBindBuffer(34962, 0);
        GLES20.glUseProgram(0);
        GLES20.glActiveTexture(33984);
        if (list != null) {
            list.clear();
            for (VertexStruct vertexStruct : this.L) {
                list.add(vertexStruct.a);
            }
        }
    }

    public void a(PainterMode painterMode) {
        this.b = painterMode;
    }

    public void a(Texture texture) {
        this.j = texture;
    }

    public void a(Vector4 vector4) {
        this.i = vector4;
    }

    public void a(float f2) {
        this.g = f2;
        a();
    }
}
