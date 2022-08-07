package com.qxy.mzbzy.data.repository;

import android.util.Log;

import com.qxy.mzbzy.data.api.APIs;
import com.qxy.mzbzy.data.api.TestService;
import com.qxy.mzbzy.data.bean.Test;
import com.qxy.mzbzy.data.response.DataResult;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRepository {

        private static final TestRepository S_REQUEST_MANAGER = new TestRepository();

        private TestRepository() {
        }

        public static TestRepository getInstance() {
            return S_REQUEST_MANAGER;
        }

        private final Retrofit retrofit;

        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(8, TimeUnit.SECONDS)
                    .readTimeout(8, TimeUnit.SECONDS)
                    .writeTimeout(8, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIs.TEST_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public void getTestData(DataResult.Result<Test> result) {
            TestService service = retrofit.create(TestService.class);
            Call<Test> call = service.testHttp();
            Log.d("http","test");
            call.enqueue(new Callback<Test>() {  // 发送网络请求
                //请求成功时回调
                @Override
                public void onResponse(Call<Test> call, Response<Test> response) {
//                    showTV.setText(response.body().show());
                    result.onResult(new DataResult<>(response.body()));
                }

                //请求失败时回调
                @Override
                public void onFailure(Call<Test> call, Throwable t) {
                    Log.e("testRepository", "连接服务器失败！ ");
//                    Toast.makeText(MainActivity.this, "连接服务器失败！", Toast.LENGTH_SHORT).show();
                }
            });
        }


}
