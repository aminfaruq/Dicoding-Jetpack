package co.id.movieapps.ui.tvShow

import co.id.movieapps.R
import co.id.movieapps.data.TvShowEntity
import co.id.movieapps.utils.loadImage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_row.view.*


class TvShowsListItem(
    private val tvShowEntity: List<TvShowEntity>,
    private val onItemClick: OnItemClick
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        val image = viewHolder.itemView.img_movie
        val title = viewHolder.itemView.tv_title
        val description = viewHolder.itemView.tv_desc_detail

        image.loadImage(tvShowEntity[position].image)
        title.text = tvShowEntity[position].title
        description.text = tvShowEntity[position].description

        viewHolder.itemView.setOnClickListener {
            onItemClick.onClick(tvShowEntity[position])
        }
    }

    override fun getLayout(): Int = R.layout.item_row

    interface OnItemClick {
        fun onClick(item: TvShowEntity)
    }
}