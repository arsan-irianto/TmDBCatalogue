package com.arsan.tmdbcatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.room.AppDatabase
import com.arsan.tmdbcatalogue.ui.movies.MoviesFragment
import com.arsan.tmdbcatalogue.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(MoviesFragment(), "Movies")
        viewPagerAdapter.addFragment(TvShowFragment(), "Tv Show")
        viewpager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewpager)
    }
}
