package com.arsan.tmdbcatalogue.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class InjectApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@InjectApplication)
            modules(listOf(viewModelModule, netWorkModule, repositoryModule))
        }
    }
}