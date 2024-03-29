package com.hy.wanandroid.data.state

/**
 * author : yonghuang5
 * date : 2022/7/4 11:02
 * description : UI状态类
 */
sealed class UiState {
    //返回成功结果
    data class Success<T>(var result: T) : UiState()

    //网络异常，失败结果
    data class Error<T>(var error: T) : UiState()
}
