package co.id.movieapps.data.repository

import co.id.movieapps.data.entity.domain.movie.MovieDomain
import co.id.movieapps.data.mapper.MovieMapper
import co.id.movieapps.data.remote.ApiInterface
import io.reactivex.Single

class MovieRepositoryImpl(
    private val service: ApiInterface,
    private val movieMapper: MovieMapper
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

}