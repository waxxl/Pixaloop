package com.example.pixaloop.application;

import android.app.Application;
import android.content.Context;

public class PixaApplication extends Application {
    private Context context;

    static {
        //System.loadLibrary("pixaloop");
        //System.loadLibrary("native-lib");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        context = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Context getContext() {
        return context;
    }
}
