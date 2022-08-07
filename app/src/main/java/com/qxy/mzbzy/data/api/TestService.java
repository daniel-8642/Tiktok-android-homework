package com.qxy.mzbzy.data.api;

import com.qxy.mzbzy.data.bean.Test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET("test")
    Call<Test> testHttp();

}
