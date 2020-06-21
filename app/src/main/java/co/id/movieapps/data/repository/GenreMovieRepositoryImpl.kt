package co.id.movieapps.data.repository

import co.id.movieapps.data.entity.domain.movie.GenresItemDomain
import co.id.movieapps.data.mapper.GenreItemMapper
import co.id.movieapps.data.remote.ApiInterface
import io.reactivex.Single

class GenreMovieRepositoryImpl(
    private val service: ApiInterface,
    private val genreMapper: GenreItemMapper
) : GenreMovieRepository {
    override fun getGenreMovie(
        movieId: Int,
        apiKey: String,
        language: String
    ): Single<List<GenresItemDomain>> {
        return service.getDetailGenre(movieId, apiKey, language).map {
            it.genres?.let { it1 -> genreMapper.mapToListDomain(it1) }
        }
    }


}