package co.id.movieapps.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.local.dao.MoviesDao
import co.id.movieapps.data.mapper.MovieMapper
import co.id.movieapps.data.remote.ApiInterface
import co.id.movieapps.utils.room.APIResponse
import co.id.movieapps.utils.room.AppExecutors
import co.id.movieapps.utils.room.NetworkBoundResource
import co.id.movieapps.utils.room.Resource
import io.reactivex.Single

class MovieRepositoryImpl(
    private val service: ApiInterface,
    private val movieMapper: MovieMapper,
    private val movieDao: MoviesDao,
    val executors: AppExecutors

) : MovieRepository {
    override fun getMovies(
        apiKey: String,
        language: String,
        shortBy: String,
        page: Int
    ): Single<List<MovieDomain>> {
        return service.getMovies(apiKey, language, shortBy, page).map {
            it.results.let { movies ->
                movieMapper.mapToListDomain(movies)
            }
        }
    }

    fun getMoviesAsPaged(): DataSource.Factory<Int, ResponseDetailMovieDomain> {
        return movieDao.getFavMoviePagination()
    }

    fun getMoviesAsPage(): LiveData<Resource<PagedList<ResponseDetailMovieDomain>>> {
        return object :
            NetworkBoundResource<PagedList<ResponseDetailMovieDomain>, List<ResponseDetailMovieDomain>>(
                executors
            ) {
            override fun loadFromDB(): LiveData<PagedList<ResponseDetailMovieDomain>> {
                return LivePagedListBuilder(
                    getMoviesAsPaged(), 20
                ).build()
            }

            override fun shouldFetch(data: PagedList<ResponseDetailMovieDomain>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<APIResponse<List<ResponseDetailMovieDomain>>>? {
                return null
            }

            override fun saveCallResult(data: List<ResponseDetailMovieDomain>) {

            }
        }.asLiveData()
    }

}