package com.hy.wanandroid.library.util;

import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * Created by huangyong on 2018/5/7
 * BottomSheetDialog辅助工具类
 */
public class BottomDialogUtils {
    private BottomDialogUtils() {
    }

    /**
     * 隐藏底部选择菜单
     */
    public static void hideBottomShitDialog(BottomSheetDialog bottomDialog) {
        if (bottomDialog != null && bottomDialog.isShowing())
            bottomDialog.dismiss();
    }
}
