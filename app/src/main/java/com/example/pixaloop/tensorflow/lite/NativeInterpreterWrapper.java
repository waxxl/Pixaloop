package com.example.pixaloop.tensorflow.lite;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class NativeInterpreterWrapper implements AutoCloseable {
    public long a;
    public long b;
    public long c;
    public long d = -1;
    public ByteBuffer e;
    public Map<String, Integer> f;
    public Map<String, Integer> g;
    public Tensor[] h;
    public Tensor[] i;
    public boolean j = false;
    public final List<Delegate> k = new ArrayList();

    static {
        TensorFlowLite.a();
    }

    public NativeInterpreterWrapper(ByteBuffer byteBuffer, Interpreter.Options options) {
        if (byteBuffer == null || (!(byteBuffer instanceof MappedByteBuffer) && (!byteBuffer.isDirect() || byteBuffer.order() != ByteOrder.nativeOrder()))) {
            throw new IllegalArgumentException("Model ByteBuffer should be either a MappedByteBuffer of the model file, or a direct ByteBuffer using ByteOrder.nativeOrder() which contains bytes of model content.");
        }
        this.e = byteBuffer;
        long createErrorReporter = createErrorReporter(Database.MAX_BLOB_LENGTH);
        a(createErrorReporter, createModelWithBuffer(this.e, createErrorReporter), options);
    }

    public static native long allocateTensors(long j2, long j3);

    public static native void allowBufferHandleOutput(long j2, boolean z);

    public static native void allowFp16PrecisionForFp32(long j2, boolean z);

    public static native void applyDelegate(long j2, long j3, long j4);

    public static native long createErrorReporter(int i2);

    public static native long createInterpreter(long j2, long j3, int i2);

    public static native long createModelWithBuffer(ByteBuffer byteBuffer, long j2);

    public static native void delete(long j2, long j3, long j4);

    public static native int getInputCount(long j2);

    public static native int getInputTensorIndex(long j2, int i2);

    public static native int getOutputCount(long j2);

    public static native int getOutputTensorIndex(long j2, int i2);

    public static native boolean resizeInput(long j2, long j3, int i2, int[] iArr);

    public static native void run(long j2, long j3);

    public static native void useNNAPI(long j2, boolean z);

    public final void a(long j2, long j3, Interpreter.Options options) {
        if (options == null) {
            options = new Interpreter.Options();
        }
        this.a = j2;
        this.c = j3;
        this.b = createInterpreter(j3, j2, options.a);
        this.h = new Tensor[getInputCount(this.b)];
        this.i = new Tensor[getOutputCount(this.b)];
        Boolean bool = options.b;
        if (bool != null) {
            a(bool.booleanValue());
        }
        Boolean bool2 = options.c;
        if (bool2 != null) {
            allowFp16PrecisionForFp32(this.b, bool2.booleanValue());
        }
        Boolean bool3 = options.d;
        if (bool3 != null) {
            allowBufferHandleOutput(this.b, bool3.booleanValue());
        }
        for (Delegate next : options.e) {
            applyDelegate(this.b, j2, next.a());
            this.k.add(next);
        }
        allocateTensors(this.b, j2);
        this.j = true;
    }

    public Tensor b(int i2) {
        if (i2 >= 0) {
            Tensor[] tensorArr = this.i;
            if (i2 < tensorArr.length) {
                Tensor tensor = tensorArr[i2];
                if (tensor != null) {
                    return tensor;
                }
                long j2 = this.b;
                Tensor a2 = Tensor.a(j2, getOutputTensorIndex(j2, i2));
                tensorArr[i2] = a2;
                return a2;
            }
        }
        throw new IllegalArgumentException("Invalid output Tensor index: " + i2);
    }

    public void close() {
        int i2 = 0;
        while (true) {
            Tensor[] tensorArr = this.h;
            if (i2 >= tensorArr.length) {
                break;
            }
            if (tensorArr[i2] != null) {
                tensorArr[i2].b();
                this.h[i2] = null;
            }
            i2++;
        }
        int i3 = 0;
        while (true) {
            Tensor[] tensorArr2 = this.i;
            if (i3 < tensorArr2.length) {
                if (tensorArr2[i3] != null) {
                    tensorArr2[i3].b();
                    this.i[i3] = null;
                }
                i3++;
            } else {
                delete(this.a, this.c, this.b);
                this.a = 0;
                this.c = 0;
                this.b = 0;
                this.e = null;
                this.f = null;
                this.g = null;
                this.j = false;
                this.k.clear();
                return;
            }
        }
    }

    public void a(Object[] objArr, Map<Integer, Object> map) {
        this.d = -1;
        if (objArr == null || objArr.length == 0) {
            throw new IllegalArgumentException("Input error: Inputs should not be null or empty.");
        } else if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Input error: Outputs should not be null or empty.");
        } else {
            int i2 = 0;
            for (int i3 = 0; i3 < objArr.length; i3++) {
                int[] e2 = a(i3).e(objArr[i3]);
                if (e2 != null) {
                    a(i3, e2);
                }
            }
            boolean z = !this.j;
            if (z) {
                allocateTensors(this.b, this.a);
                this.j = true;
            }
            for (int i4 = 0; i4 < objArr.length; i4++) {
                a(i4).g(objArr[i4]);
            }
            long nanoTime = System.nanoTime();
            run(this.b, this.a);
            long nanoTime2 = System.nanoTime() - nanoTime;
            if (z) {
                while (true) {
                    Tensor[] tensorArr = this.i;
                    if (i2 >= tensorArr.length) {
                        break;
                    }
                    if (tensorArr[i2] != null) {
                        tensorArr[i2].d();
                    }
                    i2++;
                }
            }
            for (Map.Entry next : map.entrySet()) {
                b(((Integer) next.getKey()).intValue()).c(next.getValue());
            }
            this.d = nanoTime2;
        }
    }

    public void a(int i2, int[] iArr) {
        if (resizeInput(this.b, this.a, i2, iArr)) {
            this.j = false;
            Tensor[] tensorArr = this.h;
            if (tensorArr[i2] != null) {
                tensorArr[i2].d();
            }
        }
    }

    public void a(boolean z) {
        useNNAPI(this.b, z);
    }

    public Tensor a(int i2) {
        if (i2 >= 0) {
            Tensor[] tensorArr = this.h;
            if (i2 < tensorArr.length) {
                Tensor tensor = tensorArr[i2];
                if (tensor != null) {
                    return tensor;
                }
                long j2 = this.b;
                Tensor a2 = Tensor.a(j2, getInputTensorIndex(j2, i2));
                tensorArr[i2] = a2;
                return a2;
            }
        }
        throw new IllegalArgumentException("Invalid input Tensor index: " + i2);
    }
}
