package com.arsan.tmdbcatalogue.utils.ui

import androidx.test.rule.ActivityTestRule
import com.arsan.tmdbcatalogue.ui.home.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule



class TmDBTest {

    @Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {}

    @After
    fun tearDown() {}

}