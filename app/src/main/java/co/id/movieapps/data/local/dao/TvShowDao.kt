package co.id.movieapps.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import io.reactivex.Single

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tb_detail_tv ORDER BY id ASC")
    fun getFavoriteTv(): Single<List<ResponseDetailTvDomain>>

    @Query("SELECT * FROM tb_detail_tv WHERE id=:idTv")
    fun getFavoriteTvById(idTv: Int): Single<List<ResponseDetailTvDomain>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavoriteTv(tvShow: ResponseDetailTvDomain)

    @Query("DELETE FROM tb_detail_tv WHERE id =:idTv ")
    fun deleteFavoriteTv(idTv: Int)

    @Query("SELECT * FROM tb_detail_tv ORDER BY  id ASC")
    fun getFavTvPagination(): DataSource.Factory<Int, ResponseDetailTvDomain>
}