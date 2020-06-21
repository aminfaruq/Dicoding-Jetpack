package co.id.movieapps.ui.detail.tv

import androidx.lifecycle.MutableLiveData
import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.repository.DetailTvRepository
import co.id.movieapps.data.repository.GenreTvRepository
import co.id.movieapps.ui.base.BaseViewModel
import co.id.movieapps.ui.base.Constant
import co.id.movieapps.utils.EspressoIdlingResource
import co.id.movieapps.utils.RxUtils

sealed class DetailTvShowState
data class ErrorState(var msg: String?) : DetailTvShowState()
data class DetailTvShowDataLoaded(val detailTvShowDomain: ResponseDetailTvDomain) :
    DetailTvShowState()

object LoadingTvShowState : DetailTvShowState()
object DataTvNotFoundState : DetailTvShowState()

sealed class GenreTvState
data class ErrorGenreTvState(var msg: String?) : GenreTvState()
data class GenreListTvLoaded(val detailGenreDomain: List<GenresItemDomain>) :
    GenreTvState()

object DataGenreTvNotFoundState : GenreTvState()


class DetailTvViewModel(

    private val repository: DetailTvRepository,
    private val genreTvRepository: GenreTvRepository

) : BaseViewModel() {

    val detailTvShowState = MutableLiveData<DetailTvShowState>()
    val detailGenreTvState = MutableLiveData<GenreTvState>()

    fun getDetailTvShow(id: Int) {
        EspressoIdlingResource.increment()
        detailTvShowState.value = LoadingTvShowState
        compositeDisposable.add(
            repository.getDetailTvShow(id, Constant.API_KEY, Constant.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailTvShowState.value = DetailTvShowDataLoaded(result)
                        EspressoIdlingResource.decrement()
                    } else {
                        detailTvShowState.value = DataTvNotFoundState
                    }
                }, this::onError)
        )
    }

    fun getGenreTvShow(id: Int) {
        EspressoIdlingResource.increment()
        compositeDisposable.add(
            genreTvRepository.getGenreTv(id, Constant.API_KEY, Constant.LANG)
                .compose(RxUtils.applySingleAsync())
                .subscribe({ result ->
                    if (result != null) {
                        detailGenreTvState.value = GenreListTvLoaded(
                            result
                        )
                        EspressoIdlingResource.decrement()
                    } else {
                        detailGenreTvState.value = DataGenreTvNotFoundState
                    }
                }, this::onError)
        )
    }

    override fun onError(error: Throwable) {
        detailTvShowState.value = ErrorState(error.message)
        detailGenreTvState.value = ErrorGenreTvState(error.message)
    }
}