package com.example.pixaloop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.Executor;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Storage {
//    public static final ImmutableSet<String> a = ImmutableSet.n().a((Object) "log").a((Object) "image").a((Object) "pixaloop_export").a();
    public static final Object b = new Object();

    public static String c(File file) {
        String a2 = Files.getFileExtension(file.getName());
        if (Strings.isNullOrEmpty(a2)) {
            return "";
        }
        return "." + a2;
    }

    public static File a(@NonNull Context context, @NonNull String str, @Nullable String str2) {
        ArrayList a2 = Lists.newArrayList();
        a2.add(context.getCacheDir());
        Iterator it = a2.iterator();
        while (true) {
            File file = null;
            if (!it.hasNext()) {
                return file;
            }
            try {
                File createTempFile = File.createTempFile(str, str2, (File) it.next());
                if (createTempFile.canWrite()) {
                    return createTempFile;
                }
            } catch (IOException unused) {
                if (file != null) {
                    file.delete();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r3 != null) goto L_0x001a;
     */
    public static void a(File file, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        try {
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    public static File a() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "Pixaloop");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File a(File file, File file2, String str) {
        File file3 = new File(file2, str + c(file));
        a(file, file3);
        return file3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r7 = r2;
        r2 = r1;
        r1 = r7;
     */
    public static void a(File file, File file2) {
        Throwable th;
        Throwable th2;
        FileChannel channel = null;
        try {
            channel = new FileOutputStream(file2).getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileChannel channel2 = new FileInputStream(file).getChannel();
            channel2.transferTo(0, channel2.size(), channel);
            if (channel2 != null) {
                channel2.close();
            }
            if (channel != null) {
                channel.close();
                return;
            }
            return;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            try {
                throw th4;
            } catch (Throwable th5) {
                th4.addSuppressed(th5);
            }
        }
    }

    public static File a(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2;
    }
}
