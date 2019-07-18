package com.arsan.tmdbcatalogue.ui.tvshow


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.TvShow
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private var tvShow: MutableList<TvShow> = mutableListOf()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        viewModel.tvData().observe(viewLifecycleOwner, Observer {
            showTv(it.results)
        })

        if (activity != null) {
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

    }

    private fun showTv(data: List<TvShow>) {
        tvShow.clear()
        tvShow.addAll(data)
        tvShowAdapter.notifyDataSetChanged()
    }

}
