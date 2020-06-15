package co.id.movieapps.ui.tvShow

import androidx.lifecycle.ViewModel
import co.id.movieapps.data.TvShowEntity
import co.id.movieapps.utils.DataDummy

class TvShowsViewModel : ViewModel() {

    fun getTvShow(): List<TvShowEntity> = DataDummy.generateDummyTvShow()
}
