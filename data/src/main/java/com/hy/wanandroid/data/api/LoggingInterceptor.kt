package com.hy.wanandroid.data.api;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * author : yonghuang5
 * date : 2022/2/14 13:52
 * description :
 */
public class LoggingInterceptor implements Interceptor {
    Charset UTF8 = StandardCharsets.UTF_8;

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Headers headers = request.headers();
        Set<String> names = headers.names();
        StringBuilder headMap = new StringBuilder();
        if (names.size() > 0) {
            for (String key : names) {
                String value = headers.get(key);
                headMap.append(" ")
                        .append(key)
                        .append(":")
                        .append(value)
                        .append(" ;");
            }
        }

        String body = "";
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (charset != null) {
                body = buffer.readString(charset);
            }
        }
        //继续前前进（开始请求）
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);

        String res = "";
        ResponseBody responseBody = response.body();
        MediaType contentType = null;
        if (responseBody != null) {
            contentType = responseBody.contentType();
        }
        if (contentType != null) {
            Charset charset = UTF8;
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            if (charset != null) {
                res = buffer.clone().readString(charset);
            }
        }

        long time = System.currentTimeMillis() - startTime;
        Log.e("--", "\n响应: code:" + response.code()
                + "\nurl：" + response.request().url()
                + "\nheads:" + headMap
                + "\n请求：" + body
                + "\n返回: " + res
                + "\n响应时间：" + time + "毫秒");

        return response;
    }
}
