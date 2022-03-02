package com.hy.wanandroid.data.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.hy.wanandroid.data.utils.LiveDataCallAdapterFactory;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

    private static final String baseUrl = "https://www.wanandroid.com/";

    /**
     * 常规网络请求
     */
    public <T> T getApiService(Class<T> clazz) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor(true))
                .build();
        Retrofit retrofit = buildRetrofit(okHttpClient);
        return retrofit.create(clazz);
    }

    private Retrofit buildRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    /**
     * 创建日志拦截器
     *
     * @param isShowBody false 防止上传writeTo重写两次
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor(boolean isShowBody) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@NonNull String message) {
                        Log.v("OkHttp", "log = " + message);
                    }
                });
        if (isShowBody) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        }
        return loggingInterceptor;
    }
}
