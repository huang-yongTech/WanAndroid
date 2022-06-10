package com.hy.wanandroid.data.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hy.wanandroid.data.bean.JsonRootBean
import com.hy.wanandroid.data.bean.PageData
import com.hy.wanandroid.data.bean.Article
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
    @get:GET("hotkey/json")
    val hotWords: LiveData<JsonListRootBean<HotWord?>?>?

    /**
     * 根据关键词查找文章
     *
     * @param page 页码
     * @param key  关键字
     * @return 结果集
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    fun queryArticlesByKey(
        @Path("page") page: Int,
        @Field("k") key: String?
    ): LiveData<JsonRootBean<PageData<Article?>?>?>?
}