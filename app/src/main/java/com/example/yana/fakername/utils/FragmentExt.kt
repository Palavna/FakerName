package com.example.yana.fakername.utils

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