package co.id.movieapps.data.mapper

import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.entity.model.tv.ResponseDetailTv

class DetailTvMapper : BaseMapper<ResponseDetailTv, ResponseDetailTvDomain> {
    override fun mapToDomain(model: ResponseDetailTv): ResponseDetailTvDomain {
        return ResponseDetailTvDomain(
            model.originalLanguage,
            model.numberOfEpisodes,
            model.type,
            model.backdropPath,
            model.popularity,
            model.id,
            model.numberOfSeasons,
            model.voteCount,
            model.firstAirDate,
            model.overview,
            model.posterPath,
            model.originalName,
            model.voteAverage,
            model.name,
            model.inProduction,
            model.lastAirDate,
            model.homepage,
            model.status
        )
    }

    override fun mapToModel(domain: ResponseDetailTvDomain): ResponseDetailTv {
        return ResponseDetailTv(
            domain.originalLanguage,
            domain.numberOfEpisodes,
            domain.type,
            domain.backdropPath,
            domain.popularity,
            domain.id,
            domain.numberOfSeasons,
            domain.voteCount,
            domain.firstAirDate,
            domain.overview,
            domain.posterPath,
            domain.originalName,
            domain.voteAverage,
            domain.name,
            domain.inProduction,
            domain.lastAirDate,
            domain.homepage,
            domain.status
        )
    }
}