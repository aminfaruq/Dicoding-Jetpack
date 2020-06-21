package co.id.movieapps.ui.tvShow

import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.utils.loadImage
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_row.view.*


class TvShowsListItem(
    private val tvShowEntity: TvShowDomain,
    private val onItemClick: OnItemClick
) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {

        val image = viewHolder.itemView.img_movie
        val title = viewHolder.itemView.tv_title
        val description = viewHolder.itemView.tv_desc_detail

        tvShowEntity.posterPath?.let { image.loadImage(it) }
        title.text = tvShowEntity.name
        description.text = tvShowEntity.overview

        viewHolder.itemView.setOnClickListener {
            onItemClick.onClick(tvShowEntity)
        }
    }

    override fun getLayout(): Int = R.layout.item_row

    interface OnItemClick {
        fun onClick(item: TvShowDomain)
    }
}