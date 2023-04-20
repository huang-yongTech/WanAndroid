package com.hy.wanandroid.library.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.hy.wanandroid.library.R;
import com.hy.wanandroid.library.base.MBaseApplication;

public class ToastUtils {

    /**
     * Ui线程/非UI线程中显示 Toast，默认居中显示
     */
    public static void showToast(final int strID) {
        showToast(strID, Gravity.CENTER);
    }

    /**
     * UI线程/非UI线程均可调用 显示 Toast，默认居中显示
     */
    public static void showToast(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }

        showToast(str, Gravity.CENTER);
    }

    /**
     * UI线程/非UI线程均可调用 显示 Toast，默认居中显示
     */
    public static void showToastWithImage(final String str, @DrawableRes int resId) {
        showToastWithImage(str, resId, Gravity.CENTER);
    }

    /**
     * UI线程/非UI线程均可调用 显示 Toast
     */
    public static void showToast(final int strID, final int gravity) {
        showToast(MBaseApplication.getContext().getString(strID), gravity);
    }

    private static Toast toast = null;

    /**
     * UI线程/非UI线程均可调用 显示 Toast
     */
    public static void showToast(final String str, final int gravity) {
        if (toast == null) {
            try {
//                toast = Toast.makeText(MBaseApplication.context, str,
//                        Toast.LENGTH_SHORT);
                Context context = MBaseApplication.getContext();
                toast = new Toast(context);
                View view = LayoutInflater.from(context).inflate(R.layout.mbase_toast_view, null);
                AppCompatTextView textView = view.findViewById(R.id.mbase_toast_text_view);
                textView.setText(str);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(view);

                if (gravity == Gravity.BOTTOM) {
                    toast.setGravity(gravity, 0, 100);
                } else {
                    toast.setGravity(gravity, 0, 0);
                }
                toast.show();
            } catch (Exception e) {
            }
        } else {
            toast.cancel();
            toast = null;
            showToast(str, gravity);
        }
    }

    /**
     * UI线程/非UI线程均可调用 显示 Toast
     */
    public static void showToastWithImage(final String str, @DrawableRes int resId, final int gravity) {
        if (toast == null) {
            try {
                Context context = MBaseApplication.getContext();
                toast = new Toast(context);
                View view = LayoutInflater.from(context).inflate(R.layout.mbase_toast_view_with_image, null);
                AppCompatImageView imageView = view.findViewById(R.id.mbase_toast_image_iv);
                imageView.setImageResource(resId);
                AppCompatTextView textView = view.findViewById(R.id.mbase_toast_image_tv);
                textView.setText(str);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(view);

                if (gravity == Gravity.BOTTOM) {
                    toast.setGravity(gravity, 0, 100);
                } else {
                    toast.setGravity(gravity, 0, 0);
                }
                toast.show();
            } catch (Exception ignored) {
            }
        } else {
            toast.cancel();
            toast = null;
            showToastWithImage(str, resId, gravity);
        }
    }
}
