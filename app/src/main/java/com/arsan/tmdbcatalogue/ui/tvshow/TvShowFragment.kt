package com.arsan.tmdbcatalogue.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.TvShow
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private var viewModel: TvShowViewModel = TvShowViewModel()
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

        if(activity!=null) {
            tvShowAdapter = TvShowAdapter(requireContext(), tvShow) {
                Toast.makeText(requireContext(), "Hello World", Toast.LENGTH_SHORT).show()
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
