package com.example.pixaloop.render;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.opengl.GLES30;
import android.renderscript.Float3;
import android.renderscript.Matrix3f;
import android.renderscript.Matrix4f;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pixaloop.R;
import com.example.pixaloop.common.render.DisposableResource;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.AttributeData;
import com.example.pixaloop.common.render.gpu.DynamicDrawer;
import com.example.pixaloop.common.render.gpu.Fbo;
import com.example.pixaloop.common.render.gpu.GpuStruct;
import com.example.pixaloop.common.render.gpu.GpuStructField;
import com.example.pixaloop.common.render.gpu.Shader;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.gpu.Vector4;
import com.example.pixaloop.common.render.painter.Brush;

import com.example.pixaloop.common.render.painter.Painter;
import com.example.pixaloop.common.render.painter.PainterParams;
import com.example.pixaloop.common.render.utils.Size;
import com.example.pixaloop.features.AdjustModel;
import com.example.pixaloop.features.AnimateModel;
import com.example.pixaloop.features.CameraFxModel;
import com.example.pixaloop.features.OverlayItemModel;
import com.example.pixaloop.features.OverlayModel;
import com.example.pixaloop.features.PathArrowModel;
import com.example.pixaloop.features.SessionState;
import com.example.pixaloop.features.SkyModel;
import com.example.pixaloop.other.BezierSpline;
import com.example.pixaloop.other.PainterMode;
import com.example.pixaloop.remoteResources.RemoteAssetsManager;
import com.example.pixaloop.util.ImageLoader;
import com.example.pixaloop.util.LogU;
import com.example.pixaloop.util.ShaderLoader;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Closeable;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

public class PixaloopRenderer implements DisposableResource {
    public static final GpuStruct a = new GpuStruct("PIXALOOP_MOTION_VERTEX", Lists.newArrayList( new GpuStructField[]{new GpuStructField("position", 4, 5126, true), new GpuStructField("texcoord", 2, 5126, true)}));
    public static final FloatBuffer b = FloatBuffer.wrap(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f});
    public static final List<AttributeData> c = Lists.newArrayList(new AttributeData[]{new AttributeData(b, a)});
    public static final Matrix3f d = new Matrix3f();
    public static final Matrix4f e = new Matrix4f();
    public final Texture A;
    public final Texture B;
    public Fbo C;
    public final BicubicResizeProcessor D;
    public final QuadMixerProcessor E;
    public final Mat F;
    public final Mat G;
    public boolean H;
    public Texture I;
    public Texture J;
    //public SkyNNBinaryModel K;
    public float L;
    public float M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float RFloat;
    public Matrix4f S;
    public Matrix4f T;
    public SkyMotionCoefficients U;
    public final OverlayRenderConfig V;
    public final OverlayRenderConfig W;
    public final OverlayInfoProvider X;
    public CameraFxLayer Y;
    public final Context f;
    public final Texture g;
    public final Texture h;
    public final Shader i;
    public final Shader j;
    public final Matrix4f k;
    public final Matrix4f l;
    public Texture m;
    public final Texture n;
    public final Texture o;
    public final Vector4 p;
    public final int q;
    public boolean r;
    public final Texture s;
    public boolean t;
    public SessionState sessionState;
    public boolean v;
    public FieldRenderer fieldRenderer;
    public final FreezePainter x;
    public final DynamicDrawer y;
    public Matrix4f z;

    @Override
    public void close() throws Exception {
        dispose();
    }

    /* renamed from: com.lightricks.pixaloop.render.PixaloopRenderer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] a = new int[OverlayInfo.StorageType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            a[OverlayInfo.StorageType.INTERNAL_STORAGE.ordinal()] = 1;
            a[OverlayInfo.StorageType.APPLICATION_ASSET.ordinal()] = 2;
        }
    }

    public PixaloopRenderer(Context context, RemoteAssetsManager remoteAssetsManager, SessionState sessionState, Texture texture, Texture texture2) {
        this.r = false;
        this.t = false;
        this.H = false;
        this.V = new OverlayRenderConfig((AnonymousClass1) null);
        this.W = new OverlayRenderConfig((AnonymousClass1) null);
        this.f = context.getApplicationContext();
        this.X = new OverlayInfoProvider(remoteAssetsManager);
        String a2 = ShaderLoader.a("PNXMotion.vsh");
        String a3 = ShaderLoader.a("PNXMotion.fsh");
        String a4 = ShaderLoader.a("VectorField.vsh");
        String a5 = ShaderLoader.a("VectorField.fsh");
        this.i = new Shader(a2, a3);
        this.j = new Shader(a4, a5);
        this.sessionState = sessionState;
        this.k = new Matrix4f();
        this.l = new Matrix4f();
        this.m = texture;
        this.o = texture2;
        this.g = new Texture(1, 1, Texture.Type.RGBA32Float, false);
        this.h = this.g;
        a(context);
        a((SkyModel) null, sessionState.g());
        a(sessionState.d());
        int color = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = context.getResources().getColor(R.color.pnx_main_orange, (Resources.Theme) null);
        } else {
            color = context.getResources().getColor(R.color.pnx_main_orange);
        }
        int integer = context.getResources().getInteger(R.integer.animate_freeze_overlay_color_alpha);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.brush_texture_radius);
        this.q = context.getResources().getInteger(R.integer.animate_freeze_alpha_threshold);
        this.p = new Vector4(((float) Color.red(color)) / 255.0f, ((float) Color.green(color)) / 255.0f, ((float) Color.blue(color)) / 255.0f, ((float) integer) / 255.0f);
        Mat a6 = AdjustMatrices.a();
        this.s = new Texture(Texture.Type.R8Unorm, a6);
        a6.release();
        a(sessionState.b());
        a((OverlayModel) null, sessionState.f(), this.V);
        a((OverlayModel) null, sessionState.e(), this.W);
        this.fieldRenderer = new FieldRenderer(texture.l(), texture.i(), this.j);
        this.y = new DynamicDrawer(this.i, Lists.newArrayList(new GpuStruct[]{a}));
        e();
        this.D = new BicubicResizeProcessor(texture);
        this.A = a(texture2.l(), texture2.i());
        this.B = texture2.b();
        this.E = new QuadMixerProcessor(texture, this.B, texture2);
        this.n = Texture.a(texture);
        this.C = new Fbo(this.n);
        Pair<Mat, Mat> b2 = b(this.A);
        this.F = (Mat) b2.first;
        this.G = (Mat) b2.second;
        this.x = new FreezePainter(new Painter(a(dimensionPixelSize)));
        //this.x.a(sessionState.c().d(), false);
        f();
    }

    public final void a(Context context) {
        this.I = this.g;
        this.J = this.h;
        this.U = new SkyMotionCoefficients((long) (context.getResources().getInteger(R.integer.animate_duration_master_period) * 2));
        //this.K = (SkyNNBinaryModel) new SkyNetworkModelBuilder(context).a();
    }

    public void b(SessionState sessionState, boolean z2) {
        if (this.v != z2 || !Objects.equals(this.sessionState, sessionState)) {
            this.v = z2;
            a(sessionState, z2);
            a(this.sessionState.g(), sessionState.g());
            a(this.sessionState.d(), sessionState.d());
            a(this.sessionState.b(), sessionState.b());
            a(this.sessionState.f(), sessionState.f(), this.V);
            a(this.sessionState.e(), sessionState.e(), this.W);
            this.sessionState = sessionState;
            if (!z2) {
                e();
                if (this.t) {
                    f();
                    this.t = false;
                }
            }
        }
    }

    public final void c(AdjustModel adjustModel) {
        this.z = Adjust.b(adjustModel.e(), adjustModel.d());
    }

    public final Matrix4f d() {
        Matrix4f matrix4f = new Matrix4f();
//        if (!this.V.b()) {
//            return matrix4f;
//        }
//        int i2 = this.V.videoReader.i();
//        int h2 = this.V.videoReader.h();
//        float l2 = ((float) this.m.l()) / ((float) this.m.i());
//        float f2 = ((float) i2) / ((float) h2);
//        if (f2 < l2) {
//            float f3 = f2 / l2;
//            matrix4f.translate(0.0f, (1.0f - f3) * 0.5f, 0.0f);
//            matrix4f.scale(1.0f, f3, 1.0f);
//        } else {
//            float f4 = l2 / f2;
//            matrix4f.translate((1.0f - f4) * 0.5f, 0.0f, 0.0f);
//            matrix4f.scale(f4, 1.0f, 1.0f);
//        }
        return matrix4f;
    }

    public void dispose() {
        Texture texture;
        this.D.dispose();
        this.E.dispose();
        this.C.dispose();
        if (this.r && (texture = this.o) != null) {
            texture.dispose();
        }
        this.n.dispose();
        this.A.dispose();
        this.B.dispose();
        this.fieldRenderer.dispose();
        this.s.dispose();
        this.y.dispose();
        this.i.dispose();
        this.j.dispose();
        this.I.dispose();
        this.J.dispose();
        this.g.dispose();
        this.h.dispose();
        this.V.a();
        this.W.a();
        this.x.dispose();
        this.F.release();
        this.G.release();
//        SkyNNBinaryModel skyNNBinaryModel = this.K;
//        if (skyNNBinaryModel != null) {
//            try {
//                skyNNBinaryModel.close();
//            } catch (Exception unused) {
//            }
//        }
    }

    public final void e() {
        LogU.e("xxl", "fieldRenderer.a(a(this.sessionState.c()) : ");
        List<Arrow> arrows = new ArrayList<>();
        arrows.add(Arrow.a(new PointF(-1,10)));
        arrows.add(Arrow.a(new PointF(10,-9)));
        this.fieldRenderer.a(a(this.sessionState.c()));
    }

    public final void f() {
        Mat mat = null;
        Mat mat2 = null;
        Mat mat3;
        Mat mat4;
        Exception e2;
        Mat mat5;
        Mat mat6;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            mat4 = this.x.a().o();
            try {
                mat3 = new Mat();
            } catch (Exception e3) {

                mat3 = null;
                mat6 = null;
                mat5 = mat2;

                try {
                    if (mat4 != null) {
                    }
                    if (mat2 != null) {
                    }
                    if (mat3 != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (mat4 != null) {
                    }
                    if (mat2 != null) {
                    }
                    if (mat3 != null) {
                    }
                    if (mat != null) {
                    }
                    release((List<Mat>) arrayList);
                    release((List<Mat>) arrayList2);
                    throw th;
                }
            } catch (Throwable th2) {

                mat3 = null;
                mat2 = null;
                mat = mat2;

                release((List<Mat>) arrayList);
                release((List<Mat>) arrayList2);

            }
            try {
                mat2 = new Mat();
                try {
                    mat = new Mat();
                } catch (Exception e4) {
                    mat5 = null;
                } catch (Throwable th3) {
                    mat = null;
                    release((List<Mat>) arrayList);
                    release((List<Mat>) arrayList2);
                }
                try {
                    Core.split(mat4, (List<Mat>) arrayList);
                    mat4.release();
                    Imgproc.threshold((Mat) arrayList.get(0), mat3, (double) this.q, 255.0d, 0);
                    release((List<Mat>) arrayList);
                    Photo.inpaint(this.F, mat3, mat2, 2.0d, 1);
                    mat3.release();
                    Core.split(mat2, (List<Mat>) arrayList2);
                    mat2.release();
                    arrayList2.add(this.G);
                    Core.merge((List<Mat>) arrayList2, mat);
                    arrayList2.remove(3);
                    release((List<Mat>) arrayList2);
                    this.B.a(mat);
                    mat.release();
                    c();
                    if (mat4 != null) {
                        mat4.release();
                    }
                    mat2.release();
                    mat3.release();
                } catch (Exception e5) {
                    e5.printStackTrace();
                    LogU.a("PixaloopRenderer", "Error producing inpainted texture1.", (Throwable) e5);
                }
            } catch (Exception e6) {


                e6.printStackTrace();

                LogU.a("PixaloopRenderer", "Error producing inpainted texture2.");
            } catch (Throwable th4) {

                mat2 = null;
                mat = null;
                release((List<Mat>) arrayList);
                release((List<Mat>) arrayList2);

            }
        } catch (Exception e7) {
            mat3 = null;
            mat2 = null;
            mat = null;
            e2 = e7;
            mat4 = null;
            e7.printStackTrace();
            LogU.a("PixaloopRenderer", "Error producing inpainted texture3.", (Throwable) e2);
            if (mat4 != null) {
                mat4.release();
            }
            if (mat2 != null) {
                mat2.release();
            }
            if (mat3 != null) {
                mat3.release();
            }
        } catch (Throwable th5) {
            mat3 = null;
            mat2 = null;
            mat = null;

            mat4 = null;
            if (mat4 != null) {
                mat4.release();
            }
            if (mat2 != null) {
                mat2.release();
            }
            if (mat3 != null) {
                mat3.release();
            }
            if (mat != null) {
                mat.release();
            }
            release((List<Mat>) arrayList);
            release((List<Mat>) arrayList2);

        }
        mat.release();
        release((List<Mat>) arrayList);
        release((List<Mat>) arrayList2);
    }

    public final void c(float f2) {
        SkyMotionCoefficients.SkyKeyframe a2 = this.U.a(f2, this.RFloat);
        this.Q = a2.c;
        if (!this.H) {
            Matrix4f matrix4f = e;
            this.S = matrix4f;
            this.T = matrix4f;
            return;
        }
        this.S = b(a2.a);
        this.T = b(a2.b);
    }

    private static class OverlayRenderConfig {
//        public VideoReader videoReader;
        public float b;
        public boolean c;
        public Float3 d;
        public int e;
        public Closeable f;

        public OverlayRenderConfig() {
//            this.videoReader = null;
//            this.d = new Float3();
        }

        public void a() {
//            VideoReader videoReader = this.videoReader;
//            if (videoReader != null) {
//                videoReader.close();
//            }
//            this.videoReader = null;
//            Closeable closeable = this.f;
//            if (closeable != null) {
//                try {
//                    closeable.close();
//                } catch (IOException e2) {
//                    Log.e("PixaloopRenderer", "Error closing video file descriptor.");
//                }
//                this.f = null;
//            }
        }

        public boolean b() {
//            return this.videoReader != null;
            return false;
        }

        public /* synthetic */ OverlayRenderConfig(AnonymousClass1 r1) {
            this();
        }
    }

    public static Texture a(Texture texture) {
        return Painter.a(Math.max(Math.round(((float) texture.l()) * 0.25f), 1), Math.max(Math.round(((float) texture.i()) * 0.25f), 1));
    }

    public final PainterParams a(int i2) {
        PainterParams painterParams = new PainterParams();
        Brush a2 = Brush.a(i2, ((float) i2) / 3.0f, 1.0f);
        painterParams.a(this.m.l(), this.m.i());
        painterParams.b(142.5f);
        painterParams.a(142.5f);
        painterParams.b(a2).a(a2);
        painterParams.a(this.o);
        painterParams.b(true);
        painterParams.a(Vector4.a.a(0.9f), Vector4.a.a(0.9f));
        painterParams.c(0.3f);
        painterParams.a(0.0f, 1.0f);
        painterParams.c(false);
        painterParams.a(PainterMode.PAINT.a());
        painterParams.a(false);
        return painterParams;
    }

    public final void c() {
        this.C.a();
        this.C.b();
        this.E.a();
        this.C.c();
    }

    public final void b(AdjustModel adjustModel) {
        Mat a2 = Adjust.a(adjustModel.b(), adjustModel.c());
        this.s.a(a2);
        a2.release();
    }

    public final Matrix4f b(float f2) {
        Size j2 = this.I.j();
        Size j3 = this.m.j();
        org.opencv.core.Size a2 = a(new org.opencv.core.Size((double) (((float) j3.b()) / ((float) j2.b())), (double) (((float) j3.a()) / ((float) j2.a()))), new org.opencv.core.Size(1.0d, 1.0d));
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.loadTranslate(f2, (float) (1.0d - a2.height), 0.0f);
        Matrix4f matrix4f2 = new Matrix4f();
        matrix4f2.loadScale((float) a2.width, (float) a2.height, 1.0f);
        Matrix4f matrix4f3 = new Matrix4f();
        matrix4f3.loadTranslate(0.0f, this.M, 0.0f);
        matrix4f.multiply(matrix4f2);
        matrix4f.multiply(matrix4f3);
        return matrix4f;
    }

    public final boolean a(SkyModel skyModel) {
        //return this.K != null && skyModel.h();
        return false;
    }

    public final void a(AdjustModel adjustModel) {
        a((AdjustModel) null, adjustModel);
    }

    public final void a(@Nullable AdjustModel adjustModel, AdjustModel adjustModel2) {
        if (adjustModel == null) {
            b(adjustModel2);
            c(adjustModel2);
            return;
        }
        if (!(adjustModel.b() == adjustModel2.b() && adjustModel.c() == adjustModel2.c())) {
            b(adjustModel2);
        }
        if (adjustModel.d() != adjustModel2.d() || adjustModel.e() != adjustModel2.e()) {
            c(adjustModel2);
        }
    }

    public final void a(OverlayModel overlayModel, OverlayModel overlayModel2, OverlayRenderConfig overlayRenderConfig) {
//        OverlayItemModel a2 = a(overlayModel);
//        OverlayItemModel a3 = a(overlayModel2);
//        if (!Objects.equals(a2, a3)) {
//            OverlayInfo b2 = a2 != null ? this.X.b(a2.c()) : null;
//            OverlayInfo b3 = a3 != null ? this.X.b(a3.c()) : null;
//            String str = b2 != null ? b2.a : null;
//            String str2 = b3 != null ? b3.a : null;
//            if (!Objects.equals(str, str2)) {
//                overlayRenderConfig.a();
//                if (str2 != null) {
//                    try {
//                        int i2 = AnonymousClass1.a[b3.e.ordinal()];
//                        if (i2 == 1) {
//                            RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.f.getDataDir(), str2), "r");
//                            overlayRenderConfig.f = randomAccessFile;
//                            overlayRenderConfig.videoReader = new VideoReader(randomAccessFile.getFD(), (Long) null, (Long) null);
//                        } else if (i2 == 2) {
//                            AssetFileDescriptor openFd = this.f.getAssets().openFd(str2);
//                            overlayRenderConfig.f = openFd;
//                            overlayRenderConfig.videoReader = new VideoReader(openFd.getFileDescriptor(), Long.valueOf(openFd.getStartOffset()), Long.valueOf(openFd.getLength()));
//                        }
//                        overlayRenderConfig.videoReader.k();
//                    } catch (Exception e2) {
//                        throw new RuntimeException(String.format(Locale.ENGLISH, "Error reading video of Overlay [%s], video asset [%s]", new Object[]{a3.c(), str2}), e2);
//                    }
//                }
//            }
//            if (overlayRenderConfig.b()) {
//                overlayRenderConfig.b = a3.d();
//                overlayRenderConfig.c = b3.c;
//                overlayRenderConfig.d.x = ((float) Color.red(b3.d)) / 255.0f;
//                overlayRenderConfig.d.y = ((float) Color.green(b3.d)) / 255.0f;
//                overlayRenderConfig.d.z = ((float) Color.blue(b3.d)) / 255.0f;
//                overlayRenderConfig.e = b3.b;
//            }
//        }
    }

    public final RectF b() {
        return new RectF();
        //return ElementUtil.a(this.f, (float) this.W.videoReader.i(), (float) this.W.videoReader.h(), (float) this.m.l(), (float) this.m.i());
    }

    public final Pair<Mat, Mat> b(@NonNull Texture texture) {

        Mat o2 = texture.o();
        ArrayList arrayList = new ArrayList();
        Core.split(o2, (List<Mat>) arrayList);
        o2.release();
        Mat mat = new Mat();
        Core.merge((List<Mat>) arrayList, mat);
        return new Pair<>(mat, (Mat) arrayList.remove(3));
    }

    public PixaloopRenderer(Context context, RemoteAssetsManager remoteAssetsManager, SessionState sessionState, Texture texture) {
        this(context, remoteAssetsManager, sessionState, texture, a(texture));
        this.r = true;
    }

    public static OverlayItemModel a(OverlayModel overlayModel) {
        if (overlayModel == null) {
            return null;
        }
        ImmutableList<OverlayItemModel> b2 = overlayModel.b();
        if (b2.isEmpty()) {
            return null;
        }
        return b2.get(0);
    }

    public final void a(SessionState sessionState, boolean z2) {
//        if (!z2 || !Objects.equals(this.sessionState.c(), sessionState.c())) {
//            this.t = this.x.a(sessionState.c().d(), z2);
//        }
    }

    public final void a(SkyModel skyModel, SkyModel skyModel2) {
        if (!Objects.equals(skyModel, skyModel2)) {
            this.H = a(skyModel2);
            if (this.H) {
//                if (this.J == this.h) {
//                    try {
//                        SkyNNBinaryModel.SkyMask skyMask = (SkyNNBinaryModel.SkyMask) this.K.b(this.m.n());
//                        this.J = skyMask.c();
//                        this.M = skyMask.a();
//                    } catch (NeuralNetworkGeneralException e2) {
//                        throw new RuntimeException("Failed to apply sky feature.", e2);
//                    }
//                }
//                if (skyModel == null || !Objects.equals(skyModel.e(), skyModel2.e())) {
//                    Bitmap a2 = a(SkyAssetsProvider.a(skyModel2), skyModel2.e().d());
//                    this.I.a(a2);
//                    a2.recycle();
//                }
//                this.L = skyModel2.d();
//                this.N = skyModel2.f();
//                this.O = skyModel2.c();
//                this.P = skyModel2.b();
//                this.RFloat = skyModel2.g();
            }
        }
    }

    public final Bitmap a(Integer num, String str) {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.f.getResources(), num.intValue());
        a(decodeResource, str);
        int h2 = RenderEngine.b().c().h();
        return (decodeResource.getWidth() > h2 || decodeResource.getHeight() > h2) ? ImageLoader.a(decodeResource, this.f) : decodeResource;
    }

    public final void a(Bitmap bitmap, String str) {
        if (bitmap.getWidth() > 4096 || bitmap.getHeight() > 4096) {
            throw new IllegalArgumentException(String.format("Resource %s dimensions are too big, keep it under %d", new Object[]{str, 4096}));
        }
    }

    public final void a(CameraFxModel cameraFxModel, CameraFxModel cameraFxModel2) {
        if (!Objects.equals(cameraFxModel, cameraFxModel2)) {
            a(cameraFxModel2);
        }
    }

    public final org.opencv.core.Size a(org.opencv.core.Size size, org.opencv.core.Size size2) {
        double min = Math.min(size2.width / size.width, size2.height / size.height);
        return new org.opencv.core.Size(size.width * min, size.height * min);
    }

    public void a(float f2, float f3, long j2, Matrix4f matrix4f, Matrix4f matrix4f2, boolean z2) {
        float f4 = f3;
        long j3 = j2;
        c(f3);
        int a2 = this.V.b() ? a(j2, this.V, a(this.sessionState.f())) : 0;
        int a3 = this.W.b() ? a(j2, this.W, a(this.sessionState.e())) : 0;
        HashMap c2 = Maps.newHashMap();
        c2.put("sourceTexture", this.m);
        c2.put("field", this.fieldRenderer.b());
        c2.put("mask", this.x.a());
        c2.put("inpaintedTexture", f2 == 0.0f ? this.m : this.n);
        c2.put("skyMask", this.J);
        c2.put("skyTexture", this.I);
        c2.put("toneLUT", this.s);
        HashMap c3 = Maps.newHashMap();
        c3.put("overlayTexture", Integer.valueOf(a2));
        c3.put("elementTexture", Integer.valueOf(a3));
        float f5 = ((f2 / 100.0f) * 2.0f) - 1.0f;
        AnimateModel.MotionType g2 = this.sessionState.c().g();
        Pair<Float, Float> b2 = Animate.b(f5, g2);
        ArrayList a4 = Lists.newArrayList(new Pair[]{Pair.create("projection", matrix4f2), Pair.create("modelview", this.k), Pair.create("textureTransform", matrix4f), Pair.create("displacementA", b2.first), Pair.create("displacementB", b2.second), Pair.create("displacementBlending", Float.valueOf(Animate.a(f5, g2))), Pair.create("drawFreezeMask", Boolean.valueOf(z2)), Pair.create("freezeColor", this.p), Pair.create("freezeThreshold", Float.valueOf(((float) this.q) / 255.0f)), Pair.create("texelWidth", Float.valueOf(1.0f / ((float) this.s.l()))), Pair.create("tonalTransform", this.z), Pair.create("useSky", Boolean.valueOf(this.H)), Pair.create("skyHaze", Float.valueOf(this.L)), Pair.create("skyHorizon", Float.valueOf(this.M)), Pair.create("skyOpacity", Float.valueOf(this.N)), Pair.create("skyAlpha", Float.valueOf(this.Q)), Pair.create("skyTransformA", this.S), Pair.create("skyTransformB", this.T), Pair.create("fxTransform", a((f4 * 2.0f) % 100.0f)), Pair.create("overlayEnable", Boolean.valueOf(this.V.b())), /*Pair.create("overlayTransform", d()),*/ Pair.create("overlayBlendMode", Integer.valueOf(this.V.e)), Pair.create("overlayOpacity", Float.valueOf(this.V.b)), Pair.create("overlayEnableChromaKey", Boolean.valueOf(this.V.c)), Pair.create("overlayChromaKeyColor", this.V.d), Pair.create("elementEnable", Boolean.valueOf(this.W.b())), Pair.create("elementTransform", a()), Pair.create("elementBlendMode", Integer.valueOf(this.W.e)), Pair.create("elementOpacity", Float.valueOf(this.W.b)), Pair.create("elementEnableChromaKey", Boolean.valueOf(this.W.c)), Pair.create("elementChromaKeyColor", this.W.d)});
        GLES30.glClear(16384);
        this.y.a(5, 4, a4, c2, c3, c);
    }

    public final int a(long j2, OverlayRenderConfig overlayRenderConfig, OverlayItemModel overlayItemModel) {
//        try {
//            j2 %= overlayRenderConfig.videoReader.e();
//            overlayRenderConfig.videoReader.a(j2);
//            return overlayRenderConfig.videoReader.g();
//        } catch (Exception e2) {
//            Log.a("PixaloopRenderer", String.format(Locale.ENGLISH, "Error reading overlay [%s] at time [%d of %d]", new Object[]{overlayItemModel != null ? overlayItemModel.c() : null, Long.valueOf(j2), Long.valueOf(overlayRenderConfig.videoReader.e())}), (Throwable) e2);
//            overlayRenderConfig.a();
//            return 0;
//        }
        return 0;
    }

    public final Matrix4f a() {
        OverlayItemModel a2 = a(this.sessionState.e());
        if (a2 == null || !this.W.b()) {
            return new Matrix4f();
        }
        return a(a(a2), (float) Math.toDegrees(Float.valueOf(a2.b()).doubleValue()));
    }

    public final Matrix4f a(RectF rectF, float f2) {
        Matrix4f matrix4f = new Matrix4f();
        if (!this.W.b()) {
            return matrix4f;
        }
        matrix4f.translate(0.5f, 0.5f, 0.0f);
        matrix4f.scale(1.0f / rectF.width(), 1.0f / rectF.height(), 1.0f);
        matrix4f.rotate(-f2, 0.0f, 0.0f, 1.0f);
        matrix4f.scale((float) this.m.l(), (float) this.m.i(), 1.0f);
        matrix4f.translate(-0.5f, -0.5f, 0.0f);
        matrix4f.translate((-(rectF.centerX() - (((float) this.m.l()) / 2.0f))) / ((float) this.m.l()), (-(rectF.centerY() - (((float) this.m.i()) / 2.0f))) / ((float) this.m.i()), 0.0f);
        return matrix4f;
    }

    public final RectF a(OverlayItemModel overlayItemModel) {
        if (overlayItemModel.e() != null) {
            return overlayItemModel.e();
        }
        return b();
    }

    public void a(float f2, float f3, long j2, boolean z2) {
        a(f2, f3, j2, this.l, e, z2);
    }

    public final Matrix4f a(float f2) {
        Matrix4f matrix4f = new Matrix4f();
        CameraFxLayer cameraFxLayer = this.Y;
        return cameraFxLayer != null ? cameraFxLayer.a(f2, ((float) this.m.l()) / ((float) this.m.i())) : matrix4f;
    }

    public final void a(CameraFxModel cameraFxModel) {
        if (!cameraFxModel.c()) {
            this.Y = null;
        } else {
            //this.Y = new CameraFxProvider(this.f).a(cameraFxModel.b().b());
        }
    }

    public final List<Arrow> a(AnimateModel animateModel) {
        ArrayList arrayList = new ArrayList();
        if (animateModel != null) {
            UnmodifiableIterator<PathArrowModel> pathArrow = animateModel.c().iterator();
            while (pathArrow.hasNext()) {
                PathArrowModel next = pathArrow.next();
                int i2 = 0;
                PathMeasure pathMeasure = new PathMeasure(a(next.b()), false);
                float length = pathMeasure.getLength();
                float ceil = length / ((float) Math.ceil((double) (length / next.c())));
                int round = Math.round(length / ceil);
                while (i2 < round) {
                    i2++;
                    arrayList.add(new Arrow(a(((float) i2) * ceil, pathMeasure), a(((float) i2) * ceil, pathMeasure)));
                }
            }
            UnmodifiableIterator<PointF> it2 = animateModel.b().iterator();
            while (it2.hasNext()) {
                arrayList.add(Arrow.a(it2.next()));
            }
            UnmodifiableIterator<ImmutableList<PointF>> it3 = animateModel.h().iterator();
            while (it3.hasNext()) {
                ImmutableList next2 = it3.next();
                int size = next2.size();
                for (int i3 = 1; i3 < size; i3++) {
                    arrayList.add(new Arrow((PointF) next2.get(i3 - 1), (PointF) next2.get(i3)));
                }
            }
        }
        return arrayList;
    }

    public final Path a(ImmutableList<PointF> immutableList) {
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<PointF> it = immutableList.iterator();
        while (it.hasNext()) {
            PointF next = it.next();
            arrayList.add(Double.valueOf((double) next.x));
            arrayList.add(Double.valueOf((double) next.y));
        }
        Pair<List<Double>, List<Double>> b2 = BezierSpline.b(arrayList);
        Path path = new Path();
        path.moveTo(immutableList.get(0).x, immutableList.get(0).y);
        for (int i2 = 0; i2 < immutableList.size() - 1; i2++) {
            int i3 = i2 * 2;
            int i4 = i3 + 1;
            path.cubicTo(((Double) ((List) b2.first).get(i3)).floatValue(), ((Double) ((List) b2.first).get(i4)).floatValue(), ((Double) ((List) b2.second).get(i3)).floatValue(), ((Double) ((List) b2.second).get(i4)).floatValue(), ((Double) arrayList.get(i3 + 2)).floatValue(), ((Double) arrayList.get(i3 + 3)).floatValue());
        }
        return path;
    }

    public final PointF a(float f2, PathMeasure pathMeasure) {
        float[] fArr = new float[2];
        pathMeasure.getPosTan(f2, fArr, new float[2]);
        return new PointF(fArr[0], fArr[1]);
    }

    public final Texture a(int i2, int i3) {
        Texture texture = new Texture(i2, i3, this.m.k(), true);
        Fbo fbo = new Fbo(texture);
        fbo.a();
        fbo.b();
        this.D.a();
        fbo.c();
        fbo.dispose();
        return texture;
    }

    public final void release(List<Mat> list) {
        for (Mat g2 : list) {
            g2.release();
        }
        list.clear();
    }
}
