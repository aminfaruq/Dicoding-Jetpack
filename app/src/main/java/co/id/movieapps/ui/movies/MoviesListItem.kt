package co.id.movieapps.ui.movies

import co.id.movieapps.R
import co.id.movieapps.data.MovieEntity
import co.id.movieapps.utils.loadImage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_row.view.*


class MoviesListItem(
    private val movieEntity: List<MovieEntity>,
    private val onItemClick: OnItemClick
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        val image = viewHolder.itemView.img_movie
        val title = viewHolder.itemView.tv_title
        val description = viewHolder.itemView.tv_desc_detail

        image.loadImage(movieEntity[position].image)
        title.text = movieEntity[position].title
        description.text = movieEntity[position].description

        viewHolder.itemView.setOnClickListener {
            onItemClick.onClick(movieEntity[position])
        }

    }

    override fun getLayout(): Int = R.layout.item_row

    interface OnItemClick {
        fun onClick(item: MovieEntity)
    }

}