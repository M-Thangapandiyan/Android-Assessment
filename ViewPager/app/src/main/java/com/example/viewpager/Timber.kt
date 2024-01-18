package com.example.viewpager

import android.app.Application
import timber.log.Timber
class Timber : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}