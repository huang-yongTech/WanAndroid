package com.hy.wanandroid.library.util

import android.annotation.SuppressLint
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.appcompat.widget.PopupMenu

/**
 * Created by huangyong on 2018/7/17
 * 菜单功能辅助类
 */
object MenuUtils {
    /**
     * 显示PopupMenu的图标
     *
     * @param popupMenu popupMenu
     */
    @SuppressLint("RestrictedApi")
    fun showMenuIcon(popupMenu: PopupMenu) {
        try {
            val field = popupMenu.javaClass.getDeclaredField("mPopup")
            field.isAccessible = true
            val helper = field[popupMenu] as MenuPopupHelper
            helper.setForceShowIcon(true)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}