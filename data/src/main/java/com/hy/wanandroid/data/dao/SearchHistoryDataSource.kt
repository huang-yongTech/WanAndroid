package com.hy.wanandroid.data.dao

import android.app.Application
import androidx.lifecycle.LiveData
import com.hy.wanandroid.data.bean.HotWord

/**
 * author：created by huangyong on 2020/4/1 17:13
 * email：756655135@qq.com
 * description :
 */
class SearchHistoryDataSource(application: Application) {
    private val historyDao: SearchHistoryDao?
    fun insertSearchKey(hotWord: HotWord?) {
        AppDatabase.DATABASE_WRITE_EXECUTOR?.execute {
            historyDao?.insertKey(
                hotWord
            )
        }
    }

    fun deleteKeys() {
        AppDatabase.DATABASE_WRITE_EXECUTOR?.execute { historyDao?.deleteKeys() }
    }

    fun getHistoryKey(): LiveData<MutableList<HotWord?>?>? {
        return historyDao?.getHistoryKey()
    }

    init {
        val database: AppDatabase? = AppDatabase.getInstance(application)
        historyDao = database?.historyDao()
    }
}