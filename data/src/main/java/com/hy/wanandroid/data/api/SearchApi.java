package com.hy.wanandroid.data.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hy.wanandroid.data.bean.HotWord;
import com.hy.wanandroid.data.bean.JsonListRootBean;

import retrofit2.http.GET;

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
    @GET("/hotkey/json")
    MutableLiveData<JsonListRootBean<HotWord>> queryHotWords();
}
