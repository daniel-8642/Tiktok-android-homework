package com.qxy.mzbzy.data.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qxy.mzbzy.data.bean.Rank;

import java.lang.reflect.Type;
import java.util.List;
public class RankConverter {

    @TypeConverter
    public String objectToString(Rank rank) {
        Gson gson = new Gson();
        return gson.toJson(rank);
    }

    @TypeConverter
    public Rank stringToObject(String json) {
        Type listType = new TypeToken<Rank>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, listType);
    }
}
