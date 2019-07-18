package com.arsan.tmdbcatalogue.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.di.Injection
import com.arsan.tmdbcatalogue.ui.movies.MoviesViewModel
import com.arsan.tmdbcatalogue.ui.tvshow.TvShowViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val appRepository: AppRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.isAssignableFrom(MoviesViewModel::class.java) as T
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            instance ?: synchronized(this) {
                instance ?: Injection.provideAppRepository()
            }
            return instance
        }
    }
}