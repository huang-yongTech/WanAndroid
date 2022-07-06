package com.hy.wanandroid.data.bean

import androidx.lifecycle.Observer

/**
 * author : yonghuang5
 * date : 2022/7/1 10:34
 * description : Observer包装基类
 */
abstract class ResultObserver<T> : Observer<T> where T : JsonRootBean<Any?>? {

    override fun onChanged(result: T?) {
        if (result?.errorCode == 0) {
            onCodeSuccess(result)
        } else {
            onError(result)
        }
    }

    abstract fun onCodeSuccess(result: T?)

    abstract fun onError(result: T?)
}