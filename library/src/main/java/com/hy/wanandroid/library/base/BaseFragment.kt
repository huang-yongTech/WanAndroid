package com.hy.wanandroid.library.base

import android.view.ViewGroup
import android.os.Bundle
import com.hy.wanandroid.library.util.BarUtils
import com.hy.wanandroid.library.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * BaseFragment
 */
abstract class BaseFragment protected constructor() : Fragment() {
    private val mParam1: String? = null
    private val mParam2: String? = null
    private var mStatusBarView: View? = null
    private val mViewGroup: ViewGroup? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addStatusBar()
    }

    /**
     * 添加一个空的状态栏
     */
    private fun addStatusBar() {
        if (mStatusBarView == null) {
            mStatusBarView = View(context)
            val screenWidth = resources.displayMetrics.widthPixels
            val statusBarHeight = BarUtils.getStatusBarHeight(requireContext())
            val params = ViewGroup.LayoutParams(screenWidth, statusBarHeight)
            mStatusBarView!!.layoutParams = params
            mStatusBarView!!.setBackgroundColor(Color.BLACK)
            mStatusBarView!!.requestLayout()
            mViewGroup?.addView(mStatusBarView, 0)
        }
    }

    /**
     * 正在加载中界面
     */
    protected fun getLoadingView(viewGroup: ViewGroup?): View {
        return layoutInflater.inflate(R.layout.view_loading, viewGroup, false)
    }

    /**
     * 空数据界面
     */
    protected fun getEmptyDataView(viewGroup: ViewGroup?): View {
        val notDataView = layoutInflater.inflate(R.layout.view_empty, viewGroup, false)
        notDataView.setOnClickListener { getData() }
        return notDataView
    }

    /**
     * 加载数据出错界面
     */
    protected fun getErrorView(viewGroup: ViewGroup?): View {
        val errorView = layoutInflater.inflate(R.layout.view_network_error, viewGroup, false)
        errorView.setOnClickListener { getData() }
        return errorView
    }

    /**
     * 获取并刷新数据（子类可实现该方法）
     */
    protected abstract fun getData()

    /**
     * TextView等组件drawableEnd图标的点击事件
     *
     * @param tv tv
     */
    @SuppressLint("ClickableViewAccessibility")
    protected fun clickRightClear(tv: TextView?) {
        tv?.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                clickRightClear(tv, event)
            } else false
        }
    }

    /**
     * TextView右侧drawable图片清空按钮点击事件处理
     */
    private fun clickRightClear(tv: TextView, event: MotionEvent): Boolean {
        val drawableRight = tv.compoundDrawables[2]
        if (drawableRight != null && event.rawX >= tv.right - drawableRight.bounds.width()) {
            tv.text = null
            return true
        }
        return false
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
    }
}