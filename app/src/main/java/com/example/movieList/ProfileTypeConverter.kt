package com.example.movieList

import androidx.room.TypeConverter
import com.example.movieList.db.entity.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ProfileTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromPostDataValuesList(postDataList: List<Results?>?): String? {
            if (postDataList == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<Results?>?>() {}.type
            return gson.toJson(postDataList, type)

        }

        @TypeConverter
        @JvmStatic
        fun toPostDataValuesList(postDataValuesString: String?): List<Results>? {
            if (postDataValuesString == null) {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<Results?>?>() {}.type
            return gson.fromJson<List<Results>>(postDataValuesString, type)
        }
    }
}

