package com.arsan.tmdbcatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.vo.Resource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TvShowViewModelTest {
    @Rule
    @JvmField
    val executor = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowViewModel: TvShowViewModel
    private val appRepository = Mockito.mock(AppRepository::class.java)


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        tvShowViewModel = TvShowViewModel(appRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testFetchTvTopRated_Success() {
        val tvshowsData: MutableLiveData<Resource<List<TvShow>>> = MutableLiveData()
        Mockito.`when`(appRepository.getAllTvShow()).thenReturn(tvshowsData)
        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<List<TvShow>>>
        tvShowViewModel.liveData.observeForever { observer }
        tvShowViewModel.setData("TvShowList")
        tvShowViewModel.liveData.observeForever(observer)
        Mockito.verify(appRepository).getAllTvShow()
    }
}