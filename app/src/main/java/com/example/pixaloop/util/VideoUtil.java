package com.example.pixaloop.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.util.Size;
import java.io.IOException;

public final class VideoUtil {
    public static Size a(Context context, String str) {
        AssetFileDescriptor openFd;
        try {
            openFd = context.getAssets().openFd(str);
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            Size size = new Size(Float.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue(), Float.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue());
            if (openFd != null) {
                openFd.close();
            }
            return size;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {

        }
        return null;
    }
}
