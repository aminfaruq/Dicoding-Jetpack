package co.id.movieapps.data.remote

import co.id.movieapps.data.entity.model.movie.MovieEntity
import co.id.movieapps.data.entity.model.movie.ResponseDetailMovie
import co.id.movieapps.data.entity.model.movie.ResponseGenreMovie
import co.id.movieapps.data.entity.model.tv.ResponseDetailTv
import co.id.movieapps.data.entity.model.tv.TvShowEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ): Single<MovieEntity.ResponseMovie>

    @GET("3/discover/tv")
    fun getTvShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int
    ): Single<TvShowEntity.ResponseTv>

    @GET("3/movie/{movie_id}")
    fun getDetailMovies(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Single<ResponseDetailMovie>

    @GET("3/movie/{movie_id}")
    fun getDetailGenre(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Single<ResponseGenreMovie>


    @GET("3/tv/{tv_id}")
    fun getDetailTvGenre(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Single<ResponseGenreMovie>


    @GET("3/tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Single<ResponseDetailTv>

}