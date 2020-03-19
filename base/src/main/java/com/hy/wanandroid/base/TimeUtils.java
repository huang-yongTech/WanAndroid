package com.hy.wanandroid.base;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by huangyong on 2017/12/25.
 * 日期转换工具类
 */

public final class TimeUtils {
    private TimeUtils() {
    }

    private static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
    public static final DateFormat FORMAT_WITH_NO_SECONDS = new SimpleDateFormat("yy年MM月dd日 HH:mm", Locale.CHINA);

    /**
     * 将Date类型转换为时间字符串
     *
     * @param date 类型时间
     * @return 时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_FORMAT);
    }

    /**
     * 将Date类型转换为时间字符串
     *
     * @param date   类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date date, DateFormat format) {
        return format.format(date);
    }

    /**
     * 将date类型转换为时间戳
     *
     * @param date date类型时间
     * @return 时间戳
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转换为date类型
     *
     * @param millis 时间戳类型时间
     * @return date类型时间
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }

    public static String millis2String(long millis) {
        return millis2String(millis, DEFAULT_FORMAT);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为 format</p>
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, DateFormat format) {
        if (millis == 0) {
            return "";
        }
        return format.format(new Date(millis));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time) {
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time, DateFormat format) {
        if (TextUtils.isEmpty(time)) {
            return 0;
        }
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 判断一个字符串是否是一个合法的Date格式
     *
     * @param time 时间字符串
     * @return 布尔返回值
     */
    public static boolean isValidDate(String time) {
        boolean convertSuccess = true;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            format.setLenient(false);
            format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 计算起始日期和终止日期之间相差的天数（包含跨年和不跨年）
     *
     * @param beginTime 起始日期
     * @param endTime   终止日期
     * @return 相差天数
     */
    public static int calculateIntervalDay(long beginTime, long endTime) {
        Calendar beginCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        beginCal.setTimeInMillis(beginTime);
        endCal.setTimeInMillis(endTime);

        int beginYear = beginCal.get(Calendar.YEAR);
        int endYear = endCal.get(Calendar.YEAR);
        int beginDayOfYear = beginCal.get(Calendar.DAY_OF_YEAR);
        int endDayOfYear = endCal.get(Calendar.DAY_OF_YEAR);

        if (beginYear == endYear) {
            return endDayOfYear - beginDayOfYear;
        } else {
            int timeDistance = 0;
            for (int i = beginYear; i < endYear; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (endDayOfYear - beginDayOfYear);
        }
    }

    /**
     * 获取年份
     *
     * @param time 时间戳
     * @return 年份
     */
    public static int getYear(long time) {
        return getCalendar(time).get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @param time 时间戳
     * @return 月份
     */
    public static int getMonth(long time) {
        return getCalendar(time).get(Calendar.MONTH) + 1;
    }

    /**
     * 获取月份中的天
     *
     * @param time 时间戳
     * @return 月份中的天
     */
    public static int getDayOfMonth(long time) {
        return getCalendar(time).get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将日期加一天并返回增加日期后的年份
     *
     * @return 年份
     */
    public static int addDayAndGetYear(long time, int days) {
        Calendar calendar = getCalendar(time);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 将日期增加一天并返回增加日期后的月份
     *
     * @return 月份
     */
    public static int addDayAndGetMonth(long time, int days) {
        Calendar calendar = getCalendar(time);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 将日期增加一天后并返回增加日期后的那一天
     *
     * @return 增加日期后的天
     */
    public static int addDayAndGetDayOfMonth(long time, int days) {
        Calendar calendar = getCalendar(time);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据指定的时间获取calendar
     *
     * @param time 时间
     * @return calendar
     */
    private static Calendar getCalendar(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }
}
