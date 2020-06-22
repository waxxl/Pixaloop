package com.example.pixaloop.common.render.gpu;

import android.opengl.GLES20;
import android.os.Build;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class GpuDeviceInfo {
    public static final List<String> a = new ImmutableList.Builder<String>().add((String) "PowerVR SGX 5.+").add((String) "VideoCore IV HW").build();
    public static final Map<String, Integer> b = new ImmutableMap.Builder<String, Integer>().put("Adreno \\(TM\\) 200", 2097152).put("Adreno \\(TM\\) 205", 4194304).put("Adreno \\(TM\\) 203", 5242880).put("Adreno \\(TM\\) 220", 5242880).put("Adreno \\(TM\\) 225", 5242880).put("Mali-300", 4194304).build();
    public final String A = GLES20.glGetString(35724);
    public final ImmutableSet<String> B = ImmutableSet.copyOf(GLES20.glGetString(7939).split(" "));
    public final ImmutableSet<String> C;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final int i;
    public final int j;
    public final int[] k = new int[2];
    public final int l;
    public final int m;
    public final int n;
    public final int o;
    public final int p;
    public final int q;
    public final int r;
    public final int[] s = new int[3];
    public final int[] t = new int[3];
    public final int[] u = new int[3];
    public final boolean v;
    public final int w;
    public final String x = GLES20.glGetString(7937);
    public final String y = GLES20.glGetString(7936);
    public final String z = GLES20.glGetString(7938);

    public GpuDeviceInfo(GpuContext gpuContext) {
        this.C = ImmutableSortedSet.copyOf(String.CASE_INSENSITIVE_ORDER, gpuContext.d());
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3379, iArr, 0);
        this.i = iArr[0];
        GLES20.glGetIntegerv(34024, iArr, 0);
        this.j = iArr[0];
        GLES20.glGetIntegerv(3386, this.k, 0);
        GLES20.glGetIntegerv(35661, iArr, 0);
        this.l = iArr[0];
        GLES20.glGetIntegerv(36349, iArr, 0);
        this.m = iArr[0];
        GLES20.glGetIntegerv(36347, iArr, 0);
        this.n = iArr[0];
        GLES20.glGetIntegerv(34930, iArr, 0);
        this.o = iArr[0];
        GLES20.glGetIntegerv(36348, iArr, 0);
        this.p = iArr[0];
        GLES20.glGetIntegerv(34921, iArr, 0);
        this.q = iArr[0];
        GLES20.glGetIntegerv(35660, iArr, 0);
        this.r = iArr[0];
        if (!Build.MANUFACTURER.contains("Genymotion")) {
            int[] iArr2 = this.s;
            GLES20.glGetShaderPrecisionFormat(35632, 36336, iArr2, 0, iArr2, 2);
            int[] iArr3 = this.t;
            GLES20.glGetShaderPrecisionFormat(35632, 36337, iArr3, 0, iArr3, 2);
            int[] iArr4 = this.u;
            GLES20.glGetShaderPrecisionFormat(35632, 36338, iArr4, 0, iArr4, 2);
        }
        this.e = this.B.contains("GL_OES_texture_half_float");
        this.f = this.B.contains("GL_OES_texture_float");
        this.c = this.B.contains("GL_EXT_color_buffer_half_float");
        this.d = this.B.contains("GL_EXT_color_buffer_float");
        this.g = this.B.contains("GL_OES_texture_npot");
        this.h = this.B.contains("GL_EXT_discard_framebuffer");
        this.v = true ^ a(this.x, a);
        this.w = a();
    }

    public static GpuDeviceInfo a(GpuContext gpuContext) {
        return new GpuDeviceInfo(gpuContext);
    }

    public boolean b() {
        return this.v;
    }

    public Set<String> c() {
        return this.C;
    }

    public Set<String> d() {
        return this.B;
    }

    public int e() {
        return this.l;
    }

    public int f() {
        return this.m;
    }

    public int g() {
        return this.o;
    }

    public int h() {
        return this.i;
    }

    public int i() {
        return this.p;
    }

    public int j() {
        return this.q;
    }

    public int k() {
        return this.r;
    }

    public int l() {
        return this.n;
    }

    public int m() {
        return this.k[1];
    }

    public int n() {
        return this.k[0];
    }

    public int o() {
        return this.w;
    }

    public String p() {
        return this.x;
    }

    public String q() {
        return this.y;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GpuDeviceInfo:\n");
        sb.append("\n vendor=");
        sb.append(this.y);
        sb.append("\n renderer=");
        sb.append(this.x);
        sb.append("\n version=");
        sb.append(this.z);
        sb.append("\n glsl=");
        sb.append(this.A);
        sb.append("\n maxTextureSize=");
        sb.append(this.i);
        sb.append("\n maxRenderbufferSize=");
        sb.append(this.j);
        sb.append("\n maxViewportDim=");
        sb.append(n());
        sb.append("X");
        sb.append(m());
        sb.append("\n recommendedTexturePixelsLimit=");
        sb.append(this.w);
        sb.append("\n halfFloatTextures=");
        sb.append(this.e);
        sb.append("\n floatTextures=");
        sb.append(this.f);
        sb.append("\n writeHalfFloatTextures=");
        sb.append(this.c);
        sb.append("\n writeFloatTextures=");
        sb.append(this.d);
        sb.append("\n sampleWriteSameTexture=");
        sb.append(b());
        sb.append("\n repeatNonPowerOfTwoTextures=");
        sb.append(this.g);
        sb.append("\n discardFramebuffer=");
        sb.append(this.h);
        sb.append("\n GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = ");
        sb.append(e());
        sb.append("\n GL_MAX_FRAGMENT_UNIFORM_VECTORS = ");
        sb.append(f());
        sb.append("\n GL_MAX_TEXTURE_IMAGE_UNITS = ");
        sb.append(g());
        sb.append("\n GL_MAX_VARYING_VECTORS = ");
        sb.append(i());
        sb.append("\n GL_MAX_VERTEX_ATTRIBS = ");
        sb.append(j());
        sb.append("\n GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = ");
        sb.append(k());
        sb.append("\n GL_MAX_VERTEX_UNIFORM_VECTORS = ");
        sb.append(l());
        sb.append("\n EGL Extensions:");
        UnmodifiableIterator<String> it = this.C.iterator();
        while (it.hasNext()) {
            sb.append("\n   ");
            sb.append(it.next());
        }
        sb.append("\n Extensions:");
        UnmodifiableIterator<String> it2 = this.B.iterator();
        while (it2.hasNext()) {
            sb.append("\n   ");
            sb.append(it2.next());
        }
        sb.append("\n Fragment shader float precision:");
        sb.append("\n   lowp: range 2^");
        sb.append(this.s[1]);
        sb.append(", precision 2^-");
        sb.append(this.s[2]);
        sb.append("\n   mediump: range 2^");
        sb.append(this.t[1]);
        sb.append(", precision 2^-");
        sb.append(this.t[2]);
        sb.append("\n   highp: range 2^");
        sb.append(this.u[1]);
        sb.append(", precision 2^-");
        sb.append(this.u[2]);
        return sb.toString();
    }

    public static boolean a(String str, List<String> list) {
        for (String matches : list) {
            if (Pattern.matches(matches, str)) {
                return true;
            }
        }
        return false;
    }

    public final int a() {
        int min = Math.min(h() * h(), m() * n());
        for (Map.Entry next : b.entrySet()) {
            if (this.x.matches((String) next.getKey())) {
                min = Math.min(min, ((Integer) next.getValue()).intValue());
            }
        }
        return min;
    }
}
