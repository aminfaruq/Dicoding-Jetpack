package co.id.movieapps.ui.base

import co.id.movieapps.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class LoadMoreItemView : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {}

    override fun getLayout(): Int = R.layout.load_more_progress
}