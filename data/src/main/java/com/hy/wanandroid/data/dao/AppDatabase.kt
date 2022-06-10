package com.hy.wanandroid.data.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hy.wanandroid.data.bean.HotWord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author：created by huangyong on 2020/4/1 17:07
 * email：756655135@qq.com
 * description :
 */
@Database(entities = {HotWord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService DATABASE_WRITE_EXECUTOR =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDatabase INSTANCE;

    public abstract SearchHistoryDao historyDao();

    static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "wan_android.db")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
