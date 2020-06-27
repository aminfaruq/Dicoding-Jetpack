package co.id.movieapps.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM tb_detail_movie ORDER BY id ASC")
    fun getFavoriteMovie(): Single<List<ResponseDetailMovieDomain>>

    @Query("SELECT * FROM tb_detail_movie WHERE id=:idMovie")
    fun getFavoriteMovieById(idMovie: Int): Single<List<ResponseDetailMovieDomain>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteMovie(movies: ResponseDetailMovieDomain)

    @Query("DELETE FROM tb_detail_movie WHERE id=:idMovie")
    fun deleteFavoriteMovie(idMovie: Int)

    @Query("SELECT * FROM tb_detail_movie ORDER BY id ASC")
    fun getFavMoviePagination(): DataSource.Factory<Int, ResponseDetailMovieDomain>
}