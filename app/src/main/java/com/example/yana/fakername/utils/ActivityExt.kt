package com.example.yana.fakername.utils

import android.content.Context
import android.content.Intent
import android.text.Spanned
import androidx.core.text.HtmlCompat

inline fun <reified T> Context.cleanLaunchActivity(){
    startActivity(Intent(this, T::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
}
inline fun <reified T> Context.launchActivity(){
    startActivity(Intent(this, T::class.java))
}

fun String?.getTextIsNotEmpty(): String {

    val htmlString: String = this ?: "Отсутствует"
    val spanned: Spanned = HtmlCompat
        .fromHtml(
            htmlString,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
     return spanned.trim().toString()
}