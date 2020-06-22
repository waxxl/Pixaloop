package com.example.pixaloop.tensorflow.lite;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public final class Tensor {
    public long a;
    public final DataType b;
    public int[] c;

    static {
        TensorFlowLite.a();
    }

    public Tensor(long j) {
        this.a = j;
        this.b = DataType.a(dtype(j));
        this.c = shape(j);
    }

    public static Tensor a(long j, int i) {
        return new Tensor(create(j, i));
    }

    public static native ByteBuffer buffer(long j);

    public static native long create(long j, int i);

    public static native void delete(long j);

    public static native int dtype(long j);

    public static boolean f(Object obj) {
        return obj instanceof ByteBuffer;
    }

    public static native boolean hasDelegateBufferHandle(long j);

    public static native int numBytes(long j);

    public static native void readMultiDimensionalArray(long j, Object obj);

    public static native int[] shape(long j);

    public static native void writeDirectBuffer(long j, ByteBuffer byteBuffer);

    public static native void writeMultiDimensionalArray(long j, Object obj);

    public void b() {
        delete(this.a);
        this.a = 0;
    }

    public int c() {
        return numBytes(this.a);
    }

    public void d() {
        this.c = shape(this.a);
    }

    public int[] e(Object obj) {
        if (obj == null || f(obj)) {
            return null;
        }
        j(obj);
        int[] b2 = b(obj);
        if (Arrays.equals(this.c, b2)) {
            return null;
        }
        return b2;
    }

    public void g(Object obj) {
        if (obj != null) {
            h(obj);
            if (f(obj)) {
                ByteBuffer byteBuffer = (ByteBuffer) obj;
                if (!byteBuffer.isDirect() || byteBuffer.order() != ByteOrder.nativeOrder()) {
                    a().put(byteBuffer);
                } else {
                    writeDirectBuffer(this.a, byteBuffer);
                }
            } else {
                writeMultiDimensionalArray(this.a, obj);
            }
        } else if (!hasDelegateBufferHandle(this.a)) {
            throw new IllegalArgumentException("Null inputs are allowed only if the Tensor is bound to a buffer handle.");
        }
    }

    public final void h(Object obj) {
        j(obj);
        i(obj);
    }

    public final void i(Object obj) {
        if (f(obj)) {
            ByteBuffer byteBuffer = (ByteBuffer) obj;
            if (byteBuffer.capacity() != c()) {
                throw new IllegalArgumentException(String.format("Cannot convert between a TensorFlowLite buffer with %d bytes and a ByteBuffer with %d bytes.", new Object[]{Integer.valueOf(c()), Integer.valueOf(byteBuffer.capacity())}));
            }
            return;
        }
        int[] b2 = b(obj);
        if (!Arrays.equals(b2, this.c)) {
            throw new IllegalArgumentException(String.format("Cannot copy between a TensorFlowLite tensor with shape %s and a Java object with shape %s.", new Object[]{Arrays.toString(this.c), Arrays.toString(b2)}));
        }
    }

    public final void j(Object obj) {
        DataType d;
        DataType dataType;
        if (!f(obj) && (d = d(obj)) != (dataType = this.b)) {
            throw new IllegalArgumentException(String.format("Cannot convert between a TensorFlowLite tensor with type %s and a Java object of type %s (which is compatible with the TensorFlowLite type %s).", new Object[]{dataType, obj.getClass().getName(), d}));
        }
    }

    public static int a(Object obj) {
        if (obj == null || !obj.getClass().isArray()) {
            return 0;
        }
        if (Array.getLength(obj) != 0) {
            return a(Array.get(obj, 0)) + 1;
        }
        throw new IllegalArgumentException("Array lengths cannot be 0.");
    }

    public static DataType d(Object obj) {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            while (cls.isArray()) {
                cls = cls.getComponentType();
            }
            if (Float.TYPE.equals(cls)) {
                return DataType.FLOAT32;
            }
            if (Integer.TYPE.equals(cls)) {
                return DataType.INT32;
            }
            if (Byte.TYPE.equals(cls)) {
                return DataType.UINT8;
            }
            if (Long.TYPE.equals(cls)) {
                return DataType.INT64;
            }
            if (String.class.equals(cls)) {
                return DataType.STRING;
            }
        }
        throw new IllegalArgumentException("DataType error: cannot resolve DataType of " + obj.getClass().getName());
    }

    public Object c(Object obj) {
        if (obj != null) {
            h(obj);
            if (obj instanceof ByteBuffer) {
                ((ByteBuffer) obj).put(a());
                return obj;
            }
            readMultiDimensionalArray(this.a, obj);
            return obj;
        } else if (hasDelegateBufferHandle(this.a)) {
            return obj;
        } else {
            throw new IllegalArgumentException("Null outputs are allowed only if the Tensor is bound to a buffer handle.");
        }
    }

    public static int[] b(Object obj) {
        int[] iArr = new int[a(obj)];
        a(obj, 0, iArr);
        return iArr;
    }

    public static void a(Object obj, int i, int[] iArr) {
        if (iArr != null && i != iArr.length) {
            int length = Array.getLength(obj);
            if (iArr[i] == 0) {
                iArr[i] = length;
            } else if (iArr[i] != length) {
                throw new IllegalArgumentException(String.format("Mismatched lengths (%d and %d) in dimension %d", new Object[]{Integer.valueOf(iArr[i]), Integer.valueOf(length), Integer.valueOf(i)}));
            }
            for (int i2 = 0; i2 < length; i2++) {
                a(Array.get(obj, i2), i + 1, iArr);
            }
        }
    }

    public final ByteBuffer a() {
        return buffer(this.a).order(ByteOrder.nativeOrder());
    }
}
