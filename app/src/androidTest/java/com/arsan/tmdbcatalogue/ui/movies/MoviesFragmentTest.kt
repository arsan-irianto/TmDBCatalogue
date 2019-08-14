package com.arsan.tmdbcatalogue.ui.movies

import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.testing.SingleFragmentActivity
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import com.arsan.tmdbcatalogue.utils.utils.RecyclerViewItemCountAssertion
import com.arsan.tmdbcatalogue.vo.Resource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

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
        onView(withId(R.id.fragment_movies)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.rv_movies)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.pb_movies)).perform(ProgressBar.VISIBLE)
        onView(withId(R.id.pb_movies)).perform(ProgressBar.INVISIBLE)
        onView(withId(R.id.sr_movies)).perform(swipeDown())
/*        onView(withId(R.id.rv_movies)).check(RecyclerViewItemCountAssertion(1))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.movie_detail)).check(matches(isDisplayed()))*/
    }
}

private fun ViewInteraction.perform(visible: Int) {}