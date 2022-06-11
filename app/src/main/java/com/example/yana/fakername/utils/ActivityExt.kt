package com.example.yana.fakername.utils

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.cleanLaunchActivity(){
    startActivity(Intent(this, T::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
}
inline fun <reified T> Context.launchActivity(){
    startActivity(Intent(this, T::class.java))
}