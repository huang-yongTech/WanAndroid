package com.hy.wanandroid.data.dao

import androidx.lifecycle.LiveData
import com.hy.wanandroid.data.bean.HotWord
import androidx.room.*

/**
 * author：created by huangyong on 2020/4/1 15:58
 * email：756655135@qq.com
 * description :
 */
@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKey(hotWord: HotWord?)

    @Query("delete from hot_word")
    fun deleteKeys()

    @get:Query("select * from hot_word")
    val historyKey: LiveData<MutableList<HotWord?>?>?
}