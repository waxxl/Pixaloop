package com.example.pixaloop.common.render.utils;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class HandlerExecutorAdapter implements Executor {
    public Handler a;

    public void execute(Runnable runnable) {
        if (!this.a.post(runnable)) {
            throw new RejectedExecutionException();
        }
    }
}
