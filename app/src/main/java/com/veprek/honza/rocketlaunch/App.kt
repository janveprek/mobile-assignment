package com.veprek.honza.rocketlaunch

import android.app.Application
import com.veprek.honza.rocketlaunch.di.databaseModule
import com.veprek.honza.rocketlaunch.di.networkModule
import com.veprek.honza.rocketlaunch.di.repoModule
import com.veprek.honza.rocketlaunch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import quanti.com.kotlinlog.Log
import quanti.com.kotlinlog.android.AndroidLogger
import quanti.com.kotlinlog.base.LogLevel
import quanti.com.kotlinlog.base.LoggerBundle

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.initialise(this)

        val androidBundle = LoggerBundle(LogLevel.DEBUG)
        Log.addLogger(AndroidLogger(androidBundle))

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(networkModule, repoModule, databaseModule, viewModelModule)
        }
    }
}
