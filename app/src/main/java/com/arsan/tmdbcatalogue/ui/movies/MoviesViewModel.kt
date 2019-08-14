package com.arsan.tmdbcatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.vo.Resource

class MoviesViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val mutableLiveData = MutableLiveData<String>()

    val liveData: LiveData<Resource<List<Movie>>> = Transformations.switchMap(mutableLiveData) {
        appRepository.getAllMovie()
    }

    fun setData(mvdata: String) {
        mutableLiveData.value = mvdata
    }
}
