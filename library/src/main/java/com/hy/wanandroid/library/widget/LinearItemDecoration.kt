package com.hy.wanandroid.library.widget

import android.content.Context
import com.hy.wanandroid.library.util.SizeUtils.dp2px
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.View
import java.util.*
import kotlin.math.roundToInt

/**
 * Created by huangyong on 2018/1/23.
 * RecyclerView分割线（取消最后一行末尾的分割线，并添加了可以设置widthMargin方法）
 */
class LinearItemDecoration(context: Context, orientation: Int) : ItemDecoration() {
    private var mDivider: Drawable?
    var widthMargin //宽度margin
            = 0

    /**
     * Current orientation. Either [.HORIZONTAL] or [.VERTICAL].
     */
    private var mOrientation = 0
    private val mBounds = Rect()

    /**
     * Sets the orientation for this divider. This should be called if
     * [RecyclerView.LayoutManager] changes orientation.
     *
     * @param orientation [.HORIZONTAL] or [.VERTICAL]
     */
    fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL && orientation != VERTICAL)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        mOrientation = orientation
    }

    /**
     * Sets the [Drawable] for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    fun setDrawable(drawable: Drawable) {
        mDivider = drawable
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || mDivider == null) {
            return
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        //设置分割线水平间距
        val horizontalMargin: Int = if (widthMargin != 0) {
            widthMargin
        } else {
            DEFAULT_MARGIN
        }
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft + horizontalMargin
            right = parent.width - parent.paddingRight - horizontalMargin
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = horizontalMargin
            right = parent.width - horizontalMargin
        }
        val childCount = parent.childCount
        //默认为childCount，这里设置为childCount-1，这样最后一行末尾处就没有分割线
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + child.translationY.roundToInt()
            val top = bottom - (mDivider?.intrinsicHeight ?: 0)
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(canvas)
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(
                parent.paddingLeft, top,
                parent.width - parent.paddingRight, bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.layoutManager?.getDecoratedBoundsWithMargins(child, mBounds)
            val right = mBounds.right + child.translationX.roundToInt()
            val left = right - (mDivider?.intrinsicWidth ?: 0)
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mDivider == null) {
            outRect[0, 0, 0] = 0
            return
        }
        if (mOrientation == VERTICAL) {
            outRect[0, 0, 0] = mDivider!!.intrinsicHeight
        } else {
            outRect[0, 0, mDivider!!.intrinsicWidth] = 0
        }
    }

    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
        private val DEFAULT_MARGIN = dp2px(8f)
        private const val TAG = "DividerItem"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    /**
     * Creates a divider [RecyclerView.ItemDecoration] that can be used with a
     * [LinearLayoutManager].
     *
     * @param context     Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be [.HORIZONTAL] or [.VERTICAL].
     */
    init {
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        if (mDivider == null) {
            Log.w(
                TAG, "@android:attr/listDivider was not set in the theme used for this "
                        + "DividerItemDecoration. Please set that attribute all call setDrawable()"
            )
        }
        a.recycle()
        setOrientation(orientation)
    }
}