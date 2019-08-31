package com.yehonatan.latestmovies.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterListInt {

    @TypeConverter
    fun storedStringToInt(value:String?): List<Int>
    {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun storedIntToString(list: List<Int>?): String {
        return Gson().toJson(list)
    }
}