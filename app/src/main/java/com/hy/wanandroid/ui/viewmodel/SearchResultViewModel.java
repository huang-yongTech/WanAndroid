package com.hy.wanandroid.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hy.wanandroid.data.api.RetrofitUtils;
import com.hy.wanandroid.data.api.SearchApi;
import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.data.bean.PageData;

/**
 * author：created by huangyong on 2020/3/31 17:03
 * email：756655135@qq.com
 * description :
 */
public class SearchResultViewModel extends ViewModel {
    /**
     * 根据关键字搜索文章
     */
    public LiveData<JsonRootBean<PageData<Article>>> queryArticlesByKey(int page, String key) {
        return RetrofitUtils.getInstance()
                .getApiService(SearchApi.class)
                .queryArticlesByKey(page, key)
                ;
    }
}
