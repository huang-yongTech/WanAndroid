package com.hy.wanandroid.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hy.wanandroid.data.bean.HotWord;

import java.util.List;

/**
 * author：created by huangyong on 2020/4/1 15:58
 * email：756655135@qq.com
 * description :
 */
@Dao
public interface SearchHistoryDao {
    @Insert
    void insertKey(HotWord hotWord);

    @Query("select * from hot_word")
    LiveData<List<HotWord>> getHistoryKey();
}
