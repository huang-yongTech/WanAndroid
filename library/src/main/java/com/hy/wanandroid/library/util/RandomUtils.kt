package com.hy.wanandroid.library.util

import androidx.annotation.ColorInt
import android.graphics.Color
import androidx.annotation.IntRange
import java.util.*

/**
 * @author Jenly [Jenly](mailto:jenly1314@gmail.com)
 */
enum class RandomUtils {
    INSTANCE;

    private val mRandom = Random()
    @ColorInt
    fun randomColor(
        @IntRange(from = 0, to = 255) min: Int,
        @IntRange(from = 0, to = 255) max: Int
    ): Int {
        return Color.rgb(random(min, max), random(min, max), random(min, max))
    }

    private fun random(min: Int, max: Int): Int {
        return mRandom.nextInt(max - min + 1) + min
    }
}