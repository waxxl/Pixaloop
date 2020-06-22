package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.DisposableResource;

public class GatherTextureReader implements DisposableResource {
    public long a;

    public static native void nativeDestroy(long j);

    public void dispose() {
        long j = this.a;
        if (j != 0) {
            nativeDestroy(j);
            this.a = 0;
        }
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
