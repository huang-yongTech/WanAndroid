package com.hy.wanandroid.library.util;

import android.annotation.SuppressLint;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;

import java.lang.reflect.Field;

/**
 * Created by huangyong on 2018/7/17
 * 菜单功能辅助类
 */
public class MenuUtils {
    private MenuUtils() {
    }

    /**
     * 显示PopupMenu的图标
     *
     * @param popupMenu popupMenu
     */
    @SuppressLint("RestrictedApi")
    public static void showMenuIcon(PopupMenu popupMenu) {
        try {
            Field field = popupMenu.getClass().getDeclaredField("mPopup");
            field.setAccessible(true);
            MenuPopupHelper helper = (MenuPopupHelper) field.get(popupMenu);
            helper.setForceShowIcon(true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
