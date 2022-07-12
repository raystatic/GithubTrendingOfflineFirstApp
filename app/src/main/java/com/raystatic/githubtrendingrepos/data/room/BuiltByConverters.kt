package com.raystatic.githubtrendingrepos.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raystatic.githubtrendingrepos.data.response_models.BuiltBy
import java.lang.reflect.Type

class BuiltByConverters {

    @TypeConverter
    fun fromStringToBuiltByList(value: String?): List<BuiltBy>? {
        val type: Type = object : TypeToken<List<BuiltBy>?>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromBuiltByListToString(list: List<BuiltBy>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

}