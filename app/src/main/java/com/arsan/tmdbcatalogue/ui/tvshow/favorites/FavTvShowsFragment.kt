package com.arsan.tmdbcatalogue.ui.tvshow.favorites


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.ui.home.HomeActivity
import com.arsan.tmdbcatalogue.ui.tvshow.details.DetailTvShowActivity
import com.arsan.tmdbcatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_fav_tv_shows.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavTvShowsFragment : Fragment() {

    private val viewModel: FavTvShowsVM by viewModel()
    private var tvShow: MutableList<FavTvshow> = mutableListOf()
    private lateinit var favTvshowsAdapter: FavTvshowsAdapter

    companion object {
        fun instance(): FavTvShowsFragment = FavTvShowsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_tv_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sr_fav_tvshow.isRefreshing = false
        if (activity != null) {

            loadFavTvShow()

            favTvshowsAdapter = FavTvshowsAdapter(requireContext(), tvShow) {
                val intent = Intent(requireContext(), DetailTvShowActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("title", it.name)
                    putExtra("overview", it.overview)
                    putExtra("poster_path", it.poster_path)
                    putExtra("backdrop_path", it.backdrop_path)
                    putExtra("vote_average", it.vote_average)
                }
                startActivity(intent)
            }
            rv_fav_tvshow.adapter = favTvshowsAdapter
            rv_fav_tvshow.layoutManager = LinearLayoutManager(requireContext())
        }

        sr_fav_tvshow.setOnRefreshListener {
            loadFavTvShow()
        }

    }

    private fun loadFavTvShow() {
        viewModel.setData("FavTvShowList")
        viewModel.liveData.observe(activity as HomeActivity, Observer {
            when (it.status) {
                Status.LOADING -> pb_fav_tvshow.visibility = View.VISIBLE
                Status.SUCCESS -> it.data?.let { data ->
                    favTvshowsAdapter.submitList(data)
                    showTv(data)
                }
                Status.ERROR -> {
                    pb_fav_tvshow.visibility = View.GONE
                }
            }
        })
        sr_fav_tvshow.isRefreshing = false
    }

    private fun showTv(data: List<FavTvshow>) {
        tvShow.clear()
        tvShow.addAll(data)
        favTvshowsAdapter.notifyDataSetChanged()
        sr_fav_tvshow.isRefreshing = false
        pb_fav_tvshow.visibility = View.INVISIBLE
    }
}
