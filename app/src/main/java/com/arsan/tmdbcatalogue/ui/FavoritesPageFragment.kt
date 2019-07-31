package com.arsan.tmdbcatalogue.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.ui.home.ViewPagerAdapter
import com.arsan.tmdbcatalogue.ui.movies.favorites.FavMoviesFragment
import com.arsan.tmdbcatalogue.ui.tvshow.favorites.FavTvShowsFragment
import kotlinx.android.synthetic.main.fragment_favorites_page.*

class FavoritesPageFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        favorites_view_pager.adapter = viewPagerAdapter
        viewPagerAdapter.addFragment(FavMoviesFragment.instance(), "Movies")
        viewPagerAdapter.addFragment(FavTvShowsFragment.instance(), "Tv Show")
        viewPagerAdapter.notifyDataSetChanged()
        tabs.setupWithViewPager(favorites_view_pager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites_page, container, false)
    }

    companion object {
        fun instance(): FavoritesPageFragment = FavoritesPageFragment()
    }

}
