package com.example.yana.fakername.prefs

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceFaker {

    private const val PREFS_NAME = "com.example.yana.fakername.prefs"


    private var preference: Preferences? = null

    fun init(context: Context) {
        preference = Preferences(context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE))
    }

    var token: String
    get() = preference?.token ?: ""
    set(value) { preference?.token = value }

    var id: Int
        get() = preference?.id ?: -1
        set(value) { preference?.id = value }

    fun clear(){
        preference?.clear()
    }

}

class Preferences(private val preference: SharedPreferences){

    private val ID = "ID"
    private val TOKEN = "TOKEN"

    var token: String?
    get() = preference.getString(TOKEN, "")
    set(value) { preference.edit()?.putString(TOKEN, value)?.apply()}

    var id: Int?
        get() = preference.getInt(ID, -1)
        set(value) { preference.edit()?.putInt(ID, value ?: -1)?.apply()}

    fun clear(){
        preference.edit().clear().apply()
    }
}