package com.hy.wanandroid.library.util

import java.io.Closeable
import java.io.IOException

/**
 * Created by huangyong on 2018/3/1
 * 关闭IO流的通用工具类
 */
object CloseUtils {
    /**
     * 关闭流
     *
     * @param closeables 流对象数组
     */
    fun close(vararg closeables: Closeable?) {
        for (closeable in closeables) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}