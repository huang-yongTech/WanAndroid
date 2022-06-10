package com.hy.wanandroid.data.bean;

import androidx.annotation.DrawableRes;

/**
 * author：created by huangyong on 2020/3/31 14:46
 * email：756655135@qq.com
 * description :
 */
public class MenuItem {
    private int iconId;
    private String title;

    public MenuItem() {
    }

    public MenuItem(@DrawableRes int iconId, String title) {
        this.iconId = iconId;
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(@DrawableRes int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
