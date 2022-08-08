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

// 在数据仓库中托管retrofit对象用于连接云端
public class TestRepository {

    // 静态单例
    private static final TestRepository S_REQUEST_MANAGER = new TestRepository();

    // 禁用构造函数
    private TestRepository() {
    }
    //获取单例对象的方法
    public static TestRepository getInstance() {
        return S_REQUEST_MANAGER;
    }
    // 仓库托管的Retrofit对象
    private final Retrofit retrofit;


    // 静态代码段，在程序初始化（引入仓库时运行）
    {
        //设置打印日志
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 构建okhttpclient，以自定义连接参数
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        // 构建Retrofit对象，单例模式，唯一
        retrofit = new Retrofit.Builder()
                .baseUrl(APIs.TEST_BASE_URL)//设定域名
                .client(client)//输入OkHttpClient
                .addConverterFactory(GsonConverterFactory.create())//设置Gson，翻译json为对象
                .build();
    }

    // 获取数据的函数，输入参数为函数式接口
    public void getTestData(DataResult.Result<Test> result) {
        // 使用retrofit，从interface获取
        TestService service = retrofit.create(TestService.class);
        //获取请求对象
        Call<Test> call = service.testHttp();
        Log.d("http", "test");//日志
        call.enqueue(new Callback<Test>() {  // 发送网络请求，enqueue为异步请求
            //请求成功时回调
            @Override
            public void onResponse(Call<Test> call, Response<Test> response) {
//                    showTV.setText(response.body().show());
                //请求返回结果从Response对象获取，返回的正文从 body() 获取
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
