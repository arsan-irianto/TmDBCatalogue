package com.arsan.tmdbcatalogue.ui.movies.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.vo.Resource

class FavMoviesVM(private val appRepository: AppRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<String>()

    val liveData: LiveData<Resource<PagedList<FavMovie>>> = Transformations.switchMap(mutableLiveData) {
        appRepository.getFavMovies()
    }

    fun setData(mvdata: String) {
        mutableLiveData.value = mvdata
    }
}