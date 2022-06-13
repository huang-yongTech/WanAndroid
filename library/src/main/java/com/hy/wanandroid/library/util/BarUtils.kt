package com.hy.wanandroid.library.util

import com.google.android.material.appbar.AppBarLayout
import android.content.Context
import androidx.appcompat.widget.Toolbar

/**
 * Created by huangyong on 2017/9/12.
 * 一些常用工具类
 */
object BarUtils {
    /**
     * 获取状态栏的高度
     *
     * @param context 当前上下文
     * @return 状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        val resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourcesId)
    }

    /**
     * 设置ToolBar的MarginTop属性(父布局为AppBarLayout)
     *
     * @param context context参数
     * @param toolbar toolbar
     */
    fun setAppToolBarMarginTop(context: Context, toolbar: Toolbar?) {
        val statusBarHeight = getStatusBarHeight(context)
        val layoutParams = toolbar?.layoutParams as AppBarLayout.LayoutParams
        layoutParams.setMargins(0, statusBarHeight, 0, 0)
        toolbar.layoutParams = layoutParams
    }
}