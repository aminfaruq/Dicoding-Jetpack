package co.id.movieapps.data.repository

import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.mapper.DetailMovieMapper
import co.id.movieapps.data.remote.ApiInterface
import io.reactivex.Single

class DetailMovieRepositoryImpl(
    private val service: ApiInterface,
    private val detailMovieMapper: DetailMovieMapper
) : DetailRepository {
    override fun getDetailMovie(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<ResponseDetailMovieDomain> {
        return service.getDetailMovies(movieId, apiKey, language).map {
            detailMovieMapper.mapToDomain(it)
        }
    }
}