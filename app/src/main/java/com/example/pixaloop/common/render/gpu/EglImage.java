package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.DisposableResource;

public class EglImage implements DisposableResource {
    public static int[] a = {12498, 1, 12344, 12344};
    public static int[] b = {12498, 0, 12344, 12344};
    public long c;

    public void dispose() {
        long j = this.c;
        if (j != 0) {
            if (GLUtils.eglDestroyImageKHR(j)) {
                this.c = 0;
                return;
            }
            throw new RuntimeException("Can't destroy EGL image");
        }
    }

    @Override
    public void close() throws Exception {
        dispose();
    }
}
