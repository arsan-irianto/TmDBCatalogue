package com.arsan.tmdbcatalogue.di

import android.app.Application
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository

object Injection {
    fun provideAppRepository(): AppRepository? {
        val localRepository = LocalRepository()
        val remoteRepository = RemoteRepository()

        return AppRepository.getInstance(localRepository, remoteRepository)
    }
}