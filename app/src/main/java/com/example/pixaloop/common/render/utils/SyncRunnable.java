package com.example.pixaloop.common.render.utils;

import android.os.ConditionVariable;

public class SyncRunnable implements Runnable {
    public final ConditionVariable a;
    public final Runnable b;

    public SyncRunnable(Runnable runnable, ConditionVariable conditionVariable) {
        this.a = conditionVariable;
        this.b = runnable;
    }

    public void run() {
        try {
            this.b.run();
        } finally {
            this.a.open();
        }
    }
}
