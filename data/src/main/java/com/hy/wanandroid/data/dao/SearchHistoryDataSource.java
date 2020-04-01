package com.hy.wanandroid.data.dao;

import androidx.lifecycle.LiveData;

import com.hy.wanandroid.data.bean.HotWord;

import java.util.List;

/**
 * author：created by huangyong on 2020/4/1 17:13
 * email：756655135@qq.com
 * description :
 */
public class SearchHistoryDataSource {
    private SearchHistoryDao historyDao;

    public SearchHistoryDataSource(SearchHistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public void insertSearchKey(HotWord hotWord) {
        historyDao.insertKey(hotWord);
    }

    public LiveData<List<HotWord>> getHistoryKey() {
        return historyDao.getHistoryKey();
    }
}
