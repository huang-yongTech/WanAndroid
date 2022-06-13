package com.hy.wanandroid.library.util

import okhttp3.ResponseBody
import android.util.Log
import java.io.*

/**
 * Created by huangyong on 2018/3/19
 * IO流工具类
 */
object IOUtils {
    /**
     * 下载文件
     *
     * @param responseBody 从服务器接收到的文件数据
     * @param file         保存到本地的文件
     */
    fun download(responseBody: ResponseBody, file: File?): Boolean {
        var inputStream: BufferedInputStream? = null
        var outputStream: BufferedOutputStream? = null
        return try {
            inputStream = BufferedInputStream(responseBody.byteStream())
            outputStream = BufferedOutputStream(FileOutputStream(file))
            val bytes = ByteArray(1024)
            var length: Int
            while (inputStream.read(bytes).also { length = it } > 0) {
                //输出流务必要这么写，否则会出现重复下载的情况导致文件内容重复（主要针对小文件）
                outputStream.write(bytes, 0, length)
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i("下载附件异常:", e.message ?: "")
            false
        } finally {
            CloseUtils.close(inputStream, outputStream)
        }
    }
}