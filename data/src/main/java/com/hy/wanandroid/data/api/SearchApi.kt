package com.hy.wanandroid.data.api;

import androidx.lifecycle.LiveData;

import com.hy.wanandroid.data.bean.Article;
import com.hy.wanandroid.data.bean.HotWord;
import com.hy.wanandroid.data.bean.JsonListRootBean;
import com.hy.wanandroid.data.bean.JsonRootBean;
import com.hy.wanandroid.data.bean.PageData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * author：created by huangyong on 2020/3/26 18:50
 * email：756655135@qq.com
 * description :
 */
public interface SearchApi {
    /**
     * 获取热搜词列表
     *
     * @return 结果集
     */
    @GET("hotkey/json")
    LiveData<JsonListRootBean<HotWord>> getHotWords();

    /**
     * 根据关键词查找文章
     *
     * @param page 页码
     * @param key  关键字
     * @return 结果集
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    LiveData<JsonRootBean<PageData<Article>>> queryArticlesByKey(@Path("page") int page, @Field("k") String key);
}
