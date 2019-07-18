package com.arsan.tmdbcatalogue.ui.tvshow

import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class TvShowViewModelTest {
    private val mainThread = newSingleThreadContext("UI Thread")

    @MockK
    private lateinit var tmDBServices: TmDBServices
    private lateinit var tvShowViewModel: TvShowViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThread)
        MockKAnnotations.init(this)
        tvShowViewModel = TvShowViewModel()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThread.close()
    }

    @Test
    fun testFetchTvTopRated_Success() {
        GlobalScope.launch(Dispatchers.IO) {
            coEvery { tmDBServices.fetchTvTopRated() } returns Response.success(
                TvShowResponse(
                    page = 1,
                    results = listOf(
                        TvShow(
                            backdrop_path = "/o8Site0BMZ8xhknKJ0m52iLfqHg.jpg",
                            first_air_date = "2004-05-10",
                            genre_ids = listOf(60, 35),
                            id = 100,
                            name = "I Am Not an Animal",
                            origin_country = listOf("GB"),
                            original_language = "",
                            original_name = "I Am Not an Animal",
                            overview = "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                            popularity = 11.301,
                            poster_path = "/nMhv6jG5dtLdW7rgguYWvpbk0YN.jpg",
                            vote_average = 9.5,
                            vote_count = 305
                        )
                    ),
                    total_pages = 1000,
                    total_results = 19999
                )
            )

            tvShowViewModel.tvData().observeForever { }
            assert(tvShowViewModel.tvData().value != null)
            assert(
                tvShowViewModel.tvData().value?.results ?: true == listOf(
                    TvShow(
                        backdrop_path = "/o8Site0BMZ8xhknKJ0m52iLfqHg.jpg",
                        first_air_date = "2004-05-10",
                        genre_ids = listOf(60, 35),
                        id = 100,
                        name = "I Am Not an Animal",
                        origin_country = listOf("GB"),
                        original_language = "",
                        original_name = "I Am Not an Animal",
                        overview = "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
                        popularity = 11.301,
                        poster_path = "/nMhv6jG5dtLdW7rgguYWvpbk0YN.jpg",
                        vote_average = 9.5,
                        vote_count = 305
                    )
                )
            )
            Assert.assertEquals(20, tvShowViewModel.tvData().value?.results?.size)
        }
    }
}