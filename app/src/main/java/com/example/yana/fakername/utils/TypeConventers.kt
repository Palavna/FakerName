package com.example.yana.fakername.utils

import androidx.room.TypeConverter
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.dataClass.SearchModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConventers {

    @TypeConverter
    fun fromSearchModel(list: List<SearchModel?>?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toSearchModel(string: String?): List<SearchModel?>? {
        val devicesType = object :
            TypeToken<List<SearchModel?>?>() {}.type
        return Gson()
            .fromJson<List<SearchModel?>?>(string, devicesType)
    }

    @TypeConverter
    fun fromCountries(list: Countries?): String? {
        return if (list == null){
            null
        }else Gson().toJson(list)
    }

    @TypeConverter
    fun toCountries(string: String?): Countries? {
        val devicesType = object :
            TypeToken <Countries?>() {}.type
        return Gson()
            .fromJson<Countries?>(string, devicesType)
    }
}