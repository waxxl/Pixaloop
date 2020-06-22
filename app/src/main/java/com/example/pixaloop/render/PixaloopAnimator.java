package com.example.pixaloop.render;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pixaloop.Cclass.C0091eh;
import com.example.pixaloop.Cclass.C0105gh;
import com.example.pixaloop.Cclass.C0125jh;
import com.example.pixaloop.R;
import com.example.pixaloop.common.render.AnimationClock;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.features.AnimateModel;
import com.example.pixaloop.features.ProjectWithImage;
import com.example.pixaloop.features.SessionState;
import com.example.pixaloop.remoteResources.RemoteAssetsManager;

import java.lang.reflect.Field;
import java.util.Locale;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class PixaloopAnimator implements DisposableResource {
    public final MutableLiveData a = new MutableLiveData();
    public final MutableLiveData<RenderingResources> b = new MutableLiveData<>();
    public final MutableLiveData<Boolean> c = new MutableLiveData<>();
    public final Context d;
    public final RemoteAssetsManager e;
    public final int f;
    public final int g;
    public PixaloopRenderer pixaloopRenderer;
    public AnimationClock i;
    public AnimationClock j;
    public volatile float k = 0.0f;
    public volatile float l = 0.0f;
    public volatile boolean m = false;
    public RenderingResources n;
    public final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PixaloopAnimator(Context context, RemoteAssetsManager remoteAssetsManager) {
        this.d = context;
        this.e = remoteAssetsManager;
        this.f = context.getResources().getInteger(R.integer.animate_duration_master_period);
        this.g = context.getResources().getInteger(R.integer.animate_duration_frequency);
        this.c.setValue(false);
        a();
        this.i = new AnimationClock(new Handler(Looper.getMainLooper()), (long) f);
        this.j = new AnimationClock(new Handler(Looper.getMainLooper()), (long) (f * 2));
//        this.compositeDisposable.add(this.i.b().doOnNext((Consumer<? super Float>) C0077ch.a).doOnSubscribe(new C0098fh(this)));
//        this.compositeDisposable.add(this.j.b().doOnNext((Consumer<? super Float>) C0118ih.a).b(new C0084dh(this)));
    }

    public final void a() {
        float f2 = Settings.Global.getFloat(this.d.getContentResolver(), "animator_duration_scale", 1.0f);
        if (f2 != 1.0f) {
            Log.e("PixaloopAnimator", String.format(Locale.ENGLISH, "Animations on device are scaled by a factor of %f. Trying to override this.", new Object[]{Float.valueOf(f2)}));
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                declaredField.set(new ValueAnimator(), Float.valueOf(1.0f));
                declaredField.setAccessible(false);
            } catch (Exception e2) {
                Log.e("PixaloopAnimator", "Failed to override animation scaling.", (Throwable) e2);
            }
        }
    }

    public void setTexture(RenderingResources resources) {

        if (pixaloopRenderer != null) {
            pixaloopRenderer.dispose();
            this.pixaloopRenderer = null;
        }
//        n  = resources;
//        this.pixaloopRenderer = new PixaloopRenderer(this.d, this.e, projectWithImage.a.b().b(), this.n.a);
    }

    public LiveData<RenderingResources> d() {
        return this.b;
    }

    public void dispose() {
        this.compositeDisposable.dispose();
        RenderEngine.b().post((Runnable) new C0105gh(this));
    }

    public LiveData<Boolean> e() {
        return this.c;
    }

    public /* synthetic */ void f() {
        RenderingResources renderingResources = this.n;
        if (renderingResources != null) {
            renderingResources.dispose();
        }
        PixaloopRenderer pixaloopRenderer = this.pixaloopRenderer;
        if (pixaloopRenderer != null) {
            pixaloopRenderer.dispose();
        }
    }

    public void g() {
        this.i.f();
        this.j.f();
        this.c.setValue(true);
    }

    public final void h() {
        if (this.c.getValue() == null || !this.c.getValue().booleanValue()) {
            this.a.setValue(null);
        }
    }

    public void i() {
        RenderEngine.b().check();
        if (this.pixaloopRenderer != null) {
            this.pixaloopRenderer.a(this.k, this.l, (long) (this.j.a() * ((double) this.j.b().blockingFirst().floatValue()) * 10000.0d), this.m && !e().getValue().booleanValue());
        }
    }

    public void j() {
        this.i.g();
        this.j.g();
        this.c.setValue(false);
    }

    public void b(Float f2) {
        this.k = f2.floatValue();
        this.a.setValue(null);
    }

    public void c(Float f2) {
        this.l = f2.floatValue();
        this.a.setValue(null);
    }

//    public Completable b(ProjectWithImage projectWithImage) {
//        return Single.a(projectWithImage).a(RenderEngine.b().l()).c(new C0132kh(this)).a(AndroidSchedulers.a()).b(new _g(this));
//    }

    public LiveData<Void> c() {
        return this.a;
    }

//    public final Completable b(RenderingResources renderingResources) {
//        return Completable.a((Runnable) new C0112hh(this, renderingResources));
//    }

//    public LiveData<Float> b() {
//        return LiveDataReactiveStreams.a(this.j.b().d(C0070bh.a).a(BackpressureStrategy.LATEST).a((Consumer<? super Throwable>) C0063ah.a));
//    }

    public final RenderingResources a(ProjectWithImage projectWithImage) {
        RenderingResources renderingResources = this.n;
        if (renderingResources != null) {
            renderingResources.dispose();
            this.b.setValue(null);
        }
        if (pixaloopRenderer != null) {
            pixaloopRenderer.dispose();
            this.pixaloopRenderer = null;
        }
        this.n = new RenderingResources(projectWithImage.b);
        this.pixaloopRenderer = new PixaloopRenderer(this.d, this.e, projectWithImage.a.b().b(), this.n.a);
        return this.n;
    }

    public final RenderingResources setData(Bitmap bitmap, SessionState sessionState) {
        RenderingResources renderingResources = this.n;
        if (renderingResources != null) {
            renderingResources.dispose();
            this.b.setValue(null);
        }
        if (pixaloopRenderer != null) {
            pixaloopRenderer.dispose();
            this.pixaloopRenderer = null;
        }
        this.n = new RenderingResources(bitmap);
        this.pixaloopRenderer = new PixaloopRenderer(this.d, this.e, sessionState, this.n.a);
        return this.n;
    }

    public void b(SessionState sessionState, boolean z) {
        this.i.b(AnimateModel.a(this.f, this.g, sessionState.c().i()));
        RenderEngine.b().post(new C0091eh(this, sessionState, z), new C0125jh(this));
    }

    public void a(RenderingResources renderingResources) {
        this.b.setValue(renderingResources);
    }

    public /* synthetic */ void a(SessionState sessionState, boolean z) {
        if (pixaloopRenderer != null) {
            pixaloopRenderer.b(sessionState, z);
        }
    }

    public void a(boolean z) {
        this.m = z;
        h();
    }

    @Override
    public void close() throws Exception {
        dispose();
    }

    public void test() {
        RenderEngine.b().post(new Runnable() {
            @Override
            public void run() {
                Log.d("xxl", "test pixaloopRenderer.e();");
                if (pixaloopRenderer != null) {
                    pixaloopRenderer.e();
                }
            }
        });
        Log.d("xxl", "test ;");

    }
}
