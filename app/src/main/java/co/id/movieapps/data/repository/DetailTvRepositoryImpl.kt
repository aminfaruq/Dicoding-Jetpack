package co.id.movieapps.data.repository

import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.mapper.DetailTvMapper
import co.id.movieapps.data.remote.ApiInterface
import io.reactivex.Single

class DetailTvRepositoryImpl(
    private val service: ApiInterface,
    private val detailTvMapper: DetailTvMapper
) : DetailTvRepository {
    override fun getDetailTvShow(
        tvId: Int,
        apiKey: String,
        language: String
    ): Single<ResponseDetailTvDomain> {
        return service.getDetailTvShow(tvId, apiKey, language).map {
            detailTvMapper.mapToDomain(it)
        }
    }

}