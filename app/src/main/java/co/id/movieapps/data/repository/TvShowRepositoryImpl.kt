package co.id.movieapps.data.repository

import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.data.mapper.TvShowMapper
import co.id.movieapps.data.remote.ApiInterface
import io.reactivex.Single

class TvShowRepositoryImpl(
    private val service: ApiInterface,
    private val tvShowMapper: TvShowMapper
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
}