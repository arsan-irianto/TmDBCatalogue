package com.arsan.tmdbcatalogue.ui.tvshow.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.utils.GlideApp
import kotlinx.android.synthetic.main.item_tvshow.view.*

class FavTvshowsAdapter(
    private val context: Context,
    private val tvList: List<FavTvshow>,
    private val listener: (FavTvshow) -> Unit
) :
    RecyclerView.Adapter<FavTvshowsAdapter.TvShowVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowVH = TvShowVH(
        LayoutInflater.from(context).inflate(
            R.layout.item_fav_tvshow, parent, false
        )
    )

    override fun getItemCount(): Int = tvList.size

    override fun onBindViewHolder(holder: TvShowVH, position: Int) {
        val tv = tvList[position]
        holder.bindItems(tv, listener)
        GlideApp.with(context)
            .load(BuildConfig.IMAGE_URL + "w92" + tv.poster_path)
            .into(holder.itemView.img_poster_path)
    }

    class TvShowVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(tvShow: FavTvshow, listener: (FavTvshow) -> Unit) = with(itemView) {
            itemView.tv_name.text = tvShow.name
            itemView.tv_overview.text = tvShow.overview
            itemView.tv_first_air_date.text = tvShow.first_air_date
            setOnClickListener { listener(tvShow) }
        }

    }

}