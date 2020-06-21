package co.id.movieapps.ui.detail.movie

import co.id.movieapps.R
import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.genre_row.view.*

class GenreListItemView(
    private val genresItemDomain: GenresItemDomain
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val title = viewHolder.itemView.tv_genre
        title.text = genresItemDomain.name
    }

    override fun getLayout(): Int {
        return R.layout.genre_row
    }
}