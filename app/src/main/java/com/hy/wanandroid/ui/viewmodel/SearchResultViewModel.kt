package com.hy.wanandroid.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hy.wanandroid.data.api.RetrofitUtils
import com.hy.wanandroid.data.api.SearchApi
import com.hy.wanandroid.data.state.BaseViewModel
import com.hy.wanandroid.data.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * author：created by huangyong on 2020/3/31 17:03
 * email：756655135@qq.com
 * description :
 */
class SearchResultViewModel : BaseViewModel() {
    private val _searchArticles = MutableStateFlow<UiState>(UiState.Success(null))
    val searchArticle: StateFlow<UiState> = _searchArticles

    /**
     * 根据关键字搜索文章
     */
    fun queryArticlesByKey(page: Int, key: String?, isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore

        viewModelScope.launch {
            flow {
                val articles = RetrofitUtils.instance
                    .getApiService(SearchApi::class.java)
                    .queryArticlesByKey(page, key)
                emit(articles)
            }
                .flowOn(Dispatchers.IO)
                .catch { exception ->
                    _searchArticles.value = UiState.Error(handleObjError(exception))
                }
                .collect {
                    _searchArticles.value = UiState.Success(it)
                }
        }
    }
}