package com.hy.wanandroid.library.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by huangyong on 2018/3/1
 * 关闭IO流的通用工具类
 */
public final class CloseUtils {
    private CloseUtils() {
    }

    /**
     * 关闭流
     *
     * @param closeables 流对象数组
     */
    public static void close(final Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
