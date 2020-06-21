package co.id.movieapps.data.mapper

import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import co.id.movieapps.data.entity.model.tv.TvShowEntity

class TvShowMapper : BaseMapper<TvShowEntity, TvShowDomain> {
    override fun mapToDomain(model: TvShowEntity): TvShowDomain {
        return TvShowDomain(
            model.description,
            model.originalLanguage,
            model.original_name,
            model.title,
            model.image,
            model.backdropPath,
            model.release_date,
            model.popularity,
            model.voteAverage,
            model.tvId
        )
    }

    override fun mapToModel(domain: TvShowDomain): TvShowEntity {
        return TvShowEntity(
            domain.id,
            domain.name,
            domain.overview,
            domain.posterPath,
            domain.popularity,
            domain.voteAverage,
            domain.originalLanguage,
            domain.first_air_date,
            domain.original_name,
            domain.backdropPath
        )
    }
}