package com.example.shared_common.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AbilityConverter {
    @TypeConverter
    fun anyToList(data: String): List<Any> {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToAny(list: List<Any>): Any {
        return Gson().toJson(list)
    }
}