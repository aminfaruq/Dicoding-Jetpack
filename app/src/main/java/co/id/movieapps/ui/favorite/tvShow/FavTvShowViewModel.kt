package co.id.movieapps.ui.favorite.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.repository.TvShowRepositoryImpl
import co.id.movieapps.utils.room.Resource

class FavTvShowViewModel(repositoryImpl: TvShowRepositoryImpl) : ViewModel() {

    val getTvShowPage: LiveData<Resource<PagedList<ResponseDetailTvDomain>>> =
        repositoryImpl.getTvShowPage()

}
