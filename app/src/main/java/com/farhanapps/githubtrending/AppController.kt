package com.farhanapps.githubtrending

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication

class AppController : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        private lateinit var instance: AppController
        fun instance(): Context {
            return instance
        }
    }
}