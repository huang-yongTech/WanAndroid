package com.hy.wanandroid.ui.viewmodel

import android.view.View
import com.hy.wanandroid.ui.R
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField

/**
 * author：created by huangyong on 2020/4/7 15:58
 * email：756655135@qq.com
 * description :
 */
class LoginViewModel : ViewModel() {
    //用户民
    @JvmField
    val mUserName = ObservableField<String>()

    //密码
    @JvmField
    val mUserPwd = ObservableField<String>()

    //点击事件
    fun clickEvent(view: View) {
        when (view.id) {
            R.id.login_btn -> {}
            R.id.login_register_btn -> {}
        }
    }
}