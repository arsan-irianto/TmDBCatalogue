package com.arsan.tmdbcatalogue.ui.tvshow

import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.testing.SingleFragmentActivity
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import com.arsan.tmdbcatalogue.utils.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFragmentTest {

    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(SingleFragmentActivity::class.java)

    private val tvShowFragment = TvShowFragment()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_tvshow)).perform(ProgressBar.VISIBLE)
        onView(withId(R.id.pb_tvshow)).perform(ProgressBar.INVISIBLE)
        onView(withId(R.id.sr_tvshow)).perform(swipeDown())
        onView(withId(R.id.rv_tvshow)).check(RecyclerViewItemCountAssertion(20))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tvshow_detail)).check(matches(isDisplayed()))
    }
}

private fun ViewInteraction.perform(visible: Int) {}
