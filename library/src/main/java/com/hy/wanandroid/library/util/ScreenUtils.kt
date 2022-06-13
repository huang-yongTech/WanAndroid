package com.hy.wanandroid.library.util

import android.content.Context

/**
 * Created by huangyong on 2017/9/25.
 * 手机屏幕相关工具类
 */
object ScreenUtils {
    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(context: Context): Int {
        val resources = context.resources
        return resources.displayMetrics.widthPixels
    }
}