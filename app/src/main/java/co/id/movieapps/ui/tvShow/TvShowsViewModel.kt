package co.id.movieapps.ui.tvShow

import androidx.lifecycle.MutableLiveData
import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.data.repository.TvShowRepository
import co.id.movieapps.ui.base.BaseViewModel
import co.id.movieapps.ui.base.Constant
import co.id.movieapps.utils.EspressoIdlingResource
import co.id.movieapps.utils.RxUtils

sealed class TvShowState
data class ErrorState(var msg: String?) : TvShowState()
data class TvShowDataLoadedState(val tvShowDomain: List<TvShowDomain>) : TvShowState()
object LoadingState : TvShowState()
object LastPageState : TvShowState()
object DataNotFoundState : TvShowState()
class TvShowsViewModel(private val repository: TvShowRepository) : BaseViewModel() {

    val tvState = MutableLiveData<TvShowState>()

    fun getTvShow(page: Int) {
        EspressoIdlingResource.increment()
        tvState.value = LoadingState
        compositeDisposable.add(
            repository.getTvShow(Constant.API_KEY, Constant.LANG, Constant.SORT_BY, page)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result.isNotEmpty()) {
                        tvState.value = TvShowDataLoadedState(result)
                        EspressoIdlingResource.decrement()
                    } else {
                        if (page == 1) {
                            tvState.value = DataNotFoundState
                        } else {
                            tvState.value = LastPageState
                        }
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        tvState.value = ErrorState(error.message)
    }
}
