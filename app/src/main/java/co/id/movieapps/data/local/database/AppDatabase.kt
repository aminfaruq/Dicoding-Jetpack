package co.id.movieapps.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.id.movieapps.data.entity.domain.movie.ResponseDetailMovieDomain
import co.id.movieapps.data.entity.domain.tv.ResponseDetailTvDomain
import co.id.movieapps.data.local.dao.MoviesDao
import co.id.movieapps.data.local.dao.TvShowDao
import co.id.movieapps.ui.base.Constant

@Database(
    entities = [
        ResponseDetailMovieDomain::class,
        ResponseDetailTvDomain::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowDao(): TvShowDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        Constant.NAME_DATABASE
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}