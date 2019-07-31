package com.arsan.tmdbcatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.ui.FavoritesPageFragment
import com.arsan.tmdbcatalogue.ui.movies.MoviesFragment
import com.arsan.tmdbcatalogue.ui.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_new.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_new)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(MoviesFragment.instance())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_movie -> {
                val moviesFragment = MoviesFragment.instance()
                openFragment(moviesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tvshow -> {
                val tvShowFragment = TvShowFragment.instance()
                openFragment(tvShowFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                val favoritesPageFragment = FavoritesPageFragment.instance()
                openFragment(favoritesPageFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.page_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
