package com.hy.wanandroid.ui.click;

import android.view.View;

import androidx.navigation.Navigation;

/**
 * author：created by huangyong on 2020/3/31 16:56
 * email：756655135@qq.com
 * description :
 */
public class PublicClickProxy {
    /**
     * 公共toolbar点击事件
     *
     * @param view view
     */
    public void clickEvent(View view) {
        Navigation.findNavController(view).navigateUp();
    }
}
