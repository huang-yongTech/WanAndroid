package com.hy.wanandroid.data.dao

import android.content.Context
import androidx.room.Database
import com.hy.wanandroid.data.bean.HotWord
import androidx.room.RoomDatabase
import androidx.room.Room
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * author：created by huangyong on 2020/4/1 17:07
 * email：756655135@qq.com
 * description :
 */
@Database(entities = [HotWord::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): SearchHistoryDao?

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val DATABASE_WRITE_EXECUTOR: ExecutorService? = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "wan_android.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}