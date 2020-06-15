package co.id.movieapps.ui.detail

import androidx.lifecycle.ViewModel
import co.id.movieapps.data.MovieEntity
import co.id.movieapps.data.TvShowEntity
import co.id.movieapps.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTv(tvId: String) {
        this.tvId = tvId
    }

    fun getMovie(): MovieEntity {
        lateinit var movies: MovieEntity
        val movieEntities = DataDummy.generateDummyMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movies = movieEntity
            }
        }

        return movies
    }

    fun getTv(): TvShowEntity {
        lateinit var tvShows: TvShowEntity
        val tvShowEntities = DataDummy.generateDummyTvShow()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.tvId == tvId) {
                tvShows = tvShowEntity
            }
        }
        return tvShows
    }


}