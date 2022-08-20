package com.qxy.mzbzy.data.bean;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.qxy.mzbzy.data.converter.RankConverter;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "cache")
public class Cache {
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String name;
    @TypeConverters(RankConverter.class)
    @ColumnInfo(name = "rank", typeAffinity = ColumnInfo.TEXT)
    public Rank rank;

    public Cache(@NonNull String name, Rank rank) {
        this.name = name;
        this.rank = rank;
    }
}
