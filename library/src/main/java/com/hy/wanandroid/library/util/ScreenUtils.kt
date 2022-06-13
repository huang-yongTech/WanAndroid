package com.hy.wanandroid.library.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by huangyong on 2017/9/25.
 * 手机屏幕相关工具类
 */

public final class ScreenUtils {
    private ScreenUtils() {
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics().widthPixels;
    }
}
