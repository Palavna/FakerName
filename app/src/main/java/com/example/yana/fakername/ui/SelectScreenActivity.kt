package com.example.yana.fakername.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.utils.cleanLaunchActivity

class SelectScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SharedPreferenceFaker.token.isNotEmpty())
            cleanLaunchActivity<AuthorizedMainActivity>()
        else cleanLaunchActivity<MainActivity>()
    }

}