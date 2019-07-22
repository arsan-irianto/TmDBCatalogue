package com.arsan.tmdbcatalogue.ui.movies

import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.stopKoin
import retrofit2.Response

@ObsoleteCoroutinesApi
@RunWith(JUnit4::class)
class MoviesViewModelTest {

    private val mainThread = newSingleThreadContext("UI Thread")

    @MockK
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var remoteRepository: RemoteRepository

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThread)
        MockKAnnotations.init(this)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
        mainThread.close()
    }

    @Test
    fun testFetchNowPlaying_Success() {
        GlobalScope.launch(Dispatchers.IO) {
            coEvery { remoteRepository.getMovies() } returns Response.success(
                MoviesResponse(
                    page = 1,
                    results = listOf(
                        Movie(
                            adult = false,
                            backdrop_path = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
                            id = 429617,
                            original_language = "en",
                            original_title = "Spider-Man: Far from Home",
                            overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                            popularity = 481.951,
                            poster_path = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
                            release_date = "2019-06-28",
                            title = "Spider-Man: Far from Home",
                            video = false,
                            vote_average = 7.8,
                            vote_count = 1116
                        )
                    ),
                    total_pages = 883,
                    total_results = 45
                )
            )

            moviesViewModel.liveData.observeForever { }
            assert(moviesViewModel.liveData.value != null)
            assert(
                moviesViewModel.liveData.value?.results ?: true == listOf(
                    Movie(
                        adult = false,
                        backdrop_path = "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
                        id = 429617,
                        original_language = "en",
                        original_title = "Spider-Man: Far from Home",
                        overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                        popularity = 481.951,
                        poster_path = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg",
                        release_date = "2019-06-28",
                        title = "Spider-Man: Far from Home",
                        video = false,
                        vote_average = 7.8,
                        vote_count = 1116
                    )
                )
            )
            assertEquals(20, moviesViewModel.liveData.value?.results?.size)
        }
    }
}
