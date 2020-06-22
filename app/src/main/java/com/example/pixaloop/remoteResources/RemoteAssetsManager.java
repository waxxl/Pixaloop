package com.example.pixaloop.remoteResources;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;

import com.example.pixaloop.remoteResources.model.AssetDescriptor;
import com.example.pixaloop.remoteResources.model.AssetsConfigurationDescriptor;
import com.example.pixaloop.remoteResources.model.FeaturePacksDescriptor;
import com.example.pixaloop.remoteResources.model.PackDescriptor;
import com.example.pixaloop.util.LogU;
import com.example.pixaloop.util.Storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public class RemoteAssetsManager {
    public static Gson a = new GsonBuilder().create();
    public final Context c;
    public final String string;
    public Set<String> e = new HashSet();

    /* renamed from: com.lightricks.pixaloop.remoteResources.RemoteAssetsManager$1  reason: invalid class name */
    public class AnonymousClass1 extends AssetsConfigurationDescriptor {
    }

    /* renamed from: com.lightricks.pixaloop.remoteResources.RemoteAssetsManager$2  reason: invalid class name */
    public class AnonymousClass2 extends FeaturePacksDescriptor {
    }

    public RemoteAssetsManager(Context context, String str) {
        this.c = context.getApplicationContext();
        this.string = str;
        b();
    }

//    public Completable a(String str, List<PackDescriptor> list) {
//        File a2 = a("video_asset.mp4", str, this.c);
//        File a3 = a("asset_descriptor.json", str, this.c);
//        if (!a2.exists() || !a3.exists()) {
//            return a(str, list, a3).b(new Zg(this, a2)).b((Action) new Xg(this, str));
//        }
//        return Completable.c();
//    }

    @Nullable
    public final AssetDescriptor b(String str, List<PackDescriptor> list) {
        for (PackDescriptor packDescriptor : list) {
            Iterator<AssetDescriptor> it = packDescriptor.assetDescriptors.iterator();
            while (true) {
                if (it.hasNext()) {
                    AssetDescriptor next = it.next();
                    if (next.id.equals(str)) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        r11 = r3;
        r3 = r0;
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0039, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003a, code lost:
        r3 = null;
     */
    public VideoAssetInfo c(String str) {

        FileReader fileReader = null;
        try {
             fileReader = new FileReader(a("asset_descriptor.json", str, this.c));
            AssetDescriptor.Video video = null; /*= ((AssetDescriptor) a.a((Reader) fileReader, AssetDescriptor.class)).videos.get(0)*/;
            VideoAssetInfo videoAssetInfo = new VideoAssetInfo(b(str), video.width, video.height, video.blendMode, video.representativeFrameTime, video.chromaKeyColor);
            fileReader.close();
            return videoAssetInfo;



        } catch (IOException e2) {
            try {
                fileReader.close();
            } catch (Exception th3) {

            }
            LogU.a("RemoteAssetsManager", "Failed getting AssetDescriptor for asset id: " + str, (Throwable) e2);
            return null;
        }
    }

    public void d(String str) {
        this.e.add(str);
    }

    public boolean e(String str) {
        return !this.e.contains(str);
    }

    public static String b(String str) {
        return new Uri.Builder().appendPath("assets").appendPath(str).appendPath("video_asset.mp4").toString();
    }

    public final void b() {
        for (File file : Storage.a(this.c.getDataDir(), "assets").listFiles()) {
            if (a(file)) {
                this.e.add(file.getName());
            }
        }
    }

//    public Uri a(String str) {
//        if (str.startsWith("/")) {
//            return a().appendEncodedPath(str.substring(1)).build();
//        }
//        return Uri.parse(str);
//    }

//    public final Single<AssetDescriptor> a(String str, List<PackDescriptor> list, File file) {
//        AssetDescriptor b2 = b(str, list);
//        if (b2 != null) {
//            return Storage.a(file, a.a((Object) b2, (Type) AssetDscriptor.class).getBytes()).a(b2);
//        }
//        return Single.b((Throwable) new Exception("Couldn't find asset descriptor with id: " + str));
//    }

    public static File a(String str, String str2, Context context) {
        return new File(a(str2, context), str);
    }

    @SuppressLint("NewApi")
    public static File a(String str, Context context) {
        return Storage.a(Storage.a(context.getDataDir(), "assets"), str);
    }

    public final boolean a(File file) {
        if (!file.isDirectory()) {
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (File file2 : file.listFiles()) {
            if (file2.getName().equals("asset_descriptor.json")) {
                z = true;
            } else if (file2.getName().equals("video_asset.mp4")) {
                z2 = true;
            }
            if (z && z2) {
                return true;
            }
        }
        return false;
    }
}
