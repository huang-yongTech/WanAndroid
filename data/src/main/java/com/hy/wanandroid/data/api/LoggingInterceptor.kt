package com.hy.wanandroid.data.api

import android.util.Log
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets
import java.nio.charset.UnsupportedCharsetException

/**
 * author : yonghuang5
 * date : 2022/2/14 13:52
 * description :
 */
class LoggingInterceptor : Interceptor {
    var UTF8 = StandardCharsets.UTF_8
    @kotlin.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers
        val names = headers.names()
        val headMap = StringBuilder()
        if (names.size > 0) {
            for (key in names) {
                val value = headers[key]
                headMap.append(" ")
                    .append(key)
                    .append(":")
                    .append(value)
                    .append(" ;")
            }
        }
        var body = ""
        val requestBody = request.body
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            var charset = UTF8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            if (charset != null) {
                body = buffer.readString(charset)
            }
        }
        //继续前前进（开始请求）
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(request)
        var res = ""
        val responseBody = response.body
        var contentType: MediaType? = null
        if (responseBody != null) {
            contentType = responseBody.contentType()
        }
        if (contentType != null) {
            var charset = UTF8
            try {
                charset = contentType.charset(UTF8)
            } catch (e: UnsupportedCharsetException) {
                e.printStackTrace()
            }
            val source = responseBody!!.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            if (charset != null) {
                res = buffer.clone().readString(charset)
            }
        }
        val time = System.currentTimeMillis() - startTime
        Log.e(
            "--", """
     
     响应: code:${response.code}
     url：${response.request.url}
     heads:$headMap
     请求：$body
     返回: $res
     响应时间：${time}毫秒
     """.trimIndent()
        )
        return response
    }
}