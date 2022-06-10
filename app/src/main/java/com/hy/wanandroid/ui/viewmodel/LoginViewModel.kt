package com.hy.wanandroid.ui.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.hy.wanandroid.ui.R;

/**
 * author：created by huangyong on 2020/4/7 15:58
 * email：756655135@qq.com
 * description :
 */
public class LoginViewModel extends ViewModel {
    //用户民
    public final ObservableField<String> mUserName = new ObservableField<>();

    //密码
    public final ObservableField<String> mUserPwd = new ObservableField<>();

    //点击事件
    public void clickEvent(View view) {
        switch (view.getId()) {
            case R.id.login_btn:

                break;
            case R.id.login_register_btn:

                break;
        }
    }
}
