package com.hy.wanandroid.ui.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.hy.wanandroid.ui.R;

/**
 * author：created by huangyong on 2020/3/31 14:47
 * email：756655135@qq.com
 * description :
 */
public class DrawerViewModel extends ViewModel {

    //drawer栏图标点击事件
    public void clickEvent(View view) {
        Navigation.findNavController(view).navigate(R.id.action_drawer_to_login_fragment);
    }
}
