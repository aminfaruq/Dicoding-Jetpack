package co.id.movieapps.ui.movies

import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.utils.ui.loadImage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_row.view.*


class MoviesListItem(
    private val movieEntity: MovieDomain,
    private val onItemClick: OnItemClick
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        val image = viewHolder.itemView.img_movie
        val title = viewHolder.itemView.tv_title
        val description = viewHolder.itemView.tv_desc_detail

        movieEntity.posterPath?.let { image.loadImage(it) }
        title.text = movieEntity.title
        description.text = movieEntity.overview

        viewHolder.itemView.setOnClickListener {
            onItemClick.onClick(movieEntity)
        }

    }

    override fun getLayout(): Int = R.layout.item_row

    interface OnItemClick {
        fun onClick(item: MovieDomain)
    }

}