package com.hy.wanandroid.data.api

import com.hy.wanandroid.data.bean.Article
import retrofit2.http.GET
import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.data.bean.PageData
import retrofit2.http.Path

interface HomeApi {

    /**
     * 查询首页文章列表
     * @return 结果集
     */
    @GET("article/list/{page}/json")
    suspend fun queryArticleList(@Path("page") page: Int): JsonRootBean<PageData<Article>?>?
}