package com.hy.wanandroid.library.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.abs

/**
 * Created by huangyong on 2018/3/12.
 * 软键盘相关工具类
 */
object KeyboardUtils {
    /**
     * 如果软键盘打开，则收起软键盘
     *
     * @param activity 当前activity
     */
    fun hintKeyBoard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && activity.currentFocus != null) {
            if (activity.currentFocus!!.windowToken != null) {
                imm.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }

    /**
     * Hide the soft input.
     *
     * @param view The view.
     */
    fun hideSoftInput(view: View?) {
        val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            ?: return
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * 判断软件盘是否可见
     *
     * @param activity activity
     * @return 可见true，不可见false
     */
    fun isSoftInputVisible(activity: Activity): Boolean {
        return getDecorViewInvisibleHeight(activity) > 0
    }

    private var sDecorViewDelta = 0

    /**
     * 获取DecorView的可见高度
     *
     * @param activity activity
     * @return DecorView可见高度
     */
    private fun getDecorViewInvisibleHeight(activity: Activity): Int {
        val decorView = activity.window.decorView
        val outRect = Rect()
        decorView.getWindowVisibleDisplayFrame(outRect)
        Log.i(
            "KeyboardUtils", "getDecorViewInvisibleHeight: "
                    + (decorView.bottom - outRect.bottom)
        )
        val delta = abs(decorView.bottom - outRect.bottom)
        if (delta <= navBarHeight) {
            sDecorViewDelta = delta
            return 0
        }
        return delta - sDecorViewDelta
    }

    /**
     * 获取底部导航栏高度
     *
     * @return 导航栏高度
     */
    private val navBarHeight: Int
        get() {
            val res = Resources.getSystem()
            val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
            return if (resourceId != 0) {
                res.getDimensionPixelSize(resourceId)
            } else {
                0
            }
        }
}