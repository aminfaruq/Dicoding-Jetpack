package co.id.movieapps.ui.favorite.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.utils.ui.loadImage
import kotlinx.android.synthetic.main.item_row.view.*

class FavMovieAdapter(private val context: Context, private val onItemClick: OnItemClick) :
    PagedListAdapter<ResponseDetailMovieDomain, FavMovieAdapter.ViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResponseDetailMovieDomain>() {
            override fun areItemsTheSame(
                oldItem: ResponseDetailMovieDomain,
                newItem: ResponseDetailMovieDomain
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseDetailMovieDomain,
                newItem: ResponseDetailMovieDomain
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        if (movie != null) {
            movie.posterPath?.let { holder.img.loadImage(it) }
            holder.title.text = movie.originalTitle
            holder.description.text = movie.overview
            holder.itemView.setOnClickListener {
                onItemClick.onClick(movie)
            }

        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val img: ImageView = itemView.img_movie
        val title: TextView = itemView.tv_title
        val description: TextView = itemView.tv_desc_detail

    }

    interface OnItemClick {
        fun onClick(item: ResponseDetailMovieDomain)
    }

}