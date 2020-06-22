//package com.example.pixaloop.pixaloop.nn;
//
//import android.content.Context;
//import com.lightricks.common.nn.ModelImportException;
//import com.lightricks.common.nn.NeuralNetworkModelBuilder;
//import com.lightricks.common.nn.utils.Assets;
//import com.lightricks.pixaloop.util.GLESUtilsV2;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//
//public class SkyNetworkModelBuilder implements NeuralNetworkModelBuilder {
//    public final Context a;
//
//    public SkyNetworkModelBuilder(Context context) {
//        this.a = context.getApplicationContext();
//    }
//
//    public NeuralNetworkModelBuilder.NeuralNetworkModel a() {
//        try {
//            ByteBuffer a2 = Assets.a(this.a.getAssets(), "blobs/blob1.blob");
//            GLESUtilsV2.db(a2);
//            return new SkyNNBinaryModel(a2);
//        } catch (IOException e) {
//            throw new ModelImportException(e.getMessage());
//        }
//    }
//}
