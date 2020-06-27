package co.id.movieapps.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.data.local.dao.TvShowDao
import co.id.movieapps.data.mapper.TvShowMapper
import co.id.movieapps.data.remote.ApiInterface
import co.id.movieapps.utils.room.APIResponse
import co.id.movieapps.utils.room.AppExecutors
import co.id.movieapps.utils.room.NetworkBoundResource
import co.id.movieapps.utils.room.Resource
import io.reactivex.Single

class TvShowRepositoryImpl(
    private val service: ApiInterface,
    private val tvShowMapper: TvShowMapper,
    private val executors: AppExecutors,
    private val tvShowDao: TvShowDao
) : TvShowRepository {
    override fun getTvShow(
        apiKey: String,
        language: String,
        shortBy: String,
        page: Int
    ): Single<List<TvShowDomain>> {
        return service.getTvShows(apiKey, language, shortBy, page).map {
            it.result.let { tv -> tvShowMapper.mapToListDomain(tv) }
        }
    }

    fun getTvShowAsPaged(): DataSource.Factory<Int, ResponseDetailTvDomain> {
        return tvShowDao.getFavTvPagination()
    }

    fun getTvShowPage(): LiveData<Resource<PagedList<ResponseDetailTvDomain>>> {
        return object :
            NetworkBoundResource<PagedList<ResponseDetailTvDomain>, List<ResponseDetailTvDomain>>(
                executors
            ) {
            override fun loadFromDB(): LiveData<PagedList<ResponseDetailTvDomain>> {
                return LivePagedListBuilder(
                    getTvShowAsPaged(), 20
                ).build()
            }

            override fun shouldFetch(data: PagedList<ResponseDetailTvDomain>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<ResponseDetailTvDomain>>>? {
                return null
            }

            override fun saveCallResult(data: List<ResponseDetailTvDomain>) {

            }
        }.asLiveData()
    }
}