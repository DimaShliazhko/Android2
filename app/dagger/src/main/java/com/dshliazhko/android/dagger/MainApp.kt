package com.dshliazhko.android.dagger

import android.app.Application
import android.content.Context


val Context.appComponetn: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponetn
    }

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

    }
}