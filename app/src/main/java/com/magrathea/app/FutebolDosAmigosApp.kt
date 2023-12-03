package com.magrathea.app

import android.app.Application
import com.magrathea.data.dataModule
import com.magrathea.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FutebolDosAmigosApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(dataModule)
            modules(domainModule)
            modules(appModule)
        }
    }
}