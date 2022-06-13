package com.hy.wanandroid.library.util

import android.text.TextUtils
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by huangyong on 2017/12/25.
 * 日期转换工具类
 */
object TimeUtils {
    private val DEFAULT_FORMAT: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    val FORMAT_WITH_NO_SECONDS: DateFormat = SimpleDateFormat("yy年MM月dd日 HH:mm", Locale.CHINA)
    /**
     * 将Date类型转换为时间字符串
     *
     * @param date   类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    /**
     * 将Date类型转换为时间字符串
     *
     * @param date 类型时间
     * @return 时间字符串
     */
    @JvmOverloads
    fun date2String(date: Date?, format: DateFormat = DEFAULT_FORMAT): String {
        return format.format(date)
    }

    /**
     * 将date类型转换为时间戳
     *
     * @param date date类型时间
     * @return 时间戳
     */
    fun date2Millis(date: Date): Long {
        return date.time
    }

    /**
     * 将时间戳转换为date类型
     *
     * @param millis 时间戳类型时间
     * @return date类型时间
     */
    fun millis2Date(millis: Long): Date {
        return Date(millis)
    }

    /**
     * 将时间戳转为时间字符串
     *
     * 格式为 format
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    @JvmOverloads
    fun millis2String(millis: Long, format: DateFormat = DEFAULT_FORMAT): String {
        return if (millis == 0L) {
            ""
        } else format.format(Date(millis))
    }
    /**
     * 将时间字符串转为时间戳
     *
     * time 格式为 format
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    /**
     * 将时间字符串转为时间戳
     *
     * time 格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    @JvmOverloads
    fun string2Millis(time: String?, format: DateFormat = DEFAULT_FORMAT): Long {
        if (TextUtils.isEmpty(time)) {
            return 0
        }
        try {
            return format.parse(time.toString())?.time ?: 0
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 判断一个字符串是否是一个合法的Date格式
     *
     * @param time 时间字符串
     * @return 布尔返回值
     */
    fun isValidDate(time: String?): Boolean {
        var convertSuccess = true
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        try {
            format.isLenient = false
            format.parse(time.toString())
        } catch (e: ParseException) {
            e.printStackTrace()
            convertSuccess = false
        }
        return convertSuccess
    }

    /**
     * 计算起始日期和终止日期之间相差的天数（包含跨年和不跨年）
     *
     * @param beginTime 起始日期
     * @param endTime   终止日期
     * @return 相差天数
     */
    fun calculateIntervalDay(beginTime: Long, endTime: Long): Int {
        val beginCal = Calendar.getInstance()
        val endCal = Calendar.getInstance()
        beginCal.timeInMillis = beginTime
        endCal.timeInMillis = endTime
        val beginYear = beginCal[Calendar.YEAR]
        val endYear = endCal[Calendar.YEAR]
        val beginDayOfYear = beginCal[Calendar.DAY_OF_YEAR]
        val endDayOfYear = endCal[Calendar.DAY_OF_YEAR]
        return if (beginYear == endYear) {
            endDayOfYear - beginDayOfYear
        } else {
            var timeDistance = 0
            for (i in beginYear until endYear) {
                timeDistance += if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    366
                } else {
                    //不是闰年
                    365
                }
            }
            timeDistance + (endDayOfYear - beginDayOfYear)
        }
    }

    /**
     * 获取年份
     *
     * @param time 时间戳
     * @return 年份
     */
    fun getYear(time: Long): Int {
        return getCalendar(time)[Calendar.YEAR]
    }

    /**
     * 获取月份
     *
     * @param time 时间戳
     * @return 月份
     */
    fun getMonth(time: Long): Int {
        return getCalendar(time)[Calendar.MONTH] + 1
    }

    /**
     * 获取月份中的天
     *
     * @param time 时间戳
     * @return 月份中的天
     */
    fun getDayOfMonth(time: Long): Int {
        return getCalendar(time)[Calendar.DAY_OF_MONTH]
    }

    /**
     * 将日期加一天并返回增加日期后的年份
     *
     * @return 年份
     */
    fun addDayAndGetYear(time: Long, days: Int): Int {
        val calendar = getCalendar(time)
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar[Calendar.YEAR]
    }

    /**
     * 将日期增加一天并返回增加日期后的月份
     *
     * @return 月份
     */
    fun addDayAndGetMonth(time: Long, days: Int): Int {
        val calendar = getCalendar(time)
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar[Calendar.MONTH] + 1
    }

    /**
     * 将日期增加一天后并返回增加日期后的那一天
     *
     * @return 增加日期后的天
     */
    fun addDayAndGetDayOfMonth(time: Long, days: Int): Int {
        val calendar = getCalendar(time)
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar[Calendar.DAY_OF_MONTH]
    }

    /**
     * 根据指定的时间获取calendar
     *
     * @param time 时间
     * @return calendar
     */
    private fun getCalendar(time: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return calendar
    }
}