package com.arsan.tmdbcatalogue.ui.movies.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.utils.GlideApp
import kotlinx.android.synthetic.main.item_fav_movie.view.*

class FavMoviesAdapter(
    private val context: Context,
    private val movieList: List<FavMovie>,
    private val listener: (FavMovie) -> Unit
) : PagedListAdapter<FavMovie, FavMoviesAdapter.MoviesVH>(DATA_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH = MoviesVH(
        LayoutInflater.from(context).inflate(
            R.layout.item_fav_movie, parent, false
        )
    )

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        val movies = movieList[position]
        holder.bindItems(movies, listener)
        GlideApp.with(context)
            .load(BuildConfig.IMAGE_URL + "w92" + movies.poster_path)
            .into(holder.itemView.img_poster)
    }

    class MoviesVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(movie: FavMovie, listener: (FavMovie) -> Unit) = with(itemView) {
            itemView.tv_item_title.text = movie.title
            itemView.tv_overview.text = movie.overview
            itemView.tv_item_date.text = movie.release_date
            setOnClickListener { listener(movie) }
        }

    }

    companion object  {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<FavMovie>() {
            override fun areItemsTheSame(oldItem: FavMovie, newItem: FavMovie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FavMovie, newItem: FavMovie): Boolean =
                oldItem.id == newItem.id
        }
    }

}