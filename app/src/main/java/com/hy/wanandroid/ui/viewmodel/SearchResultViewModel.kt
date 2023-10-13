package com.hy.wanandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hy.wanandroid.data.api.RetrofitUtils
import com.hy.wanandroid.data.api.SearchApi
import com.hy.wanandroid.data.bean.Article
import com.hy.wanandroid.data.bean.PageData
import com.hy.wanandroid.data.state.BaseViewModel
import com.hy.wanandroid.data.state.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * author：created by huangyong on 2020/3/31 17:03
 * email：756655135@qq.com
 * description :
 */
class SearchResultViewModel : BaseViewModel() {
    private val _articles =
        MutableStateFlow<DataState<PageData<Article>?>?>(DataState.OnSuccess(null))
    val article: StateFlow<DataState<PageData<Article>?>?> = _articles

    /**
     * 根据关键字搜索文章
     */
    fun getArticlesByKey(page: Int, key: String?, isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore

        viewModelScope.launch {
            flow {
                val articles = RetrofitUtils.instance
                    .getApiService(SearchApi::class.java)
                    .getArticlesByKey(page, key)
                emit(articles)
            }
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    _articles.value = DataState.OnNetError(handleObjError(exception))
                }
                .collect {
                    _articles.value = DataState.OnSuccess(it)
                }
        }
    }
}