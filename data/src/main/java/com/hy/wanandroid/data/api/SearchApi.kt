package com.hy.wanandroid.data.api

import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.data.bean.JsonListRootBean
import com.hy.wanandroid.data.bean.HotWord
import retrofit2.http.*

/**
 * author：created by huangyong on 2020/3/26 18:50
 * email：756655135@qq.com
 * description :
 */
interface SearchApi {
    /**
     * 获取热搜词列表
     *
     * @return 结果集
     */
    @GET("hotkey/json")
    suspend fun getHotWords(): JsonListRootBean<HotWord?>?

    /**
     * 根据关键词查找文章
     *
     * @param page 页码
     * @param key  关键字
     * @return 结果集
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    suspend fun queryArticlesByKey(
        @Path("page") page: Int,
        @Field("k") key: String?
    ): JsonRootBean<Any?>?
}