package com.hy.wanandroid.library.util;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by huangyong on 2018/5/5
 * 用户阅读指定消息辅助类
 */
public class ReaderHelper {
    private ReaderHelper() {
    }

    /**
     * 检测当前用户对诸如新闻，公告等是否已阅读
     *
     * @param context 上下文参数
     * @param readers 已阅读用户
     * @return {@code true}: 已阅读<br>{@code false}: 未阅读
     */
    public static boolean unRead(Context context, StringBuilder readers) {
        String currentUserId = SPUtils.getInstance(context, Constant.USER_SESSION).getString(Constant.USER_ID);

        if (TextUtils.isEmpty(readers)) {
            readers.append(currentUserId).append(",");
            return true;
        }

        if (readers.toString().contains(currentUserId)) {
            return false;
        } else {
            readers.append(currentUserId).append(",");
            return true;
        }
    }
}
