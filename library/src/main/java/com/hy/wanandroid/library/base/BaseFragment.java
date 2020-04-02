package com.hy.wanandroid.library.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hy.wanandroid.library.R;
import com.hy.wanandroid.library.util.BarUtils;

import java.util.Objects;

/**
 * BaseFragment
 */
public abstract class BaseFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View mStatusBarView;
    private ViewGroup mViewGroup;

    protected BaseFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addStatusBar();
    }

    /**
     * 添加一个空的状态栏
     */
    private void addStatusBar() {
        if (mStatusBarView == null) {
            mStatusBarView = new View(getContext());
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int statusBarHeight = BarUtils.getStatusBarHeight(Objects.requireNonNull(getContext()));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, statusBarHeight);
            mStatusBarView.setLayoutParams(params);
            mStatusBarView.setBackgroundColor(Color.BLACK);
            mStatusBarView.requestLayout();
            if (mViewGroup != null)
                mViewGroup.addView(mStatusBarView, 0);
        }
    }

    /**
     * 正在加载中界面
     */
    protected View getLoadingView(ViewGroup viewGroup) {
        return getLayoutInflater().inflate(R.layout.view_loading, viewGroup, false);
    }

    /**
     * 空数据界面
     */
    protected View getEmptyDataView(ViewGroup viewGroup) {
        View notDataView = getLayoutInflater().inflate(R.layout.view_empty, viewGroup, false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        return notDataView;
    }

    /**
     * 加载数据出错界面
     */
    protected View getErrorView(ViewGroup viewGroup) {
        View errorView = getLayoutInflater().inflate(R.layout.view_network_error, viewGroup, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        return errorView;
    }

    /**
     * 获取并刷新数据（子类可实现该方法）
     */
    protected abstract void getData();

    /**
     * TextView等组件drawableEnd图标的点击事件
     *
     * @param tv tv
     */
    @SuppressLint("ClickableViewAccessibility")
    protected void clickRightClear(final TextView tv) {
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    return clickRightClear(tv, event);
                }
                return false;
            }
        });
    }

    /**
     * TextView右侧drawable图片清空按钮点击事件处理
     */
    private boolean clickRightClear(TextView tv, MotionEvent event) {
        Drawable drawableRight = tv.getCompoundDrawables()[2];
        if (drawableRight != null && event.getRawX() >= (tv.getRight() - drawableRight.getBounds().width())) {
            tv.setText(null);
            return true;
        }
        return false;
    }
}
