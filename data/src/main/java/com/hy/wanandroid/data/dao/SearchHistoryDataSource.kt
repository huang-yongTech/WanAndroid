package com.hy.wanandroid.data.dao;

import android.app.Application;

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

    public SearchHistoryDataSource(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        historyDao = database.historyDao();
    }

    public void insertSearchKey(final HotWord hotWord) {
        AppDatabase.DATABASE_WRITE_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                historyDao.insertKey(hotWord);
            }
        });
    }

    public void deleteKeys() {
        AppDatabase.DATABASE_WRITE_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                historyDao.deleteKeys();
            }
        });
    }

    public LiveData<List<HotWord>> getHistoryKey() {
        return historyDao.getHistoryKey();
    }
}
