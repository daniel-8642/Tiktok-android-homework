package com.qxy.mzbzy.data.api;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.qxy.mzbzy.data.bean.Test2;

import java.util.List;

@Dao
public interface Test2Dao {
    @Insert
    void insert( Test2 test2);

    @Delete
    void delete( Test2 test2);

    @Update
    void update( Test2 test2);

    @Query("SELECT * FROM test2")
    List<Test2> getList();

    @Query("DELETE FROM test2")
    void clear();

    @Query("SELECT * FROM test2 WHERE id = :id")
    Test2 getById(int id);
}
