package com.example.yana.fakername.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.yana.fakername.R

fun FragmentManager.changeFragment(fragment: Fragment) {
         beginTransaction()
        .replace(R.id.container, fragment)
        .setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.pop_enter,
            R.anim.pop_exit
        )
        .commit()
}


fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}