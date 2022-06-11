package com.example.yana.fakername

import android.app.Application
import com.example.yana.fakername.di.fakerModules
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationFakerName: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationFakerName)
            fakerModules
        }
        SharedPreferenceFaker.init(this)
    }
}