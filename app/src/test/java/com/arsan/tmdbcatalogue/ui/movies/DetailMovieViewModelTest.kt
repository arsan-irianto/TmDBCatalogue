package com.arsan.tmdbcatalogue.ui.movies

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    lateinit var detailMovieViewModel: DetailMovieViewModel

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel()
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
        assertEquals(7.8, detailMovieViewModel.movieRating)
    }

}