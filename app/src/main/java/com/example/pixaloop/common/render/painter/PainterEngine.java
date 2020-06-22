//package com.example.pixaloop.common.render.painter;
//
//import android.graphics.PointF;
//import android.graphics.RectF;
//import android.os.Handler;
//import android.os.Message;
//import com.example.pixaloop.common.render.gpu.Vector2;
//import com.example.pixaloop.common.render.painter.Painter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PainterEngine {
//    public final Handler a;
//    public final Callback b;
//
//    /* renamed from: com.example.pixaloop.common.render.painter.PainterEngine$1  reason: invalid class name */
//    class AnonymousClass1 implements Handler.Callback {
//        public final /* synthetic */ PainterEngine a;
//
//        public boolean handleMessage(Message message) {
//            if (message.what != 1) {
//                return false;
//            }
//            PaintCallbackData paintCallbackData = (PaintCallbackData) message.obj;
//            this.a.b.a(paintCallbackData.a, paintCallbackData.b, paintCallbackData.c);
//            return true;
//        }
//    }
//
//    /* renamed from: com.example.pixaloop.common.render.painter.PainterEngine$2  reason: invalid class name */
//    class AnonymousClass2 implements Handler.Callback {
//        public final /* synthetic */ PainterEngine a;
//
//        public boolean handleMessage(Message message) {
//            int i = message.what;
//            if (i == 1) {
//                StartStrokeData startStrokeData = (StartStrokeData) message.obj;
//                startStrokeData.a.a((double) startStrokeData.b);
//            } else if (i == 2) {
//                ((Painter) message.obj).d();
//            } else if (i == 3) {
//                PaintSegmentData paintSegmentData = (PaintSegmentData) message.obj;
//                ArrayList arrayList = this.a.b != null ? new ArrayList() : null;
//                RectF a2 = paintSegmentData.a.a(paintSegmentData.b, paintSegmentData.c, arrayList);
//                if (this.a.b != null) {
//                    this.a.a.obtainMessage(1, new PaintCallbackData(paintSegmentData.a, a2, arrayList)).sendToTarget();
//                }
//            } else if (i != 5) {
//                return false;
//            } else {
//                PaintChangeModeData paintChangeModeData = (PaintChangeModeData) message.obj;
//                paintChangeModeData.a.a(paintChangeModeData.b);
//            }
//            return true;
//        }
//    }
//
//    public interface Callback {
//        void a(Painter painter, RectF rectF, List<Vector2> list);
//    }
//
//    private static class PaintCallbackData {
//        public final Painter a;
//        public final RectF b;
//        public final List<Vector2> c;
//
//        public PaintCallbackData(Painter painter, RectF rectF, List<Vector2> list) {
//            this.a = painter;
//            this.b = rectF;
//            this.c = list;
//        }
//    }
//
//    private static class PaintChangeModeData {
//        public final Painter a;
//        public final Painter.PainterMode b;
//    }
//
//    private static class PaintSegmentData {
//        public final Painter a;
//        public final PointF b;
//        public final PointF c;
//    }
//
//    private static class StartStrokeData {
//        public final Painter a;
//        public final float b;
//    }
//}
