package com.example.pixaloop.common.render.gpu;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.example.pixaloop.common.render.DisposableResource;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nullable;

public class GpuContext implements DisposableResource {
    public static final int[] a = {12440, 3, 12344};
    public EGLDisplay b = EGL14.eglGetDisplay(0);
    public EGLConfig c;
    public EGLContext d;
    public EGLSurface e;
    public EGLSurface f;
    public String g;
    public String h;
    public ImmutableSet<String> mImmutableSet;

    @Override
    public void close() throws Exception {
        dispose();
    }

    public static class Configuration {
        public final int a;
        public final int b;
        public final boolean c;

        public Configuration(int i, int i2, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = z;
        }

        public static Configuration a() {
            return new Configuration(1, 1, false);
        }

        public Configuration b() {
            return new Configuration(this.a, this.b, true);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Configuration.class != obj.getClass()) {
                return false;
            }
            Configuration configuration = (Configuration) obj;
            if (this.a != configuration.a || this.b != configuration.b) {
                return false;
            }
            if (this.c == configuration.c) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.a * 31) + this.b) * 31) + (this.c ? 1 : 0);
        }

        public String toString() {
//            return MoreObjects.a("Configuration").a("offscreenSurfaceWidth", this.a).a("offscreenSurfaceHeight", this.b).a("recordable", this.c).toString();
            return "";
        }
    }

    public GpuContext(Configuration configuration) {
        EGLDisplay eGLDisplay = this.b;
        if (eGLDisplay != EGL14.EGL_NO_DISPLAY) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            if (EGL14.eglInitialize(eGLDisplay, iArr, 0, iArr2, 0)) {
                this.mImmutableSet = ImmutableSortedSet.copyOf(String.CASE_INSENSITIVE_ORDER, Splitter.on(' ').splitToList((CharSequence) EGL14.eglQueryString(this.b, 12373).trim()));
                int[] a2 = a(iArr[0] > 1 || (iArr[0] == 1 && iArr2[0] >= 5) || this.mImmutableSet.contains("EGL_KHR_create_context"), configuration.c && this.mImmutableSet.contains("EGL_ANDROID_recordable"));
                int[] iArr3 = new int[1];
                if (!EGL14.eglChooseConfig(this.b, a2, 0, new EGLConfig[1], 0, 1, iArr3, 0)) {
                    throw new RuntimeException("eglChooseConfig() failed");
                } else if (iArr3[0] > 0) {
                    EGLConfig[] eGLConfigArr = new EGLConfig[iArr3[0]];
                    if (!EGL14.eglChooseConfig(this.b, a2, 0, eGLConfigArr, 0, eGLConfigArr.length, iArr3, 0)) {
                        throw new RuntimeException("eglChooseConfig() failed to set FB config");
                    } else if (iArr3[0] == eGLConfigArr.length) {
                        this.c = eGLConfigArr[0];
                        this.d = EGL14.eglCreateContext(this.b, this.c, EGL14.EGL_NO_CONTEXT, a, 0);
                        if (this.d != EGL14.EGL_NO_CONTEXT) {
                            this.e = EGL14.eglCreatePbufferSurface(this.b, this.c, new int[]{12375, configuration.a, 12374, configuration.b, 12344}, 0);
                            if (this.e != EGL14.EGL_NO_SURFACE) {
                                this.g = EGL14.eglQueryString(this.b, 12371);
                                this.h = EGL14.eglQueryString(this.b, 12372);
                                return;
                            }
                            throw new RuntimeException("eglCreatePbufferSurface() failed");
                        }
                        throw new RuntimeException("eglCreateContext() failed");
                    } else {
                        throw new RuntimeException("eglChooseConfig() returned inconsistent number of configs");
                    }
                } else {
                    throw new RuntimeException("eglChooseConfig() returned no valid FB configs");
                }
            } else {
                throw new RuntimeException("eglInitialize() failed");
            }
        } else {
            throw new RuntimeException("eglGetDisplay() failed");
        }
    }

    public static GpuContext a() {
        return new GpuContext(Configuration.a());
    }

    public static GpuContext b() {
        return a(Configuration.a().b());
    }

    public int c() {
        return EGL14.eglGetError();
    }

    public Set<String> d() {
        return this.mImmutableSet;
    }

    public void dispose() {
        if (this.b != null) {
            m();
            EGLSurface eGLSurface = this.e;
            if (!(eGLSurface == null || eGLSurface == EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(this.b, eGLSurface);
                this.e = null;
            }
            EGLSurface eGLSurface2 = this.f;
            if (!(eGLSurface2 == null || eGLSurface2 == EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(this.b, eGLSurface2);
                this.f = null;
            }
            EGL14.eglTerminate(this.b);
            this.b = null;
        }
    }

    @Nullable
    public Object e() {
        return this.f;
    }

    public int f() {
        EGLSurface eGLSurface = this.f;
        if (eGLSurface == null) {
            eGLSurface = this.e;
        }
        return a(eGLSurface, 12374);
    }

    public int g() {
        EGLSurface eGLSurface = this.f;
        if (eGLSurface == null) {
            eGLSurface = this.e;
        }
        return a(eGLSurface, 12375);
    }

    public String h() {
        return this.g;
    }

    public String i() {
        return this.h;
    }

    public boolean j() {
        return Objects.equals(this.d, EGL14.eglGetCurrentContext());
    }

    public void k() {
        EGLSurface eGLSurface = this.f;
        if (eGLSurface == null) {
            eGLSurface = this.e;
        }
        if (!EGL14.eglMakeCurrent(this.b, eGLSurface, eGLSurface, this.d)) {
            throw new RuntimeException("eglMakeCurrent() failed");
        }
    }

    public void l() {
        EGLDisplay eGLDisplay = this.b;
        EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT)) {
            throw new RuntimeException("eglMakeCurrent() failed");
        }
    }

    public void m() {
        if (j()) {
            l();
        }
    }

    public void n() {
        EGLSurface eGLSurface = this.f;
        if (eGLSurface == null) {
            eGLSurface = this.e;
        }
        if (!EGL14.eglSwapBuffers(this.b, eGLSurface)) {
            throw new RuntimeException("eglSwapBuffers() failed");
        }
    }

    public void o() {
        if (this.f != null) {
            boolean j = j();
            if (j) {
                l();
            }
            if (EGL14.eglDestroySurface(this.b, this.f)) {
                this.f = null;
                if (j) {
                    k();
                    return;
                }
                return;
            }
            throw new RuntimeException("eglDestroySurface() failed");
        }
    }

    public static GpuContext a(Configuration configuration) {
        return new GpuContext(configuration);
    }

    public void a(Object obj) {
        if (obj != null) {
            boolean j = j();
            if (this.f != null) {
                if (j) {
                    l();
                }
                if (EGL14.eglDestroySurface(this.b, this.f)) {
                    this.f = null;
                } else {
                    throw new RuntimeException("eglDestroySurface() failed");
                }
            }
            this.f = EGL14.eglCreateWindowSurface(this.b, this.c, obj, new int[]{12344}, 0);
            if (this.f == EGL14.EGL_NO_SURFACE) {
                throw new RuntimeException("eglCreateWindowSurface() failed");
            } else if (j) {
                k();
            }
        }
    }

    public void a(long j) {
        EGLSurface eGLSurface = this.f;
        if (eGLSurface != null && !EGLExt.eglPresentationTimeANDROID(this.b, eGLSurface, j)) {
            throw new RuntimeException("setPresentationTime() failed");
        }
    }

    public final int a(EGLSurface eGLSurface, int i2) {
        int[] iArr = new int[1];
        if (EGL14.eglQuerySurface(this.b, eGLSurface, i2, iArr, 0)) {
            return iArr[0];
        }
        throw new RuntimeException("eglQuerySurface() failed");
    }

    public static int[] a(boolean z, boolean z2) {
        int[] iArr = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12610, -1, 12339, 5, 12344};
        if (z) {
            iArr[1] = 64;
        }
        if (z2) {
            iArr[11] = 1;
        }
        return iArr;
    }
}
