package com.hy.wanandroid.library.util;

import android.content.Context;
import android.content.res.Resources;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by huangyong on 2017/9/12.
 * 一些常用工具类
 */

public final class BarUtils {
    private BarUtils() {
    }

    /**
     * 获取状态栏的高度
     *
     * @param context 当前上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourcesId);
    }

    /**
     * 设置ToolBar的MarginTop属性(父布局为AppBarLayout)
     *
     * @param context context参数
     * @param toolbar toolbar
     */
    public static void setAppToolBarMarginTop(Context context, Toolbar toolbar) {
        int statusBarHeight = BarUtils.getStatusBarHeight(context);
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        layoutParams.setMargins(0, statusBarHeight, 0, 0);
        toolbar.setLayoutParams(layoutParams);
    }
}
