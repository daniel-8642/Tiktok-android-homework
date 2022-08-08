package com.qxy.mzbzy.data.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.qxy.mzbzy.data.api.Test2Dao;
import com.qxy.mzbzy.data.bean.Test2;

@Database(entities = {Test2.class}, version = 1)
public abstract class Test2Repository extends RoomDatabase
{
    private static final String DATABASE_NAME = "my_db";

    private static Test2Repository databaseInstance;

    public static synchronized Test2Repository getInstance(Context context)
    {
        if(databaseInstance == null)
        {
            databaseInstance = Room
                    .databaseBuilder(context.getApplicationContext(), Test2Repository.class, DATABASE_NAME)
                    .build();
        }
        return databaseInstance;
    }

    public abstract Test2Dao Test2Dao();
}
