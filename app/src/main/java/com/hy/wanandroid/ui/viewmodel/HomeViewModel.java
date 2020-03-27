package com.hy.wanandroid.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hy.wanandroid.data.api.HomeApi;
import com.hy.wanandroid.data.api.RetrofitUtils;
import com.hy.wanandroid.data.bean.PageData;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;

/**
 * author：created by huangyong on 2020/3/26 11:23
 * email：756655135@qq.com
 * description :
 */
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";

    public LiveData<JsonRootBean<PageData<Article>>> queryHomeArticleList(int page) {
        return RetrofitUtils.getInstance()
                .getApiService(HomeApi.class)
                .queryHomeArticleList(page)
                ;
    }
}
