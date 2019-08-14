package com.arsan.tmdbcatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.ui.movies.details.DetailMovieViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMovieViewModelTest {

    @Rule
    @JvmField
    val executor = InstantTaskExecutorRule()

    @Mock
    private val appRepository = Mockito.mock(AppRepository::class.java)

    private lateinit var detailMovieViewModel: DetailMovieViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailMovieViewModel = DetailMovieViewModel(appRepository)
    }

    @Test
    fun testGetMovieData() {
        with(detailMovieViewModel) {
            movieId = 429617
            movieTitle = "Spider-Man: Far from Home"
            movieOverview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent."
            moviePoster = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg"
            movieBackdrop = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg"
            movieRating=7.8
        }

        assertEquals(429617, detailMovieViewModel.movieId)
        assertEquals("Spider-Man: Far from Home", detailMovieViewModel.movieTitle)
        assertEquals("Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
            detailMovieViewModel.movieOverview)
        assertEquals("/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg", detailMovieViewModel.moviePoster)
        assertEquals("/dihW2yTsvQlust7mSuAqJDtqW7k.jpg", detailMovieViewModel.movieBackdrop)
        assertEquals(7.8, detailMovieViewModel.movieRating, 0.0)
    }
}