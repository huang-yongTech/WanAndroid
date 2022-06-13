package com.hy.wanandroid.library.util

import android.content.Context
import android.widget.Toast
import android.os.Environment
import java.io.File

/**
 * Created by huangyong on 2018/3/5.
 * SD卡工具类
 */
object SDCardUtils {
    /**
     * 获取外部存储路径
     *
     * @param context 上下文参数
     * @return 外部存储路径
     */
    fun getSDCardPath(context: Context?): String? {
        //如果SD卡已安装好
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file = Environment.getExternalStorageDirectory()
            file.absolutePath + File.separator + "GcitMobile" + File.separator
        } else {
            Toast.makeText(context, "下载文件失败，该设备未安装SD卡", Toast.LENGTH_SHORT).show()
            null
        }
    }
}