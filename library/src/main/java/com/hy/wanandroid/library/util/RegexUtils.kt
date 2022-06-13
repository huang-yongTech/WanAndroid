package com.hy.wanandroid.library.util

import java.util.regex.Pattern

/**
 * Created by huangyong on 2018/3/21.
 * 正则匹配工具类
 */
object RegexUtils {
    /**
     * 查找某个html字符串中是否包含img标签
     *
     * @param html  待匹配的字符串
     * @param regex 匹配规则
     * @return 结果
     */
    fun findTagImg(html: String?, regex: String?): Boolean {
        val matcher = Pattern.compile(regex.toString()).matcher(html.toString())
        return matcher.find()
    }
}