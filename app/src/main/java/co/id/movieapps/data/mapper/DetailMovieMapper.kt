package co.id.movieapps.data.mapper

import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.entity.model.movie.ResponseDetailMovie

class DetailMovieMapper : BaseMapper<ResponseDetailMovie, ResponseDetailMovieDomain> {
    override fun mapToDomain(model: ResponseDetailMovie): ResponseDetailMovieDomain {
        return ResponseDetailMovieDomain(
            model.originalLanguage,
            model.title,
            model.backdropPath,
            model.popularity,
            model.id,
            model.voteCount,
            model.overview,
            model.originalTitle,
            model.posterPath,
            model.releaseDate,
            model.voteAverage,
            model.tagline,
            model.homepage,
            model.status
        )
    }

    override fun mapToModel(domain: ResponseDetailMovieDomain): ResponseDetailMovie {
        return ResponseDetailMovie(
            domain.originalLanguage,
            domain.title,
            domain.backdropPath,
            domain.popularity,
            domain.id,
            domain.voteCount,
            domain.overview,
            domain.originalTitle,
            domain.posterPath,
            domain.releaseDate,
            domain.voteAverage,
            domain.tagline,
            domain.homepage,
            domain.status
        )
    }
}