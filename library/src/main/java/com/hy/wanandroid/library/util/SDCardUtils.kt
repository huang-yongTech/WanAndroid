package com.hy.wanandroid.library.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

/**
 * Created by huangyong on 2018/3/5.
 * SD卡工具类
 */

public final class SDCardUtils {
    private SDCardUtils() {
    }

    /**
     * 获取外部存储路径
     *
     * @param context 上下文参数
     * @return 外部存储路径
     */
    public static String getSDCardPath(Context context) {
        //如果SD卡已安装好
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = Environment.getExternalStorageDirectory();
            return file.getAbsolutePath() + File.separator + "GcitMobile" + File.separator;
        } else {
            Toast.makeText(context, "下载文件失败，该设备未安装SD卡", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
