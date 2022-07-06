package com.hy.wanandroid.data.bean

import androidx.lifecycle.Observer
import com.hy.wanandroid.data.state.UiState

/**
 * author : yonghuang5
 * date : 2022/7/5 14:24
 * description : 基础观察者类
 */
abstract class BaseResultObserver<T> : Observer<T> where T : UiState {
    override fun onChanged(t: T?) {
        when (t) {
            is UiState.Success<*> -> onCodeSuccess(t)
            is UiState.Error<*> -> onError(t)
        }
    }

    abstract fun onCodeSuccess(result: UiState.Success<*>?)

//    abstract fun onCodeError(result: UiState.CodeError?)

    abstract fun onError(result: UiState.Error<*>?)
}