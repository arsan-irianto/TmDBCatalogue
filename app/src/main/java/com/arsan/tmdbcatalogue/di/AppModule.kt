package com.arsan.tmdbcatalogue.di

import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.ui.movies.MoviesViewModel
import com.arsan.tmdbcatalogue.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val tmDBServices: TmDBServices = RetrofitService.createService(TmDBServices::class.java)

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}
val netWorkModule = module { single { tmDBServices } }
val repositoryModule = module {
    single { LocalRepository() }
    single { RemoteRepository(get()) }
}


