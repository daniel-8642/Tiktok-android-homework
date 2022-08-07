package com.qxy.mzbzy.data.response;

import androidx.lifecycle.LiveData;

public class DataResult<T> {
    private final T mEntity;

    public DataResult(T entity) {
        mEntity = entity;
    }

    public T getResult() {
        return mEntity;
    }

    @FunctionalInterface
    public interface Result<T> {
        void onResult(DataResult<T> result);
    }
}
