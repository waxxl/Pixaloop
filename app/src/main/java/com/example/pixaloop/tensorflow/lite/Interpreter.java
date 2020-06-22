package com.example.pixaloop.tensorflow.lite;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Interpreter implements AutoCloseable {
    public NativeInterpreterWrapper a;

    public static class Options {
        public int a = -1;
        public Boolean b;
        public Boolean c;
        public Boolean d;
        public final List<Delegate> e = new ArrayList();

        public Options a(boolean z) {
            this.b = Boolean.valueOf(z);
            return this;
        }
    }

    public Interpreter(ByteBuffer byteBuffer, Options options) {
        this.a = new NativeInterpreterWrapper(byteBuffer, options);
    }

    public void a(Object obj, Object obj2) {
        Object[] objArr = {obj};
        HashMap hashMap = new HashMap();
        hashMap.put(0, obj2);
        a(objArr, (Map<Integer, Object>) hashMap);
    }

    public final void b() {
        if (this.a == null) {
            throw new IllegalStateException("Internal error: The Interpreter has already been closed.");
        }
    }

    public void close() {
        NativeInterpreterWrapper nativeInterpreterWrapper = this.a;
        if (nativeInterpreterWrapper != null) {
            nativeInterpreterWrapper.close();
            this.a = null;
        }
    }

    public void finalize() {
        try {
            close();
        } finally {
            try {
                super.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void a(Object[] objArr, Map<Integer, Object> map) {
        b();
        this.a.a(objArr, map);
    }
}
