package com.hy.wanandroid.ui.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hy.wanandroid.data.api.HomeApi
import com.hy.wanandroid.data.api.RetrofitUtils
import com.hy.wanandroid.data.bean.Article

/**
 * 文章分页Paging类
 */
class ArticlePagingSource : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currPage = params.key ?: 1
            val resData = RetrofitUtils.instance
                .getApiService(HomeApi::class.java)
                .queryArticleList(currPage)
            val nextPage = if (currPage < (resData?.data?.pageCount ?: 0)) {
                currPage + 1
            } else {
                null
            }
            val preKey = if (currPage > 1) currPage - 1 else null
            if (resData != null) {
                val articleList: List<Article> = resData.data?.datas as List<Article>
                LoadResult.Page(articleList, preKey, nextPage)
            } else {
                LoadResult.Error(throwable = Throwable())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}