package com.qxy.mzbzy.data.response;
// 函数式接口
// 使用示例 repository.getTestData(test -> vm.setTestdata(test));
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
