package com.hy.wanandroid.library.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hy.wanandroid.library.R
import com.hy.wanandroid.library.util.BarUtils


/**
 * BaseFragment
 */
abstract class BaseFragment protected constructor() : Fragment() {
    private val mParam1: String? = null
    private val mParam2: String? = null
    private var mStatusBarView: View? = null
    private val mViewGroup: ViewGroup? = null

    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null

    protected open lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

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

    protected open fun <T : ViewModel> getFragmentScopeViewModel(modelClass: Class<T>): T? {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider?.get(modelClass)
    }

    protected open fun <T : ViewModel> getActivityScopeViewModel(
        activity: AppCompatActivity,
        modelClass: Class<T>
    ): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(activity)
        }
        return mActivityProvider?.get(modelClass)
    }

    protected open fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T? {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(MBaseApplication.getInstance())
        }
        return mApplicationProvider?.get(modelClass)
    }

    protected open fun getApplicationFactory(activity: Activity): ViewModelProvider.Factory {
        checkActivity(this)
        val application: Application = checkApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    protected open fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }

    protected open fun checkActivity(fragment: Fragment) {
        val activity = fragment.activity
            ?: throw IllegalStateException("Can't create ViewModelProvider for detached fragment")
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
    protected open fun getData() {}

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