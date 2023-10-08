package com.hy.wanandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * author：created by huangyong on 2020/4/8 11:23
 * email：756655135@qq.com
 * description : 用于在多个fragment界面之间通信用的ViewModel。
 */
class SharedViewModel : ViewModel() {
    val menuArticleFlow: MutableSharedFlow<String?> =
        MutableSharedFlow(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val menuJump: UnPeekLiveData<String?> = UnPeekLiveData()
}