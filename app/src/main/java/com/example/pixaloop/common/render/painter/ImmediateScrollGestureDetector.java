//package com.example.pixaloop.common.render.painter;
//
//import android.graphics.PointF;
//import android.util.Log;
//import android.view.MotionEvent;
//
//public class ImmediateScrollGestureDetector {
//    public final OnImmediateScrollGestureListener a;
//    public final PointF b = new PointF();
//    public final PointF c = new PointF();
//    public final float d;
//    public final float e;
//    public float f;
//    public boolean g;
//
//    public interface OnImmediateScrollGestureListener {
//        void a(PointF pointF, PointF pointF2) {
//        }
//
//        void b(PointF pointF) {
//        }
//
//        void h() {
//        }
//    }
//
//    public ImmediateScrollGestureDetector(float f2, float f3, OnImmediateScrollGestureListener onImmediateScrollGestureListener) {
//        this.a = onImmediateScrollGestureListener;
//        this.d = f2;
//        this.e = f3;
//    }
//
//    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
//        if (r0 != 6) goto L_0x00c1;
//     */
//    public boolean a(MotionEvent motionEvent) {
//        int actionMasked = motionEvent.getActionMasked();
//        if (actionMasked != 0) {
//            if (actionMasked != 1) {
//                if (actionMasked != 2) {
//                    if (actionMasked == 3) {
//                        Log.i("ImmediateScrollGestureDetector", "Received ACTION_CANCEL, pretending it's ACTION_UP");
//                    } else if (actionMasked != 5) {
//                    }
//                } else if (this.g && motionEvent.getPointerCount() == 1) {
//                    this.c.x = motionEvent.getX();
//                    this.c.y = motionEvent.getY();
//                    PointF pointF = this.c;
//                    float f2 = pointF.x;
//                    PointF pointF2 = this.b;
//                    if (((float) Math.hypot((double) (f2 - pointF2.x), (double) (pointF.y - pointF2.y))) > this.f) {
//                        OnImmediateScrollGestureListener onImmediateScrollGestureListener = this.a;
//                        PointF pointF3 = this.b;
//                        PointF pointF4 = new PointF(pointF3.x, pointF3.y);
//                        PointF pointF5 = this.c;
//                        onImmediateScrollGestureListener.a(pointF4, new PointF(pointF5.x, pointF5.y));
//                        this.b.set(this.c);
//                        this.f = this.e;
//                    }
//                }
//                return true;
//            }
//            if (this.g) {
//                this.a.h();
//                this.g = false;
//            }
//            return true;
//        }
//        if (motionEvent.getPointerCount() <= 1) {
//            if (this.g) {
//                this.a.h();
//            }
//            this.b.x = motionEvent.getX();
//            this.b.y = motionEvent.getY();
//            OnImmediateScrollGestureListener onImmediateScrollGestureListener2 = this.a;
//            PointF pointF6 = this.b;
//            onImmediateScrollGestureListener2.b(new PointF(pointF6.x, pointF6.y));
//            this.f = this.d;
//            this.g = true;
//        }
//        return true;
//    }
//}
