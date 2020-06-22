package com.example.pixaloop.util;

import androidx.annotation.NonNull;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

public class SelfDisposableEvent<T> {
    public final T a;
    public boolean b = false;

    public SelfDisposableEvent(@NonNull T t) {
        Preconditions.checkNotNull(t);
        this.a = t;
    }

    @Nullable
    public T a() {
        T t = this.b ? null : this.a;
        this.b = true;
        return t;
    }

    public boolean b() {
        return this.b;
    }
}
