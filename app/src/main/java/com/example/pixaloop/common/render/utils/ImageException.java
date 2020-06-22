package com.example.pixaloop.common.render.utils;

public class ImageException extends Exception {
    public ImageException() {
    }

    public ImageException(String str) {
        super(str);
    }

    public ImageException(String str, Throwable th) {
        super(str, th);
    }
}
