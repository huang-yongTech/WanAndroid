package com.hy.wanandroid.data.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.hy.wanandroid.data.utils.LiveDataCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 * Created by huangyong on 2017/8/22.
 * Retrofit网络请求工具类
 */
class RetrofitUtils private constructor() {
    private object Holder {
        val INSTANCE = RetrofitUtils()
    }

    /**
     * 常规网络请求
     */
    fun <T> getApiService(clazz: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .proxy(Proxy.NO_PROXY)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(getHttpLoggingInterceptor(true))
            .build()
        val retrofit = buildRetrofit(okHttpClient)
        return retrofit.create(clazz)
    }

    private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private const val baseUrl = "https://www.wanandroid.com/"

        val instance = Holder.INSTANCE

        /**
         * 创建日志拦截器
         *
         * @param isShowBody false 防止上传writeTo重写两次
         */
        fun getHttpLoggingInterceptor(isShowBody: Boolean): HttpLoggingInterceptor {
            val loggingInterceptor =
                HttpLoggingInterceptor { message -> Log.v("OkHttp", "log = $message") }
            if (isShowBody) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            }
            return loggingInterceptor
        }
    }
}