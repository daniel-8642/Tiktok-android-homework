package com.qxy.mzbzy.data.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.qxy.mzbzy.data.api.CacheDao;
import com.qxy.mzbzy.data.bean.Cache;

@Database(entities = {Cache.class}, version = 1)
public abstract class CacheRepository extends RoomDatabase
{
    private static final String DATABASE_NAME = "page_content_cache";

    private static CacheRepository databaseInstance;

    public static synchronized CacheRepository getInstance(Context context)
    {
        if(databaseInstance == null) {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), CacheRepository.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

    public abstract CacheDao CacheDao();
}
