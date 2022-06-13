package com.hy.wanandroid.library.util

import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Created by huangyong on 2018/5/7
 * BottomSheetDialog辅助工具类
 */
object BottomDialogUtils {
    /**
     * 隐藏底部选择菜单
     */
    fun hideBottomShitDialog(bottomDialog: BottomSheetDialog?) {
        if (bottomDialog != null && bottomDialog.isShowing) bottomDialog.dismiss()
    }
}