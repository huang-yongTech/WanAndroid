package com.hy.wanandroid.library.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyong on 2018/3/21.
 * 正则匹配工具类
 */

public final class RegexUtils {
    private RegexUtils() {
    }

    /**
     * 查找某个html字符串中是否包含img标签
     *
     * @param html  待匹配的字符串
     * @param regex 匹配规则
     * @return 结果
     */
    public static boolean findTagImg(String html, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(html);
        return matcher.find();
    }
}
