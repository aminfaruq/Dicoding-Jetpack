package co.id.movieapps.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.repository.DetailRepository
import co.id.movieapps.data.repository.GenreMovieRepository
import co.id.movieapps.ui.base.BaseViewModel
import co.id.movieapps.ui.base.Constant
import co.id.movieapps.utils.EspressoIdlingResource
import co.id.movieapps.utils.RxUtils

sealed class DetailMovieState
data class ErrorState(var msg: String?) : DetailMovieState()
data class DetailMovieDataLoaded(val detailMovieDomain: ResponseDetailMovieDomain) :
    DetailMovieState()

object LoadingState : DetailMovieState()
object DataNotFoundState : DetailMovieState()

sealed class GenreMovieState
data class ErrorGenreState(var msg: String?) : GenreMovieState()
data class GenreListLoaded(val detailGenreDomain: List<GenresItemDomain>) :
    GenreMovieState()

object DataGenreNotFoundState : GenreMovieState()

class DetailViewModel(
    private val repository: DetailRepository,
    private val genreMovieRepository: GenreMovieRepository
) : BaseViewModel() {

    val detailMovieState = MutableLiveData<DetailMovieState>()
    val detailGenreMovieState = MutableLiveData<GenreMovieState>()

    fun getDetailMovie(id: Int) {
        EspressoIdlingResource.increment()
        detailMovieState.value = LoadingState
        compositeDisposable.add(
            repository.getDetailMovie(id, Constant.API_KEY, Constant.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailMovieState.value =
                            DetailMovieDataLoaded(
                                result
                            )
                        EspressoIdlingResource.decrement()
                    } else {
                        detailMovieState.value =
                            DataNotFoundState
                    }
                }, this::onError)
        )
    }

    fun getGenreMovie(id: Int) {
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            genreMovieRepository.getGenreMovie(id, Constant.API_KEY, Constant.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailGenreMovieState.value =
                            GenreListLoaded(
                                result
                            )
                        EspressoIdlingResource.decrement()
                    } else {
                        detailGenreMovieState.value =
                            DataGenreNotFoundState
                    }
                }, this::onError)
        )
    }


    override fun onError(error: Throwable) {
        detailMovieState.value =
            ErrorState(error.message)
        detailGenreMovieState.value =
            ErrorGenreState(error.message)
    }

}