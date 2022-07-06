package com.hy.wanandroid.data.state

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.library.util.Constant
import com.hy.wanandroid.library.util.RandomUtils
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * author : yonghuang5
 * date : 2022/7/4 11:12
 * description : ViewModel基类
 */
open class BaseViewModel : ViewModel() {
    var isLoadMore: Boolean = false

    protected fun handleObjError(e: Throwable): JsonRootBean<Any?>? {
        val errorMsg = if (e is TimeoutException) {
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
        val result = JsonRootBean<Any?>()
        result.errorMsg = errorMsg
        return result
    }

    protected fun handleStrError(e: Throwable): String {
        return if (e is TimeoutException) {
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
    }
}