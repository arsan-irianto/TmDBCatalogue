package com.arsan.tmdbcatalogue.ui.movies

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.testing.SingleFragmentActivity
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import com.arsan.tmdbcatalogue.utils.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {
    @get:Rule
    var activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)

    private val moviesFragment = MoviesFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        activityRule.activity.setFragment(moviesFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).check(RecyclerViewItemCountAssertion(20))
    }
}