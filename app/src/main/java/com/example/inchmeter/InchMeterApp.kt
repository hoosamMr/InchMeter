package com.example.inchmeter

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InchMeterApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("InchMeterApp", "onCreate")
    }
}