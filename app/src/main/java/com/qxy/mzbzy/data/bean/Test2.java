package com.qxy.mzbzy.data.bean;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "test2")
public class Test2 {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "time", typeAffinity = ColumnInfo.TEXT)
    public String time;

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String name;

    /**
     * Room会使用这个构造器来存储数据，也就是当你从表中得到Student对象时候，Room会使用这个构造器
     * */
    public Test2(String time, String name)
    {
        this.time = time;
        this.name = name;
    }

    /**
     * 由于Room只能识别和使用一个构造器，如果希望定义多个构造器，你可以使用Ignore标签，让Room忽略这个构造器
     * 同样，@Ignore标签还可用于字段，使用@Ignore标签标记过的字段，Room不会持久化该字段的数据
     * */
    @Ignore
    public Test2(String name) {
        this.name = name;
        this.time = "1";
    }
}
