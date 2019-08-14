package com.arsan.tmdbcatalogue.ui.tvshow.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.vo.Resource

class FavTvShowsVM(private val appRepository: AppRepository) : ViewModel() {
    private var mutableLiveData = MutableLiveData<String>()
    val liveData: LiveData<Resource<PagedList<FavTvshow>>> = Transformations.switchMap(mutableLiveData) {
        appRepository.getFavTvshows()
    }

    fun setData(mvdata: String) {
        mutableLiveData.value = mvdata
    }
}