package com.example.pixaloop.render;

public final class SkyMotionCoefficients {
    public final float a;
    public float b;

    public static class SkyKeyframe {
        public final float a;
        public final float b;
        public final float c;

        public SkyKeyframe(float f, float f2, float f3) {
            this.b = f2;
            this.a = f;
            this.c = f3;
        }
    }

    public SkyMotionCoefficients(long j) {
        this.a = (float) j;
    }

    public SkyKeyframe a(float f, float f2) {
        float f3 = (f / 100.0f) * this.a;
        this.b = f2 * 0.1f;
        return new SkyKeyframe(b(f3), b((this.a * 0.5f) + f3), a(f3));
    }

    public final float b(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        float f2 = this.a;
        float f3 = this.b;
        return (((-2.0f * f3) / f2) * (f % f2)) + f3;
    }

    public final float a(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        float f2 = this.a;
        return Math.abs(((-2.0f / f2) * (f % f2)) + 1.0f);
    }
}
