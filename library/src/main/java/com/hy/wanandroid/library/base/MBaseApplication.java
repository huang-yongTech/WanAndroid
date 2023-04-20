package com.hy.wanandroid.library.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MBaseApplication extends Application implements ViewModelStoreOwner {
    private static final String TAG = "MBaseApplication";

    private static WeakReference<MBaseApplication> applicationRef;
    private static WeakReference<Context> contextRef;

    private ViewModelStore mAppViewModelStore;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationRef = new WeakReference<>(this);
        contextRef = new WeakReference<Context>(this);

        mAppViewModelStore = new ViewModelStore();
    }

    //突破64k
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static MBaseApplication getInstance() {
        return applicationRef.get();
    }

    public static Context getContext() {
        return contextRef.get();
    }

    public Class<?> getActivityClass(String activityName) {
        return null;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
