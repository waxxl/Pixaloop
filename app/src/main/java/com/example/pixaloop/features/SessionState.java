package com.example.pixaloop.features;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@AutoValue
public abstract class SessionState {

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract AdjustModel.Builder a();

        public abstract Builder a(AdjustModel adjustModel);

        public abstract Builder a(AnimateModel animateModel);

        public abstract Builder a(CameraFxModel cameraFxModel);

        public abstract Builder a(OverlayModel overlayModel);

        public abstract Builder a(SkyModel skyModel);

        public abstract AnimateModel.Builder b();

        public abstract Builder b(OverlayModel overlayModel);

        public abstract SessionState c();

        public abstract CameraFxModel.Builder d();

        public abstract OverlayModel.Builder e();

        public abstract OverlayModel.Builder f();

        public abstract SkyModel.Builder g();
    }

    public static TypeAdapter<SessionState> a(Gson gson) {
        return new SessionStateGsonTypeAdapter(gson);
    }

    public abstract AdjustModel b();

    public abstract AnimateModel c();

    public abstract CameraFxModel d();

    public abstract OverlayModel e();

    public abstract OverlayModel f();

    public abstract SkyModel g();

    public abstract Builder h();

    public static final class SessionStateGsonTypeAdapter extends TypeAdapter<SessionState> {
        public final Gson a;

        public SessionStateGsonTypeAdapter(Gson gson) {
            this.a = gson;
        }

        public void a(JsonWriter jsonWriter, SessionState sessionState) {
//            jsonWriter.f();
//            jsonWriter.e("animateModel");
//            this.a.a(AnimateModel.class).a(jsonWriter, sessionState.c());
//            jsonWriter.e("skyModel");
//            this.a.a(SkyModel.class).a(jsonWriter, sessionState.g());
//            jsonWriter.e("overlayModel");
//            this.a.a(OverlayModel.class).a(jsonWriter, sessionState.f());
//            jsonWriter.e("elementModel");
//            this.a.a(OverlayModel.class).a(jsonWriter, sessionState.e());
//            jsonWriter.e("adjustModel");
//            this.a.a(AdjustModel.class).a(jsonWriter, sessionState.b());
//            jsonWriter.e("cameraFxModel");
//            this.a.a(CameraFxModel.class).a(jsonWriter, sessionState.d());
//            jsonWriter.h();
        }

        public SessionState a(JsonReader jsonReader) {
//            OverlayModel a2 = OverlayModel.a().a();
//            jsonReader.d();
//            AnimateModel animateModel = null;
//            OverlayModel overlayModel = a2;
//            SkyModel skyModel = null;
//            OverlayModel overlayModel2 = null;
//            AdjustModel adjustModel = null;
//            CameraFxModel cameraFxModel = null;
//            while (jsonReader.j()) {
//                String H = jsonReader.H();
//                char c = 65535;
//                switch (H.hashCode()) {
//                    case -1739906414:
//                        if (H.equals("cameraFxModel")) {
//                            c = 5;
//                            break;
//                        }
//                        break;
//                    case -1722983800:
//                        if (H.equals("skyModel")) {
//                            c = 1;
//                            break;
//                        }
//                        break;
//                    case -589960819:
//                        if (H.equals("elementModel")) {
//                            c = 3;
//                            break;
//                        }
//                        break;
//                    case 269154489:
//                        if (H.equals("overlayModel")) {
//                            c = 2;
//                            break;
//                        }
//                        break;
//                    case 1028029640:
//                        if (H.equals("animateModel")) {
//                            c = 0;
//                            break;
//                        }
//                        break;
//                    case 1130837210:
//                        if (H.equals("adjustModel")) {
//                            c = 4;
//                            break;
//                        }
//                        break;
//                }
//                if (c == 0) {
//                    animateModel = this.a.a(AnimateModel.class).a(jsonReader);
//                } else if (c == 1) {
//                    skyModel = this.a.a(SkyModel.class).a(jsonReader);
//                } else if (c == 2) {
//                    overlayModel2 = this.a.a(OverlayModel.class).a(jsonReader);
//                } else if (c == 3) {
//                    overlayModel = this.a.a(OverlayModel.class).a(jsonReader);
//                } else if (c == 4) {
//                    adjustModel = this.a.a(AdjustModel.class).a(jsonReader);
//                } else if (c == 5) {
//                    cameraFxModel = this.a.a(CameraFxModel.class).a(jsonReader);
//                }
//            }
//            jsonReader.i();
//            return SessionState.a().a(animateModel).a(skyModel).b(overlayModel2).a(overlayModel).a(adjustModel).a(cameraFxModel).c();
            return null;
        }

        @Override
        public void write(JsonWriter out, SessionState value) throws IOException {

        }

        @Override
        public SessionState read(JsonReader in) throws IOException {
            return null;
        }
    }

//    public static Builder a() {
//        return new C$AutoValue_SessionState.Builder().a(AnimateModel.a().b()).a(SkyModel.a().a()).b(OverlayModel.a().a()).a(OverlayModel.a().a()).a(AdjustModel.a().b()).a(CameraFxModel.a().a());
//    }
}
