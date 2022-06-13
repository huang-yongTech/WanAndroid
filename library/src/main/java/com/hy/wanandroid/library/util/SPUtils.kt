package com.hy.wanandroid.library.util

import android.content.Context
import android.content.SharedPreferences
import androidx.collection.SimpleArrayMap

/**
 * Created by huangyong on 2018/5/3
 * SharedPreferences工具类
 */
class SPUtils {
    private var sp: SharedPreferences

    private constructor(context: Context, spName: String) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    private constructor(context: Context, spName: String, mode: Int) {
        sp = context.getSharedPreferences(spName, mode)
    }

    /**
     * Put the string value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun putString(key: String, value: String?) {
        sp.edit().putString(key, value).apply()
    }

    /**
     * Return the string value in sp.
     *
     * @param key The key of sp.
     * @return the string value if sp exists or `""` otherwise
     */
    fun getString(key: String): String? {
        return getString(key, "")
    }

    /**
     * Return the string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the string value if sp exists or `defaultValue` otherwise
     */
    fun getString(key: String, defaultValue: String?): String? {
        return sp.getString(key, defaultValue)
    }

    /**
     * Put the int value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun putInt(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    /**
     * Return the int value in sp.
     *
     * @param key The key of sp.
     * @return the int value if sp exists or `-1` otherwise
     */
    fun getInt(key: String): Int {
        return getInt(key, -1)
    }

    /**
     * Return the int value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the int value if sp exists or `defaultValue` otherwise
     */
    fun getInt(key: String, defaultValue: Int): Int {
        return sp.getInt(key, defaultValue)
    }

    /**
     * Put the long value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun putLong(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }

    /**
     * Return the long value in sp.
     *
     * @param key The key of sp.
     * @return the long value if sp exists or `-1` otherwise
     */
    fun getLong(key: String): Long {
        return getLong(key, -1L)
    }

    /**
     * Return the long value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the long value if sp exists or `defaultValue` otherwise
     */
    fun getLong(key: String, defaultValue: Long): Long {
        return sp.getLong(key, defaultValue)
    }

    /**
     * Put the float value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun putFloat(key: String, value: Float) {
        sp.edit().putFloat(key, value).apply()
    }

    /**
     * Return the float value in sp.
     *
     * @param key The key of sp.
     * @return the float value if sp exists or `-1f` otherwise
     */
    fun getFloat(key: String): Float {
        return getFloat(key, -1f)
    }

    /**
     * Return the float value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the float value if sp exists or `defaultValue` otherwise
     */
    fun getFloat(key: String, defaultValue: Float): Float {
        return sp.getFloat(key, defaultValue)
    }

    /**
     * Put the boolean value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun putBoolean(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key The key of sp.
     * @return the boolean value if sp exists or `false` otherwise
     */
    fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    /**
     * Return the boolean value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the boolean value if sp exists or `defaultValue` otherwise
     */
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    /**
     * Put the set of string value in sp.
     *
     * @param key   The key of sp.
     * @param value The value of sp.
     */
    fun putStringSet(key: String, value: Set<String?>?) {
        sp.edit().putStringSet(key, value).apply()
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key The key of sp.
     * @return the set of string value if sp exists
     * or `Collections.<String>emptySet()` otherwise
     */
    fun getStringSet(key: String): Set<String>? {
        return getStringSet(key, emptySet<String>())
    }

    /**
     * Return the set of string value in sp.
     *
     * @param key          The key of sp.
     * @param defaultValue The default value if the sp doesn't exist.
     * @return the set of string value if sp exists or `defaultValue` otherwise
     */
    fun getStringSet(key: String, defaultValue: Set<String?>?): Set<String>? {
        return sp.getStringSet(key, defaultValue)
    }

    /**
     * Return all values in sp.
     *
     * @return all values in sp
     */
    val all: Map<String, *>
        get() = sp.all

    /**
     * Return whether the sp contains the preference.
     *
     * @param key The key of sp.
     * @return `true`: yes<br></br>`false`: no
     */
    operator fun contains(key: String): Boolean {
        return sp.contains(key)
    }

    /**
     * Remove the preference in sp.
     *
     * @param key The key of sp.
     */
    fun remove(key: String) {
        sp.edit().remove(key).apply()
    }

    /**
     * Remove all preferences in sp.
     */
    fun clear() {
        sp.edit().clear().apply()
    }

    companion object {
        private val SP_UTILS_MAP = SimpleArrayMap<String, SPUtils>()

        /**
         * Return the single [SPUtils] instance
         *
         * @param spName The name of sp.
         * @return the single [SPUtils] instance
         */
        fun getInstance(context: Context, spName: String): SPUtils {
            return getInstance(context, spName, Context.MODE_PRIVATE)
        }

        /**
         * Return the single [SPUtils] instance
         *
         * @param spName The name of sp.
         * @param mode   Operating mode.
         * @return the single [SPUtils] instance
         */
        fun getInstance(context: Context, spName: String, mode: Int): SPUtils {
            var spName = spName
            if (isSpace(spName)) spName = "spUtils"
            var spUtils = SP_UTILS_MAP[spName]
            if (spUtils == null) {
                spUtils = SPUtils(context, spName, mode)
                SP_UTILS_MAP.put(spName, spUtils)
            }
            return spUtils
        }

        /**
         * 检测字符串是否为空白字符串
         *
         * @param s 待校验的字符串
         * @return 检测结果
         */
        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }
    }
}