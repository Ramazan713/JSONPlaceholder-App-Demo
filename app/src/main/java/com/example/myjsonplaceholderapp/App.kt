package com.example.myjsonplaceholderapp

import android.app.Application
import com.example.myjsonplaceholderapp.di.appModule
import com.example.myjsonplaceholderapp.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(remoteModule, appModule)
        }
    }
}