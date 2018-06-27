package com.chyngyz.testapp.ui;

public interface ILifecycle<V> {
    void bind(V view);

    void unbind();
}
