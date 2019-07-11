package com.arsan.tmdbcatalogue.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsan.academies.utils.GlideApp
import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
    private val listener: (Movie) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH = MoviesVH(
        LayoutInflater.from(context).inflate(
            R.layout.item_movie, parent, false
        )
    )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        val movies = movieList[position]
        holder.bindItems(movies, listener)
        GlideApp.with(context)
            .load(BuildConfig.IMAGE_URL + "w92" + movies.poster_path)
            .into(holder.itemView.img_poster)
    }

    class MoviesVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(movie: Movie, listener: (Movie) -> Unit) = with(itemView) {
            itemView.tv_item_title.text = movie.title
            itemView.tv_overview.text = movie.overview
            itemView.tv_item_date.text = movie.release_date
            setOnClickListener { listener(movie) }
        }

    }

}