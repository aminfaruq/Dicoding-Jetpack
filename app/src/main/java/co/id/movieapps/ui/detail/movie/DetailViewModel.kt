package co.id.movieapps.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.local.dao.MoviesDao
import co.id.movieapps.data.repository.DetailRepository
import co.id.movieapps.data.repository.GenreMovieRepository
import co.id.movieapps.ui.base.BaseViewModel
import co.id.movieapps.ui.base.Constant
import co.id.movieapps.utils.espresso.EspressoIdlingResource
import co.id.movieapps.utils.ui.RxUtils
import java.util.concurrent.Executor

//Detail Movie
sealed class DetailMovieState
data class ErrorState(var msg: String?) : DetailMovieState()
data class DetailMovieDataLoaded(val detailMovieDomain: ResponseDetailMovieDomain) :
    DetailMovieState()

data class FavMovieDataFound(val detailMovieDomain: List<ResponseDetailMovieDomain>) :
    DetailMovieState()

object LoadingState : DetailMovieState()
object DataNotFoundState : DetailMovieState()
object FavMovieSave : DetailMovieState()
object RemoveMovieFav : DetailMovieState()

//Detail Genre Movie
sealed class GenreMovieState
data class ErrorGenreState(var msg: String?) : GenreMovieState()
data class GenreListLoaded(val detailGenreDomain: List<GenresItemDomain>) :
    GenreMovieState()

object DataGenreNotFoundState : GenreMovieState()

class DetailViewModel(
    private val repository: DetailRepository,
    private val genreMovieRepository: GenreMovieRepository,
    private val movieDao: MoviesDao,
    private val executor: Executor
) : BaseViewModel() {

    val detailMovieState = MutableLiveData<DetailMovieState>()
    val detailGenreMovieState = MutableLiveData<GenreMovieState>()

    fun saveFavMovieSave(movies: ResponseDetailMovieDomain) {
        executor.execute { movieDao.insertFavoriteMovie(movies) }
        detailMovieState.value = FavMovieSave
    }

    fun removeMovieFav(idMovie: Int) {
        executor.execute { movieDao.deleteFavoriteMovie(idMovie) }
        detailMovieState.value = RemoveMovieFav
    }

    fun checkFavMovie(id: Int) {
        compositeDisposable.add(
            movieDao.getFavoriteMovieById(id)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        detailMovieState.value = FavMovieDataFound(
                            result
                        )
                    }
                }, this::onError)
        )
    }

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