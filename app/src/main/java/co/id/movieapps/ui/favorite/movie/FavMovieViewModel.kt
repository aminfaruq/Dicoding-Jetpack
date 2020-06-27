package co.id.movieapps.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.repository.MovieRepositoryImpl
import co.id.movieapps.utils.room.Resource

class FavMovieViewModel(repositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val getMoviePage: LiveData<Resource<PagedList<ResponseDetailMovieDomain>>> =
        repositoryImpl.getMoviesAsPage()
}
