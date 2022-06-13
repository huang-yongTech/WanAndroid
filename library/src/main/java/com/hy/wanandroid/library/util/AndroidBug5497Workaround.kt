package com.hy.wanandroid.library.util

import android.view.ViewGroup
import android.graphics.Rect
import android.view.View

/**
 * 软键盘弹出辅助类
 * 使软件盘弹出时，底部组件能被软键盘顶起
 */
class AndroidBug5497Workaround private constructor(content: View?) {
    private var mChildOfContent: View? = null
    private var usableHeightPrevious = 0
    private var frameLayoutParams: ViewGroup.LayoutParams? = null
    private fun possiblyResizeChildOfContent() {
        val usableHeightNow = computeUsableHeight()
        if (usableHeightNow != usableHeightPrevious) {
            //如果两次高度不一致
            //将计算的可视高度设置成视图的高度
            frameLayoutParams!!.height = usableHeightNow
            mChildOfContent!!.requestLayout() //请求重新布局
            usableHeightPrevious = usableHeightNow
        }
    }

    private fun computeUsableHeight(): Int {
        //计算视图可视高度
        val r = Rect()
        mChildOfContent!!.getWindowVisibleDisplayFrame(r)
        return r.bottom
    }

    companion object {
        fun assistActivity(content: View?) {
            AndroidBug5497Workaround(content)
        }
    }

    init {
        if (content != null) {
            mChildOfContent = content
            mChildOfContent?.viewTreeObserver?.addOnGlobalLayoutListener { possiblyResizeChildOfContent() }
            frameLayoutParams = mChildOfContent?.layoutParams
        }
    }
}