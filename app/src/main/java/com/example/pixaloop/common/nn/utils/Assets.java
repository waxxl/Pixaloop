package com.example.pixaloop.common.nn.utils;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Assets {
    public static ByteBuffer a(AssetManager assetManager, String str) throws IOException {
        InputStream open = null;
        try {
            open = assetManager.open(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int available = open.available();
            byte[] bArr = new byte[available];
            if (available == open.read(bArr)) {
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(available);
                allocateDirect.order(ByteOrder.nativeOrder());
                allocateDirect.put(bArr);
                if (open != null) {
                    open.close();
                }
                return allocateDirect;
            }

        } catch (Exception th2) {
            throw new IOException(String.format("Failed to read the entire file: %s", new Object[]{str}));
        }
        return null;
    }
}
