package com.hy.wanandroid.library.util

import android.content.res.Resources
import android.widget.Spinner

/**
 * Created by huangyong on 2017/8/23.
 * 尺寸转换工具类
 */
object SizeUtils {
    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    @JvmStatic
    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 获取spinner高度并设置弹出框竖直偏移量
     *
     * @param spinner 传入的spinner组件
     */
    fun setSpinnerDropDown(spinner: Spinner) {
        spinner.post {
            val dropDownOffset = spinner.height
            spinner.dropDownVerticalOffset = dropDownOffset
        }
    }
}