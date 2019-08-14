package com.arsan.tmdbcatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.vo.Resource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesViewModelTest {

    @Rule
    @JvmField
    val executor = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesViewModel: MoviesViewModel
    private val appRepository = Mockito.mock(AppRepository::class.java)


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesViewModel = MoviesViewModel(appRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testFetchNowPlaying_Success() {
        val moviesData: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()
        Mockito.`when`(appRepository.getAllMovie()).thenReturn(moviesData)
        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<List<Movie>>>
        moviesViewModel.liveData.observeForever { observer }
        moviesViewModel.setData("MovieList")
        moviesViewModel.liveData.observeForever(observer)
        Mockito.verify(appRepository).getAllMovie()
    }
}
