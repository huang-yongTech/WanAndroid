package com.hy.wanandroid.library.util

import android.app.Activity
import com.hy.wanandroid.library.util.ActivityManagerUtils
import com.hy.wanandroid.library.util.ActivityManagerUtils.ActivityManagerHolder
import android.view.ViewGroup
import com.hy.wanandroid.library.util.AndroidBug5497Workaround
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.hy.wanandroid.library.util.BarUtils
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hy.wanandroid.library.util.HanZiToPinyin
import okhttp3.ResponseBody
import com.hy.wanandroid.library.util.CloseUtils
import com.hy.wanandroid.library.util.KeyboardUtils
import android.annotation.SuppressLint
import androidx.appcompat.view.menu.MenuPopupHelper
import com.hy.wanandroid.library.util.PhoneUtils
import android.content.Intent
import android.provider.MediaStore
import com.hy.wanandroid.library.util.SDCardUtils
import android.provider.DocumentsContract
import android.content.ContentUris
import android.widget.Toast
import android.media.MediaScannerConnection
import androidx.annotation.ColorInt
import android.content.SharedPreferences
import androidx.collection.SimpleArrayMap
import com.hy.wanandroid.library.util.SPUtils
import java.util.ArrayList

/**
 * Created by huangyong on 2017/10/23.
 * Activity管理类
 * 将每个启动的activity添加到列表中，方便集中销毁
 */
class ActivityManagerUtils private constructor() {
    private val allActivities: MutableList<Activity> = ArrayList()

    private object ActivityManagerHolder {
        val instance = ActivityManagerUtils()
    }

    //在Activity基类的onCreate()方法中执行
    fun addActivity(activity: Activity) {
        allActivities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        allActivities.remove(activity)
    }

    //注销是销毁所有的Activity
    fun OutSign() {
        for (activity in allActivities) {
            activity?.finish()
        }
    }

    companion object {
        val instance: ActivityManagerUtils
            get() = ActivityManagerHolder.instance
    }
}