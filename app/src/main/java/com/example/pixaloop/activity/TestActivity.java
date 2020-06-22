package com.example.pixaloop.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pixaloop.R;
import com.example.pixaloop.common.render.RenderEngine;
import com.example.pixaloop.common.render.gpu.Texture;
import com.example.pixaloop.common.render.ltview.DrawDelegate;
import com.example.pixaloop.common.render.ltview.LTView;
import com.example.pixaloop.features.AdjustModel;
import com.example.pixaloop.features.AnimateModel;
import com.example.pixaloop.features.CameraFxModel;
import com.example.pixaloop.features.GeometricArrowsInteraction;
import com.example.pixaloop.features.OverlayModel;
import com.example.pixaloop.features.PathArrowModel;
import com.example.pixaloop.features.SessionState;
import com.example.pixaloop.features.SkyModel;
import com.example.pixaloop.features.StrokeData;
import com.example.pixaloop.remoteResources.RemoteAssetsManager;
import com.example.pixaloop.render.PixaloopAnimator;
import com.example.pixaloop.render.RenderingResources;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.opencv.android.OpenCVLoader;

public class TestActivity extends AppCompatActivity implements DrawDelegate {
    private static final String TAG = "xxl";
    LTView ltView;
     PixaloopAnimator pixaloopAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        OpenCVLoader.initDebug();
        ltView = findViewById(R.id.ltView);

//        RenderEngine.b().post(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap  = BitmapFactory.decodeResource(getResources(), R.drawable.preview);
//                setDraw(new RenderingResources(bitmap));
//            }
//        });



        init();
//        Log.d("xxl", "runtimeVersion" + TensorFlowLite.runtimeVersion());
    }

    public final void setDraw(@Nullable RenderingResources renderingResources) {
        ltView.setContent(renderingResources != null ? renderingResources.b : null);
        ltView.setDrawDelegate(this);
        ltView.c();
    }

    private void init() {
        RemoteAssetsManager manager = new RemoteAssetsManager(this, "");
        pixaloopAnimator = new PixaloopAnimator(this, manager);
        final SessionState sessionState = new SessionState() {
            @Override
            public AdjustModel b() {
                return new AdjustModel() {
                    @Override
                    public Builder f() {
                        return null;
                    }
                };
            }

            @Override
            public AnimateModel c() {
                return new AnimateModel() {
                    @Override
                    public ImmutableList<PointF> b() {
                        ImmutableList<PointF> list = ImmutableList.of(new PointF(1,300), new PointF(300,1));
                        return list;
                    }

                    @Override
                    public ImmutableList<PathArrowModel> c() {
                        ImmutableList<PathArrowModel> list = ImmutableList.of(new PathArrowModel());
                        return list;
                    }

                    @Override
                    public ImmutableList<StrokeData> d() {
                        return null;
                    }

                    @Override
                    public ImmutableList<ImmutableList<PointF>> e() {
                        ImmutableList<ImmutableList<PointF>> list = ImmutableList.of(ImmutableList.of(new PointF(1,300), new PointF(300,1)));
                        return list;
                    }

                    @Override
                    public GeometricArrowsInteraction f() {
                        return new GeometricArrowsInteraction() {
                            @Override
                            public Builder f() {
                                return null;
                            }
                        };
                    }

                    @Override
                    public MotionType g() {
                        return MotionType.SEAMLESS_LOOP;
                    }

                    @Override
                    public float i() {
                        return 1;
                    }

                    @Override
                    public Builder j() {
                        return null;
                    }
                };
            }

            @Override
            public CameraFxModel d() {
                return new CameraFxModel() {
                    @Override
                    public Optional<String> b() {

                        Optional<String> optional = Optional.absent();
                        return optional;
                    }

                    @Override
                    public Builder d() {
                        return null;
                    }
                };
            }

            @Override
            public OverlayModel e() {
                return null;
            }

            @Override
            public OverlayModel f() {
                return null;
            }

            @Override
            public SkyModel g() {
                return null;
            }

            @Override
            public Builder h() {
                return null;
            }
        };
        final Bitmap bitmap  = BitmapFactory.decodeResource(getResources(), R.drawable.main_bg);

        RenderEngine.b().post(new Runnable() {
            @Override
            public void run() {
                final RenderingResources renderingResources = pixaloopAnimator.setData(bitmap,sessionState);
                setDraw(renderingResources);

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pixaloopAnimator.g();
            }
        }, 1000);
    }



    @Override
    public void a(LTView lTView, RectF rectF) {

    }

    @Override
    public boolean a(LTView lTView) {
        return true;
    }

    @Override
    public Texture b(LTView lTView) {
        return null;
    }

    @Override
    public void b(LTView lTView, RectF rectF) {

    }

    @Override
    public void c(LTView lTView, RectF rectF) {
        pixaloopAnimator.i();
    }

    @Override
    public boolean c(LTView lTView) {
        return true;
    }

    @Override
    public boolean d(LTView lTView) {
        return true;
    }

    @Override
    public boolean d(LTView lTView, RectF rectF) {
        return false;
    }
}
