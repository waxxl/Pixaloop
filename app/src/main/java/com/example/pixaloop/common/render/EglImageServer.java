package com.example.pixaloop.common.render;

import android.util.Log;

import com.example.pixaloop.Cclass.C0078db;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.example.pixaloop.common.render.gpu.GpuContext;
import com.example.pixaloop.common.render.gpu.GpuDeviceInfo;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class EglImageServer implements ImageServer {
    public static final Set<String> a = new ImmutableSet.Builder().add((Object) "GL_OES_EGL_image").build();
    public static final Set<String> b = new ImmutableSet.Builder().add((Object) "EGL_KHR_gl_texture_2D_image").build();
    public final ListeningExecutorService c;
    public final Semaphore semaphore = new Semaphore(1);
    public GpuContext e;

    public EglImageServer(ListeningExecutorService listeningExecutorService) {
        this.c = listeningExecutorService;
        listeningExecutorService.execute(new C0078db(this));
    }

    public void a() {
        this.e = GpuContext.a();
        this.e.k();
    }

    public static boolean a(GpuDeviceInfo gpuDeviceInfo) {
        if (gpuDeviceInfo.p().matches("PowerVR SGX 544.*")) {
            Log.i("EglImageServer", "Possible EGLImage leak, EglImageServer is disabled.");
            return false;
        }
        Set<String> c2 = gpuDeviceInfo.c();
        if (!(c2.contains("EGL_KHR_image_base") || c2.contains("EGL_KHR_image")) || !gpuDeviceInfo.d().containsAll(a) || !c2.containsAll(b)) {
            return false;
        }
        return true;
    }
}
