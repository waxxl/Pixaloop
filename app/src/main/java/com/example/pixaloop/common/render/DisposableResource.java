package com.example.pixaloop.common.render;

public interface DisposableResource extends AutoCloseable {

    void dispose();
}
