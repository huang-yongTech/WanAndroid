package com.hy.wanandroid.ui.viewmodel

import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.data.bean.PageData
import com.hy.wanandroid.data.bean.Article
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import com.hy.wanandroid.data.api.RetrofitUtils
import com.hy.wanandroid.data.api.SearchApi

/**
 * author：created by huangyong on 2020/3/31 17:03
 * email：756655135@qq.com
 * description :
 */
class SearchResultViewModel : ViewModel() {
    /**
     * 根据关键字搜索文章
     */
    fun queryArticlesByKey(page: Int, key: String?): LiveData<JsonRootBean<PageData<Article?>?>?>? {
        return RetrofitUtils.instance
            .getApiService(SearchApi::class.java)
            .queryArticlesByKey(page, key)
    }
}