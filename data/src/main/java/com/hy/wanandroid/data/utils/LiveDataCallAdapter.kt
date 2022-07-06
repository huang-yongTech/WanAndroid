/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hy.wanandroid.data.utils

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.library.util.Constant
import retrofit2.*
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicBoolean
import javax.net.ssl.SSLHandshakeException

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<R>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<R> {
        return object : LiveData<R>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.body())
                        }

                        override fun onFailure(call: Call<R>, e: Throwable) {
                            val errorMessage: String = if (e is TimeoutException) {
                                // 连接超时
                                Constant.ERROR_NAME.TIMEOUT_ERROR
                            } else if (e is ConnectException) {
                                // 服务器异常
                                Constant.ERROR_NAME.CONNECT_ERROR
                            } else if (e is NetworkErrorException) {
                                // 网络无法连接
                                Constant.ERROR_NAME.NET_NOT_CONNECT
                            } else if (e is UnknownHostException) {
                                //Socket异常
                                Constant.ERROR_NAME.SOCKET_ERROR
                            } else if (e is SSLHandshakeException) {
                                Constant.ERROR_NAME.SSL_ERROR
                            } else if (e is HttpException) {
                                if (e.toString().contains("401")) {
                                    Constant.ERROR_NAME.HTTP_401_ERROR;
                                } else {
                                    Constant.ERROR_NAME.SSL_ERROR
                                }
                            } else {
                                Constant.ERROR_NAME.UNKNOWEN_ERROR
                            }
                            val httpResult = JsonRootBean<R>()
                            httpResult.errorMsg = errorMessage
                            httpResult.errorCode = -1
                            postValue(httpResult as R)
                        }
                    })
                }
            }
        }
    }
}
