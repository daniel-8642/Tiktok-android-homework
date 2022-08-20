package com.qxy.mzbzy.data.api;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.qxy.mzbzy.data.bean.Cache;

@Dao
public interface CacheDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert( Cache cache);

    @Query("DELETE FROM cache")
    void clear();

    @Query("SELECT * FROM cache WHERE name = :name")
    Cache getByName(String name);

    @Query("SELECT * FROM cache WHERE name = :name")
    Cache deleteByName(String name);
}
