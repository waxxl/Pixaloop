package com.example.pixaloop.common.render.ltview;

import android.content.Context;
import android.graphics.RectF;
import android.opengl.GLES30;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.Texture;
import org.opencv.core.Scalar;

public class LTView extends SurfaceView implements SurfaceHolder.Callback2 {
    public final RenderEngine renderEngine = RenderEngine.b();
    public NavigationModel b = new NavigationModel();
    public NavigationModel c = new NavigationModel();
    //public final BehaviorSubject<NavigationModel> navigationModelBehaviorSubject = BehaviorSubject.n();
    public final LTViewRenderer renderer = new LTViewRenderer(this);
    public final Navigator f = new Navigator(this);
    public NavigationMode g;
    public TouchDelegate h;
    public boolean i;

    public enum NavigationMode {
        NONE,
        FULL,
        TWO_FINGERS,
        NONE_BOUNCE_BACK,
        FULL_BOUNCE_BACK
    }

    public interface TouchDelegate {
        boolean a(LTView lTView, MotionEvent motionEvent);
    }

    public LTView(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        if (!isInEditMode()) {
            this.g = NavigationMode.NONE;
            this.f.a(this.g);
            this.i = false;
            getHolder().setFormat(2);
            getHolder().addCallback(this);
        }
    }

    public void b() {
        this.renderer.h();
    }

    public void c() {
        this.renderer.b((RectF) null);
    }

    public synchronized NavigationModel getCurrentFrameNavigationModel() {
        return this.b;
    }

//    public Observable<NavigationModel> getCurrentNavigationModelObservable() {
//        return this.navigationModelBehaviorSubject.a(AndroidSchedulers.a());
//    }

    public NavigationMode getNavigationMode() {
        return this.g;
    }

    public synchronized NavigationModel getNextFrameNavigationModel() {
        return this.c;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        TouchDelegate touchDelegate;
        boolean onTouch = this.f.onTouch(this, motionEvent);
        if (!this.i || (touchDelegate = this.h) == null) {
            return onTouch;
        }
        return touchDelegate.a(this, motionEvent) || onTouch;
    }

    public void setBackgroundColors(Pair<Scalar, Scalar> pair) {
        this.renderEngine.post((Runnable) new C0147nb(this, pair));
        b();
    }

    public void setContent(Texture texture) {
        NavigationModel nextFrameNavigationModel = getNextFrameNavigationModel();
        this.f.a(new NavigationModel().j(nextFrameNavigationModel.k()).i(nextFrameNavigationModel.d()).h(texture != null ? texture.e() : null).b(nextFrameNavigationModel.d).a(nextFrameNavigationModel.c));
        this.renderEngine.c((Runnable) new C0154ob(this, texture));
    }

    public void setDrawDelegate(DrawDelegate drawDelegate) {
        this.renderEngine.b((Runnable) new C0140mb(this, drawDelegate));
    }

    public void setFeatureTouchDelegate(TouchDelegate touchDelegate) {
        this.h = touchDelegate;
        this.i = this.h != null;
    }

    public void setNavigationMode(NavigationMode navigationMode) {
        this.g = navigationMode;
        this.f.a(navigationMode);
    }

    public void setNeedsDisplay(RectF rectF) {
        this.renderer.a(rectF);
    }

    public void setNeedsDisplayContent(RectF rectF) {
        this.renderer.b(rectF);
    }

    public synchronized void setNextFrameNavigationModel(NavigationModel navigationModel) {
        this.c = navigationModel;
    }

    public void setTouchDelegateEnabled(boolean z) {
        this.i = z;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        Log.d("LTView", "surfaceChanged: " + surfaceHolder.getSurface() + " format=0x" + Integer.toHexString(i2) + " " + i3 + "x" + i4);
        this.renderEngine.a(surfaceHolder, i3, i4);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("LTView", "surfaceCreated: " + surfaceHolder.getSurface());
        this.renderEngine.post(this.renderer);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d("LTView", "surfaceDestroyed: " + surfaceHolder.getSurface());
        this.renderEngine.e(this.renderer);
        this.renderEngine.a((Object) surfaceHolder);
    }

    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
        this.renderEngine.k();
    }

    public void a(Texture texture) {
        GLES30.glFinish();
        this.renderer.a(texture);
    }

    public LTView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void a(Pair pair) {
        this.renderer.a((Pair<Scalar, Scalar>) pair);
    }

    public synchronized void a() {
        this.b = this.c;
        //this.navigationModelBehaviorSubject.a(this.b);
    }

    public /* synthetic */ void a(DrawDelegate drawDelegate) {
        this.renderer.a(drawDelegate);
    }

    public LTView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }
}
