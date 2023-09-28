package com.hy.wanandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * author：created by huangyong on 2020/4/8 11:23
 * email：756655135@qq.com
 * description : 用于在多个fragment界面之间通信用的ViewModel。
 */
class SharedViewModel : ViewModel() {
    val menuJumpFlow: MutableSharedFlow<String?> = MutableSharedFlow()
}