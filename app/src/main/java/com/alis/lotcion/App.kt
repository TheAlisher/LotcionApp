package com.alis.lotcion

import android.app.Application
import com.alis.lotcion.di.lotcionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(lotcionModule)
        }
    }
}