package com.hy.wanandroid.data.state

import com.hy.wanandroid.data.bean.JsonRootBean

/**
 * author : yonghuang5
 * date : 2022/7/4 11:02
 * description : UI状态类
 */
sealed class DataState<out T> {
    //返回成功结果
    data class OnSuccess<T>(var result: JsonRootBean<T>?) : DataState<T>()

    data class OnCodeError<T>(var error: JsonRootBean<T>?) : DataState<T>()

    //网络异常，失败结果
    data class OnNetError(var error: JsonRootBean<Any?>?) : DataState<Nothing>()
}
