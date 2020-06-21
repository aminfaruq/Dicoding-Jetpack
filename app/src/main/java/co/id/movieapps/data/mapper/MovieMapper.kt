package co.id.movieapps.data.mapper

import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.data.entity.model.movie.MovieEntity

class MovieMapper : BaseMapper<MovieEntity, MovieDomain> {
    override fun mapToDomain(model: MovieEntity): MovieDomain {
        return MovieDomain(
            model.description,
            model.originalLanguage,
            model.title,
            model.title,
            model.image,
            model.backdropPath,
            model.release_date,
            model.popularity,
            model.voteAverage,
            model.movieId
        )
    }

    override fun mapToModel(domain: MovieDomain): MovieEntity {
        return MovieEntity(
            domain.id,
            domain.title,
            domain.overview,
            domain.posterPath,
            domain.popularity,
            domain.voteAverage,
            domain.originalLanguage,
            domain.releaseDate,
            domain.backdropPath
        )
    }
}