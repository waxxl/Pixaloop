package com.example.pixaloop.common.render.utils;

import android.os.Handler;
import com.google.common.util.concurrent.AbstractListeningExecutorService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorService extends AbstractListeningExecutorService {
    public final Handler a;

    public HandlerExecutorService(Handler handler) {
        this.a = handler;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return false;
    }

    public void execute(Runnable runnable) {
        if (!this.a.post(runnable)) {
            throw new RejectedExecutionException();
        }
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public void shutdown() {
        this.a.getLooper().quit();
    }

    public List<Runnable> shutdownNow() {
        this.a.getLooper().quit();
        return new ArrayList();
    }
}
