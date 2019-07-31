package com.arsan.tmdbcatalogue.di

import androidx.room.Room
import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.data.room.AppDatabase
import com.arsan.tmdbcatalogue.ui.movies.MoviesViewModel
import com.arsan.tmdbcatalogue.ui.movies.details.DetailMovieViewModel
import com.arsan.tmdbcatalogue.ui.movies.favorites.FavMoviesVM
import com.arsan.tmdbcatalogue.ui.tvshow.TvShowViewModel
import com.arsan.tmdbcatalogue.ui.tvshow.details.DetailTvShowViewModel
import com.arsan.tmdbcatalogue.ui.tvshow.favorites.FavTvShowsVM
import com.arsan.tmdbcatalogue.utils.CoroutineContextProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    val tmDBServices: TmDBServices = RetrofitService.createService(TmDBServices::class.java)
    val coroutineContextProvider: CoroutineContextProvider = CoroutineContextProvider.getInstance()

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "tmdb")
            .allowMainThreadQueries()
            .build()
    }
    single { get<AppDatabase>().movieDao() }
    single { get<AppDatabase>().tvShowDao() }
    single { LocalRepository(get(), get()) }

    single { tmDBServices }
    single { RemoteRepository(get()) }

    single { coroutineContextProvider }
    single { AppRepository(get(), get(), get()) }

    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
    viewModel { FavMoviesVM(get()) }
    viewModel { FavTvShowsVM(get()) }
}


