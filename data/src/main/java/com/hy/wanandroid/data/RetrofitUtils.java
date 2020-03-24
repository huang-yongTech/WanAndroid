package com.hy.wanandroid.data;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huangyong on 2017/8/22.
 * Retrofit网络请求工具类
 */

public class RetrofitUtils {
    private RetrofitUtils() {
    }

    private static final class Holder {
        private static final RetrofitUtils RETROFIT_UTILS = new RetrofitUtils();
    }

    public static RetrofitUtils getInstance() {
        return Holder.RETROFIT_UTILS;
    }

    private static final String baseUrl = "https://www.wanandroid.com";

    /**
     * 常规网络请求
     */
    public <T> T getApiService(Class<T> clazz) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = buildRetrofit(okHttpClient);
        return retrofit.create(clazz);
    }

    private Retrofit buildRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
