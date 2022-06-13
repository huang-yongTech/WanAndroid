package com.hy.wanandroid.library.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyong on 2017/10/23.
 * Activity管理类
 * 将每个启动的activity添加到列表中，方便集中销毁
 */

public class ActivityManagerUtils {
    private List<Activity> allActivities = new ArrayList<>();


    private ActivityManagerUtils() {
    }

    private static class ActivityManagerHolder {
        private static final ActivityManagerUtils instance = new ActivityManagerUtils();
    }

    public static ActivityManagerUtils getInstance() {
        return ActivityManagerHolder.instance;
    }

    //在Activity基类的onCreate()方法中执行
    public void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        allActivities.remove(activity);
    }

    //注销是销毁所有的Activity
    public void OutSign() {
        for (Activity activity : allActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
