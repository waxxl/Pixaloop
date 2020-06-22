package com.example.pixaloop.common.render.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.pixaloop.R;

public class GenericGestureDetector {
    public final GestureDetector a;
    public final OnGestureListener b;
    public boolean c;
    public boolean d;
    public final float e;

    public interface OnGestureListener extends GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        void a();

        void a(MotionEvent motionEvent);

        void b();

        void b(MotionEvent motionEvent);
    }

    private class OnGestureListenerAdapter implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        public OnGestureListenerAdapter() {
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            return GenericGestureDetector.this.b.onDoubleTap(motionEvent);
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return GenericGestureDetector.this.b.onDoubleTapEvent(motionEvent);
        }

        public boolean onDown(MotionEvent motionEvent) {
            return GenericGestureDetector.this.b.onDown(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return GenericGestureDetector.this.b.onFling(motionEvent, motionEvent2, f, f2);
        }

        public void onLongPress(MotionEvent motionEvent) {
            boolean unused = GenericGestureDetector.this.c = true;
            GenericGestureDetector.this.b.onLongPress(motionEvent);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!GenericGestureDetector.this.d) {
                f = MathUtils.a(f, -GenericGestureDetector.this.e, GenericGestureDetector.this.e);
                f2 = MathUtils.a(f2, -GenericGestureDetector.this.e, GenericGestureDetector.this.e);
                GenericGestureDetector.this.b.a();
            }
            boolean unused = GenericGestureDetector.this.d = true;
            return GenericGestureDetector.this.b.onScroll(motionEvent, motionEvent2, f, f2);
        }

        public void onShowPress(MotionEvent motionEvent) {
            GenericGestureDetector.this.b.onShowPress(motionEvent);
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return GenericGestureDetector.this.b.onSingleTapConfirmed(motionEvent);
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return GenericGestureDetector.this.b.onSingleTapUp(motionEvent);
        }
    }

    public static class SimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener implements OnGestureListener {
        public void a() {
        }

        public void a(MotionEvent motionEvent) {
        }

        public void b() {
        }

        public void b(MotionEvent motionEvent) {
        }
    }

    public GenericGestureDetector(Context context, OnGestureListener onGestureListener) {
        this.b = onGestureListener;
        this.a = new GestureDetector(context, new OnGestureListenerAdapter());
        this.e = context.getResources().getDimension(R.dimen.max_first_scroll_distance);
    }

    public boolean a(MotionEvent motionEvent) {
        boolean onTouchEvent = this.a.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1) {
            if (this.c) {
                this.c = false;
                this.b.b(motionEvent);
            }
            if (this.d) {
                this.b.b();
                this.d = false;
            }
            this.b.a(motionEvent);
        }
        return onTouchEvent;
    }
}
