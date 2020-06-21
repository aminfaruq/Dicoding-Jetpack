package co.id.movieapps.ui.movies

import androidx.lifecycle.MutableLiveData
import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.data.repository.MovieRepository
import co.id.movieapps.ui.base.BaseViewModel
import co.id.movieapps.ui.base.Constant
import co.id.movieapps.utils.EspressoIdlingResource
import co.id.movieapps.utils.RxUtils

sealed class MovieState
data class ErrorState(var msg: String?) : MovieState()
data class MovieDataLoadedState(val movieDomain: List<MovieDomain>) : MovieState()
object LoadingState : MovieState()
object LastPageState : MovieState()
object DataNotFoundState : MovieState()
class MoviesViewModel(private val repository: MovieRepository) : BaseViewModel() {

    val movieState = MutableLiveData<MovieState>()

    fun getMovies(page: Int) {
        EspressoIdlingResource.increment()
        movieState.value = LoadingState
        compositeDisposable.add(
            repository.getMovies(Constant.API_KEY, Constant.LANG, Constant.SORT_BY, page)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        movieState.value = MovieDataLoadedState(result)
                        EspressoIdlingResource.decrement()
                    } else {
                        if (page == 1) {
                            movieState.value = DataNotFoundState
                        } else {
                            movieState.value = LastPageState
                        }
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        movieState.value = ErrorState(error.message)
    }
}
