package com.hy.wanandroid.data.api;

import androidx.lifecycle.LiveData;

import com.hy.wanandroid.data.bean.Data;
import com.hy.wanandroid.data.bean.Datas;
import com.hy.wanandroid.data.bean.JsonRootBean;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeApi {
    /**
     * 查询首页文章列表
     * @return 结果集
     */
    @GET("/article/list/{page}/json")
    LiveData<JsonRootBean<Data<Datas>>> queryHomeArticleList(@Path("page") int page);
}
