package com.example.pixaloop.common.render.gpu;

import com.example.pixaloop.common.render.DisposableResource;

public class PixelBuffer implements DisposableResource {
    public long a;

    public interface MatFunction {
    }

    public enum Usage {
        SW_READ_NEVER(0),
        SW_READ_RARELY(2),
        SW_READ_OFTEN(3),
        SW_READ_MASK(15),
        SW_WRITE_NEVER(0),
        SW_WRITE_RARELY(32),
        SW_WRITE_OFTEN(48),
        SW_WRITE_MASK(240),
        SW_OFTEN(SW_READ_OFTEN.q | SW_WRITE_OFTEN.q),
        GPU_SAMPLED_IMAGE(256),
        GPU_COLOR_OUTPUT(512),
        GPU_DATA_BUFFER(16777216),
        GPU_CUBE_MAP(33554432),
        GPU_MIPMAP_COMPLETE(67108864),
        SW_GPU_TEXTURE_SHARED(GPU_SAMPLED_IMAGE.q | SW_OFTEN.q);
        
        public final long q;

        /* access modifiers changed from: public */
        Usage(long j) {
            this.q = j;
        }
    }

    public static native void nativeDestroy(long j);

    @Override
    public void close() {
        dispose();
    }

    public void dispose() {
        long j = this.a;
        if (j != 0) {
            nativeDestroy(j);
            this.a = 0;
        }
    }
}
