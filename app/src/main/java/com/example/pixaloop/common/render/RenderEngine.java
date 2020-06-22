package com.example.pixaloop.common.render;

import android.annotation.SuppressLint;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import com.example.pixaloop.Cclass.C0085eb;
import com.example.pixaloop.Cclass.C0092fb;
import com.example.pixaloop.Cclass.C0099gb;
import com.example.pixaloop.Cclass.C0106hb;
import com.example.pixaloop.Cclass.C0113ib;
import com.example.pixaloop.Cclass.C0119jb;
import com.example.pixaloop.Cclass.Xa;
import com.example.pixaloop.Cclass.Ya;
import com.example.pixaloop.common.render.gpu.GpuContext;
import com.example.pixaloop.common.render.gpu.GpuDeviceInfo;
import com.example.pixaloop.common.render.utils.HandlerExecutorService;
import com.example.pixaloop.common.render.utils.SyncRunnable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class RenderEngine {
    public static RenderEngine a;
    public HandlerThread b;
    public Handler c;
    public final Handler d = new Handler(Looper.getMainLooper());
    public RenderEngineDelegate e;
    public ListeningExecutorService f;
    public ListenableFuture<ImageServer> g;
    public GpuContext h;
    public int i;
    public int j;
    public final AtomicBoolean k = new AtomicBoolean();
    public ListenableFuture<GpuDeviceInfo> l;
    public int m;
    public final Runnable n = new Xa(this);

    private class CompletionRunnable implements Runnable {
        public final Runnable a;
        public final Runnable b;

        public CompletionRunnable(Runnable runnable, Runnable runnable2) {
            this.a = runnable;
            this.b = runnable2;
        }

        public void run() {
            try {
                this.a.run();
            } finally {
                RenderEngine.this.d.post(this.b);
            }
        }
    }

    public interface RenderEngineDelegate {
        void a();

        void a(int i, int i2);

        void b();

        void c();
    }

    public static RenderEngine b() {
        synchronized (RenderEngine.class) {
            if (a == null) {
                a = new RenderEngine();
                a.m();
            }
        }
        return a;
    }

    @NonNull
    public GpuDeviceInfo c() {

        return (GpuDeviceInfo) Futures.getUnchecked(this.l);
    }

    public ListenableFuture<GpuDeviceInfo> d() {

        return this.l;
    }

    public Looper e() {
        return this.c.getLooper();
    }

    public void post(@NonNull RenderEngineDelegate renderEngineDelegate) {
        post((Runnable) new C0119jb(this, renderEngineDelegate));
    }

    public final void g() {
        this.k.set(false);
        RenderEngineDelegate renderEngineDelegate = this.e;
        if (renderEngineDelegate != null) {
            renderEngineDelegate.a();
            try {
                this.h.n();
                this.m = 0;
            } catch (RuntimeException e2) {
                Log.e("RenderEngine", "Error in eglSwapBuffers: " + e2.getMessage());
                int c2 = this.h.c();
                if (c2 != 12288) {
                    if (c2 == 12299 || c2 == 12301) {
                        Log.w("RenderEngine", "Ignoring failed eglSwapBuffers due to a bleak hope of one time race in surface destruction. Eyes closed and fingers crossed: please go away, please go away!");
                    }
                    this.m++;
                    if (this.m >= 5) {
                        throw new RuntimeException("Error in eglSwapBuffers. Egl error is:" + c2, e2);
                    }
                    return;
                }
                Log.w("RenderEngine", "Ignoring failed eglSwapBuffers due to lack of EGL errors");
            }
        }
    }

    public boolean h() {
        return Looper.myLooper() == e();
    }

    public /* synthetic */ GpuDeviceInfo i() {
        return GpuDeviceInfo.a(this.h);
    }

    public void j() {
        if (this.k.compareAndSet(false, true)) {
            post((Runnable) new Xa(this));
        }
    }

    public void k() {
        c(this.n);
    }

//    public Scheduler l() {
//        return AndroidSchedulers.a(this.b.getLooper());
//    }

    public void m() {
        if (this.b != null) {
            Log.w("RenderEngine", "Starting already started engine");
            return;
        }
        this.b = new HandlerThread("RenderEngine");
        this.b.start();
        this.c = new Handler(this.b.getLooper());
        this.f = new HandlerExecutorService(this.c);
        post((Runnable) new C0085eb(this));
        this.l = a(new C0099gb(this));
        this.g = Futures.transform(this.l, new Ya(this), MoreExecutors.directExecutor());
    }

    public final ImageServer a(GpuDeviceInfo gpuDeviceInfo) {
        if (EglImageServer.a(gpuDeviceInfo)) {
            Log.d("RenderEngine", "EGL Image server supported, starting");
            return new EglImageServer(MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor()));
        }
        Log.d("RenderEngine", "Using compatibility image server");
        return new CompatImageServer();
    }

    @SuppressLint("RestrictedApi")
    public void e(@NonNull RenderEngineDelegate renderEngineDelegate) {
        Preconditions.checkNotNull(renderEngineDelegate);
        post((Runnable) new C0113ib(this, renderEngineDelegate));
    }

    public final void f() {
        Log.d("RenderEngine", "Creating OpenGL context");
        this.h = GpuContext.a();
        Log.d("RenderEngine", "Have new context: " + this.h.toString());
        Log.d("RenderEngine", "EGL Version: " + this.h.i());
        Log.d("RenderEngine", "EGL Vendor: " + this.h.h());
        c((Object) null, 1, 1);
    }

    public void b(Runnable runnable) {
        if (h()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public void c(Runnable runnable) {
        if (h()) {
            runnable.run();
            return;
        }
        ConditionVariable conditionVariable = new ConditionVariable();
        this.c.post(new SyncRunnable(runnable, conditionVariable));
        conditionVariable.block();
    }

    public void a(Object obj, int i2, int i3) {
        post((Runnable) new C0106hb(this, obj, i2, i3));
    }

    /* renamed from: b */
    public final void c(Object obj, int i2, int i3) {
        Log.d("RenderEngine", "New surface: " + obj + " " + i2 + "x" + i3);
        if (obj != null) {
            this.h.a(obj);
        } else {
            this.h.o();
        }
        this.h.k();
        int g2 = this.h.g();
        int f2 = this.h.f();
        if (!(i2 == g2 && i3 == f2)) {
            Log.w("RenderEngine", "Mismatch in surface size: reported: " + i2 + "x" + i3 + " queried by egl: " + g2 + "x" + f2);
            Log.w("RenderEngine", "Troubles ahead...");
        }
        this.i = i2;
        this.j = i3;
        Log.d("RenderEngine", "Have new dimensions: " + this.i + "x" + this.j);
        RenderEngineDelegate renderEngineDelegate = this.e;
        if (renderEngineDelegate != null) {
            renderEngineDelegate.a(this.i, this.j);
        }
    }

    public void a(Object obj) {
        c((Runnable) new C0092fb(this, obj));
    }

    public void post(Runnable runnable) {
        this.c.post(runnable);
    }

    public void post(Runnable runnable, Runnable runnable2) {
        post((Runnable) new CompletionRunnable(runnable, runnable2));
    }

    public <T> ListenableFuture<T> a(Callable<T> callable) {
        ListenableFutureTask<T> a2 = ListenableFutureTask.create(callable);
        post((Runnable) a2);
        return a2;
    }

    public void check() {
        if (!h()) {
            throw new RuntimeException("Caller is *NOT* on the render thread");
        }
    }

    /* renamed from: a */
    public final void d(RenderEngineDelegate renderEngineDelegate) {
        Log.d("RenderEngine", "Changing delegates: old=" + this.e + " new=" + renderEngineDelegate);
        RenderEngineDelegate renderEngineDelegate2 = this.e;
        if (renderEngineDelegate2 != null) {
            c(renderEngineDelegate2);
        }
        this.e = renderEngineDelegate;
        this.e.b();
        this.e.a(this.i, this.j);
    }

    public final void c(Object obj) {
        Log.d("RenderEngine", "Removing surface");
        if (Objects.equals(obj, this.h.e())) {
            c((Object) null, 1, 1);
        }
    }

    public final void c(RenderEngineDelegate renderEngineDelegate) {
        Log.d("RenderEngine", "Removing delegate: " + renderEngineDelegate);
        if (this.e != renderEngineDelegate) {
            Log.w("RenderEngine", "Remove delegate " + renderEngineDelegate + " while current delegate is " + this.e + ". Ignoring");
        } else if (renderEngineDelegate != null) {
            renderEngineDelegate.c();
            this.e = null;
        }
    }
}
