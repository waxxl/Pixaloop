package com.example.pixaloop.tensorflow.lite;

public enum DataType {
    FLOAT32(1),
    INT32(2),
    UINT8(3),
    INT64(4),
    STRING(5);
    
    public static DataType[] f = null;
    public final int h;

    /* renamed from: org.tensorflow.lite.DataType$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static /* synthetic */ int[] a = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            a = new int[DataType.values().length];
            a[DataType.FLOAT32.ordinal()] = 1;
            a[DataType.INT32.ordinal()] = 2;
            a[DataType.UINT8.ordinal()] = 3;
            a[DataType.INT64.ordinal()] = 4;
            a[DataType.STRING.ordinal()] = 5;
        }
    }

    static {
        f = values();
    }

    /* access modifiers changed from: public */
    DataType(int i) {
        this.h = i;
    }

    public static DataType a(int i) {
        for (DataType dataType : f) {
            if (dataType.h == i) {
                return dataType;
            }
        }
        throw new IllegalArgumentException("DataType error: DataType " + i + " is not recognized in Java (version " + TensorFlowLite.runtimeVersion() + ")");
    }
}
