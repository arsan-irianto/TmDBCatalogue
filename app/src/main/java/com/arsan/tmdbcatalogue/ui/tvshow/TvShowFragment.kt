package com.arsan.tmdbcatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.ui.home.HomeActivity
import com.arsan.tmdbcatalogue.ui.tvshow.details.DetailTvShowActivity
import com.arsan.tmdbcatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private val viewModel: TvShowViewModel by viewModel()
    private var tvShow: MutableList<TvShow> = mutableListOf()
    private lateinit var tvShowAdapter: TvShowAdapter

    companion object {
        fun instance(): TvShowFragment = TvShowFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sr_tvshow.isRefreshing = false
        if (activity != null) {

            loadDataTvShow()

            tvShowAdapter = TvShowAdapter(requireContext(), tvShow) {
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
            rv_tvshow.adapter = tvShowAdapter
            rv_tvshow.layoutManager = LinearLayoutManager(requireContext())
        }

        sr_tvshow.setOnRefreshListener {
            loadDataTvShow()
        }

    }

    private fun loadDataTvShow() {
        viewModel.setData("TvShowList")
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> pb_tvshow.visibility = View.VISIBLE
                Status.SUCCESS -> it.data?.let { data -> showTv(data) }
                Status.ERROR -> {
                    pb_tvshow.visibility = View.GONE
                }
            }
        })
        sr_tvshow.isRefreshing = false
    }

    private fun showTv(data: List<TvShow>) {
        tvShow.clear()
        tvShow.addAll(data)
        tvShowAdapter.notifyDataSetChanged()
        sr_tvshow.isRefreshing = false
        pb_tvshow.visibility = View.INVISIBLE
    }

}
