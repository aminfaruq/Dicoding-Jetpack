package co.id.movieapps.data.repository

import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.entity.domain.tv.TvShowDomain
import io.reactivex.Single

interface MovieRepository {
    fun getMovies(
        apiKey: String,
        language: String,
        shortBy: String,
        page: Int
    ): Single<List<MovieDomain>>
}

interface TvShowRepository {
    fun getTvShow(
        apiKey: String,
        language: String,
        shortBy: String,
        page: Int
    ): Single<List<TvShowDomain>>
}

interface DetailRepository {
    fun getDetailMovie(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<ResponseDetailMovieDomain>
}

interface GenreMovieRepository {
    fun getGenreMovie(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<List<GenresItemDomain>>
}

interface GenreTvRepository {
    fun getGenreTv(
        tvId: Int,
        apiKey: String,
        language: String
    ): Single<List<GenresItemDomain>>
}


interface DetailTvRepository {
    fun getDetailTvShow(
        tvId: Int,
        apiKey: String,
        language: String
    ): Single<ResponseDetailTvDomain>
}

