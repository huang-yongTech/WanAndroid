package com.hy.wanandroid.library.util;

import android.util.Log;

import java.io.*;
import java.util.Objects;

import okhttp3.ResponseBody;

/**
 * Created by huangyong on 2018/3/19
 * IO流工具类
 */
public final class IOUtils {
    private IOUtils() {
    }

    /**
     * 下载文件
     *
     * @param responseBody 从服务器接收到的文件数据
     * @param file         保存到本地的文件
     */
    public static boolean download(ResponseBody responseBody, File file) {
        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(responseBody.byteStream());
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) > 0) {
                //输出流务必要这么写，否则会出现重复下载的情况导致文件内容重复（主要针对小文件）
                outputStream.write(bytes, 0, length);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("下载附件异常:", Objects.requireNonNull(e.getMessage()));
            return false;
        } finally {
            CloseUtils.close(inputStream, outputStream);
        }
    }
}
